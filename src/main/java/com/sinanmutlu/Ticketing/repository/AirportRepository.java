package com.sinanmutlu.Ticketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sinanmutlu.Ticketing.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	List<Airport> findByNameIgnoreCase(String name);
	List<Airport> findByLocationIgnoreCase(String location);
}
