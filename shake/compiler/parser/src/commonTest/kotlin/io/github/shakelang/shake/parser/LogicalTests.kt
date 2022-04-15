package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.expression.ShakeAddNode
import io.github.shakelang.shake.parser.node.expression.ShakeMulNode
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.logical.*
import kotlin.reflect.KClass
import kotlin.test.*

class LogicalTests {
    @Test
    fun testTrue() {
        assertNotNull(ParserTestUtil.parseSingle("<LogicalTrueTest>", "true", ShakeLogicalTrueNode::class))
    }

    @Test
    fun testFalse() {
        assertNotNull(
            ParserTestUtil.parseSingle(
                "<LogicalFalseTest>",
                "false",
                ShakeLogicalFalseNode::class
            )
        )
    }

    @Test
    fun testEqEquals() {
        testBasic("10==10", 10.0, 10.0, ShakeLogicalEqEqualsNode::class)
    }

    @Test
    fun testBiggerEquals() {
        testBasic("10>=10", 10.0, 10.0, ShakeLogicalBiggerEqualsNode::class)
    }

    @Test
    fun testSmallerEquals() {
        testBasic("10<=10", 10.0, 10.0, ShakeLogicalSmallerEqualsNode::class)
    }

    @Test
    fun testBigger() {
        testBasic("10>10", 10.0, 10.0, ShakeLogicalBiggerNode::class)
    }

    @Test
    fun testSmaller() {
        testBasic("10<10", 10.0, 10.0, ShakeLogicalSmallerNode::class)
    }

    @Test
    fun testAnd() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalAndTest>",
            "true && false",
            ShakeLogicalAndNode::class
        )
        assertNotNull(node.left)
        assertType(ShakeLogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(ShakeLogicalFalseNode::class, node.right)
    }

    @Test
    fun testOr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalOrTest>",
            "true || false",
            ShakeLogicalOrNode::class
        )
        assertNotNull(node.left)
        assertType(ShakeLogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(ShakeLogicalFalseNode::class, node.right)
    }

    @Test
    fun testXOr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalXOrTest>",
            "true ^ false",
            ShakeLogicalXOrNode::class
        )
        assertNotNull(node.left)
        assertType(ShakeLogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(ShakeLogicalFalseNode::class, node.right)
    }

    @Test
    fun testBrackets() {
        val node =
            ParserTestUtil.parseSingle(
                "<LogicalBracketTest>",
                "true && (false || true)",
                ShakeLogicalAndNode::class
            )
        assertNotNull(node.left)
        assertType(ShakeLogicalTrueNode::class, node.left)
        assertNotNull(node.right)
        assertType(ShakeLogicalOrNode::class, node.right)
        val or = node.right as ShakeLogicalOrNode
        assertNotNull(or.left)
        assertType(ShakeLogicalFalseNode::class, or.left)
        assertNotNull(or.right)
        assertType(ShakeLogicalTrueNode::class, or.right)
    }

    @Test
    fun testExpr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalExpressionTest>",
            "10 >= 5 + 9 * 2",
            ShakeLogicalBiggerEqualsNode::class
        )
        assertNotNull(node.left)
        assertType(ShakeIntegerNode::class, node.left)
        assertEquals(10, (node.left as ShakeIntegerNode).number)
        assertNotNull(node.right)
        assertType(ShakeAddNode::class, node.right)
        val add = node.right as ShakeAddNode
        assertNotNull(add.left)
        assertType(ShakeIntegerNode::class, add.left)
        assertEquals(5, (add.left as ShakeIntegerNode).number)
        assertNotNull(add.right)
        assertType(ShakeMulNode::class, add.right)
        val mul = add.right as ShakeMulNode
        assertNotNull(mul.left)
        assertType(ShakeIntegerNode::class, mul.left)
        assertEquals(9, (mul.left as ShakeIntegerNode).number)
        assertNotNull(mul.right)
        assertType(ShakeIntegerNode::class, mul.right)
        assertEquals(2, (mul.right as ShakeIntegerNode).number)
    }

    private fun <T : ShakeLogicalCompareNode> testBasic(input: String, left: Double, right: Double, type: KClass<T>) {
        val node = ParserTestUtil.parseSingle(
            '<'.toString() + type.simpleName?.substring(type.simpleName?.length?.minus(4) ?: 0) + "Test>",
            input,
            type
        )
        assertNotNull(node.left)
        assertTrue(node.left is ShakeDoubleNode || node.left is ShakeIntegerNode)
        if (node.left is ShakeDoubleNode) assertEquals(
            left,
            (node.left as ShakeDoubleNode).number
        ) else assertEquals(left, (node.left as ShakeIntegerNode).number.toDouble())
        assertNotNull(node.right)
        assertTrue(node.right is ShakeDoubleNode || node.right is ShakeIntegerNode)
        if (node.right is ShakeDoubleNode) assertEquals(
            right,
            (node.right as ShakeDoubleNode).number
        ) else assertEquals(right, (node.right as ShakeIntegerNode).number.toDouble())
    }
}