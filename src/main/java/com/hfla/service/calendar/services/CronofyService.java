package com.hfla.service.calendar.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hfla.service.calendar.pojos.Events;

@Service
public class CronofyService {
  
  private final WebClient webClient;

  public CronofyService(WebClient.Builder webClientBuilder, @Value("${cronofy.access.token}") String accessToken) {
    this.webClient = webClientBuilder.baseUrl("https://api.cronofy.com/").defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken).build();
  }

  public Events getEvents() {
    return this.webClient.get().uri("/v1/events?tzid=Etc/UTC").retrieve().bodyToMono(Events.class).block();
  }

}
