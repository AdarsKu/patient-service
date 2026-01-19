package com.service.patient.exception;


// it makes easy to debug than Directly using runtime exception
public class EmailAlreadyExistException extends RuntimeException {

	public EmailAlreadyExistException(String message) {
		super(message);
		
	}

	

}
