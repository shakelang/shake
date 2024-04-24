package com.shakelang.util.commander

/**
 * A collection of common [CommanderValueValidator] implementations
 * @see CommanderValueValidator
 * @since 0.1.0
 * @version 0.1.0
 */
object CommanderValueValidators {

    /**
     * Check if the given [value] is a byte
     *
     * Accepts:
     * - Numbers between -128 and 127
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a byte")
    }

    /**
     * Check if the given [value] is a shorts
     *
     * Accepts:
     * - Numbers between -32768 and 32767
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a shorts
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a shorts")
    }

    /**
     * Check if the given [value] is an int
     *
     * Accepts:
     * - Numbers between -2147483648 and 2147483647
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not an int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a int")
    }

    /**
     * Check if the given [value] is a long
     *
     * Accepts:
     * - Numbers between -9223372036854775808 and 9223372036854775807
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a long")
    }

    /**
     * Check if the given [value] is a float
     *
     * Accepts:
     * - All numbers (positive and negative)
     * - E-Notation
     * - Infinity
     * - NaN
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isFloat(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a float")
    }

    /**
     * Check if the given [value] is a double
     *
     * Accepts:
     * - All numbers (positive and negative)
     * - E-Notation
     * - Infinity
     * - NaN
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isDouble(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a double")
    }

    /**
     * Check if the given [value] is a boolean
     *
     * Accepts:
     * - true >> true
     * - false >> false
     * - 1 >> true
     * - 0 >> false
     * - yes >> true
     * - no >> false
     * - y >> true
     * - n >> false
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a boolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isBoolean(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        when (value) {
            "true", "false", "1", "0", "yes", "no", "y", "n" -> {
                return
            }

            else -> throw ValueValidationException("CommanderValue \"$value\" is not a boolean")
        }
    }

    /**
     * Check if the given [value] is a char
     *
     * Accepts:
     * - All chars (every string with a length of 1)
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a boolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isChar(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.length != 1) throw ValueValidationException("CommanderValue \"$value\" is not a char")
    }

    /**
     * Check if the given [value] is a string
     *
     * Accepts:
     * - All strings (even empty ones)
     * (Accepts everything except null)
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a string
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isString(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
    }

    /**
     * Check if the given [value] is null
     *
     * Accepts:
     * - null
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNull(value: String?) {
        if (value != null) throw ValueValidationException("CommanderValue is not null")
    }

    /**
     * Check if the given [value] is not null
     *
     * Accepts:
     * - Everything except null
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNotNull(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
    }

    /**
     * Check if the given [value] is an unsigned byte
     *
     * Accepts:
     * - Numbers between 0 and 255
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not an unsigned byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toUByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned byte")
    }

    /**
     * Check if the given [value] is an unsigned shorts
     *
     * Accepts:
     * - Numbers between 0 and 65535
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not an unsigned shorts
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toUShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned shorts")
    }

    /**
     * Check if the given [value] is an unsigned int
     *
     * Accepts:
     * - Numbers between 0 and 4294967295
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not an unsigned int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toUIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned int")
    }

    /**
     * Check if the given [value] is an unsigned long
     *
     * Accepts:
     * - Numbers between 0 and 18446744073709551615
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not an unsigned long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isUnsignedLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toULongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned long")
    }

    /**
     * Check if the given [value] is a positive byte
     *
     * Accepts:
     * - Numbers between 0 and 127
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
        if (value.toByte() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
    }

    /**
     * Check if the given [value] is a positive shorts
     *
     * Accepts:
     * - Numbers between 0 and 32767
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive shorts
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive shorts")
        if (value.toShort() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive shorts")
    }

    /**
     * Check if the given [value] is a positive int
     *
     * Accepts:
     * - Numbers between 0 and 2147483647
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
        if (value.toInt() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
    }

    /**
     * Check if the given [value] is a positive long
     *
     * Accepts:
     * - Numbers between 0 and 9223372036854775807
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
        if (value.toLong() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
    }

    /**
     * Check if the given [value] is a positive float
     *
     * Accepts:
     * - Numbers between 0 and Infinity
     * - E-Notation
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveFloat(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
        if (value.toFloat() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
    }

    /**
     * Check if the given [value] is a positive double
     *
     * Accepts:
     * - Numbers between 0 and Infinity
     * - E-Notation
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositiveDouble(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
    }

    /**
     * Check if the given [value] is a positive number
     *
     * Accepts:
     * - Numbers between 0 and Infinity
     * - E-Notation
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a positive number
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isPositive(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
    }

    /**
     * Check if the given [value] is a negative byte
     *
     * Accepts:
     * - Numbers between -127 and 0
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
        if (value.toByte() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
    }

    /**
     * Check if the given [value] is a negative shorts
     *
     * Accepts:
     * - Numbers between -32768 and 0
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative shorts
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative shorts")
        if (value.toShort() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative shorts")
    }

    /**
     * Check if the given [value] is a negative int
     *
     * Accepts:
     * - Numbers between -2147483648 and 0
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
        if (value.toInt() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
    }

    /**
     * Check if the given [value] is a negative long
     *
     * Accepts:
     * - Numbers between -9223372036854775808 and 0
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
        if (value.toLong() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
    }

    /**
     * Check if the given [value] is a negative float
     *
     * Accepts:
     * - Numbers between -Infinity and 0
     * - E-Notation
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeFloat(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
        if (value.toFloat() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
    }

    /**
     * Check if the given [value] is a negative double
     *
     * Accepts:
     * - Numbers between -Infinity and 0
     * - E-Notation
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegativeDouble(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
    }

    /**
     * Check if the given [value] is a negative number
     *
     * Accepts:
     * - Numbers between -Infinity and 0
     * - E-Notation
     *
     * @param value The value to check
     * @throws ValueValidationException If the value is not a negative number
     * @since 0.1.0
     * @version 0.1.0
     */
    fun isNegative(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
    }

    /**
     * Check if the given value is a byte
     *
     * Accepts:
     * - Numbers between -128 and 127
     *
     * @throws ValueValidationException If the value is not a byte
     * @since 0.1.0
     * @version 0.1.0
     * @see isByte
     */
    val isByte: CommanderValueValidator = CommanderValueValidator.of(::isByte)

    /**
     * Check if the given value is a shorts
     *
     * Accepts:
     * - Numbers between -32768 and 32767
     *
     * @throws ValueValidationException If the value is not a shorts
     * @since 0.1.0
     * @version 0.1.0
     * @see isShort
     */
    val isShort: CommanderValueValidator = CommanderValueValidator.of(::isShort)

    /**
     * Check if the given value is an int
     *
     * Accepts:
     * - Numbers between -2147483648 and 2147483647
     *
     * @throws ValueValidationException If the value is not an int
     * @since 0.1.0
     * @version 0.1.0
     * @see isInt
     */
    val isInt: CommanderValueValidator = CommanderValueValidator.of(::isInt)

    /**
     * Check if the given value is a long
     *
     * Accepts:
     * - Numbers between -9223372036854775808 and 9223372036854775807
     *
     * @throws ValueValidationException If the value is not a long
     * @since 0.1.0
     * @version 0.1.0
     * @see isLong
     */
    val isLong: CommanderValueValidator = CommanderValueValidator.of(::isLong)

    /**
     * Check if the given value is a float
     *
     * Accepts:
     * - All numbers (positive and negative)
     * - E-Notation
     * - Infinity
     * - NaN
     *
     * @throws ValueValidationException If the value is not a float
     * @since 0.1.0
     * @version 0.1.0
     * @see isFloat
     */
    val isFloat: CommanderValueValidator = CommanderValueValidator.of(::isFloat)

    /**
     * Check if the given value is a double
     *
     * Accepts:
     * - All numbers (positive and negative)
     * - E-Notation
     * - Infinity
     * - NaN
     *
     * @throws ValueValidationException If the value is not a double
     * @since 0.1.0
     * @version 0.1.0
     * @see isDouble
     */
    val isDouble: CommanderValueValidator = CommanderValueValidator.of(::isDouble)

    /**
     * Check if the given value is a boolean
     *
     * Accepts:
     * - true >> true
     * - false >> false
     * - 1 >> true
     * - 0 >> false
     * - yes >> true
     * - no >> false
     * - y >> true
     * - n >> false
     *
     * @throws ValueValidationException If the value is not a boolean
     * @since 0.1.0
     * @version 0.1.0
     * @see isBoolean
     */
    val isBoolean: CommanderValueValidator = CommanderValueValidator.of(::isBoolean)

    /**
     * Check if the given value is a char
     *
     * Accepts:
     * - All chars (every string with a length of 1)
     *
     * @throws ValueValidationException If the value is not a boolean
     * @since 0.1.0
     * @version 0.1.0
     * @see isChar
     */
    val isChar: CommanderValueValidator = CommanderValueValidator.of(::isChar)

    /**
     * Check if the given value is a string
     *
     * Accepts:
     * - All strings (even empty ones)
     * (Accepts everything except null)
     *
     * @throws ValueValidationException If the value is not a string
     * @since 0.1.0
     * @version 0.1.0
     * @see isString
     */
    val isString: CommanderValueValidator = CommanderValueValidator.of(::isString)

    /**
     * Check if the given value is null
     *
     * Accepts:
     * - null
     *
     * @throws ValueValidationException If the value is not null
     * @since 0.1.0
     * @version 0.1.0
     * @see isNull
     */
    val isNull: CommanderValueValidator = CommanderValueValidator.of(::isNull)

    /**
     * Check if the given value is not null
     *
     * Accepts:
     * - Everything except null
     *
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     * @see isNotNull
     */
    val isNotNull: CommanderValueValidator = CommanderValueValidator.of(::isNotNull)

    /**
     * Check if the given value is an unsigned byte
     *
     * Accepts:
     * - Numbers between 0 and 255
     *
     * @throws ValueValidationException If the value is not an unsigned byte
     * @since 0.1.0
     * @version 0.1.0
     * @see isUnsignedByte
     */
    val isUnsignedByte: CommanderValueValidator = CommanderValueValidator.of(::isUnsignedByte)

    /**
     * Check if the given value is an unsigned shorts
     *
     * Accepts:
     * - Numbers between 0 and 65535
     *
     * @throws ValueValidationException If the value is not an unsigned shorts
     * @since 0.1.0
     * @version 0.1.0
     * @see isUnsignedShort
     */
    val isUnsignedShort: CommanderValueValidator = CommanderValueValidator.of(::isUnsignedShort)

    /**
     * Check if the given value is an unsigned int
     *
     * Accepts:
     * - Numbers between 0 and 4294967295
     *
     * @throws ValueValidationException If the value is not an unsigned int
     * @since 0.1.0
     * @version 0.1.0
     * @see isUnsignedInt
     */
    val isUnsignedInt: CommanderValueValidator = CommanderValueValidator.of(::isUnsignedInt)

    /**
     * Check if the given value is an unsigned long
     *
     * Accepts:
     * - Numbers between 0 and 18446744073709551615
     *
     * @throws ValueValidationException If the value is not an unsigned long
     * @since 0.1.0
     * @version 0.1.0
     * @see isUnsignedLong
     */
    val isUnsignedLong: CommanderValueValidator = CommanderValueValidator.of(::isUnsignedLong)

    /**
     * Check if the given value is a positive byte
     *
     * Accepts:
     * - Numbers between 0 and 127
     *
     * @throws ValueValidationException If the value is not a positive byte
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositiveByte
     */
    val isPositiveByte: CommanderValueValidator = CommanderValueValidator.of(::isPositiveByte)

    /**
     * Check if the given value is a positive shorts
     *
     * Accepts:
     * - Numbers between 0 and 32767
     *
     * @throws ValueValidationException If the value is not a positive shorts
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositiveShort
     */
    val isPositiveShort: CommanderValueValidator = CommanderValueValidator.of(::isPositiveShort)

    /**
     * Check if the given value is a positive int
     *
     * Accepts:
     * - Numbers between 0 and 2147483647
     *
     * @throws ValueValidationException If the value is not a positive int
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositiveInt
     */
    val isPositiveInt: CommanderValueValidator = CommanderValueValidator.of(::isPositiveInt)

    /**
     * Check if the given value is a positive long
     *
     * Accepts:
     * - Numbers between 0 and 9223372036854775807
     *
     * @throws ValueValidationException If the value is not a positive long
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositiveLong
     */
    val isPositiveLong: CommanderValueValidator = CommanderValueValidator.of(::isPositiveLong)

    /**
     * Check if the given value is a positive float
     *
     * Accepts:
     * - Numbers between zero and positive Infinity
     * - E-Notation
     *
     * @throws ValueValidationException If the value is not a positive float
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositiveFloat
     */
    val isPositiveFloat: CommanderValueValidator = CommanderValueValidator.of(::isPositiveFloat)

    /**
     * Check if the given value is a positive double
     *
     * Accepts:
     * - Numbers between zero and positive Infinity
     * - E-Notation
     *
     * @throws ValueValidationException If the value is not a positive double
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositiveDouble
     */
    val isPositiveDouble: CommanderValueValidator = CommanderValueValidator.of(::isPositiveDouble)

    /**
     * Check if the given value is a positive number
     *
     * Accepts:
     * - Numbers between zero and positive Infinity
     * - E-Notation
     *
     * @throws ValueValidationException If the value is not a positive number
     * @since 0.1.0
     * @version 0.1.0
     * @see isPositive
     */
    val isPositive: CommanderValueValidator = CommanderValueValidator.of(::isPositive)

    /**
     * Check if the given value is a negative byte
     *
     * Accepts:
     * - Numbers between -127 and 0
     *
     * @throws ValueValidationException If the value is not a negative byte
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegativeByte
     */
    val isNegativeByte: CommanderValueValidator = CommanderValueValidator.of(::isNegativeByte)

    /**
     * Check if the given value is a negative shorts
     *
     * Accepts:
     * - Numbers between -32768 and 0
     *
     * @throws ValueValidationException If the value is not a negative shorts
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegativeShort
     */
    val isNegativeShort: CommanderValueValidator = CommanderValueValidator.of(::isNegativeShort)

    /**
     * Check if the given value is a negative int
     *
     * Accepts:
     * - Numbers between -2147483648 and 0
     *
     * @throws ValueValidationException If the value is not a negative int
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegativeInt
     */
    val isNegativeInt: CommanderValueValidator = CommanderValueValidator.of(::isNegativeInt)

    /**
     * Check if the given value is a negative long
     *
     * Accepts:
     * - Numbers between -9223372036854775808 and 0
     *
     * @throws ValueValidationException If the value is not a negative long
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegativeLong
     */
    val isNegativeLong: CommanderValueValidator = CommanderValueValidator.of(::isNegativeLong)

    /**
     * Check if the given value is a negative float
     *
     * Accepts:
     * - Numbers between negative Infinity and 0
     * - E-Notation
     *
     * @throws ValueValidationException If the value is not a negative float
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegativeFloat
     */
    val isNegativeFloat: CommanderValueValidator = CommanderValueValidator.of(::isNegativeFloat)

    /**
     * Check if the given value is a negative double
     *
     * Accepts:
     * - Numbers between negative Infinity and 0
     * - E-Notation
     *
     * @throws ValueValidationException If the value is not a negative double
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegativeDouble
     */
    val isNegativeDouble: CommanderValueValidator = CommanderValueValidator.of(::isNegativeDouble)

    /**
     * Check if the given value is a negative number
     *
     * Accepts:
     * - Numbers between negative Infinity and 0
     * - E-Notation
     *
     * @throws ValueValidationException If the value is not a negative number
     * @since 0.1.0
     * @version 0.1.0
     * @see isNegative
     */
    val isNegative: CommanderValueValidator = CommanderValueValidator.of(::isNegative)
}
