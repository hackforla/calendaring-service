package com.hfla.service.calendar.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfla.service.calendar.models.ConnectedCalendar;
import com.hfla.service.calendar.models.NylasEvent;
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
		this.accessToken = "FSlSGssL1k7VbJodSUfJUKYrgQOBzG"; //nylasId: hsej56l19s6h4nvtmwcqekyv
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

	public List<NylasEvent> getEvents(String calendarId) {
		List<NylasEvent> events = null;
		String baseUrl = "https://api.nylas.com/events?calendar_id=" + calendarId;
		Request request = buildNylasGetRequest(baseUrl);

		try (Response response = new OkHttpClient()
									.newCall(request)
									.execute()){
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response
					.body()
					.byteStream(), StandardCharsets.UTF_8));
			events = new ObjectMapper().readValue(bufferedReader,
					new TypeReference<List<NylasEvent>>() {});
					//mapper.getTypeFactory().constructCollectionType(List.class, NylasEvent.class));

		} catch (IOException ioe) {
			logger.error("errorMessage: %s", ioe.getMessage());
		}

		return events;
	}

	public String addEvent(NylasEvent event) {
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
					new TypeReference<NylasEvent>() {}).getEventId();
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
				.header("Accept", APPLICATION_JSON)
				.header("Content-type", APPLICATION_JSON)
				.header("Authorization", "Basic " + base64EncodeUTF8(accessToken + ":"))
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

	private RequestBody buildRequestBody(NylasEvent event) throws JsonProcessingException{
		return RequestBody.create(new ObjectMapper().writeValueAsString(event),
				MediaType.parse(APPLICATION_JSON));
	}
}
