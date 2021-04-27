package com.hfla.service.calendar.pojos;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
public class Availability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private DateTime dayOfWeek;
	private DateTime startTime;
	private DateTime endTime;
	private String nylasId;

	public List<Availability> calculate(List<ConnectedEvent> events) {
		return new ArrayList<>();
	}

	public List<Availability> calculateWithStreams(Stream<ConnectedEvent> events) {
		return new ArrayList<>();
	}
}
