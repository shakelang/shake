package com.github.shakelang.shake.util.json

interface JsonElement {

    val value: Any?

    companion object {
        fun from(value: String) = JsonStringElement(value)
        fun from(value: Double) = JsonDoubleElement(value)
        fun from(value: Int) = JsonIntegerElement(value)
    }

    fun isJsonPrimitive() = this is JsonPrimitive
    fun toJsonPrimitive() = if(this.isJsonPrimitive()) this as JsonPrimitive else throw Error("Element is not a JsonPrimitive")

    fun isJsonObject() = this is JsonObject
    fun toJsonObject() = if(this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

    fun isJsonArray() = this is JsonObject
    fun toJsonArray() = if(this.isJsonObject()) this as JsonObject else throw Error("Element is not a JsonObject")

}

interface JsonPrimitive : JsonElement {

    fun isInt() = this is JsonIntegerElement
    fun toInt() = if(this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    fun isDouble() = this is JsonIntegerElement
    fun toDouble() = if(this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    fun isString() = this is JsonIntegerElement
    fun toStringElement() = if(this.isString()) this as JsonStringElement else throw Error("Element is not a JsonStringElement")

}

class JsonNullElement : JsonPrimitive {

    override val value: Nothing?
        get() = null

    override fun toString(): String = json.stringify(this)

}


class JsonStringElement (

    override val value: String

) : JsonPrimitive {

    override fun toString(): String = json.stringify(this)

}


class JsonIntegerElement (

    override val value: Int

) : JsonPrimitive {

    override fun toString(): String = json.stringify(this)

}


class JsonDoubleElement (

    override val value: Double

) : JsonPrimitive {

    override fun toString(): String = json.stringify(this)

}

interface JsonObject : JsonElement, MapType<String, JsonElement, JsonObject, MutableJsonObject>
interface MutableJsonObject : JsonObject, MutableMapType<String, JsonElement, JsonObject, MutableJsonObject>

class JsonObjectImplementation (value: Map<String, JsonElement>)
    : JsonObject, MapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

    override val value: Map<String, JsonElement> get() = this.map

    override fun toMap(): JsonObject = JsonObjectImplementation(map.toMap())

    override fun toMutableMap(): MutableJsonObject = MutableJsonObjectImplementation(value.toMutableMap())

    override fun toString(): String = json.stringify(this)

}

class MutableJsonObjectImplementation (value: MutableMap<String, JsonElement>)
    : MutableJsonObject, MutableMapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

    override val value: MutableMap<String, JsonElement> get() = this.map

    override fun toMap(): JsonObject = JsonObjectImplementation(value.toMap())

    override fun toMutableMap(): MutableJsonObject = MutableJsonObjectImplementation(value.toMutableMap())

    override fun toString(): String = json.stringify(this)

}

interface JsonArray : JsonElement, CollectionType<JsonElement, JsonArray, MutableJsonArray>
interface MutableJsonArray : JsonArray, MutableCollectionType<JsonElement, JsonArray, MutableJsonArray>

class JsonArrayImplementation (value: Collection<JsonElement>) : JsonArray,
    CollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

    override val value get() = collection

    override fun toMap(): JsonArray
        = JsonArrayImplementation(value.toList())


    override fun toMutableMap(): MutableJsonArray
        = MutableJsonArrayImplementation(value.toMutableList())

    override fun toString(): String = json.stringify(this)

}

class MutableJsonArrayImplementation (value: MutableCollection<JsonElement>) : MutableJsonArray,
    MutableCollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

    override val value get() = collection

    override fun toMap(): JsonArray
            = JsonArrayImplementation(value.toList())

    override fun toMutableMap(): MutableJsonArray
            = MutableJsonArrayImplementation(value.toMutableList())

    override fun toString(): String = json.stringify(this)
}