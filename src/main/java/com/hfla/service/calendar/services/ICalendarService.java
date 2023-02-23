package com.hfla.service.calendar.services;

import com.hfla.service.calendar.pojos.Cronify.Events;
import com.hfla.service.calendar.pojos.EventsInteface;
import com.nylas.RequestFailedException;

import java.io.IOException;
import java.time.Instant;

public interface ICalendarService {

    public Events getEvents(String token) throws IOException, RequestFailedException;

    public Object checkAvailability(Instant start, Instant end) throws IOException,RequestFailedException;

}
