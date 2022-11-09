package com.hfla.service.calendar.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.service.calendar.pojos.Calendars;
import com.hfla.service.calendar.services.CalendarService;
import com.hfla.service.calendar.services.CronofyService;
import com.hfla.service.calendar.services.EventService;
import com.nylas.Calendar;
import com.nylas.Event;
import com.nylas.FreeBusy;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;
import com.nylas.TimeSlot;


@RestController
@RequestMapping(path = "/calendars")
public class CalendarController {

  private final CalendarService calendarService;
  private final EventService eventService;
  private final CronofyService cronofyService;

  @Autowired
  public CalendarController(CalendarService calendarService, EventService eventService, CronofyService cronofyService) {
    this.calendarService = calendarService;
    this.eventService = eventService;
    this.cronofyService = cronofyService;
  }

  @GetMapping
  public Calendars getCalendars() throws IOException, RequestFailedException {
      System.out.println("Getting calendars");
      return cronofyService.getCalendars();
  }
  
  @GetMapping(path = "/freebusy")
  public List<FreeBusy> getFreeBusy() throws IOException, RequestFailedException {
      System.out.println("Getting the free busy");

      return calendarService.checkFreeBusy();
  }

  @GetMapping(path = "/availability")
  public List<TimeSlot> getAvailability() throws IOException, RequestFailedException {
      System.out.println("Getting availability");

      return calendarService.checkAvailability();
  }

  @GetMapping(path = "/events")
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
      System.out.println("Getting events");
      return eventService.getEvents();
  }
}
