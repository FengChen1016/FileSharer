package com.filesharer.common.monitor.filemonitor;

import com.filesharer.common.file.FileResource;
import com.filesharer.common.monitor.Subject;

public class FileSubject extends FileResource implements Subject {

	public FileSubject(String key) {
		super(key);
	}

	@Override
	public FileState state() {
		return new FileState();
	}

}
