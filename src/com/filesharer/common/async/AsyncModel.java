package com.filesharer.common.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class AsyncModel {
	private final int coreNum;
	private final List<AsyncTask> taskList = new ArrayList<AsyncTask>();
	private final Executor executor;
	
	public AsyncModel(int coreNum) {
		this.coreNum = coreNum;
		executor = Executors.newFixedThreadPool(coreNum, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r);
			}
		});
	}
	
	

}
