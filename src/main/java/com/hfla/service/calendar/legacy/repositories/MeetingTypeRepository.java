package com.hfla.service.calendar.legacy.repositories;

import com.hfla.service.calendar.legacy.pojos.MeetingType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingTypeRepository extends CrudRepository<MeetingType, Long> {

	MeetingType findById(long id);
	MeetingType findByMeetingType(String meetingType);
}
