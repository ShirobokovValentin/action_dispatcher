package com.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public class HeaderReader extends WithParamReader
{
	public HeaderReader (String params)
	{
		setParams(params);
	}
	
	public String read(HttpServletRequest request)
	{
		if (getParams() == null)
			return getDefaultValue();

		String value = request.getHeader(getParams());
		if (value != null)
			return value;

		return getDefaultValue();
	}

}
