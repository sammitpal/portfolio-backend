package com.portfolio.exception;

public class SessionExpiredException extends RuntimeException{

	public SessionExpiredException(String msg) {
		super(msg);
	}
	
}
