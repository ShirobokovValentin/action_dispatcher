package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.convertors.ParamConvertor;
import com.github.shirobokovvalentin.convertors.HebrewConvertor;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.IncompatibleAnnotationException;
import com.github.shirobokovvalentin.action_dispatcher.scanners.annotation.Convertor_FactoryMethodMissing;
import com.github.shirobokovvalentin.action_dispatcher.scanners.annotation.Convertor_FactoryMethodPresent;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParamConvertorScannerTests extends AbstractParamScannerTests<ParamConvertorScanner>
{
	@Before
	public void setUp()
	{
		testSubject = new ParamConvertorScanner();
	}

	// ------------------------------------------------------------

	@Test
	public void convertorNotDefined()
	{
		Annotation[] annotations = { getSkippedAnnotation() };
		ParamConvertor<?> paramConvertor = testSubject.scan(annotations, Integer.class);

		assertNotNull(paramConvertor.getClass());
	}

	@Test
	public void factoryMethodMissing()
	{
		Annotation[] annotations = { getSkippedAnnotation(), getFactoryMethodMissing() };
		ParamConvertor<?> paramConvertor = testSubject.scan(annotations, Integer.class);

		assertEquals(HebrewConvertor.class, paramConvertor.getClass());
	}

	@Test
	public void factoryMethodPresent()
	{
		Annotation[] annotations = { getSkippedAnnotation(), getFactoryMethodPresent() };
		ParamConvertor<?> paramConvertor = testSubject.scan(annotations, Integer.class);

		assertEquals(HebrewConvertor.class, paramConvertor.getClass());
	}

	@Test(expected = IncompatibleAnnotationException.class)
	public void multipleConvertorDefined()
	{
		Annotation[] annotations = { getFactoryMethodMissing(), getFactoryMethodPresent() };
		testSubject.scan(annotations, Integer.class);
	}

	// ------------------------------------------------------------

	private Convertor_FactoryMethodMissing getFactoryMethodMissing()
	{
		return new Convertor_FactoryMethodMissing()
		{
			public Class<? extends Annotation> annotationType()
			{
				return Convertor_FactoryMethodMissing.class;
			}
		};
	}

	private Convertor_FactoryMethodPresent getFactoryMethodPresent()
	{
		return new Convertor_FactoryMethodPresent()
		{
			public Class<? extends Annotation> annotationType()
			{
				return Convertor_FactoryMethodPresent.class;
			}
		};
	}

}
