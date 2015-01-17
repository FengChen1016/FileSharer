package com.filesharer.common.monitor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Observer {
	
	private final List<Listener> listeners = new CopyOnWriteArrayList<>();
	
	protected final Subject subject;
	
	protected State state = null;
	
	public Observer(Subject subject) {
		this.subject = subject;
		initState();
	}
	
	protected void initState() {
		state = subject.exists() ? subject.state() : null;
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
	 * To be override by subclass.
	 * Check state of subject according to specific business logic
	 * @return change event string
	 */
	protected String checkChangeEvent() {
		if (state == null) {
			if (subject.exists()) {
				state = subject.state();
				return ChangeEvent.CREATE;
			}
		} else {
			if (!subject.exists()) {
				state = null;
				return ChangeEvent.DELETE;
			}
			if (state.compareTo(subject.state()) != 0) {
				state = subject.state();
				return ChangeEvent.CHANGE;
			}
		}
		return ChangeEvent.NO_CHANGE;
	}

	
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
