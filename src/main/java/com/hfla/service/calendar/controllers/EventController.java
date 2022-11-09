package com.hfla.service.calendar.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.service.calendar.pojos.Events;
import com.hfla.service.calendar.services.CronofyService;
import com.hfla.service.calendar.services.EventService;
import com.nylas.Event;
import com.nylas.RequestFailedException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/events")
public class EventController {
  private final EventService eventService;
  private final CronofyService cronofyService;

  @Autowired
  public EventController(EventService eventService, CronofyService cronofyService) {
    this.eventService = eventService;
    this.cronofyService = cronofyService;
  }

  @PostMapping(value="/create")
  public Event createEvent() throws IOException, RequestFailedException {
    return eventService.createEvent();
  }

  @GetMapping(value="/list")
  public Events getEvents() {
    return cronofyService.getEvents();
  }

  @GetMapping
  public Event getEventById(@RequestParam String eventId) throws IOException, RequestFailedException {
    return eventService.getEventById(eventId);
  }
  
  
}