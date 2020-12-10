package com.github.nsc.de.shake.interpreter.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.shake.interpreter.values.BooleanValue.*;

public class BooleanValueTest {

    @Test
    public void testFromBoolean() {

        assertSame(TRUE, from(true));
        assertSame(FALSE, from(false));

    }

    @Test
    public void testFromInterpreterValue() {

        // Boolean-values
        assertSame(TRUE, from(TRUE));
        assertSame(FALSE, from(FALSE));

        // Integers
        assertSame(TRUE, from(IntegerValue.ONE));
        assertSame(TRUE, from(new IntegerValue(Integer.MAX_VALUE)));
        assertSame(FALSE, from(new IntegerValue(Integer.MIN_VALUE)));
        assertSame(FALSE, from(new IntegerValue(0)));

        // Double
        assertSame(TRUE, from(new DoubleValue(1)));
        assertSame(TRUE, from(new DoubleValue(Double.MAX_VALUE)));
        assertSame(TRUE, from(new DoubleValue(Double.MIN_VALUE)));
        assertSame(TRUE, from(new DoubleValue(0.1)));
        assertSame(FALSE, from(new DoubleValue(-0.1)));
        assertSame(FALSE, from(new IntegerValue(0)));

        // Null
        assertSame(FALSE, from(NullValue.NULL));

        // TODO Function test

    }

    @Test
    public void testAdd() {
        Error error = assertThrows(Error.class, () -> TRUE.add(TRUE));
        assertEquals("Operator '+' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testSub() {
        Error error = assertThrows(Error.class, () -> TRUE.sub(TRUE));
        assertEquals("Operator '-' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testMul() {
        Error error = assertThrows(Error.class, () -> TRUE.mul(TRUE));
        assertEquals("Operator '*' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testDiv() {
        Error error = assertThrows(Error.class, () -> TRUE.div(TRUE));
        assertEquals("Operator '/' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testMod() {
        Error error = assertThrows(Error.class, () -> TRUE.mod(TRUE));
        assertEquals("Operator '%' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testPow() {
        Error error = assertThrows(Error.class, () -> TRUE.pow(TRUE));
        assertEquals("Operator '**' is not defined for type boolean", error.getMessage());
    }



    @Test
    public void testOr() {
        assertEquals(TRUE, TRUE.or(TRUE));
        assertEquals(TRUE, FALSE.or(TRUE));
        assertEquals(TRUE, TRUE.or(FALSE));
        assertEquals(FALSE, FALSE.or(FALSE));
    }

    @Test
    public void testAnd() {
        assertEquals(TRUE, TRUE.and(TRUE));
        assertEquals(FALSE, FALSE.and(TRUE));
        assertEquals(FALSE, TRUE.and(FALSE));
        assertEquals(FALSE, FALSE.and(FALSE));
    }



    @Test
    public void testEqualsEquals() {
        assertEquals(TRUE, TRUE.equals(TRUE));
        assertEquals(FALSE, FALSE.equals(TRUE));
        assertEquals(FALSE, TRUE.equals(FALSE));
        assertEquals(TRUE, FALSE.equals(FALSE));
    }

    @Test
    public void testBiggerEquals() {
        Error error = assertThrows(Error.class, () -> TRUE.bigger_equals(TRUE));
        assertEquals("Operator '>=' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testSmallerEquals() {
        Error error = assertThrows(Error.class, () -> TRUE.smaller_equals(TRUE));
        assertEquals("Operator '<=' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testBigger() {
        Error error = assertThrows(Error.class, () -> TRUE.bigger(TRUE));
        assertEquals("Operator '>' is not defined for type boolean", error.getMessage());
    }

    @Test
    public void testSmaller() {
        Error error = assertThrows(Error.class, () -> TRUE.smaller(TRUE));
        assertEquals("Operator '<' is not defined for type boolean", error.getMessage());
    }



}
