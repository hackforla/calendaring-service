package com.hfla.service.calendar.controllers;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfla.service.calendar.services.EventService;
import com.nylas.Event;
import com.nylas.RequestFailedException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/event")
public class EventController {
  private final EventService eventService;

  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  //TODO: event should return values like start date, enddate and description.
  @PostMapping(value="/create")
  public Event createEvent(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String description,
		  @RequestParam String location, @RequestParam boolean notify) throws IOException, RequestFailedException {
	  
    return eventService.createEvent(Instant.parse(startDate),Instant.parse(endDate),description,location,notify );
  }

  @GetMapping
  public Event getEventById(@RequestParam String eventId) throws IOException, RequestFailedException {
    return eventService.getEventById(eventId);
  }
  
  
}