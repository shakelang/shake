package com.github.nsc.de.compiler.parser;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.*;
import static com.github.nsc.de.compiler.TestUtil.*;

import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.NumberNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import org.junit.jupiter.api.Test;

public class ParserTests {

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
    public void testVariableDeclaration() {

        VariableDeclarationNode node = parseSingle("<VariableDeclarationTest>", "var i", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertNull(node.getAssignment());

        node = parseSingle("<VariableDeclarationTest>", "var i = 0", VariableDeclarationNode.class);
        assertEquals("i", node.getName());
        assertNotNull(node.getAssignment());

    }

    @Test
    public void testVariableUsage() {

        VariableUsageNode node = parseSingle("<VariableUsageTest>", "i", VariableUsageNode.class);

        // Variable names
        assertEquals("i", node.getName());

    }

    @Test
    public void testMultiStatement() {

        Tree node = parse("<MultiStatementTest>", "var i; i");

        assertEquals(2, node.getChildren().length);
        assertType(VariableDeclarationNode.class, node.getChildren()[0]);
        assertType(VariableUsageNode.class, node.getChildren()[1]);

        node = parse("<MultiStatementTest>", "var i\ni");

        assertEquals(2, node.getChildren().length);
        assertType(VariableDeclarationNode.class, node.getChildren()[0]);
        assertType(VariableUsageNode.class, node.getChildren()[1]);

    }

    @Test
    public void testWhile() {
        WhileNode node = parseSingle("<WhileTest>", "while (true) { i }", WhileNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());

        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());


        node = parseSingle("<WhileTest>", "while (true) i;", WhileNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());

        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());
    }

    @Test
    public void testIf() {
        IfNode node = parseSingle("<IfTest>", "if (true) { i }", IfNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());

        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());


        node = parseSingle("<IfTest>", "if (true) i;", IfNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());
        assertNull(node.getElseBody());
        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());


        node = parseSingle("<IfTest>", "if (true) i; else i;", IfNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());
        assertNotNull(node.getElseBody());
        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());
        assertType(Tree.class, node.getElseBody());
    }

}
