package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.assertType
import org.junit.jupiter.api.Test
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode
import com.github.nsc.de.shake.parser.node.AccessDescriber
import org.junit.jupiter.api.Assertions

class ConstructorTests {
    @Test
    fun testConstructorType1() {
        val tree = ParserTestUtil.parse("<ConstructorTest>", "class TestClass { constructor() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertNull(node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testConstructorType1Children() {
        val tree = ParserTestUtil.parse("<ConstructorChildrenTest>", "class TestClass { constructor() { print(10); } }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertNull(node.name)
        Assertions.assertSame(1, node.body.children.size)
    }

    @Test
    fun testPublicConstructorType1() {
        val tree = ParserTestUtil.parse("<PublicConstructorTest>", "class TestClass { public constructor() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertNull(node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testProtectedConstructorType1() {
        val tree = ParserTestUtil.parse("<ProtectedConstructorTest>", "class TestClass { protected constructor() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertNull(node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testPrivateConstructorType1() {
        val tree = ParserTestUtil.parse("<PrivateConstructorTest>", "class TestClass { private constructor() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertNull(node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedConstructorType1() {
        val tree = ParserTestUtil.parse("<NamedConstructorTest>", "class TestClass { constructor MyConstructor() {} }")
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertEquals("MyConstructor", node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedConstructorType1Children() {
        val tree = ParserTestUtil.parse(
            "<NamedConstructorChildrenTest>",
            "class TestClass { constructor MyConstructor() { print(10); } }"
        )
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PACKAGE, node.access)
        Assertions.assertEquals("MyConstructor", node.name)
        Assertions.assertSame(1, node.body.children.size)
    }

    @Test
    fun testNamedPublicConstructorType1() {
        val tree = ParserTestUtil.parse(
            "<NamedPublicConstructorTest>",
            "class TestClass { public constructor MyConstructor() {} }"
        )
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PUBLIC, node.access)
        Assertions.assertEquals("MyConstructor", node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedProtectedConstructorType1() {
        val tree = ParserTestUtil.parse(
            "<NamedProtectedConstructorTest>",
            "class TestClass { protected constructor MyConstructor() {} }"
        )
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PROTECTED, node.access)
        Assertions.assertEquals("MyConstructor", node.name)
        Assertions.assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedPrivateConstructorType1() {
        val tree = ParserTestUtil.parse(
            "<NamedPrivateConstructorTest>",
            "class TestClass { private constructor MyConstructor() {} }"
        )
        Assertions.assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class.java, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        Assertions.assertEquals("TestClass", classNode.name)
        Assertions.assertFalse(classNode.isFinal)
        Assertions.assertFalse(classNode.isStatic)
        Assertions.assertFalse(classNode.isInClass)
        Assertions.assertSame(0, classNode.classes.size)
        Assertions.assertSame(0, classNode.fields.size)
        Assertions.assertSame(0, classNode.methods.size)
        Assertions.assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        Assertions.assertSame(AccessDescriber.PRIVATE, node.access)
        Assertions.assertEquals("MyConstructor", node.name)
        Assertions.assertSame(0, node.body.children.size)
    }
}