package com.github.shakelang.shake.parser

import com.github.shakelang.shake.assertType
import com.github.shakelang.shake.parser.node.expression.*
import com.github.shakelang.shake.parser.node.factor.DoubleNode
import com.github.shakelang.shake.parser.node.factor.IntegerNode
import kotlin.reflect.KClass
import kotlin.test.*

class MathTests {
    @Test
    fun testBasicNumber() {
        val node = ParserTestUtil.parseSingle("<VariableUsageTest>", "10", IntegerNode::class)
        assertEquals(10, node.number)
    }

    @Test
    fun testBasicExpr() {
        testBasic("10 + 3", 10.0, 3.0, AddNode::class)
        testBasic("10 - 3", 10.0, 3.0, SubNode::class)
    }

    @Test
    fun testBasicTerm() {
        testBasic("10 * 3", 10.0, 3.0, MulNode::class)
        testBasic("10 / 3", 10.0, 3.0, DivNode::class)
        testBasic("10 % 3", 10.0, 3.0, ModNode::class)
    }

    @Test
    fun testBasicPow() {
        testBasic("10 ** 3", 10.0, 3.0, PowNode::class)
    }

    @Test
    fun testPointBeforeLine() {
        val add = ParserTestUtil.parseSingle("<PointBeforeLineTest>", "1 + 2 * 3 ** 4", AddNode::class)
        assertNotNull(add.left)
        assertType(IntegerNode::class, add.left)
        assertEquals(1, (add.left as IntegerNode).number)
        assertNotNull(add.right)
        assertType(MulNode::class, add.right)
        val mul = add.right as MulNode
        assertNotNull(mul.left)
        assertType(IntegerNode::class, mul.left)
        assertEquals(2, (mul.left as IntegerNode).number)
        assertNotNull(mul.right)
        assertType(PowNode::class, mul.right)
        val pow = mul.right as PowNode
        assertNotNull(pow.left)
        assertType(IntegerNode::class, pow.left)
        assertEquals(3, (pow.left as IntegerNode).number)
        assertNotNull(pow.right)
        assertType(IntegerNode::class, pow.right)
        assertEquals(4, (pow.right as IntegerNode).number)
    }

    @Test
    fun testBrackets() {
        val node = ParserTestUtil.parseSingle("<BracketTest>", "2 * (4 + 3)", MulNode::class)
        assertNotNull(node.left)
        assertType(IntegerNode::class, node.left)
        assertEquals(2, (node.left as IntegerNode).number)
        assertNotNull(node.right)
        assertType(AddNode::class, node.right)
        val add = node.right as AddNode
        assertNotNull(add.left)
        assertType(IntegerNode::class, add.left)
        assertEquals(4, (add.left as IntegerNode).number)
        assertNotNull(add.right)
        assertType(IntegerNode::class, add.right)
        assertEquals(3, (add.right as IntegerNode).number)
    }

    private fun <T : ExpressionNode> testBasic(input: String, left: Double, right: Double, type: KClass<T>) {
        val node = ParserTestUtil.parseSingle(
            '<'.toString() + type.simpleName!!.substring(type.simpleName!!.length - 4) + "Test>",
            input,
            type
        )
        assertNotNull(node.left)
        assertTrue(node.left is DoubleNode || node.left is IntegerNode)
        if (node.left is DoubleNode) assertEquals(
            left,
            (node.left as DoubleNode).number
        ) else assertEquals(left, (node.left as IntegerNode).number.toDouble())
        assertNotNull(node.right)
        assertTrue(node.right is DoubleNode || node.right is IntegerNode)
        if (node.right is DoubleNode) assertEquals(
            right,
            (node.right as DoubleNode).number
        ) else assertEquals(right, (node.right as IntegerNode).number.toDouble())
    }
}