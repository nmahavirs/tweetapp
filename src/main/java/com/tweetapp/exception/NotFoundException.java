package com.tweetapp.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3575530557825760740L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
