package com.github.nsc.de.shake.parser;

import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.functions.ReturnNode;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.shake.TestUtil.assertType;
import static com.github.nsc.de.shake.parser.ParserTestUtil.parse;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTests {

    @Test
    public void testFunction() {

        Tree tree = parse("<FunctionTest>", "function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testFunctionChildren() {

        Tree tree = parse("<FunctionChildrenTest>", "function f() { print(10); }");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(1, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testPublicFunction() {

        Tree tree = parse("<PublicFunctionTest>", "public function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testProtectedFunction() {

        Tree tree = parse("<ProtectedFunctionTest>", "protected function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testPrivateFunction() {

        Tree tree = parse("<PrivateFunctionTest>", "private function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testFinalFunction() {

        Tree tree = parse("<FinalFunctionTest>", "final function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testPublicFinalFunction() {

        Tree tree = parse("<PublicFinalFunctionTest>", "public final function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testProtectedFinalFunction() {

        Tree tree = parse("<ProtectedFinalFunctionTest>", "protected final function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testPrivateFinalFunction() {

        Tree tree = parse("<PrivateFinalFunctionTest>", "private final function f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testReturn() {

        Tree tree = parse("<FunctionTest>", "return 10;");

        assertEquals(1, tree.getChildren().length);
        assertType(ReturnNode.class, tree.getChildren()[0]);

        ReturnNode node = (ReturnNode) tree.getChildren()[0];
        assertType(IntegerNode.class, node.getValue());

    }

}
