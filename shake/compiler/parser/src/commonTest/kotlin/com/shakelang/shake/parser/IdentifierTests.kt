package com.shakelang.shake.parser

import com.shakelang.shake.assertType
import com.shakelang.shake.parser.node.functions.ShakeInvocationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import io.kotest.core.spec.style.FreeSpec
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class IdentifierTests : FreeSpec({

    "test basic identifier" {
        val node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "test", ShakeVariableUsageNode::class)
        assertEquals("test", node.identifier.name)
        assertNull(node.identifier.parent)
    }

    "test complex identifier" {
        var node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "test.test2", ShakeVariableUsageNode::class)
        assertEquals("test2", node.identifier.name)
        assertNotNull(node.identifier.parent)
        assertType(ShakeVariableUsageNode::class, node.identifier.parent!!)
        node = node.identifier.parent as ShakeVariableUsageNode
        assertEquals("test", node.identifier.name)
        assertNull(node.identifier.parent)
    }

    "test complex identifier with invocation" {
        var node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "aaa.test().test2", ShakeVariableUsageNode::class)
        assertEquals("test2", node.identifier.name)
        assertNotNull(node.identifier.parent)
        assertType(ShakeInvocationNode::class, node.identifier.parent!!)
        val node2 = node.identifier.parent as ShakeInvocationNode
        assertNotNull(node.identifier.parent)
        assertType(ShakeVariableUsageNode::class, node2.function)
        assertEquals("test", (node2.function as ShakeVariableUsageNode).identifier.name)
        assertType(ShakeVariableUsageNode::class, (node2.function as ShakeVariableUsageNode).identifier.parent!!)
        node = (node2.function as ShakeVariableUsageNode).identifier.parent as ShakeVariableUsageNode
        assertEquals("aaa", node.identifier.name)
        assertNull(node.identifier.parent)
    }
})
