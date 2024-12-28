package com.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public interface AttrReader
{
	public Object read(HttpServletRequest request);

	public String getParamName();
}
