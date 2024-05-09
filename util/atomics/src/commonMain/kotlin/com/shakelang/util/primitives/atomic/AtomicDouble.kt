package com.shakelang.util.primitives.atomic

import kotlin.math.pow

// Double Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicDoubleOperationImplementations {

    // Double Operations
    fun plus(source1: AtomicDouble, other: Byte): Double = (source1.doubleValue + other)
    fun plus(source1: AtomicDouble, other: AtomicByte): Double = (source1.doubleValue + other.byteValue)
    fun plus(source1: AtomicDouble, other: Short): Double = (source1.doubleValue + other)
    fun plus(source1: AtomicDouble, other: AtomicShort): Double = (source1.doubleValue + other.shortValue)
    fun plus(source1: AtomicDouble, other: Int): Double = (source1.doubleValue + other)
    fun plus(source1: AtomicDouble, other: AtomicInt): Double = (source1.doubleValue + other.intValue)
    fun plus(source1: AtomicDouble, other: Long): Double = (source1.doubleValue + other)
    fun plus(source1: AtomicDouble, other: AtomicLong): Double = (source1.doubleValue + other.longValue)
    fun plus(source1: AtomicDouble, other: Float): Double = (source1.doubleValue + other)
    fun plus(source1: AtomicDouble, other: AtomicFloat): Double = (source1.doubleValue + other.floatValue)
    fun plus(source1: AtomicDouble, other: Double): Double = (source1.doubleValue + other)
    fun plus(source1: AtomicDouble, source2: AtomicDouble): Double = (source1.doubleValue + source2.doubleValue)

    fun minus(source1: AtomicDouble, other: Byte): Double = (source1.doubleValue - other)
    fun minus(source1: AtomicDouble, other: AtomicByte): Double = (source1.doubleValue - other.byteValue)
    fun minus(source1: AtomicDouble, other: Short): Double = (source1.doubleValue - other)
    fun minus(source1: AtomicDouble, other: AtomicShort): Double = (source1.doubleValue - other.shortValue)
    fun minus(source1: AtomicDouble, other: Int): Double = (source1.doubleValue - other)
    fun minus(source1: AtomicDouble, other: AtomicInt): Double = (source1.doubleValue - other.intValue)
    fun minus(source1: AtomicDouble, other: Long): Double = (source1.doubleValue - other)
    fun minus(source1: AtomicDouble, other: AtomicLong): Double = (source1.doubleValue - other.longValue)
    fun minus(source1: AtomicDouble, other: Float): Double = (source1.doubleValue - other)
    fun minus(source1: AtomicDouble, other: AtomicFloat): Double = (source1.doubleValue - other.floatValue)
    fun minus(source1: AtomicDouble, other: Double): Double = (source1.doubleValue - other)
    fun minus(source1: AtomicDouble, source2: AtomicDouble): Double = (source1.doubleValue - source2.doubleValue)

    fun times(source1: AtomicDouble, other: Byte): Double = (source1.doubleValue * other)
    fun times(source1: AtomicDouble, other: AtomicByte): Double = (source1.doubleValue * other.byteValue)
    fun times(source1: AtomicDouble, other: Short): Double = (source1.doubleValue * other)
    fun times(source1: AtomicDouble, other: AtomicShort): Double = (source1.doubleValue * other.shortValue)
    fun times(source1: AtomicDouble, other: Int): Double = (source1.doubleValue * other)
    fun times(source1: AtomicDouble, other: AtomicInt): Double = (source1.doubleValue * other.intValue)
    fun times(source1: AtomicDouble, other: Long): Double = (source1.doubleValue * other)
    fun times(source1: AtomicDouble, other: AtomicLong): Double = (source1.doubleValue * other.longValue)
    fun times(source1: AtomicDouble, other: Float): Double = (source1.doubleValue * other)
    fun times(source1: AtomicDouble, other: AtomicFloat): Double = (source1.doubleValue * other.floatValue)
    fun times(source1: AtomicDouble, other: Double): Double = (source1.doubleValue * other)
    fun times(source1: AtomicDouble, source2: AtomicDouble): Double = (source1.doubleValue * source2.doubleValue)

    fun div(source1: AtomicDouble, other: Byte): Double = (source1.doubleValue / other)
    fun div(source1: AtomicDouble, other: AtomicByte): Double = (source1.doubleValue / other.byteValue)
    fun div(source1: AtomicDouble, other: Short): Double = (source1.doubleValue / other)
    fun div(source1: AtomicDouble, other: AtomicShort): Double = (source1.doubleValue / other.shortValue)
    fun div(source1: AtomicDouble, other: Int): Double = (source1.doubleValue / other)
    fun div(source1: AtomicDouble, other: AtomicInt): Double = (source1.doubleValue / other.intValue)
    fun div(source1: AtomicDouble, other: Long): Double = (source1.doubleValue / other)
    fun div(source1: AtomicDouble, other: AtomicLong): Double = (source1.doubleValue / other.longValue)
    fun div(source1: AtomicDouble, other: Float): Double = (source1.doubleValue / other)
    fun div(source1: AtomicDouble, other: AtomicFloat): Double = (source1.doubleValue / other.floatValue)
    fun div(source1: AtomicDouble, other: Double): Double = (source1.doubleValue / other)
    fun div(source1: AtomicDouble, source2: AtomicDouble): Double = (source1.doubleValue / source2.doubleValue)

    fun rem(source1: AtomicDouble, other: Byte): Double = (source1.doubleValue % other)
    fun rem(source1: AtomicDouble, other: AtomicByte): Double = (source1.doubleValue % other.byteValue)
    fun rem(source1: AtomicDouble, other: Short): Double = (source1.doubleValue % other)
    fun rem(source1: AtomicDouble, other: AtomicShort): Double = (source1.doubleValue % other.shortValue)
    fun rem(source1: AtomicDouble, other: Int): Double = (source1.doubleValue % other)
    fun rem(source1: AtomicDouble, other: AtomicInt): Double = (source1.doubleValue % other.intValue)
    fun rem(source1: AtomicDouble, other: Long): Double = (source1.doubleValue % other)
    fun rem(source1: AtomicDouble, other: AtomicLong): Double = (source1.doubleValue % other.longValue)
    fun rem(source1: AtomicDouble, other: Float): Double = (source1.doubleValue % other)
    fun rem(source1: AtomicDouble, other: AtomicFloat): Double = (source1.doubleValue % other.floatValue)
    fun rem(source1: AtomicDouble, other: Double): Double = (source1.doubleValue % other)
    fun rem(source1: AtomicDouble, source2: AtomicDouble): Double = (source1.doubleValue % source2.doubleValue)

    fun pow(source1: AtomicDouble, other: Byte): Double = source1.doubleValue.toDouble().pow(other.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: AtomicByte): Double = source1.doubleValue.toDouble().pow(other.byteValue.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: Short): Double = source1.doubleValue.toDouble().pow(other.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: AtomicShort): Double = source1.doubleValue.toDouble().pow(other.shortValue.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: Int): Double = source1.doubleValue.toDouble().pow(other.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: AtomicInt): Double = source1.doubleValue.toDouble().pow(other.intValue.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: Long): Double = source1.doubleValue.toDouble().pow(other.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: AtomicLong): Double = source1.doubleValue.toDouble().pow(other.longValue.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: Float): Double = source1.doubleValue.toDouble().pow(other.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: AtomicFloat): Double = source1.doubleValue.toDouble().pow(other.floatValue.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, other: Double): Double = source1.doubleValue.toDouble().pow(other.toDouble()).toDouble()
    fun pow(source1: AtomicDouble, source2: AtomicDouble): Double = source1.doubleValue.toDouble().pow(source2.doubleValue.toDouble()).toDouble()

    fun negate(source1: AtomicDouble): Double = (-source1.doubleValue)

    val doublePlusByte: BinaryOperation<AtomicDouble, Byte, Double> = ::plus
    val doublePlusAtomicByte: BinaryOperation<AtomicDouble, AtomicByte, Double> = ::plus
    val doublePlusShort: BinaryOperation<AtomicDouble, Short, Double> = ::plus
    val doublePlusAtomicShort: BinaryOperation<AtomicDouble, AtomicShort, Double> = ::plus
    val doublePlusInt: BinaryOperation<AtomicDouble, Int, Double> = ::plus
    val doublePlusAtomicInt: BinaryOperation<AtomicDouble, AtomicInt, Double> = ::plus
    val doublePlusLong: BinaryOperation<AtomicDouble, Long, Double> = ::plus
    val doublePlusAtomicLong: BinaryOperation<AtomicDouble, AtomicLong, Double> = ::plus
    val doublePlusFloat: BinaryOperation<AtomicDouble, Float, Double> = ::plus
    val doublePlusAtomicFloat: BinaryOperation<AtomicDouble, AtomicFloat, Double> = ::plus
    val doublePlusDouble: BinaryOperation<AtomicDouble, Double, Double> = ::plus
    val doublePlusAtomicDouble: BinaryOperation<AtomicDouble, AtomicDouble, Double> = ::plus

    val doubleMinusByte: BinaryOperation<AtomicDouble, Byte, Double> = ::minus
    val doubleMinusAtomicByte: BinaryOperation<AtomicDouble, AtomicByte, Double> = ::minus
    val doubleMinusShort: BinaryOperation<AtomicDouble, Short, Double> = ::minus
    val doubleMinusAtomicShort: BinaryOperation<AtomicDouble, AtomicShort, Double> = ::minus
    val doubleMinusInt: BinaryOperation<AtomicDouble, Int, Double> = ::minus
    val doubleMinusAtomicInt: BinaryOperation<AtomicDouble, AtomicInt, Double> = ::minus
    val doubleMinusLong: BinaryOperation<AtomicDouble, Long, Double> = ::minus
    val doubleMinusAtomicLong: BinaryOperation<AtomicDouble, AtomicLong, Double> = ::minus
    val doubleMinusFloat: BinaryOperation<AtomicDouble, Float, Double> = ::minus
    val doubleMinusAtomicFloat: BinaryOperation<AtomicDouble, AtomicFloat, Double> = ::minus
    val doubleMinusDouble: BinaryOperation<AtomicDouble, Double, Double> = ::minus
    val doubleMinusAtomicDouble: BinaryOperation<AtomicDouble, AtomicDouble, Double> = ::minus

    val doubleTimesByte: BinaryOperation<AtomicDouble, Byte, Double> = ::times
    val doubleTimesAtomicByte: BinaryOperation<AtomicDouble, AtomicByte, Double> = ::times
    val doubleTimesShort: BinaryOperation<AtomicDouble, Short, Double> = ::times
    val doubleTimesAtomicShort: BinaryOperation<AtomicDouble, AtomicShort, Double> = ::times
    val doubleTimesInt: BinaryOperation<AtomicDouble, Int, Double> = ::times
    val doubleTimesAtomicInt: BinaryOperation<AtomicDouble, AtomicInt, Double> = ::times
    val doubleTimesLong: BinaryOperation<AtomicDouble, Long, Double> = ::times
    val doubleTimesAtomicLong: BinaryOperation<AtomicDouble, AtomicLong, Double> = ::times
    val doubleTimesFloat: BinaryOperation<AtomicDouble, Float, Double> = ::times
    val doubleTimesAtomicFloat: BinaryOperation<AtomicDouble, AtomicFloat, Double> = ::times
    val doubleTimesDouble: BinaryOperation<AtomicDouble, Double, Double> = ::times
    val doubleTimesAtomicDouble: BinaryOperation<AtomicDouble, AtomicDouble, Double> = ::times

    val doubleDivByte: BinaryOperation<AtomicDouble, Byte, Double> = ::div
    val doubleDivAtomicByte: BinaryOperation<AtomicDouble, AtomicByte, Double> = ::div
    val doubleDivShort: BinaryOperation<AtomicDouble, Short, Double> = ::div
    val doubleDivAtomicShort: BinaryOperation<AtomicDouble, AtomicShort, Double> = ::div
    val doubleDivInt: BinaryOperation<AtomicDouble, Int, Double> = ::div
    val doubleDivAtomicInt: BinaryOperation<AtomicDouble, AtomicInt, Double> = ::div
    val doubleDivLong: BinaryOperation<AtomicDouble, Long, Double> = ::div
    val doubleDivAtomicLong: BinaryOperation<AtomicDouble, AtomicLong, Double> = ::div
    val doubleDivFloat: BinaryOperation<AtomicDouble, Float, Double> = ::div
    val doubleDivAtomicFloat: BinaryOperation<AtomicDouble, AtomicFloat, Double> = ::div
    val doubleDivDouble: BinaryOperation<AtomicDouble, Double, Double> = ::div
    val doubleDivAtomicDouble: BinaryOperation<AtomicDouble, AtomicDouble, Double> = ::div

    val doubleRemByte: BinaryOperation<AtomicDouble, Byte, Double> = ::rem
    val doubleRemAtomicByte: BinaryOperation<AtomicDouble, AtomicByte, Double> = ::rem
    val doubleRemShort: BinaryOperation<AtomicDouble, Short, Double> = ::rem
    val doubleRemAtomicShort: BinaryOperation<AtomicDouble, AtomicShort, Double> = ::rem
    val doubleRemInt: BinaryOperation<AtomicDouble, Int, Double> = ::rem
    val doubleRemAtomicInt: BinaryOperation<AtomicDouble, AtomicInt, Double> = ::rem
    val doubleRemLong: BinaryOperation<AtomicDouble, Long, Double> = ::rem
    val doubleRemAtomicLong: BinaryOperation<AtomicDouble, AtomicLong, Double> = ::rem
    val doubleRemFloat: BinaryOperation<AtomicDouble, Float, Double> = ::rem
    val doubleRemAtomicFloat: BinaryOperation<AtomicDouble, AtomicFloat, Double> = ::rem
    val doubleRemDouble: BinaryOperation<AtomicDouble, Double, Double> = ::rem
    val doubleRemAtomicDouble: BinaryOperation<AtomicDouble, AtomicDouble, Double> = ::rem

    val doublePowByte: BinaryOperation<AtomicDouble, Byte, Double> = ::pow
    val doublePowAtomicByte: BinaryOperation<AtomicDouble, AtomicByte, Double> = ::pow
    val doublePowShort: BinaryOperation<AtomicDouble, Short, Double> = ::pow
    val doublePowAtomicShort: BinaryOperation<AtomicDouble, AtomicShort, Double> = ::pow
    val doublePowInt: BinaryOperation<AtomicDouble, Int, Double> = ::pow
    val doublePowAtomicInt: BinaryOperation<AtomicDouble, AtomicInt, Double> = ::pow
    val doublePowLong: BinaryOperation<AtomicDouble, Long, Double> = ::pow
    val doublePowAtomicLong: BinaryOperation<AtomicDouble, AtomicLong, Double> = ::pow
    val doublePowFloat: BinaryOperation<AtomicDouble, Float, Double> = ::pow
    val doublePowAtomicFloat: BinaryOperation<AtomicDouble, AtomicFloat, Double> = ::pow
    val doublePowDouble: BinaryOperation<AtomicDouble, Double, Double> = ::pow
    val doublePowAtomicDouble: BinaryOperation<AtomicDouble, AtomicDouble, Double> = ::pow

    val doubleNegate: UnaryOperation<AtomicDouble, Double> = ::negate

    fun transformPlus(source1: AtomicDouble, other: Byte): AtomicDouble = transform1(source1, other, doublePlusByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: AtomicByte): AtomicDouble = transform1(source1, other, doublePlusAtomicByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: Short): AtomicDouble = transform1(source1, other, doublePlusShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: AtomicShort): AtomicDouble = transform1(source1, other, doublePlusAtomicShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: Int): AtomicDouble = transform1(source1, other, doublePlusInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: AtomicInt): AtomicDouble = transform1(source1, other, doublePlusAtomicInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: Long): AtomicDouble = transform1(source1, other, doublePlusLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: AtomicLong): AtomicDouble = transform1(source1, other, doublePlusAtomicLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: Float): AtomicDouble = transform1(source1, other, doublePlusFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: AtomicFloat): AtomicDouble = transform1(source1, other, doublePlusAtomicFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: Double): AtomicDouble = transform1(source1, other, doublePlusDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicDouble, other: AtomicDouble): AtomicDouble = transform2(source1, other, doublePlusAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformMinus(source1: AtomicDouble, other: Byte): AtomicDouble = transform1(source1, other, doubleMinusByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: AtomicByte): AtomicDouble = transform1(source1, other, doubleMinusAtomicByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: Short): AtomicDouble = transform1(source1, other, doubleMinusShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: AtomicShort): AtomicDouble = transform1(source1, other, doubleMinusAtomicShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: Int): AtomicDouble = transform1(source1, other, doubleMinusInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: AtomicInt): AtomicDouble = transform1(source1, other, doubleMinusAtomicInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: Long): AtomicDouble = transform1(source1, other, doubleMinusLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: AtomicLong): AtomicDouble = transform1(source1, other, doubleMinusAtomicLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: Float): AtomicDouble = transform1(source1, other, doubleMinusFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: AtomicFloat): AtomicDouble = transform1(source1, other, doubleMinusAtomicFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: Double): AtomicDouble = transform1(source1, other, doubleMinusDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicDouble, other: AtomicDouble): AtomicDouble = transform2(source1, other, doubleMinusAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformTimes(source1: AtomicDouble, other: Byte): AtomicDouble = transform1(source1, other, doubleTimesByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: AtomicByte): AtomicDouble = transform1(source1, other, doubleTimesAtomicByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: Short): AtomicDouble = transform1(source1, other, doubleTimesShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: AtomicShort): AtomicDouble = transform1(source1, other, doubleTimesAtomicShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: Int): AtomicDouble = transform1(source1, other, doubleTimesInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: AtomicInt): AtomicDouble = transform1(source1, other, doubleTimesAtomicInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: Long): AtomicDouble = transform1(source1, other, doubleTimesLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: AtomicLong): AtomicDouble = transform1(source1, other, doubleTimesAtomicLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: Float): AtomicDouble = transform1(source1, other, doubleTimesFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: AtomicFloat): AtomicDouble = transform1(source1, other, doubleTimesAtomicFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: Double): AtomicDouble = transform1(source1, other, doubleTimesDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicDouble, other: AtomicDouble): AtomicDouble = transform2(source1, other, doubleTimesAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformDiv(source1: AtomicDouble, other: Byte): AtomicDouble = transform1(source1, other, doubleDivByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: AtomicByte): AtomicDouble = transform1(source1, other, doubleDivAtomicByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: Short): AtomicDouble = transform1(source1, other, doubleDivShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: AtomicShort): AtomicDouble = transform1(source1, other, doubleDivAtomicShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: Int): AtomicDouble = transform1(source1, other, doubleDivInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: AtomicInt): AtomicDouble = transform1(source1, other, doubleDivAtomicInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: Long): AtomicDouble = transform1(source1, other, doubleDivLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: AtomicLong): AtomicDouble = transform1(source1, other, doubleDivAtomicLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: Float): AtomicDouble = transform1(source1, other, doubleDivFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: AtomicFloat): AtomicDouble = transform1(source1, other, doubleDivAtomicFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: Double): AtomicDouble = transform1(source1, other, doubleDivDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicDouble, other: AtomicDouble): AtomicDouble = transform2(source1, other, doubleDivAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformRem(source1: AtomicDouble, other: Byte): AtomicDouble = transform1(source1, other, doubleRemByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: AtomicByte): AtomicDouble = transform1(source1, other, doubleRemAtomicByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: Short): AtomicDouble = transform1(source1, other, doubleRemShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: AtomicShort): AtomicDouble = transform1(source1, other, doubleRemAtomicShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: Int): AtomicDouble = transform1(source1, other, doubleRemInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: AtomicInt): AtomicDouble = transform1(source1, other, doubleRemAtomicInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: Long): AtomicDouble = transform1(source1, other, doubleRemLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: AtomicLong): AtomicDouble = transform1(source1, other, doubleRemAtomicLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: Float): AtomicDouble = transform1(source1, other, doubleRemFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: AtomicFloat): AtomicDouble = transform1(source1, other, doubleRemAtomicFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: Double): AtomicDouble = transform1(source1, other, doubleRemDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicDouble, other: AtomicDouble): AtomicDouble = transform2(source1, other, doubleRemAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformPow(source1: AtomicDouble, other: Byte): AtomicDouble = transform1(source1, other, doublePowByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: AtomicByte): AtomicDouble = transform1(source1, other, doublePowAtomicByte, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: Short): AtomicDouble = transform1(source1, other, doublePowShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: AtomicShort): AtomicDouble = transform1(source1, other, doublePowAtomicShort, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: Int): AtomicDouble = transform1(source1, other, doublePowInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: AtomicInt): AtomicDouble = transform1(source1, other, doublePowAtomicInt, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: Long): AtomicDouble = transform1(source1, other, doublePowLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: AtomicLong): AtomicDouble = transform1(source1, other, doublePowAtomicLong, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: Float): AtomicDouble = transform1(source1, other, doublePowFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: AtomicFloat): AtomicDouble = transform1(source1, other, doublePowAtomicFloat, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: Double): AtomicDouble = transform1(source1, other, doublePowDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicDouble, other: AtomicDouble): AtomicDouble = transform2(source1, other, doublePowAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformNegate(source1: AtomicDouble): AtomicDouble = transform1(source1, doubleNegate, AutoUpdateAbleAtomicDouble.creator)
}

/**
 * An atomic double value
 */
interface AtomicDouble : AtomicValue {

    /**
     * The value of the atomic double
     */
    val doubleValue: Double

    /**
     * Get the value of the atomic double
     */
    fun get(): Double

    /**
     * Add another byte to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other byte to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Byte) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicByte] to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicByte] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicByte) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another short to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other short to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Short) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicShort] to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicShort] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicShort) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another int to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other int to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Int) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicInt] to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicInt] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicInt) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another long to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other long to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Long) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicLong] to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicLong] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicLong) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another float to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other float to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Float) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicFloat] to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicFloat] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicFloat) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another double to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Double) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicDouble] to this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicDouble) = AtomicDoubleOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another byte from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other byte to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Byte) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicByte] from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicByte] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicByte) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another short from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other short to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Short) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicShort] from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicShort] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicShort) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another int from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other int to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Int) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicInt] from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicInt] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicInt) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another long from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other long to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Long) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicLong] from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicLong] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicLong) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another float from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other float to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Float) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicFloat] from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicFloat] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicFloat) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another double from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Double) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicDouble] from this double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicDouble) = AtomicDoubleOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this double with another byte (returns a new [AtomicDouble] that automatically updates)
     * @param other The other byte to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Byte) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another [AtomicByte] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicByte] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicByte) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another short (returns a new [AtomicDouble] that automatically updates)
     * @param other The other short to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Short) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another [AtomicShort] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicShort] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicShort) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other int to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Int) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another [AtomicInt] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicInt] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicInt) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another long (returns a new [AtomicDouble] that automatically updates)
     * @param other The other long to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Long) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another [AtomicLong] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicLong] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicLong) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other float to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Float) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another [AtomicFloat] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicFloat] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicFloat) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Double) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this double with another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicDouble) = AtomicDoubleOperationImplementations.transformTimes(this, other)

    /**
     * Divide this double by another byte (returns a new [AtomicDouble] that automatically updates)
     * @param other The other byte to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Byte) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another [AtomicByte] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicByte] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicByte) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another short (returns a new [AtomicDouble] that automatically updates)
     * @param other The other short to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Short) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another [AtomicShort] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicShort] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicShort) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other int to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Int) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another [AtomicInt] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicInt] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicInt) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another long (returns a new [AtomicDouble] that automatically updates)
     * @param other The other long to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Long) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another [AtomicLong] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicLong] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicLong) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other float to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Float) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another [AtomicFloat] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicFloat] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicFloat) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Double) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Divide this double by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicDouble) = AtomicDoubleOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this double by another byte (returns a new [AtomicDouble] that automatically updates)
     * @param other The other byte to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Byte) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another [AtomicByte] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicByte] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicByte) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another short (returns a new [AtomicDouble] that automatically updates)
     * @param other The other short to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Short) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another [AtomicShort] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicShort] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicShort) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other int to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Int) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another [AtomicInt] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicInt] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicInt) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another long (returns a new [AtomicDouble] that automatically updates)
     * @param other The other long to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Long) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another [AtomicLong] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicLong] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicLong) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other float to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */

    operator fun rem(other: Float) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another [AtomicFloat] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicFloat] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicFloat) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Double) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Modulo this double by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicDouble) = AtomicDoubleOperationImplementations.transformRem(this, other)

    /**
     * Power this double by another byte (returns a new [AtomicDouble] that automatically updates)
     * @param other The other byte to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Byte) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another [AtomicByte] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicByte] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicByte) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another short (returns a new [AtomicDouble] that automatically updates)
     * @param other The other short to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Short) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another [AtomicShort] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicShort] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicShort) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another int (returns a new [AtomicDouble] that automatically updates)
     * @param other The other int to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Int) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another [AtomicInt] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicInt] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicInt) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another long (returns a new [AtomicDouble] that automatically updates)
     * @param other The other long to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Long) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another [AtomicLong] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicLong] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicLong) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other float to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Float) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another [AtomicFloat] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicFloat] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicFloat) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: Double) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Power this double by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to power by
     * @return The new [AtomicDouble] that automatically updates
     */
    infix fun pow(other: AtomicDouble) = AtomicDoubleOperationImplementations.transformPow(this, other)

    /**
     * Negate this double (returns a new [AtomicDouble] that automatically updates)
     * @return The new [AtomicDouble] that automatically updates
     */
    fun negate() = AtomicDoubleOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicDouble]
     */
    fun destroy() {}
}

abstract class AtomicDoubleBase(
    initialValue: Double,
) : ListenableItemImpl(), AtomicDouble {

    final override var doubleValue: Double = initialValue
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

    protected open fun update(newValue: Double) {
        doubleValue = newValue
    }

    override fun get(): Double = doubleValue
}

class EditableAtomicDouble(
    initialValue: Double,
) : AtomicDoubleBase(initialValue) {

    public override fun update(newValue: Double) {
        super.update(newValue)
    }
}

fun atomicDoubleOf(
    initialValue: Double,
): AtomicDouble = EditableAtomicDouble(initialValue)

fun Double.atomic(): AtomicDouble = atomicDoubleOf(this)

class AutoUpdateAbleAtomicDouble(
    initialValue: Double,
) : AtomicDoubleBase(initialValue), AutoUpdatableAtomicValue<Double> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Double) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(
            initialValue: Double,
        ): AutoUpdateAbleAtomicDouble = AutoUpdateAbleAtomicDouble(initialValue)

        fun create(): AutoUpdateAbleAtomicDouble = create(0.0)

        val creator: () -> AutoUpdateAbleAtomicDouble = ::create
    }
}
