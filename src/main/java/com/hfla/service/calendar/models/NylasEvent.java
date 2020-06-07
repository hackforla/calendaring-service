package com.hfla.service.calendar.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NylasEvent {

	@JsonProperty("account_id")
	private String accountId;
	private boolean busy;
	@JsonProperty("calendar_id")
	private String calendarId;
	private String description;
	@JsonProperty("id")
	private String eventId;
	private String location;
	@JsonProperty("message_id")
	private String messageId;
	@JsonProperty("read_only")
	private boolean readOnly;
	private String status;
	private String object;
	private String owner;
	private String title;
	@JsonProperty("ical_uid")
	private String icalUid;
	private List<Participants> participants;
	private When when;

	private static Logger logger = Logger.getLogger("NylasEvent.class");

	public static String addEvent(NylasEvent event, String accessToken) {

		String BASE_URL = "https://api.nylas.com/events";
		String APPLICATION_JSON = "application/json";
		ObjectMapper mapper = new ObjectMapper();
		OkHttpClient client = new OkHttpClient();
		String eventId = "0";

		try  {
			RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(event), MediaType.parse(APPLICATION_JSON));
			logger.info("requestBody: %" + mapper.writeValueAsString(event));
			Request request = new Request.Builder()
					.url(BASE_URL)
					.header("Content-Type", APPLICATION_JSON)
					.header("Authorization", "Basic  " + ConnectedCalendar.base64EncodeUTF8(accessToken + ":"))
					.post(requestBody)
					.build();

			Call call = client.newCall(request);
			Response response = call.execute();
			ResponseBody responseBody = response.body();
			InputStream inputStream = responseBody.byteStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			TypeReference<NylasEvent> typeReference = new TypeReference<NylasEvent>() {};
			eventId = mapper.readValue(reader, typeReference).getEventId();
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
	 *
	 */
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public boolean getBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public String getIcalUid() {
		return icalUid;
	}

	public List<Participants> getParticipants() {
		return participants;
	}

	public When getWhen() {
		return when;
	}

	public void setWhen(When when) {
		this.when = when;
	}

	public void setParticipants(List<Participants> participants) {
		this.participants = participants;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public void setIcalUid(String icalUid) {
		this.icalUid = icalUid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
