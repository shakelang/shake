package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import org.junit.jupiter.api.Test
import com.github.nsc.de.shake.parser.node.variables.VariableUsageNode
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode
import com.github.nsc.de.shake.parser.node.IdentifierNode
import org.junit.jupiter.api.Assertions

class IdentifierTests {
    @Test
    fun testBasicIdentifier() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "test")
        Assertions.assertEquals(1, tree.children.size)
        assertType(VariableUsageNode::class.java, tree.children[0])
        val node = tree.children[0] as VariableUsageNode
        Assertions.assertEquals("test", node.variable.name)
        Assertions.assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifier() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "test.test2")
        Assertions.assertEquals(1, tree.children.size)
        assertType(VariableUsageNode::class.java, tree.children[0])
        var node = tree.children[0] as VariableUsageNode
        Assertions.assertEquals("test2", node.variable.name)
        Assertions.assertNotNull(node.variable.parent)
        assertType(VariableUsageNode::class.java, node.variable.parent)
        node = node.variable.parent as VariableUsageNode
        Assertions.assertEquals("test", node.variable.name)
        Assertions.assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifierWithFunctions() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "aaa.test().test2")
        Assertions.assertEquals(1, tree.children.size)
        assertType(VariableUsageNode::class.java, tree.children[0])
        var node = tree.children[0] as VariableUsageNode
        Assertions.assertEquals("test2", node.variable.name)
        Assertions.assertNotNull(node.variable.parent)
        assertType(FunctionCallNode::class.java, node.variable.parent)
        val node2 = node.variable.parent as FunctionCallNode
        Assertions.assertNotNull(node.variable.parent)
        assertType(IdentifierNode::class.java, node2.function)
        Assertions.assertEquals("test", (node2.function as IdentifierNode).name)
        assertType(VariableUsageNode::class.java, (node2.function as IdentifierNode).parent)
        node = (node2.function as IdentifierNode).parent as VariableUsageNode
        Assertions.assertEquals("aaa", node.variable.name)
        Assertions.assertNull(node.variable.parent)
    }
}