package com.github.shirobokovvalentin.convertors;

public class RawDataConvertor
{
	private StringDataSource<String> stringDataSource;

	// ------------------------------------------------------------

	public StringDataSource<?> getStringDataSource()
	{
		return this.stringDataSource;
	}

	public void setStringDataSource(StringDataSource<String> stringDataSource)
	{
		this.stringDataSource = stringDataSource;
	}

	public RawDataConvertor stringDataSource(StringDataSource<String> stringDataSource)
	{
		setStringDataSource(stringDataSource);
		return this;
	}

	protected Convertible getData(String propertyName)
	{
		String data = stringDataSource.getData(propertyName);
		return new ConvertibleString(data);
	}

}
