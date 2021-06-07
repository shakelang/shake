package com.github.shakelang.shake.parser

import com.github.shakelang.shake.assertType
import com.github.shakelang.shake.parser.node.AccessDescriber
import com.github.shakelang.shake.parser.node.objects.ClassDeclarationNode
import kotlin.test.*

class ConstructorTests {
    @Test
    fun testConstructorType1() {
        val tree = ParserTestUtil.parse("<ConstructorTest>", "class TestClass { constructor() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertNull(node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testConstructorType1Children() {
        val tree = ParserTestUtil.parse("<ConstructorChildrenTest>", "class TestClass { constructor() { print(10); } }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertNull(node.name)
        assertSame(1, node.body.children.size)
    }

    @Test
    fun testPublicConstructorType1() {
        val tree = ParserTestUtil.parse("<PublicConstructorTest>", "class TestClass { public constructor() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertNull(node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testProtectedConstructorType1() {
        val tree = ParserTestUtil.parse("<ProtectedConstructorTest>", "class TestClass { protected constructor() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertNull(node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testPrivateConstructorType1() {
        val tree = ParserTestUtil.parse("<PrivateConstructorTest>", "class TestClass { private constructor() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertNull(node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedConstructorType1() {
        val tree = ParserTestUtil.parse("<NamedConstructorTest>", "class TestClass { constructor MyConstructor() {} }")
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("MyConstructor", node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedConstructorType1Children() {
        val tree = ParserTestUtil.parse(
            "<NamedConstructorChildrenTest>",
            "class TestClass { constructor MyConstructor() { print(10); } }"
        )
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PACKAGE, node.access)
        assertEquals("MyConstructor", node.name)
        assertSame(1, node.body.children.size)
    }

    @Test
    fun testNamedPublicConstructorType1() {
        val tree = ParserTestUtil.parse(
            "<NamedPublicConstructorTest>",
            "class TestClass { public constructor MyConstructor() {} }"
        )
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PUBLIC, node.access)
        assertEquals("MyConstructor", node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedProtectedConstructorType1() {
        val tree = ParserTestUtil.parse(
            "<NamedProtectedConstructorTest>",
            "class TestClass { protected constructor MyConstructor() {} }"
        )
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PROTECTED, node.access)
        assertEquals("MyConstructor", node.name)
        assertSame(0, node.body.children.size)
    }

    @Test
    fun testNamedPrivateConstructorType1() {
        val tree = ParserTestUtil.parse(
            "<NamedPrivateConstructorTest>",
            "class TestClass { private constructor MyConstructor() {} }"
        )
        assertEquals(1, tree.children.size)
        assertType(ClassDeclarationNode::class, tree.children[0])
        val classNode = tree.children[0] as ClassDeclarationNode
        assertEquals("TestClass", classNode.name)
        assertFalse(classNode.isFinal)
        assertFalse(classNode.isStatic)
        assertFalse(classNode.isInClass)
        assertSame(0, classNode.classes.size)
        assertSame(0, classNode.fields.size)
        assertSame(0, classNode.methods.size)
        assertSame(1, classNode.constructors.size)
        val node = classNode.constructors[0]
        assertSame(AccessDescriber.PRIVATE, node.access)
        assertEquals("MyConstructor", node.name)
        assertSame(0, node.body.children.size)
    }
}