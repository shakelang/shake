package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DoubleValueTest {
    @Test
    fun testAdd() {
        Assertions.assertEquals(2.0, (DoubleValue(1.0).add(DoubleValue(1.0)) as DoubleValue).value)
        Assertions.assertEquals(0.0, (DoubleValue(100.0).add(DoubleValue(-100.0)) as DoubleValue).value)
        Assertions.assertEquals(2.0, (DoubleValue(1.0).add(IntegerValue.ONE) as DoubleValue).value)
        Assertions.assertEquals(0.0, (DoubleValue(100.0).add(IntegerValue(-100)) as DoubleValue).value)
    }

    @Test
    fun testSub() {
        Assertions.assertEquals(0.0, (DoubleValue(1.0).sub(DoubleValue(1.0)) as DoubleValue).value)
        Assertions.assertEquals(200.0, (DoubleValue(100.0).sub(DoubleValue(-100.0)) as DoubleValue).value)
        Assertions.assertEquals(0.0, (DoubleValue(1.0).sub(IntegerValue.ONE) as DoubleValue).value)
        Assertions.assertEquals(200.0, (DoubleValue(100.0).sub(IntegerValue(-100)) as DoubleValue).value)
    }

    @Test
    fun testMul() {
        Assertions.assertEquals(1.0, (DoubleValue(1.0).mul(DoubleValue(1.0)) as DoubleValue).value)
        Assertions.assertEquals(-10000.0, (DoubleValue(100.0).mul(DoubleValue(-100.0)) as DoubleValue).value)
        Assertions.assertEquals(1.0, (DoubleValue(1.0).mul(IntegerValue(1)) as DoubleValue).value)
        Assertions.assertEquals(-10000.0, (DoubleValue(100.0).mul(IntegerValue(-100)) as DoubleValue).value)
    }

    @Test
    fun testDiv() {
        Assertions.assertEquals(5.0, (DoubleValue(10.0).div(IntegerValue(2)) as DoubleValue).value)
        Assertions.assertEquals(9.6, (DoubleValue(48.0).div(IntegerValue(5)) as DoubleValue).value)
        Assertions.assertEquals(2.2, (DoubleValue(11.0).div(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testMod() {
        Assertions.assertEquals(0.0, (DoubleValue(10.0).mod(IntegerValue(2)) as DoubleValue).value)
        Assertions.assertEquals(3.0, (DoubleValue(48.0).mod(IntegerValue(5)) as DoubleValue).value)
        Assertions.assertEquals(1.0, (DoubleValue(11.0).mod(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testPow() {
        Assertions.assertEquals(4.0, (DoubleValue(2.0).pow(IntegerValue(2)) as DoubleValue).value)
        Assertions.assertEquals(0.04, (DoubleValue(5.0).pow(IntegerValue(-2)) as DoubleValue).value)
        Assertions.assertEquals(2.0, (DoubleValue(4.0).pow(DoubleValue(.5)) as DoubleValue).value)
    }

    @Test
    fun testOr() {
        val error = Assertions.assertThrows(Error::class.java) { DoubleValue(1.0).or(DoubleValue(1.0)) }
        Assertions.assertEquals("Operator '||' is not defined for type double", error.message)
    }

    @Test
    fun testAnd() {
        val error = Assertions.assertThrows(Error::class.java) { DoubleValue(1.0).and(DoubleValue(1.0)) }
        Assertions.assertEquals("Operator '&&' is not defined for type double", error.message)
    }

    @Test
    fun testEqualsEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).equals(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).equals(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).equals(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).equals(IntegerValue(2)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).equals(DoubleValue(1.1)))
    }

    @Test
    fun testBiggerEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger_equals(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger_equals(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger_equals(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger_equals(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger_equals(DoubleValue(-10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger_equals(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger_equals(DoubleValue(10.0)))
    }

    @Test
    fun testSmallerEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller_equals(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller_equals(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller_equals(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller_equals(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller_equals(DoubleValue(10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller_equals(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller_equals(DoubleValue(-10.0)))
    }

    @Test
    fun testBigger() {
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).bigger(DoubleValue(-10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).bigger(DoubleValue(10.0)))
    }

    @Test
    fun testSmaller() {
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.TRUE, DoubleValue(1.0).smaller(DoubleValue(10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.FALSE, DoubleValue(1.0).smaller(DoubleValue(-10.0)))
    }
}