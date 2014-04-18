package com.filesharer.common;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String key) {
		super("Resource not found:" + key);
	}
	
	public ResourceNotFoundException(String key, String msg) {
		super("Resource not found:" + key + ". " + msg);
	}
	
	public ResourceNotFoundException(String key, String msg, Throwable t) {
		super("Resource not found:" + key + ". " + msg, t);
	}
	
}
