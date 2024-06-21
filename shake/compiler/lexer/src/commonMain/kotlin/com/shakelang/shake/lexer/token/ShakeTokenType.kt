package com.shakelang.shake.lexer.token

import com.shakelang.util.parseutils.lexer.token.TokenType

/**
 * These are the different types of tokens, that the lexer creates
 */
@Suppress("MemberVisibilityCanBePrivate")
enum class ShakeTokenType(
    override val value: String?,
    val simpleValue: String,
) : TokenType {
    ADD("+", "'+'"),
    ADD_ASSIGN("+=", "'+='"),
    ASSIGN("=", "'='"),
    BIGGER(">", "'>'"),
    BIGGER_EQUALS(">=", "'>='"),
    BITWISE_AND("&", "'&'"),
    BITWISE_NOT("~", "'~'"),
    BITWISE_OR("|", "'|'"),
    BITWISE_XOR("^", "'^'"),
    BITWISE_NOR("~|", "'~|'"),
    BITWISE_NAND("~&", "'~&'"),
    BITWISE_XNOR("~^", "'~^'"),

    // Shift operation handling in lexer causes problems with generic parsing
    // For this reason we handle them in the parser
    // Take a look at https://github.com/shakelang/shake/issues/237
    //
    // BITWISE_SHL("<<", "'<<'"),
    // BITWISE_SHR(">>", "'>>'"),
    // BITWISE_SHRU(">>>", "'>>>'"),

    CHARACTER(null, "[character]"),
    COLON(":", "':'"),
    COMMA(",", "','"),
    DECR("--", "'--'"),
    DIV("/", "'/'"),
    DIV_ASSIGN("/=", "'/='"),
    DOT(".", "'.'"),
    FLOAT(null, "[float]"),
    EQ_EQUALS("==", "'=='"),
    IDENTIFIER(null, "[identifier]"),
    INCR("++", "'++'"),
    INTEGER(null, "[integer]"),
    KEYWORD_ABSTRACT("abstract", "'abstract'"),
    KEYWORD_AS("as", "'as'"),
    KEYWORD_CLASS("class", "'class'"),
    KEYWORD_CONST("const", "'const'"),
    KEYWORD_CONSTRUCTOR("constructor", "'constructor'"),
    KEYWORD_DO("do", "'do'"),
    KEYWORD_ELSE("else", "'else'"),
    KEYWORD_ENUM("enum", "'enum'"),
    KEYWORD_FALSE("false", "'false'"),
    KEYWORD_FINAL("final", "'final'"),
    KEYWORD_FOR("for", "'for'"),
    KEYWORD_FUN("fun", "'fun'"),
    KEYWORD_IF("if", "'if'"),
    KEYWORD_IMPORT("import", "'import'"),
    KEYWORD_IN("in", "'in'"),
    KEYWORD_INLINE("inline", "'inline'"),
    KEYWORD_INSTANCEOF("instanceof", "'instanceof'"),
    KEYWORD_INTERFACE("interface", "'interface'"),
    KEYWORD_NATIVE("native", "'native'"),
    KEYWORD_NULL("null", "'null'"),
    KEYWORD_OBJECT("object", "'object'"),
    KEYWORD_OPERATOR("operator", "'operator'"),
    KEYWORD_OVERRIDE("override", "'override'"),
    KEYWORD_PACKAGE("package", "'package'"),
    KEYWORD_PRIVATE("private", "'private'"),
    KEYWORD_PROTECTED("protected", "'protected'"),
    KEYWORD_PUBLIC("public", "'public'"),
    KEYWORD_RETURN("return", "'return'"),
    KEYWORD_STATIC("static", "'static'"),
    KEYWORD_SUPER("super", "'super'"),
    KEYWORD_SYNCHRONIZED("synchronized", "'synchronized'"),
    KEYWORD_THIS("this", "'this'"),
    KEYWORD_TRUE("true", "'true'"),
    KEYWORD_VAL("val", "'val'"),
    KEYWORD_VAR("var", "'var'"),
    KEYWORD_WHILE("while", "'while'"),
    LCURL("{", "'{'"),
    LINE_SEPARATOR("\n", "'\\n'"),
    LOGICAL_AND("&&", "'&&'"),
    LOGICAL_NOT("!", "'!'"),
    LOGICAL_OR("||", "'||'"),
    LOGICAL_XOR("^^", "'^^'"),
    LPAREN("(", "'('"),
    MOD("%", "'%'"),
    MOD_ASSIGN("%=", "'%='"),
    MUL("*", "'*'"),
    MUL_ASSIGN("*=", "'*='"),
    NOT_EQUALS("!=", "'!='"),
    POW("**", "'**'"),
    POW_ASSIGN("**=", "'**='"),
    RCURL("}", "'}'"),
    RPAREN(")", "')'"),
    SEMICOLON(";", "';'"),
    SMALLER("<", "'<'"),
    SMALLER_EQUALS("<=", "'<='"),
    STRING(null, "[string]"),
    SUB("-", "'-'"),
    SUB_ASSIGN("-=", "'-='"),
    EOF("[EOF]", "'[EOF]'"),
    ;

    override fun length(value: String?): Int = value?.length ?: length

    private val length: Int = value?.length ?: -1
}
