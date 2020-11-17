package com.github.nsc.de.compiler.interpreter.values;

import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.interpreter.values.IntegerValue.*;
import static com.github.nsc.de.compiler.interpreter.values.BooleanValue.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerValueTest {

    @Test
    public void testAdd() {
        assertEquals(2, ((IntegerValue) ONE.add(ONE)).getValue());
        assertEquals(0, ((IntegerValue) new IntegerValue(100).add(new IntegerValue(-100))).getValue());
    }

    @Test
    public void testSub() {
        assertEquals(0, ((IntegerValue) ONE.sub(ONE)).getValue());
        assertEquals(200, ((IntegerValue) new IntegerValue(100).sub(new IntegerValue(-100))).getValue());
    }

    @Test
    public void testMul() {
        assertEquals(1, ((IntegerValue) ONE.mul(ONE)).getValue());
        assertEquals(-10_000, ((IntegerValue) new IntegerValue(100).mul(new IntegerValue(-100))).getValue());
    }

    @Test
    public void testDiv() {
        assertEquals(5, ((IntegerValue) new IntegerValue(10).div(new IntegerValue(2))).getValue());
        assertEquals(6, ((IntegerValue) new IntegerValue(48).div(new IntegerValue(7))).getValue());
        assertEquals(2.2, ((DoubleValue) new IntegerValue(11).div(new DoubleValue(5))).getValue());
    }

    @Test
    public void testPow() {
        assertEquals(4, ((IntegerValue) new IntegerValue(2).pow(new IntegerValue(2))).getValue());
        assertEquals(0, ((IntegerValue) new IntegerValue(5).pow(new IntegerValue(-2))).getValue());
        assertEquals(2, ((DoubleValue) new IntegerValue(4).pow(new DoubleValue(.5))).getValue());
    }



    @Test
    public void testOr() {
        Error error = assertThrows(Error.class, () -> ONE.or(ONE));
        assertEquals("Operator '||' is not defined for type integer", error.getMessage());
    }

    @Test
    public void testAnd() {
        Error error = assertThrows(Error.class, () -> ONE.and(ONE));
        assertEquals("Operator '&&' is not defined for type integer", error.getMessage());
    }



    @Test
    public void testEqualsEquals() {
        assertEquals(TRUE, ONE.equals(ONE));
        assertEquals(TRUE, ONE.equals(new DoubleValue(1.0)));
        assertEquals(TRUE, ONE.equals(new IntegerValue(1)));
        assertEquals(FALSE, ONE.equals(new IntegerValue(2)));
        assertEquals(FALSE, ONE.equals(new DoubleValue(1.1)));
    }

    @Test
    public void testBiggerEquals() {
        assertEquals(TRUE, ONE.bigger_equals(ONE));
        assertEquals(TRUE, ONE.bigger_equals(new DoubleValue(1.0)));
        assertEquals(TRUE, ONE.bigger_equals(new IntegerValue(1)));
        assertEquals(TRUE, ONE.bigger_equals(new IntegerValue(-10)));
        assertEquals(TRUE, ONE.bigger_equals(new DoubleValue(-10.0)));
        assertEquals(FALSE, ONE.bigger_equals(new IntegerValue(10)));
        assertEquals(FALSE, ONE.bigger_equals(new DoubleValue(10.0)));
    }

    @Test
    public void testSmallerEquals() {
        assertEquals(TRUE, ONE.smaller_equals(ONE));
        assertEquals(TRUE, ONE.smaller_equals(new DoubleValue(1.0)));
        assertEquals(TRUE, ONE.smaller_equals(new IntegerValue(1)));
        assertEquals(TRUE, ONE.smaller_equals(new IntegerValue(10)));
        assertEquals(TRUE, ONE.smaller_equals(new DoubleValue(10.0)));
        assertEquals(FALSE, ONE.smaller_equals(new IntegerValue(-10)));
        assertEquals(FALSE, ONE.smaller_equals(new DoubleValue(-10.0)));
    }

    @Test
    public void testBigger() {
        assertEquals(FALSE, ONE.bigger(ONE));
        assertEquals(FALSE, ONE.bigger(new DoubleValue(1.0)));
        assertEquals(FALSE, ONE.bigger(new IntegerValue(1)));
        assertEquals(TRUE, ONE.bigger(new IntegerValue(-10)));
        assertEquals(TRUE, ONE.bigger(new DoubleValue(-10.0)));
        assertEquals(FALSE, ONE.bigger(new IntegerValue(10)));
        assertEquals(FALSE, ONE.bigger(new DoubleValue(10.0)));
    }

    @Test
    public void testSmaller() {
        assertEquals(FALSE, ONE.smaller(ONE));
        assertEquals(FALSE, ONE.smaller(new DoubleValue(1.0)));
        assertEquals(FALSE, ONE.smaller(new IntegerValue(1)));
        assertEquals(TRUE, ONE.smaller(new IntegerValue(10)));
        assertEquals(TRUE, ONE.smaller(new DoubleValue(10.0)));
        assertEquals(FALSE, ONE.smaller(new IntegerValue(-10)));
        assertEquals(FALSE, ONE.smaller(new DoubleValue(-10.0)));
    }



}
