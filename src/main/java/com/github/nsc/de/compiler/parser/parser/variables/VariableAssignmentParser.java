package com.github.nsc.de.compiler.parser.parser.variables;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.variables.*;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface VariableAssignmentParser extends ParserType {

    @Override
    default VariableAssignmentNode varAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.ASSIGN) throw this.error("Expecting '='");
        Node value = operation();
        return new VariableAssignmentNode(variable, value);
    }

    @Override
    default VariableAddAssignmentNode varAddAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.ADD_ASSIGN) throw this.error("Expecting '+='");
        Node value = operation();
        return new VariableAddAssignmentNode(variable, value);
    }

    @Override
    default VariableSubAssignmentNode varSubAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.SUB_ASSIGN) throw this.error("Expecting '-='");
        Node value = operation();
        return new VariableSubAssignmentNode(variable, value);
    }

    @Override
    default VariableMulAssignmentNode varMulAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.MUL_ASSIGN) throw this.error("Expecting '*='");
        Node value = operation();
        return new VariableMulAssignmentNode(variable, value);
    }

    @Override
    default VariableDivAssignmentNode varDivAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.DIV_ASSIGN) throw this.error("Expecting '/='");
        Node value = operation();
        return new VariableDivAssignmentNode(variable, value);
    }

    @Override
    default VariablePowAssignmentNode varPowAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.POW_ASSIGN) throw this.error("Expecting '^='");
        Node value = operation();
        return new VariablePowAssignmentNode(variable, value);
    }

    @Override
    default VariableIncreaseNode varIncrease(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.INCR) throw this.error("Expecting '++'");
        return new VariableIncreaseNode(variable);
    }

    @Override
    default VariableDecreaseNode varDecrease(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.DECR) throw this.error("Expecting '--'");
        return new VariableDecreaseNode(variable);
    }
}
