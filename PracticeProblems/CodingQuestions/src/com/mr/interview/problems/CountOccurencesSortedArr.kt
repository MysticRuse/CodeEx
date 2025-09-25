package com.mr.interview.problems

class CountOccurencesSortedArr {

    fun searchRange(nums: IntArray?, target: Int): IntArray {
        // Binary search - would return 1 value

        // if nums[mid] == target
        // 2 binary searches
        // [5, 7, 7, 8, 8, 8, 8, 8, 10, 10, 10]

        val firstOccurrence = this.findBound(nums!!, target, true)
        if (firstOccurrence == -1) {
            return intArrayOf(-1, -1)
        }

        val lastOccurrence = this.findBound(nums, target, false)
        return intArrayOf(firstOccurrence, lastOccurrence)
    }

    private fun findBound(nums: IntArray, target: Int, isFirst: Boolean): Int {
        var mid: Int
        var l = 0
        var r = nums.size - 1

        while (l <= r) {
            mid = l + (r - l) / 2
            if (nums[mid] == target) {
                if (isFirst) {
                    // We found our lower bound
                    if (mid == l || nums[mid - 1] != target) {
                        println("Found lower bound: $mid")
                        return mid
                    }
                    // Search for bound on left side.
                    r = mid - 1
                } else {
                    if (mid == r || nums[mid + 1] != target) {
                        // We have found our upper bound
                        println("Found upper bound: $mid")
                        return mid
                    }

                    // Search for bound on the right side
                    l = mid + 1
                }
            } else if (nums[mid] > target) {
                // search in left of mid.
                r = mid - 1
            } else {
                // search in right
                l = mid + 1
            }
        }

        println("Did not find target")
        return -1
    }
}

fun main() {
    val numbers = intArrayOf(1, 2, 2, 2, 3, 4, 5, 5, 5, 5, 6, 7)
    val searchRangeInArr = CountOccurencesSortedArr();
    println("Numbers: ${numbers.joinToString(" ")}")
    println("Range of 1: [${searchRangeInArr.searchRange(numbers, 1).joinToString(" ")}]")
    println("---------------")
    println("Range of 2: [${searchRangeInArr.searchRange(numbers, 2).joinToString(" ")}]")
    println("---------------")
    println("Range of 3: [${searchRangeInArr.searchRange(numbers, 3).joinToString(" ")}]")
    println("---------------")
    println("Range of 4: [${searchRangeInArr.searchRange(numbers, 4).joinToString(" ")}]")
    println("---------------")
    println("Range of 5: [${searchRangeInArr.searchRange(numbers, 5).joinToString(" ")}]")
    println("---------------")
    println("Range of 100: [${searchRangeInArr.searchRange(numbers, 100).joinToString(" ")}]")
    println("---------------")
}