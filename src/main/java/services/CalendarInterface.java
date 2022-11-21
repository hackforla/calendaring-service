package services;

import java.io.IOException;
import java.time.Instant;

import com.hfla.service.calendar.pojos.Calendar;
import com.hfla.service.calendar.pojos.Event;
import com.nylas.RequestFailedException;

public interface CalendarInterface {
	
	public Event createEvent(Instant start, Instant end) throws IOException, RequestFailedException;

	public String getEvent(Event event) ;
	
	public Event returnEvent();
	
	
	
	public Calendar getCalendar();
	
	public boolean freeBetween(Instant start, Instant end);
	
	
	
	
}
