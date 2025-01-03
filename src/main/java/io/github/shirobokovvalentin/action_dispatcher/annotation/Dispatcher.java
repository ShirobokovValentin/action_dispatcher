package io.github.shirobokovvalentin.action_dispatcher.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dispatcher
{
	String value();
}
