package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BooleanValueTest {
    @Test
    fun testFromBoolean() {
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(true))
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(false))
    }

    @Test
    fun testFromInterpreterValue() {

        // Boolean-values
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(BooleanValue.TRUE))
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(BooleanValue.FALSE))

        // Integers
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(IntegerValue.ONE))
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(IntegerValue(Int.MAX_VALUE)))
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(IntegerValue(Int.MIN_VALUE)))
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(IntegerValue(0)))

        // Double
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(1.0)))
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(Double.MAX_VALUE)))
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(Double.MIN_VALUE)))
        Assertions.assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(0.1)))
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(DoubleValue(-0.1)))
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(IntegerValue(0)))

        // Null
        Assertions.assertSame(BooleanValue.FALSE, BooleanValue.from(NullValue.NULL))

        // TODO Function test
    }

    @Test
    fun testAdd() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.add(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '+' is not defined for type boolean", error.message)
    }

    @Test
    fun testSub() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.sub(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '-' is not defined for type boolean", error.message)
    }

    @Test
    fun testMul() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.mul(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '*' is not defined for type boolean", error.message)
    }

    @Test
    fun testDiv() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.div(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '/' is not defined for type boolean", error.message)
    }

    @Test
    fun testMod() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.mod(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '%' is not defined for type boolean", error.message)
    }

    @Test
    fun testPow() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.pow(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '**' is not defined for type boolean", error.message)
    }

    @Test
    fun testOr() {
        Assertions.assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.or(BooleanValue.TRUE))
        Assertions.assertEquals(BooleanValue.TRUE, BooleanValue.FALSE.or(BooleanValue.TRUE))
        Assertions.assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.or(BooleanValue.FALSE))
        Assertions.assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.or(BooleanValue.FALSE))
    }

    @Test
    fun testAnd() {
        Assertions.assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.and(BooleanValue.TRUE))
        Assertions.assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.and(BooleanValue.TRUE))
        Assertions.assertEquals(BooleanValue.FALSE, BooleanValue.TRUE.and(BooleanValue.FALSE))
        Assertions.assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.and(BooleanValue.FALSE))
    }

    @Test
    fun testEqualsEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.equals(BooleanValue.TRUE))
        Assertions.assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.equals(BooleanValue.TRUE))
        Assertions.assertEquals(BooleanValue.FALSE, BooleanValue.TRUE.equals(BooleanValue.FALSE))
        Assertions.assertEquals(BooleanValue.TRUE, BooleanValue.FALSE.equals(BooleanValue.FALSE))
    }

    @Test
    fun testBiggerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.bigger_equals(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '>=' is not defined for type boolean", error.message)
    }

    @Test
    fun testSmallerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.smaller_equals(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '<=' is not defined for type boolean", error.message)
    }

    @Test
    fun testBigger() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.bigger(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '>' is not defined for type boolean", error.message)
    }

    @Test
    fun testSmaller() {
        val error = Assertions.assertThrows(Error::class.java) { BooleanValue.TRUE.smaller(BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '<' is not defined for type boolean", error.message)
    }
}