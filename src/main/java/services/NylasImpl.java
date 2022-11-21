package services;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hfla.service.calendar.services.CalendarService;
import com.nylas.Calendar;
//import com.nylas.Event;
import com.nylas.NylasAccount;
import com.nylas.NylasClient;
import com.nylas.RequestFailedException;
import com.nylas.Event.Timespan;

//import com.hfla.service.calendar.pojos.Calendar;
import com.hfla.service.calendar.pojos.Event;

public class NylasImpl implements CalendarInterface {
	
	 private final CalendarService calendarService;
	  private static NylasClient client = new NylasClient();

	  @Value("${access.token}")
	  private String accessToken;
	  
	  @Autowired
	  public NylasImpl(CalendarService calendarService) {
	    this.calendarService = calendarService;
	  }
	  
	  @Override
	  public Event createEvent(Instant start, Instant end) throws  RequestFailedException, IOException {
		  NylasAccount account = client.account(accessToken);
		    Calendar primaryCalendar = calendarService.getPrimaryCalendar();

		    Timespan ts = new com.nylas.Event.Timespan(start, end);


		    com.nylas.Event event = new com.nylas.Event(primaryCalendar.getId(), ts);

		
		    event.setBusy(true);
		    com.nylas.Event newEvent = account.events().create(event, false);
//		    return newEvent;
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
	public com.hfla.service.calendar.pojos.Calendar getCalendar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean freeBetween(Instant start, Instant end) {
		// TODO Auto-generated method stub
		return false;
	}
}
