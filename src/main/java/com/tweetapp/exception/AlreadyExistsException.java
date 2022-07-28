package com.tweetapp.exception;

public class AlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3575530557825760740L;

	public AlreadyExistsException(String msg) {
		super(msg);
	}
}
