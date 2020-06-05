package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.node.logical.LogicalAndNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalFalseNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalOrNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.TestUtil.*;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.*;

public class MathTests {

    @Test
    public void testBasicNumber() {

        NumberNode node = parseSingle("<VariableUsageTest>", "10", NumberNode.class);
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
        assertType(NumberNode.class, add.getLeft());
        assertEquals(1, ((NumberNode) add.getLeft()).getNumber());
        assertNotNull(add.getRight());
        assertType(MulNode.class, add.getRight());

        MulNode mul = (MulNode) add.getRight();
        assertNotNull(mul.getLeft());
        assertType(NumberNode.class, mul.getLeft());
        assertEquals(2, ((NumberNode) mul.getLeft()).getNumber());
        assertNotNull(mul.getRight());
        assertType(PowNode.class, mul.getRight());

        PowNode pow = (PowNode) mul.getRight();
        assertNotNull(pow.getLeft());
        assertType(NumberNode.class, pow.getLeft());
        assertEquals(3, ((NumberNode) pow.getLeft()).getNumber());
        assertNotNull(pow.getRight());
        assertType(NumberNode.class, pow.getRight());
        assertEquals(4, ((NumberNode) pow.getRight()).getNumber());

    }

    @Test
    public void testBrackets() {

        MulNode node = parseSingle("<BracketTest>", "2 * (4 + 3)", MulNode.class);
        assertNotNull(node.getLeft());
        assertType(NumberNode.class, node.getLeft());
        assertEquals(2, ((NumberNode) node.getLeft()).getNumber());
        assertNotNull(node.getRight());
        assertType(AddNode.class, node.getRight());

        AddNode add = (AddNode) node.getRight();
        assertNotNull(add.getLeft());
        assertType(NumberNode.class, add.getLeft());
        assertEquals(4, ((NumberNode) add.getLeft()).getNumber());
        assertNotNull(add.getRight());
        assertType(NumberNode.class, add.getRight());
        assertEquals(3, ((NumberNode) add.getRight()).getNumber());

    }

    private <T extends ExpressionNode> void testBasic(String input, double left, double right, Class<T> type) {

        T node = parseSingle('<'+type.getSimpleName().substring(type.getSimpleName().length() - 4)+"Test>", input, type);
        assertNotNull(node.getLeft());
        assertType(NumberNode.class, node.getLeft());
        assertEquals(left, ((NumberNode) node.getLeft()).getNumber());
        assertNotNull(node.getRight());
        assertType(NumberNode.class, node.getRight());
        assertEquals(right, ((NumberNode) node.getRight()).getNumber());

    }
}
