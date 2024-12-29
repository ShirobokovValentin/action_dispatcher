package io.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.ParamValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamConstraintClassName
{
	Class<? extends ParamValidator> value();
}
