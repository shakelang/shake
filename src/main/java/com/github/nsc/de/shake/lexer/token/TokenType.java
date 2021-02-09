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
            "LOGICAL_XOR",          // 20
            "LOGICAL_AND",          // 21
            "LINE_SEPARATOR",       // 22
            "ASSIGN",               // 23
            "INCR",                 // 24
            "DECR",                 // 25
            "ADD_ASSIGN",           // 26
            "SUB_ASSIGN",           // 27
            "MUL_ASSIGN",           // 28
            "DIV_ASSIGN",           // 29
            "MOD_ASSIGN",           // 30
            "POW_ASSIGN",           // 31
            "LPAREN",               // 32
            "RPAREN",               // 33
            "LCURL",                // 34
            "RCURL",                // 35
            "KEYWORD_DO",           // 36
            "KEYWORD_WHILE",        // 37
            "KEYWORD_FOR",          // 38
            "KEYWORD_IF",           // 39
            "KEYWORD_ELSE",         // 40
            "KEYWORD_TRUE",         // 41
            "KEYWORD_FALSE",        // 42
            "KEYWORD_CLASS",        // 43
            "KEYWORD_EXTENDS",      // 44
            "KEYWORD_IMPLEMENTS",   // 45
            "KEYWORD_STATIC",       // 46
            "KEYWORD_FINAL",        // 47
            "KEYWORD_PUBLIC",       // 48
            "KEYWORD_PROTECTED",    // 49
            "KEYWORD_PRIVATE",      // 50
            "KEYWORD_NEW",          // 51
            "KEYWORD_FUNCTION",     // 52
            "KEYWORD_RETURN",       // 53
            "KEYWORD_VAR",          // 54
            "KEYWORD_CONST",        // 55
            "KEYWORD_DYNAMIC",      // 56
            "KEYWORD_BYTE",         // 57
            "KEYWORD_SHORT",        // 58
            "KEYWORD_INT",          // 59
            "KEYWORD_LONG",         // 60
            "KEYWORD_FLOAT",        // 61
            "KEYWORD_DOUBLE",       // 62
            "KEYWORD_CHAR",         // 63
            "KEYWORD_BOOLEAN",      // 64
            "KEYWORD_IMPORT",       // 65
            "KEYWORD_VOID",         // 66
            "KEYWORD_CONSTRUCTOR",  // 67
            "KEYWORD_AS",           // 68
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
            2,      //  LOGICAL_XOR          20
            2,      //  LOGICAL_AND          21
            1,      //  LINE_SEPARATOR       22
            1,      //  ASSIGN               23
            2,      //  INCR                 24
            2,      //  DECR                 25
            2,      //  ADD_ASSIGN           26
            2,      //  SUB_ASSIGN           27
            2,      //  MUL_ASSIGN           28
            2,      //  DIV_ASSIGN           29
            2,      //  MOD_ASSIGN           30
            3,      //  POW_ASSIGN           31
            1,      //  LPAREN               32
            1,      //  RPAREN               33
            1,      //  LCURL                34
            1,      //  RCURL                35
            2,      //  KEYWORD_DO           36
            5,      //  KEYWORD_WHILE        37
            3,      //  KEYWORD_FOR          38
            2,      //  KEYWORD_IF           39
            4,      //  KEYWORD_ELSE         40
            4,      //  KEYWORD_TRUE         41
            5,      //  KEYWORD_FALSE        42
            5,      //  KEYWORD_CLASS        43
            7,      //  KEYWORD_EXTENDS      44
            10,     //  KEYWORD_IMPLEMENTS   45
            6,      //  KEYWORD_STATIC       46
            5,      //  KEYWORD_FINAL        47
            6,      //  KEYWORD_PUBLIC       48
            9,      //  KEYWORD_PROTECTED    49
            7,      //  KEYWORD_PRIVATE      50
            3,      //  KEYWORD_NEW          51
            8,      //  KEYWORD_FUNCTION     52
            6,      //  KEYWORD_RETURN       53
            3,      //  KEYWORD_VAR          54
            5,      //  KEYWORD_CONST        55
            7,      //  KEYWORD_DYNAMIC      56
            4,      //  KEYWORD_BYTE         57
            5,      //  KEYWORD_SHORT        58
            3,      //  KEYWORD_INT          59
            4,      //  KEYWORD_LONG         60
            5,      //  KEYWORD_FLOAT        61
            6,      //  KEYWORD_DOUBLE       62
            4,      //  KEYWORD_CHAR         63
            7,      //  KEYWORD_BOOLEAN      64
            6,      //  KEYWORD_IMPORT       65
            4,      //  KEYWORD_VOID         66
            11,     //  KEYWORD_CONSTRUCTOR  67
            2,      //  KEYWORD_AS           68
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
     * Token '||' (logical or operator)
     */
    public static final byte LOGICAL_XOR = 20;

    /**
     * Token '&amp;&amp;' (logical and operator)
     */
    public static final byte LOGICAL_AND = 21;



    // *****************************************************
    // Separators

    /**
     * Token '\n' (separator that can be used instead of a semicolon or that will be ignored)
     */
    public static final byte LINE_SEPARATOR = 22;



    // *****************************************************
    // Assign

    /**
     * Token '=' for assigning values to variable
     */
    public static final byte ASSIGN = 23;

    /**
     * Token '++' for increasing variable values
     */
    public static final byte INCR = 24;

    /**
     * Token '--' for decreasing variable values
     */
    public static final byte DECR = 25;

    /**
     * Token '+=' for add-assigning values to a variable
     */
    public static final byte ADD_ASSIGN = 26;

    /**
     * Token '-=' for subtract-assigning values to a variable
     */
    public static final byte SUB_ASSIGN = 27;

    /**
     * Token '*=' for multiply-assigning values to a variable
     */
    public static final byte MUL_ASSIGN = 28;

    /**
     * Token '/=' for divide-assigning values to a variable
     */
    public static final byte DIV_ASSIGN = 29;

    /**
     * Token '%=' for modulo-assigning values to a variable
     */
    public static final byte MOD_ASSIGN = 30;

    /**
     * Token '^=' or '**=' for divide-assigning values to a variable
     */
    public static final byte POW_ASSIGN = 31;



    // *****************************************************
    // Brackets

    /**
     * Token '('
     */
    public static final byte LPAREN = 32;

    /**
     * Token ')'
     */
    public static final byte RPAREN = 33;

    /**
     * Token '{'
     */
    public static final byte LCURL = 34;

    /**
     * Token '}'
     */
    public static final byte RCURL = 35;




    // *****************************************************
    // Keywords

    /**
     * Keyword "do" for do-while-loops
     */
    public static final byte KEYWORD_DO = 36;

    /**
     * Keyword "while" for while-loops and do-while-loops
     */
    public static final byte KEYWORD_WHILE = 37;

    /**
     * Keyword "for" for for-loops
     */
    public static final byte KEYWORD_FOR = 38;

    /**
     * Keyword "if" for if-clauses
     */
    public static final byte KEYWORD_IF = 39;

    /**
     * Keyword "else" for if-else-clauses
     */
    public static final byte KEYWORD_ELSE = 40;

    /**
     * Keyword "true" for boolean-true-values
     */
    public static final byte KEYWORD_TRUE = 41;

    /**
     * Keyword "false" for boolean-false-values
     */
    public static final byte KEYWORD_FALSE = 42;

    // Declarations

    /**
     * Keyword "class" for declaring classes
     */
    public static final byte KEYWORD_CLASS = 43;

    /**
     * Keyword "extends" for setting a super-class in a class-declaration
     */
    public static final byte KEYWORD_EXTENDS = 44;

    /**
     * Keyword "implements" for implementing interfaces in a class-declaration
     */
    public static final byte KEYWORD_IMPLEMENTS = 45;

    /**
     * Keyword "static" for creating statics inside of classes
     */
    public static final byte KEYWORD_STATIC = 46;

    /**
     * Keyword "final" for creating a constant function, variable or class
     */
    public static final byte KEYWORD_FINAL = 47;

    /**
     * Keyword "public" for defining public functions, variables and classes
     */
    public static final byte KEYWORD_PUBLIC = 48;

    /**
     * Keyword "protected" for defining protected functions, variables and classes
     */
    public static final byte KEYWORD_PROTECTED = 49;

    /**
     * Keyword "private" for defining private functions, variables and classes
     */
    public static final byte KEYWORD_PRIVATE = 50;

    /**
     * Keyword "new" for creating new instances of a class
     */
    public static final byte KEYWORD_NEW = 51;



    /**
     * Keyword "function" for declaring functions
     */
    public static final byte KEYWORD_FUNCTION = 52;

    /**
     * Keyword "return" for returning function-results
     */
    public static final byte KEYWORD_RETURN = 53;



    // Variable Declaration

    /**
     * Keyword "var" or "let" for declaring variables
     */
    public static final byte KEYWORD_VAR = 54;

    /**
     * Keyword "const" for declaring final variables
     */
    public static final byte KEYWORD_CONST = 55;



    /**
     * Keyword "dynamic" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_DYNAMIC = 56;

    /**
     * Keyword "byte" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_BYTE = 57;

    /**
     * Keyword "short" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_SHORT = 58;

    /**
     * Keyword "int" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_INT = 59;

    /**
     * Keyword "long" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_LONG = 60;

    /**
     * Keyword "float" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_FLOAT = 61;

    /**
     * Keyword "double" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_DOUBLE = 62;

    /**
     * Keyword "char" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_CHAR = 63;

    /**
     * Keyword "boolean" as return type for functions or for declaring variables
     */
    public static final byte KEYWORD_BOOLEAN = 64;

    /**
     * Keyword "import" for import statements
     */
    public static final byte KEYWORD_IMPORT = 65;

    /**
     * Keyword "void" for functions returning nothing
     */
    public static final byte KEYWORD_VOID = 66;

    /**
     * Keyword "constructor" for constructor style 1
     */
    public static final byte KEYWORD_CONSTRUCTOR = 67;

    /**
     * Keyword "as" for casting
     */
    public static final byte KEYWORD_AS = 68;

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
