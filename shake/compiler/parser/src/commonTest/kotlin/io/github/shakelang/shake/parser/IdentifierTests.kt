package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.ShakeIdentifierNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class IdentifierTests {
    @Test
    fun testBasicIdentifier() {
        val node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "test", ShakeVariableUsageNode::class)
        assertEquals("test", node.variable.name)
        assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifier() {
        var node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "test.test2", ShakeVariableUsageNode::class)
        assertEquals("test2", node.variable.name)
        assertNotNull(node.variable.parent)
        assertType(ShakeVariableUsageNode::class, node.variable.parent!!)
        node = node.variable.parent as ShakeVariableUsageNode
        assertEquals("test", node.variable.name)
        assertNull(node.variable.parent)
    }

    @Test
    fun testComplexIdentifierWithFunctions() {
        var node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "aaa.test().test2", ShakeVariableUsageNode::class)
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
