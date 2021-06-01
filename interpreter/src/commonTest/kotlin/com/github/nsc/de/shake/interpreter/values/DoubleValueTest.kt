package com.github.nsc.de.shake.interpreter.values

import kotlin.test.*

class DoubleValueTest {
    @Test
    fun testAdd() {
        assertEquals(2.0, (DoubleValue(1.0).add(DoubleValue(1.0)) as DoubleValue).value)
        assertEquals(0.0, (DoubleValue(100.0).add(DoubleValue(-100.0)) as DoubleValue).value)
        assertEquals(2.0, (DoubleValue(1.0).add(IntegerValue.ONE) as DoubleValue).value)
        assertEquals(0.0, (DoubleValue(100.0).add(IntegerValue(-100)) as DoubleValue).value)
    }

    @Test
    fun testSub() {
        assertEquals(0.0, (DoubleValue(1.0).sub(DoubleValue(1.0)) as DoubleValue).value)
        assertEquals(200.0, (DoubleValue(100.0).sub(DoubleValue(-100.0)) as DoubleValue).value)
        assertEquals(0.0, (DoubleValue(1.0).sub(IntegerValue.ONE) as DoubleValue).value)
        assertEquals(200.0, (DoubleValue(100.0).sub(IntegerValue(-100)) as DoubleValue).value)
    }

    @Test
    fun testMul() {
        assertEquals(1.0, (DoubleValue(1.0).mul(DoubleValue(1.0)) as DoubleValue).value)
        assertEquals(-10000.0, (DoubleValue(100.0).mul(DoubleValue(-100.0)) as DoubleValue).value)
        assertEquals(1.0, (DoubleValue(1.0).mul(IntegerValue(1)) as DoubleValue).value)
        assertEquals(-10000.0, (DoubleValue(100.0).mul(IntegerValue(-100)) as DoubleValue).value)
    }

    @Test
    fun testDiv() {
        assertEquals(5.0, (DoubleValue(10.0).div(IntegerValue(2)) as DoubleValue).value)
        assertEquals(9.6, (DoubleValue(48.0).div(IntegerValue(5)) as DoubleValue).value)
        assertEquals(2.2, (DoubleValue(11.0).div(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testMod() {
        assertEquals(0.0, (DoubleValue(10.0).mod(IntegerValue(2)) as DoubleValue).value)
        assertEquals(3.0, (DoubleValue(48.0).mod(IntegerValue(5)) as DoubleValue).value)
        assertEquals(1.0, (DoubleValue(11.0).mod(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testPow() {
        assertEquals(4.0, (DoubleValue(2.0).pow(IntegerValue(2)) as DoubleValue).value)
        assertEquals(0.04, (DoubleValue(5.0).pow(IntegerValue(-2)) as DoubleValue).value)
        assertEquals(2.0, (DoubleValue(4.0).pow(DoubleValue(.5)) as DoubleValue).value)
    }

    @Test
    fun testOr() {
        val error = assertFailsWith(Error::class) { DoubleValue(1.0).or(DoubleValue(1.0)) }
        assertEquals("Operator '||' is not defined for type double", error.message)
    }

    @Test
    fun testAnd() {
        val error = assertFailsWith(Error::class) { DoubleValue(1.0).and(DoubleValue(1.0)) }
        assertEquals("Operator '&&' is not defined for type double", error.message)
    }

    @Test
    fun testEqualsEquals() {
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).equals(IntegerValue.ONE))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).equals(DoubleValue(1.0)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).equals(IntegerValue(1)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).equals(IntegerValue(2)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).equals(DoubleValue(1.1)))
    }

    @Test
    fun testBiggerEquals() {
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).biggerEquals(IntegerValue.ONE))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).biggerEquals(DoubleValue(1.0)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).biggerEquals(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).biggerEquals(IntegerValue(-10)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).biggerEquals(DoubleValue(-10.0)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).biggerEquals(IntegerValue(10)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).biggerEquals(DoubleValue(10.0)))
    }

    @Test
    fun testSmallerEquals() {
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smallerEquals(IntegerValue.ONE))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smallerEquals(DoubleValue(1.0)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smallerEquals(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smallerEquals(IntegerValue(10)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smallerEquals(DoubleValue(10.0)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smallerEquals(IntegerValue(-10)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smallerEquals(DoubleValue(-10.0)))
    }

    @Test
    fun testBigger() {
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(IntegerValue.ONE))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(DoubleValue(1.0)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger(IntegerValue(-10)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger(DoubleValue(-10.0)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(IntegerValue(10)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(DoubleValue(10.0)))
    }

    @Test
    fun testSmaller() {
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(IntegerValue.ONE))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(DoubleValue(1.0)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller(IntegerValue(10)))
        assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller(DoubleValue(10.0)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(IntegerValue(-10)))
        assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(DoubleValue(-10.0)))
    }
}