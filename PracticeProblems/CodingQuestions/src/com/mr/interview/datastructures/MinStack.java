package com.mr.interview.datastructures;

import java.util.Stack;

public class MinStack {

    // Design a min stack.
    private final Stack<Integer> stack;
    private final Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack();
    }

    public  void push(int n) {
        stack.push(n);
        if (minStack.empty() || n <= minStack.peek()) {
            this.minStack.push(n);
        }
    }

    public int pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return stack.pop();
    }

    public int getMin() {
        return minStack.peek();
    }

    public int top() {
        return stack.peek();
    }

    public static void main(final String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        //minStack.getMin() // return -3
        System.out.println("getMin() returns (-3):" + minStack.getMin());
        //minStack.pop(); // return 0
        System.out.println("pop() returns (-3):" + minStack.pop());
        //minStack.top();    // return 0
        System.out.println("top() returns (0):" + minStack.top());
        //minStack.getMin(); // return -2
        System.out.println("getMin() returns (-2):" + minStack.getMin());
    }
}
