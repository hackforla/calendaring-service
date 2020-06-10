package com.hfla.service.calendar.controllers;

import com.hfla.service.calendar.pojos.ConnectedCalendar;
import com.hfla.service.calendar.pojos.ConnectedEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/calendar")
public class ConnectedCalendarController {
	private static Logger logger = LogManager.getLogManager().getLogger("ConnectedCalendarController.class");

	@GetMapping(value = "/events", consumes = "application/json", produces = "application/json")
	ResponseEntity<List<ConnectedEvent>> getEvents(@RequestParam(name="nylasId") String nylasId,
												   @RequestParam(name = "calendarId") String calendarId) {

		return new ResponseEntity<>(new ConnectedCalendar(nylasId, calendarId).getEvents(),
				new HttpHeaders(),
				HttpStatus.OK);
	}

	@GetMapping(value = "/calendars", consumes = "application/json", produces = "application/json")
	ResponseEntity<List<ConnectedCalendar>> getConnectedCalendar(@RequestParam(name="nylasId") String nylasId) {

		return new ResponseEntity<>(new ConnectedCalendar(nylasId).getCalendars(),
				new HttpHeaders(),
				HttpStatus.OK);
	}

	@PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
	ResponseEntity<String> createEvent(@RequestBody ConnectedEvent nylasEvent, @RequestParam String nylasId) {

		return new ResponseEntity<>(ConnectedEvent.addEvent(nylasEvent,
				new ConnectedCalendar(nylasId).getAccessToken()),
				new HttpHeaders(),
				HttpStatus.OK);

	}

	@DeleteMapping(value = "/event/{id}", consumes = "application/json", produces = "application/json")
	ResponseEntity<Void> deleteEvent(@PathVariable(name = "id") String eventId, @RequestParam String nylasId) {
		return new ResponseEntity<>(ConnectedEvent.deleteEvent(eventId, new ConnectedCalendar(nylasId).getAccessToken()),
				new HttpHeaders(),
				HttpStatus.OK);
	}

}
