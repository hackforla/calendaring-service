package com.hfla.service.calendar.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfla.service.calendar.pojos.ConnectedCalendar;
import com.hfla.service.calendar.pojos.ConnectedEvent;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NylasRequest {

	private static Logger logger = LogManager.getFormatterLogger("NylasRequest.class");
	private String nylasId;
	private String accessToken;
	private String APPLICATION_JSON = "application/json";

	public NylasRequest(String nylasId) {
		this.nylasId = nylasId;
		this.accessToken = "BVoFdO9uZXRwaSAgkfaTXPtRS8XjJP"; //nylasId: 3h070kon7pzwf64v8uj5bomof
		//this.accessToken = "FSlSGssL1k7VbJodSUfJUKYrgQOBzG"; //nylasId: hsej56l19s6h4nvtmwcqekyv
		//this.accessToken = "sQB57ozr3wVNn1AYFX5077QHKMnpoF";
	}

	public List<ConnectedCalendar> getCalendars() {
		List<ConnectedCalendar> calendars = null;
		String baseUrl = "https://api.nylas.com/calendars";
		Request request = buildNylasGetRequest(baseUrl);

		try (Response response = new OkHttpClient()
									.newCall(request)
									.execute()){
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response
					.body()
					.byteStream(), StandardCharsets.UTF_8));
			calendars = new ObjectMapper().readValue(bufferedReader,
				new TypeReference<List<ConnectedCalendar>>() {});
		} catch (IOException ioe) {
			logger.error("error message: %s", ioe.getMessage());
		}

		return calendars;
	}

	public List<ConnectedEvent> getEvents(String calendarId) {
		List<ConnectedEvent> events = null;
		String baseUrl = "https://api.nylas.com/events?calendar_id=" + calendarId;
		Request request = buildNylasGetRequest(baseUrl);

		try (Response response = new OkHttpClient()
									.newCall(request)
									.execute()){
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response
					.body()
					.byteStream(), StandardCharsets.UTF_8));
			events = new ObjectMapper().readValue(bufferedReader,
					new TypeReference<List<ConnectedEvent>>() {});
					//mapper.getTypeFactory().constructCollectionType(List.class, NylasEvent.class));

		} catch (IOException ioe) {
			logger.error("errorMessage: %s", ioe.getMessage());
		}

		return events;
	}

	public Void deleteEvent(String eventId) {
		StringBuilder baseUrlBuilder  = new StringBuilder();
		baseUrlBuilder.append("https://api.nylas.com/events/");
		baseUrlBuilder.append(eventId);

		Request request = buildNylasDeleteRequest(baseUrlBuilder.toString());
		OkHttpClient client = new OkHttpClient();
		Call call = client.newCall(request);

		ObjectMapper mapper = new ObjectMapper();

	    try (Response response = call.execute()) {
	    	InputStream inputStream = response.body().byteStream();
	    	InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
	    	String nylasResponse = mapper.readValue(inputStream, String.class);
	    	logger.info("nylasResponse: " + nylasResponse);

		} catch (IOException ioE) {
	    	logger.error("error message: " + ioE.getMessage());
		}

	    return null;
	}

	public String addEvent(ConnectedEvent event) {
		String baseUrl = "https://api.nylas.com/events";
		ObjectMapper mapper = new ObjectMapper();
		String eventId = "0";

		try  {
			InputStream inputStream = new OkHttpClient()
					.newCall(buildNylasPostRequest(baseUrl,buildRequestBody(event)))
					.execute()
					.body()
					.byteStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			eventId = mapper.readValue(bufferedReader,
					new TypeReference<ConnectedEvent>() {}).getEventId();
			logger.info("eventId: " +  eventId);
		} catch (JsonProcessingException jPE) {
			logger.error("error in processing json: %s" + jPE.getMessage());
		} catch (IOException ioE) {
			logger.error("iOE error: %" + ioE.getMessage());
		} catch (Exception e) {
			logger.error("exception: %" + e.getMessage());
		}

		return eventId;
	}

	/**
	 * Accessors and Mutators
	 */

	public String getNylasId() {
		return nylasId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	private static String base64EncodeUTF8(String str) {
		String encoding = new String(
				org.apache.commons.codec.binary.Base64.encodeBase64
						(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(str))
		);
		return encoding;
	}

	private Request buildNylasGetRequest(String baseUrl) {
		return new Request.Builder()
				.url(baseUrl)
				.header("Content-type", APPLICATION_JSON)
				.header("Authorization", "Basic " + base64EncodeUTF8(accessToken + ":"))
				.get()
				.build();
	}

	private Request buildNylasPostRequest(String baseUrl, RequestBody requestBody) {
		return new Request.Builder()
				.url(baseUrl)
				.header("Content-Type", APPLICATION_JSON)
				.header("Authorization", "Basic  " + base64EncodeUTF8(accessToken + ":"))
				.post(requestBody)
				.build();
	}

	private Request buildNylasDeleteRequest(String baseUrl) {
		return new Request.Builder()
				.url(baseUrl)
				.header("Content-Type", APPLICATION_JSON)
				.header("Authorization", "Basic " + base64EncodeUTF8(accessToken + ":"))
				.delete()
				.build();
	}

	private RequestBody buildRequestBody(ConnectedEvent event) throws JsonProcessingException{
		return RequestBody.create(new ObjectMapper().writeValueAsString(event),
				MediaType.parse(APPLICATION_JSON));
	}
}
