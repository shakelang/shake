package com.github.nsc.de.compiler.lexer.token;

public enum TokenType {

    // Numbers
    INTEGER,
    DOUBLE,

    // Operators
    ADD,
    SUB,
    MUL,
    DIV,
    POW,

    // Seperators
    LINE_SEPERATOR,
    SEMICOLON,

    // Assign
    ASSIGN,

    // Brackets
    LPAREN,
    RPAREN,

    // Keyword
    KEYWORD_VAR,

    // Identifier
    IDENTIFIER,
    ;
}
