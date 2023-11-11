package com.shakelang.shake.parser

import com.shakelang.shake.assertType
import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.factor.ShakeIntegerNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.functions.ShakeReturnNode
import kotlin.test.*

class FunctionTests {

    // ********************************************************************
    // C-Style
    @Test
    fun testCStyleFunction() {
        val tree = ParserTestUtil.parse("<CStyleFunctionTest>", "int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionChildren() {
        val tree = ParserTestUtil.parse("<CStyleFunctionChildrenTest>", "int f() { print(10); }")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(1, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStylePublicFunction() {
        val tree = ParserTestUtil.parse("<CStylePublicFunctionTest>", "public int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleProtectedFunction() {
        val tree = ParserTestUtil.parse("<CStyleProtectedFunctionTest>", "protected int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStylePrivateFunction() {
        val tree = ParserTestUtil.parse("<CStylePrivateFunctionTest>", "private int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFinalFunction() {
        val tree = ParserTestUtil.parse("<CStyleFinalFunctionTest>", "final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStylePublicFinalFunction() {
        val tree = ParserTestUtil.parse("<CStylePublicFinalFunctionTest>", "public final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStyleProtectedFinalFunction() {
        val tree = ParserTestUtil.parse("<CStyleProtectedFinalFunctionTest>", "protected final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStylePrivateFinalFunction() {
        val tree = ParserTestUtil.parse("<CStylePrivateFinalFunctionTest>", "private final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.INTEGER, node.type)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStyleFunctionByte() {
        val tree = ParserTestUtil.parse("<CStyleFunctionByteTest>", "byte f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.BYTE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionShort() {
        val tree = ParserTestUtil.parse("<CStyleFunctionShortTest>", "short f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.SHORT, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionLong() {
        val tree = ParserTestUtil.parse("<CStyleFunctionLongTest>", "long f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.LONG, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionFloat() {
        val tree = ParserTestUtil.parse("<CStyleFunctionFloatTest>", "float f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.FLOAT, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionDouble() {
        val tree = ParserTestUtil.parse("<CStyleFunctionDoubleTest>", "double f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.DOUBLE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionChar() {
        val tree = ParserTestUtil.parse("<CStyleFunctionCharTest>", "char f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.CHAR, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionBoolean() {
        val tree = ParserTestUtil.parse("<CStyleFunctionBooleanTest>", "boolean f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.BOOLEAN, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionVoid() {
        val tree = ParserTestUtil.parse("<CStyleFunctionVoidTest>", "void f() {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeFunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeFunctionDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body?.children?.size)
        assertSame(ShakeVariableType.VOID, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testReturn() {
        val tree = ParserTestUtil.parseStatement("<FunctionTest>", "return 10;")
        assertEquals(1, tree.children.size)
        assertType(ShakeReturnNode::class, tree.children[0])
        val node = tree.children[0] as ShakeReturnNode
        assertType(ShakeIntegerNode::class, node.value)
    }
}
