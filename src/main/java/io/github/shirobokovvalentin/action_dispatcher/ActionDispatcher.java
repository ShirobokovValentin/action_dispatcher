package io.github.shirobokovvalentin.action_dispatcher;

import io.github.shirobokovvalentin.convertors.ConvertorFactory;
import io.github.shirobokovvalentin.convertors.ConvertorService;
import io.github.shirobokovvalentin.action_dispatcher.constraint.entity.Conclusion;
import io.github.shirobokovvalentin.action_dispatcher.constraint.exceptions.ArgumentValidationException;
import io.github.shirobokovvalentin.action_dispatcher.constraint.exceptions.ViolationException;
import io.github.shirobokovvalentin.action_dispatcher.entity.ActionDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.entity.ArgumentDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.entity.DispatcherDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.entity.ExceptionHandlerDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.RequestDispatcherException;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;
import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundAction;
import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundDispatching;
import io.github.shirobokovvalentin.action_dispatcher.scanners.DispatcherScanner;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ActionDispatcher
{
	private final Object dispatcher;

	private final DispatcherDescriptor dispatcherDescriptor;

	// ------------------------------------------------------------

	private ActionDispatcher(Object dispatcher, DispatcherDescriptor dispatcherDescriptor)
	{
		this.dispatcher = dispatcher;
		this.dispatcherDescriptor = dispatcherDescriptor;
	}

	public static ActionDispatcher newInstance(Object dispatcher) throws InvalidMappingException
	{
		DispatcherScanner dispatcherScanner = new DispatcherScanner();
		DispatcherDescriptor dispatcherDescriptor = dispatcherScanner.scan(dispatcher.getClass());
		return new ActionDispatcher(dispatcher, dispatcherDescriptor);
	}

	// ------------------------------------------------------------

	public String process(HttpServletRequest request)
	{
		try
		{
			return tryProcess(request);

		} catch (RequestDispatcherException e)
		{
			throw e;
		} catch (Error e)
		{
			throw e;
		} catch (Throwable e)
		{
			throw new RequestDispatcherException(e);
		}
	}

	private String tryProcess(HttpServletRequest request) throws Throwable
	{
		HttpServletRequest modifiedRequest = applyBeforeExtractionInterceptors(request);

		String actionParamName = dispatcherDescriptor.getActionParamName();
		String actionValue = actionParamName != null ? modifiedRequest.getParameter(actionParamName) : null;

		ActionDescriptor actionDescriptor = dispatcherDescriptor.getAction(actionValue);
		List<ArgumentDescriptor> paramDescriptors = actionDescriptor.getArgumentDescriptors();

		Method method = actionDescriptor.getMethod();
		Object invocationResult;
		try
		{
			List<Object> arguments = extractArguments(paramDescriptors, modifiedRequest);
			applyBeforeMethodInterceptors(method, arguments, actionDescriptor);
			invocationResult = method.invoke(dispatcher, arguments.toArray());
			invocationResult = applyAfterMethodInterceptors(invocationResult, actionDescriptor);
		} catch (InvocationTargetException ite)
		{
			Throwable e = ite.getCause();
			invocationResult = handleException(e, actionDescriptor);
		} catch (Exception e)
		{
			invocationResult = handleException(e, actionDescriptor);
		}

		ConvertorService convertorService = ConvertorFactory.getConvertorService(actionDescriptor.getMediaType());
		String result = convertorService.convert(invocationResult);
		String modifiedResult = applyAfterConvertingInterceptors(result);

		return modifiedResult;
	}

	private Object handleException(Throwable e, ActionDescriptor actionDescriptor) throws Throwable
	{
		ExceptionHandlerDescriptor exceptionHandler = dispatcherDescriptor.getSuitableExceptionHandler(e);
		if (exceptionHandler == null)
			throw e;

		Method handlerMethod = exceptionHandler.getMethod();
		applyBeforeExceptionHandling(handlerMethod, e, actionDescriptor);
		Object invocationResult = handlerMethod.invoke(dispatcher, e);
		invocationResult = applyAfterExceptionHandling(invocationResult, actionDescriptor);
		return invocationResult;
	}

	private HttpServletRequest applyBeforeExtractionInterceptors(HttpServletRequest request)
	{
		List<AroundDispatching> interceptors = dispatcherDescriptor.getDispatchingInterceptors();

		HttpServletRequest result = request;
		for (AroundDispatching interceptor : interceptors)
		{
			result = interceptor.beforeExtraction(result);
		}
		return request;
	}

	private void applyBeforeMethodInterceptors(Method method, List<Object> arguments, ActionDescriptor actionDescriptor)
	{
		for (AroundAction interceptor : findInterceptors(actionDescriptor))
		{
			interceptor.beforeMethod(method, arguments);
		}
	}

	private Object applyAfterMethodInterceptors(Object obj, ActionDescriptor actionDescriptor)
	{
		Object result = obj;
		for (AroundAction interceptor : findInterceptors(actionDescriptor))
		{
			result = interceptor.afterMethod(result);
		}
		return result;
	}

	private void applyBeforeExceptionHandling(Method method, Throwable arguments, ActionDescriptor actionDescriptor)
	{
		for (AroundAction interceptor : findInterceptors(actionDescriptor))
		{
			interceptor.beforeExceptionHandling(method, arguments);
		}
	}

	private Object applyAfterExceptionHandling(Object obj, ActionDescriptor actionDescriptor)
	{
		Object result = obj;
		for (AroundAction interceptor : findInterceptors(actionDescriptor))
		{
			result = interceptor.afterExceptionHandling(result);
		}
		return result;
	}

	private List<AroundAction> findInterceptors(ActionDescriptor actionDescriptor)
	{
		if (actionDescriptor.getInterceptors() != null)
			return actionDescriptor.getInterceptors();

		return dispatcherDescriptor.getActionInterceptors();
	}

	private String applyAfterConvertingInterceptors(String result)
	{
		List<AroundDispatching> interceptors = dispatcherDescriptor.getDispatchingInterceptors();

		String newResult = result;
		for (AroundDispatching interceptor : interceptors)
		{
			newResult = interceptor.afterConverting(newResult);
		}
		return newResult;
	}

	private List<Object> extractArguments(List<ArgumentDescriptor> paramDescriptors, HttpServletRequest request) throws ArgumentValidationException
	{
		List<Object> arguments = new ArrayList<Object>();
		Conclusion conclusion = new Conclusion();

		for (ArgumentDescriptor argumentDescriptor : paramDescriptors)
		{
			try
			{
				Object valueFromRequest = argumentDescriptor.getArgument(request);
				arguments.add(valueFromRequest);
			} catch (ViolationException e)
			{
				conclusion.put(e.getParamName(), e.getViolation());
			}
		}

		if (conclusion.hasViolation())
			throw new ArgumentValidationException(conclusion);

		return arguments;
	}

}
