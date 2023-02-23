package com.hfla.service.calendar.legacy.pojos;

import com.hfla.service.calendar.legacy.clients.NylasRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import org.jboss.logging.Logger;

import java.util.List;

@Getter
@Setter
public class ConnectedEvent {

	@JsonProperty("account_id")
	private String accountId;
	private boolean busy;
	@JsonProperty("calendar_id")
	private String calendarId;
	private String description;
	@JsonProperty("master_event_id")
	private String masterEventId;
	@JsonProperty("original_start_time")
	private long originalStartTime;
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

	private static Logger logger = Logger.getLogger("ConnectedEvent.class");

	public static String addEvent(ConnectedEvent event, String nylasId) {
		return new NylasRequest(nylasId)
				.addEvent(event);
	}

	public static Void deleteEvent(String eventId, String nylasId) {
		return new NylasRequest(nylasId)
				.deleteEvent(eventId);
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
