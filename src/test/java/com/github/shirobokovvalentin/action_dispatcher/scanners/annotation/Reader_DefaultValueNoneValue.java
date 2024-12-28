package com.github.shirobokovvalentin.action_dispatcher.scanners.annotation;

import com.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamReaderClass;
import com.github.shirobokovvalentin.action_dispatcher.scanners.TestReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamReaderClass(TestReader.class)
public @interface Reader_DefaultValueNoneValue
{
	String defaultValue() default ParamReaderClass.DEFAULT_NONE;
}
