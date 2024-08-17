package com.mr.interview.datastructures;

import java.util.Arrays;

public class MinHeap {

    private int size = 0;
    private int capacity = 10;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) { return parentIndex * 2 + 1; }
    private int getRightChildIndex(int parentIndex) { return parentIndex * 2 + 2; }
    private int getParentIndex(int childIndex) { return (childIndex - 1)/2; }

    private boolean hasLeftChild(int parentIndex) {return getLeftChildIndex(parentIndex) < size; }
    private boolean hasRightChild(int parentIndex) {return getRightChildIndex(parentIndex) < size; }
    private boolean hasParent(int childIndex) { return getParentIndex(childIndex) >= 0; }

    private int leftChild(int parentIndex) { return items[getLeftChildIndex(parentIndex)]; }
    private int rightChild(int parentIndex) { return items[getRightChildIndex(parentIndex)]; }
    private int parent(int parentIndex) { return items[getParentIndex(parentIndex)]; }

    private void swap(final int idx1, final int idx2) {
        final int temp = this.items[idx1];
        this.items[idx1] = this.items[idx2];
        this.items[idx2] = temp;
    }

    private void ensureExtraCapacity() {
        if (this.size == this.capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() {
        if (size == 0) throw new IllegalArgumentException();
        return items[0];
    }

    public int poll() {
        if (size == 0) throw new IllegalArgumentException();
        // Poll the item at 0th position - that is the min value for the array.
        int item = items[0];
        // Bring last element to the first.
        items[0] = items[size - 1];
        // reduce size of heap.
        size--;
        heapifyDown();
        return item;
    }

    public void add(final int addMe) {
        ensureExtraCapacity();
        items[size] = addMe;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && items[index] < parent(index)) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }
}
