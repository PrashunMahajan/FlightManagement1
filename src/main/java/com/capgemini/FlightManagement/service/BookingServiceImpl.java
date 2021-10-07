package com.capgemini.FlightManagement.service;
import java.math.BigInteger;
import java.util.Optional;

import com.capgemini.FlightManagement.exception.RecordAlreadyExistException;
import com.capgemini.FlightManagement.exception.RecordNotFoundException;
import com.capgemini.FlightManagement.model.Booking;
import com.capgemini.FlightManagement.repository.BookingReposiyory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class BookingServiceImpl implements BookingService {

	/*
	 * Creating DAO object
	 */
	@Autowired
	BookingReposiyory bookingReposiyory;

	/*
	 * making new Booking
	 */
	@Override
	public ResponseEntity<Booking> createBooking(Booking newBooking) {

		Optional<Booking> findBookingById = bookingReposiyory.findById(newBooking.getBookingId());
		try {
			if (!findBookingById.isPresent()) {
				bookingReposiyory.save(newBooking);
				return new ResponseEntity<Booking>(newBooking, HttpStatus.OK);
			} else
				throw new RecordAlreadyExistException(
						"Booking with Booking Id: " + newBooking.getBookingId() + " already exists!!");
		} catch (RecordAlreadyExistException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * update booking made
	 */
	@Override
	public Booking updateBooking(Booking changedBooking) {
		Optional<Booking> findBookingById = bookingReposiyory.findById(changedBooking.getBookingId());
		if (findBookingById.isPresent()) {
			bookingReposiyory.save(changedBooking);
		} else
			throw new RecordNotFoundException(
					"Booking with Booking Id: " + changedBooking.getBookingId() + " not exists!!");
		return changedBooking;
	}

	/*
	 * deleteing the booking
	 */

	@Override
	public String deleteBooking(BigInteger bookingId) {

		Optional<Booking> findBookingById = bookingReposiyory.findById(bookingId);
		if (findBookingById.isPresent()) {
			bookingReposiyory.deleteById(bookingId);
			return "Booking Deleted!!";
		} else
			throw new RecordNotFoundException("Booking not found for the entered BookingID");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.BookingService#displayAllBooking() show all booking
	 */
	@Override
	public Iterable<Booking> displayAllBooking() {

		return bookingReposiyory.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.org.service.BookingService#findBookingById(java.math.BigInteger)
	 * find booking by ID
	 */
	@Override
	public ResponseEntity<?> findBookingById(BigInteger bookingId) {
		Optional<Booking> findById = bookingReposiyory.findById(bookingId);
		try {
			if (findById.isPresent()) {
				Booking findBooking = findById.get();
				return new ResponseEntity<Booking>(findBooking, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + bookingId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
