package io.github.shirobokovvalentin.action_dispatcher.constraint;

import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamConvertorClass;
import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamConvertorFactoryMethod;
import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamReaderClass;
import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamReaderFactoryMethod;
import io.github.shirobokovvalentin.action_dispatcher.constraint.annotation.ParamConstraintClassName;
import io.github.shirobokovvalentin.action_dispatcher.constraint.annotation.ParamConstraintFactoryMethod;
import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.ComplexValidator;
import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.ParamValidator;
import io.github.shirobokovvalentin.action_dispatcher.entity.RawArgumentDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;
import io.github.shirobokovvalentin.action_dispatcher.scanners.AbstractParamScanner;
import io.github.shirobokovvalentin.action_dispatcher.ActionDispatcherReflectionUtils;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

public class ParamConstraintScanner extends AbstractParamScanner<ParamValidator, String>
{
	public ComplexValidator scan(Annotation[] annotations, Class<?> argumentType) throws InvalidMappingException
	{
		ComplexValidator argumentConstrain = new ComplexValidator();

		for (Annotation annotation : annotations)
		{
			ComplexValidator constrain = resolveUnknownAnnotation(annotation, argumentType);
			if (constrain.isNotEmpty())
				argumentConstrain.add(constrain);
		}

		return argumentConstrain;
	}

	// ------------------------------------------------------------

	@Override
	protected Class<? extends Annotation> getWorkerClassAnnotation()
	{
		return ParamConstraintClassName.class;
	}

	@Override
	protected Class<? extends Annotation> getFactoryAnnotation()
	{
		return ParamConstraintFactoryMethod.class;
	}

	private ComplexValidator resolveUnknownAnnotation(Annotation annotation, Class<?> argumentType)
	{
		ComplexValidator annotationConstrain = new ComplexValidator();

		Object message = ActionDispatcherReflectionUtils.getValue(annotation, MESSAGE_STRING_METHOD_NAME);
		if (message != null && (message instanceof String))
			annotationConstrain.setMessage((String) message);

		Class<?>[] interfaces = annotation.getClass().getInterfaces();

		for (Class<?> currentInterface : interfaces)
		{
			if (currentInterface.getAnnotation(getWorkerClassAnnotation()) != null)
			{
				RawArgumentDescriptor<ParamValidator, String> descriptor = resolveWellKnownAnnotation(annotation, currentInterface);
				ParamValidator constraint = createWorker(descriptor);
				annotationConstrain.add(constraint);
			}

			Annotation[] annotations = currentInterface.getAnnotations();

			for (Annotation currentAnnotation : annotations)
			{
				if (isWellKnownAnnotation(currentAnnotation))
					continue;

				ComplexValidator innerConstrain = resolveUnknownAnnotation(currentAnnotation, argumentType);

				if (innerConstrain.isNotEmpty())
					annotationConstrain.add(innerConstrain);
			}

		}
		return annotationConstrain;
	}

	private RawArgumentDescriptor<ParamValidator, String> resolveWellKnownAnnotation(Annotation annotation, AnnotatedElement currentInterface)
	{
		RawArgumentDescriptor<ParamValidator, String> constraintDescriptor = new RawArgumentDescriptor<ParamValidator, String>();

		findWorkerClass(currentInterface, constraintDescriptor);
		findFactoryMethod(currentInterface, constraintDescriptor);

		findCreationParams(annotation, constraintDescriptor);
		findMessage(annotation, constraintDescriptor);

		return constraintDescriptor;
	}

	private boolean isWellKnownAnnotation(Annotation annotation)
	{
		if (annotation.annotationType() == getWorkerClassAnnotation())
			return true;
		if (annotation.annotationType() == getFactoryAnnotation())
			return true;

		if (annotation.annotationType() == ParamConvertorClass.class)
			return true;
		if (annotation.annotationType() == ParamConvertorFactoryMethod.class)
			return true;
		if (annotation.annotationType() == ParamReaderClass.class)
			return true;
		if (annotation.annotationType() == ParamReaderFactoryMethod.class)
			return true;

		if (annotation.annotationType() == Documented.class)
			return true;
		if (annotation.annotationType() == Retention.class)
			return true;
		if (annotation.annotationType() == Inherited.class)
			return true;
		if (annotation.annotationType() == Target.class)
			return true;

		return false;
	}

	private void findMessage(Annotation annotation, RawArgumentDescriptor<ParamValidator, String> descriptor)
	{
		Object message = ActionDispatcherReflectionUtils.getValue(annotation, MESSAGE_STRING_METHOD_NAME);

		if (message != null && (message instanceof String))
			descriptor.setAdditionalValue((String) message);
	}

	// ------------------------------------------------------------

	protected ParamValidator createWorker(RawArgumentDescriptor<ParamValidator, String> descriptor)
	{
		Class<? extends ParamValidator> clazz = descriptor.getWorkerClass();
		String factoryMethod = descriptor.getFactoryMethod();
		Object params = descriptor.getCreationParams();
		String message = descriptor.getAdditionalValue();

		ParamValidator constraint = ActionDispatcherReflectionUtils.getNewInstance(clazz, factoryMethod, params);

		if (constraint != null)
			constraint.setMessage(message);

		return constraint;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	private ParamValidator recursiveCreateWorker(List<RecursiveRawArgumentDescriptor<ParamValidator, String>> descriptors)
//	{
//		ComplexValidator argumentConstrain = new ComplexValidator();
//		if (descriptors == null || descriptors.isEmpty())
//			return argumentConstrain;
//
//		for (RawArgumentDescriptor<ParamValidator, String> descriptor : descriptors)
//		{
//			ParamValidator constrain = createWorker(descriptor);
//			argumentConstrain.add(constrain);
//		}
//
//		return argumentConstrain;
//	}

	abstract static class CreatableArgumentDescriptor<T, E> extends RawArgumentDescriptor<T, E>
	{
		public T createWorker()
		{
			Class<? extends T> clazz = getWorkerClass();
			String factoryMethod = getFactoryMethod();
			Object params = getCreationParams();
			E additionalValue = getAdditionalValue();

			T worker = ActionDispatcherReflectionUtils.getNewInstance(clazz, factoryMethod, params);

			setAdditionalValue(worker, additionalValue);

			return worker;
		}

		abstract void setAdditionalValue(T worker, E additionalValue);
	}

	abstract static class RecursiveRawArgumentDescriptor<T, E> extends CreatableArgumentDescriptor<T, E>
	{
		private List<RecursiveRawArgumentDescriptor<T, E>> descriptors = new ArrayList<RecursiveRawArgumentDescriptor<T, E>>();

		// ------------------------------------------------------------

		public List<RecursiveRawArgumentDescriptor<T, E>> getDescriptors()
		{
			return this.descriptors;
		}

		public void setDescriptors(List<RecursiveRawArgumentDescriptor<T, E>> descriptors)
		{
			this.descriptors = descriptors;
		}
	}

	static class ParamValidatorDescriptor extends RecursiveRawArgumentDescriptor<ParamValidator, String>
	{
		@Override
		void setAdditionalValue(ParamValidator worker, String additionalValue)
		{
			if (worker != null)
				worker.setMessage(additionalValue);
		}

	}

}
