package io.github.shirobokovvalentin.action_dispatcher.exceptions;

import java.util.ArrayList;
import java.util.List;

public class IncompatibleAnnotationException extends InvalidMappingException
{
	private static final long serialVersionUID = -7392641634673966614L;

	private List<String> annotationNames = new ArrayList<String>();

	// ------------------------------------------------------------

	public IncompatibleAnnotationException()
	{
		super();
	}

	public List<String> getAnnotationNames()
	{
		return this.annotationNames;
	}

	public void setAnnotationNames(List<String> annotationNames)
	{
		this.annotationNames = annotationNames;
	}

	public void setAnnotationName(String annotationName)
	{
		this.annotationNames.add(annotationName);
	}

}
