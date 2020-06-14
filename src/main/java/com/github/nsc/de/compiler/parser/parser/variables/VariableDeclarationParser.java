package com.github.nsc.de.compiler.parser.parser.variables;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface VariableDeclarationParser extends ParserType {

    @Override
    default VariableDeclarationNode varDeclaration() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_VAR) throw this.error("Expecting var keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(this.getInput().peek(2) != null && this.getInput().peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode((String) this.getInput().peek().getValue(), this.varAssignment());
        } else {
            this.getInput().skip();
            return new VariableDeclarationNode((String) this.getInput().actual().getValue());
        }
    }

}