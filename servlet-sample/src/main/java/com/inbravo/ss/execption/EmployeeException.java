package com.inbravo.ss.execption;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeException() {
		super();
	}

	public EmployeeException(final String message) {
		super(message);
	}

	public EmployeeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
