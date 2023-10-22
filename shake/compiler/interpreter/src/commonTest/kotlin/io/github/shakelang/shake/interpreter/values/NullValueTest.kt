package io.github.shakelang.shake.interpreter.values

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NullValueTest {

    @Test
    fun testAdd() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.add(NullValue.NULL) }
        assertEquals("Operator '+' is not defined for type null", error.message)
    }

    @Test
    fun testSub() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.sub(NullValue.NULL) }
        assertEquals("Operator '-' is not defined for type null", error.message)
    }

    @Test
    fun testMul() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.mul(NullValue.NULL) }
        assertEquals("Operator '*' is not defined for type null", error.message)
    }

    @Test
    fun testDiv() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.div(NullValue.NULL) }
        assertEquals("Operator '/' is not defined for type null", error.message)
    }

    @Test
    fun testMod() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.mod(NullValue.NULL) }
        assertEquals("Operator '%' is not defined for type null", error.message)
    }

    @Test
    fun testPow() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.pow(NullValue.NULL) }
        assertEquals("Operator '**' is not defined for type null", error.message)
    }

    @Test
    fun testOr() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.or(NullValue.NULL) }
        assertEquals("Operator '||' is not defined for type null", error.message)
    }

    @Test
    fun testAnd() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.and(NullValue.NULL) }
        assertEquals("Operator '&&' is not defined for type null", error.message)
    }

    @Test
    fun testEquals() {
        assertEquals(BooleanValue.TRUE, NullValue.NULL.equals(NullValue.NULL))
        assertEquals(BooleanValue.FALSE, NullValue.NULL.equals(BooleanValue.FALSE))
    }

    @Test
    fun testBiggerEquals() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.biggerEquals(NullValue.NULL) }
        assertEquals("Operator '>=' is not defined for type null", error.message)
    }

    @Test
    fun testSmallerEquals() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.smallerEquals(NullValue.NULL) }
        assertEquals("Operator '<=' is not defined for type null", error.message)
    }

    @Test
    fun testBigger() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.bigger(NullValue.NULL) }
        assertEquals("Operator '>' is not defined for type null", error.message)
    }

    @Test
    fun testSmaller() {
        val error = assertFailsWith(Error::class) { NullValue.NULL.smaller(NullValue.NULL) }
        assertEquals("Operator '<' is not defined for type null", error.message)
    }
}