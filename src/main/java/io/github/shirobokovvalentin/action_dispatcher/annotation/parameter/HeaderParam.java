package io.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import io.github.shirobokovvalentin.action_dispatcher.readers.HeaderReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamReaderClass(HeaderReader.class)
public @interface HeaderParam
{
	String value();

	String defaultValue() default ParamReaderClass.DEFAULT_NONE;
}
