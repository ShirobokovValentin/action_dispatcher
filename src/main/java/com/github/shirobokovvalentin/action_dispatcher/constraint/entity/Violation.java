package com.github.shirobokovvalentin.action_dispatcher.constraint.entity;

import java.util.List;

public abstract class Violation
{
	public abstract String getMessage();
	
	public abstract List<Violation> getViolations();

}
