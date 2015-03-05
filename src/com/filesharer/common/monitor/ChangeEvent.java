package com.filesharer.common.monitor;

public enum ChangeEvent {
	/*static final String NO_CHANGE = "NO_CHANGE";
	static final String CREATE = "CREATE";
	static final String CHANGE = "CHANGE";
	static final String DELETE = "DELETE";*/
	
	NO_CHANGE("NO_CHANGE"),
	CREATE("CREATE"),
	CHANGE("CHANGE"),
	DELETE("DELETE");
	
	private String name;
	
	private ChangeEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}