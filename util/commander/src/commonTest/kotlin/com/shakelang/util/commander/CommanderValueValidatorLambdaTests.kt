package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class CommanderValueValidatorLambdaTests : FreeSpec(
    {

        "isByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isByte.invoke(null)
            }
        }

        "isByte() should throw an exception if the value is not a byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isByte.invoke("abc")
            }
        }

        "isByte() should not throw an exception if the value is a byte" {
            CommanderValueValidators.isByte.invoke("1")
        }

        "isShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isShort.invoke(null)
            }
        }

        "isShort() should throw an exception if the value is not a shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isShort.invoke("abc")
            }
        }

        "isShort() should not throw an exception if the value is a shorts" {
            CommanderValueValidators.isShort.invoke("1")
        }

        "isInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isInt.invoke(null)
            }
        }

        "isInt() should throw an exception if the value is not a int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isInt.invoke("abc")
            }
        }

        "isInt() should not throw an exception if the value is a int" {
            CommanderValueValidators.isInt.invoke("1")
        }

        "isLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isLong.invoke(null)
            }
        }

        "isLong() should throw an exception if the value is not a long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isLong.invoke("abc")
            }
        }

        "isLong() should not throw an exception if the value is a long" {
            CommanderValueValidators.isLong.invoke("1")
        }

        "isFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isFloat.invoke(null)
            }
        }

        "isFloat() should throw an exception if the value is not a float" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isFloat.invoke("abc")
            }
        }

        "isFloat() should not throw an exception if the value is a float" {
            CommanderValueValidators.isFloat.invoke("1")
        }

        "isDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isDouble.invoke(null)
            }
        }

        "isDouble() should throw an exception if the value is not a double" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isDouble.invoke("abc")
            }
        }

        "isDouble() should not throw an exception if the value is a double" {
            CommanderValueValidators.isDouble.invoke("1")
        }

        "isBoolean() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isBoolean.invoke(null)
            }
        }

        "isBoolean() should throw an exception if the value is not a boolean" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isBoolean.invoke("abc")
            }
        }

        "isBoolean() should not throw an exception if the value is a boolean" {
            CommanderValueValidators.isBoolean.invoke("true")
        }

        "isBoolean() should interpret 1 as true" {
            CommanderValueValidators.isBoolean.invoke("1")
        }

        "isBoolean() should interpret 0 as false" {
            CommanderValueValidators.isBoolean.invoke("0")
        }

        "isBoolean() should interpret yes as true" {
            CommanderValueValidators.isBoolean.invoke("yes")
        }

        "isBoolean() should interpret no as false" {
            CommanderValueValidators.isBoolean.invoke("no")
        }

        "isBoolean() should interpret y as true" {
            CommanderValueValidators.isBoolean.invoke("y")
        }

        "isBoolean() should interpret n as false" {
            CommanderValueValidators.isBoolean.invoke("n")
        }

        "isBoolean() should interpret true as true" {
            CommanderValueValidators.isBoolean.invoke("true")
        }

        "isBoolean() should interpret false as false" {
            CommanderValueValidators.isBoolean.invoke("false")
        }

        "isChar() should accept a single character" {
            CommanderValueValidators.isChar.invoke("a")
        }

        "isChar() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isChar.invoke(null)
            }
        }

        "isChar() should throw an exception if the value is not a char" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isChar.invoke("abc")
            }
        }

        "isString() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isString.invoke(null)
            }
        }

        "isString() should not throw an exception if the value is a string" {
            CommanderValueValidators.isString.invoke("abc")
        }

        "isNull() should throw an exception if the value is not null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNull.invoke("abc")
            }
        }

        "isNull() should not throw an exception if the value is null" {
            CommanderValueValidators.isNull.invoke(null)
        }

        "isNotNull() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNotNull.invoke(null)
            }
        }

        "isNotNull() should not throw an exception if the value is not null" {
            CommanderValueValidators.isNotNull.invoke("abc")
        }

        "isUnsignedByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedByte.invoke(null)
            }
        }

        "isUnsignedByte() should throw an exception if the value is not a unsigned byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedByte.invoke("abc")
            }
        }

        "isUnsignedByte() should not throw an exception if the value is a unsigned byte" {
            CommanderValueValidators.isUnsignedByte.invoke("1")
        }

        "isUnsignedShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedShort.invoke(null)
            }
        }

        "isUnsignedShort() should throw an exception if the value is not a unsigned shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedShort.invoke("abc")
            }
        }

        "isUnsignedShort() should not throw an exception if the value is a unsigned shorts" {
            CommanderValueValidators.isUnsignedShort.invoke("1")
        }

        "isUnsignedInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedInt.invoke(null)
            }
        }

        "isUnsignedInt() should throw an exception if the value is not a unsigned int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedInt.invoke("abc")
            }
        }

        "isUnsignedInt() should not throw an exception if the value is a unsigned int" {
            CommanderValueValidators.isUnsignedInt.invoke("1")
        }

        "isUnsignedLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedLong.invoke(null)
            }
        }

        "isUnsignedLong() should throw an exception if the value is not a unsigned long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedLong.invoke("abc")
            }
        }

        "isUnsignedLong() should not throw an exception if the value is a unsigned long" {
            CommanderValueValidators.isUnsignedLong.invoke("1")
        }

        "isPositiveByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveByte.invoke(null)
            }
        }

        "isPositiveByte() should throw an exception if the value is not a positive byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveByte.invoke("-1")
            }
        }

        "isPositiveByte() should not throw an exception if the value is a positive byte" {
            CommanderValueValidators.isPositiveByte.invoke("1")
        }

        "isPositiveShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveShort.invoke(null)
            }
        }

        "isPositiveShort() should throw an exception if the value is not a positive shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveShort.invoke("-1")
            }
        }

        "isPositiveShort() should not throw an exception if the value is a positive shorts" {
            CommanderValueValidators.isPositiveShort.invoke("1")
        }

        "isPositiveInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveInt.invoke(null)
            }
        }

        "isPositiveInt() should throw an exception if the value is not a positive int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveInt.invoke("-1")
            }
        }

        "isPositiveInt() should not throw an exception if the value is a positive int" {
            CommanderValueValidators.isPositiveInt.invoke("1")
        }

        "isPositiveLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveLong.invoke(null)
            }
        }

        "isPositiveLong() should throw an exception if the value is not a positive long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveLong.invoke("-1")
            }
        }

        "isPositiveLong() should not throw an exception if the value is a positive long" {
            CommanderValueValidators.isPositiveLong.invoke("1")
        }

        "isPositiveFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveFloat.invoke(null)
            }
        }

        "isPositiveFloat() should throw an exception if the value is not a positive float" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveFloat.invoke("-1")
            }
        }

        "isPositiveFloat() should not throw an exception if the value is a positive float" {
            CommanderValueValidators.isPositiveFloat.invoke("1")
        }

        "isPositiveDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveDouble.invoke(null)
            }
        }

        "isPositiveDouble() should throw an exception if the value is not a positive double" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveDouble.invoke("-1")
            }
        }

        "isPositiveDouble() should not throw an exception if the value is a positive double" {
            CommanderValueValidators.isPositiveDouble.invoke("1")
        }

        "isPositive() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositive.invoke(null)
            }
        }

        "isPositive() should throw an exception if the value is not a positive number" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositive.invoke("-1")
            }
        }

        "isPositive() should not throw an exception if the value is a positive number" {
            CommanderValueValidators.isPositive.invoke("1")
        }

        "isNegativeByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeByte.invoke(null)
            }
        }

        "isNegativeByte() should throw an exception if the value is not a negative byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeByte.invoke("1")
            }
        }

        "isNegativeByte() should not throw an exception if the value is a negative byte" {
            CommanderValueValidators.isNegativeByte.invoke("-1")
        }

        "isNegativeShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeShort.invoke(null)
            }
        }

        "isNegativeShort() should throw an exception if the value is not a negative shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeShort.invoke("1")
            }
        }

        "isNegativeShort() should not throw an exception if the value is a negative shorts" {
            CommanderValueValidators.isNegativeShort.invoke("-1")
        }

        "isNegativeInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeInt.invoke(null)
            }
        }

        "isNegativeInt() should throw an exception if the value is not a negative int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeInt.invoke("1")
            }
        }

        "isNegativeInt() should not throw an exception if the value is a negative int" {
            CommanderValueValidators.isNegativeInt.invoke("-1")
        }

        "isNegativeLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeLong.invoke(null)
            }
        }

        "isNegativeLong() should throw an exception if the value is not a negative long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeLong.invoke("1")
            }
        }

        "isNegativeLong() should not throw an exception if the value is a negative long" {
            CommanderValueValidators.isNegativeLong.invoke("-1")
        }

        "isNegativeFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeFloat.invoke(null)
            }
        }

        "isNegativeFloat() should throw an exception if the value is not a negative float" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeFloat.invoke("1")
            }
        }

        "isNegativeFloat() should not throw an exception if the value is a negative float" {
            CommanderValueValidators.isNegativeFloat.invoke("-1")
        }

        "isNegativeDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeDouble.invoke(null)
            }
        }

        "isNegativeDouble() should throw an exception if the value is not a negative double" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeDouble.invoke("1")
            }
        }

        "isNegativeDouble() should not throw an exception if the value is a negative double" {
            CommanderValueValidators.isNegativeDouble.invoke("-1")
        }

        "isNegative() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegative.invoke(null)
            }
        }

        "isNegative() should throw an exception if the value is not a negative number" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegative.invoke("1")
            }
        }

        "isNegative() should not throw an exception if the value is a negative number" {
            CommanderValueValidators.isNegative.invoke("-1")
        }
    },
)
