@file:Suppress("NOTHING_TO_INLINE")

package com.shakelang.shake.lexer.token

import com.shakelang.util.parseutils.lexer.token.TokenType

/**
 * These are the different types of tokens, that the lexer creates
 */
@Suppress("MemberVisibilityCanBePrivate")
enum class ShakeTokenType(
    val length: Int,
    val simpleValue: String,
) : TokenType {
    ADD(1, "'+'"),
    ADD_ASSIGN(2, "'+='"),
    ASSIGN(1, "'='"),
    BIGGER(1, "'>'"),
    BIGGER_EQUALS(2, "'>='"),
    BITWISE_AND(1, "'&'"),
    BITWISE_NOT(1, "'~'"),
    BITWISE_OR(1, "'|'"),
    BITWISE_XOR(1, "'^'"),
    BITWISE_NOR(2, "'~|'"),
    BITWISE_NAND(2, "'~&'"),
    BITWISE_XNOR(2, "'~^'"),
    BITWISE_SHL(2, "'<<'"),
    BITWISE_SHR(2, "'>>'"),
    BITWISE_USHR(3, "'>>>'"),
    CHARACTER(-1, "character"),
    COLON(1, "':'"),
    COMMA(1, "','"),
    DECR(2, "'--'"),
    DIV(1, "'/'"),
    DIV_ASSIGN(2, "'/='"),
    DOT(1, "'.'"),
    FLOAT(-1, "[float]"),
    EQ_EQUALS(2, "'=='"),
    IDENTIFIER(-1, "[identifier]"),
    INCR(2, "'++'"),
    INTEGER(-1, "[integer]"),
    KEYWORD_ABSTRACT(8, "abstract"),
    KEYWORD_AS(2, "as"),
    KEYWORD_CLASS(5, "class"),
    KEYWORD_CONST(5, "const"),
    KEYWORD_CONSTRUCTOR(11, "constructor"),
    KEYWORD_DO(2, "do"),
    KEYWORD_ELSE(4, "else"),
    KEYWORD_ENUM(4, "enum"),
    KEYWORD_FALSE(5, "false"),
    KEYWORD_FINAL(5, "final"),
    KEYWORD_FOR(3, "for"),
    KEYWORD_FUN(3, "fun"),
    KEYWORD_IF(2, "if"),
    KEYWORD_IMPORT(6, "import"),
    KEYWORD_IN(2, "in"),
    KEYWORD_INLINE(7, "inline"),
    KEYWORD_INSTANCEOF(11, "instanceof"),
    KEYWORD_INTERFACE(9, "interface"),
    KEYWORD_NATIVE(6, "native"),
    KEYWORD_NEW(3, "new"),
    KEYWORD_NULL(4, "null"),
    KEYWORD_OBJECT(6, "object"),
    KEYWORD_OPERATOR(8, "operator"),
    KEYWORD_OVERRIDE(8, "override"),
    KEYWORD_PACKAGE(7, "package"),
    KEYWORD_PRIVATE(7, "private"),
    KEYWORD_PROTECTED(9, "protected"),
    KEYWORD_PUBLIC(6, "public"),
    KEYWORD_RETURN(6, "return"),
    KEYWORD_STATIC(6, "static"),
    KEYWORD_SUPER(5, "super"),
    KEYWORD_SYNCHRONIZED(12, "synchronized"),
    KEYWORD_THIS(4, "this"),
    KEYWORD_TRUE(4, "true"),
    KEYWORD_VAL(3, "val"),
    KEYWORD_VAR(3, "var"),
    KEYWORD_WHILE(5, "while"),
    LCURL(1, "'{'"),
    LINE_SEPARATOR(1, "'\\n'"),
    LOGICAL_AND(2, "'&&'"),
    LOGICAL_NOT(1, "'!'"),
    LOGICAL_OR(2, "'||'"),
    LOGICAL_XOR(1, "'^^'"),
    LPAREN(1, "'('"),
    MOD(1, "'%'"),
    MOD_ASSIGN(2, "'%='"),
    MUL(1, "'*'"),
    MUL_ASSIGN(2, "'*='"),
    NOT_EQUALS(2, "'!='"),
    POW(2, "'**'"),
    POW_ASSIGN(3, "'**='"),
    RCURL(1, "'}'"),
    RPAREN(1, "')'"),
    SEMICOLON(1, "';'"),
    SMALLER(1, "'<'"),
    SMALLER_EQUALS(2, "'<='"),
    STRING(-1, "[string]"),
    SUB(1, "'-'"),
    SUB_ASSIGN(2, "'-='"),
    EOF(0, "[EOF]"),
    ;

    override fun length(value: String?): Int {
        return if (hasValue) value?.length ?: 0 else length
    }

    override val hasValue: Boolean get() = length < 0
    val tokenName: String get() = name
    val tokenLength: Int get() = length
}
