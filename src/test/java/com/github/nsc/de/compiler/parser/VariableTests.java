package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.interpreter.VariableType;
import com.github.nsc.de.compiler.parser.node.expression.NumberNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import com.github.nsc.de.compiler.parser.node.variables.*;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.TestUtil.assertType;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.parseSingle;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableTests {

    @Test
    public void testVariableAssignment() {

        VariableAssignmentNode node = parseSingle("<VariableAssignmentTest>", "i = 0", VariableAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

        node = parseSingle("<VariableAssignmentTest>", "i = 0.1", VariableAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

        node = parseSingle("<VariableAssignmentTest>", "i = true", VariableAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(LogicalTrueNode.class, node.getValue());

    }

    @Test
    public void testVariableAddAssignment() {

        VariableAddAssignmentNode node = parseSingle("<VariableAddAssignmentTest>", "i += 0", VariableAddAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

    }

    @Test
    public void testVariableSubAssignment() {

        VariableSubAssignmentNode node = parseSingle("<VariableSubAssignmentTest>", "i -= 0", VariableSubAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

    }

    @Test
    public void testVariableMulAssignment() {

        VariableMulAssignmentNode node = parseSingle("<VariableMulAssignmentTest>", "i *= 0", VariableMulAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

    }

    @Test
    public void testVariableDivAssignment() {

        VariableDivAssignmentNode node = parseSingle("<VariableDivAssignmentTest>", "i /= 0", VariableDivAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

    }

    @Test
    public void testVariablePowAssignment() {

        VariablePowAssignmentNode node = parseSingle("<VariablePowAssignmentTest>", "i ^= 0", VariablePowAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

        node = parseSingle("<VariablePowAssignmentTest>", "i **= 0", VariablePowAssignmentNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getValue());
        assertType(NumberNode.class, node.getValue());

    }

    @Test
    public void testVariableIncrease() {

        VariableIncreaseNode node = parseSingle("<VariableIncreaseTest>", "i ++", VariableIncreaseNode.class);
        assertEquals("i", node.getName());

    }

    @Test
    public void testVariableDecrease() {

        VariableDecreaseNode node = parseSingle("<VariableDecreaseTest>", "i --", VariableDecreaseNode.class);
        assertEquals("i", node.getName());

    }

    @Test
    public void testVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<VariableDeclarationTest>", "var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<VariableDeclarationTest>", "var i = 0", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.DYNAMIC, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<DynamicVariableDeclarationTest>", "dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<DynamicVariableDeclarationTest>", "dynamic d = 0", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.DYNAMIC, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<BooleanVariableDeclarationTest>", "boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<BooleanDeclarationTest>", "boolean b = true", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.BOOLEAN, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<CharacterVariableDeclarationTest>", "char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<CharacterVariableDeclarationTest>", "char c = 12", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.CHAR, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ByteVariableDeclarationTest>", "byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<ByteDeclarationTest>", "byte b = 12", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.BYTE, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ShortVariableDeclarationTest>", "short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<ShortVariableDeclarationTest>", "short s = 12", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.SHORT, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<IntegerVariableDeclarationTest>", "int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<IntegerVariableDeclarationTest>", "int i = 12", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.INTEGER, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<LongVariableDeclarationTest>", "long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<LongVariableDeclarationTest>", "long l = 12", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.LONG, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FloatVariableDeclarationTest>", "float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<FloatVariableDeclarationTest>", "float f = 12", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.FLOAT, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<DoubleVariableDeclarationTest>", "double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<DoubleVariableDeclarationTest>", "double d = 12", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableDeclarationNode.VariableType.Type.DOUBLE, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testVariableUsage() {

        VariableUsageNode node = parseSingle("<VariableUsageTest>", "i", VariableUsageNode.class);

        // Variable names
        assertEquals("i", node.getName());

    }


}
