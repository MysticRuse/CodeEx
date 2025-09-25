package com.mr.interview.problems

fun generateBingoCard() : Array<Array<String>> {

    val card = Array(5) {Array(5) {""} }
    val ranges = arrayOf(
        1..15,  // B
        16..30, // I
        31..45, // N
        46..60, // G
        61..75  // O
    )

    for (col in 0 until 5) {
        val numbers = ranges[col].shuffled().take(5).toMutableList()
        for (row in 0 until 5) {
            card[row][col] = numbers[row].toString()
        }
    }

    // Set the center as FREE
    card[2][2] = "FREE"

    return card
}

fun printBingoCard(card: Array<Array<String>>) {
    println("B  I  N  G  O")
    for (row in 0 until 5) {
        for (col in 0 until 5) {
            val value = card[row][col]
            print(String.format("%-4s", value))
        }
        println()
    }
}

fun main() {
    val card = generateBingoCard()
    printBingoCard(card)
}