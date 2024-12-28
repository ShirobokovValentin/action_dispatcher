package com.github.shirobokovvalentin.convertors;

public interface Convertible
{
	public CsvToListConvertor asList();
	
	public CsvToListConvertor asListSeparatedBy(String separator);
	
	public CsvToSetConvertor asSet();
	
	public CsvToSetConvertor asSetSeparatedBy(String separator);

	public <T> T asObject(Class<T> clazz);

	public <T> T orDefaultValue(T defaultValue);
	
	public String asString();

	public boolean asBoolean();

	public byte asByte();

	public char asChar();

	public short asShort();

	public int asInt();

	public long asLong();

	public float asFloat();

	public double asDouble();

}
