package com.inbravo.esws.exception;

/**
 * 
 * @author amit.dixit
 * 
 */
public final class ESWSException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public ESWSException()
	{
		super();
	}

	public ESWSException(final String message)
	{
		super(message);
	}

	public ESWSException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
