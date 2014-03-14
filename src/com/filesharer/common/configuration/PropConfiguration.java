package com.filesharer.common.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropConfiguration implements IConfiguration {
	
	private String path;
	private Map<String, String> props = new HashMap<String, String>();

	public PropConfiguration(String path) {
		this.path = path;
	}
	
	/**
	 * Load properties configuration via given file path
	 */
	@Override
	public void load() {
		File propFile = new File(path);
		if (!propFile.exists()) {
			throw new ConfigurationException("Configuration properities file not exist.");
		}
		
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(propFile);
			p.load(in);
			Set<Entry<Object, Object>> set = p.entrySet();
			for (Entry<Object, Object> e : set) {
				props.put((String) e.getKey(), (String) e.getValue());
			}
			
		} catch (IOException e) {
			throw new ConfigurationException("IOException occured in loading properties.", e);
		}
	}

	@Override
	public String getStringValue(String key) {
		return getStringValue(key, null);
	}

	@Override
	public String getStringValue(String key, String defaultValue) {
		String value = props.get(key);
		return value != null ? value : defaultValue;
	}

	@Override
	public boolean getBooleanValue(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBooleanValue(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getIntValue(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntValue(String key, int defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(String key, boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(String key, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getStringArray(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStringArray(String key, String[] value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		
	}

}
