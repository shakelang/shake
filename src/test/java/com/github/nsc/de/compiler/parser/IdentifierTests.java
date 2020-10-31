package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.IdentifierNode;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableUsageNode;
import org.junit.jupiter.api.Test;

import static com.github.nsc.de.compiler.TestUtil.assertType;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.parse;
import static org.junit.jupiter.api.Assertions.*;

public class IdentifierTests {

    @Test
    public void testBasicIdentifier() {

        Tree tree = parse("<BasicIdentifierTest>", "test");
        assertEquals(1, tree.getChildren().length);
        assertType(VariableUsageNode.class, tree.getChildren()[0]);

        VariableUsageNode node = (VariableUsageNode) tree.getChildren()[0];
        assertEquals("test", node.getVariable().getName());
        assertNull(node.getVariable().getParent());

    }

    @Test
    public void testComplexIdentifier() {

        Tree tree = parse("<BasicIdentifierTest>", "test.test2");
        assertEquals(1, tree.getChildren().length);
        assertType(VariableUsageNode.class, tree.getChildren()[0]);

        VariableUsageNode node = (VariableUsageNode) tree.getChildren()[0];
        assertEquals("test2", node.getVariable().getName());
        assertNotNull(node.getVariable().getParent());
        assertType(VariableUsageNode.class, node.getVariable().getParent());

        node = (VariableUsageNode) node.getVariable().getParent();
        assertEquals("test", node.getVariable().getName());
        assertNull(node.getVariable().getParent());

    }

    @Test
    public void testComplexIdentifierWithFunctions() {

        Tree tree = parse("<BasicIdentifierTest>", "aaa.test().test2");
        assertEquals(1, tree.getChildren().length);
        assertType(VariableUsageNode.class, tree.getChildren()[0]);

        VariableUsageNode node = (VariableUsageNode) tree.getChildren()[0];
        assertEquals("test2", node.getVariable().getName());
        assertNotNull(node.getVariable().getParent());
        assertType(FunctionCallNode.class, node.getVariable().getParent());

        FunctionCallNode node2 = (FunctionCallNode) node.getVariable().getParent();
        assertNotNull(node.getVariable().getParent());
        assertType(IdentifierNode.class, node2.getFunction());
        assertEquals("test", ((IdentifierNode) node2.getFunction()).getName());
        assertType(VariableUsageNode.class, ((IdentifierNode) node2.getFunction()).getParent());

        node = (VariableUsageNode) ((IdentifierNode) node2.getFunction()).getParent();
        assertEquals("aaa", node.getVariable().getName());
        assertNull(node.getVariable().getParent());

    }
}
