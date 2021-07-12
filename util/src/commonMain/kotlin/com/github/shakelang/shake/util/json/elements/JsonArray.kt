package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.*
import com.github.shakelang.shake.util.json.collection.CollectionBase
import com.github.shakelang.shake.util.json.collection.CollectionType
import com.github.shakelang.shake.util.json.collection.MutableCollectionBase
import com.github.shakelang.shake.util.json.collection.MutableCollectionType


interface JsonArray : JsonElement, CollectionType<JsonElement, JsonArray, MutableJsonArray> {

    class JsonArrayImplementation (value: Collection<JsonElement>) : JsonArray,
        CollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        override val value get() = collection

        override fun toMap(): JsonArray = of(value)

        override fun toMutableMap(): MutableJsonArray = MutableJsonArray.of(value)

        override fun toString(): String = JSON.stringify(this)

    }

    companion object {

        fun of(value: Collection<JsonElement>) = JsonArrayImplementation(value.toList())

        fun of(vararg values: JsonElement) = JsonArrayImplementation(values.toList())

        fun of(value: Collection<Any?>) = JsonArrayImplementation(value.map { JsonElement.from(it) })

        fun of(vararg values: Any?) = JsonArrayImplementation(values.map { JsonElement.from(it) })

    }

}
interface MutableJsonArray : JsonArray, MutableCollectionType<JsonElement, JsonArray, MutableJsonArray> {

    class MutableJsonArrayImplementation (value: MutableCollection<JsonElement>) : MutableJsonArray,
        MutableCollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        override val value get() = collection

        override fun toMap(): JsonArray = JsonArray.of(value)

        override fun toMutableMap(): MutableJsonArray = of(value)

        override fun toString(): String = JSON.stringify(this)
    }

    companion object {

        fun of(value: Collection<JsonElement>) = MutableJsonArrayImplementation(value.toMutableList())

        fun of(vararg values: JsonElement) = MutableJsonArrayImplementation(values.toMutableList())

        fun of(value: Collection<Any?>)
                = MutableJsonArrayImplementation(value.map { JsonElement.from(it) }.toMutableList())

        fun of(vararg values: Any?)
                = MutableJsonArrayImplementation(values.map { JsonElement.from(it) }.toMutableList())

    }

}