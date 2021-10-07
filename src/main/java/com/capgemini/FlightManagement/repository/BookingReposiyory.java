package com.capgemini.FlightManagement.repository;

import java.math.BigInteger;

import com.capgemini.FlightManagement.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingReposiyory extends CrudRepository<Booking, BigInteger> {

}
