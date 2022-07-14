@file:Suppress("NOTHING_TO_INLINE")
package io.github.shakelang.parseutils.calc

import kotlin.math.abs

/**
 * Add a byte and an unsigned byte to get an integer.
 */
inline operator fun Byte.plus(other: UByte): Int = this + other.toShort()

/**
 * Add a byte and an unsigned short to get an integer.
 */
inline operator fun Byte.plus(other: UShort): Int = this + other.toInt()

/**
 * Add a byte and an unsigned integer to get a long.
 */
inline operator fun Byte.plus(other: UInt): Long = this + other.toLong()

/**
 * Add a byte and an unsigned long to get a long.
 */
inline operator fun Byte.plus(other: ULong): Long = this + other.toLong()


/**
 * Add a short and an unsigned byte to get an integer.
 */
inline operator fun Short.plus(other: UByte): Int = this + other.toInt()

/**
 * Add a short and an unsigned short to get an integer.
 */
inline operator fun Short.plus(other: UShort): Int = this + other.toInt()

/**
 * Add a short and an unsigned integer to get a long.
 */
inline operator fun Short.plus(other: UInt): Long = this + other.toLong()

/**
 * Add a short and an unsigned long to get a long.
 */
inline operator fun Short.plus(other: ULong): Long = this + other.toLong()


/**
 * Add an integer and an unsigned byte to get an integer.
 */
inline operator fun Int.plus(other: UByte): Int = this + other.toInt()

/**
 * Add an integer and an unsigned short to get an integer.
 */
inline operator fun Int.plus(other: UShort): Int = this + other.toInt()

/**
 * Add an integer and an unsigned integer to get a long.
 */
inline operator fun Int.plus(other: UInt): Long = this + other.toLong()

/**
 * Add an integer and an unsigned long to get a long.
 */
inline operator fun Int.plus(other: ULong): Long = this + abs(other.toLong())


/**
 * Add a long and an unsigned byte to get a long.
 */
inline operator fun Long.plus(other: UByte): Long = this + other.toLong()

/**
 * Add a long and an unsigned short to get a long.
 */
inline operator fun Long.plus(other: UShort): Long = this + other.toLong()

/**
 * Add a long and an unsigned integer to get a long.
 */
inline operator fun Long.plus(other: UInt): Long = this + other.toLong()

/**
 * Add a long and an unsigned long to get a long.
 */
inline operator fun Long.plus(other: ULong): Long = this + abs(other.toLong())


/**
 * Subtract a byte and an unsigned byte to get an integer.
 */
inline operator fun Byte.minus(other: UByte): Int = this - other.toShort()

/**
 * Subtract a byte and an unsigned short to get an integer.
 */
inline operator fun Byte.minus(other: UShort): Int = this - other.toInt()

/**
 * Subtract a byte and an unsigned integer to get a long.
 */
inline operator fun Byte.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract a byte and an unsigned long to get a long.
 */
inline operator fun Byte.minus(other: ULong): Long = this - other.toLong()


/**
 * Subtract a short and an unsigned byte to get an integer.
 */
inline operator fun Short.minus(other: UByte): Int = this - other.toInt()

/**
 * Subtract a short and an unsigned short to get an integer.
 */
inline operator fun Short.minus(other: UShort): Int = this - other.toInt()

/**
 * Subtract a short and an unsigned integer to get a long.
 */
inline operator fun Short.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract a short and an unsigned long to get a long.
 */
inline operator fun Short.minus(other: ULong): Long = this - other.toLong()


/**
 * Subtract an integer and an unsigned byte to get an integer.
 */
inline operator fun Int.minus(other: UByte): Int = this - other.toInt()

/**
 * Subtract an integer and an unsigned short to get an integer.
 */
inline operator fun Int.minus(other: UShort): Int = this - other.toInt()

/**
 * Subtract an integer and an unsigned integer to get a long.
 */
inline operator fun Int.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract an integer and an unsigned long to get a long.
 */
inline operator fun Int.minus(other: ULong): Long = this - abs(other.toLong())


/**
 * Subtract a long and an unsigned byte to get a long.
 */
inline operator fun Long.minus(other: UByte): Long = this - other.toLong()

/**
 * Subtract a long and an unsigned short to get a long.
 */
inline operator fun Long.minus(other: UShort): Long = this - other.toLong()

/**
 * Subtract a long and an unsigned integer to get a long.
 */
inline operator fun Long.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract a long and an unsigned long to get a long.
 */
inline operator fun Long.minus(other: ULong): Long = this - abs(other.toLong())


/**
 * Multiply a byte and an unsigned byte to get an integer.
 */
inline operator fun Byte.times(other: UByte): Int = this * other.toShort()

/**
 * Multiply a byte and an unsigned short to get an integer.
 */
inline operator fun Byte.times(other: UShort): Int = this * other.toInt()

/**
 * Multiply a byte and an unsigned integer to get a long.
 */
inline operator fun Byte.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply a byte and an unsigned long to get a long.
 */
inline operator fun Byte.times(other: ULong): Long = this * other.toLong()


/**
 * Multiply a short and an unsigned byte to get an integer.
 */
inline operator fun Short.times(other: UByte): Int = this * other.toInt()

/**
 * Multiply a short and an unsigned short to get an integer.
 */
inline operator fun Short.times(other: UShort): Int = this * other.toInt()

/**
 * Multiply a short and an unsigned integer to get a long.
 */
inline operator fun Short.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply a short and an unsigned long to get a long.
 */
inline operator fun Short.times(other: ULong): Long = this * other.toLong()


/**
 * Multiply an integer and an unsigned byte to get an integer.
 */
inline operator fun Int.times(other: UByte): Int = this * other.toInt()

/**
 * Multiply an integer and an unsigned short to get an integer.
 */
inline operator fun Int.times(other: UShort): Int = this * other.toInt()

/**
 * Multiply an integer and an unsigned integer to get a long.
 */
inline operator fun Int.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply an integer and an unsigned long to get a long.
 */
inline operator fun Int.times(other: ULong): Long = this * abs(other.toLong())


/**
 * Multiply a long and an unsigned byte to get a long.
 */
inline operator fun Long.times(other: UByte): Long = this * other.toLong()

/**
 * Multiply a long and an unsigned short to get a long.
 */
inline operator fun Long.times(other: UShort): Long = this * other.toLong()

/**
 * Multiply a long and an unsigned integer to get a long.
 */
inline operator fun Long.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply a long and an unsigned long to get a long.
 */
inline operator fun Long.times(other: ULong): Long = this * abs(other.toLong())


/**
 * Divide a byte and an unsigned byte to get an integer.
 */
inline operator fun Byte.div(other: UByte): Int = this / other.toShort()

/**
 * Divide a byte and an unsigned short to get an integer.
 */
inline operator fun Byte.div(other: UShort): Int = this / other.toInt()

/**
 * Divide a byte and an unsigned integer to get a long.
 */
inline operator fun Byte.div(other: UInt): Long = this / other.toLong()

/**
 * Divide a byte and an unsigned long to get a long.
 */
inline operator fun Byte.div(other: ULong): Long = this / other.toLong()


/**
 * Divide a short and an unsigned byte to get an integer.
 */
inline operator fun Short.div(other: UByte): Int = this / other.toInt()

/**
 * Divide a short and an unsigned short to get an integer.
 */
inline operator fun Short.div(other: UShort): Int = this / other.toInt()

/**
 * Divide a short and an unsigned integer to get a long.
 */
inline operator fun Short.div(other: UInt): Long = this / other.toLong()

/**
 * Divide a short and an unsigned long to get a long.
 */
inline operator fun Short.div(other: ULong): Long = this / other.toLong()


/**
 * Divide an integer and an unsigned byte to get an integer.
 */
inline operator fun Int.div(other: UByte): Int = this / other.toInt()

/**
 * Divide an integer and an unsigned short to get an integer.
 */
inline operator fun Int.div(other: UShort): Int = this / other.toInt()

/**
 * Divide an integer and an unsigned integer to get a long.
 */
inline operator fun Int.div(other: UInt): Long = this / other.toLong()

/**
 * Divide an integer and an unsigned long to get a long.
 */
inline operator fun Int.div(other: ULong): Long = this / abs(other.toLong())


/**
 * Divide a long and an unsigned byte to get a long.
 */
inline operator fun Long.div(other: UByte): Long = this / other.toLong()

/**
 * Divide a long and an unsigned short to get a long.
 */
inline operator fun Long.div(other: UShort): Long = this / other.toLong()

/**
 * Divide a long and an unsigned integer to get a long.
 */
inline operator fun Long.div(other: UInt): Long = this / other.toLong()

/**
 * Divide a long and an unsigned long to get a long.
 */
inline operator fun Long.div(other: ULong): Long = this / abs(other.toLong())


/**
 * Modulo a byte and an unsigned byte to get an integer.
 */
inline operator fun Byte.rem(other: UByte): Int = this % other.toShort()

/**
 * Modulo a byte and an unsigned short to get an integer.
 */
inline operator fun Byte.rem(other: UShort): Int = this % other.toInt()

/**
 * Modulo a byte and an unsigned integer to get a long.
 */
inline operator fun Byte.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo a byte and an unsigned long to get a long.
 */
inline operator fun Byte.rem(other: ULong): Long = this % other.toLong()


/**
 * Modulo a short and an unsigned byte to get an integer.
 */
inline operator fun Short.rem(other: UByte): Int = this % other.toInt()

/**
 * Modulo a short and an unsigned short to get an integer.
 */
inline operator fun Short.rem(other: UShort): Int = this % other.toInt()

/**
 * Modulo a short and an unsigned integer to get a long.
 */
inline operator fun Short.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo a short and an unsigned long to get a long.
 */
inline operator fun Short.rem(other: ULong): Long = this % other.toLong()


/**
 * Modulo an integer and an unsigned byte to get an integer.
 */
inline operator fun Int.rem(other: UByte): Int = this % other.toInt()

/**
 * Modulo an integer and an unsigned short to get an integer.
 */
inline operator fun Int.rem(other: UShort): Int = this % other.toInt()

/**
 * Modulo an integer and an unsigned integer to get a long.
 */
inline operator fun Int.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo an integer and an unsigned long to get a long.
 */
inline operator fun Int.rem(other: ULong): Long = this % abs(other.toLong())


/**
 * Modulo a long and an unsigned byte to get a long.
 */
inline operator fun Long.rem(other: UByte): Long = this % other.toLong()

/**
 * Modulo a long and an unsigned short to get a long.
 */
inline operator fun Long.rem(other: UShort): Long = this % other.toLong()

/**
 * Modulo a long and an unsigned integer to get a long.
 */
inline operator fun Long.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo a long and an unsigned long to get a long.
 */
inline operator fun Long.rem(other: ULong): Long = this % abs(other.toLong())


/**
 * Add an unsigned byte and a byte to get a integer.
 */
inline operator fun UByte.plus(other: Byte): Int = this.toShort() + other


/**
 * Add an unsigned byte and a short to get an integer.
 */
inline operator fun UByte.plus(other: Short): Int = this.toInt() + other

/**
 * Add an unsigned byte and an int to get a long.
 */
inline operator fun UByte.plus(other: Int): Long = this.toLong() + other

/**
 * Add an unsigned byte and a long to get a long.
 */
inline operator fun UByte.plus(other: Long): Long = this.toLong() + other

/**
 * Add an unsigned short and an unsigned byte to get an integer.
 */
inline operator fun UShort.plus(other: Byte): UShort = (this.toInt() + other).toUShort()

/**
 * Add an unsigned short and a short to get an integer.
 */
inline operator fun UShort.plus(other: Short): UShort = (this.toInt() + other).toUShort()

/**
 * Add an unsigned short and an int to get a long.
 */
inline operator fun UShort.plus(other: Int): ULong = (this.toLong() + other).toULong()

/**
 * Add an unsigned short and a long to get a long.
 */
inline operator fun UShort.plus(other: Long): ULong = (this.toLong() + other).toULong()


/**
 * Add an unsigned integer and an unsigned byte to get an integer.
 */
inline operator fun UInt.plus(other: Byte): UInt = (this.toLong() + other).toUInt()

/**
 * Add an unsigned integer and a short to get an integer.
 */
inline operator fun UInt.plus(other: Short): UInt = (this.toLong() + other).toUInt()

/**
 * Add an unsigned integer and an int to get a long.
 */
inline operator fun UInt.plus(other: Int): ULong = (this.toLong() + other).toULong()

/**
 * Add an unsigned integer and a long to get a long.
 */
inline operator fun UInt.plus(other: Long): ULong = (this.toLong() + other).toULong()

/**
 * Add an unsigned long and an unsigned byte to get a long.
 */
inline operator fun ULong.plus(other: Byte): ULong = (this.toLong() + other).toULong()

/**
 * Add an unsigned long and a short to get a long.
 */
inline operator fun ULong.plus(other: Short): ULong = (this.toLong() + other).toULong()

/**
 * Add an unsigned long and an int to get a long.
 */
inline operator fun ULong.plus(other: Int): ULong = (this.toLong() + other).toULong()

/**
 * Add an unsigned long and a long to get a long.
 */
inline operator fun ULong.plus(other: Long): ULong = (this.toLong() + other).toULong()


/**
 * Subtract an unsigned byte and a byte to get a integer.
 */
inline operator fun UByte.minus(other: Byte): UByte = (this.toShort() - other).toUByte()

/**
 * Subtract an unsigned byte and a short to get an integer.
 */
inline operator fun UByte.minus(other: Short): UByte = (this.toInt() - other).toUByte()

/**
 * Subtract an unsigned byte and an int to get a long.
 */
inline operator fun UByte.minus(other: Int): ULong = (this.toLong() - other).toULong()

/**
 * Subtract an unsigned byte and a long to get a long.
 */
inline operator fun UByte.minus(other: Long): ULong = (this.toLong() - other).toULong()


/**
 * Subtract an unsigned short and an unsigned byte to get an integer.
 */
inline operator fun UShort.minus(other: Byte): UShort = (this.toInt() - other).toUShort()

/**
 * Subtract an unsigned short and a short to get an integer.
 */
inline operator fun UShort.minus(other: Short): UShort = (this.toInt() - other).toUShort()

/**
 * Subtract an unsigned short and an int to get a long.
 */
inline operator fun UShort.minus(other: Int): ULong = (this.toLong() - other).toULong()

/**
 * Subtract an unsigned short and a long to get a long.
 */
inline operator fun UShort.minus(other: Long): ULong = (this.toLong() - other).toULong()


/**
 * Subtract an unsigned integer and an unsigned byte to get an integer.
 */
inline operator fun UInt.minus(other: Byte): UInt = (this.toLong() - other).toUInt()

/**
 * Subtract an unsigned integer and a short to get an integer.
 */
inline operator fun UInt.minus(other: Short): UInt = (this.toLong() - other).toUInt()

/**
 * Subtract an unsigned integer and an int to get a long.
 */
inline operator fun UInt.minus(other: Int): ULong = (this.toLong() - other).toULong()

/**
 * Subtract an unsigned integer and a long to get a long.
 */
inline operator fun UInt.minus(other: Long): ULong = (this.toLong() - other).toULong()


/**
 * Subtract an unsigned long and an unsigned byte to get a long.
 */
inline operator fun ULong.minus(other: Byte): ULong = (this.toLong() - other).toULong()

/**
 * Subtract an unsigned long and a short to get a long.
 */
inline operator fun ULong.minus(other: Short): ULong = (this.toLong() - other).toULong()

/**
 * Subtract an unsigned long and an int to get a long.
 */
inline operator fun ULong.minus(other: Int): ULong = (this.toLong() - other).toULong()

/**
 * Subtract an unsigned long and a long to get a long.
 */
inline operator fun ULong.minus(other: Long): ULong = (this.toLong() - other).toULong()


/**
 * Multiply an unsigned byte and a byte to get a integer.
 */
inline operator fun UByte.times(other: Byte): UByte = (this.toShort() * other).toUByte()

/**
 * Multiply an unsigned byte and a short to get an integer.
 */
inline operator fun UByte.times(other: Short): UShort = (this.toInt() * other).toUShort()

/**
 * Multiply an unsigned byte and an int to get a long.
 */
inline operator fun UByte.times(other: Int): UInt = (this.toLong() * other).toUInt()

/**
 * Multiply an unsigned byte and a long to get a long.
 */
inline operator fun UByte.times(other: Long): ULong = (this.toLong() * other).toULong()


/**
 * Multiply an unsigned short and an unsigned byte to get an integer.
 */
inline operator fun UShort.times(other: Byte): UShort = (this.toInt() * other).toUShort()

/**
 * Multiply an unsigned short and a short to get an integer.
 */
inline operator fun UShort.times(other: Short): UShort = (this.toInt() * other).toUShort()

/**
 * Multiply an unsigned short and an int to get a long.
 */
inline operator fun UShort.times(other: Int): UInt = (this.toLong() * other).toUInt()

/**
 * Multiply an unsigned short and a long to get a long.
 */
inline operator fun UShort.times(other: Long): ULong = (this.toLong() * other).toULong()


/**
 * Multiply an unsigned integer and an unsigned byte to get an integer.
 */
inline operator fun UInt.times(other: Byte): UInt = (this.toLong() * other).toUInt()

/**
 * Multiply an unsigned integer and a short to get an integer.
 */
inline operator fun UInt.times(other: Short): UInt = (this.toLong() * other).toUInt()

/**
 * Multiply an unsigned integer and an int to get a long.
 */
inline operator fun UInt.times(other: Int): UInt = (this.toLong() * other).toUInt()

/**
 * Multiply an unsigned integer and a long to get a long.
 */
inline operator fun UInt.times(other: Long): ULong = (this.toLong() * other).toULong()


/**
 * Multiply an unsigned long and an unsigned byte to get a long.
 */
inline operator fun ULong.times(other: Byte): ULong = (this.toLong() * other).toULong()

/**
 * Multiply an unsigned long and a short to get a long.
 */
inline operator fun ULong.times(other: Short): ULong = (this.toLong() * other).toULong()

/**
 * Multiply an unsigned long and an int to get a long.
 */
inline operator fun ULong.times(other: Int): ULong = (this.toLong() * other).toULong()

/**
 * Multiply an unsigned long and a long to get a long.
 */
inline operator fun ULong.times(other: Long): ULong = (this.toLong() * other).toULong()


/**
 * Divide an unsigned byte and a byte to get a integer.
 */
inline operator fun UByte.div(other: Byte): UByte = (this.toShort() / other).toUByte()

/**
 * Divide an unsigned byte and a short to get an integer.
 */
inline operator fun UByte.div(other: Short): UShort = (this.toInt() / other).toUShort()

/**
 * Divide an unsigned byte and an int to get a long.
 */
inline operator fun UByte.div(other: Int): UInt = (this.toLong() / other).toUInt()

/**
 * Divide an unsigned byte and a long to get a long.
 */
inline operator fun UByte.div(other: Long): ULong = (this.toLong() / other).toULong()


/**
 * Divide an unsigned short and an unsigned byte to get an integer.
 */
inline operator fun UShort.div(other: Byte): UShort = (this.toInt() / other).toUShort()

/**
 * Divide an unsigned short and a short to get an integer.
 */
inline operator fun UShort.div(other: Short): UShort = (this.toInt() / other).toUShort()

/**
 * Divide an unsigned short and an int to get a long.
 */
inline operator fun UShort.div(other: Int): UInt = (this.toLong() / other).toUInt()

/**
 * Divide an unsigned short and a long to get a long.
 */
inline operator fun UShort.div(other: Long): ULong = (this.toLong() / other).toULong()


/**
 * Divide an unsigned integer and an unsigned byte to get an integer.
 */
inline operator fun UInt.div(other: Byte): UInt = (this.toLong() / other).toUInt()

/**
 * Divide an unsigned integer and a short to get an integer.
 */
inline operator fun UInt.div(other: Short): UInt = (this.toLong() / other).toUInt()

/**
 * Divide an unsigned integer and an int to get a long.
 */
inline operator fun UInt.div(other: Int): UInt = (this.toLong() / other).toUInt()

/**
 * Divide an unsigned integer and a long to get a long.
 */
inline operator fun UInt.div(other: Long): ULong = (this.toLong() / other).toULong()


/**
 * Divide an unsigned long and an unsigned byte to get a long.
 */
inline operator fun ULong.div(other: Byte): ULong = (this.toLong() / other).toULong()

/**
 * Divide an unsigned long and a short to get a long.
 */
inline operator fun ULong.div(other: Short): ULong = (this.toLong() / other).toULong()

/**
 * Divide an unsigned long and an int to get a long.
 */
inline operator fun ULong.div(other: Int): ULong = (this.toLong() / other).toULong()

/**
 * Divide an unsigned long and a long to get a long.
 */
inline operator fun ULong.div(other: Long): ULong = (this.toLong() / other).toULong()


/**
 * Modulo an unsigned byte and a byte to get a integer.
 */
inline operator fun UByte.rem(other: Byte): UByte = (this.toShort() % other).toUByte()

/**
 * Modulo an unsigned byte and a short to get an integer.
 */
inline operator fun UByte.rem(other: Short): UShort = (this.toInt() % other).toUShort()

/**
 * Modulo an unsigned byte and an int to get a long.
 */
inline operator fun UByte.rem(other: Int): UInt = (this.toLong() % other).toUInt()

/**
 * Modulo an unsigned byte and a long to get a long.
 */
inline operator fun UByte.rem(other: Long): ULong = (this.toLong() % other).toULong()


/**
 * Modulo an unsigned short and an unsigned byte to get an integer.
 */
inline operator fun UShort.rem(other: Byte): UShort = (this.toInt() % other).toUShort()

/**
 * Modulo an unsigned short and a short to get an integer.
 */
inline operator fun UShort.rem(other: Short): UShort = (this.toInt() % other).toUShort()

/**
 * Modulo an unsigned short and an int to get a long.
 */
inline operator fun UShort.rem(other: Int): UInt = (this.toLong() % other).toUInt()

/**
 * Modulo an unsigned short and a long to get a long.
 */
inline operator fun UShort.rem(other: Long): ULong = (this.toLong() % other).toULong()


/**
 * Modulo an unsigned integer and an unsigned byte to get an integer.
 */
inline operator fun UInt.rem(other: Byte): UInt = (this.toLong() % other).toUInt()

/**
 * Modulo an unsigned integer and a short to get an integer.
 */
inline operator fun UInt.rem(other: Short): UInt = (this.toLong() % other).toUInt()

/**
 * Modulo an unsigned integer and an int to get a long.
 */
inline operator fun UInt.rem(other: Int): UInt = (this.toLong() % other).toUInt()

/**
 * Modulo an unsigned integer and a long to get a long.
 */
inline operator fun UInt.rem(other: Long): ULong = (this.toLong() % other).toULong()


/**
 * Modulo an unsigned long and an unsigned byte to get a long.
 */
inline operator fun ULong.rem(other: Byte): ULong = (this.toLong() % other).toULong()

/**
 * Modulo an unsigned long and a short to get a long.
 */
inline operator fun ULong.rem(other: Short): ULong = (this.toLong() % other).toULong()

/**
 * Modulo an unsigned long and an int to get a long.
 */
inline operator fun ULong.rem(other: Int): ULong = (this.toLong() % other).toULong()

/**
 * Modulo an unsigned long and a long to get a long.
 */
inline operator fun ULong.rem(other: Long): ULong = (this.toLong() % other).toULong()
