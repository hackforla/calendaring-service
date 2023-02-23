package com.hfla.service.calendar.pojos.Nylas;

import com.hfla.service.calendar.pojos.CalendarsInterface;
import com.nylas.Calendar;
import com.nylas.RemoteCollection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NylasCalendars implements CalendarsInterface {

    private RemoteCollection<Calendar> calendarRemoteCollection;

    public NylasCalendars(RemoteCollection<Calendar> calendarRemoteCollection){
        this.calendarRemoteCollection = calendarRemoteCollection;
    }
}
