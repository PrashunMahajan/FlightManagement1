package com.capgemini.FlightManagement.service;
import com.capgemini.FlightManagement.exception.RecordAlreadyExistException;
import com.capgemini.FlightManagement.exception.RecordNotFoundException;
import com.capgemini.FlightManagement.model.Flight;
import com.capgemini.FlightManagement.repository.FlightReposiyory;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightReposiyory flightReposiyory;

	/*
	 * add a flight
	 */
	@Override
	public ResponseEntity<Flight> addFlight(Flight flight) {
		Optional<Flight> findById = flightReposiyory.findById(flight.getFlightNo());
		try {
		if (!findById.isPresent()) {
			flightReposiyory.save(flight);
			return new ResponseEntity<Flight>(flight,HttpStatus.OK);
		} else
			throw new RecordAlreadyExistException("Flight with number: " + flight.getFlightNo() + " already present");
	}
		catch(RecordAlreadyExistException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * view all flights
	 */
	@Override
	public Iterable<Flight> viewAllFlight() {
		return flightReposiyory.findAll();
	}

	/*
	 * search a flight
	 */
	@Override
	public Flight viewFlight(BigInteger flightNumber) {
		Optional<Flight> findById = flightReposiyory.findById(flightNumber);
		if (findById.isPresent()) {
			return findById.get();
		}
		else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");
	    }
		/*catch(RecordNotFoundException e)
		{
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}*/

	/*
	 * modify a flight
	 */
	@Override
	public Flight modifyFlight(Flight flight) {
		Optional<Flight> findById = flightReposiyory.findById(flight.getFlightNo());
		if (findById.isPresent()) {
			flightReposiyory.save(flight);
		} else
			throw new RecordNotFoundException("Flight with number: " + flight.getFlightNo() + " not exists");
		return flight;
	}

	/*
	 * remove a flight
	 */
	public String removeFlight(BigInteger flightNumber) {
		Optional<Flight> findById = flightReposiyory.findById(flightNumber);
		if (findById.isPresent()) {
			flightReposiyory.deleteById(flightNumber);
			return "Flight removed!!";
		} else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");

	}
}
