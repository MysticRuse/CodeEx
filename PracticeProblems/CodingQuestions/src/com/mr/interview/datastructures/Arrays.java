package com.mr.interview.datastructures;

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


    public static final void main(final String... args) {
        final int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        final Arrays ar = new Arrays();
        final int[][] resultMatrix = ar.rotate(matrix);
    }
}
