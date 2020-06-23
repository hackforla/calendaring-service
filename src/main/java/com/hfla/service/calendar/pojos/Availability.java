package com.hfla.service.calendar.pojos;

import com.hfla.service.calendar.models.OfficeHours;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Availability {

	private DateTime startTime;
	private DateTime endTime;
	private static OfficeHours SUNDAY;
	private static OfficeHours MONDAY;
	private static OfficeHours TUESDAY;
	private static OfficeHours WEDNESDAY;
	private static OfficeHours THURSDAY;
	private static OfficeHours FRIDAY;
	private static OfficeHours SATURDAY;

	static {
		OfficeHours SUNDAY = new OfficeHours(OfficeHours.Day.SUNDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
		OfficeHours MONDAY = new OfficeHours(OfficeHours.Day.MONDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
		OfficeHours TUESDAY = new OfficeHours(OfficeHours.Day.TUESDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
		OfficeHours WEDNESDY = new OfficeHours(OfficeHours.Day.WEDNESDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
		OfficeHours THURSDAY = new OfficeHours(OfficeHours.Day.THURSDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
		OfficeHours FRIDAY = new OfficeHours(OfficeHours.Day.FRIDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
		OfficeHours SATURDAY = new OfficeHours(OfficeHours.Day.SATURDAY,
				new DateTime().withTime(8,30,00,00),
				new DateTime().withTime(10,00,00, 00));
	}

	public List<Availability> calculate(List<ConnectedEvent> events) {
		List<OfficeHours> officeHours =  Arrays.asList(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY);
		return new ArrayList<>();
	}

	public List<Availability> calculateWithStreams(Stream<ConnectedEvent> events) {
		Stream<OfficeHours> officeHours =  Stream.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY);
		return new ArrayList<>();
	}
}
