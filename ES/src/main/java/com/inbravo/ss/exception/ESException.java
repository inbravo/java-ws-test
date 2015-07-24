package com.inbravo.ss.exception;

/**
 * 
 * @author amit.dixit
 *
 */
public final class ESException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ESException() {
		super();
	}

	public ESException(final String message) {
		super(message);
	}

	public ESException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
