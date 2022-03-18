package com.mr.interview.datastructures;

public class LinkedList {
	private LinkedListNode mRoot;
	private int mLength;

	public class LinkedListNode {
	
		private LinkedListNode mNext;
		private int mData;

		public void setData(int value) {
			mData = value;
		}
		public int getData() {
			return mData;
		}

		public void setNext(LinkedListNode next) {
			mNext = next;
		}
		public LinkedListNode getNext() {
			return mNext;
		}
	}

	public void addHead(int n) {
		if (mRoot == null) {
			mRoot = new LinkedListNode();
			mRoot.setData(n);
			mRoot.setNext(null);
		} else {
			LinkedListNode newNode = new LinkedListNode();
			newNode.setData(n);
			newNode.setNext(mRoot);
			mRoot = newNode;
		}
		mLength++;
	}

	public void addTail(int n) {
		LinkedListNode newNode = new LinkedListNode();
		newNode.setData(n);
		newNode.setNext(null);
		if (mRoot == null) {
			mRoot = newNode;
		} else {
			LinkedListNode temp = mRoot;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(newNode);
		}
		mLength++;
	}

	public int getLength() {
		return mLength;
	}


	public void addLoop(int loop) {
		LinkedListNode tail = returnTail();
		LinkedListNode loopNode = returnNode(loop);
		if (loopNode != null) {
			tail.setNext(loopNode);
			System.out.print("\naddLoop() Loop Node: " + loop + " added");
		} else {
			System.out.print("\naddLoop() Loop Node: " + loop + " not found!");
		}
	}

	public boolean isLooped() {
		boolean loopFound = false;
		if (mRoot != null) {
			LinkedListNode slow = mRoot.getNext();
			if (slow != null) {
				LinkedListNode fast = slow.getNext();
				while (slow != null && fast != null) {
					if (slow.getData() == fast.getData()) {
						loopFound = true;
						break;
					}
					slow = slow.getNext();
					fast = fast.getNext();
					if (fast != null) {
						fast = fast.getNext();
					}
				}
			}
		}
		return loopFound;
	}

	public int getLoopStartNode() {
		int len = getLoopLength();
		LinkedListNode front = mRoot; 
		for (int i =0; i<len; i++) {
			front = front.getNext();
		}
		LinkedListNode back = mRoot;
		while (front != back) {
			front = front.getNext();
			back = back.getNext();
		}
		return front.getData();
	}

	public int getLoopLength() {
		int loopLength = 0;
		LinkedListNode meetingNode = meetingNode();
		if (meetingNode != null) {
			LinkedListNode movingNode = meetingNode.getNext();
			loopLength = 1;
			while (movingNode != meetingNode) {
				movingNode = movingNode.getNext();
				loopLength++;
			}
		}
		return loopLength;
	}

	public void doReverse() {
		if (mRoot != null && mRoot.getNext() != null) {
			LinkedListNode current = mRoot;
			LinkedListNode prev = null;
			LinkedListNode next = null;

			while (current != null) {
				next = current.getNext();
				current.setNext(prev);
				prev = current;
				current = next;
			}
			mRoot = prev;
		}
	}

	public LinkedListNode getKthNodeFromEnd(int k) {
		LinkedListNode kThNode = null;
		if (k >= 0) {
			LinkedListNode sentinel = mRoot;
			int i = 0;
			while (i < k && sentinel != null) {
				sentinel = sentinel.getNext();
				i++;
			}
			kThNode = mRoot;
			while(sentinel != null) {
				sentinel = sentinel.getNext();
				kThNode = kThNode.getNext();
			}
		}
		
		return kThNode;
	}

	private LinkedListNode meetingNode() {
		LinkedListNode meetingNode = null;
		if (mRoot != null) {
			LinkedListNode slow = mRoot.getNext();
			if (slow != null) {
				LinkedListNode fast = slow.getNext();
				while (slow != null && fast != null) {
					if (slow.getData() == fast.getData()) {
						meetingNode = fast;
						break;
					}
					slow = slow.getNext();
					fast = fast.getNext();
					if (fast != null) {
						fast = fast.getNext();
					}
				}
			}
		}
		return meetingNode;
	}

	private LinkedListNode returnNode(int nodeData) {
		LinkedListNode temp = mRoot;
		while (temp != null && temp.getData() != nodeData) {
			temp = temp.getNext();
		}
		return temp;
	}

	private LinkedListNode returnTail() {
		LinkedListNode temp = mRoot;
		if ( temp != null) {
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
		}
		return temp;
	}

	public void display() {
		if (isLooped()) {
			System.out.print("\nprintList(): Loop Found: Cannot print list! ");
		} else {
			LinkedListNode temp = mRoot;
			System.out.print("\nprintList() : ");
			while (temp != null) {
				System.out.print(temp.getData() + "->");
				temp = temp.getNext();
			}
			System.out.print("null");
		}
	}

	public static void main(String... args) {
		
		LinkedList loopTestList = createTestList();
		testLooped(loopTestList);
		System.out.print("\n========================================================");

		LinkedList list = createTestList();
		testKthNodeFromEnd(list);
		System.out.print("\n========================================================");
		
		testReverse(list);
		System.out.print("\n========================================================");

	}

	private static void testReverse(LinkedList ll) {
		System.out.print("\nTest Reverse() ");
		ll.doReverse();
		ll.display();
		ll.doReverse();
		ll.display();
	}

	private static void testKthNodeFromEnd(LinkedList ll) {
		System.out.print("\n" + "Test Kth Node From The End() for listlength: " + ll.getLength());
		int k = 1;
		LinkedListNode kThNode = ll.getKthNodeFromEnd(k);
		if (kThNode != null) {
			System.out.print("\n" + k +"th Node from the end: " + kThNode.getData());
		} else {
			System.out.print("\n" + k +"th Node from end is NULL");
		}
		k = 2;
		kThNode = ll.getKthNodeFromEnd(k);
		if (kThNode != null) {
			System.out.print("\n" + k +"th Node from the end: " + kThNode.getData());
		}
		k = 4;
		kThNode = ll.getKthNodeFromEnd(k);
		if (kThNode != null) {
			System.out.print("\n" + k +"th Node from the end: " + kThNode.getData());
		}
		int listLength = ll.getLength();
		k = listLength;
		kThNode = ll.getKthNodeFromEnd(k);
		if (kThNode != null) {
			System.out.print("\n" + k +"th Node from the end: " + kThNode.getData());
		}
	}

	private static LinkedList createTestList() {
		// Create a linked list
		LinkedList ll = new LinkedList();
		ll.addTail(1);
		ll.addTail(2);
		ll.addHead(0);
		ll.addTail(3);
		ll.addTail(4);
		ll.addTail(5);
		ll.display();
		return ll;
	}

	private static void testLooped(LinkedList ll) {
		System.out.print("\n" + "Test Loop in Linked List");
		// Test if looped.
		System.out.print("\nIs Looped? " + ll.isLooped());
		ll.addLoop(8);
		// Test if looped.
	    System.out.print("\nIs Looped? " + ll.isLooped());
		ll.addLoop(2);
		// Test if looped.
	    System.out.print("\nIs Looped? " + ll.isLooped());
	    ll.display();
	    
	    System.out.print("\nLoop Length = " + ll.getLoopLength());
	    System.out.print("\nLoop Start Node = " + ll.getLoopStartNode());
	}
}