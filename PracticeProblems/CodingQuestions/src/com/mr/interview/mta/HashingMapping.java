package com.mr.interview.mta;

import java.util.HashSet;
import java.util.Set;

public class HashingMapping {
    /**
     * 123. Colorful Numbers
     * Objective: Given a number, find out whether it is colorful.
     * Colorful Number: When in a given number, the product of every contiguous sub-sequence is different. That number is called a Colorful Number.
     * Example 1:
     * Given Number : 3245
     * Output: Colorful
     * Number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
     * this number is a colorful number, since product of every digit of a sub-sequence are different.
     * That is, 3 2 4 5 (3*2)=6 (2*4)=8 (4*5)=20, (3*2*4)= 24 (2*4*5)= 40
     * Example 2:
     * Given Number : 326
     * Output: Not Colorful.
     * 326 is not a colorful number as it generates 3 2 6 (3*2)=6 (2*6)=12.
     */

    public boolean isColorful(int number) {
        // Initialize a Set
        final Set<Integer> set = new HashSet<>();
        // Get length of number
        final String numberStr = String.valueOf(number);
        final int len = numberStr.length();

        // Start with 2 loops.
        // Loop 1: Start point of the sub array.
        // Loop 2: Length of the sub array.
        // Get the product and check if subarray product present in set.
        // if set, not a colorful number

        for (int i = 0; i < len; i++) {
            int product = 1;
            for (int j = i; j < len; j++) {
                product *= numberStr.charAt(j) - '0';
                if (set.contains(product)) {
                    System.out.println("Set already contains: " + product);
                    return false;
                }
                System.out.println("Adding to set: " + product);
                set.add(product);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        HashingMapping solution = new HashingMapping();

        System.out.println("---326 Colorful - " + solution.isColorful(326));
        System.out.println("---3245 Colorful - " + solution.isColorful(3245));
        System.out.println("---32458 Colorful - " + solution.isColorful(32458));

    }
}
