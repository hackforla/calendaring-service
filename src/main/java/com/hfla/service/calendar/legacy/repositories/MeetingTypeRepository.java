package com.hfla.service.calendar.legacy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hfla.service.calendar.legacy.pojos.MeetingType;

import java.util.List;

@Repository
public interface MeetingTypeRepository extends CrudRepository<MeetingType, Long> {

	MeetingType findById(long id);
	MeetingType findByMeetingType(String meetingType);
}
