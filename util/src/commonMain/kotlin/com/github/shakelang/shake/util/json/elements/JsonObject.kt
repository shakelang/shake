package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.*
import com.github.shakelang.shake.util.json.map.MapBase
import com.github.shakelang.shake.util.json.map.MapType
import com.github.shakelang.shake.util.json.map.MutableMapBase
import com.github.shakelang.shake.util.json.map.MutableMapType
import kotlin.jvm.JvmName


@Suppress("unused")
interface JsonObject : JsonElement, MapType<String, JsonElement, JsonObject, MutableJsonObject> {

    class JsonObjectImplementation (value: Map<String, JsonElement>)
        : JsonObject, MapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

        override val value: Map<String, JsonElement> get() = this.map

        override fun toMap(): JsonObject = of(map)

        override fun toMutableMap(): MutableJsonObject = MutableJsonObject.of(value)

        override fun toString(): String = JSON.stringify(value)

    }

    companion object {

        @JvmName("ofElements")
        fun of(value: Map<String, JsonElement>) = JsonObjectImplementation(value.toMutableMap())

        @JvmName("ofElements")
        fun of(vararg values: Pair<String, JsonElement>) = JsonObjectImplementation(mutableMapOf(*values))

        fun of() = JsonObjectImplementation(mapOf())

        fun of(value: Map<String, Any?>)
                = JsonObjectImplementation(value.mapValues { JsonElement.from(it.value) })

        fun of(vararg values: Pair<String, Any?>) = of(mutableMapOf(*values))

    }

}


@Suppress("unused")
interface MutableJsonObject : JsonObject, MutableMapType<String, JsonElement, JsonObject, MutableJsonObject> {

    class MutableJsonObjectImplementation (value: MutableMap<String, JsonElement>)
        : MutableJsonObject, MutableMapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

        override val value: MutableMap<String, JsonElement> get() = this.map

        override fun toMap(): JsonObject = JsonObject.of(map)

        override fun toMutableMap(): MutableJsonObject = of(value)

        override fun toString(): String = JSON.stringify(value)

    }

    companion object {

        @JvmName("ofElements")
        fun of(value: Map<String, JsonElement>) = MutableJsonObjectImplementation(value.toMutableMap())

        @JvmName("ofElements")
        fun of(vararg values: Pair<String, JsonElement>) = MutableJsonObjectImplementation(mutableMapOf(*values))

        fun of() = MutableJsonObjectImplementation(mutableMapOf())

        fun of(value: Map<String, Any?>)
                = MutableJsonObjectImplementation(value.mapValues { JsonElement.from(it.value) }.toMutableMap())

        fun of(vararg values: Pair<String, Any?>) = of(mapOf(*values))

    }

}


@Suppress("unused")
@JvmName("mutableJsonObjectOfElements")
fun mutableJsonObjectOf(value: Map<String, JsonElement>) = MutableJsonObject.of(value)

@Suppress("unused")
@JvmName("mutableJsonObjectOfElements")
fun mutableJsonObjectOf(vararg values: Pair<String, JsonElement>) = MutableJsonObject.of(*values)

@Suppress("unused")
fun mutableJsonObjectOf(value: Map<String, Any?>) = MutableJsonObject.of(value)

@Suppress("unused")
fun mutableJsonObjectOf(vararg values: Pair<String, Any?>) = MutableJsonObject.of(*values)

@Suppress("unused")
fun mutableJsonObjectOf() = MutableJsonObject.of()


@Suppress("unused")
@JvmName("jsonObjectOfElements")
fun jsonObjectOf(value: Map<String, JsonElement>) = JsonObject.of(value)

@Suppress("unused")
@JvmName("jsonObjectOfElements")
fun jsonObjectOf(vararg values: Pair<String, JsonElement>) = JsonObject.of(*values)

@Suppress("unused")
fun jsonObjectOf(value: Map<String, Any?>) = JsonObject.of(value)

@Suppress("unused")
fun jsonObjectOf(vararg values: Pair<String, Any?>) = JsonObject.of(*values)

@Suppress("unused")
fun jsonObjectOf() = JsonObject.of()