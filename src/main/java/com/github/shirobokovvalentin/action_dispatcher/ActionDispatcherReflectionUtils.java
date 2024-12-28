package com.github.shirobokovvalentin.action_dispatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

public abstract class ActionDispatcherReflectionUtils extends ReflectionUtils
{
	private static final String VALUE = "value";

	// ------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static <T> T getNewInstance(Class<T> typeClass, String factoryMethodName, Object... args) throws IllegalStateException
	{
		if (args != null && args.length == 1 && args[0] == null)
			args = null;

		if (StringUtils.isNotBlank(factoryMethodName))
		{
			Class<?>[] ct = getParamsType(args);
			Method factoryMethod = ReflectionUtils.findMethod(typeClass, factoryMethodName, ct);

			if (factoryMethod == null)
			{
				throw new IllegalStateException("factoryMethod :" + factoryMethodName + "(?) not found.");
			}

			return (T) invokeMethod(factoryMethod, null, args);
		}
		return getNewInstance(typeClass, args);

	}

	private static Class<?>[] getParamsType(Object... args)
	{
		if (args != null)
		{
			Class<?>[] paramTypes = new Class<?>[args.length];

			for (int i = 0; i < paramTypes.length; i++)
			{
				paramTypes[i] = args[i].getClass();
			}
			return paramTypes;
		}

		return new Class<?>[0];
	}

	public static Object invokeMethod(Method method, Object tagret, Object... args) throws IllegalStateException
	{
		if (method == null)
			throw new IllegalArgumentException("Method can't be null");
		try
		{
			return method.invoke(tagret, args);
		} catch (IllegalArgumentException e)
		{
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e)
		{
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e)
		{
			throw new IllegalStateException(e);
		}
	}

	public static <T> T getNewInstance(Class<T> typeClass, Object[] initargs) throws IllegalStateException
	{
		Class<?>[] parameterTypes = getParamsType(initargs);
		Constructor<T> constructor;
		try
		{
			constructor = typeClass.getConstructor(parameterTypes);
			return constructor.newInstance(initargs);
		} catch (Exception e)
		{
			throw new IllegalStateException(e);
		}

	}

	public static <T> T getNewInstance(Class<T> typeClass) throws IllegalStateException
	{
		if (typeClass == null)
			throw new IllegalArgumentException("Class can't be null");

		try
		{
			return typeClass.newInstance();

		} catch (InstantiationException e)
		{
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e)
		{
			throw new IllegalStateException(e);
		}

	}

	public static List<Class<?>> searchParameterizedTypes(Class<?> parametrizedClass, Class<?> soughtforInterface)
	{
		Type[] interfaces = parametrizedClass.getGenericInterfaces();

		List<Class<?>> result = new ArrayList<Class<?>>();

		for (Type currentInterface : interfaces)
		{
			if (!(currentInterface instanceof ParameterizedType))
				continue;

			ParameterizedType pt = (ParameterizedType) currentInterface;
			Type rawType = pt.getRawType();

			if (!rawType.equals(soughtforInterface))
				continue;

			Type[] types = pt.getActualTypeArguments();
			for (Type type : types)
			{
				if (type instanceof Class<?>)
				{
					result.add((Class<?>) type);
				}
			}
		}
		return result;
	}

	public static List<Field> findAllFields(Class<?> clazz)
	{
		Assert.notNull(clazz, "Class must not be null");
		List<Field> allFields = new ArrayList<Field>();
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null)
		{
			Field[] fields = searchType.getDeclaredFields();

			for (Field field : fields)
			{
				allFields.add(field);
			}
			searchType = searchType.getSuperclass();
		}
		return allFields;
	}

	public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes)
	{
		Class<?> searchType = clazz;
		while (searchType != null)
		{
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (Method method : methods)
			{
				if (name.equals(method.getName()))
					if ((paramTypes == null ||
							Arrays.equals(paramTypes, method.getParameterTypes())))
					{
						return method;
					}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	public static Method findPublicStaticMethod(Class<?> clazz, String name, Class<?>... paramTypes)
	{
		if (clazz == null)
			return null;

		Method[] methods = clazz.getMethods();
		for (Method method : methods)
		{
			if (Modifier.isStatic(method.getModifiers()))
			{
				if (name.equals(method.getName()))
					if ((paramTypes == null ||
							Arrays.equals(paramTypes, method.getParameterTypes())))
					{
						return method;
					}
			}
		}
		return null;
	}

	// ------------------------------------------------------------

	public static String likeParams(Class<?>[] array)
	{
		String[] simplNames = new String[array.length];

		for (int i = 0; i < array.length; i++)
		{
			simplNames[i] = array[i].getSimpleName();
		}

		String result = StringUtils.join(simplNames, ", ");
		if (result == null)
			return "";

		return result;

	}

	public static String prettyMethod(Method method)
	{
		if (method == null)
			return null;
		String likeParams = ActionDispatcherReflectionUtils.likeParams(method.getParameterTypes());
		return method.getName() + "(" + likeParams + ")";
	}

	public static String fullMethod(Method method)
	{
		String fullMethod = method.getDeclaringClass().getSimpleName() + "." + prettyMethod(method);
		return fullMethod;
	}

	// ------------------------------------------------------------

	public static Object getValue(Annotation annotation, String attributeName)
	{
		try
		{
			Method method = annotation.annotationType().getDeclaredMethod(attributeName);
			ReflectionUtils.makeAccessible(method);
			return method.invoke(annotation);
		} catch (Exception ex)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(Annotation annotation)
	{
		return (T) getValue(annotation, VALUE);
	}

	public static List<Type> getGeneric(Type type)
	{
		if (!(type instanceof ParameterizedType))
			return Collections.emptyList();

		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] args = parameterizedType.getActualTypeArguments();

		return Arrays.asList(args);

	}
}
