package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ValueTransformerFunctionTests : FreeSpec({

    "toByte() should be able to parse '0'" {
        val result = ValueTransformers.toByte("0")
        result shouldBe 0
    }

    "toByte() should be able to parse '1'" {
        val result = ValueTransformers.toByte("1")
        result shouldBe 1
    }

    "toByte() should be able to parse '-1'" {
        val result = ValueTransformers.toByte("-1")
        result shouldBe -1
    }

    "toByte() should be able to parse '127'" {
        val result = ValueTransformers.toByte("127")
        result shouldBe 127
    }

    "toByte() should be able to parse '-128'" {
        val result = ValueTransformers.toByte("-128")
        result shouldBe -128
    }

    "toByte() should fail to parse '128'" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte("128")
        }
    }

    "toByte() should fail to parse '-129'" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte("-129")
        }
    }

    "toByte() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte("abc")
        }
    }

    "toByte() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte(null)
        }
    }

    "toShort() should be able to parse '0'" {
        val result = ValueTransformers.toShort("0")
        result shouldBe 0
    }

    "toShort() should be able to parse '1'" {
        val result = ValueTransformers.toShort("1")
        result shouldBe 1
    }

    "toShort() should be able to parse '-1'" {
        val result = ValueTransformers.toShort("-1")
        result shouldBe -1
    }

    "toShort() should be able to parse '32767'" {
        val result = ValueTransformers.toShort("32767")
        result shouldBe 32767
    }

    "toShort() should be able to parse '-32768'" {
        val result = ValueTransformers.toShort("-32768")
        result shouldBe -32768
    }

    "toShort() should fail to parse '32768'" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort("32768")
        }
    }

    "toShort() should fail to parse '-32769'" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort("-32769")
        }
    }

    "toShort() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort("abc")
        }
    }

    "toShort() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort(null)
        }
    }

    "toInt() should be able to parse '0'" {
        val result = ValueTransformers.toInt("0")
        result shouldBe 0
    }

    "toInt() should be able to parse '1'" {
        val result = ValueTransformers.toInt("1")
        result shouldBe 1
    }

    "toInt() should be able to parse '-1'" {
        val result = ValueTransformers.toInt("-1")
        result shouldBe -1
    }

    "toInt() should be able to parse '2147483647'" {
        val result = ValueTransformers.toInt("2147483647")
        result shouldBe 2147483647
    }

    "toInt() should be able to parse '-2147483648'" {
        val result = ValueTransformers.toInt("-2147483648")
        result shouldBe -2147483648
    }

    "toInt() should fail to parse '2147483648'" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt("2147483648")
        }
    }

    "toInt() should fail to parse '-2147483649'" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt("-2147483649")
        }
    }

    "toInt() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt("abc")
        }
    }

    "toInt() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt(null)
        }
    }

    "toLong() should be able to parse '0'" {
        val result = ValueTransformers.toLong("0")
        result shouldBe 0
    }

    "toLong() should be able to parse '1'" {
        val result = ValueTransformers.toLong("1")
        result shouldBe 1
    }

    "toLong() should be able to parse '-1'" {
        val result = ValueTransformers.toLong("-1")
        result shouldBe -1
    }

    "toLong() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toLong("9223372036854775807")
        result shouldBe 9223372036854775807
    }

    "toLong() should be able to parse '-9223372036854775808'" {
        val result = ValueTransformers.toLong("-9223372036854775808")
        result shouldBe Long.MIN_VALUE
    }

    "toLong() should fail to parse '9223372036854775808'" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong("9223372036854775808")
        }
    }

    "toLong() should fail to parse '-9223372036854775809'" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong("-9223372036854775809")
        }
    }

    "toLong() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong("abc")
        }
    }

    "toLong() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong(null)
        }
    }

    "toFloat() should be able to parse '0'" {
        val result = ValueTransformers.toFloat("0")
        result shouldBe 0f
    }

    "toFloat() should be able to parse '1'" {
        val result = ValueTransformers.toFloat("1")
        result shouldBe 1f
    }

    "toFloat() should be able to parse '-1'" {
        val result = ValueTransformers.toFloat("-1")
        result shouldBe -1f
    }

    "toFloat() should be able to parse '3.4028235E38'" {
        val result = ValueTransformers.toFloat("3.4028235E38")
        result shouldBe 3.4028235E38f
    }

    "toFloat() should be able to parse '-3.4028235E38'" {
        val result = ValueTransformers.toFloat("-3.4028235E38")
        result shouldBe -3.4028235E38f
    }

    "toFloat() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toFloat("abc")
        }
    }

    "toFloat() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toFloat(null)
        }
    }

    "toDouble() should be able to parse '0'" {
        val result = ValueTransformers.toDouble("0")
        result shouldBe 0.0
    }

    "toDouble() should be able to parse '1'" {
        val result = ValueTransformers.toDouble("1")
        result shouldBe 1.0
    }

    "toDouble() should be able to parse '-1'" {
        val result = ValueTransformers.toDouble("-1")
        result shouldBe -1.0
    }

    "toDouble() should be able to parse '1.7976931348623157E308'" {
        val result = ValueTransformers.toDouble("1.7976931348623157E308")
        result shouldBe 1.7976931348623157E308
    }

    "toDouble() should be able to parse '-1.7976931348623157E308'" {
        val result = ValueTransformers.toDouble("-1.7976931348623157E308")
        result shouldBe -1.7976931348623157E308
    }

    "toDouble() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toDouble("abc")
        }
    }

    "toDouble() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toDouble(null)
        }
    }

    "toBoolean() should be able to parse 'true'" {
        val result = ValueTransformers.toBoolean("true")
        result shouldBe true
    }

    "toBoolean() should be able to parse 'false'" {
        val result = ValueTransformers.toBoolean("false")
        result shouldBe false
    }

    "toBoolean() should be able to parse '1'" {
        val result = ValueTransformers.toBoolean("1")
        result shouldBe true
    }

    "toBoolean() should be able to parse '0'" {
        val result = ValueTransformers.toBoolean("0")
        result shouldBe false
    }

    "toBoolean() should fail to parse '2'" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean("2")
        }
    }

    "toBoolean() should fail to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean("-1")
        }
    }

    "toBoolean() should be able to parse 'yes'" {
        val result = ValueTransformers.toBoolean("yes")
        result shouldBe true
    }

    "toBoolean() should be able to parse 'no'" {
        val result = ValueTransformers.toBoolean("no")
        result shouldBe false
    }

    "toBoolean() should be able to parse 'y'" {
        val result = ValueTransformers.toBoolean("y")
        result shouldBe true
    }

    "toBoolean() should be able to parse 'n'" {
        val result = ValueTransformers.toBoolean("n")
        result shouldBe false
    }

    "toBoolean() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean("abc")
        }
    }

    "toBoolean() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean(null)
        }
    }

    "toChar() should be able to parse 'a'" {
        val result = ValueTransformers.toChar("a")
        result shouldBe 'a'
    }

    "toChar() should be able to parse 'A'" {
        val result = ValueTransformers.toChar("A")
        result shouldBe 'A'
    }

    "toChar() should be able to parse '1'" {
        val result = ValueTransformers.toChar("1")
        result shouldBe '1'
    }

    "toChar() should be able to parse ' '" {
        val result = ValueTransformers.toChar(" ")
        result shouldBe ' '
    }

    "toChar() should not be able to parse 'ab'" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar("ab")
        }
    }

    "toChar() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar(null)
        }
    }

    "toChar() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar("")
        }
    }

    "toChar() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar("abc")
        }
    }

    "toUnsignedByte() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedByte("0")
        result shouldBe 0u.toUByte()
    }

    "toUnsignedByte() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedByte("1")
        result shouldBe 1u.toUByte()
    }

    "toUnsignedByte() should be able to parse '127'" {
        val result = ValueTransformers.toUnsignedByte("127")
        result shouldBe 127u.toUByte()
    }

    "toUnsignedByte() should be able to parse '128'" {
        val result = ValueTransformers.toUnsignedByte("128")
        result shouldBe 128u.toUByte()
    }

    "toUnsignedByte() should be able to parse '255'" {
        val result = ValueTransformers.toUnsignedByte("255")
        result shouldBe 255u.toUByte()
    }

    "toUnsignedByte() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte("-1")
        }
    }

    "toUnsignedByte() should not be able to parse '256'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte("256")
        }
    }

    "toUnsignedByte() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte("abc")
        }
    }

    "toUnsignedByte() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte(null)
        }
    }

    "toUnsignedShort() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedShort("0")
        result shouldBe 0u.toUShort()
    }

    "toUnsignedShort() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedShort("1")
        result shouldBe 1u.toUShort()
    }

    "toUnsignedShort() should be able to parse '32767'" {
        val result = ValueTransformers.toUnsignedShort("65535")
        result shouldBe 65535u.toUShort()
    }

    "toUnsignedShort() should be able to parse '32768'" {
        val result = ValueTransformers.toUnsignedShort("32768")
        result shouldBe 32768u.toUShort()
    }

    "toUnsignedShort() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort("-1")
        }
    }

    "toUnsignedShort() should not be able to parse '65536'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort("65536")
        }
    }

    "toUnsignedShort() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort("abc")
        }
    }

    "toUnsignedShort() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort(null)
        }
    }

    "toUnsignedInt() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedInt("0")
        result shouldBe 0u
    }

    "toUnsignedInt() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedInt("1")
        result shouldBe 1u
    }

    "toUnsignedInt() should be able to parse '2147483647'" {
        val result = ValueTransformers.toUnsignedInt("2147483647")
        result shouldBe 2147483647u
    }

    "toUnsignedInt() should be able to parse '4294967295'" {
        val result = ValueTransformers.toUnsignedInt("4294967295")
        result shouldBe 4294967295u
    }

    "toUnsignedInt() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt("-1")
        }
    }

    "toUnsignedInt() should not be able to parse '4294967296'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt("4294967296")
        }
    }

    "toUnsignedInt() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt("abc")
        }
    }

    "toUnsignedInt() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt(null)
        }
    }

    "toUnsignedLong() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedLong("0")
        result shouldBe 0uL
    }

    "toUnsignedLong() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedLong("1")
        result shouldBe 1uL
    }

    "toUnsignedLong() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toUnsignedLong("9223372036854775807")
        result shouldBe 9223372036854775807uL
    }

    "toUnsignedLong() should be able to parse '18446744073709551615'" {
        val result = ValueTransformers.toUnsignedLong( "18446744073709551615")
        result shouldBe ULong.MAX_VALUE
    }

    "toUnsignedLong() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong("-1")
        }
    }

    "toUnsignedLong() should not be able to parse '18446744073709551616'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong("18446744073709551616")
        }
    }

    "toUnsignedLong() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong("abc")
        }
    }

    "toUnsignedLong() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong(null)
        }
    }

    "toUnsignedLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong("")
        }
    }

    "toUnsignedLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong(" ")
        }
    }

    "toPositiveByte() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveByte("0")
        result shouldBe 0
    }

    "toPositiveByte() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveByte("1")
        result shouldBe 1
    }

    "toPositiveByte() should be able to parse '127'" {
        val result = ValueTransformers.toPositiveByte("127")
        result shouldBe 127
    }

    "toPositiveByte() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte("-1")
        }
    }

    "toPositiveByte() should not be able to parse '128'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte("128")
        }
    }

    "toPositiveByte() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte("abc")
        }
    }

    "toPositiveByte() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte(null)
        }
    }

    "toPositiveShort() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveShort("0")
        result shouldBe 0
    }

    "toPositiveShort() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveShort("1")
        result shouldBe 1
    }

    "toPositiveShort() should be able to parse '32767'" {
        val result = ValueTransformers.toPositiveShort("32767")
        result shouldBe 32767
    }

    "toPositiveShort() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort("-1")
        }
    }

"toPositiveShort() should not be able to parse '32768'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort("32768")
        }
    }

    "toPositiveShort() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort("abc")
        }
    }

    "toPositiveShort() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort(null)
        }
    }

    "toPositiveInt() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveInt("0")
        result shouldBe 0
    }

    "toPositiveInt() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveInt("1")
        result shouldBe 1
    }

    "toPositiveInt() should be able to parse '2147483647'" {
        val result = ValueTransformers.toPositiveInt("2147483647")
        result shouldBe 2147483647
    }

    "toPositiveInt() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt("-1")
        }
    }

    "toPositiveInt() should not be able to parse '2147483648'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt("2147483648")
        }
    }

    "toPositiveInt() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt("abc")
        }
    }

    "toPositiveInt() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt(null)
        }
    }

    "toPositiveLong() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveLong("0")
        result shouldBe 0
    }

    "toPositiveLong() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveLong("1")
        result shouldBe 1
    }

    "toPositiveLong() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toPositiveLong("9223372036854775807")
        result shouldBe 9223372036854775807
    }

    "toPositiveLong() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong("-1")
        }
    }

    "toPositiveLong() should not be able to parse '9223372036854775808'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong("9223372036854775808")
        }
    }

    "toPositiveLong() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong("abc")
        }
    }

    "toPositiveLong() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong(null)
        }
    }

    "toPositiveLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong("")
        }
    }

    "toPositiveLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong(" ")
        }
    }

    "toPositiveFloat() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveFloat("0")
        result shouldBe 0f
    }

    "toPositiveFloat() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveFloat("1")
        result shouldBe 1f
    }

    "toPositiveFloat() should be able to parse '3.4028235E38'" {
        val result = ValueTransformers.toPositiveFloat("3.4028235E38")
        result shouldBe 3.4028235E38f
    }

    "toPositiveFloat() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat("-1")
        }
    }

    "toPositiveFloat() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat("abc")
        }
    }

    "toPositiveFloat() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat(null)
        }
    }

    "toPositiveFloat() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat("")
        }
    }

    "toPositiveFloat() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat(" ")
        }
    }

    "toPositiveDouble() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveDouble("0")
        result shouldBe 0.0
    }

    "toPositiveDouble() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveDouble("1")
        result shouldBe 1.0
    }

    "toPositiveDouble() should be able to parse '1.7976931348623157E308'" {
        val result = ValueTransformers.toPositiveDouble("1.7976931348623157E308")
        result shouldBe 1.7976931348623157E308
    }

    "toPositiveDouble() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble("-1")
        }
    }

    "toPositiveDouble() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble("abc")
        }
    }

    "toPositiveDouble() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble(null)
        }
    }

    "toPositiveDouble() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble("")
        }
    }

    "toPositiveDouble() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble(" ")
        }
    }

    "toNegativeByte() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeByte("0")
        result shouldBe 0
    }

    "toNegativeByte() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeByte("-1")
        result shouldBe -1
    }

    "toNegativeByte() should be able to parse '-128'" {
        val result = ValueTransformers.toNegativeByte("-128")
        result shouldBe -128
    }

    "toNegativeByte() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte("1")
        }
    }

    "toNegativeByte() should not be able to parse '127'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte("127")
        }
    }

    "toNegativeByte() should not be able to parse '128'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte("128")
        }
    }

    "toNegativeByte() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte("abc")
        }
    }

    "toNegativeByte() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte(null)
        }
    }

    "toNegativeShort() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeShort("0")
        result shouldBe 0
    }

    "toNegativeShort() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeShort("-1")
        result shouldBe -1
    }

    "toNegativeShort() should be able to parse '-32768'" {
        val result = ValueTransformers.toNegativeShort("-32768")
        result shouldBe -32768
    }

    "toNegativeShort() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort("1")
        }
    }

    "toNegativeShort() should not be able to parse '32767'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort("32767")
        }
    }

"toNegativeShort() should not be able to parse '32768'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort("32768")
        }
    }

    "toNegativeShort() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort("abc")
        }
    }

    "toNegativeShort() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort(null)
        }
    }

    "toNegativeInt() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeInt("0")
        result shouldBe 0
    }

    "toNegativeInt() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeInt("-1")
        result shouldBe -1
    }

    "toNegativeInt() should be able to parse '-2147483648'" {
        val result = ValueTransformers.toNegativeInt("-2147483648")
        result shouldBe -2147483648
    }

    "toNegativeInt() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt("1")
        }
    }

    "toNegativeInt() should not be able to parse '2147483647'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt("2147483647")
        }
    }

    "toNegativeInt() should not be able to parse '2147483648'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt("2147483648")
        }
    }

    "toNegativeInt() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt("abc")
        }
    }

    "toNegativeInt() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt(null)
        }
    }

    "toNegativeLong() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeLong("0")
        result shouldBe 0
    }

    "toNegativeLong() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeLong("-1")
        result shouldBe -1
    }

    "toNegativeLong() should be able to parse '-9223372036854775808'" {
        val result = ValueTransformers.toNegativeLong("-9223372036854775808")
        result shouldBe Long.MIN_VALUE
    }

    "toNegativeLong() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong("1")
        }
    }

    "toNegativeLong() should not be able to parse '9223372036854775807'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong("9223372036854775807")
        }
    }

    "toNegativeLong() should not be able to parse '9223372036854775808'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong("9223372036854775808")
        }
    }

    "toNegativeLong() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong("abc")
        }
    }

    "toNegativeLong() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong(null)
        }
    }

    "toNegativeLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong("")
        }
    }

    "toNegativeLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong(" ")
        }
    }

    "toNegativeFloat() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeFloat("0")
        result shouldBe 0f
    }

    "toNegativeFloat() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeFloat("-1")
        result shouldBe -1f
    }

    "toNegativeFloat() should be able to parse '-3.4028235E38'" {
        val result = ValueTransformers.toNegativeFloat("-3.4028235E38")
        result shouldBe -3.4028235E38f
    }

    "toNegativeFloat() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat("1")
        }
    }

    "toNegativeFloat() should not be able to parse '3.4028236E38'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat("3.4028236E38")
        }
    }

    "toNegativeFloat() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat("abc")
        }
    }

    "toNegativeFloat() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat(null)
        }
    }

    "toNegativeFloat() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat("")
        }
    }

    "toNegativeFloat() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat(" ")
        }
    }

    "toNegativeDouble() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeDouble("0")
        result shouldBe 0.0
    }

    "toNegativeDouble() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeDouble("-1")
        result shouldBe -1.0
    }

    "toNegativeDouble() should be able to parse '-1.7976931348623157E308'" {
        val result = ValueTransformers.toNegativeDouble("-1.7976931348623157E308")
        result shouldBe -1.7976931348623157E308
    }

    "toNegativeDouble() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble("1")
        }
    }

    "toNegativeDouble() should not be able to parse '1.7976931348623159E308'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble("1.7976931348623159E308")
        }
    }

    "toNegativeDouble() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble("abc")
        }
    }

    "toNegativeDouble() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble(null)
        }
    }

    "toNegativeDouble() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble("")
        }
    }

    "toNegativeDouble() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble(" ")
        }
    }

})