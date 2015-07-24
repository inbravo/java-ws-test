package com.inbravo.erws.exception;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class ERWSException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ERWSException()
	{
		super();
	}

	public ERWSException(final String message)
	{
		super(message);
	}

	public ERWSException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
