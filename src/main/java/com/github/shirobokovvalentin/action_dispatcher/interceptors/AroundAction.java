package com.github.shirobokovvalentin.action_dispatcher.interceptors;

import java.lang.reflect.Method;
import java.util.List;

public interface AroundAction
{
	public void beforeMethod(Method method, List<Object> arguments);

	public Object afterMethod(Object result);

	public void beforeExceptionHandling(Method method, Throwable e);

	public Object afterExceptionHandling(Object result);

}
