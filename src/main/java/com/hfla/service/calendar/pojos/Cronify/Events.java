
package com.hfla.service.calendar.pojos.Cronify;

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
import com.hfla.service.calendar.pojos.Cronify.Event;
import com.hfla.service.calendar.pojos.EventsInteface;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "events"
})
@Generated("jsonschema2pojo")
public class Events implements EventsInteface {

    @JsonProperty("events")
    private List<Event> events = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("events")
    public List<Event> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<Event> events) {
        this.events = events;
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
