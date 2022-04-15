package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertArrayEquals
import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.ShakeIfNode
import io.github.shakelang.shake.parser.node.ShakeImportNode
import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalSmallerNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalTrueNode
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableIncreaseNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
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
        var node = ParserTestUtil.parse("<MultiStatementTest>", "var i; i")
        assertEquals(2, node.children.size)
        assertType(ShakeVariableDeclarationNode::class, node.children[0])
        assertType(ShakeVariableUsageNode::class, node.children[1])
        node = ParserTestUtil.parse("<MultiStatementTest>", "var i\ni")
        assertEquals(2, node.children.size)
        assertType(ShakeVariableDeclarationNode::class, node.children[0])
        assertType(ShakeVariableUsageNode::class, node.children[1])
    }

    @Test
    fun testWhile() {
        var node = ParserTestUtil.parseSingle("<WhileTest>", "while (true) { i }", ShakeWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
        node = ParserTestUtil.parseSingle("<WhileTest>", "while (true) i;", ShakeWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
    }

    @Test
    fun testDoWhile() {
        var node = ParserTestUtil.parseSingle("<DoWhileTest>", "do { i } while (true);", ShakeDoWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
        node = ParserTestUtil.parseSingle("<DpWhileTest>", "do i; while (true);", ShakeDoWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
    }

    @Test
    fun testFor() {
        var node = ParserTestUtil.parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) { i; }", ShakeForNode::class)
        assertNotNull(node.declaration)
        assertNotNull(node.condition)
        assertNotNull(node.round)
        assertNotNull(node.body)
        assertType(ShakeVariableDeclarationNode::class, node.declaration)
        assertType(ShakeLogicalSmallerNode::class, node.condition)
        assertType(ShakeVariableIncreaseNode::class, node.round)
        assertType(ShakeTree::class, node.body)
        node = ParserTestUtil.parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) i;", ShakeForNode::class)
        assertNotNull(node.declaration)
        assertNotNull(node.condition)
        assertNotNull(node.round)
        assertNotNull(node.body)
        assertType(ShakeVariableDeclarationNode::class, node.declaration)
        assertType(ShakeLogicalSmallerNode::class, node.condition)
        assertType(ShakeVariableIncreaseNode::class, node.round)
        assertType(ShakeTree::class, node.body)
    }

    @Test
    fun testIf() {
        var node = ParserTestUtil.parseSingle("<IfTest>", "if (true) { i }", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i;", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNull(node.elseBody)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i; else i;", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNotNull(node.elseBody)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
        assertType(ShakeTree::class, node.elseBody!!)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i; else if (true) i; else i;", ShakeIfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNotNull(node.elseBody)
        assertType(ShakeLogicalTrueNode::class, node.condition)
        assertType(ShakeTree::class, node.body)
        assertType(ShakeTree::class, node.elseBody!!)
        val elseBody = node.elseBody!!
        assertSame(1, elseBody.children.size)
        assertType(ShakeIfNode::class, elseBody.children[0])
        val if2 = elseBody.children[0] as ShakeIfNode
        assertType(ShakeTree::class, if2.body)
        assertType(ShakeTree::class, if2.elseBody!!)
    }
}