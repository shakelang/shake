package io.github.shakelang.shake.interpreter.values

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

class BooleanValueTest {
    
    @Test
    fun testFromBoolean() {
        assertSame(BooleanValue.TRUE, BooleanValue.from(true))
        assertSame(BooleanValue.FALSE, BooleanValue.from(false))
    }

    @Test
    fun testFromInterpreterValue() {

        // Boolean-values
        assertSame(BooleanValue.TRUE, BooleanValue.from(BooleanValue.TRUE))
        assertSame(BooleanValue.FALSE, BooleanValue.from(BooleanValue.FALSE))

        // Integers
        assertSame(BooleanValue.TRUE, BooleanValue.from(IntegerValue.ONE))
        assertSame(BooleanValue.TRUE, BooleanValue.from(IntegerValue(Int.MAX_VALUE)))
        assertSame(BooleanValue.FALSE, BooleanValue.from(IntegerValue(Int.MIN_VALUE)))
        assertSame(BooleanValue.FALSE, BooleanValue.from(IntegerValue(0)))

        // Double
        assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(1.0)))
        assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(Double.MAX_VALUE)))
        assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(Double.MIN_VALUE)))
        assertSame(BooleanValue.TRUE, BooleanValue.from(DoubleValue(0.1)))
        assertSame(BooleanValue.FALSE, BooleanValue.from(DoubleValue(-0.1)))
        assertSame(BooleanValue.FALSE, BooleanValue.from(IntegerValue(0)))

        // Null
        assertSame(BooleanValue.FALSE, BooleanValue.from(NullValue.NULL))

        // TODO Function test
    }

    @Test
    fun testAdd() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.add(BooleanValue.TRUE) }
        assertEquals("Operator '+' is not defined for type boolean", error.message)
    }

    @Test
    fun testSub() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.sub(BooleanValue.TRUE) }
        assertEquals("Operator '-' is not defined for type boolean", error.message)
    }

    @Test
    fun testMul() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.mul(BooleanValue.TRUE) }
        assertEquals("Operator '*' is not defined for type boolean", error.message)
    }

    @Test
    fun testDiv() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.div(BooleanValue.TRUE) }
        assertEquals("Operator '/' is not defined for type boolean", error.message)
    }

    @Test
    fun testMod() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.mod(BooleanValue.TRUE) }
        assertEquals("Operator '%' is not defined for type boolean", error.message)
    }

    @Test
    fun testPow() {
        val error = assertFailsWith(Error::class) {  BooleanValue.TRUE.pow(BooleanValue.TRUE) }
        assertEquals("Operator '**' is not defined for type boolean", error.message)
    }

    @Test
    fun testOr() {
        assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.or(BooleanValue.TRUE))
        assertEquals(BooleanValue.TRUE, BooleanValue.FALSE.or(BooleanValue.TRUE))
        assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.or(BooleanValue.FALSE))
        assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.or(BooleanValue.FALSE))
    }

    @Test
    fun testAnd() {
        assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.and(BooleanValue.TRUE))
        assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.and(BooleanValue.TRUE))
        assertEquals(BooleanValue.FALSE, BooleanValue.TRUE.and(BooleanValue.FALSE))
        assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.and(BooleanValue.FALSE))
    }

    @Test
    fun testEqualsEquals() {
        assertEquals(BooleanValue.TRUE, BooleanValue.TRUE.equals(BooleanValue.TRUE))
        assertEquals(BooleanValue.FALSE, BooleanValue.FALSE.equals(BooleanValue.TRUE))
        assertEquals(BooleanValue.FALSE, BooleanValue.TRUE.equals(BooleanValue.FALSE))
        assertEquals(BooleanValue.TRUE, BooleanValue.FALSE.equals(BooleanValue.FALSE))
    }

    @Test
    fun testBiggerEquals() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.biggerEquals(BooleanValue.TRUE) }
        assertEquals("Operator '>=' is not defined for type boolean", error.message)
    }

    @Test
    fun testSmallerEquals() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.smallerEquals(BooleanValue.TRUE) }
        assertEquals("Operator '<=' is not defined for type boolean", error.message)
    }

    @Test
    fun testBigger() {
        val error = assertFailsWith(Error::class) { BooleanValue.TRUE.bigger(BooleanValue.TRUE)}
        assertEquals("Operator '>' is not defined for type boolean", error.message)
    }

    @Test
    fun testSmaller() {
        val error = assertFailsWith(Error::class) {
            BooleanValue.TRUE.smaller(BooleanValue.TRUE)
        }
        assertEquals("Operator '<' is not defined for type boolean", error.message)
    }
}