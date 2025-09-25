package com.mr.interview.problems

/**
 * Problem: "Find the next smallest palindrome strictly greater than the given number."
 * This is usually solved using:
 * Mirror left to right,
 * If not greater, increment the middle and mirror again.
 * Example
 * Input: 12345 → Output: 12421
 * Input: 999   → Output: 1001
 */
fun nextPalindrome(num: String): String {
    val digits = num.toCharArray()
    val n = digits.size

    // Step 1: Mirror left to right
    val mirrored = digits.copyOf()
    for (i in 0 until n / 2) {
        mirrored[n - i - 1] = mirrored[i]
    }

    val mirroredNum = String(mirrored)
    if (mirroredNum > num) {
        println("Mirrored number $mirroredNum is already greater.")
        return mirroredNum
    }

    // Step 2: Increment the middle
    val incremented = digits.copyOf()
    var mid = (n - 1) / 2
    var carry = 1
    while (mid >= 0 && carry > 0) {
        val newDigit = incremented[mid] - '0' + carry
        incremented[mid] = ((newDigit % 10) + '0'.toInt()).toChar()
        carry = newDigit / 10
        mid--
    }

    // If carry is left, we need to add '1' at beginning and end
    val finalNum = if (carry > 0) {
        val result = CharArray(n + 1)
        result[0] = '1'
        result[n] = '1'
        for (i in 1 until n) {
            result[i] = '0'
        }
        return String(result)
    } else {
        // Mirror left to right again
        for (i in 0 until n / 2) {
            incremented[n - i - 1] = incremented[i]
        }
        return String(incremented)
    }
}

fun main() {
    val input = "12345"
    val result = nextPalindrome(input)
    println("Next palindrome > $input is $result")
}

