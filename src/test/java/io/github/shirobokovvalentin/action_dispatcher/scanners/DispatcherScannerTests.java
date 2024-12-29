package io.github.shirobokovvalentin.action_dispatcher.scanners;

import io.github.shirobokovvalentin.action_dispatcher.annotation.ActionInterceptor;
import io.github.shirobokovvalentin.action_dispatcher.annotation.Dispatcher;
import io.github.shirobokovvalentin.action_dispatcher.annotation.DispatcherInterceptor;
import io.github.shirobokovvalentin.action_dispatcher.annotation.method.Action;
import io.github.shirobokovvalentin.action_dispatcher.annotation.method.DefaultAction;
import io.github.shirobokovvalentin.action_dispatcher.annotation.method.ExceptionHandler;
import io.github.shirobokovvalentin.action_dispatcher.entity.DispatcherDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidMappingException;
import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundAction;
import io.github.shirobokovvalentin.action_dispatcher.interceptors.AroundDispatching;
import io.github.shirobokovvalentin.action_dispatcher.scanners.ExceptionHandlerScannerTests.ExHandlerNorm;
import io.github.shirobokovvalentin.AbstractTests;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

public class DispatcherScannerTests extends AbstractTests<DispatcherScanner>
{
	static final String ACTION_PARAM_NAME = "actionParamName";

	static final String ACTION_KEY = "actionValue";

	// ------------------------------------------------------------

	@Before
	public void setUp()
	{
		testSubject = new DispatcherScanner();
	}

	// ------------------------------------------------------------
	
	@Test
	public void defaultAction_missing()
	{
		testException(new DefaultActionMissing());
	}

	@Test
	public void defaultAction_notUnique()
	{
		testException(new DefaultActionNotUnique());
	}

	@Test
	public void defAction()
	{
		DispatcherDescriptor dispatcherDescriptor = testSubject.scan(DefaultActionPresent.class);
		String defaultActionMethodName = dispatcherDescriptor.getDefaultAction().getMethod().getName();

		assertEquals("defaultAction", defaultActionMethodName);
	}

	@Test
	public void exHandler()
	{
		DispatcherDescriptor dispatcherDescriptor = testSubject.scan(ExHandlerNorm.class);
		String defaultActionMethodName = dispatcherDescriptor.getExceptionHandler(Exception.class).getMethod().getName();

		assertEquals("exceptionHandler", defaultActionMethodName);
	}

	@Test
	public void exHandler_notUnique()
	{
		testException(new ExHandlerNotUnique());
	}

	@Test
	public void action_err()
	{
		testException(new WithActionErr());
	}

	@Test
	public void action()
	{
		DispatcherDescriptor dispatcherDescriptor = testSubject.scan(WithAction.class);
		String actionParamName = dispatcherDescriptor.getActionParamName();
		String actionMethodName = dispatcherDescriptor.getAction(ACTION_KEY).getMethod().getName();

		assertEquals(ACTION_PARAM_NAME, actionParamName);
		assertEquals("action", actionMethodName);
	}

	@Test
	public void dispatcherInterceptor()
	{
		DispatcherDescriptor dispatcherDescriptor = testSubject.scan(DispatcherInterceptorPresent.class);
		List<AroundDispatching> interceptors = dispatcherDescriptor.getDispatchingInterceptors();

		assertThat(interceptors, not(empty()));
	}

	@Test
	public void actionInterceptor()
	{
		DispatcherDescriptor dispatcherDescriptor = testSubject.scan(ActionInterceptorPresent.class);
		List<AroundAction> interceptors = dispatcherDescriptor.getActionInterceptors();

		assertThat(interceptors, not(empty()));
	}

	@Test
	public void dispatcherInterceptor_err()
	{
		testException(new DispatcherInterceptorFail());
	}

	@Test
	public void actionInterceptor_err()
	{
		testException(new ActionInterceptorFail());
	}

	private void testException(Object dispatcher)
	{
		try
		{
			testSubject.scan(dispatcher.getClass());
		} catch (Exception e)
		{
			assertEquals(InvalidMappingException.class, e.getClass());
		}

	}

	// ------------------------------------------------------------

	private static class DefaultActionMissing
	{
	}

	public static class DefaultActionPresent extends DefaultActionMissing
	{
		@DefaultAction
		public void defaultAction()
		{
		}
	}

	private static class DefaultActionNotUnique extends DefaultActionPresent
	{
		@DefaultAction
		public void defaultActionOther()
		{
		}
	}

	@Dispatcher(DispatcherScannerTests.ACTION_PARAM_NAME)
	private static class WithAction extends DefaultActionPresent
	{

		@Action({ DispatcherScannerTests.ACTION_KEY })
		public void action()
		{
		}
	}

	@Dispatcher(DispatcherScannerTests.ACTION_PARAM_NAME)
	private static class WithActionErr extends DefaultActionPresent
	{
		@Action("")
		public void action()
		{
		}
	}

	public static class ExHandlerNotUnique extends ExHandlerNorm
	{
		@ExceptionHandler
		@SuppressWarnings("unused")
		public void exceptionHandlerOther(Exception e)
		{
		}
	}

	@DispatcherInterceptor(Interceptor.class)
	private static class DispatcherInterceptorPresent extends DefaultActionPresent
	{
	}

	@DispatcherInterceptor(FailInterceptor.class)
	private static class DispatcherInterceptorFail extends DefaultActionPresent
	{
	}

	@ActionInterceptor(Interceptor.class)
	private static class ActionInterceptorPresent extends DefaultActionPresent
	{
	}

	@ActionInterceptor({ FailInterceptor.class })
	private static class ActionInterceptorFail extends DefaultActionPresent
	{
	}

	static class Interceptor implements AroundDispatching, AroundAction
	{
		public HttpServletRequest beforeExtraction(HttpServletRequest request)
		{
			return null;
		}

		public String afterConverting(String result)
		{
			return null;
		}

		public void beforeMethod(Method method, List<Object> arguments)
		{
		}

		public Object afterMethod(Object result)
		{
			return null;
		}

		public void beforeExceptionHandling(Method method, Throwable e)
		{
		}

		public Object afterExceptionHandling(Object result)
		{
			return null;
		}
	}

	static class FailInterceptor extends Interceptor
	{
		private FailInterceptor()
		{
		}
	}

}
