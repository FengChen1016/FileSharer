package com.filesharer.common.async;

public interface AsyncTask {
	
	void preExecute();
	
	void execute();
	
	void postExecute();
	
}
