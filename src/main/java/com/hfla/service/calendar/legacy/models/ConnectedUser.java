package com.hfla.service.calendar.legacy.models;
import java.util.List;

import com.hfla.service.calendar.legacy.pojos.ConnectedCalendar;

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
