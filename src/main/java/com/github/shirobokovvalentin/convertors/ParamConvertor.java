package com.github.shirobokovvalentin.convertors;

import com.github.shirobokovvalentin.convertors.exceptions.TypeConversionException;

public interface ParamConvertor<T>
{
	public T fromString(String paramValue) throws TypeConversionException;
}
