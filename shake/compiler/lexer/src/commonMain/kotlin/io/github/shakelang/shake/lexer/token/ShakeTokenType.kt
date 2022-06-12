@file:Suppress("NOTHING_TO_INLINE")
package io.github.shakelang.shake.lexer.token

import io.github.shakelang.parseutils.lexer.token.TokenType

/**
 * These are the different types of tokens, that the lexer creates
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
enum class ShakeTokenType(private val length: Int) : TokenType {
    ADD(1),
    ADD_ASSIGN(2),
    ASSIGN(1),
    BIGGER(1),
    BIGGER_EQUALS (2),
    CHARACTER(-1),
    COMMA(1),
    DECR(2),
    DIV(1),
    DIV_ASSIGN(2),
    DOT(1),
    DOUBLE(-1),
    EQ_EQUALS(2),
    IDENTIFIER(-1),
    INCR(2),
    INTEGER(-1),
    KEYWORD_ABSTRACT(8),
    KEYWORD_AS(2),
    KEYWORD_BOOLEAN(7),
    KEYWORD_BYTE(4),
    KEYWORD_CHAR(4),
    KEYWORD_CLASS(5),
    KEYWORD_CONST(5),
    KEYWORD_CONSTRUCTOR(11),
    KEYWORD_DO(2),
    KEYWORD_DOUBLE(6),
    KEYWORD_DYNAMIC(7),
    KEYWORD_ELSE(4),
    KEYWORD_ENUM(4),
    KEYWORD_EXTENDS(7),
    KEYWORD_FALSE(5),
    KEYWORD_FINAL(5),
    KEYWORD_FLOAT(5),
    KEYWORD_FOR(3),
    KEYWORD_FUNCTION(8),
    KEYWORD_IF(2),
    KEYWORD_IMPLEMENTS(10),
    KEYWORD_IMPORT(6),
    KEYWORD_INT(3),
    KEYWORD_INTERFACE(9),
    KEYWORD_LONG(4),
    KEYWORD_NEW(3),
    KEYWORD_NULL(4),
    KEYWORD_OBJECT(6),
    KEYWORD_PACKAGE(7),
    KEYWORD_PRIVATE(7),
    KEYWORD_PROTECTED(9),
    KEYWORD_PUBLIC(6),
    KEYWORD_RETURN(6),
    KEYWORD_SHORT(5),
    KEYWORD_STATIC(6),
    KEYWORD_TRUE(4),
    KEYWORD_VAR(3),
    KEYWORD_VOID(4),
    KEYWORD_WHILE(5),
    LCURL(1),
    LINE_SEPARATOR(1),
    LOGICAL_AND(2),
    LOGICAL_OR(2),
    LOGICAL_XOR(1),
    LPAREN(1),
    MOD(1),
    MOD_ASSIGN(2),
    MUL(1),
    MUL_ASSIGN(2),
    NONE(0),
    POW(2),
    POW_ASSIGN(3),
    RCURL(1),
    RPAREN(1),
    SEMICOLON(1),
    SMALLER(1),
    SMALLER_EQUALS(2),
    STRING(-1),
    SUB(1),
    SUB_ASSIGN(2),
    ;

    override fun length(value: String?): Int {
        return if(hasValue) value?.length ?: 0 else length
    }
    override val hasValue: Boolean get() = length < 0
    val tokenName: String get() = name
    val tokenLength: Int get() = length
}