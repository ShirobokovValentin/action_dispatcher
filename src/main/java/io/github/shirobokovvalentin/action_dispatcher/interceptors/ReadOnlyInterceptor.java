package io.github.shirobokovvalentin.action_dispatcher.interceptors;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

public abstract class ReadOnlyInterceptor implements UniversalInterceptor
{
	public abstract void beforeMethod(Method method, List<Object> arguments);

	public abstract void beforeExceptionHandling(Method method, Throwable e);

	public Object afterMethod(Object result)
	{
		afterMethodCall(result);
		return result;
	}

	public abstract void afterMethodCall(Object result);

	public Object afterExceptionHandling(Object result)
	{
		afterExceptionHandlingCall(result);
		return result;
	}

	public abstract void afterExceptionHandlingCall(Object result);

	public HttpServletRequest beforeExtraction(HttpServletRequest request)
	{
		beforeParamsExtraction(request);
		return request;
	}

	public abstract void beforeParamsExtraction(HttpServletRequest request);

	public String afterConverting(String result)
	{
		afterConvertingCall(result);
		return result;
	}

	public abstract void afterConvertingCall(String result);

}
