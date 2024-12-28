package com.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public class RawRequest implements AttrReader
{
	// ------------------------------------------------------------

	@Override
	public HttpServletRequest read(HttpServletRequest request)
	{
		return request;
	}

	// ------------------------------------------------------------

	@Override
	public String getParamName()
	{
		return "HttpServletRequest";
	}

}
