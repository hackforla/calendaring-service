package com.hfla.service.calendar.services;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.Calendar;
import com.nylas.Event;
import com.nylas.Event.Timespan;
import com.nylas.EventQuery;
import com.nylas.NylasAccount;
import com.nylas.NylasClient;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;

@Component
public class EventService {

  private final NylasCalendarService nylasCalendarService;
  private static NylasClient client = new NylasClient();

  @Value("${access.token}")
  private String accessToken;

  @Autowired
  public EventService(NylasCalendarService nylasCalendarService) {
    this.nylasCalendarService = nylasCalendarService;
  }

  // The the next 50 events from the next 30 days
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = nylasCalendarService.getPrimaryCalendar();

    Instant start = Instant.now();
    Instant end = start.plus(120, ChronoUnit.DAYS);

    EventQuery query =
        new EventQuery().calendarId(primaryCalendar.getId()).startsAfter(start).endsBefore(end);
    RemoteCollection<Event> events = account.events().list(query);

    return events;
  }

  public RemoteCollection<Event> getEventsInRange(Instant start, Instant end)
      throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = nylasCalendarService.getPrimaryCalendar();
    EventQuery query =
        new EventQuery().calendarId(primaryCalendar.getId()).startsAfter(start).endsBefore(end);
    RemoteCollection<Event> events = account.events().list(query);

    return events;
  }

  public Event createEvent(Instant start, Instant end, String description, String location,
      boolean notify) throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Calendar primaryCalendar = nylasCalendarService.getPrimaryCalendar();

    Timespan ts = new Event.Timespan(start, end);


    Event event = new Event(primaryCalendar.getId(), ts);

    event.setLocation(location);
    event.setDescription(description);
    event.setBusy(true);
    Event newEvent = account.events().create(event, notify);
    return newEvent;
  }


  public Event getEventById(String id) throws IOException, RequestFailedException {
    NylasAccount account = client.account(accessToken);
    Event event = account.events().get(id);
    return event;
  }
}
