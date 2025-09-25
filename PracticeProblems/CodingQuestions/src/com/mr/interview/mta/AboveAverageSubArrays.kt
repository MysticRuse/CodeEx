package com.mr.interview.mta

/**
 * Above-Average Subarrays
 * You are given an array A containing N integers. Your task is to find all subarrays whose average sum is greater than the average sum of the remaining array elements. You must return the start and end index of each subarray in sorted order.
 * A subarray that starts at position L1 and ends at position R1 comes before a subarray that starts at L2 and ends at R2 if L1 < L2, or if L1 = L2 and R1 ≤ R2.
 * Note that we'll define the average sum of an empty array to be 0, and we'll define the indicies of the array (for the purpose of output) to be 1 through N. A subarray that contains a single element will have L1 = R1.
 * Signature
 * Subarray[] aboveAverageSubarrays(int[] A)
 * Input
 * 1 ≤ N ≤ 2,000
 * 1 ≤ A[i] ≤ 1,000,000
 * Output
 * A Subarray is an object with two integer fields, left and right, defining the range that a given subarray covers. Return a list of all above-average subarrays sorted as explained above.
 * Example 1
 * A = [3, 4, 2]
 * output = [[1, 2], [1, 3], [2, 2]]
 * The above-average subarrays are [3, 4], [3, 4, 2], and [4].
 */

data class SubArray(val start: Int, val end: Int)

fun aboveAverageSubArrays(A: IntArray): List<SubArray> {
    val n = A.size
    val totalSum = A.sum()
    val prefix = IntArray(n+1)

    // Compute prefix sum
    for ( i in A.indices) {
        prefix[i+1] = prefix[i] + A[i]
    }

    println("Prefix: ${prefix.joinToString(" ")}")

    val result = mutableListOf<SubArray>()

    for ( start in 0 until n) {
        for (end in start until n) {
            val subSum = prefix[end+1] - prefix[start]
            val count = end + 1 - start
            val subAvg = subSum.toDouble() / count

            val remSum = totalSum - subSum
            val remCount = n - count
            println("remSum: $remSum, remCount = $remCount")
            val remAvg = if (remCount > 0) remSum.toDouble() / remCount else 0.0

            if (subAvg > remAvg) {
                result.add(SubArray(end+1, start+1)) // Use 1-based indexing
            }

        }
    }

    return result

}

fun main() {
    val A = intArrayOf(3, 4, 2)
    val result = aboveAverageSubArrays(A)
    result.forEach {
        println(("[${it.start}, ${it.end}]"))
    }
}