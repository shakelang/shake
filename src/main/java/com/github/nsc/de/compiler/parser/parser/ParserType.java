package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.Position;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.compiler.parser.node.variables.*;
import com.github.nsc.de.compiler.util.CompilerError;

public interface ParserType {

    Tree prog();
    Node operation();
    ValuedNode valuedOperation();
    ValuedNode statement();

    VariableDeclarationNode varDeclaration1(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal);
    VariableDeclarationNode varDeclaration2(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal);

    VariableAssignmentNode varAssignment();
    VariableAddAssignmentNode varAddAssignment();
    VariableSubAssignmentNode varSubAssignment();
    VariableMulAssignmentNode varMulAssignment();
    VariableDivAssignmentNode varDivAssignment();
    VariablePowAssignmentNode varPowAssignment();
    VariableIncreaseNode varIncrease();
    VariableDecreaseNode varDecrease();

    VariableUsageNode varUsage();

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

    FunctionDeclarationNode functionDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal);
    FunctionCallNode functionCall();

    ClassDeclarationNode classDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal);

    TokenInputStream getInput();

    Parser.ParserError error(String name, String error, Position start, Position end);
    Parser.ParserError error(String error, Position start, Position end);
    Parser.ParserError error(String name, String error, int start, int end);
    Parser.ParserError error(String error, int start, int end);
    Parser.ParserError error(String error, int position);
    Parser.ParserError error(String name, String error, int position);
    Parser.ParserError error(String error);
    Parser.ParserError error(String name, String error);

}
