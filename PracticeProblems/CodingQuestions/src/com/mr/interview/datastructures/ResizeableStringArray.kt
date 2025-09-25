package com.mr.interview.datastructures

class ResizeableStringArray(initialCapacity: Int = 3) {

    private var items: Array<String?> = arrayOfNulls(initialCapacity)
    private var count: Int = 0

    fun size() : Int{
        return count
    }

    fun get(index: Int): String {
        if (index == count) {
            throw IllegalArgumentException("Index out of bounds")

        }
        return items[index]!!
    }

    fun set(value: String, index: Int) {
        println("set: index: $index, value: $value, count: $count, items size: ${items.size}")
        if (index >= count) {
            throw IllegalArgumentException("Index to set value is out of bounds")
        }

        items[index] = value
    }

    fun append(value: String) {
        // Set the value and return the index
        if (count >= items.size) {
            resize()
        }
        items[count] = value
        count++
    }

    private fun resize() {
        val newCapacity = items.size * 2
        println("Resized to new capacity: $newCapacity")
        val biggerArray = arrayOfNulls<String>(newCapacity)
        for (i in 0 until items.size) {
            biggerArray[i] = items[i]
        }
        items = biggerArray
    }

    fun printArray() {
        println("Count: ${size()}, array: ${toString()}")
    }

    override fun toString(): String {
        return (0 until count).joinToString(", ", "[", "]") { get(it) }
    }
}

public fun main() {
    val myArray = ResizeableStringArray(4)
    // Has to call append to first create the array before calling set to index to modify value in index.
    myArray.append("A")
    myArray.append("B")
    myArray.append("C")
    myArray.printArray()

    myArray.append("D")
    myArray.append("E")
    myArray.printArray()

    myArray.set("Zero", 0)
    myArray.set("One", 1)
    myArray.set("Two", 2)
    myArray.printArray()
}