parser grammar ShakeParser;

options {
	tokenVocab = ShakeLexer;
}

// MISC

shakeIdentifier:
	(identifier = IDENTIFIER)		# Identifier
	| (identifier = IDENTIFIER2)	# Identifier2;

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

shakeNamespace: (identifier = shakeIdentifier)
	| (identifier = shakeIdentifier) (dot = DOT) (
		child = shakeNamespace
	);

shakeType: (namespace = shakeNamespace) (shakeTypeArguments)?;

genericDeclaration: (name = shakeIdentifier) (colon = COLON) (
		type = shakeType
	);

genericsDeclaration: (lt = LT) (
		genericDeclaration (COMMA genericDeclaration)*
	) (gt = GT);

funArgs: (lt = LPAREN) (
		(args = funArgDeclaration (COMMA funArgDeclaration)*)
	) (gt = RPAREN);

funArgDeclaration: (name = shakeIdentifier) (colon = COLON) (
		type = shakeType
	);

shakeBlock: (lt = LCURLY) (shakeBlockChild)* (gt = RCURLY)
	| (shakeBlockChild);

shakeBlockChild: shakeStatement;

shakeArgument:
	shakeValue							# Argument
	| shakeIdentifier ASSIGN shakeValue	# NamedArgument;

shakeArguments:
	LPAREN ((shakeArgument) (COMMA shakeArgument) COMMA?)* RPAREN;

shakeTypeArgument: shakeType;
shakeTypeArguments:
	LT ((shakeTypeArgument) (COMMA shakeTypeArgument) COMMA?)*;

// Shake File
shakeFile: shakeFileChild*;

shakeFileChild:
	shakePackageDeclaration
	| shakeImportDeclaration
	| shakeFunDeclaration
	| shakeFieldDeclaration;

shakePackageDeclaration: KEYWORD_PACKAGE shakeNamespace;
shakeImportDeclaration: KEYWORD_IMPORT shakeNamespace;

shakeFunDeclaration:
	shakeModifierList (fun = KEYWORD_FUN) (
		generics = genericsDeclaration
	)? ((expanding = shakeNamespace) DOT)? (
		name = shakeIdentifier
	) (args = funArgs) ((colon = COLON) (returnType = shakeType))? (
		block = shakeBlock
	);

shakeFieldDeclaration:
	shakeModifierList (var = KEYWORD_VAR | var = KEYWORD_VAL) (
		(expanding = shakeNamespace) DOT
	)? (name = shakeIdentifier) (
		(colon = COLON) (variableType = shakeType)
	)? ((assign = ASSIGN) (value = shakeValue))?;

// Shake Statement
shakeStatement: shakeVarDeclaration;

shakeVarDeclaration:
	shakeModifierList ((var = KEYWORD_VAR) | (var = KEYWORD_VAL)) (
		(expanding = shakeNamespace) DOT
	)? (name = shakeIdentifier) (
		(colon = COLON) (variableType = shakeType)
	)? ((assign = ASSIGN) (value = shakeValue))?;

shakeWhile:
	KEYWORD_WHILE LPAREN (condition = shakeValue) RPAREN (
		body = shakeBlock
	);

shakeDoWhile:
	KEYWORD_DO (body = shakeBlock) KEYWORD_WHILE LPAREN (
		condition = shakeValue
	) RPAREN;

shakeFor:
	KEYWORD_FOR LPAREN (init = shakeStatement) SEMICOLON (
		condition = shakeValue
	) SEMICOLON (round = shakeValue) (body = shakeBlock);

shakeIf:
	KEYWORD_IF LPAREN (condition = shakeValue) RPAREN (
		thenBlock = shakeBlock
	)
	| KEYWORD_IF LPAREN (condition = shakeValue) RPAREN (
		thenBlock = shakeBlock
	) KEYWORD_ELSE (elseBlock = shakeBlock);

// Value

shakeValue: shakeAssignment;

shakeAssignment:
	shakeLogicalOr												# LogicalOr
	| (left = shakeAssignment) ASSIGN (right = shakeLogicalOr)	# Assign
	| (left = shakeAssignment) ADD_ASSIGN (
		right = shakeLogicalOr
	) # AddAssign
	| (left = shakeAssignment) SUB_ASSIGN (
		right = shakeLogicalOr
	) # SubAssign
	| (left = shakeAssignment) MUL_ASSIGN (
		right = shakeLogicalOr
	) # MulAssign
	| (left = shakeAssignment) DIV_ASSIGN (
		right = shakeLogicalOr
	) # DivAssign
	| (left = shakeAssignment) MOD_ASSIGN (
		right = shakeLogicalOr
	) # ModAssign
	| (left = shakeAssignment) POW_ASSIGN (
		right = shakeLogicalOr
	) # PowAssign;

// TODO Ternary

shakeLogicalOr:
	shakeLogicalXor													# LogicalXor
	| (left = shakeLogicalOr) LOGIC_OR (right = shakeLogicalXor)	# LogicalOr;

shakeLogicalXor:
	shakeLogicalAnd # LogicalAnd
	| (left = shakeLogicalXor) LOGIC_XOR (
		right = shakeLogicalAnd
	) # LogicalXor;

shakeLogicalAnd:
	shakeBitwiseOr													# BitwiseOr
	| (left = shakeLogicalAnd) LOGIC_AND (right = shakeBitwiseOr)	# LogicalAnd;

shakeBitwiseOr:
	shakeBitwiseXor												# BitwiseXor
	| (left = shakeBitwiseOr) BIT_OR (right = shakeBitwiseXor)	# BitOr
	| (left = shakeBitwiseOr) BIT_NOR (right = shakeBitwiseXor)	# BitNor;

shakeBitwiseXor:
	shakeBitwiseAnd													# BitwiseAnd
	| (left = shakeBitwiseXor) BIT_XOR (right = shakeBitwiseAnd)	# BitXor
	| (left = shakeBitwiseXor) BIT_XNOR (right = shakeBitwiseAnd)	# BitXnor;

shakeBitwiseAnd:
	shakeEquality												# Equality
	| (left = shakeBitwiseAnd) BIT_AND (right = shakeEquality)	# BitAnd
	| (left = shakeBitwiseAnd) BIT_NAND (right = shakeEquality)	# BitNand;

shakeEquality:
	shakeRelation											# Relation
	| (left = shakeEquality) EQUALS (right = shakeRelation)	# Equal
	| (left = shakeEquality) NEQ (right = shakeRelation)	# NotEqual;

shakeRelation:
	shakeShift											# Shift
	| (left = shakeRelation) LT (right = shakeShift)	# LessThan
	| (left = shakeRelation) GT (right = shakeShift)	# GreaterThan
	| (left = shakeRelation) LTE (right = shakeShift)	# LessEqual
	| (left = shakeRelation) GTE (right = shakeShift)	# GreaterEqual;

shakeShift:
	shakeAddition											# Addition
	| (left = shakeShift) LT LT (right = shakeAddition)		# LeftShift
	| (left = shakeShift) GT GT GT (right = shakeAddition)	# UnsignedRightShift
	| (left = shakeShift) GT GT (right = shakeAddition)		# RightShift;

shakeAddition:
	shakeMutiplication											# Mutiplication
	| (left = shakeAddition) ADD (right = shakeMutiplication)	# Addition
	| (left = shakeAddition) SUB (right = shakeMutiplication)	# Subtraction;

shakeMutiplication:
	shakePower												# Power
	| (left = shakeMutiplication) MOD (right = shakePower)	# Modulo
	| (left = shakeMutiplication) DIV (right = shakePower)	# Division
	| (left = shakeMutiplication) MUL (right = shakePower)	# Multiplication;

shakePower:
	shakeCast											# Cast
	| (left = shakePower) POW (right = shakeMandory)	# Power;

shakeCast:
	shakeAccess												# Access
	| (target = shakeCast) KEYWORD_AS (type = shakeType)	# Cast;

shakeAccess:
	shakeMandory													# Mandory
	| (left = shakeMandory) DOT (right = shakeAccess)				# PropertyAccess
	| (element = shakeAccess) LBRACK (element = shakeValue) RBRACK	# Get
	| (element = shakeAccess) (typeargs = shakeTypeArguments)? (
		args = shakeArguments
	) # Call;

shakeMandory:
	shakeSign						# Sign
	| INCR (value = shakeMandory)	# IncrBefore
	| (value = shakeMandory) INCR	# IncrAfter
	| DECR (value = shakeMandory)	# DecrBefore
	| (value = shakeMandory) DECR	# DecrAfter;

shakeSign:
	shakePriority
	| (sign = SUB) (content = shakeSign)
	| (sign = ADD) (content = shakeSign)
	| (sign = LOGIC_NOT) (content = shakeSign)
	| (sign = BIT_NOT) (content = shakeSign);

shakePriority: shakeLiteral | LPAREN shakeValue RPAREN;

shakeLiteral: (literal = CHAR)		# CharLiteral
	| (literal = STRING)			# StringLiteral
	| (literal = INTEGER)			# IntegerLiteral
	| (literal = FLOAT)				# FloatLiteral
	| (literal = BIN_NUMBER)		# BinNumberLiteral
	| (literal = HEX_NUMBER)		# HexNumberLiteral
	| (literal = OCT_NUMBER)		# OctNumberLiteral
	| (literal = shakeIdentifier)	# IdentifierLiteral
	| (literal = KEYWORD_TRUE)		# TrueLiteral
	| (literal = KEYWORD_FALSE)		# FalseLiteral
	| (literal = KEYWORD_THIS)		# ThisLiteral
	| (literal = KEYWORD_SUPER)		# SuperLiteral;