package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
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

    default ValuedNode parseDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        TokenInputStream input = getInput();

        switch(input.peek().getType()) {
            case KEYWORD_PUBLIC: input.skip(); return parseDeclaration(AccessDescriber.PUBLIC, isInClass, isStatic, isFinal);
            case KEYWORD_PROTECTED: input.skip(); return parseDeclaration(AccessDescriber.PROTECTED, isInClass, isStatic, isFinal);
            case KEYWORD_PRIVATE: input.skip(); return parseDeclaration(AccessDescriber.PRIVATE, isInClass, isStatic, isFinal);
            case KEYWORD_STATIC:
                if(!isInClass) throw new Error("Static keyword is only for objects in classes");
                input.skip();
                return parseDeclaration(access, true, true, isFinal);
            case KEYWORD_FINAL: input.skip(); return parseDeclaration(access, isInClass, isStatic, true);
            case KEYWORD_FUNCTION: return functionDeclaration(access, isInClass, isStatic, isFinal);
            case KEYWORD_CLASS: return classDeclaration(access, isInClass, isStatic, isFinal);
            case KEYWORD_VAR: return varDeclaration1(access, isInClass, isStatic, isFinal);
            case KEYWORD_DYNAMIC:
            case KEYWORD_BOOLEAN:
            case KEYWORD_CHAR:
            case KEYWORD_BYTE:
            case KEYWORD_SHORT:
            case KEYWORD_INT:
            case KEYWORD_LONG:
            case KEYWORD_FLOAT:
            case KEYWORD_DOUBLE:
                return varDeclaration2(access, isInClass, isStatic, isFinal);
            default:
                throw this.error("Unexpected token (" + input.peek().getType() + ')');
        }

    }

    default ValuedNode parseDeclaration(boolean isInClass) {
        return parseDeclaration(AccessDescriber.PACKAGE, isInClass, false, false);
    }

    default ValuedNode parseDeclaration() {
        return parseDeclaration(false);
    }
}
