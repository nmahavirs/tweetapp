package com.tweetapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tweetapp.model.response.APIResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse> handleValidationExceptions(Exception exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<APIResponse> handleUserAlreadyExists(UserAlreadyExistsException exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getMessage(), null),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<APIResponse> handleUserNotFound(UserNotFoundException exception) {
		return new ResponseEntity<APIResponse>(new APIResponse(null, exception.getMessage(), null),
				HttpStatus.NO_CONTENT);
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
}