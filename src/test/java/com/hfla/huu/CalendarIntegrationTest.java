package com.hfla.huu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfla.service.calendar.models.ConnectedCalendar;
import com.hfla.service.calendar.models.NylasEvent;
import okhttp3.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CalendarIntegrationTest {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void getEventsTestUsingNylas() {
		//arrange
		String nylasId = "9mnvr3qpqtvwkj4egbic7jcht";
		//String url = "/calendar/events?nylasId=" + nylasId;
		String url = "http://localhost:8080";
		URI uri = UriComponentsBuilder.fromHttpUrl(url)
				.path("/calendar/events")
				.queryParam("nylasId", nylasId)
				.build().toUri();

		//act
		ResponseEntity<ConnectedCalendar> response =  restTemplate.getForEntity(uri, ConnectedCalendar.class);

		//assert
		//Assertions.assertThat(response.getBody().getEvents()).isNotNull();
	}

	@Test
	public void getCalendars() {
		//arrange
		String nylasId = "9mnvr3qpqtvwkj4egbic7jcht";

		//act
		ConnectedCalendar calendars = getCalendars(nylasId);

		//assert
		Assertions.assertThat(calendars).isNotNull();
	}

	private ConnectedCalendar getCalendars(String nylasId) {

		String url = "http://localhost:8080";
		URI uri = UriComponentsBuilder.fromHttpUrl(url)
				.path("/calendar/calendars")
				.queryParam("nylasId", nylasId)
				.build().toUri();

		ResponseEntity<ConnectedCalendar> response = restTemplate.getForEntity(uri, ConnectedCalendar.class);
		return response.getBody();
	}

	@Test
	public void addEvent() {
		//arrange
		String nylasId = "9mnvr3qpqtvwkj4egbic7jcht";
		ConnectedCalendar calendars = getCalendars(nylasId);

		String calendarId = calendars.getCalendarId();
		ObjectMapper mapper = new ObjectMapper();
		NylasEvent nylasEvent = new NylasEvent();
		nylasEvent.setCalendarId(calendarId);
		nylasEvent.setBusy(true);
		nylasEvent.setDescription("tracy testing");
		//nylasEvent.setStartTime(new DateTime().plusDays(1).plusMinutes(30).getMillis());
		//nylasEvent.setEndTime(new DateTime().plusDays(1).plusHours(1).getMillis());
		//nylasEvent.setParticipants(Arrays.asList("sunnyskies0333@gmail.com", "myshelly0211@yahoo.com"));
		String requestBody = null;

		try {
			requestBody = mapper.writeValueAsString(nylasEvent);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}

		String url = "https://localhost:8080";
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		//act
		Call call  = client.newCall(request);
		try (Response response = call.execute()) {
			//assert
			Assertions.assertThat(response.body()).isNotNull();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}


	}
}
