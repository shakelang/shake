# Shake Antrl Grammar

This folder contains a ANTLR4 grammar for the shake language. The grammar consists of three g4 files:

- [`ShakeLexer.g4`](./ShakeLexer.g4): The lexer grammar file.
- [`ShakeParser.g4`](./ShakeParser.g4): The parser grammar file.
- [`Shake.g4`](./Shake.g4): The combined grammar file.

The grammar files are strictly following the shake language specification and should mirror the exact behavior of the shake compiler implementation.

- [Lexer Specification](https://spec.shakelang.com/compiler/category/lexer)
- [Parser Specification](https://spec.shakelang.com/compiler/category/parser)
