package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ProgramParser extends ParserType, ParseUtils {

    List<TokenType> NUMBER = Arrays.asList(TokenType.INTEGER, TokenType.DOUBLE);

    @Override
    default Tree prog() {
        List<Node> nodes = new ArrayList<>();
        int position = -2;
        skipSeparators();
        boolean separator = true;

        while (this.getInput().hasNext()) {

            if(!separator) break; //throw this.error("Awaited separator at this point");
            separator = false;
            if(position >= this.getInput().getPosition()) break;
            position = this.getInput().getPosition();

            if(this.getInput().hasNext()) {
                Node result = operation();
                if(result != null) nodes.add(result);
            }

            if(this.skipSeparators() > 0) separator = true;

        }
        return new Tree(nodes);
    }

    @Override
    default Node operation() {

        Token token = this.getInput().peek();
        Token token2 = this.getInput().peek(2);

        // Keywords
        if(token.getType() == TokenType.KEYWORD_VAR) return this.varDeclaration();
        if(token.getType() == TokenType.KEYWORD_WHILE) return this.whileLoop();
        if(token.getType() == TokenType.KEYWORD_DO) return this.doWhileLoop();
        if(token.getType() == TokenType.KEYWORD_FOR) return this.forLoop();
        if(token.getType() == TokenType.KEYWORD_IF) return this.ifStatement();

        else return this.valuedOperation();
    }

    @Override
    default ValuedNode valuedOperation() {

        Token token = this.getInput().peek();
        Token token2 = this.getInput().peek(2);


        if(token.getType() == TokenType.KEYWORD_FUNCTION) return this.function();

        // Assignments
        if(token.getType() == TokenType.IDENTIFIER && token2 != null) {
            if(token2.getType() == TokenType.LPAREN) return this.functionCall();
            if(token2.getType() == TokenType.ASSIGN) return this.varAssignment();
            if(token2.getType() == TokenType.ADD_ASSIGN) return this.varAddAssignment();
            if(token2.getType() == TokenType.SUB_ASSIGN) return this.varSubAssignment();
            if(token2.getType() == TokenType.MUL_ASSIGN) return this.varMulAssignment();
            if(token2.getType() == TokenType.DIV_ASSIGN) return this.varDivAssignment();
            if(token2.getType() == TokenType.POW_ASSIGN) return this.varPowAssignment();
            if(token2.getType() == TokenType.INCR) return this.varIncrease();
            if(token2.getType() == TokenType.DECR) return this.varDecrease();
        }

        // Expression
        if(NUMBER.contains(token.getType()) ||
                token.getType() == TokenType.IDENTIFIER ||
                token.getType() == TokenType.KEYWORD_TRUE ||
                token.getType() == TokenType.KEYWORD_FALSE)
            return this.statement();

        return null;


    }
}
