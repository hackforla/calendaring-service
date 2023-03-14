package com.hfla.service.calendar.pojos.Cronify;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Member {

    public Member() {}
    public Member(String subscription, List<String> calendarIds)
    {
        this.subscription = subscription;
        this.calendarIds = calendarIds;
    }

    @JsonProperty(value = "sub")
    public String subscription;
    @JsonProperty(value = "calendar_ids")
    public List<String> calendarIds;
}
