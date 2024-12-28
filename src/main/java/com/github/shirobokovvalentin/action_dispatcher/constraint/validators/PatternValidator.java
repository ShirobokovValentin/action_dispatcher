package com.github.shirobokovvalentin.action_dispatcher.constraint.validators;

import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.SimpleViolation;
import com.github.shirobokovvalentin.action_dispatcher.exceptions.InvalidDefinitionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternValidator extends AbstractValidator implements ParamValidator
{
	private final Pattern pattern;

	// ------------------------------------------------------------

	public PatternValidator(String patternString)
	{
		pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
	}

	// ------------------------------------------------------------

	public static PatternValidator getInstance(String args)
	{
		if (args == null)
			throw new InvalidDefinitionException("Can't create PatternValidator");
		return new PatternValidator(args);
	}

	public SimpleViolation check(Object obj)
	{
		if (!(obj instanceof String))
			return null;

		Matcher matcher = pattern.matcher((String) obj);
		if (matcher.find())
			return null;
		
		return new SimpleViolation( "must match the pattern: " + pattern.toString());

	}

}
