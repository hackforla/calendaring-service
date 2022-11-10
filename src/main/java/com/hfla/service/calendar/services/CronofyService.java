package com.hfla.service.calendar.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hfla.service.calendar.pojos.Calendars;
import com.hfla.service.calendar.pojos.Event;
import com.hfla.service.calendar.pojos.Events;
import reactor.core.publisher.Mono;

@Service
public class CronofyService {

  private final WebClient webClient;

  public CronofyService(WebClient.Builder webClientBuilder,
      @Value("${cronofy.access.token}") String accessToken) {
    this.webClient = webClientBuilder.baseUrl("https://api.cronofy.com/")
        .defaultHeader(HttpHeaders.HOST, "api.cronofy.com")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken).build();
  }

  public Events getEvents() {
    return this.webClient.get().uri("/v1/events?tzid=Etc/UTC").retrieve().bodyToMono(Events.class)
        .block();
  }

  public Calendars getCalendars() {
    return this.webClient.get().uri("/v1/calendars").retrieve().bodyToMono(Calendars.class).block();
  }

  public String createEvent(Event event) {
    return this.webClient.post()
        .uri(uriBuilder -> uriBuilder.path("/v1/calendars/{calendarId}/events")
            .build("cal_Y2sBYDWC7ABeefcA_UU9oqpv0li@bRbfNEDeOsQ"))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Mono.just(event), Event.class).retrieve().bodyToMono(String.class).block();
  }

}


// "event_id": "qTtZdczOccgaPncGJaCiLg",
// "summary": "Board meeting",
// "description": "Discuss plans for the next quarter.",
// "start": "2022-11-11T15:30:00Z",
// "end": "2022-11-11T17:00:00Z",
// "location": {
// "description": "Board room"
// }
// }
