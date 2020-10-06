package com.github.nsc.de.compiler.parser.parser.variables;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.VariableType;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface VariableDeclarationParser extends ParserType {

    @Override
    default VariableDeclarationNode varDeclaration1(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_VAR) throw this.error("Expecting var keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(this.getInput().peek(2) != null && this.getInput().peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode(this.getInput().peek().getValue(), VariableType.DYNAMIC, this.varAssignment(), access, isInClass, isStatic, isFinal);
        } else {
            this.getInput().skip();
            return new VariableDeclarationNode(this.getInput().actual().getValue(), VariableType.DYNAMIC, null, access, isInClass, isStatic, isFinal);
        }

    }

    @Override
    default VariableDeclarationNode varDeclaration2(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        Token t = this.getInput().next();
        VariableType declarationNode =
                t.getType() == TokenType.KEYWORD_DYNAMIC ? VariableType.DYNAMIC :
                t.getType() == TokenType.KEYWORD_BYTE ? VariableType.BYTE :
                t.getType() == TokenType.KEYWORD_SHORT ? VariableType.SHORT :
                t.getType() == TokenType.KEYWORD_INT ? VariableType.INTEGER :
                t.getType() == TokenType.KEYWORD_LONG ? VariableType.LONG :
                t.getType() == TokenType.KEYWORD_FLOAT ? VariableType.FLOAT :
                t.getType() == TokenType.KEYWORD_DOUBLE ? VariableType.DOUBLE :
                t.getType() == TokenType.KEYWORD_BOOLEAN ? VariableType.BOOLEAN :
                t.getType() == TokenType.KEYWORD_CHAR ? VariableType.CHAR : null;

        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(this.getInput().peek(2) != null && this.getInput().peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode(this.getInput().peek().getValue(), declarationNode, this.varAssignment(), access, isInClass, isStatic, isFinal);
        } else {
            this.getInput().skip();
            return new VariableDeclarationNode(this.getInput().actual().getValue(), declarationNode, null, access, isInClass, isStatic, isFinal);
        }
    }

}