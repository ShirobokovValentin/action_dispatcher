package io.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.NotEmptyStringValidator;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

@ParamConstraintClassName(NotEmptyStringValidator.class)
public @interface NotEmpty
{
	String message() default "must be non-empty string";

}
