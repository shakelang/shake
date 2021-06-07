package com.github.shakelang.shake.parser

import com.github.shakelang.shake.assertType
import com.github.shakelang.shake.parser.node.expression.AddNode
import com.github.shakelang.shake.parser.node.expression.MulNode
import com.github.shakelang.shake.parser.node.factor.DoubleNode
import com.github.shakelang.shake.parser.node.factor.IntegerNode
import com.github.shakelang.shake.parser.node.logical.*
import kotlin.reflect.KClass
import kotlin.test.*

class LogicalTests {
    @Test
    fun testTrue() {
        assertNotNull(ParserTestUtil.parseSingle("<LogicalTrueTest>", "true", LogicalTrueNode::class))
    }

    @Test
    fun testFalse() {
        assertNotNull(
            ParserTestUtil.parseSingle(
                "<LogicalFalseTest>",
                "false",
                LogicalFalseNode::class
            )
        )
    }

    @Test
    fun testEqEquals() {
        testBasic("10==10", 10.0, 10.0, LogicalEqEqualsNode::class)
    }

    @Test
    fun testBiggerEquals() {
        testBasic("10>=10", 10.0, 10.0, LogicalBiggerEqualsNode::class)
    }

    @Test
    fun testSmallerEquals() {
        testBasic("10<=10", 10.0, 10.0, LogicalSmallerEqualsNode::class)
    }

    @Test
    fun testBigger() {
        testBasic("10>10", 10.0, 10.0, LogicalBiggerNode::class)
    }

    @Test
    fun testSmaller() {
        testBasic("10<10", 10.0, 10.0, LogicalSmallerNode::class)
    }

    @Test
    fun testAnd() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalAndTest>",
            "true && false",
            LogicalAndNode::class
        )
        assertNotNull(node.left)
        assertType(LogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(LogicalFalseNode::class, node.right)
    }

    @Test
    fun testOr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalOrTest>",
            "true || false",
            LogicalOrNode::class
        )
        assertNotNull(node.left)
        assertType(LogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(LogicalFalseNode::class, node.right)
    }

    @Test
    fun testXOr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalXOrTest>",
            "true ^ false",
            LogicalXOrNode::class
        )
        assertNotNull(node.left)
        assertType(LogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(LogicalFalseNode::class, node.right)
    }

    @Test
    fun testBrackets() {
        val node =
            ParserTestUtil.parseSingle(
                "<LogicalBracketTest>",
                "true && (false || true)",
                LogicalAndNode::class
            )
        assertNotNull(node.left)
        assertType(LogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(LogicalOrNode::class, node.right)
        val or = node.right as LogicalOrNode
        assertNotNull(or.left)
        assertType(LogicalFalseNode::class, or.left)
        assertNotNull(or.right)
        assertType(LogicalTrueNode::class, or.right)
    }

    @Test
    fun testExpr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalExpressionTest>",
            "10 >= 5 + 9 * 2",
            LogicalBiggerEqualsNode::class
        )
        assertNotNull(node.left)
        assertType(IntegerNode::class, node.left)
        assertEquals(10, (node.left as IntegerNode).number)
        assertNotNull(node.right)
        assertType(AddNode::class, node.right)
        val add = node.right as AddNode
        assertNotNull(add.left)
        assertType(IntegerNode::class, add.left)
        assertEquals(5, (add.left as IntegerNode).number)
        assertNotNull(add.right)
        assertType(MulNode::class, add.right)
        val mul = add.right as MulNode
        assertNotNull(mul.left)
        assertType(IntegerNode::class, mul.left)
        assertEquals(9, (mul.left as IntegerNode).number)
        assertNotNull(mul.right)
        assertType(IntegerNode::class, mul.right)
        assertEquals(2, (mul.right as IntegerNode).number)
    }

    private fun <T : LogicalCompareNode> testBasic(input: String, left: Double, right: Double, type: KClass<T>) {
        val node = ParserTestUtil.parseSingle(
            '<'.toString() + type.simpleName?.substring(type.simpleName?.length?.minus(4) ?: 0) + "Test>",
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