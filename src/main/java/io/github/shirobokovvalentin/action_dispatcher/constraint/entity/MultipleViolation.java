package io.github.shirobokovvalentin.action_dispatcher.constraint.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleViolation extends SimpleViolation
{
	private final List<Violation> violations = new ArrayList<Violation>();

	// ------------------------------------------------------------

	public MultipleViolation(String message)
	{
		super(message);
	}

	// ------------------------------------------------------------

	public void add(Violation constraintViolation)
	{
		violations.add(constraintViolation);
	}

	@Override
	public String getMessage()
	{
		if (super.getMessage() != null)
			return super.getMessage();

		boolean firstmessage = true;

		StringBuilder messages = new StringBuilder();

		for (Violation violation : violations)
		{
			if (violation == null)
				continue;

			if (firstmessage)
				firstmessage = false;
			else
				messages.append(", ");

			messages.append(violation.getMessage());
		}

		return messages.toString();

	}

	@Override
	public List<Violation> getViolations()
	{
		List<Violation> result = new ArrayList<Violation>();

		for (Violation violation : violations)
		{
			result.addAll(violation.getViolations());
		}

		return result;
	}

	// ------------------------------------------------------------

	@Override
	public String toString()
	{
		if (super.getMessage() == null)
			return Arrays.deepToString(violations.toArray());
		return super.getMessage();
	}

}
