package com.mr.interview.problems;

import java.util.*;

public class UniqueNumbersIn3Arrays {



// Task:
// Write a method/function that takes 3 arrays of numbers as input and returns
// an array containing unique entries of numbers found in all input arrays.
//
// Example:
// Input:
// [5, 7, 8, 11]
// [2, 5, 5, 8]
// [8, 5, 8, 5]
// Output:
// [5, 8]

// [5, 7, 8, 11]
// [2, 5, 5, 8]
// [5, 5, 8, 8]

// Option: hashmap
// - 3 traversals and fill hashMap.
// - return ones which have value == 3
// Option sorting and 3 pointers.
// -

    public static void main(String[] args) {

        //int[] a1 = {5, 7, 8, 11};
        //int[] a2 = {2, 5, 5, 8};
        //int[] a3 = {8, 5, 8, 5};
        //int[] a3 = {1, 1, 1};

        int[] a1 = {1, 5, 7, 8, 9};
        int[] a2 = {1, 5, 78, 9};
        int[] a3 = {1, 1, 1};

        int[] answer =  getUnique(a1, a2, a3);
        System.out.println("Unique numbers: " + Arrays.toString(answer));

    }

    static int[] getUnique(int[] a1, int[] a2, int[] a3) {
        if (a1 == null || a2 == null || a3 == null ||
                a1.length == 0 || a2.length == 0 || a3.length == 0) {
            return null;
        }

        // OLD:
        //Map<Integer, Integer> map = new HashMap<>();

        // WORKS:
        // Maintain a map of the number as key and value as an integer array as big as the number of array
        // to count the occurrence of the number in each array.
        Map<Integer, int[]> map = new HashMap<>();
        // Pass the arr number for each array.
        // Each value array in the HashMap will be an array of size = number of arrays, here 3.
        fillMap(map, a1, 0);
        fillMap(map, a2, 1);
        fillMap(map, a3, 2);
        Set<Integer> results = new HashSet<>();

        for (Map.Entry<Integer, int[]> entry: map.entrySet()) {
            int key = entry.getKey();
            int[] values = entry.getValue();
            System.out.println("Map: key " + key + ", val: " + Arrays.toString(values));
            boolean allThree = true;
            for (int count: values) {
                allThree = allThree && count >= 1;
            }
            if (allThree) {
                results.add(key);
            }
        }

        int size = results.size();
        System.out.println("Result size: " + size);
        int[] resultArr = new int[size];
        int index = 0;
        for (int result: results) {
            resultArr[index++] = result;
        }

        // Clear the map else it may lead to memory leak - being held in static functions:
        map.clear();

        return resultArr;
    }

    static void fillMap(Map<Integer, int[]> map, int[] arr, int arrNumber) {
        for (int num: arr) {
            // OLD:
//            if (map.containsKey(num) && map.get(num) < arrCounts) {
//                map.put(num, map.get(num)+ 1));
//            } else {
//                map.put(num, 1);
//            }

            // WORKS:
            int[] counts;
            if (map.containsKey(num)) {
                counts = map.get(num);
                counts[arrNumber] += 1;
            } else {
                // Allocate a count array of size 3 and add 1 to the arrNumber index of the counts array.
                counts = new int[3];
                counts[arrNumber] = 1;
                map.put(num, counts);
            }
        }
        System.out.println("Map size: " + map.size());
    }

}
