package com.shakelang.util.shason.processing

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonParserTests : FreeSpec(
    {

        "parse integer" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseInteger()", "42")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isInt() shouldBe true
            parsed.toJsonPrimitive().toInt().value shouldBe 42
        }

        "parse double" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseDouble()", "42.2")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isDouble() shouldBe true
            parsed.toJsonPrimitive().toDouble().value shouldBe 42.2
        }

        "parse true" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseTrue()", "true")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isBoolean() shouldBe true
            parsed.toJsonPrimitive().toBoolean().value shouldBe true
        }

        "parse false" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseTrue()", "false")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isBoolean() shouldBe true
            parsed.toJsonPrimitive().toBoolean().value shouldBe false
        }

        "parse string" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseString()", "\"hello\"")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isString() shouldBe true
            parsed.toJsonPrimitive().toStringElement().value shouldBe "hello"
        }

        "parse array (empty)" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseArrayEmpty()", "[]")
            parsed.isJsonArray() shouldBe true
            parsed.toJsonArray().size shouldBe 0
        }

        "parse array (filled)" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseArrayFilled()", "[1, 2]")
            parsed.isJsonArray() shouldBe true
            parsed.toJsonArray().size shouldBe 2
            parsed.toJsonArray()[0].toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonArray()[1].toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (empty)" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectEmpty()", "{}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 0
        }

        "parse object (filled)" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\"one\": 1, \"two\": 2}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with spaces" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{ \"one\" : 1, \"two\" : 2 }")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with new lines" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\n\"one\": 1,\n\"two\": 2\n}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with tabs" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\t\"two\": 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with mixed" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\n\"two\": 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with mixed and spaces" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\" : 1,\n\"two\" : 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with mixed and new lines" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\n\"two\": 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse object (filled) with mixed and tabs" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\t\"two\": 2\n}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parse null" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseNull()", "null")
            parsed.isJsonNull() shouldBe true
        }

        "parse null with spaces" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseNull()", "null ")
            parsed.isJsonNull() shouldBe true
        }

        "parse null with new lines" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseNull()", "null\n")
            parsed.isJsonNull() shouldBe true
        }

        "parse null with tabs" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseNull()", "null\t")
            parsed.isJsonNull() shouldBe true
        }
    },
)
