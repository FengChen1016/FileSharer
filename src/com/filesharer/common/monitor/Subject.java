package com.filesharer.common.monitor;

public interface Subject {
	/**
	 * Check if the subject exists
	 * @return true if the subject exists;
	 * 		   false if the subject not exists;
	 */
	boolean exists();
	/**
	 * Query subject's current state 
	 * @return Object stand for subject's current state
	 */
	Object state();
	
}
