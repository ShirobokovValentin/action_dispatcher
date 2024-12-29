package io.github.shirobokovvalentin.action_dispatcher.scanners;

import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamReaderClass;
import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamReaderFactoryMethod;
import io.github.shirobokovvalentin.action_dispatcher.entity.RawArgumentDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.IncompatibleAnnotationException;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidDefinitionException;
import io.github.shirobokovvalentin.action_dispatcher.readers.ParamReader;
import io.github.shirobokovvalentin.action_dispatcher.ActionDispatcherReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.List;

public class ParamReaderScanner extends LinearAnnotationParamScanner<ParamReader, String>
{
	@Override
	protected Class<? extends Annotation> getWorkerClassAnnotation()
	{
		return ParamReaderClass.class;
	}

	@Override
	protected Class<? extends Annotation> getFactoryAnnotation()
	{
		return ParamReaderFactoryMethod.class;
	}

	@Override
	protected void findAdditionalValue(Annotation annotation, RawArgumentDescriptor<ParamReader, String> descriptor) throws InvalidDefinitionException
	{
		Object defaultValue = ActionDispatcherReflectionUtils.getValue(annotation, DEFAULT_VALUE_METHOD_NAME);

		if (defaultValue == null)
			return;

		if (ParamReaderClass.DEFAULT_NONE.equals(defaultValue))
			return;

		if (!(defaultValue instanceof String))
			throw new InvalidDefinitionException("defaultValue for paramReader must be Strign.");

		String readerDefaultValue = (String) defaultValue;
		descriptor.setAdditionalValue(readerDefaultValue);
	}

	@Override
	protected ParamReader createWorker(List<RawArgumentDescriptor<ParamReader, String>> descriptors, Class<?> argumentType)
	{
		if (descriptors == null || descriptors.isEmpty())
			return null;

		if (descriptors.size() != 1)
			throw new IncompatibleAnnotationException();

		RawArgumentDescriptor<ParamReader, String> descriptor = descriptors.get(FIRST_ELEMENT);

		Class<?> readerClass = descriptor.getWorkerClass();
		if (readerClass == null)
			return null;

		String factoryMethod = descriptor.getFactoryMethod();
		Object params = descriptor.getCreationParams();
		String defaultValue = descriptor.getAdditionalValue();

		ParamReader reader = (ParamReader) ActionDispatcherReflectionUtils.getNewInstance(readerClass, factoryMethod, params);
		reader.setDefaultValue(defaultValue);

		return reader;
	}

}
