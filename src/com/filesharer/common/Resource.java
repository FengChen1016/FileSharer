package com.filesharer.common;

import java.io.InputStream;

public interface Resource {
	
	public String getKey();
	
	public InputStream open();
	
	public boolean exists();
	
	public long lastModified();
}
