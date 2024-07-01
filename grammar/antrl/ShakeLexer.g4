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

import UnicodeClasses;

// Keywords