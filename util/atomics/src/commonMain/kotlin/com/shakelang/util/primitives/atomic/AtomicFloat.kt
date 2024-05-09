package com.shakelang.util.primitives.atomic

import kotlin.math.pow

// Float Operations
@Suppress("MemberVisibilityCanBePrivate")
object AtomicFloatOperationImplementations {

    // Float Operations
    fun plus(source1: AtomicFloat, other: Byte): Float = (source1.floatValue + other)
    fun plus(source1: AtomicFloat, other: AtomicByte): Float = (source1.floatValue + other.byteValue)
    fun plus(source1: AtomicFloat, other: Short): Float = (source1.floatValue + other)
    fun plus(source1: AtomicFloat, other: AtomicShort): Float = (source1.floatValue + other.shortValue)
    fun plus(source1: AtomicFloat, other: Int): Float = (source1.floatValue + other)
    fun plus(source1: AtomicFloat, other: AtomicInt): Float = (source1.floatValue + other.intValue)
    fun plus(source1: AtomicFloat, other: Long): Float = (source1.floatValue + other)
    fun plus(source1: AtomicFloat, other: AtomicLong): Float = (source1.floatValue + other.longValue)
    fun plus(source1: AtomicFloat, other: Float): Float = (source1.floatValue + other)
    fun plus(source1: AtomicFloat, source2: AtomicFloat): Float = (source1.floatValue + source2.floatValue)
    fun plus(source1: AtomicFloat, other: Double): Double = (source1.floatValue + other)
    fun plus(source1: AtomicFloat, other: AtomicDouble): Double = (source1.floatValue + other.doubleValue)

    fun minus(source1: AtomicFloat, other: Byte): Float = (source1.floatValue - other)
    fun minus(source1: AtomicFloat, other: AtomicByte): Float = (source1.floatValue - other.byteValue)
    fun minus(source1: AtomicFloat, other: Short): Float = (source1.floatValue - other)
    fun minus(source1: AtomicFloat, other: AtomicShort): Float = (source1.floatValue - other.shortValue)
    fun minus(source1: AtomicFloat, other: Int): Float = (source1.floatValue - other)
    fun minus(source1: AtomicFloat, other: AtomicInt): Float = (source1.floatValue - other.intValue)
    fun minus(source1: AtomicFloat, other: Long): Float = (source1.floatValue - other)
    fun minus(source1: AtomicFloat, other: AtomicLong): Float = (source1.floatValue - other.longValue)
    fun minus(source1: AtomicFloat, other: Float): Float = (source1.floatValue - other)
    fun minus(source1: AtomicFloat, source2: AtomicFloat): Float = (source1.floatValue - source2.floatValue)
    fun minus(source1: AtomicFloat, other: Double): Double = (source1.floatValue - other)
    fun minus(source1: AtomicFloat, other: AtomicDouble): Double = (source1.floatValue - other.doubleValue)

    fun times(source1: AtomicFloat, other: Byte): Float = (source1.floatValue * other)
    fun times(source1: AtomicFloat, other: AtomicByte): Float = (source1.floatValue * other.byteValue)
    fun times(source1: AtomicFloat, other: Short): Float = (source1.floatValue * other)
    fun times(source1: AtomicFloat, other: AtomicShort): Float = (source1.floatValue * other.shortValue)
    fun times(source1: AtomicFloat, other: Int): Float = (source1.floatValue * other)
    fun times(source1: AtomicFloat, other: AtomicInt): Float = (source1.floatValue * other.intValue)
    fun times(source1: AtomicFloat, other: Long): Float = (source1.floatValue * other)
    fun times(source1: AtomicFloat, other: AtomicLong): Float = (source1.floatValue * other.longValue)
    fun times(source1: AtomicFloat, other: Float): Float = (source1.floatValue * other)
    fun times(source1: AtomicFloat, source2: AtomicFloat): Float = (source1.floatValue * source2.floatValue)
    fun times(source1: AtomicFloat, other: Double): Double = (source1.floatValue * other)
    fun times(source1: AtomicFloat, other: AtomicDouble): Double = (source1.floatValue * other.doubleValue)

    fun div(source1: AtomicFloat, other: Byte): Float = (source1.floatValue / other)
    fun div(source1: AtomicFloat, other: AtomicByte): Float = (source1.floatValue / other.byteValue)
    fun div(source1: AtomicFloat, other: Short): Float = (source1.floatValue / other)
    fun div(source1: AtomicFloat, other: AtomicShort): Float = (source1.floatValue / other.shortValue)
    fun div(source1: AtomicFloat, other: Int): Float = (source1.floatValue / other)
    fun div(source1: AtomicFloat, other: AtomicInt): Float = (source1.floatValue / other.intValue)
    fun div(source1: AtomicFloat, other: Long): Float = (source1.floatValue / other)
    fun div(source1: AtomicFloat, other: AtomicLong): Float = (source1.floatValue / other.longValue)
    fun div(source1: AtomicFloat, other: Float): Float = (source1.floatValue / other)
    fun div(source1: AtomicFloat, source2: AtomicFloat): Float = (source1.floatValue / source2.floatValue)
    fun div(source1: AtomicFloat, other: Double): Double = (source1.floatValue / other)
    fun div(source1: AtomicFloat, other: AtomicDouble): Double = (source1.floatValue / other.doubleValue)

    fun rem(source1: AtomicFloat, other: Byte): Float = (source1.floatValue % other)
    fun rem(source1: AtomicFloat, other: AtomicByte): Float = (source1.floatValue % other.byteValue)
    fun rem(source1: AtomicFloat, other: Short): Float = (source1.floatValue % other)
    fun rem(source1: AtomicFloat, other: AtomicShort): Float = (source1.floatValue % other.shortValue)
    fun rem(source1: AtomicFloat, other: Int): Float = (source1.floatValue % other)
    fun rem(source1: AtomicFloat, other: AtomicInt): Float = (source1.floatValue % other.intValue)
    fun rem(source1: AtomicFloat, other: Long): Float = (source1.floatValue % other)
    fun rem(source1: AtomicFloat, other: AtomicLong): Float = (source1.floatValue % other.longValue)
    fun rem(source1: AtomicFloat, other: Float): Float = (source1.floatValue % other)
    fun rem(source1: AtomicFloat, source2: AtomicFloat): Float = (source1.floatValue % source2.floatValue)
    fun rem(source1: AtomicFloat, other: Double): Double = (source1.floatValue % other)
    fun rem(source1: AtomicFloat, other: AtomicDouble): Double = (source1.floatValue % other.doubleValue)

    fun pow(source1: AtomicFloat, other: Byte): Float = source1.floatValue.toDouble().pow(other.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: AtomicByte): Float = source1.floatValue.toDouble().pow(other.byteValue.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: Short): Float = source1.floatValue.toDouble().pow(other.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: AtomicShort): Float = source1.floatValue.toDouble().pow(other.shortValue.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: Int): Float = source1.floatValue.toDouble().pow(other.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: AtomicInt): Float = source1.floatValue.toDouble().pow(other.intValue.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: Long): Float = source1.floatValue.toDouble().pow(other.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: AtomicLong): Float = source1.floatValue.toDouble().pow(other.longValue.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: Float): Float = source1.floatValue.toDouble().pow(other.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, source2: AtomicFloat): Float = source1.floatValue.toDouble().pow(source2.floatValue.toDouble()).toFloat()
    fun pow(source1: AtomicFloat, other: Double): Double = source1.floatValue.toDouble().pow(other)
    fun pow(source1: AtomicFloat, other: AtomicDouble): Double = source1.floatValue.toDouble().pow(other.doubleValue)

    fun negate(source1: AtomicFloat): Float = (-source1.floatValue)

    val floatPlusByte: BinaryOperation<AtomicFloat, Byte, Float> = ::plus
    val floatPlusAtomicByte: BinaryOperation<AtomicFloat, AtomicByte, Float> = ::plus
    val floatPlusShort: BinaryOperation<AtomicFloat, Short, Float> = ::plus
    val floatPlusAtomicShort: BinaryOperation<AtomicFloat, AtomicShort, Float> = ::plus
    val floatPlusInt: BinaryOperation<AtomicFloat, Int, Float> = ::plus
    val floatPlusAtomicInt: BinaryOperation<AtomicFloat, AtomicInt, Float> = ::plus
    val floatPlusLong: BinaryOperation<AtomicFloat, Long, Float> = ::plus
    val floatPlusAtomicLong: BinaryOperation<AtomicFloat, AtomicLong, Float> = ::plus
    val floatPlusFloat: BinaryOperation<AtomicFloat, Float, Float> = ::plus
    val floatPlusAtomicFloat: BinaryOperation<AtomicFloat, AtomicFloat, Float> = ::plus
    val floatPlusDouble: BinaryOperation<AtomicFloat, Double, Double> = ::plus
    val floatPlusAtomicDouble: BinaryOperation<AtomicFloat, AtomicDouble, Double> = ::plus

    val floatMinusByte: BinaryOperation<AtomicFloat, Byte, Float> = ::minus
    val floatMinusAtomicByte: BinaryOperation<AtomicFloat, AtomicByte, Float> = ::minus
    val floatMinusShort: BinaryOperation<AtomicFloat, Short, Float> = ::minus
    val floatMinusAtomicShort: BinaryOperation<AtomicFloat, AtomicShort, Float> = ::minus
    val floatMinusInt: BinaryOperation<AtomicFloat, Int, Float> = ::minus
    val floatMinusAtomicInt: BinaryOperation<AtomicFloat, AtomicInt, Float> = ::minus
    val floatMinusLong: BinaryOperation<AtomicFloat, Long, Float> = ::minus
    val floatMinusAtomicLong: BinaryOperation<AtomicFloat, AtomicLong, Float> = ::minus
    val floatMinusFloat: BinaryOperation<AtomicFloat, Float, Float> = ::minus
    val floatMinusAtomicFloat: BinaryOperation<AtomicFloat, AtomicFloat, Float> = ::minus
    val floatMinusDouble: BinaryOperation<AtomicFloat, Double, Double> = ::minus
    val floatMinusAtomicDouble: BinaryOperation<AtomicFloat, AtomicDouble, Double> = ::minus

    val floatTimesByte: BinaryOperation<AtomicFloat, Byte, Float> = ::times
    val floatTimesAtomicByte: BinaryOperation<AtomicFloat, AtomicByte, Float> = ::times
    val floatTimesShort: BinaryOperation<AtomicFloat, Short, Float> = ::times
    val floatTimesAtomicShort: BinaryOperation<AtomicFloat, AtomicShort, Float> = ::times
    val floatTimesInt: BinaryOperation<AtomicFloat, Int, Float> = ::times
    val floatTimesAtomicInt: BinaryOperation<AtomicFloat, AtomicInt, Float> = ::times
    val floatTimesLong: BinaryOperation<AtomicFloat, Long, Float> = ::times
    val floatTimesAtomicLong: BinaryOperation<AtomicFloat, AtomicLong, Float> = ::times
    val floatTimesFloat: BinaryOperation<AtomicFloat, Float, Float> = ::times
    val floatTimesAtomicFloat: BinaryOperation<AtomicFloat, AtomicFloat, Float> = ::times
    val floatTimesDouble: BinaryOperation<AtomicFloat, Double, Double> = ::times
    val floatTimesAtomicDouble: BinaryOperation<AtomicFloat, AtomicDouble, Double> = ::times

    val floatDivByte: BinaryOperation<AtomicFloat, Byte, Float> = ::div
    val floatDivAtomicByte: BinaryOperation<AtomicFloat, AtomicByte, Float> = ::div
    val floatDivShort: BinaryOperation<AtomicFloat, Short, Float> = ::div
    val floatDivAtomicShort: BinaryOperation<AtomicFloat, AtomicShort, Float> = ::div
    val floatDivInt: BinaryOperation<AtomicFloat, Int, Float> = ::div
    val floatDivAtomicInt: BinaryOperation<AtomicFloat, AtomicInt, Float> = ::div
    val floatDivLong: BinaryOperation<AtomicFloat, Long, Float> = ::div
    val floatDivAtomicLong: BinaryOperation<AtomicFloat, AtomicLong, Float> = ::div
    val floatDivFloat: BinaryOperation<AtomicFloat, Float, Float> = ::div
    val floatDivAtomicFloat: BinaryOperation<AtomicFloat, AtomicFloat, Float> = ::div
    val floatDivDouble: BinaryOperation<AtomicFloat, Double, Double> = ::div
    val floatDivAtomicDouble: BinaryOperation<AtomicFloat, AtomicDouble, Double> = ::div

    val floatRemByte: BinaryOperation<AtomicFloat, Byte, Float> = ::rem
    val floatRemAtomicByte: BinaryOperation<AtomicFloat, AtomicByte, Float> = ::rem
    val floatRemShort: BinaryOperation<AtomicFloat, Short, Float> = ::rem
    val floatRemAtomicShort: BinaryOperation<AtomicFloat, AtomicShort, Float> = ::rem
    val floatRemInt: BinaryOperation<AtomicFloat, Int, Float> = ::rem
    val floatRemAtomicInt: BinaryOperation<AtomicFloat, AtomicInt, Float> = ::rem
    val floatRemLong: BinaryOperation<AtomicFloat, Long, Float> = ::rem
    val floatRemAtomicLong: BinaryOperation<AtomicFloat, AtomicLong, Float> = ::rem
    val floatRemFloat: BinaryOperation<AtomicFloat, Float, Float> = ::rem
    val floatRemAtomicFloat: BinaryOperation<AtomicFloat, AtomicFloat, Float> = ::rem
    val floatRemDouble: BinaryOperation<AtomicFloat, Double, Double> = ::rem
    val floatRemAtomicDouble: BinaryOperation<AtomicFloat, AtomicDouble, Double> = ::rem

    val floatPowByte: BinaryOperation<AtomicFloat, Byte, Float> = ::pow
    val floatPowAtomicByte: BinaryOperation<AtomicFloat, AtomicByte, Float> = ::pow
    val floatPowShort: BinaryOperation<AtomicFloat, Short, Float> = ::pow
    val floatPowAtomicShort: BinaryOperation<AtomicFloat, AtomicShort, Float> = ::pow
    val floatPowInt: BinaryOperation<AtomicFloat, Int, Float> = ::pow
    val floatPowAtomicInt: BinaryOperation<AtomicFloat, AtomicInt, Float> = ::pow
    val floatPowLong: BinaryOperation<AtomicFloat, Long, Float> = ::pow
    val floatPowAtomicLong: BinaryOperation<AtomicFloat, AtomicLong, Float> = ::pow
    val floatPowFloat: BinaryOperation<AtomicFloat, Float, Float> = ::pow
    val floatPowAtomicFloat: BinaryOperation<AtomicFloat, AtomicFloat, Float> = ::pow
    val floatPowDouble: BinaryOperation<AtomicFloat, Double, Double> = ::pow
    val floatPowAtomicDouble: BinaryOperation<AtomicFloat, AtomicDouble, Double> = ::pow

    val floatNegate: UnaryOperation<AtomicFloat, Float> = ::negate

    fun transformPlus(source1: AtomicFloat, other: Byte): AtomicFloat = transform1(source1, other, floatPlusByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: AtomicByte): AtomicFloat = transform1(source1, other, floatPlusAtomicByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: Short): AtomicFloat = transform1(source1, other, floatPlusShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: AtomicShort): AtomicFloat = transform1(source1, other, floatPlusAtomicShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: Int): AtomicFloat = transform1(source1, other, floatPlusInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: AtomicInt): AtomicFloat = transform1(source1, other, floatPlusAtomicInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: Long): AtomicFloat = transform1(source1, other, floatPlusLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: AtomicLong): AtomicFloat = transform1(source1, other, floatPlusAtomicLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: Float): AtomicFloat = transform1(source1, other, floatPlusFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: AtomicFloat): AtomicFloat = transform2(source1, other, floatPlusAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPlus(source1: AtomicFloat, other: Double): AtomicDouble = transform1(source1, other, floatPlusDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformPlus(source1: AtomicFloat, other: AtomicDouble): AtomicDouble = transform1(source1, other, floatPlusAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformMinus(source1: AtomicFloat, other: Byte): AtomicFloat = transform1(source1, other, floatMinusByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: AtomicByte): AtomicFloat = transform1(source1, other, floatMinusAtomicByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: Short): AtomicFloat = transform1(source1, other, floatMinusShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: AtomicShort): AtomicFloat = transform1(source1, other, floatMinusAtomicShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: Int): AtomicFloat = transform1(source1, other, floatMinusInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: AtomicInt): AtomicFloat = transform1(source1, other, floatMinusAtomicInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: Long): AtomicFloat = transform1(source1, other, floatMinusLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: AtomicLong): AtomicFloat = transform1(source1, other, floatMinusAtomicLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: Float): AtomicFloat = transform1(source1, other, floatMinusFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: AtomicFloat): AtomicFloat = transform2(source1, other, floatMinusAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformMinus(source1: AtomicFloat, other: Double): AtomicDouble = transform1(source1, other, floatMinusDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformMinus(source1: AtomicFloat, other: AtomicDouble): AtomicDouble = transform1(source1, other, floatMinusAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformTimes(source1: AtomicFloat, other: Byte): AtomicFloat = transform1(source1, other, floatTimesByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: AtomicByte): AtomicFloat = transform1(source1, other, floatTimesAtomicByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: Short): AtomicFloat = transform1(source1, other, floatTimesShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: AtomicShort): AtomicFloat = transform1(source1, other, floatTimesAtomicShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: Int): AtomicFloat = transform1(source1, other, floatTimesInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: AtomicInt): AtomicFloat = transform1(source1, other, floatTimesAtomicInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: Long): AtomicFloat = transform1(source1, other, floatTimesLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: AtomicLong): AtomicFloat = transform1(source1, other, floatTimesAtomicLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: Float): AtomicFloat = transform1(source1, other, floatTimesFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: AtomicFloat): AtomicFloat = transform2(source1, other, floatTimesAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformTimes(source1: AtomicFloat, other: Double): AtomicDouble = transform1(source1, other, floatTimesDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformTimes(source1: AtomicFloat, other: AtomicDouble): AtomicDouble = transform1(source1, other, floatTimesAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformDiv(source1: AtomicFloat, other: Byte): AtomicFloat = transform1(source1, other, floatDivByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: AtomicByte): AtomicFloat = transform1(source1, other, floatDivAtomicByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: Short): AtomicFloat = transform1(source1, other, floatDivShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: AtomicShort): AtomicFloat = transform1(source1, other, floatDivAtomicShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: Int): AtomicFloat = transform1(source1, other, floatDivInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: AtomicInt): AtomicFloat = transform1(source1, other, floatDivAtomicInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: Long): AtomicFloat = transform1(source1, other, floatDivLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: AtomicLong): AtomicFloat = transform1(source1, other, floatDivAtomicLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: Float): AtomicFloat = transform1(source1, other, floatDivFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: AtomicFloat): AtomicFloat = transform2(source1, other, floatDivAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformDiv(source1: AtomicFloat, other: Double): AtomicDouble = transform1(source1, other, floatDivDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformDiv(source1: AtomicFloat, other: AtomicDouble): AtomicDouble = transform1(source1, other, floatDivAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformRem(source1: AtomicFloat, other: Byte): AtomicFloat = transform1(source1, other, floatRemByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: AtomicByte): AtomicFloat = transform1(source1, other, floatRemAtomicByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: Short): AtomicFloat = transform1(source1, other, floatRemShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: AtomicShort): AtomicFloat = transform1(source1, other, floatRemAtomicShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: Int): AtomicFloat = transform1(source1, other, floatRemInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: AtomicInt): AtomicFloat = transform1(source1, other, floatRemAtomicInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: Long): AtomicFloat = transform1(source1, other, floatRemLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: AtomicLong): AtomicFloat = transform1(source1, other, floatRemAtomicLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: Float): AtomicFloat = transform1(source1, other, floatRemFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: AtomicFloat): AtomicFloat = transform2(source1, other, floatRemAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformRem(source1: AtomicFloat, other: Double): AtomicDouble = transform1(source1, other, floatRemDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformRem(source1: AtomicFloat, other: AtomicDouble): AtomicDouble = transform1(source1, other, floatRemAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformPow(source1: AtomicFloat, other: Byte): AtomicFloat = transform1(source1, other, floatPowByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: AtomicByte): AtomicFloat = transform1(source1, other, floatPowAtomicByte, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: Short): AtomicFloat = transform1(source1, other, floatPowShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: AtomicShort): AtomicFloat = transform1(source1, other, floatPowAtomicShort, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: Int): AtomicFloat = transform1(source1, other, floatPowInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: AtomicInt): AtomicFloat = transform1(source1, other, floatPowAtomicInt, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: Long): AtomicFloat = transform1(source1, other, floatPowLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: AtomicLong): AtomicFloat = transform1(source1, other, floatPowAtomicLong, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: Float): AtomicFloat = transform1(source1, other, floatPowFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: AtomicFloat): AtomicFloat = transform2(source1, other, floatPowAtomicFloat, AutoUpdateAbleAtomicFloat.creator)
    fun transformPow(source1: AtomicFloat, other: Double): AtomicDouble = transform1(source1, other, floatPowDouble, AutoUpdateAbleAtomicDouble.creator)
    fun transformPow(source1: AtomicFloat, other: AtomicDouble): AtomicDouble = transform1(source1, other, floatPowAtomicDouble, AutoUpdateAbleAtomicDouble.creator)

    fun transformNegate(source1: AtomicFloat): AtomicFloat = transform1(source1, floatNegate, AutoUpdateAbleAtomicFloat.creator)
}

/**
 * An atomic float value
 */
interface AtomicFloat : AtomicValue {

    /**
     * The value of the atomic float
     */
    val floatValue: Float

    /**
     * Get the value of the atomic float
     */
    fun get(): Float

    /**
     * Add another byte to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other byte to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: Byte) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicByte] to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicByte] to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: AtomicByte) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another short to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other short to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: Short) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicShort] to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicShort] to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: AtomicShort) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another int to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other int to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: Int) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicInt] to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicInt] to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: AtomicInt) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another long to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other long to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: Long) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicLong] to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicLong] to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: AtomicLong) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another float to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: Float) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicFloat] to this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to add
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun plus(other: AtomicFloat) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another double to this float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: Double) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Add another [AtomicDouble] to this float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to add
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun plus(other: AtomicDouble) = AtomicFloatOperationImplementations.transformPlus(this, other)

    /**
     * Subtract another byte from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other byte to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: Byte) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicByte] from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicByte] to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: AtomicByte) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another short from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other short to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: Short) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicShort] from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicShort] to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: AtomicShort) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another int from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other int to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: Int) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicInt] from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicInt] to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: AtomicInt) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another long from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other long to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: Long) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicLong] from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicLong] to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: AtomicLong) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another float from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: Float) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicFloat] from this float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to subtract
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun minus(other: AtomicFloat) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another double from this float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: Double) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Subtract another [AtomicDouble] from this float (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to subtract
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun minus(other: AtomicDouble) = AtomicFloatOperationImplementations.transformMinus(this, other)

    /**
     * Multiply this float with another byte (returns a new [AtomicFloat] that automatically updates)
     * @param other The other byte to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: Byte) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another [AtomicByte] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicByte] to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: AtomicByte) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another short (returns a new [AtomicFloat] that automatically updates)
     * @param other The other short to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: Short) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another [AtomicShort] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicShort] to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: AtomicShort) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other int to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: Int) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another [AtomicInt] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicInt] to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: AtomicInt) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another long (returns a new [AtomicFloat] that automatically updates)
     * @param other The other long to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: Long) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another [AtomicLong] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicLong] to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: AtomicLong) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: Float) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to multiply with
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun times(other: AtomicFloat) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: Double) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Multiply this float with another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to multiply with
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun times(other: AtomicDouble) = AtomicFloatOperationImplementations.transformTimes(this, other)

    /**
     * Divide this float by another byte (returns a new [AtomicFloat] that automatically updates)
     * @param other The other byte to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: Byte) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another [AtomicByte] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicByte] to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: AtomicByte) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another short (returns a new [AtomicFloat] that automatically updates)
     * @param other The other short to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: Short) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another [AtomicShort] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicShort] to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: AtomicShort) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other int to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: Int) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another [AtomicInt] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicInt] to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: AtomicInt) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another long (returns a new [AtomicFloat] that automatically updates)
     * @param other The other long to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: Long) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another [AtomicLong] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicLong] to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: AtomicLong) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: Float) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to divide by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun div(other: AtomicFloat) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: Double) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Divide this float by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to divide by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun div(other: AtomicDouble) = AtomicFloatOperationImplementations.transformDiv(this, other)

    /**
     * Modulo this float by another byte (returns a new [AtomicFloat] that automatically updates)
     * @param other The other byte to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: Byte) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another [AtomicByte] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicByte] to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: AtomicByte) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another short (returns a new [AtomicFloat] that automatically updates)
     * @param other The other short to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: Short) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another [AtomicShort] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicShort] to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: AtomicShort) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other int to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: Int) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another [AtomicInt] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicInt] to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: AtomicInt) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another double (returns a new [AtomicDouble] that automatically updates)
     * @param other The other double to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: Double) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another [AtomicDouble] (returns a new [AtomicDouble] that automatically updates)
     * @param other The other [AtomicDouble] to modulo by
     * @return The new [AtomicDouble] that automatically updates
     */
    operator fun rem(other: AtomicDouble) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: Float) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Modulo this float by another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to modulo by
     * @return The new [AtomicFloat] that automatically updates
     */
    operator fun rem(other: AtomicFloat) = AtomicFloatOperationImplementations.transformRem(this, other)

    /**
     * Power this float by another byte (returns a new [AtomicFloat] that automatically updates)
     * @param other The other byte to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: Byte) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another [AtomicByte] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicByte] to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: AtomicByte) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another short (returns a new [AtomicFloat] that automatically updates)
     * @param other The other short to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: Short) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another [AtomicShort] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicShort] to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: AtomicShort) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another int (returns a new [AtomicFloat] that automatically updates)
     * @param other The other int to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: Int) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another [AtomicInt] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicInt] to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: AtomicInt) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another float (returns a new [AtomicFloat] that automatically updates)
     * @param other The other float to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: Float) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Power this float by another [AtomicFloat] (returns a new [AtomicFloat] that automatically updates)
     * @param other The other [AtomicFloat] to power by
     * @return The new [AtomicFloat] that automatically updates
     */
    infix fun pow(other: AtomicFloat) = AtomicFloatOperationImplementations.transformPow(this, other)

    /**
     * Negate this float (returns a new [AtomicFloat] that automatically updates)
     * @return The new [AtomicFloat] that automatically updates
     */
    fun negate() = AtomicFloatOperationImplementations.transformNegate(this)

    /**
     * Destroy the [AtomicFloat]
     */
    fun destroy() {}
}

abstract class AtomicFloatBase(
    initialValue: Float,
) : ListenableItemImpl(), AtomicFloat {

    final override var floatValue: Float = initialValue
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

    protected open fun update(newValue: Float) {
        floatValue = newValue
    }

    override fun get(): Float = floatValue
}

class EditableAtomicFloat(
    initialValue: Float,
) : AtomicFloatBase(initialValue) {

    public override fun update(newValue: Float) {
        super.update(newValue)
    }
}

fun atomicFloatOf(
    initialValue: Float,
): AtomicFloat = EditableAtomicFloat(initialValue)

fun Float.atomic(): AtomicFloat = atomicFloatOf(this)

class AutoUpdateAbleAtomicFloat(
    initialValue: Float,
) : AtomicFloatBase(initialValue), AutoUpdatableAtomicValue<Float> {
    init {
        update(initialValue)
    }

    override fun update(newValue: Float) {
        super.update(newValue)
    }

    override fun destroy() {
        super.destroy()
        destroyListeners.forEach { it() }
    }

    companion object {
        fun create(
            initialValue: Float,
        ): AutoUpdateAbleAtomicFloat = AutoUpdateAbleAtomicFloat(initialValue)

        fun create(): AutoUpdateAbleAtomicFloat = create(0.0f)

        val creator: () -> AutoUpdateAbleAtomicFloat = ::create
    }
}
