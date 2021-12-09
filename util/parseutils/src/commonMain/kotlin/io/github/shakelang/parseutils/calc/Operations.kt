@file:Suppress("NOTHING_TO_INLINE")
package io.github.shakelang.parseutils.calc

import kotlin.math.abs

inline operator fun Byte.plus(other: UByte): Int = this + other.toShort()
inline operator fun Byte.plus(other: UShort): Int = this + other.toInt()
inline operator fun Byte.plus(other: UInt): Long = this + other.toLong()
inline operator fun Byte.plus(other: ULong): Long = this + other.toLong()

inline operator fun Short.plus(other: UByte): Int = this + other.toInt()
inline operator fun Short.plus(other: UShort): Int = this + other.toInt()
inline operator fun Short.plus(other: UInt): Long = this + other.toLong()
inline operator fun Short.plus(other: ULong): Long = this + other.toLong()

inline operator fun Int.plus(other: UByte): Int = this + other.toInt()
inline operator fun Int.plus(other: UShort): Int = this + other.toInt()
inline operator fun Int.plus(other: UInt): Long = this + other.toLong()
inline operator fun Int.plus(other: ULong): Long = this + abs(other.toLong())

inline operator fun Long.plus(other: UByte): Long = this + other.toLong()
inline operator fun Long.plus(other: UShort): Long = this + other.toLong()
inline operator fun Long.plus(other: UInt): Long = this + other.toLong()
inline operator fun Long.plus(other: ULong): Long = this + abs(other.toLong())


inline operator fun Byte.minus(other: UByte): Int = this - other.toShort()
inline operator fun Byte.minus(other: UShort): Int = this - other.toInt()
inline operator fun Byte.minus(other: UInt): Long = this - other.toLong()
inline operator fun Byte.minus(other: ULong): Long = this - other.toLong()

inline operator fun Short.minus(other: UByte): Int = this - other.toInt()
inline operator fun Short.minus(other: UShort): Int = this - other.toInt()
inline operator fun Short.minus(other: UInt): Long = this - other.toLong()
inline operator fun Short.minus(other: ULong): Long = this - other.toLong()

inline operator fun Int.minus(other: UByte): Int = this - other.toInt()
inline operator fun Int.minus(other: UShort): Int = this - other.toInt()
inline operator fun Int.minus(other: UInt): Long = this - other.toLong()
inline operator fun Int.minus(other: ULong): Long = this - abs(other.toLong())

inline operator fun Long.minus(other: UByte): Long = this - other.toLong()
inline operator fun Long.minus(other: UShort): Long = this - other.toLong()
inline operator fun Long.minus(other: UInt): Long = this - other.toLong()
inline operator fun Long.minus(other: ULong): Long = this - abs(other.toLong())


inline operator fun Byte.times(other: UByte): Int = this * other.toShort()
inline operator fun Byte.times(other: UShort): Int = this * other.toInt()
inline operator fun Byte.times(other: UInt): Long = this * other.toLong()
inline operator fun Byte.times(other: ULong): Long = this * other.toLong()

inline operator fun Short.times(other: UByte): Int = this * other.toInt()
inline operator fun Short.times(other: UShort): Int = this * other.toInt()
inline operator fun Short.times(other: UInt): Long = this * other.toLong()
inline operator fun Short.times(other: ULong): Long = this * other.toLong()

inline operator fun Int.times(other: UByte): Int = this * other.toInt()
inline operator fun Int.times(other: UShort): Int = this * other.toInt()
inline operator fun Int.times(other: UInt): Long = this * other.toLong()
inline operator fun Int.times(other: ULong): Long = this * abs(other.toLong())

inline operator fun Long.times(other: UByte): Long = this * other.toLong()
inline operator fun Long.times(other: UShort): Long = this * other.toLong()
inline operator fun Long.times(other: UInt): Long = this * other.toLong()
inline operator fun Long.times(other: ULong): Long = this * abs(other.toLong())


inline operator fun Byte.div(other: UByte): Int = this / other.toShort()
inline operator fun Byte.div(other: UShort): Int = this / other.toInt()
inline operator fun Byte.div(other: UInt): Long = this / other.toLong()
inline operator fun Byte.div(other: ULong): Long = this / other.toLong()

inline operator fun Short.div(other: UByte): Int = this / other.toInt()
inline operator fun Short.div(other: UShort): Int = this / other.toInt()
inline operator fun Short.div(other: UInt): Long = this / other.toLong()
inline operator fun Short.div(other: ULong): Long = this / other.toLong()

inline operator fun Int.div(other: UByte): Int = this / other.toInt()
inline operator fun Int.div(other: UShort): Int = this / other.toInt()
inline operator fun Int.div(other: UInt): Long = this / other.toLong()
inline operator fun Int.div(other: ULong): Long = this / abs(other.toLong())

inline operator fun Long.div(other: UByte): Long = this / other.toLong()
inline operator fun Long.div(other: UShort): Long = this / other.toLong()
inline operator fun Long.div(other: UInt): Long = this / other.toLong()
inline operator fun Long.div(other: ULong): Long = this / abs(other.toLong())


inline operator fun Byte.rem(other: UByte): Int = this % other.toShort()
inline operator fun Byte.rem(other: UShort): Int = this % other.toInt()
inline operator fun Byte.rem(other: UInt): Long = this % other.toLong()
inline operator fun Byte.rem(other: ULong): Long = this % other.toLong()

inline operator fun Short.rem(other: UByte): Int = this % other.toInt()
inline operator fun Short.rem(other: UShort): Int = this % other.toInt()
inline operator fun Short.rem(other: UInt): Long = this % other.toLong()
inline operator fun Short.rem(other: ULong): Long = this % other.toLong()

inline operator fun Int.rem(other: UByte): Int = this % other.toInt()
inline operator fun Int.rem(other: UShort): Int = this % other.toInt()
inline operator fun Int.rem(other: UInt): Long = this % other.toLong()
inline operator fun Int.rem(other: ULong): Long = this % abs(other.toLong())

inline operator fun Long.rem(other: UByte): Long = this % other.toLong()
inline operator fun Long.rem(other: UShort): Long = this % other.toLong()
inline operator fun Long.rem(other: UInt): Long = this % other.toLong()
inline operator fun Long.rem(other: ULong): Long = this % abs(other.toLong())



inline operator fun UByte.plus(other: Byte): UByte = (this.toShort() + other).toUByte()
inline operator fun UByte.plus(other: Short): UByte = (this.toInt() + other).toUByte()
inline operator fun UByte.plus(other: Int): ULong = (this.toLong() + other).toULong()
inline operator fun UByte.plus(other: Long): ULong = (this.toLong() + other).toULong()

inline operator fun UShort.plus(other: Byte): UShort = (this.toInt() + other).toUShort()
inline operator fun UShort.plus(other: Short): UShort = (this.toInt() + other).toUShort()
inline operator fun UShort.plus(other: Int): ULong = (this.toLong() + other).toULong()
inline operator fun UShort.plus(other: Long): ULong = (this.toLong() + other).toULong()

inline operator fun UInt.plus(other: Byte): UInt = (this.toLong() + other).toUInt()
inline operator fun UInt.plus(other: Short): UInt = (this.toLong() + other).toUInt()
inline operator fun UInt.plus(other: Int): ULong = (this.toLong() + other).toULong()
inline operator fun UInt.plus(other: Long): ULong = (this.toLong() + other).toULong()

inline operator fun ULong.plus(other: Byte): ULong = (this.toLong() + other).toULong()
inline operator fun ULong.plus(other: Short): ULong = (this.toLong() + other).toULong()
inline operator fun ULong.plus(other: Int): ULong = (this.toLong() + other).toULong()
inline operator fun ULong.plus(other: Long): ULong = (this.toLong() + other).toULong()


inline operator fun UByte.minus(other: Byte): UByte = (this.toShort() - other).toUByte()
inline operator fun UByte.minus(other: Short): UByte = (this.toInt() - other).toUByte()
inline operator fun UByte.minus(other: Int): ULong = (this.toLong() - other).toULong()
inline operator fun UByte.minus(other: Long): ULong = (this.toLong() - other).toULong()

inline operator fun UShort.minus(other: Byte): UShort = (this.toInt() - other).toUShort()
inline operator fun UShort.minus(other: Short): UShort = (this.toInt() - other).toUShort()
inline operator fun UShort.minus(other: Int): ULong = (this.toLong() - other).toULong()
inline operator fun UShort.minus(other: Long): ULong = (this.toLong() - other).toULong()

inline operator fun UInt.minus(other: Byte): UInt = (this.toLong() - other).toUInt()
inline operator fun UInt.minus(other: Short): UInt = (this.toLong() - other).toUInt()
inline operator fun UInt.minus(other: Int): ULong = (this.toLong() - other).toULong()
inline operator fun UInt.minus(other: Long): ULong = (this.toLong() - other).toULong()

inline operator fun ULong.minus(other: Byte): ULong = (this.toLong() - other).toULong()
inline operator fun ULong.minus(other: Short): ULong = (this.toLong() - other).toULong()
inline operator fun ULong.minus(other: Int): ULong = (this.toLong() - other).toULong()
inline operator fun ULong.minus(other: Long): ULong = (this.toLong() - other).toULong()


inline operator fun UByte.times(other: Byte): UByte = (this.toShort() * other).toUByte()
inline operator fun UByte.times(other: Short): UShort = (this.toInt() * other).toUShort()
inline operator fun UByte.times(other: Int): UInt = (this.toLong() * other).toUInt()
inline operator fun UByte.times(other: Long): ULong = (this.toLong() * other).toULong()

inline operator fun UShort.times(other: Byte): UShort = (this.toInt() * other).toUShort()
inline operator fun UShort.times(other: Short): UShort = (this.toInt() * other).toUShort()
inline operator fun UShort.times(other: Int): UInt = (this.toLong() * other).toUInt()
inline operator fun UShort.times(other: Long): ULong = (this.toLong() * other).toULong()

inline operator fun UInt.times(other: Byte): UInt = (this.toLong() * other).toUInt()
inline operator fun UInt.times(other: Short): UInt = (this.toLong() * other).toUInt()
inline operator fun UInt.times(other: Int): UInt = (this.toLong() * other).toUInt()
inline operator fun UInt.times(other: Long): ULong = (this.toLong() * other).toULong()

inline operator fun ULong.times(other: Byte): ULong = (this.toLong() * other).toULong()
inline operator fun ULong.times(other: Short): ULong = (this.toLong() * other).toULong()
inline operator fun ULong.times(other: Int): ULong = (this.toLong() * other).toULong()
inline operator fun ULong.times(other: Long): ULong = (this.toLong() * other).toULong()


inline operator fun UByte.div(other: Byte): UByte = (this.toShort() / other).toUByte()
inline operator fun UByte.div(other: Short): UShort = (this.toInt() / other).toUShort()
inline operator fun UByte.div(other: Int): UInt = (this.toLong() / other).toUInt()
inline operator fun UByte.div(other: Long): ULong = (this.toLong() / other).toULong()

inline operator fun UShort.div(other: Byte): UShort = (this.toInt() / other).toUShort()
inline operator fun UShort.div(other: Short): UShort = (this.toInt() / other).toUShort()
inline operator fun UShort.div(other: Int): UInt = (this.toLong() / other).toUInt()
inline operator fun UShort.div(other: Long): ULong = (this.toLong() / other).toULong()

inline operator fun UInt.div(other: Byte): UInt = (this.toLong() / other).toUInt()
inline operator fun UInt.div(other: Short): UInt = (this.toLong() / other).toUInt()
inline operator fun UInt.div(other: Int): UInt = (this.toLong() / other).toUInt()
inline operator fun UInt.div(other: Long): ULong = (this.toLong() / other).toULong()

inline operator fun ULong.div(other: Byte): ULong = (this.toLong() / other).toULong()
inline operator fun ULong.div(other: Short): ULong = (this.toLong() / other).toULong()
inline operator fun ULong.div(other: Int): ULong = (this.toLong() / other).toULong()
inline operator fun ULong.div(other: Long): ULong = (this.toLong() / other).toULong()


inline operator fun UByte.rem(other: Byte): UByte = (this.toShort() % other).toUByte()
inline operator fun UByte.rem(other: Short): UShort = (this.toInt() % other).toUShort()
inline operator fun UByte.rem(other: Int): UInt = (this.toLong() % other).toUInt()
inline operator fun UByte.rem(other: Long): ULong = (this.toLong() % other).toULong()

inline operator fun UShort.rem(other: Byte): UShort = (this.toInt() % other).toUShort()
inline operator fun UShort.rem(other: Short): UShort = (this.toInt() % other).toUShort()
inline operator fun UShort.rem(other: Int): UInt = (this.toLong() % other).toUInt()
inline operator fun UShort.rem(other: Long): ULong = (this.toLong() % other).toULong()

inline operator fun UInt.rem(other: Byte): UInt = (this.toLong() % other).toUInt()
inline operator fun UInt.rem(other: Short): UInt = (this.toLong() % other).toUInt()
inline operator fun UInt.rem(other: Int): UInt = (this.toLong() % other).toUInt()
inline operator fun UInt.rem(other: Long): ULong = (this.toLong() % other).toULong()

inline operator fun ULong.rem(other: Byte): ULong = (this.toLong() % other).toULong()
inline operator fun ULong.rem(other: Short): ULong = (this.toLong() % other).toULong()
inline operator fun ULong.rem(other: Int): ULong = (this.toLong() % other).toULong()
inline operator fun ULong.rem(other: Long): ULong = (this.toLong() % other).toULong()