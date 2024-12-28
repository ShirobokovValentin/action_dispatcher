package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.action_dispatcher.annotation.parameter.Request;
import com.github.shirobokovvalentin.action_dispatcher.annotation.parameter.RequestAttribute;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.IncompatibleAnnotationException;
import com.github.shirobokovvalentin.action_dispatcher.readers.AttrReader;
import com.github.shirobokovvalentin.action_dispatcher.readers.AttributeReader;
import com.github.shirobokovvalentin.action_dispatcher.readers.RawRequest;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AttributeReaderScannerTests extends AbstractParamScannerTests<AttributeReaderScanner>
{
	private static final String PARAM_NAME = "param";

	// ------------------------------------------------------------

	@Before
	public void setUp()
	{
		testSubject = new AttributeReaderScanner();
	}

	// ------------------------------------------------------------

	@Test
	public void norm()
	{
		Annotation[] annotations = { getSkippedAnnotation(), getNormalAnnotation() };
		AttrReader attrReader = testSubject.scan(annotations, null);

		assertEquals(AttributeReader.class, attrReader.getClass());
		assertEquals(PARAM_NAME, attrReader.getParamName());
	}

	@Test
	public void norm2()
	{
		Annotation[] annotations = { getSkippedAnnotation(), getOtherAnnotattion() };
		AttrReader attrReader = testSubject.scan(annotations, null);

		assertEquals(RawRequest.class, attrReader.getClass());
		assertEquals("HttpServletRequest", attrReader.getParamName());
	}

	@Test(expected = IncompatibleAnnotationException.class)
	public void multipleReaderDefined()
	{
		Annotation[] annotations = { getNormalAnnotation(), getOtherAnnotattion() };
		testSubject.scan(annotations, null);
	}

	@Test //(expected = RequredAnnotationMissingException.class)
	public void readerMissing()
	{
		Annotation[] annotations = { getSkippedAnnotation() };
		assertNull(testSubject.scan(annotations, null));
	}

	// ------------------------------------------------------------

	private RequestAttribute getNormalAnnotation()
	{
		return new RequestAttribute()
		{
			public Class<? extends Annotation> annotationType()
			{
				return RequestAttribute.class;
			}

			public String value()
			{
				return PARAM_NAME;
			}

			public String defaultValue()
			{
				return "defValue";
			}

		};
	}

	private Request getOtherAnnotattion()
	{
		return new Request()
		{
			public Class<? extends Annotation> annotationType()
			{
				return Request.class;
			}

		};
	}

}
