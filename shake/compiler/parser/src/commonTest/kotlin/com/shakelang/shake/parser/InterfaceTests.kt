package com.shakelang.shake.parser

import io.github.shakelang.shake.assertType
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassType
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import kotlin.test.*

class InterfaceTests {
    @Test
    fun testFinalInterface() {
        assertFailsWith(
            com.shakelang.shake.parser.ShakeParserImpl.ParserError::class,
            "Error occurred in parser: ParserError, Interfaces cannot be final in PositionMaker(index=22, " +
                "column=23, line=1, lineSeparators=[]):1:7: Interfaces cannot be final\n" +
                "\n" +
                "at <InterfaceTest>:1:7\n" +
                "1  final interface test {}\n" +
                "         ^^^^^^^^^\n" +
                "\n"
        ) {
            ParserTestUtil.parse("<InterfaceTest>", "final interface test {}")
        }
    }

    @Test
    fun testClass() {
        val tree = ParserTestUtil.parse("<InterfaceTest>", "interface test {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertEquals(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testPublicClass() {
        val tree = ParserTestUtil.parse("<PublicClassTest>", "public interface test {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PUBLIC, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testProtectedClass() {
        val tree = ParserTestUtil.parse("<ProtectedClassTest>", "protected interface test {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PROTECTED, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testPrivateClass() {
        val tree = ParserTestUtil.parse("<PrivateClassTest>", "private interface test {}")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PRIVATE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testClassField1() {
        val tree = ParserTestUtil.parse("<ClassField1Test>", "interface test { int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i", variable.name)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassField2() {
        val tree = ParserTestUtil.parse("<ClassField2Test>", "interface test { int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i", variable.name)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassMultiField() {
        val tree = ParserTestUtil.parse("<ClassMultiFieldTest>", "interface test { int i = 0; int i2 = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(2, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        var variable = node.fields[0]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i", variable.name)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        variable = node.fields[1]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i2", variable.name)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassStaticField() {
        val tree = ParserTestUtil.parse("<ClassStaticFieldTest>", "interface test { static int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i", variable.name)
        assertTrue(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassFinalField() {
        val tree = ParserTestUtil.parse("<ClassFinalFieldTest>", "interface test { final int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i", variable.name)
        assertFalse(variable.isStatic)
        assertTrue(variable.isFinal)
    }

    @Test
    fun testClassStaticFinalField() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalFieldTest>", "interface test { static final int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeVariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.value)
        assertEquals("i", variable.name)
        assertTrue(variable.isStatic)
        assertTrue(variable.isFinal)
    }

    @Test
    fun testClassFunction() {
        val tree = ParserTestUtil.parse("<ClassFunctionTest>", "interface test { int f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeFunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(ShakeVariableType.Type.INTEGER, function.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertFalse(function.isStatic)
        assertFalse(function.isFinal)
    }

    @Test
    fun testClassMultiFunction() {
        val tree = ParserTestUtil.parse("<ClassMultiFunctionTest>", "interface test { void f() {}\nvoid g() {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(2, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeFunctionDeclarationNode::class, node.methods[0])
        var function = node.methods[0]
        assertSame(ShakeVariableType.Type.VOID, function.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertFalse(function.isStatic)
        assertFalse(function.isFinal)
        assertType(ShakeFunctionDeclarationNode::class, node.methods[1])
        function = node.methods[1]
        assertSame(ShakeVariableType.Type.VOID, function.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("g", function.name)
        assertFalse(function.isStatic)
        assertFalse(function.isFinal)
    }

    @Test
    fun testClassStaticFunction() {
        val tree = ParserTestUtil.parse("<ClassStaticFunctionTest>", "interface test { static void f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeFunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(ShakeVariableType.Type.VOID, function.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isStatic)
        assertFalse(function.isFinal)
    }

    @Test
    fun testClassFinalFunction() {
        val tree = ParserTestUtil.parse("<ClassFinalFunctionTest>", "interface test { final int f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeFunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(ShakeVariableType.Type.INTEGER, function.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertFalse(function.isStatic)
        assertTrue(function.isFinal)
    }

    @Test
    fun testClassStaticFinalFunction() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalFunctionTest>", "interface test { static final void f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(ShakeFunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(ShakeVariableType.Type.VOID, function.type.type)
        assertSame(ShakeAccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isStatic)
        assertTrue(function.isFinal)
    }

    @Test
    fun testClassClass() {
        val tree = ParserTestUtil.parse("<ClassClassTest>", "interface test { class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ShakeClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(ShakeAccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertFalse(aclass.isStatic)
        assertFalse(aclass.isFinal)
    }

    @Test
    fun testClassMultiClass() {
        val tree = ParserTestUtil.parse("<ClassMultiClassTest>", "interface test { class c {} class c2 {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(2, node.classes.size)
        assertType(ShakeClassDeclarationNode::class, node.classes[0])
        var aclass = node.classes[0]
        assertSame(ShakeAccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertFalse(aclass.isStatic)
        assertFalse(aclass.isFinal)
        assertType(ShakeClassDeclarationNode::class, node.classes[1])
        aclass = node.classes[1]
        assertSame(ShakeAccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c2", aclass.name)
        assertFalse(aclass.isStatic)
        assertFalse(aclass.isFinal)
    }

    @Test
    fun testStaticClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticClassTest>", "interface test { static class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ShakeClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(ShakeAccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isStatic)
        assertFalse(aclass.isFinal)
    }

    @Test
    fun testFinalClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalClassTest>", "interface test { final class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ShakeClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(ShakeAccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertFalse(aclass.isStatic)
        assertTrue(aclass.isFinal)
    }

    @Test
    fun testStaticFinalClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalClassTest>", "interface test { static final class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ShakeClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ShakeClassDeclarationNode
        assertSame(ShakeAccessDescriber.PACKAGE, node.access)
        assertSame(ShakeClassType.INTERFACE, node.type)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ShakeClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(ShakeAccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isStatic)
        assertTrue(aclass.isFinal)
    }
}
