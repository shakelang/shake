package com.shakelang.util.commander

import kotlin.js.JsName

/**
 * A collection of common [CommanderValueTransformer] implementations
 * @since 0.1.0
 * @version 0.1.0
 */
object CommanderValueTransformers {

    /**
     * Transform a given [value] to a [Byte]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByte(value: String?): Byte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toByteOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a byte")
    }

    /**
     * Transform a given [value] to a [Byte] or return null if the value is not a byte
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toByteOrNull(value: String?): Byte? {
        if (value == null) return null
        return value.toByteOrNull()
    }

    /**
     * Transform a given [value] to a [Short]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a short
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShort(value: String?): Short {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toShortOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a short")
    }

    /**
     * Transform a given [value] to a [Short] or return null if the value is not a short
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toShortOrNull(value: String?): Short? {
        if (value == null) return null
        return value.toShortOrNull()
    }

    /**
     * Transform a given [value] to a [Int]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not an int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toInt(value: String?): Int {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toIntOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a int")
    }

    /**
     * Transform a given [value] to a [Int] or return null if the value is not an int
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toIntOrNull(value: String?): Int? {
        if (value == null) return null
        return value.toIntOrNull()
    }

    /**
     * Transform a given [value] to a [Long]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLong(value: String?): Long {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toLongOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a long")
    }

    /**
     * Transform a given [value] to a [Long] or return null if the value is not a long
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toLongOrNull(value: String?): Long? {
        if (value == null) return null
        return value.toLongOrNull()
    }

    /**
     * Transform a given [value] to a [Float]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloat(value: String?): Float {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toFloatOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a float")
    }

    /**
     * Transform a given [value] to a [Float] or return null if the value is not a float
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toFloatOrNull(value: String?): Float? {
        if (value == null) return null
        return value.toFloatOrNull()
    }

    /**
     * Transform a given [value] to a [Double]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDouble(value: String?): Double {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toDoubleOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a double")
    }

    /**
     * Transform a given [value] to a [Double] or return null if the value is not a double
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toDoubleOrNull(value: String?): Double? {
        if (value == null) return null
        return value.toDoubleOrNull()
    }

    /**
     * Transform a given [value] to a [Number]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a number
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNumber(value: String?): Number {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toDoubleOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a number")
    }

    /**
     * Transform a given [value] to a [Number] or return null if the value is not a number
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNumberOrNull(value: String?): Number? {
        if (value == null) return null
        return value.toDoubleOrNull()
    }

    /**
     * Transform a given [value] to a positive [Byte]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveByte(value: String?): Byte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
        if (value.toByte() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive byte")
        return value.toByte()
    }

    /**
     * Transform a given [value] to a positive [Byte] or return null if the value is not a positive byte
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveByteOrNull(value: String?): Byte? {
        if (value == null) return null
        if (value.toByteOrNull() == null) return null
        if (value.toByte() < 0) return null
        return value.toByte()
    }

    /**
     * Transform a given [value] to a positive [Short]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive short
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveShort(value: String?): Short {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive short")
        if (value.toShort() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive short")
        return value.toShort()
    }

    /**
     * Transform a given [value] to a positive [Short] or return null if the value is not a positive short
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveShortOrNull(value: String?): Short? {
        if (value == null) return null
        if (value.toShortOrNull() == null) return null
        if (value.toShort() < 0) return null
        return value.toShort()
    }

    /**
     * Transform a given [value] to a positive [Int]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveInt(value: String?): Int {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
        if (value.toInt() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive int")
        return value.toInt()
    }

    /**
     * Transform a given [value] to a positive [Int] or return null if the value is not a positive int
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveIntOrNull(value: String?): Int? {
        if (value == null) return null
        if (value.toIntOrNull() == null) return null
        if (value.toInt() < 0) return null
        return value.toInt()
    }

    /**
     * Transform a given [value] to a positive [Long]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveLong(value: String?): Long {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
        if (value.toLong() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive long")
        return value.toLong()
    }

    /**
     * Transform a given [value] to a positive [Long] or return null if the value is not a positive long
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveLongOrNull(value: String?): Long? {
        if (value == null) return null
        if (value.toLongOrNull() == null) return null
        if (value.toLong() < 0) return null
        return value.toLong()
    }

    /**
     * Transform a given [value] to a positive [Float]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveFloat(value: String?): Float {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
        if (value.toFloat() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive float")
        return value.toFloat()
    }

    /**
     * Transform a given [value] to a positive [Float] or return null if the value is not a positive float
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveFloatOrNull(value: String?): Float? {
        if (value == null) return null
        if (value.toFloatOrNull() == null) return null
        if (value.toFloat() < 0) return null
        return value.toFloat()
    }

    /**
     * Transform a given [value] to a positive [Double]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveDouble(value: String?): Double {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive double")
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a positive [Double] or return null if the value is not a positive double
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveDoubleOrNull(value: String?): Double? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() < 0) return null
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a positive [Number]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a positive number
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositive(value: String?): Number {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
        if (value.toDouble() < 0) throw ValueValidationException("CommanderValue \"$value\" is not a positive number")
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a positive [Number] or return null if the value is not a positive number
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toPositiveOrNull(value: String?): Number? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() < 0) return null
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a negative [Byte]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative byte
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeByte(value: String?): Byte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toByteOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
        if (value.toByte() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative byte")
        return value.toByte()
    }

    /**
     * Transform a given [value] to a negative [Byte] or return null if the value is not a negative byte
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeByteOrNull(value: String?): Byte? {
        if (value == null) return null
        if (value.toByteOrNull() == null) return null
        if (value.toByte() > 0) return null
        return value.toByte()
    }

    /**
     * Transform a given [value] to a negative [Short]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative short
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeShort(value: String?): Short {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toShortOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative short")
        if (value.toShort() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative short")
        return value.toShort()
    }

    /**
     * Transform a given [value] to a negative [Short] or return null if the value is not a negative short
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeShortOrNull(value: String?): Short? {
        if (value == null) return null
        if (value.toShortOrNull() == null) return null
        if (value.toShort() > 0) return null
        return value.toShort()
    }

    /**
     * Transform a given [value] to a negative [Int]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeInt(value: String?): Int {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toIntOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
        if (value.toInt() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative int")
        return value.toInt()
    }

    /**
     * Transform a given [value] to a negative [Int] or return null if the value is not a negative int
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeIntOrNull(value: String?): Int? {
        if (value == null) return null
        if (value.toIntOrNull() == null) return null
        if (value.toInt() > 0) return null
        return value.toInt()
    }

    /**
     * Transform a given [value] to a negative [Long]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative long
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeLong(value: String?): Long {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toLongOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
        if (value.toLong() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative long")
        return value.toLong()
    }

    /**
     * Transform a given [value] to a negative [Long] or return null if the value is not a negative long
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeLongOrNull(value: String?): Long? {
        if (value == null) return null
        if (value.toLongOrNull() == null) return null
        if (value.toLong() > 0) return null
        return value.toLong()
    }

    /**
     * Transform a given [value] to a negative [Float]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative float
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeFloat(value: String?): Float {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toFloatOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
        if (value.toFloat() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative float")
        return value.toFloat()
    }

    /**
     * Transform a given [value] to a negative [Float] or return null if the value is not a negative float
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeFloatOrNull(value: String?): Float? {
        if (value == null) return null
        if (value.toFloatOrNull() == null) return null
        if (value.toFloat() > 0) return null
        return value.toFloat()
    }

    /**
     * Transform a given [value] to a negative [Double]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative double
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeDouble(value: String?): Double {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative double")
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a negative [Double] or return null if the value is not a negative double
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeDoubleOrNull(value: String?): Double? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() > 0) return null
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a negative [Number]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a negative number
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegative(value: String?): Number {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.toDoubleOrNull() == null) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
        if (value.toDouble() > 0) throw ValueValidationException("CommanderValue \"$value\" is not a negative number")
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a negative [Number] or return null if the value is not a negative number
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNegativeOrNull(value: String?): Number? {
        if (value == null) return null
        if (value.toDoubleOrNull() == null) return null
        if (value.toDouble() > 0) return null
        return value.toDouble()
    }

    /**
     * Transform a given [value] to a [Boolean]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a boolean
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBoolean(value: String?): Boolean {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return when (value) {
            "true", "yes", "y", "1" -> true
            "false", "no", "n", "0" -> false
            else -> throw ValueValidationException("CommanderValue \"$value\" is not a boolean")
        }
    }

    /**
     * Transform a given [value] to a [Boolean] or return null if the value is not a boolean
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toBooleanOrNull(value: String?): Boolean? {
        if (value == null) return null
        return when (value) {
            "true", "yes", "y", "1" -> true
            "false", "no", "n", "0" -> false
            else -> null
        }
    }

    /**
     * Transform a given [value] to a [Char]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is not a char
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toChar(value: String?): Char {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        if (value.length != 1) throw ValueValidationException("CommanderValue \"$value\" is not a char")
        return value[0]
    }

    /**
     * Transform a given [value] to a [Char] or return null if the value is not a char
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toCharOrNull(value: String?): Char? {
        if (value == null) return null
        if (value.length != 1) return null
        return value[0]
    }

    /**
     * Transform a given [value] to a [String]
     * @param value The value to transform
     * @return The transformed value
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toString(value: String?): String {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toStringOrNull(value: String?): String? {
        if (value == null) return null
        return value
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNull(value: String?): String? {
        if (value != null) throw ValueValidationException("CommanderValue is not null")
        return null
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toNotNull(value: String?): String {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedByte(value: String?): UByte {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toUByteOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned byte")
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedByteOrNull(value: String?): UByte? {
        if (value == null) return null
        return value.toUByteOrNull()
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not an unsigned short
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedShort(value: String?): UShort {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toUShortOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned short")
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedShortOrNull(value: String?): UShort? {
        if (value == null) return null
        return value.toUShortOrNull()
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not an unsigned int
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedInt(value: String?): UInt {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toUIntOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned int")
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedIntOrNull(value: String?): UInt? {
        if (value == null) return null
        return value.toUIntOrNull()
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @throws ValueValidationException If the value is not an unsigned long
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedLong(value: String?): ULong {
        if (value == null) throw ValueValidationException("CommanderValue is null")
        return value.toULongOrNull() ?: throw ValueValidationException("CommanderValue \"$value\" is not a unsigned long")
    }

    /**
     * Transform a given [value] to a [String] or return null if the value is null
     * @param value The value to transform
     * @throws ValueValidationException If the value is not an unsigned long
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    fun toUnsignedLongOrNull(value: String?): ULong? {
        if (value == null) return null
        return value.toULongOrNull()
    }

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not an unsigned long
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toByte: CommanderNullableValueTransformer<Byte> = ::toByte

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toByteOrNull: CommanderNullableValueTransformer<Byte?> = ::toByteOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not an unsigned short
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toShort: CommanderNullableValueTransformer<Short> = ::toShort

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toShortOrNull: CommanderNullableValueTransformer<Short?> = ::toShortOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not an unsigned int
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toInt: CommanderNullableValueTransformer<Int> = ::toInt

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toIntOrNull: CommanderNullableValueTransformer<Int?> = ::toIntOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not an unsigned long
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toLong: CommanderNullableValueTransformer<Long> = ::toLong

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toLongOrNull: CommanderNullableValueTransformer<Long?> = ::toLongOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not an unsigned float
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toFloat: CommanderNullableValueTransformer<Float> = ::toFloat

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toFloatOrNull: CommanderNullableValueTransformer<Float?> = ::toFloatOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not an unsigned double
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toDouble: CommanderNullableValueTransformer<Double> = ::toDouble

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toDoubleOrNull: CommanderNullableValueTransformer<Double?> = ::toDoubleOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a number
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNumber: CommanderNullableValueTransformer<Number> = ::toNumber

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNumberOrNull: CommanderNullableValueTransformer<Number?> = ::toNumberOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive byte
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveByte: CommanderNullableValueTransformer<Byte> = ::toPositiveByte

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive byte
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveByteOrNull: CommanderNullableValueTransformer<Byte?> = ::toPositiveByteOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive short
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveShort: CommanderNullableValueTransformer<Short> = ::toPositiveShort

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive short
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveShortOrNull: CommanderNullableValueTransformer<Short?> = ::toPositiveShortOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive int
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveInt: CommanderNullableValueTransformer<Int> = ::toPositiveInt

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive int
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveIntOrNull: CommanderNullableValueTransformer<Int?> = ::toPositiveIntOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive long
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveLong: CommanderNullableValueTransformer<Long> = ::toPositiveLong

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive long
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveLongOrNull: CommanderNullableValueTransformer<Long?> = ::toPositiveLongOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive float
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveFloat: CommanderNullableValueTransformer<Float> = ::toPositiveFloat

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive float
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveFloatOrNull: CommanderNullableValueTransformer<Float?> = ::toPositiveFloatOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive double
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveDouble: CommanderNullableValueTransformer<Double> = ::toPositiveDouble

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive double
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveDoubleOrNull: CommanderNullableValueTransformer<Double?> = ::toPositiveDoubleOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is not a positive number
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositive: CommanderNullableValueTransformer<Number> = ::toPositive

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a positive number
     * @since 0.1.0
     * @version 0.1.0
     */
    val toPositiveOrNull: CommanderNullableValueTransformer<Number?> = ::toPositiveOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeByte: CommanderNullableValueTransformer<Byte> = ::toNegativeByte

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeByteOrNull: CommanderNullableValueTransformer<Byte?> = ::toNegativeByteOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeShort: CommanderNullableValueTransformer<Short> = ::toNegativeShort

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeShortOrNull: CommanderNullableValueTransformer<Short?> = ::toNegativeShortOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeInt: CommanderNullableValueTransformer<Int> = ::toNegativeInt

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeIntOrNull: CommanderNullableValueTransformer<Int?> = ::toNegativeIntOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeLong: CommanderNullableValueTransformer<Long> = ::toNegativeLong

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeLongOrNull: CommanderNullableValueTransformer<Long?> = ::toNegativeLongOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeFloat: CommanderNullableValueTransformer<Float> = ::toNegativeFloat

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeFloatOrNull: CommanderNullableValueTransformer<Float?> = ::toNegativeFloatOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeDouble: CommanderNullableValueTransformer<Double> = ::toNegativeDouble

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeDoubleOrNull: CommanderNullableValueTransformer<Double?> = ::toNegativeDoubleOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @throws ValueValidationException If the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegative: CommanderNullableValueTransformer<Number> = ::toNegative

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNegativeOrNull: CommanderNullableValueTransformer<Number?> = ::toNegativeOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @since 0.1.0
     * @version 0.1.0
     */
    val toBoolean: CommanderNullableValueTransformer<Boolean> = ::toBoolean

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toBooleanOrNull: CommanderNullableValueTransformer<Boolean?> = ::toBooleanOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is not a char
     * @since 0.1.0
     * @version 0.1.0
     */
    val toChar: CommanderNullableValueTransformer<Char> = ::toChar

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not a char
     * @since 0.1.0
     * @version 0.1.0
     */
    val toCharOrNull: CommanderNullableValueTransformer<Char?> = ::toCharOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    @JsName("toStringLambda")
    val toString: CommanderNullableValueTransformer<String> = ::toString

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toStringOrNull: CommanderNullableValueTransformer<String?> = ::toStringOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is not null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNull: CommanderNullableValueTransformer<String?> = ::toNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is null
     * @since 0.1.0
     * @version 0.1.0
     */
    val toNotNull: CommanderNullableValueTransformer<String> = ::toNotNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is not an unsigned byte
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedByte: CommanderNullableValueTransformer<UByte> = ::toUnsignedByte

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not an unsigned byte
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedByteOrNull: CommanderNullableValueTransformer<UByte?> = ::toUnsignedByteOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is not an unsigned short
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedShort: CommanderNullableValueTransformer<UShort> = ::toUnsignedShort

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not an unsigned short
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedShortOrNull: CommanderNullableValueTransformer<UShort?> = ::toUnsignedShortOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is not an unsigned int
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedInt: CommanderNullableValueTransformer<UInt> = ::toUnsignedInt

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not an unsigned int
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedIntOrNull: CommanderNullableValueTransformer<UInt?> = ::toUnsignedIntOrNull

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value
     * @throws ValueValidationException If the value is not an unsigned long
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedLong: CommanderNullableValueTransformer<ULong> = ::toUnsignedLong

    /**
     * Transform a given value to a [String] or return null if the value is null
     * @return The transformed value or null
     * @throws ValueValidationException If the value is not an unsigned long
     * @since 0.1.0
     * @version 0.1.0
     */
    val toUnsignedLongOrNull: CommanderNullableValueTransformer<ULong?> = ::toUnsignedLongOrNull
}
