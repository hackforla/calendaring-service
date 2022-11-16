
package com.hfla.service.calendar.pojos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "calendar_id",
    "event_uid",
    "summary",
    "description",
    "start",
    "end",
    "deleted",
    "location",
    "participation_status",
    "transparency",
    "event_status",
    "categories",
    "attendees",
    "created",
    "updated"
})
@Generated("jsonschema2pojo")
public class Event {

    @JsonProperty("calendar_id")
    private String calendarId;
    @JsonProperty("event_uid")
    private String eventUid;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("description")
    private String description;
    @JsonProperty("start")
    private String start;
    @JsonProperty("end")
    private String end;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("participation_status")
    private String participationStatus;
    @JsonProperty("transparency")
    private String transparency;
    @JsonProperty("event_status")
    private String eventStatus;
    @JsonProperty("categories")
    private List<Object> categories = null;
    @JsonProperty("attendees")
    private List<Attendee> attendees = null;
    @JsonProperty("created")
    private String created;
    @JsonProperty("updated")
    private String updated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("calendar_id")
    public String getCalendarId() {
        return calendarId;
    }

    @JsonProperty("calendar_id")
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    @JsonProperty("event_uid")
    public String getEventUid() {
        return eventUid;
    }

    @JsonProperty("event_uid")
    public void setEventUid(String eventUid) {
        this.eventUid = eventUid;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("end")
    public String getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(String end) {
        this.end = end;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("participation_status")
    public String getParticipationStatus() {
        return participationStatus;
    }

    @JsonProperty("participation_status")
    public void setParticipationStatus(String participationStatus) {
        this.participationStatus = participationStatus;
    }

    @JsonProperty("transparency")
    public String getTransparency() {
        return transparency;
    }

    @JsonProperty("transparency")
    public void setTransparency(String transparency) {
        this.transparency = transparency;
    }

    @JsonProperty("event_status")
    public String getEventStatus() {
        return eventStatus;
    }

    @JsonProperty("event_status")
    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @JsonProperty("categories")
    public List<Object> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    @JsonProperty("attendees")
    public List<Attendee> getAttendees() {
        return attendees;
    }

    @JsonProperty("attendees")
    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("updated")
    public String getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
