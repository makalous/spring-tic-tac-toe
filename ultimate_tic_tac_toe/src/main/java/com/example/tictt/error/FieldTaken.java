package com.example.tictt.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FieldTaken extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FieldTaken() {
        super();
    }
    public FieldTaken(String message, Throwable cause) {
        super(message, cause);
    }
    public FieldTaken(String message) {
        super(message);
    }
    public FieldTaken(Throwable cause) {
        super(cause);
    }
}