package com.hfla.service.calendar.services;

import com.hfla.service.calendar.pojos.Cronify.Calendars;
import com.hfla.service.calendar.pojos.Cronify.Event;
import com.hfla.service.calendar.pojos.EventsInteface;
import com.hfla.service.calendar.pojos.Cronify.Events;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CronofyService implements CalendarService{

  private final WebClient webClient;

  public CronofyService(WebClient.Builder webClientBuilder,
      @Value("${cronofy.access.token}") String accessToken) {
    this.webClient = webClientBuilder.baseUrl("https://api.cronofy.com/")
        .defaultHeader(HttpHeaders.HOST, "api.cronofy.com")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken).build();
  }

  public EventsInteface getEvents() {
    return this.webClient.get().uri("/v1/events?tzid=Etc/UTC").retrieve().bodyToMono(Events.class)
        .block();
  }

  public Calendars getCalendars() {
    return this.webClient.get().uri("/v1/calendars").retrieve().bodyToMono(Calendars.class).block();
  }




  @Override
  public String createEvent(EventsInteface eventParameter) {
    Events event = (Events)eventParameter;
    String calendarId = this.getCalendars().getCalendars().get(0).getCalendarId();

    return this.webClient.post()
        .uri(uriBuilder -> uriBuilder.path("/v1/calendars/{calendarId}/events").build(calendarId))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Mono.just(event), Event.class).retrieve().bodyToMono(String.class).block();
  }

}
