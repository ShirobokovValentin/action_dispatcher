package io.github.shirobokovvalentin.action_dispatcher.annotation;

import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundAction;

import java.lang.annotation.*;

@Inherited
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionInterceptor
{
	Class<? extends AroundAction>[] value();
}
