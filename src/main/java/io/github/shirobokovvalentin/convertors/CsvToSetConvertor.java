package io.github.shirobokovvalentin.convertors;

import java.util.HashSet;
import java.util.Set;

public class CsvToSetConvertor extends CsvToCollectionConvertor
{
	private static final long serialVersionUID = 5187370936583981101L;

	// ------------------------------------------------------------

	public CsvToSetConvertor(String data, String separator)
	{
		super(data, separator);
	}

	public CsvToSetConvertor(String data)
	{
		super(data);
	}

	// ------------------------------------------------------------

	public <T> Set<T> ofObjects(Class<T> clazz)
	{
		String[] rawStringsArray = data.split(separator);

		Set<T> result = new HashSet<T>();
		for (String rawString : rawStringsArray)
		{
			T value = new ConvertibleString(rawString).asObject(clazz);
			result.add(value);
		}
		return result;
	}

	public Set<Boolean> ofBooleans()
	{
		return ofObjects(Boolean.class);
	}

	public Set<Byte> ofBytes()
	{
		return ofObjects(Byte.class);
	}

	public Set<Character> ofCharacters()
	{
		return ofObjects(Character.class);
	}

	public Set<Short> ofShorts()
	{
		return ofObjects(Short.class);
	}

	public Set<Integer> ofIntegers()
	{
		return ofObjects(Integer.class);
	}

	public Set<Long> ofLongs()
	{
		return ofObjects(Long.class);
	}

	public Set<Float> ofFloats()
	{
		return ofObjects(Float.class);
	}

	public Set<Double> ofDoubles()
	{
		return ofObjects(Double.class);
	}

	public Set<String> ofStrings()
	{
		return ofObjects(String.class);
	}

}
