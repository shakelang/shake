parser grammar ShakeParser;

options {
	tokenVocab = ShakeLexer;
}

shakeFile: shakeFileChild*;

shakeFileChild: EOF;

shakePackageDeclaration: KEYWORD_PACKAGE namespace;

namespace: (identifier = IDENTIFIER)
	| namespace (dot = DOT) (identifier = IDENTIFIER);