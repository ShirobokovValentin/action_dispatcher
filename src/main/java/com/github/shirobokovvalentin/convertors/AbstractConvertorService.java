package com.github.shirobokovvalentin.convertors;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConvertorService implements ConvertorService
{
	protected Map<Object, Object> properties = new HashMap<Object, Object>();
	
	// ------------------------------------------------------------
	
	public ConvertorService property(Object key, Object value)
	{
		properties.put(key, value);
		return this;
	}

}
