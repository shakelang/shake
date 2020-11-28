package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.expression.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.TestUtil.*;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.*;

public class MathTests {

    @Test
    public void testBasicNumber() {

        IntegerNode node = parseSingle("<VariableUsageTest>", "10", IntegerNode.class);
        assertEquals(10, node.getNumber());

    }

    @Test
    public void testBasicExpr() {

        testBasic("10 + 3", 10, 3, AddNode.class);
        testBasic("10 - 3", 10, 3, SubNode.class);
    }

    @Test
    public void testBasicTerm() {

        testBasic("10 * 3", 10, 3, MulNode.class);
        testBasic("10 / 3", 10, 3, DivNode.class);
        testBasic("10 % 3", 10, 3, ModNode.class);
    }

    @Test
    public void testBasicPow() {

        testBasic("10 ^ 3", 10, 3, PowNode.class);
        testBasic("10 ** 3", 10, 3, PowNode.class);

    }

    @Test
    public void testPointBeforeLine() {

        AddNode add = parseSingle("<PointBeforeLineTest>", "1 + 2 * 3 ^ 4", AddNode.class);
        assertNotNull(add.getLeft());
        assertType(IntegerNode.class, add.getLeft());
        assertEquals(1, ((IntegerNode) add.getLeft()).getNumber());
        assertNotNull(add.getRight());
        assertType(MulNode.class, add.getRight());

        MulNode mul = (MulNode) add.getRight();
        assertNotNull(mul.getLeft());
        assertType(IntegerNode.class, mul.getLeft());
        assertEquals(2, ((IntegerNode) mul.getLeft()).getNumber());
        assertNotNull(mul.getRight());
        assertType(PowNode.class, mul.getRight());

        PowNode pow = (PowNode) mul.getRight();
        assertNotNull(pow.getLeft());
        assertType(IntegerNode.class, pow.getLeft());
        assertEquals(3, ((IntegerNode) pow.getLeft()).getNumber());
        assertNotNull(pow.getRight());
        assertType(IntegerNode.class, pow.getRight());
        assertEquals(4, ((IntegerNode) pow.getRight()).getNumber());

    }

    @Test
    public void testBrackets() {

        MulNode node = parseSingle("<BracketTest>", "2 * (4 + 3)", MulNode.class);
        assertNotNull(node.getLeft());
        assertType(IntegerNode.class, node.getLeft());
        assertEquals(2, ((IntegerNode) node.getLeft()).getNumber());
        assertNotNull(node.getRight());
        assertType(AddNode.class, node.getRight());

        AddNode add = (AddNode) node.getRight();
        assertNotNull(add.getLeft());
        assertType(IntegerNode.class, add.getLeft());
        assertEquals(4, ((IntegerNode) add.getLeft()).getNumber());
        assertNotNull(add.getRight());
        assertType(IntegerNode.class, add.getRight());
        assertEquals(3, ((IntegerNode) add.getRight()).getNumber());

    }

    private <T extends ExpressionNode> void testBasic(String input, double left, double right, Class<T> type) {

        T node = parseSingle('<'+type.getSimpleName().substring(type.getSimpleName().length() - 4)+"Test>", input, type);
        assertNotNull(node.getLeft());
        assertTrue(node.getLeft() instanceof DoubleNode || node.getLeft() instanceof IntegerNode);
        if(node.getLeft() instanceof DoubleNode) assertEquals(left, ((DoubleNode) node.getLeft()).getNumber());
        else assertEquals(left, ((IntegerNode) node.getLeft()).getNumber());
        assertNotNull(node.getRight());
        assertTrue(node.getRight() instanceof DoubleNode || node.getRight() instanceof IntegerNode);
        if(node.getRight() instanceof DoubleNode) assertEquals(right, ((DoubleNode) node.getRight()).getNumber());
        else assertEquals(right, ((IntegerNode) node.getRight()).getNumber());

    }
}
