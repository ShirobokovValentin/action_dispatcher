package com.github.shirobokovvalentin.action_dispatcher.constraint.validators;

import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.SimpleViolation;

public class NotEmptyStringValidator extends AbstractValidator implements ParamValidator
{
	public SimpleViolation check(Object obj)
	{
		if ((obj instanceof String) && !((String) obj).isEmpty())
			return null;
		return new SimpleViolation(getMessage());
	}
}
