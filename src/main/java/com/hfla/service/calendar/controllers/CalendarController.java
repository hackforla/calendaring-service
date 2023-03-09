package com.hfla.service.calendar.controllers;

import com.nylas.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hfla.service.calendar.pojos.Cronify.Events;

import com.hfla.service.calendar.services.CronofyService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.UUID;

import java.io.IOException;


@RestController
@RequestMapping(path = "/calendars")
public class CalendarController {

  @Value("${cronofy.client.id}")
  private String clientId;

  @Value("${cronofy.redirect.uri}")
  private String redirectUri;

  @Value("${cronofy.app.baseuri}")
  private String cronofyAppBaseUrl;
  private final CronofyService cronofyService;

  @Autowired
  public CalendarController(CronofyService cronofyService) {
    this.cronofyService = cronofyService;
  }

  @GetMapping(path = "/token")
  public String getToken(@RequestParam("code") String code) throws IOException, RequestFailedException {
    return cronofyService.getToken(code);
  }

  @RequestMapping(value = "/startMock", method = RequestMethod.GET)
  public void redirectToTwitter(HttpServletResponse httpServletResponse) throws IOException {
    httpServletResponse.sendRedirect(cronofyAppBaseUrl + "/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=read_write&state=" + UUID.randomUUID());
  }
  @RequestMapping(value = "/events", method = RequestMethod.GET)
  public Events getEvents(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws IOException, RequestFailedException {
    return cronofyService.getEvents(token.replace("Bearer ", ""));

  }

}
