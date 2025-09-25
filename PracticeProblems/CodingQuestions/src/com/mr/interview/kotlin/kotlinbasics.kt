package com.mr.interview.kotlin

import com.mr.interview.datastructures.Strings
import java.io.File
import javax.sql.DataSource

// source: https://github.com/alidehkhodaei/kotlin-cheat-sheet?tab=readme-ov-file

fun datatypes() : Unit {
    var booleanVar: Boolean = true
    var byteVar: Byte = 127                    // Occupies 1 byte = 8 bits in memory (-128 to 127)
    val shortVar: Short = 32767                // Occupies 2 bytes = 16 bits in memory (-32,768 to 32,767)
    val intVar: Int = 2147483647               // 4 bytes = 32 bits
    val longVar: Long = 9223372036854775807L   // 8 bytes = 64 bits
    val floatVar: Float = 3.14f
    val doubleVar: Double = 3.14159265358979323846
    val charVar: Char = 'A'
    val stringVar: String = "Hello, world!"

    println("----DataTypes----")
    println("booleanVar: $booleanVar")
    println("byteVar: $byteVar")
    println("shortVar: $shortVar")
    println("intVar: $intVar")

    println("longVar: $longVar")
    println("floatVar: $floatVar")
    println("doubleVar: $doubleVar")
    println("charVar: $charVar")
    println("stringVar: $stringVar")
}

fun typeConversion() : Unit {
    println("----TypeConversion----")
    val str: String = "123"
    val num: Int = str.toInt() // Convert String to Int
    println("String $str to Int $num")

    val dbl: Double = 123.45
    val int: Int = dbl.toInt() // Convert Double to Int
    println("Double $dbl to Int $int")

    val lng: Long = 9867543210
    val flt: Float = lng.toFloat() // Convert Long to Float
    println("Long $lng to float $flt")

    val bol: Boolean = true
    val strBol: String = bol.toString() // Convert Boolean to String
    println("Boolean $bol to String $strBol")

    val char: Char = 'A'
    val intChar: Int = char.toInt() // Convert Char to Int // Conversion of Char to Number is deprecated. Use Char.code property instead.
    val intCharNew: Int = char.code;

    val byte: Byte = 127
    val short: Short = byte.toShort() // Convert Byte to Short
    println("Byte $byte to Short $short")

    // String templates:
    val stringTemplateIntChar = "StringTemplate: Code of $char is $intCharNew"
    println(stringTemplateIntChar)
}

fun aboutOperatorsAndTypes() {

    // 1. Explore numeric operators
    println(1+1)
    println(53-1)
    println(1.0/2)
    println(1/2)
    println(2.0 * 5)
    println(2.times(5))
    println(3.5.plus(4))
    println(2.4.div(2))
    println(2 * 4.div(2.5))

    println("Comparison Operators")
    val c = 10
    val d = 5
    println(c > d) // true
    println(c >= d) // true
    println(c < d) // false
    println(c <= d) // false
    println(c == d) // false
    println(c != d) // true

    println("Assignment operators")
    var h = 10
    h += 5
    println(h) // 15
    h -= 5
    println(h) // 10
    h *= 2
    println(h) // 20
    h /= 4
    println(h) // 5
    h %= 3.0.toInt()
    println(h) // 1

    println("Logical Operators")
    val a = true
    val b = false
    println("a = $a, b = $b")
    println("a && b: ${a && b}") // false
    println("a || b: ${a || b}") // true
    println("!a: ${!a}") // false

    println("Bitwise Operators")
    val k = 0b1010
    val l = 0b1100
    println("k = $k, l = $l")
    println("k and l: ${k and l}") // Prints "8" (0b1000)
    println("k or l: ${k or l}") // Prints "14" (0b1110)
    println("k xor l: ${k xor l}" ) // Prints "6" (0b0110)

    println("Range Operators x..y ( x and y included)")
    val o: LongRange = 1L..10L  // similar IntRange: = 1..100, CharRange: = 'a'..'z'
    println("Range: $o")
    println("o.contains(5): ${o.contains(5)}") // Prints "true"
    println("o.contains(11): ${o.contains(11)}") // Prints "false"
    println("o.contains(-1): ${o.contains(-1)}") // Prints "false"

    println("Range Operators rangeTo() function with explicit type spec")
    val intRange: IntRange = 1.rangeTo(10)
    val charRange: CharRange = 'a'.rangeTo('z')
    val longRange: LongRange = 1L.rangeTo(100L)
    println("intRange.contains(5): ${intRange.contains(5)}") // Prints "true"
    println("charRange.contains('a'): ${charRange.contains('a')}") // Prints "false"
    println("longRange.contains(-1L): ${longRange.contains(-1L)}") // Prints "false"

    println("Operators downTo() reverse ranges : type IntProgression")
    val reverseIntProgression: IntProgression = 9.downTo(1)
    println("reverseIntProgression 9.downTo(1): $reverseIntProgression")
    for (i in reverseIntProgression) print(i)
    println()

    println("Operators .. with step : type IntProgression")
    val intProgressionWithStep2: IntProgression = 0..8 step 2
    println("intProgressionWithStep2 0..8 with step 2: $intProgressionWithStep2")
    for (i in intProgressionWithStep2) print(i)
    println()

    println("Using types")
    // 2. Using types
    val i: Int = 6
    val b1 = i.toByte()
    println(b1)

    // 3. Assigning Byte value to different variable types
    val b2: Byte = 1
    //val i1: Int = b2 <----
    //error: type mismatch: inferred type is Byte but Int was expected
    //val i1: Int = b2

    // 4. Try casting if assignments return error
    val i4: Int = b2.toInt()
    println(i4) // returns 1 as an Int

    val i5: String = b2.toString()
    println(i5) // returns 1 as  String

    val i6: Double = b2.toDouble()
    println(i6) // Returns 1:0

    // 5. Kotlin allows placing underscore in long numbers to make it more readable.
    val oneMillion = 1_000_000
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_011010001_10010100_100010010

    // 6. Strings and characters : String template
    println("$oneMillion, $socialSecurityNumber, $hexBytes, $bytes")

    val numberOfFish = 5
    val numberOfPlants = 12
    println("I have $numberOfFish fish and $numberOfPlants plants")
    println("I have ${numberOfFish + numberOfPlants} fish and plants")

    // 7. Variable types:
    //   - val (immutable/unchangeable - assign only once)
    //   - var(mutable, changeable = can reassign)

}

fun maps() {
    val myMap = mutableMapOf("apple" to 1, "banana" to 2, "cherry" to 3)

    val filteredMap = myMap.filter{(key, value) -> key.endsWith("1") && value > 0}
    println(filteredMap)

    // Create a list of strings
    val strs = mutableListOf("eat", "tea", "tan","ate","nat","bat")
    val map = mutableMapOf<String, MutableList<String>>()
    for (word in strs) {
        //val sortedWord = word.toCharArray().sorted().joinToString { "" }

        val wordArray = word.toCharArray()
        wordArray.sort()
        val sortedWord = wordArray.joinToString("")

        //map.getOrPut(sortedWord) { mutableListOf() }.add(word)

        val listForKey = map.getOrPut(sortedWord) {
            mutableListOf<String>()
        }
        listForKey.add(word)
    }
    println(map.values.toList())
}

fun nullSafety() {
    // https://www.baeldung.com/kotlin/null-safety
    // https://kotlinlang.org/docs/null-safety.html
    // Non- Nullable. Cannot assign null to a
    var a: String = "Value"
    println("Non-nullable a: $a")
    // Cannot assign null to a. Null cannot be assigned to a non-null String
    //a = null
    a = null.toString()
    println("Non-nullable toString: $a")

    // Nullable String
    var b: String? = "Nullable Value"
    println("Nullable b: $b")
    b = null
    println("Nulled b: $b")

    // .let()

    // Elvis operator
}

fun extensionFunctions() {
    // https://kotlinlang.org/docs/extensions.html
    // https://www.baeldung.com/kotlin/extension-methods
    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
    }

    val list = mutableListOf(1, 2, 3)
    println("Before swap: $list")
    list.swap(0, 2)
    println("After swap: $list")

    fun String.escapeForXml() : String {
        return this
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
    }

    //Strings.escapeForXml(xml);
}

fun dataClasses() {
    // https://kotlinlang.org/docs/data-classes.html
    // Data classes can't be abstract, open, sealed, or inner.
    data class User(val name: String, val age: Int)

    data class Person(val name: String) {
        var age: Int = 0
    }

    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20

    // Equal evaluated properties from the primary constructor
    println("person1 == person2: ${person1 == person2}")
    // person1 == person2: true

    println("person1 with age ${person1.age}: ${person1}")
    // person1 with age 10: Person(name=John)

    println("person2 with age ${person2.age}: ${person2}")
    // person2 with age 20: Person(name=John

    // Destructuring declarations
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age")

    // Standard library provides Pair and Triple classes.
}

// sealed interface
sealed interface Error

// enum class extending the sealed interface Error
enum class ErrorType : Error {
    FILE_ERROR, DATABASE_ERROR
}

// Create a sealed class that implements sealed interface Error
sealed class IOError() : Error

// Define subclasses that extend sealed class 'IOError'
class FileReadError(val file: File): IOError()
class DatabaseError(val source: DataSource): IOError()

sealed class MyError(val message: String) {
    class NetworkError : MyError("Network failure")
    class DatabaseError : MyError("Database cannot be reached")
    class UnknownError : MyError("An unknown error has occurred")
}

// Create a singleton object implementing the 'Error' sealed interface
object RuntimeError : Error

enum class ErrorSeverity {MINOR, MAJOR, CRITICAL}

sealed class ErrorBySev(val severity: ErrorSeverity) {
    class FileReadError(val file: File): ErrorBySev(ErrorSeverity.MAJOR)
    class DatabaseError(val source: DataSource): ErrorBySev(ErrorSeverity.CRITICAL)
    object RuntimeError : ErrorBySev(ErrorSeverity.CRITICAL)
    // Additional error types can be added here
}

sealed class MyIOError {
    // A sealed class constructor has protected visibility by default. It's visible inside this class and its subclasses
    constructor() { /*...*/ }

    // Private constructor, visible inside this class only.
    // Using a private constructor in a sealed class allows for even stricter control over instantiation, enabling specific initialization procedures within the class.
    private constructor(description: String): this() { /*...*/ }

    // This will raise an error because public and internal constructors are not allowed in sealed classes
    // public constructor(code: Int): this() {}
}

fun sealedClasses() {
    // https://kotlinlang.org/docs/sealed-classes.html

    val errors = listOf(MyError.NetworkError(), MyError.DatabaseError(), MyError.UnknownError())
    errors.forEach { println(it.message) }
}


fun main() {
    //datatypes()

    println()
    //typeConversion()

    println()
    //aboutOperatorsAndTypes()

    println()
    maps()

    println()
    nullSafety()

    println()
    println("--- Extension Functions ---")
    extensionFunctions()

    println()
    println("--- Data Classes ---")
    dataClasses()

    println()
    println("--- Sealed Classes ---")
    sealedClasses()
}








