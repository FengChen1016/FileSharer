package com.filesharer.common.configuration;

public interface IConfiguration {
	
	public void load(String configKey);
	
	public String getStringValue(String key);
	public String getStringValue(String key,String defaultValue);

	public boolean getBooleanValue(String key);
	public boolean getBooleanValue(String key,boolean defaultValue);


	public int getIntValue(String key);
	public int getIntValue(String key,int defaultValue);
	
	public void setValue(String key, String value);

	public void setValue(String key, boolean value);
	
	public void setValue(String key, int value);

	// public <E> E getValueAsInstance(String key, Class<E> clazz); 

	public String [] getStringArray(String key);
	public void setStringArray(String key, String [] value);
	
	public void save();
}
