package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class ValidatorFunctionTests : FreeSpec({

    "isByte() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isByte(null)
        }
    }

    "isByte() should throw an exception if the value is not a byte" {
        shouldThrow<ValueValidationException> {
            Validators.isByte("abc")
        }
    }

    "isByte() should not throw an exception if the value is a byte" {
        Validators.isByte("1")
    }

    "isShort() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isShort(null)
        }
    }

    "isShort() should throw an exception if the value is not a short" {
        shouldThrow<ValueValidationException> {
            Validators.isShort("abc")
        }
    }

    "isShort() should not throw an exception if the value is a short" {
        Validators.isShort("1")
    }

    "isInt() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isInt(null)
        }
    }

    "isInt() should throw an exception if the value is not a int" {
        shouldThrow<ValueValidationException> {
            Validators.isInt("abc")
        }
    }

    "isInt() should not throw an exception if the value is a int" {
        Validators.isInt("1")
    }

    "isLong() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isLong(null)
        }
    }

    "isLong() should throw an exception if the value is not a long" {
        shouldThrow<ValueValidationException> {
            Validators.isLong("abc")
        }
    }

    "isLong() should not throw an exception if the value is a long" {
        Validators.isLong("1")
    }

    "isFloat() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isFloat(null)
        }
    }

    "isFloat() should throw an exception if the value is not a float" {
        shouldThrow<ValueValidationException> {
            Validators.isFloat("abc")
        }
    }

    "isFloat() should not throw an exception if the value is a float" {
        Validators.isFloat("1")
    }

    "isDouble() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isDouble(null)
        }
    }

    "isDouble() should throw an exception if the value is not a double" {
        shouldThrow<ValueValidationException> {
            Validators.isDouble("abc")
        }
    }

    "isDouble() should not throw an exception if the value is a double" {
        Validators.isDouble("1")
    }

    "isBoolean() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isBoolean(null)
        }
    }

    "isBoolean() should throw an exception if the value is not a boolean" {
        shouldThrow<ValueValidationException> {
            Validators.isBoolean("abc")
        }
    }

    "isBoolean() should not throw an exception if the value is a boolean" {
        Validators.isBoolean("true")
    }

    "isBoolean() should interpret 1 as true" {
        Validators.isBoolean("1")
    }

    "isBoolean() should interpret 0 as false" {
        Validators.isBoolean("0")
    }

    "isBoolean() should interpret yes as true" {
        Validators.isBoolean("yes")
    }

    "isBoolean() should interpret no as false" {
        Validators.isBoolean("no")
    }

    "isBoolean() should interpret y as true" {
        Validators.isBoolean("y")
    }

    "isBoolean() should interpret n as false" {
        Validators.isBoolean("n")
    }

    "isBoolean() should interpret true as true" {
        Validators.isBoolean("true")
    }

    "isBoolean() should interpret false as false" {
        Validators.isBoolean("false")
    }

    "isString() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isString(null)
        }
    }

    "isString() should not throw an exception if the value is a string" {
        Validators.isString("abc")
    }

    "isNull() should throw an exception if the value is not null" {
        shouldThrow<ValueValidationException> {
            Validators.isNull("abc")
        }
    }

    "isNull() should not throw an exception if the value is null" {
        Validators.isNull(null)
    }

    "isNotNull() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNotNull(null)
        }
    }

    "isNotNull() should not throw an exception if the value is not null" {
        Validators.isNotNull("abc")
    }

    "isUnsignedByte() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedByte(null)
        }
    }

    "isUnsignedByte() should throw an exception if the value is not a unsigned byte" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedByte("abc")
        }
    }

    "isUnsignedByte() should not throw an exception if the value is a unsigned byte" {
        Validators.isUnsignedByte("1")
    }

    "isUnsignedShort() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedShort(null)
        }
    }

    "isUnsignedShort() should throw an exception if the value is not a unsigned short" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedShort("abc")
        }
    }

    "isUnsignedShort() should not throw an exception if the value is a unsigned short" {
        Validators.isUnsignedShort("1")
    }

    "isUnsignedInt() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedInt(null)
        }
    }

    "isUnsignedInt() should throw an exception if the value is not a unsigned int" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedInt("abc")
        }
    }

    "isUnsignedInt() should not throw an exception if the value is a unsigned int" {
        Validators.isUnsignedInt("1")
    }

    "isUnsignedLong() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedLong(null)
        }
    }

    "isUnsignedLong() should throw an exception if the value is not a unsigned long" {
        shouldThrow<ValueValidationException> {
            Validators.isUnsignedLong("abc")
        }
    }

    "isUnsignedLong() should not throw an exception if the value is a unsigned long" {
        Validators.isUnsignedLong("1")
    }

    "isPositiveByte() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveByte(null)
        }
    }

    "isPositiveByte() should throw an exception if the value is not a positive byte" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveByte("-1")
        }
    }

    "isPositiveByte() should not throw an exception if the value is a positive byte" {
        Validators.isPositiveByte("1")
    }

    "isPositiveShort() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveShort(null)
        }
    }

    "isPositiveShort() should throw an exception if the value is not a positive short" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveShort("-1")
        }
    }

    "isPositiveShort() should not throw an exception if the value is a positive short" {
        Validators.isPositiveShort("1")
    }

    "isPositiveInt() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveInt(null)
        }
    }

    "isPositiveInt() should throw an exception if the value is not a positive int" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveInt("-1")
        }
    }

    "isPositiveInt() should not throw an exception if the value is a positive int" {
        Validators.isPositiveInt("1")
    }

    "isPositiveLong() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveLong(null)
        }
    }

    "isPositiveLong() should throw an exception if the value is not a positive long" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveLong("-1")
        }
    }

    "isPositiveLong() should not throw an exception if the value is a positive long" {
        Validators.isPositiveLong("1")
    }

    "isPositiveFloat() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveFloat(null)
        }
    }

    "isPositiveFloat() should throw an exception if the value is not a positive float" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveFloat("-1")
        }
    }

    "isPositiveFloat() should not throw an exception if the value is a positive float" {
        Validators.isPositiveFloat("1")
    }

    "isPositiveDouble() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveDouble(null)
        }
    }

    "isPositiveDouble() should throw an exception if the value is not a positive double" {
        shouldThrow<ValueValidationException> {
            Validators.isPositiveDouble("-1")
        }
    }

    "isPositiveDouble() should not throw an exception if the value is a positive double" {
        Validators.isPositiveDouble("1")
    }

    "isPositive() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isPositive(null)
        }
    }

    "isPositive() should throw an exception if the value is not a positive number" {
        shouldThrow<ValueValidationException> {
            Validators.isPositive("-1")
        }
    }

    "isPositive() should not throw an exception if the value is a positive number" {
        Validators.isPositive("1")
    }

    "isNegativeByte() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeByte(null)
        }
    }

    "isNegativeByte() should throw an exception if the value is not a negative byte" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeByte("1")
        }
    }

    "isNegativeByte() should not throw an exception if the value is a negative byte" {
        Validators.isNegativeByte("-1")
    }

    "isNegativeShort() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeShort(null)
        }
    }

    "isNegativeShort() should throw an exception if the value is not a negative short" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeShort("1")
        }
    }

    "isNegativeShort() should not throw an exception if the value is a negative short" {
        Validators.isNegativeShort("-1")
    }

    "isNegativeInt() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeInt(null)
        }
    }

    "isNegativeInt() should throw an exception if the value is not a negative int" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeInt("1")
        }
    }

    "isNegativeInt() should not throw an exception if the value is a negative int" {
        Validators.isNegativeInt("-1")
    }

    "isNegativeLong() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeLong(null)
        }
    }

    "isNegativeLong() should throw an exception if the value is not a negative long" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeLong("1")
        }
    }

    "isNegativeLong() should not throw an exception if the value is a negative long" {
        Validators.isNegativeLong("-1")
    }

    "isNegativeFloat() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeFloat(null)
        }
    }

    "isNegativeFloat() should throw an exception if the value is not a negative float" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeFloat("1")
        }
    }

    "isNegativeFloat() should not throw an exception if the value is a negative float" {
        Validators.isNegativeFloat("-1")
    }

    "isNegativeDouble() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeDouble(null)
        }
    }

    "isNegativeDouble() should throw an exception if the value is not a negative double" {
        shouldThrow<ValueValidationException> {
            Validators.isNegativeDouble("1")
        }
    }

    "isNegativeDouble() should not throw an exception if the value is a negative double" {
        Validators.isNegativeDouble("-1")
    }

    "isNegative() should throw an exception if the value is null" {
        shouldThrow<ValueValidationException> {
            Validators.isNegative(null)
        }
    }

    "isNegative() should throw an exception if the value is not a negative number" {
        shouldThrow<ValueValidationException> {
            Validators.isNegative("1")
        }
    }

    "isNegative() should not throw an exception if the value is a negative number" {
        Validators.isNegative("-1")
    }
})
