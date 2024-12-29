package io.github.shirobokovvalentin.action_dispatcher.annotation.parameter;

import io.github.shirobokovvalentin.action_dispatcher.readers.IpReader;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ParamReaderClass(IpReader.class)
@ParamReaderFactoryMethod("getInstance")
public @interface IpParam
{

}
