package com.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public class HttpMethodReader extends WithParamReader
{
	public String read(HttpServletRequest request)
	{
		return request.getMethod();
	}

}
