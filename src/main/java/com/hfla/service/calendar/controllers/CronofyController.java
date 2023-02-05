package com.hfla.service.calendar.controllers;

import com.hfla.service.calendar.pojos.Cronify.Calendars;
import com.hfla.service.calendar.pojos.Cronify.Event;
import com.hfla.service.calendar.pojos.Cronify.Events;
import com.hfla.service.calendar.pojos.EventsInteface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.service.calendar.services.CronofyService;


@RestController
@RequestMapping(path = "/cronofy")
public class CronofyController {
  private final CronofyService cronofyService;

  @Autowired
  public CronofyController(CronofyService cronofyService) {
    this.cronofyService = cronofyService;
  }

  @GetMapping(path = "/calendars")
  public Calendars getCalendars() {
    return cronofyService.getCalendars();
  }

  @GetMapping(path = "/events")
  public EventsInteface getEvents() {
    return cronofyService.getEvents();
  }

  @PostMapping(path = "/events")
  public String createEvent(EventsInteface event) {
    return cronofyService.createEvent(event);
  }


}
