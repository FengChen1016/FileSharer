package com.filesharer.common.monitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class Monitor implements Runnable {
	private static final long ONE_SECOND = 1000;
	private final long interval;
	private AtomicBoolean running = new AtomicBoolean(false);
	
	private final List<Observer> observers = Collections.synchronizedList(new ArrayList<Observer>());;
	
	public Monitor() {
		interval = ONE_SECOND;
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
		if (!running.compareAndSet(false, true)) {
			System.out.println("Monior had already been started.");
			return;
		} else {
			System.out.println("Monior started...");
		}
	}

	@Override
	public void run() {
		while (running.get()) {
			for (Observer ob : observers) {
				try {
					ob.checkAndNotify();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
