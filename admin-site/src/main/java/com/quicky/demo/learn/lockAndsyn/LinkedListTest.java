package com.quicky.demo.learn.lockAndsyn;

/**
 * 仿造写一个链表
 * 
 * @author Administrator
 *
 * @param <E>
 */
public class LinkedListTest<E> {

	private Node<E> first;
	
	private Node<E> last;
	
	private int size = 0;
	
	private static class Node<E> {
		E e;
		Node<E> next;
		Node<E> pre;

		// 有参无参构造方法
		Node(E e, Node<E> next, Node<E> pre) {
			this.e = e;
			this.next = next;
			this.pre = pre;
		}
	}
	
	public boolean add(E e) {
		Node<E> x = first;
		Node<E> newX = new Node<E>(e,null , first);
		last = newX;
		if(first==null)
			first = newX;
		else
			newX.next = newX;
		
		size++;
		return true;
	}
	
	public E get(int index) {
		if(index==0) {
			return first.e;
		}else {
			Node<E> x = last;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x.e;
		}
	}
	
	
	
	public static void main(String[] args) {
		
		LinkedListTest<String> ss = new LinkedListTest<String>();
		ss.add("a");
		ss.add("b");
		ss.add("c");
		System.out.println(ss.get(1));
		
		
	}
	
	
	
	
	
	
	

}
