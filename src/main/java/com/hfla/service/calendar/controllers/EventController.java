package com.hfla.service.calendar.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hfla.service.calendar.services.EventService;
import com.nylas.Event;
import com.nylas.RemoteCollection;
import com.nylas.RequestFailedException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/events")
public class EventController {
  private final EventService eventService;

  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping(value = "/create")
  public Event createEvent(@RequestBody Event event) throws IOException, RequestFailedException {
    return eventService.createEvent();
  }

  @GetMapping(value = "/list")
  public RemoteCollection<Event> getEvents() throws IOException, RequestFailedException {
    return eventService.getEvents();
  }

  @GetMapping
  public Event getEventById(@RequestParam String eventId)
      throws IOException, RequestFailedException {
    return eventService.getEventById(eventId);
  }


}
