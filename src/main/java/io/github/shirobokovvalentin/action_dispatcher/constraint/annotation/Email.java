package io.github.shirobokovvalentin.action_dispatcher.constraint.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

@Pattern("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
public @interface Email
{
	String message() default "must look like email";
}
