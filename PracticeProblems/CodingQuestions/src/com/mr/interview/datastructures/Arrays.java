package com.mr.interview.datastructures;

import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;

public class Arrays {

    /**
     * Rotate groups of 4 cells.
     * https://leetcode.com/problems/rotate-image/
     * https://leetcode.com/problems/rotate-image/solution/
     * @param matrix
     */
    private int[][] rotate(final int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }

    private int[][] rotateReverseDiagonalReflectLtoR(final int[][] matrix) {
        int[][] resultMatrix = transpose(matrix);
        resultMatrix = reflect(matrix);
        return resultMatrix;
    }

    private int[][] reflect(final int[][] matrix) {
        // Reverse left to right.
        // Traverse only half the columns - hence j < n/2
        final int n = matrix.length;
        for (int i=0; i< n; i++) {
            for (int j = 0; j < n/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }
        return matrix;
    }

    private int[][] transpose(final int[][] matrix) {
        // Reverse along diagonal [i][j] to [j][i]
        final int n = matrix.length;
        for (int i=0; i<n; i++) {
            for (int j = i+1; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }

        return matrix;
    }

    private int[] pairSumSortedArray(final int[] arr, final int targetSum) {
        if (arr == null || arr.length == 0) {
            return new int[] {-1, -1};
        }
        int[] indices = new int[2];
        int start = 0; int end = arr.length - 1;
        int sum;
        while (start < end) {
            sum = arr[start] + arr[end];
            if (sum == targetSum) {
                // got it;
                indices[0] = start;
                indices[1] = end;
                return indices;
            } else if (sum < targetSum) {
                start++;
            } else {
                end--;
            }
        }
        return indices;
    }

    private boolean almostIncreasingSequence(final int[] arr) {
        /**
         * 0. Counter for errors
         * 1. Iterate through sequence.
         *    a. for each value, check its right neighbor.
         *      i. if neighbor is less, check across neighbor
         *      ii. check neighbor with previous value
         *      iii. If both are false return false.
         *    c.
         * 2. more than 1 error, return false.
         */

        final int size = arr.length;
        if (size == 2) {
            return true;
        }

        int errCount = 0;
        for (int pos = 0; pos < size -1; pos++) {
            if (arr[pos+1] <= arr[pos]) {
                errCount++;
                final boolean skipNeighbor = pos + 2 < size && arr[pos+2] <= arr[pos];
                final boolean skipBack = pos - 1 >=0 && arr[pos+1] <= arr[pos-1];

                if (skipNeighbor && skipBack || errCount >=2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ticket numbers usually consist of an even number of digits.
     * A ticket number is considered lucky if the sum of the first half of the digits is equal to the sum of the second half.
     * Given a ticket number n, determine if it's lucky or not.
     * Example
     * For n = 1230, the output should be
     * luckyTicketNumber(n) = true;
     * For n = 239017, the output should be
     * solution(n) = false.
     * @param n the ticket number
     * @return true/false whether ticket is licku number.
     */
    private boolean luckyTicketNumber(final int n) {

        if (n == 0) {
            return false;
        }

        // Given the number, divide by 10 to get quotient and remainder
        // to get the number in the form of an integer array
        int remainder;
        int quotient = n;
        int current;
        final ArrayList<Integer> l = new ArrayList<>();
        while (quotient != 0) {
            current = quotient;
            quotient = current/10;
            remainder = current%10;
            l.add(remainder);
        }

        int start = 0;
        int end = l.size() - 1;
        int fhSum = 0;
        int shSum = 0;

        while (start < end) {
            fhSum += l.get(start);
            shSum += l.get(end);
            start++;
            end--;
        }

        if (fhSum == shSum) {
            return true;
        }
        return false;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Some people are standing in a row in a park. There are trees between them which cannot be moved. Your task is to rearrange the people by their heights in a non-descending order without moving the trees. People can be very tall!
     * Example
     * For a = [-1, 150, 190, 170, -1, -1, 160, 180], the output should be
     * solution(a) = [-1, 150, 160, 170, -1, -1, 180, 190].
     * Input/Output
     * [execution time limit] 3 seconds (java)
     * [input] array.integer a
     * If a[i] = -1, then the ith position is occupied by a tree. Otherwise a[i] is the height of a person standing in the ith position.
     * Guaranteed constraints:
     * 1 ≤ a.length ≤ 1000,
     * -1 ≤ a[i] ≤ 1000.
     * [output] array.integer
     * Sorted array a with all the trees untouched.
     * @param a the array of numbers representing heights of people and trees (1)
     * @return the array sorted by height non-decreasing.
     */
    private int[] sortByHeight(final int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return a;
        }

        final int s = a.length;
        int l = 0;
        int r = s - 1;

        while (l <= r) {
            if (a[l] == -1) {
                l++;
            } else if (a[r] == -1) {
                r--;
            } else {
                insertionSort(a, l, r);
                l++;
            }
        }

        return a;
    }

    private void insertionSort(final int[] a, final int l, final int r) {
        for (int ind = r; ind >=l; ind--) {
            if (a[ind] != -1 && a[ind] < a[l]) {
                swap(a, ind, l);
            }
        }
    }

    private void swap(final int[] a, final int p1, final int p2) {
        int temp = a[p1];
        a[p1] = a[p2];
        a[p2] = temp;

        return;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // MIN number of currency notes and values that sum to amount
    // Given an amount, print a combination of notes of different denominations
    // that sum up to the given amount. For example, the denominations are 1, 5, 10, 20, 100,
    // and the target amount is 148. Then 1x100 + 2x20 + 1x5 +3x1 is a valid combination.
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void findCombinations(int target, final int[] denoms) {
        final int denomSize = denoms.length;
        final int[] denomCount = new int[denomSize];
        int targetAchieved = 0;

        System.out.println("Target: " + target + ", denominations:  "
                + new ArrayList<Integer>() {{ for (int i : denoms) add(i); }});

        // Sort the denominations in descending order so that highest denominations can be utilized
        // and minimum number of denominations will be used.
        // If the denominations are not sorted, the number of denominations used will not be minimal.
        sortDenominations(denoms);

        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < denomSize; i++) {
            if (target > denoms[i]) {
                denomCount[i] = target/denoms[i];
                targetAchieved += denomCount[i] * denoms[i];
                target = target - denomCount[i] * denoms[i];
                sb.append(denomCount[i] + "*" + denoms[i] + " ");
            }
        }

        System.out.println("Combinations: " + sb.toString() + "targetAchieved: " + targetAchieved);
    }

    private static void sortDenominations(int[] denoms) {
        // Insertion sort
        for (int j = 1; j < denoms.length; j++) {
            int current = denoms[j];
            int i = j-1;
            while ((i > -1) && (denoms[i] < current)) {
                denoms[i+1] = denoms[i];
                i--;
            }
            denoms[i+1] = current;
        }

        System.out.println("After sorting denominations: " + new ArrayList<Integer>() {{ for (int i : denoms) add(i); }});
    }


    public static final void main(final String... args) {
        final int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        final Arrays ar = new Arrays();
        final int[][] resultMatrix = ar.rotate(matrix);
    }
}
