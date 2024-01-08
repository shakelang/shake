package com.shakelang.util.shason.elements

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonArrayTests : FreeSpec(
    {

        "size" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.size shouldBe 5
        }

        "get" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array[0].toJsonDouble().value shouldBe 1.0
            array[1].toJsonDouble().value shouldBe 2.0
            array[2].toJsonDouble().value shouldBe 3.0
            array[3].toJsonDouble().value shouldBe 4.0
            array[4].toJsonDouble().value shouldBe 5.0
        }

        "isEmpty (not empty)" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isEmpty() shouldBe false
        }

        "isEmpty (empty)" {

            val array = JsonArray.of()
            array.isEmpty() shouldBe true
        }

        "isJsonNull" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonNull() shouldBe false
        }

        "isJsonBoolean" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonBoolean() shouldBe false
        }

        "isJsonInteger" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonInteger() shouldBe false
        }

        "isJsonDouble" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonDouble() shouldBe false
        }

        "isJsonString" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonString() shouldBe false
        }

        "isJsonArray" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonArray() shouldBe true
        }

        "isJsonObject" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonObject() shouldBe false
        }

        "isJsonPrimitive" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.isJsonPrimitive() shouldBe false
        }

        "toCollection" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.toCollection() shouldBe array
        }

        "toString" {

            val array = JsonArray.of(1, 2, 3, 4, 5)
            array.toString() shouldBe "[1,2,3,4,5]"
        }
    },
)

class MutableJsonArrayTests : FreeSpec(
    {

        "isEmpty (not empty)" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isEmpty() shouldBe false
        }

        "isEmpty (empty)" {

            val array = MutableJsonArray.of()
            array.isEmpty() shouldBe true
        }

        "isJsonNull" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonNull() shouldBe false
        }

        "isJsonBoolean" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonBoolean() shouldBe false
        }

        "isJsonInteger" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonInteger() shouldBe false
        }

        "isJsonDouble" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonDouble() shouldBe false
        }

        "isJsonString" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonString() shouldBe false
        }

        "isJsonArray" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonArray() shouldBe true
        }

        "isJsonObject" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonObject() shouldBe false
        }

        "isJsonPrimitive" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.isJsonPrimitive() shouldBe false
        }

        "toCollection" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.toCollection() shouldBe array
        }

        "toString" {

            val array = MutableJsonArray.of(1, 2, 3, 4, 5)
            array.toString() shouldBe "[1,2,3,4,5]"
        }
    },
)
