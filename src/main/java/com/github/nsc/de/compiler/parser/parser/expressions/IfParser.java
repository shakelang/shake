package com.github.nsc.de.compiler.parser.parser.expressions;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.IfNode;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.parser.ParseUtils;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface IfParser extends ParserType, ParseUtils {

    @Override
    default Node ifStatement() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_IF) throw this.error("Expecting if keyword");
        ValuedNode condition = parseConditionStatement();
        Tree body = parseBodyStatement();
        boolean separator = skipSeparators()>0;
        if(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.KEYWORD_ELSE) {
            if(!separator) throw this.error("Awaited separator at this point");
            this.getInput().skip();
            Tree elseBody = parseBodyStatement();
            return new IfNode(body, elseBody, condition);
        }
        return new IfNode(body, condition);
    }
}
