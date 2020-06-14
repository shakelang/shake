package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.variables.*;

public interface ParserType {

    Tree prog();
    Node operation();

    VariableDeclarationNode varDeclaration();

    VariableAssignmentNode varAssignment();

    VariableAddAssignmentNode varAddAssignment();

    VariableSubAssignmentNode varSubAssignment();

    VariableMulAssignmentNode varMulAssignment();

    VariableDivAssignmentNode varDivAssignment();

    VariablePowAssignmentNode varPowAssignment();

    VariableIncreaseNode varIncrease();

    VariableDecreaseNode varDecrease();

    VariableUsageNode varUsage();

    ValuedNode statement();

    ValuedNode expr();

    ValuedNode term();

    ValuedNode pow();

    ValuedNode logicalOr();

    ValuedNode logicalAnd();

    ValuedNode compare();

    ValuedNode factor();

    Node whileLoop();

    Node doWhileLoop();

    Node forLoop();

    Node ifStatement();
    
    Error error(String error);

    TokenInputStream getInput();

}
