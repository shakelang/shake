package com.github.nsc.de.compiler.parser.parser.statements;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.logical.*;
import com.github.nsc.de.compiler.parser.parser.ParserType;

import java.util.Arrays;
import java.util.List;

public interface LogicalParser extends ParserType {

    List<TokenType> COMPARE = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS,
            TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS);

    @Override
    default ValuedNode logicalOr() {
        ValuedNode result = this.logicalAnd();

        while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.LOGICAL_OR) {
            this.getInput().skip();
            result = new LogicalOrNode(result, this.logicalAnd());
        }
        return result;
    }


    @Override
    default ValuedNode logicalAnd() {
        ValuedNode result = this.compare();

        while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.LOGICAL_AND) {
            this.getInput().skip();
            result = new LogicalAndNode(result, this.compare());
        }
        return result;
    }


    @Override
    default ValuedNode compare() {
        ValuedNode left = this.factor();

        while(this.getInput().hasNext() && COMPARE.contains(this.getInput().peek().getType())) {

            Token comparer = this.getInput().next();
            if(comparer.getType() == TokenType.EQ_EQUALS) return new LogicalEqEqualsNode(left, this.statement());
            else if(comparer.getType() == TokenType.BIGGER_EQUALS) left = new LogicalBiggerEqualsNode(left, this.statement());
            else if(comparer.getType() == TokenType.SMALLER_EQUALS) left = new LogicalSmallerEqualsNode(left, this.statement());
            else if(comparer.getType() == TokenType.BIGGER) left = new LogicalBiggerNode(left, this.statement());
            else left = new LogicalSmallerNode(left, this.statement());

        }
        return left;
    }

}
