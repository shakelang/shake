package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import org.junit.jupiter.api.Test
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.factor.DoubleNode
import com.github.nsc.de.shake.parser.node.expression.*
import org.junit.jupiter.api.Assertions

class MathTests {
    @Test
    fun testBasicNumber() {
        val node = ParserTestUtil.parseSingle("<VariableUsageTest>", "10", IntegerNode::class.java)
        Assertions.assertEquals(10, node.number)
    }

    @Test
    fun testBasicExpr() {
        testBasic("10 + 3", 10.0, 3.0, AddNode::class.java)
        testBasic("10 - 3", 10.0, 3.0, SubNode::class.java)
    }

    @Test
    fun testBasicTerm() {
        testBasic("10 * 3", 10.0, 3.0, MulNode::class.java)
        testBasic("10 / 3", 10.0, 3.0, DivNode::class.java)
        testBasic("10 % 3", 10.0, 3.0, ModNode::class.java)
    }

    @Test
    fun testBasicPow() {
        testBasic("10 ** 3", 10.0, 3.0, PowNode::class.java)
    }

    @Test
    fun testPointBeforeLine() {
        val add = ParserTestUtil.parseSingle("<PointBeforeLineTest>", "1 + 2 * 3 ** 4", AddNode::class.java)
        Assertions.assertNotNull(add.left)
        assertType(IntegerNode::class.java, add.left)
        Assertions.assertEquals(1, (add.left as IntegerNode).number)
        Assertions.assertNotNull(add.right)
        assertType(MulNode::class.java, add.right)
        val mul = add.right as MulNode
        Assertions.assertNotNull(mul.left)
        assertType(IntegerNode::class.java, mul.left)
        Assertions.assertEquals(2, (mul.left as IntegerNode).number)
        Assertions.assertNotNull(mul.right)
        assertType(PowNode::class.java, mul.right)
        val pow = mul.right as PowNode
        Assertions.assertNotNull(pow.left)
        assertType(IntegerNode::class.java, pow.left)
        Assertions.assertEquals(3, (pow.left as IntegerNode).number)
        Assertions.assertNotNull(pow.right)
        assertType(IntegerNode::class.java, pow.right)
        Assertions.assertEquals(4, (pow.right as IntegerNode).number)
    }

    @Test
    fun testBrackets() {
        val node = ParserTestUtil.parseSingle("<BracketTest>", "2 * (4 + 3)", MulNode::class.java)
        Assertions.assertNotNull(node.left)
        assertType(IntegerNode::class.java, node.left)
        Assertions.assertEquals(2, (node.left as IntegerNode).number)
        Assertions.assertNotNull(node.right)
        assertType(AddNode::class.java, node.right)
        val add = node.right as AddNode
        Assertions.assertNotNull(add.left)
        assertType(IntegerNode::class.java, add.left)
        Assertions.assertEquals(4, (add.left as IntegerNode).number)
        Assertions.assertNotNull(add.right)
        assertType(IntegerNode::class.java, add.right)
        Assertions.assertEquals(3, (add.right as IntegerNode).number)
    }

    private fun <T : ExpressionNode?> testBasic(input: String, left: Double, right: Double, type: Class<T>) {
        val node = ParserTestUtil.parseSingle(
            '<'.toString() + type.simpleName.substring(type.simpleName.length - 4) + "Test>",
            input,
            type
        )
        Assertions.assertNotNull(node!!.left)
        Assertions.assertTrue(node.left is DoubleNode || node.left is IntegerNode)
        if (node.left is DoubleNode) Assertions.assertEquals(
            left,
            (node.left as DoubleNode).number
        ) else Assertions.assertEquals(left, (node.left as IntegerNode).number.toDouble())
        Assertions.assertNotNull(node.right)
        Assertions.assertTrue(node.right is DoubleNode || node.right is IntegerNode)
        if (node.right is DoubleNode) Assertions.assertEquals(
            right,
            (node.right as DoubleNode).number
        ) else Assertions.assertEquals(right, (node.right as IntegerNode).number.toDouble())
    }
}