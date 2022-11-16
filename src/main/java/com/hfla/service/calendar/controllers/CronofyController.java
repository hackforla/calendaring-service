package com.hfla.service.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.service.calendar.services.CronofyService;
import com.hfla.service.calendar.pojos.Calendars;
import com.hfla.service.calendar.pojos.Events;
import com.hfla.service.calendar.pojos.Event;


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
  public Events getEvents() {
    return cronofyService.getEvents();
  }

  @PostMapping(path = "/events")
  public String createEvent(Event event) {
    return cronofyService.createEvent(event);
  }


}
