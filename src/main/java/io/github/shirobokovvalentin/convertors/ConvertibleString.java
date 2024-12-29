package io.github.shirobokovvalentin.convertors;

import java.io.Serializable;

public class ConvertibleString implements Convertible, Serializable
{
	private static final long serialVersionUID = -7727195740645462710L;

	// ------------------------------------------------------------

	private final String data;

	// ------------------------------------------------------------

	public ConvertibleString(String data)
	{
		super();
		this.data = data;
	}

	// ------------------------------------------------------------

	public CsvToListConvertor asList()
	{
		return new CsvToListConvertor(data);
	}

	public CsvToListConvertor asListSeparatedBy(String separator)
	{
		return new CsvToListConvertor(data, separator);
	}

	@Override
	public CsvToSetConvertor asSet()
	{
		return new CsvToSetConvertor(data);
	}

	@Override
	public CsvToSetConvertor asSetSeparatedBy(String separator)
	{
		return new CsvToSetConvertor(data, separator);
	}

	public <T> T asObject(Class<T> clazz)
	{
		ParamConvertor<T> convertor = ConvertorsStoke.getConvertors(clazz);
		return convertor.fromString(data);
	}

	@SuppressWarnings("unchecked")
	public <T> T orDefaultValue(T defaultValue)
	{
		Class<? extends Object> clazz = defaultValue.getClass();
		Object value = asObject(clazz);
		if (value != null)
			return (T) value;
		return defaultValue;
	}

	// ------------------------------------------------------------

	public String asString()
	{
		return asObject(String.class);
	}

	public boolean asBoolean()
	{
		return asObject(boolean.class);
	}

	public byte asByte()
	{
		return asObject(byte.class);
	}

	public char asChar()
	{
		return asObject(char.class);
	}

	public short asShort()
	{
		return asObject(short.class);
	}

	public int asInt()
	{
		return asObject(int.class);
	}

	public long asLong()
	{
		return asObject(long.class);
	}

	public float asFloat()
	{
		return asObject(float.class);
	}

	public double asDouble()
	{
		return asObject(double.class);
	}

}
