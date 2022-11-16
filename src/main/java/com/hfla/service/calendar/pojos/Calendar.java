
package com.hfla.service.calendar.pojos;

import java.util.HashMap;
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
    "provider_name",
    "profile_id",
    "profile_name",
    "calendar_id",
    "calendar_name",
    "calendar_readonly",
    "calendar_deleted"
})
@Generated("jsonschema2pojo")
public class Calendar {

    @JsonProperty("provider_name")
    private String providerName;
    @JsonProperty("profile_id")
    private String profileId;
    @JsonProperty("profile_name")
    private String profileName;
    @JsonProperty("calendar_id")
    private String calendarId;
    @JsonProperty("calendar_name")
    private String calendarName;
    @JsonProperty("calendar_readonly")
    private Boolean calendarReadonly;
    @JsonProperty("calendar_deleted")
    private Boolean calendarDeleted;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("provider_name")
    public String getProviderName() {
        return providerName;
    }

    @JsonProperty("provider_name")
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @JsonProperty("profile_id")
    public String getProfileId() {
        return profileId;
    }

    @JsonProperty("profile_id")
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @JsonProperty("profile_name")
    public String getProfileName() {
        return profileName;
    }

    @JsonProperty("profile_name")
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @JsonProperty("calendar_id")
    public String getCalendarId() {
        return calendarId;
    }

    @JsonProperty("calendar_id")
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    @JsonProperty("calendar_name")
    public String getCalendarName() {
        return calendarName;
    }

    @JsonProperty("calendar_name")
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    @JsonProperty("calendar_readonly")
    public Boolean getCalendarReadonly() {
        return calendarReadonly;
    }

    @JsonProperty("calendar_readonly")
    public void setCalendarReadonly(Boolean calendarReadonly) {
        this.calendarReadonly = calendarReadonly;
    }

    @JsonProperty("calendar_deleted")
    public Boolean getCalendarDeleted() {
        return calendarDeleted;
    }

    @JsonProperty("calendar_deleted")
    public void setCalendarDeleted(Boolean calendarDeleted) {
        this.calendarDeleted = calendarDeleted;
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
