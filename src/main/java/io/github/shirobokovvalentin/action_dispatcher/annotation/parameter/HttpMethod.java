package io.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import io.github.shirobokovvalentin.action_dispatcher.readers.HttpMethodReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamReaderClass(HttpMethodReader.class)
public @interface HttpMethod
{
}
