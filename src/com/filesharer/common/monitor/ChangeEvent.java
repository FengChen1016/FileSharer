package com.filesharer.common.monitor;

public interface ChangeEvent {
	static final String NO_CHANGE = "NO_CHANGE";
	static final String CREATE = "CREATE";
	static final String CHANGE = "CHANGE";
	static final String DELETE = "DELETE";
}