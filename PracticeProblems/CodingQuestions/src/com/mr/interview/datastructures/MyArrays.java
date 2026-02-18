package com.mr.interview.datastructures;

import java.util.ArrayList;
import java.util.Stack;

public class MyArrays {

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
    public static void findCoinChangeCombinations(int target, final int[] denoms) {
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

             /* Move elements of arr[0..i-1], that are
            greater than key, to one position ahead
            of their current position */
            while ((i > -1) && (denoms[i] < current)) {
                denoms[i+1] = denoms[i];
                i--;
            }
            denoms[i+1] = current;
        }

        System.out.println("After sorting denominations: " + new ArrayList<Integer>() {{ for (int i : denoms) add(i); }});
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Are similar arrays
    // Two arrays are called similar if one can be obtained from another by swapping at most one pair of elements in one of the arrays.
    //Given two arrays a and b, check whether they are similar.
    //Example
    //For a = [1, 2, 3] and b = [1, 2, 3], the output should be
    //solution(a, b) = true.
    //The arrays are equal, no need to swap any elements.
    //For a = [1, 2, 3] and b = [2, 1, 3], the output should be
    //solution(a, b) = true.
    //We can obtain b from a by swapping 2 and 1 in b.
    //For a = [1, 2, 2] and b = [2, 1, 1], the output should be
    //solution(a, b) = false.
    //Any swap of any two elements either in a or in b won't make a and b equal.
    ////////////////////////////////////////////////////////////////////////////////////////////////
    boolean areSimilarArrays(int[] a, int[] b) {

        // They have to be the same length
        if (a.length != b.length) {
            return false;
        }

        // Compare each element per index;
        // if there is a mismatch
        //    store the mismatched index in stack
        //    increment counter of diff
        //    if another mismatch occurs use stack index to compare if it can be swapped.

        final Stack<Integer> indStack = new Stack<>();
        int diff = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                if (indStack.isEmpty()) {
                    indStack.add(i);
                    diff++;
                } else if (a[i] == b[indStack.peek()] && b[i] == a[indStack.peek()]) {
                    indStack.pop();
                }
                if (diff >= 2) {
                    return false;
                }
            }
        }

        return indStack.empty();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Array change to increasing sequence.
    // You are given an array of integers. On each move you are allowed to increase exactly one of its element by one.
    // Find the minimal number of moves required to obtain a strictly increasing sequence from the input.
    //Example
    //For inputArray = [1, 1, 1], the output should be
    //solution(inputArray) = 3.
    ///////////////////////////////////////////////////////////////////////////////////////////////
    static int arrayChange(int[] inputArray) {

        final int len = inputArray.length;

        int moves = 0;
        for (int i=1; i < len; i++) {
            if (inputArray[i] <= inputArray[i-1]) {
                moves += inputArray[i-1] + 1 - inputArray[i];
                inputArray[i] = inputArray[i-1] + 1;
                System.out.println("i: " + i + ", moves: " + moves + ", i val: " + inputArray[i]);
            }
        }
        return moves;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Avoid obstacles
    //You are given an array of integers representing coordinates of obstacles situated on a straight line.
    // Assume that you are jumping from the point with coordinate 0 to the right. You are allowed only to make
    // jumps of the same length represented by some integer.
    // Find the minimal length of the jump enough to avoid all the obstacles.
    // For inputArray = [5, 3, 6, 7, 9], the output should be
    // solution(inputArray) = 4
    // 0, 1, 2, (3), 4, (5), (6), (7), 8, (9)
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private static int avoidObstacles(final int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }

        int mod = 2;
        int val;
        for(int i = 0; i < inputArray.length; i++) {
            val = inputArray[i] % mod;
            if (val == 0) {
                mod++;
                i = -1;
            }
        }

        return mod;

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Blur box
    //so you want to apply the box blur algorithm to the photo to hide its content.
    //The pixels in the input image are represented as integers. The algorithm distorts the input
    // in the following way: Every pixel x in the output image has a value equal to the average value
    // of the pixel values from the 3 × 3 square that has its center at x, including x itself.
    // All the pixels on the border of x are then removed.
    //Return the blurred image as an integer, with the fractions rounded down.
    //Example
    //image = [[1, 1, 1],
    //         [1, 7, 1],
    //         [1, 1, 1]]
    //the output should be solution(image) = [[1]].
    //To get the value of the middle pixel in the input 3 × 3 square: (1 + 1 + 1 + 1 + 7 + 1 + 1 + 1 + 1) = 15 / 9 = 1.66666 = 1.
    //The border pixels are cropped from the final result.
    //image = [[7, 4, 0, 1],
    //         [5, 6, 2, 2],
    //         [6, 10, 7, 8],
    //         [1, 4, 2, 0]]
    //the output should be
    //solution(image) = [[5, 4],
    //                  [4, 4]]
    //There are four 3 × 3 squares in the input image, so there should be four integers in the blurred output.
    // To get the first value: (7 + 4 + 0 + 5 + 6 + 2 + 6 + 10 + 7) = 47 / 9 = 5.2222 = 5. The other three integers are obtained
    // the same way, then the surrounding integers are cropped from the final result.
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static int[][] boxBlur(final int[][] image) {

        // Iterate through each square not on the border.
        // Get average of each 3x3 area in the iamge including the square centre you are on.
        // return the image.
        final int rows = image.length;
        final int columns = image[0].length;

        // minus 2 as the topand bottom rows and lefta nd right columns will be left out
        // - i.e borders left out
        final int[][] blur = new int[rows-2][columns - 2];

        // Start with the 1st middle pixel and continue
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < columns - 1 ; c++) {
                // indices in blur should start with 0,0.
                blur[r-1][c-1] = getAverage(image, r, c);
            }
        }

        return blur;
    }

    public static int getAverage(final int[][] image, final int r, final int c) {
    /*
        The positions of the 9 entries in 3x3
        ul -1, -1
        um -1, 0
        ur -1, 1
        ml 0, -1
        mm 0, 0
        mr 0, 1
        dl 1, -1
        dm 1, 0
        dr 1, 1
    */
        return (image[r-1][c-1] + image[r-1][c] + image[r-1][c+1]
                + image[r][c-1] + image[r][c] + image[r][c+1]
                + image[r+1][c-1] + image[r+1][c] + image[r+1][c+1]) / 9;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // MineSweeper
    // In the popular Minesweeper game you have a board with some mines and those cells that don't contain a mine have a number in it that indicates the total number of mines in the neighboring cells. Starting off with some arrangement of mines we want to create a Minesweeper game setup.
    //Example
    //matrix = [[true, false, false],
    //          [false, true, false],
    //          [false, false, false]]
    //the output should be
    //solution(matrix) = [[1, 2, 1],
    //                       [2, 1, 1],
    //                       [1, 1, 1]]
    ///////////////////////////////////////////////////////////////////////////////////////////////
    int[][] mineSweeper(boolean[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;

        final int[][] mMatrix = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int  c = 0; c < cols; c++) {
                mMatrix[r][c] = findNumMines(matrix, r, c);
            }
        }

        return mMatrix;
    }

    int findNumMines(boolean[][] matrix, int rw, int cl) {
    /*
        ul  -1, -1
        um  -1, 0
        ur  -1, 1
        ml  0, -1
        mr  0, 1
        dl  1, -1
        dm  1, 0
        dr. 1, 1
    */

        final int rows = matrix.length;
        final int cols = matrix[0].length;
        int mines = 0;
        final int u = rw - 1 >= 0 ? rw-1 : 0;
        final int d = rw + 1 <= rows - 1 ? rw + 1 : rows - 1;
        final int l = cl - 1 >= 0 ? cl - 1 : 0;
        final int r = cl + 1 <= cols - 1? cl + 1 : cols - 1;

        for (int i = u; i <= d; i++) {
            for (int j = l; j <= r; j++) {
                if (matrix[i][j] && (!( i == rw && j == cl))) {
                    mines++;
                }
            }
        }

        return mines;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


    public static final void main(final String... args) {
        final int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        final MyArrays ar = new MyArrays();
        final int[][] resultMatrix = ar.rotate(matrix);

        final int[] arr = {1, 1, 1};
        arrayChange(arr);

        final int[] arr1 = {5, 3, 6, 7, 9};
        System.out.println("Avoid obstacles jump for [5, 3, 6, 7, 9]: " + avoidObstacles(arr1));
        final int[] arr2 = {1, 4, 10, 6, 2};
        System.out.println("Avoid obstacles jump for [1, 4, 10, 6, 2]: " + avoidObstacles(arr2));

        final int[][] image = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Box blur for {{1,2,3},{4,5,6},{7,8,9}}: ");
        printMatrix(boxBlur(image));

        final int[][] image2 = {{1,2,3,4},{4,5,6,7},{7,8,9,10}, {1,4,6,8}};
        System.out.println("Box blur for {{1,2,3,4},{4,5,6,7},{7,8,9,10}, {1,4,6,8}}: ");
        printMatrix(boxBlur(image2));
    }

    private static void printMatrix(final int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            System.out.print("[");
            for (int c = 0; c < arr[0].length; c++) {
                System.out.print(arr[r][c] + ",");
            }
            System.out.println("]");
        }
    }
}
