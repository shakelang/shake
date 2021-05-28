package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.VariableType
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode
import com.github.nsc.de.shake.parser.node.functions.ReturnNode
import kotlin.test.*

class FunctionTests {
    @Test
    fun testFunction() {
        val tree = ParserTestUtil.parse("<FunctionTest>", "function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testFunctionChildren() {
        val tree = ParserTestUtil.parse("<FunctionChildrenTest>", "function f() { print(10); }")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(1, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testPublicFunction() {
        val tree = ParserTestUtil.parse("<PublicFunctionTest>", "public function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testProtectedFunction() {
        val tree = ParserTestUtil.parse("<ProtectedFunctionTest>", "protected function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testPrivateFunction() {
        val tree = ParserTestUtil.parse("<PrivateFunctionTest>", "private function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testFinalFunction() {
        val tree = ParserTestUtil.parse("<FinalFunctionTest>", "final function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testPublicFinalFunction() {
        val tree = ParserTestUtil.parse("<PublicFinalFunctionTest>", "public final function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testProtectedFinalFunction() {
        val tree = ParserTestUtil.parse("<ProtectedFinalFunctionTest>", "protected final function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testPrivateFinalFunction() {
        val tree = ParserTestUtil.parse("<PrivateFinalFunctionTest>", "private final function f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DYNAMIC, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    // ********************************************************************
    // C-Style
    @Test
    fun testCStyleFunction() {
        val tree = ParserTestUtil.parse("<CStyleFunctionTest>", "int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionChildren() {
        val tree = ParserTestUtil.parse("<CStyleFunctionChildrenTest>", "int f() { print(10); }")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(1, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStylePublicFunction() {
        val tree = ParserTestUtil.parse("<CStylePublicFunctionTest>", "public int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleProtectedFunction() {
        val tree = ParserTestUtil.parse("<CStyleProtectedFunctionTest>", "protected int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStylePrivateFunction() {
        val tree = ParserTestUtil.parse("<CStylePrivateFunctionTest>", "private int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFinalFunction() {
        val tree = ParserTestUtil.parse("<CStyleFinalFunctionTest>", "final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStylePublicFinalFunction() {
        val tree = ParserTestUtil.parse("<CStylePublicFinalFunctionTest>", "public final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStyleProtectedFinalFunction() {
        val tree = ParserTestUtil.parse("<CStyleProtectedFinalFunctionTest>", "protected final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStylePrivateFinalFunction() {
        val tree = ParserTestUtil.parse("<CStylePrivateFinalFunctionTest>", "private final int f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.INTEGER, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
    }

    @Test
    fun testCStyleFunctionByte() {
        val tree = ParserTestUtil.parse("<CStyleFunctionByteTest>", "byte f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.BYTE, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionShort() {
        val tree = ParserTestUtil.parse("<CStyleFunctionShortTest>", "short f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.SHORT, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionLong() {
        val tree = ParserTestUtil.parse("<CStyleFunctionLongTest>", "long f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.LONG, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionFloat() {
        val tree = ParserTestUtil.parse("<CStyleFunctionFloatTest>", "float f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.FLOAT, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionDouble() {
        val tree = ParserTestUtil.parse("<CStyleFunctionDoubleTest>", "double f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.DOUBLE, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionChar() {
        val tree = ParserTestUtil.parse("<CStyleFunctionCharTest>", "char f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.CHAR, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionBoolean() {
        val tree = ParserTestUtil.parse("<CStyleFunctionBooleanTest>", "boolean f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.BOOLEAN, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testCStyleFunctionVoid() {
        val tree = ParserTestUtil.parse("<CStyleFunctionVoidTest>", "void f() {}")
        assertEquals(1, tree.children.size)
        assertType(FunctionDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as FunctionDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("f", node.name)
        assertSame(0, node.body.children.size)
        assertSame(VariableType.VOID, node.type)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
    }

    @Test
    fun testReturn() {
        val tree = ParserTestUtil.parse("<FunctionTest>", "return 10;")
        assertEquals(1, tree.children.size)
        assertType(ReturnNode::class, tree.children[0])
        val node = tree.children[0] as ReturnNode
        assertType(IntegerNode::class, node.value)
    }
}