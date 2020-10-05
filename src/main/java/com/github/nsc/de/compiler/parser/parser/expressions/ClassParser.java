package com.github.nsc.de.compiler.parser.parser.expressions;

import com.github.nsc.de.compiler.lexer.token.TokenType;
import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.functions.FunctionDeclarationNode;
import com.github.nsc.de.compiler.parser.node.objects.ClassDeclarationNode;
import com.github.nsc.de.compiler.parser.node.variables.VariableDeclarationNode;
import com.github.nsc.de.compiler.parser.parser.ParseUtils;
import com.github.nsc.de.compiler.parser.parser.ParserType;

import java.util.ArrayList;
import java.util.List;

public interface ClassParser extends ParserType, FunctionParser, ParseUtils {

    @Override
    default ClassDeclarationNode classDeclaration(AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        if(!this.getInput().hasNext() || this.getInput().next().getType() != TokenType.KEYWORD_CLASS) throw this.error("Expecting class keyword");
        if(!this.getInput().hasNext() || this.getInput().peek().getType() != TokenType.IDENTIFIER) throw this.error("Expecting identifier");
        String name = this.getInput().next().getValue();

        List<VariableDeclarationNode> fields = new ArrayList<>();
        List<FunctionDeclarationNode> methods = new ArrayList<>();
        List<ClassDeclarationNode> classes = new ArrayList<>();

        // TODO: extends, implements
        if(this.getInput().next().getType() != TokenType.LCURL) throw this.error("Expecting class-body");

        while(this.getInput().hasNext() && this.getInput().next().getType() != TokenType.RCURL) {

            ValuedNode node = parseDeclaration();
            if(node instanceof ClassDeclarationNode) classes.add((ClassDeclarationNode) node);
            else if(node instanceof FunctionDeclarationNode) methods.add((FunctionDeclarationNode) node);
            else if(node instanceof VariableDeclarationNode) fields.add((VariableDeclarationNode) node);

        }

        return new ClassDeclarationNode(name, fields, methods, classes, access, isInClass, isStatic, isFinal);
    }

}
