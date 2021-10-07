package com.capgemini.FlightManagement.service;
import java.math.BigInteger;

import com.capgemini.FlightManagement.model.Users;
import org.springframework.http.ResponseEntity;


public interface UserService {

	public ResponseEntity<?> createUser(Users newUser);

	public Users updateUser(Users newUser);

	public String deleteUser(BigInteger UserId);

	public Iterable<Users> displayAllUser();

	public ResponseEntity<?> findUserById(BigInteger userId);
}