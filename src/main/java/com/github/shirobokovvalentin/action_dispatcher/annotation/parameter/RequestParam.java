package com.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import com.github.shirobokovvalentin.action_dispatcher.readers.ParameterReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamReaderClass(ParameterReader.class)
public @interface RequestParam
{
	String value();
	
	String defaultValue() default ParamReaderClass.DEFAULT_NONE;
}
