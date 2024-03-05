package com.shakelang.util.shason.processing

import com.shakelang.util.shason.elements.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonGeneratorTests : FreeSpec(
    {

        "generate byte" {
            JsonGenerator.generate(42.toByte()) shouldBe "42"
            JsonGenerator.generate(0.toByte()) shouldBe "0"
            JsonGenerator.generate((-1).toByte()) shouldBe "-1"
        }

        "generate shorts" {
            JsonGenerator.generate(42.toShort()) shouldBe "42"
            JsonGenerator.generate(0.toShort()) shouldBe "0"
            JsonGenerator.generate((-1).toShort()) shouldBe "-1"
        }

        "generate int" {
            JsonGenerator.generate(42) shouldBe "42"
            JsonGenerator.generate(0) shouldBe "0"
            JsonGenerator.generate(-1) shouldBe "-1"
        }

        "generate long" {
            JsonGenerator.generate(42L) shouldBe "42"
            JsonGenerator.generate(0L) shouldBe "0"
            JsonGenerator.generate(-1L) shouldBe "-1"
        }

        "generate float" {
            JsonGenerator.generate(42.1.toFloat()) shouldBe "42.1"
            JsonGenerator.generate(0.1.toFloat()) shouldBe "0.1"
            JsonGenerator.generate((-1.1).toFloat()) shouldBe "-1.1"
        }

        "generate doubles" {
            JsonGenerator.generate(42.1) shouldBe "42.1"
            JsonGenerator.generate(0.1) shouldBe "0.1"
            JsonGenerator.generate(-1.1) shouldBe "-1.1"
        }

        "generate boolean" {
            JsonGenerator.generate(true) shouldBe "true"
            JsonGenerator.generate(false) shouldBe "false"
        }

        "generate array empty" {
            JsonGenerator.generate(emptyArray<Any>()) shouldBe "[]"
            JsonGenerator.generate(emptyArray<Any>(), indentAmount = 2) shouldBe "[]"
            JsonGenerator.generate(emptyArray<Any>(), indentAmount = 4) shouldBe "[]"
        }

        "generate array filled one element" {
            JsonGenerator.generate(arrayOf("hello")) shouldBe "[\"hello\"]"
            JsonGenerator.generate(arrayOf("hello"), indent = " ".repeat(2)) shouldBe "[\n  \"hello\"\n]"
            JsonGenerator.generate(arrayOf("hello"), indent = " ".repeat(4)) shouldBe "[\n    \"hello\"\n]"
        }

        "generate array filled multiple elements" {
            JsonGenerator.generate(arrayOf("hello", "world")) shouldBe "[\"hello\",\"world\"]"
            JsonGenerator.generate(
                arrayOf("hello", "world"),
                indent = " ".repeat(2),
            ) shouldBe "[\n  \"hello\",\n  \"world\"\n]"
            JsonGenerator.generate(
                arrayOf("hello", "world"),
                indent = " ".repeat(4),
            ) shouldBe "[\n    \"hello\",\n    \"world\"\n]"
        }

        "generate array nested" {
            JsonGenerator.generate(arrayOf(arrayOf("hello"))) shouldBe "[[\"hello\"]]"
            JsonGenerator.generate(
                arrayOf(arrayOf("hello")),
                indent = " ".repeat(2),
            ) shouldBe "[\n  [\n    \"hello\"\n  ]\n]"
            JsonGenerator.generate(
                arrayOf(arrayOf("hello")),
                indent = " ".repeat(4),
            ) shouldBe "[\n    [\n        \"hello\"\n    ]\n]"
        }

        "generate list empty" {
            JsonGenerator.generate(emptyList<Any>()) shouldBe "[]"
            JsonGenerator.generate(emptyList<Any>(), indentAmount = 2) shouldBe "[]"
            JsonGenerator.generate(emptyList<Any>(), indentAmount = 4) shouldBe "[]"
        }

        "generate list filled one element" {
            JsonGenerator.generate(listOf("hello")) shouldBe "[\"hello\"]"
            JsonGenerator.generate(listOf("hello"), indent = " ".repeat(2)) shouldBe "[\n  \"hello\"\n]"
            JsonGenerator.generate(listOf("hello"), indent = " ".repeat(4)) shouldBe "[\n    \"hello\"\n]"
        }

        "generate list filled multiple elements" {
            JsonGenerator.generate(listOf("hello", "world")) shouldBe "[\"hello\",\"world\"]"
            JsonGenerator.generate(
                listOf("hello", "world"),
                indent = " ".repeat(2),
            ) shouldBe "[\n  \"hello\",\n  \"world\"\n]"
            JsonGenerator.generate(
                listOf("hello", "world"),
                indent = " ".repeat(4),
            ) shouldBe "[\n    \"hello\",\n    \"world\"\n]"
        }

        "generate list nested" {
            JsonGenerator.generate(listOf(listOf("hello"))) shouldBe "[[\"hello\"]]"
            JsonGenerator.generate(listOf(listOf("hello")), indent = " ".repeat(2)) shouldBe "[\n  [\n    \"hello\"\n  ]\n]"
            JsonGenerator.generate(
                listOf(listOf("hello")),
                indent = " ".repeat(4),
            ) shouldBe "[\n    [\n        \"hello\"\n    ]\n]"
        }

        "generate map empty" {
            JsonGenerator.generate(emptyMap<String, Any>()) shouldBe "{}"
            JsonGenerator.generate(emptyMap<String, Any>(), indentAmount = 2) shouldBe "{}"
            JsonGenerator.generate(emptyMap<String, Any>(), indentAmount = 4) shouldBe "{}"
        }

        "generate map filled one element" {
            JsonGenerator.generate(mapOf("hello" to 1)) shouldBe "{\"hello\":1}"
            JsonGenerator.generate(mapOf("hello" to 1), indent = " ".repeat(2)) shouldBe "{\n  \"hello\": 1\n}"
            JsonGenerator.generate(mapOf("hello" to 1), indent = " ".repeat(4)) shouldBe "{\n    \"hello\": 1\n}"
        }

        "generate map filled multiple elements" {
            JsonGenerator.generate(mapOf("hello" to 1, "world" to 2)) shouldBe "{\"hello\":1,\"world\":2}"
            JsonGenerator.generate(
                mapOf("hello" to 1, "world" to 2),
                indent = " ".repeat(2),
            ) shouldBe "{\n  \"hello\": 1,\n  \"world\": 2\n}"
            JsonGenerator.generate(
                mapOf("hello" to 1, "world" to 2),
                indent = " ".repeat(4),
            ) shouldBe "{\n    \"hello\": 1,\n    \"world\": 2\n}"
        }

        "generate map nested" {
            JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1))) shouldBe "{\"hello\":{\"world\":1}}"
            JsonGenerator.generate(
                mapOf("hello" to mapOf("world" to 1)),
                indent = " ".repeat(2),
            ) shouldBe "{\n  \"hello\": {\n    \"world\": 1\n  }\n}"
            JsonGenerator.generate(
                mapOf("hello" to mapOf("world" to 1)),
                indent = " ".repeat(4),
            ) shouldBe "{\n    \"hello\": {\n        \"world\": 1\n    }\n}"
        }

        "generate JsonElement" {
            JsonGenerator.generate(JsonStringElement("42")) shouldBe "\"42\""
            JsonGenerator.generate(JsonNullElement.INSTANCE) shouldBe "null"
            JsonGenerator.generate(JsonNullElement.INSTANCE, indentAmount = 2) shouldBe "null"
            JsonGenerator.generate(JsonNullElement.INSTANCE, indentAmount = 4) shouldBe "null"
            JsonGenerator.generate(JsonStringElement("42"), indentAmount = 2) shouldBe "\"42\""
            JsonGenerator.generate(JsonBooleanElement.TRUE) shouldBe "true"
            JsonGenerator.generate(JsonBooleanElement.FALSE) shouldBe "false"
        }

        "generate JsonNullElement" {
            JsonGenerator.generate(JsonNullElement.INSTANCE) shouldBe "null"
            JsonGenerator.generate(JsonNullElement.INSTANCE, indentAmount = 2) shouldBe "null"
            JsonGenerator.generate(JsonNullElement.INSTANCE, indentAmount = 4) shouldBe "null"
        }

        "generate JsonStringElement" {
            JsonGenerator.generate(JsonStringElement("42")) shouldBe "\"42\""
            JsonGenerator.generate(JsonStringElement("42"), indentAmount = 2) shouldBe "\"42\""
        }

        "generate JsonBooleanElement" {
            JsonGenerator.generate(JsonBooleanElement.TRUE) shouldBe "true"
            JsonGenerator.generate(JsonBooleanElement.FALSE) shouldBe "false"
        }

        "generate JsonArray" {
            JsonGenerator.generate(JsonArray.of(listOf(JsonStringElement("42")))) shouldBe "[\"42\"]"
        }

        "generate JsonObject" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to JsonStringElement("42")))) shouldBe "{\"hello\":\"42\"}"
        }

        "generate map with null value" {
            JsonGenerator.generate(mapOf("hello" to null)) shouldBe "{\"hello\":null}"
        }

        "generate map with null key" {
            JsonGenerator.generate(mapOf(null to "world")) shouldBe "{\"null\":\"world\"}"
        }

        "generate map with null key and value" {
            JsonGenerator.generate(mapOf(null to null)) shouldBe "{\"null\":null}"
        }

        "generate map with byte value" {
            JsonGenerator.generate(mapOf("hello" to 42.toByte())) shouldBe "{\"hello\":42}"
        }

        "generate map with shorts value" {
            JsonGenerator.generate(mapOf("hello" to 42.toShort())) shouldBe "{\"hello\":42}"
        }

        "generate map with int value" {
            JsonGenerator.generate(mapOf("hello" to 42)) shouldBe "{\"hello\":42}"
        }

        "generate map with long value" {
            JsonGenerator.generate(mapOf("hello" to 42L)) shouldBe "{\"hello\":42}"
        }

        "generate map with float value" {
            JsonGenerator.generate(mapOf("hello" to 42.1.toFloat())) shouldBe "{\"hello\":42.1}"
        }

        "generate map with doubles value" {
            JsonGenerator.generate(mapOf("hello" to 42.1)) shouldBe "{\"hello\":42.1}"
        }

        "generate map with boolean value" {
            JsonGenerator.generate(mapOf("hello" to true)) shouldBe "{\"hello\":true}"
        }

        "generate map with array value" {
            JsonGenerator.generate(mapOf("hello" to arrayOf("world"))) shouldBe "{\"hello\":[\"world\"]}"
        }

        "generate map with list value" {
            JsonGenerator.generate(mapOf("hello" to listOf("world"))) shouldBe "{\"hello\":[\"world\"]}"
        }

        "generate map with map value" {
            JsonGenerator.generate(mapOf("hello" to mapOf("world" to 42))) shouldBe "{\"hello\":{\"world\":42}}"
        }

        "generate map with JsonStringElement value" {
            JsonGenerator.generate(mapOf("hello" to JsonStringElement("world"))) shouldBe "{\"hello\":\"world\"}"
        }

        "generate map with JsonNullElement value" {
            JsonGenerator.generate(mapOf("hello" to JsonNullElement.INSTANCE)) shouldBe "{\"hello\":null}"
        }

        "generate map with JsonBooleanElement value" {
            JsonGenerator.generate(mapOf("hello" to JsonBooleanElement.TRUE)) shouldBe "{\"hello\":true}"
        }

        "generate map with JsonArray value" {
            JsonGenerator.generate(mapOf("hello" to JsonArray.of(listOf(JsonStringElement("world"))))) shouldBe "{\"hello\":[\"world\"]}"
        }

        "generate map with JsonObject value" {
            JsonGenerator.generate(mapOf("hello" to JsonObject.of(mapOf("world" to JsonStringElement("42"))))) shouldBe "{\"hello\":{\"world\":\"42\"}}"
        }

        "generate map with mixed values" {
            JsonGenerator.generate(
                mapOf(
                    "hello" to 42,
                    "world" to "42",
                    "array" to arrayOf("42"),
                    "list" to listOf("42"),
                    "map" to mapOf("42" to 42),
                    "json" to JsonStringElement("42"),
                ),
            ) shouldBe "{\"hello\":42,\"world\":\"42\",\"array\":[\"42\"],\"list\":[\"42\"],\"map\":{\"42\":42},\"json\":\"42\"}"
        }

        "generate array with null value" {
            JsonGenerator.generate(arrayOf<String?>(null)) shouldBe "[null]"
        }

        "generate array with byte value" {
            JsonGenerator.generate(arrayOf(42.toByte())) shouldBe "[42]"
        }

        "generate array with shorts value" {
            JsonGenerator.generate(arrayOf(42.toShort())) shouldBe "[42]"
        }

        "generate array with int value" {
            JsonGenerator.generate(arrayOf(42)) shouldBe "[42]"
        }

        "generate array with long value" {
            JsonGenerator.generate(arrayOf(42L)) shouldBe "[42]"
        }

        "generate array with float value" {
            JsonGenerator.generate(arrayOf(42.1.toFloat())) shouldBe "[42.1]"
        }

        "generate array with doubles value" {
            JsonGenerator.generate(arrayOf(42.1)) shouldBe "[42.1]"
        }

        "generate array with boolean value" {
            JsonGenerator.generate(arrayOf(true)) shouldBe "[true]"
        }

        "generate array with array value" {
            JsonGenerator.generate(arrayOf(arrayOf("world"))) shouldBe "[[\"world\"]]"
        }

        "generate array with list value" {
            JsonGenerator.generate(arrayOf(listOf("world"))) shouldBe "[[\"world\"]]"
        }

        "generate array with map value" {
            JsonGenerator.generate(arrayOf(mapOf("world" to 42))) shouldBe "[{\"world\":42}]"
        }

        "generate array with JsonStringElement value" {
            JsonGenerator.generate(arrayOf(JsonStringElement("world"))) shouldBe "[\"world\"]"
        }

        "generate array with JsonNullElement value" {
            JsonGenerator.generate(arrayOf(JsonNullElement.INSTANCE)) shouldBe "[null]"
        }

        "generate array with JsonBooleanElement value" {
            JsonGenerator.generate(arrayOf(JsonBooleanElement.TRUE)) shouldBe "[true]"
        }

        "generate array with JsonArray value" {
            JsonGenerator.generate(arrayOf(JsonArray.of(listOf(JsonStringElement("world"))))) shouldBe "[[\"world\"]]"
        }

        "generate array with JsonObject value" {
            JsonGenerator.generate(arrayOf(JsonObject.of(mapOf("world" to JsonStringElement("42"))))) shouldBe "[{\"world\":\"42\"}]"
        }

        "generate array with mixed values" {
            JsonGenerator.generate(
                arrayOf(
                    42,
                    "42",
                    arrayOf("42"),
                    listOf("42"),
                    mapOf("42" to 42),
                    JsonStringElement("42"),
                ),
            ) shouldBe "[42,\"42\",[\"42\"],[\"42\"],{\"42\":42},\"42\"]"
        }

        "generate array with mixed values and null" {
            JsonGenerator.generate(
                arrayOf(
                    42,
                    "42",
                    arrayOf("42"),
                    listOf("42"),
                    mapOf("42" to 42),
                    JsonStringElement("42"),
                    null,
                ),
            ) shouldBe "[42,\"42\",[\"42\"],[\"42\"],{\"42\":42},\"42\",null]"
        }

        "generate list with null value" {
            JsonGenerator.generate(listOf<String?>(null)) shouldBe "[null]"
        }

        "generate list with byte value" {
            JsonGenerator.generate(listOf(42.toByte())) shouldBe "[42]"
        }

        "generate list with shorts value" {
            JsonGenerator.generate(listOf(42.toShort())) shouldBe "[42]"
        }

        "generate list with int value" {
            JsonGenerator.generate(listOf(42)) shouldBe "[42]"
        }

        "generate list with long value" {
            JsonGenerator.generate(listOf(42L)) shouldBe "[42]"
        }

        "generate list with float value" {
            JsonGenerator.generate(listOf(42.1.toFloat())) shouldBe "[42.1]"
        }

        "generate list with doubles value" {
            JsonGenerator.generate(listOf(42.1)) shouldBe "[42.1]"
        }

        "generate list with boolean value" {
            JsonGenerator.generate(listOf(true)) shouldBe "[true]"
        }

        "generate list with array value" {
            JsonGenerator.generate(listOf(arrayOf("world"))) shouldBe "[[\"world\"]]"
        }

        "generate list with list value" {
            JsonGenerator.generate(listOf(listOf("world"))) shouldBe "[[\"world\"]]"
        }

        "generate list with map value" {
            JsonGenerator.generate(listOf(mapOf("world" to 42))) shouldBe "[{\"world\":42}]"
        }

        "generate list with JsonStringElement value" {
            JsonGenerator.generate(listOf(JsonStringElement("world"))) shouldBe "[\"world\"]"
        }

        "generate list with JsonNullElement value" {
            JsonGenerator.generate(listOf(JsonNullElement.INSTANCE)) shouldBe "[null]"
        }

        "generate list with JsonBooleanElement value" {
            JsonGenerator.generate(listOf(JsonBooleanElement.TRUE)) shouldBe "[true]"
        }

        "generate list with JsonArray value" {
            JsonGenerator.generate(listOf(JsonArray.of(listOf(JsonStringElement("world"))))) shouldBe "[[\"world\"]]"
        }

        "generate list with JsonObject value" {
            JsonGenerator.generate(listOf(JsonObject.of(mapOf("world" to JsonStringElement("42"))))) shouldBe "[{\"world\":\"42\"}]"
        }

        "generate list with mixed values" {
            JsonGenerator.generate(
                listOf(
                    42,
                    "42",
                    arrayOf("42"),
                    listOf("42"),
                    mapOf("42" to 42),
                    JsonStringElement("42"),
                ),
            ) shouldBe "[42,\"42\",[\"42\"],[\"42\"],{\"42\":42},\"42\"]"
        }

        "generate list with mixed values and null" {
            JsonGenerator.generate(
                listOf(
                    42,
                    "42",
                    arrayOf("42"),
                    listOf("42"),
                    mapOf("42" to 42),
                    JsonStringElement("42"),
                    null,
                ),
            ) shouldBe "[42,\"42\",[\"42\"],[\"42\"],{\"42\":42},\"42\",null]"
        }

        "generate JsonArray with null value" {
            JsonGenerator.generate(JsonArray.of(listOf(null))) shouldBe "[null]"
        }

        "generate JsonArray with byte value" {
            JsonGenerator.generate(JsonArray.of(listOf(42.toByte()))) shouldBe "[42]"
        }

        "generate JsonArray with shorts value" {
            JsonGenerator.generate(JsonArray.of(listOf(42.toShort()))) shouldBe "[42]"
        }

        "generate JsonArray with int value" {
            JsonGenerator.generate(JsonArray.of(listOf(42))) shouldBe "[42]"
        }

        "generate JsonArray with long value" {
            JsonGenerator.generate(JsonArray.of(listOf(42L))) shouldBe "[42]"
        }

        "generate JsonArray with float value" {
            JsonGenerator.generate(JsonArray.of(listOf(42.0.toFloat()))) shouldBe "[42]"
        }

        "generate JsonArray with doubles value" {
            JsonGenerator.generate(JsonArray.of(listOf(42.1))) shouldBe "[42.1]"
        }

        "generate JsonArray with boolean value" {
            JsonGenerator.generate(JsonArray.of(listOf(true))) shouldBe "[true]"
        }

        "generate JsonArray with array value" {
            JsonGenerator.generate(JsonArray.of(listOf(arrayOf("world")))) shouldBe "[[\"world\"]]"
        }

        "generate JsonArray with list value" {
            JsonGenerator.generate(JsonArray.of(listOf(listOf("world")))) shouldBe "[[\"world\"]]"
        }

        "generate JsonArray with map value" {
            JsonGenerator.generate(JsonArray.of(listOf(mapOf("world" to 42)))) shouldBe "[{\"world\":42}]"
        }

        "generate JsonArray with JsonStringElement value" {
            JsonGenerator.generate(JsonArray.of(listOf(JsonStringElement("world")))) shouldBe "[\"world\"]"
        }

        "generate JsonArray with JsonNullElement value" {
            JsonGenerator.generate(JsonArray.of(listOf(JsonNullElement.INSTANCE))) shouldBe "[null]"
        }

        "generate JsonArray with JsonBooleanElement value" {
            JsonGenerator.generate(JsonArray.of(listOf(JsonBooleanElement.TRUE))) shouldBe "[true]"
        }

        "generate JsonArray with JsonArray value" {
            JsonGenerator.generate(JsonArray.of(listOf(JsonArray.of(listOf(JsonStringElement("world")))))) shouldBe "[[\"world\"]]"
        }

        "generate JsonArray with JsonObject value" {
            JsonGenerator.generate(JsonArray.of(listOf(JsonObject.of(mapOf("world" to JsonStringElement("42")))))) shouldBe "[{\"world\":\"42\"}]"
        }

        "generate JsonArray with mixed values" {
            JsonGenerator.generate(
                JsonArray.of(
                    listOf(
                        42,
                        "42",
                        arrayOf("42"),
                        listOf("42"),
                        mapOf("42" to 42),
                        JsonStringElement("42"),
                    ),
                ),
            ) shouldBe "[42,\"42\",[\"42\"],[\"42\"],{\"42\":42},\"42\"]"
        }

        "generate JsonArray with mixed values and null" {
            JsonGenerator.generate(
                JsonArray.of(
                    listOf(
                        42,
                        "42",
                        arrayOf("42"),
                        listOf("42"),
                        mapOf("42" to 42),
                        JsonStringElement("42"),
                        null,
                    ),
                ),
            ) shouldBe "[42,\"42\",[\"42\"],[\"42\"],{\"42\":42},\"42\",null]"
        }

        "generate JsonObject with null value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to null))) shouldBe "{\"hello\":null}"
        }

        "generate JsonObject with byte value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to 42.toByte()))) shouldBe "{\"hello\":42}"
        }

        "generate JsonObject with shorts value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to 42.toShort()))) shouldBe "{\"hello\":42}"
        }

        "generate JsonObject with int value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to 42))) shouldBe "{\"hello\":42}"
        }

        "generate JsonObject with long value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to 42L))) shouldBe "{\"hello\":42}"
        }

        "generate JsonObject with float value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to 42.toFloat()))) shouldBe "{\"hello\":42}"
        }

        "generate JsonObject with doubles value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to 42.1))) shouldBe "{\"hello\":42.1}"
        }

        "generate JsonObject with boolean value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to true))) shouldBe "{\"hello\":true}"
        }

        "generate JsonObject with array value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to arrayOf("world")))) shouldBe "{\"hello\":[\"world\"]}"
        }

        "generate JsonObject with list value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to listOf("world")))) shouldBe "{\"hello\":[\"world\"]}"
        }

        "generate JsonObject with map value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to mapOf("world" to 42)))) shouldBe "{\"hello\":{\"world\":42}}"
        }

        "generate JsonObject with JsonStringElement value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to JsonStringElement("world")))) shouldBe "{\"hello\":\"world\"}"
        }

        "generate JsonObject with JsonNullElement value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to JsonNullElement.INSTANCE))) shouldBe "{\"hello\":null}"
        }

        "generate JsonObject with JsonBooleanElement value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to JsonBooleanElement.TRUE))) shouldBe "{\"hello\":true}"
        }

        "generate JsonObject with JsonArray value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to JsonArray.of(listOf(JsonStringElement("world")))))) shouldBe "{\"hello\":[\"world\"]}"
        }

        "generate JsonObject with JsonObject value" {
            JsonGenerator.generate(JsonObject.of(mapOf("hello" to JsonObject.of(mapOf("world" to JsonStringElement("42")))))) shouldBe "{\"hello\":{\"world\":\"42\"}}"
        }

        "generate JsonObject with mixed values" {
            JsonGenerator.generate(
                JsonObject.of(
                    mapOf(
                        "hello" to 42,
                        "world" to "42",
                        "array" to arrayOf("42"),
                        "list" to listOf("42"),
                        "map" to mapOf("42" to 42),
                        "json" to JsonStringElement("42"),
                    ),
                ),
            ) shouldBe "{\"hello\":42,\"world\":\"42\",\"array\":[\"42\"],\"list\":[\"42\"],\"map\":{\"42\":42},\"json\":\"42\"}"
        }

        "generate JsonObject with mixed values and null" {
            JsonGenerator.generate(
                JsonObject.of(
                    mapOf(
                        "hello" to 42,
                        "world" to "42",
                        "array" to arrayOf("42"),
                        "list" to listOf("42"),
                        "map" to mapOf("42" to 42),
                        "json" to JsonStringElement("42"),
                        "null" to null,
                    ),
                ),
            ) shouldBe "{\"hello\":42,\"world\":\"42\",\"array\":[\"42\"],\"list\":[\"42\"],\"map\":{\"42\":42},\"json\":\"42\",\"null\":null}"
        }
    },
)
