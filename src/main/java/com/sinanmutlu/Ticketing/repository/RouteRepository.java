package com.sinanmutlu.Ticketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinanmutlu.Ticketing.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findBySourceCityAndDestinationCity(String sourceCity, String destinationCity);
	List<Route> findBySourceAirportIdAndDestinationAirportId(long sourceAirportId, long destinationAirportId);

}
