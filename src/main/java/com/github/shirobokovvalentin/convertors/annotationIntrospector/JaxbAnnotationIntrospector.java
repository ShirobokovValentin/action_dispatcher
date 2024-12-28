package com.github.shirobokovvalentin.convertors.annotationIntrospector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.Annotated;

public class JaxbAnnotationIntrospector extends org.codehaus.jackson.xc.JaxbAnnotationIntrospector
{

	@Override
	public JsonSerialize.Inclusion findSerializationInclusion(Annotated a, JsonSerialize.Inclusion defValue)
	{
		boolean nillable = isNillable(a);

		if (nillable)
			return JsonSerialize.Inclusion.ALWAYS;
		
		if (defValue == JsonSerialize.Inclusion.ALWAYS)
			return JsonSerialize.Inclusion.NON_NULL;
		
		return defValue;
	}

	private boolean isNillable(Annotated a)
	{
		XmlElementWrapper w = a.getAnnotation(XmlElementWrapper.class);
		if (w != null)
			return w.nillable();

		XmlElement e = a.getAnnotation(XmlElement.class);
		if (e != null)
			return e.nillable();

		return false;
	}
}
