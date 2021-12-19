package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertArrayEquals
import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.IfNode
import io.github.shakelang.shake.parser.node.ImportNode
import io.github.shakelang.shake.parser.node.Tree
import io.github.shakelang.shake.parser.node.logical.LogicalSmallerNode
import io.github.shakelang.shake.parser.node.logical.LogicalTrueNode
import io.github.shakelang.shake.parser.node.loops.DoWhileNode
import io.github.shakelang.shake.parser.node.loops.ForNode
import io.github.shakelang.shake.parser.node.loops.WhileNode
import io.github.shakelang.shake.parser.node.variables.VariableDeclarationNode
import io.github.shakelang.shake.parser.node.variables.VariableIncreaseNode
import io.github.shakelang.shake.parser.node.variables.VariableUsageNode
import kotlin.test.*

class ParserTests {
    @Test
    fun testImport() {
        var node = ParserTestUtil.parse("<ImportTest>", "import com;")
        assertEquals(1, node.children.size)
        assertType(ImportNode::class, node.children[0])
        assertArrayEquals(arrayOf("com"), (node.children[0] as ImportNode).import)
        node = ParserTestUtil.parse("<ImportTest>", "import io.github.shakelang.shake;")
        assertEquals(1, node.children.size)
        assertType(ImportNode::class, node.children[0])
        assertArrayEquals(
            arrayOf("io", "github", "shakelang", "shake"),
            (node.children[0] as ImportNode).import
        )
        node = ParserTestUtil.parse("<ImportTest>", "import io.github.shakelang.shake.*;")
        assertEquals(1, node.children.size)
        assertType(ImportNode::class, node.children[0])
        assertArrayEquals(
            arrayOf("io", "github", "shakelang", "shake", "*"),
            (node.children[0] as ImportNode).import
        )
    }

    @Test
    fun testMultiStatement() {
        var node = ParserTestUtil.parse("<MultiStatementTest>", "var i; i")
        assertEquals(2, node.children.size)
        assertType(VariableDeclarationNode::class, node.children[0])
        assertType(VariableUsageNode::class, node.children[1])
        node = ParserTestUtil.parse("<MultiStatementTest>", "var i\ni")
        assertEquals(2, node.children.size)
        assertType(VariableDeclarationNode::class, node.children[0])
        assertType(VariableUsageNode::class, node.children[1])
    }

    @Test
    fun testWhile() {
        var node = ParserTestUtil.parseSingle("<WhileTest>", "while (true) { i }", WhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
        node = ParserTestUtil.parseSingle("<WhileTest>", "while (true) i;", WhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
    }

    @Test
    fun testDoWhile() {
        var node = ParserTestUtil.parseSingle("<DoWhileTest>", "do { i } while (true);", DoWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
        node = ParserTestUtil.parseSingle("<DpWhileTest>", "do i; while (true);", DoWhileNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
    }

    @Test
    fun testFor() {
        var node = ParserTestUtil.parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) { i; }", ForNode::class)
        assertNotNull(node.declaration)
        assertNotNull(node.condition)
        assertNotNull(node.round)
        assertNotNull(node.body)
        assertType(VariableDeclarationNode::class, node.declaration)
        assertType(LogicalSmallerNode::class, node.condition)
        assertType(VariableIncreaseNode::class, node.round)
        assertType(Tree::class, node.body)
        node = ParserTestUtil.parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) i;", ForNode::class)
        assertNotNull(node.declaration)
        assertNotNull(node.condition)
        assertNotNull(node.round)
        assertNotNull(node.body)
        assertType(VariableDeclarationNode::class, node.declaration)
        assertType(LogicalSmallerNode::class, node.condition)
        assertType(VariableIncreaseNode::class, node.round)
        assertType(Tree::class, node.body)
    }

    @Test
    fun testIf() {
        var node = ParserTestUtil.parseSingle("<IfTest>", "if (true) { i }", IfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i;", IfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNull(node.elseBody)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i; else i;", IfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNotNull(node.elseBody)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
        assertType(Tree::class, node.elseBody!!)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i; else if (true) i; else i;", IfNode::class)
        assertNotNull(node.condition)
        assertNotNull(node.body)
        assertNotNull(node.elseBody)
        assertType(LogicalTrueNode::class, node.condition)
        assertType(Tree::class, node.body)
        assertType(Tree::class, node.elseBody!!)
        val elseBody = node.elseBody!!
        assertSame(1, elseBody.children.size)
        assertType(IfNode::class, elseBody.children[0])
        val if2 = elseBody.children[0] as IfNode
        assertType(Tree::class, if2.body)
        assertType(Tree::class, if2.elseBody!!)
    }
}