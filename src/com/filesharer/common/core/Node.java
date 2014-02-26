package com.filesharer.common.core;

public class Node<T> {
	private T value;
	public Node<T> next;
	public Node<T> previous;
	
	public Node(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}

}
