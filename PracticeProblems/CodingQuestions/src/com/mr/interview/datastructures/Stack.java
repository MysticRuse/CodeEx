package com.mr.interview.datastructures;

public class Stack {

    class Node {
        int value;
        Node next;
    }

    int stackSize;
    Node top;

    public Stack() {
        this.stackSize = 0;
        this.top = null;
    }

    public boolean isEmpty() {
        return (this.top == null || this.stackSize == 0);
    }

    public void push(final int value) {
        final Node pushNode = new Node();
        pushNode.value = value;
        if (this.top == null) {
            this.top = pushNode;
            this.top.next = null;
            System.out.println("Pushing 1st elem to stack: " + value);
        } else {
            pushNode.next = top;
            this.top = pushNode;
            System.out.println("Pushing to stack: " + value);
        }
        this.stackSize += 1;
    }

    public int pop() {
        if (this.top != null) {
            final int topVal = this.top.value;
            this.top = this.top.next;
            this.stackSize -= 1;
            System.out.println("Popping from stack: " + topVal);
            return topVal;
        }
        System.out.println("Pop: Stack is empty!!!");
        return 0;
    }

    public int peek() {
        if (this.top != null) {
            System.out.println("Peeking from stack: " + this.top.value);
            return this.top.value;
        }
        System.out.println("Peek: Stack is empty!!!");
        return 0;
    }

    public int size() {
        return this.stackSize;
    }

    public static void main(final String[] args) {

        final Stack st = new Stack();
        st.push(1);
        st.push(2);
        System.out.println("Stack size: " + st.size());
        st.push(14);
        st.push(15);
        System.out.println("Stack size: " + st.size());
        int val = st.peek();
        while (!st.isEmpty()) {
            st.pop();
        }
        st.pop();
    }
}


