package io.github.shirobokovvalentin.action_dispatcher.scanners.annotation;

import io.github.shirobokovvalentin.action_dispatcher.annotation.parameter.ParamConvertorClass;
import io.github.shirobokovvalentin.convertors.HebrewConvertor;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamConvertorClass(HebrewConvertor.class)
public @interface Convertor_FactoryMethodMissing
{

}
