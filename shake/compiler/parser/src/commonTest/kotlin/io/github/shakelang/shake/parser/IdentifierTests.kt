package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.ShakeIdentifierNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import kotlin.test.*

class IdentifierTests {
    @Test
    fun testBasicIdentifier() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "test")
        assertEquals(1, tree.children.size)
        assertType(ShakeVariableUsageNode::class, tree.children[0])
        val node = tree.children[0] as ShakeVariableUsageNode
        assertEquals("test", node.variable.name)
        assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifier() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "test.test2")
        assertEquals(1, tree.children.size)
        assertType(ShakeVariableUsageNode::class, tree.children[0])
        var node = tree.children[0] as ShakeVariableUsageNode
        assertEquals("test2", node.variable.name)
        assertNotNull(node.variable.parent)
        assertType(ShakeVariableUsageNode::class, node.variable.parent!!)
        node = node.variable.parent as ShakeVariableUsageNode
        assertEquals("test", node.variable.name)
        assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifierWithFunctions() {
        val tree = ParserTestUtil.parse("<BasicIdentifierTest>", "aaa.test().test2")
        assertEquals(1, tree.children.size)
        assertType(ShakeVariableUsageNode::class, tree.children[0])
        var node = tree.children[0] as ShakeVariableUsageNode
        assertEquals("test2", node.variable.name)
        assertNotNull(node.variable.parent)
        assertType(ShakeFunctionCallNode::class, node.variable.parent!!)
        val node2 = node.variable.parent as ShakeFunctionCallNode
        assertNotNull(node.variable.parent)
        assertType(ShakeIdentifierNode::class, node2.function)
        assertEquals("test", (node2.function as ShakeIdentifierNode).name)
        assertType(ShakeVariableUsageNode::class, (node2.function as ShakeIdentifierNode).parent!!)
        node = (node2.function as ShakeIdentifierNode).parent as ShakeVariableUsageNode
        assertEquals("aaa", node.variable.name)
        assertNull(node.variable.parent)
    }
}