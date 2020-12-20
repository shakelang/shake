package com.github.nsc.de.shake.parser;

import com.github.nsc.de.shake.lexer.characterinput.position.Position;
import com.github.nsc.de.shake.lexer.token.Token;
import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.lexer.token.TokenType;
import com.github.nsc.de.shake.parser.node.*;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.logical.*;
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode;
import com.github.nsc.de.shake.parser.node.loops.ForNode;
import com.github.nsc.de.shake.parser.node.loops.WhileNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.*;
import com.github.nsc.de.shake.util.CompilerError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final TokenInputStream in;

    public Parser(TokenInputStream in) {
        this.in = in;
    }

    public Tree parse() {
        if(this.in.peek() == null) return null;

        Tree result = this.prog();

        if(this.in.hasNext()) throw new ParserError("Input did not end");
        return result;
    }



    // ****************************************************************************
    // Basic Program


    private Tree prog() {
        List<Node> nodes = new ArrayList<>();
        int position = -2;
        skipSeparators();
        // TODO Require Separator
        // boolean separator = true;

        while (this.getInput().hasNext()) {

            // if(!separator) throw new ParserError("AwaitSeparatorError", "Awaited separator at this point");
            // separator = false;
            if(position >= this.getInput().getPosition()) break;
            position = this.getInput().getPosition();

            if(this.getInput().hasNext()) {
                Node result = operation();
                if(result != null) nodes.add(result);
            }

            // if(this.skipSeparators() > 0) separator = true;
            skipSeparators();

        }
        return new Tree(nodes);
    }

    private Node operation() {

        Token token = this.getInput().peek();
        if(token.getType() == TokenType.KEYWORD_WHILE) return this.whileLoop();
        if(token.getType() == TokenType.KEYWORD_DO) return this.doWhileLoop();
        if(token.getType() == TokenType.KEYWORD_FOR) return this.forLoop();
        if(token.getType() == TokenType.KEYWORD_IF) return this.ifStatement();

        return this.valuedOperation();
    }

    private ValuedNode valuedOperation() {

        Token token = this.getInput().peek();

        if(token.getType() == TokenType.KEYWORD_FUNCTION
                || token.getType() == TokenType.KEYWORD_VAR
                || token.getType() == TokenType.KEYWORD_CONST
                || token.getType() == TokenType.KEYWORD_CLASS
                || token.getType() == TokenType.KEYWORD_PUBLIC
                || token.getType() == TokenType.KEYWORD_PROTECTED
                || token.getType() == TokenType.KEYWORD_PRIVATE
                || token.getType() == TokenType.KEYWORD_FINAL
                || token.getType() == TokenType.KEYWORD_STATIC
                || token.getType() == TokenType.KEYWORD_DYNAMIC
                || token.getType() == TokenType.KEYWORD_BYTE
                || token.getType() == TokenType.KEYWORD_SHORT
                || token.getType() == TokenType.KEYWORD_INT
                || token.getType() == TokenType.KEYWORD_LONG
                || token.getType() == TokenType.KEYWORD_FLOAT
                || token.getType() == TokenType.KEYWORD_DOUBLE
                || token.getType() == TokenType.KEYWORD_BOOLEAN
                || token.getType() == TokenType.KEYWORD_CHAR) return parseDeclaration();

        // Expression
        if(token.getType() == TokenType.INTEGER ||
                token.getType() == TokenType.DOUBLE ||
                token.getType() == TokenType.KEYWORD_TRUE ||
                token.getType() == TokenType.KEYWORD_FALSE ||
                token.getType() == TokenType.IDENTIFIER ||
                token.getType() == TokenType.KEYWORD_NEW)
            return this.logicalOr();

        return null;

    }



    // ****************************************************************************
    // Utils
    private int skipSeparators() {

        int number = 0;
        while(this.getInput().hasNext() && (this.getInput().peek().getType() == TokenType.SEMICOLON || this.getInput().peek().getType() == TokenType.LINE_SEPARATOR)) {
            number++;
            this.getInput().skip();
        }
        return number;

    }

    private void awaitSemicolon() {

        if(this.getInput().skipIgnorable().next().getType() != TokenType.SEMICOLON) throw new ParserError("Expecting semicolon at this point", this.getInput().getPosition());

    }

    private ValuedNode parseDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        TokenInputStream input = getInput();

        switch(input.peek().getType()) {
            case KEYWORD_PUBLIC: input.skip(); return parseDeclaration(AccessDescriber.PUBLIC, isInClass, isStatic, isFinal);
            case KEYWORD_PROTECTED: input.skip(); return parseDeclaration(AccessDescriber.PROTECTED, isInClass, isStatic, isFinal);
            case KEYWORD_PRIVATE: input.skip(); return parseDeclaration(AccessDescriber.PRIVATE, isInClass, isStatic, isFinal);
            case KEYWORD_STATIC:
                if(!isInClass) throw new ParserError("Static keyword is only for objects in classes");
                input.skip();
                return parseDeclaration(access, true, true, isFinal);
            case KEYWORD_FINAL: input.skip(); return parseDeclaration(access, isInClass, isStatic, true);
            case KEYWORD_FUNCTION: return functionDeclaration(access, isInClass, isStatic, isFinal);
            case KEYWORD_CLASS: return classDeclaration(access, isInClass, isStatic, isFinal);
            case KEYWORD_CONST:
            case KEYWORD_VAR:
                return varDeclaration1(access, isInClass, isStatic, isFinal);
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
                throw new ParserError("Unexpected token (" + input.peek().getType() + ')');
        }

    }

    private ValuedNode parseDeclaration(boolean isInClass) {
        return parseDeclaration(AccessDescriber.PACKAGE, isInClass, false, false);
    }

    private ValuedNode parseDeclaration() {
        return parseDeclaration(false);
    }

    private ValuedNode parseIdentifier(ValuedNode parent) {
        Token identifier = getInput().next();
        if(identifier.getType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");

        IdentifierNode identifierNode = new IdentifierNode(parent, identifier.getValue());
        ValuedNode ret = null;

        // Assignments
        if(this.getInput().hasNext()) {

            Token token2 = getInput().skipIgnorable().peek();
            if(token2.getType() == TokenType.LPAREN) ret = this.functionCall(identifierNode);
            if(token2.getType() == TokenType.ASSIGN) ret = this.varAssignment(identifierNode);
            if(token2.getType() == TokenType.ADD_ASSIGN) ret = this.varAddAssignment(identifierNode);
            if(token2.getType() == TokenType.SUB_ASSIGN) ret = this.varSubAssignment(identifierNode);
            if(token2.getType() == TokenType.MUL_ASSIGN) ret = this.varMulAssignment(identifierNode);
            if(token2.getType() == TokenType.DIV_ASSIGN) ret = this.varDivAssignment(identifierNode);
            if(token2.getType() == TokenType.MOD_ASSIGN) ret = this.varModAssignment(identifierNode);
            if(token2.getType() == TokenType.POW_ASSIGN) ret = this.varPowAssignment(identifierNode);
            if(token2.getType() == TokenType.INCR) ret = this.varIncrease(identifierNode);
            if(token2.getType() == TokenType.DECR) ret = this.varDecrease(identifierNode);
            if(getInput().skipIgnorable().hasNext() && getInput().peek().getType() == TokenType.DOT) {
                getInput().skip().skipIgnorable();
                return this.parseIdentifier(ret != null ? ret : new VariableUsageNode(identifierNode));
            }
            if(ret != null) return ret;

        }
        return new VariableUsageNode(identifierNode);

    }

    private ClassConstructionNode parseClassConstruction() {
        this.getInput().skip().skipIgnorable();
        int start = getInput().actual().getStart();
        ValuedNode node = parseIdentifier(null);
        if(!(node instanceof FunctionCallNode))
            throw new ParserError("Expecting a call after keyword new",
                    start, getInput().actual().getEnd());
        return new ClassConstructionNode(((FunctionCallNode) node).getFunction(), ((FunctionCallNode) node).getArgs());
    }



    // ****************************************************************************
    // Classes


    private ClassDeclarationNode classDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_CLASS) throw new ParserError("Expecting class keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");
        String name = this.getInput().next().getValue();

        List<VariableDeclarationNode> fields = new ArrayList<>();
        List<FunctionDeclarationNode> methods = new ArrayList<>();
        List<ClassDeclarationNode> classes = new ArrayList<>();

        // TODO: extends, implements
        if(this.getInput().next().getType() != TokenType.LCURL) throw new ParserError("Expecting class-body");

        while(this.getInput().hasNext() && this.getInput().peek().getType() != TokenType.RCURL) {

            skipSeparators();

            ValuedNode node = parseDeclaration(true);
            if(node instanceof ClassDeclarationNode) classes.add((ClassDeclarationNode) node);
            else if(node instanceof FunctionDeclarationNode) methods.add((FunctionDeclarationNode) node);
            else if(node instanceof VariableDeclarationNode) fields.add((VariableDeclarationNode) node);

            skipSeparators();

        }

        if(this.getInput().next().getType() != TokenType.RCURL) throw new ParserError("Expecting class-body to end");

        return new ClassDeclarationNode(name, fields, methods, classes, access, isInClass, isStatic, isFinal);
    }



    // ****************************************************************************
    // Functions


    private FunctionDeclarationNode functionDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        List<FunctionArgumentNode> args = new ArrayList<>();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_FUNCTION) throw new ParserError("Expecting function keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");
        String name = this.getInput().next().getValue();

        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw new ParserError("Expecting '('");

        if(this.checkArgument()) {
            args.add(this.parseArgument());
            while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.COMMA) {
                this.getInput().skip();
                if(this.checkArgument()) args.add(this.parseArgument());
                else break;
            }
        }

        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");

        Tree body = this.parseBodyStatement();
        return new FunctionDeclarationNode(name, body, args.toArray(new FunctionArgumentNode[0]), access, isInClass, isStatic, isFinal);
    }

    private FunctionCallNode functionCall(ValuedNode function) {
        List<ValuedNode> args = new ArrayList<>();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw new ParserError("Expecting '('");
        if(this.getInput().peek().getType() != TokenType.RPAREN) {
            args.add(this.valuedOperation());
            while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.COMMA) {
                this.getInput().skip();
                ValuedNode operation = this.valuedOperation();
                if(operation != null) args.add(operation);
                else break;
            }
        }
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");
        return new FunctionCallNode(function, args.toArray(new ValuedNode[0]));
    }

    private FunctionArgumentNode parseArgument() {
        if(this.getInput().peek().getType() == TokenType.IDENTIFIER) {
            return new FunctionArgumentNode(this.getInput().next().getValue());
        }
        else throw new ParserError("Expecting identifier");
    }

    private boolean checkArgument() {
        return this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.IDENTIFIER;
    }



    // ****************************************************************************
    // Variables


    private VariableAssignmentNode varAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.ASSIGN) throw new ParserError("Expecting '='");
        Node value = operation();
        return new VariableAssignmentNode(variable, value);
    }

    private VariableAddAssignmentNode varAddAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.ADD_ASSIGN) throw new ParserError("Expecting '+='");
        Node value = operation();
        return new VariableAddAssignmentNode(variable, value);
    }

    private VariableSubAssignmentNode varSubAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.SUB_ASSIGN) throw new ParserError("Expecting '-='");
        Node value = operation();
        return new VariableSubAssignmentNode(variable, value);
    }

    private VariableMulAssignmentNode varMulAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.MUL_ASSIGN) throw new ParserError("Expecting '*='");
        Node value = operation();
        return new VariableMulAssignmentNode(variable, value);
    }

    private VariableDivAssignmentNode varDivAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.DIV_ASSIGN) throw new ParserError("Expecting '/='");
        Node value = operation();
        return new VariableDivAssignmentNode(variable, value);
    }

    private VariableModAssignmentNode varModAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.MOD_ASSIGN) throw new ParserError("Expecting '%='");
        Node value = operation();
        return new VariableModAssignmentNode(variable, value);
    }

    private VariablePowAssignmentNode varPowAssignment(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.POW_ASSIGN) throw new ParserError("Expecting '^='");
        Node value = operation();
        return new VariablePowAssignmentNode(variable, value);
    }

    private VariableIncreaseNode varIncrease(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.INCR) throw new ParserError("Expecting '++'");
        return new VariableIncreaseNode(variable);
    }

    private VariableDecreaseNode varDecrease(ValuedNode variable) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.DECR) throw new ParserError("Expecting '--'");
        return new VariableDecreaseNode(variable);
    }

    private VariableDeclarationNode varDeclaration1(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!getInput().hasNext()) throw new ParserError("Expecting var or const keyword");
        if(this.getInput().next().getType() == TokenType.KEYWORD_CONST) {
            if(isFinal) throw new ParserError("A constant is always final, must not have \"final\" attribute!");
            isFinal = true;
        }
        else if(this.getInput().actual().getType() != TokenType.KEYWORD_VAR) throw new ParserError("Expecting var or const keyword");
        if(!this.getInput().skipIgnorable().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");

        String identifier = this.getInput().next().getValue();

        if(this.getInput().skipIgnorable().hasNext() && this.getInput().peek().getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode(identifier, VariableType.DYNAMIC, this.varAssignment(new IdentifierNode(identifier)), access, isInClass, isStatic, isFinal);
        } else {
            return new VariableDeclarationNode(this.getInput().actual().getValue(), VariableType.DYNAMIC, null, access, isInClass, isStatic, isFinal);
        }

    }

    private VariableDeclarationNode varDeclaration2(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

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

        if(!this.getInput().skipIgnorable().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");

        String identifier = this.getInput().next().getValue();

        if(this.getInput().skipIgnorable().hasNext() && this.getInput().peek().getType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode(identifier, declarationNode, this.varAssignment(new IdentifierNode(identifier)), access, isInClass, isStatic, isFinal);
        } else {
            return new VariableDeclarationNode(this.getInput().actual().getValue(), declarationNode, null, access, isInClass, isStatic, isFinal);
        }
    }






    // ****************************************************************************
    // Loops & If


    private Node forLoop() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_FOR) throw new ParserError("Expecting for keyword");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw new ParserError("Expecting '('");
        Node declaration = operation();
        awaitSemicolon();
        ValuedNode condition = valuedOperation();
        awaitSemicolon();
        Node round = operation();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");
        Tree body = parseBodyStatement();
        return new ForNode(body, declaration, condition, round);
    }


    private Node doWhileLoop() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_DO) throw new ParserError("Expecting do keyword");
        Tree body = parseBodyStatement();
        skipSeparators();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_WHILE) throw new ParserError("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        return new DoWhileNode(body, condition);
    }


    private Node whileLoop() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_WHILE) throw new ParserError("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        return new WhileNode(body, condition);
    }


    private Node ifStatement() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_IF) throw new ParserError("Expecting if keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        boolean separator = skipSeparators()>0;
        if(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.KEYWORD_ELSE) {
            if(!separator) throw new ParserError("Awaited separator at this point");
            this.getInput().skip();
            Tree elseBody = parseBodyStatement();
            return new IfNode(body, elseBody, condition);
        }
        return new IfNode(body, condition);
    }

    private ValuedNode parseConditionStatement() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw new ParserError("Expecting '('", getInput().getPosition());
        ValuedNode condition = logicalOr();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw new ParserError("Expecting ')'", getInput().getPosition());
        return condition;
    }

    private Tree parseBodyStatement() {
        skipSeparators();
        if(this.getInput().peek().getType() == TokenType.LCURL) {
            this.getInput().skip();
            Tree body = prog();
            if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RCURL) throw new ParserError("Expecting '}'", getInput().getPosition());
            return body;
        }
        else {
            return new Tree(new Node[] { this.operation() });
        }
    }



    // ****************************************************************************
    // Statements

    List<TokenType> LOGICAL = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS,
            TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS, TokenType.LOGICAL_OR,
            TokenType.LOGICAL_AND);


    // (Factor)

    private ValuedNode factor() {

        Token token = this.getInput().peek();

        if(token.getType() == TokenType.LPAREN) {
            getInput().skip();
            ValuedNode result = this.logicalOr();
            if(this.getInput().next().getType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");
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
            return new IntegerNode(Integer.parseInt(token.getValue()));
        }

        if(token.getType() == TokenType.DOUBLE) {
            getInput().skip();
            return new DoubleNode(Double.parseDouble(token.getValue()));
        }

        if(token.getType() == TokenType.IDENTIFIER) {
            return parseIdentifier(null);
        }

        if(token.getType() == TokenType.KEYWORD_NEW) {
            return parseClassConstruction();
        }

        if(token.getType() == TokenType.ADD) {
            getInput().skip();
            return new AddNode(0, this.factor());
        }

        if(token.getType() == TokenType.SUB) {
            getInput().skip();
            return new SubNode(0, this.factor());
        }

        throw new ParserError(this.getInput().toString());
    }


    // (Calculations)

    List<TokenType> EXPR = Arrays.asList(TokenType.ADD, TokenType.SUB);
    List<TokenType> TERM = Arrays.asList(TokenType.MUL, TokenType.DIV, TokenType.MOD);

    private ValuedNode expr() {
        ValuedNode result = this.term();

        while(this.getInput().hasNext() && EXPR.contains(this.getInput().peek().getType())) {
            if(this.getInput().peek().getType() == TokenType.ADD) {
                this.getInput().skip();
                result = new AddNode(result, this.term());
            }
            else if(this.getInput().peek().getType() == TokenType.SUB) {
                this.getInput().skip();
                result = new SubNode(result, this.term());
            }
        }

        return result;
    }

    private ValuedNode term() {
        ValuedNode result = this.pow();

        while(this.getInput().hasNext() && TERM.contains(this.getInput().peek().getType())) {
            if(this.getInput().peek().getType() == TokenType.MUL) {
                this.getInput().skip();
                result = new MulNode(result, this.pow());
            }
            else if(this.getInput().peek().getType() == TokenType.DIV) {
                this.getInput().skip();
                result = new DivNode(result, this.pow());
            }
            else if(this.getInput().peek().getType() == TokenType.MOD) {
                this.getInput().skip();
                result = new ModNode(result, this.pow());
            }
        }
        return result;
    }

    private ValuedNode pow() {
        ValuedNode result = this.factor();

        while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.POW) {
            this.getInput().skip();
            result = new PowNode(result, this.factor());
        }
        return result;
    }


    // (Logical)

    List<TokenType> COMPARE = Arrays.asList(TokenType.BIGGER, TokenType.BIGGER_EQUALS,
            TokenType.SMALLER, TokenType.SMALLER_EQUALS, TokenType.EQ_EQUALS);

    private ValuedNode logicalOr() {
        ValuedNode result = this.logicalAnd();

        while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.LOGICAL_OR) {
            this.getInput().skip();
            result = new LogicalOrNode(result, this.logicalAnd());
        }
        return result;
    }


    private ValuedNode logicalAnd() {
        ValuedNode result = this.compare();

        while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.LOGICAL_AND) {
            this.getInput().skip();
            result = new LogicalAndNode(result, this.compare());
        }
        return result;
    }


    private ValuedNode compare() {
        ValuedNode left = this.expr();

        while(this.getInput().hasNext() && COMPARE.contains(this.getInput().peek().getType())) {

            Token comparer = this.getInput().next();
            if(comparer.getType() == TokenType.EQ_EQUALS) return new LogicalEqEqualsNode(left, this.logicalOr());
            else if(comparer.getType() == TokenType.BIGGER_EQUALS) left = new LogicalBiggerEqualsNode(left, this.logicalOr());
            else if(comparer.getType() == TokenType.SMALLER_EQUALS) left = new LogicalSmallerEqualsNode(left, this.logicalOr());
            else if(comparer.getType() == TokenType.BIGGER) left = new LogicalBiggerNode(left, this.logicalOr());
            else left = new LogicalSmallerNode(left, this.logicalOr());

        }
        return left;
    }



    // ****************************************************************************
    // Errors
    

    public class ParserError extends CompilerError {

        public ParserError (String message, String name, String details, Position start, Position end) {
            super(message, name, details, start, end);
        }

        public ParserError (String name, String details, Position start, Position end) {
            this("Error occurred in parser: " + name + ", " + details + " in " + start.getSource() + ":" + start.getLine() + ":" + start.getColumn(),name, details, start, end);
        }

        public ParserError (String details, Position start, Position end) {
            this("ParserError", details, start, end);
        }

        public ParserError (String details, int start, int end) {
            this("ParserError", details, getInput().getMap().resolve(start), getInput().getMap().resolve(end));
        }

        public ParserError (String error, int position) {
            this(error,
                    getInput().getMap().resolve(getInput().get(position).getStart()),
                    getInput().getMap().resolve(getInput().get(position).getEnd()));
        }

        public ParserError (String error) {
            this(error,
                    getInput().getMap().resolve(getInput().peek().getStart()),
                    getInput().getMap().resolve(getInput().peek().getEnd()));
        }

    }

    public TokenInputStream getInput() {
        return this.in;
    }
}
