package com.hfla.service.calendar.legacy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hfla.service.calendar.legacy.pojos.Availability;

import java.util.List;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

	public List<Availability> findById(long id);
	public List<Availability> findByNylasId(String nylasId);
}
