package com.shakelang.shake.util.shason.processing

import com.shakelang.shake.util.shason.elements.JsonBooleanElement
import com.shakelang.shake.util.shason.elements.JsonNullElement
import com.shakelang.shake.util.shason.elements.JsonStringElement
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonGeneratorTests {

    @Test
    fun testGenerateByte() {
        assertEquals("42", JsonGenerator.generate(42.toByte()))
        assertEquals("0", JsonGenerator.generate(0.toByte()))
        assertEquals("-1", JsonGenerator.generate((-1).toByte()))
    }

    @Test
    fun testGenerateShort() {
        assertEquals("42", JsonGenerator.generate(42.toShort()))
        assertEquals("0", JsonGenerator.generate(0.toShort()))
        assertEquals("-1", JsonGenerator.generate((-1).toShort()))
    }

    @Test
    fun testGenerateInt() {
        assertEquals("42", JsonGenerator.generate(42))
        assertEquals("0", JsonGenerator.generate(0))
        assertEquals("-1", JsonGenerator.generate(-1))
    }

    @Test
    fun testGenerateLong() {
        assertEquals("42", JsonGenerator.generate(42.toLong()))
        assertEquals("0", JsonGenerator.generate(0.toLong()))
        assertEquals("-1", JsonGenerator.generate((-1).toLong()))
    }

    @Test
    fun testGenerateFloat() {
        assertEquals("42.1", JsonGenerator.generate(42.1.toFloat()))
        assertEquals("0.1", JsonGenerator.generate(0.1.toFloat()))
        assertEquals("-1.1", JsonGenerator.generate((-1.1).toFloat()))
    }

    @Test
    fun testGenerateDouble() {
        assertEquals("42.1", JsonGenerator.generate(42.1))
        assertEquals("0.1", JsonGenerator.generate(0.1))
        assertEquals("-1.1", JsonGenerator.generate(-1.1))
    }

    @Test
    fun testGenerateBoolean() {
        assertEquals("true", JsonGenerator.generate(true))
        assertEquals("false", JsonGenerator.generate(false))
    }

    @Test
    fun testGenerateArrayEmpty() {
        assertEquals("[]", JsonGenerator.generate(emptyArray<Any>()))
        assertEquals("[]", JsonGenerator.generate(emptyArray<Any>(), indentAmount = 2))
        assertEquals("[]", JsonGenerator.generate(emptyArray<Any>(), indentAmount = 4))
    }

    @Test
    fun testGenerateArrayFilledOneElement() {
        assertEquals("[\"hello\"]", JsonGenerator.generate(arrayOf("hello")))
        assertEquals("[\n  \"hello\"\n]", JsonGenerator.generate(arrayOf("hello"), indent = " ".repeat(2)))
        assertEquals("[\n    \"hello\"\n]", JsonGenerator.generate(arrayOf("hello"), indent = " ".repeat(4)))
    }

    @Test
    fun testGenerateArrayFilledMultipleElements() {
        assertEquals("[\"hello\",\"world\"]", JsonGenerator.generate(arrayOf("hello", "world")))
        assertEquals(
            "[\n  \"hello\",\n  \"world\"\n]",
            JsonGenerator.generate(arrayOf("hello", "world"), indent = " ".repeat(2))
        )
        assertEquals(
            "[\n    \"hello\",\n    \"world\"\n]",
            JsonGenerator.generate(arrayOf("hello", "world"), indent = " ".repeat(4))
        )
    }

    @Test
    fun testGenerateArrayNested() {
        assertEquals("[[\"hello\"]]", JsonGenerator.generate(arrayOf(arrayOf("hello"))))
        assertEquals(
            "[\n  [\n    \"hello\"\n  ]\n]",
            JsonGenerator.generate(arrayOf(arrayOf("hello")), indent = " ".repeat(2))
        )
        assertEquals(
            "[\n    [\n        \"hello\"\n    ]\n]",
            JsonGenerator.generate(arrayOf(arrayOf("hello")), indent = " ".repeat(4))
        )
    }

    @Test
    fun testGenerateListEmpty() {
        assertEquals("[]", JsonGenerator.generate(emptyList<Any>()))
        assertEquals("[]", JsonGenerator.generate(emptyList<Any>(), indentAmount = 2))
        assertEquals("[]", JsonGenerator.generate(emptyList<Any>(), indentAmount = 4))
    }

    @Test
    fun testGenerateListFilledOneElement() {
        assertEquals("[\"hello\"]", JsonGenerator.generate(listOf("hello")))
        assertEquals("[\n  \"hello\"\n]", JsonGenerator.generate(listOf("hello"), indent = " ".repeat(2)))
        assertEquals("[\n    \"hello\"\n]", JsonGenerator.generate(listOf("hello"), indent = " ".repeat(4)))
    }

    @Test
    fun testGenerateListFilledMultipleElements() {
        assertEquals("[\"hello\",\"world\"]", JsonGenerator.generate(listOf("hello", "world")))
        assertEquals(
            "[\n  \"hello\",\n  \"world\"\n]",
            JsonGenerator.generate(listOf("hello", "world"), indent = " ".repeat(2))
        )
        assertEquals(
            "[\n    \"hello\",\n    \"world\"\n]",
            JsonGenerator.generate(listOf("hello", "world"), indent = " ".repeat(4))
        )
    }

    @Test
    fun testGenerateListNested() {
        assertEquals("[[\"hello\"]]", JsonGenerator.generate(listOf(listOf("hello"))))
        assertEquals(
            "[\n  [\n    \"hello\"\n  ]\n]",
            JsonGenerator.generate(listOf(listOf("hello")), indent = " ".repeat(2))
        )
        assertEquals(
            "[\n    [\n        \"hello\"\n    ]\n]",
            JsonGenerator.generate(listOf(listOf("hello")), indent = " ".repeat(4))
        )
    }

    @Test
    fun testGenerateMapEmpty() {
        assertEquals("{}", JsonGenerator.generate(emptyMap<String, Any>()))
        assertEquals("{}", JsonGenerator.generate(emptyMap<String, Any>(), indentAmount = 2))
        assertEquals("{}", JsonGenerator.generate(emptyMap<String, Any>(), indentAmount = 4))
    }

    @Test
    fun testGenerateMapFilledOneElement() {
        assertEquals("{\"hello\":1}", JsonGenerator.generate(mapOf("hello" to 1)))
        assertEquals("{\n  \"hello\": 1\n}", JsonGenerator.generate(mapOf("hello" to 1), indent = " ".repeat(2)))
        assertEquals("{\n    \"hello\": 1\n}", JsonGenerator.generate(mapOf("hello" to 1), indent = " ".repeat(4)))
    }

    @Test
    fun testGenerateMapFilledMultipleElements() {
        assertEquals("{\"hello\":1,\"world\":2}", JsonGenerator.generate(mapOf("hello" to 1, "world" to 2)))
        assertEquals(
            "{\n  \"hello\": 1,\n  \"world\": 2\n}",
            JsonGenerator.generate(mapOf("hello" to 1, "world" to 2), indent = " ".repeat(2))
        )
        assertEquals(
            "{\n    \"hello\": 1,\n    \"world\": 2\n}",
            JsonGenerator.generate(mapOf("hello" to 1, "world" to 2), indent = " ".repeat(4))
        )
    }

    @Test
    fun testGenerateMapNested() {
        assertEquals("{\"hello\":{\"world\":1}}", JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1))))
        assertEquals(
            "{\n  \"hello\": {\n    \"world\": 1\n  }\n}",
            JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1)), indent = " ".repeat(2))
        )
        assertEquals(
            "{\n    \"hello\": {\n        \"world\": 1\n    }\n}",
            JsonGenerator.generate(mapOf("hello" to mapOf("world" to 1)), indent = " ".repeat(4))
        )
    }

    @Test
    fun testGenerateJsonElement() {
        assertEquals("\"42\"", JsonGenerator.generate(JsonStringElement("42")))
        assertEquals("null", JsonGenerator.generate(JsonNullElement.INSTANCE))
        assertEquals("null", JsonGenerator.generate(JsonNullElement.INSTANCE, indentAmount = 2))
        assertEquals("null", JsonGenerator.generate(JsonNullElement.INSTANCE, indentAmount = 4))
        assertEquals("\"42\"", JsonGenerator.generate(JsonStringElement("42"), indentAmount = 2))
        assertEquals("true", JsonGenerator.generate(JsonBooleanElement.TRUE))
        assertEquals("false", JsonGenerator.generate(JsonBooleanElement.FALSE))
    }
}
