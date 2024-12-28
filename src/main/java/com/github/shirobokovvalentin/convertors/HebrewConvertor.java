package com.github.shirobokovvalentin.convertors;

import java.io.UnsupportedEncodingException;

public class HebrewConvertor implements ParamConvertor<String>
{
	private static final String ISO_8859_1 = "ISO-8859-1";

	private static final String UTF_8 = "UTF-8";

	private static final HebrewConvertor instance = new HebrewConvertor();

	// ------------------------------------------------------------

	public static HebrewConvertor getInstance()
	{
		return instance;
	}

	// ------------------------------------------------------------

	public String fromString(String paramValue)
	{
		try
		{
			return paramValue == null ? null : new String(paramValue.getBytes(ISO_8859_1), UTF_8);
		} catch (UnsupportedEncodingException ex)
		{
			return null;
		}

	}

}