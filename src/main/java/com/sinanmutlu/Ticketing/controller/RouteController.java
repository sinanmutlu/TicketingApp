package com.sinanmutlu.Ticketing.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinanmutlu.Ticketing.model.Route;
import com.sinanmutlu.Ticketing.repository.RouteRepository;

@RestController
@RequestMapping("/api")
public class RouteController {

	@Autowired
	RouteRepository routeRepository;

	// TODO comment out
	@GetMapping("/route")
	public List<Route> getAllNotes() {
		return routeRepository.findAll();
	}

	// route from an airport to an airport
	@PostMapping("/addRoute")
	public Route addRoute(@Valid @RequestBody Route route) {
		Date currentDate = new Date();
		route.setCreatedAt(currentDate);
		route.setUpdatedAt(currentDate);
		return routeRepository.save(route);
	}

	@PostMapping("/searchRouteByCity")
	public List<Route> searchRouteByCity(@Valid @RequestBody Route route) {

		return routeRepository.findBySourceCityAndDestinationCity(route.getSourceCity(), route.getDestinationCity());
	}
	
	@PostMapping("/searchRouteByAirport")
	public List<Route> searchRouteByAirport(@Valid @RequestBody Route route) {

		return routeRepository.findBySourceAirportIdAndDestinationAirportId(route.getSourceAirportId(), route.getDestinationAirportId());
	}

}
