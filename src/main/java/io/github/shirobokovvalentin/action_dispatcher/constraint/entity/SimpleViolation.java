package io.github.shirobokovvalentin.action_dispatcher.constraint.entity;

import io.github.shirobokovvalentin.convertors.exceptions.TypeConversionException;

import java.util.ArrayList;
import java.util.List;

public class SimpleViolation extends Violation
{
	private String message;

	// ------------------------------------------------------------
	
	public SimpleViolation()
	{
	}

	public SimpleViolation(String message)
	{
		this.message = message;
	}

	public SimpleViolation(TypeConversionException e)
	{
		this.message = "Cannot get " + e.getTargetClass().getSimpleName() + " from \"" + e.getInputString() + "\"";
	}

	@Override
	public String getMessage()
	{
		return this.message;
	}

	@Override
	public List<Violation> getViolations()
	{
		List<Violation> result = new ArrayList<Violation>();
		result.add(this);
		return result;
	}
	
	// ------------------------------------------------------------

	@Override
	public String toString()
	{
		return getMessage();
	}

}
