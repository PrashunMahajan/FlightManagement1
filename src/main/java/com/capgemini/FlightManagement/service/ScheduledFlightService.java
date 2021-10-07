package com.capgemini.FlightManagement.service;
import java.math.BigInteger;


import com.capgemini.FlightManagement.exception.RecordNotFoundException;
import com.capgemini.FlightManagement.exception.ScheduledFlightNotFoundException;
import com.capgemini.FlightManagement.model.ScheduledFlight;


public interface ScheduledFlightService {
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);

	public String removeScheduledFlight(BigInteger id) throws RecordNotFoundException;

	public Iterable<ScheduledFlight> viewAllScheduledFlights();

	public ScheduledFlight viewScheduledFlight(BigInteger id) throws ScheduledFlightNotFoundException;
	// boolean cancelBookings(BigInteger flightId) throws RecordNotFoundException;

}
