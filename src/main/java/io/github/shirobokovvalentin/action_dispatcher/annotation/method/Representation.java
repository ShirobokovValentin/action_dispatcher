package io.github.shirobokovvalentin.action_dispatcher.annotation.method;

import io.github.shirobokovvalentin.convertors.MediaType;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Representation
{
	MediaType value() default MediaType.APPLICATION_JSON;
}
