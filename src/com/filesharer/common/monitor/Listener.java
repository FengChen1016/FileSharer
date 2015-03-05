package com.filesharer.common.monitor;

public interface Listener {
	/**
	 * Respond to any state change of subject
	 * @param subject subject that is observed
	 * @param changeEvent
	 */
	void onChange(Subject subject, ChangeEvent changeEvent);
	
}
