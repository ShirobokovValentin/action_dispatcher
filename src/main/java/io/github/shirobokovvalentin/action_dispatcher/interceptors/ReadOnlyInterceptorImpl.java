package io.github.shirobokovvalentin.action_dispatcher.interceptors;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

public class ReadOnlyInterceptorImpl extends ReadOnlyInterceptor
{

	public void beforeMethod(Method method, List<Object> arguments)
	{
		// TODO Auto-generated method stub
		
	}

	public void beforeExceptionHandling(Method method, Throwable e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterMethodCall(Object result)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterExceptionHandlingCall(Object result)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeParamsExtraction(HttpServletRequest request)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterConvertingCall(String result)
	{
		// TODO Auto-generated method stub
		
	}

}
