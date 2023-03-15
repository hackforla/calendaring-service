package com.hfla.service.calendar.pojos.Cronify;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.Date;

public class QueryPeriod {
    public QueryPeriod (String startTime, String endTime)
    {
        this.start = startTime;
        this.end = endTime;
    }
    @JsonProperty(value = "start")
    public String start;

    @JsonProperty(value = "end")
    public String end;
}
