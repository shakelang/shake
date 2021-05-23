package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BooleanValueTest {
    @Test
    fun testFromBoolean() {
        Assertions.assertSame(com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(true))
        Assertions.assertSame(com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(false))
    }

    @Test
    fun testFromInterpreterValue() {

        // Boolean-values
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))

        // Integers
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.IntegerValue.ONE))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(Int.MAX_VALUE)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(Int.MIN_VALUE)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(0)
            ))

        // Double
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(1.0)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(Double.MAX_VALUE)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(Double.MIN_VALUE)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(0.1)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.DoubleValue(-0.1)
            ))
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.IntegerValue(0)
            ))

        // Null
        Assertions.assertSame(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.from(
                com.github.nsc.de.shake.interpreter.values.NullValue.NULL))

        // TODO Function test
    }

    @Test
    fun testAdd() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.add(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '+' is not defined for type boolean", error.message)
    }

    @Test
    fun testSub() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.sub(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '-' is not defined for type boolean", error.message)
    }

    @Test
    fun testMul() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.mul(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '*' is not defined for type boolean", error.message)
    }

    @Test
    fun testDiv() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.div(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '/' is not defined for type boolean", error.message)
    }

    @Test
    fun testMod() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.mod(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '%' is not defined for type boolean", error.message)
    }

    @Test
    fun testPow() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.pow(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '**' is not defined for type boolean", error.message)
    }

    @Test
    fun testOr() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.or(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE.or(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.or(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE.or(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
    }

    @Test
    fun testAnd() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.and(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE.and(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.and(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE.and(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
    }

    @Test
    fun testEqualsEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.equals(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE.equals(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.equals(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE.equals(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
    }

    @Test
    fun testBiggerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.bigger_equals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '>=' is not defined for type boolean", error.message)
    }

    @Test
    fun testSmallerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.smaller_equals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '<=' is not defined for type boolean", error.message)
    }

    @Test
    fun testBigger() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.bigger(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '>' is not defined for type boolean", error.message)
    }

    @Test
    fun testSmaller() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE.smaller(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE) }
        Assertions.assertEquals("Operator '<' is not defined for type boolean", error.message)
    }
}