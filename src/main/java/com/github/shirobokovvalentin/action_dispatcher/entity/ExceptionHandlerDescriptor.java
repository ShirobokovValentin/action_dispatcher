package com.github.shirobokovvalentin.action_dispatcher.entity;

import java.lang.reflect.Method;

public class ExceptionHandlerDescriptor extends AbstractMethodDescriptor
{
	private Class<? extends Exception> argumentType;

	// ------------------------------------------------------------

	public ExceptionHandlerDescriptor()
	{
		super();
	}

	public ExceptionHandlerDescriptor(Method method)
	{
		super(method);
	}

	// ------------------------------------------------------------

	public Class<? extends Exception> getArgumentType()
	{
		return this.argumentType;
	}

	public void setArgumentType(Class<? extends Exception> argumentType)
	{
		this.argumentType = argumentType;
	}

}
