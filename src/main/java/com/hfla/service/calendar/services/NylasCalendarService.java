package com.hfla.service.calendar.services;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import com.hfla.service.calendar.pojos.EventsInteface;
import com.hfla.service.calendar.pojos.Nylas.NylasCalendar;
import com.hfla.service.calendar.pojos.Nylas.NylasCalendars;
import com.hfla.service.calendar.pojos.Nylas.NylasEvents;
import com.nylas.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NylasCalendarService implements CalendarService{

  @Value("${access.token}")
  private String accessToken;


  private static final Logger log = LogManager.getLogger(NylasCalendarService.class);

  private static NylasClient client = new NylasClient();
  

  public NylasCalendars getCalendars() throws RequestFailedException, IOException {
      NylasAccount account = client.account(accessToken);
      NylasCalendars calendars = new NylasCalendars(account.calendars().list());
      return calendars;
  }

    @Override
    public String createEvent(EventsInteface event) {
        return null;
    }


    public NylasCalendar getPrimaryCalendar() throws IOException, RequestFailedException {
      NylasAccount account = client.account(accessToken);

      RemoteCollection<Calendar> calendars = account.calendars().list();
      NylasCalendar primary = null;

      for (Calendar calendar : calendars) {
          if (!calendar.isReadOnly() && calendar.getName().contains("@")) {
              primary = (NylasCalendar) calendar;
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

  public List<TimeSlot> checkAvailability(Instant start, Instant end) throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendars calendars = account.calendars();
    Calendar primaryCalendar = getPrimaryCalendar();
    String accountId = primaryCalendar.getAccountId();
    String calendarId = primaryCalendar.getId();

    FreeBusyCalendars freeBusyCalendars = new FreeBusyCalendars(accountId, Collections.singletonList(calendarId));
      SingleAvailabilityQuery query = new SingleAvailabilityQuery()
        .startTime(start)
        .endTime(end)
       
        .calendars(freeBusyCalendars);
    Availability availability = calendars.availability(query);
    
    return availability.getTimeSlots();
  }


    public NylasEvents getEvents() throws IOException, RequestFailedException {
      NylasAccount account = client.account(accessToken);
      Calendar primaryCalendar = getPrimaryCalendar();
      String calendarId = primaryCalendar.getId();
      System.out.println(primaryCalendar);


      EventQuery query = new EventQuery().calendarId(calendarId).startsAfter(Instant.now()).limit(5);
      NylasEvents events = new NylasEvents( account.events().list(query));

      return events;
  }
}
