package com.hfla.service.calendar.repositories;

import com.hfla.service.calendar.pojos.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

	public List<Availability> findById(long id);
	public List<Availability> findByNylasId(String nylasId);
}
