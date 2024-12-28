package com.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import com.github.shirobokovvalentin.convertors.PossiblyHebrewConvertor;
import com.github.shirobokovvalentin.action_dispatcher.readers.ParameterReader;

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
