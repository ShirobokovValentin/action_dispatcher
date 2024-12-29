package io.github.shirobokovvalentin.action_dispatcher.exceptions;

import io.github.shirobokovvalentin.BaseException;

public class RequestDispatcherException extends BaseException
{
	private static final long serialVersionUID = -4149413181576101647L;

	// ------------------------------------------------------------

	public RequestDispatcherException()
	{
		super();
	}

	public RequestDispatcherException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RequestDispatcherException(String message)
	{
		super(message);
	}

	public RequestDispatcherException(Throwable cause)
	{
		super(cause);
	}

}
