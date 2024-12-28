package com.github.shirobokovvalentin.convertors.exceptions;

import com.github.shirobokovvalentin.convertors.MediaType;

public class UnsupportedMediaTypeException extends ConvertorException
{
	private static final long serialVersionUID = -4818950825622284448L;

	// ------------------------------------------------------------

	private MediaType mediaType;

	// ------------------------------------------------------------

	public MediaType getMediaType()
	{
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType)
	{
		this.mediaType = mediaType;
	}

	public UnsupportedMediaTypeException mediaType(MediaType mediaType)
	{
		setMediaType(mediaType);
		return this;
	}

}
