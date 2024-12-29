package io.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public class AttributeReader implements AttrReader
{
	private final String paramName;

	// ------------------------------------------------------------

	public AttributeReader(String param)
	{
		this.paramName = param;
	}

	public Object read(HttpServletRequest request)
	{
		if (this.paramName == null)
			return null;
		Object value = request.getAttribute(this.paramName);
		return value;
	}

	// ------------------------------------------------------------

	public String getParamName()
	{
		return this.paramName;
	}

}
