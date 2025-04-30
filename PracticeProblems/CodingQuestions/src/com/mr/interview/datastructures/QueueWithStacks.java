package com.mr.interview.datastructures;

import java.util.Scanner;
import java.util.Stack;
public class QueueWithStacks {

    private final Stack<Integer> pushStk;
    private final Stack<Integer> popStk;


    public QueueWithStacks() {
        this.pushStk = new Stack<>();
        this.popStk = new Stack<>();
    }

    public void enqueue(final int val) {
        this.pushStk.push(val);
    }

    public int dequeue() {
        if (popStk.isEmpty()) {
            while (!pushStk.isEmpty()) {
                popStk.push(pushStk.pop());
            }
        }

        return (popStk.isEmpty() ? -1 : popStk.pop());
    }

    public int peekqueue() {
        if (popStk.isEmpty()) {
            while(!pushStk.isEmpty()) {
                popStk.push(pushStk.pop());
            }
        }

        return (popStk.isEmpty() ? -1 : popStk.peek());
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        final Scanner sc = new Scanner(System.in);
        QueueWithStacks queue = new QueueWithStacks();

        int q = sc.nextInt(); // number of queries

        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();

            if (type == 1) {
                int x = sc.nextInt();
                queue.enqueue(x);
            } else if (type == 2) {
                queue.dequeue();
            } else if (type == 3) {
                System.out.println(queue.peekqueue());
            }
        }

        sc.close();
    }

    /**
     * Sample input:
     * STDIN   Function
     * -----   --------
     * 10      q = 10 (number of queries)
     * 1 42    1st query, enqueue 42
     * 2       dequeue front element
     * 1 14    enqueue 42
     * 3       print the front element
     * 1 28    enqueue 28
     * 3       print the front element
     * 1 60    enqueue 60
     * 1 78    enqueue 78
     * 2       dequeue front element
     * 2       dequeue front element
     */
}
