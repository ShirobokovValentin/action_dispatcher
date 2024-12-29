package io.github.shirobokovvalentin.convertors.exceptions;

import io.github.shirobokovvalentin.BaseException;

public class ConvertorException extends BaseException
{

	private static final long serialVersionUID = -6911495157354783727L;

	// ------------------------------------------------------------

	public ConvertorException()
	{
		super();
	}

	public ConvertorException(Throwable cause)
	{
		super(cause);
	}

	public ConvertorException(String message)
	{
		super(message);
	}

	public ConvertorException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
