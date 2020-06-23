package com.hfla.service.calendar.models;

import org.joda.time.DateTime;

public class OfficeHours {

	private DateTime startTime;
	private DateTime endTime;
	private Day dayOfWeek;

	public enum Day {
		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY;
	}

	public OfficeHours(Day dayOfWeek, DateTime startTime, DateTime endTime) {
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}


	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}

}
