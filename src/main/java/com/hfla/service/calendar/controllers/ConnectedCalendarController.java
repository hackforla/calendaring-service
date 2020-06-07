package com.hfla.service.calendar.controllers;

import com.hfla.service.calendar.models.ConnectedCalendar;
import com.hfla.service.calendar.models.NylasEvent;
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
	ResponseEntity<List<NylasEvent>> getEvents(@RequestParam(name="nylasId") String nylasId,
										 @RequestParam(name = "calendarId") String calendarId) {

		ConnectedCalendar connectedCalendar = new ConnectedCalendar(nylasId, calendarId);
		List<NylasEvent> events = connectedCalendar.getEvents();

		return new ResponseEntity<>(events, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(value = "/calendars", consumes = "application/json", produces = "application/json")
	ResponseEntity<List<ConnectedCalendar>> getConnectedCalendar(@RequestParam(name="nylasId") String nylasId) {

		ConnectedCalendar connectedCalendar = new ConnectedCalendar(nylasId);
		return new ResponseEntity<>(connectedCalendar.retrieveCalendars(), new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(value = "/event", consumes = "application/json", produces = "application/json")
	ResponseEntity<String> createEvent(@RequestBody NylasEvent nylasEvent, @RequestParam String nylasId) {
		String eventId;

		String accessToken = new ConnectedCalendar(nylasId).getAccessToken();
		eventId = NylasEvent.addEvent(nylasEvent, accessToken);

		return new ResponseEntity<>(eventId, new HttpHeaders(), HttpStatus.OK);

	}

}
