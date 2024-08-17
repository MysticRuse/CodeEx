package com.mr.interview.problems;

import com.mr.interview.datastructures.MinHeap;

import java.util.Arrays;

public class FindKthLargestOrSmallest {

    public static void main(final String[] args) {
        final Integer[] arr = new Integer[] {5, 7, 23, 6, 1, 67};
        final int k = 2;


        //System.out.println("K'th smallest element = " + kthSmallestSimple(arr, k));

        //System.out.println("K'th smallest element (quick select) = " + kthSmallestQuickSelect(arr, 0, arr.length - 1, k));

        System.out.println("K'th smallest element (heap select) = " + kthSmallestMinHeap(arr, k));
    }

    /**
     * A simple solution is to sort the given array using an O(N log N) sorting algorithm like Merge Sort,
     * Heap Sort, etc, and return the element at index k-1 in the sorted array.
     * Time Complexity : O(n Log n)
     */
    private static int kthSmallestSimple(final Integer[] arr, final int k) {
        final Integer[] temp = arr;
        Arrays.sort(temp);

        return temp[k-1];
    }

    /**
     * Build a min heap and extract the elements k times.
     * Building max heap: O(n)
     * Heapify k times: O(k log n)
     * Time complexity: O(n + k log n)
     */
    private static int kthSmallestMinHeap(final Integer[] arr, final int k) {
        final MinHeap mh  = new MinHeap();

        // First build the heap by adding each element of the array to the heap.
        for (int i = 0; i < arr.length; i++) {
            mh.add(arr[i]);
        }

        int smallest = 0;
        // Extract the smallest element k times to get the kth element.
        for (int i = 0; i < k; i++) {
            smallest = mh.poll();
        }
        return smallest;
    }

    /**
     * Using a randomized pivot, quick select can give
     * Time complexity: O(n) complexity on average.
     */
    private static int kthSmallestQuickSelect(final Integer[] arr, final int low, final int high, final int k) {

        // find the partition
        final int partition = partitionQuickSelect(arr, low, high);

        // if partition value == kth position, return value at kth position
        if (partition == k - 1) {
            return arr[partition];
        }

        // if partition value is less than kth position, search right side of the array.
        if (partition < k - 1) {
            return kthSmallestQuickSelect(arr, partition + 1, high, k);
        }

        // If partition valueis more that kth position, search left side of the array.
        return kthSmallestQuickSelect(arr, low, partition - 1, k);
    }

    // Considers last element as pivot and adds elements with less value to the left and high value
    // to the right and also changes the pivot position to its respective position in the final array.
    private static int partitionQuickSelect(final Integer[] arr, final int low, final int high) {
        int pivot = arr[high], pivotloc = low;
        int temp;
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                // swap
                temp = arr[i];
                arr[i] = arr[pivotloc];
                arr[pivotloc] = temp;
                System.out.println("Swapping arr[" + i + "] and arr[" + pivotloc + "]");
                pivotloc++;
            }
        }


        // Swapping pivot to the final pivot location
        temp = arr[high];
        arr[high] = arr[pivotloc];
        arr[pivotloc] = temp;

        System.out.println("partitionQuickSelect low: " + low + ",  high: " + high + ", pivotloc: " + pivotloc);

        return pivotloc;
    }
}
