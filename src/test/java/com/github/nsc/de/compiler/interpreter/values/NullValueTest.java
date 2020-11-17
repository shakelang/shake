package com.github.nsc.de.compiler.interpreter.values;

import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.interpreter.values.NullValue.*;
import static com.github.nsc.de.compiler.interpreter.values.BooleanValue.*;
import static org.junit.jupiter.api.Assertions.*;

public class NullValueTest {

    @Test
    public void testAdd() {
        Error error = assertThrows(Error.class, () -> NULL.add(NULL));
        assertEquals("Operator '+' is not defined for type null", error.getMessage());
    }

    @Test
    public void testSub() {
        Error error = assertThrows(Error.class, () -> NULL.sub(NULL));
        assertEquals("Operator '-' is not defined for type null", error.getMessage());
    }

    @Test
    public void testMul() {
        Error error = assertThrows(Error.class, () -> NULL.mul(NULL));
        assertEquals("Operator '*' is not defined for type null", error.getMessage());
    }

    @Test
    public void testDiv() {
        Error error = assertThrows(Error.class, () -> NULL.div(NULL));
        assertEquals("Operator '/' is not defined for type null", error.getMessage());
    }

    @Test
    public void testPow() {
        Error error = assertThrows(Error.class, () -> NULL.pow(NULL));
        assertEquals("Operator '**' is not defined for type null", error.getMessage());
    }



    @Test
    public void testOr() {
        Error error = assertThrows(Error.class, () -> NULL.or(NULL));
        assertEquals("Operator '||' is not defined for type null", error.getMessage());
    }

    @Test
    public void testAnd() {
        Error error = assertThrows(Error.class, () -> NULL.and(NULL));
        assertEquals("Operator '&&' is not defined for type null", error.getMessage());
    }



    @Test
    public void testEqualsEquals() {
        assertEquals(TRUE, NULL.equals(NULL));
        Error error = assertThrows(Error.class, () -> NULL.equals(FALSE));
        assertEquals("Operator '==' is not defined for null and boolean", error.getMessage());
    }

    @Test
    public void testBiggerEquals() {
        Error error = assertThrows(Error.class, () -> NULL.bigger_equals(NULL));
        assertEquals("Operator '>=' is not defined for type null", error.getMessage());
    }

    @Test
    public void testSmallerEquals() {
        Error error = assertThrows(Error.class, () -> NULL.smaller_equals(NULL));
        assertEquals("Operator '<=' is not defined for type null", error.getMessage());
    }

    @Test
    public void testBigger() {
        Error error = assertThrows(Error.class, () -> NULL.bigger(NULL));
        assertEquals("Operator '>' is not defined for type null", error.getMessage());
    }

    @Test
    public void testSmaller() {
        Error error = assertThrows(Error.class, () -> NULL.smaller(NULL));
        assertEquals("Operator '<' is not defined for type null", error.getMessage());
    }



}
