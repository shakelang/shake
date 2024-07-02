parser grammar ShakeParser;

options {
	tokenVocab = ShakeLexer;
}

// MISC
shakeModifier:
	KEYWORD_PUBLIC
	| KEYWORD_PRIVATE
	| KEYWORD_PROTECTED
	| KEYWORD_STATIC
	| KEYWORD_FINAL
	| KEYWORD_ABSTRACT
	| KEYWORD_NATIVE
	| KEYWORD_SYNCHRONIZED
	| KEYWORD_INLINE;

shakeModifierList: shakeModifier*;

namespace: (identifier = IDENTIFIER)
	| (identifier = IDENTIFIER) (dot = DOT) (child = namespace);

type: (namespace = namespace) (
		(lt = LT) (typeArgs = typeList) (lt = GT)
	)?;

typeList: type (COMMA type)*;

genericDeclaration: (name = IDENTIFIER) (colon = COLON) (
		type = type
	);

genericsDeclaration: (lt = LT) (
		genericDeclaration (COMMA genericDeclaration)*
	) (gt = GT);

funArgs: (lt = LPAREN) ((args = funArg (COMMA funArg)*)) (
		gt = RPAREN
	);

funArg: (name = IDENTIFIER) (colon = COLON) (type = type);

block: (lt = LCURLY) (blockChild)* (gt = RCURLY) | (blockChild);

blockChild: shakeStatement;

// Shake File

shakeFile: shakeFileChild*;

shakeFileChild:
	shakePackageDeclaration
	| shakeImportDeclaration
	| shakeFunDeclaration
	| shakeFieldDeclaration;

shakePackageDeclaration: KEYWORD_PACKAGE namespace;
shakeImportDeclaration: KEYWORD_IMPORT namespace;

shakeFunDeclaration:
	shakeModifierList (fun = KEYWORD_FUN) (
		generics = genericsDeclaration
	)? ((expanding = namespace) DOT)? (name = IDENTIFIER) (
		args = funArgs
	) ((colon = COLON) (returnType = type))? (block = block);

shakeFieldDeclaration:
	shakeModifierList (var = KEYWORD_VAR | var = KEYWORD_VAL) (
		(expanding = namespace) DOT
	)? (name = IDENTIFIER) (
		(colon = COLON) (variableType = type)
	)? ((assign = ASSIGN) (value = shakeValue))?;

// Shake Statement
shakeStatement: shakeVarDeclaration;

shakeVarDeclaration:
	shakeModifierList ((var = KEYWORD_VAR) | (var = KEYWORD_VAL)) (
		(expanding = namespace) DOT
	)? (name = IDENTIFIER) (
		(colon = COLON) (variableType = type)
	)? ((assign = ASSIGN) (value = shakeValue))?;

shakeWhile:
	KEYWORD_WHILE LPAREN (condition = shakeValue) RPAREN (
		body = block
	);

shakeDoWhile:
	KEYWORD_DO (body = block) KEYWORD_WHILE LPAREN (
		condition = shakeValue
	) RPAREN;

shakeFor:
	KEYWORD_FOR LPAREN (init = shakeStatement) SEMICOLON (
		condition = shakeValue
	) SEMICOLON (round = shakeValue) (body = block);

shakeIf:
	KEYWORD_IF LPAREN (condition = shakeValue) RPAREN block
	| KEYWORD_IF LPAREN (condition = shakeValue) RPAREN block KEYWORD_ELSE block;

// Value

shakeValue: KEYWORD_ABSTRACT;

shakeMandory:
	shakeSign
	| INCR shakeMandory
	| shakeMandory INCR
	| DECR shakeMandory
	| shakeMandory DECR;

shakeSign:
	shakePriority
	| (sign = SUB) (content = shakeSign)
	| (sign = ADD) (content = shakeSign)
	| (sign = LOGIC_NOT) (content = shakeSign)
	| (sign = BIT_NOT) (content = shakeSign);

shakePriority: LPAREN shakeValue RPAREN | shakeLiteral;

shakeLiteral: (literal = CHAR)
	| (literal = STRING)
	| (literal = INTEGER)
	| (literal = FLOAT)
	| (literal = BIN_NUMBER)
	| (literal = HEX_NUMBER)
	| (literal = OCT_NUMBER)
	| (literal = IDENTIFIER)
	| (literal = KEYWORD_TRUE)
	| (literal = KEYWORD_FALSE)
	| (literal = KEYWORD_THIS)
	| (literal = KEYWORD_SUPER);