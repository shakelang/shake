package com.github.nsc.de.compiler.parser.parser.variables;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.variables.VariableUsageNode;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface VariableUsageParser extends ParserType {

    @Override
    default VariableUsageNode varUsage() {
        if(this.getInput().next().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        return new VariableUsageNode(this.getInput().actual().getValue());
    }
}
