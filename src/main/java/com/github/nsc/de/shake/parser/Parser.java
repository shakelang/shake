package com.github.nsc.de.shake.parser;

import com.github.nsc.de.shake.lexer.characterinput.position.Position;
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
import com.github.nsc.de.shake.parser.node.variables.*;
import com.github.nsc.de.shake.util.Characters;
import com.github.nsc.de.shake.util.CompilerError;

import java.util.ArrayList;
import java.util.List;

import static com.github.nsc.de.shake.lexer.token.TokenType.*;

public class Parser {

    private final TokenInputStream in;

    public Parser(TokenInputStream in) {
        this.in = in;
    }

    public Tree parse() {
        if(!this.in.hasNext()) return new Tree(new Node[]{});

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
        return new Tree(nodes);
    }

    private Node operation() {

        byte token = this.in.peekType();
        if(token == TokenType.KEYWORD_WHILE) return this.whileLoop();
        if(token == TokenType.KEYWORD_DO) return this.doWhileLoop();
        if(token == TokenType.KEYWORD_FOR) return this.forLoop();
        if(token == TokenType.KEYWORD_IF) return this.ifStatement();
        if(token == TokenType.KEYWORD_RETURN) return this.returnStatement();

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
                || token == TokenType.KEYWORD_CHAR) return parseDeclaration();

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
        String identifier = in.actualValue();

        IdentifierNode identifierNode = new IdentifierNode(parent, identifier);
        ValuedNode ret = null;

        // Assignments
        if(this.in.hasNext()) {

            byte token2 = in.skipIgnorable().peekType();
            if(token2 == LPAREN) ret = this.functionCall(identifierNode);
            if(token2 == ASSIGN) ret = this.varAssignment(identifierNode);
            if(token2 == IDENTIFIER) ret = this.varDeclaration2(identifierNode, AccessDescriber.PRIVATE, false, false, false);
            if(token2 == ADD_ASSIGN) ret = this.varAddAssignment(identifierNode);
            if(token2 == SUB_ASSIGN) ret = this.varSubAssignment(identifierNode);
            if(token2 == MUL_ASSIGN) ret = this.varMulAssignment(identifierNode);
            if(token2 == DIV_ASSIGN) ret = this.varDivAssignment(identifierNode);
            if(token2 == MOD_ASSIGN) ret = this.varModAssignment(identifierNode);
            if(token2 == POW_ASSIGN) ret = this.varPowAssignment(identifierNode);
            if(token2 == INCR) ret = this.varIncrease(identifierNode);
            if(token2 == DECR) ret = this.varDecrease(identifierNode);
            if(in.skipIgnorable().hasNext() && in.peekType() == DOT) {
                this.in.skip();
                this.in.skipIgnorable();
                return this.parseIdentifier(ret != null ? ret : new VariableUsageNode(identifierNode));
            }
            if(ret != null) return ret;

        }
        return new VariableUsageNode(identifierNode);

    }

    private ClassConstructionNode parseClassConstruction() {
        this.in.skip();
        this.in.skipIgnorable();
        int start = in.actualStart();
        ValuedNode node = parseIdentifier(null);
        if(!(node instanceof FunctionCallNode))
            throw new ParserError("Expecting a call after keyword new",
                    start, in.actualEnd());
        return new ClassConstructionNode(((FunctionCallNode) node).getFunction(), ((FunctionCallNode) node).getArgs());
    }



    // ****************************************************************************
    // Classes


    private ClassDeclarationNode classDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_CLASS) throw new ParserError("Expecting class keyword");
        if(!this.in.hasNext() || this.in.peekType() != IDENTIFIER) throw new ParserError("Expecting identifier");
        String name = this.in.nextValue();

        List<VariableDeclarationNode> fields = new ArrayList<>();
        List<FunctionDeclarationNode> methods = new ArrayList<>();
        List<ClassDeclarationNode> classes = new ArrayList<>();

        // TODO: extends, implements
        if(this.in.nextType() != LCURL) throw new ParserError("Expecting class-body");

        while(this.in.hasNext() && this.in.peekType() != RCURL) {

            skipSeparators();

            ValuedNode node = parseDeclaration(true);
            if(node instanceof ClassDeclarationNode) classes.add((ClassDeclarationNode) node);
            else if(node instanceof FunctionDeclarationNode) methods.add((FunctionDeclarationNode) node);
            else if(node instanceof VariableDeclarationNode) fields.add((VariableDeclarationNode) node);

            skipSeparators();

        }

        if(this.in.nextType() != RCURL) throw new ParserError("Expecting class-body to end");

        return new ClassDeclarationNode(name, fields, methods, classes, access, isInClass, isStatic, isFinal);
    }



    // ****************************************************************************
    // Functions


    private FunctionDeclarationNode functionDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        List<FunctionArgumentNode> args = new ArrayList<>();
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_FUNCTION) throw new ParserError("Expecting function keyword");
        if(!this.in.hasNext() || this.in.peekType() != IDENTIFIER) throw new ParserError("Expecting identifier");
        String name = this.in.nextValue();

        if(!this.in.hasNext() || this.in.nextType() != LPAREN) throw new ParserError("Expecting '('");

        if(this.checkArgument()) {
            args.add(this.parseArgument());
            while(this.in.hasNext() && this.in.peekType() == COMMA) {
                this.in.skip();
                if(this.checkArgument()) args.add(this.parseArgument());
                else break;
            }
        }

        if(!this.in.hasNext() || this.in.nextType() != RPAREN) throw new ParserError("Expecting ')'");

        Tree body = this.parseBodyStatement();
        return new FunctionDeclarationNode(name, body, args.toArray(new FunctionArgumentNode[0]), access, isInClass, isStatic, isFinal);
    }

    private FunctionCallNode functionCall(ValuedNode function) {
        List<ValuedNode> args = new ArrayList<>();
        if(!this.in.hasNext() || this.in.nextType() != LPAREN) throw new ParserError("Expecting '('");
        if(this.in.peekType() != RPAREN) {
            args.add(this.valuedOperation());
            while(this.in.hasNext() && this.in.peekType() == COMMA) {
                this.in.skip();
                ValuedNode operation = this.valuedOperation();
                if(operation != null) args.add(operation);
                else break;
            }
        }
        if(!this.in.hasNext() || this.in.nextType() != RPAREN) throw new ParserError("Expecting ')'");
        return new FunctionCallNode(function, args.toArray(new ValuedNode[0]));
    }

    private FunctionArgumentNode parseArgument() {
        if(this.in.peekType() == IDENTIFIER) {
            return new FunctionArgumentNode(this.in.nextValue());
        }
        else throw new ParserError("Expecting identifier");
    }

    private boolean checkArgument() {
        return this.in.hasNext() && this.in.peekType() == IDENTIFIER;
    }


    private ReturnNode returnStatement() {
        this.in.skip();
        return new ReturnNode(valuedOperation());
    }



    // ****************************************************************************
    // Variables


    private VariableAssignmentNode varAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != ASSIGN) throw new ParserError("Expecting '='");
        Node value = operation();
        return new VariableAssignmentNode(variable, value);
    }

    private VariableAddAssignmentNode varAddAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != ADD_ASSIGN) throw new ParserError("Expecting '+='");
        Node value = operation();
        return new VariableAddAssignmentNode(variable, value);
    }

    private VariableSubAssignmentNode varSubAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != SUB_ASSIGN) throw new ParserError("Expecting '-='");
        Node value = operation();
        return new VariableSubAssignmentNode(variable, value);
    }

    private VariableMulAssignmentNode varMulAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != MUL_ASSIGN) throw new ParserError("Expecting '*='");
        Node value = operation();
        return new VariableMulAssignmentNode(variable, value);
    }

    private VariableDivAssignmentNode varDivAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != DIV_ASSIGN) throw new ParserError("Expecting '/='");
        Node value = operation();
        return new VariableDivAssignmentNode(variable, value);
    }

    private VariableModAssignmentNode varModAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != MOD_ASSIGN) throw new ParserError("Expecting '%='");
        Node value = operation();
        return new VariableModAssignmentNode(variable, value);
    }

    private VariablePowAssignmentNode varPowAssignment(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != POW_ASSIGN) throw new ParserError("Expecting '^='");
        Node value = operation();
        return new VariablePowAssignmentNode(variable, value);
    }

    private VariableIncreaseNode varIncrease(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != INCR) throw new ParserError("Expecting '++'");
        return new VariableIncreaseNode(variable);
    }

    private VariableDecreaseNode varDecrease(ValuedNode variable) {
        if(!this.in.hasNext() || this.in.nextType() != DECR) throw new ParserError("Expecting '--'");
        return new VariableDecreaseNode(variable);
    }

    private VariableDeclarationNode varDeclaration1(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!in.hasNext()) throw new ParserError("Expecting var or const keyword");
        if(this.in.nextType() == KEYWORD_CONST) {
            if(isFinal) throw new ParserError("A constant is always final, must not have \"final\" attribute!");
            isFinal = true;
        }
        else if(this.in.actualType() != KEYWORD_VAR) throw new ParserError("Expecting var or const keyword");
        if(!this.in.skipIgnorable().hasNext() || this.in.peekType() != IDENTIFIER) throw new ParserError("Expecting identifier");

        String identifier = this.in.nextValue();

        if(this.in.skipIgnorable().hasNext() && this.in.peekType() == ASSIGN) {
            return new VariableDeclarationNode(identifier, VariableType.DYNAMIC, this.varAssignment(new IdentifierNode(identifier)), access, isInClass, isStatic, isFinal);
        } else {
            return new VariableDeclarationNode(this.in.actualValue(), VariableType.DYNAMIC, null, access, isInClass, isStatic, isFinal);
        }

    }

    private VariableDeclarationNode varDeclaration2(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        byte t = this.in.nextType();
        VariableType declarationNode =
                t == KEYWORD_DYNAMIC ? VariableType.DYNAMIC :
                t == KEYWORD_BYTE ? VariableType.BYTE :
                t == KEYWORD_SHORT ? VariableType.SHORT :
                t == KEYWORD_INT ? VariableType.INTEGER :
                t == KEYWORD_LONG ? VariableType.LONG :
                t == KEYWORD_FLOAT ? VariableType.FLOAT :
                t == KEYWORD_DOUBLE ? VariableType.DOUBLE :
                t == KEYWORD_BOOLEAN ? VariableType.BOOLEAN :
                t == KEYWORD_CHAR ? VariableType.CHAR :
                t == IDENTIFIER ? VariableType.OBJECT : null;

        if(!this.in.skipIgnorable().hasNext() || this.in.peekType() != IDENTIFIER)
            throw new ParserError("Expecting identifier");

        String identifier = this.in.nextValue();

        if(this.in.skipIgnorable().hasNext() && this.in.peekType() == ASSIGN) {
            return new VariableDeclarationNode(identifier, declarationNode,
                    this.varAssignment(new IdentifierNode(identifier)), access, isInClass, isStatic, isFinal);
        } else {
            return new VariableDeclarationNode(this.in.actualValue(), declarationNode, null, access, isInClass, isStatic, isFinal);
        }
    }

    private VariableDeclarationNode varDeclaration2(IdentifierNode node, AccessDescriber access,
                                                    boolean isInClass, boolean isStatic, boolean isFinal) {

        if(!this.in.skipIgnorable().hasNext() || this.in.peekType() != IDENTIFIER)
            throw new ParserError("Expecting identifier");

        String identifier = this.in.nextValue();

        if(this.in.skipIgnorable().hasNext() && this.in.peekType() == ASSIGN) {
            return new VariableDeclarationNode(identifier, new VariableType(node),
                    this.varAssignment(new IdentifierNode(identifier)), access, isInClass, isStatic, isFinal);
        } else {
            return new VariableDeclarationNode(this.in.actualValue(), new VariableType(node),
                    null, access, isInClass, isStatic, isFinal);
        }
    }






    // ****************************************************************************
    // Loops & If


    private Node forLoop() {
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_FOR) throw new ParserError("Expecting for keyword");
        if(!this.in.hasNext() || this.in.nextType() != LPAREN) throw new ParserError("Expecting '('");
        Node declaration = operation();
        awaitSemicolon();
        ValuedNode condition = valuedOperation();
        awaitSemicolon();
        Node round = operation();
        if(!this.in.hasNext() || this.in.nextType() != RPAREN) throw new ParserError("Expecting ')'");
        Tree body = parseBodyStatement();
        return new ForNode(body, declaration, condition, round);
    }


    private Node doWhileLoop() {
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_DO)
            throw new ParserError("Expecting do keyword");
        Tree body = parseBodyStatement();
        skipSeparators();
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_WHILE)
            throw new ParserError("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        return new DoWhileNode(body, condition);
    }


    private Node whileLoop() {
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_WHILE)
            throw new ParserError("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        return new WhileNode(body, condition);
    }


    private Node ifStatement() {
        if(!this.in.hasNext() || this.in.nextType() != KEYWORD_IF)
            throw new ParserError("Expecting if keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        boolean separator = skipSeparators()>0;
        if(this.in.hasNext() && this.in.peekType() == KEYWORD_ELSE) {
            if(!separator) throw new ParserError("Awaited separator at this point");
            this.in.skip();
            Tree elseBody = parseBodyStatement();
            return new IfNode(body, elseBody, condition);
        }
        return new IfNode(body, condition);
    }

    private ValuedNode parseConditionStatement() {
        if(!this.in.hasNext() || this.in.nextType() != LPAREN) throw new ParserError("Expecting '('", in.getPosition());
        ValuedNode condition = logicalOr();
        if(!this.in.hasNext() || this.in.nextType() != RPAREN) throw new ParserError("Expecting ')'", in.getPosition());
        return condition;
    }

    private Tree parseBodyStatement() {
        skipSeparators();
        if(this.in.peekType() == LCURL) {
            this.in.skip();
            Tree body = prog();
            if(!this.in.hasNext() || this.in.nextType() != RCURL) throw new ParserError("Expecting '}'", in.getPosition());
            return body;
        }
        else {
            return new Tree(new Node[] { this.operation() });
        }
    }



    // ****************************************************************************
    // Statements


    // (Factor)

    private ValuedNode factor() {

        byte token = this.in.peekType();

        if(token == LPAREN) {
            in.skip();
            ValuedNode result = this.logicalOr();
            if(this.in.nextType() != RPAREN) throw new ParserError("Expecting ')'");
            return result;
        }

        if(token == KEYWORD_TRUE) {
            in.skip();
            return new LogicalTrueNode();
        }

        if(token == KEYWORD_FALSE) {
            in.skip();
            return new LogicalFalseNode();
        }

        if(token == INTEGER) {
            in.skip();
            return new IntegerNode(Integer.parseInt(in.actualValue()));
        }

        if(token == DOUBLE) {
            in.skip();
            return new DoubleNode(Double.parseDouble(in.actualValue()));
        }

        if(token == IDENTIFIER) {
            return parseIdentifier(null);
        }

        if(token == KEYWORD_NEW) {
            return parseClassConstruction();
        }

        if(token == ADD) {
            in.skip();
            return new AddNode(0, this.factor());
        }

        if(token == SUB) {
            in.skip();
            return new SubNode(0, this.factor());
        }

        if(token == STRING) {
            in.skip();
            return new StringNode(Characters.parseString(in.actualValue()));
        }

        if(token == CHARACTER) {
            in.skip();
            return new CharacterNode(Characters.parseString(in.actualValue()).charAt(0));
        }

        throw new ParserError(this.in.toString());
    }


    // (Calculations)

    private ValuedNode expr() {
        ValuedNode result = this.term();
        byte tmp_type;
        while(this.in.hasNext() &&
                ((tmp_type = this.in.peekType()) == ADD || tmp_type == SUB)) {

            if(tmp_type == ADD) {
                this.in.skip();
                result = new AddNode(result, this.term());
            }

            else {
                this.in.skip();
                result = new SubNode(result, this.term());
            }

        }

        return result;
    }

    private ValuedNode term() {
        ValuedNode result = this.pow();

        byte tmp_type;
        while(this.in.hasNext() &&
                ((tmp_type = this.in.peekType()) == MUL || tmp_type == DIV || tmp_type == MOD)) {

            if(tmp_type == MUL) {
                this.in.skip();
                result = new MulNode(result, this.pow());
            }
            else if(tmp_type == DIV) {
                this.in.skip();
                result = new DivNode(result, this.pow());
            }
            else {
                this.in.skip();
                result = new ModNode(result, this.pow());
            }
        }
        return result;
    }

    private ValuedNode pow() {
        ValuedNode result = this.factor();

        while(this.in.hasNext() && this.in.peekType() == POW) {
            this.in.skip();
            result = new PowNode(result, this.factor());
        }
        return result;
    }


    // (Logical)

    private ValuedNode logicalOr() {
        ValuedNode result = this.logicalAnd();

        while(this.in.hasNext() && this.in.peekType() == LOGICAL_OR) {
            this.in.skip();
            result = new LogicalOrNode(result, this.logicalAnd());
        }
        return result;
    }


    private ValuedNode logicalAnd() {
        ValuedNode result = this.compare();

        while(this.in.hasNext() && this.in.peekType() == LOGICAL_AND) {
            this.in.skip();
            result = new LogicalAndNode(result, this.compare());
        }
        return result;
    }


    private ValuedNode compare() {
        ValuedNode left = this.expr();

        byte tmp_type;
        while(this.in.hasNext()
                && ((tmp_type = this.in.peekType()) == EQ_EQUALS || tmp_type == BIGGER_EQUALS
                    || tmp_type == SMALLER_EQUALS || tmp_type == BIGGER || tmp_type == SMALLER)) {

            this.in.skip();
            if(tmp_type == EQ_EQUALS) return new LogicalEqEqualsNode(left, this.logicalOr());
            else if(tmp_type == BIGGER_EQUALS) left = new LogicalBiggerEqualsNode(left, this.logicalOr());
            else if(tmp_type == SMALLER_EQUALS) left = new LogicalSmallerEqualsNode(left, this.logicalOr());
            else if(tmp_type == BIGGER) left = new LogicalBiggerNode(left, this.logicalOr());
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
