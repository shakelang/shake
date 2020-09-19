package com.github.nsc.de.compiler.parser;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.*;
import static com.github.nsc.de.compiler.TestUtil.*;

import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.NumberNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalSmallerNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import com.github.nsc.de.compiler.parser.node.loops.DoWhileNode;
import com.github.nsc.de.compiler.parser.node.loops.ForNode;
import com.github.nsc.de.compiler.parser.node.loops.WhileNode;
import com.github.nsc.de.compiler.parser.node.variables.*;
import org.junit.jupiter.api.Test;

public class ParserTests {

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
    public void testDoWhile() {
        DoWhileNode node = parseSingle("<DoWhileTest>", "do { i } while (true);", DoWhileNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());

        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());


        node = parseSingle("<WhileTest>", "do i; while (true);", DoWhileNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());

        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());
    }

    @Test
    public void testFor() {
        ForNode node = parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) { i; }", ForNode.class);
        assertNotNull(node.getDeclaration());
        assertNotNull(node.getCondition());
        assertNotNull(node.getRound());
        assertNotNull(node.getBody());

        assertType(VariableDeclarationNode.class, node.getDeclaration());
        assertType(LogicalSmallerNode.class, node.getCondition());
        assertType(VariableIncreaseNode.class, node.getRound());
        assertType(Tree.class, node.getBody());


        node = parseSingle("<ForTest>", "for(var i = 0; i < 100; i++) i;", ForNode.class);
        assertNotNull(node.getDeclaration());
        assertNotNull(node.getCondition());
        assertNotNull(node.getRound());
        assertNotNull(node.getBody());

        assertType(VariableDeclarationNode.class, node.getDeclaration());
        assertType(LogicalSmallerNode.class, node.getCondition());
        assertType(VariableIncreaseNode.class, node.getRound());
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


        node = parseSingle("<IfTest>", "if (true) i; else if (true) i; else i;", IfNode.class);
        assertNotNull(node.getCondition());
        assertNotNull(node.getBody());
        assertNotNull(node.getElseBody());
        assertType(LogicalTrueNode.class, node.getCondition());
        assertType(Tree.class, node.getBody());
        assertType(Tree.class, node.getElseBody());

        Tree elseBody = node.getElseBody();
        assertSame(1, elseBody.getChildren().length);
        assertType(IfNode.class, elseBody.getChildren()[0]);

        IfNode if2 = (IfNode) elseBody.getChildren()[0];
        assertType(Tree.class, if2.getBody());
        assertType(Tree.class, if2.getElseBody());


    }

}
