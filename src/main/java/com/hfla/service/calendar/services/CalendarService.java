package com.hfla.service.calendar.services;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.Availability;
import com.nylas.Calendar;
import com.nylas.Calendars;
import com.nylas.Event;
import com.nylas.EventQuery;
import com.nylas.FreeBusy;
import com.nylas.FreeBusyCalendars;
import com.nylas.FreeBusyQuery;
import com.nylas.NylasAccount;
import com.nylas.NylasClient;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;
import com.nylas.SingleAvailabilityQuery;
import com.nylas.TimeSlot;

@Component
public class CalendarService {

  @Value("${access.token}")
  private String accessToken;

  private static final Logger log = LogManager.getLogger(CalendarService.class);

  private static NylasClient client = new NylasClient();

  public RemoteCollection<Calendar> getCalendars() throws IOException, RequestFailedException {
      NylasAccount account = client.account(accessToken);
      RemoteCollection<Calendar> calendars = account.calendars().list();
      return calendars;
  }

  public Calendar getPrimaryCalendar() throws IOException, RequestFailedException {
      NylasAccount account = client.account(accessToken);

      RemoteCollection<Calendar> calendars = account.calendars().list();
      Calendar primary = null;

      for (Calendar calendar : calendars) {
          if (!calendar.isReadOnly() && calendar.getName().contains("@")) {
              primary = calendar;
              break;
          }
      }

      if(primary == null) {
          log.info("Unable to find primary calendar");
          throw new IllegalStateException("No primary calendar found");
      }

      return primary;
  }

  public List<FreeBusy> checkFreeBusy() throws IOException, RequestFailedException {
      NylasAccount account = client.account(accessToken);
      Calendars calendars = account.calendars();
      Calendar primaryCalendar = getPrimaryCalendar();
      
      Instant end = ZonedDateTime.now().toInstant();
      Instant start = end.minus(30, ChronoUnit.DAYS);

      FreeBusyQuery query = new FreeBusyQuery().startTime(start.getEpochSecond()).endTime(end.getEpochSecond()).emails(primaryCalendar.getName());
      List<FreeBusy> freeBusy = calendars.checkFreeBusy(query);

      return freeBusy;
  }

  public List<TimeSlot> checkAvailability() throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendars calendars = account.calendars();
    Calendar primaryCalendar = getPrimaryCalendar();
    String accountId = primaryCalendar.getAccountId();
    String calendarId = primaryCalendar.getId();

    FreeBusyCalendars freeBusyCalendars = new FreeBusyCalendars(accountId, Collections.singletonList(calendarId));
      SingleAvailabilityQuery query = new SingleAvailabilityQuery()
        .startTime(Instant.now().minus(10, ChronoUnit.HOURS))
        .endTime(Instant.now().plus(4, ChronoUnit.HOURS))
        .durationMinutes(30)
        .intervalMinutes(10)
        .calendars(freeBusyCalendars);
    Availability availability = calendars.availability(query);
    
    return availability.getTimeSlots();
  }

  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
      NylasAccount account = client.account(accessToken);
      Calendar primaryCalendar = getPrimaryCalendar();
      String calendarId = primaryCalendar.getId();
      System.out.println(primaryCalendar);


      EventQuery query = new EventQuery().calendarId(calendarId).startsAfter(Instant.now()).limit(5);
      RemoteCollection<Event> events = account.events().list(query);

      return events;
  }
}
