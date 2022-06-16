package com.tweetapp.exception;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3575530557825760740L;

	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
