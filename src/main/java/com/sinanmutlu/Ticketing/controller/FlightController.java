package com.sinanmutlu.Ticketing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinanmutlu.Ticketing.model.Airline;
import com.sinanmutlu.Ticketing.model.Flight;
import com.sinanmutlu.Ticketing.model.Route;
import com.sinanmutlu.Ticketing.repository.FlightRepository;
import com.sinanmutlu.Ticketing.repository.RouteRepository;

@RestController
@RequestMapping("/api")
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	RouteRepository routeRepository;

	// TODO comment out
	@GetMapping("/flight")
	public List<Flight> getAllNotes() {
		return flightRepository.findAll();
	}

	@PostMapping("/addFlight")
	public Flight addFlight(@Valid @RequestBody Flight flight) {
		Date currentDate = new Date();
		flight.setCreatedAt(currentDate);
		flight.setUpdatedAt(currentDate);
		return flightRepository.save(flight);

	}

	@PostMapping("/searchAirportFlight")
	public List<Flight> searchAirportFlight(@Valid @RequestBody Airline airline) {

		return flightRepository.findByAirlineId(airline.getId());
	}

	@PostMapping("/searchRouteFlight")
	public List<Flight> searchRouteFlight(@Valid @RequestBody Route route) {

		return flightRepository.findByRouteId(route.getId());
	}

	@PostMapping("/searchFlightByCities")
	public List<Flight> searchFlightByCities(@Valid @RequestBody Route tempRoute) {

		List<Route> routes = new ArrayList<>();
		List<Flight> flightList = new ArrayList<>();
		routes = routeRepository.findBySourceCityAndDestinationCity(tempRoute.getSourceCity(),
				tempRoute.getDestinationCity());
		for (Route route : routes) {
			flightList.addAll(flightRepository.findByRouteId(route.getId()));
		}

		return flightList;
	}

}
