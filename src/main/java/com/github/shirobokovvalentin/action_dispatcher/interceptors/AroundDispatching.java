package com.github.shirobokovvalentin.action_dispatcher.interceptors;

import javax.servlet.http.HttpServletRequest;

public interface AroundDispatching
{
	public HttpServletRequest beforeExtraction(HttpServletRequest request);
	
	public String afterConverting(String result);

}
