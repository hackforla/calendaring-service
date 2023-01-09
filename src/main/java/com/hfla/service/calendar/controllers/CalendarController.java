package com.hfla.service.calendar.controllers;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import com.hfla.service.calendar.pojos.CalendarsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.service.calendar.services.NylasCalendarService;
import com.hfla.service.calendar.services.EventService;
import com.nylas.Event;
import com.nylas.FreeBusy;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;
import com.nylas.TimeSlot;
import com.nylas.Calendar;



@RestController
@RequestMapping(path = "/calendars")
public class CalendarController {

  private final NylasCalendarService nylasCalendarService;
  private final EventService eventService;

  @Autowired
  public CalendarController(NylasCalendarService nylasCalendarService, EventService eventService) {
    this.nylasCalendarService = nylasCalendarService;
    this.eventService = eventService;
  }

  @GetMapping
  public CalendarsInterface getCalendars() throws IOException, RequestFailedException {
    System.out.println("Getting calendars");
    return nylasCalendarService.getCalendars();
  }

  @GetMapping(path = "/freebusy")
  public List<FreeBusy> getFreeBusy() throws IOException, RequestFailedException {
    System.out.println("Getting the free busy");

    return nylasCalendarService.checkFreeBusy();
  }

  // TODO: getAvailability should require start date and enddate.
  @GetMapping(path = "/availability")
  public List<TimeSlot> getAvailability() throws IOException, RequestFailedException {
    System.out.println("Getting availability");
    return nylasCalendarService.checkAvailability(Instant.parse("0"), Instant.parse("1"));
  }

  @GetMapping(path = "/events")
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
    System.out.println("Getting events");
    return eventService.getEvents();
  }
}
