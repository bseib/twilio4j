package com.twilio4j.twism;

public class CookieTamperedException extends Exception {
	private static final long serialVersionUID = 1L;

	public CookieTamperedException() {
	}

	public CookieTamperedException(String message) {
		super(message);
	}

	public CookieTamperedException(Throwable cause) {
		super(cause);
	}

	public CookieTamperedException(String message, Throwable cause) {
		super(message, cause);
	}

}
