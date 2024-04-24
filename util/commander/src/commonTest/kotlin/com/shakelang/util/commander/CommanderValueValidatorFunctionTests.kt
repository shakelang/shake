package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class CommanderValueValidatorFunctionTests : FreeSpec(
    {

        "isByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isByte(null)
            }
        }

        "isByte() should throw an exception if the value is not a byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isByte("abc")
            }
        }

        "isByte() should not throw an exception if the value is a byte" {
            CommanderValueValidators.isByte("1")
        }

        "isShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isShort(null)
            }
        }

        "isShort() should throw an exception if the value is not a shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isShort("abc")
            }
        }

        "isShort() should not throw an exception if the value is a shorts" {
            CommanderValueValidators.isShort("1")
        }

        "isInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isInt(null)
            }
        }

        "isInt() should throw an exception if the value is not a int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isInt("abc")
            }
        }

        "isInt() should not throw an exception if the value is a int" {
            CommanderValueValidators.isInt("1")
        }

        "isLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isLong(null)
            }
        }

        "isLong() should throw an exception if the value is not a long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isLong("abc")
            }
        }

        "isLong() should not throw an exception if the value is a long" {
            CommanderValueValidators.isLong("1")
        }

        "isFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isFloat(null)
            }
        }

        "isFloat() should throw an exception if the value is not a float" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isFloat("abc")
            }
        }

        "isFloat() should not throw an exception if the value is a float" {
            CommanderValueValidators.isFloat("1")
        }

        "isDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isDouble(null)
            }
        }

        "isDouble() should throw an exception if the value is not a double" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isDouble("abc")
            }
        }

        "isDouble() should not throw an exception if the value is a double" {
            CommanderValueValidators.isDouble("1")
        }

        "isBoolean() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isBoolean(null)
            }
        }

        "isBoolean() should throw an exception if the value is not a boolean" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isBoolean("abc")
            }
        }

        "isBoolean() should not throw an exception if the value is a boolean" {
            CommanderValueValidators.isBoolean("true")
        }

        "isBoolean() should interpret 1 as true" {
            CommanderValueValidators.isBoolean("1")
        }

        "isBoolean() should interpret 0 as false" {
            CommanderValueValidators.isBoolean("0")
        }

        "isBoolean() should interpret yes as true" {
            CommanderValueValidators.isBoolean("yes")
        }

        "isBoolean() should interpret no as false" {
            CommanderValueValidators.isBoolean("no")
        }

        "isBoolean() should interpret y as true" {
            CommanderValueValidators.isBoolean("y")
        }

        "isBoolean() should interpret n as false" {
            CommanderValueValidators.isBoolean("n")
        }

        "isBoolean() should interpret true as true" {
            CommanderValueValidators.isBoolean("true")
        }

        "isBoolean() should interpret false as false" {
            CommanderValueValidators.isBoolean("false")
        }

        "isChar() should accept a single character" {
            CommanderValueValidators.isChar("a")
        }

        "isChar() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isChar(null)
            }
        }

        "isChar() should throw an exception if the value is not a char" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isChar("abc")
            }
        }

        "isString() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isString(null)
            }
        }

        "isString() should not throw an exception if the value is a string" {
            CommanderValueValidators.isString("abc")
        }

        "isNull() should throw an exception if the value is not null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNull("abc")
            }
        }

        "isNull() should not throw an exception if the value is null" {
            CommanderValueValidators.isNull(null)
        }

        "isNotNull() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNotNull(null)
            }
        }

        "isNotNull() should not throw an exception if the value is not null" {
            CommanderValueValidators.isNotNull("abc")
        }

        "isUnsignedByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedByte(null)
            }
        }

        "isUnsignedByte() should throw an exception if the value is not a unsigned byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedByte("abc")
            }
        }

        "isUnsignedByte() should not throw an exception if the value is a unsigned byte" {
            CommanderValueValidators.isUnsignedByte("1")
        }

        "isUnsignedShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedShort(null)
            }
        }

        "isUnsignedShort() should throw an exception if the value is not a unsigned shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedShort("abc")
            }
        }

        "isUnsignedShort() should not throw an exception if the value is a unsigned shorts" {
            CommanderValueValidators.isUnsignedShort("1")
        }

        "isUnsignedInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedInt(null)
            }
        }

        "isUnsignedInt() should throw an exception if the value is not a unsigned int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedInt("abc")
            }
        }

        "isUnsignedInt() should not throw an exception if the value is a unsigned int" {
            CommanderValueValidators.isUnsignedInt("1")
        }

        "isUnsignedLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedLong(null)
            }
        }

        "isUnsignedLong() should throw an exception if the value is not a unsigned long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isUnsignedLong("abc")
            }
        }

        "isUnsignedLong() should not throw an exception if the value is a unsigned long" {
            CommanderValueValidators.isUnsignedLong("1")
        }

        "isPositiveByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveByte(null)
            }
        }

        "isPositiveByte() should throw an exception if the value is not a positive byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveByte("-1")
            }
        }

        "isPositiveByte() should not throw an exception if the value is a positive byte" {
            CommanderValueValidators.isPositiveByte("1")
        }

        "isPositiveShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveShort(null)
            }
        }

        "isPositiveShort() should throw an exception if the value is not a positive shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveShort("-1")
            }
        }

        "isPositiveShort() should not throw an exception if the value is a positive shorts" {
            CommanderValueValidators.isPositiveShort("1")
        }

        "isPositiveInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveInt(null)
            }
        }

        "isPositiveInt() should throw an exception if the value is not a positive int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveInt("-1")
            }
        }

        "isPositiveInt() should not throw an exception if the value is a positive int" {
            CommanderValueValidators.isPositiveInt("1")
        }

        "isPositiveLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveLong(null)
            }
        }

        "isPositiveLong() should throw an exception if the value is not a positive long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveLong("-1")
            }
        }

        "isPositiveLong() should not throw an exception if the value is a positive long" {
            CommanderValueValidators.isPositiveLong("1")
        }

        "isPositiveFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveFloat(null)
            }
        }

        "isPositiveFloat() should throw an exception if the value is not a positive float" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveFloat("-1")
            }
        }

        "isPositiveFloat() should not throw an exception if the value is a positive float" {
            CommanderValueValidators.isPositiveFloat("1")
        }

        "isPositiveDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveDouble(null)
            }
        }

        "isPositiveDouble() should throw an exception if the value is not a positive double" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositiveDouble("-1")
            }
        }

        "isPositiveDouble() should not throw an exception if the value is a positive double" {
            CommanderValueValidators.isPositiveDouble("1")
        }

        "isPositive() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositive(null)
            }
        }

        "isPositive() should throw an exception if the value is not a positive number" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isPositive("-1")
            }
        }

        "isPositive() should not throw an exception if the value is a positive number" {
            CommanderValueValidators.isPositive("1")
        }

        "isNegativeByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeByte(null)
            }
        }

        "isNegativeByte() should throw an exception if the value is not a negative byte" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeByte("1")
            }
        }

        "isNegativeByte() should not throw an exception if the value is a negative byte" {
            CommanderValueValidators.isNegativeByte("-1")
        }

        "isNegativeShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeShort(null)
            }
        }

        "isNegativeShort() should throw an exception if the value is not a negative shorts" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeShort("1")
            }
        }

        "isNegativeShort() should not throw an exception if the value is a negative shorts" {
            CommanderValueValidators.isNegativeShort("-1")
        }

        "isNegativeInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeInt(null)
            }
        }

        "isNegativeInt() should throw an exception if the value is not a negative int" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeInt("1")
            }
        }

        "isNegativeInt() should not throw an exception if the value is a negative int" {
            CommanderValueValidators.isNegativeInt("-1")
        }

        "isNegativeLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeLong(null)
            }
        }

        "isNegativeLong() should throw an exception if the value is not a negative long" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeLong("1")
            }
        }

        "isNegativeLong() should not throw an exception if the value is a negative long" {
            CommanderValueValidators.isNegativeLong("-1")
        }

        "isNegativeFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeFloat(null)
            }
        }

        "isNegativeFloat() should throw an exception if the value is not a negative float" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeFloat("1")
            }
        }

        "isNegativeFloat() should not throw an exception if the value is a negative float" {
            CommanderValueValidators.isNegativeFloat("-1")
        }

        "isNegativeDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeDouble(null)
            }
        }

        "isNegativeDouble() should throw an exception if the value is not a negative double" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegativeDouble("1")
            }
        }

        "isNegativeDouble() should not throw an exception if the value is a negative double" {
            CommanderValueValidators.isNegativeDouble("-1")
        }

        "isNegative() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegative(null)
            }
        }

        "isNegative() should throw an exception if the value is not a negative number" {
            shouldThrow<ValueValidationException> {
                CommanderValueValidators.isNegative("1")
            }
        }

        "isNegative() should not throw an exception if the value is a negative number" {
            CommanderValueValidators.isNegative("-1")
        }
    },
)
