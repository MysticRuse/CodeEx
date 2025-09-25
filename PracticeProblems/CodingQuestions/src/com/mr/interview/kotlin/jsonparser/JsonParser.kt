package com.mr.interview.kotlin.jsonparser

class JsonParser(private val input: String) {
    private var pos = 0

    fun parse(): JsonValue {
        skipWhiteSpace()
        return parseValue()
    }
    private fun eof(): Boolean = pos >= input.length

    private fun parseValue(): JsonValue {
        skipWhiteSpace()
        return when(peek()) {
            '{' -> parseObject()
            '[' -> parseArray()
            '"' -> JsonValue.JsonString(parseString())
            in '0'..'9', '-' ->  JsonValue.JsonNumber(parseNumber())
            't', 'f' -> JsonValue.JsonBoolean(parseBoolean())
            'n' -> parseNull()
            else -> throw IllegalArgumentException("Unexpected character: '${peek()}'")

        }

    }

    private fun parseNull(): JsonValue {
        if (input.startsWith("null", pos)) {
            pos += 4
            return JsonValue.JsonNull
        } else throw IllegalArgumentException("Invalid null")

    }

    private fun parseNumber(): Double {
        val start = pos
        while (peek() in "-0123456789.eE") next()
        return input.substring(start, pos).toDouble()

    }

    private fun parseBoolean(): Boolean {
        return if (input.startsWith("true", pos)) {
            pos += 4; true
        } else if (input.startsWith("false", pos)) {
            pos += 5; false
        } else throw IllegalArgumentException("Invalid boolean")
    }

    private fun parseString(): String {
        consume('"')
        val sb = StringBuilder()
        while (peek() != '"') {
            if (eof()) throw JsonParseException("Unterminated string at position $pos")
            val c = next()
            /**
             * Handle escape sequences
             */
            if (c == '\\') {
                if (eof()) throw JsonParseException("Unexpected end after escape at $pos")
                sb.append(when (val esc = next()) {
                    '"' -> '"'
                    '\\' -> '\\'
                    '/' -> '/'
                    'b' -> 'b'
                    'f' -> '\u000C'
                    'n' -> '\n'
                    'r' -> '\r'
                    't' -> '\t'
                    else -> esc // unknown escape; just add the character
                })
            } else {
                sb.append(c)
            }
        }

        // Expect the closing quote and consume it
        consume('"')
        return sb.toString()

    }

    private fun parseArray(): JsonValue.JsonArray {
        consume('[')

        // Allocate the return value
        val list = mutableListOf<JsonValue>()

        skipWhiteSpace()
        if (peek() == ']') {
            consume(']')
            return JsonValue.JsonArray(list)
        }

        while (true) {
            val value = parseValue()
            list.add(value)
            skipWhiteSpace()
            if (peek() == ']') {
                consume(']')
                break
            }
            consume(',')
        }
        return JsonValue.JsonArray(list)
    }

    private fun parseObject(): JsonValue.JsonObject {
        consume('{')
        // Allocate the return value
        val map = mutableMapOf<String, JsonValue>()
        skipWhiteSpace()
        if (peek() == '}') {
            consume('}')
            return JsonValue.JsonObject(map)
        }

        while (true) {
            skipWhiteSpace()
            val key = parseString()
            skipWhiteSpace()
            consume(':')
            val value = parseValue()
            map[key] = value
            skipWhiteSpace()
            if (peek() == '}') {
                consume('}')
                break
            }
            consume(',')
        }

        return JsonValue.JsonObject(map)
    }


    private fun consume(expected: Char) {
//        if (peek() != c) throw IllegalArgumentException("Expected '$c' but got '${peek()}'")
//        pos++
        val actual = next()
        if (actual != expected) {
            throw JsonParseException("Expected '$expected' but found '$actual' at position $pos")
        }
    }

    private fun peek(): Char {
        return input[pos]
    }

    //private fun next(): Char = this.input[pos++]
    fun next(): Char {
        if (eof()) throw JsonParseException("Unexpected end of input")
        return input[pos++]
    }

    private fun skipWhiteSpace() {
        while (pos < input.length && input[pos].isWhitespace()) pos++
    }

}

fun safeParse(json: String): Any {
    return try {
        JsonParser(json).parse()
    } catch (e: JsonParseException) {
        println("Parse error: ${e.message}")
    }
}

fun main() {
    val parsed = safeParse("""{"name": "Alice", "age": 30, "active": true}""")
    println(parsed)

    val parser2 = JsonParser("""[1, 2, 3, {"nested": "yes"}, null]""")
    println(parser2.parse())

    val parser3 = JsonParser("""{"empty": [], "nullTest": null, "flag": false}""")
    println(parser3.parse())

    // Bad string
    val parsed4 = safeParse("""{ "name": "John", "age": 30,""")
    println(parsed4)
}