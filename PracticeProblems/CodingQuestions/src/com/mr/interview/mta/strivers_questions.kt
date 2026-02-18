package com.mr.interview.mta

import com.mr.interview.kotlin.printMatrix

public fun MatrixZeroes(grid: Array<IntArray>): Array<IntArray> {

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
            if (dummyRows[r] && dummyRows[c]) {
                grid[r][c] = 0
            }
        }
    }

    return grid
}

fun main() {
    val grid = Array(3) {IntArray(3) { 1 }}
    grid[1][1] = 0
    println("The grid:")
    printMatrix(grid)
    val ans = MatrixZeroes(grid)
    println("The ans:")
    printMatrix(ans)
}