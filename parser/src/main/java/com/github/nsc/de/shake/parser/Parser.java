package com.github.nsc.de.shake.parser;

import com.github.nsc.de.shake.lexer.token.TokenInputStream;
import com.github.nsc.de.shake.lexer.token.TokenType;
import com.github.nsc.de.shake.parser.node.*;
import com.github.nsc.de.shake.parser.node.expression.*;
import com.github.nsc.de.shake.parser.node.factor.CharacterNode;
import com.github.nsc.de.shake.parser.node.factor.DoubleNode;
import com.github.nsc.de.shake.parser.node.factor.IntegerNode;
import com.github.nsc.de.shake.parser.node.factor.StringNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.shake.parser.node.functions.ReturnNode;
import com.github.nsc.de.shake.parser.node.logical.*;
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode;
import com.github.nsc.de.shake.parser.node.loops.ForNode;
import com.github.nsc.de.shake.parser.node.loops.WhileNode;
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode;
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.shake.parser.node.objects.ConstructorDeclarationNode;
import com.github.nsc.de.shake.parser.node.variables.*;
import com.github.nsc.de.shake.util.Characters;
import com.github.nsc.de.shake.util.CompilerError;
import com.github.nsc.de.shake.util.characterinput.position.Position;
import com.github.nsc.de.shake.util.characterinput.position.PositionMap;

import java.util.ArrayList;
import java.util.List;

import static com.github.nsc.de.shake.lexer.token.TokenType.*;

public class Parser {

    private final TokenInputStream in;
    private final PositionMap map;

    public Parser(TokenInputStream in) {
        this.in = in;
        map = in.getMap();
    }

    public Tree parse() {
        if(!this.in.hasNext()) return new Tree(map, new Node[]{});

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

        while (this.in.hasNext()) {

            // if(!separator) throw new ParserError("AwaitSeparatorError", "Awaited separator at this point");
            // separator = false;
            if(position >= this.in.getPosition()) break;
            position = this.in.getPosition();

            if(this.in.hasNext()) {
                Node result = operation();
                if(result != null) nodes.add(result);
            }

            // if(this.skipSeparators() > 0) separator = true;
            skipSeparators();

        }
        return new Tree(map, nodes);
    }

    private Node operation() {

        byte token = this.in.peekType();
        if(token == TokenType.KEYWORD_WHILE) return this.whileLoop();
        if(token == TokenType.KEYWORD_DO) return this.doWhileLoop();
        if(token == TokenType.KEYWORD_FOR) return this.forLoop();
        if(token == TokenType.KEYWORD_IF) return this.ifStatement();
        if(token == TokenType.KEYWORD_RETURN) return this.returnStatement();
        if(token == TokenType.KEYWORD_IMPORT) return this.parseImport();

        return this.valuedOperation();
    }

    private ValuedNode valuedOperation() {

        byte token = this.in.peekType();

        if(token == TokenType.KEYWORD_FUNCTION
                || token == TokenType.KEYWORD_VAR
                || token == TokenType.KEYWORD_CONST
                || token == TokenType.KEYWORD_CLASS
                || token == TokenType.KEYWORD_PUBLIC
                || token == TokenType.KEYWORD_PROTECTED
                || token == TokenType.KEYWORD_PRIVATE
                || token == TokenType.KEYWORD_FINAL
                || token == TokenType.KEYWORD_STATIC
                || token == TokenType.KEYWORD_DYNAMIC
                || token == TokenType.KEYWORD_BYTE
                || token == TokenType.KEYWORD_SHORT
                || token == TokenType.KEYWORD_INT
                || token == TokenType.KEYWORD_LONG
                || token == TokenType.KEYWORD_FLOAT
                || token == TokenType.KEYWORD_DOUBLE
                || token == TokenType.KEYWORD_BOOLEAN
                || token == TokenType.KEYWORD_CHAR
                || token == TokenType.KEYWORD_VOID) return parseDeclaration();

        // Expression
        if(token == TokenType.INTEGER ||
                token == TokenType.DOUBLE ||
                token == TokenType.KEYWORD_TRUE ||
                token == TokenType.KEYWORD_FALSE ||
                token == TokenType.IDENTIFIER ||
                token == TokenType.KEYWORD_NEW ||
                token == TokenType.STRING ||
                token == TokenType.CHARACTER)
            return this.logicalOr();

        return null;

    }



    // ****************************************************************************
    // Utils
    private int skipSeparators() {

        int number = 0;
        while(this.in.hasNext() && (this.in.peekType() == TokenType.SEMICOLON
                || this.in.peekType() == TokenType.LINE_SEPARATOR)) {
            number++;
            this.in.skip();
        }
        return number;

    }

    private void awaitSemicolon() {

        if(this.in.skipIgnorable().nextType() != TokenType.SEMICOLON) throw new ParserError("Expecting semicolon at this point", this.in.getPosition());

    }

    private ValuedNode parseDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        TokenInputStream input = getInput();

        switch(input.peekType()) {
            case TokenType.KEYWORD_PUBLIC: input.skip(); return parseDeclaration(AccessDescriber.PUBLIC, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_PROTECTED: input.skip(); return parseDeclaration(AccessDescriber.PROTECTED, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_PRIVATE: input.skip(); return parseDeclaration(AccessDescriber.PRIVATE, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_STATIC:
                if(!isInClass) throw new ParserError("Static keyword is only for objects in classes");
                input.skip();
                return parseDeclaration(access, true, true, isFinal);
            case TokenType.KEYWORD_FINAL: input.skip(); return parseDeclaration(access, isInClass, isStatic, true);
            case TokenType.KEYWORD_FUNCTION: return functionDeclaration(access, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_CLASS: return classDeclaration(access, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_CONSTRUCTOR: return constructorDeclaration(access, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_CONST:
            case TokenType.KEYWORD_VAR:
                return varDeclaration1(access, isInClass, isStatic, isFinal);
            case TokenType.KEYWORD_DYNAMIC:
            case TokenType.KEYWORD_BOOLEAN:
            case TokenType.KEYWORD_CHAR:
            case TokenType.KEYWORD_BYTE:
            case TokenType.KEYWORD_SHORT:
            case TokenType.KEYWORD_INT:
            case TokenType.KEYWORD_LONG:
            case TokenType.KEYWORD_FLOAT:
            case TokenType.KEYWORD_DOUBLE:
            case TokenType.KEYWORD_VOID:
            case TokenType.IDENTIFIER:
                return cStyleDeclaration(access, isInClass, isStatic, isFinal);
            default:
                throw new ParserError("Unexpected token (" + input.peekType() + ')');
        }

    }

    private ValuedNode parseDeclaration(boolean isInClass) {
        return parseDeclaration(AccessDescriber.PACKAGE, isInClass, false, false);
    }

    private ValuedNode parseDeclaration() {
        return parseDeclaration(false);
    }

    private ValuedNode parseIdentifier(ValuedNode parent) {
        if(in.nextType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");

        IdentifierNode identifierNode = new IdentifierNode(map, parent, in.actualValue(), in.actualStart());
        ValuedNode ret = null;

        // Assignments
        if(this.in.hasNext()) {

            byte token2 = in.skipIgnorable().peekType();
            if(token2 == TokenType.LPAREN) ret = this.functionCall(identifierNode);
            else if(token2 == TokenType.ASSIGN) ret = this.varAssignment(identifierNode);
            else if(token2 == TokenType.IDENTIFIER) ret = this.cStyleDeclaration(new VariableType(identifierNode),
                    AccessDescriber.PRIVATE, false, false, false);
            else if(token2 == TokenType.ADD_ASSIGN) ret = this.varAddAssignment(identifierNode);
            else if(token2 == TokenType.SUB_ASSIGN) ret = this.varSubAssignment(identifierNode);
            else if(token2 == TokenType.MUL_ASSIGN) ret = this.varMulAssignment(identifierNode);
            else if(token2 == TokenType.DIV_ASSIGN) ret = this.varDivAssignment(identifierNode);
            else if(token2 == TokenType.MOD_ASSIGN) ret = this.varModAssignment(identifierNode);
            else if(token2 == TokenType.POW_ASSIGN) ret = this.varPowAssignment(identifierNode);
            else if(token2 == TokenType.INCR) ret = this.varIncrease(identifierNode);
            else if(token2 == TokenType.DECR) ret = this.varDecrease(identifierNode);
            if(in.skipIgnorable().hasNext() && in.peekType() == TokenType.DOT) {
                this.in.skip();
                this.in.skipIgnorable();
                return this.parseIdentifier(ret != null ? ret : new VariableUsageNode(map, identifierNode));
            }
            if(ret != null) return ret;

        }
        return new VariableUsageNode(map, identifierNode);

    }

    private ClassConstructionNode parseClassConstruction() {

        this.in.skip();
        int newKeywordPosition = this.in.actualStart();
        this.in.skipIgnorable();
        int start = in.actualStart();
        ValuedNode node = parseIdentifier(null);
        if(!(node instanceof FunctionCallNode))
            throw new ParserError("Expecting a call after keyword new",
                    start, in.actualEnd());
        return new ClassConstructionNode(map, ((FunctionCallNode) node).getFunction(), ((FunctionCallNode) node).getArgs(),
                newKeywordPosition);
    }



    // ****************************************************************************
    // Imports

    private ImportNode parseImport() {
        if(!this.in.hasNext() || this.in.peekType() != TokenType.KEYWORD_IMPORT) throw new ParserError("Expecting import keyword");

        ArrayList<String> list = new ArrayList<>();

        do {

            this.in.skip();

            if(in.skipIgnorable().nextType() == TokenType.MUL) {

                list.add(ImportNode.EVERYTHING);
                break;

            }
            if(in.actualType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");
            list.add(in.actualValue());

        } while(getInput().hasNext() && in.skipIgnorable().peekType() == TokenType.DOT);


        return new ImportNode(map, list.toArray(new String[] {}));
    }



    // ****************************************************************************
    // Classes


    private ClassDeclarationNode classDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_CLASS) throw new ParserError("Expecting class keyword");
        if(!this.in.hasNext() || this.in.peekType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");
        String name = this.in.nextValue();

        List<VariableDeclarationNode> fields = new ArrayList<>();
        List<FunctionDeclarationNode> methods = new ArrayList<>();
        List<ClassDeclarationNode> classes = new ArrayList<>();
        List<ConstructorDeclarationNode> constructors = new ArrayList<>();

        // TODO: extends, implements
        if(this.in.nextType() != TokenType.LCURL) throw new ParserError("Expecting class-body");

        while(this.in.hasNext() && this.in.peekType() != TokenType.RCURL) {

            skipSeparators();

            ValuedNode node = parseDeclaration(true);
            if(node instanceof ClassDeclarationNode) classes.add((ClassDeclarationNode) node);
            else if(node instanceof FunctionDeclarationNode) methods.add((FunctionDeclarationNode) node);
            else if(node instanceof VariableDeclarationNode) fields.add((VariableDeclarationNode) node);
            else if(node instanceof ConstructorDeclarationNode) constructors.add((ConstructorDeclarationNode) node);

            skipSeparators();

        }

        if(this.in.nextType() != TokenType.RCURL) throw new ParserError("Expecting class-body to end");

        return new ClassDeclarationNode(map, name, fields, methods, classes, constructors, access, isInClass,
                isStatic, isFinal);
    }



    // ****************************************************************************
    // Functions


    private FunctionDeclarationNode functionDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_FUNCTION) throw new ParserError("Expecting function keyword");
        if(!this.in.hasNext() || this.in.peekType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");
        String name = this.in.nextValue();

        FunctionArgumentNode[] args = parseFunctionArguments();
        Tree body = this.parseBodyStatement();

        return new FunctionDeclarationNode(map, name, body, args, access, isInClass, isStatic, isFinal);
    }


    private FunctionDeclarationNode cStyleFunctionDeclaration(VariableType type, String identifier, AccessDescriber access,
                                                              boolean isInClass, boolean isStatic, boolean isFinal) {

        FunctionArgumentNode[] args = parseFunctionArguments();
        Tree body = this.parseBodyStatement();

        return new FunctionDeclarationNode(map, identifier, body, args, type,
                access, isInClass, isStatic, isFinal);
    }

    private FunctionArgumentNode[] parseFunctionArguments() {

        ArrayList<FunctionArgumentNode> args = new ArrayList<>();

        if(!this.in.hasNext() || this.in.nextType() != TokenType.LPAREN) throw new ParserError("Expecting '('");

        if(this.in.hasNext() && this.in.peekType() != TokenType.RPAREN) {
            args.add(this.parseArgument());
            while(this.in.hasNext() && this.in.peekType() == TokenType.COMMA) {
                this.in.skip();
                if(this.in.hasNext() && this.in.peekType() != TokenType.RPAREN) args.add(this.parseArgument());
                else break;
            }
        }

        if(!this.in.hasNext() || this.in.nextType() != TokenType.RPAREN) throw new ParserError("Expecting ')'", getInput().getPosition());
        return args.toArray(new FunctionArgumentNode[0]);

    }

    private FunctionCallNode functionCall(ValuedNode function) {
        List<ValuedNode> args = new ArrayList<>();
        if(!this.in.hasNext() || this.in.nextType() != TokenType.LPAREN) throw new ParserError("Expecting '('");
        if(this.in.peekType() != TokenType.RPAREN) {
            args.add(this.valuedOperation());
            while(this.in.hasNext() && this.in.peekType() == TokenType.COMMA) {
                this.in.skip();
                ValuedNode operation = this.valuedOperation();
                if(operation != null) args.add(operation);
                else break;
            }
        }
        if(!this.in.hasNext() || this.in.nextType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");
        return new FunctionCallNode(map, function, args.toArray(new ValuedNode[0]));
    }

    private FunctionArgumentNode parseArgument() {

        byte next = this.in.nextType();
        this.in.skipIgnorable();

        byte peek;
        if(next == TokenType.IDENTIFIER && (!this.in.hasNext() || (peek = this.in.peekType()) != TokenType.IDENTIFIER && peek != TokenType.DOT))
            return new FunctionArgumentNode(map, this.in.actualValue());

        VariableType type;

        switch(next) {
            case TokenType.KEYWORD_DYNAMIC: type = VariableType.DYNAMIC; break;
            case TokenType.KEYWORD_BOOLEAN: type = VariableType.BOOLEAN; break;
            case TokenType.KEYWORD_CHAR: type = VariableType.CHAR; break;
            case TokenType.KEYWORD_BYTE: type = VariableType.BYTE; break;
            case TokenType.KEYWORD_SHORT: type = VariableType.SHORT; break;
            case TokenType.KEYWORD_INT: type = VariableType.INTEGER; break;
            case TokenType.KEYWORD_LONG: type = VariableType.LONG; break;
            case TokenType.KEYWORD_FLOAT: type = VariableType.FLOAT; break;
            case TokenType.KEYWORD_DOUBLE: type = VariableType.DOUBLE; break;
            case TokenType.IDENTIFIER:
                IdentifierNode node = new IdentifierNode(map, this.getInput().actualValue(),
                        this.getInput().getPosition());
                while(this.in.peekType() == TokenType.DOT) {
                    this.in.skip();
                    this.in.skipIgnorable();
                    if(this.getInput().nextType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");
                    node = new IdentifierNode(map, this.in.actualValue(), this.in.actualStart());
                }
                type = new VariableType(node);
                break;
            default:
                throw new ParserError("Unknown variable-type token: " + TokenType.getName(next));
        }


        if(this.in.hasNext() && this.in.peekType() == TokenType.IDENTIFIER) {

            String identifier = this.in.nextValue();
            this.in.skipIgnorable();
            return new FunctionArgumentNode(map, identifier, type);

        }
        else throw new ParserError("Expecting identifier");
    }


    private ReturnNode returnStatement() {
        this.in.skip();
        return new ReturnNode(map, valuedOperation());
    }

    private ConstructorDeclarationNode constructorDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_CONSTRUCTOR) throw new ParserError("Expecting function keyword");
        if(!isInClass) throw new ParserError("A constructor must be inside of a class");
        if(isFinal) throw new ParserError("A constructor must not be final");
        if(isStatic) throw new ParserError("A constructor must not be static");

        String name = this.in.skipIgnorable().peekType() == TokenType.IDENTIFIER ? this.in.nextValue() : null;

        FunctionArgumentNode[] args = parseFunctionArguments();
        Tree body = this.parseBodyStatement();

        return new ConstructorDeclarationNode(map, name, body, args, access);
    }



    // ****************************************************************************
    // Variables


    private VariableAssignmentNode varAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.ASSIGN) throw new ParserError("Expecting '='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariableAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariableAddAssignmentNode varAddAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.ADD_ASSIGN) throw new ParserError("Expecting '+='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariableAddAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariableSubAssignmentNode varSubAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.SUB_ASSIGN) throw new ParserError("Expecting '-='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariableSubAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariableMulAssignmentNode varMulAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.MUL_ASSIGN) throw new ParserError("Expecting '*='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariableMulAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariableDivAssignmentNode varDivAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.DIV_ASSIGN) throw new ParserError("Expecting '/='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariableDivAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariableModAssignmentNode varModAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.MOD_ASSIGN) throw new ParserError("Expecting '%='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariableModAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariablePowAssignmentNode varPowAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.POW_ASSIGN) throw new ParserError("Expecting '**='");
        int operatorPosition = this.in.actualStart();
        Node value = operation();
        return new VariablePowAssignmentNode(map, variable, value, operatorPosition);
    }

    private VariableIncreaseNode varIncrease(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.INCR) throw new ParserError("Expecting '++'");
        return new VariableIncreaseNode(map, variable, this.in.actualStart());
    }

    private VariableDecreaseNode varDecrease(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.DECR) throw new ParserError("Expecting '--'");
        return new VariableDecreaseNode(map, variable, this.in.actualStart());
    }

    private VariableDeclarationNode varDeclaration1(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!in.hasNext()) throw new ParserError("Expecting var or const keyword");
        if(this.in.nextType() == TokenType.KEYWORD_CONST) {
            if(isFinal) throw new ParserError("A constant is always final, must not have \"final\" attribute!");
            isFinal = true;
        }
        else if(this.in.actualType() != TokenType.KEYWORD_VAR) throw new ParserError("Expecting var or const keyword");
        if(!this.in.skipIgnorable().hasNext() || this.in.peekType() != TokenType.IDENTIFIER) throw new ParserError("Expecting identifier");

        String identifier = this.in.nextValue();
        int pos = in.actualStart();

        if(this.in.skipIgnorable().hasNext() && this.in.peekType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode(map, identifier, VariableType.DYNAMIC,
                    this.varAssignment(new IdentifierNode(map, identifier, pos)),
                    access, isInClass, isStatic, isFinal);
        } else {
            return new VariableDeclarationNode(map, this.in.actualValue(), VariableType.DYNAMIC, null, access, isInClass, isStatic, isFinal);
        }

    }

    private ValuedNode cStyleDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        byte t = this.in.nextType();
        VariableType declarationNode =
                t == TokenType.KEYWORD_DYNAMIC ? VariableType.DYNAMIC :
                t == TokenType.KEYWORD_BYTE ? VariableType.BYTE :
                t == TokenType.KEYWORD_SHORT ? VariableType.SHORT :
                t == TokenType.KEYWORD_INT ? VariableType.INTEGER :
                t == TokenType.KEYWORD_LONG ? VariableType.LONG :
                t == TokenType.KEYWORD_FLOAT ? VariableType.FLOAT :
                t == TokenType.KEYWORD_DOUBLE ? VariableType.DOUBLE :
                t == TokenType.KEYWORD_BOOLEAN ? VariableType.BOOLEAN :
                t == TokenType.KEYWORD_CHAR ? VariableType.CHAR :
                t == TokenType.KEYWORD_VOID ? VariableType.VOID :
                t == TokenType.IDENTIFIER ? VariableType.OBJECT : null;

        return cStyleDeclaration(declarationNode, access, isInClass, isStatic, isFinal);
    }



    private ValuedNode cStyleDeclaration(VariableType type, AccessDescriber access, boolean isInClass, boolean isStatic,
                                         boolean isFinal) {

        // TODO error on void variable type

        if(!this.in.skipIgnorable().hasNext() || this.in.peekType() != TokenType.IDENTIFIER)
            throw new ParserError("Expecting identifier");

        String identifier = this.in.nextValue();
        int position = in.actualStart();

        boolean hasNext = this.in.skipIgnorable().hasNext();
        if(hasNext && this.in.peekType() == TokenType.ASSIGN) {
            return new VariableDeclarationNode(map, identifier, type,
                    this.varAssignment(new IdentifierNode(map, identifier, position)), access, isInClass, isStatic, isFinal);
        } else if(hasNext && this.in.peekType() == TokenType.LPAREN)
            return cStyleFunctionDeclaration(type, identifier, access, isInClass, isStatic, isFinal);
        else return new VariableDeclarationNode(map, this.in.actualValue(), type, null, access, isInClass,
                    isStatic, isFinal);
    }






    // ****************************************************************************
    // Loops & If


    private Node forLoop() {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_FOR) throw new ParserError("Expecting for keyword");
        if(!this.in.hasNext() || this.in.nextType() != TokenType.LPAREN) throw new ParserError("Expecting '('");
        Node declaration = operation();
        awaitSemicolon();
        ValuedNode condition = valuedOperation();
        awaitSemicolon();
        Node round = operation();
        if(!this.in.hasNext() || this.in.nextType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");
        Tree body = parseBodyStatement();
        return new ForNode(map, body, declaration, condition, round);
    }


    private Node doWhileLoop() {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_DO)
            throw new ParserError("Expecting do keyword");
        Tree body = parseBodyStatement();
        skipSeparators();
        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_WHILE)
            throw new ParserError("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        return new DoWhileNode(map, body, condition);
    }


    private Node whileLoop() {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_WHILE)
            throw new ParserError("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        return new WhileNode(map, body, condition);
    }


    private Node ifStatement() {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.KEYWORD_IF)
            throw new ParserError("Expecting if keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        boolean separator = skipSeparators()>0;
        if(this.in.hasNext() && this.in.peekType() == TokenType.KEYWORD_ELSE) {
            if(!separator) throw new ParserError("Awaited separator at this point");
            this.in.skip();
            Tree elseBody = parseBodyStatement();
            return new IfNode(map, body, elseBody, condition);
        }
        return new IfNode(map, body, condition);
    }

    private ValuedNode parseConditionStatement() {
        if(!this.in.hasNext() || this.in.nextType() != TokenType.LPAREN) throw new ParserError("Expecting '('", in.getPosition());
        ValuedNode condition = logicalOr();
        if(!this.in.hasNext() || this.in.nextType() != TokenType.RPAREN) throw new ParserError("Expecting ')'", in.getPosition());
        return condition;
    }

    private Tree parseBodyStatement() {
        skipSeparators();
        if(this.in.peekType() == TokenType.LCURL) {
            this.in.skip();
            Tree body = prog();
            if(!this.in.hasNext() || this.in.nextType() != TokenType.RCURL) throw new ParserError("Expecting '}'", in.getPosition());
            return body;
        }
        else {
            return new Tree(map, new Node[] { this.operation() });
        }
    }



    // ****************************************************************************
    // Statements


    // (Factor)

    private ValuedNode factor() {

        byte token = this.in.peekType();

        if(token == TokenType.LPAREN) {
            in.skip();
            ValuedNode result = this.logicalOr();
            if(this.in.nextType() != TokenType.RPAREN) throw new ParserError("Expecting ')'");
            return result;
        }

        if(token == TokenType.KEYWORD_TRUE) {
            in.skip();
            return new LogicalTrueNode(map);
        }

        if(token == TokenType.KEYWORD_FALSE) {
            in.skip();
            return new LogicalFalseNode(map);
        }

        if(token == TokenType.INTEGER) {
            in.skip();
            return new IntegerNode(map, Integer.parseInt(in.actualValue()));
        }

        if(token == TokenType.DOUBLE) {
            in.skip();
            return new DoubleNode(map, Double.parseDouble(in.actualValue()));
        }

        if(token == TokenType.IDENTIFIER) {
            return parseIdentifier(null);
        }

        if(token == TokenType.KEYWORD_NEW) {
            return parseClassConstruction();
        }

        if(token == TokenType.ADD) {
            in.skip();
            return new AddNode(map, 0, this.factor(), in.getPosition());
        }

        if(token == TokenType.SUB) {
            in.skip();
            return new SubNode(map, 0, this.factor(), in.getPosition());
        }

        if(token == TokenType.STRING) {
            in.skip();
            return new StringNode(map, Characters.parseString(in.actualValue()));
        }

        if(token == TokenType.CHARACTER) {
            in.skip();
            return new CharacterNode(map, Characters.parseString(in.actualValue()).charAt(0));
        }

        throw new ParserError(this.in.toString());
    }


    // Casting

    private ValuedNode cast() {
        ValuedNode result = this.factor();
        while(this.in.skipIgnorable().hasNext() && this.in.peekType() == TokenType.KEYWORD_AS) {
            this.in.skip();

            CastNode.CastTarget target;
            switch (this.in.skipIgnorable().peekType()) {
                case TokenType.KEYWORD_BYTE: target = CastNode.CastTarget.BYTE; this.in.skip(); break;
                case TokenType.KEYWORD_SHORT: target = CastNode.CastTarget.SHORT; this.in.skip(); break;
                case TokenType.KEYWORD_INT: target = CastNode.CastTarget.INTEGER; this.in.skip(); break;
                case TokenType.KEYWORD_LONG: target = CastNode.CastTarget.LONG; this.in.skip(); break;
                case TokenType.KEYWORD_FLOAT: target = CastNode.CastTarget.FLOAT; this.in.skip(); break;
                case TokenType.KEYWORD_DOUBLE: target = CastNode.CastTarget.DOUBLE; this.in.skip(); break;
                case TokenType.KEYWORD_BOOLEAN: target = CastNode.CastTarget.BOOLEAN; this.in.skip(); break;
                case TokenType.KEYWORD_CHAR: target = CastNode.CastTarget.CHAR; this.in.skip(); break;
                case TokenType.IDENTIFIER:
                    IdentifierNode node = null;
                    do {
                        if(node != null) this.in.skip();
                        if(!this.in.skipIgnorable().hasNext() && this.in.nextType() != TokenType.IDENTIFIER)
                            throw new ParserError("Expecting identifier");
                        node = new IdentifierNode(map, node, this.in.actualValue(), this.in.actualStart());
                    } while(this.in.skipIgnorable().hasNext() && in.peekType() == TokenType.DOT);
                    target = new CastNode.CastTarget(node);
                    break;
                default:
                    throw new ParserError("Expecting cast-target");
            }


            result = new CastNode(map, result, target);

        }

        return result;
    }


    // (Calculations)

    private ValuedNode expr() {
        ValuedNode result = this.term();
        byte tmp_type;
        while(this.in.hasNext() &&
                ((tmp_type = this.in.peekType()) == TokenType.ADD || tmp_type == TokenType.SUB)) {

            this.in.skip();
            int pos = this.in.actualStart();

            if(tmp_type == TokenType.ADD) {
                result = new AddNode(map, result, this.term(), pos);
            }

            else {
                result = new SubNode(map, result, this.term(), pos);
            }

        }

        return result;
    }

    private ValuedNode term() {
        ValuedNode result = this.pow();

        byte tmp_type;
        while(this.in.hasNext() &&
                ((tmp_type = this.in.peekType()) == TokenType.MUL || tmp_type == TokenType.DIV || tmp_type == TokenType.MOD)) {

            this.in.skip();
            int pos = this.in.actualStart();

            if(tmp_type == TokenType.MUL) {
                result = new MulNode(map, result, this.pow(), pos);
            }
            else if(tmp_type == TokenType.DIV) {
                result = new DivNode(map, result, this.pow(), pos);
            }
            else {
                result = new ModNode(map, result, this.pow(), pos);
            }
        }
        return result;
    }

    private ValuedNode pow() {
        ValuedNode result = this.cast();

        while(this.in.hasNext() && this.in.peekType() == TokenType.POW) {
            this.in.skip();
            int pos = this.in.actualStart();
            result = new PowNode(map, result, this.cast(), pos);
        }
        return result;
    }


    // (Logical)

    private ValuedNode logicalOr() {
        ValuedNode result = this.logicalXOr();

        while(this.in.hasNext() && this.in.peekType() == TokenType.LOGICAL_OR) {
            this.in.skip();
            result = new LogicalOrNode(map, result, this.logicalXOr());
        }
        return result;
    }


    private ValuedNode logicalXOr() {
        ValuedNode result = this.logicalAnd();

        while(this.in.hasNext() && this.in.peekType() == TokenType.LOGICAL_XOR) {
            this.in.skip();
            result = new LogicalXOrNode(map, result, this.logicalAnd());
        }
        return result;
    }


    private ValuedNode logicalAnd() {
        ValuedNode result = this.compare();

        while(this.in.hasNext() && this.in.peekType() == TokenType.LOGICAL_AND) {
            this.in.skip();
            result = new LogicalAndNode(map, result, this.compare());
        }
        return result;
    }


    private ValuedNode compare() {
        ValuedNode left = this.expr();

        byte tmp_type;
        while(this.in.hasNext()
                && ((tmp_type = this.in.peekType()) == TokenType.EQ_EQUALS || tmp_type == TokenType.BIGGER_EQUALS
                    || tmp_type == TokenType.SMALLER_EQUALS || tmp_type == TokenType.BIGGER || tmp_type == TokenType.SMALLER)) {

            this.in.skip();
            if(tmp_type == TokenType.EQ_EQUALS) return new LogicalEqEqualsNode(map, left, this.logicalOr());
            else if(tmp_type == TokenType.BIGGER_EQUALS) left = new LogicalBiggerEqualsNode(map, left, this.logicalOr());
            else if(tmp_type == TokenType.SMALLER_EQUALS) left = new LogicalSmallerEqualsNode(map, left, this.logicalOr());
            else if(tmp_type == TokenType.BIGGER) left = new LogicalBiggerNode(map, left, this.logicalOr());
            else left = new LogicalSmallerNode(map, left, this.logicalOr());

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
            this("ParserError", details, in.getMap().resolve(start), in.getMap().resolve(end));
        }

        @SuppressWarnings("deprecated")
        public ParserError (String error, int position) {
            this(error,
                    in.getMap().resolve(in.getStart(position)),
                    in.getMap().resolve(in.getEnd(position)));
        }

        public ParserError (String error) {
            this(error,
                    in.getMap().resolve(in.peekStart()),
                    in.getMap().resolve(in.peekEnd()));
        }

    }

    public TokenInputStream getInput() {
        return this.in;
    }
}
