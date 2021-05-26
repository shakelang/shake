package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import com.github.nsc.de.shake.parser.node.AccessDescriber
import com.github.nsc.de.shake.parser.node.VariableType
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ClassValueTests {
    @Test
    fun testFinalClass() {
        val tree = ParserTestUtil.parse("<ClassTest>", "final class test {}")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertTrue(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
    }

    @Test
    fun testClass() {
        val tree = ParserTestUtil.parse("<ClassTest>", "class test {}")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
    }

    @Test
    fun testPublicClass() {
        val tree = ParserTestUtil.parse("<PublicClassTest>", "public class test {}")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
    }

    @Test
    fun testProtectedClass() {
        val tree = ParserTestUtil.parse("<ProtectedClassTest>", "protected class test {}")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
    }

    @Test
    fun testPrivateClass() {
        val tree = ParserTestUtil.parse("<PrivateClassTest>", "private class test {}")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
    }

    @Test
    fun testClassField1() {
        val tree = ParserTestUtil.parse("<ClassField1Test>", "class test { var i = 0; }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(1, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        val variable = node.fields[0]
        Assertions.assertSame(VariableType.Type.DYNAMIC, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertFalse(variable.isStatic)
        Assertions.assertFalse(variable.isFinal)
    }

    @Test
    fun testClassField2() {
        val tree = ParserTestUtil.parse("<ClassField2Test>", "class test { int i = 0; }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(1, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        val variable = node.fields[0]
        Assertions.assertSame(VariableType.Type.INTEGER, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertFalse(variable.isStatic)
        Assertions.assertFalse(variable.isFinal)
    }

    @Test
    fun testClassMultiField() {
        val tree = ParserTestUtil.parse("<ClassMultiFieldTest>", "class test { int i = 0; int i2 = 0; }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(2, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        var variable = node.fields[0]
        Assertions.assertSame(VariableType.Type.INTEGER, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertFalse(variable.isStatic)
        Assertions.assertFalse(variable.isFinal)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        variable = node.fields[1]
        Assertions.assertSame(VariableType.Type.INTEGER, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i2", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertFalse(variable.isStatic)
        Assertions.assertFalse(variable.isFinal)
    }

    @Test
    fun testClassStaticField() {
        val tree = ParserTestUtil.parse("<ClassStaticFieldTest>", "class test { static int i = 0; }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(1, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        val variable = node.fields[0]
        Assertions.assertSame(VariableType.Type.INTEGER, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertTrue(variable.isStatic)
        Assertions.assertFalse(variable.isFinal)
    }

    @Test
    fun testClassFinalField() {
        val tree = ParserTestUtil.parse("<ClassFinalFieldTest>", "class test { final int i = 0; }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(1, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        val variable = node.fields[0]
        Assertions.assertSame(VariableType.Type.INTEGER, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertFalse(variable.isStatic)
        Assertions.assertTrue(variable.isFinal)
    }

    @Test
    fun testClassStaticFinalField() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalFieldTest>", "class test { static final int i = 0; }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(1, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(VariableDeclarationNode::class.java, node.fields[0])
        val variable = node.fields[0]
        Assertions.assertSame(VariableType.Type.INTEGER, variable.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, variable.access)
        Assertions.assertNotNull(variable.assignment)
        Assertions.assertEquals("i", variable.name)
        Assertions.assertTrue(variable.isInClass)
        Assertions.assertTrue(variable.isStatic)
        Assertions.assertTrue(variable.isFinal)
    }

    @Test
    fun testClassFunction() {
        val tree = ParserTestUtil.parse("<ClassFunctionTest>", "class test { function f() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(1, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class.java, node.methods[0])
        val function = node.methods[0]
        Assertions.assertSame(VariableType.Type.DYNAMIC, function.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, function.access)
        Assertions.assertSame(0, function.args.size)
        Assertions.assertEquals("f", function.name)
        Assertions.assertTrue(function.isInClass)
        Assertions.assertFalse(function.isStatic)
        Assertions.assertFalse(function.isFinal)
    }

    @Test
    fun testClassMultiFunction() {
        val tree = ParserTestUtil.parse("<ClassMultiFunctionTest>", "class test { function f() {}\nfunction g() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(2, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class.java, node.methods[0])
        var function = node.methods[0]
        Assertions.assertSame(VariableType.Type.DYNAMIC, function.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, function.access)
        Assertions.assertSame(0, function.args.size)
        Assertions.assertEquals("f", function.name)
        Assertions.assertTrue(function.isInClass)
        Assertions.assertFalse(function.isStatic)
        Assertions.assertFalse(function.isFinal)
        assertType(FunctionDeclarationNode::class.java, node.methods[1])
        function = node.methods[1]
        Assertions.assertSame(VariableType.Type.DYNAMIC, function.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, function.access)
        Assertions.assertSame(0, function.args.size)
        Assertions.assertEquals("g", function.name)
        Assertions.assertTrue(function.isInClass)
        Assertions.assertFalse(function.isStatic)
        Assertions.assertFalse(function.isFinal)
    }

    @Test
    fun testClassStaticFunction() {
        val tree = ParserTestUtil.parse("<ClassStaticFunctionTest>", "class test { static function f() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(1, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class.java, node.methods[0])
        val function = node.methods[0]
        Assertions.assertSame(VariableType.Type.DYNAMIC, function.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, function.access)
        Assertions.assertSame(0, function.args.size)
        Assertions.assertEquals("f", function.name)
        Assertions.assertTrue(function.isInClass)
        Assertions.assertTrue(function.isStatic)
        Assertions.assertFalse(function.isFinal)
    }

    @Test
    fun testClassFinalFunction() {
        val tree = ParserTestUtil.parse("<ClassFinalFunctionTest>", "class test { final function f() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(1, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class.java, node.methods[0])
        val function = node.methods[0]
        Assertions.assertSame(VariableType.Type.DYNAMIC, function.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, function.access)
        Assertions.assertSame(0, function.args.size)
        Assertions.assertEquals("f", function.name)
        Assertions.assertTrue(function.isInClass)
        Assertions.assertFalse(function.isStatic)
        Assertions.assertTrue(function.isFinal)
    }

    @Test
    fun testClassStaticFinalFunction() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalFunctionTest>", "class test { static final function f() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(1, node.methods.size)
        Assertions.assertSame(0, node.classes.size)
        assertType(FunctionDeclarationNode::class.java, node.methods[0])
        val function = node.methods[0]
        Assertions.assertSame(VariableType.Type.DYNAMIC, function.type.type)
        Assertions.assertSame(AccessDescriber.PACKAGE, function.access)
        Assertions.assertSame(0, function.args.size)
        Assertions.assertEquals("f", function.name)
        Assertions.assertTrue(function.isInClass)
        Assertions.assertTrue(function.isStatic)
        Assertions.assertTrue(function.isFinal)
    }

    @Test
    fun testClassClass() {
        val tree = ParserTestUtil.parse("<ClassClassTest>", "class test { class c {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class.java, node.classes[0])
        val aclass = node.classes[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, aclass.access)
        Assertions.assertSame(0, aclass.classes.size)
        Assertions.assertSame(0, aclass.methods.size)
        Assertions.assertSame(0, aclass.fields.size)
        Assertions.assertEquals("c", aclass.name)
        Assertions.assertTrue(aclass.isInClass)
        Assertions.assertFalse(aclass.isStatic)
        Assertions.assertFalse(aclass.isFinal)
    }

    @Test
    fun testClassMultiClass() {
        val tree = ParserTestUtil.parse("<ClassMultiClassTest>", "class test { class c {} class c2 {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(2, node.classes.size)
        assertType(ClassDeclarationNode::class.java, node.classes[0])
        var aclass = node.classes[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, aclass.access)
        Assertions.assertSame(0, aclass.classes.size)
        Assertions.assertSame(0, aclass.methods.size)
        Assertions.assertSame(0, aclass.fields.size)
        Assertions.assertEquals("c", aclass.name)
        Assertions.assertTrue(aclass.isInClass)
        Assertions.assertFalse(aclass.isStatic)
        Assertions.assertFalse(aclass.isFinal)
        assertType(ClassDeclarationNode::class.java, node.classes[1])
        aclass = node.classes[1]
        Assertions.assertSame(AccessDescriber.PACKAGE, aclass.access)
        Assertions.assertSame(0, aclass.classes.size)
        Assertions.assertSame(0, aclass.methods.size)
        Assertions.assertSame(0, aclass.fields.size)
        Assertions.assertEquals("c2", aclass.name)
        Assertions.assertTrue(aclass.isInClass)
        Assertions.assertFalse(aclass.isStatic)
        Assertions.assertFalse(aclass.isFinal)
    }

    @Test
    fun testStaticClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticClassTest>", "class test { static class c {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class.java, node.classes[0])
        val aclass = node.classes[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, aclass.access)
        Assertions.assertSame(0, aclass.classes.size)
        Assertions.assertSame(0, aclass.methods.size)
        Assertions.assertSame(0, aclass.fields.size)
        Assertions.assertEquals("c", aclass.name)
        Assertions.assertTrue(aclass.isInClass)
        Assertions.assertTrue(aclass.isStatic)
        Assertions.assertFalse(aclass.isFinal)
    }

    @Test
    fun testFinalClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalClassTest>", "class test { final class c {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class.java, node.classes[0])
        val aclass = node.classes[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, aclass.access)
        Assertions.assertSame(0, aclass.classes.size)
        Assertions.assertSame(0, aclass.methods.size)
        Assertions.assertSame(0, aclass.fields.size)
        Assertions.assertEquals("c", aclass.name)
        Assertions.assertTrue(aclass.isInClass)
        Assertions.assertFalse(aclass.isStatic)
        Assertions.assertTrue(aclass.isFinal)
    }

    @Test
    fun testStaticFinalClassClass() {
        val tree = ParserTestUtil.parse("<ClassStaticFinalClassTest>", "class test { static final class c {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val node = tree.children[0] as ClassDeclarationNode
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertFalse(node.isInClass)
        Assertions.assertFalse(node.isStatic)
        Assertions.assertFalse(node.isFinal)
        Assertions.assertEquals("test", node.name)
        Assertions.assertSame(0, node.fields.size)
        Assertions.assertSame(0, node.methods.size)
        Assertions.assertSame(1, node.classes.size)
        assertType(ClassDeclarationNode::class.java, node.classes[0])
        val aclass = node.classes[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, aclass.access)
        Assertions.assertSame(0, aclass.classes.size)
        Assertions.assertSame(0, aclass.methods.size)
        Assertions.assertSame(0, aclass.fields.size)
        Assertions.assertEquals("c", aclass.name)
        Assertions.assertTrue(aclass.isInClass)
        Assertions.assertTrue(aclass.isStatic)
        Assertions.assertTrue(aclass.isFinal)
    }
}