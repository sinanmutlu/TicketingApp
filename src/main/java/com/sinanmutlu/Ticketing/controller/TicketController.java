package com.sinanmutlu.Ticketing.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinanmutlu.Ticketing.model.Flight;
import com.sinanmutlu.Ticketing.model.Ticket;
import com.sinanmutlu.Ticketing.repository.FlightRepository;
import com.sinanmutlu.Ticketing.repository.TicketRepository;

@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	FlightRepository flightRepository;

	// TODO comment out
	@GetMapping("/ticket")
	public List<Ticket> getAllNotes() {
		return ticketRepository.findAll();
	}

	@PostMapping("/buyTicket")
	public Ticket addFlight(@Valid @RequestBody Ticket ticket) {
		Date currentDate = new Date();
		ticket.setCreatedAt(currentDate);
		ticket.setUpdatedAt(currentDate);

		Flight flight = flightRepository.findOneById(ticket.getFlightId());

		flight.setUpdatedAt(currentDate);
		flight.setCurrentCapacity(flight.getCurrentCapacity() + 1);
		flight.setCurrentPrice(flight.getPrice() / (int) (flight.getCurrentCapacity() / flight.getCapacity()) * 10);
		flightRepository.save(flight);

		return ticketRepository.save(ticket);
	}

	@PostMapping("/searchTicketById")
	public Optional<Ticket> searchTicketById(@Valid @RequestBody Ticket ticket) {

		return ticketRepository.findById(ticket.getId());
	}

	@PostMapping("/searchTicketByFlight")
	public List<Ticket> searchTicketByFlight(@Valid @RequestBody Flight flight) {

		return ticketRepository.findByFlightId(flight.getId());
	}

	@PostMapping("/searchTicketByCustomerName")
	public List<Ticket> searchTicketByCustomerName(@Valid @RequestBody Ticket ticket) {

		return ticketRepository.findByCustomerName(ticket.getCustomerName());
	}

	@PostMapping("/cancelTicket")
	public String cancelTicket(@Valid @RequestBody Ticket ticket) {

		ticketRepository.deleteById(ticket.getId());

		return "Ticket (id:" + ticket.getId() + ") deleted";
	}

}
