package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.action_dispatcher.entity.RawArgumentDescriptor;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidDefinitionException;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

public abstract class LinearAnnotationParamScanner<WorkerType, AdditionalValueType> extends AbstractParamScanner<WorkerType, AdditionalValueType>
{
	public WorkerType scan(Annotation[] annotations, Class<?> argumentType) throws InvalidMappingException
	{
		List<RawArgumentDescriptor<WorkerType, AdditionalValueType>> descriptors = new ArrayList<RawArgumentDescriptor<WorkerType, AdditionalValueType>>();

		for (Annotation annotation : annotations)
		{
			RawArgumentDescriptor<WorkerType, AdditionalValueType> descriptor = resolveUnknownAnnotaion(annotation);
			if (descriptor != null)
				descriptors.add(descriptor);
		}

		return createWorker(descriptors, argumentType);
	}

	protected RawArgumentDescriptor<WorkerType, AdditionalValueType> resolveUnknownAnnotaion(Annotation annotation)
	{
		Class<?>[] interfaces = annotation.getClass().getInterfaces();

		for (Class<?> currentInterface : interfaces)
		{
			if (currentInterface.getAnnotation(getWorkerClassAnnotation()) != null)
				return resolveWellknownAnnotaion(annotation, currentInterface);
		}
		
		return null;
	}

	protected RawArgumentDescriptor<WorkerType, AdditionalValueType> resolveWellknownAnnotaion(Annotation annotation, AnnotatedElement currentInterface)
			throws InvalidDefinitionException
	{
		RawArgumentDescriptor<WorkerType, AdditionalValueType> descriptor = new RawArgumentDescriptor<WorkerType, AdditionalValueType>();
		
		findWorkerClass(currentInterface, descriptor);
		findFactoryMethod(currentInterface, descriptor);
		findCreationParams(annotation, descriptor);
		findAdditionalValue(annotation, descriptor);

		return descriptor;
	}

	protected abstract void findAdditionalValue(Annotation annotation, RawArgumentDescriptor<WorkerType, AdditionalValueType> descriptor);

	protected abstract WorkerType createWorker(List<RawArgumentDescriptor<WorkerType, AdditionalValueType>> descriptors, Class<?> argumentType);

}
