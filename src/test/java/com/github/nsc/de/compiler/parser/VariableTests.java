package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.IdentifierNode;
import com.github.nsc.de.compiler.parser.node.VariableType;
import com.github.nsc.de.compiler.parser.node.expression.DoubleNode;
import com.github.nsc.de.compiler.parser.node.expression.IntegerNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import com.github.nsc.de.compiler.parser.node.variables.*;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.TestUtil.assertType;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.parseSingle;
import static org.junit.jupiter.api.Assertions.*;

public class VariableTests {



    //// *************************************************************
    //// Assignments



    @Test
    public void testVariableAssignment() {

        VariableAssignmentNode node = parseSingle("<VariableAssignmentTest>", "i = 0", VariableAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

        node = parseSingle("<VariableAssignmentTest>", "i = 0.1", VariableAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(DoubleNode.class, node.getValue());

        node = parseSingle("<VariableAssignmentTest>", "i = true", VariableAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(LogicalTrueNode.class, node.getValue());

    }

    @Test
    public void testVariableAddAssignment() {

        VariableAddAssignmentNode node = parseSingle("<VariableAddAssignmentTest>", "i += 0", VariableAddAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

    }

    @Test
    public void testVariableSubAssignment() {

        VariableSubAssignmentNode node = parseSingle("<VariableSubAssignmentTest>", "i -= 0", VariableSubAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

    }

    @Test
    public void testVariableMulAssignment() {

        VariableMulAssignmentNode node = parseSingle("<VariableMulAssignmentTest>", "i *= 0", VariableMulAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

    }

    @Test
    public void testVariableDivAssignment() {

        VariableDivAssignmentNode node = parseSingle("<VariableDivAssignmentTest>", "i /= 1", VariableDivAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

    }

    @Test
    public void testVariablePowAssignment() {

        VariablePowAssignmentNode node = parseSingle("<VariablePowAssignmentTest>", "i ^= 0", VariablePowAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

        node = parseSingle("<VariablePowAssignmentTest>", "i **= 0", VariablePowAssignmentNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());
        assertNotNull(node.getValue());
        assertType(IntegerNode.class, node.getValue());

    }

    @Test
    public void testVariableIncrease() {

        VariableIncreaseNode node = parseSingle("<VariableIncreaseTest>", "i ++", VariableIncreaseNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());

    }

    @Test
    public void testVariableDecrease() {

        VariableDecreaseNode node = parseSingle("<VariableDecreaseTest>", "i --", VariableDecreaseNode.class);
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", ((IdentifierNode) node.getVariable()).getName());
        assertNull(((IdentifierNode) node.getVariable()).getParent());

    }



    //// *************************************************************
    //// Variable Declaration

    @Test
    public void testVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<VariableDeclarationTest>", "var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());

        node = parseSingle("<VariableDeclarationTest>", "var i = 0", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNotNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());

    }

    @Test
    public void testVariableLetDeclaration() {

        VariableDeclarationNode node = parseSingle("<VariableLetDeclarationTest>", "let i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());

        node = parseSingle("<VariableLetDeclarationTest>", "let i = 0", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNotNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());

    }

    @Test
    public void testVariableConstDeclaration() {

        VariableDeclarationNode node = parseSingle("<VariableConstDeclarationTest>", "const i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());

        node = parseSingle("<VariableConstDeclarationTest>", "const i = 0", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNotNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());
        assertFalse(node.isStatic());

    }

    @Test
    public void testFinalVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalVariableDeclarationTest>", "final var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicVariableDeclarationTest>", "public var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalDynamicVariableDeclarationTest>", "public final var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedVariableDeclarationTest>", "protected var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalVariableDeclarationTest>", "protected final var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateVariableDeclarationTest>", "private var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalVariableDeclarationTest>", "private final var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<DynamicVariableDeclarationTest>", "dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<DynamicVariableDeclarationTest>", "dynamic d = 0", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalDynamicVariableDeclarationTest>", "final dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicDynamicVariableDeclarationTest>", "public dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalDynamicVariableDeclarationTest>", "public final dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedDynamicVariableDeclarationTest>", "protected dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedDynamicIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalDynamicVariableDeclarationTest>", "protected final dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateDynamicVariableDeclarationTest>", "private dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalDynamicVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalDynamicVariableDeclarationTest>", "private final dynamic d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DYNAMIC, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<BooleanVariableDeclarationTest>", "boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<BooleanDeclarationTest>", "boolean b = true", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalBooleanVariableDeclarationTest>", "final boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicBooleanVariableDeclarationTest>", "public boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalBooleanVariableDeclarationTest>", "public final boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedBooleanVariableDeclarationTest>", "protected boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalBooleanVariableDeclarationTest>", "protected final boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateBooleanVariableDeclarationTest>", "private boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalBooleanVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalBooleanVariableDeclarationTest>", "private final boolean b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BOOLEAN, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<CharacterVariableDeclarationTest>", "char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<CharacterVariableDeclarationTest>", "char c = 12", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalCharacterVariableDeclarationTest>", "final char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicCharacterVariableDeclarationTest>", "public char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalCharacterVariableDeclarationTest>", "public final char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedCharacterVariableDeclarationTest>", "protected char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalCharacterVariableDeclarationTest>", "protected final char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateCharacterVariableDeclarationTest>", "private char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalCharacterVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalCharacterVariableDeclarationTest>", "private final char c", VariableDeclarationNode.class);
        assertEquals("c", node.getName());
        assertSame(VariableType.Type.CHAR, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ByteVariableDeclarationTest>", "byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<ByteDeclarationTest>", "byte b = 12", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalByteVariableDeclarationTest>", "final byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicByteVariableDeclarationTest>", "public byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalByteVariableDeclarationTest>", "public final byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedByteVariableDeclarationTest>", "protected byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalByteVariableDeclarationTest>", "protected final byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateByteVariableDeclarationTest>", "private byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalByteVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalByteVariableDeclarationTest>", "private final byte b", VariableDeclarationNode.class);
        assertEquals("b", node.getName());
        assertSame(VariableType.Type.BYTE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ShortVariableDeclarationTest>", "short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<ShortVariableDeclarationTest>", "short s = 12", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalShortVariableDeclarationTest>", "final short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicShortVariableDeclarationTest>", "public short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalShortVariableDeclarationTest>", "public final short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedShortVariableDeclarationTest>", "protected short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalShortVariableDeclarationTest>", "protected final short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateShortVariableDeclarationTest>", "private short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalShortVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalShortVariableDeclarationTest>", "private final short s", VariableDeclarationNode.class);
        assertEquals("s", node.getName());
        assertSame(VariableType.Type.SHORT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<IntegerVariableDeclarationTest>", "int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<IntegerVariableDeclarationTest>", "int i = 12", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalIntegerVariableDeclarationTest>", "final int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicIntegerVariableDeclarationTest>", "public int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalIntegerVariableDeclarationTest>", "public final int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedIntegerVariableDeclarationTest>", "protected int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalIntegerVariableDeclarationTest>", "protected final int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateIntegerVariableDeclarationTest>", "private int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalIntegerVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalIntegerVariableDeclarationTest>", "private final int i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertSame(VariableType.Type.INTEGER, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<LongVariableDeclarationTest>", "long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<LongVariableDeclarationTest>", "long l = 12", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testFinalLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalLongVariableDeclarationTest>", "final long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicLongVariableDeclarationTest>", "public long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalLongVariableDeclarationTest>", "public final long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedLongVariableDeclarationTest>", "protected long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalLongVariableDeclarationTest>", "protected final long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateLongVariableDeclarationTest>", "private long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalLongVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalLongVariableDeclarationTest>", "private final long l", VariableDeclarationNode.class);
        assertEquals("l", node.getName());
        assertSame(VariableType.Type.LONG, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FloatVariableDeclarationTest>", "float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

        node = parseSingle("<FloatVariableDeclarationTest>", "float f = 12", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNotNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testFinalFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<FinalFloatVariableDeclarationTest>", "final float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalFloatVariableDeclarationTest>", "public float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalFloatVariableDeclarationTest>", "public final float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFloatVariableDeclarationTest>", "protected float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalFloatVariableDeclarationTest>", "protected final float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFloatVariableDeclarationTest>", "private float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateFinalFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalFloatVariableDeclarationTest>", "private final float f", VariableDeclarationNode.class);
        assertEquals("f", node.getName());
        assertSame(VariableType.Type.FLOAT, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<DoubleVariableDeclarationTest>", "double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());

        node = parseSingle("<DoubleVariableDeclarationTest>", "double d = 12", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testDoubleFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<DoubleVariableDeclarationTest>", "final double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PACKAGE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicDoubleVariableDeclarationTest>", "public double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPublicFinalDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PublicFinalDoubleVariableDeclarationTest>", "public final double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PUBLIC, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedDoubleVariableDeclarationTest>", "protected double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testProtectedFinalDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<ProtectedFinalDoubleVariableDeclarationTest>", "protected final double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PROTECTED, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateDoubleVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateDoubleVariableDeclarationTest>", "private double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertFalse(node.isFinal());
        assertFalse(node.isInClass());

    }

    @Test
    public void testPrivateDoubleFloatVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<PrivateFinalDoubleVariableDeclarationTest>", "private final double d", VariableDeclarationNode.class);
        assertEquals("d", node.getName());
        assertSame(VariableType.Type.DOUBLE, node.getType().getType());
        assertNull(node.getAssignment());
        assertSame(AccessDescriber.PRIVATE, node.getAccess());
        assertFalse(node.isStatic());
        assertTrue(node.isFinal());
        assertFalse(node.isInClass());

    }



    //// *************************************************************
    //// Variable Usage

    @Test
    public void testVariableUsage() {

        VariableUsageNode node = parseSingle("<VariableUsageTest>", "i", VariableUsageNode.class);

        // Variable names
        assertType(IdentifierNode.class, node.getVariable());
        assertEquals("i", node.getVariable().getName());
        assertNull(node.getVariable().getParent());

    }


}
