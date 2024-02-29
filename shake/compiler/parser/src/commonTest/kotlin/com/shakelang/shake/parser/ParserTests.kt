package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.mixed.ShakeInvocationNode
import com.shakelang.shake.parser.node.mixed.ShakeVariableIncrementAfterNode
import com.shakelang.shake.parser.node.outer.ShakeImportNode
import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.shake.parser.node.statements.ShakeDoWhileNode
import com.shakelang.shake.parser.node.statements.ShakeForNode
import com.shakelang.shake.parser.node.statements.ShakeIfNode
import com.shakelang.shake.parser.node.statements.ShakeLocalDeclarationNode
import com.shakelang.shake.parser.node.statements.ShakeWhileNode
import com.shakelang.shake.parser.node.values.expression.ShakeLessThanNode
import com.shakelang.shake.parser.node.values.factor.ShakeTrueLiteralNode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ParserTests : FreeSpec(
    {
        "test import" {
            var node = ParserTestUtil.parse("<ImportTest>", "import com")
            node.children.size shouldBe 1
            node.children[0] shouldBeOfType ShakeImportNode::class
            (node.children[0] as ShakeImportNode).importStrings shouldHaveSameContents arrayOf("com")
            node = ParserTestUtil.parse("<ImportTest>", "import com.shakelang.shake;")
            node.children.size shouldBe 1
            node.children[0] shouldBeOfType ShakeImportNode::class
            (node.children[0] as ShakeImportNode).importStrings shouldHaveSameContents arrayOf("com", "shakelang", "shake")
            node = ParserTestUtil.parse("<ImportTest>", "import com.shakelang.shake.*;")
            node.children.size shouldBe 1
            node.children[0] shouldBeOfType ShakeImportNode::class
            (node.children[0] as ShakeImportNode).importStrings shouldHaveSameContents arrayOf("com", "shakelang", "shake", "*")
        }

        "test multi statement" {
            var node = ParserTestUtil.parseStatement("<MultiStatementTest>", "var i; println(i);")
            node.children.size shouldBe 2
            node.children[0] shouldBeOfType ShakeLocalDeclarationNode::class
            node.children[1] shouldBeOfType ShakeInvocationNode::class
            node = ParserTestUtil.parseStatement("<MultiStatementTest>", "var i\nprintln(i)")
            node.children.size shouldBe 2
            node.children[0] shouldBeOfType ShakeLocalDeclarationNode::class
            node.children[1] shouldBeOfType ShakeInvocationNode::class
        }

        "test while" {
            var node = ParserTestUtil.parseStatement("<WhileTest>", "while (true) { println(); }", ShakeWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node = ParserTestUtil.parseStatement("<WhileTest>", "while (true) println();", ShakeWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
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
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node = ParserTestUtil.parseStatement("<DpWhileTest>", "do println(i); while (true);", ShakeDoWhileNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
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
            node.declaration shouldBeOfType ShakeLocalDeclarationNode::class
            node.condition shouldBeOfType ShakeLessThanNode::class
            node.round shouldBeOfType ShakeVariableIncrementAfterNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node =
                ParserTestUtil.parseStatement("<ForTest>", "for(var i = 0; i < 100; i++) println();", ShakeForNode::class)
            node.declaration shouldNotBe null
            node.condition shouldNotBe null
            node.round shouldNotBe null
            node.body shouldNotBe null
            node.declaration shouldBeOfType ShakeLocalDeclarationNode::class
            node.condition shouldBeOfType ShakeLessThanNode::class
            node.round shouldBeOfType ShakeVariableIncrementAfterNode::class
            node.body shouldBeOfType ShakeBlockNode::class
        }

        "test if" {
            var node = ParserTestUtil.parseStatement("<IfTest>", "if (true) { println(i) }", ShakeIfNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldBe null
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node = ParserTestUtil.parseStatement("<IfTest>", "if (true) println(i);", ShakeIfNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldBe null
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node = ParserTestUtil.parseStatement("<IfTest>", "if (true) println(i); else println(i);", ShakeIfNode::class)
            node.condition shouldNotBe null
            node.body shouldNotBe null
            node.elseBody shouldNotBe null
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
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
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
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
            node.condition shouldBeOfType ShakeTrueLiteralNode::class
            node.body shouldBeOfType ShakeBlockNode::class
            node.body.children.size shouldBe 1
            node.body.children[0] shouldBeOfType ShakeInvocationNode::class
            node.elseBody shouldBeOfType ShakeBlockNode::class
            node.elseBody!!.children.size shouldBe 1
            node.elseBody!!.children[0] shouldBeOfType ShakeInvocationNode::class
        }
    },
)
