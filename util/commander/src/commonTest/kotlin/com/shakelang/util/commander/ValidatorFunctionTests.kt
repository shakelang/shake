package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class ValidatorFunctionTests : FreeSpec(
    {

        "isByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isByte(null)
            }
        }

        "isByte() should throw an exception if the value is not a byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isByte("abc")
            }
        }

        "isByte() should not throw an exception if the value is a byte" {
            CommanderValidators.isByte("1")
        }

        "isShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isShort(null)
            }
        }

        "isShort() should throw an exception if the value is not a short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isShort("abc")
            }
        }

        "isShort() should not throw an exception if the value is a short" {
            CommanderValidators.isShort("1")
        }

        "isInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isInt(null)
            }
        }

        "isInt() should throw an exception if the value is not a int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isInt("abc")
            }
        }

        "isInt() should not throw an exception if the value is a int" {
            CommanderValidators.isInt("1")
        }

        "isLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isLong(null)
            }
        }

        "isLong() should throw an exception if the value is not a long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isLong("abc")
            }
        }

        "isLong() should not throw an exception if the value is a long" {
            CommanderValidators.isLong("1")
        }

        "isFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isFloat(null)
            }
        }

        "isFloat() should throw an exception if the value is not a float" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isFloat("abc")
            }
        }

        "isFloat() should not throw an exception if the value is a float" {
            CommanderValidators.isFloat("1")
        }

        "isDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isDouble(null)
            }
        }

        "isDouble() should throw an exception if the value is not a double" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isDouble("abc")
            }
        }

        "isDouble() should not throw an exception if the value is a double" {
            CommanderValidators.isDouble("1")
        }

        "isBoolean() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isBoolean(null)
            }
        }

        "isBoolean() should throw an exception if the value is not a boolean" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isBoolean("abc")
            }
        }

        "isBoolean() should not throw an exception if the value is a boolean" {
            CommanderValidators.isBoolean("true")
        }

        "isBoolean() should interpret 1 as true" {
            CommanderValidators.isBoolean("1")
        }

        "isBoolean() should interpret 0 as false" {
            CommanderValidators.isBoolean("0")
        }

        "isBoolean() should interpret yes as true" {
            CommanderValidators.isBoolean("yes")
        }

        "isBoolean() should interpret no as false" {
            CommanderValidators.isBoolean("no")
        }

        "isBoolean() should interpret y as true" {
            CommanderValidators.isBoolean("y")
        }

        "isBoolean() should interpret n as false" {
            CommanderValidators.isBoolean("n")
        }

        "isBoolean() should interpret true as true" {
            CommanderValidators.isBoolean("true")
        }

        "isBoolean() should interpret false as false" {
            CommanderValidators.isBoolean("false")
        }

        "isString() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isString(null)
            }
        }

        "isString() should not throw an exception if the value is a string" {
            CommanderValidators.isString("abc")
        }

        "isNull() should throw an exception if the value is not null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNull("abc")
            }
        }

        "isNull() should not throw an exception if the value is null" {
            CommanderValidators.isNull(null)
        }

        "isNotNull() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNotNull(null)
            }
        }

        "isNotNull() should not throw an exception if the value is not null" {
            CommanderValidators.isNotNull("abc")
        }

        "isUnsignedByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedByte(null)
            }
        }

        "isUnsignedByte() should throw an exception if the value is not a unsigned byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedByte("abc")
            }
        }

        "isUnsignedByte() should not throw an exception if the value is a unsigned byte" {
            CommanderValidators.isUnsignedByte("1")
        }

        "isUnsignedShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedShort(null)
            }
        }

        "isUnsignedShort() should throw an exception if the value is not a unsigned short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedShort("abc")
            }
        }

        "isUnsignedShort() should not throw an exception if the value is a unsigned short" {
            CommanderValidators.isUnsignedShort("1")
        }

        "isUnsignedInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedInt(null)
            }
        }

        "isUnsignedInt() should throw an exception if the value is not a unsigned int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedInt("abc")
            }
        }

        "isUnsignedInt() should not throw an exception if the value is a unsigned int" {
            CommanderValidators.isUnsignedInt("1")
        }

        "isUnsignedLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedLong(null)
            }
        }

        "isUnsignedLong() should throw an exception if the value is not a unsigned long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isUnsignedLong("abc")
            }
        }

        "isUnsignedLong() should not throw an exception if the value is a unsigned long" {
            CommanderValidators.isUnsignedLong("1")
        }

        "isPositiveByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveByte(null)
            }
        }

        "isPositiveByte() should throw an exception if the value is not a positive byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveByte("-1")
            }
        }

        "isPositiveByte() should not throw an exception if the value is a positive byte" {
            CommanderValidators.isPositiveByte("1")
        }

        "isPositiveShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveShort(null)
            }
        }

        "isPositiveShort() should throw an exception if the value is not a positive short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveShort("-1")
            }
        }

        "isPositiveShort() should not throw an exception if the value is a positive short" {
            CommanderValidators.isPositiveShort("1")
        }

        "isPositiveInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveInt(null)
            }
        }

        "isPositiveInt() should throw an exception if the value is not a positive int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveInt("-1")
            }
        }

        "isPositiveInt() should not throw an exception if the value is a positive int" {
            CommanderValidators.isPositiveInt("1")
        }

        "isPositiveLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveLong(null)
            }
        }

        "isPositiveLong() should throw an exception if the value is not a positive long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveLong("-1")
            }
        }

        "isPositiveLong() should not throw an exception if the value is a positive long" {
            CommanderValidators.isPositiveLong("1")
        }

        "isPositiveFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveFloat(null)
            }
        }

        "isPositiveFloat() should throw an exception if the value is not a positive float" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveFloat("-1")
            }
        }

        "isPositiveFloat() should not throw an exception if the value is a positive float" {
            CommanderValidators.isPositiveFloat("1")
        }

        "isPositiveDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveDouble(null)
            }
        }

        "isPositiveDouble() should throw an exception if the value is not a positive double" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositiveDouble("-1")
            }
        }

        "isPositiveDouble() should not throw an exception if the value is a positive double" {
            CommanderValidators.isPositiveDouble("1")
        }

        "isPositive() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositive(null)
            }
        }

        "isPositive() should throw an exception if the value is not a positive number" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isPositive("-1")
            }
        }

        "isPositive() should not throw an exception if the value is a positive number" {
            CommanderValidators.isPositive("1")
        }

        "isNegativeByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeByte(null)
            }
        }

        "isNegativeByte() should throw an exception if the value is not a negative byte" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeByte("1")
            }
        }

        "isNegativeByte() should not throw an exception if the value is a negative byte" {
            CommanderValidators.isNegativeByte("-1")
        }

        "isNegativeShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeShort(null)
            }
        }

        "isNegativeShort() should throw an exception if the value is not a negative short" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeShort("1")
            }
        }

        "isNegativeShort() should not throw an exception if the value is a negative short" {
            CommanderValidators.isNegativeShort("-1")
        }

        "isNegativeInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeInt(null)
            }
        }

        "isNegativeInt() should throw an exception if the value is not a negative int" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeInt("1")
            }
        }

        "isNegativeInt() should not throw an exception if the value is a negative int" {
            CommanderValidators.isNegativeInt("-1")
        }

        "isNegativeLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeLong(null)
            }
        }

        "isNegativeLong() should throw an exception if the value is not a negative long" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeLong("1")
            }
        }

        "isNegativeLong() should not throw an exception if the value is a negative long" {
            CommanderValidators.isNegativeLong("-1")
        }

        "isNegativeFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeFloat(null)
            }
        }

        "isNegativeFloat() should throw an exception if the value is not a negative float" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeFloat("1")
            }
        }

        "isNegativeFloat() should not throw an exception if the value is a negative float" {
            CommanderValidators.isNegativeFloat("-1")
        }

        "isNegativeDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeDouble(null)
            }
        }

        "isNegativeDouble() should throw an exception if the value is not a negative double" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegativeDouble("1")
            }
        }

        "isNegativeDouble() should not throw an exception if the value is a negative double" {
            CommanderValidators.isNegativeDouble("-1")
        }

        "isNegative() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegative(null)
            }
        }

        "isNegative() should throw an exception if the value is not a negative number" {
            shouldThrow<ValueValidationException> {
                CommanderValidators.isNegative("1")
            }
        }

        "isNegative() should not throw an exception if the value is a negative number" {
            CommanderValidators.isNegative("-1")
        }
    },
)
