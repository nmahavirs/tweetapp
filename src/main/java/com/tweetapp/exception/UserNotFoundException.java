package com.tweetapp.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3575530557825760740L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
