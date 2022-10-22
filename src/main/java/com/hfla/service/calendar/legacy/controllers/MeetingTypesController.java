package com.hfla.service.calendar.legacy.controllers;

import com.hfla.service.calendar.legacy.pojos.MeetingType;
import com.hfla.service.calendar.legacy.repositories.MeetingTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(path = "/meetingTypes")
public class MeetingTypesController {

	@Autowired
	MeetingTypeRepository meetingTypeRepository;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	ResponseEntity<HttpStatus> createMeetingType(@RequestBody MeetingType meetingType) {

		meetingTypeRepository.save(meetingType);
		System.out.println(meetingType.getId());

		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
}
