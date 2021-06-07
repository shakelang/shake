package com.github.shakelang.shake.parser

import com.github.shakelang.shake.assertType
import com.github.shakelang.shake.parser.node.AccessDescriber
import com.github.shakelang.shake.parser.node.IdentifierNode
import com.github.shakelang.shake.parser.node.VariableType
import com.github.shakelang.shake.parser.node.factor.DoubleNode
import com.github.shakelang.shake.parser.node.factor.IntegerNode
import com.github.shakelang.shake.parser.node.logical.LogicalTrueNode
import com.github.shakelang.shake.parser.node.variables.*
import kotlin.test.*

class VariableTests {
    
    
    //// *************************************************************
    //// Assignments
    
    @Test
    fun testVariableAssignment() {
        var node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = 0", VariableAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
        node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = 0.1", VariableAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(DoubleNode::class, node.value)
        node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = true", VariableAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(LogicalTrueNode::class, node.value)
    }

    @Test
    fun testVariableAddAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableAddAssignmentTest>", "i += 0", VariableAddAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
    }

    @Test
    fun testVariableSubAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableSubAssignmentTest>", "i -= 0", VariableSubAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
    }

    @Test
    fun testVariableMulAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableMulAssignmentTest>", "i *= 0", VariableMulAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
    }

    @Test
    fun testVariableDivAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableDivAssignmentTest>", "i /= 1", VariableDivAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
    }

    @Test
    fun testVariableModAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableModAssignmentTest>", "i %= 1", VariableModAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
    }

    @Test
    fun testVariablePowAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariablePowAssignmentTest>", "i **= 0", VariablePowAssignmentNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
        assertNotNull(node.value)
        assertType(IntegerNode::class, node.value)
    }

    @Test
    fun testVariableIncrease() {
        val node = ParserTestUtil.parseSingle("<VariableIncreaseTest>", "i ++", VariableIncreaseNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
    }

    @Test
    fun testVariableDecrease() {
        val node = ParserTestUtil.parseSingle("<VariableDecreaseTest>", "i --", VariableDecreaseNode::class)
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as IdentifierNode).name)
        assertNull((node.variable as IdentifierNode).parent)
    }

    
    //// *************************************************************
    //// Variable Declaration
    
    @Test
    fun testVariableDeclaration() {
        var node = ParserTestUtil.parseSingle("<VariableDeclarationTest>", "var i", VariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        node = ParserTestUtil.parseSingle("<VariableDeclarationTest>", "var i = 0", VariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
    }

    @Test
    fun testVariableLetDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<VariableLetDeclarationTest>", "let i", VariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        node =
            ParserTestUtil.parseSingle("<VariableLetDeclarationTest>", "let i = 0", VariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
    }

    @Test
    fun testVariableConstDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<VariableConstDeclarationTest>", "const i", VariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        node = ParserTestUtil.parseSingle(
            "<VariableConstDeclarationTest>",
            "const i = 0",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
    }

    @Test
    fun testFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalVariableDeclarationTest>",
            "final var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicVariableDeclarationTest>",
            "public var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedVariableDeclarationTest>",
            "protected var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalVariableDeclarationTest>",
            "protected final var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateVariableDeclarationTest>",
            "private var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalVariableDeclarationTest>",
            "private final var i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testDynamicVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<DynamicVariableDeclarationTest>",
            "dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<DynamicVariableDeclarationTest>",
            "dynamic d = 0",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalDynamicVariableDeclarationTest>",
            "final dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicDynamicVariableDeclarationTest>",
            "public dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedDynamicVariableDeclarationTest>",
            "protected dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDynamicIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalDynamicVariableDeclarationTest>",
            "protected final dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateDynamicVariableDeclarationTest>",
            "private dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalDynamicVariableDeclarationTest>",
            "private final dynamic d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testBooleanVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<BooleanVariableDeclarationTest>",
            "boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<BooleanDeclarationTest>",
            "boolean b = true",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalBooleanVariableDeclarationTest>",
            "final boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicBooleanVariableDeclarationTest>",
            "public boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalBooleanVariableDeclarationTest>",
            "public final boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedBooleanVariableDeclarationTest>",
            "protected boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalBooleanVariableDeclarationTest>",
            "protected final boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateBooleanVariableDeclarationTest>",
            "private boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalBooleanVariableDeclarationTest>",
            "private final boolean b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testCharacterVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<CharacterVariableDeclarationTest>",
            "char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<CharacterVariableDeclarationTest>",
            "char c = 12",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalCharacterVariableDeclarationTest>",
            "final char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicCharacterVariableDeclarationTest>",
            "public char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalCharacterVariableDeclarationTest>",
            "public final char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedCharacterVariableDeclarationTest>",
            "protected char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalCharacterVariableDeclarationTest>",
            "protected final char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateCharacterVariableDeclarationTest>",
            "private char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalCharacterVariableDeclarationTest>",
            "private final char c",
            VariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(VariableType.Type.CHAR, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testByteVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<ByteVariableDeclarationTest>", "byte b", VariableDeclarationNode::class)
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle("<ByteDeclarationTest>", "byte b = 12", VariableDeclarationNode::class)
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalByteVariableDeclarationTest>",
            "final byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicByteVariableDeclarationTest>",
            "public byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalByteVariableDeclarationTest>",
            "public final byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedByteVariableDeclarationTest>",
            "protected byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalByteVariableDeclarationTest>",
            "protected final byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateByteVariableDeclarationTest>",
            "private byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalByteVariableDeclarationTest>",
            "private final byte b",
            VariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(VariableType.Type.BYTE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testShortVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<ShortVariableDeclarationTest>", "short s", VariableDeclarationNode::class)
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<ShortVariableDeclarationTest>",
            "short s = 12",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalShortVariableDeclarationTest>",
            "final short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicShortVariableDeclarationTest>",
            "public short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalShortVariableDeclarationTest>",
            "public final short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedShortVariableDeclarationTest>",
            "protected short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalShortVariableDeclarationTest>",
            "protected final short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateShortVariableDeclarationTest>",
            "private short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalShortVariableDeclarationTest>",
            "private final short s",
            VariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(VariableType.Type.SHORT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testIntegerVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<IntegerVariableDeclarationTest>", "int i", VariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<IntegerVariableDeclarationTest>",
            "int i = 12",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalIntegerVariableDeclarationTest>",
            "final int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicIntegerVariableDeclarationTest>",
            "public int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalIntegerVariableDeclarationTest>",
            "public final int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedIntegerVariableDeclarationTest>",
            "protected int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalIntegerVariableDeclarationTest>",
            "protected final int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateIntegerVariableDeclarationTest>",
            "private int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalIntegerVariableDeclarationTest>",
            "private final int i",
            VariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(VariableType.Type.INTEGER, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testLongVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<LongVariableDeclarationTest>", "long l", VariableDeclarationNode::class)
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<LongVariableDeclarationTest>",
            "long l = 12",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalLongVariableDeclarationTest>",
            "final long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicLongVariableDeclarationTest>",
            "public long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalLongVariableDeclarationTest>",
            "public final long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedLongVariableDeclarationTest>",
            "protected long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalLongVariableDeclarationTest>",
            "protected final long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateLongVariableDeclarationTest>",
            "private long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalLongVariableDeclarationTest>",
            "private final long l",
            VariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(VariableType.Type.LONG, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testFloatVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<FloatVariableDeclarationTest>", "float f", VariableDeclarationNode::class)
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        node = ParserTestUtil.parseSingle(
            "<FloatVariableDeclarationTest>",
            "float f = 12",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNotNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalFloatVariableDeclarationTest>",
            "final float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalFloatVariableDeclarationTest>",
            "public float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalFloatVariableDeclarationTest>",
            "public final float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFloatVariableDeclarationTest>",
            "protected float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalFloatVariableDeclarationTest>",
            "protected final float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFloatVariableDeclarationTest>",
            "private float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalFloatVariableDeclarationTest>",
            "private final float f",
            VariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(VariableType.Type.FLOAT, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testDoubleVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "double d = 12",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNotNull(node.assignment)
    }

    @Test
    fun testDoubleFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "final double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicDoubleVariableDeclarationTest>",
            "public double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDoubleVariableDeclarationTest>",
            "public final double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedDoubleVariableDeclarationTest>",
            "protected double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalDoubleVariableDeclarationTest>",
            "protected final double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateDoubleVariableDeclarationTest>",
            "private double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDoubleFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalDoubleVariableDeclarationTest>",
            "private final double d",
            VariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(VariableType.Type.DOUBLE, node.type.type)
        assertNull(node.assignment)
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    //// *************************************************************
    //// Variable Usage
    @Test
    fun testVariableUsage() {
        val node = ParserTestUtil.parseSingle("<VariableUsageTest>", "i", VariableUsageNode::class)

        // Variable names
        assertType(IdentifierNode::class, node.variable)
        assertEquals("i", node.variable.name)
        assertNull(node.variable.parent)
    }
}