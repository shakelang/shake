package com.github.nsc.de.compiler.interpreter.values;

import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.interpreter.values.BooleanValue.FALSE;
import static com.github.nsc.de.compiler.interpreter.values.BooleanValue.TRUE;
import static com.github.nsc.de.compiler.interpreter.values.IntegerValue.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoubleValueTest {

    @Test
    public void testAdd() {
        assertEquals(2, ((DoubleValue) new DoubleValue(1).add(new DoubleValue(1))).getValue());
        assertEquals(0, ((DoubleValue) new DoubleValue(100).add(new DoubleValue(-100))).getValue());
        assertEquals(2, ((DoubleValue) new DoubleValue(1).add(ONE)).getValue());
        assertEquals(0, ((DoubleValue) new DoubleValue(100).add(new IntegerValue(-100))).getValue());
    }

    @Test
    public void testSub() {
        assertEquals(0, ((DoubleValue) new DoubleValue(1).sub(new DoubleValue(1))).getValue());
        assertEquals(200, ((DoubleValue) new DoubleValue(100).sub(new DoubleValue(-100))).getValue());
        assertEquals(0, ((DoubleValue) new DoubleValue(1).sub(ONE)).getValue());
        assertEquals(200, ((DoubleValue) new DoubleValue(100).sub(new IntegerValue(-100))).getValue());
    }

    @Test
    public void testMul() {
        assertEquals(1, ((DoubleValue) new DoubleValue(1).mul(new DoubleValue(1))).getValue());
        assertEquals(-10_000, ((DoubleValue) new DoubleValue(100).mul(new DoubleValue(-100))).getValue());
        assertEquals(1, ((DoubleValue) new DoubleValue(1).mul(new IntegerValue(1))).getValue());
        assertEquals(-10_000, ((DoubleValue) new DoubleValue(100).mul(new IntegerValue(-100))).getValue());
    }

    @Test
    public void testDiv() {
        assertEquals(5, ((DoubleValue) new DoubleValue(10).div(new IntegerValue(2))).getValue());
        assertEquals(9.6, ((DoubleValue) new DoubleValue(48).div(new IntegerValue(5))).getValue());
        assertEquals(2.2, ((DoubleValue) new DoubleValue(11).div(new DoubleValue(5))).getValue());
    }

    @Test
    public void testPow() {
        assertEquals(4, ((DoubleValue) new DoubleValue(2).pow(new IntegerValue(2))).getValue());
        assertEquals(0.04, ((DoubleValue) new DoubleValue(5).pow(new IntegerValue(-2))).getValue());
        assertEquals(2, ((DoubleValue) new DoubleValue(4).pow(new DoubleValue(.5))).getValue());
    }



    @Test
    public void testOr() {
        Error error = assertThrows(Error.class, () -> new DoubleValue(1).or(new DoubleValue(1)));
        assertEquals("Operator '||' is not defined for type double", error.getMessage());
    }

    @Test
    public void testAnd() {
        Error error = assertThrows(Error.class, () -> new DoubleValue(1).and(new DoubleValue(1)));
        assertEquals("Operator '&&' is not defined for type double", error.getMessage());
    }



    @Test
    public void testEqualsEquals() {
        assertEquals(TRUE, new DoubleValue(1).equals(ONE));
        assertEquals(TRUE, new DoubleValue(1).equals(new DoubleValue(1.0)));
        assertEquals(TRUE, new DoubleValue(1).equals(new IntegerValue(1)));
        assertEquals(FALSE, new DoubleValue(1).equals(new IntegerValue(2)));
        assertEquals(FALSE, new DoubleValue(1).equals(new DoubleValue(1.1)));
    }

    @Test
    public void testBiggerEquals() {
        assertEquals(TRUE, new DoubleValue(1).bigger_equals(ONE));
        assertEquals(TRUE, new DoubleValue(1).bigger_equals(new DoubleValue(1.0)));
        assertEquals(TRUE, new DoubleValue(1).bigger_equals(new IntegerValue(1)));
        assertEquals(TRUE, new DoubleValue(1).bigger_equals(new IntegerValue(-10)));
        assertEquals(TRUE, new DoubleValue(1).bigger_equals(new DoubleValue(-10.0)));
        assertEquals(FALSE, new DoubleValue(1).bigger_equals(new IntegerValue(10)));
        assertEquals(FALSE, new DoubleValue(1).bigger_equals(new DoubleValue(10.0)));
    }

    @Test
    public void testSmallerEquals() {
        assertEquals(TRUE, new DoubleValue(1).smaller_equals(ONE));
        assertEquals(TRUE, new DoubleValue(1).smaller_equals(new DoubleValue(1.0)));
        assertEquals(TRUE, new DoubleValue(1).smaller_equals(new IntegerValue(1)));
        assertEquals(TRUE, new DoubleValue(1).smaller_equals(new IntegerValue(10)));
        assertEquals(TRUE, new DoubleValue(1).smaller_equals(new DoubleValue(10.0)));
        assertEquals(FALSE, new DoubleValue(1).smaller_equals(new IntegerValue(-10)));
        assertEquals(FALSE, new DoubleValue(1).smaller_equals(new DoubleValue(-10.0)));
    }

    @Test
    public void testBigger() {
        assertEquals(FALSE, new DoubleValue(1).bigger(ONE));
        assertEquals(FALSE, new DoubleValue(1).bigger(new DoubleValue(1.0)));
        assertEquals(FALSE, new DoubleValue(1).bigger(new IntegerValue(1)));
        assertEquals(TRUE, new DoubleValue(1).bigger(new IntegerValue(-10)));
        assertEquals(TRUE, new DoubleValue(1).bigger(new DoubleValue(-10.0)));
        assertEquals(FALSE, new DoubleValue(1).bigger(new IntegerValue(10)));
        assertEquals(FALSE, new DoubleValue(1).bigger(new DoubleValue(10.0)));
    }

    @Test
    public void testSmaller() {
        assertEquals(FALSE, new DoubleValue(1).smaller(ONE));
        assertEquals(FALSE, new DoubleValue(1).smaller(new DoubleValue(1.0)));
        assertEquals(FALSE, new DoubleValue(1).smaller(new IntegerValue(1)));
        assertEquals(TRUE, new DoubleValue(1).smaller(new IntegerValue(10)));
        assertEquals(TRUE, new DoubleValue(1).smaller(new DoubleValue(10.0)));
        assertEquals(FALSE, new DoubleValue(1).smaller(new IntegerValue(-10)));
        assertEquals(FALSE, new DoubleValue(1).smaller(new DoubleValue(-10.0)));
    }



}
