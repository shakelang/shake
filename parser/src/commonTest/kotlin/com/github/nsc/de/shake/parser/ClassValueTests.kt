package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.VariableType
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode
import kotlin.test.*

class ClassValueTests {
    @Test
    fun testFinalClass() {
        val tree = ParserTestUtil.parse("<ClassTest>", "final class test {}")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertTrue(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testClass() {
        val tree = ParserTestUtil.parse("<ClassTest>", "class test {}")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testPublicClass() {
        val tree = ParserTestUtil.parse("<PublicClassTest>", "public class test {}")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testProtectedClass() {
        val tree = ParserTestUtil.parse("<ProtectedClassTest>", "protected class test {}")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testPrivateClass() {
        val tree = ParserTestUtil.parse("<PrivateClassTest>", "private class test {}")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
    }

    @Test
    fun testClassField1() {
        val tree = ParserTestUtil.parse("<ClassField1Test>", "class test { var i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(VariableType.Type.DYNAMIC, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i", variable.name)
        assertTrue(variable.isInClass)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassField2() {
        val tree = ParserTestUtil.parse("<ClassField2Test>", "class test { int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(VariableType.Type.INTEGER, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i", variable.name)
        assertTrue(variable.isInClass)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassMultiField() {
        val tree = ParserTestUtil.parse("<ClassMultiFieldTest>", "class test { int i = 0; int i2 = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(2, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class, node.fields[0])
        var variable = node.fields[0]
        assertSame(VariableType.Type.INTEGER, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i", variable.name)
        assertTrue(variable.isInClass)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
        assertType(VariableDeclarationNode::class, node.fields[0])
        variable = node.fields[1]
        assertSame(VariableType.Type.INTEGER, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i2", variable.name)
        assertTrue(variable.isInClass)
        assertFalse(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassStaticField() {
        val tree = ParserTestUtil.parse("<ClassStaticFieldTest>", "class test { static int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(VariableType.Type.INTEGER, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i", variable.name)
        assertTrue(variable.isInClass)
        assertTrue(variable.isStatic)
        assertFalse(variable.isFinal)
    }

    @Test
    fun testClassFinalField() {
        val tree = ParserTestUtil.parse("<ClassFinalFieldTest>", "class test { final int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(VariableType.Type.INTEGER, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i", variable.name)
        assertTrue(variable.isInClass)
        assertFalse(variable.isStatic)
        assertTrue(variable.isFinal)
    }

    @Test
    fun testClassStaticFinalField() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalFieldTest>", "class test { static final int i = 0; }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(1, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class, node.fields[0])
        val variable = node.fields[0]
        assertSame(VariableType.Type.INTEGER, variable.type.type)
        assertSame(AccessDescriber.PACKAGE, variable.access)
        assertNotNull(variable.assignment)
        assertEquals("i", variable.name)
        assertTrue(variable.isInClass)
        assertTrue(variable.isStatic)
        assertTrue(variable.isFinal)
    }

    @Test
    fun testClassFunction() {
        val tree = ParserTestUtil.parse("<ClassFunctionTest>", "class test { function f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(VariableType.Type.DYNAMIC, function.type.type)
        assertSame(AccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isInClass)
        assertFalse(function.isStatic)
        assertFalse(function.isFinal)
    }

    @Test
    fun testClassMultiFunction() {
        val tree = ParserTestUtil.parse("<ClassMultiFunctionTest>", "class test { function f() {}\nfunction g() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(2, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class, node.methods[0])
        var function = node.methods[0]
        assertSame(VariableType.Type.DYNAMIC, function.type.type)
        assertSame(AccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isInClass)
        assertFalse(function.isStatic)
        assertFalse(function.isFinal)
        assertType(FunctionDeclarationNode::class, node.methods[1])
        function = node.methods[1]
        assertSame(VariableType.Type.DYNAMIC, function.type.type)
        assertSame(AccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("g", function.name)
        assertTrue(function.isInClass)
        assertFalse(function.isStatic)
        assertFalse(function.isFinal)
    }

    @Test
    fun testClassStaticFunction() {
        val tree = ParserTestUtil.parse("<ClassStaticFunctionTest>", "class test { static function f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(VariableType.Type.DYNAMIC, function.type.type)
        assertSame(AccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isInClass)
        assertTrue(function.isStatic)
        assertFalse(function.isFinal)
    }

    @Test
    fun testClassFinalFunction() {
        val tree = ParserTestUtil.parse("<ClassFinalFunctionTest>", "class test { final function f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(VariableType.Type.DYNAMIC, function.type.type)
        assertSame(AccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isInClass)
        assertFalse(function.isStatic)
        assertTrue(function.isFinal)
    }

    @Test
    fun testClassStaticFinalFunction() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalFunctionTest>", "class test { static final function f() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(1, node.methods.size)
        assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class, node.methods[0])
        val function = node.methods[0]
        assertSame(VariableType.Type.DYNAMIC, function.type.type)
        assertSame(AccessDescriber.PACKAGE, function.access)
        assertSame(0, function.args.size)
        assertEquals("f", function.name)
        assertTrue(function.isInClass)
        assertTrue(function.isStatic)
        assertTrue(function.isFinal)
    }

    @Test
    fun testClassClass() {
        val tree = ParserTestUtil.parse("<ClassClassTest>", "class test { class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(AccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isInClass)
        assertFalse(aclass.isStatic)
        assertFalse(aclass.isFinal)
    }

    @Test
    fun testClassMultiClass() {
        val tree = ParserTestUtil.parse("<ClassMultiClassTest>", "class test { class c {} class c2 {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(2, node.classes.size)
        assertType(ClassDeclarationNode::class, node.classes[0])
        var aclass = node.classes[0]
        assertSame(AccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isInClass)
        assertFalse(aclass.isStatic)
        assertFalse(aclass.isFinal)
        assertType(ClassDeclarationNode::class, node.classes[1])
        aclass = node.classes[1]
        assertSame(AccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c2", aclass.name)
        assertTrue(aclass.isInClass)
        assertFalse(aclass.isStatic)
        assertFalse(aclass.isFinal)
    }

    @Test
    fun testStaticClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticClassTest>", "class test { static class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(AccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isInClass)
        assertTrue(aclass.isStatic)
        assertFalse(aclass.isFinal)
    }

    @Test
    fun testFinalClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalClassTest>", "class test { final class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(AccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isInClass)
        assertFalse(aclass.isStatic)
        assertTrue(aclass.isFinal)
    }

    @Test
    fun testStaticFinalClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalClassTest>", "class test { static final class c {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertFalse(node.isInClass)
        assertFalse(node.isStatic)
        assertFalse(node.isFinal)
        assertEquals("test", node.name)
        assertSame(0, node.fields.size)
        assertSame(0, node.methods.size)
        assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class, node.classes[0])
        val aclass = node.classes[0]
        assertSame(AccessDescriber.PACKAGE, aclass.access)
        assertSame(0, aclass.classes.size)
        assertSame(0, aclass.methods.size)
        assertSame(0, aclass.fields.size)
        assertEquals("c", aclass.name)
        assertTrue(aclass.isInClass)
        assertTrue(aclass.isStatic)
        assertTrue(aclass.isFinal)
    }
}