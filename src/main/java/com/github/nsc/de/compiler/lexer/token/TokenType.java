package com.github.nsc.de.compiler.lexer.token;

public enum TokenType {

    // Numbers
    INTEGER,
    DOUBLE,

    // Math Operators
    ADD,
    SUB,
    MUL,
    DIV,
    POW,

    // Logical Operators
    EQ_EQUALS,
    BIGGER_EQUALS,
    SMALLER_EQUALS,
    BIGGER,
    SMALLER,

    // Separators
    LINE_SEPARATOR,
    SEMICOLON,

    // Variables
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
