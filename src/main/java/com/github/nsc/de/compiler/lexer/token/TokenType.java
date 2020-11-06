package com.github.nsc.de.compiler.lexer.token;

/**
 * These are the different types of tokens, that the lexer creates
 *
 * @author Nicolas Schmidt
 */
public enum TokenType {

    /**
     * Identifier for variables, functions and classes
     */
    IDENTIFIER,



    // *****************************************************
    // Assign-Types

    /**
     * A number hat does not contain decimal places
     */
    INTEGER,

    /**
     * A number hat does contain decimal places
     */
    DOUBLE,

    /**
     * A string ("a string")
     */
    STRING,



    // *****************************************************
    // Punctuation

    /**
     * Token ','
     * A comma for separating values
     */
    COMMA,

    /**
     * Token '.'
     * A dot that is not inside of a double for sub-identifiers
     */
    DOT,

    /**
     * Token ';'
     * A semicolon as separator for statements
     */
    SEMICOLON,



    // *****************************************************
    // Math Operators

    /**
     * Token '+'
     */
    ADD,

    /**
     * Token '-'
     */
    SUB,

    /**
     * Token '*'
     */
    MUL,

    /**
     * Token '/'
     */
    DIV,

    /**
     * Token '**' or '**'
     */
    POW,



    // *****************************************************
    // Logical Operators

    /**
     * Token '==' for comparison
     */
    EQ_EQUALS,

    /**
     * Token '>=' for comparison
     */
    BIGGER_EQUALS,

    /**
     * Token '<=' for comparison
     */
    SMALLER_EQUALS,

    /**
     * Token '>' for comparison
     */
    BIGGER,

    /**
     * Token '<' for comparison
     */
    SMALLER,

    /**
     * Token '||' (logical or operator)
     */
    LOGICAL_OR,

    /**
     * Token '&&' (logical and operator)
     */
    LOGICAL_AND,



    // *****************************************************
    // Separators

    /**
     * Token '\n' (separator that can be used instead of a semicolon or that will be ignored)
     */
    LINE_SEPARATOR,



    // *****************************************************
    // Assign

    /**
     * Token '=' for assigning values to variable
     */
    ASSIGN,

    /**
     * Token '++' for increasing variable values
     */
    INCR,

    /**
     * Token '--' for decreasing variable values
     */
    DECR,

    /**
     * Token '+=' for add-assigning values to a variable
     */
    ADD_ASSIGN,

    /**
     * Token '-=' for subtract-assigning values to a variable
     */
    SUB_ASSIGN,

    /**
     * Token '*=' for multiply-assigning values to a variable
     */
    MUL_ASSIGN,

    /**
     * Token '/=' for divide-assigning values to a variable
     */
    DIV_ASSIGN,

    /**
     * Token '^=' or '**=' for divide-assigning values to a variable
     */
    POW_ASSIGN,



    // *****************************************************
    // Brackets

    /**
     * Token '('
     */
    LPAREN,

    /**
     * Token ')'
     */
    RPAREN,

    /**
     * Token '{'
     */
    LCURL,

    /**
     * Token '}'
     */
    RCURL,




    // *****************************************************
    // Keywords

    /**
     * Keyword "do" for do-while-loops
     */
    KEYWORD_DO,

    /**
     * Keyword "while" for while-loops and do-while-loops
     */
    KEYWORD_WHILE,

    /**
     * Keyword "for" for for-loops
     */
    KEYWORD_FOR,

    /**
     * Keyword "if" for if-clauses
     */
    KEYWORD_IF,

    /**
     * Keyword "else" for if-else-clauses
     */
    KEYWORD_ELSE,

    /**
     * Keyword "true" for boolean-true-values
     */
    KEYWORD_TRUE,

    /**
     * Keyword "false" for boolean-false-values
     */
    KEYWORD_FALSE,

    // Declarations

    /**
     * Keyword "class" for declaring classes
     */
    KEYWORD_CLASS,

    /**
     * Keyword "extends" for setting a super-class in a class-declaration
     */
    KEYWORD_EXTENDS,

    /**
     * Keyword "implements" for implementing interfaces in a class-declaration
     */
    KEYWORD_IMPLEMENTS,

    /**
     * Keyword "static" for creating statics instide of a classes
     */
    KEYWORD_STATIC,

    /**
     * Keyword "final" for creating a constant function, variable or class
     */
    KEYWORD_FINAL,

    /**
     * Keyword "public" for defining public functions, variables and classes
     */
    KEYWORD_PUBLIC,

    /**
     * Keyword "protected" for defining protected functions, variables and classes
     */
    KEYWORD_PROTECTED,

    /**
     * Keyword "private" for defining private functions, variables and classes
     */
    KEYWORD_PRIVATE,

    /**
     * Keyword "new" for creating new instances of a class
     */
    KEYWORD_NEW,



    /**
     * Keyword "function" for declaring functions
     */
    KEYWORD_FUNCTION,



    // Variable Declaration

    /**
     * Keyword "var" or "let" for declaring variables
     */
    KEYWORD_VAR,

    /**
     * Keyword "const" for declaring final variables
     */
    KEYWORD_CONST,



    /**
     * Keyword "dynamic" as return type for functions or for declaring variables
     */
    KEYWORD_DYNAMIC,

    /**
     * Keyword "byte" as return type for functions or for declaring variables
     */
    KEYWORD_BYTE,

    /**
     * Keyword "short" as return type for functions or for declaring variables
     */
    KEYWORD_SHORT,

    /**
     * Keyword "int" as return type for functions or for declaring variables
     */
    KEYWORD_INT,

    /**
     * Keyword "long" as return type for functions or for declaring variables
     */
    KEYWORD_LONG,

    /**
     * Keyword "float" as return type for functions or for declaring variables
     */
    KEYWORD_FLOAT,

    /**
     * Keyword "double" as return type for functions or for declaring variables
     */
    KEYWORD_DOUBLE,

    /**
     * Keyword "char" as return type for functions or for declaring variables
     */
    KEYWORD_CHAR,

    /**
     * Keyword "boolean" as return type for functions or for declaring variables
     */
    KEYWORD_BOOLEAN,
    ;
}