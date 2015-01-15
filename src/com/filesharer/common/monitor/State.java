package com.filesharer.common.monitor;

public abstract class State implements Comparable<State> {
	
	@Override
	public abstract int compareTo(State s);
	
}
