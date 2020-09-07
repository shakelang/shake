package com.github.nsc.de.compiler.parser.parser.variables;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface VariableDeclarationParser extends ParserType {

    @Override
    default VariableDeclarationNode varDeclaration1() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_VAR) throw this.error("Expecting var keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(this.getInput().peek(2) != null && this.getInput().peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode((String) this.getInput().peek().getValue(), this.varAssignment());
        } else {
            this.getInput().skip();
            return new VariableDeclarationNode((String) this.getInput().actual().getValue());
        }
    }

    @Override
    default VariableDeclarationNode varDeclaration2() {
        Token t = this.getInput().next();
        VariableDeclarationNode.VariableType declarationNode =
                t.getType() == TokenType.KEYWORD_DYNAMIC ? VariableDeclarationNode.VariableType.DYNAMIC :
                t.getType() == TokenType.KEYWORD_BYTE ? VariableDeclarationNode.VariableType.BYTE :
                t.getType() == TokenType.KEYWORD_SHORT ? VariableDeclarationNode.VariableType.SHORT :
                t.getType() == TokenType.KEYWORD_INT ? VariableDeclarationNode.VariableType.INTEGER :
                t.getType() == TokenType.KEYWORD_LONG ? VariableDeclarationNode.VariableType.LONG :
                t.getType() == TokenType.KEYWORD_FLOAT ? VariableDeclarationNode.VariableType.FLOAT :
                t.getType() == TokenType.KEYWORD_DOUBLE ? VariableDeclarationNode.VariableType.DOUBLE :
                t.getType() == TokenType.KEYWORD_BOOLEAN ? VariableDeclarationNode.VariableType.BOOLEAN :
                t.getType() == TokenType.KEYWORD_CHAR ? VariableDeclarationNode.VariableType.CHAR : null;

        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(this.getInput().peek(2) != null && this.getInput().peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode((String) this.getInput().peek().getValue(), declarationNode, this.varAssignment());
        } else {
            this.getInput().skip();
            return new VariableDeclarationNode((String) this.getInput().actual().getValue(), declarationNode);
        }
    }

}