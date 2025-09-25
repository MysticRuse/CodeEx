package com.mr.interview.mta


/**
 * Average Time: O(n log n)
 * Worst Time: O(n²) if pivot choice is poor (e.g., already sorted input)
 * Space: O(n) for extra lists (less, equal, greater)
 */
fun quickSort(arr: ArrayList<Int>): ArrayList<Int> {
    if (arr.size <=1) return arr

    val pivot = arr[arr.size - 1]
    val less  = ArrayList<Int>()
    val equal  = ArrayList<Int>()
    val more  = ArrayList<Int>()

    for (num in arr) {
        when {
            num < pivot -> less.add(num)
            num == pivot -> equal.add(num)
            else -> more.add(num)
        }
    }

    val sorted = ArrayList<Int>()
    sorted.addAll(quickSort(less))
    sorted.addAll(equal)
    sorted.addAll(quickSort(more))

    return sorted
}

/**
 * Time & Space Complexity
 * Average Time: O(n log n)
 * Worst Time: O(n²) (e.g., sorted/reversed input without randomized pivot)
 * Space: O(log n) recursion stack (in-place sorting, no extra arrays)
 */
fun quickSortInPlace(arr: ArrayList<Int>): ArrayList<Int> {
    quickSortDoInPlace(arr, 0, arr.size - 1)
    return arr
}

fun quickSortDoInPlace(arr: ArrayList<Int>, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(arr, low, high)
        quickSortDoInPlace(arr, low, pivotIndex - 1)
        quickSortDoInPlace(arr, pivotIndex + 1, high)
    }
}

private fun partition(arr: ArrayList<Int>, low: Int, high: Int): Int {
    val pivot= arr[high]

    var i = low - 1

    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            arr.swap(i, j)
        }
    }
    arr.swap(i+1, high)
    return i+1
}

private fun ArrayList<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

/**
 * The original list is : [0.4, 0.5, 11.2, 8.4, 10.4]
 * The index of element just greater than 0.6 : 2
 */
fun firstElementGreaterThanK(nums: DoubleArray, k: Double): Int {
    var result = -1
    for (i in 0 until nums.size) {
        println("k: $k, nums[i]: ${nums[i]}")
        if (nums[i] > k) {
            result = i
            break
        }
    }
    return result
}

fun main() {

    val arr = arrayListOf(9, 3, 7, 1, 5, 4, 8, 2, 6)
    var sorted = quickSort(arr)
    println("Array to be sorted: $arr")
    println("Quick sorted not in-place: $sorted") // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]

    sorted = quickSortInPlace(arr)
    println("Quick sorted in-place: $sorted") // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]

    val floatArray = doubleArrayOf(0.4, 0.5, 11.2, 8.4, 10.4)
    println("Array to be find first greater than K: $floatArray")
    print(firstElementGreaterThanK(floatArray, 0.6))


}