package com.filesharer.common.monitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Observer {
	
	private final List<Listener> listeners = Collections.synchronizedList(new ArrayList<Listener>());
	
	private final Subject subject;
	
	public Observer(Subject subject) {
		this.subject = subject;
	}
	
	protected void checkAndNotify() {
		String event = checkChangeEvent();
		if (!event.equals(ChangeEvent.NO_CHANGE)) {
			// notify all listener
			for (Listener listener : listeners) {
				listener.onChange(subject, event);
			}
		}
	}
	
	/**
	 * To be implemented by subclass.
	 * Check state of subject according to specific business logic
	 * @return change event string
	 */
	protected abstract String checkChangeEvent();

	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}
	
	public List<Listener> getListeners() {
		return Collections.unmodifiableList(listeners);
	}
	
}
