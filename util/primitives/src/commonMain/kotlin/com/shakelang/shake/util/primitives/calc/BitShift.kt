package com.shakelang.shake.util.primitives.calc

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: Byte): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: Short): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: Int): Byte = (this.toInt() shl other).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: Long): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: UByte): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: UShort): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: UInt): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the left by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shl(other: ULong): Byte = (this.toInt() shl other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: Byte): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: Short): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: Int): Byte = (this.toInt() shr other).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: Long): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: UByte): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: UShort): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 *
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: UInt): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.shr(other: ULong): Byte = (this.toInt() shr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: Byte): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: Short): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: Int): Byte = (this.toInt() ushr other).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: Long): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: UByte): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: UShort): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: UInt): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Shifts this value to the right by the [other] value and fills the left side with zeros
 *
 * @param other The shift value
 * @return The shifted value
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Byte.ushr(other: ULong): Byte = (this.toInt() ushr other.toInt()).toByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: Byte): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: Short): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: Int): Short = (this.toInt() shl other).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: Long): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: UByte): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: UShort): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: UInt): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shl(other: ULong): Short = (this.toInt() shl other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: Byte): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: Short): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: Int): Short = (this.toInt() shr other).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: Long): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: UByte): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: UShort): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: UInt): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise and operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.shr(other: ULong): Short = (this.toInt() shr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: Byte): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: Short): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: Int): Short = (this.toInt() ushr other).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: Long): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: UByte): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: UShort): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: UInt): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Short.ushr(other: ULong): Short = (this.toInt() ushr other.toInt()).toShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: Byte): Int = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: Short): Int = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: Long): Int = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: UByte): Int = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: UShort): Int = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: UInt): Int = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shl(other: ULong): Int = this shl other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: Byte): Int = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: Short): Int = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: Long): Int = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: UByte): Int = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: UShort): Int = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: UInt): Int = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.shr(other: ULong): Int = this shr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: Byte): Int = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: Short): Int = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: Long): Int = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: UByte): Int = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: UShort): Int = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: UInt): Int = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Int.ushr(other: ULong): Int = this ushr other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: Byte): Long = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: Short): Long = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: Long): Long = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: UByte): Long = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: UShort): Long = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: UInt): Long = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shl(other: ULong): Long = this shl other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: Byte): Long = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: Short): Long = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: Long): Long = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: UByte): Long = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: UShort): Long = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: UInt): Long = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.shr(other: ULong): Long = this shr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: Byte): Long = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: Short): Long = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: Long): Long = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: UByte): Long = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: UShort): Long = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: UInt): Long = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun Long.ushr(other: ULong): Long = this ushr other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: Byte): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: Short): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: Int): UByte = (this.toInt() shl other).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: Long): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: UByte): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: UShort): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: UInt): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shl(other: ULong): UByte = (this.toInt() shl other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: Byte): UByte = (this.toInt() shr other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: Short): UByte = (this.toInt() shr other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: Int): UByte = (this.toInt() shr other).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: Long): UByte = (this.toInt() ushr other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: UByte): UByte = (this.toInt() ushr other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: UShort): UByte = (this.toInt() ushr other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: UInt): UByte = (this.toInt() ushr other.toInt()).toUByte()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.shr(other: ULong): UByte = (this.toInt() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: Byte): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: Short): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: Int): UByte = (this.toByte() ushr other).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: Long): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: UByte): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: UShort): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: UInt): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UByte.ushr(other: ULong): UByte = (this.toByte() ushr other.toInt()).toUByte()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: Byte): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: Short): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: Int): UShort = (this.toShort() shl other).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: Long): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: UByte): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: UShort): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: UInt): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shl(other: ULong): UShort = (this.toShort() shl other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: Byte): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: Short): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: Int): UShort = (this.toShort() shr other).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: Long): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: UByte): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: UShort): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: UInt): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.shr(other: ULong): UShort = (this.toShort() shr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: Byte): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: Short): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: Int): UShort = (this.toShort() ushr other).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: Long): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: UByte): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: UShort): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: UInt): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UShort.ushr(other: ULong): UShort = (this.toShort() ushr other.toInt()).toUShort()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: Byte): UInt = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: Short): UInt = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: Long): UInt = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: UByte): UInt = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: UShort): UInt = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: UInt): UInt = this shl other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shl(other: ULong): UInt = this shl other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: Byte): UInt = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: Short): UInt = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: Long): UInt = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: UByte): UInt = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: UShort): UInt = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: UInt): UInt = this shr other.toInt()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.shr(other: ULong): UInt = this shr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: Byte): UInt = (this.toInt() ushr other.toInt()).toUInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: Short): UInt = (this.toInt() ushr other.toInt()).toUInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: Int): UInt = (this.toInt() ushr other).toUInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: Long): UInt = (this.toInt() ushr other.toInt()).toUInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: UByte): UInt = (this.toInt() ushr other.toInt()).toUInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: UShort): UInt = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: UInt): UInt = this ushr other.toInt()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun UInt.ushr(other: ULong): UInt = this ushr other.toInt()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: Byte): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: Short): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: Long): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: UByte): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: UShort): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: UInt): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shl operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shl operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shl(other: ULong): ULong = (this.toLong() shl other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: Byte): ULong = (this.toLong() shr other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: Short): ULong = (this.toLong() shr other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: Long): ULong = (this.toLong() shr other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: UByte): ULong = (this.toLong() shr other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: UShort): ULong = (this.toLong() shr other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: UInt): ULong = (this.toLong() shr other.toInt()).toULong()

/**
 * Performs a shr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise shr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.shr(other: ULong): ULong = (this.toLong() ushr other).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.ushr(other: Byte): ULong = (this.toLong() ushr other).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.ushr(other: Short): ULong = (this.toLong() ushr other).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.ushr(other: Int): ULong = (this.toLong() ushr other).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.ushr(other: Long): ULong = (this.toLong() ushr other).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.ushr(other: UByte): ULong = (this.toLong() ushr other.toInt()).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 * @param other The other value
 * @return The result of the bitwise ushr operation
 * @since 0.3.0
 * @version 0.3.0
 */
infix fun ULong.ushr(other: UShort): ULong = (this.toLong() ushr other.toInt()).toULong()

/**
 * Performs an ushr operation on this value and [other]
 *
 */
infix fun ULong.ushr(other: ULong): ULong = (this.toLong() ushr other.toInt()).toULong()