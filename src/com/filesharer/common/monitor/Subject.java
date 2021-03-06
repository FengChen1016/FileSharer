package com.filesharer.common.monitor;

public interface Subject {
	/**
	 * Check if the subject exists
	 * @return true if the subject exists;
	 * 		   false if the subject not exists;
	 */
	boolean exists();
	/**
	 * Check subject's current state 
	 * @return state of subject
	 */
	State state();
	
}
