package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;
import com.github.shirobokovvalentin.AbstractTests;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public abstract class AbstractMethodScannerTests<T extends AbstractMethodScanner> extends AbstractTests<T>
{
	public static Method getFirstAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotationClass)
	{
		Method[] methods = clazz.getMethods();

		for (Method method : methods)
		{
			if (method.isAnnotationPresent(annotationClass))
				return method;
		}

		return null;
	}

	protected void testException(Class<?> clazz, String message)
	{
		try
		{
			Method method = getNecessaryMethod(clazz);
			doTest(method);
		} catch (Exception e)
		{
			assertEquals(InvalidMappingException.class, e.getClass());

			if (message != null)
				assertEquals(message, e.getMessage());
		}

	}
	
	protected abstract Method getNecessaryMethod(Class<?> clazz);
	protected abstract void doTest(Method method);

}
