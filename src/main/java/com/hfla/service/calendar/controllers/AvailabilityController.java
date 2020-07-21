package com.hfla.service.calendar.controllers;

import com.hfla.service.calendar.pojos.Availability;
import com.hfla.service.calendar.pojos.ConnectedCalendar;
import com.hfla.service.calendar.pojos.ConnectedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController {

	@Autowired
	ConnectedCalendarController connectedCalendarController;

	@GetMapping(name = "/", consumes = "application/json", produces = "application/json")
	ResponseEntity<List<Availability>> calculateAvailability(@PathVariable String nylasId) {

		List<Availability> availability = new ArrayList<>();

		ResponseEntity<List<ConnectedCalendar>> calendarResponseEntity = connectedCalendarController.getConnectedCalendar(nylasId);
		List<ConnectedCalendar> calendars = calendarResponseEntity.getBody();
		for (ConnectedCalendar calendar : calendars ) {
			if (!calendar.isReadOnly()) {
				ResponseEntity<List<ConnectedEvent>> eventsResponseEntity = connectedCalendarController.getEvents(nylasId, calendar.getCalendarId());
				List<ConnectedEvent> events = eventsResponseEntity.getBody();
				availability = new Availability(events).calculate();
			}
		}

		return new ResponseEntity<>(availability, new HttpHeaders(), HttpStatus.OK);
	}
}
