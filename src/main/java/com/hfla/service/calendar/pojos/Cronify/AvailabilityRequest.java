package com.hfla.service.calendar.pojos.Cronify;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailabilityRequest {

    public AvailabilityRequest(ArrayList<Participant> participants)
    {
        this.participants = participants;
    }
    @JsonProperty(value = "response_format")
    public String responseFormat = "slots";

    @JsonProperty(value = "participants")
    public ArrayList<Participant> participants;

    @JsonProperty(value = "required_duration")
    public RequiredDuration requiredDuration;

    @JsonProperty(value = "query_periods")
    public ArrayList<QueryPeriod> queryPeriods = new ArrayList<QueryPeriod>();
}
