package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommanderValueTransformerLambdaTests : FreeSpec(
    {

        "toByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toByte.invoke("0")
            result shouldBe 0
        }

        "toByte() should be able to parse '1'" {
            val result = CommanderValueTransformers.toByte.invoke("1")
            result shouldBe 1
        }

        "toByte() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toByte.invoke("-1")
            result shouldBe -1
        }

        "toByte() should be able to parse '127'" {
            val result = CommanderValueTransformers.toByte.invoke("127")
            result shouldBe 127
        }

        "toByte() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toByte.invoke("-128")
            result shouldBe -128
        }

        "toByte() should fail to parse '128'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte.invoke("128")
            }
        }

        "toByte() should fail to parse '-129'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte.invoke("-129")
            }
        }

        "toByte() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte.invoke("abc")
            }
        }

        "toByte() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte.invoke(null)
            }
        }

        "toByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte.invoke("")
            }
        }

        "toByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toByte.invoke(" ")
            }
        }

        "toByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("0")
            result shouldBe 0
        }

        "toByteOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("1")
            result shouldBe 1
        }

        "toByteOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("-1")
            result shouldBe -1
        }

        "toByteOrNull() should be able to parse '127'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("127")
            result shouldBe 127
        }

        "toByteOrNull() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("-128")
            result shouldBe -128
        }

        "toByteOrNull() should fail to parse '128'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("128")
            result shouldBe null
        }

        "toByteOrNull() should fail to parse '-129'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("-129")
            result shouldBe null
        }

        "toByteOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("abc")
            result shouldBe null
        }

        "toByteOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toByteOrNull.invoke(null)
            result shouldBe null
        }

        "toByteOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toByteOrNull.invoke("")
            result shouldBe null
        }

        "toByteOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toByteOrNull.invoke(" ")
            result shouldBe null
        }

        "toShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toShort.invoke("0")
            result shouldBe 0
        }

        "toShort() should be able to parse '1'" {
            val result = CommanderValueTransformers.toShort.invoke("1")
            result shouldBe 1
        }

        "toShort() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toShort.invoke("-1")
            result shouldBe -1
        }

        "toShort() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toShort.invoke("32767")
            result shouldBe 32767
        }

        "toShort() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toShort.invoke("-32768")
            result shouldBe -32768
        }

        "toShort() should fail to parse '32768'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort.invoke("32768")
            }
        }

        "toShort() should fail to parse '-32769'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort.invoke("-32769")
            }
        }

        "toShort() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort.invoke("abc")
            }
        }

        "toShort() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort.invoke(null)
            }
        }

        "toShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort.invoke("")
            }
        }

        "toShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toShort.invoke(" ")
            }
        }

        "toShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("0")
            result shouldBe 0
        }

        "toShortOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("1")
            result shouldBe 1
        }

        "toShortOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("-1")
            result shouldBe -1
        }

        "toShortOrNull() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("32767")
            result shouldBe 32767
        }

        "toShortOrNull() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("-32768")
            result shouldBe -32768
        }

        "toShortOrNull() should fail to parse '32768'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("32768")
            result shouldBe null
        }

        "toShortOrNull() should fail to parse '-32769'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("-32769")
            result shouldBe null
        }

        "toShortOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("abc")
            result shouldBe null
        }

        "toShortOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toShortOrNull.invoke(null)
            result shouldBe null
        }

        "toShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toShortOrNull.invoke("")
            result shouldBe null
        }

        "toShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toShortOrNull.invoke(" ")
            result shouldBe null
        }

        "toInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toInt.invoke("0")
            result shouldBe 0
        }

        "toInt() should be able to parse '1'" {
            val result = CommanderValueTransformers.toInt.invoke("1")
            result shouldBe 1
        }

        "toInt() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toInt.invoke("-1")
            result shouldBe -1
        }

        "toInt() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toInt.invoke("2147483647")
            result shouldBe 2147483647
        }

        "toInt() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toInt.invoke("-2147483648")
            result shouldBe -2147483648
        }

        "toInt() should fail to parse '2147483648'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt.invoke("2147483648")
            }
        }

        "toInt() should fail to parse '-2147483649'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt.invoke("-2147483649")
            }
        }

        "toInt() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt.invoke("abc")
            }
        }

        "toInt() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt.invoke(null)
            }
        }

        "toInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt.invoke("")
            }
        }

        "toInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toInt.invoke(" ")
            }
        }

        "toIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("0")
            result shouldBe 0
        }

        "toIntOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("1")
            result shouldBe 1
        }

        "toIntOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("-1")
            result shouldBe -1
        }

        "toIntOrNull() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("2147483647")
            result shouldBe 2147483647
        }

        "toIntOrNull() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("-2147483648")
            result shouldBe -2147483648
        }

        "toIntOrNull() should fail to parse '2147483648'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("2147483648")
            result shouldBe null
        }

        "toIntOrNull() should fail to parse '-2147483649'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("-2147483649")
            result shouldBe null
        }

        "toIntOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("abc")
            result shouldBe null
        }

        "toIntOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toIntOrNull.invoke(null)
            result shouldBe null
        }

        "toIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toIntOrNull.invoke("")
            result shouldBe null
        }

        "toIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toIntOrNull.invoke(" ")
            result shouldBe null
        }

        "toLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toLong.invoke("0")
            result shouldBe 0
        }

        "toLong() should be able to parse '1'" {
            val result = CommanderValueTransformers.toLong.invoke("1")
            result shouldBe 1
        }

        "toLong() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toLong.invoke("-1")
            result shouldBe -1
        }

        "toLong() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toLong.invoke("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toLong() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toLong.invoke("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toLong() should fail to parse '9223372036854775808'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong.invoke("9223372036854775808")
            }
        }

        "toLong() should fail to parse '-9223372036854775809'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong.invoke("-9223372036854775809")
            }
        }

        "toLong() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong.invoke("abc")
            }
        }

        "toLong() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong.invoke(null)
            }
        }

        "toLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong.invoke("")
            }
        }

        "toLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toLong.invoke(" ")
            }
        }

        "toLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("0")
            result shouldBe 0
        }

        "toLongOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("1")
            result shouldBe 1
        }

        "toLongOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("-1")
            result shouldBe -1
        }

        "toLongOrNull() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toLongOrNull() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toLongOrNull() should fail to parse '9223372036854775808'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("9223372036854775808")
            result shouldBe null
        }

        "toLongOrNull() should fail to parse '-9223372036854775809'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("-9223372036854775809")
            result shouldBe null
        }

        "toLongOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("abc")
            result shouldBe null
        }

        "toLongOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toLongOrNull.invoke(null)
            result shouldBe null
        }

        "toLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toLongOrNull.invoke("")
            result shouldBe null
        }

        "toLongOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toLongOrNull.invoke(" ")
            result shouldBe null
        }

        "toFloat() should be able to parse '0'" {
            val result = CommanderValueTransformers.toFloat.invoke("0")
            result shouldBe 0f
        }

        "toFloat() should be able to parse '1'" {
            val result = CommanderValueTransformers.toFloat.invoke("1")
            result shouldBe 1f
        }

        "toFloat() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toFloat.invoke("-1")
            result shouldBe -1f
        }

        "toFloat() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toFloat.invoke("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toFloat() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toFloat.invoke("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toFloat() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat.invoke("abc")
            }
        }

        "toFloat() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat.invoke(null)
            }
        }

        "toFloat() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat.invoke("")
            }
        }

        "toFloat() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toFloat.invoke(" ")
            }
        }

        "toFloatOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("0")
            result shouldBe 0f
        }

        "toFloatOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("1")
            result shouldBe 1f
        }

        "toFloatOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("-1")
            result shouldBe -1f
        }

        "toFloatOrNull() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toFloatOrNull() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toFloatOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("abc")
            result shouldBe null
        }

        "toFloatOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke(null)
            result shouldBe null
        }

        "toFloatOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke("")
            result shouldBe null
        }

        "toFloatOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toFloatOrNull.invoke(" ")
            result shouldBe null
        }

        "toDouble() should be able to parse '0'" {
            val result = CommanderValueTransformers.toDouble.invoke("0")
            result shouldBe 0.0
        }

        "toDouble() should be able to parse '1'" {
            val result = CommanderValueTransformers.toDouble.invoke("1")
            result shouldBe 1.0
        }

        "toDouble() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toDouble.invoke("-1")
            result shouldBe -1.0
        }

        "toDouble() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDouble.invoke("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toDouble() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDouble.invoke("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toDouble() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble.invoke("abc")
            }
        }

        "toDouble() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble.invoke(null)
            }
        }

        "toDouble() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble.invoke("")
            }
        }

        "toDouble() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toDouble.invoke(" ")
            }
        }

        "toDoubleOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("0")
            result shouldBe 0.0
        }

        "toDoubleOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("1")
            result shouldBe 1.0
        }

        "toDoubleOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("-1")
            result shouldBe -1.0
        }

        "toDoubleOrNull() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toDoubleOrNull() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toDoubleOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("abc")
            result shouldBe null
        }

        "toDoubleOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke(null)
            result shouldBe null
        }

        "toDoubleOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke("")
            result shouldBe null
        }

        "toDoubleOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toDoubleOrNull.invoke(" ")
            result shouldBe null
        }

        "toBoolean() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toBoolean.invoke("true")
            result shouldBe true
        }

        "toBoolean() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toBoolean.invoke("false")
            result shouldBe false
        }

        "toBoolean() should be able to parse '1'" {
            val result = CommanderValueTransformers.toBoolean.invoke("1")
            result shouldBe true
        }

        "toBoolean() should be able to parse '0'" {
            val result = CommanderValueTransformers.toBoolean.invoke("0")
            result shouldBe false
        }

        "toBoolean() should fail to parse '2'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean.invoke("2")
            }
        }

        "toBoolean() should fail to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean.invoke("-1")
            }
        }

        "toBoolean() should be able to parse 'yes'" {
            val result = CommanderValueTransformers.toBoolean.invoke("yes")
            result shouldBe true
        }

        "toBoolean() should be able to parse 'no'" {
            val result = CommanderValueTransformers.toBoolean.invoke("no")
            result shouldBe false
        }

        "toBoolean() should be able to parse 'y'" {
            val result = CommanderValueTransformers.toBoolean.invoke("y")
            result shouldBe true
        }

        "toBoolean() should be able to parse 'n'" {
            val result = CommanderValueTransformers.toBoolean.invoke("n")
            result shouldBe false
        }

        "toBoolean() should fail to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean.invoke("abc")
            }
        }

        "toBoolean() should fail to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean.invoke(null)
            }
        }

        "toBoolean() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean.invoke("")
            }
        }

        "toBoolean() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toBoolean.invoke(" ")
            }
        }

        "toBooleanOrNull() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("true")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("false")
            result shouldBe false
        }

        "toBooleanOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("1")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("0")
            result shouldBe false
        }

        "toBooleanOrNull() should fail to parse '2'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("2")
            result shouldBe null
        }

        "toBooleanOrNull() should fail to parse '-1'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("-1")
            result shouldBe null
        }

        "toBooleanOrNull() should be able to parse 'yes'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("yes")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse 'no'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("no")
            result shouldBe false
        }

        "toBooleanOrNull() should be able to parse 'y'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("y")
            result shouldBe true
        }

        "toBooleanOrNull() should be able to parse 'n'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("n")
            result shouldBe false
        }

        "toBooleanOrNull() should fail to parse 'abc'" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("abc")
            result shouldBe null
        }

        "toBooleanOrNull() should fail to parse null" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke(null)
            result shouldBe null
        }

        "toBooleanOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke("")
            result shouldBe null
        }

        "toBooleanOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toBooleanOrNull.invoke(" ")
            result shouldBe null
        }

        "toChar() should be able to parse 'a'" {
            val result = CommanderValueTransformers.toChar.invoke("a")
            result shouldBe 'a'
        }

        "toChar() should be able to parse 'A'" {
            val result = CommanderValueTransformers.toChar.invoke("A")
            result shouldBe 'A'
        }

        "toChar() should be able to parse '1'" {
            val result = CommanderValueTransformers.toChar.invoke("1")
            result shouldBe '1'
        }

        "toChar() should be able to parse ' '" {
            val result = CommanderValueTransformers.toChar.invoke(" ")
            result shouldBe ' '
        }

        "toChar() should not be able to parse 'ab'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar.invoke("ab")
            }
        }

        "toChar() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar.invoke(null)
            }
        }

        "toChar() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar.invoke("")
            }
        }

        "toChar() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toChar.invoke("abc")
            }
        }

        "toCharOrNull() should be able to parse 'a'" {
            val result = CommanderValueTransformers.toCharOrNull.invoke("a")
            result shouldBe 'a'
        }

        "toCharOrNull() should be able to parse 'A'" {
            val result = CommanderValueTransformers.toCharOrNull.invoke("A")
            result shouldBe 'A'
        }

        "toCharOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toCharOrNull.invoke("1")
            result shouldBe '1'
        }

        "toCharOrNull() should be able to parse ' '" {
            val result = CommanderValueTransformers.toCharOrNull.invoke(" ")
            result shouldBe ' '
        }

        "toCharOrNull() should not be able to parse 'ab'" {
            val result = CommanderValueTransformers.toCharOrNull.invoke("ab")
            result shouldBe null
        }

        "toCharOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toCharOrNull.invoke(null)
            result shouldBe null
        }

        "toCharOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toCharOrNull.invoke("")
            result shouldBe null
        }

        "toCharOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toCharOrNull.invoke("abc")
            result shouldBe null
        }

        "toUnsignedByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedByte.invoke("0")
            result shouldBe 0u.toUByte()
        }

        "toUnsignedByte() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedByte.invoke("1")
            result shouldBe 1u.toUByte()
        }

        "toUnsignedByte() should be able to parse '127'" {
            val result = CommanderValueTransformers.toUnsignedByte.invoke("127")
            result shouldBe 127u.toUByte()
        }

        "toUnsignedByte() should be able to parse '128'" {
            val result = CommanderValueTransformers.toUnsignedByte.invoke("128")
            result shouldBe 128u.toUByte()
        }

        "toUnsignedByte() should be able to parse '255'" {
            val result = CommanderValueTransformers.toUnsignedByte.invoke("255")
            result shouldBe 255u.toUByte()
        }

        "toUnsignedByte() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte.invoke("-1")
            }
        }

        "toUnsignedByte() should not be able to parse '256'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte.invoke("256")
            }
        }

        "toUnsignedByte() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte.invoke("abc")
            }
        }

        "toUnsignedByte() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte.invoke(null)
            }
        }

        "toUnsignedByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte.invoke("")
            }
        }

        "toUnsignedByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedByte.invoke(" ")
            }
        }

        "toUnsignedByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("0")
            result shouldBe 0u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("1")
            result shouldBe 1u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '127'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("127")
            result shouldBe 127u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '128'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("128")
            result shouldBe 128u.toUByte()
        }

        "toUnsignedByteOrNull() should be able to parse '255'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("255")
            result shouldBe 255u.toUByte()
        }

        "toUnsignedByteOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("-1")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse '256'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("256")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("abc")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke(null)
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke("")
            result shouldBe null
        }

        "toUnsignedByteOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toUnsignedByteOrNull.invoke(" ")
            result shouldBe null
        }

        "toUnsignedShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedShort.invoke("0")
            result shouldBe 0u.toUShort()
        }

        "toUnsignedShort() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedShort.invoke("1")
            result shouldBe 1u.toUShort()
        }

        "toUnsignedShort() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toUnsignedShort.invoke("65535")
            result shouldBe 65535u.toUShort()
        }

        "toUnsignedShort() should be able to parse '32768'" {
            val result = CommanderValueTransformers.toUnsignedShort.invoke("32768")
            result shouldBe 32768u.toUShort()
        }

        "toUnsignedShort() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort.invoke("-1")
            }
        }

        "toUnsignedShort() should not be able to parse '65536'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort.invoke("65536")
            }
        }

        "toUnsignedShort() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort.invoke("abc")
            }
        }

        "toUnsignedShort() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort.invoke(null)
            }
        }

        "toUnsignedShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort.invoke("")
            }
        }

        "toUnsignedShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedShort.invoke(" ")
            }
        }

        "toUnsignedShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("0")
            result shouldBe 0u.toUShort()
        }

        "toUnsignedShortOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("1")
            result shouldBe 1u.toUShort()
        }

        "toUnsignedShortOrNull() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("65535")
            result shouldBe 65535u.toUShort()
        }

        "toUnsignedShortOrNull() should be able to parse '32768'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("32768")
            result shouldBe 32768u.toUShort()
        }

        "toUnsignedShortOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("-1")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse '65536'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("65536")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("abc")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke(null)
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke("")
            result shouldBe null
        }

        "toUnsignedShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toUnsignedShortOrNull.invoke(" ")
            result shouldBe null
        }

        "toUnsignedInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedInt.invoke("0")
            result shouldBe 0u
        }

        "toUnsignedInt() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedInt.invoke("1")
            result shouldBe 1u
        }

        "toUnsignedInt() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toUnsignedInt.invoke("2147483647")
            result shouldBe 2147483647u
        }

        "toUnsignedInt() should be able to parse '4294967295'" {
            val result = CommanderValueTransformers.toUnsignedInt.invoke("4294967295")
            result shouldBe 4294967295u
        }

        "toUnsignedInt() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt.invoke("-1")
            }
        }

        "toUnsignedInt() should not be able to parse '4294967296'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt.invoke("4294967296")
            }
        }

        "toUnsignedInt() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt.invoke("abc")
            }
        }

        "toUnsignedInt() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt.invoke(null)
            }
        }

        "toUnsignedInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt.invoke("")
            }
        }

        "toUnsignedInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedInt.invoke(" ")
            }
        }

        "toUnsignedIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("0")
            result shouldBe 0u
        }

        "toUnsignedIntOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("1")
            result shouldBe 1u
        }

        "toUnsignedIntOrNull() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("2147483647")
            result shouldBe 2147483647u
        }

        "toUnsignedIntOrNull() should be able to parse '4294967295'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("4294967295")
            result shouldBe 4294967295u
        }

        "toUnsignedIntOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("-1")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse '4294967296'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("4294967296")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("abc")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke(null)
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke("")
            result shouldBe null
        }

        "toUnsignedIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toUnsignedIntOrNull.invoke(" ")
            result shouldBe null
        }

        "toUnsignedLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedLong.invoke("0")
            result shouldBe 0uL
        }

        "toUnsignedLong() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedLong.invoke("1")
            result shouldBe 1uL
        }

        "toUnsignedLong() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toUnsignedLong.invoke("9223372036854775807")
            result shouldBe 9223372036854775807uL
        }

        "toUnsignedLong() should be able to parse '18446744073709551615'" {
            val result = CommanderValueTransformers.toUnsignedLong.invoke("18446744073709551615")
            result shouldBe ULong.MAX_VALUE
        }

        "toUnsignedLong() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong.invoke("-1")
            }
        }

        "toUnsignedLong() should not be able to parse '18446744073709551616'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong.invoke("18446744073709551616")
            }
        }

        "toUnsignedLong() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong.invoke("abc")
            }
        }

        "toUnsignedLong() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong.invoke(null)
            }
        }

        "toUnsignedLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong.invoke("")
            }
        }

        "toUnsignedLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toUnsignedLong.invoke(" ")
            }
        }

        "toUnsignedLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("0")
            result shouldBe 0uL
        }

        "toUnsignedLongOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("1")
            result shouldBe 1uL
        }

        "toUnsignedLongOrNull() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("9223372036854775807")
            result shouldBe 9223372036854775807uL
        }

        "toUnsignedLongOrNull() should be able to parse '18446744073709551615'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("18446744073709551615")
            result shouldBe ULong.MAX_VALUE
        }

        "toUnsignedLongOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("-1")
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse '18446744073709551616'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("18446744073709551616")
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("abc")
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke(null)
            result shouldBe null
        }

        "toUnsignedLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toUnsignedLongOrNull.invoke("")
            result shouldBe null
        }

        "toPositiveByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveByte.invoke("0")
            result shouldBe 0
        }

        "toPositiveByte() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveByte.invoke("1")
            result shouldBe 1
        }

        "toPositiveByte() should be able to parse '127'" {
            val result = CommanderValueTransformers.toPositiveByte.invoke("127")
            result shouldBe 127
        }

        "toPositiveByte() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte.invoke("-1")
            }
        }

        "toPositiveByte() should not be able to parse '128'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte.invoke("128")
            }
        }

        "toPositiveByte() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte.invoke("abc")
            }
        }

        "toPositiveByte() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte.invoke(null)
            }
        }

        "toPositiveByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte.invoke("")
            }
        }

        "toPositiveByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveByte.invoke(" ")
            }
        }

        "toPositiveByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull.invoke("0")
            result shouldBe 0
        }

        "toPositiveByteOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull.invoke("1")
            result shouldBe 1
        }

        "toPositiveByteOrNull() should be able to parse '127'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull.invoke("127")
            result shouldBe 127
        }

        "toPositiveByteOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull.invoke("-1")
            result shouldBe null
        }

        "toPositiveByteOrNull() should not be able to parse '128'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull.invoke("128")
            result shouldBe null
        }

        "toPositiveByteOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveByteOrNull.invoke("abc")
            result shouldBe null
        }

        "toPositiveShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveShort.invoke("0")
            result shouldBe 0
        }

        "toPositiveShort() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveShort.invoke("1")
            result shouldBe 1
        }

        "toPositiveShort() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toPositiveShort.invoke("32767")
            result shouldBe 32767
        }

        "toPositiveShort() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort.invoke("-1")
            }
        }

        "toPositiveShort() should not be able to parse '32768'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort.invoke("32768")
            }
        }

        "toPositiveShort() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort.invoke("abc")
            }
        }

        "toPositiveShort() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort.invoke(null)
            }
        }

        "toPositiveShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort.invoke("")
            }
        }

        "toPositiveShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveShort.invoke(" ")
            }
        }

        "toPositiveShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("0")
            result shouldBe 0
        }

        "toPositiveShortOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("1")
            result shouldBe 1
        }

        "toPositiveShortOrNull() should be able to parse '32767'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("32767")
            result shouldBe 32767
        }

        "toPositiveShortOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("-1")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse '32768'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("32768")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("abc")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke(null)
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke("")
            result shouldBe null
        }

        "toPositiveShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveShortOrNull.invoke(" ")
            result shouldBe null
        }

        "toPositiveInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveInt.invoke("0")
            result shouldBe 0
        }

        "toPositiveInt() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveInt.invoke("1")
            result shouldBe 1
        }

        "toPositiveInt() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toPositiveInt.invoke("2147483647")
            result shouldBe 2147483647
        }

        "toPositiveInt() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt.invoke("-1")
            }
        }

        "toPositiveInt() should not be able to parse '2147483648'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt.invoke("2147483648")
            }
        }

        "toPositiveInt() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt.invoke("abc")
            }
        }

        "toPositiveInt() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt.invoke(null)
            }
        }

        "toPositiveInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt.invoke("")
            }
        }

        "toPositiveInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveInt.invoke(" ")
            }
        }

        "toPositiveIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("0")
            result shouldBe 0
        }

        "toPositiveIntOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("1")
            result shouldBe 1
        }

        "toPositiveIntOrNull() should be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("2147483647")
            result shouldBe 2147483647
        }

        "toPositiveIntOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("-1")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse '2147483648'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("2147483648")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("abc")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke(null)
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke("")
            result shouldBe null
        }

        "toPositiveIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveIntOrNull.invoke(" ")
            result shouldBe null
        }

        "toPositiveLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveLong.invoke("0")
            result shouldBe 0
        }

        "toPositiveLong() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveLong.invoke("1")
            result shouldBe 1
        }

        "toPositiveLong() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toPositiveLong.invoke("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toPositiveLong() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong.invoke("-1")
            }
        }

        "toPositiveLong() should not be able to parse '9223372036854775808'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong.invoke("9223372036854775808")
            }
        }

        "toPositiveLong() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong.invoke("abc")
            }
        }

        "toPositiveLong() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong.invoke(null)
            }
        }

        "toPositiveLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong.invoke("")
            }
        }

        "toPositiveLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveLong.invoke(" ")
            }
        }

        "toPositiveLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("0")
            result shouldBe 0
        }

        "toPositiveLongOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("1")
            result shouldBe 1
        }

        "toPositiveLongOrNull() should be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("9223372036854775807")
            result shouldBe 9223372036854775807
        }

        "toPositiveLongOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("-1")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse '9223372036854775808'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("9223372036854775808")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("abc")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke(null)
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke("")
            result shouldBe null
        }

        "toPositiveLongOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveLongOrNull.invoke(" ")
            result shouldBe null
        }

        "toPositiveFloat() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveFloat.invoke("0")
            result shouldBe 0f
        }

        "toPositiveFloat() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveFloat.invoke("1")
            result shouldBe 1f
        }

        "toPositiveFloat() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toPositiveFloat.invoke("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toPositiveFloat() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat.invoke("-1")
            }
        }

        "toPositiveFloat() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat.invoke("abc")
            }
        }

        "toPositiveFloat() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat.invoke(null)
            }
        }

        "toPositiveFloat() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat.invoke("")
            }
        }

        "toPositiveFloat() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveFloat.invoke(" ")
            }
        }

        "toPositiveFloatOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke("0")
            result shouldBe 0f
        }

        "toPositiveFloatOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke("1")
            result shouldBe 1f
        }

        "toPositiveFloatOrNull() should be able to parse '3.4028235E38'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke("3.4028235E38")
            result shouldBe 3.4028235E38f
        }

        "toPositiveFloatOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke("-1")
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke("abc")
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke(null)
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke("")
            result shouldBe null
        }

        "toPositiveFloatOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveFloatOrNull.invoke(" ")
            result shouldBe null
        }

        "toPositiveDouble() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveDouble.invoke("0")
            result shouldBe 0.0
        }

        "toPositiveDouble() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveDouble.invoke("1")
            result shouldBe 1.0
        }

        "toPositiveDouble() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toPositiveDouble.invoke("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toPositiveDouble() should not be able to parse '-1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble.invoke("-1")
            }
        }

        "toPositiveDouble() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble.invoke("abc")
            }
        }

        "toPositiveDouble() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble.invoke(null)
            }
        }

        "toPositiveDouble() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble.invoke("")
            }
        }

        "toPositiveDouble() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toPositiveDouble.invoke(" ")
            }
        }

        "toPositiveDoubleOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke("0")
            result shouldBe 0.0
        }

        "toPositiveDoubleOrNull() should be able to parse '1'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke("1")
            result shouldBe 1.0
        }

        "toPositiveDoubleOrNull() should be able to parse '1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke("1.7976931348623157E308")
            result shouldBe 1.7976931348623157E308
        }

        "toPositiveDoubleOrNull() should not be able to parse '-1'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke("-1")
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke("abc")
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke(null)
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke("")
            result shouldBe null
        }

        "toPositiveDoubleOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toPositiveDoubleOrNull.invoke(" ")
            result shouldBe null
        }

        "toNegativeByte() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeByte.invoke("0")
            result shouldBe 0
        }

        "toNegativeByte() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeByte.invoke("-1")
            result shouldBe -1
        }

        "toNegativeByte() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toNegativeByte.invoke("-128")
            result shouldBe -128
        }

        "toNegativeByte() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke("1")
            }
        }

        "toNegativeByte() should not be able to parse '127'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke("127")
            }
        }

        "toNegativeByte() should not be able to parse '128'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke("128")
            }
        }

        "toNegativeByte() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke("abc")
            }
        }

        "toNegativeByte() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke(null)
            }
        }

        "toNegativeByte() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke("")
            }
        }

        "toNegativeByte() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeByte.invoke(" ")
            }
        }

        "toNegativeByteOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("0")
            result shouldBe 0
        }

        "toNegativeByteOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("-1")
            result shouldBe -1
        }

        "toNegativeByteOrNull() should be able to parse '-128'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("-128")
            result shouldBe -128
        }

        "toNegativeByteOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("1")
            result shouldBe null
        }

        "toNegativeByteOrNull() should not be able to parse '127'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("127")
            result shouldBe null
        }

        "toNegativeByteOrNull() should not be able to parse '128'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("128")
            result shouldBe null
        }

        "toNegativeByteOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeByteOrNull.invoke("abc")
            result shouldBe null
        }

        "toNegativeShort() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeShort.invoke("0")
            result shouldBe 0
        }

        "toNegativeShort() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeShort.invoke("-1")
            result shouldBe -1
        }

        "toNegativeShort() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toNegativeShort.invoke("-32768")
            result shouldBe -32768
        }

        "toNegativeShort() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke("1")
            }
        }

        "toNegativeShort() should not be able to parse '32767'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke("32767")
            }
        }

        "toNegativeShort() should not be able to parse '32768'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke("32768")
            }
        }

        "toNegativeShort() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke("abc")
            }
        }

        "toNegativeShort() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke(null)
            }
        }

        "toNegativeShort() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke("")
            }
        }

        "toNegativeShort() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeShort.invoke(" ")
            }
        }

        "toNegativeShortOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("0")
            result shouldBe 0
        }

        "toNegativeShortOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("-1")
            result shouldBe -1
        }

        "toNegativeShortOrNull() should be able to parse '-32768'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("-32768")
            result shouldBe -32768
        }

        "toNegativeShortOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("1")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse '32767'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("32767")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse '32768'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("32768")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("abc")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke(null)
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke("")
            result shouldBe null
        }

        "toNegativeShortOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeShortOrNull.invoke(" ")
            result shouldBe null
        }

        "toNegativeInt() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeInt.invoke("0")
            result shouldBe 0
        }

        "toNegativeInt() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeInt.invoke("-1")
            result shouldBe -1
        }

        "toNegativeInt() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toNegativeInt.invoke("-2147483648")
            result shouldBe -2147483648
        }

        "toNegativeInt() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke("1")
            }
        }

        "toNegativeInt() should not be able to parse '2147483647'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke("2147483647")
            }
        }

        "toNegativeInt() should not be able to parse '2147483648'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke("2147483648")
            }
        }

        "toNegativeInt() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke("abc")
            }
        }

        "toNegativeInt() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke(null)
            }
        }

        "toNegativeInt() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke("")
            }
        }

        "toNegativeInt() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeInt.invoke(" ")
            }
        }

        "toNegativeIntOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("0")
            result shouldBe 0
        }

        "toNegativeIntOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("-1")
            result shouldBe -1
        }

        "toNegativeIntOrNull() should be able to parse '-2147483648'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("-2147483648")
            result shouldBe -2147483648
        }

        "toNegativeIntOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("1")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse '2147483647'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("2147483647")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse '2147483648'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("2147483648")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("abc")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke(null)
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke("")
            result shouldBe null
        }

        "toNegativeIntOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeIntOrNull.invoke(" ")
            result shouldBe null
        }

        "toNegativeLong() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeLong.invoke("0")
            result shouldBe 0
        }

        "toNegativeLong() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeLong.invoke("-1")
            result shouldBe -1
        }

        "toNegativeLong() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toNegativeLong.invoke("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toNegativeLong() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke("1")
            }
        }

        "toNegativeLong() should not be able to parse '9223372036854775807'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke("9223372036854775807")
            }
        }

        "toNegativeLong() should not be able to parse '9223372036854775808'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke("9223372036854775808")
            }
        }

        "toNegativeLong() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke("abc")
            }
        }

        "toNegativeLong() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke(null)
            }
        }

        "toNegativeLong() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke("")
            }
        }

        "toNegativeLong() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeLong.invoke(" ")
            }
        }

        "toNegativeLongOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("0")
            result shouldBe 0
        }

        "toNegativeLongOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("-1")
            result shouldBe -1
        }

        "toNegativeLongOrNull() should be able to parse '-9223372036854775808'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("-9223372036854775808")
            result shouldBe Long.MIN_VALUE
        }

        "toNegativeLongOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("1")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse '9223372036854775807'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("9223372036854775807")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse '9223372036854775808'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("9223372036854775808")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("abc")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke(null)
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke("")
            result shouldBe null
        }

        "toNegativeLongOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeLongOrNull.invoke(" ")
            result shouldBe null
        }

        "toNegativeFloat() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeFloat.invoke("0")
            result shouldBe 0f
        }

        "toNegativeFloat() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeFloat.invoke("-1")
            result shouldBe -1f
        }

        "toNegativeFloat() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toNegativeFloat.invoke("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toNegativeFloat() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat.invoke("1")
            }
        }

        "toNegativeFloat() should not be able to parse '3.4028236E38'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat.invoke("3.4028236E38")
            }
        }

        "toNegativeFloat() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat.invoke("abc")
            }
        }

        "toNegativeFloat() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat.invoke(null)
            }
        }

        "toNegativeFloat() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat.invoke("")
            }
        }

        "toNegativeFloat() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeFloat.invoke(" ")
            }
        }

        "toNegativeFloatOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("0")
            result shouldBe 0f
        }

        "toNegativeFloatOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("-1")
            result shouldBe -1f
        }

        "toNegativeFloatOrNull() should be able to parse '-3.4028235E38'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("-3.4028235E38")
            result shouldBe -3.4028235E38f
        }

        "toNegativeFloatOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("1")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse '3.4028236E38'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("3.4028236E38")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("abc")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke(null)
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke("")
            result shouldBe null
        }

        "toNegativeFloatOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeFloatOrNull.invoke(" ")
            result shouldBe null
        }

        "toNegativeDouble() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeDouble.invoke("0")
            result shouldBe 0.0
        }

        "toNegativeDouble() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeDouble.invoke("-1")
            result shouldBe -1.0
        }

        "toNegativeDouble() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toNegativeDouble.invoke("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toNegativeDouble() should not be able to parse '1'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble.invoke("1")
            }
        }

        "toNegativeDouble() should not be able to parse '1.7976931348623159E308'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble.invoke("1.7976931348623159E308")
            }
        }

        "toNegativeDouble() should not be able to parse 'abc'" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble.invoke("abc")
            }
        }

        "toNegativeDouble() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble.invoke(null)
            }
        }

        "toNegativeDouble() should not be able to parse ''" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble.invoke("")
            }
        }

        "toNegativeDouble() should not be able to parse ' '" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toNegativeDouble.invoke(" ")
            }
        }

        "toNegativeDoubleOrNull() should be able to parse '0'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("0")
            result shouldBe 0.0
        }

        "toNegativeDoubleOrNull() should be able to parse '-1'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("-1")
            result shouldBe -1.0
        }

        "toNegativeDoubleOrNull() should be able to parse '-1.7976931348623157E308'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("-1.7976931348623157E308")
            result shouldBe -1.7976931348623157E308
        }

        "toNegativeDoubleOrNull() should not be able to parse '1'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("1")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse '1.7976931348623159E308'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("1.7976931348623159E308")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse 'abc'" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("abc")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke(null)
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse ''" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke("")
            result shouldBe null
        }

        "toNegativeDoubleOrNull() should not be able to parse ' '" {
            val result = CommanderValueTransformers.toNegativeDoubleOrNull.invoke(" ")
            result shouldBe null
        }

        "toString() should be able to parse 'abc'" {
            val result = CommanderValueTransformers.toString.invoke("abc")
            result shouldBe "abc"
        }

        "toString() should be able to parse '123'" {
            val result = CommanderValueTransformers.toString.invoke("123")
            result shouldBe "123"
        }

        "toString() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toString.invoke("true")
            result shouldBe "true"
        }

        "toString() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toString.invoke("false")
            result shouldBe "false"
        }

        "toString() should not be able to parse null" {
            shouldThrow<CommanderValueException> {
                CommanderValueTransformers.toString.invoke(null)
            }
        }

        "toString() should be able to parse ''" {
            CommanderValueTransformers.toString.invoke("") shouldBe ""
        }

        "toString() should be able to parse ' '" {
            CommanderValueTransformers.toString.invoke(" ") shouldBe " "
        }

        "toStringOrNull() should be able to parse 'abc'" {
            val result = CommanderValueTransformers.toStringOrNull.invoke("abc")
            result shouldBe "abc"
        }

        "toStringOrNull() should be able to parse '123'" {
            val result = CommanderValueTransformers.toStringOrNull.invoke("123")
            result shouldBe "123"
        }

        "toStringOrNull() should be able to parse 'true'" {
            val result = CommanderValueTransformers.toStringOrNull.invoke("true")
            result shouldBe "true"
        }

        "toStringOrNull() should be able to parse 'false'" {
            val result = CommanderValueTransformers.toStringOrNull.invoke("false")
            result shouldBe "false"
        }

        "toStringOrNull() should not be able to parse null" {
            val result = CommanderValueTransformers.toStringOrNull.invoke(null)
            result shouldBe null
        }

        "toStringOrNull() should be able to parse ''" {
            CommanderValueTransformers.toStringOrNull.invoke("") shouldBe ""
        }

        "toStringOrNull() should be able to parse ' '" {
            CommanderValueTransformers.toStringOrNull.invoke(" ") shouldBe " "
        }
    },
)
