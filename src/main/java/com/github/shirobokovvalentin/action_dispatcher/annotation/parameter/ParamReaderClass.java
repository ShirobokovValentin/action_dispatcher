package com.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import com.github.shirobokovvalentin.action_dispatcher.readers.ParamReader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamReaderClass
{
	Class<? extends ParamReader> value();

	String DEFAULT_NONE = "\u0000";
}
