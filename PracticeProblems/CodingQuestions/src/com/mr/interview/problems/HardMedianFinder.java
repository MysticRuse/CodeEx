package com.mr.interview.problems;

import java.util.PriorityQueue;

public class HardMedianFinder {
    final PriorityQueue<Integer> maxHeapRight; //smallHeap; // maxHeap
    final PriorityQueue<Integer> minHeapLeft; //largeHeap; // minHeap

    public HardMedianFinder() {
        maxHeapRight = new PriorityQueue<>();
        minHeapLeft = new PriorityQueue<>();
    }

    public void addNum(final int num) {

        if (maxHeapRight.isEmpty()) {
            maxHeapRight.add(num);
            return;
        }
        if (minHeapLeft.isEmpty()) {
            minHeapLeft.add(num);
            return;
        }
        final int min = minHeapLeft.peek();
        if (num < min) {
            minHeapLeft.add(num);
        } else {
            maxHeapRight.add(num);
        }

        final int minHeapSize = minHeapLeft.size();
        final int maxHeapSize = maxHeapRight.size();
        final int diff = Math.abs(minHeapSize - maxHeapSize);
        if (diff > 1) {
            if (minHeapSize > maxHeapSize) {
                // pop from min heap and add to max heap
                maxHeapRight.add(minHeapLeft.poll());
            } else {
                minHeapLeft.add(maxHeapRight.poll());
            }
        }
    }

    public long findMedian() {
        return 0;
    }
}
