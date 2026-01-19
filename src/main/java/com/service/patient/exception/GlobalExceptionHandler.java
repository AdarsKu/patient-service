package com.service.patient.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// this will provide logging in this global exception
	private static final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);
  
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
		
		Map<String,String> res=new HashMap<String,String>();
		ex.getBindingResult().getFieldErrors().forEach(er->res.put(er.getField(),er.getDefaultMessage()));
		
		return  ResponseEntity.badRequest().body(res);
	}
	
	// exception for internal server error 
//	 @ExceptionHandler(Exception.class)
//	    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
//
//	        Map<String, Object> error = new HashMap<>();
//	        error.put("timestamp", LocalDateTime.now());
//	        error.put("status", 500);
//	        error.put("error", "Internal Server Error");
//	        error.put("message", ex.getMessage());
//	        
//	    
//	        log.warn("error in GlobalExceptionHandler class ");
//
//	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<Map<String,String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex){
		
		log.warn("Email Already Exists {}",ex.getMessage());
		
		Map<String,String> err=new HashMap<String,String>();
		err.put("message", "Email Already Exist");
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler()
	public ResponseEntity<Map<String, String>> handlePatientNotFound(PatientNotFoundException ex){
		log.warn("Patient Not Foud On the given id");
		Map<String,String> map=new HashMap<String,String>();
		map.put("message", "There is no patient on the given id");
		
		return ResponseEntity.badRequest().body(map);
	}
}
