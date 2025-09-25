package com.mr.interview.kotlin

/**
 * 1. Given a mxn integer matrix, if an element is 0
 * set the entire row and column to → 0.
 * In-place
 */
fun matrixZeroes(grid: Array<IntArray>): Array<IntArray> {

    val rows = grid.size
    val cols = grid[0].size
    val dummyCols = Array<Boolean>(cols) { false }
    val dummyRows = Array<Boolean>(rows) { false }

    for (col in 0 until cols) {
        for (row in 0 until rows) {
            if (grid[row][col] == 0) {
                dummyCols[col] = true
            }
        }
    }
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            if (grid[row][col] == 0) {
                dummyRows[row] = true
            }
        }
    }
    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (dummyRows[r]) {
                grid[r][c] = 0
            }
            if (dummyCols[c]) {
                grid[r][c] = 0
            }
        }
    }
    return grid
}

/**
 * 2. Given an integer numRows, return the first numRows of a Pascals Triangle..
 * num Rows = 5
 *      [1]
 *    [1, 1]
 *  [1, 2, 1]
 * [1, 3, 3 1]
 *[1 4, 6, 4, 1]
 */
fun pascalsTriangle(numRows: Int): Array<Array<Int>> {
    val triangle = Array(numRows) { Array<Int>(numRows) {0}}
    // Set the triangle border to 1's
    for (i in 0 until numRows) {
        triangle[i][0] = 1
        triangle[i][i] = 1

        // Now do the additions with previous rows
        for (j in 1 until i) {
            triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]
        }
        println(triangle[i].toList())
    }
    return triangle
}

fun nextPermutation(nums: IntArray): IntArray {

    // Step 1: Traverse from end and find nums[i] < nums[i+1] - index p
    var p = 0
    for (i in nums.size - 2 downTo 0) {
        if (nums[i] > nums [i+1]) {
            p = i
            break
        }
    }

    // Step 2: Traverse from end and find the first num[i] > first number ie nums[0] - index q
    var q = 0
    for (j in nums.size - 1 downTo 0) {
        if (nums[j] > nums[0]) {
            q = j
            break
        }
    }

    // Step3: Swap p and q
    val temp = nums[p]
    nums[p] = nums[q]
    nums[q] = temp

    println("p: $p, q: $q")

    // Reverse (p+1 to end)
    var l = p + 1
    var r = nums.size - 1
    while(l <= r) {
        val temp = nums[l]
        nums[l] = nums[r]
        nums[r] = temp
        l++
        r--
    }
    println(nums.toList())
    return nums
}

/**
 * Maximum Subamay [kadane's Algonithm] :.
 * Given and integer array, find the contiguous subarray which his the array nums.
 */
fun maximumSubArraySum(nums: IntArray): Int {
    var maximumSum = Int.MIN_VALUE
    var currSum = 0
    for (i in nums.indices) {
        if (currSum < 0) {
            currSum = 0
        }
        currSum += nums[i]
        maximumSum = maxOf(maximumSum, currSum)
        println("currSum: $currSum, maxSum: $maximumSum")
    }
    return maximumSum
}

fun sortColors(colors: IntArray) {
    println("Input Colors: ${colors.toList()}")
    var left = 0
    var mid = 0
    var right = colors.size - 1

    while(left <= right) {
        if (colors[mid] == 0) {
            // Swap left and mid
            swap(colors, mid, left)
            left++
            mid++
        } else if (colors[mid] == 1) {
            mid++
        } else if (colors[mid] == 2) {
            // Swap with right
            swap(colors, mid, right)
            right--
        }
    }
    println("Sorted Colors: ${colors.toList()}")
}
private fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun mergeIntervals(intervals: Array<IntArray>) : Array<IntArray> {
    intervals.sortWith(compareBy( {it[0]}))
    val merged = mutableListOf<IntArray>()
    merged.add(intervals[0])

    for (interval in intervals) {
        val start = interval[0]
        val end = interval[1]
        val lastEnd = merged.last()[1]
        if (start < lastEnd) {
            // Extend
            merged.last()[1] = maxOf(end, lastEnd)
            println("merged: ${merged.last().toList()}")
        } else {
            merged.add(interval)
            println("added: ${interval.toList()}")
        }
    }

    return merged.toTypedArray()
}

fun main() {
    //testMatrixZeros()
    //testPascalsTriangle()
    //testNextPermutation()
    //testMaximumSubArrayKadane()
    //testSortColors()
    testMergeIntervals()
}

fun testMergeIntervals() {
    val intervals = Array(3){ IntArray(2)}
    intervals[0] = intArrayOf(1, 3)
    intervals[1] = intArrayOf(2, 6)
    intervals[2] = intArrayOf(8,10)
    println(mergeIntervals(intervals).toList())
}

fun testSortColors() {
    sortColors(intArrayOf(2, 0, 2, 1, 0, 1))
}

fun testMaximumSubArrayKadane() {
    var sum = maximumSubArraySum(intArrayOf(2, -3, 1, 4, -2, 5, 1))
    println("Maximum subarray sum kadane's: $sum")
    sum = maximumSubArraySum(intArrayOf(-2, 1,-3, 4, -1, 2, 1,-5, 4))
    println("Maximum subarray sum kadane's: $sum")
}

fun testNextPermutation() {
    nextPermutation(intArrayOf(1, 2, 3))
}

fun testPascalsTriangle() {
    var size = 0
    pascalsTriangle(size)
    size = 5
    println("Pascal's triangle of size:  $size")
    pascalsTriangle(size)
}

fun testMatrixZeros() {
    val size = 3
    val grid = Array(size) {IntArray(size) { 1 }}
    grid[size/2][size-1] = 0
    println("Input:...... ")
    printMatrix(grid)
    val ans = matrixZeroes(grid)
    println("Zeroed:...... ")
    printMatrix(ans)
}

fun printMatrix(matrix: Array<IntArray>) {
    for (element in matrix) {
        for (c in 0 until matrix[0].size) {
            print((" " + element[c]))
        }
        println()
    }
}
