package com.github.shirobokovvalentin.convertors;

import java.io.Serializable;

public abstract class CsvToCollectionConvertor implements Serializable
{
	private static final long serialVersionUID = 1173971447026574460L;

	private static final String DEFAULT_SEPARATOR = "\\s*,\\s*";

	// ------------------------------------------------------------

	protected final String separator;

	protected final String data;

	// ------------------------------------------------------------

	public CsvToCollectionConvertor(String data)
	{
		this(data, DEFAULT_SEPARATOR);
	}

	public CsvToCollectionConvertor(String data, String separator)
	{
		this.data = data;
		this.separator = separator;
	}

	// ------------------------------------------------------------

	@Override
	public String toString()
	{
		return data;
	}

}
