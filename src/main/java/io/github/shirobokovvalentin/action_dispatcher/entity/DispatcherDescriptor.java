package io.github.shirobokovvalentin.action_dispatcher.entity;

import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundAction;
import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundDispatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatcherDescriptor
{
	private String actionParamName;

	private Class<?> dispatcherClass;

	private final List<AroundDispatching> dispatchingInterceptors = new ArrayList<AroundDispatching>();

	private final List<AroundAction> actionInterceptors = new ArrayList<AroundAction>();

	private ActionDescriptor defaultAction;

	private final Map<String, ActionDescriptor> actions = new HashMap<String, ActionDescriptor>();

	private final Map<Class<? extends Exception>, ExceptionHandlerDescriptor> exceptionHandlers = new HashMap<Class<? extends Exception>, ExceptionHandlerDescriptor>();

	// ------------------------------------------------------------

	public DispatcherDescriptor(Class<?> dispatcherClass)
	{
		setDispatcherClass(dispatcherClass);
	}

	public ActionDescriptor getAction(String key)
	{
		if (this.actions.containsKey(key))
			return this.actions.get(key);

		if (getDefaultAction() != null)
			return getDefaultAction();

		return getDefaultAction();
	}

	public ExceptionHandlerDescriptor getSuitableExceptionHandler(Throwable exception)
	{
		if (exception == null)
			return null;

		Class<?> wantedClass = exception.getClass();
		while (wantedClass != Object.class)
		{
			ExceptionHandlerDescriptor handler = exceptionHandlers.get(wantedClass);

			if (handler != null)
				return handler;

			wantedClass = wantedClass.getSuperclass();
		}

		return null;
	}

	public ExceptionHandlerDescriptor getExceptionHandler(Class<? extends Exception> key)
	{
		return this.exceptionHandlers.get(key);
	}

	public void putExceptionHandler(Class<? extends Exception> key, ExceptionHandlerDescriptor m)
	{
		this.exceptionHandlers.put(key, m);
	}

	public void putAction(String key, ActionDescriptor m)
	{
		this.actions.put(key, m);
	}

	// ------------------------------------------------------------

	public String getActionParamName()
	{
		return this.actionParamName;
	}

	public void setActionParamName(String actionName)
	{
		this.actionParamName = actionName;
	}

	public ActionDescriptor getDefaultAction()
	{
		return this.defaultAction;
	}

	public void setDefaultAction(ActionDescriptor defaultAction)
	{
		this.defaultAction = defaultAction;
	}

	public Class<?> getDispatcherClass()
	{
		return this.dispatcherClass;
	}

	public void setDispatcherClass(Class<?> dispatcherClass)
	{
		this.dispatcherClass = dispatcherClass;
	}

	public List<AroundDispatching> getDispatchingInterceptors()
	{
		return this.dispatchingInterceptors;
	}

	public void addDispachingInterceptors(AroundDispatching inspector)
	{
		this.dispatchingInterceptors.add(inspector);
	}

	public List<AroundAction> getActionInterceptors()
	{
		return this.actionInterceptors;
	}

	public void addActionInterceptors(AroundAction aroundAction)
	{
		this.actionInterceptors.add(aroundAction);
	}

}
