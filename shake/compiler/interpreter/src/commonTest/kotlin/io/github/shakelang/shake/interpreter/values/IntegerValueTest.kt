package io.github.shakelang.shake.interpreter.values

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class IntegerValueTest {

    @Test
    fun testAdd() {
        assertEquals(2, (IntegerValue.ONE.add(IntegerValue.ONE) as IntegerValue).value)
        assertEquals(0, (IntegerValue(100).add(IntegerValue(-100)) as IntegerValue).value)
        assertEquals(2.0, (IntegerValue.ONE.add(DoubleValue(1.0)) as DoubleValue).value)
        assertEquals(0.0, (IntegerValue(100).add(DoubleValue(-100.0)) as DoubleValue).value)
    }

    @Test
    fun testSub() {
        assertEquals(0, (IntegerValue.ONE.sub(IntegerValue.ONE) as IntegerValue).value)
        assertEquals(200, (IntegerValue(100).sub(IntegerValue(-100)) as IntegerValue).value)
        assertEquals(0.0, (IntegerValue.ONE.sub(DoubleValue(1.0)) as DoubleValue).value)
        assertEquals(200.0, (IntegerValue(100).sub(DoubleValue(-100.0)) as DoubleValue).value)
    }

    @Test
    fun testMul() {
        assertEquals(1, (IntegerValue.ONE.mul(IntegerValue.ONE) as IntegerValue).value)
        assertEquals(-10000, (IntegerValue(100).mul(IntegerValue(-100)) as IntegerValue).value)
        assertEquals(1.0, (IntegerValue.ONE.mul(DoubleValue(1.0)) as DoubleValue).value)
        assertEquals(-10000.0, (IntegerValue(100).mul(DoubleValue(-100.0)) as DoubleValue).value)
    }

    @Test
    fun testDiv() {
        assertEquals(5, (IntegerValue(10).div(IntegerValue(2)) as IntegerValue).value)
        assertEquals(6, (IntegerValue(48).div(IntegerValue(7)) as IntegerValue).value)
        assertEquals(2.2, (IntegerValue(11).div(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testMod() {
        assertEquals(0, (IntegerValue(10).mod(IntegerValue(2)) as IntegerValue).value)
        assertEquals(6, (IntegerValue(48).mod(IntegerValue(7)) as IntegerValue).value)
        assertEquals(1.0, (IntegerValue(11).mod(DoubleValue(5.0)) as DoubleValue).value)
    }

    @Test
    fun testPow() {
        assertEquals(4, (IntegerValue(2).pow(IntegerValue(2)) as IntegerValue).value)
        assertEquals(0, (IntegerValue(5).pow(IntegerValue(-2)) as IntegerValue).value)
        assertEquals(2.0, (IntegerValue(4).pow(DoubleValue(.5)) as DoubleValue).value)
    }

    @Test
    fun testOr() {
        val error = assertFailsWith(Error::class) { IntegerValue.ONE.or(IntegerValue.ONE) }
        assertEquals("Operator '||' is not defined for type integer", error.message)
    }

    @Test
    fun testAnd() {
        val error = assertFailsWith(Error::class) { IntegerValue.ONE.and(IntegerValue.ONE) }
        assertEquals("Operator '&&' is not defined for type integer", error.message)
    }

    @Test
    fun testEqualsEquals() {
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.equals(IntegerValue.ONE))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.equals(DoubleValue(1.0)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.equals(IntegerValue(1)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.equals(IntegerValue(2)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.equals(DoubleValue(1.1)))
    }

    @Test
    fun testBiggerEquals() {
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.biggerEquals(IntegerValue.ONE))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.biggerEquals(DoubleValue(1.0)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.biggerEquals(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.biggerEquals(IntegerValue(-10)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.biggerEquals(DoubleValue(-10.0)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.biggerEquals(IntegerValue(10)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.biggerEquals(DoubleValue(10.0)))
    }

    @Test
    fun testSmallerEquals() {
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smallerEquals(IntegerValue.ONE))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smallerEquals(DoubleValue(1.0)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smallerEquals(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smallerEquals(IntegerValue(10)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smallerEquals(DoubleValue(10.0)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smallerEquals(IntegerValue(-10)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smallerEquals(DoubleValue(-10.0)))
    }

    @Test
    fun testBigger() {
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(IntegerValue.ONE))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(DoubleValue(1.0)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger(IntegerValue(-10)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.bigger(DoubleValue(-10.0)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(IntegerValue(10)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.bigger(DoubleValue(10.0)))
    }

    @Test
    fun testSmaller() {
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(IntegerValue.ONE))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(DoubleValue(1.0)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(IntegerValue(1)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller(IntegerValue(10)))
        assertEquals(BooleanValue.TRUE, IntegerValue.ONE.smaller(DoubleValue(10.0)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(IntegerValue(-10)))
        assertEquals(BooleanValue.FALSE, IntegerValue.ONE.smaller(DoubleValue(-10.0)))
    }
}