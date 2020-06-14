package com.github.nsc.de.compiler.parser.parser;

public interface ErrorGenerator extends ParserType {

    @Override
    default Error error(String error) {
        throw new Error(error + " at " + this.getInput().peek() + " in " + this.getInput());
    }
}
