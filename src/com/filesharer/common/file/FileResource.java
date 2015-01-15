package com.filesharer.common.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.filesharer.common.Resource;
import com.filesharer.common.ResourceNotFoundException;

public class FileResource implements Resource {
	private final String key;
	private File source;
	private FileMetaData metadata;
	
	public FileResource(String key) {
		this.key = key;
		this.source = new File(key);
	}
	
	@Override
	public String getKey() {
		return key;
	}
	
	public FileMetaData getMetaData() {
		return metadata;
	}
	
	@Override
	public InputStream open() {
		if (!exists()) {
			throw new ResourceNotFoundException(key);
		}
		try {
			return FileUtils.openInputStream(source);
		} catch (IOException e) {
			throw new ResourceNotFoundException(key, " Due to IOException", e);
		}
	}

	@Override
	public boolean exists() {
		return source.exists();
	}

	@Override
	public long lastModified() {
		return source.lastModified();
	}
	
}
