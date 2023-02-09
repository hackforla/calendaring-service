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
import java.net.*;
import java.io.*;
import org.json.*;

@Service
public class CronofyService {

  @Value("${cronofy.client.id}")
  private String clientId;

  @Value("${cronofy.client.secret}")
  private String clientSecret;

  @Value("${cronofy.redirect.uri}")
  private String redirectUri;

  @Value("${cronofy.api.baseuri}")
  private String cronofyApiBaseUrl;
  private final WebClient webClient;

  public CronofyService(WebClient.Builder webClientBuilder) {


    this.webClient = webClientBuilder.baseUrl("https://api.cronofy.com/")
            .defaultHeader(HttpHeaders.HOST, "api.cronofy.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  public Events getEvents(String token) {

    return this.webClient.get().uri("/v1/events?tzid=Etc/UTC").header("Authorization", "Bearer " + token).retrieve().bodyToMono(Events.class)
            .block();
  }

  public Calendars getCalendars() {
    return this.webClient.get().uri("/v1/calendars").retrieve().bodyToMono(Calendars.class).block();
  }

  public String createEvent(Event event) {
    String calendarId = this.getCalendars().getCalendars().get(0).getCalendarId();

    return this.webClient.post()
            .uri(uriBuilder -> uriBuilder.path("/v1/calendars/{calendarId}/events").build(calendarId))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(event), Event.class).retrieve().bodyToMono(String.class).block();
  }

  public String getToken(String code) {

    try {
      URL url = new URL(cronofyApiBaseUrl);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json");

      JSONObject json = new JSONObject();
      json.put("client_id", clientId);
      json.put("client_secret", clientSecret);
      json.put("grant_type", "authorization_code");
      json.put("code", code);
      json.put("redirect_uri", redirectUri);

      var jsonString = json.toString();

      con.setDoOutput(true);
      try(OutputStream os = con.getOutputStream()) {
        byte[] input = jsonString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }


      try(BufferedReader br = new BufferedReader(
              new InputStreamReader(con.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }
        JSONObject jsonObj = new JSONObject(response.toString());
        return jsonObj.getString("access_token");
      } catch (Exception e) {
        con.disconnect();
        return "";
      }

    } catch (Exception e) {
      return null;
    }

  }



}
