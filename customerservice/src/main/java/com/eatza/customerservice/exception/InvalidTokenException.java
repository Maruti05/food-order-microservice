package com.eatza.customerservice.exception;

public class InvalidTokenException extends RuntimeException {
	
	public InvalidTokenException() {
		super();
	}
	public InvalidTokenException(String msg) {
		super(msg);
	}

}
