package com.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import com.github.shirobokovvalentin.action_dispatcher.constraint.validators.NotNullValidator;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

@ParamConstraintClassName(NotNullValidator.class)
public @interface NotNull
{
	String message() default "must be not null";

}
