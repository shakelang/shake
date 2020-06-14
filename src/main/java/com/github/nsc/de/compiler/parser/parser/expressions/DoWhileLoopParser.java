package com.github.nsc.de.compiler.parser.parser.expressions;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.Node;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.loops.DoWhileNode;
import com.github.nsc.de.compiler.parser.parser.ParseUtils;
import com.github.nsc.de.compiler.parser.parser.ParserType;

public interface DoWhileLoopParser extends ParserType, ParseUtils {

    @Override
    default Node doWhileLoop() {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_DO) throw this.error("Expecting do keyword");
        Tree body = parseBodyStatement();
        skipSeparators();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_WHILE) throw this.error("Expecting while keyword");
        ValuedNode condition = parseConditionStatement();
        return new DoWhileNode(body, condition);
    }
}
