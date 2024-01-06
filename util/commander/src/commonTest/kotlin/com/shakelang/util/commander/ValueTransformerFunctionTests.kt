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

    "toByte() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte("")
        }
    }

    "toByte() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte(" ")
        }
    }

    "toByteOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toByteOrNull("0")
        result shouldBe 0
    }

    "toByteOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toByteOrNull("1")
        result shouldBe 1
    }

    "toByteOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toByteOrNull("-1")
        result shouldBe -1
    }

    "toByteOrNull() should be able to parse '127'" {
        val result = ValueTransformers.toByteOrNull("127")
        result shouldBe 127
    }

    "toByteOrNull() should be able to parse '-128'" {
        val result = ValueTransformers.toByteOrNull("-128")
        result shouldBe -128
    }

    "toByteOrNull() should fail to parse '128'" {
        val result = ValueTransformers.toByteOrNull("128")
        result shouldBe null
    }

    "toByteOrNull() should fail to parse '-129'" {
        val result = ValueTransformers.toByteOrNull("-129")
        result shouldBe null
    }

    "toByteOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toByteOrNull("abc")
        result shouldBe null
    }

    "toByteOrNull() should fail to parse null" {
        val result = ValueTransformers.toByteOrNull(null)
        result shouldBe null
    }

    "toByteOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toByteOrNull("")
        result shouldBe null
    }

    "toByteOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toByteOrNull(" ")
        result shouldBe null
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

    "toShort() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort("")
        }
    }

    "toShort() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort(" ")
        }
    }

    "toShortOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toShortOrNull("0")
        result shouldBe 0
    }

    "toShortOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toShortOrNull("1")
        result shouldBe 1
    }

    "toShortOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toShortOrNull("-1")
        result shouldBe -1
    }

    "toShortOrNull() should be able to parse '32767'" {
        val result = ValueTransformers.toShortOrNull("32767")
        result shouldBe 32767
    }

    "toShortOrNull() should be able to parse '-32768'" {
        val result = ValueTransformers.toShortOrNull("-32768")
        result shouldBe -32768
    }

    "toShortOrNull() should fail to parse '32768'" {
        val result = ValueTransformers.toShortOrNull("32768")
        result shouldBe null
    }

    "toShortOrNull() should fail to parse '-32769'" {
        val result = ValueTransformers.toShortOrNull("-32769")
        result shouldBe null
    }

    "toShortOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toShortOrNull("abc")
        result shouldBe null
    }

    "toShortOrNull() should fail to parse null" {
        val result = ValueTransformers.toShortOrNull(null)
        result shouldBe null
    }

    "toShortOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toShortOrNull("")
        result shouldBe null
    }

    "toShortOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toShortOrNull(" ")
        result shouldBe null
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

    "toInt() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt("")
        }
    }

    "toInt() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt(" ")
        }
    }

    "toIntOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toIntOrNull("0")
        result shouldBe 0
    }

    "toIntOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toIntOrNull("1")
        result shouldBe 1
    }

    "toIntOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toIntOrNull("-1")
        result shouldBe -1
    }

    "toIntOrNull() should be able to parse '2147483647'" {
        val result = ValueTransformers.toIntOrNull("2147483647")
        result shouldBe 2147483647
    }

    "toIntOrNull() should be able to parse '-2147483648'" {
        val result = ValueTransformers.toIntOrNull("-2147483648")
        result shouldBe -2147483648
    }

    "toIntOrNull() should fail to parse '2147483648'" {
        val result = ValueTransformers.toIntOrNull("2147483648")
        result shouldBe null
    }

    "toIntOrNull() should fail to parse '-2147483649'" {
        val result = ValueTransformers.toIntOrNull("-2147483649")
        result shouldBe null
    }

    "toIntOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toIntOrNull("abc")
        result shouldBe null
    }

    "toIntOrNull() should fail to parse null" {
        val result = ValueTransformers.toIntOrNull(null)
        result shouldBe null
    }

    "toIntOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toIntOrNull("")
        result shouldBe null
    }

    "toIntOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toIntOrNull(" ")
        result shouldBe null
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

    "toLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong("")
        }
    }

    "toLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong(" ")
        }
    }

    "toLongOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toLongOrNull("0")
        result shouldBe 0
    }

    "toLongOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toLongOrNull("1")
        result shouldBe 1
    }

    "toLongOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toLongOrNull("-1")
        result shouldBe -1
    }

    "toLongOrNull() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toLongOrNull("9223372036854775807")
        result shouldBe 9223372036854775807
    }

    "toLongOrNull() should be able to parse '-9223372036854775808'" {
        val result = ValueTransformers.toLongOrNull("-9223372036854775808")
        result shouldBe Long.MIN_VALUE
    }

    "toLongOrNull() should fail to parse '9223372036854775808'" {
        val result = ValueTransformers.toLongOrNull("9223372036854775808")
        result shouldBe null
    }

    "toLongOrNull() should fail to parse '-9223372036854775809'" {
        val result = ValueTransformers.toLongOrNull("-9223372036854775809")
        result shouldBe null
    }

    "toLongOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toLongOrNull("abc")
        result shouldBe null
    }

    "toLongOrNull() should fail to parse null" {
        val result = ValueTransformers.toLongOrNull(null)
        result shouldBe null
    }

    "toLongOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toLongOrNull("")
        result shouldBe null
    }

    "toLongOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toLongOrNull(" ")
        result shouldBe null
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

    "toFloat() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toFloat("")
        }
    }

    "toFloat() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toFloat(" ")
        }
    }

    "toFloatOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toFloatOrNull("0")
        result shouldBe 0f
    }

    "toFloatOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toFloatOrNull("1")
        result shouldBe 1f
    }

    "toFloatOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toFloatOrNull("-1")
        result shouldBe -1f
    }

    "toFloatOrNull() should be able to parse '3.4028235E38'" {
        val result = ValueTransformers.toFloatOrNull("3.4028235E38")
        result shouldBe 3.4028235E38f
    }

    "toFloatOrNull() should be able to parse '-3.4028235E38'" {
        val result = ValueTransformers.toFloatOrNull("-3.4028235E38")
        result shouldBe -3.4028235E38f
    }

    "toFloatOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toFloatOrNull("abc")
        result shouldBe null
    }

    "toFloatOrNull() should fail to parse null" {
        val result = ValueTransformers.toFloatOrNull(null)
        result shouldBe null
    }

    "toFloatOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toFloatOrNull("")
        result shouldBe null
    }

    "toFloatOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toFloatOrNull(" ")
        result shouldBe null
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

    "toDouble() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toDouble("")
        }
    }

    "toDouble() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toDouble(" ")
        }
    }

    "toDoubleOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toDoubleOrNull("0")
        result shouldBe 0.0
    }

    "toDoubleOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toDoubleOrNull("1")
        result shouldBe 1.0
    }

    "toDoubleOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toDoubleOrNull("-1")
        result shouldBe -1.0
    }

    "toDoubleOrNull() should be able to parse '1.7976931348623157E308'" {
        val result = ValueTransformers.toDoubleOrNull("1.7976931348623157E308")
        result shouldBe 1.7976931348623157E308
    }

    "toDoubleOrNull() should be able to parse '-1.7976931348623157E308'" {
        val result = ValueTransformers.toDoubleOrNull("-1.7976931348623157E308")
        result shouldBe -1.7976931348623157E308
    }

    "toDoubleOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toDoubleOrNull("abc")
        result shouldBe null
    }

    "toDoubleOrNull() should fail to parse null" {
        val result = ValueTransformers.toDoubleOrNull(null)
        result shouldBe null
    }

    "toDoubleOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toDoubleOrNull("")
        result shouldBe null
    }

    "toDoubleOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toDoubleOrNull(" ")
        result shouldBe null
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

    "toBoolean() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean("")
        }
    }

    "toBoolean() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean(" ")
        }
    }

    "toBooleanOrNull() should be able to parse 'true'" {
        val result = ValueTransformers.toBooleanOrNull("true")
        result shouldBe true
    }

    "toBooleanOrNull() should be able to parse 'false'" {
        val result = ValueTransformers.toBooleanOrNull("false")
        result shouldBe false
    }

    "toBooleanOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toBooleanOrNull("1")
        result shouldBe true
    }

    "toBooleanOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toBooleanOrNull("0")
        result shouldBe false
    }

    "toBooleanOrNull() should fail to parse '2'" {
        val result = ValueTransformers.toBooleanOrNull("2")
        result shouldBe null
    }

    "toBooleanOrNull() should fail to parse '-1'" {
        val result = ValueTransformers.toBooleanOrNull("-1")
        result shouldBe null
    }

    "toBooleanOrNull() should be able to parse 'yes'" {
        val result = ValueTransformers.toBooleanOrNull("yes")
        result shouldBe true
    }

    "toBooleanOrNull() should be able to parse 'no'" {
        val result = ValueTransformers.toBooleanOrNull("no")
        result shouldBe false
    }

    "toBooleanOrNull() should be able to parse 'y'" {
        val result = ValueTransformers.toBooleanOrNull("y")
        result shouldBe true
    }

    "toBooleanOrNull() should be able to parse 'n'" {
        val result = ValueTransformers.toBooleanOrNull("n")
        result shouldBe false
    }

    "toBooleanOrNull() should fail to parse 'abc'" {
        val result = ValueTransformers.toBooleanOrNull("abc")
        result shouldBe null
    }

    "toBooleanOrNull() should fail to parse null" {
        val result = ValueTransformers.toBooleanOrNull(null)
        result shouldBe null
    }

    "toBooleanOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toBooleanOrNull("")
        result shouldBe null
    }

    "toBooleanOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toBooleanOrNull(" ")
        result shouldBe null
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

    "toCharOrNull() should be able to parse 'a'" {
        val result = ValueTransformers.toCharOrNull("a")
        result shouldBe 'a'
    }

    "toCharOrNull() should be able to parse 'A'" {
        val result = ValueTransformers.toCharOrNull("A")
        result shouldBe 'A'
    }

    "toCharOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toCharOrNull("1")
        result shouldBe '1'
    }

    "toCharOrNull() should be able to parse ' '" {
        val result = ValueTransformers.toCharOrNull(" ")
        result shouldBe ' '
    }

    "toCharOrNull() should not be able to parse 'ab'" {
        val result = ValueTransformers.toCharOrNull("ab")
        result shouldBe null
    }

    "toCharOrNull() should not be able to parse null" {
        val result = ValueTransformers.toCharOrNull(null)
        result shouldBe null
    }

    "toCharOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toCharOrNull("")
        result shouldBe null
    }

    "toCharOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toCharOrNull("abc")
        result shouldBe null
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

    "toUnsignedByte() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte("")
        }
    }

    "toUnsignedByte() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte(" ")
        }
    }

    "toUnsignedByteOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedByteOrNull("0")
        result shouldBe 0u.toUByte()
    }

    "toUnsignedByteOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedByteOrNull("1")
        result shouldBe 1u.toUByte()
    }

    "toUnsignedByteOrNull() should be able to parse '127'" {
        val result = ValueTransformers.toUnsignedByteOrNull("127")
        result shouldBe 127u.toUByte()
    }

    "toUnsignedByteOrNull() should be able to parse '128'" {
        val result = ValueTransformers.toUnsignedByteOrNull("128")
        result shouldBe 128u.toUByte()
    }

    "toUnsignedByteOrNull() should be able to parse '255'" {
        val result = ValueTransformers.toUnsignedByteOrNull("255")
        result shouldBe 255u.toUByte()
    }

    "toUnsignedByteOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toUnsignedByteOrNull("-1")
        result shouldBe null
    }

    "toUnsignedByteOrNull() should not be able to parse '256'" {
        val result = ValueTransformers.toUnsignedByteOrNull("256")
        result shouldBe null
    }

    "toUnsignedByteOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toUnsignedByteOrNull("abc")
        result shouldBe null
    }

    "toUnsignedByteOrNull() should not be able to parse null" {
        val result = ValueTransformers.toUnsignedByteOrNull(null)
        result shouldBe null
    }

    "toUnsignedByteOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toUnsignedByteOrNull("")
        result shouldBe null
    }

    "toUnsignedByteOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toUnsignedByteOrNull(" ")
        result shouldBe null
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

    "toUnsignedShort() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort("")
        }
    }

    "toUnsignedShort() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort(" ")
        }
    }

    "toUnsignedShortOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedShortOrNull("0")
        result shouldBe 0u.toUShort()
    }

    "toUnsignedShortOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedShortOrNull("1")
        result shouldBe 1u.toUShort()
    }

    "toUnsignedShortOrNull() should be able to parse '32767'" {
        val result = ValueTransformers.toUnsignedShortOrNull("65535")
        result shouldBe 65535u.toUShort()
    }

    "toUnsignedShortOrNull() should be able to parse '32768'" {
        val result = ValueTransformers.toUnsignedShortOrNull("32768")
        result shouldBe 32768u.toUShort()
    }

    "toUnsignedShortOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toUnsignedShortOrNull("-1")
        result shouldBe null
    }

    "toUnsignedShortOrNull() should not be able to parse '65536'" {
        val result = ValueTransformers.toUnsignedShortOrNull("65536")
        result shouldBe null
    }

    "toUnsignedShortOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toUnsignedShortOrNull("abc")
        result shouldBe null
    }

    "toUnsignedShortOrNull() should not be able to parse null" {
        val result = ValueTransformers.toUnsignedShortOrNull(null)
        result shouldBe null
    }

    "toUnsignedShortOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toUnsignedShortOrNull("")
        result shouldBe null
    }

    "toUnsignedShortOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toUnsignedShortOrNull(" ")
        result shouldBe null
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

    "toUnsignedInt() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt("")
        }
    }

    "toUnsignedInt() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt(" ")
        }
    }

    "toUnsignedIntOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedIntOrNull("0")
        result shouldBe 0u
    }

    "toUnsignedIntOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedIntOrNull("1")
        result shouldBe 1u
    }

    "toUnsignedIntOrNull() should be able to parse '2147483647'" {
        val result = ValueTransformers.toUnsignedIntOrNull("2147483647")
        result shouldBe 2147483647u
    }

    "toUnsignedIntOrNull() should be able to parse '4294967295'" {
        val result = ValueTransformers.toUnsignedIntOrNull("4294967295")
        result shouldBe 4294967295u
    }

    "toUnsignedIntOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toUnsignedIntOrNull("-1")
        result shouldBe null
    }

    "toUnsignedIntOrNull() should not be able to parse '4294967296'" {
        val result = ValueTransformers.toUnsignedIntOrNull("4294967296")
        result shouldBe null
    }

    "toUnsignedIntOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toUnsignedIntOrNull("abc")
        result shouldBe null
    }

    "toUnsignedIntOrNull() should not be able to parse null" {
        val result = ValueTransformers.toUnsignedIntOrNull(null)
        result shouldBe null
    }

    "toUnsignedIntOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toUnsignedIntOrNull("")
        result shouldBe null
    }

    "toUnsignedIntOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toUnsignedIntOrNull(" ")
        result shouldBe null
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
        val result = ValueTransformers.toUnsignedLong("18446744073709551615")
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

    "toUnsignedLongOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedLongOrNull("0")
        result shouldBe 0uL
    }

    "toUnsignedLongOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedLongOrNull("1")
        result shouldBe 1uL
    }

    "toUnsignedLongOrNull() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toUnsignedLongOrNull("9223372036854775807")
        result shouldBe 9223372036854775807uL
    }

    "toUnsignedLongOrNull() should be able to parse '18446744073709551615'" {
        val result = ValueTransformers.toUnsignedLongOrNull("18446744073709551615")
        result shouldBe ULong.MAX_VALUE
    }

    "toUnsignedLongOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toUnsignedLongOrNull("-1")
        result shouldBe null
    }

    "toUnsignedLongOrNull() should not be able to parse '18446744073709551616'" {
        val result = ValueTransformers.toUnsignedLongOrNull("18446744073709551616")
        result shouldBe null
    }

    "toUnsignedLongOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toUnsignedLongOrNull("abc")
        result shouldBe null
    }

    "toUnsignedLongOrNull() should not be able to parse null" {
        val result = ValueTransformers.toUnsignedLongOrNull(null)
        result shouldBe null
    }

    "toUnsignedLongOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toUnsignedLongOrNull("")
        result shouldBe null
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

    "toPositiveByte() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte("")
        }
    }

    "toPositiveByte() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte(" ")
        }
    }

    "toPositiveByteOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveByteOrNull("0")
        result shouldBe 0
    }

    "toPositiveByteOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveByteOrNull("1")
        result shouldBe 1
    }

    "toPositiveByteOrNull() should be able to parse '127'" {
        val result = ValueTransformers.toPositiveByteOrNull("127")
        result shouldBe 127
    }

    "toPositiveByteOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toPositiveByteOrNull("-1")
        result shouldBe null
    }

    "toPositiveByteOrNull() should not be able to parse '128'" {
        val result = ValueTransformers.toPositiveByteOrNull("128")
        result shouldBe null
    }

    "toPositiveByteOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toPositiveByteOrNull("abc")
        result shouldBe null
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

    "toPositiveShort() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort("")
        }
    }

    "toPositiveShort() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort(" ")
        }
    }

    "toPositiveShortOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveShortOrNull("0")
        result shouldBe 0
    }

    "toPositiveShortOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveShortOrNull("1")
        result shouldBe 1
    }

    "toPositiveShortOrNull() should be able to parse '32767'" {
        val result = ValueTransformers.toPositiveShortOrNull("32767")
        result shouldBe 32767
    }

    "toPositiveShortOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toPositiveShortOrNull("-1")
        result shouldBe null
    }

    "toPositiveShortOrNull() should not be able to parse '32768'" {
        val result = ValueTransformers.toPositiveShortOrNull("32768")
        result shouldBe null
    }

    "toPositiveShortOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toPositiveShortOrNull("abc")
        result shouldBe null
    }

    "toPositiveShortOrNull() should not be able to parse null" {
        val result = ValueTransformers.toPositiveShortOrNull(null)
        result shouldBe null
    }

    "toPositiveShortOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toPositiveShortOrNull("")
        result shouldBe null
    }

    "toPositiveShortOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toPositiveShortOrNull(" ")
        result shouldBe null
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

    "toPositiveInt() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt("")
        }
    }

    "toPositiveInt() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt(" ")
        }
    }

    "toPositiveIntOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveIntOrNull("0")
        result shouldBe 0
    }

    "toPositiveIntOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveIntOrNull("1")
        result shouldBe 1
    }

    "toPositiveIntOrNull() should be able to parse '2147483647'" {
        val result = ValueTransformers.toPositiveIntOrNull("2147483647")
        result shouldBe 2147483647
    }

    "toPositiveIntOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toPositiveIntOrNull("-1")
        result shouldBe null
    }

    "toPositiveIntOrNull() should not be able to parse '2147483648'" {
        val result = ValueTransformers.toPositiveIntOrNull("2147483648")
        result shouldBe null
    }

    "toPositiveIntOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toPositiveIntOrNull("abc")
        result shouldBe null
    }

    "toPositiveIntOrNull() should not be able to parse null" {
        val result = ValueTransformers.toPositiveIntOrNull(null)
        result shouldBe null
    }

    "toPositiveIntOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toPositiveIntOrNull("")
        result shouldBe null
    }

    "toPositiveIntOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toPositiveIntOrNull(" ")
        result shouldBe null
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

    "toPositiveLongOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveLongOrNull("0")
        result shouldBe 0
    }

    "toPositiveLongOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveLongOrNull("1")
        result shouldBe 1
    }

    "toPositiveLongOrNull() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toPositiveLongOrNull("9223372036854775807")
        result shouldBe 9223372036854775807
    }

    "toPositiveLongOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toPositiveLongOrNull("-1")
        result shouldBe null
    }

    "toPositiveLongOrNull() should not be able to parse '9223372036854775808'" {
        val result = ValueTransformers.toPositiveLongOrNull("9223372036854775808")
        result shouldBe null
    }

    "toPositiveLongOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toPositiveLongOrNull("abc")
        result shouldBe null
    }

    "toPositiveLongOrNull() should not be able to parse null" {
        val result = ValueTransformers.toPositiveLongOrNull(null)
        result shouldBe null
    }

    "toPositiveLongOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toPositiveLongOrNull("")
        result shouldBe null
    }

    "toPositiveLongOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toPositiveLongOrNull(" ")
        result shouldBe null
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

    "toPositiveFloatOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveFloatOrNull("0")
        result shouldBe 0f
    }

    "toPositiveFloatOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveFloatOrNull("1")
        result shouldBe 1f
    }

    "toPositiveFloatOrNull() should be able to parse '3.4028235E38'" {
        val result = ValueTransformers.toPositiveFloatOrNull("3.4028235E38")
        result shouldBe 3.4028235E38f
    }

    "toPositiveFloatOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toPositiveFloatOrNull("-1")
        result shouldBe null
    }

    "toPositiveFloatOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toPositiveFloatOrNull("abc")
        result shouldBe null
    }

    "toPositiveFloatOrNull() should not be able to parse null" {
        val result = ValueTransformers.toPositiveFloatOrNull(null)
        result shouldBe null
    }

    "toPositiveFloatOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toPositiveFloatOrNull("")
        result shouldBe null
    }

    "toPositiveFloatOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toPositiveFloatOrNull(" ")
        result shouldBe null
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

    "toPositiveDoubleOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveDoubleOrNull("0")
        result shouldBe 0.0
    }

    "toPositiveDoubleOrNull() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveDoubleOrNull("1")
        result shouldBe 1.0
    }

    "toPositiveDoubleOrNull() should be able to parse '1.7976931348623157E308'" {
        val result = ValueTransformers.toPositiveDoubleOrNull("1.7976931348623157E308")
        result shouldBe 1.7976931348623157E308
    }

    "toPositiveDoubleOrNull() should not be able to parse '-1'" {
        val result = ValueTransformers.toPositiveDoubleOrNull("-1")
        result shouldBe null
    }

    "toPositiveDoubleOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toPositiveDoubleOrNull("abc")
        result shouldBe null
    }

    "toPositiveDoubleOrNull() should not be able to parse null" {
        val result = ValueTransformers.toPositiveDoubleOrNull(null)
        result shouldBe null
    }

    "toPositiveDoubleOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toPositiveDoubleOrNull("")
        result shouldBe null
    }

    "toPositiveDoubleOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toPositiveDoubleOrNull(" ")
        result shouldBe null
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

    "toNegativeByte() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte("")
        }
    }

    "toNegativeByte() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte(" ")
        }
    }

    "toNegativeByteOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeByteOrNull("0")
        result shouldBe 0
    }

    "toNegativeByteOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeByteOrNull("-1")
        result shouldBe -1
    }

    "toNegativeByteOrNull() should be able to parse '-128'" {
        val result = ValueTransformers.toNegativeByteOrNull("-128")
        result shouldBe -128
    }

    "toNegativeByteOrNull() should not be able to parse '1'" {
        val result = ValueTransformers.toNegativeByteOrNull("1")
        result shouldBe null
    }

    "toNegativeByteOrNull() should not be able to parse '127'" {
        val result = ValueTransformers.toNegativeByteOrNull("127")
        result shouldBe null
    }

    "toNegativeByteOrNull() should not be able to parse '128'" {
        val result = ValueTransformers.toNegativeByteOrNull("128")
        result shouldBe null
    }

    "toNegativeByteOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toNegativeByteOrNull("abc")
        result shouldBe null
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

    "toNegativeShort() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort("")
        }
    }

    "toNegativeShort() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort(" ")
        }
    }

    "toNegativeShortOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeShortOrNull("0")
        result shouldBe 0
    }

    "toNegativeShortOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeShortOrNull("-1")
        result shouldBe -1
    }

    "toNegativeShortOrNull() should be able to parse '-32768'" {
        val result = ValueTransformers.toNegativeShortOrNull("-32768")
        result shouldBe -32768
    }

    "toNegativeShortOrNull() should not be able to parse '1'" {
        val result = ValueTransformers.toNegativeShortOrNull("1")
        result shouldBe null
    }

    "toNegativeShortOrNull() should not be able to parse '32767'" {
        val result = ValueTransformers.toNegativeShortOrNull("32767")
        result shouldBe null
    }

    "toNegativeShortOrNull() should not be able to parse '32768'" {
        val result = ValueTransformers.toNegativeShortOrNull("32768")
        result shouldBe null
    }

    "toNegativeShortOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toNegativeShortOrNull("abc")
        result shouldBe null
    }

    "toNegativeShortOrNull() should not be able to parse null" {
        val result = ValueTransformers.toNegativeShortOrNull(null)
        result shouldBe null
    }

    "toNegativeShortOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toNegativeShortOrNull("")
        result shouldBe null
    }

    "toNegativeShortOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toNegativeShortOrNull(" ")
        result shouldBe null
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

    "toNegativeInt() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt("")
        }
    }

    "toNegativeInt() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt(" ")
        }
    }

    "toNegativeIntOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeIntOrNull("0")
        result shouldBe 0
    }

    "toNegativeIntOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeIntOrNull("-1")
        result shouldBe -1
    }

    "toNegativeIntOrNull() should be able to parse '-2147483648'" {
        val result = ValueTransformers.toNegativeIntOrNull("-2147483648")
        result shouldBe -2147483648
    }

    "toNegativeIntOrNull() should not be able to parse '1'" {
        val result = ValueTransformers.toNegativeIntOrNull("1")
        result shouldBe null
    }

    "toNegativeIntOrNull() should not be able to parse '2147483647'" {
        val result = ValueTransformers.toNegativeIntOrNull("2147483647")
        result shouldBe null
    }

    "toNegativeIntOrNull() should not be able to parse '2147483648'" {
        val result = ValueTransformers.toNegativeIntOrNull("2147483648")
        result shouldBe null
    }

    "toNegativeIntOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toNegativeIntOrNull("abc")
        result shouldBe null
    }

    "toNegativeIntOrNull() should not be able to parse null" {
        val result = ValueTransformers.toNegativeIntOrNull(null)
        result shouldBe null
    }

    "toNegativeIntOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toNegativeIntOrNull("")
        result shouldBe null
    }

    "toNegativeIntOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toNegativeIntOrNull(" ")
        result shouldBe null
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

    "toNegativeLongOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeLongOrNull("0")
        result shouldBe 0
    }

    "toNegativeLongOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeLongOrNull("-1")
        result shouldBe -1
    }

    "toNegativeLongOrNull() should be able to parse '-9223372036854775808'" {
        val result = ValueTransformers.toNegativeLongOrNull("-9223372036854775808")
        result shouldBe Long.MIN_VALUE
    }

    "toNegativeLongOrNull() should not be able to parse '1'" {
        val result = ValueTransformers.toNegativeLongOrNull("1")
        result shouldBe null
    }

    "toNegativeLongOrNull() should not be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toNegativeLongOrNull("9223372036854775807")
        result shouldBe null
    }

    "toNegativeLongOrNull() should not be able to parse '9223372036854775808'" {
        val result = ValueTransformers.toNegativeLongOrNull("9223372036854775808")
        result shouldBe null
    }

    "toNegativeLongOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toNegativeLongOrNull("abc")
        result shouldBe null
    }

    "toNegativeLongOrNull() should not be able to parse null" {
        val result = ValueTransformers.toNegativeLongOrNull(null)
        result shouldBe null
    }

    "toNegativeLongOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toNegativeLongOrNull("")
        result shouldBe null
    }

    "toNegativeLongOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toNegativeLongOrNull(" ")
        result shouldBe null
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

    "toNegativeFloatOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeFloatOrNull("0")
        result shouldBe 0f
    }

    "toNegativeFloatOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeFloatOrNull("-1")
        result shouldBe -1f
    }

    "toNegativeFloatOrNull() should be able to parse '-3.4028235E38'" {
        val result = ValueTransformers.toNegativeFloatOrNull("-3.4028235E38")
        result shouldBe -3.4028235E38f
    }

    "toNegativeFloatOrNull() should not be able to parse '1'" {
        val result = ValueTransformers.toNegativeFloatOrNull("1")
        result shouldBe null
    }

    "toNegativeFloatOrNull() should not be able to parse '3.4028236E38'" {
        val result = ValueTransformers.toNegativeFloatOrNull("3.4028236E38")
        result shouldBe null
    }

    "toNegativeFloatOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toNegativeFloatOrNull("abc")
        result shouldBe null
    }

    "toNegativeFloatOrNull() should not be able to parse null" {
        val result = ValueTransformers.toNegativeFloatOrNull(null)
        result shouldBe null
    }

    "toNegativeFloatOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toNegativeFloatOrNull("")
        result shouldBe null
    }

    "toNegativeFloatOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toNegativeFloatOrNull(" ")
        result shouldBe null
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

    "toNegativeDoubleOrNull() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeDoubleOrNull("0")
        result shouldBe 0.0
    }

    "toNegativeDoubleOrNull() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeDoubleOrNull("-1")
        result shouldBe -1.0
    }

    "toNegativeDoubleOrNull() should be able to parse '-1.7976931348623157E308'" {
        val result = ValueTransformers.toNegativeDoubleOrNull("-1.7976931348623157E308")
        result shouldBe -1.7976931348623157E308
    }

    "toNegativeDoubleOrNull() should not be able to parse '1'" {
        val result = ValueTransformers.toNegativeDoubleOrNull("1")
        result shouldBe null
    }

    "toNegativeDoubleOrNull() should not be able to parse '1.7976931348623159E308'" {
        val result = ValueTransformers.toNegativeDoubleOrNull("1.7976931348623159E308")
        result shouldBe null
    }

    "toNegativeDoubleOrNull() should not be able to parse 'abc'" {
        val result = ValueTransformers.toNegativeDoubleOrNull("abc")
        result shouldBe null
    }

    "toNegativeDoubleOrNull() should not be able to parse null" {
        val result = ValueTransformers.toNegativeDoubleOrNull(null)
        result shouldBe null
    }

    "toNegativeDoubleOrNull() should not be able to parse ''" {
        val result = ValueTransformers.toNegativeDoubleOrNull("")
        result shouldBe null
    }

    "toNegativeDoubleOrNull() should not be able to parse ' '" {
        val result = ValueTransformers.toNegativeDoubleOrNull(" ")
        result shouldBe null
    }

    "toString() should be able to parse 'abc'" {
        val result = ValueTransformers.toString("abc")
        result shouldBe "abc"
    }

    "toString() should be able to parse '123'" {
        val result = ValueTransformers.toString("123")
        result shouldBe "123"
    }

    "toString() should be able to parse 'true'" {
        val result = ValueTransformers.toString("true")
        result shouldBe "true"
    }

    "toString() should be able to parse 'false'" {
        val result = ValueTransformers.toString("false")
        result shouldBe "false"
    }

    "toString() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toString(null)
        }
    }

    "toString() should be able to parse ''" {
        ValueTransformers.toString("") shouldBe ""
    }

    "toString() should be able to parse ' '" {
        ValueTransformers.toString(" ") shouldBe " "
    }

    "toStringOrNull() should be able to parse 'abc'" {
        val result = ValueTransformers.toStringOrNull("abc")
        result shouldBe "abc"
    }

    "toStringOrNull() should be able to parse '123'" {
        val result = ValueTransformers.toStringOrNull("123")
        result shouldBe "123"
    }

    "toStringOrNull() should be able to parse 'true'" {
        val result = ValueTransformers.toStringOrNull("true")
        result shouldBe "true"
    }

    "toStringOrNull() should be able to parse 'false'" {
        val result = ValueTransformers.toStringOrNull("false")
        result shouldBe "false"
    }

    "toStringOrNull() should not be able to parse null" {
        val result = ValueTransformers.toStringOrNull(null)
        result shouldBe null
    }

    "toStringOrNull() should be able to parse ''" {
        ValueTransformers.toStringOrNull("") shouldBe ""
    }

    "toStringOrNull() should be able to parse ' '" {
        ValueTransformers.toStringOrNull(" ") shouldBe " "
    }
})