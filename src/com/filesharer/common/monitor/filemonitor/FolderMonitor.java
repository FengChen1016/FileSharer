package com.filesharer.common.monitor.filemonitor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Monitor folder by WatchService
 *
 */
public class FolderMonitor implements Runnable {
	private volatile boolean running = false;
	private final Path path;
	private WatchService watcher;
	
	private Thread monitorThread = null;
	
	public FolderMonitor(Path path) {
		this.path = path;
		/*watcher = FileSystems.getDefault().newWatchService();
		path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);*/
	}
	
	public void start() throws IOException {
		if (running) {
			System.out.println("Monior had already been started.");
			return;
		}
		running = true;
		watcher = FileSystems.getDefault().newWatchService();
		path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		System.out.println("starting monitor...");
		monitorThread = new Thread(this);
		monitorThread.start();
		System.out.println("monitor started...");
	}
	
	public void stop() {
		if (!running) {
			System.out.println("Monior is not running.");
			return;
		}
		running = false;
		try {
			watcher.close();
			System.out.println("Stopping monitor...");
            monitorThread.join();
		} catch (IOException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			final WatchKey key = watcher.poll();
			if (key == null) continue;
			for (WatchEvent<?> event : key.pollEvents()) {
				final Kind<?> kind = event.kind();
				// Overflow event
				if (StandardWatchEventKinds.OVERFLOW == kind) {
					System.out.println("overflow, ignore.");
					continue; // loop
				}
				// create
				if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println(((Path)event.context()).toAbsolutePath() + " created.");
				}
				// modify
				if(kind == StandardWatchEventKinds.ENTRY_MODIFY) {
					System.out.println(((Path)event.context()).toAbsolutePath() + " modified.");
				}
				// delete
				if(kind == StandardWatchEventKinds.ENTRY_DELETE) {
					System.out.println(((Path)event.context()).toAbsolutePath() + " deleted.");
				}
			}
			// put key back into it’s Ready step
			if(!key.reset()) break;
			
		}
	}
	
}
