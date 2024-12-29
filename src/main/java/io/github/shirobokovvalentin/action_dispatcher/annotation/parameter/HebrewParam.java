package io.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import io.github.shirobokovvalentin.convertors.PossiblyHebrewConvertor;
import io.github.shirobokovvalentin.action_dispatcher.readers.ParameterReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamReaderClass(ParameterReader.class)
@ParamConvertorClass(PossiblyHebrewConvertor.class)
public @interface HebrewParam
{
	String value();
}
