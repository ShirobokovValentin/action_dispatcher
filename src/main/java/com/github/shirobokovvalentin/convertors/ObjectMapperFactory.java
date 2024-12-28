package com.github.shirobokovvalentin.convertors;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;

import com.github.shirobokovvalentin.convertors.annotationIntrospector.JaxbAnnotationIntrospector;

public class ObjectMapperFactory extends ObjectMapper
{
	public static ObjectMapper newObjectMapper()
	{
		ObjectMapper mapper = new ObjectMapper();

		AnnotationIntrospector intr = new AnnotationIntrospector.Pair(
				new JaxbAnnotationIntrospector(),
				new JacksonAnnotationIntrospector());

		mapper.setSerializationConfig(
				mapper.getSerializationConfig()
						.withSerializationInclusion(JsonSerialize.Inclusion.NON_NULL)
						.withAnnotationIntrospector(intr));
		mapper.setDeserializationConfig(
				mapper.getDeserializationConfig()
						.withAnnotationIntrospector(intr));

		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

		return mapper;
	}

}
