package com.shakelang.shake.util.shason.processing

import com.shakelang.shake.util.shason.elements.JsonBooleanElement
import com.shakelang.shake.util.shason.elements.JsonNullElement
import com.shakelang.shake.util.shason.elements.JsonStringElement
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonGeneratorTests : FreeSpec({

    "generate byte" {
        JsonGenerator.generate(42.toByte()) shouldBe "42"
        JsonGenerator.generate(0.toByte()) shouldBe "0"
        JsonGenerator.generate((-1).toByte()) shouldBe "-1"
    }

    "generate short" {
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

    "generate double" {
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
        JsonGenerator.generate(arrayOf("hello", "world"), indent = " ".repeat(2)) shouldBe "[\n  \"hello\",\n  \"world\"\n]"
        JsonGenerator.generate(arrayOf("hello", "world"), indent = " ".repeat(4)) shouldBe "[\n    \"hello\",\n    \"world\"\n]"
    }

    "generate array nested" {
        JsonGenerator.generate(arrayOf(arrayOf("hello"))) shouldBe "[[\"hello\"]]"
        JsonGenerator.generate(arrayOf(arrayOf("hello")), indent = " ".repeat(2)) shouldBe "[\n  [\n    \"hello\"\n  ]\n]"
        JsonGenerator.generate(arrayOf(arrayOf("hello")), indent = " ".repeat(4)) shouldBe "[\n    [\n        \"hello\"\n    ]\n]"
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
        JsonGenerator.generate(listOf("hello", "world"), indent = " ".repeat(2)) shouldBe "[\n  \"hello\",\n  \"world\"\n]"
        JsonGenerator.generate(listOf("hello", "world"), indent = " ".repeat(4)) shouldBe "[\n    \"hello\",\n    \"world\"\n]"
    }

    "generate list nested" {
        JsonGenerator.generate(listOf(listOf("hello"))) shouldBe "[[\"hello\"]]"
        JsonGenerator.generate(listOf(listOf("hello")), indent = " ".repeat(2)) shouldBe "[\n  [\n    \"hello\"\n  ]\n]"
        JsonGenerator.generate(listOf(listOf("hello")), indent = " ".repeat(4)) shouldBe "[\n    [\n        \"hello\"\n    ]\n]"
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
        JsonGenerator.generate(mapOf("hello" to 1, "world" to 2), indent = " ".repeat(2)) shouldBe "{\n  \"hello\": 1,\n  \"world\": 2\n}"
        JsonGenerator.generate(mapOf("hello" to 1, "world" to 2), indent = " ".repeat(4)) shouldBe "{\n    \"hello\": 1,\n    \"world\": 2\n}"
    }

    "generate map nested" {
        JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1))) shouldBe "{\"hello\":{\"world\":1}}"
        JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1)), indent = " ".repeat(2)) shouldBe "{\n  \"hello\": {\n    \"world\": 1\n  }\n}"
        JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1)), indent = " ".repeat(4)) shouldBe "{\n    \"hello\": {\n        \"world\": 1\n    }\n}"
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
})
