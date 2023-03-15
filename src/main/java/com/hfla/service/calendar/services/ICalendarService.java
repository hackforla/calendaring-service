package com.hfla.service.calendar.services;

import com.hfla.service.calendar.pojos.Cronify.Events;
import com.hfla.service.calendar.pojos.EventsInteface;
import com.nylas.RequestFailedException;
import org.joda.time.DateTime;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public interface ICalendarService {

    Events getEvents(String token) throws IOException, RequestFailedException;

    String getAvailability(String subscriptionId, ArrayList<String> calendarIds, String startDate, String endDate);

}
