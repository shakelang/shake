package com.shakelang.util.commander

import kotlin.js.JsName

object ValueTransformers {
    fun toByte(value: String?): Byte {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toByteOrNull() ?: throw ValueValidationException("Value \"$value\" is not a byte")
    }

    fun toByteOrNull(value: String?): Byte? {
        if(value == null) return null
        return value.toByteOrNull()
    }

    fun toShort(value: String?): Short {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toShortOrNull() ?: throw ValueValidationException("Value \"$value\" is not a short")
    }

    fun toShortOrNull(value: String?): Short? {
        if(value == null) return null
        return value.toShortOrNull()
    }

    fun toInt(value: String?): Int {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toIntOrNull() ?: throw ValueValidationException("Value \"$value\" is not a int")
    }

    fun toIntOrNull(value: String?): Int? {
        if(value == null) return null
        return value.toIntOrNull()
    }

    fun toLong(value: String?): Long {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toLongOrNull() ?: throw ValueValidationException("Value \"$value\" is not a long")
    }

    fun toLongOrNull(value: String?): Long? {
        if(value == null) return null
        return value.toLongOrNull()
    }

    fun toFloat(value: String?): Float {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toFloatOrNull() ?: throw ValueValidationException("Value \"$value\" is not a float")
    }

    fun toFloatOrNull(value: String?): Float? {
        if(value == null) return null
        return value.toFloatOrNull()
    }

    fun toDouble(value: String?): Double {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toDoubleOrNull() ?: throw ValueValidationException("Value \"$value\" is not a double")
    }

    fun toDoubleOrNull(value: String?): Double? {
        if(value == null) return null
        return value.toDoubleOrNull()
    }

    fun toNumber(value: String?): Number {
        if(value == null) throw ValueValidationException("Value is null")
        return value.toDoubleOrNull() ?: throw ValueValidationException("Value \"$value\" is not a number")
    }

    fun toNumberOrNull(value: String?): Number? {
        if(value == null) return null
        return value.toDoubleOrNull()
    }

    fun toPositiveByte(value: String?): Byte {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toByteOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive byte")
        if(value.toByte() < 0) throw ValueValidationException("Value \"$value\" is not a positive byte")
        return value.toByte()
    }

    fun toPositiveByteOrNull(value: String?): Byte? {
        if(value == null) return null
        if(value.toByteOrNull() == null) return null
        if(value.toByte() < 0) return null
        return value.toByte()
    }

    fun toPositiveShort(value: String?): Short {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toShortOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive short")
        if(value.toShort() < 0) throw ValueValidationException("Value \"$value\" is not a positive short")
        return value.toShort()
    }

    fun toPositiveShortOrNull(value: String?): Short? {
        if(value == null) return null
        if(value.toShortOrNull() == null) return null
        if(value.toShort() < 0) return null
        return value.toShort()
    }

    fun toPositiveInt(value: String?): Int {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toIntOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive int")
        if(value.toInt() < 0) throw ValueValidationException("Value \"$value\" is not a positive int")
        return value.toInt()
    }

    fun toPositiveIntOrNull(value: String?): Int? {
        if(value == null) return null
        if(value.toIntOrNull() == null) return null
        if(value.toInt() < 0) return null
        return value.toInt()
    }

    fun toPositiveLong(value: String?): Long {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toLongOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive long")
        if(value.toLong() < 0) throw ValueValidationException("Value \"$value\" is not a positive long")
        return value.toLong()
    }

    fun toPositiveLongOrNull(value: String?): Long? {
        if(value == null) return null
        if(value.toLongOrNull() == null) return null
        if(value.toLong() < 0) return null
        return value.toLong()
    }

    fun toPositiveFloat(value: String?): Float {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toFloatOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive float")
        if(value.toFloat() < 0) throw ValueValidationException("Value \"$value\" is not a positive float")
        return value.toFloat()
    }

    fun toPositiveFloatOrNull(value: String?): Float? {
        if(value == null) return null
        if(value.toFloatOrNull() == null) return null
        if(value.toFloat() < 0) return null
        return value.toFloat()
    }

    fun toPositiveDouble(value: String?): Double {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toDoubleOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive double")
        if(value.toDouble() < 0) throw ValueValidationException("Value \"$value\" is not a positive double")
        return value.toDouble()
    }

    fun toPositiveDoubleOrNull(value: String?): Double? {
        if(value == null) return null
        if(value.toDoubleOrNull() == null) return null
        if(value.toDouble() < 0) return null
        return value.toDouble()
    }

    fun toPositive(value: String?): Number {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toDoubleOrNull() == null) throw ValueValidationException("Value \"$value\" is not a positive number")
        if(value.toDouble() < 0) throw ValueValidationException("Value \"$value\" is not a positive number")
        return value.toDouble()
    }

    fun toPositiveOrNull(value: String?): Number? {
        if(value == null) return null
        if(value.toDoubleOrNull() == null) return null
        if(value.toDouble() < 0) return null
        return value.toDouble()
    }

    fun toNegativeByte(value: String?): Byte {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toByteOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative byte")
        if(value.toByte() > 0) throw ValueValidationException("Value \"$value\" is not a negative byte")
        return value.toByte()
    }

    fun toNegativeByteOrNull(value: String?): Byte? {
        if(value == null) return null
        if(value.toByteOrNull() == null) return null
        if(value.toByte() > 0) return null
        return value.toByte()
    }

    fun toNegativeShort(value: String?): Short {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toShortOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative short")
        if(value.toShort() > 0) throw ValueValidationException("Value \"$value\" is not a negative short")
        return value.toShort()
    }

    fun toNegativeShortOrNull(value: String?): Short? {
        if(value == null) return null
        if(value.toShortOrNull() == null) return null
        if(value.toShort() > 0) return null
        return value.toShort()
    }

    fun toNegativeInt(value: String?): Int {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toIntOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative int")
        if(value.toInt() > 0) throw ValueValidationException("Value \"$value\" is not a negative int")
        return value.toInt()
    }

    fun toNegativeIntOrNull(value: String?): Int? {
        if(value == null) return null
        if(value.toIntOrNull() == null) return null
        if(value.toInt() > 0) return null
        return value.toInt()
    }

    fun toNegativeLong(value: String?): Long {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toLongOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative long")
        if(value.toLong() > 0) throw ValueValidationException("Value \"$value\" is not a negative long")
        return value.toLong()
    }

    fun toNegativeLongOrNull(value: String?): Long? {
        if(value == null) return null
        if(value.toLongOrNull() == null) return null
        if(value.toLong() > 0) return null
        return value.toLong()
    }

    fun toNegativeFloat(value: String?): Float {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toFloatOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative float")
        if(value.toFloat() > 0) throw ValueValidationException("Value \"$value\" is not a negative float")
        return value.toFloat()
    }

    fun toNegativeFloatOrNull(value: String?): Float? {
        if(value == null) return null
        if(value.toFloatOrNull() == null) return null
        if(value.toFloat() > 0) return null
        return value.toFloat()
    }

    fun toNegativeDouble(value: String?): Double {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toDoubleOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative double")
        if(value.toDouble() > 0) throw ValueValidationException("Value \"$value\" is not a negative double")
        return value.toDouble()
    }

    fun toNegativeDoubleOrNull(value: String?): Double? {
        if(value == null) return null
        if(value.toDoubleOrNull() == null) return null
        if(value.toDouble() > 0) return null
        return value.toDouble()
    }

    fun toNegative(value: String?): Number {
        if(value == null) throw ValueValidationException("Value is null")
        if(value.toDoubleOrNull() == null) throw ValueValidationException("Value \"$value\" is not a negative number")
        if(value.toDouble() > 0) throw ValueValidationException("Value \"$value\" is not a negative number")
        return value.toDouble()
    }

    fun toNegativeOrNull(value: String?): Number? {
        if(value == null) return null
        if(value.toDoubleOrNull() == null) return null
        if(value.toDouble() > 0) return null
        return value.toDouble()
    }

    fun toBoolean(value: String?): Boolean {
        if (value == null) throw ValueValidationException("Value is null")
        return when (value) {
            "true", "yes", "y", "1" -> true
            "false", "no", "n", "0" -> false
            else -> throw ValueValidationException("Value \"$value\" is not a boolean")
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
        if(value == null) throw ValueValidationException("Value is null")
        if(value.length != 1) throw ValueValidationException("Value \"$value\" is not a char")
        return value[0]
    }

    fun toCharOrNull(value: String?): Char? {
        if(value == null) return null
        if(value.length != 1) return null
        return value[0]
    }

    fun toString(value: String?): String {
        if(value == null) throw ValueValidationException("Value is null")
        return value
    }

    fun toStringOrNull(value: String?): String? {
        if(value == null) return null
        return value
    }

    fun toNull(value: String?): String? {
        if(value != null) throw ValueValidationException("Value is not null")
        return null
    }

    fun toNotNull(value: String?): String {
        if(value == null) throw ValueValidationException("Value is null")
        return value
    }

    fun toUnsignedByte(value: String?): UByte {
        if (value == null) throw ValueValidationException("Value is null")
        return value.toUByteOrNull() ?: throw ValueValidationException("Value \"$value\" is not a unsigned byte")
    }

    fun toUnsignedByteOrNull(value: String?): UByte? {
        if (value == null) return null
        return value.toUByteOrNull()
    }

    fun toUnsignedShort(value: String?): UShort {
        if (value == null) throw ValueValidationException("Value is null")
        return value.toUShortOrNull() ?: throw ValueValidationException("Value \"$value\" is not a unsigned short")
    }

    fun toUnsignedShortOrNull(value: String?): UShort? {
        if (value == null) return null
        return value.toUShortOrNull()
    }

    fun toUnsignedInt(value: String?): UInt {
        if (value == null) throw ValueValidationException("Value is null")
        return value.toUIntOrNull() ?: throw ValueValidationException("Value \"$value\" is not a unsigned int")
    }

    fun toUnsignedIntOrNull(value: String?): UInt? {
        if (value == null) return null
        return value.toUIntOrNull()
    }

    fun toUnsignedLong(value: String?): ULong {
        if (value == null) throw ValueValidationException("Value is null")
        return value.toULongOrNull() ?: throw ValueValidationException("Value \"$value\" is not a unsigned long")
    }

    fun toUnsignedLongOrNull(value: String?): ULong? {
        if (value == null) return null
        return value.toULongOrNull()
    }

    val toByte: NullableValueTransformer<Byte> = ::toByte
    val toByteOrNull: NullableValueTransformer<Byte?> = ::toByteOrNull
    val toShort: NullableValueTransformer<Short> = ::toShort
    val toShortOrNull: NullableValueTransformer<Short?> = ::toShortOrNull
    val toInt: NullableValueTransformer<Int> = ::toInt
    val toIntOrNull: NullableValueTransformer<Int?> = ::toIntOrNull
    val toLong: NullableValueTransformer<Long> = ::toLong
    val toLongOrNull: NullableValueTransformer<Long?> = ::toLongOrNull
    val toFloat: NullableValueTransformer<Float> = ::toFloat
    val toFloatOrNull: NullableValueTransformer<Float?> = ::toFloatOrNull
    val toDouble: NullableValueTransformer<Double> = ::toDouble
    val toDoubleOrNull: NullableValueTransformer<Double?> = ::toDoubleOrNull
    val toNumber: NullableValueTransformer<Number> = ::toNumber
    val toNumberOrNull: NullableValueTransformer<Number?> = ::toNumberOrNull
    val toPositiveByte: NullableValueTransformer<Byte> = ::toPositiveByte
    val toPositiveByteOrNull: NullableValueTransformer<Byte?> = ::toPositiveByteOrNull
    val toPositiveShort: NullableValueTransformer<Short> = ::toPositiveShort
    val toPositiveShortOrNull: NullableValueTransformer<Short?> = ::toPositiveShortOrNull
    val toPositiveInt: NullableValueTransformer<Int> = ::toPositiveInt
    val toPositiveIntOrNull: NullableValueTransformer<Int?> = ::toPositiveIntOrNull
    val toPositiveLong: NullableValueTransformer<Long> = ::toPositiveLong
    val toPositiveLongOrNull: NullableValueTransformer<Long?> = ::toPositiveLongOrNull
    val toPositiveFloat: NullableValueTransformer<Float> = ::toPositiveFloat
    val toPositiveFloatOrNull: NullableValueTransformer<Float?> = ::toPositiveFloatOrNull
    val toPositiveDouble: NullableValueTransformer<Double> = ::toPositiveDouble
    val toPositiveDoubleOrNull: NullableValueTransformer<Double?> = ::toPositiveDoubleOrNull
    val toPositive: NullableValueTransformer<Number> = ::toPositive
    val toPositiveOrNull: NullableValueTransformer<Number?> = ::toPositiveOrNull
    val toNegativeByte: NullableValueTransformer<Byte> = ::toNegativeByte
    val toNegativeByteOrNull: NullableValueTransformer<Byte?> = ::toNegativeByteOrNull
    val toNegativeShort: NullableValueTransformer<Short> = ::toNegativeShort
    val toNegativeShortOrNull: NullableValueTransformer<Short?> = ::toNegativeShortOrNull
    val toNegativeInt: NullableValueTransformer<Int> = ::toNegativeInt
    val toNegativeIntOrNull: NullableValueTransformer<Int?> = ::toNegativeIntOrNull
    val toNegativeLong: NullableValueTransformer<Long> = ::toNegativeLong
    val toNegativeLongOrNull: NullableValueTransformer<Long?> = ::toNegativeLongOrNull
    val toNegativeFloat: NullableValueTransformer<Float> = ::toNegativeFloat
    val toNegativeFloatOrNull: NullableValueTransformer<Float?> = ::toNegativeFloatOrNull
    val toNegativeDouble: NullableValueTransformer<Double> = ::toNegativeDouble
    val toNegativeDoubleOrNull: NullableValueTransformer<Double?> = ::toNegativeDoubleOrNull
    val toNegative: NullableValueTransformer<Number> = ::toNegative
    val toNegativeOrNull: NullableValueTransformer<Number?> = ::toNegativeOrNull
    val toBoolean: NullableValueTransformer<Boolean> = ::toBoolean
    val toBooleanOrNull: NullableValueTransformer<Boolean?> = ::toBooleanOrNull
    val toChar: NullableValueTransformer<Char> = ::toChar
    val toCharOrNull: NullableValueTransformer<Char?> = ::toCharOrNull

    @JsName("toStringLambda")
    val toString: NullableValueTransformer<String> = ::toString
    val toStringOrNull: NullableValueTransformer<String?> = ::toStringOrNull
    val toNull: NullableValueTransformer<String?> = ::toNull
    val toNotNull: NullableValueTransformer<String> = ::toNotNull
    val toUnsignedByte: NullableValueTransformer<UByte> = ::toUnsignedByte
    val toUnsignedByteOrNull: NullableValueTransformer<UByte?> = ::toUnsignedByteOrNull
    val toUnsignedShort: NullableValueTransformer<UShort> = ::toUnsignedShort
    val toUnsignedShortOrNull: NullableValueTransformer<UShort?> = ::toUnsignedShortOrNull
    val toUnsignedInt: NullableValueTransformer<UInt> = ::toUnsignedInt
    val toUnsignedIntOrNull: NullableValueTransformer<UInt?> = ::toUnsignedIntOrNull
    val toUnsignedLong: NullableValueTransformer<ULong> = ::toUnsignedLong
    val toUnsignedLongOrNull: NullableValueTransformer<ULong?> = ::toUnsignedLongOrNull

}