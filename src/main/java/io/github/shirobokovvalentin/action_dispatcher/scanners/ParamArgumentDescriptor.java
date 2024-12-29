package io.github.shirobokovvalentin.action_dispatcher.scanners;

import io.github.shirobokovvalentin.convertors.ParamConvertor;
import io.github.shirobokovvalentin.convertors.exceptions.TypeConversionException;
import io.github.shirobokovvalentin.action_dispatcher.constraint.entity.SimpleViolation;
import io.github.shirobokovvalentin.action_dispatcher.constraint.entity.Violation;
import io.github.shirobokovvalentin.action_dispatcher.constraint.exceptions.ViolationException;
import io.github.shirobokovvalentin.action_dispatcher.entity.ArgumentDescriptor;
import io.github.shirobokovvalentin.action_dispatcher.readers.ParamReader;

import javax.servlet.http.HttpServletRequest;

public class ParamArgumentDescriptor extends ArgumentDescriptor
{
	private ParamReader paramReader;

	private ParamConvertor<?> paramConvertor;

	// ------------------------------------------------------------

	public Object getArgument(HttpServletRequest request) throws ViolationException
	{
		try
		{
			String rawParamValue = getParamReader().read(request);
			Object convertedParamValue = getParamConvertor().fromString(rawParamValue);
			Violation violation = getConstraint().check(convertedParamValue);

			if (violation != null)
				throw new ViolationException(getParamReader().getParamName(), violation);

			return convertedParamValue;

		} catch (TypeConversionException e)
		{
			throw new ViolationException(getParamReader().getParamName(), new SimpleViolation(e));
		}
	}

	// ------------------------------------------------------------

	public ParamReader getParamReader()
	{
		return this.paramReader;
	}

	public void setParamReader(ParamReader paramReader)
	{
		this.paramReader = paramReader;
	}

	public ParamConvertor<?> getParamConvertor()
	{
		return this.paramConvertor;
	}

	public void setParamConvertor(ParamConvertor<?> paramConvertor)
	{
		this.paramConvertor = paramConvertor;
	}
}
