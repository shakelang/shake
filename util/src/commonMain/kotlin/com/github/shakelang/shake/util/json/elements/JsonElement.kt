package com.github.shakelang.shake.util.json.elements

interface JsonElement {

    val value: Any?

    companion object {

        fun from(value: Any?)
            = when(value) {
                null -> JsonNullElement.INSTANCE
                is String -> from(value)
                is Double -> from(value)
                is Int -> from(value)
                else -> throw Error("Can't create JsonElement from $value")
            }

        fun from(value: String) = JsonStringElement(value)
        fun from(value: Double) = JsonDoubleElement(value)
        fun from(value: Int) = JsonIntegerElement(value)

        fun from(value: Map<String, Any?>) = JsonObject.of(value)
        fun from(vararg values: Pair<String, Any?>) = JsonObject.of(*values)

        fun from(value: Collection<Any?>) = JsonArray.of(value)
        fun from(vararg values: Any?) = JsonArray.of(*values)

    }

    fun isJsonPrimitive() = this is JsonPrimitive
    fun toJsonPrimitive() = if(this.isJsonPrimitive()) this as JsonPrimitive else throw Error("Element is not a JsonPrimitive")

    fun isJsonObject() = this is JsonObject
    fun toJsonObject() = if(this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

    fun isJsonArray() = this is JsonObject
    fun toJsonArray() = if(this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

}