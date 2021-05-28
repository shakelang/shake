package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.IdentifierNode
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode
import com.github.nsc.de.shake.parser.node.variables.VariableUsageNode
import kotlin.test.*

class IdentifierTests {
    @Test
    fun testBasicIdentifier() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "test")
        assertEquals(1, tree.children.size)
        assertType(VariableUsageNode::class, tree.children[0])
        val node = tree.children[0] as VariableUsageNode
        assertEquals("test", node.variable.name)
        assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifier() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "test.test2")
        assertEquals(1, tree.children.size)
        assertType(VariableUsageNode::class, tree.children[0])
        var node = tree.children[0] as VariableUsageNode
        assertEquals("test2", node.variable.name)
        assertNotNull(node.variable.parent)
        assertType(VariableUsageNode::class, node.variable.parent!!)
        node = node.variable.parent as VariableUsageNode
        assertEquals("test", node.variable.name)
        assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifierWithFunctions() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "aaa.test().test2")
        assertEquals(1, tree.children.size)
        assertType(VariableUsageNode::class, tree.children[0])
        var node = tree.children[0] as VariableUsageNode
        assertEquals("test2", node.variable.name)
        assertNotNull(node.variable.parent)
        assertType(FunctionCallNode::class, node.variable.parent!!)
        val node2 = node.variable.parent as FunctionCallNode
        assertNotNull(node.variable.parent)
        assertType(IdentifierNode::class, node2.function)
        assertEquals("test", (node2.function as IdentifierNode).name)
        assertType(VariableUsageNode::class, (node2.function as IdentifierNode).parent!!)
        node = (node2.function as IdentifierNode).parent as VariableUsageNode
        assertEquals("aaa", node.variable.name)
        assertNull(node.variable.parent)
    }
}