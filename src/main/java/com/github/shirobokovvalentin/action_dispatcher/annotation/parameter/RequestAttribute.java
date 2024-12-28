package com.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import com.github.shirobokovvalentin.action_dispatcher.readers.AttributeReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@AttributeReaderClass(AttributeReader.class)
public @interface RequestAttribute
{
	String value();

	String defaultValue() default ParamReaderClass.DEFAULT_NONE;
}
