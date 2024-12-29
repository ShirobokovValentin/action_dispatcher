package io.github.shirobokovvalentin.convertors;

import io.github.shirobokovvalentin.convertors.exceptions.TypeConversionException;

public interface ParamConvertor<T>
{
	public T fromString(String paramValue) throws TypeConversionException;
}
