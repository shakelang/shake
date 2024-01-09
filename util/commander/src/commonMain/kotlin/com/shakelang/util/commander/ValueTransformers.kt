package com.shakelang.util.commander

import kotlin.js.JsName

object ValueTransformers {
    fun toByte(value: String?): Byte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toByteOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a byte")
    }

    fun toByteOrNull(value: String?): Byte? {
        if (value == null) return null
        return value.toByteOrNull()
    }

    fun toShort(value: String?): Short {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toShortOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a short")
    }

    fun toShortOrNull(value: String?): Short? {
        if (value == null) return null
        return value.toShortOrNull()
    }

    fun toInt(value: String?): Int {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toIntOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a int")
    }

    fun toIntOrNull(value: String?): Int? {
        if (value == null) return null
        return value.toIntOrNull()
    }

    fun toLong(value: String?): Long {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toLongOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a long")
    }

    fun toLongOrNull(value: String?): Long? {
        if (value == null) return null
        return value.toLongOrNull()
    }

    fun toFloat(value: String?): Float {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toFloatOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a float")
    }

    fun toFloatOrNull(value: String?): Float? {
        if (value == null) return null
        return value.toFloatOrNull()
    }

    fun toDouble(value: String?): Double {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toDoubleOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a double")
    }

    fun toDoubleOrNull(value: String?): Double? {
        if (value == null) return null
        return value.toDoubleOrNull()
    }

    fun toNumber(value: String?): Number {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toDoubleOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a number")
    }

    fun toNumberOrNull(value: String?): Number? {
        if (value == null) return null
        return value.toDoubleOrNull()
    }

    fun toPositiveByte(value: String?): Byte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
        if (value.toByte() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
        return value.toByte()
    }

    fun toPositiveByteOrNull(value: String?): Byte? {
        if (value == null) return null
        if (value.toByteOrNull() == null) return null
        if (value.toByte() < 0) return null
        return value.toByte()
    }

    fun toPositiveShort(value: String?): Short {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive short")
        if (value.toShort() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive short")
        return value.toShort()
    }

    fun toPositiveShortOrNull(value: String?): Short? {
        if (value == null) return null
        if (value.toShortOrNull() == null) return null
        if (value.toShort() < 0) return null
        return value.toShort()
    }

    fun toPositiveInt(value: String?): Int {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
        if (value.toInt() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
        return value.toInt()
    }

    fun toPositiveIntOrNull(value: String?): Int? {
        if (value == null) return null
        if (value.toIntOrNull() == null) return null
        if (value.toInt() < 0) return null
        return value.toInt()
    }

    fun toPositiveLong(value: String?): Long {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
        if (value.toLong() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
        return value.toLong()
    }

    fun toPositiveLongOrNull(value: String?): Long? {
        if (value == null) return null
        if (value.toLongOrNull() == null) return null
        if (value.toLong() < 0) return null
        return value.toLong()
    }

    fun toPositiveFloat(value: String?): Float {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
        if (value.toFloat() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
        return value.toFloat()
    }

    fun toPositiveFloatOrNull(value: String?): Float? {
        if (value == null) return null
        if (value.toFloatOrNull() == null) return null
        if (value.toFloat() < 0) return null
        return value.toFloat()
    }

    fun toPositiveDouble(value: String?): Double {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
        return value.toDouble()
    }

    fun toPositiveDoubleOrNull(value: String?): Double? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() < 0) return null
        return value.toDouble()
    }

    fun toPositive(value: String?): Number {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
        return value.toDouble()
    }

    fun toPositiveOrNull(value: String?): Number? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() < 0) return null
        return value.toDouble()
    }

    fun toNegativeByte(value: String?): Byte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
        if (value.toByte() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
        return value.toByte()
    }

    fun toNegativeByteOrNull(value: String?): Byte? {
        if (value == null) return null
        if (value.toByteOrNull() == null) return null
        if (value.toByte() > 0) return null
        return value.toByte()
    }

    fun toNegativeShort(value: String?): Short {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative short")
        if (value.toShort() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative short")
        return value.toShort()
    }

    fun toNegativeShortOrNull(value: String?): Short? {
        if (value == null) return null
        if (value.toShortOrNull() == null) return null
        if (value.toShort() > 0) return null
        return value.toShort()
    }

    fun toNegativeInt(value: String?): Int {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
        if (value.toInt() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
        return value.toInt()
    }

    fun toNegativeIntOrNull(value: String?): Int? {
        if (value == null) return null
        if (value.toIntOrNull() == null) return null
        if (value.toInt() > 0) return null
        return value.toInt()
    }

    fun toNegativeLong(value: String?): Long {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
        if (value.toLong() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
        return value.toLong()
    }

    fun toNegativeLongOrNull(value: String?): Long? {
        if (value == null) return null
        if (value.toLongOrNull() == null) return null
        if (value.toLong() > 0) return null
        return value.toLong()
    }

    fun toNegativeFloat(value: String?): Float {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
        if (value.toFloat() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
        return value.toFloat()
    }

    fun toNegativeFloatOrNull(value: String?): Float? {
        if (value == null) return null
        if (value.toFloatOrNull() == null) return null
        if (value.toFloat() > 0) return null
        return value.toFloat()
    }

    fun toNegativeDouble(value: String?): Double {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
        return value.toDouble()
    }

    fun toNegativeDoubleOrNull(value: String?): Double? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() > 0) return null
        return value.toDouble()
    }

    fun toNegative(value: String?): Number {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
        return value.toDouble()
    }

    fun toNegativeOrNull(value: String?): Number? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() > 0) return null
        return value.toDouble()
    }

    fun toBoolean(value: String?): Boolean {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return when (value) {
            "true", "yes", "y", "1" -> true
            "false", "no", "n", "0" -> false
            else -> throw ValueValidationException("CommanderValue \"$value\" is not a boolean")
        }
    }

    fun toBooleanOrNull(value: String?): Boolean? {
        if (value == null) return null
        return when (value) {
            "true", "yes", "y", "1" -> true
            "false", "no", "n", "0" -> false
            else -> null
        }
    }

    fun toChar(value: String?): Char {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.length != 1) throw ValueValidationException("CommanderValue \"$value\" is not a char")
        return value[0]
    }

    fun toCharOrNull(value: String?): Char? {
        if (value == null) return null
        if (value.length != 1) return null
        return value[0]
    }

    fun toString(value: String?): String {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value
    }

    fun toStringOrNull(value: String?): String? {
        if (value == null) return null
        return value
    }

    fun toNull(value: String?): String? {
        if (value != null) throw ValueValidationException("CommanderValue is not null")
        return null
    }

    fun toNotNull(value: String?): String {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value
    }

    fun toUnsignedByte(value: String?): UByte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toUByteOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned byte")
    }

    fun toUnsignedByteOrNull(value: String?): UByte? {
        if (value == null) return null
        return value.toUByteOrNull()
    }

    fun toUnsignedShort(value: String?): UShort {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toUShortOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned short")
    }

    fun toUnsignedShortOrNull(value: String?): UShort? {
        if (value == null) return null
        return value.toUShortOrNull()
    }

    fun toUnsignedInt(value: String?): UInt {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toUIntOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned int")
    }

    fun toUnsignedIntOrNull(value: String?): UInt? {
        if (value == null) return null
        return value.toUIntOrNull()
    }

    fun toUnsignedLong(value: String?): ULong {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toULongOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned long")
    }

    fun toUnsignedLongOrNull(value: String?): ULong? {
        if (value == null) return null
        return value.toULongOrNull()
    }

    val toByte: CommanderNullableValueTransformer<Byte> = ::toByte
    val toByteOrNull: CommanderNullableValueTransformer<Byte?> = ::toByteOrNull
    val toShort: CommanderNullableValueTransformer<Short> = ::toShort
    val toShortOrNull: CommanderNullableValueTransformer<Short?> = ::toShortOrNull
    val toInt: CommanderNullableValueTransformer<Int> = ::toInt
    val toIntOrNull: CommanderNullableValueTransformer<Int?> = ::toIntOrNull
    val toLong: CommanderNullableValueTransformer<Long> = ::toLong
    val toLongOrNull: CommanderNullableValueTransformer<Long?> = ::toLongOrNull
    val toFloat: CommanderNullableValueTransformer<Float> = ::toFloat
    val toFloatOrNull: CommanderNullableValueTransformer<Float?> = ::toFloatOrNull
    val toDouble: CommanderNullableValueTransformer<Double> = ::toDouble
    val toDoubleOrNull: CommanderNullableValueTransformer<Double?> = ::toDoubleOrNull
    val toNumber: CommanderNullableValueTransformer<Number> = ::toNumber
    val toNumberOrNull: CommanderNullableValueTransformer<Number?> = ::toNumberOrNull
    val toPositiveByte: CommanderNullableValueTransformer<Byte> = ::toPositiveByte
    val toPositiveByteOrNull: CommanderNullableValueTransformer<Byte?> = ::toPositiveByteOrNull
    val toPositiveShort: CommanderNullableValueTransformer<Short> = ::toPositiveShort
    val toPositiveShortOrNull: CommanderNullableValueTransformer<Short?> = ::toPositiveShortOrNull
    val toPositiveInt: CommanderNullableValueTransformer<Int> = ::toPositiveInt
    val toPositiveIntOrNull: CommanderNullableValueTransformer<Int?> = ::toPositiveIntOrNull
    val toPositiveLong: CommanderNullableValueTransformer<Long> = ::toPositiveLong
    val toPositiveLongOrNull: CommanderNullableValueTransformer<Long?> = ::toPositiveLongOrNull
    val toPositiveFloat: CommanderNullableValueTransformer<Float> = ::toPositiveFloat
    val toPositiveFloatOrNull: CommanderNullableValueTransformer<Float?> = ::toPositiveFloatOrNull
    val toPositiveDouble: CommanderNullableValueTransformer<Double> = ::toPositiveDouble
    val toPositiveDoubleOrNull: CommanderNullableValueTransformer<Double?> = ::toPositiveDoubleOrNull
    val toPositive: CommanderNullableValueTransformer<Number> = ::toPositive
    val toPositiveOrNull: CommanderNullableValueTransformer<Number?> = ::toPositiveOrNull
    val toNegativeByte: CommanderNullableValueTransformer<Byte> = ::toNegativeByte
    val toNegativeByteOrNull: CommanderNullableValueTransformer<Byte?> = ::toNegativeByteOrNull
    val toNegativeShort: CommanderNullableValueTransformer<Short> = ::toNegativeShort
    val toNegativeShortOrNull: CommanderNullableValueTransformer<Short?> = ::toNegativeShortOrNull
    val toNegativeInt: CommanderNullableValueTransformer<Int> = ::toNegativeInt
    val toNegativeIntOrNull: CommanderNullableValueTransformer<Int?> = ::toNegativeIntOrNull
    val toNegativeLong: CommanderNullableValueTransformer<Long> = ::toNegativeLong
    val toNegativeLongOrNull: CommanderNullableValueTransformer<Long?> = ::toNegativeLongOrNull
    val toNegativeFloat: CommanderNullableValueTransformer<Float> = ::toNegativeFloat
    val toNegativeFloatOrNull: CommanderNullableValueTransformer<Float?> = ::toNegativeFloatOrNull
    val toNegativeDouble: CommanderNullableValueTransformer<Double> = ::toNegativeDouble
    val toNegativeDoubleOrNull: CommanderNullableValueTransformer<Double?> = ::toNegativeDoubleOrNull
    val toNegative: CommanderNullableValueTransformer<Number> = ::toNegative
    val toNegativeOrNull: CommanderNullableValueTransformer<Number?> = ::toNegativeOrNull
    val toBoolean: CommanderNullableValueTransformer<Boolean> = ::toBoolean
    val toBooleanOrNull: CommanderNullableValueTransformer<Boolean?> = ::toBooleanOrNull
    val toChar: CommanderNullableValueTransformer<Char> = ::toChar
    val toCharOrNull: CommanderNullableValueTransformer<Char?> = ::toCharOrNull

    @JsName("toStringLambda")
    val toString: CommanderNullableValueTransformer<String> = ::toString
    val toStringOrNull: CommanderNullableValueTransformer<String?> = ::toStringOrNull
    val toNull: CommanderNullableValueTransformer<String?> = ::toNull
    val toNotNull: CommanderNullableValueTransformer<String> = ::toNotNull
    val toUnsignedByte: CommanderNullableValueTransformer<UByte> = ::toUnsignedByte
    val toUnsignedByteOrNull: CommanderNullableValueTransformer<UByte?> = ::toUnsignedByteOrNull
    val toUnsignedShort: CommanderNullableValueTransformer<UShort> = ::toUnsignedShort
    val toUnsignedShortOrNull: CommanderNullableValueTransformer<UShort?> = ::toUnsignedShortOrNull
    val toUnsignedInt: CommanderNullableValueTransformer<UInt> = ::toUnsignedInt
    val toUnsignedIntOrNull: CommanderNullableValueTransformer<UInt?> = ::toUnsignedIntOrNull
    val toUnsignedLong: CommanderNullableValueTransformer<ULong> = ::toUnsignedLong
    val toUnsignedLongOrNull: CommanderNullableValueTransformer<ULong?> = ::toUnsignedLongOrNull
}
