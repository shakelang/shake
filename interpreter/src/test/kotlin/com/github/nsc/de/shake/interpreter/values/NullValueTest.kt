package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NullValueTest {
    @Test
    fun testAdd() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.add(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '+' is not defined for type null", error.message)
    }

    @Test
    fun testSub() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.sub(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '-' is not defined for type null", error.message)
    }

    @Test
    fun testMul() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.mul(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '*' is not defined for type null", error.message)
    }

    @Test
    fun testDiv() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.div(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '/' is not defined for type null", error.message)
    }

    @Test
    fun testMod() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.mod(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '%' is not defined for type null", error.message)
    }

    @Test
    fun testPow() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.pow(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '**' is not defined for type null", error.message)
    }

    @Test
    fun testOr() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.or(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '||' is not defined for type null", error.message)
    }

    @Test
    fun testAnd() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.and(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '&&' is not defined for type null", error.message)
    }

    @Test
    fun testEquals() {
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.TRUE, com.github.nsc.de.shake.interpreter.values.NullValue.NULL.equals(
                com.github.nsc.de.shake.interpreter.values.NullValue.NULL))
        Assertions.assertEquals(
            com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE, com.github.nsc.de.shake.interpreter.values.NullValue.NULL.equals(
                com.github.nsc.de.shake.interpreter.values.BooleanValue.FALSE))
    }

    @Test
    fun testBiggerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.biggerEquals(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '>=' is not defined for type null", error.message)
    }

    @Test
    fun testSmallerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.smallerEquals(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '<=' is not defined for type null", error.message)
    }

    @Test
    fun testBigger() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.bigger(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '>' is not defined for type null", error.message)
    }

    @Test
    fun testSmaller() {
        val error = Assertions.assertThrows(Error::class.java) { com.github.nsc.de.shake.interpreter.values.NullValue.NULL.smaller(
            com.github.nsc.de.shake.interpreter.values.NullValue.NULL) }
        Assertions.assertEquals("Operator '<' is not defined for type null", error.message)
    }
}