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

    LOGICAL_OR,
    LOGICAL_AND,

    // Separators
    LINE_SEPARATOR,
    SEMICOLON,

    // Assign
    ASSIGN,
    INCR,
    DECR,
    ADD_ASSIGN,
    SUB_ASSIGN,
    MUL_ASSIGN,
    DIV_ASSIGN,
    POW_ASSIGN,

    // Brackets
    LPAREN,
    RPAREN,
    LCURL,
    RCURL,

    // Keyword
    KEYWORD_DO,
    KEYWORD_WHILE,
    KEYWORD_FOR,
    KEYWORD_IF,
    KEYWORD_ELSE,
    KEYWORD_TRUE,
    KEYWORD_FALSE,
    KEYWORD_VAR,

    // Identifier
    IDENTIFIER,
    ;
}
