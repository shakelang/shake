package com.shakelang.util.commander

object CommanderValidators {

    fun isByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a byte")
    }

    fun isShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a short")
    }

    fun isInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a int")
    }

    fun isLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a long")
    }

    fun isFloat(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a float")
    }

    fun isDouble(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a double")
    }

    fun isBoolean(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        when (value) {
            "true", "false", "1", "0", "yes", "no", "y", "n" -> {
                return
            }

            else -> throw ValueValidationException("CommanderValue \"$value\" is not a boolean")
        }
    }

    fun isString(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
    }

    fun isNull(value: String?) {
        if (value != null) throw ValueValidationException("CommanderValue is not null")
    }

    fun isNotNull(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
    }

    fun isUnsignedByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toUByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned byte")
    }

    fun isUnsignedShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toUShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned short")
    }

    fun isUnsignedInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toUIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned int")
    }

    fun isUnsignedLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toULongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a unsigned long")
    }

    fun isPositiveByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
        if (value.toByte() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
    }

    fun isPositiveShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive short")
        if (value.toShort() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive short")
    }

    fun isPositiveInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
        if (value.toInt() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
    }

    fun isPositiveLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
        if (value.toLong() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
    }

    fun isPositiveFloat(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
        if (value.toFloat() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
    }

    fun isPositiveDouble(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
    }

    fun isPositive(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
    }

    fun isNegativeByte(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
        if (value.toByte() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
    }

    fun isNegativeShort(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative short")
        if (value.toShort() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative short")
    }

    fun isNegativeInt(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
        if (value.toInt() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
    }

    fun isNegativeLong(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
        if (value.toLong() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
    }

    fun isNegativeFloat(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
        if (value.toFloat() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
    }

    fun isNegativeDouble(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
    }

    fun isNegative(value: String?) {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
    }

    val isByte: CommanderValueValidator = ::isByte
    val isShort: CommanderValueValidator = ::isShort
    val isInt: CommanderValueValidator = ::isInt
    val isLong: CommanderValueValidator = ::isLong
    val isFloat: CommanderValueValidator = ::isFloat
    val isDouble: CommanderValueValidator = ::isDouble
    val isBoolean: CommanderValueValidator = ::isBoolean
    val isString: CommanderValueValidator = ::isString
    val isNull: CommanderValueValidator = ::isNull
    val isNotNull: CommanderValueValidator = ::isNotNull
    val isUnsignedByte: CommanderValueValidator = ::isUnsignedByte
    val isUnsignedShort: CommanderValueValidator = ::isUnsignedShort
    val isUnsignedInt: CommanderValueValidator = ::isUnsignedInt
    val isUnsignedLong: CommanderValueValidator = ::isUnsignedLong
    val isPositiveByte: CommanderValueValidator = ::isPositiveByte
    val isPositiveShort: CommanderValueValidator = ::isPositiveShort
    val isPositiveInt: CommanderValueValidator = ::isPositiveInt
    val isPositiveLong: CommanderValueValidator = ::isPositiveLong
    val isPositiveFloat: CommanderValueValidator = ::isPositiveFloat
    val isPositiveDouble: CommanderValueValidator = ::isPositiveDouble
    val isPositive: CommanderValueValidator = ::isPositive
    val isNegativeByte: CommanderValueValidator = ::isNegativeByte
    val isNegativeShort: CommanderValueValidator = ::isNegativeShort
    val isNegativeInt: CommanderValueValidator = ::isNegativeInt
    val isNegativeLong: CommanderValueValidator = ::isNegativeLong
    val isNegativeFloat: CommanderValueValidator = ::isNegativeFloat
    val isNegativeDouble: CommanderValueValidator = ::isNegativeDouble
    val isNegative: CommanderValueValidator = ::isNegative
}
