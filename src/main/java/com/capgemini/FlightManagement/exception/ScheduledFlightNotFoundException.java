package com.capgemini.FlightManagement.exception;

public class ScheduledFlightNotFoundException extends RuntimeException {
    public ScheduledFlightNotFoundException(String s) {
        super(s);
    }
}
