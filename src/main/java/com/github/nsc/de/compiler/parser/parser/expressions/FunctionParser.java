package com.github.nsc.de.compiler.parser.parser.expressions;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionArgumentNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionCallNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.parser.ParseUtils;
import com.github.nsc.de.compiler.parser.parser.ParserType;

import java.util.ArrayList;
import java.util.List;

public interface FunctionParser extends ParserType, ParseUtils {

    @Override
    default FunctionDeclarationNode functionDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {

        List<FunctionArgumentNode> args = new ArrayList<>();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_FUNCTION) throw this.error("Expecting function keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        String name = this.getInput().next().getValue();

        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw this.error("Expecting '('");

        if(this.checkArgument()) {
            args.add(this.parseArgument());
            while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.COMMA) {
                this.getInput().skip();
                if(this.checkArgument()) args.add(this.parseArgument());
                else break;
            }
        }

        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");

        Tree body = this.parseBodyStatement();
        return new FunctionDeclarationNode(name, body, args.toArray(new FunctionArgumentNode[0]), access, isInClass, isStatic, isFinal);
    }

    @Override
    default FunctionCallNode functionCall(ValuedNode function) {
        List<ValuedNode> args = new ArrayList<>();
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.LPAREN) throw this.error("Expecting '('");
        if(this.getInput().peek().getType() != TokenType.RPAREN) {
            args.add(this.valuedOperation());
            while(this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.COMMA) {
                this.getInput().skip();
                ValuedNode operation = this.valuedOperation();
                if(operation != null) args.add(operation);
                else break;
            }
        }
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.RPAREN) throw this.error("Expecting ')'");
        return new FunctionCallNode(function, args.toArray(new ValuedNode[0]));
    }

    default FunctionArgumentNode parseArgument() {
        if(this.getInput().peek().getType() == TokenType.IDENTIFIER) {
            return new FunctionArgumentNode(this.getInput().next().getValue());
        }
        else throw this.error("Expecting identifier");
    }

    default boolean checkArgument() {
        return this.getInput().hasNext() && this.getInput().peek().getType() == TokenType.IDENTIFIER;
    }

}
