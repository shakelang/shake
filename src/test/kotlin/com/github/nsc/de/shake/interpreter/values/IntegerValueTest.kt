package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntegerValueTest {
    @Test
    fun testAdd() {
        Assertions.assertEquals(2, (IntegerValue.ONE.add(IntegerValue.ONE) as IntegerValue).value)
        Assertions.assertEquals(0, (IntegerValue(100).add(IntegerValue(-100)) as IntegerValue).value)
        Assertions.assertEquals(2.0, (IntegerValue.ONE.add(DoubleValue(1.0)) as DoubleValue).value)
        Assertions.assertEquals(0.0, (IntegerValue(100).add(DoubleValue(-100.0)) as DoubleValue).value)
    }

    @Test
    fun testSub() {
        Assertions.assertEquals(0, (IntegerValue.ONE.sub(IntegerValue.ONE) as IntegerValue).value)
        Assertions.assertEquals(200, (IntegerValue(100).sub(IntegerValue(-100)) as IntegerValue).value)
        Assertions.assertEquals(0.0, (IntegerValue.ONE.sub(DoubleValue(1.0)) as DoubleValue).value)
        Assertions.assertEquals(200.0, (IntegerValue(100).sub(DoubleValue(-100.0)) as DoubleValue).value)
    }

    @Test
    fun testMul() {
        Assertions.assertEquals(1, (IntegerValue.ONE.mul(IntegerValue.ONE) as IntegerValue).value)
        Assertions.assertEquals(-10000, (IntegerValue(100).mul(IntegerValue(-100)) as IntegerValue).value)
        Assertions.assertEquals(1.0, (IntegerValue.ONE.mul(DoubleValue(1.0)) as DoubleValue).value)
        Assertions.assertEquals(-10000.0, (IntegerValue(100).mul(DoubleValue(-100.0)) as DoubleValue).value)
    }

    @Test
    fun testDiv() {
        Assertions.assertEquals(5, (IntegerValue(10).div(IntegerValue(2)) as IntegerValue).value)
        Assertions.assertEquals(6, (IntegerValue(48).div(IntegerValue(7)) as IntegerValue).value)
        Assertions.assertEquals(2.2, (IntegerValue(11).div(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testMod() {
        Assertions.assertEquals(0, (IntegerValue(10).mod(IntegerValue(2)) as IntegerValue).value)
        Assertions.assertEquals(6, (IntegerValue(48).mod(IntegerValue(7)) as IntegerValue).value)
        Assertions.assertEquals(1.0, (IntegerValue(11).mod(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testPow() {
        Assertions.assertEquals(4, (IntegerValue(2).pow(IntegerValue(2)) as IntegerValue).value)
        Assertions.assertEquals(0, (IntegerValue(5).pow(IntegerValue(-2)) as IntegerValue).value)
        Assertions.assertEquals(2.0, (IntegerValue(4).pow(DoubleValue(.5)) as DoubleValue).value)
    }

    @Test
    fun testOr() {
        val error = Assertions.assertThrows(Error::class.java) { IntegerValue.ONE.or(IntegerValue.ONE) }
        Assertions.assertEquals("Operator '||' is not defined for type integer", error.message)
    }

    @Test
    fun testAnd() {
        val error = Assertions.assertThrows(Error::class.java) { IntegerValue.ONE.and(IntegerValue.ONE) }
        Assertions.assertEquals("Operator '&&' is not defined for type integer", error.message)
    }

    @Test
    fun testEqualsEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.equals(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.equals(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.equals(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.equals(IntegerValue(2)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.equals(DoubleValue(1.1)))
    }

    @Test
    fun testBiggerEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger_equals(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger_equals(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger_equals(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger_equals(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger_equals(DoubleValue(-10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger_equals(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger_equals(DoubleValue(10.0)))
    }

    @Test
    fun testSmallerEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller_equals(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller_equals(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller_equals(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller_equals(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller_equals(DoubleValue(10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller_equals(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller_equals(DoubleValue(-10.0)))
    }

    @Test
    fun testBigger() {
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger(DoubleValue(-10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(DoubleValue(10.0)))
    }

    @Test
    fun testSmaller() {
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(IntegerValue.ONE))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(DoubleValue(1.0)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(IntegerValue(1)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller(IntegerValue(10)))
        Assertions.assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller(DoubleValue(10.0)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(IntegerValue(-10)))
        Assertions.assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(DoubleValue(-10.0)))
    }
}