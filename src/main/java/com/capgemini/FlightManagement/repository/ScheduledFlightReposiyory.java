package com.capgemini.FlightManagement.repository;

import java.math.BigInteger;

import com.capgemini.FlightManagement.model.ScheduledFlight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledFlightReposiyory extends CrudRepository<ScheduledFlight, BigInteger>{

}
