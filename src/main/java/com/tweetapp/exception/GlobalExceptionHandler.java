package com.tweetapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.tweetapp.model.response.APIResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<APIResponse> handleAlreadyExists(AlreadyExistsException exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getMessage(), null),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<APIResponse> handleNotFound(NotFoundException exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getMessage(), null),
				HttpStatus.ACCEPTED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<APIResponse>(new APIResponse(null, "Invalid Input Request", errors),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<APIResponse> handleValidationExceptions(ResponseStatusException exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getReason(), null),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse> handleValidationExceptions(Exception exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}