package com.mr.interview.problems;

import java.util.Arrays;

public class CountOccurrencesSortedArr {

    public static int count(int[] numbers, int k) {
        int first = findFirstOccurrence(numbers, k);
        if (first == -1) {
            // Not found.
            return 0;
        }
        int last = findLastOccurrence(numbers, k);

        return last - first + 1;
    }

    private static int findFirstOccurrence(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        int foundIndex = -1;

        while (l <= r) {
            int mid = l + (r-l)/2;
            if (k == arr[mid]) {
                foundIndex = mid;
                r = mid - 1; // Search in the left half
            } else if (k < arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println("Found first occurrence: " + foundIndex);
        return foundIndex;
    }

    private static int findLastOccurrence(int[] arr, int k) {
        int l = 0;
        int r = arr.length -1;
        int foundIndex = -1;

        while (l <= r) {
            int mid = l + (r-l)/2;
            if (arr[mid] == k) {
                foundIndex = mid;
                l = mid + 1; // Search in the right half
            } else if (arr[mid] < k) {
                l = mid + 1; // Search right
            } else {
                r = mid-1; // Search left
            }
        }
        System.out.println("Found last occurrence: " + foundIndex);
        return foundIndex;
    }

    public static final void main(String[] args) {
        final int[] numbers = new int[]{1, 2, 2, 2, 3, 4, 5, 5, 5, 5, 6, 7};
        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Count of 1: "+ count(numbers, 1));
        System.out.println("---------------");
        System.out.println("Count of 2: "+ count(numbers, 2));
        System.out.println("---------------");
        System.out.println("Count of 3: "+ count(numbers, 3));
        System.out.println("---------------");
        System.out.println("Count of 4: "+ count(numbers, 4));
        System.out.println("---------------");
        System.out.println("Count of 5: "+ count(numbers, 5));
        System.out.println("---------------");
        System.out.println("Count of 100: "+ count(numbers, 100));
        System.out.println("---------------");
    }

}
