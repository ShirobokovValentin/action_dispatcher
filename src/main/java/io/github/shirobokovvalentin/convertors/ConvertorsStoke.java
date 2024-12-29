package io.github.shirobokovvalentin.convertors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.github.shirobokovvalentin.convertors.exceptions.TypeConversionException;

public class ConvertorsStoke
{
	static private final Map<Class<?>, ParamConvertor<?>> primitiveConvertors = new HashMap<Class<?>, ParamConvertor<?>>();

	// ------------------------------------------------------------

	static
	{
		primitiveConvertors.put(Boolean.class, new ParamConvertor<Boolean>()
		{
			public Boolean fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				return Boolean.valueOf(paramValue);
			}
		});

		primitiveConvertors.put(boolean.class, new ParamConvertor<Boolean>()
		{
			public Boolean fromString(String paramValue)
			{
				if (paramValue == null)
					throw new TypeConversionException()
							.targetClass(boolean.class)
							.inputString(paramValue);

				return Boolean.valueOf(paramValue);
			}
		});

		primitiveConvertors.put(Character.class, new ParamConvertor<Character>()
		{
			public Character fromString(String paramValue)
			{
				if (paramValue == null)
					return null;

				if (paramValue.length() == 1)
					return paramValue.charAt(0);

				throw new TypeConversionException()
						.targetClass(Character.class)
						.inputString(paramValue);
			}
		});

		primitiveConvertors.put(char.class, new ParamConvertor<Character>()
		{
			public Character fromString(String paramValue)
			{
				if (paramValue != null && paramValue.length() == 1)
					return paramValue.charAt(0);

				throw new TypeConversionException()
						.targetClass(char.class)
						.inputString(paramValue);
			}
		});

		primitiveConvertors.put(Byte.class, new ParamConvertor<Byte>()
		{
			public Byte fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					return Byte.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Byte.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(byte.class, new ParamConvertor<Byte>()
		{
			public Byte fromString(String paramValue)
			{
				try
				{
					return Byte.valueOf(paramValue);
				} catch (Exception e)
				{
					throw new TypeConversionException(e)
							.targetClass(byte.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(Short.class, new ParamConvertor<Short>()
		{
			public Short fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					return Short.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Short.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(short.class, new ParamConvertor<Short>()
		{
			public Short fromString(String paramValue)
			{
				try
				{
					return Short.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(short.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(Integer.class, new ParamConvertor<Integer>()
		{
			public Integer fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					return Integer.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Integer.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(int.class, new ParamConvertor<Integer>()
		{
			public Integer fromString(String paramValue)
			{
				try
				{
					return Integer.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(int.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(Long.class, new ParamConvertor<Long>()
		{
			public Long fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					return Long.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Long.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(long.class, new ParamConvertor<Long>()
		{
			public Long fromString(String paramValue)
			{
				try
				{
					return Long.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(long.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(Float.class, new ParamConvertor<Float>()
		{
			public Float fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					return Float.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Float.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(float.class, new ParamConvertor<Float>()
		{
			public Float fromString(String paramValue)
			{
				try
				{
					return Float.valueOf(paramValue);
				} catch (Exception e)
				{
					throw new TypeConversionException()
							.targetClass(float.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(Double.class, new ParamConvertor<Double>()
		{
			public Double fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					return Double.valueOf(paramValue);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Double.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(double.class, new ParamConvertor<Double>()
		{
			public Double fromString(String paramValue)
			{
				try
				{
					return Double.valueOf(paramValue);
				} catch (Exception e)
				{
					throw new TypeConversionException(e)
							.targetClass(double.class)
							.inputString(paramValue);
				}
			}
		});

		primitiveConvertors.put(String.class, new ParamConvertor<String>()
		{
			public String fromString(String paramValue)
			{
				return paramValue;
			}
		});

		primitiveConvertors.put(Date.class, new ParamConvertor<Date>()
		{
			public Date fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				try
				{
					Long l = Long.valueOf(paramValue);
					return new Date(l);
				} catch (NumberFormatException e)
				{
					throw new TypeConversionException()
							.targetClass(Date.class)
							.inputString(paramValue);
				}
			}
		});
	}

	public static <T> ParamConvertor<T> getConvertors(final Class<T> clazz)
	{
		if (primitiveConvertors.containsKey(clazz))
		{
			return (ParamConvertor<T>) primitiveConvertors.get(clazz);
		}

		if (clazz.isEnum())
		{
			return new ParamConvertor<T>()
			{
				public T fromString(String paramValue)
				{
					if (paramValue == null)
						return null;

					try
					{
						return (T) Enum.valueOf((Class<Enum>) clazz, paramValue);
					} catch (Exception e)
					{
						throw new TypeConversionException()
								.targetClass(clazz)
								.inputString(paramValue);
					}
				}
			};
		}

		return new ParamConvertor<T>()
		{
			ConvertorService convertor = ConvertorFactory.getConvertorService(MediaType.APPLICATION_JSON);

			public T fromString(String paramValue)
			{
				if (paramValue == null)
					return null;
				return convertor.convert(paramValue, clazz);
			}
		};
	}

}
