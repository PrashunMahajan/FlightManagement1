package com.capgemini.FlightManagement.service;
import com.capgemini.FlightManagement.exception.RecordAlreadyExistException;
import com.capgemini.FlightManagement.exception.RecordNotFoundException;
import com.capgemini.FlightManagement.model.Airport;
import com.capgemini.FlightManagement.repository.AirportReposiyory;
import java.math.BigInteger;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Service
public class AirportServiceImpl implements AirportService {
	@Autowired
	AirportReposiyory airportReposiyory;

	/*
	 * view all Airports
	 */
	@Override
	public Iterable<Airport> viewAllAirport() {
		return airportReposiyory.findAll();
	}

	/*
	 * view airport by airportCode
	 */
	@Override
	public Airport viewAirport(String airportCode) {
		Optional<Airport> findById = airportReposiyory.findById(airportCode);
		if (findById.isPresent()) {
			return findById.get();
		}
			
			//return new ResponseEntity<Airport>(airport, HttpStatus.OK)}
		else
			throw new RecordNotFoundException("Airport with airport code: " + airportCode + "not exists");
	    }
		/*catch(RecordNotFoundException e)
		{
			return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
        }*/

	/*
	 * add a airport
	 */
	@Override
	public ResponseEntity<?> addAirport(Airport airport) {
		Optional<Airport> findById = airportReposiyory.findById(airport.getAirportCode());
		try {
		if (!findById.isPresent()) {
			airportReposiyory.save(airport);
			return new ResponseEntity<Airport>(airport,HttpStatus.OK);
		} 
		else
			throw new RecordAlreadyExistException(
					"Airport with code : " + airport.getAirportCode() + " already present");
	     }
		catch(RecordAlreadyExistException e)
		{
			return new ResponseEntity<Airport>(airport,HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * modify an Airport
	 */
	@Override
	public Airport modifyAirport(Airport airport) {
		Optional<Airport> findById = airportReposiyory.findById(airport.getAirportCode());
		if (findById.isPresent()) {
			airportReposiyory.save(airport);
		} 
		else
			throw new RecordNotFoundException("Airport with code: " + airport.getAirportCode() + " not exists");
		return airport;
	}

	/*
	 * remove an airport
	 */
	@Override
	public String removeAirport(String airportCode) {
		Optional<Airport> findById = airportReposiyory.findById(airportCode);
		if (findById.isPresent()) {
			airportReposiyory.deleteById(airportCode);
			return "Airport removed";
		} else
			throw new RecordNotFoundException("Airport with code: " + airportCode + " not exists");

	}
}
