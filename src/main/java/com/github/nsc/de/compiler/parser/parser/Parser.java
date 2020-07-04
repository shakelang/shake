package com.github.nsc.de.compiler.parser.parser;

import com.github.nsc.de.compiler.lexer.token.TokenInputStream;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.parser.expressions.*;
import com.github.nsc.de.compiler.parser.parser.statements.CalculationParser;
import com.github.nsc.de.compiler.parser.parser.statements.FactorParser;
import com.github.nsc.de.compiler.parser.parser.statements.LogicalParser;
import com.github.nsc.de.compiler.parser.parser.statements.StatementParser;
import com.github.nsc.de.compiler.parser.parser.variables.*;

public class Parser implements ProgramParser, ErrorGenerator,
        VariableDeclarationParser, VariableAssignmentParser, VariableUsageParser,
        StatementParser, CalculationParser, LogicalParser, FactorParser,
        WhileLoopParser, DoWhileLoopParser, ForLoopParser, IfParser, FunctionParser {

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

    @Override
    public TokenInputStream getInput() {
        return this.in;
    }
}
