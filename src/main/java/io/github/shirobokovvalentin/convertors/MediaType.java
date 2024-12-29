package io.github.shirobokovvalentin.convertors;

public enum MediaType
{
	APPLICATION_XML("application/xml"), APPLICATION_JSON("application/json"), PRETTY_JSON("pretty/json");

	private final String text;

	private MediaType(final String text)
	{
		this.text = text;
	}

	@Override
	public String toString()
	{
		return text;
	}
}
