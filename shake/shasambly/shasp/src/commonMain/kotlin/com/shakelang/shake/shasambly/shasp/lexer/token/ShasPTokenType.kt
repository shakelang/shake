@file:Suppress("NOTHING_TO_INLINE")

package com.shakelang.shake.shasambly.shasp.lexer.token

import com.shakelang.util.parseutils.lexer.token.TokenType

/**
 * These are the different types of tokens, that the lexer creates
 */
enum class ShasPTokenType(private val size: Int, override val hasValue: Boolean = false) : TokenType {

    /**
     * Identifier for variables, functions and classes
     */
    IDENTIFIER(-1, true),

    /**
     * Integer literal
     */
    INTEGER(-1, true),

    /**
     * Double literal
     */
    DOUBLE(-1, true),

    /**
     * Character literal
     */
    CHARACTER(-1, true),

    /**
     * String literal
     */
    STRING(-1, true),

    /**
     * Comma
     */
    COMMA(1),

    /**
     * Dot
     */
    DOT(1),

    /**
     * Semicolon
     */
    SEMICOLON(1),

    /**
     * Addition
     */
    ADD(1),

    /**
     * Subtraction
     */
    SUB(1),

    /**
     * Multiplication
     */
    MUL(1),

    /**
     * Division
     */
    DIV(1),

    /**
     * Modulo
     */
    MOD(1),

    /**
     * Equal
     */
    EQ_EQUALS(2),

    /**
     * Not Equal
     */
    NOT_EQUALS(2),

    /**
     * Bigger than
     */
    BIGGER_EQUALS(2),

    /**
     * Smaller than
     */
    SMALLER_EQUALS(2),

    /**
     * Bigger
     */
    BIGGER(2),

    /**
     * Smaller
     */
    SMALLER(2),

    /**
     * Logical or
     */
    LOGICAL_OR(2),

    /**
     * Logical xor
     */
    LOGICAL_XOR(1),

    /**
     * Logical and
     */
    LOGICAL_AND(2),

    /**
     * Assignment
     */
    ASSIGN(1),

    /**
     * Increment
     */
    INCR(2),

    /**
     * Decrement
     */
    DECR(2),

    /**
     * Addition assignment
     */
    ADD_ASSIGN(2),

    /**
     * Subtraction assignment
     */
    SUB_ASSIGN(2),

    /**
     * Multiplication assignment
     */
    MUL_ASSIGN(2),

    /**
     * Division assignment
     */
    DIV_ASSIGN(2),

    /**
     * Modulo assignment
     */
    MOD_ASSIGN(2),

    /**
     * Left parenthesis
     */
    LPAREN(1),

    /**
     * Right parenthesis
     */
    RPAREN(1),

    /**
     * Left curly bracket
     */
    LCURL(1),

    /**
     * Right curly bracket
     */
    RCURL(1),

    /**
     * Left bracket
     */
    LBRACKET(1),

    /**
     * Right bracket
     */
    RBRACKET(1),

    /**
     * Do keyword
     */
    KEYWORD_DO(2),

    /**
     * While keyword
     */
    KEYWORD_WHILE(5),

    /**
     * For keyword
     */
    KEYWORD_FOR(3),

    /**
     * If keyword
     */
    KEYWORD_IF(2),

    /**
     * Else keyword
     */
    KEYWORD_ELSE(4),

    /**
     * True keyword
     */
    KEYWORD_TRUE(4),

    /**
     * False keyword
     */
    KEYWORD_FALSE(5),

    /**
     * Return keyword
     */
    KEYWORD_RETURN(6),

    /**
     * Byte keyword
     */
    KEYWORD_BYTE(4),

    /**
     * Short keyword
     */
    KEYWORD_SHORT(5),

    /**
     * Int keyword
     */
    KEYWORD_INT(3),

    /**
     * Long keyword
     */
    KEYWORD_LONG(4),

    /**
     * Float keyword
     */
    KEYWORD_FLOAT(5),

    /**
     * Double keyword
     */
    KEYWORD_DOUBLE(6),

    /**
     * Char keyword
     */
    KEYWORD_CHAR(4),

    /**
     * Boolean keyword
     */
    KEYWORD_BOOLEAN(7),

    /**
     * Void keyword
     */
    KEYWORD_VOID(4),

    /**
     * As keyword
     */
    KEYWORD_AS(2),

    /**
     * Unsigned keyword
     */
    KEYWORD_UNSIGNED(8),

    /**
     * New keyword
     */
    KEYWORD_NEW(3),
    ;

    fun length(): Int {
        return size
    }

    override fun length(value: String?): Int {
        return if (size == -1) value!!.length else size
    }
}
