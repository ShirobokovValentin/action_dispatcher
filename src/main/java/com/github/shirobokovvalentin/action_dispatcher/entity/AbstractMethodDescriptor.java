package com.github.shirobokovvalentin.action_dispatcher.entity;

import java.lang.reflect.Method;

public abstract class AbstractMethodDescriptor
{
	private Method method;
	private Class<?> returnType;
	
	// ------------------------------------------------------------

	public AbstractMethodDescriptor()
	{
	}
	
	public AbstractMethodDescriptor(Method method)
	{
		setMethod(method);
	}
	
	// ------------------------------------------------------------
	
	public Method getMethod()
	{
		return this.method;
	}

	public void setMethod(Method method)
	{
		this.method = method;
	}
	
	public Class<?> getReturnType()
	{
		return this.returnType;
	}

	public void setReturnType(Class<?> returnType)
	{
		this.returnType = returnType;
	}

}
