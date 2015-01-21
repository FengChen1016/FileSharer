package com.filesharer.common.monitor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Monitor implements Runnable {
	private static final long DEFAULT_INTERVAL = 10 * 1000;  // ten seconds
	private final long interval;
	private volatile boolean running = false;
	private Thread monitorThread = null;
	
	// private final List<Observer> observers = Collections.synchronizedList(new ArrayList<Observer>());
	// use CopyOnWriteArrayList for concurrent modification. 
	// reference apache.commons.io.monitor.FileAlterationMonitor
	private final List<Observer> observers = new CopyOnWriteArrayList<>();
	
	public Monitor() {
		interval = DEFAULT_INTERVAL;
	}
	
	public Monitor(long interval) {
		this.interval = interval;
	}
	
	public Monitor(long interval, Observer... obsrvs) {
		this.interval = interval;
		for (Observer ob : obsrvs) {
			addObserver(ob);
		}
	}
	
	public long getInterval() {
		return interval;
	}

	public void addObserver(Observer ob) {
		observers.add(ob);
	}
	
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}
	
	public void start() {
		if (running) {
			System.out.println("Monior had already been started.");
			return;
		}
		running = true;
		System.out.println("starting monitor...");
		monitorThread = new Thread(this);
		monitorThread.start();
		System.out.println("monitor started...");
	}
	
	public void stop() {
		stop(interval);
	}
	
	public void stop(long stopInterval) {
		if (!running) {
			System.out.println("Monior is not running.");
			return;
		}
		running = false;
		try {
			System.out.println("Stopping monitor...");
            monitorThread.join(stopInterval);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
		System.out.println("Monitor stopped.");
	}

	@Override
	public void run() {
		while (running) {
			for (Observer ob : observers) {
				try {
					ob.checkAndNotify();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!running) {
				break;
			}
			try {
				Thread.sleep(interval);
			} catch (InterruptedException ignore) {
			}
		}
	}
	
}
