package com.filesharer.common.configuration;

public class ConfigurationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
		super();
	}
	
	public ConfigurationException(String e) {
		super(e);
	}
	
	public ConfigurationException(String s, Throwable t) {
		super(s, t);
	}
	
	public ConfigurationException(Throwable t) {
		super(t);
	}
	
}
