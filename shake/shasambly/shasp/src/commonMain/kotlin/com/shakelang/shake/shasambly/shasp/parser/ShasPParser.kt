package com.shakelang.shake.shasambly.shasp.parser

import com.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenInputStream
import com.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenType
import com.shakelang.shake.shasambly.shasp.parser.nodes.*
import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.Characters
import com.shakelang.util.parseutils.characters.position.Position

class ShasPParser(
    private val input: ShasPTokenInputStream
) {

    fun parse(): ShasPProgram {
        val contents = mutableListOf<ShasPProgChild>()
        while (input.hasNext()) {
            contents.add(parseFileChild())
        }
        return ShasPProgram(contents.toTypedArray())
    }

    private fun parseType(): ShasPType {
        val unsigned = input.actual.type == ShasPTokenType.KEYWORD_UNSIGNED
        if (unsigned) input.skip()

        var type = when (input.actual.type) {
            ShasPTokenType.KEYWORD_BYTE -> if (unsigned) ShasPType.UBYTE else ShasPType.BYTE
            ShasPTokenType.KEYWORD_SHORT -> if (unsigned) ShasPType.USHORT else ShasPType.SHORT
            ShasPTokenType.KEYWORD_INT -> if (unsigned) ShasPType.UINT else ShasPType.INT
            ShasPTokenType.KEYWORD_LONG -> if (unsigned) ShasPType.ULONG else ShasPType.LONG
            ShasPTokenType.KEYWORD_FLOAT -> if (unsigned) throw ParserError("Wrong data type: Unsigned float") else ShasPType.FLOAT
            ShasPTokenType.KEYWORD_DOUBLE -> if (unsigned) throw ParserError("Wrong data type: Unsigned double") else ShasPType.DOUBLE
            ShasPTokenType.KEYWORD_BOOLEAN -> if (unsigned) throw ParserError("Wrong data type: Unsigned boolean") else ShasPType.BOOLEAN
            ShasPTokenType.KEYWORD_CHAR -> if (unsigned) throw ParserError("Wrong data type: Unsigned char") else ShasPType.CHAR
            else -> {
                throw ParserError("Expected type keyword, got ${input.next().type}")
            }
        }

        while (input.hasNext() && (input.peekType() == ShasPTokenType.LBRACKET)) {
            input.skip()
            if (!input.hasNext() || input.peekType() != ShasPTokenType.RBRACKET) {
                val size = logicalOr()
                if (!input.hasNext() || input.nextType() != ShasPTokenType.RBRACKET) {
                    throw ParserError("Expected ']', got ${input.next().type}")
                }
                type = ShasPType.arrayOf(type, size)
            } else {
                input.skip()
                type = ShasPType.arrayOf(type)
            }
        }

        return type
    }

    private fun parseFileChild(): ShasPProgChild {
        input.skip()
        val type = parseType()

        if (input.next().type != ShasPTokenType.IDENTIFIER) {
            throw ParserError(
                "Expected identifier after type",
                input.peekStart(),
                input.peekEnd()
            )
        }
        val name = input.actual.value!!
        if (input.peek().type == ShasPTokenType.LPAREN) {
            input.skip()
            return parseFunctionDeclaration(type, name)
        }
        return parseVariableDeclaration(type, name)
    }

    private fun parseFunctionDeclaration(type: ShasPType, name: String): ShasPFunctionDeclaration {
        // Parse function definition
        val args = mutableListOf<ShasPArgument>()
        while (input.next().type != ShasPTokenType.RPAREN) {
            args.add(parseArgument())
        }

        val body = parseBody()

        return ShasPFunctionDeclaration(type, name, args.toTypedArray(), body)
    }

    private fun parseVariableDeclaration(): ShasPVariableDeclaration {
        val type = parseType()

        if (input.next().type != ShasPTokenType.IDENTIFIER) {
            throw ParserError(
                "Expected identifier after type",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val name = input.actualValue!!

        return parseVariableDeclaration(type, name)
    }

    private fun parseVariableDeclaration(type: ShasPType, name: String): ShasPVariableDeclaration {
        if (input.peek().type == ShasPTokenType.ASSIGN) {
            input.skip()
            val value = expr()
            return ShasPVariableDeclaration(name, type, value)
        }

        return ShasPVariableDeclaration(name, type)
    }

    private fun parseVariableAssignment(name: String): ShasPVariableAssignment {
        if (input.next().type != ShasPTokenType.ASSIGN) {
            throw ParserError(
                "Expected assignment operator",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val value = expr()

        return ShasPVariableAssignment(name, value)
    }

    private fun parseIfStatement(): ShasPIf {
        if (input.actual.type != ShasPTokenType.KEYWORD_IF) {
            throw ParserError(
                "Expected if keyword",
                input.peekStart(),
                input.peekEnd()
            )
        }

        if (input.next().type != ShasPTokenType.LPAREN) {
            throw ParserError(
                "Expected left parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val condition = value()

        if (input.next().type != ShasPTokenType.RPAREN) {
            throw ParserError(
                "Expected right parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val body = parseBody()

        if (input.peek().type == ShasPTokenType.KEYWORD_ELSE) {
            input.skip()
            val elseBody = parseBody()
            return ShasPIf(condition, body, elseBody)
        }

        return ShasPIf(condition, body)
    }

    fun parseWhileStatement(): ShasPWhile {
        if (input.actual.type != ShasPTokenType.KEYWORD_WHILE) {
            throw ParserError(
                "Expected while keyword",
                input.peekStart(),
                input.peekEnd()
            )
        }

        if (input.next().type != ShasPTokenType.LPAREN) {
            throw ParserError(
                "Expected left parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val condition = value()

        if (input.next().type != ShasPTokenType.RPAREN) {
            throw ParserError(
                "Expected right parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val body = parseBody()

        return ShasPWhile(condition, body)
    }

    fun parseReturnStatement(): ShasPReturn {
        if (input.actual.type != ShasPTokenType.KEYWORD_RETURN) {
            throw ParserError(
                "Expected return keyword",
                input.peekStart(),
                input.peekEnd()
            )
        }

        if (input.peek().type == ShasPTokenType.SEMICOLON) {
            return ShasPReturn(null)
        }

        val value = expr()

        if (input.peek().type == ShasPTokenType.SEMICOLON) {
            return ShasPReturn(value)
        }

        throw ParserError(
            "Expected semicolon",
            input.peekStart(),
            input.peekEnd()
        )
    }

    private fun parseForStatement(): ShasPFor {
        if (input.actual.type != ShasPTokenType.KEYWORD_FOR) {
            throw ParserError(
                "Expected for keyword",
                input.peekStart(),
                input.peekEnd()
            )
        }

        if (input.next().type != ShasPTokenType.LPAREN) {
            throw ParserError(
                "Expected left parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val initializer = parseStatement()

        if (input.next().type != ShasPTokenType.SEMICOLON) {
            throw ParserError(
                "Expected semicolon",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val condition = value()

        if (input.next().type != ShasPTokenType.SEMICOLON) {
            throw ParserError(
                "Expected semicolon",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val increment = parseStatement()

        if (input.next().type != ShasPTokenType.RPAREN) {
            throw ParserError(
                "Expected right parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val body = parseBody()

        return ShasPFor(initializer, condition, increment, body)
    }

    private fun parseDoWhileStatement(): ShasPDoWhile {
        if (input.actual.type != ShasPTokenType.KEYWORD_DO) {
            throw ParserError(
                "Expected do keyword",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val body = parseBody()

        if (input.actual.type != ShasPTokenType.KEYWORD_WHILE) {
            throw ParserError(
                "Expected while keyword",
                input.peekStart(),
                input.peekEnd()
            )
        }

        if (input.next().type != ShasPTokenType.LPAREN) {
            throw ParserError(
                "Expected left parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val condition = value()

        if (input.next().type != ShasPTokenType.RPAREN) {
            throw ParserError(
                "Expected right parenthesis",
                input.peekStart(),
                input.peekEnd()
            )
        }

        if (input.next().type != ShasPTokenType.SEMICOLON) {
            throw ParserError(
                "Expected semicolon",
                input.peekStart(),
                input.peekEnd()
            )
        }

        return ShasPDoWhile(condition, body)
    }

    private fun parseStatement(): ShasPStatement {
        val token = input.next()
        if (token.type == ShasPTokenType.IDENTIFIER) {
            val name = token.value!!
            val peek = input.peek()
            if (peek.type == ShasPTokenType.ASSIGN) {
                return parseVariableAssignment(name)
            }
            if (peek.type == ShasPTokenType.LPAREN) {
                return parseFunctionCall(name)
            }
            if (peek.type == ShasPTokenType.ADD_ASSIGN) {
                input.skip()
                return ShasPVariableAddAssignment(name, value())
            }
            if (peek.type == ShasPTokenType.SUB_ASSIGN) {
                input.skip()
                return ShasPVariableSubAssignment(name, value())
            }
            if (peek.type == ShasPTokenType.MUL_ASSIGN) {
                input.skip()
                return ShasPVariableMulAssignment(name, value())
            }
            if (peek.type == ShasPTokenType.DIV_ASSIGN) {
                input.skip()
                return ShasPVariableDivAssignment(name, value())
            }
            if (peek.type == ShasPTokenType.MOD_ASSIGN) {
                input.skip()
                return ShasPVariableModAssignment(name, value())
            }
            if (peek.type == ShasPTokenType.INCR) {
                input.skip()
                return ShasPVariableIncr(name)
            }
            if (peek.type == ShasPTokenType.DECR) {
                input.skip()
                return ShasPVariableDecr(name)
            }
        }
        if (token.type == ShasPTokenType.KEYWORD_IF) {
            return parseIfStatement()
        }
        if (token.type == ShasPTokenType.KEYWORD_WHILE) {
            return parseWhileStatement()
        }
        if (token.type == ShasPTokenType.KEYWORD_RETURN) {
            return parseReturnStatement()
        }
        if (token.type == ShasPTokenType.KEYWORD_FOR) {
            return parseForStatement()
        }
        if (token.type == ShasPTokenType.KEYWORD_DO) {
            return parseDoWhileStatement()
        }
        if (token.type == ShasPTokenType.KEYWORD_BYTE ||
            token.type == ShasPTokenType.KEYWORD_SHORT ||
            token.type == ShasPTokenType.KEYWORD_INT ||
            token.type == ShasPTokenType.KEYWORD_LONG ||
            token.type == ShasPTokenType.KEYWORD_FLOAT ||
            token.type == ShasPTokenType.KEYWORD_DOUBLE ||
            token.type == ShasPTokenType.KEYWORD_CHAR ||
            token.type == ShasPTokenType.KEYWORD_BOOLEAN ||
            token.type == ShasPTokenType.KEYWORD_UNSIGNED
        ) {
            return parseVariableDeclaration()
        }
        throw ParserError("Expected statement, but got ${token.type.name}")
    }

    private fun parseBody(): ShasPCode {
        if (input.next().type != ShasPTokenType.LCURL) {
            throw ParserError(
                "Expected '{' after function name",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val statements = mutableListOf<ShasPStatement>()

        while (input.peek().type != ShasPTokenType.RCURL) {
            if (input.peek().type == ShasPTokenType.SEMICOLON) {
                input.skip()
                continue
            }
            statements.add(parseStatement())
        }

        input.skip()

        return ShasPCode(statements.toTypedArray())
    }

    private fun parseIdentifier(): ShasPValuedNode {
        if (input.actualType != ShasPTokenType.IDENTIFIER) {
            throw ParserError(
                "Expected identifier",
                input.peekStart(),
                input.peekEnd()
            )
        }

        val identifier = input.actual.value!!
        return if (input.peek().type == ShasPTokenType.LPAREN) {
            parseFunctionCall(identifier)
        } else {
            ShasPIdentifier(identifier)
        }
    }

    private fun parseFunctionCall(identifier: String): ShasPFunctionCall {
        input.skip()
        val args = mutableListOf<ShasPValuedNode>()
        while (input.peek().type != ShasPTokenType.RPAREN) {
            args.add(value())
        }
        input.skip()
        return ShasPFunctionCall(identifier, args.toTypedArray())
    }

    // ****************************************************************************
    // Statements
    // (Factor)
    private fun factor(): ShasPValuedNode {
        val token = input.nextType()
        if (token == ShasPTokenType.LPAREN) {
            val result = value()
            if (input.nextType() != ShasPTokenType.RPAREN) throw ParserError("Expecting ')'")
            return result
        }
        if (token == ShasPTokenType.KEYWORD_TRUE) {
            return ShasPLogicalTrueNode()
        }
        if (token == ShasPTokenType.KEYWORD_FALSE) {
            return ShasPLogicalFalseNode()
        }
        if (token == ShasPTokenType.INTEGER) {
            return ShasPIntegerLiteral(input.actualValue!!)
        }
        if (token == ShasPTokenType.DOUBLE) {
            return ShasPDoubleLiteral(input.actualValue!!)
        }
        if (token == ShasPTokenType.IDENTIFIER) {
            return parseIdentifier()
        }
        if (token == ShasPTokenType.ADD) {
            input.skip()
            return ShasPPosNode(factor())
        }
        if (token == ShasPTokenType.SUB) {
            input.skip()
            return ShasPNegNode(factor())
        }
        if (token == ShasPTokenType.CHARACTER) {
            input.skip()
            return ShasPCharLiteral(Characters.parseString(input.actualValue!!)[0])
        }
        if (token == ShasPTokenType.KEYWORD_NEW) {
            return parseNew()
        }
        throw ParserError("Unexpected Token $token")
    }

    private fun parseNew(): ShasPValuedNode {
        if (input.actualType != ShasPTokenType.KEYWORD_NEW) {
            throw ParserError("Expected 'new'")
        }

        input.skip()

        val type = parseType()
        if (type !is ShasPType.ShasPArrayType) throw ParserError("Expected array type")
        return ShasPArrayInitializer(type)
    }

    // Casting
    private fun cast(): ShasPValuedNode {
        var result = factor()
        while (input.hasNext() && input.peekType() == ShasPTokenType.KEYWORD_AS) {
            input.skip()

            val target = when (input.nextType()) {
                ShasPTokenType.KEYWORD_BYTE -> ShasPType.BYTE
                ShasPTokenType.KEYWORD_SHORT -> ShasPType.SHORT
                ShasPTokenType.KEYWORD_INT -> ShasPType.INT
                ShasPTokenType.KEYWORD_LONG -> ShasPType.LONG
                ShasPTokenType.KEYWORD_FLOAT -> ShasPType.FLOAT
                ShasPTokenType.KEYWORD_DOUBLE -> ShasPType.DOUBLE
                ShasPTokenType.KEYWORD_BOOLEAN -> ShasPType.BOOLEAN
                ShasPTokenType.KEYWORD_CHAR -> ShasPType.CHAR
                else -> throw ParserError("Expecting cast-target")
            }
            result = ShasPCast(target, result)
        }
        return result
    }

    // (Calculations)
    private fun expr(): ShasPValuedNode {
        var result = term()
        while (input.hasNext() && input.peekType().let { it == ShasPTokenType.ADD || it == ShasPTokenType.SUB }) {
            input.skip()
            val type = input.actualType
            result = if (type == ShasPTokenType.ADD) {
                ShasPAdd(result, term())
            } else {
                ShasPSub(result, term())
            }
        }
        return result
    }

    private fun term(): ShasPValuedNode {
        var result = cast()
        while (input.hasNext() && input.peekType().let {
            it == ShasPTokenType.MUL ||
                it == ShasPTokenType.DIV ||
                it == ShasPTokenType.MOD
        }
        ) {
            input.skip()
            val type = input.actualType
            result = when (type) {
                ShasPTokenType.MUL -> ShasPMul(result, cast())
                ShasPTokenType.DIV -> ShasPDiv(result, cast())
                else -> ShasPMod(result, cast())
            }
        }
        return result
    }

    // (Logical)
    private fun value(): ShasPValuedNode = logicalOr()

    private fun logicalOr(): ShasPValuedNode {
        var result = logicalXOr()
        while (input.hasNext() && input.peekType() == ShasPTokenType.LOGICAL_OR) {
            input.skip()
            result = ShasPOr(result, logicalXOr())
        }
        return result
    }

    private fun logicalXOr(): ShasPValuedNode {
        var result = logicalAnd()
        while (input.hasNext() && input.peekType() == ShasPTokenType.LOGICAL_XOR) {
            input.skip()
            result = ShasPXor(result, logicalAnd())
        }
        return result
    }

    private fun logicalAnd(): ShasPValuedNode {
        var result = compare()
        while (input.hasNext() && input.peekType() == ShasPTokenType.LOGICAL_AND) {
            input.skip()
            result = ShasPAnd(result, compare())
        }
        return result
    }

    private fun compare(): ShasPValuedNode {
        var result = expr()
        while (input.hasNext() && input.peekType().let {
            it == ShasPTokenType.EQ_EQUALS ||
                it == ShasPTokenType.NOT_EQUALS ||
                it == ShasPTokenType.BIGGER ||
                it == ShasPTokenType.BIGGER_EQUALS ||
                it == ShasPTokenType.SMALLER ||
                it == ShasPTokenType.SMALLER_EQUALS
        }
        ) {
            input.skip()
            val type = input.actualType
            result = when (type) {
                ShasPTokenType.EQ_EQUALS -> ShasPEqual(result, expr())
                ShasPTokenType.NOT_EQUALS -> ShasPNotEqual(result, expr())
                ShasPTokenType.SMALLER -> ShasPLess(result, expr())
                ShasPTokenType.SMALLER_EQUALS -> ShasPLessEqual(result, expr())
                ShasPTokenType.BIGGER -> ShasPGreater(result, expr())
                ShasPTokenType.BIGGER_EQUALS -> ShasPGreaterEqual(result, expr())
                else -> throw ParserError("Expecting comparison operator")
            }
        }
        return result
    }

    private fun parseArgument(): ShasPArgument {
        input.skip()
        val type = parseType()
        if (input.next().type != ShasPTokenType.IDENTIFIER) {
            throw ParserError(
                "Expected identifier after type",
                input.peekStart(),
                input.peekEnd()
            )
        }
        val name = input.actual.value!!
        return ShasPArgument(name, type)
    }

    // ****************************************************************************
    // Errors

    inner class ParserError(message: String?, name: String?, details: String?, start: Position?, end: Position?) :
        CompilerError(
            message!!,
            name!!,
            details!!,
            start!!,
            end!!
        ) {
        constructor(
            name: String,
            details: String,
            start: Position,
            end: Position?
        ) : this(
            "Error occurred in parser: " + name + ", " + details + " in " + start.source + ":" + start.line + ":" + start.column,
            name,
            details,
            start,
            end
        )

        constructor(details: String, start: Position, end: Position?) : this("ParserError", details, start, end)
        constructor(details: String, start: Int, end: Int) : this(
            "ParserError",
            details,
            input.map.resolve(start),
            input.map.resolve(end)
        )

        constructor(error: String) : this(
            error,
            input.map.resolve(input.actualStart),
            input.map.resolve(input.actualEnd)
        )
    }
}
