package com.github.shirobokovvalentin.action_dispatcher.readers;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class IpReader implements ParamReader
{
	private static final IpReader instance = new IpReader();

	private static final List<String> HEADERS_TO_CHECK = Arrays.asList(
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR"
	);

	// ------------------------------------------------------------

	public static IpReader getInstance()
	{
		return instance;
	}

	// ------------------------------------------------------------

	@Override
	public String read(HttpServletRequest request)
	{
		for (String header : HEADERS_TO_CHECK) {
			String ip = request.getHeader(header);
			if (isValidIp(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr(); // Возврат IP по умолчанию
	}

	private boolean isValidIp(String ip)
	{
		return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
	}

	public void setDefaultValue(String defaultValue)
	{
		//NOP
	}

	public String getParamName()
	{
		return "Header params";
	}

}
