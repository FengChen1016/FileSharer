package com.filesharer.common.monitor.filemonitor;

import com.filesharer.common.monitor.State;

public class FileState extends State {
	private final long lastModified;
	
	public FileState(long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public int compareTo(State s) {
		FileState fs = (FileState)s;
		if (lastModified == fs.lastModified) return 0;
		return lastModified < fs.lastModified ? -1 : 1;
	}

}
