package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DoubleValueTest {
    @Test
    fun testAdd() {
        Assertions.assertEquals(2.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            .add(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(100.0)
            .add(com.github.nsc.de.shake.interpreter.values.DoubleValue(-100.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(2.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            .add(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(100.0)
            .add(com.github.nsc.de.shake.interpreter.values.IntegerValue(-100)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testSub() {
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            .sub(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(200.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(100.0)
            .sub(com.github.nsc.de.shake.interpreter.values.DoubleValue(-100.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            .sub(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(200.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(100.0)
            .sub(com.github.nsc.de.shake.interpreter.values.IntegerValue(-100)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testMul() {
        Assertions.assertEquals(1.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            .mul(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(-10000.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(
            100.0
        )
            .mul(com.github.nsc.de.shake.interpreter.values.DoubleValue(-100.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(1.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            .mul(com.github.nsc.de.shake.interpreter.values.IntegerValue(1)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(-10000.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(
            100.0
        )
            .mul(com.github.nsc.de.shake.interpreter.values.IntegerValue(-100)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testDiv() {
        Assertions.assertEquals(5.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)
            .div(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(9.6, (com.github.nsc.de.shake.interpreter.values.DoubleValue(48.0)
            .div(com.github.nsc.de.shake.interpreter.values.IntegerValue(5)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(2.2, (com.github.nsc.de.shake.interpreter.values.DoubleValue(11.0)
            .div(com.github.nsc.de.shake.interpreter.values.DoubleValue(5.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testMod() {
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)
            .mod(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(3.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(48.0)
            .mod(com.github.nsc.de.shake.interpreter.values.IntegerValue(5)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(1.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(11.0)
            .mod(com.github.nsc.de.shake.interpreter.values.DoubleValue(5.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testPow() {
        Assertions.assertEquals(4.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(2.0)
            .pow(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(0.04, (com.github.nsc.de.shake.interpreter.values.DoubleValue(5.0)
            .pow(com.github.nsc.de.shake.interpreter.values.IntegerValue(-2)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(2.0, (com.github.nsc.de.shake.interpreter.values.DoubleValue(4.0)
            .pow(com.github.nsc.de.shake.interpreter.values.DoubleValue(.5)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testOr() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.DoubleValue(
            1.0
        ).or(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)) }
        Assertions.assertEquals("Operator '||' is not defined for type double", error.message)
    }

    @Test
    fun testAnd() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.DoubleValue(
            1.0
        ).and(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)) }
        Assertions.assertEquals("Operator '&&' is not defined for type double", error.message)
    }

    @Test
    fun testEqualsEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).equals(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).equals(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).equals(com.github.nsc.de.shake.interpreter.values.IntegerValue(1)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).equals(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).equals(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.1)))
    }

    @Test
    fun testBiggerEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue(1)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue(10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).biggerEquals(com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)))
    }

    @Test
    fun testSmallerEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue(1)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue(10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smallerEquals(com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)))
    }

    @Test
    fun testBigger() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.IntegerValue(1)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.IntegerValue(10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).bigger(com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)))
    }

    @Test
    fun testSmaller() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.IntegerValue(1)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.IntegerValue(10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.DoubleValue(
                1.0
            ).smaller(com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)))
    }
}