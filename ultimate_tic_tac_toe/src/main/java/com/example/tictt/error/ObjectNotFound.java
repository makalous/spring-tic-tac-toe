package com.example.tictt.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFound extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFound() {
		super();
	}

	public ObjectNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFound(String message) {
		super(message);
	}

	public ObjectNotFound(Throwable cause) {
		super(cause);
	}
}
