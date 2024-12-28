package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.convertors.MediaType;
import com.github.shirobokovvalentin.action_dispatcher.annotation.ActionInterceptor;
import com.github.shirobokovvalentin.action_dispatcher.annotation.method.Representation;
import com.github.shirobokovvalentin.action_dispatcher.entity.ActionDescriptor;
import com.github.shirobokovvalentin.action_dispatcher.entity.ArgumentDescriptor;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.RequredAnnotationMissingException;
import com.github.shirobokovvalentin.action_dispatcher.interceptors.AroundAction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ActionScanner extends AbstractMethodScanner
{
	private final ArgumentScanner argumentScanner = new ArgumentScanner();

	// ------------------------------------------------------------

	public ActionDescriptor scan(Method method)
	{
		ActionDescriptor actionDescriptor = new ActionDescriptor(method);

		processReturnType(actionDescriptor);
		processMediaType(actionDescriptor);
		processInterceptor(actionDescriptor);
		processArguments(actionDescriptor);

		return actionDescriptor;
	}

	// ------------------------------------------------------------

	private void processMediaType(ActionDescriptor actionDescriptor)
	{
		Method method = actionDescriptor.getMethod();
		if (method.isAnnotationPresent(Representation.class))
		{
			MediaType mediaType = method.getAnnotation(Representation.class).value();
			actionDescriptor.setMediaType(mediaType);
		}
	}

	private void processInterceptor(ActionDescriptor actionDescriptor)
	{
		Method method = actionDescriptor.getMethod();
		if (method.isAnnotationPresent(ActionInterceptor.class))
		{
			Class<? extends AroundAction>[] interceptorsClasses = method.getAnnotation(ActionInterceptor.class).value();
			addInterceptors(actionDescriptor, interceptorsClasses);
		}
	}

	private void addInterceptors(ActionDescriptor actionDescriptor, Class<? extends AroundAction>[] interceptorsClasses)
	{
		for (Class<? extends AroundAction> interceptorsClass : interceptorsClasses)
		{
			try
			{
				actionDescriptor.addInterceptor(interceptorsClass.newInstance());
			} catch (Exception e)
			{
				throw new InvalidMappingException("Can't create interceptor " + interceptorsClass, e);
			}
		}
	}

	private void processArguments(ActionDescriptor actionDescriptor) throws InvalidMappingException
	{
		Method method = actionDescriptor.getMethod();
		Class<?>[] argumentTypes = method.getParameterTypes();
		Annotation[][] annotations = method.getParameterAnnotations();

		List<ArgumentDescriptor> argDescriptors = new ArrayList<ArgumentDescriptor>();
		for (int i = 0; i < argumentTypes.length; i++)
		{
			try
			{
				ArgumentDescriptor argDescriptor = argumentScanner.scan(annotations[i], argumentTypes[i]);
				argDescriptors.add(argDescriptor);
			} catch (RequredAnnotationMissingException e)
			{
				throw new InvalidMappingException("In method " + method.getDeclaringClass().getName() + "." + method.getName() +
						"(...) annotation " + e.getAnnotations() + " for argument " + (i + 1) + " not found");
			}
		}

		actionDescriptor.setArgumentDescriptors(argDescriptors);
	}

}
