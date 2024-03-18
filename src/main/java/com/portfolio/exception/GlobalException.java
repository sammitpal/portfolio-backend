package com.portfolio.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(SessionExpiredException.class)
	public ResponseEntity<ErrorResponse> handleSessionExpired(SessionExpiredException e){
		ErrorResponse errResp = new ErrorResponse(LocalDateTime.now(),e.getMessage(),HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(errResp,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<ErrorResponse> jsonParseError(ParseException e){
		ErrorResponse errResp = new ErrorResponse(LocalDateTime.now(),e.getMessage(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errResp,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach(error -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	
}
