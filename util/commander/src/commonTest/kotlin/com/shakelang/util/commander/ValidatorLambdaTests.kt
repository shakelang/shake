package com.shakelang.util.commander

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

class ValidatorLambdaTests : FreeSpec(
    {

        "isByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isByte.invoke(null)
            }
        }

        "isByte() should throw an exception if the value is not a byte" {
            shouldThrow<ValueValidationException> {
                Validators.isByte.invoke("abc")
            }
        }

        "isByte() should not throw an exception if the value is a byte" {
            Validators.isByte.invoke("1")
        }

        "isShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isShort.invoke(null)
            }
        }

        "isShort() should throw an exception if the value is not a short" {
            shouldThrow<ValueValidationException> {
                Validators.isShort.invoke("abc")
            }
        }

        "isShort() should not throw an exception if the value is a short" {
            Validators.isShort.invoke("1")
        }

        "isInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isInt.invoke(null)
            }
        }

        "isInt() should throw an exception if the value is not a int" {
            shouldThrow<ValueValidationException> {
                Validators.isInt.invoke("abc")
            }
        }

        "isInt() should not throw an exception if the value is a int" {
            Validators.isInt.invoke("1")
        }

        "isLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isLong.invoke(null)
            }
        }

        "isLong() should throw an exception if the value is not a long" {
            shouldThrow<ValueValidationException> {
                Validators.isLong.invoke("abc")
            }
        }

        "isLong() should not throw an exception if the value is a long" {
            Validators.isLong.invoke("1")
        }

        "isFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isFloat.invoke(null)
            }
        }

        "isFloat() should throw an exception if the value is not a float" {
            shouldThrow<ValueValidationException> {
                Validators.isFloat.invoke("abc")
            }
        }

        "isFloat() should not throw an exception if the value is a float" {
            Validators.isFloat.invoke("1")
        }

        "isDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isDouble.invoke(null)
            }
        }

        "isDouble() should throw an exception if the value is not a double" {
            shouldThrow<ValueValidationException> {
                Validators.isDouble.invoke("abc")
            }
        }

        "isDouble() should not throw an exception if the value is a double" {
            Validators.isDouble.invoke("1")
        }

        "isBoolean() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isBoolean.invoke(null)
            }
        }

        "isBoolean() should throw an exception if the value is not a boolean" {
            shouldThrow<ValueValidationException> {
                Validators.isBoolean.invoke("abc")
            }
        }

        "isBoolean() should not throw an exception if the value is a boolean" {
            Validators.isBoolean.invoke("true")
        }

        "isBoolean() should interpret 1 as true" {
            Validators.isBoolean.invoke("1")
        }

        "isBoolean() should interpret 0 as false" {
            Validators.isBoolean.invoke("0")
        }

        "isBoolean() should interpret yes as true" {
            Validators.isBoolean.invoke("yes")
        }

        "isBoolean() should interpret no as false" {
            Validators.isBoolean.invoke("no")
        }

        "isBoolean() should interpret y as true" {
            Validators.isBoolean.invoke("y")
        }

        "isBoolean() should interpret n as false" {
            Validators.isBoolean.invoke("n")
        }

        "isBoolean() should interpret true as true" {
            Validators.isBoolean.invoke("true")
        }

        "isBoolean() should interpret false as false" {
            Validators.isBoolean.invoke("false")
        }

        "isString() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isString.invoke(null)
            }
        }

        "isString() should not throw an exception if the value is a string" {
            Validators.isString.invoke("abc")
        }

        "isNull() should throw an exception if the value is not null" {
            shouldThrow<ValueValidationException> {
                Validators.isNull.invoke("abc")
            }
        }

        "isNull() should not throw an exception if the value is null" {
            Validators.isNull.invoke(null)
        }

        "isNotNull() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNotNull.invoke(null)
            }
        }

        "isNotNull() should not throw an exception if the value is not null" {
            Validators.isNotNull.invoke("abc")
        }

        "isUnsignedByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedByte.invoke(null)
            }
        }

        "isUnsignedByte() should throw an exception if the value is not a unsigned byte" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedByte.invoke("abc")
            }
        }

        "isUnsignedByte() should not throw an exception if the value is a unsigned byte" {
            Validators.isUnsignedByte.invoke("1")
        }

        "isUnsignedShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedShort.invoke(null)
            }
        }

        "isUnsignedShort() should throw an exception if the value is not a unsigned short" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedShort.invoke("abc")
            }
        }

        "isUnsignedShort() should not throw an exception if the value is a unsigned short" {
            Validators.isUnsignedShort.invoke("1")
        }

        "isUnsignedInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedInt.invoke(null)
            }
        }

        "isUnsignedInt() should throw an exception if the value is not a unsigned int" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedInt.invoke("abc")
            }
        }

        "isUnsignedInt() should not throw an exception if the value is a unsigned int" {
            Validators.isUnsignedInt.invoke("1")
        }

        "isUnsignedLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedLong.invoke(null)
            }
        }

        "isUnsignedLong() should throw an exception if the value is not a unsigned long" {
            shouldThrow<ValueValidationException> {
                Validators.isUnsignedLong.invoke("abc")
            }
        }

        "isUnsignedLong() should not throw an exception if the value is a unsigned long" {
            Validators.isUnsignedLong.invoke("1")
        }

        "isPositiveByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveByte.invoke(null)
            }
        }

        "isPositiveByte() should throw an exception if the value is not a positive byte" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveByte.invoke("-1")
            }
        }

        "isPositiveByte() should not throw an exception if the value is a positive byte" {
            Validators.isPositiveByte.invoke("1")
        }

        "isPositiveShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveShort.invoke(null)
            }
        }

        "isPositiveShort() should throw an exception if the value is not a positive short" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveShort.invoke("-1")
            }
        }

        "isPositiveShort() should not throw an exception if the value is a positive short" {
            Validators.isPositiveShort.invoke("1")
        }

        "isPositiveInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveInt.invoke(null)
            }
        }

        "isPositiveInt() should throw an exception if the value is not a positive int" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveInt.invoke("-1")
            }
        }

        "isPositiveInt() should not throw an exception if the value is a positive int" {
            Validators.isPositiveInt.invoke("1")
        }

        "isPositiveLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveLong.invoke(null)
            }
        }

        "isPositiveLong() should throw an exception if the value is not a positive long" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveLong.invoke("-1")
            }
        }

        "isPositiveLong() should not throw an exception if the value is a positive long" {
            Validators.isPositiveLong.invoke("1")
        }

        "isPositiveFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveFloat.invoke(null)
            }
        }

        "isPositiveFloat() should throw an exception if the value is not a positive float" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveFloat.invoke("-1")
            }
        }

        "isPositiveFloat() should not throw an exception if the value is a positive float" {
            Validators.isPositiveFloat.invoke("1")
        }

        "isPositiveDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveDouble.invoke(null)
            }
        }

        "isPositiveDouble() should throw an exception if the value is not a positive double" {
            shouldThrow<ValueValidationException> {
                Validators.isPositiveDouble.invoke("-1")
            }
        }

        "isPositiveDouble() should not throw an exception if the value is a positive double" {
            Validators.isPositiveDouble.invoke("1")
        }

        "isPositive() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isPositive.invoke(null)
            }
        }

        "isPositive() should throw an exception if the value is not a positive number" {
            shouldThrow<ValueValidationException> {
                Validators.isPositive.invoke("-1")
            }
        }

        "isPositive() should not throw an exception if the value is a positive number" {
            Validators.isPositive.invoke("1")
        }

        "isNegativeByte() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeByte.invoke(null)
            }
        }

        "isNegativeByte() should throw an exception if the value is not a negative byte" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeByte.invoke("1")
            }
        }

        "isNegativeByte() should not throw an exception if the value is a negative byte" {
            Validators.isNegativeByte.invoke("-1")
        }

        "isNegativeShort() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeShort.invoke(null)
            }
        }

        "isNegativeShort() should throw an exception if the value is not a negative short" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeShort.invoke("1")
            }
        }

        "isNegativeShort() should not throw an exception if the value is a negative short" {
            Validators.isNegativeShort.invoke("-1")
        }

        "isNegativeInt() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeInt.invoke(null)
            }
        }

        "isNegativeInt() should throw an exception if the value is not a negative int" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeInt.invoke("1")
            }
        }

        "isNegativeInt() should not throw an exception if the value is a negative int" {
            Validators.isNegativeInt.invoke("-1")
        }

        "isNegativeLong() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeLong.invoke(null)
            }
        }

        "isNegativeLong() should throw an exception if the value is not a negative long" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeLong.invoke("1")
            }
        }

        "isNegativeLong() should not throw an exception if the value is a negative long" {
            Validators.isNegativeLong.invoke("-1")
        }

        "isNegativeFloat() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeFloat.invoke(null)
            }
        }

        "isNegativeFloat() should throw an exception if the value is not a negative float" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeFloat.invoke("1")
            }
        }

        "isNegativeFloat() should not throw an exception if the value is a negative float" {
            Validators.isNegativeFloat.invoke("-1")
        }

        "isNegativeDouble() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeDouble.invoke(null)
            }
        }

        "isNegativeDouble() should throw an exception if the value is not a negative double" {
            shouldThrow<ValueValidationException> {
                Validators.isNegativeDouble.invoke("1")
            }
        }

        "isNegativeDouble() should not throw an exception if the value is a negative double" {
            Validators.isNegativeDouble.invoke("-1")
        }

        "isNegative() should throw an exception if the value is null" {
            shouldThrow<ValueValidationException> {
                Validators.isNegative.invoke(null)
            }
        }

        "isNegative() should throw an exception if the value is not a negative number" {
            shouldThrow<ValueValidationException> {
                Validators.isNegative.invoke("1")
            }
        }

        "isNegative() should not throw an exception if the value is a negative number" {
            Validators.isNegative.invoke("-1")
        }
    },
)
