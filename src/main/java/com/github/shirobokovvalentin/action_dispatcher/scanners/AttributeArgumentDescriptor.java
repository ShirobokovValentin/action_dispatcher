package com.github.shirobokovvalentin.action_dispatcher.scanners;

import com.github.shirobokovvalentin.convertors.exceptions.TypeConversionException;
import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.SimpleViolation;
import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.Violation;
import com.github.shirobokovvalentin.action_dispatcher.constraint.exceptions.ViolationException;
import com.github.shirobokovvalentin.action_dispatcher.entity.ArgumentDescriptor;
import com.github.shirobokovvalentin.action_dispatcher.readers.AttrReader;

import javax.servlet.http.HttpServletRequest;

public class AttributeArgumentDescriptor extends ArgumentDescriptor
{
	private AttrReader attrReader;

	// ------------------------------------------------------------

	public Object getArgument(HttpServletRequest request) throws ViolationException
	{
		try
		{
			Object convertedParamValue = getAttrReader().read(request);
			Violation violation = getConstraint().check(convertedParamValue);

			if (violation != null)
				throw new ViolationException(getAttrReader().getParamName(), violation);

			return convertedParamValue;

		} catch (TypeConversionException e)
		{
			throw new ViolationException(getAttrReader().getParamName(), new SimpleViolation(e));
		}
	}

	// ------------------------------------------------------------

	public AttrReader getAttrReader()
	{
		return this.attrReader;
	}

	public void setAttrReader(AttrReader attrReader)
	{
		this.attrReader = attrReader;
	}

}
