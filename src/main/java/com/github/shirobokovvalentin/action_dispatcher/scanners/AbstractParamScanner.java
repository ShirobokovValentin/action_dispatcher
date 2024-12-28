package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.action_dispatcher.entity.RawArgumentDescriptor;
import com.github.shirobokovvalentin.action_dispatcher.ActionDispatcherReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public abstract class AbstractParamScanner<WorkerType, AdditionalValueType>
{
	protected static final String DEFAULT_VALUE_METHOD_NAME = "defaultValue";

	protected static final String CREATION_PARAMS_METHOD_NAME = "value";

	protected static final String MESSAGE_STRING_METHOD_NAME = "message";

	protected static final int FIRST_ELEMENT = 0;

	// ------------------------------------------------------------

	protected void findCreationParams(Annotation annotation, RawArgumentDescriptor<WorkerType, AdditionalValueType> descriptor)
	{
		Object creationParams = ActionDispatcherReflectionUtils.getValue(annotation, CREATION_PARAMS_METHOD_NAME);
		descriptor.setCreationParams(creationParams);
	}

	protected void findWorkerClass(AnnotatedElement currentInterface, RawArgumentDescriptor<WorkerType, AdditionalValueType> descriptor)
	{
		Annotation annotation = currentInterface.getAnnotation(getWorkerClassAnnotation());
		if (annotation != null)
		{
			Class<? extends WorkerType> value = ActionDispatcherReflectionUtils.getValue(annotation);
			descriptor.setWorkerClass(value);
		}
	}

	protected abstract Class<? extends Annotation> getWorkerClassAnnotation();

	protected void findFactoryMethod(AnnotatedElement currentInterface, RawArgumentDescriptor<WorkerType, AdditionalValueType> descriptor)
	{
		Annotation annotation = currentInterface.getAnnotation(getFactoryAnnotation());
		if (annotation != null)
		{
			String value = ActionDispatcherReflectionUtils.getValue(annotation);
			descriptor.setFactoryMethod(value);
		}
	}

	protected abstract Class<? extends Annotation> getFactoryAnnotation();

}
