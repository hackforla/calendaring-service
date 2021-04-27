package com.hfla.service.calendar.repositories;

import com.hfla.service.calendar.pojos.MeetingType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingTypeRepository extends CrudRepository<MeetingType, Long> {

	MeetingType findById(long id);
	MeetingType findByMeetingType(String meetingType);
}
