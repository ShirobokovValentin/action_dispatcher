package io.github.shirobokovvalentin.action_dispatcher.scanners;

import io.github.shirobokovvalentin.action_dispatcher.constraint.ParamConstraintScanner;
import io.github.shirobokovvalentin.action_dispatcher.constraint.annotation.Email;
import io.github.shirobokovvalentin.action_dispatcher.constraint.annotation.MinValue;
import io.github.shirobokovvalentin.action_dispatcher.constraint.annotation.NotNull;
import io.github.shirobokovvalentin.action_dispatcher.constraint.entity.Violation;
import io.github.shirobokovvalentin.action_dispatcher.constraint.validators.ParamValidator;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParamConstraintScannerTests extends AbstractParamScannerTests<ParamConstraintScanner>
{
	private static final String SIMPLE_MESSAGE = "must be not null";
	private static final String PARAMETERIZED_MESSAGE = "must be not less then 0 but was -1";
	private static final String PARAMETERIZED_MESSAGE_ANOTHER = "must be not less then 50 but was -1";
	
	private static final String COMPLEX_MESSAGE = "complex message";
	private static final String MULTIPLE_MESSAGE = PARAMETERIZED_MESSAGE + ", " + PARAMETERIZED_MESSAGE_ANOTHER;
	
	// ------------------------------------------------------------

	@Before
	public void setUp()
	{
		testSubject = new ParamConstraintScanner();
	}

	// ------------------------------------------------------------

	@Test
	public void missing()
	{
		Annotation[] annotations = { getSkippedAnnotation() };
		ParamValidator validator = testSubject.scan(annotations, Integer.class);
		Violation res = validator.check(0);
		assertNull(res);
	}

	@Test
	public void simple()
	{
		Annotation[] annotations = { getSimpleConstraint() };
		ParamValidator validator = testSubject.scan(annotations, Integer.class);

		String actual =  validator.check(null).getMessage();
		assertEquals(SIMPLE_MESSAGE, actual);
	}
	
	@Test
	public void parameterized()
	{
		Annotation[] annotations = { getConstraintConstructParam() };
		ParamValidator validator = testSubject.scan(annotations, Integer.class);

		String actual =  validator.check(-1).getMessage();
		assertEquals(PARAMETERIZED_MESSAGE, actual);
	}
	
	@Test
	public void multiple()
	{
		Annotation[] annotations = { getConstraintConstructParam(), getConstraintConstructParamAnother() };
		ParamValidator validator = testSubject.scan(annotations, Integer.class);

		String actual =  validator.check(-1).getMessage();
		assertEquals(MULTIPLE_MESSAGE, actual);
	}
	
	@Test
	public void complex()
	{
		Annotation[] annotations = { getComplexConstraint()};
		ParamValidator validator = testSubject.scan(annotations, String.class);

		String actual =  validator.check("NOT_EMAIL").getMessage();
		assertEquals(COMPLEX_MESSAGE, actual);
	}
	
	// ------------------------------------------------------------

	private NotNull getSimpleConstraint()
	{
		return new NotNull()
		{
			public Class<? extends Annotation> annotationType()
			{
				return NotNull.class;
			}

			public String message()
			{
				return SIMPLE_MESSAGE;
			}
		};
	}
	
	private MinValue getConstraintConstructParam()
	{
		return new MinValue()
		{

			public Class<? extends Annotation> annotationType()
			{
				return MinValue.class;
			}

			public int value()
			{
				return 0;
			}

		};
	}
	
	private MinValue getConstraintConstructParamAnother()
	{
		return new MinValue()
		{

			public Class<? extends Annotation> annotationType()
			{
				return MinValue.class;
			}

			public int value()
			{
				return 50;
			}

		};
	}
	
	private Email getComplexConstraint()
	{
		return new Email()
		{
			public Class<? extends Annotation> annotationType()
			{
				return Email.class;
			}

			public String message()
			{
				return COMPLEX_MESSAGE;
			}
		};
	}

}
