package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertArrayEquals
import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.ShakeBlockNode
import io.github.shakelang.shake.parser.node.ShakeIfNode
import io.github.shakelang.shake.parser.node.ShakeImportNode
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableIncreaseNode
import kotlin.test.*

class ParserTests {
    @Test
    fun testImport() {
        var node = ParserTestUtil.parse("<ImportTest>", "import com;")
        assertEquals(1, node.children.size)
        assertType(ShakeImportNode::class, node.children[0])
        assertArrayEquals(arrayOf("com"), (node.children[0] as ShakeImportNode).import)
        node = ParserTestUtil.parse("<ImportTest>", "import io.github.shakelang.shake;")
        assertEquals(1, node.children.size)
        assertType(ShakeImportNode::class, node.children[0])
        assertArrayEquals(
            arrayOf("io", "github", "shakelang", "shake"),
            (node.children[0] as ShakeImportNode).import
        )
        node = ParserTestUtil.parse("<ImportTest>", "import io.github.shakelang.shake.*;")
        assertEquals(1, node.children.size)
        assertType(ShakeImportNode::class, node.children[0])
        assertArrayEquals(
            arrayOf("io", "github", "shakelang", "shake", "*"),
            (node.children[0] as ShakeImportNode).import
        )
    }

    @Test
    fun testMultiStatement() {
        var node = ParserTestUtil.parseStatement("<MultiStatementTest>", "var i; println(i);")
        assertEquals(2, node.children.size)
        assertType(ShakeVariableDeclarationNode::class, node.children[0])
        assertType(ShakeFunctionCallNode::class, node.children[1])
        node = ParserTestUtil.parseStatement("<MultiStatementTest>", "var i\nprintln(i)")
        assertEquals(2, node.children.size)
        assertType(ShakeVariableDeclarationNode::class, node.children[0])
        assertType(ShakeFunctionCallNode::class, node.children[1])
    }

    @Test
    fun testWhile() {
        var node = ParserTestUtil.parseStatement("<WhileTest>", "while (true) { println(); }", ShakeWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
        node = ParserTestUtil.parseStatement("<WhileTest>", "while (true) println();", ShakeWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
    }

    @Test
    fun testDoWhile() {
        var node =
            ParserTestUtil.parseStatement("<DoWhileTest>", "do { println(i) } while (true);", ShakeDoWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
        node = ParserTestUtil.parseStatement("<DpWhileTest>", "do println(i); while (true);", ShakeDoWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
    }

    @Test
    fun testFor() {
        var node = ParserTestUtil.parseStatement(
            "<ForTest>",
            "for(var i = 0; i < 100; i++) { println(); }",
            ShakeForNode::class
        )
        assertNotNull(node.declaration)
        assertNotNull(node.condition)
        assertNotNull(node.round)
        assertNotNull(node.body)
        assertType(ShakeVariableDeclarationNode::class, node.declaration)
        assertType(ShakeLogicalSmallerNode::class, node.condition)
        assertType(ShakeVariableIncreaseNode::class, node.round)
        assertType(ShakeBlockNode::class, node.body)
        node =
            ParserTestUtil.parseStatement("<ForTest>", "for(var i = 0; i < 100; i++) println();", ShakeForNode::class)
        assertNotNull(node.declaration)
        assertNotNull(node.condition)
        assertNotNull(node.round)
        assertNotNull(node.body)
        assertType(ShakeVariableDeclarationNode::class, node.declaration)
        assertType(ShakeLogicalSmallerNode::class, node.condition)
        assertType(ShakeVariableIncreaseNode::class, node.round)
        assertType(ShakeBlockNode::class, node.body)
    }

    @Test
    fun testIf() {
        var node = ParserTestUtil.parseStatement("<IfTest>", "if (true) { println(i) }", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
        node = ParserTestUtil.parseStatement("<IfTest>", "if (true) println(i);", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNull(node.elseBody)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
        node = ParserTestUtil.parseStatement("<IfTest>", "if (true) println(i); else println(i);", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNotNull(node.elseBody)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
        assertType(ShakeBlockNode::class, node.elseBody!!)
        node = ParserTestUtil.parseStatement(
            "<IfTest>",
            "if (true) println(i); else if (true) println(i); else println(i);",
            ShakeIfNode::class
        )
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNotNull(node.elseBody)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeBlockNode::class, node.body)
        assertType(ShakeBlockNode::class, node.elseBody!!)
        val elseBody = node.elseBody!!
        assertSame(1, elseBody.children.size)
        assertType(ShakeIfNode::class, elseBody.children[0])
        val if2 = elseBody.children[0] as ShakeIfNode
        assertType(ShakeBlockNode::class, if2.body)
        assertType(ShakeBlockNode::class, if2.elseBody!!)
    }
}