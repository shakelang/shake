package io.github.shakelang.shason.processing

import io.github.shakelang.shason.elements.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JsonParserTests {

    @Test
    fun testParseInteger() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseInteger()", "42")
        assertTrue(parsed.isJsonPrimitive(), "Integer parsing should return a JsonPrimitive")
        assertTrue((parsed as JsonPrimitive).isInt(), "Integer parsing should return a JsonIntegerElement")
        assertEquals(42, parsed.toJsonPrimitive().toInt().value)
    }

    @Test
    fun testParseDouble() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseDouble()", "42.2")
        assertTrue(parsed.isJsonPrimitive(), "Double parsing should return a JsonPrimitive")
        assertTrue((parsed as JsonPrimitive).isDouble(), "Double parsing should return a JsonDoubleElement")
        assertEquals(42.2, parsed.toJsonPrimitive().toDouble().value)
    }

    @Test
    fun testParseTrue() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseTrue()", "true")
        assertTrue(parsed.isJsonPrimitive(), "Boolean parsing should return a JsonPrimitive")
        assertTrue((parsed as JsonPrimitive).isBoolean(), "Boolean parsing should return a JsonBooleanElement")
        assertEquals(true, parsed.toJsonPrimitive().toBoolean().value)
    }

    @Test
    fun testParseFalse() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseTrue()", "false")
        assertTrue(parsed.isJsonPrimitive(), "Boolean parsing should return a JsonPrimitive")
        assertTrue((parsed as JsonPrimitive).isBoolean(), "Boolean parsing should return a JsonBooleanElement")
        assertEquals(false, parsed.toJsonPrimitive().toBoolean().value)
    }

    @Test
    fun testParseString() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseString()", "\"hello\"")
        assertTrue(parsed.isJsonPrimitive(), "String parsing should return a JsonPrimitive")
        assertTrue((parsed as JsonPrimitive).isString(), "String parsing should return a JsonStringElement")
        assertEquals("hello", parsed.toJsonPrimitive().toStringElement().value)
    }

    @Test
    fun testParseArrayEmpty() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseArrayEmpty()", "[]")
        assertTrue(parsed.isJsonArray(), "Array parsing should return a JsonArray")
        assertEquals(0, parsed.toJsonArray().size, "Parsing of empty array should return an empty JsonArray")
    }

    @Test
    fun testParseArrayFilled() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseArrayFilled()", "[1, 2]")
        assertTrue(parsed.isJsonArray(), "Array parsing should return a JsonArray")
        assertEquals(2, parsed.toJsonArray().size, "Parsing of empty array should return an empty JsonArray")
        assertEquals(1, parsed.toJsonArray()[0].toJsonPrimitive().toInt().value)
        assertEquals(2, parsed.toJsonArray()[1].toJsonPrimitive().toInt().value)
    }

    @Test
    fun testParseObjectEmpty() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseObjectEmpty()", "{}")
        assertTrue(parsed.isJsonObject(), "Object parsing should return a JsonObject")
        assertEquals(0, parsed.toJsonObject().size, "Parsing of empty object should return an empty JsonObject")
    }

    @Test
    fun testParseObjectFilled() {
        val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\"one\": 1, \"two\": 2}")
        assertTrue(parsed.isJsonObject(), "Object parsing should return a JsonObject")
        assertEquals(2, parsed.toJsonObject().size)
        assertTrue(parsed.toJsonObject().contains("one"), "Should contain key one")
        assertTrue(parsed.toJsonObject().contains("two"), "Should contain key two")
        assertEquals(1, parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value)
        assertEquals(2, parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value)
    }

}