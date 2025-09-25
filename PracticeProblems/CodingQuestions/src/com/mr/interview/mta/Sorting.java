package com.mr.interview.mta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {


    /**
     * Bubble sort python:
     * def bubble_sort(arr):
     *     """
     *     Args:
     *      arr(list_int32)
     *     Returns:
     *      list_int32
     *     """
     *     # Write your code here.
     *     n = len(arr)
     *
     *     for i in range(n):
     *         for red in range(n-1, i, -1):
     *             if (arr[red-1] > arr[red]):
     *                 arr[red-1], arr[red] = arr[red], arr[red-1]
     *
     *     return arr
     *
     */


    public final int[] bubbleSort(final int[] arr) {
        final int n = arr.length;

        for (int i = 0; i < n-1; i++) {
            // Iterate from right to left to bubble the largest to the right.
            for (int red = n-1; red >= i+1; red--) {
                // Swap to put the larger to the right.
                if (arr[red-1] > arr[red]) {
                    int temp = arr[red - 1];
                    arr[red - 1] = arr[red];
                    arr[red] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * In Insertion Sort Part 1, you inserted one element into an array at its correct sorted position. Using the same approach repeatedly, can you sort an entire array?
     * Guideline: You already can place an element into a sorted array. How can you use that code to build up a sorted array, one element at a time? Note that in the first step, when you consider an array with just the first element, it is already sorted since there's nothing to compare it to.
     * In this challenge, print the array after each iteration of the insertion sort, i.e., whenever the next element has been inserted at its correct position. Since the array composed of just the first element is already sorted, begin printing after placing the second element.
     * Example.
     * Working from left to right, we get the following output:
     * 3 4 7 5 6 2 1
     * 3 4 7 5 6 2 1
     * 3 4 5 7 6 2 1
     * 3 4 5 6 7 2 1
     * 2 3 4 5 6 7 1
     * 1 2 3 4 5 6 7
     *
     * Complete the 'insertionSort2' function below.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */
    public final void insertionSort2(int n, List<Integer> arr) {
        // Write your code here
        int j;
        for (int i = 1; i < n; i++) {
            j = i;
            while (j > 0 && arr.get(j) < arr.get(j-1)) {
                // Swap j and j-1
                int temp = arr.get(j);
                arr.set(j, arr.get(j-1));
                arr.set(j-1, temp);
                j--;
            }
            print(arr);
        }
    }

    private void print(List<Integer> arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    /**
     * Merge Sort
     * @param arr
     * @return
     */
    public final int[] mergeSort(final int[] arr) {
        final int[] res = new int[arr.length];
        divideForMerge(arr, res, 0, res.length - 1);
        return res;
    }

    private void divideForMerge(int[] arr, int[] res, int low, int high) {
        if (low >= high) {
            return;
        }
        final int mid = low + (high - low)/2;
        System.out.println("mid: " + mid + ", arr[mid]: " + arr[mid]);
        divideForMerge(arr, res, low, mid);
        divideForMerge(arr, res, mid+1, high);
        merge(arr, res, low, high);
    }

    private void merge(final int[] arr, final int[] res, final int leftStart, final int rightEnd) {

        int size = rightEnd - leftStart + 1;
        int leftEnd = leftStart + (rightEnd - leftStart)/2; // This is actually the mid.
        int rightStart = leftEnd + 1; // mid + 1

        int left = leftStart;
        int right  = rightStart;
        int index = left;

        while (left <= leftEnd && right <= rightEnd) {
            if (arr[left] <= arr[right]) {
                res[index] = arr[left];
                left++;
            } else { // arr[left] > arr[right]
                res[index] = arr[right];
                right++;
            }
            index++;
        }

        // Gather phase:
        // One of the pointers would get over. So either the left remianing will be copied over or the right remaining.
        System.arraycopy(arr, left, res, index, leftEnd - left + 1);
        System.arraycopy(arr, right, res, index, rightEnd - right + 1);
        System.arraycopy(res, leftStart, arr, leftStart, size);
    }

    /**
     * Quicksort
     * https://www.youtube.com/watch?v=SLauY6PpjW4
     *
     * @param arr
     */
    private void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //int pivot = left + (right - left)/2; // Take the middle item as pivot.
        //int index = hoarePartition(arr, left, right, pivot);
        //quickSort(arr, left, index-1);
        //quickSort(arr, index, right);
        int index = lomutoPartitionPivotFirst(arr, left, right);
        quickSort(arr, left, index-1);
        quickSort(arr, index+1, right);

        // Only print subarrays of length >= 2
        if (right - left + 1 >= 2) {
            printSubArray(arr, left, right);
        }
    }
    private int lomutoPartitionPivotFirst(int[] arr, int left, int right) {
        int pivot = arr[left];

        List<Integer> leftPart = new ArrayList<>();
        List<Integer> rightPart = new ArrayList<>();

        // Partition keeping order
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                leftPart.add(arr[i]);
            } else {
                rightPart.add(arr[i]);
            }
        }

        // Rebuild the original array portion
        int index = left;
        for (int num : leftPart) {
            arr[index++] = num;
        }

        int pivotIndex = index;
        arr[index++] = pivot;

        for (int num : rightPart) {
            arr[index++] = num;
        }
        return pivotIndex;
    }

    private int lomutoPartition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j <= right-1; j++) {
            if (arr[j] < pivot) {
                // Swap i and j
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Put pivot in right place.
        // Swap 1+1 with high
        int temp2 = arr[i+1];
        arr[i+1] = arr[right];
        arr[right] = temp2;

        return i+1;
    }

    private int hoarePartition(int[] arr, int left, int right, int pivot) {

        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }

            if (left <= right) {
                // Swap left and right
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                // Progress the left and right indices.
                left++;
                right--;
            }
        }

        return left;
    }

    private void printSubArray(int[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        final Sorting sorting = new Sorting();


        final int[] myArray = new int[]{1, 2, 5, 0, 9, 3};
        System.out.println("Array: " + Arrays.toString(myArray));

        // testing bubble sort
        System.out.println("Bubble Sorted:" + Arrays.toString(sorting.bubbleSort(myArray)));

        // testing insertion sort 2
        final Integer[] myArrIs = new Integer[]{1, 2, 5, 0, 9, 3};
        final List<Integer> myList = Arrays.asList(myArrIs);
        System.out.println("Insertion sort 2....: ");
        sorting.insertionSort2(6, myList);

        System.out.println("Merge Sort....: ");
        // testing merge sort
        final int[] myArrayMs = new int[]{1, 2, 5, 0, 9, 3};
        System.out.println("Merge Sorted: " + Arrays.toString(sorting.mergeSort(myArrayMs)));

        System.out.println("Quick Sort....: ");
        final int[] myArrayQs = new int[]{5, 8, 1, 3, 7, 9, 2};
        sorting.quickSort(myArrayQs);
    }
}
