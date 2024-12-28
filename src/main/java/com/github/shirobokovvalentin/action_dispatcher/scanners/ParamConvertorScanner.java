package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.convertors.ParamConvertor;
import com.github.shirobokovvalentin.convertors.ConvertorsStoke;
import com.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamConvertorClass;
import com.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamConvertorFactoryMethod;
import com.github.shirobokovvalentin.action_dispatcher.entity.RawArgumentDescriptor;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.IncompatibleAnnotationException;
import com.github.shirobokovvalentin.action_dispatcher.ActionDispatcherReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.List;

public class ParamConvertorScanner extends LinearAnnotationParamScanner<ParamConvertor<?>, String>
{
	@Override
	protected Class<? extends Annotation> getWorkerClassAnnotation()
	{
		return ParamConvertorClass.class;
	}

	@Override
	protected Class<? extends Annotation> getFactoryAnnotation()
	{
		return ParamConvertorFactoryMethod.class;
	}

	@Override
	protected void findAdditionalValue(Annotation annotation, RawArgumentDescriptor<ParamConvertor<?>, String> descriptor)
	{
		//NOP
	}

	@Override
	protected ParamConvertor<?> createWorker(List<RawArgumentDescriptor<ParamConvertor<?>, String>> descriptors, Class<?> argumentType)
	{
		if (descriptors == null || descriptors.isEmpty())
			return ConvertorsStoke.getConvertors(argumentType);

		if (descriptors.size() != 1)
			throw new IncompatibleAnnotationException();

		RawArgumentDescriptor<ParamConvertor<?>, String> descriptor = descriptors.get(FIRST_ELEMENT);

		Class<?> convertorClass = descriptor.getWorkerClass();

		if (convertorClass == null)
			return ConvertorsStoke.getConvertors(argumentType);

		String factoryMethod = descriptor.getFactoryMethod();
		Object params = null;

		ParamConvertor<?> convertor = (ParamConvertor<?>) ActionDispatcherReflectionUtils.getNewInstance(convertorClass, factoryMethod, params);

		return convertor;
	}

}
