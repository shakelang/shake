package com.github.nsc.de.shake.lexer.token;

/**
 * These are the different types of tokens, that the lexer creates
 *
 * @author <a href="https://github.com/nsc-de">Nicolas Schmidt &lt;@nsc-de&gt;</a>
 */
public class TokenType {

    private static final String[] NAMES = new String[] {
            "IDENTIFIER",           //  0
            "INTEGER",              //  1
            "DOUBLE",               //  2
            "CHARACTER",            //  3
            "STRING",               //  4
            "COMMA",                //  5
            "DOT",                  //  6
            "SEMICOLON",            //  7
            "ADD",                  //  8
            "SUB",                  //  9
            "MUL",                  // 10
            "DIV",                  // 11
            "MOD",                  // 12
            "POW",                  // 13
            "EQ_EQUALS",            // 14
            "BIGGER_EQUALS",        // 15
            "SMALLER_EQUALS",       // 16
            "BIGGER",               // 17
            "SMALLER",              // 18
            "LOGICAL_OR",           // 19
            "LOGICAL_AND",          // 20
            "LINE_SEPARATOR",       // 21
            "ASSIGN",               // 22
            "INCR",                 // 23
            "DECR",                 // 24
            "ADD_ASSIGN",           // 25
            "SUB_ASSIGN",           // 26
            "MUL_ASSIGN",           // 27
            "DIV_ASSIGN",           // 28
            "MOD_ASSIGN",           // 29
            "POW_ASSIGN",           // 30
            "LPAREN",               // 31
            "RPAREN",               // 32
            "LCURL",                // 33
            "RCURL",                // 34
            "KEYWORD_DO",           // 35
            "KEYWORD_WHILE",        // 36
            "KEYWORD_FOR",          // 37
            "KEYWORD_IF",           // 38
            "KEYWORD_ELSE",         // 39
            "KEYWORD_TRUE",         // 40
            "KEYWORD_FALSE",        // 41
            "KEYWORD_CLASS",        // 42
            "KEYWORD_EXTENDS",      // 43
            "KEYWORD_IMPLEMENTS",   // 44
            "KEYWORD_STATIC",       // 45
            "KEYWORD_FINAL",        // 46
            "KEYWORD_PUBLIC",       // 47
            "KEYWORD_PROTECTED",    // 48
            "KEYWORD_PRIVATE",      // 49
            "KEYWORD_NEW",          // 50
            "KEYWORD_FUNCTION",     // 51
            "KEYWORD_RETURN",       // 52
            "KEYWORD_VAR",          // 53
            "KEYWORD_CONST",        // 54
            "KEYWORD_DYNAMIC",      // 55
            "KEYWORD_BYTE",         // 56
            "KEYWORD_SHORT",        // 57
            "KEYWORD_INT",          // 58
            "KEYWORD_LONG",         // 59
            "KEYWORD_FLOAT",        // 60
            "KEYWORD_DOUBLE",       // 61
            "KEYWORD_CHAR",         // 62
            "KEYWORD_BOOLEAN",      // 63
            "KEYWORD_IMPORT",       // 64
            "KEYWORD_CONSTRUCTOR",  // 65
    };

    private static final byte[] TOKEN_LENGTH = new byte[] {
            // The length of the tokens
            //          TokenType           Index
            //         -------------------------
            -1,     //  IDENTIFIER            0
            -1,     //  INTEGER               1
            -1,     //  DOUBLE                2
            -1,     //  CHARACTER             3
            -1,     //  STRING                4
            1,      //  COMMA                 5
            1,      //  DOT                   6
            1,      //  SEMICOLON             7
            1,      //  ADD                   8
            1,      //  SUB                   9
            1,      //  MUL                  10
            1,      //  DIV                  11
            1,      //  MOD                  12
            2,      //  POW                  13
            2,      //  EQ_EQUALS            14
            2,      //  BIGGER_EQUALS        15
            2,      //  SMALLER_EQUALS       16
            1,      //  BIGGER               17
            1,      //  SMALLER              18
            2,      //  LOGICAL_OR           19
            2,      //  LOGICAL_AND          20
            1,      //  LINE_SEPARATOR       21
            1,      //  ASSIGN               22
            2,      //  INCR                 23
            2,      //  DECR                 24
            2,      //  ADD_ASSIGN           25
            2,      //  SUB_ASSIGN           26
            2,      //  MUL_ASSIGN           27
            2,      //  DIV_ASSIGN           28
            2,      //  MOD_ASSIGN           29
            3,      //  POW_ASSIGN           30
            1,      //  LPAREN               31
            1,      //  RPAREN               32
            1,      //  LCURL                33
            1,      //  RCURL                34
            2,      //  KEYWORD_DO           35
            5,      //  KEYWORD_WHILE        36
            3,      //  KEYWORD_FOR          37
            2,      //  KEYWORD_IF           38
            4,      //  KEYWORD_ELSE         39
            4,      //  KEYWORD_TRUE         40
            5,      //  KEYWORD_FALSE        41
            5,      //  KEYWORD_CLASS        42
            7,      //  KEYWORD_EXTENDS      43
            10,     //  KEYWORD_IMPLEMENTS   44
            6,      //  KEYWORD_STATIC       45
            5,      //  KEYWORD_FINAL        46
            6,      //  KEYWORD_PUBLIC       47
            9,      //  KEYWORD_PROTECTED    48
            7,      //  KEYWORD_PRIVATE      49
            3,      //  KEYWORD_NEW          50
            8,      //  KEYWORD_FUNCTION     51
            6,      //  KEYWORD_RETURN       52
            3,      //  KEYWORD_VAR          53
            5,      //  KEYWORD_CONST        54
            7,      //  KEYWORD_DYNAMIC      55
            4,      //  KEYWORD_BYTE         56
            5,      //  KEYWORD_SHORT        57
            3,      //  KEYWORD_INT          58
            4,      //  KEYWORD_LONG         59
            5,      //  KEYWORD_FLOAT        60
            6,      //  KEYWORD_DOUBLE       61
            4,      //  KEYWORD_CHAR         62
            7,      //  KEYWORD_BOOLEAN      63
            6,      //  KEYWORD_IMPORT       64
            11,     //  KEYWORD_CONSTRUCTOR  65
    };
    
    /**
     * Identifier for variables, functions and classes
     */
    public static final byte IDENTIFIER = 0;



    // *****************************************************
    // Assign-Types

    /**
     * A number that does not contain decimal places
     */
    public static final byte INTEGER = 1;

    /**
     * A number that does contain decimal places
     */
    public static final byte DOUBLE = 2;

    /**
     * A character
     */
    public static final byte CHARACTER = 3;

    /**
     * A string ("a string")
     */
    public static final byte STRING = 4;



    // *****************************************************
    // Punctuation

    /**
     * Token ','
     * A comma for separating values
     */
    public static final byte COMMA = 5;

    /**
     * Token '.'
     * A dot that is not inside of a double for sub-identifiers
     */
    public static final byte DOT = 6;

    /**
     * Token ';'
     * A semicolon as separator for statements
     */
    public static final byte SEMICOLON = 7;



    // *****************************************************
    // Math Operators

    /**
     * Token '+' for adding values
     */
    public static final byte ADD = 8;

    /**
     * Token '-' for subtracting values
     */
    public static final byte SUB = 9;

    /**
     * Token '*' for multiplying values
     */
    public static final byte MUL = 10;

    /**
     * Token '/' for dividing values
     */
    public static final byte DIV = 11;

    /**
     * Token '%' for modulo operations
     */
    public static final byte MOD = 12;

    /**
     * Token '**' for pow operations
     */
    public static final byte POW = 13;



    // *****************************************************
    // Logical Operators

    /**
     * Token '==' for comparison
     */
    public static final byte EQ_EQUALS = 14;

    /**
     * Token '&gt;=' for comparison
     */
    public static final byte BIGGER_EQUALS = 15;

    /**
     * Token '&lt;=' for comparison
     */
    public static final byte SMALLER_EQUALS = 16;

    /**
     * Token '&gt;' for comparison
     */
    public static final byte BIGGER = 17;

    /**
     * Token '&lt;' for comparison
     */
    public static final byte SMALLER = 18;

    /**
     * Token '||' (logical or operator)
     */
    public static final byte LOGICAL_OR = 19;

    /**
     * Token '&amp;&amp;' (logical and operator)
     */
    public static final byte LOGICAL_AND = 20;



    // *****************************************************
    // Separators

    /**
     * Token '\n' (separator that can be used instead of a semicolon or that will be ignored)
     */
    public static final byte LINE_SEPARATOR = 21;



    // *****************************************************
    // Assign

    /**
     * Token '=' for assigning values to variable
     */
    public static final byte ASSIGN = 22;

    /**
     * Token '++' for increasing variable values
     */
    public static final byte INCR = 23;

    /**
     * Token '--' for decreasing variable values
     */
    public static final byte DECR = 24;

    /**
     * Token '+=' for add-assigning values to a variable
     */
    public static final byte ADD_ASSIGN = 25;

    /**
     * Token '-=' for subtract-assigning values to a variable
     */
    public static final byte SUB_ASSIGN = 26;

    /**
     * Token '*=' for multiply-assigning values to a variable
     */
    public static final byte MUL_ASSIGN = 27;

    /**
     * Token '/=' for divide-assigning values to a variable
     */
    public static final byte DIV_ASSIGN = 28;

    /**
     * Token '%=' for modulo-assigning values to a variable
     */
    public static final byte MOD_ASSIGN = 29;

    /**
     * Token '^=' or '**=' for divide-assigning values to a variable
     */
    public static final byte POW_ASSIGN = 30;



    // *****************************************************
    // Brackets

    /**
     * Token '('
     */
    public static final byte LPAREN = 31;

    /**
     * Token ')'
     */
    public static final byte RPAREN = 32;

    /**
     * Token '{'
     */
    public static final byte LCURL = 33;

    /**
     * Token '}'
     */
    public static final byte RCURL = 34;




    // *****************************************************
    // Keywords

    /**
     * Keyword "do" for do-while-loops
     */
    public static final byte KEYWORD_DO = 35;

    /**
     * Keyword "while" for while-loops and do-while-loops
     */
    public static final byte KEYWORD_WHILE = 36;

    /**
     * Keyword "for" for for-loops
     */
    public static final byte KEYWORD_FOR = 37;

    /**
     * Keyword "if" for if-clauses
     */
    public static final byte KEYWORD_IF = 38;

    /**
     * Keyword "else" for if-else-clauses
     */
    public static final byte KEYWORD_ELSE = 39;

    /**
     * Keyword "true" for boolean-true-values
     */
    public static final byte KEYWORD_TRUE = 40;

    /**
     * Keyword "false" for boolean-false-values
     */
    public static final byte KEYWORD_FALSE = 41;

    // Declarations

    /**
     * Keyword "class" for declaring classes
     */
    public static final byte KEYWORD_CLASS = 42;

    /**
     * Keyword "extends" for setting a super-class in a class-declaration
     */
    public static final byte KEYWORD_EXTENDS = 43;

    /**
     * Keyword "implements" for implementing interfaces in a class-declaration
     */
    public static final byte KEYWORD_IMPLEMENTS = 44;

    /**
     * Keyword "static" for creating statics instide of a classes
     */
    public static final byte KEYWORD_STATIC = 45;

    /**
     * Keyword "final" for creating a constant function, variable or class
     */
    public static final byte KEYWORD_FINAL = 46;

    /**
     * Keyword "public" for defining public functions, variables and classes
     */
    public static final byte KEYWORD_PUBLIC = 47;

    /**
     * Keyword "protected" for defining protected functions, variables and classes
     */
    public static final byte KEYWORD_PROTECTED = 48;

    /**
     * Keyword "private" for defining private functions, variables and classes
     */
    public static final byte KEYWORD_PRIVATE = 49;

    /**
     * Keyword "new" for creating new instances of a class
     */
    public static final byte KEYWORD_NEW = 50;



    /**
     * Keyword "function" for declaring functions
     */
    public static final byte KEYWORD_FUNCTION = 51;

    /**
     * Keyword "return" for returning function-results
     */
    public static final byte KEYWORD_RETURN = 52;



    // Variable Declaration

    /**
     * Keyword "var" or "let" for declaring variables
     */
    public static final byte KEYWORD_VAR = 53;

    /**
     * Keyword "const" for declaring final variables
     */
    public static final byte KEYWORD_CONST = 54;



    /**
     * Keyword "dynamic" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_DYNAMIC = 55;

    /**
     * Keyword "byte" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_BYTE = 56;

    /**
     * Keyword "short" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_SHORT = 57;

    /**
     * Keyword "int" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_INT = 58;

    /**
     * Keyword "long" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_LONG = 59;

    /**
     * Keyword "float" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_FLOAT = 60;

    /**
     * Keyword "double" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_DOUBLE = 61;

    /**
     * Keyword "char" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_CHAR = 62;

    /**
     * Keyword "boolean" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_BOOLEAN = 63;

    /**
     * Keyword "import" for import statements
     */
    public static final byte KEYWORD_IMPORT = 64;

    /**
     * Keyword "constructor" for constructor style 1
     */
    public static final byte KEYWORD_CONSTRUCTOR = 65;

    public static String getName(byte b) {
        return NAMES[b];
    }

    public static boolean hasValue(byte b) {
        return b < 5;
    }

    public static byte getTokenLength(byte b) {
        return TOKEN_LENGTH[b];
    }

    public static int getTokenLength(byte b, String value) {
        if(TOKEN_LENGTH[b] != -1) return TOKEN_LENGTH[b];
        else if(b == STRING || b == CHARACTER) return value.length() + 2;
        return value.length();
    }
}