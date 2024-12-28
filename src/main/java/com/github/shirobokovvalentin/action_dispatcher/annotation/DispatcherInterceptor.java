package com.github.shirobokovvalentin.action_dispatcher.annotation;

import com.github.shirobokovvalentin.action_dispatcher.interceptors.AroundDispatching;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DispatcherInterceptor
{
	Class<? extends AroundDispatching>[] value();
}
