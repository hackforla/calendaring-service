package com.hfla.service.calendar.controllers;

import java.io.IOException;
import java.time.Instant;

import com.hfla.service.calendar.pojos.CalendarsInterface;
import com.hfla.service.calendar.pojos.EventsInteface;
import com.hfla.service.calendar.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nylas.RequestFailedException;





@RestController
@RequestMapping(path = "/calendars")
public class CalendarController {

  private final CalendarService calendarService;
  //private final EventService eventService;

  @Autowired
  public CalendarController(CalendarService calendarService) {

    this.calendarService = calendarService;
  }

  @GetMapping(path = "/calendars")
  public CalendarsInterface getCalendars() throws IOException, RequestFailedException {
    System.out.println("Getting calendars");
    return calendarService.getCalendars();
  }


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

  @PostMapping(value = "/createEvent")
  public String createEvent()
          {
    return calendarService.createEvent(null);
  }

}
