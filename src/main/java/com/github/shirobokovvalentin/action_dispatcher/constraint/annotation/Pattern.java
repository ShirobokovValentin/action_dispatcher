package com.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import com.github.shirobokovvalentin.action_dispatcher.constraint.validators.PatternValidator;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

@ParamConstraintClassName(PatternValidator.class)
@ParamConstraintFactoryMethod("getInstance")
public @interface Pattern
{
	String value();
}
