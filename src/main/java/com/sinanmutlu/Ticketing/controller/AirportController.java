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

import com.sinanmutlu.Ticketing.model.Airport;
import com.sinanmutlu.Ticketing.repository.AirportRepository;

@RestController
@RequestMapping("/api")
public class AirportController {

	@Autowired
	AirportRepository airportRepository;

	//TODO comment out 
	@GetMapping("/airport")
	public List<Airport> getAllNotes() {
		return airportRepository.findAll();
	}

	@PostMapping("/addAirport")
	public Airport addAirport(@Valid @RequestBody Airport airport) {
		Date currentDate = new Date();
		airport.setCreatedAt(currentDate);
		airport.setUpdatedAt(currentDate);
		return airportRepository.save(airport);
	}

    @PostMapping("/searchAirportName")
    public List<Airport> searchAirportName(@Valid @RequestBody Airport airport) {

    	//TODO null check ve one göre response dön
        return airportRepository.findByLocationIgnoreCase(airport.getName());
    }
    
    @PostMapping("/searchAirportLocation")
    public List<Airport> searchAirportLocation(@Valid @RequestBody Airport airport) {

    	//TODO null check ve one göre response dön
        return airportRepository.findByLocationIgnoreCase(airport.getLocation());
    }

}
