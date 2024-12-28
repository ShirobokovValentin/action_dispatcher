package com.github.shirobokovvalentin.convertors;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import com.github.shirobokovvalentin.convertors.exceptions.ConvertorException;

public class ConvertorServiceJaxbXml extends AbstractConvertorService implements ConvertorService
{
	private void fillFamiliarProperties(Marshaller marshaller) throws PropertyException
	{
		Object value;
		value = properties.get(Marshaller.JAXB_FORMATTED_OUTPUT);
		if (value != null)
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, value);

		value = properties.get(Marshaller.JAXB_FRAGMENT);
		if (value != null)
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, value);

		value = properties.get(Marshaller.JAXB_ENCODING);
		if (value != null)
			marshaller.setProperty(Marshaller.JAXB_ENCODING, value);

		value = properties.get(Marshaller.JAXB_SCHEMA_LOCATION);
		if (value != null)
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, value);

		value = properties.get(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION);
		if (value != null)
			marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, value);
	}

	public String convert(final Object fromObject) throws ConvertorException
	{
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(fromObject.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			fillFamiliarProperties(marshaller);
			StringWriter sw = new StringWriter();
			marshaller.marshal(fromObject, sw);
			String result = sw.toString();
			return result;

		} catch (Exception e)
		{
			throw new ConvertorException(e);
		}
	}

	public <T> T convert(String fromXmlString, Class<T> toClass) throws ConvertorException
	{
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(toClass);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader sr = new StringReader(fromXmlString);
			@SuppressWarnings("unchecked")
			T result = (T) unmarshaller.unmarshal(sr);
			return result;

		} catch (Exception e)
		{
			throw new ConvertorException(e);
		}
	}

}
