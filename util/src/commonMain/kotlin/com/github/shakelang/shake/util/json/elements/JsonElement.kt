package com.github.shakelang.shake.util.json.elements

import kotlin.jvm.JvmName

interface JsonElement {

    val value: Any?

    companion object {

        fun from(value: Any?)
            = when(value) {
                null -> JsonNullElement.INSTANCE
                is String -> from(value)
                is Float -> from(value)
                is Double -> from(value)
                is Byte -> from(value)
                is Short -> from(value)
                is Int -> from(value)
                is Long -> from(value)
                is Boolean -> from(value)
                is JsonElement -> value
                else -> throw Error("Can't create JsonElement from $value")
            }

        fun from(value: String) = JsonStringElement(value)

        fun from(value: Boolean) = JsonBooleanElement.from(value)

        fun from(value: Double) = JsonDoubleElement(value)
        fun from(value: Float) = from(value.toDouble())

        fun from(value: Long) = JsonIntegerElement(value)
        fun from(value: Int) = from(value.toLong())
        fun from(value: Short) = from(value.toLong())
        fun from(value: Byte) = from(value.toLong())
        fun from(value: Map<String, Any?>) = JsonObject.of(value)
        fun from(vararg values: Pair<String, Any?>) = JsonObject.of(*values)

        @JvmName("fromElements")
        fun from(value: Map<String, JsonElement>) = JsonObject.of(value)

        @JvmName("fromElements")
        fun from(vararg values: Pair<String, JsonElement>) = JsonObject.of(*values)

        fun from(value: Collection<Any?>) = JsonArray.of(value)
        fun from(vararg values: Any?) = JsonArray.of(*values)

        @JvmName("fromElements")
        fun from(value: Collection<JsonElement>) = JsonArray.of(value)

        @JvmName("fromElements")
        fun from(vararg values: JsonElement) = JsonArray.of(*values)

    }

    fun isJsonPrimitive() = this is JsonPrimitive
    fun toJsonPrimitive() = if(this.isJsonPrimitive()) this as JsonPrimitive else throw Error("Element is not a JsonPrimitive")

    fun isJsonObject() = this is JsonObject
    fun toJsonObject() = if(this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

    fun isJsonArray() = this is JsonObject
    fun toJsonArray() = if(this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

}