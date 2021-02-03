package com.github.nsc.de.shake.parser;

import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.functions.ReturnNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.objects.ConstructorDeclarationNode;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.shake.TestUtil.assertType;
import static com.github.nsc.de.shake.parser.ParserTestUtil.parse;
import static org.junit.jupiter.api.Assertions.*;

public class ConstructorTests {

    @Test
    public void testConstructorType1() {

        Tree tree = parse("<ConstructorTest>", "class TestClass { constructor() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode classNode = (ClassDeclarationNode) tree.getChildren()[0];
        assertEquals("TestClass", classNode.getName());
        assertFalse(classNode.isFinal());
        assertFalse(classNode.isStatic());
        assertFalse(classNode.isInClass());
        assertSame(0, classNode.getClasses().length);
        assertSame(0, classNode.getFields().length);
        assertSame(0, classNode.getMethods().length);
        assertSame(1, classNode.getConstructors().length);

        ConstructorDeclarationNode node = classNode.getConstructors()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertNull(node.getName());
        assertSame(0, node.getBody().getChildren().length);

    }

    @Test
    public void testConstructorType1Children() {

        Tree tree = parse("<ConstructorChildrenTest>", "class TestClass { constructor() { print(10); } }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode classNode = (ClassDeclarationNode) tree.getChildren()[0];
        assertEquals("TestClass", classNode.getName());
        assertFalse(classNode.isFinal());
        assertFalse(classNode.isStatic());
        assertFalse(classNode.isInClass());
        assertSame(0, classNode.getClasses().length);
        assertSame(0, classNode.getFields().length);
        assertSame(0, classNode.getMethods().length);
        assertSame(1, classNode.getConstructors().length);

        ConstructorDeclarationNode node = classNode.getConstructors()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertNull(node.getName());
        assertSame(1, node.getBody().getChildren().length);

    }

    @Test
    public void testPublicFunction() {

        Tree tree = parse("<PublicConstructorTest>", "class TestClass { public constructor() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode classNode = (ClassDeclarationNode) tree.getChildren()[0];
        assertEquals("TestClass", classNode.getName());
        assertFalse(classNode.isFinal());
        assertFalse(classNode.isStatic());
        assertFalse(classNode.isInClass());
        assertSame(0, classNode.getClasses().length);
        assertSame(0, classNode.getFields().length);
        assertSame(0, classNode.getMethods().length);
        assertSame(1, classNode.getConstructors().length);

        ConstructorDeclarationNode node = classNode.getConstructors()[0];
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertNull(node.getName());
        assertSame(0, node.getBody().getChildren().length);

    }

    @Test
    public void testProtectedConstructor() {

        Tree tree = parse("<ProtectedConstructorTest>", "class TestClass { protected constructor() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode classNode = (ClassDeclarationNode) tree.getChildren()[0];
        assertEquals("TestClass", classNode.getName());
        assertFalse(classNode.isFinal());
        assertFalse(classNode.isStatic());
        assertFalse(classNode.isInClass());
        assertSame(0, classNode.getClasses().length);
        assertSame(0, classNode.getFields().length);
        assertSame(0, classNode.getMethods().length);
        assertSame(1, classNode.getConstructors().length);

        ConstructorDeclarationNode node = classNode.getConstructors()[0];
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertNull(node.getName());
        assertSame(0, node.getBody().getChildren().length);

    }

    @Test
    public void testPrivateFunction() {

        Tree tree = parse("<PrivateConstructorTest>", "class TestClass { private constructor() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode classNode = (ClassDeclarationNode) tree.getChildren()[0];
        assertEquals("TestClass", classNode.getName());
        assertFalse(classNode.isFinal());
        assertFalse(classNode.isStatic());
        assertFalse(classNode.isInClass());
        assertSame(0, classNode.getClasses().length);
        assertSame(0, classNode.getFields().length);
        assertSame(0, classNode.getMethods().length);
        assertSame(1, classNode.getConstructors().length);

        ConstructorDeclarationNode node = classNode.getConstructors()[0];
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertNull(node.getName());
        assertSame(0, node.getBody().getChildren().length);

    }

}
