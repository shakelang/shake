package com.shakelang.shake.parser

import com.shakelang.shake.assertType
import com.shakelang.shake.parser.node.expression.*
import com.shakelang.shake.parser.node.factor.ShakeDoubleNode
import com.shakelang.shake.parser.node.factor.ShakeIntegerNode
import com.shakelang.shake.parser.node.factor.ShakePriorityNode
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MathTests {
    @Test
    fun testBasicNumber() {
        val node = ParserTestUtil.parseValue("<VariableUsageTest>", "10", ShakeIntegerNode::class)
        assertEquals(10, node.number)
    }

    @Test
    fun testBasicExpr() {
        testBasic("10 + 3", 10.0, 3.0, ShakeAddNode::class)
        testBasic("10 - 3", 10.0, 3.0, ShakeSubNode::class)
    }

    @Test
    fun testBasicTerm() {
        testBasic("10 * 3", 10.0, 3.0, ShakeMulNode::class)
        testBasic("10 / 3", 10.0, 3.0, ShakeDivNode::class)
        testBasic("10 % 3", 10.0, 3.0, ShakeModNode::class)
    }

    @Test
    fun testBasicPow() {
        testBasic("10 ** 3", 10.0, 3.0, ShakePowNode::class)
    }

    @Test
    fun testPointBeforeLine() {
        val add = ParserTestUtil.parseValue("<PointBeforeLineTest>", "1 + 2 * 3 ** 4", ShakeAddNode::class)
        assertNotNull(add.left)
        assertType(ShakeIntegerNode::class, add.left)
        assertEquals(1, (add.left as ShakeIntegerNode).number)
        assertNotNull(add.right)
        assertType(ShakeMulNode::class, add.right)
        val mul = add.right as ShakeMulNode
        assertNotNull(mul.left)
        assertType(ShakeIntegerNode::class, mul.left)
        assertEquals(2, (mul.left as ShakeIntegerNode).number)
        assertNotNull(mul.right)
        assertType(ShakePowNode::class, mul.right)
        val pow = mul.right as ShakePowNode
        assertNotNull(pow.left)
        assertType(ShakeIntegerNode::class, pow.left)
        assertEquals(3, (pow.left as ShakeIntegerNode).number)
        assertNotNull(pow.right)
        assertType(ShakeIntegerNode::class, pow.right)
        assertEquals(4, (pow.right as ShakeIntegerNode).number)
    }

    @Test
    fun testBrackets() {
        val node = ParserTestUtil.parseValue("<BracketTest>", "2 * (4 + 3)", ShakeMulNode::class)
        assertNotNull(node.left)
        assertType(ShakeIntegerNode::class, node.left)
        assertEquals(2, (node.left as ShakeIntegerNode).number)
        assertNotNull(node.right)
        assertType(ShakePriorityNode::class, node.right)
        val priority = node.right as ShakePriorityNode
        assertNotNull(priority.value)
        assertType(ShakeAddNode::class, priority.value)
        val add = priority.value as ShakeAddNode
        assertNotNull(add.left)
        assertType(ShakeIntegerNode::class, add.left)
        assertEquals(4, (add.left as ShakeIntegerNode).number)
        assertNotNull(add.right)
        assertType(ShakeIntegerNode::class, add.right)
        assertEquals(3, (add.right as ShakeIntegerNode).number)
    }

    private fun <T : ShakeExpressionNode> testBasic(input: String, left: Double, right: Double, type: KClass<T>) {
        val node = ParserTestUtil.parseValue(
            '<'.toString() + type.simpleName!!.substring(type.simpleName!!.length - 4) + "Test>",
            input,
            type
        )
        assertNotNull(node.left)
        assertTrue(node.left is ShakeDoubleNode || node.left is ShakeIntegerNode)
        if (node.left is ShakeDoubleNode) {
            assertEquals(
                left,
                (node.left as ShakeDoubleNode).number
            )
        } else {
            assertEquals(left, (node.left as ShakeIntegerNode).number.toDouble())
        }
        assertNotNull(node.right)
        assertTrue(node.right is ShakeDoubleNode || node.right is ShakeIntegerNode)
        if (node.right is ShakeDoubleNode) {
            assertEquals(
                right,
                (node.right as ShakeDoubleNode).number
            )
        } else {
            assertEquals(right, (node.right as ShakeIntegerNode).number.toDouble())
        }
    }
}
