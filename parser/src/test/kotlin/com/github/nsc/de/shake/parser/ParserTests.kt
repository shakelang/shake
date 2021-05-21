package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.IfNode
import com.github.nsc.de.shake.parser.node.ImportNode
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerNode
import com.github.nsc.de.shake.parser.node.logical.LogicalTrueNode
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode
import com.github.nsc.de.shake.parser.node.loops.ForNode
import com.github.nsc.de.shake.parser.node.loops.WhileNode
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode
import com.github.nsc.de.shake.parser.node.variables.VariableIncreaseNode
import com.github.nsc.de.shake.parser.node.variables.VariableUsageNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParserTests {
    @Test
    fun testImport() {
        var node = ParserTestUtil.parse("<ImportTest>", "import com;")
        Assertions.assertEquals(1, node.children.size)
        assertType(ImportNode::class.java, node.children[0])
        Assertions.assertArrayEquals(arrayOf("com"), (node.children[0] as ImportNode).import)
        node = ParserTestUtil.parse("<ImportTest>", "import com.github.nsc.de.shake;")
        Assertions.assertEquals(1, node.children.size)
        assertType(ImportNode::class.java, node.children[0])
        Assertions.assertArrayEquals(
            arrayOf("com", "github", "nsc", "de", "shake"),
            (node.children[0] as ImportNode).import
        )
        node = ParserTestUtil.parse("<ImportTest>", "import com.github.nsc.de.shake.*;")
        Assertions.assertEquals(1, node.children.size)
        assertType(ImportNode::class.java, node.children[0])
        Assertions.assertArrayEquals(
            arrayOf("com", "github", "nsc", "de", "shake", "*"),
            (node.children[0] as ImportNode).import
        )
    }

    @Test
    fun testMultiStatement() {
        var node = ParserTestUtil.parse("<MultiStatementTest>", "var i; i")
        Assertions.assertEquals(2, node.children.size)
        assertType(VariableDeclarationNode::class.java, node.children[0])
        assertType(VariableUsageNode::class.java, node.children[1])
        node = ParserTestUtil.parse("<MultiStatementTest>", "var i\ni")
        Assertions.assertEquals(2, node.children.size)
        assertType(VariableDeclarationNode::class.java, node.children[0])
        assertType(VariableUsageNode::class.java, node.children[1])
    }

    @Test
    fun testWhile() {
        var node = ParserTestUtil.parseSingle("<WhileTest>", "while (true) { i }", WhileNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
        node = ParserTestUtil.parseSingle("<WhileTest>", "while (true) i;", WhileNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
    }

    @Test
    fun testDoWhile() {
        var node = ParserTestUtil.parseSingle("<DoWhileTest>", "do { i } while (true);", DoWhileNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
        node = ParserTestUtil.parseSingle("<WhileTest>", "do i; while (true);", DoWhileNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
    }

    @Test
    fun testFor() {
        var node = ParserTestUtil.parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) { i; }", ForNode::class.java)
        Assertions.assertNotNull(node.declaration)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.round)
        Assertions.assertNotNull(node.body)
        assertType(VariableDeclarationNode::class.java, node.declaration)
        assertType(LogicalSmallerNode::class.java, node.condition)
        assertType(VariableIncreaseNode::class.java, node.round)
        assertType(Tree::class.java, node.body)
        node = ParserTestUtil.parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) i;", ForNode::class.java)
        Assertions.assertNotNull(node.declaration)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.round)
        Assertions.assertNotNull(node.body)
        assertType(VariableDeclarationNode::class.java, node.declaration)
        assertType(LogicalSmallerNode::class.java, node.condition)
        assertType(VariableIncreaseNode::class.java, node.round)
        assertType(Tree::class.java, node.body)
    }

    @Test
    fun testIf() {
        var node = ParserTestUtil.parseSingle("<IfTest>", "if (true) { i }", IfNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i;", IfNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        Assertions.assertNull(node.elseBody)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i; else i;", IfNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        Assertions.assertNotNull(node.elseBody)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
        assertType(Tree::class.java, node.elseBody)
        node = ParserTestUtil.parseSingle("<IfTest>", "if (true) i; else if (true) i; else i;", IfNode::class.java)
        Assertions.assertNotNull(node.condition)
        Assertions.assertNotNull(node.body)
        Assertions.assertNotNull(node.elseBody)
        assertType(LogicalTrueNode::class.java, node.condition)
        assertType(Tree::class.java, node.body)
        assertType(Tree::class.java, node.elseBody)
        val elseBody = node.elseBody
        Assertions.assertSame(1, elseBody.children.size)
        assertType(IfNode::class.java, elseBody.children[0])
        val if2 = elseBody.children[0] as IfNode
        assertType(Tree::class.java, if2.body)
        assertType(Tree::class.java, if2.elseBody)
    }
}