package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderValueTransformerFunctionTests : FreeSpec(
    {

        "toByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toByte("0")
            result shouldBe 0
        }

        "toByte() should be able to parse '1'" {
            val result = CommanderValueTransformers.toByte("1")
            result shouldBe 1
        }

        "toByte() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toByte("-1")
            result shouldBe -1
        }

        "toByte() should be able to parse '127'" {
            val result = CommanderValueTransformers.toByte("127")
            result shouldBe 127
        }

        "toByte() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toByte("-128")
            result shouldBe -128
        }

        "toByte() should fail to parse '128'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte("128")
            }
        }

        "toByte() should fail to parse '-129'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte("-129")
            }
        }

        "toByte() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte("abc")
            }
        }

        "toByte() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte(null)
            }
        }

        "toByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte("")
            }
        }

        "toByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte(" ")
            }
        }

        "toByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toByteOrNull("0")
            result shouldBe 0
        }

        "toByteOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toByteOrNull("1")
            result shouldBe 1
        }

        "toByteOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toByteOrNull("-1")
            result shouldBe -1
        }

        "toByteOrNull() should be able to parse '127'" {
            val result = CommanderValueTransformers.toByteOrNull("127")
            result shouldBe 127
        }

        "toByteOrNull() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toByteOrNull("-128")
            result shouldBe -128
        }

        "toByteOrNull() should fail to parse '128'" {
            val result = CommanderValueTransformers.toByteOrNull("128")
            result shouldBe null
        }

        "toByteOrNull() should fail to parse '-129'" {
            val result = CommanderValueTransformers.toByteOrNull("-129")
            result shouldBe null
        }

        "toByteOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toByteOrNull("abc")
            result shouldBe null
        }

        "toByteOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toByteOrNull(null)
            result shouldBe null
        }

        "toByteOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toByteOrNull("")
            result shouldBe null
        }

        "toByteOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toByteOrNull(" ")
            result shouldBe null
        }

        "toShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toShort("0")
            result shouldBe 0
        }

        "toShort() should be able to parse '1'" {
            val result = CommanderValueTransformers.toShort("1")
            result shouldBe 1
        }

        "toShort() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toShort("-1")
            result shouldBe -1
        }

        "toShort() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toShort("32767")
            result shouldBe 32767
        }

        "toShort() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toShort("-32768")
            result shouldBe -32768
        }

        "toShort() should fail to parse '32768'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort("32768")
            }
        }

        "toShort() should fail to parse '-32769'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort("-32769")
            }
        }

        "toShort() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort("abc")
            }
        }

        "toShort() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort(null)
            }
        }

        "toShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort("")
            }
        }

        "toShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort(" ")
            }
        }

        "toShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toShortOrNull("0")
            result shouldBe 0
        }

        "toShortOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toShortOrNull("1")
            result shouldBe 1
        }

        "toShortOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toShortOrNull("-1")
            result shouldBe -1
        }

        "toShortOrNull() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toShortOrNull("32767")
            result shouldBe 32767
        }

        "toShortOrNull() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toShortOrNull("-32768")
            result shouldBe -32768
        }

        "toShortOrNull() should fail to parse '32768'" {
            val result = CommanderValueTransformers.toShortOrNull("32768")
            result shouldBe null
        }

        "toShortOrNull() should fail to parse '-32769'" {
            val result = CommanderValueTransformers.toShortOrNull("-32769")
            result shouldBe null
        }

        "toShortOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toShortOrNull("abc")
            result shouldBe null
        }

        "toShortOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toShortOrNull(null)
            result shouldBe null
        }

        "toShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toShortOrNull("")
            result shouldBe null
        }

        "toShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toShortOrNull(" ")
            result shouldBe null
        }

        "toInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toInt("0")
            result shouldBe 0
        }

        "toInt() should be able to parse '1'" {
            val result = CommanderValueTransformers.toInt("1")
            result shouldBe 1
        }

        "toInt() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toInt("-1")
            result shouldBe -1
        }

        "toInt() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toInt("2147483647")
            result shouldBe 2147483647
        }

        "toInt() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toInt("-2147483648")
            result shouldBe -2147483648
        }

        "toInt() should fail to parse '2147483648'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt("2147483648")
            }
        }

        "toInt() should fail to parse '-2147483649'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt("-2147483649")
            }
        }

        "toInt() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt("abc")
            }
        }

        "toInt() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt(null)
            }
        }

        "toInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt("")
            }
        }

        "toInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt(" ")
            }
        }

        "toIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toIntOrNull("0")
            result shouldBe 0
        }

        "toIntOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toIntOrNull("1")
            result shouldBe 1
        }

        "toIntOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toIntOrNull("-1")
            result shouldBe -1
        }

        "toIntOrNull() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toIntOrNull("2147483647")
            result shouldBe 2147483647
        }

        "toIntOrNull() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toIntOrNull("-2147483648")
            result shouldBe -2147483648
        }

        "toIntOrNull() should fail to parse '2147483648'" {
            val result = CommanderValueTransformers.toIntOrNull("2147483648")
            result shouldBe null
        }

        "toIntOrNull() should fail to parse '-2147483649'" {
            val result = CommanderValueTransformers.toIntOrNull("-2147483649")
            result shouldBe null
        }

        "toIntOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toIntOrNull("abc")
            result shouldBe null
        }

        "toIntOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toIntOrNull(null)
            result shouldBe null
        }

        "toIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toIntOrNull("")
            result shouldBe null
        }

        "toIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toIntOrNull(" ")
            result shouldBe null
        }

        "toLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toLong("0")
            result shouldBe 0
        }

        "toLong() should be able to parse '1'" {
            val result = CommanderValueTransformers.toLong("1")
            result shouldBe 1
        }

        "toLong() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toLong("-1")
            result shouldBe -1
        }

        "toLong() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toLong("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toLong() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toLong("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toLong() should fail to parse '9223372036854775808'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong("9223372036854775808")
            }
        }

        "toLong() should fail to parse '-9223372036854775809'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong("-9223372036854775809")
            }
        }

        "toLong() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong("abc")
            }
        }

        "toLong() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong(null)
            }
        }

        "toLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong("")
            }
        }

        "toLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong(" ")
            }
        }

        "toLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toLongOrNull("0")
            result shouldBe 0
        }

        "toLongOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toLongOrNull("1")
            result shouldBe 1
        }

        "toLongOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toLongOrNull("-1")
            result shouldBe -1
        }

        "toLongOrNull() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toLongOrNull("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toLongOrNull() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toLongOrNull("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toLongOrNull() should fail to parse '9223372036854775808'" {
            val result = CommanderValueTransformers.toLongOrNull("9223372036854775808")
            result shouldBe null
        }

        "toLongOrNull() should fail to parse '-9223372036854775809'" {
            val result = CommanderValueTransformers.toLongOrNull("-9223372036854775809")
            result shouldBe null
        }

        "toLongOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toLongOrNull("abc")
            result shouldBe null
        }

        "toLongOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toLongOrNull(null)
            result shouldBe null
        }

        "toLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toLongOrNull("")
            result shouldBe null
        }

        "toLongOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toLongOrNull(" ")
            result shouldBe null
        }

        "toFloat() should be able to parse '0'" {
            val result = CommanderValueTransformers.toFloat("0")
            result shouldBe 0f
        }

        "toFloat() should be able to parse '1'" {
            val result = CommanderValueTransformers.toFloat("1")
            result shouldBe 1f
        }

        "toFloat() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toFloat("-1")
            result shouldBe -1f
        }

        "toFloat() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toFloat("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toFloat() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toFloat("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toFloat() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat("abc")
            }
        }

        "toFloat() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat(null)
            }
        }

        "toFloat() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat("")
            }
        }

        "toFloat() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat(" ")
            }
        }

        "toFloatOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toFloatOrNull("0")
            result shouldBe 0f
        }

        "toFloatOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toFloatOrNull("1")
            result shouldBe 1f
        }

        "toFloatOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toFloatOrNull("-1")
            result shouldBe -1f
        }

        "toFloatOrNull() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toFloatOrNull("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toFloatOrNull() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toFloatOrNull("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toFloatOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toFloatOrNull("abc")
            result shouldBe null
        }

        "toFloatOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toFloatOrNull(null)
            result shouldBe null
        }

        "toFloatOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toFloatOrNull("")
            result shouldBe null
        }

        "toFloatOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toFloatOrNull(" ")
            result shouldBe null
        }

        "toDouble() should be able to parse '0'" {
            val result = CommanderValueTransformers.toDouble("0")
            result shouldBe 0.0
        }

        "toDouble() should be able to parse '1'" {
            val result = CommanderValueTransformers.toDouble("1")
            result shouldBe 1.0
        }

        "toDouble() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toDouble("-1")
            result shouldBe -1.0
        }

        "toDouble() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDouble("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toDouble() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDouble("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toDouble() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble("abc")
            }
        }

        "toDouble() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble(null)
            }
        }

        "toDouble() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble("")
            }
        }

        "toDouble() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble(" ")
            }
        }

        "toDoubleOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toDoubleOrNull("0")
            result shouldBe 0.0
        }

        "toDoubleOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toDoubleOrNull("1")
            result shouldBe 1.0
        }

        "toDoubleOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toDoubleOrNull("-1")
            result shouldBe -1.0
        }

        "toDoubleOrNull() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDoubleOrNull("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toDoubleOrNull() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDoubleOrNull("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toDoubleOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toDoubleOrNull("abc")
            result shouldBe null
        }

        "toDoubleOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toDoubleOrNull(null)
            result shouldBe null
        }

        "toDoubleOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toDoubleOrNull("")
            result shouldBe null
        }

        "toDoubleOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toDoubleOrNull(" ")
            result shouldBe null
        }

        "toBoolean() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toBoolean("true")
            result shouldBe true
        }

        "toBoolean() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toBoolean("false")
            result shouldBe false
        }

        "toBoolean() should be able to parse '1'" {
            val result = CommanderValueTransformers.toBoolean("1")
            result shouldBe true
        }

        "toBoolean() should be able to parse '0'" {
            val result = CommanderValueTransformers.toBoolean("0")
            result shouldBe false
        }

        "toBoolean() should fail to parse '2'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean("2")
            }
        }

        "toBoolean() should fail to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean("-1")
            }
        }

        "toBoolean() should be able to parse 'yes'" {
            val result = CommanderValueTransformers.toBoolean("yes")
            result shouldBe true
        }

        "toBoolean() should be able to parse 'no'" {
            val result = CommanderValueTransformers.toBoolean("no")
            result shouldBe false
        }

        "toBoolean() should be able to parse 'y'" {
            val result = CommanderValueTransformers.toBoolean("y")
            result shouldBe true
        }

        "toBoolean() should be able to parse 'n'" {
            val result = CommanderValueTransformers.toBoolean("n")
            result shouldBe false
        }

        "toBoolean() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean("abc")
            }
        }

        "toBoolean() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean(null)
            }
        }

        "toBoolean() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean("")
            }
        }

        "toBoolean() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean(" ")
            }
        }

        "toBooleanOrNull() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toBooleanOrNull("true")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toBooleanOrNull("false")
            result shouldBe false
        }

        "toBooleanOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toBooleanOrNull("1")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toBooleanOrNull("0")
            result shouldBe false
        }

        "toBooleanOrNull() should fail to parse '2'" {
            val result = CommanderValueTransformers.toBooleanOrNull("2")
            result shouldBe null
        }

        "toBooleanOrNull() should fail to parse '-1'" {
            val result = CommanderValueTransformers.toBooleanOrNull("-1")
            result shouldBe null
        }

        "toBooleanOrNull() should be able to parse 'yes'" {
            val result = CommanderValueTransformers.toBooleanOrNull("yes")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse 'no'" {
            val result = CommanderValueTransformers.toBooleanOrNull("no")
            result shouldBe false
        }

        "toBooleanOrNull() should be able to parse 'y'" {
            val result = CommanderValueTransformers.toBooleanOrNull("y")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse 'n'" {
            val result = CommanderValueTransformers.toBooleanOrNull("n")
            result shouldBe false
        }

        "toBooleanOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toBooleanOrNull("abc")
            result shouldBe null
        }

        "toBooleanOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toBooleanOrNull(null)
            result shouldBe null
        }

        "toBooleanOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toBooleanOrNull("")
            result shouldBe null
        }

        "toBooleanOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toBooleanOrNull(" ")
            result shouldBe null
        }

        "toChar() should be able to parse 'a'" {
            val result = CommanderValueTransformers.toChar("a")
            result shouldBe 'a'
        }

        "toChar() should be able to parse 'A'" {
            val result = CommanderValueTransformers.toChar("A")
            result shouldBe 'A'
        }

        "toChar() should be able to parse '1'" {
            val result = CommanderValueTransformers.toChar("1")
            result shouldBe '1'
        }

        "toChar() should be able to parse ' '" {
            val result = CommanderValueTransformers.toChar(" ")
            result shouldBe ' '
        }

        "toChar() should not be able to parse 'ab'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar("ab")
            }
        }

        "toChar() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar(null)
            }
        }

        "toChar() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar("")
            }
        }

        "toChar() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar("abc")
            }
        }

        "toCharOrNull() should be able to parse 'a'" {
            val result = CommanderValueTransformers.toCharOrNull("a")
            result shouldBe 'a'
        }

        "toCharOrNull() should be able to parse 'A'" {
            val result = CommanderValueTransformers.toCharOrNull("A")
            result shouldBe 'A'
        }

        "toCharOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toCharOrNull("1")
            result shouldBe '1'
        }

        "toCharOrNull() should be able to parse ' '" {
            val result = CommanderValueTransformers.toCharOrNull(" ")
            result shouldBe ' '
        }

        "toCharOrNull() should not be able to parse 'ab'" {
            val result = CommanderValueTransformers.toCharOrNull("ab")
            result shouldBe null
        }

        "toCharOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toCharOrNull(null)
            result shouldBe null
        }

        "toCharOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toCharOrNull("")
            result shouldBe null
        }

        "toCharOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toCharOrNull("abc")
            result shouldBe null
        }

        "toUnsignedByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedByte("0")
            result shouldBe 0u.toUByte()
        }

        "toUnsignedByte() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedByte("1")
            result shouldBe 1u.toUByte()
        }

        "toUnsignedByte() should be able to parse '127'" {
            val result = CommanderValueTransformers.toUnsignedByte("127")
            result shouldBe 127u.toUByte()
        }

        "toUnsignedByte() should be able to parse '128'" {
            val result = CommanderValueTransformers.toUnsignedByte("128")
            result shouldBe 128u.toUByte()
        }

        "toUnsignedByte() should be able to parse '255'" {
            val result = CommanderValueTransformers.toUnsignedByte("255")
            result shouldBe 255u.toUByte()
        }

        "toUnsignedByte() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte("-1")
            }
        }

        "toUnsignedByte() should not be able to parse '256'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte("256")
            }
        }

        "toUnsignedByte() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte("abc")
            }
        }

        "toUnsignedByte() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte(null)
            }
        }

        "toUnsignedByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte("")
            }
        }

        "toUnsignedByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte(" ")
            }
        }

        "toUnsignedByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("0")
            result shouldBe 0u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("1")
            result shouldBe 1u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '127'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("127")
            result shouldBe 127u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '128'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("128")
            result shouldBe 128u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '255'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("255")
            result shouldBe 255u.toUByte()
        }

        "toUnsignedByteOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("-1")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse '256'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("256")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("abc")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull(null)
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull("")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull(" ")
            result shouldBe null
        }

        "toUnsignedShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedShort("0")
            result shouldBe 0u.toUShort()
        }

        "toUnsignedShort() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedShort("1")
            result shouldBe 1u.toUShort()
        }

        "toUnsignedShort() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toUnsignedShort("65535")
            result shouldBe 65535u.toUShort()
        }

        "toUnsignedShort() should be able to parse '32768'" {
            val result = CommanderValueTransformers.toUnsignedShort("32768")
            result shouldBe 32768u.toUShort()
        }

        "toUnsignedShort() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort("-1")
            }
        }

        "toUnsignedShort() should not be able to parse '65536'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort("65536")
            }
        }

        "toUnsignedShort() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort("abc")
            }
        }

        "toUnsignedShort() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort(null)
            }
        }

        "toUnsignedShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort("")
            }
        }

        "toUnsignedShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort(" ")
            }
        }

        "toUnsignedShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("0")
            result shouldBe 0u.toUShort()
        }

        "toUnsignedShortOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("1")
            result shouldBe 1u.toUShort()
        }

        "toUnsignedShortOrNull() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("65535")
            result shouldBe 65535u.toUShort()
        }

        "toUnsignedShortOrNull() should be able to parse '32768'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("32768")
            result shouldBe 32768u.toUShort()
        }

        "toUnsignedShortOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("-1")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse '65536'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("65536")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("abc")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull(null)
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull("")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull(" ")
            result shouldBe null
        }

        "toUnsignedInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedInt("0")
            result shouldBe 0u
        }

        "toUnsignedInt() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedInt("1")
            result shouldBe 1u
        }

        "toUnsignedInt() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toUnsignedInt("2147483647")
            result shouldBe 2147483647u
        }

        "toUnsignedInt() should be able to parse '4294967295'" {
            val result = CommanderValueTransformers.toUnsignedInt("4294967295")
            result shouldBe 4294967295u
        }

        "toUnsignedInt() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt("-1")
            }
        }

        "toUnsignedInt() should not be able to parse '4294967296'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt("4294967296")
            }
        }

        "toUnsignedInt() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt("abc")
            }
        }

        "toUnsignedInt() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt(null)
            }
        }

        "toUnsignedInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt("")
            }
        }

        "toUnsignedInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt(" ")
            }
        }

        "toUnsignedIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("0")
            result shouldBe 0u
        }

        "toUnsignedIntOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("1")
            result shouldBe 1u
        }

        "toUnsignedIntOrNull() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("2147483647")
            result shouldBe 2147483647u
        }

        "toUnsignedIntOrNull() should be able to parse '4294967295'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("4294967295")
            result shouldBe 4294967295u
        }

        "toUnsignedIntOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("-1")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse '4294967296'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("4294967296")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("abc")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull(null)
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull("")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull(" ")
            result shouldBe null
        }

        "toUnsignedLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedLong("0")
            result shouldBe 0uL
        }

        "toUnsignedLong() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedLong("1")
            result shouldBe 1uL
        }

        "toUnsignedLong() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toUnsignedLong("9223372036854775807")
            result shouldBe 9223372036854775807uL
        }

        "toUnsignedLong() should be able to parse '18446744073709551615'" {
            val result = CommanderValueTransformers.toUnsignedLong("18446744073709551615")
            result shouldBe ULong.MAX_VALUE
        }

        "toUnsignedLong() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong("-1")
            }
        }

        "toUnsignedLong() should not be able to parse '18446744073709551616'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong("18446744073709551616")
            }
        }

        "toUnsignedLong() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong("abc")
            }
        }

        "toUnsignedLong() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong(null)
            }
        }

        "toUnsignedLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong("")
            }
        }

        "toUnsignedLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong(" ")
            }
        }

        "toUnsignedLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("0")
            result shouldBe 0uL
        }

        "toUnsignedLongOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("1")
            result shouldBe 1uL
        }

        "toUnsignedLongOrNull() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("9223372036854775807")
            result shouldBe 9223372036854775807uL
        }

        "toUnsignedLongOrNull() should be able to parse '18446744073709551615'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("18446744073709551615")
            result shouldBe ULong.MAX_VALUE
        }

        "toUnsignedLongOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("-1")
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse '18446744073709551616'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("18446744073709551616")
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("abc")
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull(null)
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull("")
            result shouldBe null
        }

        "toPositiveByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveByte("0")
            result shouldBe 0
        }

        "toPositiveByte() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveByte("1")
            result shouldBe 1
        }

        "toPositiveByte() should be able to parse '127'" {
            val result = CommanderValueTransformers.toPositiveByte("127")
            result shouldBe 127
        }

        "toPositiveByte() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte("-1")
            }
        }

        "toPositiveByte() should not be able to parse '128'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte("128")
            }
        }

        "toPositiveByte() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte("abc")
            }
        }

        "toPositiveByte() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte(null)
            }
        }

        "toPositiveByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte("")
            }
        }

        "toPositiveByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte(" ")
            }
        }

        "toPositiveByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull("0")
            result shouldBe 0
        }

        "toPositiveByteOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull("1")
            result shouldBe 1
        }

        "toPositiveByteOrNull() should be able to parse '127'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull("127")
            result shouldBe 127
        }

        "toPositiveByteOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull("-1")
            result shouldBe null
        }

        "toPositiveByteOrNull() should not be able to parse '128'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull("128")
            result shouldBe null
        }

        "toPositiveByteOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull("abc")
            result shouldBe null
        }

        "toPositiveShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveShort("0")
            result shouldBe 0
        }

        "toPositiveShort() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveShort("1")
            result shouldBe 1
        }

        "toPositiveShort() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toPositiveShort("32767")
            result shouldBe 32767
        }

        "toPositiveShort() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort("-1")
            }
        }

        "toPositiveShort() should not be able to parse '32768'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort("32768")
            }
        }

        "toPositiveShort() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort("abc")
            }
        }

        "toPositiveShort() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort(null)
            }
        }

        "toPositiveShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort("")
            }
        }

        "toPositiveShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort(" ")
            }
        }

        "toPositiveShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("0")
            result shouldBe 0
        }

        "toPositiveShortOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("1")
            result shouldBe 1
        }

        "toPositiveShortOrNull() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("32767")
            result shouldBe 32767
        }

        "toPositiveShortOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("-1")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse '32768'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("32768")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("abc")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveShortOrNull(null)
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveShortOrNull("")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveShortOrNull(" ")
            result shouldBe null
        }

        "toPositiveInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveInt("0")
            result shouldBe 0
        }

        "toPositiveInt() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveInt("1")
            result shouldBe 1
        }

        "toPositiveInt() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toPositiveInt("2147483647")
            result shouldBe 2147483647
        }

        "toPositiveInt() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt("-1")
            }
        }

        "toPositiveInt() should not be able to parse '2147483648'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt("2147483648")
            }
        }

        "toPositiveInt() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt("abc")
            }
        }

        "toPositiveInt() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt(null)
            }
        }

        "toPositiveInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt("")
            }
        }

        "toPositiveInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt(" ")
            }
        }

        "toPositiveIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("0")
            result shouldBe 0
        }

        "toPositiveIntOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("1")
            result shouldBe 1
        }

        "toPositiveIntOrNull() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("2147483647")
            result shouldBe 2147483647
        }

        "toPositiveIntOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("-1")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse '2147483648'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("2147483648")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("abc")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveIntOrNull(null)
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveIntOrNull("")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveIntOrNull(" ")
            result shouldBe null
        }

        "toPositiveLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveLong("0")
            result shouldBe 0
        }

        "toPositiveLong() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveLong("1")
            result shouldBe 1
        }

        "toPositiveLong() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toPositiveLong("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toPositiveLong() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong("-1")
            }
        }

        "toPositiveLong() should not be able to parse '9223372036854775808'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong("9223372036854775808")
            }
        }

        "toPositiveLong() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong("abc")
            }
        }

        "toPositiveLong() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong(null)
            }
        }

        "toPositiveLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong("")
            }
        }

        "toPositiveLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong(" ")
            }
        }

        "toPositiveLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("0")
            result shouldBe 0
        }

        "toPositiveLongOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("1")
            result shouldBe 1
        }

        "toPositiveLongOrNull() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toPositiveLongOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("-1")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse '9223372036854775808'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("9223372036854775808")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("abc")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveLongOrNull(null)
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveLongOrNull("")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveLongOrNull(" ")
            result shouldBe null
        }

        "toPositiveFloat() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveFloat("0")
            result shouldBe 0f
        }

        "toPositiveFloat() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveFloat("1")
            result shouldBe 1f
        }

        "toPositiveFloat() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toPositiveFloat("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toPositiveFloat() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat("-1")
            }
        }

        "toPositiveFloat() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat("abc")
            }
        }

        "toPositiveFloat() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat(null)
            }
        }

        "toPositiveFloat() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat("")
            }
        }

        "toPositiveFloat() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat(" ")
            }
        }

        "toPositiveFloatOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull("0")
            result shouldBe 0f
        }

        "toPositiveFloatOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull("1")
            result shouldBe 1f
        }

        "toPositiveFloatOrNull() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toPositiveFloatOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull("-1")
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull("abc")
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull(null)
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull("")
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull(" ")
            result shouldBe null
        }

        "toPositiveDouble() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveDouble("0")
            result shouldBe 0.0
        }

        "toPositiveDouble() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveDouble("1")
            result shouldBe 1.0
        }

        "toPositiveDouble() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toPositiveDouble("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toPositiveDouble() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble("-1")
            }
        }

        "toPositiveDouble() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble("abc")
            }
        }

        "toPositiveDouble() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble(null)
            }
        }

        "toPositiveDouble() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble("")
            }
        }

        "toPositiveDouble() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble(" ")
            }
        }

        "toPositiveDoubleOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull("0")
            result shouldBe 0.0
        }

        "toPositiveDoubleOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull("1")
            result shouldBe 1.0
        }

        "toPositiveDoubleOrNull() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toPositiveDoubleOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull("-1")
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull("abc")
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull(null)
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull("")
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull(" ")
            result shouldBe null
        }

        "toNegativeByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeByte("0")
            result shouldBe 0
        }

        "toNegativeByte() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeByte("-1")
            result shouldBe -1
        }

        "toNegativeByte() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toNegativeByte("-128")
            result shouldBe -128
        }

        "toNegativeByte() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte("1")
            }
        }

        "toNegativeByte() should not be able to parse '127'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte("127")
            }
        }

        "toNegativeByte() should not be able to parse '128'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte("128")
            }
        }

        "toNegativeByte() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte("abc")
            }
        }

        "toNegativeByte() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte(null)
            }
        }

        "toNegativeByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte("")
            }
        }

        "toNegativeByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte(" ")
            }
        }

        "toNegativeByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("0")
            result shouldBe 0
        }

        "toNegativeByteOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("-1")
            result shouldBe -1
        }

        "toNegativeByteOrNull() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("-128")
            result shouldBe -128
        }

        "toNegativeByteOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("1")
            result shouldBe null
        }

        "toNegativeByteOrNull() should not be able to parse '127'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("127")
            result shouldBe null
        }

        "toNegativeByteOrNull() should not be able to parse '128'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("128")
            result shouldBe null
        }

        "toNegativeByteOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull("abc")
            result shouldBe null
        }

        "toNegativeShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeShort("0")
            result shouldBe 0
        }

        "toNegativeShort() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeShort("-1")
            result shouldBe -1
        }

        "toNegativeShort() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toNegativeShort("-32768")
            result shouldBe -32768
        }

        "toNegativeShort() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort("1")
            }
        }

        "toNegativeShort() should not be able to parse '32767'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort("32767")
            }
        }

        "toNegativeShort() should not be able to parse '32768'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort("32768")
            }
        }

        "toNegativeShort() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort("abc")
            }
        }

        "toNegativeShort() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort(null)
            }
        }

        "toNegativeShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort("")
            }
        }

        "toNegativeShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort(" ")
            }
        }

        "toNegativeShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("0")
            result shouldBe 0
        }

        "toNegativeShortOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("-1")
            result shouldBe -1
        }

        "toNegativeShortOrNull() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("-32768")
            result shouldBe -32768
        }

        "toNegativeShortOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("1")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse '32767'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("32767")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse '32768'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("32768")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("abc")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeShortOrNull(null)
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeShortOrNull("")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeShortOrNull(" ")
            result shouldBe null
        }

        "toNegativeInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeInt("0")
            result shouldBe 0
        }

        "toNegativeInt() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeInt("-1")
            result shouldBe -1
        }

        "toNegativeInt() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toNegativeInt("-2147483648")
            result shouldBe -2147483648
        }

        "toNegativeInt() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt("1")
            }
        }

        "toNegativeInt() should not be able to parse '2147483647'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt("2147483647")
            }
        }

        "toNegativeInt() should not be able to parse '2147483648'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt("2147483648")
            }
        }

        "toNegativeInt() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt("abc")
            }
        }

        "toNegativeInt() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt(null)
            }
        }

        "toNegativeInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt("")
            }
        }

        "toNegativeInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt(" ")
            }
        }

        "toNegativeIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("0")
            result shouldBe 0
        }

        "toNegativeIntOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("-1")
            result shouldBe -1
        }

        "toNegativeIntOrNull() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("-2147483648")
            result shouldBe -2147483648
        }

        "toNegativeIntOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("1")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("2147483647")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse '2147483648'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("2147483648")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("abc")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeIntOrNull(null)
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeIntOrNull("")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeIntOrNull(" ")
            result shouldBe null
        }

        "toNegativeLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeLong("0")
            result shouldBe 0
        }

        "toNegativeLong() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeLong("-1")
            result shouldBe -1
        }

        "toNegativeLong() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toNegativeLong("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toNegativeLong() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong("1")
            }
        }

        "toNegativeLong() should not be able to parse '9223372036854775807'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong("9223372036854775807")
            }
        }

        "toNegativeLong() should not be able to parse '9223372036854775808'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong("9223372036854775808")
            }
        }

        "toNegativeLong() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong("abc")
            }
        }

        "toNegativeLong() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong(null)
            }
        }

        "toNegativeLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong("")
            }
        }

        "toNegativeLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong(" ")
            }
        }

        "toNegativeLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("0")
            result shouldBe 0
        }

        "toNegativeLongOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("-1")
            result shouldBe -1
        }

        "toNegativeLongOrNull() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toNegativeLongOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("1")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("9223372036854775807")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse '9223372036854775808'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("9223372036854775808")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("abc")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeLongOrNull(null)
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeLongOrNull("")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeLongOrNull(" ")
            result shouldBe null
        }

        "toNegativeFloat() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeFloat("0")
            result shouldBe 0f
        }

        "toNegativeFloat() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeFloat("-1")
            result shouldBe -1f
        }

        "toNegativeFloat() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toNegativeFloat("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toNegativeFloat() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat("1")
            }
        }

        "toNegativeFloat() should not be able to parse '3.4028236E38'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat("3.4028236E38")
            }
        }

        "toNegativeFloat() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat("abc")
            }
        }

        "toNegativeFloat() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat(null)
            }
        }

        "toNegativeFloat() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat("")
            }
        }

        "toNegativeFloat() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat(" ")
            }
        }

        "toNegativeFloatOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("0")
            result shouldBe 0f
        }

        "toNegativeFloatOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("-1")
            result shouldBe -1f
        }

        "toNegativeFloatOrNull() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toNegativeFloatOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("1")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse '3.4028236E38'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("3.4028236E38")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("abc")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull(null)
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull("")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull(" ")
            result shouldBe null
        }

        "toNegativeDouble() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeDouble("0")
            result shouldBe 0.0
        }

        "toNegativeDouble() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeDouble("-1")
            result shouldBe -1.0
        }

        "toNegativeDouble() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toNegativeDouble("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toNegativeDouble() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble("1")
            }
        }

        "toNegativeDouble() should not be able to parse '1.7976931348623159E308'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble("1.7976931348623159E308")
            }
        }

        "toNegativeDouble() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble("abc")
            }
        }

        "toNegativeDouble() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble(null)
            }
        }

        "toNegativeDouble() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble("")
            }
        }

        "toNegativeDouble() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble(" ")
            }
        }

        "toNegativeDoubleOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("0")
            result shouldBe 0.0
        }

        "toNegativeDoubleOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("-1")
            result shouldBe -1.0
        }

        "toNegativeDoubleOrNull() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toNegativeDoubleOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("1")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse '1.7976931348623159E308'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("1.7976931348623159E308")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("abc")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull(null)
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull("")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull(" ")
            result shouldBe null
        }

        "toString() should be able to parse 'abc'" {
            val result = CommanderValueTransformers.toString("abc")
            result shouldBe "abc"
        }

        "toString() should be able to parse '123'" {
            val result = CommanderValueTransformers.toString("123")
            result shouldBe "123"
        }

        "toString() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toString("true")
            result shouldBe "true"
        }

        "toString() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toString("false")
            result shouldBe "false"
        }

        "toString() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toString(null)
            }
        }

        "toString() should be able to parse ''" {
            CommanderValueTransformers.toString("") shouldBe ""
        }

        "toString() should be able to parse ' '" {
            CommanderValueTransformers.toString(" ") shouldBe " "
        }

        "toStringOrNull() should be able to parse 'abc'" {
            val result = CommanderValueTransformers.toStringOrNull("abc")
            result shouldBe "abc"
        }

        "toStringOrNull() should be able to parse '123'" {
            val result = CommanderValueTransformers.toStringOrNull("123")
            result shouldBe "123"
        }

        "toStringOrNull() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toStringOrNull("true")
            result shouldBe "true"
        }

        "toStringOrNull() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toStringOrNull("false")
            result shouldBe "false"
        }

        "toStringOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toStringOrNull(null)
            result shouldBe null
        }

        "toStringOrNull() should be able to parse ''" {
            CommanderValueTransformers.toStringOrNull("") shouldBe ""
        }

        "toStringOrNull() should be able to parse ' '" {
            CommanderValueTransformers.toStringOrNull(" ") shouldBe " "
        }
    },
)
