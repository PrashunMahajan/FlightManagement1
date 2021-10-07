package com.capgemini.FlightManagement.repository;

import com.capgemini.FlightManagement.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportReposiyory extends CrudRepository<Airport, String> {

}
