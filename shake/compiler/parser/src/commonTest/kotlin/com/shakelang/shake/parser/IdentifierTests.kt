package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.functions.ShakeInvocationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import com.shakelang.shake.shouldBeOfType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class IdentifierTests : FreeSpec({

    "test basic identifier" {
        val node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "test", ShakeVariableUsageNode::class)
        node.identifier.name shouldBe "test"
        node.identifier.parent shouldBe null
    }

    "test complex identifier" {
        var node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "test.test2", ShakeVariableUsageNode::class)
        node.identifier.name shouldBe "test2"
        node.identifier.parent shouldNotBe null
        node.identifier.parent shouldBeOfType ShakeVariableUsageNode::class
        node = node.identifier.parent as ShakeVariableUsageNode
        node.identifier.name shouldBe "test"
        node.identifier.parent shouldBe null
    }

    "test complex identifier with invocation" {
        var node = ParserTestUtil.parseValue("<BasicIdentifierTest>", "aaa.test().test2", ShakeVariableUsageNode::class)
        node.identifier.name shouldBe "test2"
        node.identifier.parent shouldNotBe null
        node.identifier.parent shouldBeOfType ShakeInvocationNode::class
        val node2 = node.identifier.parent as ShakeInvocationNode
        node.identifier.parent shouldNotBe null
        node2.function shouldBeOfType ShakeVariableUsageNode::class
        (node2.function as ShakeVariableUsageNode).identifier.name shouldBe "test"
        (node2.function as ShakeVariableUsageNode).identifier.parent shouldNotBe null
        (node2.function as ShakeVariableUsageNode).identifier.parent shouldBeOfType ShakeVariableUsageNode::class
        node = (node2.function as ShakeVariableUsageNode).identifier.parent as ShakeVariableUsageNode
        node.identifier.name shouldBe "aaa"
        node.identifier.parent shouldBe null
    }
})
