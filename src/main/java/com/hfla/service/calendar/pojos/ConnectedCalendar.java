package com.hfla.service.calendar.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hfla.service.calendar.clients.NylasRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ConnectedCalendar {

	private static Logger logger = LogManager.getFormatterLogger("ConnectedCalendar.class");

	@JsonProperty("account_id")
	private String nylasId;
	private String accessToken;
	@JsonProperty("id")
	private String calendarId;
	private String description;
	@JsonProperty("is_primary")
	private String isPrimary;
	private String name;
	private String object;
	@JsonProperty("read_only")
	private boolean readOnly;
	private String timezone;
	private String location;
	@JsonIgnore
	private List<ConnectedEvent> events;

	public ConnectedCalendar() {}

	public ConnectedCalendar(String nylasId, String calendarId) {
		this.nylasId = nylasId;
		this.calendarId = calendarId;
		this.accessToken = "FSlSGssL1k7VbJodSUfJUKYrgQOBzG"; //nylasId: hsej56l19s6h4nvtmwcqekyv
		//this.accessToken = "sQB57ozr3wVNn1AYFX5077QHKMnpoF"; //nylasId: 9mnvr3qpqtvwkj4egbic7jcht
		this.events = getEvents();
	}

	public ConnectedCalendar(String nylasId) {
		this.nylasId = nylasId;
		this.accessToken = "FSlSGssL1k7VbJodSUfJUKYrgQOBzG"; //nylasId: hsej56l19s6h4nvtmwcqekyv
		//this.accessToken = "sQB57ozr3wVNn1AYFX5077QHKMnpoF";
	}

	public List<ConnectedCalendar> getCalendars() {
		return new NylasRequest(nylasId)
				.getCalendars();
	}

	public List<ConnectedEvent> getEvents() {
		return new NylasRequest(nylasId)
				.getEvents(calendarId);
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

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly  = readOnly;
	}

	public void setNylasId(String nylasId) {
		this.nylasId = nylasId;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setEvents(List<ConnectedEvent> events) {
		this.events = events;
	}
}
