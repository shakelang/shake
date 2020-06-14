package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.lexer.token.Token;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.*;
import com.github.nsc.de.compiler.parser.node.expression.*;
import com.github.nsc.de.compiler.parser.node.logical.*;
import com.github.nsc.de.compiler.parser.node.variables.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final List<TokenType> EXPR = Arrays.asList(TokenType.ADD, TokenType.SUB);
    private static final List<TokenType> TERM = Arrays.asList(TokenType.MUL, TokenType.DIV);
    private static final List<TokenType> NUMBER = Arrays.asList(TokenType.INTEGER, TokenType.DOUBLE);
    private static final List<TokenType> COMPARE = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS,
            TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS);
    private static final List<TokenType> LOGICAL = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS,
            TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS, TokenType.LOGICAL_OR,
            TokenType.LOGICAL_AND);

    private final TokenInputStream in;

    public Parser(TokenInputStream in) {
        this.in = in;
    }

    public Tree parse() {
        if(this.in.peek() == null) return null;

        Tree result = this.prog();

        if(this.in.hasNext()) throw this.error("Input did not end: ");
        return result;

    }

    private Tree prog() {

        List<Node> nodes = new ArrayList<>();
        int position = -2;
        skipSeparators();
        boolean separator = true;

        while (in.hasNext()) {

            if(!separator) break; //throw this.error("Awaited separator at this point");
            separator = false;
            if(position >= this.in.getPosition()) break;
            position = this.in.getPosition();

            if(this.in.hasNext()) {
                Node result = operation();
                if(result != null) nodes.add(result);
            }

            if(this.skipSeparators() > 0) separator = true;

        }
        return new Tree(nodes);

    }

    private Node operation() {

        Token token = this.in.peek();
        Token token2 = this.in.peek(2);

        // Keywords
        if(token.getType() == TokenType.KEYWORD_VAR) return this.varDeclaration();
        if(token.getType() == TokenType.KEYWORD_WHILE) return this.whileLoop();
        if(token.getType() == TokenType.KEYWORD_IF) return this.ifStatement();

        // Assignments
        if(token.getType() == TokenType.IDENTIFIER && token2 != null) {
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
                token.getType() == TokenType.KEYWORD_FALSE )
            return this.statement();

        else return null;
    }

    private VariableDeclarationNode varDeclaration() {
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.KEYWORD_VAR) throw this.error("Expecting var keyword");
        if(!this.in.hasNext() || this.in.peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(this.in.peek(2) != null && this.in.peek(2).getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode((String) this.in.peek().getValue(), this.varAssignment());
        } else {
            this.in.skip();
            return new VariableDeclarationNode((String) this.in.actual().getValue());
        }
    }

    private VariableAssignmentNode varAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.ASSIGN) throw this.error("Expecting '='");
        Node value = operation();
        return new VariableAssignmentNode((String) identifier.getValue(), value);
    }

    private VariableAddAssignmentNode varAddAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.ADD_ASSIGN) throw this.error("Expecting '+='");
        Node value = operation();
        return new VariableAddAssignmentNode((String) identifier.getValue(), value);
    }

    private VariableSubAssignmentNode varSubAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.SUB_ASSIGN) throw this.error("Expecting '-='");
        Node value = operation();
        return new VariableSubAssignmentNode((String) identifier.getValue(), value);
    }

    private VariableMulAssignmentNode varMulAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.MUL_ASSIGN) throw this.error("Expecting '*='");
        Node value = operation();
        return new VariableMulAssignmentNode((String) identifier.getValue(), value);
    }

    private VariableDivAssignmentNode varDivAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.DIV_ASSIGN) throw this.error("Expecting '/='");
        Node value = operation();
        return new VariableDivAssignmentNode((String) identifier.getValue(), value);
    }

    private VariablePowAssignmentNode varPowAssignment() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.POW_ASSIGN) throw this.error("Expecting '^='");
        Node value = operation();
        return new VariablePowAssignmentNode((String) identifier.getValue(), value);
    }


    private VariableIncreaseNode varIncrease() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.INCR) throw this.error("Expecting '++'");
        return new VariableIncreaseNode((String) identifier.getValue());
    }


    private VariableDecreaseNode varDecrease() {
        Token identifier = this.in.next();
        if(identifier == null || identifier.getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.DECR) throw this.error("Expecting '--'");
        return new VariableDecreaseNode((String) identifier.getValue());
    }

    private VariableUsageNode varUsage() {
        if(this.in.next().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        return new VariableUsageNode((String) this.in.actual().getValue());
    }

    private ValuedNode statement() {
        Token peek = this.in.peek(2);
        if(peek == null) return factor();
        if(LOGICAL.contains(peek.getType())) return this.logicalOr();
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

    private ValuedNode logicalOr() {
        ValuedNode result = this.logicalAnd();

        while(this.in.hasNext() && this.in.peek().getType() == TokenType.LOGICAL_OR) {
            this.in.skip();
            result = new LogicalOrNode(result, this.logicalAnd());
        }
        return result;
    }

    private ValuedNode logicalAnd() {
        ValuedNode result = this.compare();

        while(this.in.hasNext() && this.in.peek().getType() == TokenType.LOGICAL_AND) {
            this.in.skip();
            result = new LogicalAndNode(result, this.compare());
        }
        return result;
    }

    private ValuedNode compare() {
        ValuedNode left = this.factor();

        while(this.in.hasNext() && COMPARE.contains(this.in.peek().getType())) {

            Token comparer = this.in.next();
            if(comparer.getType() == TokenType.EQ_EQUALS) return new LogicalEqEqualsNode(left, this.statement());
            else if(comparer.getType() == TokenType.BIGGER_EQUALS) left = new LogicalBiggerEqualsNode(left, this.statement());
            else if(comparer.getType() == TokenType.SMALLER_EQUALS) left = new LogicalSmallerEqualsNode(left, this.statement());
            else if(comparer.getType() == TokenType.BIGGER) left = new LogicalBiggerNode(left, this.statement());
            else left = new LogicalSmallerNode(left, this.statement());

        }
        return left;
    }

    private ValuedNode factor() {
        Token token = this.in.peek();

        if(token.getType() == TokenType.LPAREN) {
            in.skip();
            ValuedNode result = this.statement();
            if(this.in.next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");
            return result;
        }

        if(token.getType() == TokenType.KEYWORD_TRUE) {
            in.skip();
            return new LogicalTrueNode();
        }

        if(token.getType() == TokenType.KEYWORD_FALSE) {
            in.skip();
            return new LogicalFalseNode();
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

        throw this.error(this.in.toString());
    }

    private Node whileLoop() {
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.KEYWORD_WHILE) throw this.error("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        if(!this.in.hasNext()) throw this.error("Expecting while body");
        Tree body = parseBodyStatement();
        return new WhileNode(body, condition);
    }

    private Node ifStatement() {
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.KEYWORD_IF) throw this.error("Expecting if keyword");
        ValuedNode condition = parseConditionStatement();
        if(!this.in.hasNext()) throw this.error("Expecting if body");
        Tree body = parseBodyStatement();
        boolean separator = skipSeparators()>0;
        if(this.in.hasNext() && this.in.peek().getType() == TokenType.KEYWORD_ELSE) {
            if(!separator) throw this.error("Awaited separator at this point");
            this.in.skip();
            Tree elseBody = parseBodyStatement();
            System.out.println(elseBody);
            return new IfNode(body, elseBody, condition);
        }
        return new IfNode(body, condition);
    }

    private ValuedNode parseConditionStatement() {
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.LPAREN) throw this.error("Expecting '('");
        ValuedNode condition = logicalOr();
        if(!this.in.hasNext() || this.in.next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");
        return condition;
    }

    private Tree parseBodyStatement() {
        skipSeparators();
        if(this.in.peek().getType() == TokenType.LCURL) {
            in.skip();
            Tree body = prog();
            if(!this.in.hasNext() || this.in.next().getType() != TokenType.RCURL) throw this.error("Expecting '}'");
            return body;
        }
        else {
            return new Tree(new Node[] { this.operation() });
        }
    }

    private int skipSeparators() {

        int number = 0;
        while(in.hasNext() && (in.peek().getType() == TokenType.SEMICOLON || in.peek().getType() == TokenType.LINE_SEPARATOR)) {
            number++;
            in.skip();
        }
        return number;

    }
    
    private Error error(String error) {
        throw new Error(error + " at " + this.in.peek() + " in " + this.in);
    }

}
