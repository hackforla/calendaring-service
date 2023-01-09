package com.hfla.service.calendar.services;

import com.hfla.service.calendar.pojos.CalendarsInterface;
import com.hfla.service.calendar.pojos.EventsInteface;
import com.nylas.RequestFailedException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CalendarService {




  public EventsInteface getEvents() throws IOException, RequestFailedException;

  public CalendarsInterface getCalendars() throws RequestFailedException, IOException;

  public String createEvent(EventsInteface event);


}
