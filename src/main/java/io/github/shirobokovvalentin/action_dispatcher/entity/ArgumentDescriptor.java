package io.github.shirobokovvalentin.action_dispatcher.entity;

import io.github.shirobokovvalentin.action_dispatcher.constraint.exceptions.ViolationException;
import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.ComplexValidator;

import javax.servlet.http.HttpServletRequest;

public abstract class ArgumentDescriptor
{
	private ComplexValidator constraint;

	// ------------------------------------------------------------

	public abstract Object getArgument(HttpServletRequest request) throws ViolationException;

	// ------------------------------------------------------------

	public ComplexValidator getConstraint()
	{
		return this.constraint;
	}

	public void setConstraint(ComplexValidator constraint)
	{
		this.constraint = constraint;
	}
}