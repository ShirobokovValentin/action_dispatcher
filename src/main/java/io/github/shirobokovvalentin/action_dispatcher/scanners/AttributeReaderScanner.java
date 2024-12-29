package io.github.shirobokovvalentin.action_dispatcher.scanners;

import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.AttributeReaderClass;
import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.AttributeReaderFactoryMethod;
import io.github.shirobokovvalentin.action_dispatcher.entity.RawArgumentDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.IncompatibleAnnotationException;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidDefinitionException;
import io.github.shirobokovvalentin.action_dispatcher.readers.AttrReader;
import io.github.shirobokovvalentin.action_dispatcher.ActionDispatcherReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.List;

public class AttributeReaderScanner extends LinearAnnotationParamScanner<AttrReader, String>
{
	@Override
	protected Class<? extends Annotation> getWorkerClassAnnotation()
	{
		return AttributeReaderClass.class;
	}

	@Override
	protected Class<? extends Annotation> getFactoryAnnotation()
	{
		return AttributeReaderFactoryMethod.class;
	}

	@Override
	protected void findAdditionalValue(Annotation annotation, RawArgumentDescriptor<AttrReader, String> descriptor) throws InvalidDefinitionException
	{
	}

	@Override
	protected AttrReader createWorker(List<RawArgumentDescriptor<AttrReader, String>> descriptors, Class<?> argumentType)
	{
		if (descriptors == null || descriptors.isEmpty())
			return null;

		if (descriptors.size() != 1)
			throw new IncompatibleAnnotationException();

		RawArgumentDescriptor<AttrReader, String> descriptor = descriptors.get(FIRST_ELEMENT);

		Class<?> readerClass = descriptor.getWorkerClass();

		String factoryMethod = descriptor.getFactoryMethod();
		Object params = descriptor.getCreationParams();

		AttrReader reader = (AttrReader) ActionDispatcherReflectionUtils.getNewInstance(readerClass, factoryMethod, params);

		return reader;
	}

}
