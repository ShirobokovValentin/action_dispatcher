package io.github.shirobokovvalentin.action_dispatcher.entity;

public class RawArgumentDescriptor<T, E>
{
	private Class<? extends T> workerClass;

	private String factoryMethod;

	private Object creationParams;
	
	private E additionalValue;

	// ------------------------------------------------------------

	public Class<? extends T> getWorkerClass()
	{
		return this.workerClass;
	}

	public void setWorkerClass(Class<? extends T> clazz)
	{
		this.workerClass = clazz;
	}

	public Object getCreationParams()
	{
		return this.creationParams;
	}

	public void setCreationParams(Object creationParams)
	{
		this.creationParams = creationParams;
	}

	public String getFactoryMethod()
	{
		return this.factoryMethod;
	}

	public void setFactoryMethod(String factoryMethod)
	{
		this.factoryMethod = factoryMethod;
	}

	public E getAdditionalValue()
	{
		return this.additionalValue;
	}

	public void setAdditionalValue(E additionalValue)
	{
		this.additionalValue = additionalValue;
	}

}
