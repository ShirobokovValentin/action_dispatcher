package com.github.shirobokovvalentin.action_dispatcher.constraint.validators;

import com.github.shirobokovvalentin.action_dispatcher.constraint.entity.Violation;

public interface ParamValidator
{
	public Violation check(Object obj);
	
	public void setMessage(String message);

}
