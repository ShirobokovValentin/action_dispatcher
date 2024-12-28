package com.github.shirobokovvalentin.action_dispatcher.exceptions;

import java.util.ArrayList;
import java.util.List;

public class RequredAnnotationMissingException extends InvalidMappingException
{
	private static final long serialVersionUID = -7034233172696075077L;

	private List<String> annotations = new ArrayList<String>();

	// ------------------------------------------------------------

	public RequredAnnotationMissingException()
	{
		super();
	}

	public void addAnnotation(String annotationName)
	{
		this.annotations.add(annotationName);
	}

	public RequredAnnotationMissingException annotation(String annotationName)
	{
		addAnnotation(annotationName);
		return this;
	}

	public List<String> getAnnotations()
	{
		return this.annotations;
	}
}
