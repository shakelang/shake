package com.github.shakelang.shake.util.json.elements

import com.github.shakelang.shake.util.json.*
import com.github.shakelang.shake.util.json.map.MapBase
import com.github.shakelang.shake.util.json.map.MapType
import com.github.shakelang.shake.util.json.map.MutableMapBase
import com.github.shakelang.shake.util.json.map.MutableMapType


interface JsonObject : JsonElement, MapType<String, JsonElement, JsonObject, MutableJsonObject> {

    class JsonObjectImplementation (value: Map<String, JsonElement>)
        : JsonObject, MapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

        override val value: Map<String, JsonElement> get() = this.map

        override fun toMap(): JsonObject = of(map)

        override fun toMutableMap(): MutableJsonObject = MutableJsonObject.of(value)

        override fun toString(): String = JSON.stringify(this)

    }

    companion object {

        fun of(value: Map<String, JsonElement>) = JsonObjectImplementation(value.toMutableMap())

        fun of(vararg values: Pair<String, JsonElement>) = JsonObjectImplementation(mutableMapOf(*values))

        fun of(value: Map<String, Any?>)
                = JsonObjectImplementation(value.mapValues { JsonElement.from(it.value) })

        fun of(vararg values: Pair<String, Any?>) = of(mutableMapOf(*values))

    }

}

interface MutableJsonObject : JsonObject, MutableMapType<String, JsonElement, JsonObject, MutableJsonObject> {

    class MutableJsonObjectImplementation (value: MutableMap<String, JsonElement>)
        : MutableJsonObject, MutableMapBase<String, JsonElement, JsonObject, MutableJsonObject>(value) {

        override val value: MutableMap<String, JsonElement> get() = this.map

        override fun toMap(): JsonObject = JsonObject.of(map)

        override fun toMutableMap(): MutableJsonObject = of(value)

        override fun toString(): String = JSON.stringify(this)

    }

    companion object {

        fun of(value: Map<String, JsonElement>) = MutableJsonObjectImplementation(value.toMutableMap())

        fun of(vararg values: Pair<String, JsonElement>) = MutableJsonObjectImplementation(mutableMapOf(*values))

        fun of(value: Map<String, Any?>)
                = MutableJsonObjectImplementation(value.mapValues { JsonElement.from(it.value) }.toMutableMap())

        fun of(vararg values: Pair<String, Any?>) = of(mapOf(*values))

    }

}