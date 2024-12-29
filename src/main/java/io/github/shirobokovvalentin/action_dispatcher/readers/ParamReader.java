package io.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;

public interface ParamReader
{
	public String read(HttpServletRequest request);

	public void setDefaultValue(String defaultValue);
	
	public String getParamName();
}
