package com.sinanmutlu.Ticketing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinanmutlu.Ticketing.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findByAirlineId(long airlineId);

	List<Flight> findByRouteId(long routeId);

	Flight findOneById(long Id);

}
