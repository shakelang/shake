package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.Position;
import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.parser.expressions.*;
import com.github.nsc.de.compiler.parser.parser.statements.CalculationParser;
import com.github.nsc.de.compiler.parser.parser.statements.FactorParser;
import com.github.nsc.de.compiler.parser.parser.statements.LogicalParser;
import com.github.nsc.de.compiler.parser.parser.statements.StatementParser;
import com.github.nsc.de.compiler.parser.parser.variables.*;
import com.github.nsc.de.compiler.util.CompilerError;
import sun.nio.cs.ext.MacTurkish;

public class Parser implements ProgramParser,
        VariableDeclarationParser, VariableAssignmentParser, VariableUsageParser,
        StatementParser, CalculationParser, LogicalParser, FactorParser,
        WhileLoopParser, DoWhileLoopParser, ForLoopParser, IfParser, FunctionParser,
        ClassParser, ParserType {

    private final TokenInputStream in;

    public Parser(TokenInputStream in) {
        this.in = in;
    }

    public Tree parse() {
        if(this.in.peek() == null) return null;

        Tree result = this.prog();

        if(this.in.hasNext()) throw this.error("Input did not end");
        return result;
    }

    @Override
    public ParserError error(String name, String error, Position start, Position end) {
        return new ParserError(name, error, start, end);
    }

    @Override
    public ParserError error(String error, Position start, Position end) {
        return error("ParserError", error, start, end);
    }

    @Override
    public ParserError error(String name, String error, int start, int end) {
        return error(name, error, getInput().get(start).getStart(), getInput().get(end).getEnd());
    }

    @Override
    public ParserError error(String error, int start, int end) {
        return error(error, getInput().get(start).getStart(), getInput().get(end).getEnd());
    }

    @Override
    public ParserError error(String error, int position) {
        return error(error, getInput().get(position).getStart(), getInput().get(position).getEnd());
    }

    @Override
    public ParserError error(String name, String error, int position) {
        return error(name, error, getInput().get(position).getStart(), getInput().get(position).getEnd());
    }

    @Override
    public ParserError error(String error) {
        return error(error, getInput().peek().getStart(), getInput().peek().getEnd());
    }

    @Override
    public ParserError error(String name, String error) {
        return error(name, error, getInput().peek().getStart(), getInput().peek().getEnd());
    }

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
    }

    @Override
    public TokenInputStream getInput() {
        return this.in;
    }
}
