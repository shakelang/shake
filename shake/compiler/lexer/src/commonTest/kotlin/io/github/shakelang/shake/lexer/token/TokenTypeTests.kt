package io.github.shakelang.shake.lexer.token

import kotlin.test.Test
import kotlin.test.assertEquals

class TokenTypeTests {

    private val NAMES = arrayOf(
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
        "KEYWORD_AS"            // 68
    )
    private val TOKEN_LENGTH = byteArrayOf( // The length of the tokens
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
        2       // KEYWORD_AS            68
    )

    @Test
    fun testGetName() {
        assertEquals("IDENTIFIER", TokenType.IDENTIFIER.tokenName)
        assertEquals("INTEGER", TokenType.INTEGER.tokenName)
        assertEquals("DOUBLE", TokenType.DOUBLE.tokenName)
        assertEquals("CHARACTER", TokenType.CHARACTER.tokenName)
        assertEquals("STRING", TokenType.STRING.tokenName)
        assertEquals("COMMA", TokenType.COMMA.tokenName)
        assertEquals("DOT", TokenType.DOT.tokenName)
        assertEquals("SEMICOLON", TokenType.SEMICOLON.tokenName)
        assertEquals("ADD", TokenType.ADD.tokenName)
        assertEquals("SUB", TokenType.SUB.tokenName)
        assertEquals("MUL", TokenType.MUL.tokenName)
        assertEquals("DIV", TokenType.DIV.tokenName)
        assertEquals("MOD", TokenType.MOD.tokenName)
        assertEquals("POW", TokenType.POW.tokenName)
        assertEquals("EQ_EQUALS", TokenType.EQ_EQUALS.tokenName)
        assertEquals("BIGGER_EQUALS", TokenType.BIGGER_EQUALS.tokenName)
        assertEquals("SMALLER_EQUALS", TokenType.SMALLER_EQUALS.tokenName)
        assertEquals("BIGGER", TokenType.BIGGER.tokenName)
        assertEquals("SMALLER", TokenType.SMALLER.tokenName)
        assertEquals("LOGICAL_OR", TokenType.LOGICAL_OR.tokenName)
        assertEquals("LOGICAL_XOR", TokenType.LOGICAL_XOR.tokenName)
        assertEquals("LOGICAL_AND", TokenType.LOGICAL_AND.tokenName)
        assertEquals("LINE_SEPARATOR", TokenType.LINE_SEPARATOR.tokenName)
        assertEquals("ASSIGN", TokenType.ASSIGN.tokenName)
        assertEquals("INCR", TokenType.INCR.tokenName)
        assertEquals("DECR", TokenType.DECR.tokenName)
        assertEquals("ADD_ASSIGN", TokenType.ADD_ASSIGN.tokenName)
        assertEquals("SUB_ASSIGN", TokenType.SUB_ASSIGN.tokenName)
        assertEquals("MUL_ASSIGN", TokenType.MUL_ASSIGN.tokenName)
        assertEquals("DIV_ASSIGN", TokenType.DIV_ASSIGN.tokenName)
        assertEquals("MOD_ASSIGN", TokenType.MOD_ASSIGN.tokenName)
        assertEquals("POW_ASSIGN", TokenType.POW_ASSIGN.tokenName)
        assertEquals("LPAREN", TokenType.LPAREN.tokenName)
        assertEquals("RPAREN", TokenType.RPAREN.tokenName)
        assertEquals("LCURL", TokenType.LCURL.tokenName)
        assertEquals("RCURL", TokenType.RCURL.tokenName)
        assertEquals("KEYWORD_DO", TokenType.KEYWORD_DO.tokenName)
        assertEquals("KEYWORD_WHILE", TokenType.KEYWORD_WHILE.tokenName)
        assertEquals("KEYWORD_FOR", TokenType.KEYWORD_FOR.tokenName)
        assertEquals("KEYWORD_IF", TokenType.KEYWORD_IF.tokenName)
        assertEquals("KEYWORD_ELSE", TokenType.KEYWORD_ELSE.tokenName)
        assertEquals("KEYWORD_TRUE", TokenType.KEYWORD_TRUE.tokenName)
        assertEquals("KEYWORD_FALSE", TokenType.KEYWORD_FALSE.tokenName)
        assertEquals("KEYWORD_CLASS", TokenType.KEYWORD_CLASS.tokenName)
        assertEquals("KEYWORD_EXTENDS", TokenType.KEYWORD_EXTENDS.tokenName)
        assertEquals("KEYWORD_IMPLEMENTS", TokenType.KEYWORD_IMPLEMENTS.tokenName)
        assertEquals("KEYWORD_STATIC", TokenType.KEYWORD_STATIC.tokenName)
        assertEquals("KEYWORD_FINAL", TokenType.KEYWORD_FINAL.tokenName)
        assertEquals("KEYWORD_PUBLIC", TokenType.KEYWORD_PUBLIC.tokenName)
        assertEquals("KEYWORD_PROTECTED", TokenType.KEYWORD_PROTECTED.tokenName)
        assertEquals("KEYWORD_PRIVATE", TokenType.KEYWORD_PRIVATE.tokenName)
        assertEquals("KEYWORD_NEW", TokenType.KEYWORD_NEW.tokenName)
        assertEquals("KEYWORD_FUNCTION", TokenType.KEYWORD_FUNCTION.tokenName)
        assertEquals("KEYWORD_RETURN", TokenType.KEYWORD_RETURN.tokenName)
        assertEquals("KEYWORD_VAR", TokenType.KEYWORD_VAR.tokenName)
        assertEquals("KEYWORD_CONST", TokenType.KEYWORD_CONST.tokenName)
        assertEquals("KEYWORD_DYNAMIC", TokenType.KEYWORD_DYNAMIC.tokenName)
        assertEquals("KEYWORD_BYTE", TokenType.KEYWORD_BYTE.tokenName)
        assertEquals("KEYWORD_SHORT", TokenType.KEYWORD_SHORT.tokenName)
        assertEquals("KEYWORD_INT", TokenType.KEYWORD_INT.tokenName)
        assertEquals("KEYWORD_LONG", TokenType.KEYWORD_LONG.tokenName)
        assertEquals("KEYWORD_FLOAT", TokenType.KEYWORD_FLOAT.tokenName)
        assertEquals("KEYWORD_DOUBLE", TokenType.KEYWORD_DOUBLE.tokenName)
        assertEquals("KEYWORD_CHAR", TokenType.KEYWORD_CHAR.tokenName)
        assertEquals("KEYWORD_BOOLEAN", TokenType.KEYWORD_BOOLEAN.tokenName)
        assertEquals("KEYWORD_IMPORT", TokenType.KEYWORD_IMPORT.tokenName)
        assertEquals("KEYWORD_VOID", TokenType.KEYWORD_VOID.tokenName)
        assertEquals("KEYWORD_CONSTRUCTOR", TokenType.KEYWORD_CONSTRUCTOR.tokenName)
        assertEquals("KEYWORD_AS", TokenType.KEYWORD_AS.tokenName)
    }

    @Test
    fun testTokenLength() {
        assertEquals(-1, TokenType.IDENTIFIER.tokenLength)
        assertEquals(-1, TokenType.INTEGER.tokenLength)
        assertEquals(-1, TokenType.DOUBLE.tokenLength)
        assertEquals(-1, TokenType.CHARACTER.tokenLength)
        assertEquals(-1, TokenType.STRING.tokenLength)
        assertEquals(1, TokenType.COMMA.tokenLength)
        assertEquals(1, TokenType.DOT.tokenLength)
        assertEquals(1, TokenType.SEMICOLON.tokenLength)
        assertEquals(1, TokenType.ADD.tokenLength)
        assertEquals(1, TokenType.SUB.tokenLength)
        assertEquals(1, TokenType.MUL.tokenLength)
        assertEquals(1, TokenType.DIV.tokenLength)
        assertEquals(1, TokenType.MOD.tokenLength)
        assertEquals(2, TokenType.POW.tokenLength)
        assertEquals(2, TokenType.EQ_EQUALS.tokenLength)
        assertEquals(2, TokenType.BIGGER_EQUALS.tokenLength)
        assertEquals(2, TokenType.SMALLER_EQUALS.tokenLength)
        assertEquals(1, TokenType.BIGGER.tokenLength)
        assertEquals(1, TokenType.SMALLER.tokenLength)
        assertEquals(2, TokenType.LOGICAL_OR.tokenLength)
        assertEquals(1, TokenType.LOGICAL_XOR.tokenLength)
        assertEquals(2, TokenType.LOGICAL_AND.tokenLength)
        assertEquals(1, TokenType.LINE_SEPARATOR.tokenLength)
        assertEquals(1, TokenType.ASSIGN.tokenLength)
        assertEquals(2, TokenType.INCR.tokenLength)
        assertEquals(2, TokenType.DECR.tokenLength)
        assertEquals(2, TokenType.ADD_ASSIGN.tokenLength)
        assertEquals(2, TokenType.SUB_ASSIGN.tokenLength)
        assertEquals(2, TokenType.MUL_ASSIGN.tokenLength)
        assertEquals(2, TokenType.DIV_ASSIGN.tokenLength)
        assertEquals(2, TokenType.MOD_ASSIGN.tokenLength)
        assertEquals(3, TokenType.POW_ASSIGN.tokenLength)
        assertEquals(1, TokenType.LPAREN.tokenLength)
        assertEquals(1, TokenType.RPAREN.tokenLength)
        assertEquals(1, TokenType.LCURL.tokenLength)
        assertEquals(1, TokenType.RCURL.tokenLength)
        assertEquals(2, TokenType.KEYWORD_DO.tokenLength)
        assertEquals(5, TokenType.KEYWORD_WHILE.tokenLength)
        assertEquals(3, TokenType.KEYWORD_FOR.tokenLength)
        assertEquals(2, TokenType.KEYWORD_IF.tokenLength)
        assertEquals(4, TokenType.KEYWORD_ELSE.tokenLength)
        assertEquals(4, TokenType.KEYWORD_TRUE.tokenLength)
        assertEquals(5, TokenType.KEYWORD_FALSE.tokenLength)
        assertEquals(5, TokenType.KEYWORD_CLASS.tokenLength)
        assertEquals(7, TokenType.KEYWORD_EXTENDS.tokenLength)
        assertEquals(10, TokenType.KEYWORD_IMPLEMENTS.tokenLength)
        assertEquals(6, TokenType.KEYWORD_STATIC.tokenLength)
        assertEquals(5, TokenType.KEYWORD_FINAL.tokenLength)
        assertEquals(6, TokenType.KEYWORD_PUBLIC.tokenLength)
        assertEquals(9, TokenType.KEYWORD_PROTECTED.tokenLength)
        assertEquals(7, TokenType.KEYWORD_PRIVATE.tokenLength)
        assertEquals(3, TokenType.KEYWORD_NEW.tokenLength)
        assertEquals(8, TokenType.KEYWORD_FUNCTION.tokenLength)
        assertEquals(6, TokenType.KEYWORD_RETURN.tokenLength)
        assertEquals(3, TokenType.KEYWORD_VAR.tokenLength)
        assertEquals(5, TokenType.KEYWORD_CONST.tokenLength)
        assertEquals(7, TokenType.KEYWORD_DYNAMIC.tokenLength)
        assertEquals(4, TokenType.KEYWORD_BYTE.tokenLength)
        assertEquals(5, TokenType.KEYWORD_SHORT.tokenLength)
        assertEquals(3, TokenType.KEYWORD_INT.tokenLength)
        assertEquals(4, TokenType.KEYWORD_LONG.tokenLength)
        assertEquals(5, TokenType.KEYWORD_FLOAT.tokenLength)
        assertEquals(6, TokenType.KEYWORD_DOUBLE.tokenLength)
        assertEquals(4, TokenType.KEYWORD_CHAR.tokenLength)
        assertEquals(7, TokenType.KEYWORD_BOOLEAN.tokenLength)
        assertEquals(6, TokenType.KEYWORD_IMPORT.tokenLength)
        assertEquals(4, TokenType.KEYWORD_VOID.tokenLength)
        assertEquals(11, TokenType.KEYWORD_CONSTRUCTOR.tokenLength)
        assertEquals(2, TokenType.KEYWORD_AS.tokenLength)
    }

    @Test
    fun testTokenLength2() {
        assertEquals(-1, TokenType.IDENTIFIER.tokenLength())
        assertEquals(-1, TokenType.INTEGER.tokenLength())
        assertEquals(-1, TokenType.DOUBLE.tokenLength())
        assertEquals(-1, TokenType.CHARACTER.tokenLength())
        assertEquals(-1, TokenType.STRING.tokenLength())
        assertEquals(1, TokenType.COMMA.tokenLength())
        assertEquals(1, TokenType.DOT.tokenLength())
        assertEquals(1, TokenType.SEMICOLON.tokenLength())
        assertEquals(1, TokenType.ADD.tokenLength())
        assertEquals(1, TokenType.SUB.tokenLength())
        assertEquals(1, TokenType.MUL.tokenLength())
        assertEquals(1, TokenType.DIV.tokenLength())
        assertEquals(1, TokenType.MOD.tokenLength())
        assertEquals(2, TokenType.POW.tokenLength())
        assertEquals(2, TokenType.EQ_EQUALS.tokenLength())
        assertEquals(2, TokenType.BIGGER_EQUALS.tokenLength())
        assertEquals(2, TokenType.SMALLER_EQUALS.tokenLength())
        assertEquals(1, TokenType.BIGGER.tokenLength())
        assertEquals(1, TokenType.SMALLER.tokenLength())
        assertEquals(2, TokenType.LOGICAL_OR.tokenLength())
        assertEquals(1, TokenType.LOGICAL_XOR.tokenLength())
        assertEquals(2, TokenType.LOGICAL_AND.tokenLength())
        assertEquals(1, TokenType.LINE_SEPARATOR.tokenLength())
        assertEquals(1, TokenType.ASSIGN.tokenLength())
        assertEquals(2, TokenType.INCR.tokenLength())
        assertEquals(2, TokenType.DECR.tokenLength())
        assertEquals(2, TokenType.ADD_ASSIGN.tokenLength())
        assertEquals(2, TokenType.SUB_ASSIGN.tokenLength())
        assertEquals(2, TokenType.MUL_ASSIGN.tokenLength())
        assertEquals(2, TokenType.DIV_ASSIGN.tokenLength())
        assertEquals(2, TokenType.MOD_ASSIGN.tokenLength())
        assertEquals(3, TokenType.POW_ASSIGN.tokenLength())
        assertEquals(1, TokenType.LPAREN.tokenLength())
        assertEquals(1, TokenType.RPAREN.tokenLength())
        assertEquals(1, TokenType.LCURL.tokenLength())
        assertEquals(1, TokenType.RCURL.tokenLength())
        assertEquals(2, TokenType.KEYWORD_DO.tokenLength())
        assertEquals(5, TokenType.KEYWORD_WHILE.tokenLength())
        assertEquals(3, TokenType.KEYWORD_FOR.tokenLength())
        assertEquals(2, TokenType.KEYWORD_IF.tokenLength())
        assertEquals(4, TokenType.KEYWORD_ELSE.tokenLength())
        assertEquals(4, TokenType.KEYWORD_TRUE.tokenLength())
        assertEquals(5, TokenType.KEYWORD_FALSE.tokenLength())
        assertEquals(5, TokenType.KEYWORD_CLASS.tokenLength())
        assertEquals(7, TokenType.KEYWORD_EXTENDS.tokenLength())
        assertEquals(10, TokenType.KEYWORD_IMPLEMENTS.tokenLength())
        assertEquals(6, TokenType.KEYWORD_STATIC.tokenLength())
        assertEquals(5, TokenType.KEYWORD_FINAL.tokenLength())
        assertEquals(6, TokenType.KEYWORD_PUBLIC.tokenLength())
        assertEquals(9, TokenType.KEYWORD_PROTECTED.tokenLength())
        assertEquals(7, TokenType.KEYWORD_PRIVATE.tokenLength())
        assertEquals(3, TokenType.KEYWORD_NEW.tokenLength())
        assertEquals(8, TokenType.KEYWORD_FUNCTION.tokenLength())
        assertEquals(6, TokenType.KEYWORD_RETURN.tokenLength())
        assertEquals(3, TokenType.KEYWORD_VAR.tokenLength())
        assertEquals(5, TokenType.KEYWORD_CONST.tokenLength())
        assertEquals(7, TokenType.KEYWORD_DYNAMIC.tokenLength())
        assertEquals(4, TokenType.KEYWORD_BYTE.tokenLength())
        assertEquals(5, TokenType.KEYWORD_SHORT.tokenLength())
        assertEquals(3, TokenType.KEYWORD_INT.tokenLength())
        assertEquals(4, TokenType.KEYWORD_LONG.tokenLength())
        assertEquals(5, TokenType.KEYWORD_FLOAT.tokenLength())
        assertEquals(6, TokenType.KEYWORD_DOUBLE.tokenLength())
        assertEquals(4, TokenType.KEYWORD_CHAR.tokenLength())
        assertEquals(7, TokenType.KEYWORD_BOOLEAN.tokenLength())
        assertEquals(6, TokenType.KEYWORD_IMPORT.tokenLength())
        assertEquals(4, TokenType.KEYWORD_VOID.tokenLength())
        assertEquals(11, TokenType.KEYWORD_CONSTRUCTOR.tokenLength())
        assertEquals(2, TokenType.KEYWORD_AS.tokenLength())
    }

    @Test
    fun testTokenLength3() {
        assertEquals(5, TokenType.IDENTIFIER.tokenLength("hello"))
        assertEquals(2, TokenType.INTEGER.tokenLength("10"))
        assertEquals(4, TokenType.DOUBLE.tokenLength("10.0"))
        assertEquals(3, TokenType.CHARACTER.tokenLength("a"))
        assertEquals(7, TokenType.STRING.tokenLength("hello"))
        assertEquals(1, TokenType.COMMA.tokenLength(""))
        assertEquals(1, TokenType.DOT.tokenLength(""))
        assertEquals(1, TokenType.SEMICOLON.tokenLength(""))
        assertEquals(1, TokenType.ADD.tokenLength(""))
        assertEquals(1, TokenType.SUB.tokenLength(""))
        assertEquals(1, TokenType.MUL.tokenLength(""))
        assertEquals(1, TokenType.DIV.tokenLength(""))
        assertEquals(1, TokenType.MOD.tokenLength(""))
        assertEquals(2, TokenType.POW.tokenLength(""))
        assertEquals(2, TokenType.EQ_EQUALS.tokenLength(""))
        assertEquals(2, TokenType.BIGGER_EQUALS.tokenLength(""))
        assertEquals(2, TokenType.SMALLER_EQUALS.tokenLength(""))
        assertEquals(1, TokenType.BIGGER.tokenLength(""))
        assertEquals(1, TokenType.SMALLER.tokenLength(""))
        assertEquals(2, TokenType.LOGICAL_OR.tokenLength(""))
        assertEquals(1, TokenType.LOGICAL_XOR.tokenLength(""))
        assertEquals(2, TokenType.LOGICAL_AND.tokenLength(""))
        assertEquals(1, TokenType.LINE_SEPARATOR.tokenLength(""))
        assertEquals(1, TokenType.ASSIGN.tokenLength(""))
        assertEquals(2, TokenType.INCR.tokenLength(""))
        assertEquals(2, TokenType.DECR.tokenLength(""))
        assertEquals(2, TokenType.ADD_ASSIGN.tokenLength(""))
        assertEquals(2, TokenType.SUB_ASSIGN.tokenLength(""))
        assertEquals(2, TokenType.MUL_ASSIGN.tokenLength(""))
        assertEquals(2, TokenType.DIV_ASSIGN.tokenLength(""))
        assertEquals(2, TokenType.MOD_ASSIGN.tokenLength(""))
        assertEquals(3, TokenType.POW_ASSIGN.tokenLength(""))
        assertEquals(1, TokenType.LPAREN.tokenLength(""))
        assertEquals(1, TokenType.RPAREN.tokenLength(""))
        assertEquals(1, TokenType.LCURL.tokenLength(""))
        assertEquals(1, TokenType.RCURL.tokenLength(""))
        assertEquals(2, TokenType.KEYWORD_DO.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_WHILE.tokenLength(""))
        assertEquals(3, TokenType.KEYWORD_FOR.tokenLength(""))
        assertEquals(2, TokenType.KEYWORD_IF.tokenLength(""))
        assertEquals(4, TokenType.KEYWORD_ELSE.tokenLength(""))
        assertEquals(4, TokenType.KEYWORD_TRUE.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_FALSE.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_CLASS.tokenLength(""))
        assertEquals(7, TokenType.KEYWORD_EXTENDS.tokenLength(""))
        assertEquals(10, TokenType.KEYWORD_IMPLEMENTS.tokenLength(""))
        assertEquals(6, TokenType.KEYWORD_STATIC.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_FINAL.tokenLength(""))
        assertEquals(6, TokenType.KEYWORD_PUBLIC.tokenLength(""))
        assertEquals(9, TokenType.KEYWORD_PROTECTED.tokenLength(""))
        assertEquals(7, TokenType.KEYWORD_PRIVATE.tokenLength(""))
        assertEquals(3, TokenType.KEYWORD_NEW.tokenLength(""))
        assertEquals(8, TokenType.KEYWORD_FUNCTION.tokenLength(""))
        assertEquals(6, TokenType.KEYWORD_RETURN.tokenLength(""))
        assertEquals(3, TokenType.KEYWORD_VAR.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_CONST.tokenLength(""))
        assertEquals(7, TokenType.KEYWORD_DYNAMIC.tokenLength(""))
        assertEquals(4, TokenType.KEYWORD_BYTE.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_SHORT.tokenLength(""))
        assertEquals(3, TokenType.KEYWORD_INT.tokenLength(""))
        assertEquals(4, TokenType.KEYWORD_LONG.tokenLength(""))
        assertEquals(5, TokenType.KEYWORD_FLOAT.tokenLength(""))
        assertEquals(6, TokenType.KEYWORD_DOUBLE.tokenLength(""))
        assertEquals(4, TokenType.KEYWORD_CHAR.tokenLength(""))
        assertEquals(7, TokenType.KEYWORD_BOOLEAN.tokenLength(""))
        assertEquals(6, TokenType.KEYWORD_IMPORT.tokenLength(""))
        assertEquals(4, TokenType.KEYWORD_VOID.tokenLength(""))
        assertEquals(11, TokenType.KEYWORD_CONSTRUCTOR.tokenLength(""))
        assertEquals(2, TokenType.KEYWORD_AS.tokenLength(""))
    }

}