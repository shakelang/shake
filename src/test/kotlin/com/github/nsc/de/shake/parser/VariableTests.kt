package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.IdentifierNode
import com.github.nsc.de.shake.parser.node.VariableType
import org.junit.jupiter.api.Test
import com.github.nsc.de.shake.parser.node.factor.DoubleNode
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.logical.LogicalTrueNode
import com.github.nsc.de.shake.parser.node.variables.*
import org.junit.jupiter.api.Assertions

class VariableTests {
    //// *************************************************************
    //// Assignments
    @Test
    fun testVariableAssignment() {
        var node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = 0", VariableAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
        node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = 0.1", VariableAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(DoubleNode::class.java, node.value)
        node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = true", VariableAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(LogicalTrueNode::class.java, node.value)
    }

    @Test
    fun testVariableAddAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableAddAssignmentTest>", "i += 0", VariableAddAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
    }

    @Test
    fun testVariableSubAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableSubAssignmentTest>", "i -= 0", VariableSubAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
    }

    @Test
    fun testVariableMulAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableMulAssignmentTest>", "i *= 0", VariableMulAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
    }

    @Test
    fun testVariableDivAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableDivAssignmentTest>", "i /= 1", VariableDivAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
    }

    @Test
    fun testVariableModAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableModAssignmentTest>", "i %= 1", VariableModAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
    }

    @Test
    fun testVariablePowAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariablePowAssignmentTest>", "i **= 0", VariablePowAssignmentNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
        Assertions.assertNotNull(node.value)
        assertType(IntegerNode::class.java, node.value)
    }

    @Test
    fun testVariableIncrease() {
        val node = ParserTestUtil.parseSingle("<VariableIncreaseTest>", "i ++", VariableIncreaseNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
    }

    @Test
    fun testVariableDecrease() {
        val node = ParserTestUtil.parseSingle("<VariableDecreaseTest>", "i --", VariableDecreaseNode::class.java)
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", (node.variable as IdentifierNode).name)
        Assertions.assertNull((node.variable as IdentifierNode).parent)
    }

    //// *************************************************************
    //// Variable Declaration
    @Test
    fun testVariableDeclaration() {
        var node = ParserTestUtil.parseSingle("<VariableDeclarationTest>", "var i", VariableDeclarationNode::class.java)
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        node = ParserTestUtil.parseSingle("<VariableDeclarationTest>", "var i = 0", VariableDeclarationNode::class.java)
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNotNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
    }

    @Test
    fun testVariableLetDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<VariableLetDeclarationTest>", "let i", VariableDeclarationNode::class.java)
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        node =
            ParserTestUtil.parseSingle("<VariableLetDeclarationTest>", "let i = 0", VariableDeclarationNode::class.java)
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNotNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
    }

    @Test
    fun testVariableConstDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<VariableConstDeclarationTest>", "const i", VariableDeclarationNode::class.java)
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        node = ParserTestUtil.parseSingle(
            "<VariableConstDeclarationTest>",
            "const i = 0",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNotNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
    }

    @Test
    fun testFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalVariableDeclarationTest>",
            "final var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicVariableDeclarationTest>",
            "public var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedVariableDeclarationTest>",
            "protected var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalVariableDeclarationTest>",
            "protected final var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateVariableDeclarationTest>",
            "private var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalVariableDeclarationTest>",
            "private final var i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testDynamicVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<DynamicVariableDeclarationTest>",
            "dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<DynamicVariableDeclarationTest>",
            "dynamic d = 0",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalDynamicVariableDeclarationTest>",
            "final dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicDynamicVariableDeclarationTest>",
            "public dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedDynamicVariableDeclarationTest>",
            "protected dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDynamicIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalDynamicVariableDeclarationTest>",
            "protected final dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateDynamicVariableDeclarationTest>",
            "private dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalDynamicVariableDeclarationTest>",
            "private final dynamic d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DYNAMIC, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testBooleanVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<BooleanVariableDeclarationTest>",
            "boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<BooleanDeclarationTest>",
            "boolean b = true",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalBooleanVariableDeclarationTest>",
            "final boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicBooleanVariableDeclarationTest>",
            "public boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalBooleanVariableDeclarationTest>",
            "public final boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedBooleanVariableDeclarationTest>",
            "protected boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalBooleanVariableDeclarationTest>",
            "protected final boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateBooleanVariableDeclarationTest>",
            "private boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalBooleanVariableDeclarationTest>",
            "private final boolean b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BOOLEAN, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testCharacterVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<CharacterVariableDeclarationTest>",
            "char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<CharacterVariableDeclarationTest>",
            "char c = 12",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalCharacterVariableDeclarationTest>",
            "final char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicCharacterVariableDeclarationTest>",
            "public char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalCharacterVariableDeclarationTest>",
            "public final char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedCharacterVariableDeclarationTest>",
            "protected char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalCharacterVariableDeclarationTest>",
            "protected final char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateCharacterVariableDeclarationTest>",
            "private char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalCharacterVariableDeclarationTest>",
            "private final char c",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("c", node.name)
        Assertions.assertSame(VariableType.Type.CHAR, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testByteVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<ByteVariableDeclarationTest>", "byte b", VariableDeclarationNode::class.java)
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle("<ByteDeclarationTest>", "byte b = 12", VariableDeclarationNode::class.java)
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalByteVariableDeclarationTest>",
            "final byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicByteVariableDeclarationTest>",
            "public byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalByteVariableDeclarationTest>",
            "public final byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedByteVariableDeclarationTest>",
            "protected byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalByteVariableDeclarationTest>",
            "protected final byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateByteVariableDeclarationTest>",
            "private byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalByteVariableDeclarationTest>",
            "private final byte b",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("b", node.name)
        Assertions.assertSame(VariableType.Type.BYTE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testShortVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<ShortVariableDeclarationTest>", "short s", VariableDeclarationNode::class.java)
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<ShortVariableDeclarationTest>",
            "short s = 12",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalShortVariableDeclarationTest>",
            "final short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicShortVariableDeclarationTest>",
            "public short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalShortVariableDeclarationTest>",
            "public final short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedShortVariableDeclarationTest>",
            "protected short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalShortVariableDeclarationTest>",
            "protected final short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateShortVariableDeclarationTest>",
            "private short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalShortVariableDeclarationTest>",
            "private final short s",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("s", node.name)
        Assertions.assertSame(VariableType.Type.SHORT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testIntegerVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<IntegerVariableDeclarationTest>", "int i", VariableDeclarationNode::class.java)
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<IntegerVariableDeclarationTest>",
            "int i = 12",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalIntegerVariableDeclarationTest>",
            "final int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicIntegerVariableDeclarationTest>",
            "public int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalIntegerVariableDeclarationTest>",
            "public final int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedIntegerVariableDeclarationTest>",
            "protected int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalIntegerVariableDeclarationTest>",
            "protected final int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateIntegerVariableDeclarationTest>",
            "private int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalIntegerVariableDeclarationTest>",
            "private final int i",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("i", node.name)
        Assertions.assertSame(VariableType.Type.INTEGER, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testLongVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<LongVariableDeclarationTest>", "long l", VariableDeclarationNode::class.java)
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<LongVariableDeclarationTest>",
            "long l = 12",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalLongVariableDeclarationTest>",
            "final long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicLongVariableDeclarationTest>",
            "public long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalLongVariableDeclarationTest>",
            "public final long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedLongVariableDeclarationTest>",
            "protected long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalLongVariableDeclarationTest>",
            "protected final long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateLongVariableDeclarationTest>",
            "private long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalLongVariableDeclarationTest>",
            "private final long l",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("l", node.name)
        Assertions.assertSame(VariableType.Type.LONG, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testFloatVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<FloatVariableDeclarationTest>", "float f", VariableDeclarationNode::class.java)
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
        node = ParserTestUtil.parseSingle(
            "<FloatVariableDeclarationTest>",
            "float f = 12",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNotNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalFloatVariableDeclarationTest>",
            "final float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalFloatVariableDeclarationTest>",
            "public float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalFloatVariableDeclarationTest>",
            "public final float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFloatVariableDeclarationTest>",
            "protected float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalFloatVariableDeclarationTest>",
            "protected final float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFloatVariableDeclarationTest>",
            "private float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalFloatVariableDeclarationTest>",
            "private final float f",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("f", node.name)
        Assertions.assertSame(VariableType.Type.FLOAT, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testDoubleVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "double d = 12",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNotNull(node.assignment)
    }

    @Test
    fun testDoubleFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "final double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicDoubleVariableDeclarationTest>",
            "public double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDoubleVariableDeclarationTest>",
            "public final double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedDoubleVariableDeclarationTest>",
            "protected double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalDoubleVariableDeclarationTest>",
            "protected final double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateDoubleVariableDeclarationTest>",
            "private double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDoubleFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalDoubleVariableDeclarationTest>",
            "private final double d",
            VariableDeclarationNode::class.java
        )
        Assertions.assertEquals("d", node.name)
        Assertions.assertSame(VariableType.Type.DOUBLE, node.type.type)
        Assertions.assertNull(node.assignment)
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertFalse(node.isInClass)
    }

    //// *************************************************************
    //// Variable Usage
    @Test
    fun testVariableUsage() {
        val node = ParserTestUtil.parseSingle("<VariableUsageTest>", "i", VariableUsageNode::class.java)

        // Variable names
        assertType(IdentifierNode::class.java, node.variable)
        Assertions.assertEquals("i", node.variable.name)
        Assertions.assertNull(node.variable.parent)
    }
}