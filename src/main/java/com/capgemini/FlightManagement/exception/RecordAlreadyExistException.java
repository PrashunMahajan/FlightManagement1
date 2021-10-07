package com.capgemini.FlightManagement.exception;

public class RecordAlreadyExistException extends RuntimeException {
    public RecordAlreadyExistException(String s) {
        super(s);
    }
}
