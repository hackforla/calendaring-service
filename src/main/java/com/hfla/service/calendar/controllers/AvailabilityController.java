package com.hfla.service.calendar.controllers;

import com.hfla.service.calendar.algorithm.FreeTimeService;
import com.hfla.service.calendar.pojos.Availability;
import com.hfla.service.calendar.pojos.ConnectedCalendar;
import com.hfla.service.calendar.pojos.ConnectedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController {

	@Autowired
	ConnectedCalendarController connectedCalendarController;

	@Autowired
	FreeTimeService freeTime;

	@GetMapping(name = "/", consumes = "application/json", produces = "application/json")
	ResponseEntity<List<Availability>> calculateAvailability(@RequestParam String nylasId) {

		List<Availability> availability = new ArrayList<>();

		ResponseEntity<List<ConnectedCalendar>> calendarResponseEntity = connectedCalendarController.getConnectedCalendar(nylasId);
		List<ConnectedCalendar> calendars = calendarResponseEntity.getBody();
		for (ConnectedCalendar calendar : calendars ) {
			if (!calendar.isReadOnly()) {
				ResponseEntity<List<ConnectedEvent>> eventsResponseEntity = connectedCalendarController.getEvents(nylasId, calendar.getCalendarId());
				List<ConnectedEvent> events = eventsResponseEntity.getBody();
				//availability = new Availability(events).calculate();
				freeTime.perDay(events);
			}
		}

		return new ResponseEntity<>(availability, new HttpHeaders(), HttpStatus.OK);
	}
}
