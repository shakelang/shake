package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ValueTransformerLambdaTests : FreeSpec({

    "toByte() should be able to parse '0'" {
        val result = ValueTransformers.toByte.invoke("0")
        result shouldBe 0
    }

    "toByte() should be able to parse '1'" {
        val result = ValueTransformers.toByte.invoke("1")
        result shouldBe 1
    }

    "toByte() should be able to parse '-1'" {
        val result = ValueTransformers.toByte.invoke("-1")
        result shouldBe -1
    }

    "toByte() should be able to parse '127'" {
        val result = ValueTransformers.toByte.invoke("127")
        result shouldBe 127
    }

    "toByte() should be able to parse '-128'" {
        val result = ValueTransformers.toByte.invoke("-128")
        result shouldBe -128
    }

    "toByte() should fail to parse '128'" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte.invoke("128")
        }
    }

    "toByte() should fail to parse '-129'" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte.invoke("-129")
        }
    }

    "toByte() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte.invoke("abc")
        }
    }

    "toByte() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toByte.invoke(null)
        }
    }

    "toShort() should be able to parse '0'" {
        val result = ValueTransformers.toShort.invoke("0")
        result shouldBe 0
    }

    "toShort() should be able to parse '1'" {
        val result = ValueTransformers.toShort.invoke("1")
        result shouldBe 1
    }

    "toShort() should be able to parse '-1'" {
        val result = ValueTransformers.toShort.invoke("-1")
        result shouldBe -1
    }

    "toShort() should be able to parse '32767'" {
        val result = ValueTransformers.toShort.invoke("32767")
        result shouldBe 32767
    }

    "toShort() should be able to parse '-32768'" {
        val result = ValueTransformers.toShort.invoke("-32768")
        result shouldBe -32768
    }

    "toShort() should fail to parse '32768'" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort.invoke("32768")
        }
    }

    "toShort() should fail to parse '-32769'" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort.invoke("-32769")
        }
    }

    "toShort() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort.invoke("abc")
        }
    }

    "toShort() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toShort.invoke(null)
        }
    }

    "toInt() should be able to parse '0'" {
        val result = ValueTransformers.toInt.invoke("0")
        result shouldBe 0
    }

    "toInt() should be able to parse '1'" {
        val result = ValueTransformers.toInt.invoke("1")
        result shouldBe 1
    }

    "toInt() should be able to parse '-1'" {
        val result = ValueTransformers.toInt.invoke("-1")
        result shouldBe -1
    }

    "toInt() should be able to parse '2147483647'" {
        val result = ValueTransformers.toInt.invoke("2147483647")
        result shouldBe 2147483647
    }

    "toInt() should be able to parse '-2147483648'" {
        val result = ValueTransformers.toInt.invoke("-2147483648")
        result shouldBe -2147483648
    }

    "toInt() should fail to parse '2147483648'" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt.invoke("2147483648")
        }
    }

    "toInt() should fail to parse '-2147483649'" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt.invoke("-2147483649")
        }
    }

    "toInt() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt.invoke("abc")
        }
    }

    "toInt() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toInt.invoke(null)
        }
    }

    "toLong() should be able to parse '0'" {
        val result = ValueTransformers.toLong.invoke("0")
        result shouldBe 0
    }

    "toLong() should be able to parse '1'" {
        val result = ValueTransformers.toLong.invoke("1")
        result shouldBe 1
    }

    "toLong() should be able to parse '-1'" {
        val result = ValueTransformers.toLong.invoke("-1")
        result shouldBe -1
    }

    "toLong() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toLong.invoke("9223372036854775807")
        result shouldBe 9223372036854775807
    }

    "toLong() should be able to parse '-9223372036854775808'" {
        val result = ValueTransformers.toLong.invoke("-9223372036854775808")
        result shouldBe Long.MIN_VALUE
    }

    "toLong() should fail to parse '9223372036854775808'" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong.invoke("9223372036854775808")
        }
    }

    "toLong() should fail to parse '-9223372036854775809'" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong.invoke("-9223372036854775809")
        }
    }

    "toLong() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong.invoke("abc")
        }
    }

    "toLong() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toLong.invoke(null)
        }
    }

    "toFloat() should be able to parse '0'" {
        val result = ValueTransformers.toFloat.invoke("0")
        result shouldBe 0f
    }

    "toFloat() should be able to parse '1'" {
        val result = ValueTransformers.toFloat.invoke("1")
        result shouldBe 1f
    }

    "toFloat() should be able to parse '-1'" {
        val result = ValueTransformers.toFloat.invoke("-1")
        result shouldBe -1f
    }

    "toFloat() should be able to parse '3.4028235E38'" {
        val result = ValueTransformers.toFloat.invoke("3.4028235E38")
        result shouldBe 3.4028235E38f
    }

    "toFloat() should be able to parse '-3.4028235E38'" {
        val result = ValueTransformers.toFloat.invoke("-3.4028235E38")
        result shouldBe -3.4028235E38f
    }

    "toFloat() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toFloat.invoke("abc")
        }
    }

    "toFloat() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toFloat.invoke(null)
        }
    }

    "toDouble() should be able to parse '0'" {
        val result = ValueTransformers.toDouble.invoke("0")
        result shouldBe 0.0
    }

    "toDouble() should be able to parse '1'" {
        val result = ValueTransformers.toDouble.invoke("1")
        result shouldBe 1.0
    }

    "toDouble() should be able to parse '-1'" {
        val result = ValueTransformers.toDouble.invoke("-1")
        result shouldBe -1.0
    }

    "toDouble() should be able to parse '1.7976931348623157E308'" {
        val result = ValueTransformers.toDouble.invoke("1.7976931348623157E308")
        result shouldBe 1.7976931348623157E308
    }

    "toDouble() should be able to parse '-1.7976931348623157E308'" {
        val result = ValueTransformers.toDouble.invoke("-1.7976931348623157E308")
        result shouldBe -1.7976931348623157E308
    }

    "toDouble() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toDouble.invoke("abc")
        }
    }

    "toDouble() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toDouble.invoke(null)
        }
    }

    "toBoolean() should be able to parse 'true'" {
        val result = ValueTransformers.toBoolean.invoke("true")
        result shouldBe true
    }

    "toBoolean() should be able to parse 'false'" {
        val result = ValueTransformers.toBoolean.invoke("false")
        result shouldBe false
    }

    "toBoolean() should be able to parse '1'" {
        val result = ValueTransformers.toBoolean.invoke("1")
        result shouldBe true
    }

    "toBoolean() should be able to parse '0'" {
        val result = ValueTransformers.toBoolean.invoke("0")
        result shouldBe false
    }

    "toBoolean() should fail to parse '2'" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean.invoke("2")
        }
    }

    "toBoolean() should fail to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean.invoke("-1")
        }
    }

    "toBoolean() should be able to parse 'yes'" {
        val result = ValueTransformers.toBoolean.invoke("yes")
        result shouldBe true
    }

    "toBoolean() should be able to parse 'no'" {
        val result = ValueTransformers.toBoolean.invoke("no")
        result shouldBe false
    }

    "toBoolean() should be able to parse 'y'" {
        val result = ValueTransformers.toBoolean.invoke("y")
        result shouldBe true
    }

    "toBoolean() should be able to parse 'n'" {
        val result = ValueTransformers.toBoolean.invoke("n")
        result shouldBe false
    }

    "toBoolean() should fail to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean.invoke("abc")
        }
    }

    "toBoolean() should fail to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toBoolean.invoke(null)
        }
    }

    "toChar() should be able to parse 'a'" {
        val result = ValueTransformers.toChar.invoke("a")
        result shouldBe 'a'
    }

    "toChar() should be able to parse 'A'" {
        val result = ValueTransformers.toChar.invoke("A")
        result shouldBe 'A'
    }

    "toChar() should be able to parse '1'" {
        val result = ValueTransformers.toChar.invoke("1")
        result shouldBe '1'
    }

    "toChar() should be able to parse ' '" {
        val result = ValueTransformers.toChar.invoke(" ")
        result shouldBe ' '
    }

    "toChar() should not be able to parse 'ab'" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar.invoke("ab")
        }
    }

    "toChar() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar.invoke(null)
        }
    }

    "toChar() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar.invoke("")
        }
    }

    "toChar() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toChar.invoke("abc")
        }
    }

    "toUnsignedByte() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedByte.invoke("0")
        result shouldBe 0u.toUByte()
    }

    "toUnsignedByte() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedByte.invoke("1")
        result shouldBe 1u.toUByte()
    }

    "toUnsignedByte() should be able to parse '127'" {
        val result = ValueTransformers.toUnsignedByte.invoke("127")
        result shouldBe 127u.toUByte()
    }

    "toUnsignedByte() should be able to parse '128'" {
        val result = ValueTransformers.toUnsignedByte.invoke("128")
        result shouldBe 128u.toUByte()
    }

    "toUnsignedByte() should be able to parse '255'" {
        val result = ValueTransformers.toUnsignedByte.invoke("255")
        result shouldBe 255u.toUByte()
    }

    "toUnsignedByte() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte.invoke("-1")
        }
    }

    "toUnsignedByte() should not be able to parse '256'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte.invoke("256")
        }
    }

    "toUnsignedByte() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte.invoke("abc")
        }
    }

    "toUnsignedByte() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedByte.invoke(null)
        }
    }

    "toUnsignedShort() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedShort.invoke("0")
        result shouldBe 0u.toUShort()
    }

    "toUnsignedShort() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedShort.invoke("1")
        result shouldBe 1u.toUShort()
    }

    "toUnsignedShort() should be able to parse '32767'" {
        val result = ValueTransformers.toUnsignedShort.invoke("65535")
        result shouldBe 65535u.toUShort()
    }

    "toUnsignedShort() should be able to parse '32768'" {
        val result = ValueTransformers.toUnsignedShort.invoke("32768")
        result shouldBe 32768u.toUShort()
    }

    "toUnsignedShort() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort.invoke("-1")
        }
    }

    "toUnsignedShort() should not be able to parse '65536'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort.invoke("65536")
        }
    }

    "toUnsignedShort() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort.invoke("abc")
        }
    }

    "toUnsignedShort() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedShort.invoke(null)
        }
    }

    "toUnsignedInt() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedInt.invoke("0")
        result shouldBe 0u
    }

    "toUnsignedInt() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedInt.invoke("1")
        result shouldBe 1u
    }

    "toUnsignedInt() should be able to parse '2147483647'" {
        val result = ValueTransformers.toUnsignedInt.invoke("2147483647")
        result shouldBe 2147483647u
    }

    "toUnsignedInt() should be able to parse '4294967295'" {
        val result = ValueTransformers.toUnsignedInt.invoke("4294967295")
        result shouldBe 4294967295u
    }

    "toUnsignedInt() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt.invoke("-1")
        }
    }

    "toUnsignedInt() should not be able to parse '4294967296'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt.invoke("4294967296")
        }
    }

    "toUnsignedInt() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt.invoke("abc")
        }
    }

    "toUnsignedInt() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedInt.invoke(null)
        }
    }

    "toUnsignedLong() should be able to parse '0'" {
        val result = ValueTransformers.toUnsignedLong.invoke("0")
        result shouldBe 0uL
    }

    "toUnsignedLong() should be able to parse '1'" {
        val result = ValueTransformers.toUnsignedLong.invoke("1")
        result shouldBe 1uL
    }

    "toUnsignedLong() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toUnsignedLong.invoke("9223372036854775807")
        result shouldBe 9223372036854775807uL
    }

    "toUnsignedLong() should be able to parse '18446744073709551615'" {
        val result = ValueTransformers.toUnsignedLong.invoke( "18446744073709551615")
        result shouldBe ULong.MAX_VALUE
    }

    "toUnsignedLong() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong.invoke("-1")
        }
    }

    "toUnsignedLong() should not be able to parse '18446744073709551616'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong.invoke("18446744073709551616")
        }
    }

    "toUnsignedLong() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong.invoke("abc")
        }
    }

    "toUnsignedLong() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong.invoke(null)
        }
    }

    "toUnsignedLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong.invoke("")
        }
    }

    "toUnsignedLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toUnsignedLong.invoke(" ")
        }
    }

    "toPositiveByte() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveByte.invoke("0")
        result shouldBe 0
    }

    "toPositiveByte() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveByte.invoke("1")
        result shouldBe 1
    }

    "toPositiveByte() should be able to parse '127'" {
        val result = ValueTransformers.toPositiveByte.invoke("127")
        result shouldBe 127
    }

    "toPositiveByte() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte.invoke("-1")
        }
    }

    "toPositiveByte() should not be able to parse '128'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte.invoke("128")
        }
    }

    "toPositiveByte() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte.invoke("abc")
        }
    }

    "toPositiveByte() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveByte.invoke(null)
        }
    }

    "toPositiveShort() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveShort.invoke("0")
        result shouldBe 0
    }

    "toPositiveShort() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveShort.invoke("1")
        result shouldBe 1
    }

    "toPositiveShort() should be able to parse '32767'" {
        val result = ValueTransformers.toPositiveShort.invoke("32767")
        result shouldBe 32767
    }

    "toPositiveShort() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort.invoke("-1")
        }
    }

"toPositiveShort() should not be able to parse '32768'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort.invoke("32768")
        }
    }

    "toPositiveShort() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort.invoke("abc")
        }
    }

    "toPositiveShort() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveShort.invoke(null)
        }
    }

    "toPositiveInt() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveInt.invoke("0")
        result shouldBe 0
    }

    "toPositiveInt() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveInt.invoke("1")
        result shouldBe 1
    }

    "toPositiveInt() should be able to parse '2147483647'" {
        val result = ValueTransformers.toPositiveInt.invoke("2147483647")
        result shouldBe 2147483647
    }

    "toPositiveInt() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt.invoke("-1")
        }
    }

    "toPositiveInt() should not be able to parse '2147483648'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt.invoke("2147483648")
        }
    }

    "toPositiveInt() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt.invoke("abc")
        }
    }

    "toPositiveInt() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveInt.invoke(null)
        }
    }

    "toPositiveLong() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveLong.invoke("0")
        result shouldBe 0
    }

    "toPositiveLong() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveLong.invoke("1")
        result shouldBe 1
    }

    "toPositiveLong() should be able to parse '9223372036854775807'" {
        val result = ValueTransformers.toPositiveLong.invoke("9223372036854775807")
        result shouldBe 9223372036854775807
    }

    "toPositiveLong() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong.invoke("-1")
        }
    }

    "toPositiveLong() should not be able to parse '9223372036854775808'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong.invoke("9223372036854775808")
        }
    }

    "toPositiveLong() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong.invoke("abc")
        }
    }

    "toPositiveLong() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong.invoke(null)
        }
    }

    "toPositiveLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong.invoke("")
        }
    }

    "toPositiveLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveLong.invoke(" ")
        }
    }

    "toPositiveFloat() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveFloat.invoke("0")
        result shouldBe 0f
    }

    "toPositiveFloat() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveFloat.invoke("1")
        result shouldBe 1f
    }

    "toPositiveFloat() should be able to parse '3.4028235E38'" {
        val result = ValueTransformers.toPositiveFloat.invoke("3.4028235E38")
        result shouldBe 3.4028235E38f
    }

    "toPositiveFloat() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat.invoke("-1")
        }
    }

    "toPositiveFloat() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat.invoke("abc")
        }
    }

    "toPositiveFloat() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat.invoke(null)
        }
    }

    "toPositiveFloat() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat.invoke("")
        }
    }

    "toPositiveFloat() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveFloat.invoke(" ")
        }
    }

    "toPositiveDouble() should be able to parse '0'" {
        val result = ValueTransformers.toPositiveDouble.invoke("0")
        result shouldBe 0.0
    }

    "toPositiveDouble() should be able to parse '1'" {
        val result = ValueTransformers.toPositiveDouble.invoke("1")
        result shouldBe 1.0
    }

    "toPositiveDouble() should be able to parse '1.7976931348623157E308'" {
        val result = ValueTransformers.toPositiveDouble.invoke("1.7976931348623157E308")
        result shouldBe 1.7976931348623157E308
    }

    "toPositiveDouble() should not be able to parse '-1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble.invoke("-1")
        }
    }

    "toPositiveDouble() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble.invoke("abc")
        }
    }

    "toPositiveDouble() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble.invoke(null)
        }
    }

    "toPositiveDouble() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble.invoke("")
        }
    }

    "toPositiveDouble() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toPositiveDouble.invoke(" ")
        }
    }

    "toNegativeByte() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeByte.invoke("0")
        result shouldBe 0
    }

    "toNegativeByte() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeByte.invoke("-1")
        result shouldBe -1
    }

    "toNegativeByte() should be able to parse '-128'" {
        val result = ValueTransformers.toNegativeByte.invoke("-128")
        result shouldBe -128
    }

    "toNegativeByte() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte.invoke("1")
        }
    }

    "toNegativeByte() should not be able to parse '127'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte.invoke("127")
        }
    }

    "toNegativeByte() should not be able to parse '128'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte.invoke("128")
        }
    }

    "toNegativeByte() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte.invoke("abc")
        }
    }

    "toNegativeByte() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeByte.invoke(null)
        }
    }

    "toNegativeShort() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeShort.invoke("0")
        result shouldBe 0
    }

    "toNegativeShort() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeShort.invoke("-1")
        result shouldBe -1
    }

    "toNegativeShort() should be able to parse '-32768'" {
        val result = ValueTransformers.toNegativeShort.invoke("-32768")
        result shouldBe -32768
    }

    "toNegativeShort() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort.invoke("1")
        }
    }

    "toNegativeShort() should not be able to parse '32767'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort.invoke("32767")
        }
    }

"toNegativeShort() should not be able to parse '32768'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort.invoke("32768")
        }
    }

    "toNegativeShort() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort.invoke("abc")
        }
    }

    "toNegativeShort() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeShort.invoke(null)
        }
    }

    "toNegativeInt() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeInt.invoke("0")
        result shouldBe 0
    }

    "toNegativeInt() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeInt.invoke("-1")
        result shouldBe -1
    }

    "toNegativeInt() should be able to parse '-2147483648'" {
        val result = ValueTransformers.toNegativeInt.invoke("-2147483648")
        result shouldBe -2147483648
    }

    "toNegativeInt() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt.invoke("1")
        }
    }

    "toNegativeInt() should not be able to parse '2147483647'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt.invoke("2147483647")
        }
    }

    "toNegativeInt() should not be able to parse '2147483648'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt.invoke("2147483648")
        }
    }

    "toNegativeInt() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt.invoke("abc")
        }
    }

    "toNegativeInt() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeInt.invoke(null)
        }
    }

    "toNegativeLong() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeLong.invoke("0")
        result shouldBe 0
    }

    "toNegativeLong() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeLong.invoke("-1")
        result shouldBe -1
    }

    "toNegativeLong() should be able to parse '-9223372036854775808'" {
        val result = ValueTransformers.toNegativeLong.invoke("-9223372036854775808")
        result shouldBe Long.MIN_VALUE
    }

    "toNegativeLong() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke("1")
        }
    }

    "toNegativeLong() should not be able to parse '9223372036854775807'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke("9223372036854775807")
        }
    }

    "toNegativeLong() should not be able to parse '9223372036854775808'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke("9223372036854775808")
        }
    }

    "toNegativeLong() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke("abc")
        }
    }

    "toNegativeLong() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke(null)
        }
    }

    "toNegativeLong() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke("")
        }
    }

    "toNegativeLong() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeLong.invoke(" ")
        }
    }

    "toNegativeFloat() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeFloat.invoke("0")
        result shouldBe 0f
    }

    "toNegativeFloat() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeFloat.invoke("-1")
        result shouldBe -1f
    }

    "toNegativeFloat() should be able to parse '-3.4028235E38'" {
        val result = ValueTransformers.toNegativeFloat.invoke("-3.4028235E38")
        result shouldBe -3.4028235E38f
    }

    "toNegativeFloat() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat.invoke("1")
        }
    }

    "toNegativeFloat() should not be able to parse '3.4028236E38'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat.invoke("3.4028236E38")
        }
    }

    "toNegativeFloat() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat.invoke("abc")
        }
    }

    "toNegativeFloat() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat.invoke(null)
        }
    }

    "toNegativeFloat() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat.invoke("")
        }
    }

    "toNegativeFloat() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeFloat.invoke(" ")
        }
    }

    "toNegativeDouble() should be able to parse '0'" {
        val result = ValueTransformers.toNegativeDouble.invoke("0")
        result shouldBe 0.0
    }

    "toNegativeDouble() should be able to parse '-1'" {
        val result = ValueTransformers.toNegativeDouble.invoke("-1")
        result shouldBe -1.0
    }

    "toNegativeDouble() should be able to parse '-1.7976931348623157E308'" {
        val result = ValueTransformers.toNegativeDouble.invoke("-1.7976931348623157E308")
        result shouldBe -1.7976931348623157E308
    }

    "toNegativeDouble() should not be able to parse '1'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble.invoke("1")
        }
    }

    "toNegativeDouble() should not be able to parse '1.7976931348623159E308'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble.invoke("1.7976931348623159E308")
        }
    }

    "toNegativeDouble() should not be able to parse 'abc'" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble.invoke("abc")
        }
    }

    "toNegativeDouble() should not be able to parse null" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble.invoke(null)
        }
    }

    "toNegativeDouble() should not be able to parse ''" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble.invoke("")
        }
    }

    "toNegativeDouble() should not be able to parse ' '" {
        shouldThrow<ValueException> {
            ValueTransformers.toNegativeDouble.invoke(" ")
        }
    }

})