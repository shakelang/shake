package com.github.shakelang.shake.util.json.elements

interface JsonPrimitive : JsonElement {

    fun isInt() = this is JsonIntegerElement
    fun toInt() = if(this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    fun isDouble() = this is JsonIntegerElement
    fun toDouble() = if(this.isInt()) this as JsonIntegerElement else throw Error("Element is not a JsonIntegerElement")

    fun isString() = this is JsonIntegerElement
    fun toStringElement() = if(this.isString()) this as JsonStringElement else throw Error("Element is not a JsonStringElement")

}