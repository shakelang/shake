package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.expression.AddNode
import com.github.nsc.de.shake.parser.node.expression.MulNode
import com.github.nsc.de.shake.parser.node.factor.DoubleNode
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.logical.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LogicalTests {
    @Test
    fun testTrue() {
        Assertions.assertNotNull(ParserTestUtil.parseSingle("<LogicalTrueTest>", "true", LogicalTrueNode::class.java))
    }

    @Test
    fun testFalse() {
        Assertions.assertNotNull(
            ParserTestUtil.parseSingle(
                "<LogicalFalseTest>",
                "false",
                LogicalFalseNode::class.java
            )
        )
    }

    @Test
    fun testEqEquals() {
        testBasic("10==10", 10.0, 10.0, LogicalEqEqualsNode::class.java)
    }

    @Test
    fun testBiggerEquals() {
        testBasic("10>=10", 10.0, 10.0, LogicalBiggerEqualsNode::class.java)
    }

    @Test
    fun testSmallerEquals() {
        testBasic("10<=10", 10.0, 10.0, LogicalSmallerEqualsNode::class.java)
    }

    @Test
    fun testBigger() {
        testBasic("10>10", 10.0, 10.0, LogicalBiggerNode::class.java)
    }

    @Test
    fun testSmaller() {
        testBasic("10<10", 10.0, 10.0, LogicalSmallerNode::class.java)
    }

    @Test
    fun testAnd() {
        val node = ParserTestUtil.parseSingle("<LogicalAndTest>", "true && false", LogicalAndNode::class.java)
        Assertions.assertNotNull(node.left)
        assertType(LogicalTrueNode::class.java, node.left)
        Assertions.assertNotNull(node.right)
        assertType(LogicalFalseNode::class.java, node.right)
    }

    @Test
    fun testOr() {
        val node = ParserTestUtil.parseSingle("<LogicalOrTest>", "true || false", LogicalOrNode::class.java)
        Assertions.assertNotNull(node.left)
        assertType(LogicalTrueNode::class.java, node.left)
        Assertions.assertNotNull(node.right)
        assertType(LogicalFalseNode::class.java, node.right)
    }

    @Test
    fun testXOr() {
        val node = ParserTestUtil.parseSingle("<LogicalXOrTest>", "true ^ false", LogicalXOrNode::class.java)
        Assertions.assertNotNull(node.left)
        assertType(LogicalTrueNode::class.java, node.left)
        Assertions.assertNotNull(node.right)
        assertType(LogicalFalseNode::class.java, node.right)
    }

    @Test
    fun testBrackets() {
        val node =
            ParserTestUtil.parseSingle("<LogicalBracketTest>", "true && (false || true)", LogicalAndNode::class.java)
        Assertions.assertNotNull(node.left)
        assertType(LogicalTrueNode::class.java, node.left)
        Assertions.assertNotNull(node.right)
        assertType(LogicalOrNode::class.java, node.right)
        val or = node.right as LogicalOrNode
        Assertions.assertNotNull(or.left)
        assertType(LogicalFalseNode::class.java, or.left)
        Assertions.assertNotNull(or.right)
        assertType(LogicalTrueNode::class.java, or.right)
    }

    @Test
    fun testExpr() {
        val node = ParserTestUtil.parseSingle(
            "<LogicalExpressionTest>",
            "10 >= 5 + 9 * 2",
            LogicalBiggerEqualsNode::class.java
        )
        Assertions.assertNotNull(node.left)
        assertType(IntegerNode::class.java, node.left)
        Assertions.assertEquals(10, (node.left as IntegerNode).number)
        Assertions.assertNotNull(node.right)
        assertType(AddNode::class.java, node.right)
        val add = node.right as AddNode
        Assertions.assertNotNull(add.left)
        assertType(IntegerNode::class.java, add.left)
        Assertions.assertEquals(5, (add.left as IntegerNode).number)
        Assertions.assertNotNull(add.right)
        assertType(MulNode::class.java, add.right)
        val mul = add.right as MulNode
        Assertions.assertNotNull(mul.left)
        assertType(IntegerNode::class.java, mul.left)
        Assertions.assertEquals(9, (mul.left as IntegerNode).number)
        Assertions.assertNotNull(mul.right)
        assertType(IntegerNode::class.java, mul.right)
        Assertions.assertEquals(2, (mul.right as IntegerNode).number)
    }

    private fun <T : LogicalCompareNode?> testBasic(input: String, left: Double, right: Double, type: Class<T>) {
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