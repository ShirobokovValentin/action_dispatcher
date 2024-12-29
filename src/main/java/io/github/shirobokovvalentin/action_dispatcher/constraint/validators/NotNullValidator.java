package io.github.shirobokovvalentin.action_dispatcher.constraint.validators;

import io.github.shirobokovvalentin.action_dispatcher.constraint.entity.SimpleViolation;

public class NotNullValidator extends AbstractValidator implements ParamValidator
{
	public SimpleViolation check(Object obj)
	{
		if (obj != null)
			return null;

		return new SimpleViolation(getMessage() != null ? getMessage() : "Can't be null");
	}

}
