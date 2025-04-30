package com.mr.interview.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Arrays;

public class EasyTwoSum {

    /**
     * 2 Sum In An Array
     * Given an array and a target number, find the indices of the two values from the array that sum up to the given target number
     * Example One
     * {
     * "numbers": [5, 3, 10, 45, 1],
     * "target": 6
     * }
     * Output:
     * [0, 4]
     * Sum of the elements at index 0 and 4 is 6.
     * Example Two
     * {
     * "numbers": [4, 1, 5, 0, -1],
     * "target": 10
     * }
     * Output:
     * [-1, -1]
     *
     * @param numbers the search list
     * @param target the target to meet
     * @return indices of the 2 sum pair.
     */
    public ArrayList<Integer> twoSumInAList(ArrayList<Integer> numbers, int target) {

        final HashMap<Integer, Integer> indexMap = new HashMap<>();
        final ArrayList<Integer> result = new ArrayList<>(Arrays.asList(-1, -1));
        final int n = numbers.size();

        for (int i = 0; i<n; i++) {
            final int first = numbers.get(i);
            final int second = target - first;
            // Doing it this way fixes error if there are duplicates
            if (indexMap.containsKey(second)) {
                result.set(0, i);
                result.set(1, indexMap.get(second));
                return result;
            } else {
                indexMap.put(first, i);
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        //final Integer[] arr = new Integer[] {5, 7, 23, 6, 1, 67};

        final ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 5, 7, 23, 6, 1, 67);
        int target = 9;

        final EasyTwoSum twoSum = new EasyTwoSum();
        ArrayList<Integer> result = twoSum.twoSumInAList(list, target);
        System.out.println(" Numbers: " + list + ", target: " + target + ", indices of 2 sum: " + result);

        target = 11;
        result = twoSum.twoSumInAList(list, target);
        System.out.println(" Numbers: " + list + ", target: " + target + ", indices of 2 sum: " + result);
    }
}
