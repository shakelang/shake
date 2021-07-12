package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.*
import com.github.shakelang.shake.util.json.collection.CollectionBase
import com.github.shakelang.shake.util.json.collection.CollectionType
import com.github.shakelang.shake.util.json.collection.MutableCollectionBase
import com.github.shakelang.shake.util.json.collection.MutableCollectionType
import kotlin.jvm.JvmName


@Suppress("unused")
interface JsonArray : JsonElement, CollectionType<JsonElement, JsonArray, MutableJsonArray> {

    class JsonArrayImplementation (value: Collection<JsonElement>) : JsonArray,
        CollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        override val value get() = collection

        override fun toMap(): JsonArray = of(value)

        override fun toMutableMap(): MutableJsonArray = MutableJsonArray.of(value)

        override fun toString(): String = JSON.stringify(value)

    }

    companion object {

        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = JsonArrayImplementation(value.toList())

        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = JsonArrayImplementation(values.toList())

        fun of() = JsonArrayImplementation(listOf())

        fun of(value: Collection<Any?>) = JsonArrayImplementation(value.map { JsonElement.from(it) })

        fun of(vararg values: Any?) = JsonArrayImplementation(values.map { JsonElement.from(it) })

    }

}


@Suppress("unused")
interface MutableJsonArray : JsonArray, MutableCollectionType<JsonElement, JsonArray, MutableJsonArray> {

    class MutableJsonArrayImplementation (value: MutableCollection<JsonElement>) : MutableJsonArray,
        MutableCollectionBase<JsonElement, JsonArray, MutableJsonArray>(value) {

        override val value get() = collection

        override fun toMap(): JsonArray = JsonArray.of(value)

        override fun toMutableMap(): MutableJsonArray = of(value)

        override fun toString(): String = JSON.stringify(value)
    }

    companion object {

        @JvmName("ofElements")
        fun of(value: Collection<JsonElement>) = MutableJsonArrayImplementation(value.toMutableList())

        @JvmName("ofElements")
        fun of(vararg values: JsonElement) = MutableJsonArrayImplementation(values.toMutableList())

        fun of() = MutableJsonArrayImplementation(mutableListOf())

        fun of(value: Collection<Any?>)
                = MutableJsonArrayImplementation(value.map { JsonElement.from(it) }.toMutableList())

        fun of(vararg values: Any?)
                = MutableJsonArrayImplementation(values.map { JsonElement.from(it) }.toMutableList())

    }

}


@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(value: Collection<JsonElement>) = MutableJsonArray.of(value)

@Suppress("unused")
@JvmName("mutableJsonArrayOfElements")
fun mutableJsonArrayOf(vararg values: JsonElement) = MutableJsonArray.of(*values)

@Suppress("unused")
fun mutableJsonArrayOf(value: Collection<Any?>) = MutableJsonArray.of(value)

@Suppress("unused")
fun mutableJsonArrayOf(vararg values: Any?) = MutableJsonArray.of(*values)

@Suppress("unused")
fun mutableJsonArrayOf() = MutableJsonArray.of()


@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(value: Collection<JsonElement>) = JsonArray.of(value)

@Suppress("unused")
@JvmName("jsonArrayOfElements")
fun jsonArrayOf(vararg values: JsonElement) = JsonArray.of(*values)

@Suppress("unused")
fun jsonArrayOf(value: Collection<Any?>) = JsonArray.of(value)

@Suppress("unused")
fun jsonArrayOf(vararg values: Any?) = JsonArray.of(*values)

@Suppress("unused")
fun jsonArrayOf() = MutableJsonArray.of()
