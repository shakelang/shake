package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderValueTests : FreeSpec(
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

        "CommanderValue.to(CommanderValueTransformer) should return the transformed value" {
            CommanderValue("test").to(valueTransformer { it + "2" }) shouldBe "test2"
        }

        "CommanderValue.to(CommanderValueTransformer) should pass through exceptions" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").to<String>(valueTransformer { throw CommanderValueException("Test") })
            }
        }

        "CommanderValue.toOrNull(CommanderValueTransformer) should return the transformed value" {
            CommanderValue("test").toOrNull(valueTransformer { it + "2" }) shouldBe "test2"
        }

        "CommanderValue.toOrNull(CommanderValueTransformer) should return null if an CommanderValueException is thrown" {
            CommanderValue("test").toOrNull<String>(valueTransformer { throw CommanderValueException("Test") }) shouldBe null
        }

        "CommanderValue.toOrNull(CommanderValueTransformer) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").toOrNull<String>(valueTransformer { throw Exception("Test") })
            }
        }

        "CommanderValue.toOrDefault(CommanderValueTransformer) should return the transformed value" {
            CommanderValue("test").toOrDefault(valueTransformer { it + "2" }, "default") shouldBe "test2"
        }

        "CommanderValue.toOrDefault(CommanderValueTransformer) should return the default value if an CommanderValueException is thrown" {
            CommanderValue("test").toOrDefault(valueTransformer { throw CommanderValueException("Test") }, "default") shouldBe "default"
        }

        "CommanderValue.toOrDefault(CommanderValueTransformer) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").toOrDefault(valueTransformer { throw Exception("Test") }, "default")
            }
        }

        "CommanderValue.to(CommanderValueTransformerLambda) should return the transformed value" {
            CommanderValue("test").to { it + "2" } shouldBe "test2"
        }

        "CommanderValue.to(CommanderValueTransformerLambda) should pass through exceptions" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").to<String> { throw CommanderValueException("Test") }
            }
        }

        "CommanderValue.toOrNull(CommanderValueTransformerLambda) should return the transformed value" {
            CommanderValue("test").toOrNull { it + "2" } shouldBe "test2"
        }

        "CommanderValue.toOrNull(CommanderValueTransformerLambda) should return null if an CommanderValueException is thrown" {
            CommanderValue("test").toOrNull<String> { throw CommanderValueException("Test") } shouldBe null
        }

        "CommanderValue.toOrNull(CommanderValueTransformerLambda) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").toOrNull<String> { throw Exception("Test") }
            }
        }

        "CommanderValue.toOrDefault(CommanderValueTransformerLambda) should return the transformed value" {
            CommanderValue("test").toOrDefault({ it + "2" }, "default") shouldBe "test2"
        }

        "CommanderValue.toOrDefault(CommanderValueTransformerLambda) should return the default value if an CommanderValueException is thrown" {
            CommanderValue("test").toOrDefault({ throw CommanderValueException("Test") }, "default") shouldBe "default"
        }

        "CommanderValue.toOrDefault(CommanderValueTransformerLambda) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").toOrDefault({ throw Exception("Test") }, "default")
            }
        }

        "CommanderValue.toByte() should return the transformed value" {
            CommanderValue("1").toByte() shouldBe 1.toByte()
        }

        "CommanderValue.toByte() should throw an CommanderValueException if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toByte()
            }
        }

        "CommanderValue.toByteOrNull() should return the transformed value" {
            CommanderValue("1").toByteOrNull() shouldBe 1.toByte()
        }

        "CommanderValue.toByteOrNull() should return null if the value is not a byte" {
            CommanderValue("test").toByteOrNull() shouldBe null
        }

        "CommanderValue.toByteOrDefault() should return the transformed value" {
            CommanderValue("1").toByteOrDefault(2.toByte()) shouldBe 1.toByte()
        }

        "CommanderValue.toByteOrDefault() should return the default value if the value is not a byte" {
            CommanderValue("test").toByteOrDefault(2.toByte()) shouldBe 2.toByte()
        }

        "CommanderValue.toShort() should return the transformed value" {
            CommanderValue("1").toShort() shouldBe 1.toShort()
        }

        "CommanderValue.toShort() should throw an CommanderValueException if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toShort()
            }
        }

        "CommanderValue.toShortOrNull() should return the transformed value" {
            CommanderValue("1").toShortOrNull() shouldBe 1.toShort()
        }

        "CommanderValue.toShortOrNull() should return null if the value is not a shorts" {
            CommanderValue("test").toShortOrNull() shouldBe null
        }

        "CommanderValue.toShortOrDefault() should return the transformed value" {
            CommanderValue("1").toShortOrDefault(2.toShort()) shouldBe 1.toShort()
        }

        "CommanderValue.toShortOrDefault() should return the default value if the value is not a shorts" {
            CommanderValue("test").toShortOrDefault(2.toShort()) shouldBe 2.toShort()
        }

        "CommanderValue.toInt() should return the transformed value" {
            CommanderValue("1").toInt() shouldBe 1
        }

        "CommanderValue.toInt() should throw an CommanderValueException if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toInt()
            }
        }

        "CommanderValue.toIntOrNull() should return the transformed value" {
            CommanderValue("1").toIntOrNull() shouldBe 1
        }

        "CommanderValue.toIntOrNull() should return null if the value is not a int" {
            CommanderValue("test").toIntOrNull() shouldBe null
        }

        "CommanderValue.toIntOrDefault() should return the transformed value" {
            CommanderValue("1").toIntOrDefault(2) shouldBe 1
        }

        "CommanderValue.toIntOrDefault() should return the default value if the value is not a int" {
            CommanderValue("test").toIntOrDefault(2) shouldBe 2
        }

        "CommanderValue.toLong() should return the transformed value" {
            CommanderValue("1").toLong() shouldBe 1L
        }

        "CommanderValue.toLong() should throw an CommanderValueException if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toLong()
            }
        }

        "CommanderValue.toLongOrNull() should return the transformed value" {
            CommanderValue("1").toLongOrNull() shouldBe 1L
        }

        "CommanderValue.toLongOrNull() should return null if the value is not a long" {
            CommanderValue("test").toLongOrNull() shouldBe null
        }

        "CommanderValue.toLongOrDefault() should return the transformed value" {
            CommanderValue("1").toLongOrDefault(2L) shouldBe 1L
        }

        "CommanderValue.toLongOrDefault() should return the default value if the value is not a long" {
            CommanderValue("test").toLongOrDefault(2L) shouldBe 2L
        }

        "CommanderValue.toFloat() should return the transformed value" {
            CommanderValue("1").toFloat() shouldBe 1f
        }

        "CommanderValue.toFloat() should throw an CommanderValueException if the value is not a float" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toFloat()
            }
        }

        "CommanderValue.toFloatOrNull() should return the transformed value" {
            CommanderValue("1").toFloatOrNull() shouldBe 1f
        }

        "CommanderValue.toFloatOrNull() should return null if the value is not a float" {
            CommanderValue("test").toFloatOrNull() shouldBe null
        }

        "CommanderValue.toFloatOrDefault() should return the transformed value" {
            CommanderValue("1").toFloatOrDefault(2f) shouldBe 1f
        }

        "CommanderValue.toFloatOrDefault() should return the default value if the value is not a float" {
            CommanderValue("test").toFloatOrDefault(2f) shouldBe 2f
        }

        "CommanderValue.toDouble() should return the transformed value" {
            CommanderValue("1").toDouble() shouldBe 1.0
        }

        "CommanderValue.toDouble() should throw an CommanderValueException if the value is not a doubles" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toDouble()
            }
        }

        "CommanderValue.toDoubleOrNull() should return the transformed value" {
            CommanderValue("1").toDoubleOrNull() shouldBe 1.0
        }

        "CommanderValue.toDoubleOrNull() should return null if the value is not a doubles" {
            CommanderValue("test").toDoubleOrNull() shouldBe null
        }

        "CommanderValue.toDoubleOrDefault() should return the transformed value" {
            CommanderValue("1").toDoubleOrDefault(2.0) shouldBe 1.0
        }

        "CommanderValue.toDoubleOrDefault() should return the default value if the value is not a doubles" {
            CommanderValue("test").toDoubleOrDefault(2.0) shouldBe 2.0
        }

        "CommanderValue.toUnsignedByte() should return the transformed value" {
            CommanderValue("1").toUnsignedByte() shouldBe 1.toUByte()
        }

        "CommanderValue.toUnsignedByte() should throw an CommanderValueException if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toUnsignedByte()
            }
        }

        "CommanderValue.toUnsignedByteOrNull() should return the transformed value" {
            CommanderValue("1").toUnsignedByteOrNull() shouldBe 1.toUByte()
        }

        "CommanderValue.toUnsignedByteOrNull() should return null if the value is not a byte" {
            CommanderValue("test").toUnsignedByteOrNull() shouldBe null
        }

        "CommanderValue.toUnsignedByteOrDefault() should return the transformed value" {
            CommanderValue("1").toUnsignedByteOrDefault(2.toUByte()) shouldBe 1.toUByte()
        }

        "CommanderValue.toUnsignedByteOrDefault() should return the default value if the value is not a byte" {
            CommanderValue("test").toUnsignedByteOrDefault(2.toUByte()) shouldBe 2.toUByte()
        }

        "CommanderValue.toUnsignedShort() should return the transformed value" {
            CommanderValue("1").toUnsignedShort() shouldBe 1.toUShort()
        }

        "CommanderValue.toUnsignedShort() should throw an CommanderValueException if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toUnsignedShort()
            }
        }

        "CommanderValue.toUnsignedShortOrNull() should return the transformed value" {
            CommanderValue("1").toUnsignedShortOrNull() shouldBe 1.toUShort()
        }

        "CommanderValue.toUnsignedShortOrNull() should return null if the value is not a shorts" {
            CommanderValue("test").toUnsignedShortOrNull() shouldBe null
        }

        "CommanderValue.toUnsignedShortOrDefault() should return the transformed value" {
            CommanderValue("1").toUnsignedShortOrDefault(2.toUShort()) shouldBe 1.toUShort()
        }

        "CommanderValue.toUnsignedShortOrDefault() should return the default value if the value is not a shorts" {
            CommanderValue("test").toUnsignedShortOrDefault(2.toUShort()) shouldBe 2.toUShort()
        }

        "CommanderValue.toUnsignedInt() should return the transformed value" {
            CommanderValue("1").toUnsignedInt() shouldBe 1.toUInt()
        }

        "CommanderValue.toUnsignedInt() should throw an CommanderValueException if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toUnsignedInt()
            }
        }

        "CommanderValue.toUnsignedIntOrNull() should return the transformed value" {
            CommanderValue("1").toUnsignedIntOrNull() shouldBe 1.toUInt()
        }

        "CommanderValue.toUnsignedIntOrNull() should return null if the value is not a int" {
            CommanderValue("test").toUnsignedIntOrNull() shouldBe null
        }

        "CommanderValue.toUnsignedIntOrDefault() should return the transformed value" {
            CommanderValue("1").toUnsignedIntOrDefault(2.toUInt()) shouldBe 1.toUInt()
        }

        "CommanderValue.toUnsignedIntOrDefault() should return the default value if the value is not a int" {
            CommanderValue("test").toUnsignedIntOrDefault(2.toUInt()) shouldBe 2.toUInt()
        }

        "CommanderValue.toUnsignedLong() should return the transformed value" {
            CommanderValue("1").toUnsignedLong() shouldBe 1.toULong()
        }

        "CommanderValue.toUnsignedLong() should throw an CommanderValueException if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toUnsignedLong()
            }
        }

        "CommanderValue.toUnsignedLongOrNull() should return the transformed value" {
            CommanderValue("1").toUnsignedLongOrNull() shouldBe 1.toULong()
        }

        "CommanderValue.toUnsignedLongOrNull() should return null if the value is not a long" {
            CommanderValue("test").toUnsignedLongOrNull() shouldBe null
        }

        "CommanderValue.toUnsignedLongOrDefault() should return the transformed value" {
            CommanderValue("1").toUnsignedLongOrDefault(2.toULong()) shouldBe 1.toULong()
        }

        "CommanderValue.toUnsignedLongOrDefault() should return the default value if the value is not a long" {
            CommanderValue("test").toUnsignedLongOrDefault(2.toULong()) shouldBe 2.toULong()
        }

        "CommanderValue.toBoolean() should return the transformed value" {
            CommanderValue("true").toBoolean() shouldBe true
        }

        "CommanderValue.toBoolean() should throw an CommanderValueException if the value is not a boolean" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toBoolean()
            }
        }

        "CommanderValue.toBooleanOrNull() should return the transformed value" {
            CommanderValue("true").toBooleanOrNull() shouldBe true
        }

        "CommanderValue.toBooleanOrNull() should return null if the value is not a boolean" {
            CommanderValue("test").toBooleanOrNull() shouldBe null
        }

        "CommanderValue.toBooleanOrDefault() should return the transformed value" {
            CommanderValue("true").toBooleanOrDefault(false) shouldBe true
        }

        "CommanderValue.toBooleanOrDefault() should return the default value if the value is not a boolean" {
            CommanderValue("test").toBooleanOrDefault(false) shouldBe false
        }

        "CommanderValue.toChar() should return the transformed value" {
            CommanderValue("a").toChar() shouldBe 'a'
        }

        "CommanderValue.toChar() should throw an CommanderValueException if the value is not a char" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toChar()
            }
        }

        "CommanderValue.toCharOrNull() should return the transformed value" {
            CommanderValue("a").toCharOrNull() shouldBe 'a'
        }

        "CommanderValue.toCharOrNull() should return null if the value is not a char" {
            CommanderValue("test").toCharOrNull() shouldBe null
        }

        "CommanderValue.toCharOrDefault() should return the transformed value" {
            CommanderValue("a").toCharOrDefault('b') shouldBe 'a'
        }

        "CommanderValue.toCharOrDefault() should return the default value if the value is not a char" {
            CommanderValue("test").toCharOrDefault('b') shouldBe 'b'
        }

        "CommanderValue.toString() should return the transformed value" {
            CommanderValue("test").toString() shouldBe "test"
        }

        "CommanderValue.toString() should throw an CommanderValueException if the value is null" {
            shouldThrow<CommanderValueException> {
                CommanderValue(null).toString()
            }
        }

        "CommanderValue.toStringOrNull() should return the transformed value" {
            CommanderValue("test").toStringOrNull() shouldBe "test"
        }

        "CommanderValue.toStringOrNull() should return null if the value is null" {
            CommanderValue(null).toStringOrNull() shouldBe null
        }

        "CommanderValue.toStringOrDefault() should return the transformed value" {
            CommanderValue("test").toStringOrDefault("default") shouldBe "test"
        }

        "CommanderValue.toStringOrDefault() should return the default value if the value is null" {
            CommanderValue(null).toStringOrDefault("default") shouldBe "default"
        }

        "CommanderValue.toPositiveByte() should return the transformed value" {
            CommanderValue("1").toPositiveByte() shouldBe 1.toByte()
        }

        "CommanderValue.toPositiveByte() should throw an CommanderValueException if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toPositiveByte()
            }
        }

        "CommanderValue.toPositiveByte() should throw an CommanderValueException if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").toPositiveByte()
            }
        }

        "CommanderValue.toPositiveByteOrNull() should return the transformed value" {
            CommanderValue("1").toPositiveByteOrNull() shouldBe 1.toByte()
        }

        "CommanderValue.toPositiveByteOrNull() should return null if the value is not a byte" {
            CommanderValue("test").toPositiveByteOrNull() shouldBe null
        }

        "CommanderValue.toPositiveByteOrNull() should return null if the value is negative" {
            CommanderValue("-1").toPositiveByteOrNull() shouldBe null
        }

        "CommanderValue.toPositiveByteOrDefault() should return the transformed value" {
            CommanderValue("1").toPositiveByteOrDefault(2.toByte()) shouldBe 1.toByte()
        }

        "CommanderValue.toPositiveByteOrDefault() should return the default value if the value is not a byte" {
            CommanderValue("test").toPositiveByteOrDefault(2.toByte()) shouldBe 2.toByte()
        }

        "CommanderValue.toPositiveByteOrDefault() should return the default value if the value is negative" {
            CommanderValue("-1").toPositiveByteOrDefault(2.toByte()) shouldBe 2.toByte()
        }

        "CommanderValue.toPositiveShort() should return the transformed value" {
            CommanderValue("1").toPositiveShort() shouldBe 1.toShort()
        }

        "CommanderValue.toPositiveShort() should throw an CommanderValueException if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toPositiveShort()
            }
        }

        "CommanderValue.toPositiveShort() should throw an CommanderValueException if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").toPositiveShort()
            }
        }

        "CommanderValue.toPositiveShortOrNull() should return the transformed value" {
            CommanderValue("1").toPositiveShortOrNull() shouldBe 1.toShort()
        }

        "CommanderValue.toPositiveShortOrNull() should return null if the value is not a shorts" {
            CommanderValue("test").toPositiveShortOrNull() shouldBe null
        }

        "CommanderValue.toPositiveShortOrNull() should return null if the value is negative" {
            CommanderValue("-1").toPositiveShortOrNull() shouldBe null
        }

        "CommanderValue.toPositiveShortOrDefault() should return the transformed value" {
            CommanderValue("1").toPositiveShortOrDefault(2.toShort()) shouldBe 1.toShort()
        }

        "CommanderValue.toPositiveShortOrDefault() should return the default value if the value is not a shorts" {
            CommanderValue("test").toPositiveShortOrDefault(2.toShort()) shouldBe 2.toShort()
        }

        "CommanderValue.toPositiveShortOrDefault() should return the default value if the value is negative" {
            CommanderValue("-1").toPositiveShortOrDefault(2.toShort()) shouldBe 2.toShort()
        }

        "CommanderValue.toPositiveInt() should return the transformed value" {
            CommanderValue("1").toPositiveInt() shouldBe 1
        }

        "CommanderValue.toPositiveInt() should throw an CommanderValueException if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toPositiveInt()
            }
        }

        "CommanderValue.toPositiveInt() should throw an CommanderValueException if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").toPositiveInt()
            }
        }

        "CommanderValue.toPositiveIntOrNull() should return the transformed value" {
            CommanderValue("1").toPositiveIntOrNull() shouldBe 1
        }

        "CommanderValue.toPositiveIntOrNull() should return null if the value is not a int" {
            CommanderValue("test").toPositiveIntOrNull() shouldBe null
        }

        "CommanderValue.toPositiveIntOrNull() should return null if the value is negative" {
            CommanderValue("-1").toPositiveIntOrNull() shouldBe null
        }

        "CommanderValue.toPositiveIntOrDefault() should return the transformed value" {
            CommanderValue("1").toPositiveIntOrDefault(2) shouldBe 1
        }

        "CommanderValue.toPositiveIntOrDefault() should return the default value if the value is not a int" {
            CommanderValue("test").toPositiveIntOrDefault(2) shouldBe 2
        }

        "CommanderValue.toPositiveIntOrDefault() should return the default value if the value is negative" {
            CommanderValue("-1").toPositiveIntOrDefault(2) shouldBe 2
        }

        "CommanderValue.toPositiveLong() should return the transformed value" {
            CommanderValue("1").toPositiveLong() shouldBe 1L
        }

        "CommanderValue.toPositiveLong() should throw an CommanderValueException if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toPositiveLong()
            }
        }

        "CommanderValue.toPositiveLong() should throw an CommanderValueException if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").toPositiveLong()
            }
        }

        "CommanderValue.toPositiveLongOrNull() should return the transformed value" {
            CommanderValue("1").toPositiveLongOrNull() shouldBe 1L
        }

        "CommanderValue.toPositiveLongOrNull() should return null if the value is not a long" {
            CommanderValue("test").toPositiveLongOrNull() shouldBe null
        }

        "CommanderValue.toPositiveLongOrNull() should return null if the value is negative" {
            CommanderValue("-1").toPositiveLongOrNull() shouldBe null
        }

        "CommanderValue.toPositiveLongOrDefault() should return the transformed value" {
            CommanderValue("1").toPositiveLongOrDefault(2L) shouldBe 1L
        }

        "CommanderValue.toPositiveLongOrDefault() should return the default value if the value is not a long" {
            CommanderValue("test").toPositiveLongOrDefault(2L) shouldBe 2L
        }

        "CommanderValue.toPositiveLongOrDefault() should return the default value if the value is negative" {
            CommanderValue("-1").toPositiveLongOrDefault(2L) shouldBe 2L
        }

        "CommanderValue.toPositiveFloat() should return the transformed value" {
            CommanderValue("1").toPositiveFloat() shouldBe 1f
        }

        "CommanderValue.toPositiveFloat() should throw an CommanderValueException if the value is not a float" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toPositiveFloat()
            }
        }

        "CommanderValue.toPositiveFloat() should throw an CommanderValueException if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").toPositiveFloat()
            }
        }

        "CommanderValue.toPositiveFloatOrNull() should return the transformed value" {
            CommanderValue("1").toPositiveFloatOrNull() shouldBe 1f
        }

        "CommanderValue.toPositiveFloatOrNull() should return null if the value is not a float" {
            CommanderValue("test").toPositiveFloatOrNull() shouldBe null
        }

        "CommanderValue.toPositiveFloatOrNull() should return null if the value is negative" {
            CommanderValue("-1").toPositiveFloatOrNull() shouldBe null
        }

        "CommanderValue.toPositiveFloatOrDefault() should return the transformed value" {
            CommanderValue("1").toPositiveFloatOrDefault(2f) shouldBe 1f
        }

        "CommanderValue.toPositiveFloatOrDefault() should return the default value if the value is not a float" {
            CommanderValue("test").toPositiveFloatOrDefault(2f) shouldBe 2f
        }

        "CommanderValue.toPositiveFloatOrDefault() should return the default value if the value is negative" {
            CommanderValue("-1").toPositiveFloatOrDefault(2f) shouldBe 2f
        }

        "CommanderValue.toPositiveDouble() should return the transformed value" {
            CommanderValue("1").toPositiveDouble() shouldBe 1.0
        }

        "CommanderValue.toPositiveDouble() should throw an CommanderValueException if the value is not a doubles" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toPositiveDouble()
            }
        }

        "CommanderValue.toPositiveDouble() should throw an CommanderValueException if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").toPositiveDouble()
            }
        }

        "CommanderValue.toPositiveDoubleOrNull() should return the transformed value" {
            CommanderValue("1").toPositiveDoubleOrNull() shouldBe 1.0
        }

        "CommanderValue.toPositiveDoubleOrNull() should return null if the value is not a doubles" {
            CommanderValue("test").toPositiveDoubleOrNull() shouldBe null
        }

        "CommanderValue.toPositiveDoubleOrNull() should return null if the value is negative" {
            CommanderValue("-1").toPositiveDoubleOrNull() shouldBe null
        }

        "CommanderValue.toPositiveDoubleOrDefault() should return the transformed value" {
            CommanderValue("1").toPositiveDoubleOrDefault(2.0) shouldBe 1.0
        }

        "CommanderValue.toPositiveDoubleOrDefault() should return the default value if the value is not a doubles" {
            CommanderValue("test").toPositiveDoubleOrDefault(2.0) shouldBe 2.0
        }

        "CommanderValue.toPositiveDoubleOrDefault() should return the default value if the value is negative" {
            CommanderValue("-1").toPositiveDoubleOrDefault(2.0) shouldBe 2.0
        }

        "CommanderValue.toNegativeByte() should return the transformed value" {
            CommanderValue("-1").toNegativeByte() shouldBe (-1).toByte()
        }

        "CommanderValue.toNegativeByte() should throw an CommanderValueException if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toNegativeByte()
            }
        }

        "CommanderValue.toNegativeByte() should throw an CommanderValueException if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").toNegativeByte()
            }
        }

        "CommanderValue.toNegativeByteOrNull() should return the transformed value" {
            CommanderValue("-1").toNegativeByteOrNull() shouldBe (-1).toByte()
        }

        "CommanderValue.toNegativeByteOrNull() should return null if the value is not a byte" {
            CommanderValue("test").toNegativeByteOrNull() shouldBe null
        }

        "CommanderValue.toNegativeByteOrNull() should return null if the value is positive" {
            CommanderValue("1").toNegativeByteOrNull() shouldBe null
        }

        "CommanderValue.toNegativeByteOrDefault() should return the transformed value" {
            CommanderValue("-1").toNegativeByteOrDefault(2.toByte()) shouldBe (-1).toByte()
        }

        "CommanderValue.toNegativeByteOrDefault() should return the default value if the value is not a byte" {
            CommanderValue("test").toNegativeByteOrDefault(2.toByte()) shouldBe 2.toByte()
        }

        "CommanderValue.toNegativeByteOrDefault() should return the default value if the value is positive" {
            CommanderValue("1").toNegativeByteOrDefault(2.toByte()) shouldBe 2.toByte()
        }

        "CommanderValue.toNegativeShort() should return the transformed value" {
            CommanderValue("-1").toNegativeShort() shouldBe (-1).toShort()
        }

        "CommanderValue.toNegativeShort() should throw an CommanderValueException if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toNegativeShort()
            }
        }

        "CommanderValue.toNegativeShort() should throw an CommanderValueException if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").toNegativeShort()
            }
        }

        "CommanderValue.toNegativeShortOrNull() should return the transformed value" {
            CommanderValue("-1").toNegativeShortOrNull() shouldBe (-1).toShort()
        }

        "CommanderValue.toNegativeShortOrNull() should return null if the value is not a shorts" {
            CommanderValue("test").toNegativeShortOrNull() shouldBe null
        }

        "CommanderValue.toNegativeShortOrNull() should return null if the value is positive" {
            CommanderValue("1").toNegativeShortOrNull() shouldBe null
        }

        "CommanderValue.toNegativeShortOrDefault() should return the transformed value" {
            CommanderValue("-1").toNegativeShortOrDefault(2.toShort()) shouldBe (-1).toShort()
        }

        "CommanderValue.toNegativeShortOrDefault() should return the default value if the value is not a shorts" {
            CommanderValue("test").toNegativeShortOrDefault(2.toShort()) shouldBe 2.toShort()
        }

        "CommanderValue.toNegativeShortOrDefault() should return the default value if the value is positive" {
            CommanderValue("1").toNegativeShortOrDefault(2.toShort()) shouldBe 2.toShort()
        }

        "CommanderValue.toNegativeInt() should return the transformed value" {
            CommanderValue("-1").toNegativeInt() shouldBe -1
        }

        "CommanderValue.toNegativeInt() should throw an CommanderValueException if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toNegativeInt()
            }
        }

        "CommanderValue.toNegativeInt() should throw an CommanderValueException if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").toNegativeInt()
            }
        }

        "CommanderValue.toNegativeIntOrNull() should return the transformed value" {
            CommanderValue("-1").toNegativeIntOrNull() shouldBe -1
        }

        "CommanderValue.toNegativeIntOrNull() should return null if the value is not a int" {
            CommanderValue("test").toNegativeIntOrNull() shouldBe null
        }

        "CommanderValue.toNegativeIntOrNull() should return null if the value is positive" {
            CommanderValue("1").toNegativeIntOrNull() shouldBe null
        }

        "CommanderValue.toNegativeIntOrDefault() should return the transformed value" {
            CommanderValue("-1").toNegativeIntOrDefault(2) shouldBe -1
        }

        "CommanderValue.toNegativeIntOrDefault() should return the default value if the value is not a int" {
            CommanderValue("test").toNegativeIntOrDefault(2) shouldBe 2
        }

        "CommanderValue.toNegativeIntOrDefault() should return the default value if the value is positive" {
            CommanderValue("1").toNegativeIntOrDefault(2) shouldBe 2
        }

        "CommanderValue.toNegativeLong() should return the transformed value" {
            CommanderValue("-1").toNegativeLong() shouldBe -1L
        }

        "CommanderValue.toNegativeLong() should throw an CommanderValueException if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toNegativeLong()
            }
        }

        "CommanderValue.toNegativeLong() should throw an CommanderValueException if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").toNegativeLong()
            }
        }

        "CommanderValue.toNegativeLongOrNull() should return the transformed value" {
            CommanderValue("-1").toNegativeLongOrNull() shouldBe -1L
        }

        "CommanderValue.toNegativeLongOrNull() should return null if the value is not a long" {
            CommanderValue("test").toNegativeLongOrNull() shouldBe null
        }

        "CommanderValue.toNegativeLongOrNull() should return null if the value is positive" {
            CommanderValue("1").toNegativeLongOrNull() shouldBe null
        }

        "CommanderValue.toNegativeLongOrDefault() should return the transformed value" {
            CommanderValue("-1").toNegativeLongOrDefault(2L) shouldBe -1L
        }

        "CommanderValue.toNegativeLongOrDefault() should return the default value if the value is not a long" {
            CommanderValue("test").toNegativeLongOrDefault(2L) shouldBe 2L
        }

        "CommanderValue.toNegativeLongOrDefault() should return the default value if the value is positive" {
            CommanderValue("1").toNegativeLongOrDefault(2L) shouldBe 2L
        }

        "CommanderValue.toNegativeFloat() should return the transformed value" {
            CommanderValue("-1").toNegativeFloat() shouldBe -1f
        }

        "CommanderValue.toNegativeFloat() should throw an CommanderValueException if the value is not a float" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toNegativeFloat()
            }
        }

        "CommanderValue.toNegativeFloat() should throw an CommanderValueException if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").toNegativeFloat()
            }
        }

        "CommanderValue.toNegativeFloatOrNull() should return the transformed value" {
            CommanderValue("-1").toNegativeFloatOrNull() shouldBe -1f
        }

        "CommanderValue.toNegativeFloatOrNull() should return null if the value is not a float" {
            CommanderValue("test").toNegativeFloatOrNull() shouldBe null
        }

        "CommanderValue.toNegativeFloatOrNull() should return null if the value is positive" {
            CommanderValue("1").toNegativeFloatOrNull() shouldBe null
        }

        "CommanderValue.toNegativeFloatOrDefault() should return the transformed value" {
            CommanderValue("-1").toNegativeFloatOrDefault(2f) shouldBe -1f
        }

        "CommanderValue.toNegativeFloatOrDefault() should return the default value if the value is not a float" {
            CommanderValue("test").toNegativeFloatOrDefault(2f) shouldBe 2f
        }

        "CommanderValue.toNegativeFloatOrDefault() should return the default value if the value is positive" {
            CommanderValue("1").toNegativeFloatOrDefault(2f) shouldBe 2f
        }

        "CommanderValue.toNegativeDouble() should return the transformed value" {
            CommanderValue("-1").toNegativeDouble() shouldBe -1.0
        }

        "CommanderValue.toNegativeDouble() should throw an CommanderValueException if the value is not a doubles" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").toNegativeDouble()
            }
        }

        "CommanderValue.toNegativeDouble() should throw an CommanderValueException if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").toNegativeDouble()
            }
        }

        "CommanderValue.toNegativeDoubleOrNull() should return the transformed value" {
            CommanderValue("-1").toNegativeDoubleOrNull() shouldBe -1.0
        }

        "CommanderValue.toNegativeDoubleOrNull() should return null if the value is not a doubles" {
            CommanderValue("test").toNegativeDoubleOrNull() shouldBe null
        }

        "CommanderValue.toNegativeDoubleOrNull() should return null if the value is positive" {
            CommanderValue("1").toNegativeDoubleOrNull() shouldBe null
        }

        "CommanderValue.toNegativeDoubleOrDefault() should return the transformed value" {
            CommanderValue("-1").toNegativeDoubleOrDefault(2.0) shouldBe -1.0
        }

        "CommanderValue.toNegativeDoubleOrDefault() should return the default value if the value is not a doubles" {
            CommanderValue("test").toNegativeDoubleOrDefault(2.0) shouldBe 2.0
        }

        "CommanderValue.toNegativeDoubleOrDefault() should return the default value if the value is positive" {
            CommanderValue("1").toNegativeDoubleOrDefault(2.0) shouldBe 2.0
        }

        "CommanderValue.validate(CommanderValueValidator) should not fail if the value is valid" {
            CommanderValue("test").validate(valueValidator { })
        }

        "CommanderValue.validate(CommanderValueValidator) should fail if the value is invalid" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validate(valueValidator { throw CommanderValueException("Test") })
            }
        }

        "CommanderValue.validate(CommanderValueValidator) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").validate(valueValidator { throw Exception("Test") })
            }
        }

        "CommanderValue.validate(CommanderValueValidatorLambda) should not fail if the value is valid" {
            CommanderValue("test").validate { }
        }

        "CommanderValue.validate(CommanderValueValidatorLambda) should fail if the value is invalid" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validate { throw CommanderValueException("Test") }
            }
        }

        "CommanderValue.validate(CommanderValueValidatorLambda) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").validate { throw Exception("Test") }
            }
        }

        "CommanderValue.isValid(CommanderValueValidator) should return true if the value is valid" {
            CommanderValue("test").isValid(valueValidator { }) shouldBe true
        }

        "CommanderValue.isValid(CommanderValueValidator) should return false if the value is invalid" {
            CommanderValue("test").isValid(valueValidator { throw CommanderValueException("Test") }) shouldBe false
        }

        "CommanderValue.isValid(CommanderValueValidator) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").isValid(valueValidator { throw Exception("Test") })
            }
        }

        "CommanderValue.isValid(CommanderValueValidatorLambda) should return true if the value is valid" {
            CommanderValue("test").isValid { } shouldBe true
        }

        "CommanderValue.isValid(CommanderValueValidatorLambda) should return false if the value is invalid" {
            CommanderValue("test").isValid { throw CommanderValueException("Test") } shouldBe false
        }

        "CommanderValue.isValid(CommanderValueValidatorLambda) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").isValid { throw Exception("Test") }
            }
        }

        "CommanderValue.isInvalid(CommanderValueValidator) should return false if the value is valid" {
            CommanderValue("test").isInvalid(valueValidator { }) shouldBe false
        }

        "CommanderValue.isInvalid(CommanderValueValidator) should return true if the value is invalid" {
            CommanderValue("test").isInvalid(valueValidator { throw CommanderValueException("Test") }) shouldBe true
        }

        "CommanderValue.isInvalid(CommanderValueValidator) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").isInvalid(valueValidator { throw Exception("Test") })
            }
        }

        "CommanderValue.isInvalid(CommanderValueValidatorLambda) should return false if the value is valid" {
            CommanderValue("test").isInvalid { } shouldBe false
        }

        "CommanderValue.isInvalid(CommanderValueValidatorLambda) should return true if the value is invalid" {
            CommanderValue("test").isInvalid { throw CommanderValueException("Test") } shouldBe true
        }

        "CommanderValue.isInvalid(CommanderValueValidatorLambda) should pass through exceptions" {
            shouldThrow<Exception> {
                CommanderValue("test").isInvalid { throw Exception("Test") }
            }
        }

        "CommanderValue.validateByte() should not fail if the value is valid" {
            CommanderValue("1").validateByte()
        }

        "CommanderValue.validateByte() should fail if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateByte()
            }
        }

        "CommanderValue.isByte() should return true if the value is a byte" {
            CommanderValue("1").isByte() shouldBe true
        }

        "CommanderValue.isByte() should return false if the value is not a byte" {
            CommanderValue("test").isByte() shouldBe false
        }

        "CommanderValue.isNotByte() should return false if the value is a byte" {
            CommanderValue("1").isNotByte() shouldBe false
        }

        "CommanderValue.isNotByte() should return true if the value is not a byte" {
            CommanderValue("test").isNotByte() shouldBe true
        }

        "CommanderValue.validateShort() should not fail if the value is valid" {
            CommanderValue("1").validateShort()
        }

        "CommanderValue.validateShort() should fail if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateShort()
            }
        }

        "CommanderValue.isShort() should return true if the value is a shorts" {
            CommanderValue("1").isShort() shouldBe true
        }

        "CommanderValue.isShort() should return false if the value is not a shorts" {
            CommanderValue("test").isShort() shouldBe false
        }

        "CommanderValue.isNotShort() should return false if the value is a shorts" {
            CommanderValue("1").isNotShort() shouldBe false
        }

        "CommanderValue.isNotShort() should return true if the value is not a shorts" {
            CommanderValue("test").isNotShort() shouldBe true
        }

        "CommanderValue.validateInt() should not fail if the value is valid" {
            CommanderValue("1").validateInt()
        }

        "CommanderValue.validateInt() should fail if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateInt()
            }
        }

        "CommanderValue.isInt() should return true if the value is a int" {
            CommanderValue("1").isInt() shouldBe true
        }

        "CommanderValue.isInt() should return false if the value is not a int" {
            CommanderValue("test").isInt() shouldBe false
        }

        "CommanderValue.isNotInt() should return false if the value is a int" {
            CommanderValue("1").isNotInt() shouldBe false
        }

        "CommanderValue.isNotInt() should return true if the value is not a int" {
            CommanderValue("test").isNotInt() shouldBe true
        }

        "CommanderValue.validateLong() should not fail if the value is valid" {
            CommanderValue("1").validateLong()
        }

        "CommanderValue.validateLong() should fail if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateLong()
            }
        }

        "CommanderValue.isLong() should return true if the value is a long" {
            CommanderValue("1").isLong() shouldBe true
        }

        "CommanderValue.isLong() should return false if the value is not a long" {
            CommanderValue("test").isLong() shouldBe false
        }

        "CommanderValue.isNotLong() should return false if the value is a long" {
            CommanderValue("1").isNotLong() shouldBe false
        }

        "CommanderValue.isNotLong() should return true if the value is not a long" {
            CommanderValue("test").isNotLong() shouldBe true
        }

        "CommanderValue.validateFloat() should not fail if the value is valid" {
            CommanderValue("1").validateFloat()
        }

        "CommanderValue.validateFloat() should fail if the value is not a float" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateFloat()
            }
        }

        "CommanderValue.isFloat() should return true if the value is a float" {
            CommanderValue("1").isFloat() shouldBe true
        }

        "CommanderValue.isFloat() should return false if the value is not a float" {
            CommanderValue("test").isFloat() shouldBe false
        }

        "CommanderValue.isNotFloat() should return false if the value is a float" {
            CommanderValue("1").isNotFloat() shouldBe false
        }

        "CommanderValue.isNotFloat() should return true if the value is not a float" {
            CommanderValue("test").isNotFloat() shouldBe true
        }

        "CommanderValue.validateDouble() should not fail if the value is valid" {
            CommanderValue("1").validateDouble()
        }

        "CommanderValue.validateDouble() should fail if the value is not a doubles" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateDouble()
            }
        }

        "CommanderValue.isDouble() should return true if the value is a doubles" {
            CommanderValue("1").isDouble() shouldBe true
        }

        "CommanderValue.isDouble() should return false if the value is not a doubles" {
            CommanderValue("test").isDouble() shouldBe false
        }

        "CommanderValue.isNotDouble() should return false if the value is a doubles" {
            CommanderValue("1").isNotDouble() shouldBe false
        }

        "CommanderValue.isNotDouble() should return true if the value is not a doubles" {
            CommanderValue("test").isNotDouble() shouldBe true
        }

        "CommanderValue.validateUnsignedByte() should not fail if the value is valid" {
            CommanderValue("1").validateUnsignedByte()
        }

        "CommanderValue.validateUnsignedByte() should fail if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateUnsignedByte()
            }
        }

        "CommanderValue.validateUnsignedByte() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validateUnsignedByte()
            }
        }

        "CommanderValue.isUnsignedByte() should return true if the value is a byte" {
            CommanderValue("1").isUnsignedByte() shouldBe true
        }

        "CommanderValue.isUnsignedByte() should return false if the value is not a byte" {
            CommanderValue("test").isUnsignedByte() shouldBe false
        }

        "CommanderValue.isUnsignedByte() should return false if the value is negative" {
            CommanderValue("-1").isUnsignedByte() shouldBe false
        }

        "CommanderValue.isNotUnsignedByte() should return false if the value is a byte" {
            CommanderValue("1").isNotUnsignedByte() shouldBe false
        }

        "CommanderValue.isNotUnsignedByte() should return true if the value is not a byte" {
            CommanderValue("test").isNotUnsignedByte() shouldBe true
        }

        "CommanderValue.isNotUnsignedByte() should return true if the value is negative" {
            CommanderValue("-1").isNotUnsignedByte() shouldBe true
        }

        "CommanderValue.validateUnsignedShort() should not fail if the value is valid" {
            CommanderValue("1").validateUnsignedShort()
        }

        "CommanderValue.validateUnsignedShort() should fail if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateUnsignedShort()
            }
        }

        "CommanderValue.validateUnsignedShort() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validateUnsignedShort()
            }
        }

        "CommanderValue.isUnsignedShort() should return true if the value is a shorts" {
            CommanderValue("1").isUnsignedShort() shouldBe true
        }

        "CommanderValue.isUnsignedShort() should return false if the value is not a shorts" {
            CommanderValue("test").isUnsignedShort() shouldBe false
        }

        "CommanderValue.isUnsignedShort() should return false if the value is negative" {
            CommanderValue("-1").isUnsignedShort() shouldBe false
        }

        "CommanderValue.isNotUnsignedShort() should return false if the value is a shorts" {
            CommanderValue("1").isNotUnsignedShort() shouldBe false
        }

        "CommanderValue.isNotUnsignedShort() should return true if the value is not a shorts" {
            CommanderValue("test").isNotUnsignedShort() shouldBe true
        }

        "CommanderValue.isNotUnsignedShort() should return true if the value is negative" {
            CommanderValue("-1").isNotUnsignedShort() shouldBe true
        }

        "CommanderValue.validateUnsignedInt() should not fail if the value is valid" {
            CommanderValue("1").validateUnsignedInt()
        }

        "CommanderValue.validateUnsignedInt() should fail if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateUnsignedInt()
            }
        }

        "CommanderValue.validateUnsignedInt() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validateUnsignedInt()
            }
        }

        "CommanderValue.isUnsignedInt() should return true if the value is a int" {
            CommanderValue("1").isUnsignedInt() shouldBe true
        }

        "CommanderValue.isUnsignedInt() should return false if the value is not a int" {
            CommanderValue("test").isUnsignedInt() shouldBe false
        }

        "CommanderValue.isUnsignedInt() should return false if the value is negative" {
            CommanderValue("-1").isUnsignedInt() shouldBe false
        }

        "CommanderValue.isNotUnsignedInt() should return false if the value is a int" {
            CommanderValue("1").isNotUnsignedInt() shouldBe false
        }

        "CommanderValue.isNotUnsignedInt() should return true if the value is not a int" {
            CommanderValue("test").isNotUnsignedInt() shouldBe true
        }

        "CommanderValue.isNotUnsignedInt() should return true if the value is negative" {
            CommanderValue("-1").isNotUnsignedInt() shouldBe true
        }

        "CommanderValue.validateUnsignedLong() should not fail if the value is valid" {
            CommanderValue("1").validateUnsignedLong()
        }

        "CommanderValue.validateUnsignedLong() should fail if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateUnsignedLong()
            }
        }

        "CommanderValue.validateUnsignedLong() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validateUnsignedLong()
            }
        }

        "CommanderValue.isUnsignedLong() should return true if the value is a long" {
            CommanderValue("1").isUnsignedLong() shouldBe true
        }

        "CommanderValue.isUnsignedLong() should return false if the value is not a long" {
            CommanderValue("test").isUnsignedLong() shouldBe false
        }

        "CommanderValue.isUnsignedLong() should return false if the value is negative" {
            CommanderValue("-1").isUnsignedLong() shouldBe false
        }

        "CommanderValue.isNotUnsignedLong() should return false if the value is a long" {
            CommanderValue("1").isNotUnsignedLong() shouldBe false
        }

        "CommanderValue.isNotUnsignedLong() should return true if the value is not a long" {
            CommanderValue("test").isNotUnsignedLong() shouldBe true
        }

        "CommanderValue.isNotUnsignedLong() should return true if the value is negative" {
            CommanderValue("-1").isNotUnsignedLong() shouldBe true
        }

        "CommanderValue.validateBoolean() should not fail if the value is valid" {
            CommanderValue("true").validateBoolean()
        }

        "CommanderValue.validateBoolean() should fail if the value is not a boolean" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateBoolean()
            }
        }

        "CommanderValue.isBoolean() should return true if the value is a boolean" {
            CommanderValue("true").isBoolean() shouldBe true
        }

        "CommanderValue.isBoolean() should return false if the value is not a boolean" {
            CommanderValue("test").isBoolean() shouldBe false
        }

        "CommanderValue.isNotBoolean() should return false if the value is a boolean" {
            CommanderValue("true").isNotBoolean() shouldBe false
        }

        "CommanderValue.isNotBoolean() should return true if the value is not a boolean" {
            CommanderValue("test").isNotBoolean() shouldBe true
        }

        "CommanderValue.validateChar() should not fail if the value is valid" {
            CommanderValue("a").validateChar()
        }

        "CommanderValue.validateChar() should fail if the value is not a char" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateChar()
            }
        }

        "CommanderValue.isChar() should return true if the value is a char" {
            CommanderValue("a").isChar() shouldBe true
        }

        "CommanderValue.isChar() should return false if the value is not a char" {
            CommanderValue("test").isChar() shouldBe false
        }

        "CommanderValue.isNotChar() should return false if the value is a char" {
            CommanderValue("a").isNotChar() shouldBe false
        }

        "CommanderValue.isNotChar() should return true if the value is not a char" {
            CommanderValue("test").isNotChar() shouldBe true
        }

        "CommanderValue.validateString() should not fail if the value is valid" {
            CommanderValue("test").validateString()
        }

        "CommanderValue.validateString() should fail if the value is null" {
            shouldThrow<CommanderValueException> {
                CommanderValue(null).validateString()
            }
        }

        "CommanderValue.isString() should return true if the value is a string" {
            CommanderValue("test").isString() shouldBe true
        }

        "CommanderValue.isString() should return false if the value is null" {
            CommanderValue(null).isString() shouldBe false
        }

        "CommanderValue.isNotString() should return false if the value is a string" {
            CommanderValue("test").isNotString() shouldBe false
        }

        "CommanderValue.isNotString() should return true if the value is null" {
            CommanderValue(null).isNotString() shouldBe true
        }

        "CommanderValue.validatePositiveByte() should not fail if the value is valid" {
            CommanderValue("1").validatePositiveByte()
        }

        "CommanderValue.validatePositiveByte() should fail if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validatePositiveByte()
            }
        }

        "CommanderValue.validatePositiveByte() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validatePositiveByte()
            }
        }

        "CommanderValue.isPositiveByte() should return true if the value is a byte" {
            CommanderValue("1").isPositiveByte() shouldBe true
        }

        "CommanderValue.isPositiveByte() should return false if the value is not a byte" {
            CommanderValue("test").isPositiveByte() shouldBe false
        }

        "CommanderValue.isPositiveByte() should return false if the value is negative" {
            CommanderValue("-1").isPositiveByte() shouldBe false
        }

        "CommanderValue.isNotPositiveByte() should return false if the value is a byte" {
            CommanderValue("1").isNotPositiveByte() shouldBe false
        }

        "CommanderValue.isNotPositiveByte() should return true if the value is not a byte" {
            CommanderValue("test").isNotPositiveByte() shouldBe true
        }

        "CommanderValue.isNotPositiveByte() should return true if the value is negative" {
            CommanderValue("-1").isNotPositiveByte() shouldBe true
        }

        "CommanderValue.validatePositiveShort() should not fail if the value is valid" {
            CommanderValue("1").validatePositiveShort()
        }

        "CommanderValue.validatePositiveShort() should fail if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validatePositiveShort()
            }
        }

        "CommanderValue.validatePositiveShort() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validatePositiveShort()
            }
        }

        "CommanderValue.isPositiveShort() should return true if the value is a shorts" {
            CommanderValue("1").isPositiveShort() shouldBe true
        }

        "CommanderValue.isPositiveShort() should return false if the value is not a shorts" {
            CommanderValue("test").isPositiveShort() shouldBe false
        }

        "CommanderValue.isPositiveShort() should return false if the value is negative" {
            CommanderValue("-1").isPositiveShort() shouldBe false
        }

        "CommanderValue.isNotPositiveShort() should return false if the value is a shorts" {
            CommanderValue("1").isNotPositiveShort() shouldBe false
        }

        "CommanderValue.isNotPositiveShort() should return true if the value is not a shorts" {
            CommanderValue("test").isNotPositiveShort() shouldBe true
        }

        "CommanderValue.isNotPositiveShort() should return true if the value is negative" {
            CommanderValue("-1").isNotPositiveShort() shouldBe true
        }

        "CommanderValue.validatePositiveInt() should not fail if the value is valid" {
            CommanderValue("1").validatePositiveInt()
        }

        "CommanderValue.validatePositiveInt() should fail if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validatePositiveInt()
            }
        }

        "CommanderValue.validatePositiveInt() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validatePositiveInt()
            }
        }

        "CommanderValue.isPositiveInt() should return true if the value is a int" {
            CommanderValue("1").isPositiveInt() shouldBe true
        }

        "CommanderValue.isPositiveInt() should return false if the value is not a int" {
            CommanderValue("test").isPositiveInt() shouldBe false
        }

        "CommanderValue.isPositiveInt() should return false if the value is negative" {
            CommanderValue("-1").isPositiveInt() shouldBe false
        }

        "CommanderValue.isNotPositiveInt() should return false if the value is a int" {
            CommanderValue("1").isNotPositiveInt() shouldBe false
        }

        "CommanderValue.isNotPositiveInt() should return true if the value is not a int" {
            CommanderValue("test").isNotPositiveInt() shouldBe true
        }

        "CommanderValue.isNotPositiveInt() should return true if the value is negative" {
            CommanderValue("-1").isNotPositiveInt() shouldBe true
        }

        "CommanderValue.validatePositiveLong() should not fail if the value is valid" {
            CommanderValue("1").validatePositiveLong()
        }

        "CommanderValue.validatePositiveLong() should fail if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validatePositiveLong()
            }
        }

        "CommanderValue.validatePositiveLong() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validatePositiveLong()
            }
        }

        "CommanderValue.isPositiveLong() should return true if the value is a long" {
            CommanderValue("1").isPositiveLong() shouldBe true
        }

        "CommanderValue.isPositiveLong() should return false if the value is not a long" {
            CommanderValue("test").isPositiveLong() shouldBe false
        }

        "CommanderValue.isPositiveLong() should return false if the value is negative" {
            CommanderValue("-1").isPositiveLong() shouldBe false
        }

        "CommanderValue.isNotPositiveLong() should return false if the value is a long" {
            CommanderValue("1").isNotPositiveLong() shouldBe false
        }

        "CommanderValue.isNotPositiveLong() should return true if the value is not a long" {
            CommanderValue("test").isNotPositiveLong() shouldBe true
        }

        "CommanderValue.isNotPositiveLong() should return true if the value is negative" {
            CommanderValue("-1").isNotPositiveLong() shouldBe true
        }

        "CommanderValue.validatePositiveFloat() should not fail if the value is valid" {
            CommanderValue("1").validatePositiveFloat()
        }

        "CommanderValue.validatePositiveFloat() should fail if the value is not a float" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validatePositiveFloat()
            }
        }

        "CommanderValue.validatePositiveFloat() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validatePositiveFloat()
            }
        }

        "CommanderValue.isPositiveFloat() should return true if the value is a float" {
            CommanderValue("1").isPositiveFloat() shouldBe true
        }

        "CommanderValue.isPositiveFloat() should return false if the value is not a float" {
            CommanderValue("test").isPositiveFloat() shouldBe false
        }

        "CommanderValue.isPositiveFloat() should return false if the value is negative" {
            CommanderValue("-1").isPositiveFloat() shouldBe false
        }

        "CommanderValue.isNotPositiveFloat() should return false if the value is a float" {
            CommanderValue("1").isNotPositiveFloat() shouldBe false
        }

        "CommanderValue.isNotPositiveFloat() should return true if the value is not a float" {
            CommanderValue("test").isNotPositiveFloat() shouldBe true
        }

        "CommanderValue.isNotPositiveFloat() should return true if the value is negative" {
            CommanderValue("-1").isNotPositiveFloat() shouldBe true
        }

        "CommanderValue.validatePositiveDouble() should not fail if the value is valid" {
            CommanderValue("1").validatePositiveDouble()
        }

        "CommanderValue.validatePositiveDouble() should fail if the value is not a doubles" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validatePositiveDouble()
            }
        }

        "CommanderValue.validatePositiveDouble() should fail if the value is negative" {
            shouldThrow<CommanderValueException> {
                CommanderValue("-1").validatePositiveDouble()
            }
        }

        "CommanderValue.isPositiveDouble() should return true if the value is a doubles" {
            CommanderValue("1").isPositiveDouble() shouldBe true
        }

        "CommanderValue.isPositiveDouble() should return false if the value is not a doubles" {
            CommanderValue("test").isPositiveDouble() shouldBe false
        }

        "CommanderValue.isPositiveDouble() should return false if the value is negative" {
            CommanderValue("-1").isPositiveDouble() shouldBe false
        }

        "CommanderValue.isNotPositiveDouble() should return false if the value is a doubles" {
            CommanderValue("1").isNotPositiveDouble() shouldBe false
        }

        "CommanderValue.isNotPositiveDouble() should return true if the value is not a doubles" {
            CommanderValue("test").isNotPositiveDouble() shouldBe true
        }

        "CommanderValue.isNotPositiveDouble() should return true if the value is negative" {
            CommanderValue("-1").isNotPositiveDouble() shouldBe true
        }

        "CommanderValue.validateNegativeByte() should not fail if the value is valid" {
            CommanderValue("-1").validateNegativeByte()
        }

        "CommanderValue.validateNegativeByte() should fail if the value is not a byte" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNegativeByte()
            }
        }

        "CommanderValue.validateNegativeByte() should fail if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").validateNegativeByte()
            }
        }

        "CommanderValue.isNegativeByte() should return true if the value is a byte" {
            CommanderValue("-1").isNegativeByte() shouldBe true
        }

        "CommanderValue.isNegativeByte() should return false if the value is not a byte" {
            CommanderValue("test").isNegativeByte() shouldBe false
        }

        "CommanderValue.isNegativeByte() should return false if the value is positive" {
            CommanderValue("1").isNegativeByte() shouldBe false
        }

        "CommanderValue.isNotNegativeByte() should return false if the value is a byte" {
            CommanderValue("-1").isNotNegativeByte() shouldBe false
        }

        "CommanderValue.isNotNegativeByte() should return true if the value is not a byte" {
            CommanderValue("test").isNotNegativeByte() shouldBe true
        }

        "CommanderValue.isNotNegativeByte() should return true if the value is positive" {
            CommanderValue("1").isNotNegativeByte() shouldBe true
        }

        "CommanderValue.validateNegativeShort() should not fail if the value is valid" {
            CommanderValue("-1").validateNegativeShort()
        }

        "CommanderValue.validateNegativeShort() should fail if the value is not a shorts" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNegativeShort()
            }
        }

        "CommanderValue.validateNegativeShort() should fail if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").validateNegativeShort()
            }
        }

        "CommanderValue.isNegativeShort() should return true if the value is a shorts" {
            CommanderValue("-1").isNegativeShort() shouldBe true
        }

        "CommanderValue.isNegativeShort() should return false if the value is not a shorts" {
            CommanderValue("test").isNegativeShort() shouldBe false
        }

        "CommanderValue.isNegativeShort() should return false if the value is positive" {
            CommanderValue("1").isNegativeShort() shouldBe false
        }

        "CommanderValue.isNotNegativeShort() should return false if the value is a shorts" {
            CommanderValue("-1").isNotNegativeShort() shouldBe false
        }

        "CommanderValue.isNotNegativeShort() should return true if the value is not a shorts" {
            CommanderValue("test").isNotNegativeShort() shouldBe true
        }

        "CommanderValue.isNotNegativeShort() should return true if the value is positive" {
            CommanderValue("1").isNotNegativeShort() shouldBe true
        }

        "CommanderValue.validateNegativeInt() should not fail if the value is valid" {
            CommanderValue("-1").validateNegativeInt()
        }

        "CommanderValue.validateNegativeInt() should fail if the value is not a int" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNegativeInt()
            }
        }

        "CommanderValue.validateNegativeInt() should fail if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").validateNegativeInt()
            }
        }

        "CommanderValue.isNegativeInt() should return true if the value is a int" {
            CommanderValue("-1").isNegativeInt() shouldBe true
        }

        "CommanderValue.isNegativeInt() should return false if the value is not a int" {
            CommanderValue("test").isNegativeInt() shouldBe false
        }

        "CommanderValue.isNegativeInt() should return false if the value is positive" {
            CommanderValue("1").isNegativeInt() shouldBe false
        }

        "CommanderValue.isNotNegativeInt() should return false if the value is a int" {
            CommanderValue("-1").isNotNegativeInt() shouldBe false
        }

        "CommanderValue.isNotNegativeInt() should return true if the value is not a int" {
            CommanderValue("test").isNotNegativeInt() shouldBe true
        }

        "CommanderValue.isNotNegativeInt() should return true if the value is positive" {
            CommanderValue("1").isNotNegativeInt() shouldBe true
        }

        "CommanderValue.validateNegativeLong() should not fail if the value is valid" {
            CommanderValue("-1").validateNegativeLong()
        }

        "CommanderValue.validateNegativeLong() should fail if the value is not a long" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNegativeLong()
            }
        }

        "CommanderValue.validateNegativeLong() should fail if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").validateNegativeLong()
            }
        }

        "CommanderValue.isNegativeLong() should return true if the value is a long" {
            CommanderValue("-1").isNegativeLong() shouldBe true
        }

        "CommanderValue.isNegativeLong() should return false if the value is not a long" {
            CommanderValue("test").isNegativeLong() shouldBe false
        }

        "CommanderValue.isNegativeLong() should return false if the value is positive" {
            CommanderValue("1").isNegativeLong() shouldBe false
        }

        "CommanderValue.isNotNegativeLong() should return false if the value is a long" {
            CommanderValue("-1").isNotNegativeLong() shouldBe false
        }

        "CommanderValue.isNotNegativeLong() should return true if the value is not a long" {
            CommanderValue("test").isNotNegativeLong() shouldBe true
        }

        "CommanderValue.isNotNegativeLong() should return true if the value is positive" {
            CommanderValue("1").isNotNegativeLong() shouldBe true
        }

        "CommanderValue.validateNegativeFloat() should not fail if the value is valid" {
            CommanderValue("-1").validateNegativeFloat()
        }

        "CommanderValue.validateNegativeFloat() should fail if the value is not a float" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNegativeFloat()
            }
        }

        "CommanderValue.validateNegativeFloat() should fail if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").validateNegativeFloat()
            }
        }

        "CommanderValue.isNegativeFloat() should return true if the value is a float" {
            CommanderValue("-1").isNegativeFloat() shouldBe true
        }

        "CommanderValue.isNegativeFloat() should return false if the value is not a float" {
            CommanderValue("test").isNegativeFloat() shouldBe false
        }

        "CommanderValue.isNegativeFloat() should return false if the value is positive" {
            CommanderValue("1").isNegativeFloat() shouldBe false
        }

        "CommanderValue.isNotNegativeFloat() should return false if the value is a float" {
            CommanderValue("-1").isNotNegativeFloat() shouldBe false
        }

        "CommanderValue.isNotNegativeFloat() should return true if the value is not a float" {
            CommanderValue("test").isNotNegativeFloat() shouldBe true
        }

        "CommanderValue.isNotNegativeFloat() should return true if the value is positive" {
            CommanderValue("1").isNotNegativeFloat() shouldBe true
        }

        "CommanderValue.validateNegativeDouble() should not fail if the value is valid" {
            CommanderValue("-1").validateNegativeDouble()
        }

        "CommanderValue.validateNegativeDouble() should fail if the value is not a doubles" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNegativeDouble()
            }
        }

        "CommanderValue.validateNegativeDouble() should fail if the value is positive" {
            shouldThrow<CommanderValueException> {
                CommanderValue("1").validateNegativeDouble()
            }
        }

        "CommanderValue.isNegativeDouble() should return true if the value is a doubles" {
            CommanderValue("-1").isNegativeDouble() shouldBe true
        }

        "CommanderValue.isNegativeDouble() should return false if the value is not a doubles" {
            CommanderValue("test").isNegativeDouble() shouldBe false
        }

        "CommanderValue.isNegativeDouble() should return false if the value is positive" {
            CommanderValue("1").isNegativeDouble() shouldBe false
        }

        "CommanderValue.isNotNegativeDouble() should return false if the value is a doubles" {
            CommanderValue("-1").isNotNegativeDouble() shouldBe false
        }

        "CommanderValue.isNotNegativeDouble() should return true if the value is not a doubles" {
            CommanderValue("test").isNotNegativeDouble() shouldBe true
        }

        "CommanderValue.isNotNegativeDouble() should return true if the value is positive" {
            CommanderValue("1").isNotNegativeDouble() shouldBe true
        }

        "CommanderValue.validateNull() should not fail if the value is null" {
            CommanderValue(null).validateNull()
        }

        "CommanderValue.validateNull() should fail if the value is not null" {
            shouldThrow<CommanderValueException> {
                CommanderValue("test").validateNull()
            }
        }

        "CommanderValue.validateNotNull() should not fail if the value is not null" {
            CommanderValue("test").validateNotNull()
        }

        "CommanderValue.validateNotNull() should fail if the value is null" {
            shouldThrow<CommanderValueException> {
                CommanderValue(null).validateNotNull()
            }
        }

        "CommanderValue.isNull() should return true if the value is null" {
            CommanderValue(null).isNull() shouldBe true
        }

        "CommanderValue.isNull() should return false if the value is not null" {
            CommanderValue("test").isNull() shouldBe false
        }

        "CommanderValue.isNotNull() should return true if the value is not null" {
            CommanderValue("test").isNotNull() shouldBe true
        }

        "CommanderValue.isNotNull() should return false if the value is null" {
            CommanderValue(null).isNotNull() shouldBe false
        }
    },
)
