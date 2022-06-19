package com.tweetapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tweetapp.model.APIResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException exception) {
		return new ResponseEntity<>(new APIResponse(null, exception.getMessage(), null), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(UserNotFoundException exception) {
		return new ResponseEntity<>(new APIResponse(null, exception.getMessage(), null), HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(
	  MethodArgumentNotValidException exception) {
	    Map<String, String> errors = new HashMap<>();
	    exception.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return new ResponseEntity<>(new APIResponse(null, "Invalid Input Request", errors), HttpStatus.BAD_REQUEST);
	}
}