package io.github.shirobokovvalentin.convertors.exceptions;

public class TypeConversionException extends ConvertorException
{
	private static final long serialVersionUID = 7303384393924860777L;

	private Class<?> targetClass;

	private String inputString;

	// ------------------------------------------------------------

	public TypeConversionException()
	{
		super();
	}

	public TypeConversionException(Throwable cause)
	{
		super(cause);
	}

	public Class<?> getTargetClass()
	{
		return this.targetClass;
	}

	// ------------------------------------------------------------

	public void setTargetClass(Class<?> targetClass)
	{
		this.targetClass = targetClass;
	}

	public TypeConversionException targetClass(Class<?> targetClass)
	{
		setTargetClass(targetClass);
		return this;
	}

	public String getInputString()
	{
		return this.inputString;
	}

	public void setInputString(String inputString)
	{
		this.inputString = inputString;
	}

	public TypeConversionException inputString(String inputString)
	{
		setInputString(inputString);
		return this;
	}

}
