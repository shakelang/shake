package com.github.nsc.de.compiler.parser.parser.statements;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.parser.ParserType;

import java.util.Arrays;
import java.util.List;

public interface CalculationParser extends ParserType {

    List<TokenType> EXPR = Arrays.asList(TokenType.ADD, TokenType.SUB);
    List<TokenType> TERM = Arrays.asList(TokenType.MUL, TokenType.DIV);

    @Override
    default ValuedNode expr() {
        ValuedNode result = this.term();

        while(this.getInput().hasNext() && EXPR.contains(this.getInput().peek().getType())) {
            if(this.getInput().peek().getType() == TokenType.ADD) {
                this.getInput().skip();
                result = new AddNode(result, this.term());
            }
            else if(this.getInput().peek().getType() == TokenType.SUB) {
                this.getInput().skip();
                result = new SubNode(result, this.term());
            }
        }

        return result;
    }

    default ValuedNode term() {
        ValuedNode result = this.pow();

        while(this.getInput().hasNext() && TERM.contains(this.getInput().peek().getType())) {
            if(this.getInput().peek().getType() == TokenType.MUL) {
                this.getInput().skip();
                result = new MulNode(result, this.pow());
            }
            else if(this.getInput().peek().getType() == TokenType.DIV) {
                this.getInput().skip();
                result = new DivNode(result, this.pow());
            }
        }
        return result;
    }

    default ValuedNode pow() {
        ValuedNode result = this.factor();

        while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.POW) {
            this.getInput().skip();
            result = new PowNode(result, this.factor());
        }
        return result;
    }
}
