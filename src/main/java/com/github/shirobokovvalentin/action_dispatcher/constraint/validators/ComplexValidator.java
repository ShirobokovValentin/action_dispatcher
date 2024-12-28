package com.github.shirobokovvalentin.action_dispatcher.constraint.validators;

import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.MultipleViolation;
import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.Violation;

import java.util.ArrayList;
import java.util.List;

public class ComplexValidator extends AbstractValidator implements ParamValidator
{
	private final List<ParamValidator> constraints = new ArrayList<ParamValidator>();

	// ------------------------------------------------------------

	public Violation check(Object obj)
	{
		MultipleViolation result = null;

		for (ParamValidator constraint : constraints)
		{
			Violation currentCv = constraint.check(obj);
			if (currentCv != null)
			{
				if (result == null)
					result = new MultipleViolation(getMessage());
				result.add(currentCv);
			}
		}

		return result;
	}
	

	public void add(ParamValidator constraint)
	{
		this.constraints.add(constraint);
	}
	
	public boolean isNotEmpty()
	{
		return !this.constraints.isEmpty();
	}

}
