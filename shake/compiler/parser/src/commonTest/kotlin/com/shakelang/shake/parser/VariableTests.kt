package com.shakelang.shake.parser

import com.shakelang.shake.assertType
import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeIdentifierNode
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.expression.ShakeAddNode
import com.shakelang.shake.parser.node.factor.ShakeIntegerNode
import com.shakelang.shake.parser.node.variables.*
import com.shakelang.shake.shouldBeOfType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.reflect.KClass
import kotlin.test.*

class VariableTests : FreeSpec({

    // // *************************************************************
    // // Assignments

    class AssignmentTestDescriptor(
        val name: String,
        val operator: String,
        val nodeClass: KClass<out ShakeCommonVariableAssignmentNode>
    )

    listOf(
        AssignmentTestDescriptor("variable assignment", "=", ShakeVariableAssignmentNode::class),
        AssignmentTestDescriptor("variable add assignment", "+=", ShakeVariableAddAssignmentNode::class),
        AssignmentTestDescriptor("variable sub assignment", "-=", ShakeVariableSubAssignmentNode::class),
        AssignmentTestDescriptor("variable mul assignment", "*=", ShakeVariableMulAssignmentNode::class),
        AssignmentTestDescriptor("variable div assignment", "/=", ShakeVariableDivAssignmentNode::class),
        AssignmentTestDescriptor("variable mod assignment", "%=", ShakeVariableModAssignmentNode::class),
        AssignmentTestDescriptor("variable pow assignment", "**=", ShakeVariablePowAssignmentNode::class),
    ).forEach {
        it.name {
            val node = ParserTestUtil.parseStatement("<${it.name}>", "i ${it.operator} 0", it.nodeClass)
            node shouldBeOfType it.nodeClass
            (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
            (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
            node.value shouldNotBe null
            node.value shouldBeOfType ShakeIntegerNode::class
            (node.value as ShakeIntegerNode).number shouldBe 0
        }

        "${it.name} with expression" {
            val node = ParserTestUtil.parseStatement("<${it.name}>", "i ${it.operator} 11 + 2", it.nodeClass)
            node shouldBeOfType it.nodeClass
            (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
            (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
            node.value shouldNotBe null
            node.value shouldBeOfType ShakeAddNode::class
            (node.value as ShakeAddNode).left shouldBeOfType ShakeIntegerNode::class
            (node.value as ShakeAddNode).right shouldBeOfType ShakeIntegerNode::class
            ((node.value as ShakeAddNode).left as ShakeIntegerNode).number shouldBe 11
            ((node.value as ShakeAddNode).right as ShakeIntegerNode).number shouldBe 2
        }
    }

    "variable incr" {
        val node = ParserTestUtil.parseStatement("<VariableIncreaseTest>", "i ++", ShakeVariableIncreaseNode::class)
        assertType(ShakeVariableUsageNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeVariableUsageNode).identifier.name)
        assertNull((node.variable as ShakeVariableUsageNode).identifier.parent)
    }

    "variable decr" {
        val node = ParserTestUtil.parseStatement("<VariableDecreaseTest>", "i --", ShakeVariableDecreaseNode::class)
        assertType(ShakeVariableUsageNode::class, node.variable)
        assertEquals("i", (node.variable as ShakeVariableUsageNode).identifier.name)
        assertNull((node.variable as ShakeVariableUsageNode).identifier.parent)
    }

    // // *************************************************************
    // // Variable Declaration

    "final variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<FinalVariableDeclarationTest>",
            "final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    "public variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<PublicVariableDeclarationTest>",
            "public int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    "public final variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<PublicFinalDynamicVariableDeclarationTest>",
            "public final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    "protected variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedVariableDeclarationTest>",
            "protected int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    "protected final variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<ProtectedFinalVariableDeclarationTest>",
            "protected final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    "private variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<PrivateVariableDeclarationTest>",
            "private int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    "private final variable declaration" {
        val node = ParserTestUtil.parseSingle(
            "<PrivateFinalVariableDeclarationTest>",
            "private final int i",
            ShakeVariableDeclarationNode::class
        )
        assertEquals("i", node.name)
        assertSame(ShakeVariableType.Type.INTEGER, node.type.type)
        assertNull(node.value)
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    "dynamic variable declaration" {
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

    "final dynamic variable declaration" {
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
    }

    "public dynamic variable declaration" {
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
    }

    "public final dynamic variable declaration" {
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
    }

    "protected dynamic variable declaration" {
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
    }

    "protected final dynamic variable declaration" {
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
    }

    "private dynamic variable declaration" {
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
    }

    "private final dynamic variable declaration" {
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
    }

    "boolean variable declaration" {
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

    "final boolean variable declaration" {
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
    }

    "public boolean variable declaration" {
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
    }

    "public final boolean variable declaration" {
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
    }

    "protected boolean variable declaration" {
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
    }

    "protected final boolean variable declaration" {
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
    }

    "private boolean variable declaration" {
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
    }

    "private final boolean variable declaration" {
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
    }

    "char variable declaration" {
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

    "final char variable declaration" {
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
    }

    "public char variable declaration" {
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
    }

    "public final char variable declaration" {
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
    }

    "protected char variable declaration" {
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
    }

    "protected final char variable declaration" {
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
    }

    "private char variable declaration" {
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
    }

    "private final char variable declaration" {
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
    }

    "float variable declaration" {
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
    }

    // // *************************************************************
    // // Variable Usage
    @Test
    fun testVariableUsage() {
        val node = ParserTestUtil.parseValue("<VariableUsageTest>", "i", ShakeVariableUsageNode::class)

        // Variable names
        assertType(ShakeIdentifierNode::class, node.identifier)
        assertEquals("i", node.identifier.name)
        assertNull(node.identifier.parent)
    }
})