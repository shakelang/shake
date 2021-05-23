package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntegerValueTest {
    @Test
    fun testAdd() {
        Assertions.assertEquals(2, (com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.add(
            com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(100)
            .add(com.github.nsc.de.shake.interpreter.values.IntegerValue(-100)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(2.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.add(
            com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
        ) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(100)
            .add(com.github.nsc.de.shake.interpreter.values.DoubleValue(-100.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testSub() {
        Assertions.assertEquals(0, (com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.sub(
            com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(200, (com.github.nsc.de.shake.interpreter.values.IntegerValue(100)
            .sub(com.github.nsc.de.shake.interpreter.values.IntegerValue(-100)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(0.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.sub(
            com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
        ) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(200.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(100)
            .sub(com.github.nsc.de.shake.interpreter.values.DoubleValue(-100.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testMul() {
        Assertions.assertEquals(1, (com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.mul(
            com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(-10000, (com.github.nsc.de.shake.interpreter.values.IntegerValue(100)
            .mul(com.github.nsc.de.shake.interpreter.values.IntegerValue(-100)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(1.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.mul(
            com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
        ) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
        Assertions.assertEquals(-10000.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(
            100
        )
            .mul(com.github.nsc.de.shake.interpreter.values.DoubleValue(-100.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testDiv() {
        Assertions.assertEquals(5, (com.github.nsc.de.shake.interpreter.values.IntegerValue(10)
            .div(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(6, (com.github.nsc.de.shake.interpreter.values.IntegerValue(48)
            .div(com.github.nsc.de.shake.interpreter.values.IntegerValue(7)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(2.2, (com.github.nsc.de.shake.interpreter.values.IntegerValue(11)
            .div(com.github.nsc.de.shake.interpreter.values.DoubleValue(5.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testMod() {
        Assertions.assertEquals(0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(10)
            .mod(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(6, (com.github.nsc.de.shake.interpreter.values.IntegerValue(48)
            .mod(com.github.nsc.de.shake.interpreter.values.IntegerValue(7)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(1.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(11)
            .mod(com.github.nsc.de.shake.interpreter.values.DoubleValue(5.0)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testPow() {
        Assertions.assertEquals(4, (com.github.nsc.de.shake.interpreter.values.IntegerValue(2)
            .pow(com.github.nsc.de.shake.interpreter.values.IntegerValue(2)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(5)
            .pow(com.github.nsc.de.shake.interpreter.values.IntegerValue(-2)) as com.github.nsc.de.shake.interpreter.values.IntegerValue).value)
        Assertions.assertEquals(2.0, (com.github.nsc.de.shake.interpreter.values.IntegerValue(4)
            .pow(com.github.nsc.de.shake.interpreter.values.DoubleValue(.5)) as com.github.nsc.de.shake.interpreter.values.DoubleValue).value)
    }

    @Test
    fun testOr() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.or(
            com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) }
        Assertions.assertEquals("Operator '||' is not defined for type integer", error.message)
    }

    @Test
    fun testAnd() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.and(
            com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE) }
        Assertions.assertEquals("Operator '&&' is not defined for type integer", error.message)
    }

    @Test
    fun testEqualsEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(1)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(2)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.1)
            ))
    }

    @Test
    fun testBiggerEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(1)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger_equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)
            ))
    }

    @Test
    fun testSmallerEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(1)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller_equals(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)
            ))
    }

    @Test
    fun testBigger() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(1)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.bigger(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)
            ))
    }

    @Test
    fun testSmaller() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(1)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(10.0)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(-10)
            ))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE.smaller(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(-10.0)
            ))
    }
}