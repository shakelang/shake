package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.ShakeBlockNode
import com.shakelang.shake.parser.node.ShakeIfNode
import com.shakelang.shake.parser.node.ShakeImportNode
import com.shakelang.shake.parser.node.expression.ShakeLessThanNode
import com.shakelang.shake.parser.node.factor.ShakeLogicalTrueLiteralNode
import com.shakelang.shake.parser.node.functions.ShakeInvocationNode
import com.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import com.shakelang.shake.parser.node.loops.ShakeForNode
import com.shakelang.shake.parser.node.loops.ShakeWhileNode
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableIncreaseNode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ParserTests : FreeSpec(
    {
        "test import" {
            var node = ParserTestUtil.parse("<ImportTest>", "import com;")
            node.children.size shouldBe 1
            node.children[0] shouldBeOfType ShakeImportNode::class
            (node.children[0] as ShakeImportNode).import shouldHaveSameContents arrayOf("com")
            node = ParserTestUtil.parse("<ImportTest>", "import com.shakelang.shake;")
            node.children.size shouldBe 1
            node.children[0] shouldBeOfType ShakeImportNode::class
            (node.children[0] as ShakeImportNode).import shouldHaveSameContents arrayOf("com", "shakelang", "shake")
            node = ParserTestUtil.parse("<ImportTest>", "import com.shakelang.shake.*;")
            node.children.size shouldBe 1
            node.children[0] shouldBeOfType ShakeImportNode::class
            (node.children[0] as ShakeImportNode).import shouldHaveSameContents arrayOf("com", "shakelang", "shake", "*")
        }

        "test multi statement" {
            var node = ParserTestUtil.parseStatement("<MultiStatementTest>", "var i; println(i);")
            node.children.size shouldBe 2
            node.children[0] shouldBeOfType ShakeVariableDeclarationNode::class
            node.children[1] shouldBeOfType ShakeInvocationNode::class
            node = ParserTestUtil.parseStatement("<MultiStatementTest>", "var i\nprintln(i)")
            node.children.size shouldBe 2
            node.children[0] shouldBeOfType ShakeVariableDeclarationNode::class
            node.children[1] shouldBeOfType ShakeInvocationNode::class
        }

        "test while" {
            var node = ParserTestUtil.parseStatement("<WhileTest>", "while (true) { println(); }", ShakeWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node = ParserTestUtil.parseStatement("<WhileTest>", "while (true) println();", ShakeWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            val body = node.body
            body.children.size shouldBe 1
            body.children[0] shouldBeOfType ShakeInvocationNode::class
        }

        "test do while" {
            var node =
                ParserTestUtil.parseStatement("<DoWhileTest>", "do { println(i) } while (true);", ShakeDoWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node = ParserTestUtil.parseStatement("<DpWhileTest>", "do println(i); while (true);", ShakeDoWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
        }

        "test for" {
            var node = ParserTestUtil.parseStatement(
                "<ForTest>",
                "for(var i = 0; i < 100; i++) { println(); }",
                ShakeForNode::class,
            )
            node.declaration shouldNotBe null
            node.condition shouldNotBe null
            node.round shouldNotBe null
            node.body shouldNotBe null
            node.declaration shouldBeOfType ShakeVariableDeclarationNode::class
            node.condition shouldBeOfType ShakeLessThanNode::class
            node.round shouldBeOfType ShakeVariableIncreaseNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node =
                ParserTestUtil.parseStatement("<ForTest>", "for(var i = 0; i < 100; i++) println();", ShakeForNode::class)
            node.declaration shouldNotBe null
            node.condition shouldNotBe null
            node.round shouldNotBe null
            node.body shouldNotBe null
            node.declaration shouldBeOfType ShakeVariableDeclarationNode::class
            node.condition shouldBeOfType ShakeLessThanNode::class
            node.round shouldBeOfType ShakeVariableIncreaseNode::class
            node.body shouldBeOfType ShakeBlockNode::class
        }

        "test if" {
            var node = ParserTestUtil.parseStatement("<IfTest>", "if (true) { println(i) }", ShakeIfNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node = ParserTestUtil.parseStatement("<IfTest>", "if (true) println(i);", ShakeIfNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node = ParserTestUtil.parseStatement("<IfTest>", "if (true) println(i); else println(i);", ShakeIfNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node.elseBody shouldBeOfType ShakeBlockNode::class
            node.elseBody!!.children.size shouldBe 1
            node.elseBody!!.children[0] shouldBeOfType ShakeInvocationNode::class

            node = ParserTestUtil.parseStatement(
                "<IfTest>",
                "if (true) println(i); else if (true) println(i); else println(i);",
                ShakeIfNode::class,
            )
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node.elseBody shouldBeOfType ShakeBlockNode::class
            node.elseBody!!.children.size shouldBe 1
            node.elseBody!!.children[0] shouldBeOfType ShakeIfNode::class
            node = node.elseBody!!.children[0] as ShakeIfNode
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldNotBe null
            node.condition shouldBeOfType ShakeLogicalTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node.elseBody shouldBeOfType ShakeBlockNode::class
            node.elseBody!!.children.size shouldBe 1
            node.elseBody!!.children[0] shouldBeOfType ShakeInvocationNode::class
        }
    },
)
