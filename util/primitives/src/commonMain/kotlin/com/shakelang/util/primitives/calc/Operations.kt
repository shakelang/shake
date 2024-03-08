@file:Suppress("NOTHING_TO_INLINE")

package com.shakelang.util.primitives.calc

import kotlin.math.abs

/**
 * Add a byte and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.plus(other: UByte): Int = this + other.toShort()

/**
 * Add a byte and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.plus(other: UShort): Int = this + other.toInt()

/**
 * Add a byte and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.plus(other: UInt): Long = this + other.toLong()

/**
 * Add a byte and an unsigned long to get a long.
 *
 * @param other The unsigned long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.plus(other: ULong): Long = this + other.toLong()

/**
 * Add a shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.plus(other: UByte): Int = this + other.toInt()

/**
 * Add a shorts and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.plus(other: UShort): Int = this + other.toInt()

/**
 * Add a shorts and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.plus(other: UInt): Long = this + other.toLong()

/**
 * Add a shorts and an unsigned long to get a long.
 *
 * @param other The unsigned long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.plus(other: ULong): Long = this + other.toLong()

/**
 * Add an integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.plus(other: UByte): Int = this + other.toInt()

/**
 * Add an integers and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.plus(other: UShort): Int = this + other.toInt()

/**
 * Add an integers and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.plus(other: UInt): Long = this + other.toLong()

/**
 * Add an integers and an unsigned long to get a long.
 *
 * @param other The unsigned long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.plus(other: ULong): Long = this + abs(other.toLong())

/**
 * Add a long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.plus(other: UByte): Long = this + other.toLong()

/**
 * Add a long and an unsigned shorts to get a long.
 *
 * @param other The unsigned shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.plus(other: UShort): Long = this + other.toLong()

/**
 * Add a long and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.plus(other: UInt): Long = this + other.toLong()

/**
 * Add a long and an unsigned long to get a long.
 *
 * @param other The unsigned long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.plus(other: ULong): Long = this + abs(other.toLong())

/**
 * Subtract a byte and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.minus(other: UByte): Int = this - other.toShort()

/**
 * Subtract a byte and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.minus(other: UShort): Int = this - other.toInt()

/**
 * Subtract a byte and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract a byte and an unsigned long to get a long.
 *
 * @param other The unsigned long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.minus(other: ULong): Long = this - other.toLong()

/**
 * Subtract a shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.minus(other: UByte): Int = this - other.toInt()

/**
 * Subtract a shorts and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.minus(other: UShort): Int = this - other.toInt()

/**
 * Subtract a shorts and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract a shorts and an unsigned long to get a long.
 *
 * @param other The unsigned long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.minus(other: ULong): Long = this - other.toLong()

/**
 * Subtract an integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.minus(other: UByte): Int = this - other.toInt()

/**
 * Subtract an integers and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.minus(other: UShort): Int = this - other.toInt()

/**
 * Subtract an integers and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract an integers and an unsigned long to get a long.
 *
 * @param other The unsigned long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.minus(other: ULong): Long = this - abs(other.toLong())

/**
 * Subtract a long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.minus(other: UByte): Long = this - other.toLong()

/**
 * Subtract a long and an unsigned shorts to get a long.
 *
 * @param other The unsigned shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.minus(other: UShort): Long = this - other.toLong()

/**
 * Subtract a long and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.minus(other: UInt): Long = this - other.toLong()

/**
 * Subtract a long and an unsigned long to get a long.
 *
 * @param other The unsigned long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.minus(other: ULong): Long = this - abs(other.toLong())

/**
 * Multiply a byte and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.times(other: UByte): Int = this * other.toShort()

/**
 * Multiply a byte and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.times(other: UShort): Int = this * other.toInt()

/**
 * Multiply a byte and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply a byte and an unsigned long to get a long.
 *
 * @param other The unsigned long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.times(other: ULong): Long = this * other.toLong()

/**
 * Multiply a shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.times(other: UByte): Int = this * other.toInt()

/**
 * Multiply a shorts and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.times(other: UShort): Int = this * other.toInt()

/**
 * Multiply a shorts and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply a shorts and an unsigned long to get a long.
 *
 * @param other The unsigned long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.times(other: ULong): Long = this * other.toLong()

/**
 * Multiply an integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.times(other: UByte): Int = this * other.toInt()

/**
 * Multiply an integers and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.times(other: UShort): Int = this * other.toInt()

/**
 * Multiply an integers and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply an integers and an unsigned long to get a long.
 *
 * @param other The unsigned long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.times(other: ULong): Long = this * abs(other.toLong())

/**
 * Multiply a long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.times(other: UByte): Long = this * other.toLong()

/**
 * Multiply a long and an unsigned shorts to get a long.
 *
 * @param other The unsigned shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.times(other: UShort): Long = this * other.toLong()

/**
 * Multiply a long and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.times(other: UInt): Long = this * other.toLong()

/**
 * Multiply a long and an unsigned long to get a long.
 *
 * @param other The unsigned long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.times(other: ULong): Long = this * abs(other.toLong())

/**
 * Divide a byte and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.div(other: UByte): Int = this / other.toShort()

/**
 * Divide a byte and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.div(other: UShort): Int = this / other.toInt()

/**
 * Divide a byte and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.div(other: UInt): Long = this / other.toLong()

/**
 * Divide a byte and an unsigned long to get a long.
 *
 * @param other The unsigned long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.div(other: ULong): Long = this / other.toLong()

/**
 * Divide a shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.div(other: UByte): Int = this / other.toInt()

/**
 * Divide a shorts and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.div(other: UShort): Int = this / other.toInt()

/**
 * Divide a shorts and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.div(other: UInt): Long = this / other.toLong()

/**
 * Divide a shorts and an unsigned long to get a long.
 *
 * @param other The unsigned long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.div(other: ULong): Long = this / other.toLong()

/**
 * Divide an integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.div(other: UByte): Int = this / other.toInt()

/**
 * Divide an integers and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.div(other: UShort): Int = this / other.toInt()

/**
 * Divide an integers and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.div(other: UInt): Long = this / other.toLong()

/**
 * Divide an integers and an unsigned long to get a long.
 *
 * @param other The unsigned long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.div(other: ULong): Long = this / abs(other.toLong())

/**
 * Divide a long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.div(other: UByte): Long = this / other.toLong()

/**
 * Divide a long and an unsigned shorts to get a long.
 *
 * @param other The unsigned shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.div(other: UShort): Long = this / other.toLong()

/**
 * Divide a long and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.div(other: UInt): Long = this / other.toLong()

/**
 * Divide a long and an unsigned long to get a long.
 *
 * @param other The unsigned long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.div(other: ULong): Long = this / abs(other.toLong())

/**
 * Modulo a byte and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.rem(other: UByte): Int = this % other.toShort()

/**
 * Modulo a byte and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.rem(other: UShort): Int = this % other.toInt()

/**
 * Modulo a byte and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo a byte and an unsigned long to get a long.
 *
 * @param other The unsigned long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Byte.rem(other: ULong): Long = this % other.toLong()

/**
 * Modulo a shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.rem(other: UByte): Int = this % other.toInt()

/**
 * Modulo a shorts and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.rem(other: UShort): Int = this % other.toInt()

/**
 * Modulo a shorts and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo a shorts and an unsigned long to get a long.
 *
 * @param other The unsigned long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Short.rem(other: ULong): Long = this % other.toLong()

/**
 * Modulo an integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.rem(other: UByte): Int = this % other.toInt()

/**
 * Modulo an integers and an unsigned shorts to get an integers.
 *
 * @param other The unsigned shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.rem(other: UShort): Int = this % other.toInt()

/**
 * Modulo an integers and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo an integers and an unsigned long to get a long.
 *
 * @param other The unsigned long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Int.rem(other: ULong): Long = this % abs(other.toLong())

/**
 * Modulo a long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.rem(other: UByte): Long = this % other.toLong()

/**
 * Modulo a long and an unsigned shorts to get a long.
 *
 * @param other The unsigned shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.rem(other: UShort): Long = this % other.toLong()

/**
 * Modulo a long and an unsigned integers to get a long.
 *
 * @param other The unsigned integers to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.rem(other: UInt): Long = this % other.toLong()

/**
 * Modulo a long and an unsigned long to get a long.
 *
 * @param other The unsigned long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun Long.rem(other: ULong): Long = this % abs(other.toLong())

/**
 * Add an unsigned byte and a byte to get a integers.
 *
 * @param other The byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.plus(other: Byte): Int = this.toShort() + other

/**
 * Add an unsigned byte and a shorts to get an integers.
 *
 * @param other The shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.plus(other: Short): Int = this.toInt() + other

/**
 * Add an unsigned byte and an int to get a long.
 *
 * @param other The int to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.plus(other: Int): Long = this.toLong() + other

/**
 * Add an unsigned byte and a long to get a long.
 *
 * @param other The long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.plus(other: Long): Long = this.toLong() + other

/**
 * Add an unsigned shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.plus(other: Byte): Int = this.toInt() + other

/**
 * Add an unsigned shorts and a shorts to get an integers.
 *
 * @param other The shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.plus(other: Short): Int = this.toInt() + other

/**
 * Add an unsigned shorts and an int to get a long.
 *
 * @param other The int to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.plus(other: Int): Long = this.toLong() + other

/**
 * Add an unsigned shorts and a long to get a long.
 *
 * @param other The long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.plus(other: Long): Long = this.toLong() + other

/**
 * Add an unsigned integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.plus(other: Byte): Long = this.toLong() + other

/**
 * Add an unsigned integers and a shorts to get an integers.
 *
 * @param other The shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.plus(other: Short): Long = this.toLong() + other

/**
 * Add an unsigned integers and an int to get a long.
 *
 * @param other The int to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.plus(other: Int): Long = this.toLong() + other

/**
 * Add an unsigned integers and a long to get a long.
 *
 * @param other The long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.plus(other: Long): Long = this.toLong() + other

/**
 * Add an unsigned long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.plus(other: Byte): Long = this.toLong() + other

/**
 * Add an unsigned long and a shorts to get a long.
 *
 * @param other The shorts to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.plus(other: Short): Long = this.toLong() + other

/**
 * Add an unsigned long and an int to get a long.
 *
 * @param other The int to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.plus(other: Int): Long = this.toLong() + other

/**
 * Add an unsigned long and a long to get a long.
 *
 * @param other The long to add
 * @return The sum of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.plus(other: Long): Long = this.toLong() + other

/**
 * Subtract an unsigned byte and a byte to get an integers.
 *
 * @param other The byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.minus(other: Byte): Int = this.toShort() - other

/**
 * Subtract an unsigned byte and a shorts to get an integers.
 *
 * @param other The shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.minus(other: Short): Int = this.toInt() - other

/**
 * Subtract an unsigned byte and an int to get a long.
 *
 * @param other The int to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.minus(other: Int): Long = this.toLong() - other

/**
 * Subtract an unsigned byte and a long to get a long.
 *
 * @param other The long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.minus(other: Long): Long = this.toLong() - other

/**
 * Subtract an unsigned shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.minus(other: Byte): Int = this.toInt() - other

/**
 * Subtract an unsigned shorts and a shorts to get an integers.
 *
 * @param other The shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.minus(other: Short): Int = this.toInt() - other

/**
 * Subtract an unsigned shorts and an int to get a long.
 *
 * @param other The int to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.minus(other: Int): Long = this.toLong() - other

/**
 * Subtract an unsigned shorts and a long to get a long.
 *
 * @param other The long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.minus(other: Long): Long = this.toLong() - other

/**
 * Subtract an unsigned integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.minus(other: Byte): Long = this.toLong() - other

/**
 * Subtract an unsigned integers and a shorts to get an integers.
 *
 * @param other The shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.minus(other: Short): Long = this.toLong() - other

/**
 * Subtract an unsigned integers and an int to get a long.
 *
 * @param other The int to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.minus(other: Int): Long = this.toLong() - other

/**
 * Subtract an unsigned integers and a long to get a long.
 *
 * @param other The long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.minus(other: Long): Long = this.toLong() - other

/**
 * Subtract an unsigned long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.minus(other: Byte): Long = this.toLong() - other

/**
 * Subtract an unsigned long and a shorts to get a long.
 *
 * @param other The shorts to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.minus(other: Short): Long = this.toLong() - other

/**
 * Subtract an unsigned long and an int to get a long.
 *
 * @param other The int to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.minus(other: Int): Long = this.toLong() - other

/**
 * Subtract an unsigned long and a long to get a long.
 *
 * @param other The long to subtract
 * @return The difference of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.minus(other: Long): Long = this.toLong() - other

/**
 * Multiply an unsigned byte and a byte to get an integers.
 *
 * @param other The byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.times(other: Byte): Int = this.toShort() * other

/**
 * Multiply an unsigned byte and a shorts to get an integers.
 *
 * @param other The shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.times(other: Short): Int = this.toInt() * other

/**
 * Multiply an unsigned byte and an int to get a long.
 *
 * @param other The int to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.times(other: Int): Long = this.toLong() * other

/**
 * Multiply an unsigned byte and a long to get a long.
 *
 * @param other The long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.times(other: Long): Long = this.toLong() * other

/**
 * Multiply an unsigned shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.times(other: Byte): Int = this.toInt() * other

/**
 * Multiply an unsigned shorts and a shorts to get an integers.
 *
 * @param other The shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.times(other: Short): Int = this.toInt() * other

/**
 * Multiply an unsigned shorts and an int to get a long.
 *
 * @param other The int to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.times(other: Int): Long = this.toLong() * other

/**
 * Multiply an unsigned shorts and a long to get a long.
 *
 * @param other The long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.times(other: Long): Long = this.toLong() * other

/**
 * Multiply an unsigned integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.times(other: Byte): Long = this.toLong() * other

/**
 * Multiply an unsigned integers and a shorts to get an integers.
 *
 * @param other The shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.times(other: Short): Long = this.toLong() * other

/**
 * Multiply an unsigned integers and an int to get a long.
 *
 * @param other The int to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.times(other: Int): Long = this.toLong() * other

/**
 * Multiply an unsigned integers and a long to get a long.
 *
 * @param other The long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.times(other: Long): Long = this.toLong() * other

/**
 * Multiply an unsigned long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.times(other: Byte): Long = this.toLong() * other

/**
 * Multiply an unsigned long and a shorts to get a long.
 *
 * @param other The shorts to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.times(other: Short): Long = this.toLong() * other

/**
 * Multiply an unsigned long and an int to get a long.
 *
 * @param other The int to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.times(other: Int): Long = this.toLong() * other

/**
 * Multiply an unsigned long and a long to get a long.
 *
 * @param other The long to multiply with
 * @return The product of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.times(other: Long): Long = this.toLong() * other

/**
 * Divide an unsigned byte and a byte to get a integers.
 *
 * @param other The byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.div(other: Byte): Int = this.toShort() / other

/**
 * Divide an unsigned byte and a shorts to get an integers.
 *
 * @param other The shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.div(other: Short): Int = this.toInt() / other

/**
 * Divide an unsigned byte and an int to get a long.
 *
 * @param other The int to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.div(other: Int): Long = this.toLong() / other

/**
 * Divide an unsigned byte and a long to get a long.
 *
 * @param other The long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.div(other: Long): Long = this.toLong() / other

/**
 * Divide an unsigned shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.div(other: Byte): Int = this.toInt() / other

/**
 * Divide an unsigned shorts and a shorts to get an integers.
 *
 * @param other The shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.div(other: Short): Int = this.toInt() / other

/**
 * Divide an unsigned shorts and an int to get a long.
 *
 * @param other The int to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.div(other: Int): Long = this.toLong() / other

/**
 * Divide an unsigned shorts and a long to get a long.
 *
 * @param other The long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.div(other: Long): Long = this.toLong() / other

/**
 * Divide an unsigned integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.div(other: Byte): Long = this.toLong() / other

/**
 * Divide an unsigned integers and a shorts to get an integers.
 *
 * @param other The shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.div(other: Short): Long = this.toLong() / other

/**
 * Divide an unsigned integers and an int to get a long.
 *
 * @param other The int to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.div(other: Int): Long = this.toLong() / other

/**
 * Divide an unsigned integers and a long to get a long.
 *
 * @param other The long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.div(other: Long): Long = this.toLong() / other

/**
 * Divide an unsigned long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.div(other: Byte): Long = this.toLong() / other

/**
 * Divide an unsigned long and a shorts to get a long.
 *
 * @param other The shorts to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.div(other: Short): Long = this.toLong() / other

/**
 * Divide an unsigned long and an int to get a long.
 *
 * @param other The int to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.div(other: Int): Long = this.toLong() / other

/**
 * Divide an unsigned long and a long to get a long.
 *
 * @param other The long to divide with
 * @return The quotient of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.div(other: Long): Long = this.toLong() / other

/**
 * Modulo an unsigned byte and a byte to get a integers.
 *
 * @param other The byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.rem(other: Byte): Int = this.toShort() % other

/**
 * Modulo an unsigned byte and a shorts to get an integers.
 *
 * @param other The shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.rem(other: Short): Int = this.toInt() % other

/**
 * Modulo an unsigned byte and an int to get a long.
 *
 * @param other The int to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.rem(other: Int): Long = this.toLong() % other

/**
 * Modulo an unsigned byte and a long to get a long.
 *
 * @param other The long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UByte.rem(other: Long): Long = this.toLong() % other

/**
 * Modulo an unsigned shorts and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.rem(other: Byte): Int = this.toInt() % other

/**
 * Modulo an unsigned shorts and a shorts to get an integers.
 *
 * @param other The shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.rem(other: Short): Int = this.toInt() % other

/**
 * Modulo an unsigned shorts and an int to get a long.
 *
 * @param other The int to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.rem(other: Int): Long = this.toLong() % other

/**
 * Modulo an unsigned shorts and a long to get a long.
 *
 * @param other The long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UShort.rem(other: Long): Long = this.toLong() % other

/**
 * Modulo an unsigned integers and an unsigned byte to get an integers.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.rem(other: Byte): Long = this.toLong() % other

/**
 * Modulo an unsigned integers and a shorts to get an integers.
 *
 * @param other The shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.rem(other: Short): Long = this.toLong() % other

/**
 * Modulo an unsigned integers and an int to get a long.
 *
 * @param other The int to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.rem(other: Int): Long = this.toLong() % other

/**
 * Modulo an unsigned integers and a long to get a long.
 *
 * @param other The long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun UInt.rem(other: Long): Long = this.toLong() % other

/**
 * Modulo an unsigned long and an unsigned byte to get a long.
 *
 * @param other The unsigned byte to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.rem(other: Byte): Long = this.toLong() % other

/**
 * Modulo an unsigned long and a shorts to get a long.
 *
 * @param other The shorts to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.rem(other: Short): Long = this.toLong() % other

/**
 * Modulo an unsigned long and an int to get a long.
 *
 * @param other The int to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.rem(other: Int): Long = this.toLong() % other

/**
 * Modulo an unsigned long and a long to get a long.
 *
 * @param other The long to modulo with
 * @return The modulo of the two numbers
 *
 * @since 0.1.0
 * @version 0.1.1
 */
inline operator fun ULong.rem(other: Long): Long = this.toLong() % other
