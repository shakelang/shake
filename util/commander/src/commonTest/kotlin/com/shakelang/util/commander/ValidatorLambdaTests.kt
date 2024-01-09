package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class ValidatorLambdaTests : FreeSpec(
    {

        "isByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isByte.invoke(null)
            }
        }

        "isByte() should throw an exception if the value is not a byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isByte.invoke("abc")
            }
        }

        "isByte() should not throw an exception if the value is a byte" {
            CommanderValidators.isByte.invoke("1")
        }

        "isShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isShort.invoke(null)
            }
        }

        "isShort() should throw an exception if the value is not a short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isShort.invoke("abc")
            }
        }

        "isShort() should not throw an exception if the value is a short" {
            CommanderValidators.isShort.invoke("1")
        }

        "isInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isInt.invoke(null)
            }
        }

        "isInt() should throw an exception if the value is not a int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isInt.invoke("abc")
            }
        }

        "isInt() should not throw an exception if the value is a int" {
            CommanderValidators.isInt.invoke("1")
        }

        "isLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isLong.invoke(null)
            }
        }

        "isLong() should throw an exception if the value is not a long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isLong.invoke("abc")
            }
        }

        "isLong() should not throw an exception if the value is a long" {
            CommanderValidators.isLong.invoke("1")
        }

        "isFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isFloat.invoke(null)
            }
        }

        "isFloat() should throw an exception if the value is not a float" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isFloat.invoke("abc")
            }
        }

        "isFloat() should not throw an exception if the value is a float" {
            CommanderValidators.isFloat.invoke("1")
        }

        "isDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isDouble.invoke(null)
            }
        }

        "isDouble() should throw an exception if the value is not a double" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isDouble.invoke("abc")
            }
        }

        "isDouble() should not throw an exception if the value is a double" {
            CommanderValidators.isDouble.invoke("1")
        }

        "isBoolean() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isBoolean.invoke(null)
            }
        }

        "isBoolean() should throw an exception if the value is not a boolean" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isBoolean.invoke("abc")
            }
        }

        "isBoolean() should not throw an exception if the value is a boolean" {
            CommanderValidators.isBoolean.invoke("true")
        }

        "isBoolean() should interpret 1 as true" {
            CommanderValidators.isBoolean.invoke("1")
        }

        "isBoolean() should interpret 0 as false" {
            CommanderValidators.isBoolean.invoke("0")
        }

        "isBoolean() should interpret yes as true" {
            CommanderValidators.isBoolean.invoke("yes")
        }

        "isBoolean() should interpret no as false" {
            CommanderValidators.isBoolean.invoke("no")
        }

        "isBoolean() should interpret y as true" {
            CommanderValidators.isBoolean.invoke("y")
        }

        "isBoolean() should interpret n as false" {
            CommanderValidators.isBoolean.invoke("n")
        }

        "isBoolean() should interpret true as true" {
            CommanderValidators.isBoolean.invoke("true")
        }

        "isBoolean() should interpret false as false" {
            CommanderValidators.isBoolean.invoke("false")
        }

        "isString() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isString.invoke(null)
            }
        }

        "isString() should not throw an exception if the value is a string" {
            CommanderValidators.isString.invoke("abc")
        }

        "isNull() should throw an exception if the value is not null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNull.invoke("abc")
            }
        }

        "isNull() should not throw an exception if the value is null" {
            CommanderValidators.isNull.invoke(null)
        }

        "isNotNull() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNotNull.invoke(null)
            }
        }

        "isNotNull() should not throw an exception if the value is not null" {
            CommanderValidators.isNotNull.invoke("abc")
        }

        "isUnsignedByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedByte.invoke(null)
            }
        }

        "isUnsignedByte() should throw an exception if the value is not a unsigned byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedByte.invoke("abc")
            }
        }

        "isUnsignedByte() should not throw an exception if the value is a unsigned byte" {
            CommanderValidators.isUnsignedByte.invoke("1")
        }

        "isUnsignedShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedShort.invoke(null)
            }
        }

        "isUnsignedShort() should throw an exception if the value is not a unsigned short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedShort.invoke("abc")
            }
        }

        "isUnsignedShort() should not throw an exception if the value is a unsigned short" {
            CommanderValidators.isUnsignedShort.invoke("1")
        }

        "isUnsignedInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedInt.invoke(null)
            }
        }

        "isUnsignedInt() should throw an exception if the value is not a unsigned int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedInt.invoke("abc")
            }
        }

        "isUnsignedInt() should not throw an exception if the value is a unsigned int" {
            CommanderValidators.isUnsignedInt.invoke("1")
        }

        "isUnsignedLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedLong.invoke(null)
            }
        }

        "isUnsignedLong() should throw an exception if the value is not a unsigned long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedLong.invoke("abc")
            }
        }

        "isUnsignedLong() should not throw an exception if the value is a unsigned long" {
            CommanderValidators.isUnsignedLong.invoke("1")
        }

        "isPositiveByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveByte.invoke(null)
            }
        }

        "isPositiveByte() should throw an exception if the value is not a positive byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveByte.invoke("-1")
            }
        }

        "isPositiveByte() should not throw an exception if the value is a positive byte" {
            CommanderValidators.isPositiveByte.invoke("1")
        }

        "isPositiveShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveShort.invoke(null)
            }
        }

        "isPositiveShort() should throw an exception if the value is not a positive short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveShort.invoke("-1")
            }
        }

        "isPositiveShort() should not throw an exception if the value is a positive short" {
            CommanderValidators.isPositiveShort.invoke("1")
        }

        "isPositiveInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveInt.invoke(null)
            }
        }

        "isPositiveInt() should throw an exception if the value is not a positive int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveInt.invoke("-1")
            }
        }

        "isPositiveInt() should not throw an exception if the value is a positive int" {
            CommanderValidators.isPositiveInt.invoke("1")
        }

        "isPositiveLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveLong.invoke(null)
            }
        }

        "isPositiveLong() should throw an exception if the value is not a positive long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveLong.invoke("-1")
            }
        }

        "isPositiveLong() should not throw an exception if the value is a positive long" {
            CommanderValidators.isPositiveLong.invoke("1")
        }

        "isPositiveFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveFloat.invoke(null)
            }
        }

        "isPositiveFloat() should throw an exception if the value is not a positive float" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveFloat.invoke("-1")
            }
        }

        "isPositiveFloat() should not throw an exception if the value is a positive float" {
            CommanderValidators.isPositiveFloat.invoke("1")
        }

        "isPositiveDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveDouble.invoke(null)
            }
        }

        "isPositiveDouble() should throw an exception if the value is not a positive double" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveDouble.invoke("-1")
            }
        }

        "isPositiveDouble() should not throw an exception if the value is a positive double" {
            CommanderValidators.isPositiveDouble.invoke("1")
        }

        "isPositive() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositive.invoke(null)
            }
        }

        "isPositive() should throw an exception if the value is not a positive number" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositive.invoke("-1")
            }
        }

        "isPositive() should not throw an exception if the value is a positive number" {
            CommanderValidators.isPositive.invoke("1")
        }

        "isNegativeByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeByte.invoke(null)
            }
        }

        "isNegativeByte() should throw an exception if the value is not a negative byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeByte.invoke("1")
            }
        }

        "isNegativeByte() should not throw an exception if the value is a negative byte" {
            CommanderValidators.isNegativeByte.invoke("-1")
        }

        "isNegativeShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeShort.invoke(null)
            }
        }

        "isNegativeShort() should throw an exception if the value is not a negative short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeShort.invoke("1")
            }
        }

        "isNegativeShort() should not throw an exception if the value is a negative short" {
            CommanderValidators.isNegativeShort.invoke("-1")
        }

        "isNegativeInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeInt.invoke(null)
            }
        }

        "isNegativeInt() should throw an exception if the value is not a negative int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeInt.invoke("1")
            }
        }

        "isNegativeInt() should not throw an exception if the value is a negative int" {
            CommanderValidators.isNegativeInt.invoke("-1")
        }

        "isNegativeLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeLong.invoke(null)
            }
        }

        "isNegativeLong() should throw an exception if the value is not a negative long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeLong.invoke("1")
            }
        }

        "isNegativeLong() should not throw an exception if the value is a negative long" {
            CommanderValidators.isNegativeLong.invoke("-1")
        }

        "isNegativeFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeFloat.invoke(null)
            }
        }

        "isNegativeFloat() should throw an exception if the value is not a negative float" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeFloat.invoke("1")
            }
        }

        "isNegativeFloat() should not throw an exception if the value is a negative float" {
            CommanderValidators.isNegativeFloat.invoke("-1")
        }

        "isNegativeDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeDouble.invoke(null)
            }
        }

        "isNegativeDouble() should throw an exception if the value is not a negative double" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeDouble.invoke("1")
            }
        }

        "isNegativeDouble() should not throw an exception if the value is a negative double" {
            CommanderValidators.isNegativeDouble.invoke("-1")
        }

        "isNegative() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegative.invoke(null)
            }
        }

        "isNegative() should throw an exception if the value is not a negative number" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegative.invoke("1")
            }
        }

        "isNegative() should not throw an exception if the value is a negative number" {
            CommanderValidators.isNegative.invoke("-1")
        }
    },
)
