package com.github.nsc.de.shake.interpreter.values

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import java.lang.Error

class NullValueTest {
    @Test
    fun testAdd() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.add(NullValue.NULL) }
        Assertions.assertEquals("Operator '+' is not defined for type null", error.message)
    }

    @Test
    fun testSub() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.sub(NullValue.NULL) }
        Assertions.assertEquals("Operator '-' is not defined for type null", error.message)
    }

    @Test
    fun testMul() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.mul(NullValue.NULL) }
        Assertions.assertEquals("Operator '*' is not defined for type null", error.message)
    }

    @Test
    fun testDiv() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.div(NullValue.NULL) }
        Assertions.assertEquals("Operator '/' is not defined for type null", error.message)
    }

    @Test
    fun testMod() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.mod(NullValue.NULL) }
        Assertions.assertEquals("Operator '%' is not defined for type null", error.message)
    }

    @Test
    fun testPow() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.pow(NullValue.NULL) }
        Assertions.assertEquals("Operator '**' is not defined for type null", error.message)
    }

    @Test
    fun testOr() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.or(NullValue.NULL) }
        Assertions.assertEquals("Operator '||' is not defined for type null", error.message)
    }

    @Test
    fun testAnd() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.and(NullValue.NULL) }
        Assertions.assertEquals("Operator '&&' is not defined for type null", error.message)
    }

    @Test
    fun testEquals() {
        Assertions.assertEquals(BooleanValue.TRUE, NullValue.NULL.equals(NullValue.NULL))
        Assertions.assertEquals(BooleanValue.FALSE, NullValue.NULL.equals(BooleanValue.FALSE))
    }

    @Test
    fun testBiggerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.bigger_equals(NullValue.NULL) }
        Assertions.assertEquals("Operator '>=' is not defined for type null", error.message)
    }

    @Test
    fun testSmallerEquals() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.smaller_equals(NullValue.NULL) }
        Assertions.assertEquals("Operator '<=' is not defined for type null", error.message)
    }

    @Test
    fun testBigger() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.bigger(NullValue.NULL) }
        Assertions.assertEquals("Operator '>' is not defined for type null", error.message)
    }

    @Test
    fun testSmaller() {
        val error = Assertions.assertThrows(Error::class.java) { NullValue.NULL.smaller(NullValue.NULL) }
        Assertions.assertEquals("Operator '<' is not defined for type null", error.message)
    }
}