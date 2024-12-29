package io.github.shirobokovvalentin.convertors;

import io.github.shirobokovvalentin.convertors.annotationIntrospector.MyPrettyPrinter;
import io.github.shirobokovvalentin.convertors.exceptions.UnsupportedMediaTypeException;

public class ConvertorFactory
{
	public static ConvertorService getConvertorService(MediaType mediaType) throws UnsupportedMediaTypeException
	{
		if (mediaType == null)
			throw new UnsupportedMediaTypeException().mediaType(mediaType);
			
		switch (mediaType) {
		case APPLICATION_XML:
			return new ConvertorServiceJaxbXml();
		case APPLICATION_JSON:
			return new ConvertorServiceJaxbJson();
		case PRETTY_JSON:
			return new ConvertorServiceJaxbJson().prettyPrinter(new MyPrettyPrinter());
		default:
			throw new UnsupportedMediaTypeException().mediaType(mediaType);
		}
	}
	
	public static ConvertorService getConvertorService() throws UnsupportedMediaTypeException
	{
			return getConvertorService(MediaType.PRETTY_JSON);
	}
}
