package com.hfla.service.calendar.pojos;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
public class Availability {

	@Id
	@Generated
	long id;
	DateTime dayOfWeek;
	DateTime startTime;
	DateTime endTime;
	String nylasId;

	public List<Availability> calculate(List<ConnectedEvent> events) {
		return new ArrayList<>();
	}

	public List<Availability> calculateWithStreams(Stream<ConnectedEvent> events) {
		return new ArrayList<>();
	}
}
