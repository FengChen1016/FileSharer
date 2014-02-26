package com.filesharer.common.core;

public class NodeOperation {
	
	public static <T> Node<T> reverse(Node<T> head) {
		if (head == null || head.next == null) return head;
		Node<T> current = head;
		Node<T> next;
		while (current.next != null) {
			next = current.next;
			current.next = current.previous;
			current.previous = next;
			current = next;
		}
		current.next = current.previous;
		current.previous = null;
		return current;
	}
	
	public static void main(String[] args) {
		Node<String> nodeA = new Node<String>("A");
		Node<String> nodeB = new Node<String>("B");
		Node<String> nodeC = new Node<String>("C");
		nodeA.next = nodeB;
		nodeB.previous = nodeA;
		nodeB.next = nodeC;
		nodeC.previous = nodeB;
		
		Node<String> tail = reverse(nodeA);
		
		while(tail != null) {
			System.out.println("   value: "+tail.getValue());
			System.out.println("    next: "+(tail.next!=null?tail.next.getValue():"null"));
			System.out.println("previous: "+(tail.previous!=null?tail.previous.getValue():"null"));
			tail = tail.next;
			System.out.println("--------------------------------");
		}
	}

}
