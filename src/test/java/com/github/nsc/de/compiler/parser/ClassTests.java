package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.IfNode;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.VariableType;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalSmallerNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import com.github.nsc.de.compiler.parser.node.loops.DoWhileNode;
import com.github.nsc.de.compiler.parser.node.loops.ForNode;
import com.github.nsc.de.compiler.parser.node.loops.WhileNode;
import com.github.nsc.de.compiler.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableIncreaseNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableUsageNode;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.TestUtil.assertType;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.parse;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.parseSingle;
import static org.junit.jupiter.api.Assertions.*;

public class ClassTests {

    @Test
    public void testFinalClass() {

        Tree tree = parse("<ClassTest>", "final class test {}");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

    }

    @Test
    public void testClass() {

        Tree tree = parse("<ClassTest>", "class test {}");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

    }

    @Test
    public void testPublicClass() {

        Tree tree = parse("<PublicClassTest>", "public class test {}");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

    }

    @Test
    public void testProtectedClass() {

        Tree tree = parse("<ProtectedClassTest>", "protected class test {}");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

    }

    @Test
    public void testPrivateClass() {

        Tree tree = parse("<PrivateClassTest>", "private class test {}");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

    }

    @Test
    public void testClassField1() {

        Tree tree = parse("<ClassField1Test>", "class test { var i = 0; }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(1, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        VariableDeclarationNode var = node.getFields()[0];

        assertSame(VariableType.Type.DYNAMIC, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i", var.getName());
        assertTrue(var.isInClass());
        assertFalse(var.isStatic());
        assertFalse(var.isFinal());

    }

    @Test
    public void testClassField2() {

        Tree tree = parse("<ClassField2Test>", "class test { int i = 0; }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(1, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        VariableDeclarationNode var = node.getFields()[0];

        assertSame(VariableType.Type.INTEGER, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i", var.getName());
        assertTrue(var.isInClass());
        assertFalse(var.isStatic());
        assertFalse(var.isFinal());

    }

    @Test
    public void testClassMultiField() {

        Tree tree = parse("<ClassMultiFieldTest>", "class test { int i = 0; int i2 = 0; }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(2, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        VariableDeclarationNode var = node.getFields()[0];

        assertSame(VariableType.Type.INTEGER, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i", var.getName());
        assertTrue(var.isInClass());
        assertFalse(var.isStatic());
        assertFalse(var.isFinal());

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        var = node.getFields()[1];

        assertSame(VariableType.Type.INTEGER, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i2", var.getName());
        assertTrue(var.isInClass());
        assertFalse(var.isStatic());
        assertFalse(var.isFinal());

    }

    @Test
    public void testClassStaticField() {

        Tree tree = parse("<ClassStaticFieldTest>", "class test { static int i = 0; }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(1, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        VariableDeclarationNode var = node.getFields()[0];

        assertSame(VariableType.Type.INTEGER, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i", var.getName());
        assertTrue(var.isInClass());
        assertTrue(var.isStatic());
        assertFalse(var.isFinal());

    }

    @Test
    public void testClassFinalField() {

        Tree tree = parse("<ClassFinalFieldTest>", "class test { final int i = 0; }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(1, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        VariableDeclarationNode var = node.getFields()[0];

        assertSame(VariableType.Type.INTEGER, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i", var.getName());
        assertTrue(var.isInClass());
        assertFalse(var.isStatic());
        assertTrue(var.isFinal());

    }

    @Test
    public void testClassStaticFinalField() {

        Tree tree = parse("<ClassStaticFinalFieldTest>", "class test { static final int i = 0; }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(1, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(VariableDeclarationNode.class, node.getFields()[0]);
        VariableDeclarationNode var = node.getFields()[0];

        assertSame(VariableType.Type.INTEGER, var.getType().getType());
        assertSame(AccessDescriber.PACKAGE, var.getAccess());
        assertNotNull(var.getAssignment());
        assertEquals("i", var.getName());
        assertTrue(var.isInClass());
        assertTrue(var.isStatic());
        assertTrue(var.isFinal());

    }

    @Test
    public void testClassFunction() {

        Tree tree = parse("<ClassFunctionTest>", "class test { function f() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(1, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(FunctionDeclarationNode.class, node.getMethods()[0]);
        FunctionDeclarationNode function = node.getMethods()[0];

        assertSame(VariableType.Type.DYNAMIC, function.getType().getType());
        assertSame(AccessDescriber.PACKAGE, function.getAccess());
        assertSame(0, function.getArgs().length);
        assertEquals("f", function.getName());
        assertTrue(function.isInClass());
        assertFalse(function.isStatic());
        assertFalse(function.isFinal());

    }

    @Test
    public void testClassMultiFunction() {

        Tree tree = parse("<ClassMultiFunctionTest>", "class test { function f() {}\nfunction g() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(2, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(FunctionDeclarationNode.class, node.getMethods()[0]);
        FunctionDeclarationNode function = node.getMethods()[0];

        assertSame(VariableType.Type.DYNAMIC, function.getType().getType());
        assertSame(AccessDescriber.PACKAGE, function.getAccess());
        assertSame(0, function.getArgs().length);
        assertEquals("f", function.getName());
        assertTrue(function.isInClass());
        assertFalse(function.isStatic());
        assertFalse(function.isFinal());

        assertType(FunctionDeclarationNode.class, node.getMethods()[1]);
        function = node.getMethods()[1];

        assertSame(VariableType.Type.DYNAMIC, function.getType().getType());
        assertSame(AccessDescriber.PACKAGE, function.getAccess());
        assertSame(0, function.getArgs().length);
        assertEquals("g", function.getName());
        assertTrue(function.isInClass());
        assertFalse(function.isStatic());
        assertFalse(function.isFinal());

    }

    @Test
    public void testClassStaticFunction() {

        Tree tree = parse("<ClassStaticFunctionTest>", "class test { static function f() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(1, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(FunctionDeclarationNode.class, node.getMethods()[0]);
        FunctionDeclarationNode function = node.getMethods()[0];

        assertSame(VariableType.Type.DYNAMIC, function.getType().getType());
        assertSame(AccessDescriber.PACKAGE, function.getAccess());
        assertSame(0, function.getArgs().length);
        assertEquals("f", function.getName());
        assertTrue(function.isInClass());
        assertTrue(function.isStatic());
        assertFalse(function.isFinal());

    }

    @Test
    public void testClassFinalFunction() {

        Tree tree = parse("<ClassFinalFunctionTest>", "class test { final function f() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(1, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(FunctionDeclarationNode.class, node.getMethods()[0]);
        FunctionDeclarationNode function = node.getMethods()[0];

        assertSame(VariableType.Type.DYNAMIC, function.getType().getType());
        assertSame(AccessDescriber.PACKAGE, function.getAccess());
        assertSame(0, function.getArgs().length);
        assertEquals("f", function.getName());
        assertTrue(function.isInClass());
        assertFalse(function.isStatic());
        assertTrue(function.isFinal());

    }

    @Test
    public void testClassStaticFinalFunction() {

        Tree tree = parse("<ClassStaticFinalFunctionTest>", "class test { static final function f() {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(1, node.getMethods().length);
        assertSame(0, node.getClasses().length);

        assertType(FunctionDeclarationNode.class, node.getMethods()[0]);
        FunctionDeclarationNode function = node.getMethods()[0];

        assertSame(VariableType.Type.DYNAMIC, function.getType().getType());
        assertSame(AccessDescriber.PACKAGE, function.getAccess());
        assertSame(0, function.getArgs().length);
        assertEquals("f", function.getName());
        assertTrue(function.isInClass());
        assertTrue(function.isStatic());
        assertTrue(function.isFinal());

    }

    @Test
    public void testClassClass() {

        Tree tree = parse("<ClassClassTest>", "class test { class c {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(1, node.getClasses().length);

        assertType(ClassDeclarationNode.class, node.getClasses()[0]);
        ClassDeclarationNode aclass = node.getClasses()[0];

        assertSame(AccessDescriber.PACKAGE, aclass.getAccess());
        assertSame(0, aclass.getClasses().length);
        assertSame(0, aclass.getMethods().length);
        assertSame(0, aclass.getFields().length);
        assertEquals("c", aclass.getName());
        assertTrue(aclass.isInClass());
        assertFalse(aclass.isStatic());
        assertFalse(aclass.isFinal());

    }

    @Test
    public void testClassMultiClass() {

        Tree tree = parse("<ClassMultiClassTest>", "class test { class c {} class c2 {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(2, node.getClasses().length);

        assertType(ClassDeclarationNode.class, node.getClasses()[0]);
        ClassDeclarationNode aclass = node.getClasses()[0];

        assertSame(AccessDescriber.PACKAGE, aclass.getAccess());
        assertSame(0, aclass.getClasses().length);
        assertSame(0, aclass.getMethods().length);
        assertSame(0, aclass.getFields().length);
        assertEquals("c", aclass.getName());
        assertTrue(aclass.isInClass());
        assertFalse(aclass.isStatic());
        assertFalse(aclass.isFinal());

        assertType(ClassDeclarationNode.class, node.getClasses()[1]);
        aclass = node.getClasses()[1];

        assertSame(AccessDescriber.PACKAGE, aclass.getAccess());
        assertSame(0, aclass.getClasses().length);
        assertSame(0, aclass.getMethods().length);
        assertSame(0, aclass.getFields().length);
        assertEquals("c2", aclass.getName());
        assertTrue(aclass.isInClass());
        assertFalse(aclass.isStatic());
        assertFalse(aclass.isFinal());

    }

    @Test
    public void testStaticClassClass() {

        Tree tree = parse("<ClassStaticClassTest>", "class test { static class c {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(1, node.getClasses().length);

        assertType(ClassDeclarationNode.class, node.getClasses()[0]);
        ClassDeclarationNode aclass = node.getClasses()[0];

        assertSame(AccessDescriber.PACKAGE, aclass.getAccess());
        assertSame(0, aclass.getClasses().length);
        assertSame(0, aclass.getMethods().length);
        assertSame(0, aclass.getFields().length);
        assertEquals("c", aclass.getName());
        assertTrue(aclass.isInClass());
        assertTrue(aclass.isStatic());
        assertFalse(aclass.isFinal());

    }

    @Test
    public void testFinalClassClass() {

        Tree tree = parse("<ClassStaticFinalClassTest>", "class test { final class c {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(1, node.getClasses().length);

        assertType(ClassDeclarationNode.class, node.getClasses()[0]);
        ClassDeclarationNode aclass = node.getClasses()[0];

        assertSame(AccessDescriber.PACKAGE, aclass.getAccess());
        assertSame(0, aclass.getClasses().length);
        assertSame(0, aclass.getMethods().length);
        assertSame(0, aclass.getFields().length);
        assertEquals("c", aclass.getName());
        assertTrue(aclass.isInClass());
        assertFalse(aclass.isStatic());
        assertTrue(aclass.isFinal());

    }

    @Test
    public void testStaticFinalClassClass() {

        Tree tree = parse("<ClassStaticFinalClassTest>", "class test { static final class c {} }");

        assertEquals(1, tree.getChildren().length);
        assertType(ClassDeclarationNode.class, tree.getChildren()[0]);

        ClassDeclarationNode node = (ClassDeclarationNode) tree.getChildren()[0];
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertEquals("test", node.getName());
        assertSame(0, node.getFields().length);
        assertSame(0, node.getMethods().length);
        assertSame(1, node.getClasses().length);

        assertType(ClassDeclarationNode.class, node.getClasses()[0]);
        ClassDeclarationNode aclass = node.getClasses()[0];

        assertSame(AccessDescriber.PACKAGE, aclass.getAccess());
        assertSame(0, aclass.getClasses().length);
        assertSame(0, aclass.getMethods().length);
        assertSame(0, aclass.getFields().length);
        assertEquals("c", aclass.getName());
        assertTrue(aclass.isInClass());
        assertTrue(aclass.isStatic());
        assertTrue(aclass.isFinal());

    }
}
