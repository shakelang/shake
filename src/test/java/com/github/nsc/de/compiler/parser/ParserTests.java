package com.github.nsc.de.compiler.parser;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.*;

import com.github.nsc.de.compiler.parser.node.*;
import org.junit.jupiter.api.Test;

public class ParserTests {

    @Test
    public void testVariableAssignment() {

        Tree tree = parse("<VariableAssignmentTest>",
                "i = 0; i2 = 0.1;");

        Node[] nodes = tree.getChildren();

        // Result should be 2 nodes
        assertEquals(2, nodes.length);

        // Result Nodes should be VariableDeclarationNodes
        assertTrue(nodes[0] instanceof VariableAssignmentNode);
        assertTrue(nodes[1] instanceof VariableAssignmentNode);

        // Variable names
        assertEquals("i", ((VariableAssignmentNode) nodes[0]).getName());
        assertEquals("i2", ((VariableAssignmentNode) nodes[1]).getName());

    }

    @Test
    public void testVariableDeclaration() {

        Tree tree = parse("<VariableDeclarationTest>",
                "var i; var i2 = 0;");

        Node[] nodes = tree.getChildren();

        // Result should be 2 nodes
        assertEquals(2, nodes.length);

        // Result Nodes should be VariableDeclarationNodes
        assertTrue(nodes[0] instanceof VariableDeclarationNode);
        assertTrue(nodes[1] instanceof VariableDeclarationNode);

        // Variable names
        assertEquals("i", ((VariableDeclarationNode) nodes[0]).getName());
        assertEquals("i2", ((VariableDeclarationNode) nodes[1]).getName());

        // First declaration has no assignment
        assertNull(((VariableDeclarationNode) nodes[0]).getAssignment());

        // Second declaration has an assignment
        assertNotNull(((VariableDeclarationNode) nodes[1]).getAssignment());

    }

    @Test
    public void testVariableUsage() {

        Tree tree = parse("<VariableUsageTest>", "i");

        Node[] nodes = tree.getChildren();

        // Result should be 1 node
        assertEquals(1, nodes.length);

        // Result Node should be VariableUsageNode
        assertTrue(nodes[0] instanceof VariableUsageNode);

        // Variable names
        assertEquals("i", ((VariableUsageNode) nodes[0]).getName());

    }

}
