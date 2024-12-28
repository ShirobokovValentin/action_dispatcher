package com.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import com.github.shirobokovvalentin.action_dispatcher.constraint.validators.MinValueValidator;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

@ParamConstraintClassName(MinValueValidator.class)
public @interface MinValue
{
	int value(); 

}
