package com.sinanmutlu.Ticketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sinanmutlu.Ticketing.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

	List<Airline> findByNameIgnoreCase(String name);
}
