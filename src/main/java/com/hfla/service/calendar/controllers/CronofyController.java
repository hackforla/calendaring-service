package com.hfla.service.calendar.controllers;

import com.nylas.RequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.hfla.service.calendar.services.CronofyService;
import com.hfla.service.calendar.pojos.Calendars;
import com.hfla.service.calendar.pojos.Events;
import com.hfla.service.calendar.pojos.Event;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.servlet.http.*;
import java.util.UUID;

import java.io.IOException;


@RestController
@RequestMapping(path = "/cronofy")
public class CronofyController {

  @Value("${cronofy.client.id}")
  private String clientId;

  @Value("${cronofy.redirect.uri}")
  private String redirectUri;

  @Value("${cronofy.app.baseuri}")
  private String cronofyAppBaseUrl;
  private final CronofyService cronofyService;

  @Autowired
  public CronofyController(CronofyService cronofyService) {
    this.cronofyService = cronofyService;
  }

  @GetMapping(path = "/calendars")
  public Calendars getCalendars() {
    return cronofyService.getCalendars();
  }

  @GetMapping(path = "/token")
  public String getToken(@RequestParam("code") String code) throws IOException, RequestFailedException {
    return cronofyService.getToken(code);
  }
  @RequestMapping(value = "/startMock", method = RequestMethod.GET)
  public void redirectToTwitter(HttpServletResponse httpServletResponse) throws IOException {
    httpServletResponse.sendRedirect(cronofyAppBaseUrl + "/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=read_write&state=" + UUID.randomUUID());
  }

  @GetMapping(path = "/events")
  public Events getEvents(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws IOException, RequestFailedException {
    return cronofyService.getEvents(token.replace("Bearer ",""));
  }

  @PostMapping(path = "/events")
  public String createEvent(Event event) {
    return cronofyService.createEvent(event);
  }


}
