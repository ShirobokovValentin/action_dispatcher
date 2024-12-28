package com.github.shirobokovvalentin.action_dispatcher.constraint.exceptions;

import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.Violation;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.RequestDispatcherException;

public class ViolationException extends RequestDispatcherException
{
	private static final long serialVersionUID = 4142180372888637778L;

	// ------------------------------------------------------------

	private final String paramName;

	private final Violation violation;

	public ViolationException(String paramName, Violation violation)
	{
		this.paramName = paramName;
		this.violation = violation;
	}

	// ------------------------------------------------------------

	public String getParamName()
	{
		return this.paramName;
	}

	public Violation getViolation()
	{
		return this.violation;
	}

}
