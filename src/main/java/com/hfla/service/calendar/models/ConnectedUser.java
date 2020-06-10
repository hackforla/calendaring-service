package com.hfla.service.calendar.models;
import com.hfla.service.calendar.pojos.ConnectedCalendar;

import java.util.List;

public class ConnectedUser {
	private String nylasId;
	private Long id;
	private List<ConnectedCalendar> calendars;
	private String accessToken;

	public ConnectedUser(Long id) {
		this.id = id;
		this.nylasId = nylasId;
		this.accessToken = new ConnectedCredentials(nylasId).getAccessToken();
		this.calendars = new ConnectedCalendar(nylasId).getCalendars();
	}
}
