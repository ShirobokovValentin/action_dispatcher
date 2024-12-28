package com.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public class ParameterReader implements ParamReader
{
	private String defaultValue;
	private final String paramName;

	// ------------------------------------------------------------

	public ParameterReader(String param)
	{
		this.paramName = param;
	}

	public String read(HttpServletRequest request)
	{
		if (this.paramName == null)
			return getDefaultValue();

		String value = request.getParameter(this.paramName);
		if (value != null)
			return value;

		return getDefaultValue();
	}

	// ------------------------------------------------------------

	public String getDefaultValue()
	{
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public String getParamName()
	{
		return this.paramName;
	}

}
