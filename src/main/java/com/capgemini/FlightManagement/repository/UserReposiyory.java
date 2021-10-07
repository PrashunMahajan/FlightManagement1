package com.capgemini.FlightManagement.repository;

import java.math.BigInteger;

import com.capgemini.FlightManagement.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserReposiyory extends CrudRepository<Users, BigInteger>{

}