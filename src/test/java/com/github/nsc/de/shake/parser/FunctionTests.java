package com.github.nsc.de.shake.parser;

import com.github.nsc.de.shake.parser.node.AccessDescriber;
import com.github.nsc.de.shake.parser.node.Tree;
import com.github.nsc.de.shake.parser.node.VariableType;
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
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
        assertSame(VariableType.DYNAMIC, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }






    // ********************************************************************
    // C-Style



    @Test
    public void testCStyleFunction() {

        Tree tree = parse("<CStyleFunctionTest>", "int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionChildren() {

        Tree tree = parse("<CStyleFunctionChildrenTest>", "int f() { print(10); }");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(1, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStylePublicFunction() {

        Tree tree = parse("<CStylePublicFunctionTest>", "public int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleProtectedFunction() {

        Tree tree = parse("<CStyleProtectedFunctionTest>", "protected int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStylePrivateFunction() {

        Tree tree = parse("<CStylePrivateFunctionTest>", "private int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFinalFunction() {

        Tree tree = parse("<CStyleFinalFunctionTest>", "final int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testCStylePublicFinalFunction() {

        Tree tree = parse("<CStylePublicFinalFunctionTest>", "public final int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testCStyleProtectedFinalFunction() {

        Tree tree = parse("<CStyleProtectedFinalFunctionTest>", "protected final int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testCStylePrivateFinalFunction() {

        Tree tree = parse("<CStylePrivateFinalFunctionTest>", "private final int f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.INTEGER, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());

    }

    @Test
    public void testCStyleFunctionByte() {

        Tree tree = parse("<CStyleFunctionByteTest>", "byte f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.BYTE, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionShort() {

        Tree tree = parse("<CStyleFunctionShortTest>", "short f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.SHORT, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionLong() {

        Tree tree = parse("<CStyleFunctionLongTest>", "long f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.LONG, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionFloat() {

        Tree tree = parse("<CStyleFunctionFloatTest>", "float f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.FLOAT, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionDouble() {

        Tree tree = parse("<CStyleFunctionDoubleTest>", "double f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.DOUBLE, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionChar() {

        Tree tree = parse("<CStyleFunctionCharTest>", "char f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.CHAR, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionBoolean() {

        Tree tree = parse("<CStyleFunctionBooleanTest>", "boolean f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.BOOLEAN, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

    }

    @Test
    public void testCStyleFunctionVoid() {

        Tree tree = parse("<CStyleFunctionVoidTest>", "void f() {}");

        assertEquals(1, tree.getChildren().length);
        assertType(FunctionDeclarationNode.class, tree.getChildren()[0]);

        FunctionDeclarationNode node = (FunctionDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertEquals("f", node.getName());
        assertSame(0, node.getBody().getChildren().length);
        assertSame(VariableType.VOID, node.getType());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());

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
