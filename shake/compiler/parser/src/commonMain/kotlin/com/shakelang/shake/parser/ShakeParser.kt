package com.shakelang.shake.parser

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.parser.node.*
import com.shakelang.shake.parser.node.ShakeCastNode.CastTarget
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
import com.shakelang.shake.parser.node.objects.ShakeClassType
import com.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import com.shakelang.shake.parser.node.variables.*
import com.shakelang.util.parseutils.CompilerError
import com.shakelang.util.parseutils.characters.position.Position
import com.shakelang.util.parseutils.characters.position.PositionMap

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

abstract class ShakeParserHelper(
    override val input: ShakeTokenInputStream,
) : ShakeParser() {

    protected fun expectNotNull(value: String?): String = value ?: throw ParserError("Value is null")

    fun expectToken(type: ShakeTokenType, message: String, skipIgnorable: Boolean = true): ShakeToken {
        if (skipIgnorable) input.skipIgnorable()
        if (!input.hasNext() || input.peekType() != type) throw ParserError(message)
        return input.next()
    }

    fun expectToken(type: ShakeTokenType, skipIgnorable: Boolean = true): ShakeToken {
        return expectToken(type, "Expecting ${type.simpleValue}", skipIgnorable)
    }

    fun nextToken(type: ShakeTokenType, skipIgnorable: Boolean = true): Boolean {
        if (skipIgnorable) input.skipIgnorable()
        return input.hasNext() && input.peekType() == type
    }

    // ****************************************************************************
    // Errors

    inner class ParserError(message: String?, name: String?, details: String?, start: Position?, end: Position?) :
        CompilerError(
            message!!,
            name!!,
            details!!,
            start!!,
            end!!,
        ) {
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

        constructor(details: String, start: Position, end: Position?) : this("ParserError", details, start, end)
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
class ShakeParserImpl(

    /**
     * The [ShakeTokenInputStream] to be parsed.
     */
    input: ShakeTokenInputStream,

) : ShakeParserHelper(input) {

    /**
     * The [PositionMap] of the [input]. It is directly taken from the [input], because [ShakeTokenInputStream]
     * already provides a [PositionMap] implementation.
     */
    override val map: PositionMap get() = input.map

    /**
     * Parses the [input] and returns the root [ShakeNode] of the parsed tree (A [ShakeFileNode])
     * Entry point of the parsing process
     */
    override fun parse(): ShakeFileNode {
        if (!input.hasNext()) return ShakeFileNode(map, arrayOf())
        val result = doParseProgram()
        if (input.hasNext()) throw ParserError("Input did not end")
        return result
    }

    /**
     * Starts the parsing process, but directly jumps into the statement parsing phase
     * (statements can normally be found in methods, constructors, etc.).
     */
    override fun parseAsStatements(): ShakeBlockNode {
        val nodes: MutableList<ShakeStatementNode> = ArrayList()
        var position = -2
        skipSeparators()
        // TODO Require Separator
        // boolean separator = true;
        while (input.hasNext()) {
            // if(!separator) throw new ParserError("AwaitSeparatorError", "Awaited separator at this point");
            // separator = false;
            if (position >= input.position) break
            position = input.position
            if (input.hasNext()) {
                val result = expectStatement()
                nodes.add(result)
            }

            // if(this.skipSeparators() > 0) separator = true;
            skipSeparators()
        }
        return ShakeBlockNode(map, nodes.toTypedArray(), null, null)
    }

    // ****************************************************************************
    // Basic Program

    /**
     * Parses a program.
     */
    private fun doParseProgram(): ShakeFileNode {
        val nodes: MutableList<ShakeFileChildNode> = ArrayList()
        var position = -2
        skipSeparators()
        // TODO Require Separator
        // boolean separator = true;
        while (input.hasNext()) {
            // if(!separator) throw new ParserError("AwaitSeparatorError", "Awaited separator at this point");
            // separator = false;
            if (position >= input.position) break
            position = input.position
            if (input.hasNext()) {
                val result = expectShakeFileChild()
                nodes.add(result)
            }

            // if(this.skipSeparators() > 0) separator = true;
            skipSeparators()
        }
        return ShakeFileNode(map, nodes)
    }

    /**
     * Parses a single file child. (package declaration, import declaration, class declaration, method declaration, etc.)
     */
    private fun expectShakeFileChild(): ShakeFileChildNode {
        val token = input.peekType()
        if (token == ShakeTokenType.KEYWORD_IMPORT) return expectImport()
        if (token == ShakeTokenType.KEYWORD_PACKAGE) return expectPackage()
        return expectDeclaration() as ShakeFileChildNode
    }

    /**
     * Parses a statement.
     */
    private fun expectStatement(): ShakeStatementNode {
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
     */
    override fun expectValue(): ShakeValuedNode = expectValuedAssignment()

    // ****************************************************************************
    // Utils

    /**
     * Skips all separators (whitespaces, comments, etc.)
     */
    private fun skipSeparators(): Int {
        var number = 0
        while (input.hasNext() && (
                input.peekType() == ShakeTokenType.SEMICOLON ||
                    input.peekType() == ShakeTokenType.LINE_SEPARATOR
                )
        ) {
            number++
            input.skip()
        }
        return number
    }

    /**
     * Expects a semicolon.
     */
    private fun expectSemicolon(): ShakeToken {
        if (input.skipIgnorable().peekType() != ShakeTokenType.SEMICOLON) {
            throw ParserError("Expecting semicolon at this point")
        }
        return input.next()
    }

    /**
     * Expect a declaration (function declaration, class declaration, field declaration, etc.).
     */
    private fun expectDeclaration(
        info: DeclarationContextInformation,
    ): ShakeNode {
        return when (input.peekType()) {
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

            ShakeTokenType.KEYWORD_CLASS -> expectClassDeclaration(info)
            ShakeTokenType.KEYWORD_INTERFACE -> expectInterfaceDeclaration(info)
            ShakeTokenType.KEYWORD_OBJECT -> expectObjectDeclaration(info)
            ShakeTokenType.KEYWORD_ENUM -> expectEnumDeclaration(info)
            ShakeTokenType.KEYWORD_CONSTRUCTOR -> {
                if (info.scope != DeclarationScope.CLASS && info.scope != DeclarationScope.ENUM) {
                    throw ParserError("Constructor is only allowed in classes")
                }
                expectConstructorDeclaration(info)
            }

            // Field declaration
            ShakeTokenType.KEYWORD_VAL, ShakeTokenType.KEYWORD_VAR -> {
                val varToken = input.next()
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

            ShakeTokenType.KEYWORD_FUN -> {
                val funToken = input.next()

                val namespace = expectNamespace()
                val expanding = namespace.parent?.toType()
                val expandingDot = namespace.dotToken
                val name = namespace.nameToken

                val lparen = expectToken(ShakeTokenType.LPAREN)

                // Collect arguments
                val argList = mutableListOf<ShakeFunctionParameterNode>()
                val commaList = mutableListOf<ShakeToken>()

                while (!nextToken(ShakeTokenType.RPAREN)) {
                    val paramName = expectToken(ShakeTokenType.IDENTIFIER)
                    val colon = expectToken(ShakeTokenType.COLON)
                    val type = expectType()

                    if (nextToken(ShakeTokenType.ASSIGN)) {
                        val assign = input.next()
                        val value = expectValue()

                        argList.add(
                            ShakeFunctionParameterNode(
                                map,
                                paramName,
                                colon,
                                type,
                                value,
                                assign,
                            ),
                        )
                    }

                    if (!nextToken(ShakeTokenType.COMMA)) break
                    commaList.add(input.next())
                }

                val rparen = expectToken(ShakeTokenType.RPAREN)

                var returnType: ShakeVariableType? = null
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
                    argList.toTypedArray(),
                    returnType!!,
                    info.access ?: ShakeAccessDescriber.PACKAGE,
                    info.staticToken,
                    info.finalToken,
                    info.abstractToken,
                    info.overrideToken,
                    info.synchronizedToken,
                    info.nativeToken,
                    info.operatorToken,
                    info.inlineToken,
                )
            }

            else -> {
                input.skip()
                throw ParserError("Unexpected token (${input.actualType})")
            }
        }
    }

    /**
     * Expect declaration of a local variable
     */
    private fun expectLocalDeclaration(): ShakeVariableDeclarationNode {
        var final = false
        if (input.peekType() == ShakeTokenType.KEYWORD_FINAL) {
            final = true
            input.skip()
        }
        if (input.peekType() == ShakeTokenType.KEYWORD_CONST) final = true
        return expectLocalDeclaration(expectType(), final)
    }

    /**
     * Expect declaration of a local variable
     */
    private fun expectLocalDeclaration(type: ShakeVariableType, const: Boolean): ShakeVariableDeclarationNode {
        if (!input.skipIgnorable().hasNext() || input.nextType() != ShakeTokenType.IDENTIFIER) {
            throw ParserError("Expecting identifier")
        }
        val name = input.actualValue!!

        if (!input.hasNext() || input.peekType() != ShakeTokenType.ASSIGN) {
            return ShakeVariableDeclarationNode(
                map,
                null,
                name,
                type,
                null,
                ShakeAccessDescriber.PACKAGE,
                isStatic = false,
                isFinal = const,
                isNative = false,
                isConst = const,
                isOverride = false,
                isInline = false,
            )
        }

        input.skip()
        val value = expectValue()

        return ShakeVariableDeclarationNode(
            map,
            null,
            name,
            type,
            value,
            ShakeAccessDescriber.PACKAGE,
            isStatic = false,
            isFinal = const,
            isNative = false,
            isConst = const,
            isOverride = false,
            isInline = false,
        )
    }

    /**
     * Expects a type for a variable, function parameter, type-argument, etc.
     */
    private fun expectType(): ShakeVariableType {
        var identifier = ShakeVariableType(expectToken(ShakeTokenType.IDENTIFIER), null, null)
        while (nextToken(ShakeTokenType.DOT)) {
            val dot = expectToken(ShakeTokenType.DOT)
            val next = expectToken(ShakeTokenType.IDENTIFIER)
            identifier = ShakeVariableType(next, identifier, dot)
        }
        return identifier
    }

    /**
     * Expects a declaration with a given declaration scope
     */
    private fun expectDeclaration(declarationScope: DeclarationScope): ShakeNode {
        return expectDeclaration(DeclarationContextInformation(declarationScope))
    }

    /**
     * Expects a statement starting with an identifier (at call we don't know if it is a function call,
     * variable assignment, etc.)
     */
    private fun expectIdentifierStatement(parent: ShakeValuedNode? = null): ShakeStatementNode {
        if (input.skipIgnorable().nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = ShakeIdentifierNode(map, parent, expectNotNull(input.actualValue), input.actualStart)
        var ret: ShakeStatementNode? = null

        if (input.skipIgnorable().hasNext()) {
            when (input.peekType()) {
                ShakeTokenType.LPAREN -> ret = expectInvocation(ShakeVariableUsageNode(map, identifierNode))
                ShakeTokenType.ASSIGN -> ret = expectVariableAssignment(ShakeVariableUsageNode(map, identifierNode))
                ShakeTokenType.IDENTIFIER ->
                    ret = this.expectLocalDeclaration(
                        ShakeVariableType.objectType(
                            ShakeVariableUsageNode(
                                map,
                                identifierNode,
                            ),
                        ),
                        false,
                    )

                ShakeTokenType.ADD_ASSIGN ->
                    ret =
                        expectVariableAddAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.SUB_ASSIGN ->
                    ret =
                        expectVariableSubAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.MUL_ASSIGN ->
                    ret =
                        expectVariableMulAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.DIV_ASSIGN ->
                    ret =
                        expectVariableDivAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.MOD_ASSIGN ->
                    ret =
                        expectVariableModAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.POW_ASSIGN ->
                    ret =
                        expectVariablePowAssignment(ShakeVariableUsageNode(map, identifierNode))

                ShakeTokenType.INCR -> ret = expectVariableIncrease(ShakeVariableUsageNode(map, identifierNode))
                ShakeTokenType.DECR -> ret = expectVariableDecrease(ShakeVariableUsageNode(map, identifierNode))
                ShakeTokenType.DOT -> {
                    if (ret != null && ret !is ShakeValuedNode) throw ParserError("Expecting a valued node")
                    input.skip()
                    input.skipIgnorable()
                    return expectIdentifierStatement(
                        (
                            ret ?: ShakeVariableUsageNode(
                                map,
                                identifierNode,
                            )
                            ) as ShakeValuedNode,
                    )
                }

                else -> {}
            }
            if (ret != null) return ret
        }
        throw ParserError("Expecting declaration, assignment or function call")
    }

    /**
     * Expects a value starting with an identifier (at call we don't know if it is a function call,
     * variable assignment, etc.)
     */
    private fun expectValueIdentifier(parent: ShakeValuedNode? = null): ShakeValuedNode {
        if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = ShakeIdentifierNode(map, parent, expectNotNull(input.actualValue), input.actualStart)
        return ShakeVariableUsageNode(map, identifierNode)
    }

    /**
     * Expects construction of class instance (identified by new keyword. This function is only called if the
     * next token is a new keyword)
     */
    private fun expectClassConstruction(): ShakeClassConstructionNode {
        if (input.skipIgnorable().nextType() != ShakeTokenType.KEYWORD_NEW) throw ParserError("Expecting new keyword")
        val newKeywordPosition = input.actualStart
        input.skipIgnorable()
        val start = input.actualStart
        val node = expectIdentifierStatement(null) as? ShakeInvocationNode
            ?: throw ParserError(
                "Expecting a call after keyword new",
                start,
                input.actualEnd,
            )
        return ShakeClassConstructionNode(
            map,
            node.function,
            node.args,
            newKeywordPosition,
        )
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
        val fields: MutableList<ShakeVariableDeclarationNode> = ArrayList()
        val methods: MutableList<ShakeFunctionDeclarationNode> = ArrayList()
        val classes: MutableList<ShakeClassDeclarationNode> = ArrayList()
        val constructors: MutableList<ShakeConstructorDeclarationNode> = ArrayList()

        var extends: ShakeNamespaceNode? = null
        var implements: MutableList<ShakeNamespaceNode>? = null

        while (input.skipIgnorable().hasNext() &&
            (
                input.peekType() == ShakeTokenType.KEYWORD_EXTENDS ||
                    input.peekType() == ShakeTokenType.KEYWORD_IMPLEMENTS
                )
        ) {
            if (input.nextType() == ShakeTokenType.KEYWORD_EXTENDS) {
                if (extends != null) throw ParserError("Class/Object can only extend one class")
                extends = expectNamespace()
            } else {
                if (implements != null) throw ParserError("Class/Object can only use implements once")
                implements = mutableListOf()

                var a = false

                do {
                    if (a) {
                        input.skip()
                        input.skipIgnorable()
                    } else {
                        a = true
                    }
                    implements.add(expectNamespace())
                } while (input.skipIgnorable().peekType() == ShakeTokenType.COMMA)
            }
        }

        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.skipIgnorable().hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            when (val node = expectDeclaration(DeclarationScope.CLASS)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeFunctionDeclarationNode -> methods.add(node)
                is ShakeVariableDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting class-body to end")
        return ShakeClassDeclarationNode(
            map,
            name,
            extends,
            implements?.toTypedArray() ?: emptyArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            constructors.toTypedArray(),
            ctx.access,
            ShakeClassType.CLASS,
            ctx.isStatic,
            ctx.isFinal,
            ctx.isAbstract,
            ctx.isNative,
        )
    }

    /**
     * Expects an interface declaration. This function is only called if the next token is an interface keyword.
     */
    private fun expectInterfaceDeclaration(
        info: DeclarationContextInformation,
    ): ShakeClassDeclarationNode {
        if (input.skipIgnorable()
                .nextType() != ShakeTokenType.KEYWORD_INTERFACE
        ) {
            throw ParserError("Expecting object keyword")
        }

        if (info.isSynchronized) throw ParserError("Synchronized interfaces are not supported")
        if (info.isConst) throw ParserError("Const interfaces are not supported")
        if (info.isAbstract) throw ParserError("Abstract interfaces are not supported")
        if (info.isFinal) throw ParserError("Final interfaces are not supported")
        if (info.isOverride) throw ParserError("Override interfaces are not supported")
        if (info.isOperator) throw ParserError("Operator interfaces are not supported")

        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")

        val name = input.nextValue() ?: throw ParserError("Identifier has no value")
        val fields: MutableList<ShakeVariableDeclarationNode> = ArrayList()
        val methods: MutableList<ShakeFunctionDeclarationNode> = ArrayList()
        val classes: MutableList<ShakeClassDeclarationNode> = ArrayList()
        val constructors: MutableList<ShakeConstructorDeclarationNode> = ArrayList()

        var implements: MutableList<ShakeNamespaceNode>? = null

        if (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.KEYWORD_EXTENDS) {
            if (implements != null) throw ParserError("Interface can only use extends once")
            implements = mutableListOf()

            var a = false

            do {
                if (a) {
                    input.skip()
                    input.skipIgnorable()
                } else {
                    a = true
                }
                implements.add(expectNamespace())
            } while (input.skipIgnorable().peekType() == ShakeTokenType.COMMA)
        }

        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            skipSeparators()
            when (val node = expectDeclaration(DeclarationScope.INTERFACE)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeFunctionDeclarationNode -> methods.add(node)
                is ShakeVariableDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting class-body to end")
        return ShakeClassDeclarationNode(
            map,
            name,
            null,
            implements?.toTypedArray() ?: emptyArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            constructors.toTypedArray(),
            info.access,
            ShakeClassType.INTERFACE,
            info.isStatic,
            info.isFinal,
            isAbstract = false,
            info.isNative,
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
        val type = expectType()
        if (!input.skipIgnorable().hasNext() || input.nextType() != ShakeTokenType.IDENTIFIER) {
            throw ParserError("Expecting identifier")
        }
        val name = input.next()
        val value = if (input.peekType() == ShakeTokenType.ASSIGN) {
            input.skip()
            expectValue()
        } else {
            null
        }
        return ShakeFunctionParameterNode(map, name, type, value)
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
        val semicolon1 = expectSemicolon()
        val condition = expectValue()
        val semicolon2 = expectSemicolon()
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
            result = ShakeLogicalOrNode(map, result, expectValuedLogicalXOr(), input.next())
        }
        return result
    }

    private fun expectValuedLogicalXOr(): ShakeValuedNode {
        var result = expectValuedLogicalAnd()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_XOR) {
            result = ShakeLogicalXOrNode(map, result, expectValuedLogicalAnd(), input.next())
        }
        return result
    }

    private fun expectValuedLogicalAnd(): ShakeValuedNode {
        var result = expectValuedBitwiseOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_AND) {
            result = ShakeLogicalAndNode(map, result, expectValuedBitwiseOr(), input.next())
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
            result = if (input.actualType == ShakeTokenType.BITWISE_OR) {
                ShakeBitwiseOrNode(map, result, expectValuedBitwiseXOr(), input.next())
            } else {
                ShakeBitwiseNOrNode(map, result, expectValuedBitwiseXOr(), input.next())
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
            result = if (input.actualType == ShakeTokenType.BITWISE_XOR) {
                ShakeBitwiseXOrNode(map, result, expectValuedBitwiseAnd(), input.next())
            } else {
                ShakeBitwiseXNOrNode(map, result, expectValuedBitwiseAnd(), input.next())
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
            result = if (input.actualType == ShakeTokenType.BITWISE_AND) {
                ShakeBitwiseAndNode(map, result, expectValuedEquality(), input.next())
            } else {
                ShakeBitwiseNAndNode(map, result, expectValuedEquality(), input.next())
            }
        }
        return result
    }

    private fun expectValuedEquality(): ShakeValuedNode {
        var left = expectValuedRelational()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.EQ_EQUALS ||
                    tmpType == ShakeTokenType.NOT_EQUALS
                )
        ) {
            left = when (tmpType) {
                ShakeTokenType.EQ_EQUALS -> return ShakeEqualNode(map, left, expectValuedRelational(), input.next())
                else -> ShakeNotEqualNode(map, left, expectValuedRelational(), input.next())
            }
        }
        return left
    }

    private fun expectValuedRelational(): ShakeValuedNode {
        var left = expectValuedBitShift()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.BIGGER_EQUALS ||
                    tmpType == ShakeTokenType.SMALLER_EQUALS ||
                    tmpType == ShakeTokenType.BIGGER ||
                    tmpType == ShakeTokenType.SMALLER
                )
        ) {
            left = when (tmpType) {
                ShakeTokenType.BIGGER_EQUALS -> ShakeGreaterThanOrEqualNode(map, left, expectValuedBitShift(), input.next())
                ShakeTokenType.SMALLER_EQUALS -> ShakeLessThanOrEqualNode(map, left, expectValuedBitShift(), input.next())
                ShakeTokenType.BIGGER -> ShakeGreaterThanNode(map, left, expectValuedBitShift(), input.next())
                else -> ShakeLessThanNode(map, left, expectValuedBitShift(), input.next())
            }
        }
        return left
    }

    private fun expectValuedBitShift(): ShakeValuedNode {
        var left = expectValuedExpr()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.BITWISE_SHL ||
                    tmpType == ShakeTokenType.BITWISE_SHR
                )
        ) {
            val pos = input.actualStart
            left = when (tmpType) {
                ShakeTokenType.BITWISE_SHL -> ShakeBitwiseShiftLeftNode(map, left, expectValuedExpr(), input.next())
                else -> ShakeBitwiseShiftRightNode(map, left, expectValuedExpr(), input.next())
            }
        }
        return left
    }

    // (Calculations)
    private fun expectValuedExpr(): ShakeValuedNode {
        var result = expectValuedTerm()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType().also { tmpType = it } == ShakeTokenType.ADD || tmpType == ShakeTokenType.SUB)
        ) {
            input.skip()
            val pos = input.actualStart
            result = if (tmpType == ShakeTokenType.ADD) {
                ShakeAddNode(map, result, expectValuedTerm(), input.next())
            } else {
                ShakeSubNode(map, result, expectValuedTerm(), input.next())
            }
        }
        return result
    }

    private fun expectValuedTerm(): ShakeValuedNode {
        var result = expectValuedPow()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (
                input.peekType()
                    .also {
                        tmpType = it
                    } == ShakeTokenType.MUL || tmpType == ShakeTokenType.DIV || tmpType == ShakeTokenType.MOD
                )
        ) {
            result = when (tmpType) {
                ShakeTokenType.MUL -> ShakeMulNode(map, result, expectValuedPow(), input.next())
                ShakeTokenType.DIV -> ShakeDivNode(map, result, expectValuedPow(), input.next())
                else -> ShakeModNode(map, result, expectValuedPow(), input.next())
            }
        }
        return result
    }

    private fun expectValuedPow(): ShakeValuedNode {
        var result = expectValuedCast()
        while (input.hasNext() && input.peekType() == ShakeTokenType.POW) {
            result = ShakePowNode(map, result, expectValuedCast(), input.next())
        }
        return result
    }

    private fun expectValuedCast(): ShakeValuedNode {
        var result = expectValuedFunctionReturning()
        while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.KEYWORD_AS) {
            input.skip()
            var target: CastTarget?
            // TODO: Add support for cast target
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
                input.skip()
                return ShakeLogicalTrueLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_FALSE -> {
                input.skip()
                return ShakeLogicalFalseLiteralNode(map, input.next())
            }

            ShakeTokenType.INTEGER -> {
                input.skip()
                return ShakeIntegerLiteralNode(map, input.next())
            }

            ShakeTokenType.FLOAT -> {
                input.skip()
                return ShakeDoubleLiteralNode(map, input.next())
            }

            ShakeTokenType.IDENTIFIER -> {
                return expectValueIdentifier(null)
            }

            ShakeTokenType.ADD -> {
                input.skip()
                return ShakeUnaryPlusNode(map, expectValuedFactor(), input.next())
            }

            ShakeTokenType.SUB -> {
                input.skip()
                return ShakeUnaryMinusNode(map, expectValuedFactor(), input.next())
            }

            ShakeTokenType.LOGICAL_NOT -> {
                input.skip()
                return ShakeLogicalNotNode(map, expectValuedFactor(), input.next())
            }

            ShakeTokenType.BITWISE_NOT -> {
                input.skip()
                return ShakeBitwiseNotNode(map, expectValuedFactor(), input.next())
            }

            ShakeTokenType.STRING -> {
                input.skip()
                return ShakeStringLiteralNode(map, input.next())
            }

            ShakeTokenType.CHARACTER -> {
                input.skip()
                return ShakeCharacterLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_NULL -> {
                input.skip()
                return ShakeNullLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_THIS -> {
                input.skip()
                return ShakeThisNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_SUPER -> {
                input.skip()
                return ShakeSuperNode(map, input.next())
            }

            else -> throw ParserError(input.toString())
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

    private fun <T> expectNotNull(v: T?): T {
        if (v == null) throw ParserError("Expecting value")
        return v
    }

    private fun expectNamespace(): ShakeNamespaceNode {
        var namespaceNode = ShakeNamespaceNode(map, expectToken(ShakeTokenType.IDENTIFIER), null, null)
        while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT) {
            val dot = input.next()
            namespaceNode = ShakeNamespaceNode(map, expectToken(ShakeTokenType.IDENTIFIER), namespaceNode, dot)
        }
        return namespaceNode
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
