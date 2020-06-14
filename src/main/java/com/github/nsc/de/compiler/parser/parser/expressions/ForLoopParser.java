package com.github.nsc.de.compiler.parser.parser.expressions;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.loops.ForNode;
import com.github.nsc.de.compiler.parser.parser.ParseUtils;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface ForLoopParser extends ParserType, ParseUtils {

    @Override
    default Node forLoop() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_FOR) throw this.error("Expecting for keyword");
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw this.error("Expecting '('");
        Node declaration = operation();
        awaitSemicolon();
        ValuedNode condition = logicalOr();
        awaitSemicolon();
        Node round = operation();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");
        Tree body = parseBodyStatement();
        return new ForNode(body, declaration, condition, round);
    }
}
