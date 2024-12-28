package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.action_dispatcher.entity.AbstractMethodDescriptor;

import java.lang.reflect.Method;

public abstract class AbstractMethodScanner
{
	protected void processReturnType(AbstractMethodDescriptor descriptor)
	{
		Method method = descriptor.getMethod();
		Class<?> returnType = method.getReturnType();
		descriptor.setReturnType(returnType);
	}
	
}
