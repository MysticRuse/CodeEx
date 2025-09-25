package com.mr.interview.problems

/*
Random elements generator
Given an array of elements (words) and an array of frequencies, build a random element generator that respects the given frequencies.
Arrays are given as a histogram, such as
elements = List[A, B, C, D, …]
frequencies = List[1, 2, 1, 3, …]

That is the same as
A:1
B:2
C:1
D:3
..
That means, if the random function is called enough times, B should be returned twice as many times as A, D should be emitted 3 times as many times as A.
*/

import kotlin.io.*;
import kotlin.random.Random

class RandomNumberGenerator {
    val elements= listOf( 'A', 'B', 'C', 'D')
    val freq = listOf(1, 2, 1, 3)
    val countMap = mutableMapOf<Char, Int>()
    // A: (1) -> 0
    // B: (2) -> 0
    // C: (1) -> 0
    // D: (3) -> 0
    var maxNums = 0
    var sumNumsReturned = 0

    init {
        initMap()
    }

    private fun initMap() {
        var i = 0;
        sumNumsReturned = 0
        for (element in elements) {
            countMap[element] = freq[i]
            maxNums += freq[i]
            i++
        }
    }

    fun getRandomElement() : Char {
        // run through the values and i all 0,
        // then init the countMap
        if (sumNumsReturned == maxNums) {
            // Reinit the map.
            println("Finished one round. Reinitialize-----")
            initMap()
        }
        // Select a random element from size of
        val element = elements[Random.nextInt(elements.size)]
        println("----element: $element, countMap : $countMap")
        if (countMap[element] != 0) {
            val count = countMap[element]
            if (count != null) {
                countMap[element] = count - 1
            }
            sumNumsReturned += 1

            return element
        }
        return getRandomElement()
    }

}


fun main(args: Array<String>) {
    val gen = RandomNumberGenerator()
    for (i in 0 until 10) {
        val num = gen.getRandomElement()
        println("${i+1}: $num ")
    }
}