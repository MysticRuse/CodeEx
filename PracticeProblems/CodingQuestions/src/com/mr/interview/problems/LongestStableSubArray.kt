package com.mr.interview.problems

import java.util.*
import kotlin.collections.ArrayDeque

// Has 2 solutions : Treemap and Deque
/**
 * | Use TreeMap when...        | Use Deques when...              |
 * | -------------------------- | ------------------------------- |
 * | You want **clearer logic** | You want **better performance** |
 * | The input size is small    | The input size is large         |
 * | Built-in sorted structure  | Want control over order         |
 *
 */


/**
 * ✅ 1. Double Deque Approach (Two Monotonic Queues)
 * Time Complexity:  * ➡️ Overall Time: O(n)
 * Every element is added and removed at most once from each deque.
 * Total time for all deque operations is O(n).
 * Each comparison and window adjustment is O(1) per element.
 *
 * Space Complexity: * ➡️ Overall Space: O(n)
 * We maintain two deques, each storing indices of at most n elements (though usually far less in practice).
 *
 * ✅ 2. TreeMap Approach
 * Time Complexity:  * ➡️ Overall Time: O(n log n)
 * For each element:
 * Insert: O(log k) where k is the number of distinct elements in the window.
 * Delete: O(log k)
 * Get max and min: O(1) with firstKey() and lastKey().
 * In the worst case, k = n (e.g., all elements unique).
 *
 * Space Complexity:  * ➡️ Overall Space: O(n) in worst case
 * TreeMap stores frequency count of elements in the window.
 *
 * 📊 Summary Table
 * Approach	Time Complexity	Space Complexity	Notes
 * Double Deque	   O(n)	        O(n)	Optimal, fast in practice
 * TreeMap	       O(n log n)	O(n)	Easier to reason but slower
 */

fun longestSubarray_MultiSet(nums: IntArray, limit: Int): Int {
    val treeMap = TreeMap<Int, Int>()
    var left = 0
    var result = 0

    for (right in nums.indices) {
        val num = nums[right]
        treeMap[num] = treeMap.getOrDefault(num, 0) + 1
        println("Added nums[$right]=$num to TreeMap: $treeMap")

        // Shrink window if max - min > limit
        while (treeMap.lastKey() - treeMap.firstKey() > limit) {
            val leftNum = nums[left]
            treeMap[leftNum] = treeMap[leftNum]!! - 1
            println("Shrinking: diff=${treeMap.lastKey() - treeMap.firstKey()}, removing nums[$left]=$leftNum")
            if (treeMap[leftNum] == 0) {
                treeMap.remove(leftNum)
                println("Removed $leftNum completely from TreeMap")
            }
            left++
            println("New left index: $left")
        }

        val windowSize = right - left + 1
        result = maxOf(result, windowSize)
        println("Current window: nums[$left..$right] → size=$windowSize, max=$result\n")
    }

    return result
}

fun longestSubarray_TwoDeque(nums: IntArray, limit: Int): Int {
    val maxDeque = ArrayDeque<Int>()
    val minDeque = ArrayDeque<Int>()
    var left = 0
    var result = 0

    for (right in nums.indices) {
        val num = nums[right]

        // Maintain decreasing order in maxDeque
        while (maxDeque.isNotEmpty() && nums[maxDeque.last()] < num) {
            val removed = maxDeque.removeLast()
            println("Removed index $removed from maxDeque (value=${nums[removed]})")
        }
        maxDeque.addLast(right)
        println("Added index $right to maxDeque (value=$num): $maxDeque")

        // Maintain increasing order in minDeque
        while (minDeque.isNotEmpty() && nums[minDeque.last()] > num) {
            val removed = minDeque.removeLast()
            println("Removed index $removed from minDeque (value=${nums[removed]})")
        }
        minDeque.addLast(right)
        println("Added index $right to minDeque (value=$num): $minDeque")

        // Shrink the window if invalid
        while (nums[maxDeque.first()] - nums[minDeque.first()] > limit) {
            println("Window invalid: max=${nums[maxDeque.first()]} min=${nums[minDeque.first()]}, diff=${nums[maxDeque.first()] - nums[minDeque.first()]}")
            if (maxDeque.first() == left) {
                println("Popped maxDeque front index ${maxDeque.first()} (value=${nums[maxDeque.first()]})")
                maxDeque.removeFirst()
            }
            if (minDeque.first() == left) {
                println("Popped minDeque front index ${minDeque.first()} (value=${nums[minDeque.first()]})")
                minDeque.removeFirst()
            }
            left++
            println("Incremented left to $left")
        }

        val windowSize = right - left + 1
        result = maxOf(result, windowSize)
        println("Window [left=$left, right=$right] → size=$windowSize, current max result=$result\n")
    }

    return result
}


fun main() {
    val nums = intArrayOf(8, 2, 4, 7)
    val limit = 4
    println("Result with MultiSet: " + longestSubarray_MultiSet(nums, limit))

    println("-------------------Uisng Two Deque--------")
    val nums2 = intArrayOf(10, 1, 2, 4, 7, 2)
    val limit2 = 5
    val result = longestSubarray_TwoDeque(nums, limit2)
    println("Longest stable subarray length with deque: $result")
}
