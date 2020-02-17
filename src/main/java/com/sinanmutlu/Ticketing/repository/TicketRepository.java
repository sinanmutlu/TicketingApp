package com.sinanmutlu.Ticketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinanmutlu.Ticketing.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByCustomerNameIgnoreCase(String customerName);
	List<Ticket> findByFlightId(long flightId);
	List<Ticket> findByCustomerName(String customerName);
	
}
