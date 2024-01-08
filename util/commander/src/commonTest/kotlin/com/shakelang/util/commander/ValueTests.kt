package com.shakelang.util.commander

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ValueTests : FreeSpec({

    "ValueValidator.accepts() should return true if no error is thrown" {
        (
            valueValidator {
                // Silence is golden
            }
            ).accepts(null) shouldBe true
    }

    "ValueValidator.accepts() should return false if an error is thrown" {
        (
            valueValidator {
                throw ValueValidationException("Test")
            }
            ).accepts(null) shouldBe false
    }

    "#value should return the value" {
        Value("Test").value shouldBe "Test"
    }

    "#to() should return the value transformed" {
        Value("Test").to { "$it!" } shouldBe "Test!"
    }

    "#toByte() should return the value transformed to a byte" {
        Value("1").toByte() shouldBe 1.toByte()
    }

    "#toByte() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toByte() }.isFailure shouldBe true
    }

    "#toByte() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toByte() }.isFailure shouldBe true
    }

    "#toShort() should return the value transformed to a short" {
        Value("1").toShort() shouldBe 1.toShort()
    }

    "#toShort() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toShort() }.isFailure shouldBe true
    }

    "#toShort() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toShort() }.isFailure shouldBe true
    }

    "#toInt() should return the value transformed to an int" {
        Value("1").toInt() shouldBe 1
    }

    "#toInt() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toInt() }.isFailure shouldBe true
    }

    "#toInt() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toInt() }.isFailure shouldBe true
    }

    "#toLong() should return the value transformed to a long" {
        Value("1").toLong() shouldBe 1L
    }

    "#toLong() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toLong() }.isFailure shouldBe true
    }

    "#toLong() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toLong() }.isFailure shouldBe true
    }

    "#toFloat() should return the value transformed to a float" {
        Value("1").toFloat() shouldBe 1.0f
    }

    "#toFloat() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toFloat() }.isFailure shouldBe true
    }

    "#toFloat() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toFloat() }.isFailure shouldBe true
    }

    "#toDouble() should return the value transformed to a double" {
        Value("1").toDouble() shouldBe 1.0
    }

    "#toDouble() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toDouble() }.isFailure shouldBe true
    }

    "#toDouble() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toDouble() }.isFailure shouldBe true
    }

    "#toBoolean() should return the value transformed to a boolean" {
        Value("true").toBoolean() shouldBe true
    }

    "#toBoolean() should throw a NumberFormatException if the value is null" {
        runCatching { Value(null).toBoolean() }.isFailure shouldBe true
    }

    "#toBoolean() should throw a NumberFormatException if the value is is not parsable" {
        runCatching { Value("Test").toBoolean() }.isFailure shouldBe true
    }

    "#toString() should return the value transformed to a string" {
        Value("Test").toString() shouldBe "Test"
    }

    "#toString() should throw a NullPointerException if the value is null" {
        runCatching { Value(null).toString() }.isFailure shouldBe true
    }

    "#toOrNull() should return the value transformed" {
        Value("Test").toOrNull { "$it!" } shouldBe "Test!"
    }

    "#toByteOrNull() should return the value transformed to a byte" {
        Value("1").toByteOrNull() shouldBe 1.toByte()
    }

    "#toByteOrNull() should return null if the value is null" {
        Value(null).toByteOrNull() shouldBe null
    }

    "#toByteOrNull() should return null if the value is is not parsable" {
        Value("Test").toByteOrNull() shouldBe null
    }

    "#toShortOrNull() should return the value transformed to a short" {
        Value("1").toShortOrNull() shouldBe 1.toShort()
    }

    "#toShortOrNull() should return null if the value is null" {
        Value(null).toShortOrNull() shouldBe null
    }

    "#toShortOrNull() should return null if the value is is not parsable" {
        Value("Test").toShortOrNull() shouldBe null
    }

    "#toIntOrNull() should return the value transformed to an int" {
        Value("1").toIntOrNull() shouldBe 1
    }

    "#toIntOrNull() should return null if the value is null" {
        Value(null).toIntOrNull() shouldBe null
    }

    "#toIntOrNull() should return null if the value is is not parsable" {
        Value("Test").toIntOrNull() shouldBe null
    }

    "#toLongOrNull() should return the value transformed to a long" {
        Value("1").toLongOrNull() shouldBe 1L
    }

    "#toLongOrNull() should return null if the value is null" {
        Value(null).toLongOrNull() shouldBe null
    }

    "#toLongOrNull() should return null if the value is is not parsable" {
        Value("Test").toLongOrNull() shouldBe null
    }

    "#toFloatOrNull() should return the value transformed to a float" {
        Value("1").toFloatOrNull() shouldBe 1.0f
    }

    "#toFloatOrNull() should return null if the value is null" {
        Value(null).toFloatOrNull() shouldBe null
    }

    "#toFloatOrNull() should return null if the value is is not parsable" {
        Value("Test").toFloatOrNull() shouldBe null
    }

    "#toDoubleOrNull() should return the value transformed to a double" {
        Value("1").toDoubleOrNull() shouldBe 1.0
    }

    "#toDoubleOrNull() should return null if the value is null" {
        Value(null).toDoubleOrNull() shouldBe null
    }

    "#toDoubleOrNull() should return null if the value is is not parsable" {
        Value("Test").toDoubleOrNull() shouldBe null
    }

    "#toBooleanOrNull() should return the value transformed to a boolean" {
        Value("true").toBooleanOrNull() shouldBe true
    }

    "#toBooleanOrNull() should return null if the value is null" {
        Value(null).toBooleanOrNull() shouldBe null
    }

    "#toBooleanOrNull() should return null if the value is is not parsable" {
        Value("Test").toBooleanOrNull() shouldBe null
    }

    "#toStringOrNull() should return the value transformed to a string" {
        Value("Test").toStringOrNull() shouldBe "Test"
    }

    "#toStringOrNull() should return null if the value is null" {
        Value(null).toStringOrNull() shouldBe null
    }

    "#validate() should validate the value" {
        Value("Test").validate { } shouldBe true
    }

    "#validate() should throw a ValueValidationException if the value is not valid" {
        Value("Test").validate { throw ValueValidationException("Test") } shouldBe false
    }

    "#validateByte() should validate the value" {
        Value("1").validateByte() shouldBe true
    }

    "#validateByte() should return false if the value is null" {
        Value(null).validateByte() shouldBe false
    }

    "#validateByte() should return false if the value is is not parsable" {
        Value("Test").validateByte() shouldBe false
    }

    "#validateShort() should validate the value" {
        Value("1").validateShort() shouldBe true
    }

    "#validateShort() should return false if the value is null" {
        Value(null).validateShort() shouldBe false
    }

    "#validateShort() should return false if the value is is not parsable" {
        Value("Test").validateShort() shouldBe false
    }

    "#validateInt() should validate the value" {
        Value("1").validateInt() shouldBe true
    }

    "#validateInt() should return false if the value is null" {
        Value(null).validateInt() shouldBe false
    }

    "#validateInt() should return false if the value is is not parsable" {
        Value("Test").validateInt() shouldBe false
    }

    "#validateLong() should validate the value" {
        Value("1").validateLong() shouldBe true
    }

    "#validateLong() should return false if the value is null" {
        Value(null).validateLong() shouldBe false
    }

    "#validateLong() should return false if the value is is not parsable" {
        Value("Test").validateLong() shouldBe false
    }

    "#validateFloat() should validate the value" {
        Value("1").validateFloat() shouldBe true
    }

    "#validateFloat() should return false if the value is null" {
        Value(null).validateFloat() shouldBe false
    }

    "#validateFloat() should return false if the value is is not parsable" {
        Value("Test").validateFloat() shouldBe false
    }

    "#validateDouble() should validate the value" {
        Value("1").validateDouble() shouldBe true
    }

    "#validateDouble() should return false if the value is null" {
        Value(null).validateDouble() shouldBe false
    }

    "#validateDouble() should return false if the value is is not parsable" {
        Value("Test").validateDouble() shouldBe false
    }

    "#validateBoolean() should validate the value" {
        Value("true").validateBoolean() shouldBe true
    }

    "#validateBoolean() should return false if the value is null" {
        Value(null).validateBoolean() shouldBe false
    }

    "#validateBoolean() should return false if the value is is not parsable" {
        Value("Test").validateBoolean() shouldBe false
    }

    "#validateString() should validate the value" {
        Value("Test").validateString() shouldBe true
    }

    "#validateString() should return false if the value is null" {
        Value(null).validateString() shouldBe false
    }

    "#isNull() should return true if the value is null" {
        Value(null).isNull() shouldBe true
    }

    "#isNull() should return false if the value is not null" {
        Value("Test").isNull() shouldBe false
    }

    "#isNotNull() should return false if the value is null" {
        Value(null).isNotNull() shouldBe false
    }

    "#isNotNull() should return true if the value is not null" {
        Value("Test").isNotNull() shouldBe true
    }

    "#isByte() should return true if the value is parsable to a byte" {
        Value("1").isByte() shouldBe true
    }

    "#isByte() should return false if the value is null" {
        Value(null).isByte() shouldBe false
    }

    "#isByte() should return false if the value is not parsable to a byte" {
        Value("Test").isByte() shouldBe false
    }

    "#isShort() should return true if the value is parsable to a short" {
        Value("1").isShort() shouldBe true
    }

    "#isShort() should return false if the value is null" {
        Value(null).isShort() shouldBe false
    }

    "#isShort() should return false if the value is not parsable to a short" {
        Value("Test").isShort() shouldBe false
    }

    "#isInt() should return true if the value is parsable to an int" {
        Value("1").isInt() shouldBe true
    }

    "#isInt() should return false if the value is null" {
        Value(null).isInt() shouldBe false
    }

    "#isInt() should return false if the value is not parsable to an int" {
        Value("Test").isInt() shouldBe false
    }

    "#isLong() should return true if the value is parsable to a long" {
        Value("1").isLong() shouldBe true
    }

    "#isLong() should return false if the value is null" {
        Value(null).isLong() shouldBe false
    }

    "#isLong() should return false if the value is not parsable to a long" {
        Value("Test").isLong() shouldBe false
    }

    "#isFloat() should return true if the value is parsable to a float" {
        Value("1").isFloat() shouldBe true
    }

    "#isFloat() should return false if the value is null" {
        Value(null).isFloat() shouldBe false
    }

    "#isFloat() should return false if the value is not parsable to a float" {
        Value("Test").isFloat() shouldBe false
    }

    "#isDouble() should return true if the value is parsable to a double" {
        Value("1").isDouble() shouldBe true
    }

    "#isDouble() should return false if the value is null" {
        Value(null).isDouble() shouldBe false
    }

    "#isDouble() should return false if the value is not parsable to a double" {
        Value("Test").isDouble() shouldBe false
    }

    "#isBoolean() should return true if the value is parsable to a boolean" {
        Value("true").isBoolean() shouldBe true
    }

    "#isBoolean() should return false if the value is null" {
        Value(null).isBoolean() shouldBe false
    }

    "#isBoolean() should return false if the value is not parsable to a boolean" {
        Value("Test").isBoolean() shouldBe false
    }

    "#isString() should return true if the value is not null" {
        Value("Test").isString() shouldBe true
    }

    "#isString() should return false if the value is null" {
        Value(null).isString() shouldBe false
    }
})
