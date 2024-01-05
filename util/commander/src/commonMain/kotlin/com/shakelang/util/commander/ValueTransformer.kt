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

    val toByte: ValueTransformer<Byte> = ::toByte
    val toByteOrNull: ValueTransformer<Byte?> = ::toByteOrNull
    val toShort: ValueTransformer<Short> = ::toShort
    val toShortOrNull: ValueTransformer<Short?> = ::toShortOrNull
    val toInt: ValueTransformer<Int> = ::toInt
    val toIntOrNull: ValueTransformer<Int?> = ::toIntOrNull
    val toLong: ValueTransformer<Long> = ::toLong
    val toLongOrNull: ValueTransformer<Long?> = ::toLongOrNull
    val toFloat: ValueTransformer<Float> = ::toFloat
    val toFloatOrNull: ValueTransformer<Float?> = ::toFloatOrNull
    val toDouble: ValueTransformer<Double> = ::toDouble
    val toDoubleOrNull: ValueTransformer<Double?> = ::toDoubleOrNull
    val toNumber: ValueTransformer<Number> = ::toNumber
    val toNumberOrNull: ValueTransformer<Number?> = ::toNumberOrNull
    val toPositiveByte: ValueTransformer<Byte> = ::toPositiveByte
    val toPositiveByteOrNull: ValueTransformer<Byte?> = ::toPositiveByteOrNull
    val toPositiveShort: ValueTransformer<Short> = ::toPositiveShort
    val toPositiveShortOrNull: ValueTransformer<Short?> = ::toPositiveShortOrNull
    val toPositiveInt: ValueTransformer<Int> = ::toPositiveInt
    val toPositiveIntOrNull: ValueTransformer<Int?> = ::toPositiveIntOrNull
    val toPositiveLong: ValueTransformer<Long> = ::toPositiveLong
    val toPositiveLongOrNull: ValueTransformer<Long?> = ::toPositiveLongOrNull
    val toPositiveFloat: ValueTransformer<Float> = ::toPositiveFloat
    val toPositiveFloatOrNull: ValueTransformer<Float?> = ::toPositiveFloatOrNull
    val toPositiveDouble: ValueTransformer<Double> = ::toPositiveDouble
    val toPositiveDoubleOrNull: ValueTransformer<Double?> = ::toPositiveDoubleOrNull
    val toPositive: ValueTransformer<Number> = ::toPositive
    val toPositiveOrNull: ValueTransformer<Number?> = ::toPositiveOrNull
    val toNegativeByte: ValueTransformer<Byte> = ::toNegativeByte
    val toNegativeByteOrNull: ValueTransformer<Byte?> = ::toNegativeByteOrNull
    val toNegativeShort: ValueTransformer<Short> = ::toNegativeShort
    val toNegativeShortOrNull: ValueTransformer<Short?> = ::toNegativeShortOrNull
    val toNegativeInt: ValueTransformer<Int> = ::toNegativeInt
    val toNegativeIntOrNull: ValueTransformer<Int?> = ::toNegativeIntOrNull
    val toNegativeLong: ValueTransformer<Long> = ::toNegativeLong
    val toNegativeLongOrNull: ValueTransformer<Long?> = ::toNegativeLongOrNull
    val toNegativeFloat: ValueTransformer<Float> = ::toNegativeFloat
    val toNegativeFloatOrNull: ValueTransformer<Float?> = ::toNegativeFloatOrNull
    val toNegativeDouble: ValueTransformer<Double> = ::toNegativeDouble
    val toNegativeDoubleOrNull: ValueTransformer<Double?> = ::toNegativeDoubleOrNull
    val toNegative: ValueTransformer<Number> = ::toNegative
    val toNegativeOrNull: ValueTransformer<Number?> = ::toNegativeOrNull
    val toBoolean: ValueTransformer<Boolean> = ::toBoolean
    val toBooleanOrNull: ValueTransformer<Boolean?> = ::toBooleanOrNull
    val toChar: ValueTransformer<Char> = ::toChar
    val toCharOrNull: ValueTransformer<Char?> = ::toCharOrNull

    @JsName("toStringLambda")
    val toString: ValueTransformer<String> = ::toString
    val toStringOrNull: ValueTransformer<String?> = ::toStringOrNull
    val toNull: ValueTransformer<String?> = ::toNull
    val toNotNull: ValueTransformer<String> = ::toNotNull
    val toUnsignedByte: ValueTransformer<UByte> = ::toUnsignedByte
    val toUnsignedByteOrNull: ValueTransformer<UByte?> = ::toUnsignedByteOrNull
    val toUnsignedShort: ValueTransformer<UShort> = ::toUnsignedShort
    val toUnsignedShortOrNull: ValueTransformer<UShort?> = ::toUnsignedShortOrNull
    val toUnsignedInt: ValueTransformer<UInt> = ::toUnsignedInt
    val toUnsignedIntOrNull: ValueTransformer<UInt?> = ::toUnsignedIntOrNull
    val toUnsignedLong: ValueTransformer<ULong> = ::toUnsignedLong
    val toUnsignedLongOrNull: ValueTransformer<ULong?> = ::toUnsignedLongOrNull

}