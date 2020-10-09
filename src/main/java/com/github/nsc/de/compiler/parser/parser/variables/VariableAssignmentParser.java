package com.github.nsc.de.compiler.parser.parser.variables;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.variables.*;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface VariableAssignmentParser extends ParserType {

    @Override
    default VariableAssignmentNode varAssignment() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.ASSIGN) throw this.error("Expecting '='");
        Node value = operation();
        return new VariableAssignmentNode(identifier.getValue(), value);
    }

    @Override
    default VariableAddAssignmentNode varAddAssignment() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.ADD_ASSIGN) throw this.error("Expecting '+='");
        Node value = operation();
        return new VariableAddAssignmentNode(identifier.getValue(), value);
    }

    @Override
    default VariableSubAssignmentNode varSubAssignment() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.SUB_ASSIGN) throw this.error("Expecting '-='");
        Node value = operation();
        return new VariableSubAssignmentNode(identifier.getValue(), value);
    }

    @Override
    default VariableMulAssignmentNode varMulAssignment() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.MUL_ASSIGN) throw this.error("Expecting '*='");
        Node value = operation();
        return new VariableMulAssignmentNode(identifier.getValue(), value);
    }

    @Override
    default VariableDivAssignmentNode varDivAssignment() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.DIV_ASSIGN) throw this.error("Expecting '/='");
        Node value = operation();
        return new VariableDivAssignmentNode(identifier.getValue(), value);
    }

    @Override
    default VariablePowAssignmentNode varPowAssignment() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.POW_ASSIGN) throw this.error("Expecting '^='");
        Node value = operation();
        return new VariablePowAssignmentNode(identifier.getValue(), value);
    }

    @Override
    default VariableIncreaseNode varIncrease() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.INCR) throw this.error("Expecting '++'");
        return new VariableIncreaseNode(identifier.getValue());
    }

    @Override
    default VariableDecreaseNode varDecrease() {
        Token identifier = this.getInput().next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.DECR) throw this.error("Expecting '--'");
        return new VariableDecreaseNode(identifier.getValue());
    }
}
