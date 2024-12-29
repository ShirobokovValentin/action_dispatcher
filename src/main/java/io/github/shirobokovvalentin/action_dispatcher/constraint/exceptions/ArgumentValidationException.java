package io.github.shirobokovvalentin.action_dispatcher.constraint.exceptions;

import io.github.shirobokovvalentin.action_dispatcher.constraint.entity.Conclusion;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.RequestDispatcherException;

public class ArgumentValidationException extends RequestDispatcherException
{
	private static final long serialVersionUID = -8208681278594257248L;

	private Conclusion conclusion;

	// ------------------------------------------------------------

	public ArgumentValidationException(Conclusion conclusion)
	{
		this(conclusion.getMessage());
		this.conclusion = conclusion;
	}

	public ArgumentValidationException()
	{
		super();
	}

	public ArgumentValidationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ArgumentValidationException(String message)
	{
		super(message);
	}

	public ArgumentValidationException(Throwable cause)
	{
		super(cause);
	}

	// ------------------------------------------------------------

	public Conclusion getConclusion()
	{
		return this.conclusion;
	}

	public void setConclusion(Conclusion conclusion)
	{
		this.conclusion = conclusion;
	}

}
