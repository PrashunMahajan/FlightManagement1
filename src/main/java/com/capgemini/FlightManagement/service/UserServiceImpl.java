package com.capgemini.FlightManagement.service;
import java.math.BigInteger;

import java.util.Optional;

import com.capgemini.FlightManagement.exception.RecordAlreadyExistException;
import com.capgemini.FlightManagement.exception.RecordNotFoundException;
import com.capgemini.FlightManagement.model.Users;
import com.capgemini.FlightManagement.repository.UserReposiyory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserReposiyory userReposiyory;
	
	@Override
	public ResponseEntity<?> createUser(Users newUser) {
		// TODO Auto-generated method stub
		Optional<Users> findUserById = userReposiyory.findById(newUser.getUserId());
		try {
			if (!findUserById.isPresent()) {
				userReposiyory.save(newUser);
				return new ResponseEntity<Users>(newUser, HttpStatus.OK);
			} else
				throw new RecordAlreadyExistException(
						"User with Id: " + newUser.getUserId() + " already exists!!");
		} catch (RecordAlreadyExistException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Users updateUser(Users updateUser) {
		// TODO Auto-generated method stub
		Optional<Users> findUserById = userReposiyory.findById(updateUser.getUserId());
		if (findUserById.isPresent()) {
			userReposiyory.save(updateUser);
		} else
			throw new RecordNotFoundException(
					"User with Id: " + updateUser.getUserId() + " not exists!!");
		return updateUser;
	}

	@Override
	public String deleteUser(BigInteger UserId) {
		// TODO Auto-generated method stub
		Optional<Users> findBookingById = userReposiyory.findById(UserId);
		if (findBookingById.isPresent()) {
			userReposiyory.deleteById(UserId);
			return "User Deleted!!";
		} else
			throw new RecordNotFoundException("User not found for the entered UserID");
	}

	@Override
	public Iterable<Users> displayAllUser() {
		// TODO Auto-generated method stub
		return userReposiyory.findAll();
	}

	@Override
	public ResponseEntity<?> findUserById(BigInteger userId) {
		// TODO Auto-generated method stub
		Optional<Users> findById = userReposiyory.findById(userId);
		try {
			if (findById.isPresent()) {
				Users findUser = findById.get();
				return new ResponseEntity<Users>(findUser, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + userId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}