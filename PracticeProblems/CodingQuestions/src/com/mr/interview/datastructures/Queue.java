package com.mr.interview.datastructures;

import java.util.Stack;

/** 
 * Implementation of Queue using 2 Stacks
 */
public class Queue {
	private Stack<QueueElement> mStackStack = null;
	private Stack<QueueElement> mStackQueue = null;

	public Queue() {
		mStackStack = new Stack<QueueElement>();
		mStackQueue = new Stack<QueueElement>();
	}

	public void enqueue(QueueElement data) {
		if ( data == null) {
			return;
		}

		System.out.println("Enque: " + data.get());
		QueueElement popData;

		// TODO: Following code can be added into a method
		// to be reused for copying one stack to another
		while (!mStackQueue.isEmpty()) {
			popData = mStackQueue.pop();
			mStackStack.push(popData);
		}

		mStackStack.push(data);

		while (!mStackStack.empty()) {
			popData = mStackStack.pop();
			mStackQueue.push(popData);
		}

	}

	public QueueElement deque() {
		QueueElement popData = null;
		if (!mStackQueue.isEmpty()) {
			popData = mStackQueue.pop();
		}
		if (popData != null) {
			System.out.println("Dequed: " + popData.get());
		} else {
			System.out.println("Deque Failed. Empty Queue!");
		}
		return popData;
	}

	public void print() {
		System.out.print("Print Queue: ");
		if (mStackQueue.isEmpty()) {
			System.out.print("null");
		} else {
            for (QueueElement elem : mStackQueue) {
                if (elem != null) {
                    System.out.print(elem.get() + " ");
                } else {
                    System.out.print("null");
                }
            }
		}
		System.out.print("\n");
	}

	public static class QueueElement {
		private int mData;

		public QueueElement(int data) {
			mData = data;
		}

		public int get() {
			return mData;
		}
	
		public void set(int data) {
			mData = data;
		}
	}

	public static void main(String... args) {
		Queue myQueue = new Queue();
		myQueue.print();
		myQueue.enqueue(new QueueElement(1));
		myQueue.enqueue(new QueueElement(2));
		myQueue.enqueue(new QueueElement(3));
		myQueue.enqueue(new QueueElement(4));
		myQueue.print();
		myQueue.deque();
		myQueue.print();
		myQueue.deque();
		myQueue.deque();
		myQueue.deque();
		myQueue.deque();
		myQueue.print();
	}

}
