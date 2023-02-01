package com.hfla.service.calendar.controllers;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import com.hfla.service.calendar.pojos.CalendarsInterface;
import com.hfla.service.calendar.pojos.EventsInteface;
import com.hfla.service.calendar.services.CalendarService;
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

  private final CalendarService calendarService;
  private final EventService eventService;

  @Autowired
  public CalendarController(CalendarService calendarService, EventService eventService) {
    this.calendarService = calendarService;
    this.eventService = eventService;
  }

  @GetMapping
  public CalendarsInterface getCalendars() throws IOException, RequestFailedException {
    System.out.println("Getting calendars");
    return calendarService.getCalendars();
  }

 /* @GetMapping(path = "/freebusy")
  public List<FreeBusy> getFreeBusy() throws IOException, RequestFailedException {
    System.out.println("Getting the free busy");

    return calendarService.checkFreeBusy();
  }
*/

  // TODO: getAvailability should require start date and enddate.
  @GetMapping(path = "/availability")
  public Object getAvailability() throws IOException, RequestFailedException {
    System.out.println("Getting availability");
    return calendarService.checkAvailability(Instant.parse("0"), Instant.parse("1"));
  }

  @GetMapping(path = "/events")
  public EventsInteface getEvents() throws IOException, RequestFailedException {
    System.out.println("Getting events");
    return calendarService.getEvents();
  }
}
