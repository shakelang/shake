package io.github.shakelang.shake.shasambly.shasp.parser

import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.position.Position
import io.github.shakelang.shake.lexer.token.stream.ShasPTokenInputStream
import io.github.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenType
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.*

class ShasPParser (
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

        val unsigned = input.peek().type == ShasPTokenType.KEYWORD_UNSIGNED
        if (unsigned) input.skip()

        val type = when(input.next().type) {
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

        if (input.peek().type != ShasPTokenType.IDENTIFIER) {
            throw ParserError(
                "Expected identifier after type name",
                input.peekStart(),
                input.peekEnd()
            )
        }

        return type
    }

    private fun parseFileChild(): ShasPProgChild {

        val type = parseType()

        if(input.next().type != ShasPTokenType.IDENTIFIER) {
            throw ParserError(
                "Expected identifier after type",
                input.peekStart(),
                input.peekEnd()
            )
        }
        val name = input.actual.value!!
        if(input.peek().type == ShasPTokenType.LPAREN) {
            input.skip()
            return parseFunctionDeclaration(type, name)
        }
        return parseVariableDeclaration(type, name)

    }

    private fun parseFunctionDeclaration(type: ShasPType, name: String): ShasPFunctionDeclaration {

        // Parse function definition
        val args = mutableListOf<ShasPArgument>()
        while(input.next().type != ShasPTokenType.RPAREN) {
            args.add(parseArgument())
        }

        val body = parseBody()

        return ShasPFunctionDeclaration(type, name, args.toTypedArray(), body)

    }

    private fun parseVariableDeclaration(type: ShasPType, name: String): ShasPVariableDeclaration {

        if(input.peek().type == ShasPTokenType.EQUALS) {
            input.skip()
            val value = parseExpression()
            return ShasPVariableDeclaration(type, name, value)
        }

        return ShasPVariableDeclaration(type, name)

    }

    private fun parseBody(): ShasPCode {

        if(input.next().type != ShasPTokenType.LCURL) {
            throw ParserError(
                "Expected '{' after function name",
                input.peekStart(),
                input.peekEnd()
            )
        }

        while(input.next().type != ShasPTokenType.RCURL) {
            parseStatement()
        }

    }

    private fun parseStatement(): ShasPStatement {



    }

    private fun parseArgument(): ShasPArgument {

        val type = parseType()
        if(input.next().type != ShasPTokenType.IDENTIFIER) {
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
            message!!, name!!, details!!, start!!, end!!
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