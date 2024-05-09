package com.shakelang.util.primitives.atomic

import kotlin.math.pow

// Int Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicIntOperationImplementations {

    // Int Operations
    fun plus(source1: AtomicInt, other: Byte): Int = (source1.intValue + other)
    fun plus(source1: AtomicInt, other: AtomicByte): Int = (source1.intValue + other.byteValue)
    fun plus(source1: AtomicInt, other: Short): Int = (source1.intValue + other)
    fun plus(source1: AtomicInt, other: AtomicShort): Int = (source1.intValue + other.shortValue)
    fun plus(source1: AtomicInt, other: Int): Int = (source1.intValue + other)
    fun plus(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue + source2.intValue)
    fun plus(source1: AtomicInt, other: Long): Long = (source1.intValue + other)
    fun plus(source1: AtomicInt, other: AtomicLong): Long = (source1.intValue + other.longValue)
    fun plus(source1: AtomicInt, other: Float): Float = (source1.intValue + other)
    fun plus(source1: AtomicInt, other: AtomicFloat): Float = (source1.intValue + other.floatValue)
    fun plus(source1: AtomicInt, other: Double): Double = (source1.intValue + other)
    fun plus(source1: AtomicInt, other: AtomicDouble): Double = (source1.intValue + other.doubleValue)

    fun minus(source1: AtomicInt, other: Byte): Int = (source1.intValue - other)
    fun minus(source1: AtomicInt, other: AtomicByte): Int = (source1.intValue - other.byteValue)
    fun minus(source1: AtomicInt, other: Short): Int = (source1.intValue - other)
    fun minus(source1: AtomicInt, other: AtomicShort): Int = (source1.intValue - other.shortValue)
    fun minus(source1: AtomicInt, other: Int): Int = (source1.intValue - other)
    fun minus(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue - source2.intValue)
    fun minus(source1: AtomicInt, other: Long): Long = (source1.intValue - other)
    fun minus(source1: AtomicInt, other: AtomicLong): Long = (source1.intValue - other.longValue)
    fun minus(source1: AtomicInt, other: Float): Float = (source1.intValue - other)
    fun minus(source1: AtomicInt, other: AtomicFloat): Float = (source1.intValue - other.floatValue)
    fun minus(source1: AtomicInt, other: Double): Double = (source1.intValue - other)
    fun minus(source1: AtomicInt, other: AtomicDouble): Double = (source1.intValue - other.doubleValue)

    fun times(source1: AtomicInt, other: Byte): Int = (source1.intValue * other)
    fun times(source1: AtomicInt, other: AtomicByte): Int = (source1.intValue * other.byteValue)
    fun times(source1: AtomicInt, other: Short): Int = (source1.intValue * other)
    fun times(source1: AtomicInt, other: AtomicShort): Int = (source1.intValue * other.shortValue)
    fun times(source1: AtomicInt, other: Int): Int = (source1.intValue * other)
    fun times(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue * source2.intValue
    fun times(source1: AtomicInt, other: Long): Long = (source1.intValue * other)
    fun times(source1: AtomicInt, other: AtomicLong): Long = (source1.intValue * other.longValue)
    fun times(source1: AtomicInt, other: Float): Float = (source1.intValue * other)
    fun times(source1: AtomicInt, other: AtomicFloat): Float = (source1.intValue * other.floatValue)
    fun times(source1: AtomicInt, other: Double): Double = (source1.intValue * other)
    fun times(source1: AtomicInt, other: AtomicDouble): Double = (source1.intValue * other.doubleValue)

    fun div(source1: AtomicInt, other: Byte): Int = (source1.intValue / other)
    fun div(source1: AtomicInt, other: AtomicByte): Int = (source1.intValue / other.byteValue)
    fun div(source1: AtomicInt, other: Short): Int = (source1.intValue / other)
    fun div(source1: AtomicInt, other: AtomicShort): Int = (source1.intValue / other.shortValue)
    fun div(source1: AtomicInt, other: Int): Int = (source1.intValue / other)
    fun div(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue / source2.intValue)
    fun div(source1: AtomicInt, other: Long): Long = (source1.intValue / other)
    fun div(source1: AtomicInt, other: AtomicLong): Long = (source1.intValue / other.longValue)
    fun div(source1: AtomicInt, other: Float): Float = (source1.intValue / other)
    fun div(source1: AtomicInt, other: AtomicFloat): Float = (source1.intValue / other.floatValue)
    fun div(source1: AtomicInt, other: Double): Double = (source1.intValue / other)
    fun div(source1: AtomicInt, other: AtomicDouble): Double = (source1.intValue / other.doubleValue)

    fun rem(source1: AtomicInt, other: Byte): Int = (source1.intValue % other)
    fun rem(source1: AtomicInt, other: AtomicByte): Int = (source1.intValue % other.byteValue)
    fun rem(source1: AtomicInt, other: Short): Int = (source1.intValue % other)
    fun rem(source1: AtomicInt, other: AtomicShort): Int = (source1.intValue % other.shortValue)
    fun rem(source1: AtomicInt, other: Int): Int = (source1.intValue % other)
    fun rem(source1: AtomicInt, source2: AtomicInt): Int = (source1.intValue % source2.intValue)
    fun rem(source1: AtomicInt, other: Long): Long = (source1.intValue % other)
    fun rem(source1: AtomicInt, other: AtomicLong): Long = (source1.intValue % other.longValue)
    fun rem(source1: AtomicInt, other: Float): Float = (source1.intValue % other)
    fun rem(source1: AtomicInt, other: AtomicFloat): Float = (source1.intValue % other.floatValue)
    fun rem(source1: AtomicInt, other: Double): Double = (source1.intValue % other)
    fun rem(source1: AtomicInt, other: AtomicDouble): Double = (source1.intValue % other.doubleValue)

    fun pow(source1: AtomicInt, other: Byte): Int = source1.intValue.toDouble().pow(other.toDouble()).toInt()
    fun pow(source1: AtomicInt, other: AtomicByte): Int = source1.intValue.toDouble().pow(other.byteValue.toDouble()).toInt()
    fun pow(source1: AtomicInt, other: Short): Int = source1.intValue.toDouble().pow(other.toDouble()).toInt()
    fun pow(source1: AtomicInt, other: AtomicShort): Int = source1.intValue.toDouble().pow(other.shortValue.toDouble()).toInt()
    fun pow(source1: AtomicInt, other: Int): Int = source1.intValue.toDouble().pow(other.toDouble()).toInt()
    fun pow(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue.toDouble().pow(source2.intValue.toDouble()).toInt()
    fun pow(source1: AtomicInt, other: Long): Long = source1.intValue.toDouble().pow(other.toDouble()).toLong()
    fun pow(source1: AtomicInt, other: AtomicLong): Long = source1.intValue.toDouble().pow(other.longValue.toDouble()).toLong()
    fun pow(source1: AtomicInt, other: Float): Float = source1.intValue.toDouble().pow(other.toDouble()).toFloat()
    fun pow(source1: AtomicInt, other: AtomicFloat): Float = source1.intValue.toDouble().pow(other.floatValue.toDouble()).toFloat()
    fun pow(source1: AtomicInt, other: Double): Double = source1.intValue.toDouble().pow(other)
    fun pow(source1: AtomicInt, other: AtomicDouble): Double = source1.intValue.toDouble().pow(other.doubleValue)

    fun and(source1: AtomicInt, other: Int): Int = source1.intValue and other
    fun and(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue and source2.intValue

    fun or(source1: AtomicInt, other: Int): Int = source1.intValue or other
    fun or(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue or source2.intValue

    fun xor(source1: AtomicInt, other: Int): Int = source1.intValue xor other
    fun xor(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue xor source2.intValue

    fun shl(source1: AtomicInt, other: Int): Int = source1.intValue shl other
    fun shl(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue shl source2.intValue

    fun shr(source1: AtomicInt, other: Int): Int = source1.intValue shr other
    fun shr(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue shr source2.intValue

    fun ushr(source1: AtomicInt, other: Int): Int = source1.intValue ushr other
    fun ushr(source1: AtomicInt, source2: AtomicInt): Int = source1.intValue ushr source2.intValue

    fun inv(source1: AtomicInt): Int = source1.intValue.inv()
    fun negate(source1: AtomicInt): Int = (-source1.intValue)

    val intPlusByte: BinaryOperation<AtomicInt, Byte, Int> = ::plus
    val intPlusAtomicByte: BinaryOperation<AtomicInt, AtomicByte, Int> = ::plus
    val intPlusShort: BinaryOperation<AtomicInt, Short, Int> = ::plus
    val intPlusAtomicShort: BinaryOperation<AtomicInt, AtomicShort, Int> = ::plus
    val intPlusInt: BinaryOperation<AtomicInt, Int, Int> = ::plus
    val intPlusAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::plus
    val intPlusLong: BinaryOperation<AtomicInt, Long, Long> = ::plus
    val intPlusAtomicLong: BinaryOperation<AtomicInt, AtomicLong, Long> = ::plus
    val intPlusFloat: BinaryOperation<AtomicInt, Float, Float> = ::plus
    val intPlusAtomicFloat: BinaryOperation<AtomicInt, AtomicFloat, Float> = ::plus
    val intPlusDouble: BinaryOperation<AtomicInt, Double, Double> = ::plus
    val intPlusAtomicDouble: BinaryOperation<AtomicInt, AtomicDouble, Double> = ::plus

    val intMinusByte: BinaryOperation<AtomicInt, Byte, Int> = ::minus
    val intMinusAtomicByte: BinaryOperation<AtomicInt, AtomicByte, Int> = ::minus
    val intMinusShort: BinaryOperation<AtomicInt, Short, Int> = ::minus
    val intMinusAtomicShort: BinaryOperation<AtomicInt, AtomicShort, Int> = ::minus
    val intMinusInt: BinaryOperation<AtomicInt, Int, Int> = ::minus
    val intMinusAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::minus
    val intMinusLong: BinaryOperation<AtomicInt, Long, Long> = ::minus
    val intMinusAtomicLong: BinaryOperation<AtomicInt, AtomicLong, Long> = ::minus
    val intMinusFloat: BinaryOperation<AtomicInt, Float, Float> = ::minus
    val intMinusAtomicFloat: BinaryOperation<AtomicInt, AtomicFloat, Float> = ::minus
    val intMinusDouble: BinaryOperation<AtomicInt, Double, Double> = ::minus
    val intMinusAtomicDouble: BinaryOperation<AtomicInt, AtomicDouble, Double> = ::minus

    val intTimesByte: BinaryOperation<AtomicInt, Byte, Int> = ::times
    val intTimesAtomicByte: BinaryOperation<AtomicInt, AtomicByte, Int> = ::times
    val intTimesShort: BinaryOperation<AtomicInt, Short, Int> = ::times
    val intTimesAtomicShort: BinaryOperation<AtomicInt, AtomicShort, Int> = ::times
    val intTimesInt: BinaryOperation<AtomicInt, Int, Int> = ::times
    val intTimesAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::times
    val intTimesLong: BinaryOperation<AtomicInt, Long, Long> = ::times
    val intTimesAtomicLong: BinaryOperation<AtomicInt, AtomicLong, Long> = ::times
    val intTimesFloat: BinaryOperation<AtomicInt, Float, Float> = ::times
    val intTimesAtomicFloat: BinaryOperation<AtomicInt, AtomicFloat, Float> = ::times
    val intTimesDouble: BinaryOperation<AtomicInt, Double, Double> = ::times
    val intTimesAtomicDouble: BinaryOperation<AtomicInt, AtomicDouble, Double> = ::times

    val intDivByte: BinaryOperation<AtomicInt, Byte, Int> = ::div
    val intDivAtomicByte: BinaryOperation<AtomicInt, AtomicByte, Int> = ::div
    val intDivShort: BinaryOperation<AtomicInt, Short, Int> = ::div
    val intDivAtomicShort: BinaryOperation<AtomicInt, AtomicShort, Int> = ::div
    val intDivInt: BinaryOperation<AtomicInt, Int, Int> = ::div
    val intDivAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::div
    val intDivLong: BinaryOperation<AtomicInt, Long, Long> = ::div
    val intDivAtomicLong: BinaryOperation<AtomicInt, AtomicLong, Long> = ::div
    val intDivFloat: BinaryOperation<AtomicInt, Float, Float> = ::div
    val intDivAtomicFloat: BinaryOperation<AtomicInt, AtomicFloat, Float> = ::div
    val intDivDouble: BinaryOperation<AtomicInt, Double, Double> = ::div
    val intDivAtomicDouble: BinaryOperation<AtomicInt, AtomicDouble, Double> = ::div

    val intRemByte: BinaryOperation<AtomicInt, Byte, Int> = ::rem
    val intRemAtomicByte: BinaryOperation<AtomicInt, AtomicByte, Int> = ::rem
    val intRemShort: BinaryOperation<AtomicInt, Short, Int> = ::rem
    val intRemAtomicShort: BinaryOperation<AtomicInt, AtomicShort, Int> = ::rem
    val intRemInt: BinaryOperation<AtomicInt, Int, Int> = ::rem
    val intRemAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::rem
    val intRemLong: BinaryOperation<AtomicInt, Long, Long> = ::rem
    val intRemAtomicLong: BinaryOperation<AtomicInt, AtomicLong, Long> = ::rem
    val intRemFloat: BinaryOperation<AtomicInt, Float, Float> = ::rem
    val intRemAtomicFloat: BinaryOperation<AtomicInt, AtomicFloat, Float> = ::rem
    val intRemDouble: BinaryOperation<AtomicInt, Double, Double> = ::rem
    val intRemAtomicDouble: BinaryOperation<AtomicInt, AtomicDouble, Double> = ::rem

    val intPowByte: BinaryOperation<AtomicInt, Byte, Int> = ::pow
    val intPowAtomicByte: BinaryOperation<AtomicInt, AtomicByte, Int> = ::pow
    val intPowShort: BinaryOperation<AtomicInt, Short, Int> = ::pow
    val intPowAtomicShort: BinaryOperation<AtomicInt, AtomicShort, Int> = ::pow
    val intPowInt: BinaryOperation<AtomicInt, Int, Int> = ::pow
    val intPowAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::pow
    val intPowLong: BinaryOperation<AtomicInt, Long, Long> = ::pow
    val intPowAtomicLong: BinaryOperation<AtomicInt, AtomicLong, Long> = ::pow
    val intPowFloat: BinaryOperation<AtomicInt, Float, Float> = ::pow
    val intPowAtomicFloat: BinaryOperation<AtomicInt, AtomicFloat, Float> = ::pow
    val intPowDouble: BinaryOperation<AtomicInt, Double, Double> = ::pow
    val intPowAtomicDouble: BinaryOperation<AtomicInt, AtomicDouble, Double> = ::pow

    val intAndInt: BinaryOperation<AtomicInt, Int, Int> = ::and
    val intAndAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::and

    val intOrInt: BinaryOperation<AtomicInt, Int, Int> = ::or
    val intOrAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::or

    val intXorInt: BinaryOperation<AtomicInt, Int, Int> = ::xor
    val intXorAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::xor
    val intShlInt: BinaryOperation<AtomicInt, Int, Int> = ::shl
    val intShlAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::shl
    val intShrInt: BinaryOperation<AtomicInt, Int, Int> = ::shr
    val intShrAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::shr
    val intUshrInt: BinaryOperation<AtomicInt, Int, Int> = ::ushr
    val intUshrAtomicInt: BinaryOperation<AtomicInt, AtomicInt, Int> = ::ushr
    val intInv: UnaryOperation<AtomicInt, Int> = ::inv
    val intNegate: UnaryOperation<AtomicInt, Int> = ::negate

    fun transformPlus(source1: AtomicInt, other: Byte): AtomicInt = transform1(source1, other, intPlusByte, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicByte): AtomicInt = transform1(source1, other, intPlusAtomicByte, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: Short): AtomicInt = transform1(source1, other, intPlusShort, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicShort): AtomicInt = transform1(source1, other, intPlusAtomicShort, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intPlusInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intPlusAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPlus(source1: AtomicInt, other: Long): AtomicLong = transform1(source1, other, intPlusLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicLong): AtomicLong = transform1(source1, other, intPlusAtomicLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPlus(source1: AtomicInt, other: Float): AtomicFloat = transform1(source1, other, intPlusFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicFloat): AtomicFloat = transform1(source1, other, intPlusAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicInt, other: Double): AtomicDouble = transform1(source1, other, intPlusDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicInt, other: AtomicDouble): AtomicDouble = transform1(source1, other, intPlusAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformMinus(source1: AtomicInt, other: Byte): AtomicInt = transform1(source1, other, intMinusByte, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicByte): AtomicInt = transform1(source1, other, intMinusAtomicByte, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: Short): AtomicInt = transform1(source1, other, intMinusShort, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicShort): AtomicInt = transform1(source1, other, intMinusAtomicShort, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intMinusInt, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intMinusAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformMinus(source1: AtomicInt, other: Long): AtomicLong = transform1(source1, other, intMinusLong, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicLong): AtomicLong = transform1(source1, other, intMinusAtomicLong, AutoUpdateAbleAtomicLong.creator)
    fun transformMinus(source1: AtomicInt, other: Float): AtomicFloat = transform1(source1, other, intMinusFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicFloat): AtomicFloat = transform1(source1, other, intMinusAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicInt, other: Double): AtomicDouble = transform1(source1, other, intMinusDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicInt, other: AtomicDouble): AtomicDouble = transform1(source1, other, intMinusAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformTimes(source1: AtomicInt, other: Byte): AtomicInt = transform1(source1, other, intTimesByte, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicByte): AtomicInt = transform1(source1, other, intTimesAtomicByte, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: Short): AtomicInt = transform1(source1, other, intTimesShort, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicShort): AtomicInt = transform1(source1, other, intTimesAtomicShort, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intTimesInt, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intTimesAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformTimes(source1: AtomicInt, other: Long): AtomicLong = transform1(source1, other, intTimesLong, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicLong): AtomicLong = transform1(source1, other, intTimesAtomicLong, AutoUpdateAbleAtomicLong.creator)
    fun transformTimes(source1: AtomicInt, other: Float): AtomicFloat = transform1(source1, other, intTimesFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicFloat): AtomicFloat = transform1(source1, other, intTimesAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicInt, other: Double): AtomicDouble = transform1(source1, other, intTimesDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicInt, other: AtomicDouble): AtomicDouble = transform1(source1, other, intTimesAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformDiv(source1: AtomicInt, other: Byte): AtomicInt = transform1(source1, other, intDivByte, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicByte): AtomicInt = transform1(source1, other, intDivAtomicByte, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: Short): AtomicInt = transform1(source1, other, intDivShort, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicShort): AtomicInt = transform1(source1, other, intDivAtomicShort, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intDivInt, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intDivAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformDiv(source1: AtomicInt, other: Long): AtomicLong = transform1(source1, other, intDivLong, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicLong): AtomicLong = transform1(source1, other, intDivAtomicLong, AutoUpdateAbleAtomicLong.creator)
    fun transformDiv(source1: AtomicInt, other: Float): AtomicFloat = transform1(source1, other, intDivFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicFloat): AtomicFloat = transform1(source1, other, intDivAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicInt, other: Double): AtomicDouble = transform1(source1, other, intDivDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicInt, other: AtomicDouble): AtomicDouble = transform1(source1, other, intDivAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformRem(source1: AtomicInt, other: Byte): AtomicInt = transform1(source1, other, intRemByte, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: AtomicByte): AtomicInt = transform1(source1, other, intRemAtomicByte, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: Short): AtomicInt = transform1(source1, other, intRemShort, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: AtomicShort): AtomicInt = transform1(source1, other, intRemAtomicShort, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intRemInt, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intRemAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformRem(source1: AtomicInt, other: Long): AtomicLong = transform1(source1, other, intRemLong, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicInt, other: AtomicLong): AtomicLong = transform1(source1, other, intRemAtomicLong, AutoUpdateAbleAtomicLong.creator)
    fun transformRem(source1: AtomicInt, other: Float): AtomicFloat = transform1(source1, other, intRemFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicInt, other: AtomicFloat): AtomicFloat = transform1(source1, other, intRemAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicInt, other: Double): AtomicDouble = transform1(source1, other, intRemDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicInt, other: AtomicDouble): AtomicDouble = transform1(source1, other, intRemAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformPow(source1: AtomicInt, other: Byte): AtomicInt = transform1(source1, other, intPowByte, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: AtomicByte): AtomicInt = transform1(source1, other, intPowAtomicByte, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: Short): AtomicInt = transform1(source1, other, intPowShort, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: AtomicShort): AtomicInt = transform1(source1, other, intPowAtomicShort, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intPowInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intPowAtomicInt, AutoUpdateAbleAtomicInt.creator)
    fun transformPow(source1: AtomicInt, other: Long): AtomicLong = transform1(source1, other, intPowLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicInt, other: AtomicLong): AtomicLong = transform1(source1, other, intPowAtomicLong, AutoUpdateAbleAtomicLong.creator)
    fun transformPow(source1: AtomicInt, other: Float): AtomicFloat = transform1(source1, other, intPowFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicInt, other: AtomicFloat): AtomicFloat = transform1(source1, other, intPowAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicInt, other: Double): AtomicDouble = transform1(source1, other, intPowDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicInt, other: AtomicDouble): AtomicDouble = transform1(source1, other, intPowAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformAnd(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intAndInt, AutoUpdateAbleAtomicInt.creator)
    fun transformAnd(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intAndAtomicInt, AutoUpdateAbleAtomicInt.creator)

    fun transformOr(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intOrInt, AutoUpdateAbleAtomicInt.creator)
    fun transformOr(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intOrAtomicInt, AutoUpdateAbleAtomicInt.creator)

    fun transformXor(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intXorInt, AutoUpdateAbleAtomicInt.creator)
    fun transformXor(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intXorAtomicInt, AutoUpdateAbleAtomicInt.creator)

    fun transformShl(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intShlInt, AutoUpdateAbleAtomicInt.creator)
    fun transformShl(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intShlAtomicInt, AutoUpdateAbleAtomicInt.creator)

    fun transformShr(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intShrInt, AutoUpdateAbleAtomicInt.creator)
    fun transformShr(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intShrAtomicInt, AutoUpdateAbleAtomicInt.creator)

    fun transformUshr(source1: AtomicInt, other: Int): AtomicInt = transform1(source1, other, intUshrInt, AutoUpdateAbleAtomicInt.creator)
    fun transformUshr(source1: AtomicInt, other: AtomicInt): AtomicInt = transform2(source1, other, intUshrAtomicInt, AutoUpdateAbleAtomicInt.creator)

    fun transformInv(source1: AtomicInt): AtomicInt = transform1(source1, intInv, AutoUpdateAbleAtomicInt.creator)
    fun transformNegate(source1: AtomicInt): AtomicInt = transform1(source1, intNegate, AutoUpdateAbleAtomicInt.creator)
}

/**
 * An atomic int value
 */
interface AtomicInt : AtomicValue {

    /**
     * The value of the atomic int
     */
    val intValue: Int

    /**
     * Get the value of the atomic int
     */
    fun get(): Int

    /**
     * Add another byte to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other byte to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: Byte) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicByte] to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicByte] to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: AtomicByte) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another short to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other short to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: Short) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicShort] to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicShort] to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: AtomicShort) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another int to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: Int) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicInt] to this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to add
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun plus(other: AtomicInt) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another long to this int (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: Long) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicLong] to this int (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to add
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun plus(other: AtomicLong) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another float to this int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: Float) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicFloat] to this int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: AtomicFloat) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another double to this int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Double) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicDouble] to this int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicDouble) = AtomicIntOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another byte from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other byte to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: Byte) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicByte] from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicByte] to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: AtomicByte) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another short from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other short to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: Short) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicShort] from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicShort] to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: AtomicShort) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another int from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: Int) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicInt] from this int (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to subtract
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun minus(other: AtomicInt) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another long from this int (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: Long) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicLong] from this int (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to subtract
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun minus(other: AtomicLong) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another float from this int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: Float) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicFloat] from this int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: AtomicFloat) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another double from this int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Double) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicDouble] from this int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicDouble) = AtomicIntOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this int with another byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other byte to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: Byte) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another [AtomicByte] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicByte] to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: AtomicByte) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another short (returns a new [AtomicInt] that automatically updates)
     * @param other The other short to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: Short) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another [AtomicShort] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicShort] to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: AtomicShort) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: Int) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to multiply with
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun times(other: AtomicInt) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: Long) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to multiply with
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun times(other: AtomicLong) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: Float) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: AtomicFloat) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this int with another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Double) = AtomicIntOperationImplementations.transformTimes(this, other)

    /**
     * Divide this int by another byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other byte to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: Byte) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicByte] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicByte] to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: AtomicByte) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another short (returns a new [AtomicInt] that automatically updates)
     * @param other The other short to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: Short) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicShort] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicShort] to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: AtomicShort) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: Int) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to divide by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun div(other: AtomicInt) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: Long) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to divide by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun div(other: AtomicLong) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: Float) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: AtomicFloat) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Double) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Divide this int by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicDouble) = AtomicIntOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this int by another byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other byte to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: Byte) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicByte] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicByte] to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: AtomicByte) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another short (returns a new [AtomicInt] that automatically updates)
     * @param other The other short to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: Short) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicShort] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicShort] to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: AtomicShort) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: Int) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to modulo by
     * @return The new [AtomicInt] that automatically updates
     */
    operator fun rem(other: AtomicInt) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: Long) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to modulo by
     * @return The new [AtomicLong] that automatically updates
     */
    operator fun rem(other: AtomicLong) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: Float) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: AtomicFloat) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Double) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Modulo this int by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicDouble) = AtomicIntOperationImplementations.transformRem(this, other)

    /**
     * Power this int by another byte (returns a new [AtomicInt] that automatically updates)
     * @param other The other byte to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: Byte) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicByte] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicByte] to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: AtomicByte) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another short (returns a new [AtomicInt] that automatically updates)
     * @param other The other short to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: Short) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicShort] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicShort] to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: AtomicShort) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: Int) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to power by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun pow(other: AtomicInt) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another long (returns a new [AtomicLong] that automatically updates)
     * @param other The other long to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: Long) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicLong] (returns a new [AtomicLong] that automatically updates)
     * @param other The other [AtomicLong] to power by
     * @return The new [AtomicLong] that automatically updates
     */
    infix fun pow(other: AtomicLong) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: Float) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: AtomicFloat) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Double) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * Power this int by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicDouble) = AtomicIntOperationImplementations.transformPow(this, other)

    /**
     * And this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to and with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun and(other: Int) = AtomicIntOperationImplementations.transformAnd(this, other)

    /**
     * And this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to and with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun and(other: AtomicInt) = AtomicIntOperationImplementations.transformAnd(this, other)

    /**
     * Or this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to or with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun or(other: Int) = AtomicIntOperationImplementations.transformOr(this, other)

    /**
     * Or this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to or with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun or(other: AtomicInt) = AtomicIntOperationImplementations.transformOr(this, other)

    /**
     * Xor this int with another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to xor with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun xor(other: Int) = AtomicIntOperationImplementations.transformXor(this, other)

    /**
     * Xor this int with another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to xor with
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun xor(other: AtomicInt) = AtomicIntOperationImplementations.transformXor(this, other)

    /**
     * Shift this int left by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to shift left by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shl(other: Int) = AtomicIntOperationImplementations.transformShl(this, other)

    /**
     * Shift this int left by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to shift left by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shl(other: AtomicInt) = AtomicIntOperationImplementations.transformShl(this, other)

    /**
     * Shift this int right by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shr(other: Int) = AtomicIntOperationImplementations.transformShr(this, other)

    /**
     * Shift this int right by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun shr(other: AtomicInt) = AtomicIntOperationImplementations.transformShr(this, other)

    /**
     * Shift this int right by another int (returns a new [AtomicInt] that automatically updates)
     * @param other The other int to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun ushr(other: Int) = AtomicIntOperationImplementations.transformUshr(this, other)

    /**
     * Shift this int right by another [AtomicInt] (returns a new [AtomicInt] that automatically updates)
     * @param other The other [AtomicInt] to shift right by
     * @return The new [AtomicInt] that automatically updates
     */
    infix fun ushr(other: AtomicInt) = AtomicIntOperationImplementations.transformUshr(this, other)

    /**
     * Invert this int (returns a new [AtomicInt] that automatically updates)
     * @return The new [AtomicInt] that automatically updates
     */
    fun inv() = AtomicIntOperationImplementations.transformInv(this)

    /**
     * Negate this int (returns a new [AtomicInt] that automatically updates)
     * @return The new [AtomicInt] that automatically updates
     */
    fun negate() = AtomicIntOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicInt]
     */
    fun destroy() {}
}

abstract class AtomicIntBase(
    initialValue: Int,
) : ListenableItemImpl(), AtomicInt {

    final override var intValue: Int = initialValue
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

    protected open fun update(newValue: Int) {
        intValue = newValue
    }

    override fun get(): Int = intValue
}

class EditableAtomicInt(
    initialValue: Int,
) : AtomicIntBase(initialValue) {

    public override fun update(newValue: Int) {
        super.update(newValue)
    }
}

fun atomicIntOf(
    initialValue: Int,
): AtomicInt = EditableAtomicInt(initialValue)

fun Int.atomic(): AtomicInt = atomicIntOf(this)

class AutoUpdateAbleAtomicInt(
    initialValue: Int,
) : AtomicIntBase(initialValue), AutoUpdatableAtomicValue<Int> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Int) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(initialValue: Int): AutoUpdateAbleAtomicInt = AutoUpdateAbleAtomicInt(initialValue)
        fun create(): AutoUpdateAbleAtomicInt = create(0)
        val creator: () -> AutoUpdateAbleAtomicInt = ::create
    }
}
