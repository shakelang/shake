package com.github.nsc.de.compiler.parser.parser.statements;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.parser.ParserType;

import java.util.Arrays;
import java.util.List;

public interface StatementParser extends ParserType {

    List<TokenType> LOGICAL = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS,
            TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS, TokenType.LOGICAL_OR,
            TokenType.LOGICAL_AND);

    @Override
    default ValuedNode statement() {
        Token peek = this.getInput().peek(2);
        if(peek == null) return factor();
        if(LOGICAL.contains(peek.getType())) return this.logicalOr();
        return this.expr();
    }

}
