package io.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

@Pattern("^[0-9A-F]{8}(-[0-9A-F]{4}){3}-[0-9A-F]{12}$")
public @interface Uniqueidentifier
{
	String message() default "must be like Unique identifier";
}
