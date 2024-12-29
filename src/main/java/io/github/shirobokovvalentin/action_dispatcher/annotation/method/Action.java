package io.github.shirobokovvalentin.action_dispatcher.annotation.method;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action
{
	String[] value();
}
