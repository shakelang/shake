package com.github.nsc.de.compiler.parser.parser.statements;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.expression.AddNode;
import com.github.nsc.de.compiler.parser.node.expression.NumberNode;
import com.github.nsc.de.compiler.parser.node.expression.SubNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalFalseNode;
import com.github.nsc.de.compiler.parser.node.logical.LogicalTrueNode;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface FactorParser extends ParserType {

    @Override
    default ValuedNode factor() {

        Token token = this.getInput().peek();

        if(token.getType() == TokenType.LPAREN) {
            getInput().skip();
            ValuedNode result = this.statement();
            if(this.getInput().next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");
            return result;
        }

        if(token.getType() == TokenType.KEYWORD_TRUE) {
            getInput().skip();
            return new LogicalTrueNode();
        }

        if(token.getType() == TokenType.KEYWORD_FALSE) {
            getInput().skip();
            return new LogicalFalseNode();
        }

        if(token.getType() == TokenType.INTEGER) {
            getInput().skip();
            return new NumberNode(Integer.parseInt(token.getValue()));
        }

        if(token.getType() == TokenType.DOUBLE) {
            getInput().skip();
            return new NumberNode(Double.parseDouble(token.getValue()));
        }

        if(token.getType() == TokenType.IDENTIFIER) {
            return varUsage();
        }

        if(token.getType() == TokenType.ADD) {
            getInput().skip();
            return new AddNode(0, this.factor());
        }

        if(token.getType() == TokenType.SUB) {
            getInput().skip();
            return new SubNode(0, this.factor());
        }

        throw this.error(this.getInput().toString());
    }
}
