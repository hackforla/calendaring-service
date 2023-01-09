package com.hfla.service.calendar.pojos.Nylas;

import com.hfla.service.calendar.pojos.EventsInteface;
import com.nylas.Event;
import com.nylas.RemoteCollection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NylasEvents implements EventsInteface {

    private RemoteCollection<Event> eventRemoteCollection;

    public NylasEvents(RemoteCollection<Event> eventRemoteCollection){
        this.eventRemoteCollection = eventRemoteCollection;
    }
}
