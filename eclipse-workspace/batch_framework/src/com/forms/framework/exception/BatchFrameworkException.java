package com.forms.framework.exception;

public class BatchFrameworkException extends Exception
{

	private static final long serialVersionUID = 1L;

	public BatchFrameworkException()
	{
		super();
	}

	public BatchFrameworkException(String ip_message)
	{
		super(ip_message);
	}

	public BatchFrameworkException(Throwable ip_cause)
	{
		super(ip_cause);
	}

	public BatchFrameworkException(String ip_message, Throwable ip_cause)
	{
		super(ip_message, ip_cause);
	}

}
