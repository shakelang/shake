package com.shakelang.util.primitives.atomic

import com.shakelang.util.primitives.calc.shl
import com.shakelang.util.primitives.calc.shr
import com.shakelang.util.primitives.calc.ushr
import kotlin.math.pow

// Long Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicLongOperationImplementations {

    // Long Operations
    fun plus(source1: AtomicLong, other: Byte): Long = (source1.longValue + other)
    fun plus(source1: AtomicLong, other: AtomicByte): Long = (source1.longValue + other.byteValue)
    fun plus(source1: AtomicLong, other: Short): Long = (source1.longValue + other)
    fun plus(source1: AtomicLong, other: AtomicShort): Long = (source1.longValue + other.shortValue)
    fun plus(source1: AtomicLong, other: Int): Long = (source1.longValue + other)
    fun plus(source1: AtomicLong, other: AtomicInt): Long = (source1.longValue + other.intValue)
    fun plus(source1: AtomicLong, other: Long): Long = (source1.longValue + other)
    fun plus(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue + source2.longValue)

    fun minus(source1: AtomicLong, other: Byte): Long = (source1.longValue - other)
    fun minus(source1: AtomicLong, other: AtomicByte): Long = (source1.longValue - other.byteValue)
    fun minus(source1: AtomicLong, other: Short): Long = (source1.longValue - other)
    fun minus(source1: AtomicLong, other: AtomicShort): Long = (source1.longValue - other.shortValue)
    fun minus(source1: AtomicLong, other: Int): Long = (source1.longValue - other)
    fun minus(source1: AtomicLong, other: AtomicInt): Long = (source1.longValue - other.intValue)
    fun minus(source1: AtomicLong, other: Long): Long = (source1.longValue - other)
    fun minus(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue - source2.longValue)

    fun times(source1: AtomicLong, other: Byte): Long = (source1.longValue * other)
    fun times(source1: AtomicLong, other: AtomicByte): Long = (source1.longValue * other.byteValue)
    fun times(source1: AtomicLong, other: Short): Long = (source1.longValue * other)
    fun times(source1: AtomicLong, other: AtomicShort): Long = (source1.longValue * other.shortValue)
    fun times(source1: AtomicLong, other: Int): Long = (source1.longValue * other)
    fun times(source1: AtomicLong, other: AtomicInt): Long = (source1.longValue * other.intValue)
    fun times(source1: AtomicLong, other: Long): Long = (source1.longValue * other)
    fun times(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue * source2.longValue)

    fun div(source1: AtomicLong, other: Byte): Long = (source1.longValue / other)
    fun div(source1: AtomicLong, other: AtomicByte): Long = (source1.longValue / other.byteValue)
    fun div(source1: AtomicLong, other: Short): Long = (source1.longValue / other)
    fun div(source1: AtomicLong, other: AtomicShort): Long = (source1.longValue / other.shortValue)
    fun div(source1: AtomicLong, other: Int): Long = (source1.longValue / other)
    fun div(source1: AtomicLong, other: AtomicInt): Long = (source1.longValue / other.intValue)
    fun div(source1: AtomicLong, other: Long): Long = (source1.longValue / other)
    fun div(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue / source2.longValue)

    fun rem(source1: AtomicLong, other: Byte): Long = (source1.longValue % other)
    fun rem(source1: AtomicLong, other: AtomicByte): Long = (source1.longValue % other.byteValue)
    fun rem(source1: AtomicLong, other: Short): Long = (source1.longValue % other)
    fun rem(source1: AtomicLong, other: AtomicShort): Long = (source1.longValue % other.shortValue)
    fun rem(source1: AtomicLong, other: Int): Long = (source1.longValue % other)
    fun rem(source1: AtomicLong, other: AtomicInt): Long = (source1.longValue % other.intValue)
    fun rem(source1: AtomicLong, other: Long): Long = (source1.longValue % other)
    fun rem(source1: AtomicLong, source2: AtomicLong): Long = (source1.longValue % source2.longValue)

    fun pow(source1: AtomicLong, other: Byte): Long = source1.longValue.toDouble().pow(other.toDouble()).toLong()
    fun pow(source1: AtomicLong, other: AtomicByte): Long = source1.longValue.toDouble().pow(other.byteValue.toDouble()).toLong()
    fun pow(source1: AtomicLong, other: Short): Long = source1.longValue.toDouble().pow(other.toDouble()).toLong()
    fun pow(source1: AtomicLong, other: AtomicShort): Long = source1.longValue.toDouble().pow(other.shortValue.toDouble()).toLong()
    fun pow(source1: AtomicLong, other: Int): Long = source1.longValue.toDouble().pow(other.toDouble()).toLong()
    fun pow(source1: AtomicLong, other: AtomicInt): Long = source1.longValue.toDouble().pow(other.intValue.toDouble()).toLong()
    fun pow(source1: AtomicLong, other: Long): Long = source1.longValue.toDouble().pow(other.toDouble()).toLong()
    fun pow(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue.toDouble().pow(source2.longValue.toDouble()).toLong()

    fun and(source1: AtomicLong, other: Long): Long = source1.longValue and other
    fun and(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue and source2.longValue

    fun or(source1: AtomicLong, other: Long): Long = source1.longValue or other
    fun or(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue or source2.longValue

    fun xor(source1: AtomicLong, other: Long): Long = source1.longValue xor other
    fun xor(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue xor source2.longValue

    fun shl(source1: AtomicLong, other: Long): Long = source1.longValue shl other
    fun shl(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue shl source2.longValue

    fun shr(source1: AtomicLong, other: Long): Long = source1.longValue shr other
    fun shr(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue shr source2.longValue

    fun ushr(source1: AtomicLong, other: Long): Long = source1.longValue ushr other
    fun ushr(source1: AtomicLong, source2: AtomicLong): Long = source1.longValue ushr source2.longValue

    fun inv(source1: AtomicLong): Long = source1.longValue.inv()
    fun negate(source1: AtomicLong): Long = (-source1.longValue)

    val longPlusByte: BinaryOperation<AtomicLong, Byte, Long> = ::plus
    val longPlusAtomicByte: BinaryOperation<AtomicLong, AtomicByte, Long> = ::plus
    val longPlusShort: BinaryOperation<AtomicLong, Short, Long> = ::plus
    val longPlusAtomicShort: BinaryOperation<AtomicLong, AtomicShort, Long> = ::plus
    val longPlusInt: BinaryOperation<AtomicLong, Int, Long> = ::plus
    val longPlusAtomicInt: BinaryOperation<AtomicLong, AtomicInt, Long> = ::plus
    val longPlusLong: BinaryOperation<AtomicLong, Long, Long> = ::plus
    val longPlusAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::plus

    val longMinusByte: BinaryOperation<AtomicLong, Byte, Long> = ::minus
    val longMinusAtomicByte: BinaryOperation<AtomicLong, AtomicByte, Long> = ::minus
    val longMinusShort: BinaryOperation<AtomicLong, Short, Long> = ::minus
    val longMinusAtomicShort: BinaryOperation<AtomicLong, AtomicShort, Long> = ::minus
    val longMinusInt: BinaryOperation<AtomicLong, Int, Long> = ::minus
    val longMinusAtomicInt: BinaryOperation<AtomicLong, AtomicInt, Long> = ::minus
    val longMinusLong: BinaryOperation<AtomicLong, Long, Long> = ::minus
    val longMinusAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::minus

    val longTimesByte: BinaryOperation<AtomicLong, Byte, Long> = ::times
    val longTimesAtomicByte: BinaryOperation<AtomicLong, AtomicByte, Long> = ::times
    val longTimesShort: BinaryOperation<AtomicLong, Short, Long> = ::times
    val longTimesAtomicShort: BinaryOperation<AtomicLong, AtomicShort, Long> = ::times
    val longTimesInt: BinaryOperation<AtomicLong, Int, Long> = ::times
    val longTimesAtomicInt: BinaryOperation<AtomicLong, AtomicInt, Long> = ::times
    val longTimesLong: BinaryOperation<AtomicLong, Long, Long> = ::times
    val longTimesAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::times

    val longDivByte: BinaryOperation<AtomicLong, Byte, Long> = ::div
    val longDivAtomicByte: BinaryOperation<AtomicLong, AtomicByte, Long> = ::div
    val longDivShort: BinaryOperation<AtomicLong, Short, Long> = ::div
    val longDivAtomicShort: BinaryOperation<AtomicLong, AtomicShort, Long> = ::div
    val longDivInt: BinaryOperation<AtomicLong, Int, Long> = ::div
    val longDivAtomicInt: BinaryOperation<AtomicLong, AtomicInt, Long> = ::div
    val longDivLong: BinaryOperation<AtomicLong, Long, Long> = ::div
    val longDivAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::div

    val longRemByte: BinaryOperation<AtomicLong, Byte, Long> = ::rem
    val longRemAtomicByte: BinaryOperation<AtomicLong, AtomicByte, Long> = ::rem
    val longRemShort: BinaryOperation<AtomicLong, Short, Long> = ::rem
    val longRemAtomicShort: BinaryOperation<AtomicLong, AtomicShort, Long> = ::rem
    val longRemInt: BinaryOperation<AtomicLong, Int, Long> = ::rem
    val longRemAtomicInt: BinaryOperation<AtomicLong, AtomicInt, Long> = ::rem
    val longRemLong: BinaryOperation<AtomicLong, Long, Long> = ::rem
    val longRemAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::rem

    val longPowByte: BinaryOperation<AtomicLong, Byte, Long> = ::pow
    val longPowAtomicByte: BinaryOperation<AtomicLong, AtomicByte, Long> = ::pow
    val longPowShort: BinaryOperation<AtomicLong, Short, Long> = ::pow
    val longPowAtomicShort: BinaryOperation<AtomicLong, AtomicShort, Long> = ::pow
    val longPowInt: BinaryOperation<AtomicLong, Int, Long> = ::pow
    val longPowAtomicInt: BinaryOperation<AtomicLong, AtomicInt, Long> = ::pow
    val longPowLong: BinaryOperation<AtomicLong, Long, Long> = ::pow
    val longPowAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::pow

    val longAndLong: BinaryOperation<AtomicLong, Long, Long> = ::and
    val longAndAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::and
    val longOrLong: BinaryOperation<AtomicLong, Long, Long> = ::or
    val longOrAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::or
    val longXorLong: BinaryOperation<AtomicLong, Long, Long> = ::xor
    val longXorAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::xor
    val longShlLong: BinaryOperation<AtomicLong, Long, Long> = ::shl
    val longShlAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::shl
    val longShrLong: BinaryOperation<AtomicLong, Long, Long> = ::shr
    val longShrAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::shr
    val longUshrLong: BinaryOperation<AtomicLong, Long, Long> = ::ushr
    val longUshrAtomicLong: BinaryOperation<AtomicLong, AtomicLong, Long> = ::ushr
    val longInv: UnaryOperation<AtomicLong, Long> = ::inv
    val longNegate: UnaryOperation<AtomicLong, Long> = ::negate

    fun transformPlus(source1: AtomicLong, other: Byte): AtomicLong = transform1(source1, other, longPlusByte, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: AtomicByte): AtomicLong = transform1(source1, other, longPlusAtomicByte, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: Short): AtomicLong = transform1(source1, other, longPlusShort, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: AtomicShort): AtomicLong = transform1(source1, other, longPlusAtomicShort, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: Int): AtomicLong = transform1(source1, other, longPlusInt, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: AtomicInt): AtomicLong = transform1(source1, other, longPlusAtomicInt, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longPlusLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longPlusAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformMinus(source1: AtomicLong, other: Byte): AtomicLong = transform1(source1, other, longMinusByte, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: AtomicByte): AtomicLong = transform1(source1, other, longMinusAtomicByte, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: Short): AtomicLong = transform1(source1, other, longMinusShort, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: AtomicShort): AtomicLong = transform1(source1, other, longMinusAtomicShort, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: Int): AtomicLong = transform1(source1, other, longMinusInt, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: AtomicInt): AtomicLong = transform1(source1, other, longMinusAtomicInt, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longMinusLong, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longMinusAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformTimes(source1: AtomicLong, other: Byte): AtomicLong = transform1(source1, other, longTimesByte, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: AtomicByte): AtomicLong = transform1(source1, other, longTimesAtomicByte, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: Short): AtomicLong = transform1(source1, other, longTimesShort, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: AtomicShort): AtomicLong = transform1(source1, other, longTimesAtomicShort, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: Int): AtomicLong = transform1(source1, other, longTimesInt, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: AtomicInt): AtomicLong = transform1(source1, other, longTimesAtomicInt, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longTimesLong, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longTimesAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformDiv(source1: AtomicLong, other: Byte): AtomicLong = transform1(source1, other, longDivByte, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: AtomicByte): AtomicLong = transform1(source1, other, longDivAtomicByte, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: Short): AtomicLong = transform1(source1, other, longDivShort, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: AtomicShort): AtomicLong = transform1(source1, other, longDivAtomicShort, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: Int): AtomicLong = transform1(source1, other, longDivInt, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: AtomicInt): AtomicLong = transform1(source1, other, longDivAtomicInt, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longDivLong, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longDivAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformRem(source1: AtomicLong, other: Byte): AtomicLong = transform1(source1, other, longRemByte, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: AtomicByte): AtomicLong = transform1(source1, other, longRemAtomicByte, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: Short): AtomicLong = transform1(source1, other, longRemShort, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: AtomicShort): AtomicLong = transform1(source1, other, longRemAtomicShort, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: Int): AtomicLong = transform1(source1, other, longRemInt, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: AtomicInt): AtomicLong = transform1(source1, other, longRemAtomicInt, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longRemLong, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longRemAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformPow(source1: AtomicLong, other: Byte): AtomicLong = transform1(source1, other, longPowByte, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: AtomicByte): AtomicLong = transform1(source1, other, longPowAtomicByte, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: Short): AtomicLong = transform1(source1, other, longPowShort, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: AtomicShort): AtomicLong = transform1(source1, other, longPowAtomicShort, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: Int): AtomicLong = transform1(source1, other, longPowInt, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: AtomicInt): AtomicLong = transform1(source1, other, longPowAtomicInt, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longPowLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longPowAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformAnd(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longAndLong, AutoUpdateAbleAtomicLong.creator)
    fun transformAnd(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longAndAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformOr(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longOrLong, AutoUpdateAbleAtomicLong.creator)
    fun transformOr(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longOrAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformXor(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longXorLong, AutoUpdateAbleAtomicLong.creator)
    fun transformXor(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longXorAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformShl(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longShlLong, AutoUpdateAbleAtomicLong.creator)
    fun transformShl(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longShlAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformShr(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longShrLong, AutoUpdateAbleAtomicLong.creator)
    fun transformShr(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longShrAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformUshr(source1: AtomicLong, other: Long): AtomicLong = transform1(source1, other, longUshrLong, AutoUpdateAbleAtomicLong.creator)
    fun transformUshr(source1: AtomicLong, other: AtomicLong): AtomicLong = transform2(source1, other, longUshrAtomicLong, AutoUpdateAbleAtomicLong.creator)

    fun transformInv(source1: AtomicLong): AtomicLong = transform1(source1, longInv, AutoUpdateAbleAtomicLong.creator)
    fun transformNegate(source1: AtomicLong): AtomicLong = transform1(source1, longNegate, AutoUpdateAbleAtomicLong.creator)
}

/**
 * An atomic long value
 */
interface AtomicLong : AtomicValue {

    /**
     * The value of the atomic long
     */
    val longValue: Long

    /**
     * Get the value of the atomic long
     */
    fun get(): Long

    /**
     * Add another byte to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other byte to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Byte) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicByte] to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicByte] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicByte) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another short to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other short to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Short) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicShort] to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicShort] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicShort) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another int to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other int to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Int) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicInt] to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicInt] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicInt) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another long to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Long) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicLong] to this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicLong) = AtomicLongOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another byte from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other byte to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Byte) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicByte] from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicByte] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicByte) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another short from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other short to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Short) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicShort] from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicShort] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicShort) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another int from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other int to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Int) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicInt] from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicInt] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicInt) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another long from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Long) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicLong] from this long (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicLong) = AtomicLongOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this long with another byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other byte to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Byte) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another [AtomicByte] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicByte] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicByte) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another short (returns a new [AtomicLong] that automatically updates)
     * @param other The other short to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Short) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another [AtomicShort] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicShort] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicShort) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another int (returns a new [AtomicLong] that automatically updates)
     * @param other The other int to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Int) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another [AtomicInt] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicInt] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicInt) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Long) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicLong) = AtomicLongOperationImplementations.transformTimes(this, other)

    /**
     * Divide this long by another byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other byte to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Byte) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another [AtomicByte] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicByte] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicByte) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another short (returns a new [AtomicLong] that automatically updates)
     * @param other The other short to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Short) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another [AtomicShort] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicShort] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicShort) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another int (returns a new [AtomicLong] that automatically updates)
     * @param other The other int to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Int) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another [AtomicInt] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicInt] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicInt) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Long) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Divide this long by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicLong) = AtomicLongOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this long by another byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other byte to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Byte) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another [AtomicByte] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicByte] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicByte) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another short (returns a new [AtomicLong] that automatically updates)
     * @param other The other short to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Short) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another [AtomicShort] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicShort] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicShort) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another int (returns a new [AtomicLong] that automatically updates)
     * @param other The other int to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Int) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another [AtomicInt] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicInt] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicInt) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Long) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Modulo this long by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicLong) = AtomicLongOperationImplementations.transformRem(this, other)

    /**
     * Power this long by another byte (returns a new [AtomicLong] that automatically updates)
     * @param other The other byte to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: Byte) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another [AtomicByte] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicByte] to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: AtomicByte) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another short (returns a new [AtomicLong] that automatically updates)
     * @param other The other short to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: Short) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another [AtomicShort] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicShort] to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: AtomicShort) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another int (returns a new [AtomicLong] that automatically updates)
     * @param other The other int to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: Int) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another [AtomicInt] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicInt] to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: AtomicInt) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: Long) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * Power this long by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: AtomicLong) = AtomicLongOperationImplementations.transformPow(this, other)

    /**
     * And this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to and with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun and(other: Long) = AtomicLongOperationImplementations.transformAnd(this, other)

    /**
     * And this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to and with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun and(other: AtomicLong) = AtomicLongOperationImplementations.transformAnd(this, other)

    /**
     * Or this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to or with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun or(other: Long) = AtomicLongOperationImplementations.transformOr(this, other)

    /**
     * Or this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to or with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun or(other: AtomicLong) = AtomicLongOperationImplementations.transformOr(this, other)

    /**
     * Xor this long with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to xor with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun xor(other: Long) = AtomicLongOperationImplementations.transformXor(this, other)

    /**
     * Xor this long with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to xor with
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun xor(other: AtomicLong) = AtomicLongOperationImplementations.transformXor(this, other)

    /**
     * Shift this long left by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to shift left by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shl(other: Long) = AtomicLongOperationImplementations.transformShl(this, other)

    /**
     * Shift this long left by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to shift left by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shl(other: AtomicLong) = AtomicLongOperationImplementations.transformShl(this, other)

    /**
     * Shift this long right by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shr(other: Long) = AtomicLongOperationImplementations.transformShr(this, other)

    /**
     * Shift this long right by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun shr(other: AtomicLong) = AtomicLongOperationImplementations.transformShr(this, other)

    /**
     * Shift this long right by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun ushr(other: Long) = AtomicLongOperationImplementations.transformUshr(this, other)

    /**
     * Shift this long right by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to shift right by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun ushr(other: AtomicLong) = AtomicLongOperationImplementations.transformUshr(this, other)

    /**
     * Invert this long (returns a new [AtomicLong] that automatically updates)
     * @return The new [AtomicLong] that automatically updates
     */
    fun inv() = AtomicLongOperationImplementations.transformInv(this)

    /**
     * Negate this long (returns a new [AtomicLong] that automatically updates)
     * @return The new [AtomicLong] that automatically updates
     */
    fun negate() = AtomicLongOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicLong]
     */
    fun destroy() {}
}

abstract class AtomicLongBase(
    initialValue: Long,
) : ListenableItemImpl(), AtomicLong {

    final override var longValue: Long = initialValue
        get() = get()
        private set(value) {
            field = value
            notifyListeners()
        }

    val destroyListeners: MutableList<() -> Unit> = mutableListOf()

    override fun addDestroyListener(listener: () -> Unit): () -> Unit {
        destroyListeners.add(listener)
        return { destroyListeners.remove(listener) }
    }

    override fun removeDestroyListener(listener: () -> Unit) {
        destroyListeners.remove(listener)
    }

    protected open fun update(newValue: Long) {
        longValue = newValue
    }

    override fun get(): Long = longValue
}

class EditableAtomicLong(
    initialValue: Long,
) : AtomicLongBase(initialValue) {

    public override fun update(newValue: Long) {
        super.update(newValue)
    }
}

fun atomicLongOf(
    initialValue: Long,
): AtomicLong = EditableAtomicLong(initialValue)

fun Long.atomic(): AtomicLong = atomicLongOf(this)

class AutoUpdateAbleAtomicLong(
    initialValue: Long,
) : AtomicLongBase(initialValue), AutoUpdatableAtomicValue<Long> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Long) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(
            initialValue: Long,
        ): AutoUpdateAbleAtomicLong = AutoUpdateAbleAtomicLong(initialValue)

        fun create(): AutoUpdateAbleAtomicLong = create(0)

        val creator: () -> AutoUpdateAbleAtomicLong = ::create
    }
}
