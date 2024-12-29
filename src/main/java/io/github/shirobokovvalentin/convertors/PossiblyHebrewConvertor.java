package io.github.shirobokovvalentin.convertors;

import java.io.UnsupportedEncodingException;

public class PossiblyHebrewConvertor implements ParamConvertor<String>
{
	private static final String ISO_8859_1 = "ISO-8859-1";

	private static final String UTF_8 = "UTF-8";

	private static final PossiblyHebrewConvertor instance = new PossiblyHebrewConvertor();

	// ------------------------------------------------------------

	public static PossiblyHebrewConvertor getInstance()
	{
		return instance;
	}

	// ------------------------------------------------------------

	public String fromString(String paramValue)
	{
		if (paramValue == null)
			return null;

		String[] charsets = { ISO_8859_1};

		try
		{
			for (String charset : charsets)
			{
				String converted = new String(paramValue.getBytes(charset), UTF_8);
				String likeOriginal = new String(converted.getBytes(UTF_8), charset);

				if (paramValue.equals(likeOriginal))
				{
					return converted;
				}
			}

		} catch (UnsupportedEncodingException e)
		{
			return null;
		}
		return paramValue;
	}
}