package com.shakelang.util.shason.processing

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonParserTests : FreeSpec(
    {

        "parseInteger" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseInteger()", "42")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isInt() shouldBe true
            parsed.toJsonPrimitive().toInt().value shouldBe 42
        }

        "parseDouble" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseDouble()", "42.2")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isDouble() shouldBe true
            parsed.toJsonPrimitive().toDouble().value shouldBe 42.2
        }

        "parseTrue" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseTrue()", "true")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isBoolean() shouldBe true
            parsed.toJsonPrimitive().toBoolean().value shouldBe true
        }

        "parseFalse" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseTrue()", "false")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isBoolean() shouldBe true
            parsed.toJsonPrimitive().toBoolean().value shouldBe false
        }

        "parseString" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseString()", "\"hello\"")
            parsed.isJsonPrimitive() shouldBe true
            parsed.toJsonPrimitive().isString() shouldBe true
            parsed.toJsonPrimitive().toStringElement().value shouldBe "hello"
        }

        "parseArrayEmpty" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseArrayEmpty()", "[]")
            parsed.isJsonArray() shouldBe true
            parsed.toJsonArray().size shouldBe 0
        }

        "parseArrayFilled" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseArrayFilled()", "[1, 2]")
            parsed.isJsonArray() shouldBe true
            parsed.toJsonArray().size shouldBe 2
            parsed.toJsonArray()[0].toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonArray()[1].toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectEmpty" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectEmpty()", "{}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 0
        }

        "parseObjectFilled" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\"one\": 1, \"two\": 2}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithSpaces" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{ \"one\" : 1, \"two\" : 2 }")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithNewLines" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\n\"one\": 1,\n\"two\": 2\n}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithTabs" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\t\"two\": 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithMixed" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\n\"two\": 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithMixedAndSpaces" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\" : 1,\n\"two\" : 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithMixedAndNewLines" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\n\"two\": 2\t}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }

        "parseObjectFilledWithMixedAndTabs" {
            val parsed = TestUtilities.parse("JsonParserTests#testParseObjectFilled()", "{\t\"one\": 1,\t\"two\": 2\n}")
            parsed.isJsonObject() shouldBe true
            parsed.toJsonObject().size shouldBe 2
            parsed.toJsonObject().contains("one") shouldBe true
            parsed.toJsonObject().contains("two") shouldBe true
            parsed.toJsonObject()["one"]!!.toJsonPrimitive().toInt().value shouldBe 1
            parsed.toJsonObject()["two"]!!.toJsonPrimitive().toInt().value shouldBe 2
        }
    },
)
