package com.capgemini.FlightManagement.repository;

import java.math.BigInteger;

import com.capgemini.FlightManagement.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightReposiyory extends CrudRepository<Flight,BigInteger>{

}
