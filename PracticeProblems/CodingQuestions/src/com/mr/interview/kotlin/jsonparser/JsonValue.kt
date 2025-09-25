package com.mr.interview.kotlin.jsonparser

sealed class JsonValue {
    data class JsonObject(val map: Map<String, JsonValue>) : JsonValue()
    data class JsonArray(val list: List<JsonValue>) : JsonValue()
    data class JsonString(val value: String) : JsonValue()
    data class JsonNumber(val value: Double) : JsonValue()
    data class JsonBoolean(val value: Boolean) : JsonValue()

    object JsonNull: JsonValue()
}