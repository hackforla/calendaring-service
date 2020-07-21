package com.hfla.service.calendar.algorithm;

import com.hfla.service.calendar.pojos.ConnectedEvent;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeTimeService {

	public void perDay(List<ConnectedEvent> events) {
		DateTime startTime;
		DateTime endTime;
		DateTime freeTimeStart = new DateTime().now();
		DateTime freeTimeEnd;
		List<Interval> freeTimes = new ArrayList<>();

		//TODO: in Java8
		for (ConnectedEvent event : events) {
			startTime = new DateTime(event.getWhen().getStartTime());
			endTime = new DateTime(event.getWhen().getEndTime());
			freeTimeEnd = startTime;
			Interval myInterval = new Interval(freeTimeStart, freeTimeEnd);
			freeTimes.add(myInterval);
			freeTimeStart = endTime;
		}

		freeTimes.stream().forEach(x -> System.out.println());

	}
}
