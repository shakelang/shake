package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.expression.*;
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
    private <T extends ExpressionNode> void testBasic(String input, double left, double right, Class<T> type) {

        T pow = parseSingle('<'+type.getSimpleName()+"Test>", input, type);
        assertNotNull(pow.getLeft());
        assertType(NumberNode.class, pow.getLeft());
        assertEquals(left, ((NumberNode) pow.getLeft()).getNumber());
        assertNotNull(pow.getRight());
        assertType(NumberNode.class, pow.getRight());
        assertEquals(right, ((NumberNode) pow.getRight()).getNumber());

    }
}
