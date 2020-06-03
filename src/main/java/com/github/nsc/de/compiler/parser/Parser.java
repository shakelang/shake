package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.node.logical.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final List<TokenType> EXPR = Arrays.asList(TokenType.ADD, TokenType.SUB);
    private static final List<TokenType> TERM = Arrays.asList(TokenType.MUL, TokenType.DIV);
    private static final List<TokenType> NUMBER = Arrays.asList(TokenType.INTEGER, TokenType.DOUBLE);
    private static final List<TokenType> COMPARE = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS, TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS);

    private final TokenInputStream in;

    public Parser(TokenInputStream in) {
        this.in = in;
    }

    public Tree parse() {
        if(this.in.peek() == null) return null;

        Tree result = this.prog();

        if(this.in.hasNext()) throw new Error("Input did not end: "+in);
        return result;

    }

    private Tree prog() {

        List<Node> nodes = new ArrayList<>();
        while (in.hasNext()) {
            while (this.in.hasNext() && (this.in.peek().getType() == TokenType.SEMICOLON || this.in.peek().getType() == TokenType.LINE_SEPARATOR)) this.in.skip();
            if(this.in.hasNext()) nodes.add(operation());
            if(!in.hasNext() || (in.peek().getType() == TokenType.SEMICOLON && in.peek().getType() == TokenType.LINE_SEPARATOR)) break;
        }
        return new Tree(nodes);

    }

    private Node operation() {

        Token token = this.in.peek();
        Token token2 = this.in.peek(2);

        // Keywords
        if(token.getType() == TokenType.KEYWORD_VAR) return this.varDeclaration();

        // Assignments
        if(token.getType() == TokenType.IDENTIFIER && token2 != null && token2.getType() == TokenType.ASSIGN) return this.varAssignment();

        // Expression
        if(NUMBER.contains(this.in.peek().getType()) || token.getType() == TokenType.IDENTIFIER) return this.statement();

        throw new Error("Unparsable Token: " + token + " in " + this.in);
    }

    private VariableDeclarationNode varDeclaration() {
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.KEYWORD_VAR) throw new Error("Expecting var keyword");
        if(!this.in.hasNext() || this.in.peek().getType() != TokenType.IDENTIFIER) throw new Error("Expecting identifier");
        if(this.in.peek(2) != null && this.in.peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode((String) this.in.peek().getValue(), this.varAssignment());
        } else {
            this.in.skip();
            return new VariableDeclarationNode((String) this.in.actual().getValue());
        }
    }

    private VariableAssignmentNode varAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw new Error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.ASSIGN) throw new Error("Expecting '='");
        Node value = operation();
        return new VariableAssignmentNode((String) identifier.getValue(), value);
    }

    private VariableUsageNode varUsage() {
        if(this.in.next().getType() != TokenType.IDENTIFIER) throw new Error("Expecting identifier");
        return new VariableUsageNode((String) this.in.actual().getValue());
    }

    private ValuedNode statement() {
        Token peek = this.in.peek(2);
        if(peek == null) return factor();
        if(COMPARE.contains(peek.getType())) return this.logicalOr();
        return this.expr();
    }

    private ValuedNode expr() {
        ValuedNode result = this.term();

        while(this.in.hasNext() && EXPR.contains(this.in.peek().getType())) {
            if(this.in.peek().getType() == TokenType.ADD) {
                this.in.skip();
                result = new AddNode(result, this.term());
            }
            else if(this.in.peek().getType() == TokenType.SUB) {
                this.in.skip();
                result = new SubNode(result, this.term());
            }
        }

        return result;
    }

    private ValuedNode term() {
        ValuedNode result = this.pow();

        while(this.in.hasNext() && TERM.contains(this.in.peek().getType())) {
            if(this.in.peek().getType() == TokenType.MUL) {
                this.in.skip();
                result = new MulNode(result, this.pow());
            }
            else if(this.in.peek().getType() == TokenType.DIV) {
                this.in.skip();
                result = new DivNode(result, this.pow());
            }
        }
        return result;
    }

    private ValuedNode pow() {
        ValuedNode result = this.factor();

        while(this.in.hasNext() && this.in.peek().getType() == TokenType.POW) {
            this.in.skip();
            result = new PowNode(result, this.factor());
        }
        return result;
    }

    private LogicalNode logicalOr() {
        LogicalNode result = this.logicalAnd();

        while(this.in.hasNext() && this.in.peek().getType() == TokenType.LOGICAL_OR) {
            this.in.skip();
            result = new LogicalOrNode(result, this.logicalAnd());
        }
        return result;
    }

    private LogicalNode logicalAnd() {
        LogicalNode result = this.compare();

        while(this.in.hasNext() && this.in.peek().getType() == TokenType.LOGICAL_AND) {
            this.in.skip();
            result = new LogicalAndNode(result, this.compare());
        }
        return result;
    }

    private LogicalNode compare() {
        ValuedNode left = this.factor();
        Token comparer = this.in.next();
        if(comparer.getType() == TokenType.EQ_EQUALS) return new EqEqualsNodeLogical(left, this.factor());
        else if(comparer.getType() == TokenType.BIGGER_EQUALS) return new BiggerEqualsNodeLogical(left, this.factor());
        else if(comparer.getType() == TokenType.SMALLER_EQUALS) return new SmallerEqualsNodeLogical(left, this.factor());
        else if(comparer.getType() == TokenType.BIGGER) return new BiggerNodeLogical(left, this.factor());
        else if(comparer.getType() == TokenType.SMALLER) return new SmallerNodeLogical(left, this.factor());
        else throw new Error("Expecting comparison operator");
    }

    private ValuedNode factor() {
        Token token = this.in.peek();

        if(token.getType() == TokenType.LPAREN) {
            in.skip();
            ValuedNode result = this.expr();
            if(this.in.next().getType() != TokenType.RPAREN) throw new Error("Expecting ')'");
            return result;
        }

        if(token.getType() == TokenType.INTEGER || token.getType() == TokenType.DOUBLE) {
            in.skip();
            if(token.getValue() instanceof Integer) return new NumberNode((int) token.getValue());
            return new NumberNode((double) token.getValue());
        }

        if(token.getType() == TokenType.IDENTIFIER) {
            return varUsage();
        }

        if(token.getType() == TokenType.ADD) {
            in.skip();
            return new AddNode(0, this.factor());
        }

        if(token.getType() == TokenType.SUB) {
            in.skip();
            return new SubNode(0, this.factor());
        }

        throw new Error();
    }

}
