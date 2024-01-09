package com.shakelang.util.commander

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ValueTests : FreeSpec(
    {

        "CommanderValueValidator.accepts() should return true if no error is thrown" {
            (
                valueValidator {
                    // Silence is golden
                }
                ).accepts(null) shouldBe true
        }

        "CommanderValueValidator.accepts() should return false if an error is thrown" {
            (
                valueValidator {
                    throw ValueValidationException("Test")
                }
                ).accepts(null) shouldBe false
        }

        "#value should return the value" {
            CommanderValue("Test").value shouldBe "Test"
        }

        "#to() should return the value transformed" {
            CommanderValue("Test").to { "$it!" } shouldBe "Test!"
        }

        "#toByte() should return the value transformed to a byte" {
            CommanderValue("1").toByte() shouldBe 1.toByte()
        }

        "#toByte() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toByte() }.isFailure shouldBe true
        }

        "#toByte() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toByte() }.isFailure shouldBe true
        }

        "#toShort() should return the value transformed to a short" {
            CommanderValue("1").toShort() shouldBe 1.toShort()
        }

        "#toShort() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toShort() }.isFailure shouldBe true
        }

        "#toShort() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toShort() }.isFailure shouldBe true
        }

        "#toInt() should return the value transformed to an int" {
            CommanderValue("1").toInt() shouldBe 1
        }

        "#toInt() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toInt() }.isFailure shouldBe true
        }

        "#toInt() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toInt() }.isFailure shouldBe true
        }

        "#toLong() should return the value transformed to a long" {
            CommanderValue("1").toLong() shouldBe 1L
        }

        "#toLong() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toLong() }.isFailure shouldBe true
        }

        "#toLong() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toLong() }.isFailure shouldBe true
        }

        "#toFloat() should return the value transformed to a float" {
            CommanderValue("1").toFloat() shouldBe 1.0f
        }

        "#toFloat() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toFloat() }.isFailure shouldBe true
        }

        "#toFloat() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toFloat() }.isFailure shouldBe true
        }

        "#toDouble() should return the value transformed to a double" {
            CommanderValue("1").toDouble() shouldBe 1.0
        }

        "#toDouble() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toDouble() }.isFailure shouldBe true
        }

        "#toDouble() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toDouble() }.isFailure shouldBe true
        }

        "#toBoolean() should return the value transformed to a boolean" {
            CommanderValue("true").toBoolean() shouldBe true
        }

        "#toBoolean() should throw a NumberFormatException if the value is null" {
            runCatching { CommanderValue(null).toBoolean() }.isFailure shouldBe true
        }

        "#toBoolean() should throw a NumberFormatException if the value is is not parsable" {
            runCatching { CommanderValue("Test").toBoolean() }.isFailure shouldBe true
        }

        "#toString() should return the value transformed to a string" {
            CommanderValue("Test").toString() shouldBe "Test"
        }

        "#toString() should throw a NullPointerException if the value is null" {
            runCatching { CommanderValue(null).toString() }.isFailure shouldBe true
        }

        "#toOrNull() should return the value transformed" {
            CommanderValue("Test").toOrNull { "$it!" } shouldBe "Test!"
        }

        "#toByteOrNull() should return the value transformed to a byte" {
            CommanderValue("1").toByteOrNull() shouldBe 1.toByte()
        }

        "#toByteOrNull() should return null if the value is null" {
            CommanderValue(null).toByteOrNull() shouldBe null
        }

        "#toByteOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toByteOrNull() shouldBe null
        }

        "#toShortOrNull() should return the value transformed to a short" {
            CommanderValue("1").toShortOrNull() shouldBe 1.toShort()
        }

        "#toShortOrNull() should return null if the value is null" {
            CommanderValue(null).toShortOrNull() shouldBe null
        }

        "#toShortOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toShortOrNull() shouldBe null
        }

        "#toIntOrNull() should return the value transformed to an int" {
            CommanderValue("1").toIntOrNull() shouldBe 1
        }

        "#toIntOrNull() should return null if the value is null" {
            CommanderValue(null).toIntOrNull() shouldBe null
        }

        "#toIntOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toIntOrNull() shouldBe null
        }

        "#toLongOrNull() should return the value transformed to a long" {
            CommanderValue("1").toLongOrNull() shouldBe 1L
        }

        "#toLongOrNull() should return null if the value is null" {
            CommanderValue(null).toLongOrNull() shouldBe null
        }

        "#toLongOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toLongOrNull() shouldBe null
        }

        "#toFloatOrNull() should return the value transformed to a float" {
            CommanderValue("1").toFloatOrNull() shouldBe 1.0f
        }

        "#toFloatOrNull() should return null if the value is null" {
            CommanderValue(null).toFloatOrNull() shouldBe null
        }

        "#toFloatOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toFloatOrNull() shouldBe null
        }

        "#toDoubleOrNull() should return the value transformed to a double" {
            CommanderValue("1").toDoubleOrNull() shouldBe 1.0
        }

        "#toDoubleOrNull() should return null if the value is null" {
            CommanderValue(null).toDoubleOrNull() shouldBe null
        }

        "#toDoubleOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toDoubleOrNull() shouldBe null
        }

        "#toBooleanOrNull() should return the value transformed to a boolean" {
            CommanderValue("true").toBooleanOrNull() shouldBe true
        }

        "#toBooleanOrNull() should return null if the value is null" {
            CommanderValue(null).toBooleanOrNull() shouldBe null
        }

        "#toBooleanOrNull() should return null if the value is is not parsable" {
            CommanderValue("Test").toBooleanOrNull() shouldBe null
        }

        "#toStringOrNull() should return the value transformed to a string" {
            CommanderValue("Test").toStringOrNull() shouldBe "Test"
        }

        "#toStringOrNull() should return null if the value is null" {
            CommanderValue(null).toStringOrNull() shouldBe null
        }

        "#validate() should validate the value" {
            CommanderValue("Test").validate { } shouldBe true
        }

        "#validate() should throw a ValueValidationException if the value is not valid" {
            CommanderValue("Test").validate { throw ValueValidationException("Test") } shouldBe false
        }

        "#validateByte() should validate the value" {
            CommanderValue("1").validateByte() shouldBe true
        }

        "#validateByte() should return false if the value is null" {
            CommanderValue(null).validateByte() shouldBe false
        }

        "#validateByte() should return false if the value is is not parsable" {
            CommanderValue("Test").validateByte() shouldBe false
        }

        "#validateShort() should validate the value" {
            CommanderValue("1").validateShort() shouldBe true
        }

        "#validateShort() should return false if the value is null" {
            CommanderValue(null).validateShort() shouldBe false
        }

        "#validateShort() should return false if the value is is not parsable" {
            CommanderValue("Test").validateShort() shouldBe false
        }

        "#validateInt() should validate the value" {
            CommanderValue("1").validateInt() shouldBe true
        }

        "#validateInt() should return false if the value is null" {
            CommanderValue(null).validateInt() shouldBe false
        }

        "#validateInt() should return false if the value is is not parsable" {
            CommanderValue("Test").validateInt() shouldBe false
        }

        "#validateLong() should validate the value" {
            CommanderValue("1").validateLong() shouldBe true
        }

        "#validateLong() should return false if the value is null" {
            CommanderValue(null).validateLong() shouldBe false
        }

        "#validateLong() should return false if the value is is not parsable" {
            CommanderValue("Test").validateLong() shouldBe false
        }

        "#validateFloat() should validate the value" {
            CommanderValue("1").validateFloat() shouldBe true
        }

        "#validateFloat() should return false if the value is null" {
            CommanderValue(null).validateFloat() shouldBe false
        }

        "#validateFloat() should return false if the value is is not parsable" {
            CommanderValue("Test").validateFloat() shouldBe false
        }

        "#validateDouble() should validate the value" {
            CommanderValue("1").validateDouble() shouldBe true
        }

        "#validateDouble() should return false if the value is null" {
            CommanderValue(null).validateDouble() shouldBe false
        }

        "#validateDouble() should return false if the value is is not parsable" {
            CommanderValue("Test").validateDouble() shouldBe false
        }

        "#validateBoolean() should validate the value" {
            CommanderValue("true").validateBoolean() shouldBe true
        }

        "#validateBoolean() should return false if the value is null" {
            CommanderValue(null).validateBoolean() shouldBe false
        }

        "#validateBoolean() should return false if the value is is not parsable" {
            CommanderValue("Test").validateBoolean() shouldBe false
        }

        "#validateString() should validate the value" {
            CommanderValue("Test").validateString() shouldBe true
        }

        "#validateString() should return false if the value is null" {
            CommanderValue(null).validateString() shouldBe false
        }

        "#isNull() should return true if the value is null" {
            CommanderValue(null).isNull() shouldBe true
        }

        "#isNull() should return false if the value is not null" {
            CommanderValue("Test").isNull() shouldBe false
        }

        "#isNotNull() should return false if the value is null" {
            CommanderValue(null).isNotNull() shouldBe false
        }

        "#isNotNull() should return true if the value is not null" {
            CommanderValue("Test").isNotNull() shouldBe true
        }

        "#isByte() should return true if the value is parsable to a byte" {
            CommanderValue("1").isByte() shouldBe true
        }

        "#isByte() should return false if the value is null" {
            CommanderValue(null).isByte() shouldBe false
        }

        "#isByte() should return false if the value is not parsable to a byte" {
            CommanderValue("Test").isByte() shouldBe false
        }

        "#isShort() should return true if the value is parsable to a short" {
            CommanderValue("1").isShort() shouldBe true
        }

        "#isShort() should return false if the value is null" {
            CommanderValue(null).isShort() shouldBe false
        }

        "#isShort() should return false if the value is not parsable to a short" {
            CommanderValue("Test").isShort() shouldBe false
        }

        "#isInt() should return true if the value is parsable to an int" {
            CommanderValue("1").isInt() shouldBe true
        }

        "#isInt() should return false if the value is null" {
            CommanderValue(null).isInt() shouldBe false
        }

        "#isInt() should return false if the value is not parsable to an int" {
            CommanderValue("Test").isInt() shouldBe false
        }

        "#isLong() should return true if the value is parsable to a long" {
            CommanderValue("1").isLong() shouldBe true
        }

        "#isLong() should return false if the value is null" {
            CommanderValue(null).isLong() shouldBe false
        }

        "#isLong() should return false if the value is not parsable to a long" {
            CommanderValue("Test").isLong() shouldBe false
        }

        "#isFloat() should return true if the value is parsable to a float" {
            CommanderValue("1").isFloat() shouldBe true
        }

        "#isFloat() should return false if the value is null" {
            CommanderValue(null).isFloat() shouldBe false
        }

        "#isFloat() should return false if the value is not parsable to a float" {
            CommanderValue("Test").isFloat() shouldBe false
        }

        "#isDouble() should return true if the value is parsable to a double" {
            CommanderValue("1").isDouble() shouldBe true
        }

        "#isDouble() should return false if the value is null" {
            CommanderValue(null).isDouble() shouldBe false
        }

        "#isDouble() should return false if the value is not parsable to a double" {
            CommanderValue("Test").isDouble() shouldBe false
        }

        "#isBoolean() should return true if the value is parsable to a boolean" {
            CommanderValue("true").isBoolean() shouldBe true
        }

        "#isBoolean() should return false if the value is null" {
            CommanderValue(null).isBoolean() shouldBe false
        }

        "#isBoolean() should return false if the value is not parsable to a boolean" {
            CommanderValue("Test").isBoolean() shouldBe false
        }

        "#isString() should return true if the value is not null" {
            CommanderValue("Test").isString() shouldBe true
        }

        "#isString() should return false if the value is null" {
            CommanderValue(null).isString() shouldBe false
        }
    },
)
