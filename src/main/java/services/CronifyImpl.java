package services;

import java.io.IOException;
import java.time.Instant;

import com.hfla.service.calendar.pojos.Calendar;
import com.hfla.service.calendar.pojos.Event;
import com.nylas.RequestFailedException;

public class CronifyImpl implements CalendarInterface {

	@Override
	public Event createEvent(Instant start, Instant end) throws  RequestFailedException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEvent(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event returnEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Calendar getCalendar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean freeBetween(Instant start, Instant end) {
		// TODO Auto-generated method stub
		return false;
	}

}
