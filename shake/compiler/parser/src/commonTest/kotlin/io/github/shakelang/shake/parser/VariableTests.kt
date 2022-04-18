package io.github.shakelang.shake.parser

import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeIdentifierNode
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalTrueNode
import io.github.shakelang.shake.parser.node.variables.*
import kotlin.test.*

class VariableTests {
    
    
    //// *************************************************************
    //// Assignments
    
    @Test
    fun testVariableAssignment() {
        var node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = 0", ShakeValuedNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
        node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = 0.1", ShakeValuedNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeDoubleNode::class, node.value)
        node = ParserTestUtil.parseSingle("<VariableAssignmentTest>", "i = true", ShakeValuedNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeLogicalTrueNode::class, node.value)
    }

    @Test
    fun testVariableAddAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableAddAssignmentTest>", "i += 0", ShakeVariableAddAssignmentNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
    }

    @Test
    fun testVariableSubAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableSubAssignmentTest>", "i -= 0", ShakeVariableSubAssignmentNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
    }

    @Test
    fun testVariableMulAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableMulAssignmentTest>", "i *= 0", ShakeVariableMulAssignmentNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
    }

    @Test
    fun testVariableDivAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableDivAssignmentTest>", "i /= 1", ShakeVariableDivAssignmentNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
    }

    @Test
    fun testVariableModAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariableModAssignmentTest>", "i %= 1", ShakeVariableModAssignmentNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
    }

    @Test
    fun testVariablePowAssignment() {
        val node =
            ParserTestUtil.parseSingle("<VariablePowAssignmentTest>", "i **= 0", ShakeVariablePowAssignmentNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
        assertNotNull(node.value)
        assertType(ShakeIntegerNode::class, node.value)
    }

    @Test
    fun testVariableIncrease() {
        val node = ParserTestUtil.parseSingle("<VariableIncreaseTest>", "i ++", ShakeVariableIncreaseNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
    }

    @Test
    fun testVariableDecrease() {
        val node = ParserTestUtil.parseSingle("<VariableDecreaseTest>", "i --", ShakeVariableDecreaseNode::class)
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeIdentifierNode).name)
        assertNull((node.variable as ShakeIdentifierNode).parent)
    }

    
    //// *************************************************************
    //// Variable Declaration
    
    @Test
    fun testVariableDeclaration() {
        var node = ParserTestUtil.parseSingle("<VariableDeclarationTest>", "var i", ShakeVariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        node = ParserTestUtil.parseSingle("<VariableDeclarationTest>", "var i = 0", ShakeVariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
    }

    @Test
    fun testVariableLetDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<VariableLetDeclarationTest>", "let i", ShakeVariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        node =
            ParserTestUtil.parseSingle("<VariableLetDeclarationTest>", "let i = 0", ShakeVariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
    }

    @Test
    fun testVariableConstDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<VariableConstDeclarationTest>", "const i", ShakeVariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        node = ParserTestUtil.parseSingle(
            "<VariableConstDeclarationTest>",
            "const i = 0",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
    }

    @Test
    fun testFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalVariableDeclarationTest>",
            "final var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicVariableDeclarationTest>",
            "public var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedVariableDeclarationTest>",
            "protected var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalVariableDeclarationTest>",
            "protected final var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateVariableDeclarationTest>",
            "private var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalVariableDeclarationTest>",
            "private final var i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testDynamicVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<DynamicVariableDeclarationTest>",
            "dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<DynamicVariableDeclarationTest>",
            "dynamic d = 0",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalDynamicVariableDeclarationTest>",
            "final dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicDynamicVariableDeclarationTest>",
            "public dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedDynamicVariableDeclarationTest>",
            "protected dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDynamicIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalDynamicVariableDeclarationTest>",
            "protected final dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateDynamicVariableDeclarationTest>",
            "private dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalDynamicVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalDynamicVariableDeclarationTest>",
            "private final dynamic d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DYNAMIC, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testBooleanVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<BooleanVariableDeclarationTest>",
            "boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<BooleanDeclarationTest>",
            "boolean b = true",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalBooleanVariableDeclarationTest>",
            "final boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicBooleanVariableDeclarationTest>",
            "public boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalBooleanVariableDeclarationTest>",
            "public final boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedBooleanVariableDeclarationTest>",
            "protected boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalBooleanVariableDeclarationTest>",
            "protected final boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateBooleanVariableDeclarationTest>",
            "private boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalBooleanVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalBooleanVariableDeclarationTest>",
            "private final boolean b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BOOLEAN, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testCharacterVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<CharacterVariableDeclarationTest>",
            "char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<CharacterVariableDeclarationTest>",
            "char c = 12",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalCharacterVariableDeclarationTest>",
            "final char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicCharacterVariableDeclarationTest>",
            "public char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalCharacterVariableDeclarationTest>",
            "public final char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedCharacterVariableDeclarationTest>",
            "protected char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalCharacterVariableDeclarationTest>",
            "protected final char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateCharacterVariableDeclarationTest>",
            "private char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalCharacterVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalCharacterVariableDeclarationTest>",
            "private final char c",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("c", node.name)
        assertSame(ShakeVariableType.Type.CHAR, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testByteVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<ByteVariableDeclarationTest>", "byte b", ShakeVariableDeclarationNode::class)
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle("<ByteDeclarationTest>", "byte b = 12", ShakeVariableDeclarationNode::class)
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalByteVariableDeclarationTest>",
            "final byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicByteVariableDeclarationTest>",
            "public byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalByteVariableDeclarationTest>",
            "public final byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedByteVariableDeclarationTest>",
            "protected byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalByteVariableDeclarationTest>",
            "protected final byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateByteVariableDeclarationTest>",
            "private byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalByteVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalByteVariableDeclarationTest>",
            "private final byte b",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("b", node.name)
        assertSame(ShakeVariableType.Type.BYTE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testShortVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<ShortVariableDeclarationTest>", "short s", ShakeVariableDeclarationNode::class)
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<ShortVariableDeclarationTest>",
            "short s = 12",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalShortVariableDeclarationTest>",
            "final short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicShortVariableDeclarationTest>",
            "public short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalShortVariableDeclarationTest>",
            "public final short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedShortVariableDeclarationTest>",
            "protected short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalShortVariableDeclarationTest>",
            "protected final short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateShortVariableDeclarationTest>",
            "private short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalShortVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalShortVariableDeclarationTest>",
            "private final short s",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("s", node.name)
        assertSame(ShakeVariableType.Type.SHORT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testIntegerVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<IntegerVariableDeclarationTest>", "int i", ShakeVariableDeclarationNode::class)
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<IntegerVariableDeclarationTest>",
            "int i = 12",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalIntegerVariableDeclarationTest>",
            "final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicIntegerVariableDeclarationTest>",
            "public int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalIntegerVariableDeclarationTest>",
            "public final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedIntegerVariableDeclarationTest>",
            "protected int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalIntegerVariableDeclarationTest>",
            "protected final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateIntegerVariableDeclarationTest>",
            "private int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalIntegerVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalIntegerVariableDeclarationTest>",
            "private final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testLongVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<LongVariableDeclarationTest>", "long l", ShakeVariableDeclarationNode::class)
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<LongVariableDeclarationTest>",
            "long l = 12",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalLongVariableDeclarationTest>",
            "final long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicLongVariableDeclarationTest>",
            "public long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalLongVariableDeclarationTest>",
            "public final long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedLongVariableDeclarationTest>",
            "protected long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalLongVariableDeclarationTest>",
            "protected final long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateLongVariableDeclarationTest>",
            "private long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalLongVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalLongVariableDeclarationTest>",
            "private final long l",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("l", node.name)
        assertSame(ShakeVariableType.Type.LONG, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testFloatVariableDeclaration() {
        var node =
            ParserTestUtil.parseSingle("<FloatVariableDeclarationTest>", "float f", ShakeVariableDeclarationNode::class)
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
        node = ParserTestUtil.parseSingle(
            "<FloatVariableDeclarationTest>",
            "float f = 12",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNotNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<FinalFloatVariableDeclarationTest>",
            "final float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalFloatVariableDeclarationTest>",
            "public float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalFloatVariableDeclarationTest>",
            "public final float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFloatVariableDeclarationTest>",
            "protected float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalFloatVariableDeclarationTest>",
            "protected final float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFloatVariableDeclarationTest>",
            "private float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateFinalFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalFloatVariableDeclarationTest>",
            "private final float f",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("f", node.name)
        assertSame(ShakeVariableType.Type.FLOAT, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testDoubleVariableDeclaration() {
        var node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "double d = 12",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNotNull(node.value)
    }

    @Test
    fun testDoubleFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<DoubleVariableDeclarationTest>",
            "final double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicDoubleVariableDeclarationTest>",
            "public double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPublicFinalDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDoubleVariableDeclarationTest>",
            "public final double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedDoubleVariableDeclarationTest>",
            "protected double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testProtectedFinalDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalDoubleVariableDeclarationTest>",
            "protected final double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDoubleVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateDoubleVariableDeclarationTest>",
            "private double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertFalse(node.isInClass)
    }

    @Test
    fun testPrivateDoubleFloatVariableDeclaration() {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalDoubleVariableDeclarationTest>",
            "private final double d",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("d", node.name)
        assertSame(ShakeVariableType.Type.DOUBLE, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertFalse(node.isInClass)
    }

    //// *************************************************************
    //// Variable Usage
    @Test
    fun testVariableUsage() {
        val node = ParserTestUtil.parseSingle("<VariableUsageTest>", "i", ShakeVariableUsageNode::class)

        // Variable names
        assertType(ShakeIdentifierNode::class, node.variable)
        assertEquals("i", node.variable.name)
        assertNull(node.variable.parent)
    }
}