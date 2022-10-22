package com.hfla.service.calendar.legacy.controllers;

import com.hfla.service.calendar.legacy.pojos.Availability;
import com.hfla.service.calendar.legacy.pojos.ConnectedCalendar;
import com.hfla.service.calendar.legacy.pojos.ConnectedEvent;
import com.hfla.service.calendar.legacy.repositories.AvailabilityRepository;

import org.joda.time.DateTime;
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
	AvailabilityRepository availabilityRepository;

	//calculates the availability for a given user based on the nylasId and a start/end time
	@GetMapping(value = "/list", consumes = "application/json", produces = "application/json")
	ResponseEntity<List<Availability>> calculateAvailability(@RequestParam(name =  "nylasId") String nylasId) {//,
															 //@RequestParam DateTime startTime,
															 //@RequestParam DateTime endTimtParam) {
		List<Availability> availability = new ArrayList<>();

		ResponseEntity<List<ConnectedCalendar>> calendarResponseEntity = connectedCalendarController.getConnectedCalendar(nylasId);
		List<ConnectedCalendar> calendars = calendarResponseEntity.getBody();
		for (ConnectedCalendar calendar : calendars ) {
			if (!calendar.isReadOnly()) {
				ResponseEntity<List<ConnectedEvent>> eventsResponseEntity = connectedCalendarController.getEvents(nylasId, calendar.getCalendarId());
				List<ConnectedEvent> events = eventsResponseEntity.getBody();
				availability = new Availability().calculate(events);
			}
		}

		return new ResponseEntity<>(availability, new HttpHeaders(), HttpStatus.OK);
	}


	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	ResponseEntity<HttpStatus> getAvailability(@PathVariable String nylasId,
											   @RequestBody Availability availability) {

		availabilityRepository.save(availability);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
