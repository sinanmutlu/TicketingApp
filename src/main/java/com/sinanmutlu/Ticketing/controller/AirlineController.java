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

import com.sinanmutlu.Ticketing.model.Airline;
import com.sinanmutlu.Ticketing.repository.AirlineRepository;

@RestController
@RequestMapping("/api")
public class AirlineController {

	@Autowired
	AirlineRepository airlineRepository;

	@GetMapping("/airline")
	public List<Airline> getAllNotes() {
		return airlineRepository.findAll();
	}

	@PostMapping("/addAirline")
	public Airline addAirline(@Valid @RequestBody Airline airline) {
		Date currentDate = new Date();
		airline.setCreatedAt(currentDate);
		airline.setUpdatedAt(currentDate);
		return airlineRepository.save(airline);
	}

	@PostMapping("/searchAirline")
	public List<Airline> searchAirline(@Valid @RequestBody Airline airline) {

		return airlineRepository.findByNameIgnoreCase(airline.getName());
	}

}
