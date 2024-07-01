// Shake lexical grammar in ANTLR4 notation
// 
// This file is part of Shake. It is a grammar file for better understanding of the Shake language,
// it is not actually used in the shake language itself (as regular expressions have are not as
// performant as the hand-written lexer)
// 
// This file strictly follows the shake lexer specification at
// https://spec.shakelang.com/compiler/lexer/ to exactly mirror the behavior of the hand-written
// lexer
// 
// Indepth information about how to implement a shake lexer can be found at
// 
// Token Definition: https://spec.shakelang.com/compiler/lexer/tokens
// 
// Rule priority for a smooth implementation: https://spec.shakelang.com/compiler/lexer/

lexer grammar ShakeLexer;

// Escape Sequences
fragment ESC: '\\' [btnfr"'\\`] | '\\u' HEX HEX HEX HEX;
fragment HEX: [0-9a-fA-F];

// Lexer Steps

WS: [ \t\r\n]+ -> channel(HIDDEN);
LINE_SEPARATOR: '\n';
SEMICOLON: ';';
COMMA: ',';
COLON: ':';
DOT: '.';
FLOAT: [0-9]+ '.' [0-9]+;
HEX_NUMBER: '0x' [0-9a-fA-F]+;
OCT_NUMBER: '0o' [0-7]+;
BIN_NUMBER: '0b' [01]+;
INTEGER: [0-9]+;

// Keywords
KEYWORD_ABSTRACT: 'abstract';
KEYWORD_AS: 'as';
KEYWORD_CLASS: 'class';
KEYWORD_CONST: 'const';
KEYWORD_CONSTRUCTOR: 'constructor';
KEYWORD_DO: 'do';
KEYWORD_ELSE: 'else';
KEYWORD_ENUM: 'enum';
KEYWORD_FALSE: 'false';
KEYWORD_FINAL: 'final';
KEYWORD_FOR: 'for';
KEYWORD_FUN: 'fun';
KEYWORD_IF: 'if';
KEYWORD_IMPORT: 'import';
KEYWORD_IN: 'in';
KEYWORD_INLINE: 'inline';
KEYWORD_INSTANCEOF: 'instanceof';
KEYWORD_INTERFACE: 'interface';
KEYWORD_NATIVE: 'native';
KEYWORD_NULL: 'null';
KEYWORD_OBJECT: 'object';
KEYWORD_OPERATOR: 'operator';
KEYWORD_OVERRIDE: 'override';
KEYWORD_PACKAGE: 'package';
KEYWORD_PRIVATE: 'private';
KEYWORD_PROTECTED: 'protected';
KEYWORD_PUBLIC: 'public';
KEYWORD_RETURN: 'return';
KEYWORD_STATIC: 'static';
KEYWORD_SUPER: 'super';
KEYWORD_SYNCHRONIZED: 'synchronized';
KEYWORD_THIS: 'this';
KEYWORD_TRUE: 'true';
KEYWORD_VAL: 'val';
KEYWORD_VAR: 'var';
KEYWORD_WHILE: 'while';

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
IDENTIFIER2: '`' (ESC | ~['\\])* '`';
STRING: '"' (ESC | ~["\\])* '"';
CHAR: '\'' (ESC | ~['\\])* '\'';
SINGLE_LINE_COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);
MULTILINE_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);

// Operators
POW_ASSIGN: '**=';
MOD_ASSIGN: '%=';
DIV_ASSIGN: '/=';
MUL_ASSIGN: '*=';
SUB_ASSIGN: '-=';
ADD_ASSIGN: '+=';
INCR: '++';
DECR: '--';
POW: '**';
MOD: '%';
DIV: '/';
MUL: '*';
SUB: '-';
ADD: '+';
LOGICAL_OR: '||';
LOGICAL_AND: '&&';
LOGICAL_XOR: '^^';
EQUALS: '==';
GTE: '>=';
LTE: '<=';
NEQ: '!=';
GT: '>';
LT: '<';
NOT: '!';
BIT_NAND: '~&';
BIT_NOR: '~|';
BIT_XNOR: '~^';
BIT_AND: '&';
BIT_OR: '|';
BIT_XOR: '^';
ASSIGN: '=';
LPAREN: '(';
RPAREN: ')';
LCURLY: '{';
RCURLY: '}';
LBRACK: '[';
RBRACK: ']';