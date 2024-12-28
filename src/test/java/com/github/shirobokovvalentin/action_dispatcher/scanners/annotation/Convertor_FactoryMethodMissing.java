package com.github.shirobokovvalentin.action_dispatcher.scanners.annotation;

import com.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamConvertorClass;
import com.github.shirobokovvalentin.convertors.HebrewConvertor;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamConvertorClass(HebrewConvertor.class)
public @interface Convertor_FactoryMethodMissing
{

}
