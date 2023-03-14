package com.hfla.service.calendar.pojos.Cronify;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class Participant {

    @JsonProperty(value = "members")
    public ArrayList<Member> members = new ArrayList<Member>();

    @JsonProperty(value = "required")
    public String required;
}
