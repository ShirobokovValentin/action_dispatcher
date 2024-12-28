package com.github.shirobokovvalentin.convertors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class CsvToListConvertor extends CsvToCollectionConvertor
{
	private static final long serialVersionUID = -1075368459840921405L;

	// ------------------------------------------------------------

	public CsvToListConvertor(String data, String separator)
	{
		super(data, separator);
	}

	public CsvToListConvertor(String data)
	{
		super(data);
	}

	// ------------------------------------------------------------

	public <T> List<T> ofObjects(Class<T> clazz)
	{
		if (data == null)
			return new ArrayList<T>(0);

		String[] rawStringsArray = StringUtils.split(data, separator);

		List<T> result = new ArrayList<T>();
		for (String rawString : rawStringsArray)
		{
			T value = new ConvertibleString(rawString.trim()).asObject(clazz);
			result.add(value);
		}
		return result;
	}

	public List<Boolean> ofBooleans()
	{
		return ofObjects(Boolean.class);
	}

	public List<Byte> ofBytes()
	{
		return ofObjects(Byte.class);
	}

	public List<Character> ofCharacters()
	{
		return ofObjects(Character.class);
	}

	public List<Short> ofShorts()
	{
		return ofObjects(Short.class);
	}

	public List<Integer> ofIntegers()
	{
		return ofObjects(Integer.class);
	}

	public List<Long> ofLongs()
	{
		return ofObjects(Long.class);
	}

	public List<Float> ofFloats()
	{
		return ofObjects(Float.class);
	}

	public List<Double> ofDoubles()
	{
		return ofObjects(Double.class);
	}

	public List<String> ofStrings()
	{
		return ofObjects(String.class);
	}

	public static void main(String[] args)
	{
		List<String> res = new CsvToListConvertor("			1		, 2, 5").ofStrings();
		System.out.println(res.contains("2"));
	}

}
