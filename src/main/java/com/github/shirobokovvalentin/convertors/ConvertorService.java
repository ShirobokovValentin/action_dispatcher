package com.github.shirobokovvalentin.convertors;

import com.github.shirobokovvalentin.convertors.exceptions.ConvertorException;

public interface ConvertorService
{
	public String convert(Object fromObject) throws ConvertorException;

	public <T> T convert(String fromString, Class<T> clazz) throws ConvertorException;
	
	public ConvertorService property(Object key, Object value);

}
