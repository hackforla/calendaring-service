package com.hfla.service.calendar.services;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.Calendar;
import com.nylas.Event;
import com.nylas.EventQuery;
import com.nylas.NylasAccount;
import com.nylas.NylasClient;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;

@Component
public class EventService {

  private final CalendarService calendarService;
  private static NylasClient client = new NylasClient();

  @Value("${access.token}")
  private String accessToken;

  @Autowired
  public EventService(CalendarService calendarService) {
    this.calendarService = calendarService;
  }

  // The the next 50 events from the next 30 days
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = calendarService.getPrimaryCalendar();

    Instant start = Instant.now();
    Instant end = start.plus(120, ChronoUnit.DAYS);

    EventQuery query =
        new EventQuery().calendarId(primaryCalendar.getId()).startsAfter(start).endsBefore(end);
    RemoteCollection<Event> events = account.events().list(query);

    return events;
  }

  public Event createEvent() throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = calendarService.getPrimaryCalendar();

    Event.When when = null;
    LocalDate today = LocalDate.now();
    when = new Event.Date(today);
    when = new Event.Datespan(today, today.plusDays(1));
    Instant sixPmUtc = today.atTime(21, 0).toInstant(ZoneOffset.UTC);
    when = new Event.Time(sixPmUtc);
    when = new Event.Timespan(sixPmUtc, sixPmUtc.plus(1, ChronoUnit.HOURS));

    Event event = new Event(primaryCalendar.getId(), when);

    event.setLocation("My house");
    event.setDescription("This is a test event");
    event.setBusy(true);
    Event newEvent = account.events().create(event, false);
    return newEvent;
  }


  public Event getEventById(String id) throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Event event = account.events().get(id);
    return event;
  }
}
