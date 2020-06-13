package com.hfla.service.calendar.controllers;

import com.hfla.service.calendar.pojos.Availability;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/availability")
public class AvailabilityController {

	@GetMapping(name = "/", consumes = "application/json", produces = "application/json")
	ResponseEntity<Availability> getAvailability() {

		return null;
	}
}
