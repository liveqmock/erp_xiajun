package com.wangqin.globalshop.order.app.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.SimpleType;

import java.io.IOException;
import java.util.List;

public class JacksonHelper
{
	private static ObjectMapper mapper = new ObjectMapper();

	static
	{
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
	}

	public static String toJSON(Object src)
	{
		try
		{
			return mapper.writeValueAsString(src);
		}
		catch (JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T fromJSON(String json, Class<T> valueType)
	{
		try
		{
			return mapper.readValue(json, valueType);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> fromJSONList(String json, Class<T> valueType)
	{
		try
		{
			CollectionType listType = CollectionType.construct(List.class, SimpleType.construct(valueType));
			return mapper.readValue(json, listType);
			// return mapper.readValue(json, new TypeReference<List<T>>() {});
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
