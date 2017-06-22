package com.inbravo.csdm.exception;

/**
 * 
 * @author amit.dixit
 *
 */
public final class CSMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CSMException() {
		super();
	}

	public CSMException(final String message) {
		super(message);
	}

	public CSMException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
