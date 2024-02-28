package com.shakelang.shake.parser

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.parser.node.*
import com.shakelang.shake.parser.node.expression.*
import com.shakelang.shake.parser.node.factor.*
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionParameterNode
import com.shakelang.shake.parser.node.functions.ShakeInvocationNode
import com.shakelang.shake.parser.node.functions.ShakeReturnNode
import com.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import com.shakelang.shake.parser.node.loops.ShakeForNode
import com.shakelang.shake.parser.node.loops.ShakeWhileNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import com.shakelang.shake.parser.node.variables.*
import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.shason.json

/**
 * An abstract Parser for the Shake Programming language. An instance is created for each file in the compilation process.
 * The Default implementation of this class is [ShakeParserImpl]. Create a ShakeParser using [ShakeParser.from]
 *
 * @see ShakeParserImpl
 * @see ShakeParser.from
 */
abstract class ShakeParser {

    /**
     * The [ShakeTokenInputStream] to be parsed.
     */
    abstract val input: ShakeTokenInputStream

    /**
     * The [PositionMap] of the [input].
     * A position map is a mapping of the index of characters in the input and its corresponding [Position] in the file
     * (line, column, etc.).
     */
    abstract val map: PositionMap

    /**
     * Parses the [input] and returns the root [ShakeNode] of the parsed tree (A [ShakeFileNode])
     */
    abstract fun parse(): ShakeFileNode

    /**
     * Starts the parsing process, but directly jumps into the statement parsing phase (statements can normally be found
     * in methods, constructors, etc.).
     */
    abstract fun parseAsStatements(): ShakeBlockNode

    /**
     * Parses a single value.
     */
    abstract fun expectValue(): ShakeValuedNode

    companion object {

        /**
         * Creates a new [ShakeParser] from the given [ShakeTokenInputStream].
         *
         * @param input The [ShakeTokenInputStream] to be parsed.
         * @return A new [ShakeParser] instance.
         */
        fun from(input: ShakeTokenInputStream) = ShakeParserImpl(input)
    }
}

/**
 * A helper class for the [ShakeParser] class.
 * This class provides some useful functions for parsing.
 * The default implementation of this class is [ShakeParserImpl].
 * Create a ShakeParser using [ShakeParser.from]
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class ShakeParserHelper(

    /**
     * The [PositionMap] of the [input]. It is directly taken from the [input], because [ShakeTokenInputStream]
     * already provides a [PositionMap] implementation.
     */
    override val input: ShakeTokenInputStream,
) : ShakeParser() {

    /**
     * Assert a value to not be null, if it is null a [ParserError] is thrown
     * @param value The value to be checked
     * @return The value if it is not null
     * @throws ParserError if the value is null
     */
    protected fun <T> expectNotNull(value: T?): T = value ?: throw ParserError("Value is null")

    // ****************************************************************************

    /**
     * Consumes the next token and returns it if it is of the given type, throws a [ParserError] otherwise
     * @param type The type of the token to be consumed
     * @param message The message of the error if the token is not of the given type
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(type: ShakeTokenType, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext() || input.peekType() != type) throw ParserError(message)
        return input.next()
    }

    /**
     * Consumes the next token and returns it if it is of the given type, throws a [ParserError] otherwise
     * @param type The type of the token to be consumed
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(type: ShakeTokenType, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(type, "Expecting ${type.simpleValue}", skipIgnorable)
    }

    /**
     * Consumes the next token and returns it if it is of one of the given types, throws a [ParserError] otherwise
     * @param types The types of the token to be consumed
     * @param message The message of the error if the token is not of the given type
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(types: List<ShakeTokenType>, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext() || input.peekType() !in types) throw ParserError(message)
        return input.next()
    }

    /**
     * Consumes the next token and returns it if it is of one of the given types, throws a [ParserError] otherwise
     * @param types The types of the token to be consumed
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return The consumed token
     * @throws ParserError If the next token is not of the given type
     */
    protected fun expectToken(types: List<ShakeTokenType>, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(types, "Expecting one of [${types.joinToString(", "){ json.stringify(it) }}]", skipIgnorable)
    }

    /**
     * Skips ignorable tokens and checks whether the next token is of the given type
     * @param type The type of the token to be checked
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return Whether the next token is of the given type
     */
    protected fun nextToken(type: ShakeTokenType, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peekType() == type
    }

    /**
     * Skips ignorable tokens and checks whether the next token is of one of the given types
     * @param types The types of the token to be checked
     * @param skipIgnorable If ignorable tokens should be skipped
     * @return Whether the next token is of one of the given types
     */
    protected fun nextToken(types: List<ShakeTokenType>, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peekType() in types
    }

    // ****************************************************************************
    // Errors

    /**
     * A class representing a parser error
     * @param message The message of the error
     * @param name The name of the error
     * @param details The details of the error
     * @param start The start position of the error
     * @param end The end position of the error
     */
    inner class ParserError(message: String?, name: String?, details: String?, start: Position?, end: Position?) :
        CompilerError(
            message!!,
            name!!,
            details!!,
            start!!,
            end!!,
        ) {

        /**
         * A class representing a parser error
         * @param name The name of the error
         * @param details The details of the error
         * @param start The start position of the error
         * @param end The end position of the error
         */
        constructor(
            name: String,
            details: String,
            start: Position,
            end: Position?,
        ) : this(
            "Error occurred in parser: $name, $details in <${start.source.location}>:${start.line}:${start.column}",
            name,
            details,
            start,
            end,
        )

        /**
         * A class representing a parser error
         * @param details The details of the error
         * @param start The start position of the error
         * @param end The end position of the error
         */
        constructor(details: String, start: Position, end: Position?) : this("ParserError", details, start, end)

        /**
         * A class representing a parser error
         * @param details The details of the error
         */
        constructor(details: String, start: Int, end: Int) : this(
            "ParserError",
            details,
            input.map.resolve(start),
            input.map.resolve(end),
        )

        constructor(error: String) : this(
            error,
            input.map.resolve(input.actualStart),
            input.map.resolve(input.actualEnd),
        )
    }
}

/**
 * The default implementation of the abstract [ShakeParser] class.
 */
class ShakeParserImpl(input: ShakeTokenInputStream) : ShakeParserHelper(input) {

    /**
     * The [PositionMap] of the [input]. It is directly taken from the [input], because [ShakeTokenInputStream]
     * already provides a [PositionMap] implementation.
     */
    override val map: PositionMap get() = input.map

    /**
     * Parses the [input] and returns the root [ShakeNode] of the parsed tree (A [ShakeFileNode])
     * Entry point of the parsing process
     * @return The parsed program
     */
    override fun parse(): ShakeFileNode {
        //
        // Executes doParseProgram().
        // If this does not finish the input file, a ParserError is thrown
        //

        if (!input.hasNext()) return ShakeFileNode(map, arrayOf())
        val result = doParseProgram()
        if (input.hasNext()) throw ParserError("Input did not end")
        return result
    }

    /**
     * Starts the parsing process, but directly jumps into the statement parsing phase
     * (statements can normally be found in methods, constructors, etc.).
     * @return The parsed statements
     */
    override fun parseAsStatements(): ShakeBlockNode {
        //
        // Expects [statement] <SEPARATOR> [statement] <SEPARATOR> ... [EOF]
        //

        val nodes = mutableListOf<ShakeStatementNode>()
        var position = -2
        skipSeparators()
        while (input.hasNext()) {
            if (position >= input.position) break
            position = input.position
            if (input.hasNext()) {
                val result = expectStatement()
                nodes.add(result)
            }
            expectSeparator()
        }
        return ShakeBlockNode(map, nodes.toTypedArray(), null, null)
    }

    // ****************************************************************************
    // Basic Program

    /**
     * Parses a program.
     * A program is a list of imports, packages, declarations and statements.
     * @return The parsed program
     */
    private fun doParseProgram(): ShakeFileNode {
        //
        // Expects
        // [import | package | declaration | statement]
        // <SEPARATOR>
        // [import | package | declaration | statement]
        // <SEPARATOR> ...
        // [EOF]
        //

        val nodes = mutableListOf<ShakeFileChildNode>()
        var position = -2

        skipSeparators()
        while (input.hasNext()) {
            if (position >= input.position) break
            position = input.position
            if (input.hasNext()) {
                val result = expectShakeFileChild()
                nodes.add(result)
            }
            expectSeparator()
        }
        return ShakeFileNode(map, nodes)
    }

    /**
     * Parses a single file child. (package declaration, import declaration,
     * class declaration, method declaration, etc.)
     * @return The parsed file child
     */
    private fun expectShakeFileChild(): ShakeFileChildNode {
        //
        // Expects [import | package | declaration | statement]
        //

        val token = input.peekType()
        if (token == ShakeTokenType.KEYWORD_IMPORT) return expectImport()
        if (token == ShakeTokenType.KEYWORD_PACKAGE) return expectPackage()
        return expectDeclaration(DeclarationScope.FILE) as ShakeFileChildNode
    }

    /**
     * Parses a statement.
     * @return The parsed statement
     */
    private fun expectStatement(): ShakeStatementNode {
        //
        // Declaration of [statement]
        //
        // A statement hereby is:
        // [while | do-while | for | if | return | local-declaration | identifier-statement]
        //

        return when (val token = input.skipIgnorable().peekType()) {
            ShakeTokenType.KEYWORD_WHILE -> expectWhileLoop()
            ShakeTokenType.KEYWORD_DO -> expectDoWhileLoop()
            ShakeTokenType.KEYWORD_FOR -> expectForLoop()
            ShakeTokenType.KEYWORD_IF -> expectIfStatement()
            ShakeTokenType.KEYWORD_RETURN -> expectReturnStatement()
            ShakeTokenType.KEYWORD_CONST,
            ShakeTokenType.KEYWORD_VAR,
            ShakeTokenType.KEYWORD_VAL,
            ShakeTokenType.KEYWORD_FINAL,
            -> expectLocalDeclaration()

            ShakeTokenType.IDENTIFIER -> expectIdentifierStatement()
            else -> {
                input.skip()
                throw ParserError("Unexpected token ($token)")
            }
        }
    }

    /**
     * Parses a value (a literal, a variable usage, a method call, calculation, etc.).
     * @return The parsed value
     */
    override fun expectValue() = expectValuedAssignment()

    // ****************************************************************************
    // Utils

    /**
     * Expects a separator (semicolon or line separator)
     * If there are multiple separators, they are skipped
     * If there are no separators, a ParserError is thrown
     * @return The consumed separator
     * @throws ShakeParserHelper.ParserError If there are no separators
     */
    private fun expectSeparator() {
        if (!input.hasNext()) return
        expectToken(listOf(ShakeTokenType.SEMICOLON, ShakeTokenType.LINE_SEPARATOR), skipIgnorable = false)
        skipSeparators()
    }

    /**
     * Skips all separators (semicolons, line separators, etc.).
     */
    private fun skipSeparators() {
        while (nextToken(ShakeTokenType.SEMICOLON)) {
            // We only need to skip multiple semicolons, as nextToken() automatically
            // ignores LineSeparators and other ignorable tokens
            input.skip()
        }
    }

    /**
     * Expects a declaration with a given declaration scope
     * @param declarationScope The scope of the declaration
     * @return The parsed declaration
     */
    private fun expectDeclaration(declarationScope: DeclarationScope): ShakeNode {
        return expectDeclaration(DeclarationContextInformation(declarationScope))
    }

    /**
     * Expect a declaration (function declaration, class declaration, field declaration, etc.).
     * @return The parsed declaration
     */
    private fun expectDeclaration(
        info: DeclarationContextInformation,
    ): ShakeNode {
        return when (input.peekType()) {
            //
            // Access modifiers
            // Just adds the modifier to the info (recursively calls expectDeclaration)
            // Modifiers: public, protected, private, static, final, abstract, synchronized, const, native, override, operator, inline
            //

            ShakeTokenType.KEYWORD_PUBLIC -> {
                if (info.access != null) throw ParserError("Access modifier is only allowed once")
                info.access = ShakeAccessDescriber.of(input.next())
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_PROTECTED -> {
                if (info.access != null) throw ParserError("Access modifier is only allowed once")
                info.access = ShakeAccessDescriber.of(input.next())
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_PRIVATE -> {
                if (info.access != null) throw ParserError("Access modifier is only allowed once")
                info.access = ShakeAccessDescriber.of(input.next())
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_STATIC -> {
                if (info.isStatic) throw ParserError("Static keyword is only allowed once")
                info.staticToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_FINAL -> {
                if (info.isFinal) throw ParserError("Final keyword is only allowed once")
                info.finalToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_ABSTRACT -> {
                if (info.isAbstract) throw ParserError("Abstract keyword is only allowed once")
                info.abstractToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_SYNCHRONIZED -> {
                if (info.isSynchronized) throw ParserError("Synchronized keyword is only allowed once")
                info.synchronizedToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_CONST -> {
                if (info.isConst) throw ParserError("Const keyword is only allowed once")
                info.constToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_NATIVE -> {
                if (info.isNative) throw ParserError("Native keyword is only allowed once")
                info.nativeToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_OVERRIDE -> {
                if (info.isOverride) throw ParserError("Override keyword is only allowed once")
                info.overrideToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_OPERATOR -> {
                if (info.isOperator) throw ParserError("Operator keyword is only allowed once")
                info.operatorToken = input.next()
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_INLINE -> {
                if (info.isInline) throw ParserError("Inline keyword is only allowed once")
                info.inlineToken = input.next()
                expectDeclaration(info)
            }

            //
            // class, interface, object, enum
            //

            ShakeTokenType.KEYWORD_CLASS -> expectClassDeclaration(info)
            ShakeTokenType.KEYWORD_INTERFACE -> expectInterfaceDeclaration(info)
            ShakeTokenType.KEYWORD_OBJECT -> expectObjectDeclaration(info)
            ShakeTokenType.KEYWORD_ENUM -> expectEnumDeclaration(info)

            //
            // constructor
            //
            ShakeTokenType.KEYWORD_CONSTRUCTOR -> {
                if (info.scope != DeclarationScope.CLASS && info.scope != DeclarationScope.ENUM) {
                    throw ParserError("Constructor is only allowed in classes")
                }
                expectConstructorDeclaration(info)
            }

            //
            // function
            //
            ShakeTokenType.KEYWORD_FUN -> expectFunctionDeclaration(info)

            //
            // field
            //
            ShakeTokenType.KEYWORD_VAL, ShakeTokenType.KEYWORD_VAR -> expectFieldDeclaration(info)

            else -> {
                input.skip()
                throw ParserError("Unexpected token (${input.actualType})")
            }
        }
    }

    /**
     * Expect a field declaration.
     * @param info The context information of the declaration
     * @return The parsed field declaration
     */
    private fun expectFieldDeclaration(info: DeclarationContextInformation): ShakeFieldDeclarationNode {
        //
        // <[attributes]> [val | var] <[namespace]> ([colon] <[type]>)? ([assign] [value])?
        //

        val varToken = expectToken(listOf(ShakeTokenType.KEYWORD_VAR, ShakeTokenType.KEYWORD_VAL))
        val namespace = expectNamespace()
        val expanding = namespace.parent?.toType()
        val expandingDot = namespace.dotToken
        val name = namespace.nameToken
        val type = if (nextToken(ShakeTokenType.COLON)) {
            input.skip()
            expectType()
        } else {
            null
        }

        if (nextToken(ShakeTokenType.ASSIGN)) {
            input.skip()
            val value = expectValue()
            return ShakeFieldDeclarationNode(
                map,
                expanding,
                expandingDot,
                name,
                type,
                value,
                info.access ?: ShakeAccessDescriber.PACKAGE,
                varToken,
                info.staticToken,
                info.finalToken,
                info.nativeToken,
                info.constToken,
                info.overrideToken,
                info.inlineToken,
            )
        } else {
            return ShakeFieldDeclarationNode(
                map,
                expanding,
                expandingDot,
                name,
                type,
                null,
                info.access ?: ShakeAccessDescriber.PACKAGE,
                varToken,
                info.staticToken,
                info.finalToken,
                info.nativeToken,
                info.constToken,
                info.overrideToken,
                info.inlineToken,
            )
        }
    }

    /**
     * Expect a local declaration.
     * @return The parsed local declaration
     */
    private fun expectLocalDeclaration(): ShakeLocalDeclarationNode {
        //
        // [val | var] <[namespace]> ([colon] <[type]>)? ([assign] [value])?
        //

        val varToken = expectToken(listOf(ShakeTokenType.KEYWORD_VAR, ShakeTokenType.KEYWORD_VAL))
        val name = expectToken(ShakeTokenType.IDENTIFIER)

        var type: ShakeVariableType? = null
        var colon: ShakeToken? = null

        if (nextToken(ShakeTokenType.COLON)) {
            colon = input.next()
            type = expectType()
        }

        var value: ShakeValuedNode? = null
        var assign: ShakeToken? = null

        if (nextToken(ShakeTokenType.ASSIGN)) {
            assign = input.next()
            value = expectValue()
        }

        return ShakeLocalDeclarationNode(
            map,
            name,
            colon,
            type,
            assign,
            value,
            varToken,
        )
    }

    /**
     * Expect a function declaration.
     * @param info The context information of the declaration
     * @return The parsed function declaration
     */
    private fun expectFunctionDeclaration(info: DeclarationContextInformation): ShakeFunctionDeclarationNode {
        //
        // fun <[namespace]> ([args])? ([colon] <[type]>)? ([colon] <[type]>)? (<[block]>)?
        //

        val funToken = input.next()

        val namespace = expectNamespace()
        val expanding = namespace.parent?.toType()
        val expandingDot = namespace.dotToken
        val name = namespace.nameToken

        if (expanding != null) throw ParserError("Function declaration cannot have a namespace")

        val (argList, lparen, rparen, commas) = expectFunctionArguments()

        var returnType = ShakeVariableType.IMPLICIT_VOID
        var colon: ShakeToken? = null
        if (nextToken(ShakeTokenType.COLON)) {
            colon = input.next()
            returnType = expectType()
        }

        val body = if (nextToken(ShakeTokenType.LCURL)) {
            expectBlock()
        } else {
            null
        }

        return ShakeFunctionDeclarationNode(
            map,
            expanding,
            name,
            body,
            argList,
            returnType,
            info.access ?: ShakeAccessDescriber.PACKAGE,
            info.staticToken,
            info.finalToken,
            info.abstractToken,
            info.overrideToken,
            info.synchronizedToken,
            info.nativeToken,
            info.operatorToken,
            info.inlineToken,
            funToken,
            lparen,
            rparen,
            colon,
            commas,
            expandingDot,
        )
    }

    /**
     * Expect a namespace.
     * @return The parsed namespace
     */
    private fun expectNamespace(): ShakeNamespaceNode {
        //
        // Parses aaa.bbb.ccc... (namespace)
        //
        var namespaceNode = ShakeNamespaceNode(map, expectToken(ShakeTokenType.IDENTIFIER), null, null)
        while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT) {
            val dot = input.next()
            namespaceNode = ShakeNamespaceNode(map, expectToken(ShakeTokenType.IDENTIFIER), namespaceNode, dot)
        }
        return namespaceNode
    }

    /**
     * Expect a type.
     */
    private fun expectType() = expectNamespace().toType()

    /**
     * Expects a statement starting with an identifier (at call we don't know if it is a function call,
     * variable assignment, etc.)
     */
    private fun expectIdentifierStatement(parent: ShakeValuedNode? = null, dotToken: ShakeToken? = null): ShakeStatementNode {
        //
        // This parses an identifier statement (any statement starting with an identifier)
        // This can be a function call, any type of assignment, or a dot statement (e.g. a.b.c.d)
        //

        val identifier = expectToken(ShakeTokenType.IDENTIFIER)
        val identifierNode = ShakeIdentifierNode(map, parent, identifier, dotToken)

        if (input.skipIgnorable().hasNext()) {
            when (input.peekType()) {
                // Function call
                ShakeTokenType.LPAREN -> return expectInvocation(ShakeVariableUsageNode(map, identifierNode))

                // Assignments
                ShakeTokenType.ASSIGN -> return expectVariableAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.ADD_ASSIGN -> return expectVariableAddAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.SUB_ASSIGN -> return expectVariableSubAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.MUL_ASSIGN -> return expectVariableMulAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.DIV_ASSIGN -> return expectVariableDivAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.MOD_ASSIGN -> return expectVariableModAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.POW_ASSIGN -> return expectVariablePowAssignment(ShakeVariableUsageNode(map, identifierNode))

                // Modification
                ShakeTokenType.INCR -> return expectVariableIncrease(ShakeVariableUsageNode(map, identifierNode))
                ShakeTokenType.DECR -> return expectVariableDecrease(ShakeVariableUsageNode(map, identifierNode))

                // Dot statement (Sub-namespace)
                ShakeTokenType.DOT -> {
                    return expectIdentifierStatement(ShakeVariableUsageNode(map, identifierNode), input.next())
                }

                else -> throw ParserError("Expecting function call or assignment")
            }
        }
        throw ParserError("Expecting declaration, assignment or function call")
    }

    /**
     * Expects a value starting with an identifier (at call we don't know if it is a function call,
     * variable assignment, etc.)
     */
    private fun expectValueIdentifier(parent: ShakeValuedNode? = null): ShakeValuedNode {
        val identifier = expectToken(ShakeTokenType.IDENTIFIER)
        val identifierNode = ShakeIdentifierNode(map, parent, identifier, null)
        return ShakeVariableUsageNode(map, identifierNode)
    }

    // ****************************************************************************
    // Imports

    /**
     * Expects an import statement. This function is only called if the next token is an import keyword.
     */
    private fun expectImport(): ShakeImportNode {
        val importToken = expectToken(ShakeTokenType.KEYWORD_IMPORT)
        val list = mutableListOf<String>()
        do {
            input.skip()
            if (input.skipIgnorable().nextType() == ShakeTokenType.MUL) {
                list.add(ShakeImportNode.EVERYTHING)
                break
            }
            if (input.actualType != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
            list.add(expectNotNull(input.actualValue))
        } while (input.hasNext() && input.skipIgnorable().peekType() == ShakeTokenType.DOT)
        return ShakeImportNode(map, list.toTypedArray())
    }

    /**
     * Expect a package declaration. This function is only called if the next token is a package keyword.
     */
    private fun expectPackage(): ShakePackageNode {
        val packageToken = expectToken(ShakeTokenType.KEYWORD_PACKAGE)
        val list = mutableListOf<String>()
        do {
            input.skip()
            if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
            list.add(expectNotNull(input.actualValue))
        } while (input.hasNext() && input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT)
        return ShakePackageNode(map, list.toTypedArray())
    }

    // ****************************************************************************
    // Classes

    /**
     * Expects a class declaration. This function is only called if the next token is a class keyword.
     */
    private fun expectClassDeclaration(ctx: DeclarationContextInformation): ShakeClassDeclarationNode {
        val classToken = expectToken(ShakeTokenType.KEYWORD_CLASS)

        if (ctx.isSynchronized) throw ParserError("Synchronized classes are not supported")
        if (ctx.isConst) throw ParserError("Const classes are not supported")
        if (ctx.isOverride) throw ParserError("Override classes are not supported")
        if (ctx.isOperator) throw ParserError("Operator classes are not supported")

        val name = expectToken(ShakeTokenType.IDENTIFIER)
        val fields = mutableListOf<ShakeFieldDeclarationNode>()
        val methods = mutableListOf<ShakeFunctionDeclarationNode>()
        val classes = mutableListOf<ShakeClassDeclarationNode>()
        val constructors = mutableListOf<ShakeConstructorDeclarationNode>()

        var colon: ShakeToken? = null
        val superClasses = mutableListOf<ShakeNamespaceNode>()

        if (nextToken(ShakeTokenType.COLON)) {
            colon = input.peek()
            do {
                input.skip()
                superClasses.add(expectNamespace())
                // TODO Constructor invocation
            } while (nextToken(ShakeTokenType.COMMA))
        }

        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.skipIgnorable().hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            when (val node = expectDeclaration(DeclarationScope.CLASS)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeFunctionDeclarationNode -> methods.add(node)
                is ShakeFieldDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting class-body to end")
        return ShakeClassDeclarationNode(
            map,
            classToken,
            name,
            superClasses.toTypedArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            constructors.toTypedArray(),
            ctx.access ?: ShakeAccessDescriber.PACKAGE,
            ctx.staticToken,
            ctx.finalToken,
            ctx.abstractToken,
            ctx.nativeToken,
        )
    }

    /**
     * Expects an interface declaration. This function is only called if the next token is an interface keyword.
     */
    private fun expectInterfaceDeclaration(
        info: DeclarationContextInformation,
    ): ShakeClassDeclarationNode {
        val interfaceToken = expectToken(ShakeTokenType.KEYWORD_INTERFACE)

        if (info.isSynchronized) throw ParserError("Synchronized interfaces are not supported")
        if (info.isConst) throw ParserError("Const interfaces are not supported")
        if (info.isAbstract) throw ParserError("Abstract interfaces are not supported")
        if (info.isFinal) throw ParserError("Final interfaces are not supported")
        if (info.isOverride) throw ParserError("Override interfaces are not supported")
        if (info.isOperator) throw ParserError("Operator interfaces are not supported")

        val name = expectToken(ShakeTokenType.IDENTIFIER)
        val fields = mutableListOf<ShakeFieldDeclarationNode>()
        val methods = mutableListOf<ShakeFunctionDeclarationNode>()
        val classes = mutableListOf<ShakeClassDeclarationNode>()
        val constructors = mutableListOf<ShakeConstructorDeclarationNode>()

        var implements: MutableList<ShakeNamespaceNode>? = null

        if (nextToken(ShakeTokenType.COLON)) {
            input.skip()
            implements = mutableListOf()
            do {
                implements.add(expectNamespace())
            } while (nextToken(ShakeTokenType.COMMA))
        }

        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            skipSeparators()
            when (val node = expectDeclaration(DeclarationScope.INTERFACE)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeFunctionDeclarationNode -> methods.add(node)
                is ShakeFieldDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting class-body to end")
        return ShakeClassDeclarationNode(
            map,
            interfaceToken,
            name,
            implements?.toTypedArray() ?: emptyArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            constructors.toTypedArray(),
            info.access ?: ShakeAccessDescriber.PACKAGE,
            info.staticToken,
            info.finalToken,
            info.abstractToken,
            info.nativeToken,
        )
    }

    /**
     * Expects an enum declaration. This function is only called if the next token is an enum keyword.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun expectEnumDeclaration(
        info: DeclarationContextInformation,
    ): ShakeClassDeclarationNode {
        TODO("Not implemented")
    }

    /**
     * Expects an interface declaration. This function is only called if the next token is a interface keyword.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun expectObjectDeclaration(
        info: DeclarationContextInformation,
    ): ShakeClassDeclarationNode {
        TODO("Not implemented")
    }

    // ****************************************************************************
    // Functions

    /**
     * Expects function arguments. (This parses all between the "(" and ")" of a function)
     */
    private class ParametersResult(
        val args: Array<ShakeFunctionParameterNode>,
        val lparen: ShakeToken,
        val rparen: ShakeToken,
        val commas: Array<ShakeToken>,
    ) {
        operator fun component1() = args
        operator fun component2() = lparen
        operator fun component3() = rparen
        operator fun component4() = commas
    }
    private fun expectFunctionArguments(): ParametersResult {
        val args = mutableListOf<ShakeFunctionParameterNode>()
        val commas = mutableListOf<ShakeToken>()
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != ShakeTokenType.LPAREN
        ) {
            throw ParserError("Expecting '('")
        }

        val lparen = input.next()

        if (input.skipIgnorable().hasNext() && input.peekType() != ShakeTokenType.RPAREN) {
            args.add(expectArgument())
            while (input.hasNext() && input.peekType() == ShakeTokenType.COMMA) {
                commas.add(input.next())
                if (input.hasNext() && input.peekType() != ShakeTokenType.RPAREN) args.add(expectArgument()) else break
            }
        }

        if (!input.hasNext() || input.peekType() != ShakeTokenType.RPAREN) {
            throw ParserError(
                "Expecting ')'",
            )
        }

        val rparen = input.next()

        return ParametersResult(args.toTypedArray(), lparen, rparen, commas.toTypedArray())
    }

    /**
     * Expects a function argument declaration.
     */
    private fun expectArgument(): ShakeFunctionParameterNode {
        val name = expectToken(ShakeTokenType.IDENTIFIER)
        val colon = expectToken(ShakeTokenType.COLON)
        val type = expectType()
        if (nextToken(ShakeTokenType.ASSIGN)) {
            val assign = input.next()
            val value = expectValue()
            return ShakeFunctionParameterNode(map, name, colon, type, value, assign)
        }
        return ShakeFunctionParameterNode(map, name, colon, type, null, null)
    }

    /**
     * Expects a function call. This is called with the function itself already parsed. It gets the function as argument.
     * It is activated when the next token is a '(' operator on a value.
     */
    private fun expectInvocation(function: ShakeValuedNode): ShakeInvocationNode {
        val args: MutableList<ShakeValuedNode> = ArrayList()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        if (input.peekType() != ShakeTokenType.RPAREN) {
            args.add(expectNotNull(expectValue()))
            while (input.hasNext() && input.peekType() == ShakeTokenType.COMMA) {
                input.skip()
                val operation = expectValue()
                args.add(operation)
            }
        }
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
        return ShakeInvocationNode(map, function, args.toTypedArray())
    }

    /**
     * Expects a return statement. It is activated when the next token is a 'return' keyword.
     */
    private fun expectReturnStatement(): ShakeReturnNode {
        val returnToken = expectToken(ShakeTokenType.KEYWORD_RETURN)
        return ShakeReturnNode(map, expectNotNull(expectValue()), returnToken)
    }

    private fun expectConstructorDeclaration(
        info: DeclarationContextInformation,
    ): ShakeConstructorDeclarationNode {
        val constructorToken = expectToken(ShakeTokenType.KEYWORD_CONSTRUCTOR)
        if (info.scope != DeclarationScope.CLASS && info.scope != DeclarationScope.ENUM) {
            throw ParserError("A constructor must be inside of a class")
        }
        if (info.isFinal) throw ParserError("A constructor must not be final")
        if (info.isStatic) throw ParserError("A constructor must not be static")
        if (info.isAbstract) throw ParserError("A constructor must not be abstract")
        if (info.isSynchronized) throw ParserError("A constructor must not be synchronized")
        if (info.isConst) throw ParserError("A constructor must not be const")

        val name = if (input.skipIgnorable().peekType() == ShakeTokenType.IDENTIFIER) input.next() else null
        val (args, lparen, rparen, commas) = expectFunctionArguments()
        val body = expectBlock()
        return ShakeConstructorDeclarationNode(
            map,
            name,
            body,
            args,
            info.access ?: ShakeAccessDescriber.PACKAGE,
            constructorToken,
            lparen,
            rparen,
            info.synchronizedToken,
            info.nativeToken,
            commas,
        )
    }

    // ****************************************************************************
    // Variables
    private fun expectVariableAssignment(variable: ShakeValuedNode): ShakeVariableAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariableAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariableAddAssignment(variable: ShakeValuedNode): ShakeVariableAddAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.ADD_ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariableAddAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariableSubAssignment(variable: ShakeValuedNode): ShakeVariableSubAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.SUB_ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariableSubAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariableMulAssignment(variable: ShakeValuedNode): ShakeVariableMulAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.MUL_ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariableMulAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariableDivAssignment(variable: ShakeValuedNode): ShakeVariableDivAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.DIV_ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariableDivAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariableModAssignment(variable: ShakeValuedNode): ShakeVariableModAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.MOD_ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariableModAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariablePowAssignment(variable: ShakeValuedNode): ShakeVariablePowAssignmentNode {
        val operatorToken = expectToken(ShakeTokenType.POW_ASSIGN)
        val value = expectNotNull(expectValue())
        return ShakeVariablePowAssignmentNode(map, variable, value, operatorToken)
    }

    private fun expectVariableIncrease(variable: ShakeValuedNode): ShakeVariableIncreaseAfterNode {
        val operatorToken = expectToken(ShakeTokenType.INCR)
        return ShakeVariableIncreaseAfterNode(map, variable, operatorToken)
    }

    private fun expectVariableDecrease(variable: ShakeValuedNode): ShakeVariableDecreaseAfterNode {
        val operatorToken = expectToken(ShakeTokenType.DECR)
        return ShakeVariableDecreaseAfterNode(map, variable, operatorToken)
    }

    // ****************************************************************************
    // Loops & If
    private fun expectForLoop(): ShakeForNode {
        val forToken = expectToken(ShakeTokenType.KEYWORD_FOR)
        val lparen = expectToken(ShakeTokenType.LPAREN)
        val declaration = expectStatement() // TODO check if it is a statement
        val semicolon1 = expectToken(ShakeTokenType.SEMICOLON)
        val condition = expectValue()
        val semicolon2 = expectToken(ShakeTokenType.SEMICOLON)
        val round = expectStatement()
        val rparen = expectToken(ShakeTokenType.RPAREN)
        val body = expectBlock()
        return ShakeForNode(
            map,
            body,
            declaration,
            expectNotNull(condition),
            expectNotNull(round),
            forToken,
            lparen,
            semicolon1,
            semicolon2,
            rparen,
        )
    }

    private fun expectDoWhileLoop(): ShakeDoWhileNode {
        val doToken = expectToken(ShakeTokenType.KEYWORD_DO)
        val body = expectBlock()
        skipSeparators()
        val whileToken = expectToken(ShakeTokenType.KEYWORD_WHILE)
        val (condition, lparen, rparen) = expectParseConditionStatement()
        return ShakeDoWhileNode(map, body, condition, doToken, whileToken, lparen, rparen)
    }

    private fun expectWhileLoop(): ShakeWhileNode {
        val whileToken = expectToken(ShakeTokenType.KEYWORD_WHILE)
        val (condition, lparen, rparen) = expectParseConditionStatement()
        val body = expectBlock()
        return ShakeWhileNode(map, body, condition, whileToken, lparen, rparen)
    }

    private fun expectIfStatement(): ShakeIfNode {
        val ifToken = expectToken(ShakeTokenType.KEYWORD_IF)
        val (condition, lparen, rparen) = expectParseConditionStatement()
        val body = expectBlock()
        skipSeparators()
        if (input.hasNext() && input.peekType() == ShakeTokenType.KEYWORD_ELSE) {
            val elseToken = input.next()
            val elseBody = expectBlock()
            return ShakeIfNode(
                map,
                body,
                elseBody,
                condition,
                ifToken,
                lparen,
                rparen,
                elseToken,
            )
        }
        return ShakeIfNode(
            map,
            body,
            null,
            condition,
            ifToken,
            lparen,
            rparen,
            null,
        )
    }

    private class ConditionResult(val condition: ShakeValuedNode, val lparen: ShakeToken, val rparen: ShakeToken) {
        operator fun component1(): ShakeValuedNode {
            return condition
        }

        operator fun component2(): ShakeToken {
            return lparen
        }

        operator fun component3(): ShakeToken {
            return rparen
        }
    }

    private fun expectParseConditionStatement(): ConditionResult {
        val lparen = expectToken(ShakeTokenType.LPAREN)
        val condition = expectValuedLogicalOr()
        val rparen = expectToken(ShakeTokenType.RPAREN)
        return ConditionResult(condition, lparen, rparen)
    }

    private fun expectBlock(): ShakeBlockNode {
        skipSeparators()
        return if (input.peekType() == ShakeTokenType.LCURL) {
            val lcurly = input.next()
            val list = mutableListOf<ShakeStatementNode>()
            skipSeparators()
            while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
                list.add(expectStatement())
                skipSeparators()
            }
            val rcurly = expectToken(ShakeTokenType.RCURL)
            ShakeBlockNode(map, list.toTypedArray(), lcurly, rcurly)
        } else {
            ShakeBlockNode(
                map,
                arrayOf(expectNotNull(expectStatement())),
                null,
                null,
            )
        }
    }

    // ****************************************************************************
    // Valued

    private fun expectValuedAssignment(): ShakeValuedNode {
        var left = expectValuedLogicalOr()
        if (input.hasNext() && (
                input.peekType() == ShakeTokenType.ASSIGN ||
                    input.peekType() == ShakeTokenType.ADD_ASSIGN ||
                    input.peekType() == ShakeTokenType.SUB_ASSIGN ||
                    input.peekType() == ShakeTokenType.MUL_ASSIGN ||
                    input.peekType() == ShakeTokenType.DIV_ASSIGN ||
                    input.peekType() == ShakeTokenType.MOD_ASSIGN ||
                    input.peekType() == ShakeTokenType.POW_ASSIGN
                )
        ) {
            val operator = input.next()
            left = when (operator.type) {
                ShakeTokenType.ASSIGN -> ShakeVariableAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                ShakeTokenType.ADD_ASSIGN -> ShakeVariableAddAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                ShakeTokenType.SUB_ASSIGN -> ShakeVariableSubAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                ShakeTokenType.MUL_ASSIGN -> ShakeVariableMulAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                ShakeTokenType.DIV_ASSIGN -> ShakeVariableDivAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                ShakeTokenType.MOD_ASSIGN -> ShakeVariableModAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                ShakeTokenType.POW_ASSIGN -> ShakeVariablePowAssignmentNode(
                    map,
                    left,
                    expectValuedLogicalOr(),
                    operator,
                )

                else -> throw ParserError("Unexpected token")
            }
        }
        return left
    }

    // (Logical)
    private fun expectValuedLogicalOr(): ShakeValuedNode {
        var result = expectValuedLogicalXOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_OR) {
            val operator = input.next()
            result = ShakeLogicalOrNode(map, result, expectValuedLogicalXOr(), operator)
        }
        return result
    }

    private fun expectValuedLogicalXOr(): ShakeValuedNode {
        var result = expectValuedLogicalAnd()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_XOR) {
            val operator = input.next()
            result = ShakeLogicalXOrNode(map, result, expectValuedLogicalAnd(), operator)
        }
        return result
    }

    private fun expectValuedLogicalAnd(): ShakeValuedNode {
        var result = expectValuedBitwiseOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_AND) {
            val operator = input.next()
            result = ShakeLogicalAndNode(map, result, expectValuedBitwiseOr(), operator)
        }
        return result
    }

    private fun expectValuedBitwiseOr(): ShakeValuedNode {
        var result = expectValuedBitwiseXOr()
        while (input.hasNext() && (
                input.peekType() == ShakeTokenType.BITWISE_OR ||
                    input.peekType() == ShakeTokenType.BITWISE_NOR
                )
        ) {
            val operator = input.next()
            result = if (input.actualType == ShakeTokenType.BITWISE_OR) {
                ShakeBitwiseOrNode(map, result, expectValuedBitwiseXOr(), operator)
            } else {
                ShakeBitwiseNOrNode(map, result, expectValuedBitwiseXOr(), operator)
            }
        }
        return result
    }

    private fun expectValuedBitwiseXOr(): ShakeValuedNode {
        var result = expectValuedBitwiseAnd()
        while (input.hasNext() && (
                input.peekType() == ShakeTokenType.BITWISE_XOR ||
                    input.peekType() == ShakeTokenType.BITWISE_XNOR
                )
        ) {
            val operator = input.next()
            result = if (input.actualType == ShakeTokenType.BITWISE_XOR) {
                ShakeBitwiseXOrNode(map, result, expectValuedBitwiseAnd(), operator)
            } else {
                ShakeBitwiseXNOrNode(map, result, expectValuedBitwiseAnd(), operator)
            }
        }
        return result
    }

    private fun expectValuedBitwiseAnd(): ShakeValuedNode {
        var result = expectValuedEquality()
        while (input.hasNext() && (
                input.peekType() == ShakeTokenType.BITWISE_AND ||
                    input.peekType() == ShakeTokenType.BITWISE_NAND
                )
        ) {
            val operator = input.next()
            result = if (input.actualType == ShakeTokenType.BITWISE_AND) {
                ShakeBitwiseAndNode(map, result, expectValuedEquality(), operator)
            } else {
                ShakeBitwiseNAndNode(map, result, expectValuedEquality(), operator)
            }
        }
        return result
    }

    private fun expectValuedEquality(): ShakeValuedNode {
        var left = expectValuedRelational()
        var tmpType: ShakeTokenType? = null
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.EQ_EQUALS ||
                    tmpType == ShakeTokenType.NOT_EQUALS
                )
        ) {
            val operator = input.next()
            left = when (tmpType) {
                ShakeTokenType.EQ_EQUALS -> return ShakeEqualNode(map, left, expectValuedRelational(), operator)
                else -> ShakeNotEqualNode(map, left, expectValuedRelational(), operator)
            }
        }
        return left
    }

    private fun expectValuedRelational(): ShakeValuedNode {
        var left = expectValuedBitShift()
        var tmpType: ShakeTokenType? = null
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.BIGGER_EQUALS ||
                    tmpType == ShakeTokenType.SMALLER_EQUALS ||
                    tmpType == ShakeTokenType.BIGGER ||
                    tmpType == ShakeTokenType.SMALLER
                )
        ) {
            val operator = input.next()
            left = when (tmpType) {
                ShakeTokenType.BIGGER_EQUALS -> ShakeGreaterThanOrEqualNode(map, left, expectValuedBitShift(), operator)
                ShakeTokenType.SMALLER_EQUALS -> ShakeLessThanOrEqualNode(map, left, expectValuedBitShift(), operator)
                ShakeTokenType.BIGGER -> ShakeGreaterThanNode(map, left, expectValuedBitShift(), operator)
                else -> ShakeLessThanNode(map, left, expectValuedBitShift(), operator)
            }
        }
        return left
    }

    private fun expectValuedBitShift(): ShakeValuedNode {
        var left = expectValuedExpr()
        var tmpType: ShakeTokenType? = null
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.BITWISE_SHL ||
                    tmpType == ShakeTokenType.BITWISE_SHR
                )
        ) {
            val operator = input.next()
            left = when (tmpType) {
                ShakeTokenType.BITWISE_SHL -> ShakeBitwiseShiftLeftNode(map, left, expectValuedExpr(), operator)
                else -> ShakeBitwiseShiftRightNode(map, left, expectValuedExpr(), operator)
            }
        }
        return left
    }

    // (Calculations)
    private fun expectValuedExpr(): ShakeValuedNode {
        var result = expectValuedTerm()
        var tmpType: ShakeTokenType? = null
        while (input.hasNext() &&
            (input.peekType().also { tmpType = it } == ShakeTokenType.ADD || tmpType == ShakeTokenType.SUB)
        ) {
            val operator = input.next()
            result = if (tmpType == ShakeTokenType.ADD) {
                ShakeAddNode(map, result, expectValuedTerm(), operator)
            } else {
                ShakeSubNode(map, result, expectValuedTerm(), operator)
            }
        }
        return result
    }

    private fun expectValuedTerm(): ShakeValuedNode {
        var result = expectValuedPow()
        var tmpType: ShakeTokenType? = null
        while (input.hasNext() &&
            (
                input.peekType()
                    .also {
                        tmpType = it
                    } == ShakeTokenType.MUL || tmpType == ShakeTokenType.DIV || tmpType == ShakeTokenType.MOD
                )
        ) {
            val operator = input.next()
            result = when (tmpType) {
                ShakeTokenType.MUL -> ShakeMulNode(map, result, expectValuedPow(), operator)
                ShakeTokenType.DIV -> ShakeDivNode(map, result, expectValuedPow(), operator)
                else -> ShakeModNode(map, result, expectValuedPow(), operator)
            }
        }
        return result
    }

    private fun expectValuedPow(): ShakeValuedNode {
        var result = expectValuedCast()
        while (input.hasNext() && input.peekType() == ShakeTokenType.POW) {
            val pow = input.next()
            result = ShakePowNode(map, result, expectValuedCast(), pow)
        }
        return result
    }

    private fun expectValuedCast(): ShakeValuedNode {
        var result = expectValuedFunctionReturning()
        while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.KEYWORD_AS) {
            val asToken = input.next()
            val target = expectType()
            result = ShakeCastNode(map, result, target)
        }
        return result
    }

    private fun expectValuedFunctionReturning(): ShakeValuedNode {
        var value = expectValuedFactor()
        while (input.hasNext() && (
                input.peekType() == ShakeTokenType.LPAREN ||
                    input.peekType() == ShakeTokenType.DOT
                )
        ) {
            when (input.peekType()) {
                ShakeTokenType.LPAREN -> value = expectInvocation(value)
                ShakeTokenType.DOT -> {
                    input.skip()
                    value = expectValueIdentifier(value)
                }

                else -> throw ParserError("Unexpected token")
            }
        }
        return value
    }

    private fun expectValuedFactor(): ShakeValuedNode {
        val token = input.peekType()
        when (token) {
            ShakeTokenType.LPAREN -> {
                val lp = input.next()
                val contents = expectValuedLogicalOr()
                if (input.peekType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
                val rp = input.next()
                return ShakePriorityNode(map, contents, lp, rp)
            }

            ShakeTokenType.KEYWORD_TRUE -> {
                return ShakeLogicalTrueLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_FALSE -> {
                return ShakeLogicalFalseLiteralNode(map, input.next())
            }

            ShakeTokenType.INTEGER -> {
                return ShakeIntegerLiteralNode(map, input.next())
            }

            ShakeTokenType.FLOAT -> {
                return ShakeDoubleLiteralNode(map, input.next())
            }

            ShakeTokenType.IDENTIFIER -> {
                return expectValueIdentifier(null)
            }

            ShakeTokenType.ADD -> {
                val op = input.next()
                return ShakeUnaryPlusNode(map, expectValuedFactor(), op)
            }

            ShakeTokenType.SUB -> {
                val op = input.next()
                return ShakeUnaryMinusNode(map, expectValuedFactor(), op)
            }

            ShakeTokenType.LOGICAL_NOT -> {
                val op = input.next()
                return ShakeLogicalNotNode(map, expectValuedFactor(), op)
            }

            ShakeTokenType.BITWISE_NOT -> {
                val op = input.next()
                return ShakeBitwiseNotNode(map, expectValuedFactor(), op)
            }

            ShakeTokenType.STRING -> {
                return ShakeStringLiteralNode(map, input.next())
            }

            ShakeTokenType.CHARACTER -> {
                return ShakeCharacterLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_NULL -> {
                return ShakeNullLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_THIS -> {
                return ShakeThisNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_SUPER -> {
                return ShakeSuperNode(map, input.next())
            }

            else -> throw ParserError("Unexpected token $token")
        }
    }

    // Type arguments
    private fun expectTypeArgumentsDeclaration(): ShakeTypeArgumentsDeclarationNode {
        if (!input.skipIgnorable().hasNext() || input.nextType() != ShakeTokenType.SMALLER) {
            throw ParserError("Expecting '<'")
        }
        if (!input.skipIgnorable().hasNext()) throw ParserError("Expecting type argument")
        if (input.peekType() == ShakeTokenType.BIGGER) return ShakeTypeArgumentsDeclarationNode(map, emptyArray())

        val arguments = mutableListOf<ShakeTypeArgumentDeclarationNode>()

        while (input.skipIgnorable().hasNext() && input.peekType() != ShakeTokenType.BIGGER) {
            arguments.add(expectTypeArgumentDeclaration())
            if (input.peekType() == ShakeTokenType.COMMA) {
                input.skip()
            } else {
                break
            }
        }

        if (input.peekType() != ShakeTokenType.BIGGER) throw ParserError("Expecting '>'")

        return ShakeTypeArgumentsDeclarationNode(map, arguments.toTypedArray())
    }

    private fun expectTypeArgumentDeclaration(): ShakeTypeArgumentDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.IDENTIFIER) {
            throw ParserError("Expecting type argument declaration")
        }
        val name = input.actualValue ?: throw ParserError("Identifier needs a value")

        if (!input.skipIgnorable().hasNext()) throw ParserError("Expecting '>'")
        if (input.peekType() == ShakeTokenType.COLON) { // TODO Replace with COLON?
            input.skip()
            return ShakeTypeArgumentDeclarationNode(map, name, expectType())
        }

        return ShakeTypeArgumentDeclarationNode(map, name, null)
    }
}

enum class DeclarationScope {
    FILE,
    CLASS,
    OBJECT,
    INTERFACE,
    ENUM,
    BLOCK,
}

class DeclarationContextInformation(
    val scope: DeclarationScope,
) {
    var access: ShakeAccessDescriber? = null
    var staticToken: ShakeToken? = null
    var finalToken: ShakeToken? = null
    var constToken: ShakeToken? = null
    var abstractToken: ShakeToken? = null
    var overrideToken: ShakeToken? = null
    var operatorToken: ShakeToken? = null
    var nativeToken: ShakeToken? = null
    var synchronizedToken: ShakeToken? = null
    var inlineToken: ShakeToken? = null

    val isStatic: Boolean
        get() = staticToken != null

    val isFinal: Boolean
        get() = finalToken != null

    val isConst: Boolean
        get() = constToken != null

    val isAbstract: Boolean
        get() = abstractToken != null

    val isOverride: Boolean
        get() = overrideToken != null

    val isOperator: Boolean
        get() = operatorToken != null

    val isNative: Boolean
        get() = nativeToken != null

    val isSynchronized: Boolean
        get() = synchronizedToken != null

    val isInline: Boolean
        get() = inlineToken != null
}
