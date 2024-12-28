package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.AbstractTests;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;

public class AbstractParamScannerTests<T extends AbstractParamScanner<?,?>> extends AbstractTests<T>
{
	protected Inherited getSkippedAnnotation()
	{
		return new Inherited()
		{
			public Class<? extends Annotation> annotationType()
			{
				return Inherited.class;
			}
		};
	}

}
