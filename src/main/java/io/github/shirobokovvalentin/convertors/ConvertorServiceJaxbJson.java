package io.github.shirobokovvalentin.convertors;

import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.map.ObjectMapper;

import io.github.shirobokovvalentin.convertors.exceptions.ConvertorException;

public class ConvertorServiceJaxbJson extends AbstractConvertorService implements ConvertorService
{
	private PrettyPrinter prettyPrinter;

	private ObjectMapper mapper;

	// ------------------------------------------------------------

	public ConvertorServiceJaxbJson()
	{
		mapper = ObjectMapperFactory.newObjectMapper();
	}

	// ------------------------------------------------------------

	public String convert(Object fromObject) throws ConvertorException
	{
		if (fromObject == null)
			return null;

		if (fromObject.getClass().equals(String.class))
			return (String) fromObject;

		try
		{
			if (getPrettyPrinter() != null)
			{
				String result = mapper.writer(getPrettyPrinter()).writeValueAsString(fromObject);
				return result;
			}

			String result = mapper.writeValueAsString(fromObject);
			return result;

		} catch (Exception e)
		{
			throw new ConvertorException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T convert(String fromString, Class<T> toClass) throws ConvertorException
	{
		if (toClass == null)
			return null;

		if (toClass.equals(String.class))
			return (T) fromString;
		try
		{
			T res = mapper.readValue(fromString, toClass);
			return res;

		} catch (Exception e)
		{
			throw new ConvertorException(e);
		}
	}

	// ------------------------------------------------------------

	public PrettyPrinter getPrettyPrinter()
	{
		return this.prettyPrinter;
	}

	public void setPrettyPrinter(PrettyPrinter prettyPrinter)
	{
		this.prettyPrinter = prettyPrinter;
	}

	public ConvertorServiceJaxbJson prettyPrinter(PrettyPrinter prettyPrinter)
	{
		setPrettyPrinter(prettyPrinter);
		return this;
	}

}
