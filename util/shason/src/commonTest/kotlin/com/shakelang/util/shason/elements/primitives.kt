package com.shakelang.util.shason.elements

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class JsonIntegerElementTests : FreeSpec({

    "value should return the value" {
        JsonIntegerElement(123).value shouldBe 123
    }

    "toString() should return the value as string" {
        JsonIntegerElement(123).toString() shouldBe "123"
    }

    "isNull() should return false" {
        JsonIntegerElement(123).isNull() shouldBe false
    }

    "isBoolean() should return false" {
        JsonIntegerElement(123).isBoolean() shouldBe false
    }

    "isString() should return false" {
        JsonIntegerElement(123).isString() shouldBe false
    }

    "isDouble() should return true" {
        JsonIntegerElement(123).isDouble() shouldBe true
    }

    "isInt() should return true" {
        JsonIntegerElement(123).isInt() shouldBe true
    }

    "isJsonPrimitive() should return true" {
        JsonIntegerElement(123).isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonIntegerElement(123).isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonIntegerElement(123).isJsonObject() shouldBe false
    }

    "isJsonDouble() should return true" {
        JsonIntegerElement(123).isJsonDouble() shouldBe true
    }

    "isJsonString() should return false" {
        JsonIntegerElement(123).isJsonString() shouldBe false
    }

    "isJsonInteger() should return true" {
        JsonIntegerElement(123).isJsonInteger() shouldBe true
    }

    "isJsonNull() should return false" {
        JsonIntegerElement(123).isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return false" {
        JsonIntegerElement(123).isJsonBoolean() shouldBe false
    }

    "toNull() should throw an exception" {
        runCatching { JsonIntegerElement(123).toNull() }.isFailure shouldBe true
    }

    "toBoolean() should throw an exception" {
        runCatching { JsonIntegerElement(123).toBoolean() }.isFailure shouldBe true
    }

    "toInt() should return the value" {
        JsonIntegerElement(123).toInt()
    }

    "toDouble() should return the value" {
        JsonIntegerElement(123).toDouble()
    }

    "toStringElement() should throw an exception" {
        runCatching { JsonIntegerElement(123).toStringElement() }.isFailure shouldBe true
    }

    "toJsonNull() should throw an exception" {
        runCatching { JsonIntegerElement(123).toJsonNull() }.isFailure shouldBe true
    }

    "toJsonBoolean() should throw an exception" {
        runCatching { JsonIntegerElement(123).toJsonBoolean() }.isFailure shouldBe true
    }

    "toJsonInteger() should return the value" {
        JsonIntegerElement(123).toJsonInteger()
    }

    "toJsonDouble() should return the value" {
        JsonIntegerElement(123).toJsonDouble()
    }

    "toJsonString() should throw an exception" {
        runCatching { JsonIntegerElement(123).toJsonString() }.isFailure shouldBe true
    }

    "toJsonArray() should throw an exception" {
        runCatching { JsonIntegerElement(123).toJsonArray() }.isFailure shouldBe true
    }

    "toJsonObject() should throw an exception" {
        runCatching { JsonIntegerElement(123).toJsonObject() }.isFailure shouldBe true
    }

    "toJsonPrimitive() should return the value" {
        JsonIntegerElement(123).toJsonPrimitive()
    }
})

class JsonDoubleElementTests : FreeSpec({

    "value should return the value" {
        JsonDoubleElement(123.0).value shouldBe 123.0
    }

    "toString() should return the value as string" {
        JsonDoubleElement(123.0).toString() shouldBe "123.0"
    }

    "isNull() should return false" {
        JsonDoubleElement(123.0).isNull() shouldBe false
    }

    "isBoolean() should return false" {
        JsonDoubleElement(123.0).isBoolean() shouldBe false
    }

    "isString() should return false" {
        JsonDoubleElement(123.0).isString() shouldBe false
    }

    "isDouble() should return true" {
        JsonDoubleElement(123.0).isDouble() shouldBe true
    }

    "isInt() should return false" {
        JsonDoubleElement(123.0).isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonDoubleElement(123.0).isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonDoubleElement(123.0).isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonDoubleElement(123.0).isJsonObject() shouldBe false
    }

    "isJsonDouble() should return true" {
        JsonDoubleElement(123.0).isJsonDouble() shouldBe true
    }

    "isJsonString() should return false" {
        JsonDoubleElement(123.0).isJsonString() shouldBe false
    }

    "isJsonInteger() should return false" {
        JsonDoubleElement(123.0).isJsonInteger() shouldBe false
    }

    "isJsonNull() should return false" {
        JsonDoubleElement(123.0).isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return false" {
        JsonDoubleElement(123.0).isJsonBoolean() shouldBe false
    }

    "toString() should return null if value is NaN" {
        JsonDoubleElement(Double.NaN).toString() shouldBe "null"
    }

    "toString() should return null if value is Infinity" {
        JsonDoubleElement(Double.POSITIVE_INFINITY).toString() shouldBe "null"
    }

    "toString() should return null if value is -Infinity" {
        JsonDoubleElement(Double.NEGATIVE_INFINITY).toString() shouldBe "null"
    }

    "toNull() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toNull() }.isFailure shouldBe true
    }

    "toBoolean() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toBoolean() }.isFailure shouldBe true
    }

    "toInt() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toInt() }.isFailure shouldBe true
    }

    "toDouble() should return the value" {
        JsonDoubleElement(123.0).toDouble()
    }

    "toStringElement() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toStringElement() }.isFailure shouldBe true
    }

    "toJsonNull() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toJsonNull() }.isFailure shouldBe true
    }

    "toJsonBoolean() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toJsonBoolean() }.isFailure shouldBe true
    }

    "toJsonInteger() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toJsonInteger() }.isFailure shouldBe true
    }

    "toJsonDouble() should return the value" {
        JsonDoubleElement(123.0).toJsonDouble()
    }

    "toJsonString() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toJsonString() }.isFailure shouldBe true
    }

    "toJsonArray() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toJsonArray() }.isFailure shouldBe true
    }

    "toJsonObject() should throw an exception" {
        runCatching { JsonDoubleElement(123.0).toJsonObject() }.isFailure shouldBe true
    }

    "toJsonPrimitive() should return the value" {
        JsonDoubleElement(123.0).toJsonPrimitive()
    }
})

class JsonStringElementTests : FreeSpec({

    "value should return the value" {
        JsonStringElement("123").value shouldBe "123"
    }

    "toString() should return the value as string" {
        JsonStringElement("123").toString() shouldBe "\"123\""
    }

    "isNull() should return false" {
        JsonStringElement("123").isNull() shouldBe false
    }

    "isBoolean() should return false" {
        JsonStringElement("123").isBoolean() shouldBe false
    }

    "isString() should return true" {
        JsonStringElement("123").isString() shouldBe true
    }

    "isDouble() should return false" {
        JsonStringElement("123").isDouble() shouldBe false
    }

    "isInt() should return false" {
        JsonStringElement("123").isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonStringElement("123").isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonStringElement("123").isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonStringElement("123").isJsonObject() shouldBe false
    }

    "isJsonDouble() should return false" {
        JsonStringElement("123").isJsonDouble() shouldBe false
    }

    "isJsonString() should return true" {
        JsonStringElement("123").isJsonString() shouldBe true
    }

    "isJsonInteger() should return false" {
        JsonStringElement("123").isJsonInteger() shouldBe false
    }

    "isJsonNull() should return false" {
        JsonStringElement("123").isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return false" {
        JsonStringElement("123").isJsonBoolean() shouldBe false
    }

    "toNull() should throw an exception" {
        runCatching { JsonStringElement("123").toNull() }.isFailure shouldBe true
    }

    "toBoolean() should throw an exception" {
        runCatching { JsonStringElement("123").toBoolean() }.isFailure shouldBe true
    }

    "toInt() should throw an exception" {
        runCatching { JsonStringElement("123").toInt() }.isFailure shouldBe true
    }

    "toDouble() should throw an exception" {
        runCatching { JsonStringElement("123").toDouble() }.isFailure shouldBe true
    }

    "toStringElement() should return the value" {
        JsonStringElement("123").toStringElement()
    }

    "toJsonNull() should throw an exception" {
        runCatching { JsonStringElement("123").toJsonNull() }.isFailure shouldBe true
    }

    "toJsonBoolean() should throw an exception" {
        runCatching { JsonStringElement("123").toJsonBoolean() }.isFailure shouldBe true
    }

    "toJsonInteger() should throw an exception" {
        runCatching { JsonStringElement("123").toJsonInteger() }.isFailure shouldBe true
    }

    "toJsonDouble() should throw an exception" {
        runCatching { JsonStringElement("123").toJsonDouble() }.isFailure shouldBe true
    }

    "toJsonString() should return the value" {
        JsonStringElement("123").toJsonString()
    }

    "toJsonArray() should throw an exception" {
        runCatching { JsonStringElement("123").toJsonArray() }.isFailure shouldBe true
    }

    "toJsonObject() should throw an exception" {
        runCatching { JsonStringElement("123").toJsonObject() }.isFailure shouldBe true
    }

    "toJsonPrimitive() should return the value" {
        JsonStringElement("123").toJsonPrimitive()
    }
})

class JsonBooleanElementTests : FreeSpec({

    "JsonBooleanElement.TRUE should return true" {
        JsonBooleanElement.TRUE.value shouldBe true
    }

    "JsonBooleanElement.FALSE should return false" {
        JsonBooleanElement.FALSE.value shouldBe false
    }

    "JsonBooleanElement.from(true) should return true" {
        JsonBooleanElement.from(true).value shouldBe true
    }

    "JsonBooleanElement.from(false) should return false" {
        JsonBooleanElement.from(false).value shouldBe false
    }

    "value should return the value" {
        JsonBooleanElement.TRUE.value shouldBe true
    }

    "toString() should return the value as string" {
        JsonBooleanElement.TRUE.toString() shouldBe "true"
    }

    "isNull() should return false" {
        JsonBooleanElement.TRUE.isNull() shouldBe false
    }

    "isBoolean() should return true" {
        JsonBooleanElement.TRUE.isBoolean() shouldBe true
    }

    "isString() should return false" {
        JsonBooleanElement.TRUE.isString() shouldBe false
    }

    "isDouble() should return false" {
        JsonBooleanElement.TRUE.isDouble() shouldBe false
    }

    "isInt() should return false" {
        JsonBooleanElement.TRUE.isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonBooleanElement.TRUE.isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonBooleanElement.TRUE.isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonBooleanElement.TRUE.isJsonObject() shouldBe false
    }

    "isJsonDouble() should return false" {
        JsonBooleanElement.TRUE.isJsonDouble() shouldBe false
    }

    "isJsonString() should return false" {
        JsonBooleanElement.TRUE.isJsonString() shouldBe false
    }

    "isJsonInteger() should return false" {
        JsonBooleanElement.TRUE.isJsonInteger() shouldBe false
    }

    "isJsonNull() should return false" {
        JsonBooleanElement.TRUE.isJsonNull() shouldBe false
    }

    "isJsonBoolean() should return true" {
        JsonBooleanElement.TRUE.isJsonBoolean() shouldBe true
    }

    "toNull() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toNull() }.isFailure shouldBe true
    }

    "toBoolean() should return the value" {
        JsonBooleanElement.TRUE.toBoolean()
    }

    "toInt() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toInt() }.isFailure shouldBe true
    }

    "toDouble() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toDouble() }.isFailure shouldBe true
    }

    "toStringElement() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toStringElement() }.isFailure shouldBe true
    }

    "toJsonNull() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toJsonNull() }.isFailure shouldBe true
    }

    "toJsonBoolean() should return the value" {
        JsonBooleanElement.TRUE.toJsonBoolean() shouldBe JsonBooleanElement.TRUE
    }

    "toJsonInteger() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toJsonInteger() }.isFailure shouldBe true
    }

    "toJsonDouble() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toJsonDouble() }.isFailure shouldBe true
    }

    "toJsonString() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toJsonString() }.isFailure shouldBe true
    }

    "toJsonArray() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toJsonArray() }.isFailure shouldBe true
    }

    "toJsonObject() should throw an exception" {
        runCatching { JsonBooleanElement.TRUE.toJsonObject() }.isFailure shouldBe true
    }

    "toJsonPrimitive() should return the value" {
        JsonBooleanElement.TRUE.toJsonPrimitive() shouldBe JsonBooleanElement.TRUE
    }
})

class JsonNullElementTests : FreeSpec({

    "value should return null" {
        JsonNullElement.INSTANCE.value shouldBe null
    }

    "toString() should return null" {
        JsonNullElement.INSTANCE.toString() shouldBe "null"
    }

    "isNull() should return true" {
        JsonNullElement.INSTANCE.isNull() shouldBe true
    }

    "isBoolean() should return false" {
        JsonNullElement.INSTANCE.isBoolean() shouldBe false
    }

    "isString() should return false" {
        JsonNullElement.INSTANCE.isString() shouldBe false
    }

    "isDouble() should return false" {
        JsonNullElement.INSTANCE.isDouble() shouldBe false
    }

    "isInt() should return false" {
        JsonNullElement.INSTANCE.isInt() shouldBe false
    }

    "isJsonPrimitive() should return true" {
        JsonNullElement.INSTANCE.isJsonPrimitive() shouldBe true
    }

    "isJsonArray() should return false" {
        JsonNullElement.INSTANCE.isJsonArray() shouldBe false
    }

    "isJsonObject() should return false" {
        JsonNullElement.INSTANCE.isJsonObject() shouldBe false
    }

    "isJsonDouble() should return false" {
        JsonNullElement.INSTANCE.isJsonDouble() shouldBe false
    }

    "isJsonString() should return false" {
        JsonNullElement.INSTANCE.isJsonString() shouldBe false
    }

    "isJsonInteger() should return false" {
        JsonNullElement.INSTANCE.isJsonInteger() shouldBe false
    }

    "isJsonNull() should return true" {
        JsonNullElement.INSTANCE.isJsonNull() shouldBe true
    }

    "isJsonBoolean() should return false" {
        JsonNullElement.INSTANCE.isJsonBoolean() shouldBe false
    }

    "toNull() should return null" {
        JsonNullElement.INSTANCE.toNull()
    }

    "toBoolean() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toBoolean() }.isFailure shouldBe true
    }

    "toInt() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toInt() }.isFailure shouldBe true
    }

    "toDouble() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toDouble() }.isFailure shouldBe true
    }

    "toStringElement() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toStringElement() }.isFailure shouldBe true
    }

    "toJsonNull() should return the value" {
        JsonNullElement.INSTANCE.toJsonNull() shouldBe JsonNullElement.INSTANCE
    }

    "toJsonBoolean() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toJsonBoolean() }.isFailure shouldBe true
    }

    "toJsonInteger() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toJsonInteger() }.isFailure shouldBe true
    }

    "toJsonDouble() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toJsonDouble() }.isFailure shouldBe true
    }

    "toJsonString() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toJsonString() }.isFailure shouldBe true
    }

    "toJsonArray() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toJsonArray() }.isFailure shouldBe true
    }

    "toJsonObject() should throw an exception" {
        runCatching { JsonNullElement.INSTANCE.toJsonObject() }.isFailure shouldBe true
    }

    "toJsonPrimitive() should return the value" {
        JsonNullElement.INSTANCE.toJsonPrimitive() shouldBe JsonNullElement.INSTANCE
    }
})
