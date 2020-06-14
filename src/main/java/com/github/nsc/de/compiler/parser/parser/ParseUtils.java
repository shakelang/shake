package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

public interface ParseUtils extends ParserType {

    default ValuedNode parseConditionStatement() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw this.error("Expecting '('");
        ValuedNode condition = logicalOr();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");
        return condition;
    }

    default Tree parseBodyStatement() {
        skipSeparators();
        if(this.getInput().peek().getType() == TokenType.LCURL) {
            this.getInput().skip();
            Tree body = prog();
            if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RCURL) throw this.error("Expecting '}'");
            return body;
        }
        else {
            return new Tree(new Node[] { this.operation() });
        }
    }

    default int skipSeparators() {

        int number = 0;
        while(this.getInput().hasNext() && (this.getInput().peek().getType() == TokenType.SEMICOLON || this.getInput().peek().getType() == TokenType.LINE_SEPARATOR)) {
            number++;
            this.getInput().skip();
        }
        return number;

    }

    default void awaitSemicolon() {

        if(this.getInput().next().getType() != TokenType.SEMICOLON) throw this.error("Expecting semicolon at this point");

    }
}
