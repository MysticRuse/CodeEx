package com.mr.interview.problems

fun findQuadSum(targetSum: Int, nums: List<Int>): List<Int> {
    // First create a sum map of pairs.
    // [1, 2, 3, 4, 5]
    // 3 -> [(1,2)],
    // 4 -> [(1,3)],
    // 5 -> [(1,4), (2,3)],
    // 6 -> [(1,5), (2,4)],
    // 7 -> [(2,5), (3,4)] ,
    // 8 -> [(3,5)],
    // 9 -> [(4,5)]

    val sumMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>() // Step 1
    for ( i in 0 until nums.size - 1) { // Step 2: Populate HashMap with sum of all pairs
        for (j in i+1 until nums.size) {
            sumMap.computeIfAbsent(nums[i]+nums[j]) {mutableListOf()}.add(Pair(i, j))
        }
    }

    // Iterate over the sums to find target
    // Eg sum = 7
    // pair1 = (1,2), complement = 7-3 = 4, pair 2 = (1,3) but 1, 2, 1, 3 are not distinct
    // pair1 = (1,3), complement - 7-4 = 3, pair2 = (1,2), but 1, 3, 1, 2 are not distinct
    // Step 3: Iterate over the sums to find target with pairs
    for ((sum, pair1) in sumMap) {
        val complement = targetSum - sum
        val pair2 = sumMap[complement] ?: continue
        for ((a,b) in pair1) {
            for ((c,d) in pair2) {
                if (a != c && a != d && b != c && b != d) {
                    return listOf(nums[a], nums[b], nums[c], nums[d])
                }
            }
        }
    }

    return emptyList()

}

fun main() {
    val numbers = mutableListOf<Int>(1, 2, 3, 4, 8, 13)
    val result = findQuadSum(15, numbers)
    println("Result for target 15 is : $result")

    println("Result for target 10 is : ${findQuadSum(10, numbers)}")
    println("Result for target 5 is : ${findQuadSum(5, numbers)}")
}