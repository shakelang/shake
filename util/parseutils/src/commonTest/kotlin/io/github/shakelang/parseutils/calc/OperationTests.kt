package io.github.shakelang.parseutils.calc

import kotlin.test.Test
import kotlin.test.assertEquals

class OperationTests {

    @Test
    fun testBytePlus() {
        assertEquals(2, 1.toByte().plus(1.toUByte()))
        assertEquals(2, 1.toByte().plus(1.toUShort()))
        assertEquals(2, 1.toByte().plus(1.toUInt()))
        assertEquals(2, 1.toByte().plus(1.toULong()))
    }

    @Test
    fun testShortPlus() {
        assertEquals(2, 1.toShort().plus(1.toUByte()))
        assertEquals(2, 1.toShort().plus(1.toUShort()))
        assertEquals(2, 1.toShort().plus(1.toUInt()))
        assertEquals(2, 1.toShort().plus(1.toULong()))
    }

    @Test
    fun testIntPlus() {
        assertEquals(2, 1.plus(1.toUByte()))
        assertEquals(2, 1.plus(1.toUShort()))
        assertEquals(2, 1.plus(1.toUInt()))
        assertEquals(2, 1.plus(1.toULong()))
    }

    @Test
    fun testLongPlus() {
        assertEquals(2, 1.toLong().plus(1.toUByte()))
        assertEquals(2, 1.toLong().plus(1.toUShort()))
        assertEquals(2, 1.toLong().plus(1.toUInt()))
        assertEquals(2, 1.toLong().plus(1.toULong()))
    }

    @Test
    fun testByteMinus() {
        assertEquals(0, 1.toByte().minus(1.toUByte()))
        assertEquals(0, 1.toByte().minus(1.toUShort()))
        assertEquals(0, 1.toByte().minus(1.toUInt()))
        assertEquals(0, 1.toByte().minus(1.toULong()))
    }

    @Test
    fun testShortMinus() {
        assertEquals(0, 1.toShort().minus(1.toUByte()))
        assertEquals(0, 1.toShort().minus(1.toUShort()))
        assertEquals(0, 1.toShort().minus(1.toUInt()))
        assertEquals(0, 1.toShort().minus(1.toULong()))
    }

    @Test
    fun testIntMinus() {
        assertEquals(0, 1.minus(1.toUByte()))
        assertEquals(0, 1.minus(1.toUShort()))
        assertEquals(0, 1.minus(1.toUInt()))
        assertEquals(0, 1.minus(1.toULong()))
    }

    @Test
    fun testLongMinus() {
        assertEquals(0, 1.toLong().minus(1.toUByte()))
        assertEquals(0, 1.toLong().minus(1.toUShort()))
        assertEquals(0, 1.toLong().minus(1.toUInt()))
        assertEquals(0, 1.toLong().minus(1.toULong()))
    }

    @Test
    fun testByteTimes() {
        assertEquals(8, 2.toByte().times(4.toUByte()))
        assertEquals(8, 2.toByte().times(4.toUShort()))
        assertEquals(8, 2.toByte().times(4.toUInt()))
        assertEquals(8, 2.toByte().times(4.toULong()))
    }

    @Test
    fun testShortTimes() {
        assertEquals(8, 2.toShort().times(4.toUByte()))
        assertEquals(8, 2.toShort().times(4.toUShort()))
        assertEquals(8, 2.toShort().times(4.toUInt()))
        assertEquals(8, 2.toShort().times(4.toULong()))
    }

    @Test
    fun testIntTimes() {
        assertEquals(8, 2.times(4.toUByte()))
        assertEquals(8, 2.times(4.toUShort()))
        assertEquals(8, 2.times(4.toUInt()))
        assertEquals(8, 2.times(4.toULong()))
    }

    @Test
    fun testLongTimes() {
        assertEquals(8, 2.toLong().times(4.toUByte()))
        assertEquals(8, 2.toLong().times(4.toUShort()))
        assertEquals(8, 2.toLong().times(4.toUInt()))
        assertEquals(8, 2.toLong().times(4.toULong()))
    }

    @Test
    fun testByteDiv() {
        assertEquals(2, 8.toByte().div(4.toUByte()))
        assertEquals(2, 8.toByte().div(4.toUShort()))
        assertEquals(2, 8.toByte().div(4.toUInt()))
        assertEquals(2, 8.toByte().div(4.toULong()))
    }

    @Test
    fun testShortDiv() {
        assertEquals(2, 8.toShort().div(4.toUByte()))
        assertEquals(2, 8.toShort().div(4.toUShort()))
        assertEquals(2, 8.toShort().div(4.toUInt()))
        assertEquals(2, 8.toShort().div(4.toULong()))
    }

    @Test
    fun testIntDiv() {
        assertEquals(2, 8.div(4.toUByte()))
        assertEquals(2, 8.div(4.toUShort()))
        assertEquals(2, 8.div(4.toUInt()))
        assertEquals(2, 8.div(4.toULong()))
    }

    @Test
    fun testLongDiv() {
        assertEquals(2, 8.toLong().div(4.toUByte()))
        assertEquals(2, 8.toLong().div(4.toUShort()))
        assertEquals(2, 8.toLong().div(4.toUInt()))
        assertEquals(2, 8.toLong().div(4.toULong()))
    }

    @Test
    fun testByteRem() {
        assertEquals(0, 8.toByte().rem(4.toUByte()))
        assertEquals(0, 8.toByte().rem(4.toUShort()))
        assertEquals(0, 8.toByte().rem(4.toUInt()))
        assertEquals(0, 8.toByte().rem(4.toULong()))
    }

    @Test
    fun testShortRem() {
        assertEquals(0, 8.toShort().rem(4.toUByte()))
        assertEquals(0, 8.toShort().rem(4.toUShort()))
        assertEquals(0, 8.toShort().rem(4.toUInt()))
        assertEquals(0, 8.toShort().rem(4.toULong()))
    }

    @Test
    fun testIntRem() {
        assertEquals(0, 8.rem(4.toUByte()))
        assertEquals(0, 8.rem(4.toUShort()))
        assertEquals(0, 8.rem(4.toUInt()))
        assertEquals(0, 8.rem(4.toULong()))
    }

    @Test
    fun testLongRem() {
        assertEquals(0, 8.toLong().rem(4.toUByte()))
        assertEquals(0, 8.toLong().rem(4.toUShort()))
        assertEquals(0, 8.toLong().rem(4.toUInt()))
        assertEquals(0, 8.toLong().rem(4.toULong()))
    }

    @Test
    fun testUBytePlus() {
        assertEquals(2, 1.toUByte().plus(1.toByte()))
        assertEquals(2, 1.toUByte().plus(1.toShort()))
        assertEquals(2.toLong(), 1.toUByte().plus(1.toInt()))
        assertEquals(2.toLong(), 1.toUByte().plus(1.toLong()))
    }

    @Test
    fun testUShortPlus() {
        assertEquals(2, 1.toUShort().plus(1.toByte()))
        assertEquals(2, 1.toUShort().plus(1.toShort()))
        assertEquals(2.toLong(), 1.toUShort().plus(1.toInt()))
        assertEquals(2.toLong(), 1.toUShort().plus(1.toLong()))
    }

    @Test
    fun testUIntPlus() {
        assertEquals(2, 1.toUInt().plus(1.toByte()))
        assertEquals(2, 1.toUInt().plus(1.toShort()))
        assertEquals(2.toLong(), 1.toUInt().plus(1.toInt()))
        assertEquals(2.toLong(), 1.toUInt().plus(1.toLong()))
    }

    @Test
    fun testULongPlus() {
        assertEquals(2, 1.toULong().plus(1.toByte()))
        assertEquals(2, 1.toULong().plus(1.toShort()))
        assertEquals(2.toLong(), 1.toULong().plus(1.toInt()))
        assertEquals(2.toLong(), 1.toULong().plus(1.toLong()))
    }

    @Test
    fun testUByteMinus() {
        assertEquals(0, 1.toUByte().minus(1.toByte()))
        assertEquals(0, 1.toUByte().minus(1.toShort()))
        assertEquals(0.toLong(), 1.toUByte().minus(1.toInt()))
        assertEquals(0.toLong(), 1.toUByte().minus(1.toLong()))
    }

    @Test
    fun testUShortMinus() {
        assertEquals(0, 1.toUShort().minus(1.toByte()))
        assertEquals(0, 1.toUShort().minus(1.toShort()))
        assertEquals(0.toLong(), 1.toUShort().minus(1.toInt()))
        assertEquals(0.toLong(), 1.toUShort().minus(1.toLong()))
    }

    @Test
    fun testUIntMinus() {
        assertEquals(0, 1.toUInt().minus(1.toByte()))
        assertEquals(0, 1.toUInt().minus(1.toShort()))
        assertEquals(0.toLong(), 1.toUInt().minus(1.toInt()))
        assertEquals(0.toLong(), 1.toUInt().minus(1.toLong()))
    }

    @Test
    fun testULongMinus() {
        assertEquals(0, 1.toULong().minus(1.toByte()))
        assertEquals(0, 1.toULong().minus(1.toShort()))
        assertEquals(0.toLong(), 1.toULong().minus(1.toInt()))
        assertEquals(0.toLong(), 1.toULong().minus(1.toLong()))
    }

    @Test
    fun testUByteTimes() {
        assertEquals(1, 1.toUByte().times(1.toByte()))
        assertEquals(1, 1.toUByte().times(1.toShort()))
        assertEquals(1.toLong(), 1.toUByte().times(1.toInt()))
        assertEquals(1.toLong(), 1.toUByte().times(1.toLong()))
    }

    @Test
    fun testUShortTimes() {
        assertEquals(1, 1.toUShort().times(1.toByte()))
        assertEquals(1, 1.toUShort().times(1.toShort()))
        assertEquals(1.toLong(), 1.toUShort().times(1.toInt()))
        assertEquals(1.toLong(), 1.toUShort().times(1.toLong()))
    }

    @Test
    fun testUIntTimes() {
        assertEquals(1, 1.toUInt().times(1.toByte()))
        assertEquals(1, 1.toUInt().times(1.toShort()))
        assertEquals(1.toLong(), 1.toUInt().times(1.toInt()))
        assertEquals(1.toLong(), 1.toUInt().times(1.toLong()))
    }

    @Test
    fun testULongTimes() {
        assertEquals(1, 1.toULong().times(1.toByte()))
        assertEquals(1, 1.toULong().times(1.toShort()))
        assertEquals(1.toLong(), 1.toULong().times(1.toInt()))
        assertEquals(1.toLong(), 1.toULong().times(1.toLong()))
    }

    @Test
    fun testUByteDiv() {
        assertEquals(1, 1.toUByte().div(1.toByte()))
        assertEquals(1, 1.toUByte().div(1.toShort()))
        assertEquals(1.toLong(), 1.toUByte().div(1.toInt()))
        assertEquals(1.toLong(), 1.toUByte().div(1.toLong()))
    }

    @Test
    fun testUShortDiv() {
        assertEquals(1, 1.toUShort().div(1.toByte()))
        assertEquals(1, 1.toUShort().div(1.toShort()))
        assertEquals(1.toLong(), 1.toUShort().div(1.toInt()))
        assertEquals(1.toLong(), 1.toUShort().div(1.toLong()))
    }

    @Test
    fun testUIntDiv() {
        assertEquals(1, 1.toUInt().div(1.toByte()))
        assertEquals(1, 1.toUInt().div(1.toShort()))
        assertEquals(1.toLong(), 1.toUInt().div(1.toInt()))
        assertEquals(1.toLong(), 1.toUInt().div(1.toLong()))
    }

    @Test
    fun testULongDiv() {
        assertEquals(1, 1.toULong().div(1.toByte()))
        assertEquals(1, 1.toULong().div(1.toShort()))
        assertEquals(1.toLong(), 1.toULong().div(1.toInt()))
        assertEquals(1.toLong(), 1.toULong().div(1.toLong()))
    }

    @Test
    fun testUByteRem() {
        assertEquals(0, 1.toUByte().rem(1.toByte()))
        assertEquals(0, 1.toUByte().rem(1.toShort()))
        assertEquals(0.toLong(), 1.toUByte().rem(1.toInt()))
        assertEquals(0.toLong(), 1.toUByte().rem(1.toLong()))
    }

    @Test
    fun testUShortRem() {
        assertEquals(0, 1.toUShort().rem(1.toByte()))
        assertEquals(0, 1.toUShort().rem(1.toShort()))
        assertEquals(0.toLong(), 1.toUShort().rem(1.toInt()))
        assertEquals(0.toLong(), 1.toUShort().rem(1.toLong()))
    }

    @Test
    fun testUIntRem() {
        assertEquals(0, 1.toUInt().rem(1.toByte()))
        assertEquals(0, 1.toUInt().rem(1.toShort()))
        assertEquals(0.toLong(), 1.toUInt().rem(1.toInt()))
        assertEquals(0.toLong(), 1.toUInt().rem(1.toLong()))
    }

    @Test
    fun testULongRem() {
        assertEquals(0, 1.toULong().rem(1.toByte()))
        assertEquals(0, 1.toULong().rem(1.toShort()))
        assertEquals(0.toLong(), 1.toULong().rem(1.toInt()))
        assertEquals(0.toLong(), 1.toULong().rem(1.toLong()))
    }
}