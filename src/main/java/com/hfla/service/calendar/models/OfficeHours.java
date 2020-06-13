package com.hfla.service.calendar.models;

import org.joda.time.DateTime;

public class OfficeHours {
	private Day sunday;
	private Day monday;
	private Day tuesday;
	private Day wednesday;
	private Day thursday;
	private Day friday;
	private Day saturday;

	public class Day {
		private DateTime startTime;
		private DateTime endTime;

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

	public OfficeHours(Day sunday,
					   Day monday,
					   Day tuesday,
					   Day wedneday,
					   Day thursday,
					   Day friday,
					   Day saturday) {
		this.sunday = sunday;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wedneday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
	}

}
