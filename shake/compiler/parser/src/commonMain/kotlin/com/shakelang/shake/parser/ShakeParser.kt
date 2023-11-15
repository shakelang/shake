package com.shakelang.shake.parser

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.parser.node.*
import com.shakelang.shake.parser.node.ShakeCastNode.CastTarget
import com.shakelang.shake.parser.node.expression.*
import com.shakelang.shake.parser.node.factor.*
import com.shakelang.shake.parser.node.functions.ShakeFunctionArgumentNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.functions.ShakeReturnNode
import com.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import com.shakelang.shake.parser.node.loops.ShakeForNode
import com.shakelang.shake.parser.node.loops.ShakeWhileNode
import com.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeClassType
import com.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import com.shakelang.shake.parser.node.variables.*
import com.shakelang.shake.util.parseutils.characters.Characters.parseString
import com.shakelang.shake.util.parseutils.characters.position.Position
import com.shakelang.shake.util.parseutils.characters.position.PositionMap

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
 * The default implementation of the abstract [ShakeParser] class.
 */
class ShakeParserImpl(

    /**
     * The [ShakeTokenInputStream] to be parsed.
     */
    override val input: ShakeTokenInputStream

) : ShakeParser() {

    /**
     * The [PositionMap] of the [input]. It is directly taken from the [input], because [ShakeTokenInputStream]
     * already provides a [PositionMap] implementation.
     */
    override val map: PositionMap get() = input.map

    /**
     * Parses the [input] and returns the root [ShakeNode] of the parsed tree (A [ShakeFileNode])
     */
    override fun parse(): ShakeFileNode {
        if (!input.hasNext()) return ShakeFileNode(map, arrayOf())
        val result = doParseProgram()
        if (input.hasNext()) throw ParserError("Input did not end")
        return result
    }

    /**
     * Starts the parsing process, but directly jumps into the statement parsing phase (statements can normally be found
     * in methods, constructors, etc.).
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
        return ShakeBlockNode(map, nodes.toTypedArray())
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
        return expectDeclaration(DeclarationScope.FILE) as ShakeFileChildNode
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
            ShakeTokenType.KEYWORD_FINAL,
            ShakeTokenType.KEYWORD_DYNAMIC,
            ShakeTokenType.KEYWORD_BOOLEAN,
            ShakeTokenType.KEYWORD_CHAR,
            ShakeTokenType.KEYWORD_BYTE,
            ShakeTokenType.KEYWORD_SHORT,
            ShakeTokenType.KEYWORD_INT,
            ShakeTokenType.KEYWORD_LONG,
            ShakeTokenType.KEYWORD_FLOAT,
            ShakeTokenType.KEYWORD_DOUBLE -> expectLocalDeclaration()

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
    override fun expectValue(): ShakeValuedNode = expectLogicalOr()

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
    private fun expectSemicolon() {
        if (input.skipIgnorable().nextType() != ShakeTokenType.SEMICOLON) {
            throw ParserError("Expecting semicolon at this point")
        }
    }

    /**
     * Expect a declaration (function declaration, class declaration, field declaration, etc.).
     */
    private fun expectDeclaration(
        info: DeclarationContextInformation
    ): ShakeNode {
        return when (input.peekType()) {
            ShakeTokenType.KEYWORD_PUBLIC -> {
                input.skip()
                if (info.access != ShakeAccessDescriber.PACKAGE) throw ParserError("Access modifier is only allowed once")
                info.access = ShakeAccessDescriber.PUBLIC
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_PROTECTED -> {
                input.skip()
                if (info.access != ShakeAccessDescriber.PACKAGE) throw ParserError("Access modifier is only allowed once")
                info.access = ShakeAccessDescriber.PROTECTED
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_PRIVATE -> {
                input.skip()
                if (info.access != ShakeAccessDescriber.PACKAGE) throw ParserError("Access modifier is only allowed once")
                info.access = ShakeAccessDescriber.PRIVATE
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_STATIC -> {
                input.skip()
                if (info.scope != DeclarationScope.CLASS &&
                    info.scope != DeclarationScope.INTERFACE &&
                    info.scope != DeclarationScope.OBJECT &&
                    info.scope != DeclarationScope.ENUM
                ) {
                    throw ParserError("Static keyword is only for objects in classes, interfaces or enums")
                }
                if (info.isStatic) throw ParserError("Static keyword is only allowed once")
                info.isStatic = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_FINAL -> {
                input.skip()
                if (info.isFinal) throw ParserError("Final keyword is only allowed once")
                info.isFinal = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_ABSTRACT -> {
                input.skip()
                if (info.isAbstract) throw ParserError("Abstract keyword is only allowed once")
                info.isAbstract = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_SYNCHRONIZED -> {
                input.skip()
                if (info.isSynchronized) throw ParserError("Synchronized keyword is only allowed once")
                info.isSynchronized = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_CONST -> {
                input.skip()
                if (info.isConst) throw ParserError("Const keyword is only allowed once")
                info.isConst = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_NATIVE -> {
                input.skip()
                if (info.isNative) throw ParserError("Native keyword is only allowed once")
                info.isNative = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_OVERRIDE -> {
                input.skip()
                if (info.isOverride) throw ParserError("Override keyword is only allowed once")
                info.isOverride = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_OPERATOR -> {
                input.skip()
                if (info.isOperator) throw ParserError("Operator keyword is only allowed once")
                info.isOperator = true
                expectDeclaration(info)
            }

            ShakeTokenType.KEYWORD_INLINE -> {
                input.skip()
                if (info.isInline) throw ParserError("Inline keyword is only allowed once")
                info.isInline = true
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

            ShakeTokenType.KEYWORD_UNSIGNED,
            ShakeTokenType.KEYWORD_DYNAMIC,
            ShakeTokenType.KEYWORD_BOOLEAN,
            ShakeTokenType.KEYWORD_CHAR,
            ShakeTokenType.KEYWORD_BYTE,
            ShakeTokenType.KEYWORD_SHORT,
            ShakeTokenType.KEYWORD_INT,
            ShakeTokenType.KEYWORD_LONG,
            ShakeTokenType.KEYWORD_FLOAT,
            ShakeTokenType.KEYWORD_DOUBLE,
            ShakeTokenType.KEYWORD_VOID,
            ShakeTokenType.IDENTIFIER -> {
                // This parses a function declaration or a variable declaration

                val type = expectType()
                if (!input.skipIgnorable()
                    .hasNext()
                ) {
                    throw ParserError("Expecting identifier, but got ${input.actualType}")
                }

                val next = input.peekType()
                var expandedType: ShakeVariableType? = null
                lateinit var identifier: String

                if (
                    next == ShakeTokenType.KEYWORD_DYNAMIC ||
                    next == ShakeTokenType.KEYWORD_BOOLEAN ||
                    next == ShakeTokenType.KEYWORD_CHAR ||
                    next == ShakeTokenType.KEYWORD_BYTE ||
                    next == ShakeTokenType.KEYWORD_SHORT ||
                    next == ShakeTokenType.KEYWORD_INT ||
                    next == ShakeTokenType.KEYWORD_LONG ||
                    next == ShakeTokenType.KEYWORD_FLOAT ||
                    next == ShakeTokenType.KEYWORD_UNSIGNED ||
                    next == ShakeTokenType.KEYWORD_DOUBLE
                ) {
                    expandedType = expectType()
                    if (!input.skipIgnorable().hasNext() || input.nextType() != ShakeTokenType.DOT) {
                        throw ParserError("Expecting '.' after type, but got ${input.actualType}")
                    }

                    if (!input.skipIgnorable().hasNext() || input.nextType() != ShakeTokenType.IDENTIFIER) {
                        throw ParserError("Expecting identifier, but got ${input.actualType}")
                    }

                    identifier = input.actualValue ?: error("identifier needs value")
                } else if (next == ShakeTokenType.IDENTIFIER) {
                    val namespace = expectNamespace()
                    identifier = namespace.parts.last()
                    if (namespace.parts.size > 1) {
                        expandedType = ShakeVariableType.objectType(
                            ShakeNamespaceNode(
                                map,
                                namespace.parts.dropLast(1).toTypedArray()
                            )
                        )
                    }
                } else {
                    throw ParserError("Expecting type, but got ${input.actualType}")
                }

                val hasNext = input.skipIgnorable().hasNext()
                val peekType = if (hasNext) input.peekType() else null
                return if (hasNext && peekType == ShakeTokenType.ASSIGN) {
                    input.skip()
                    if (info.isSynchronized) throw ParserError("Synchronized variables are not supported")
                    if (info.isOperator) throw ParserError("Operator variables are not supported")
                    ShakeVariableDeclarationNode(
                        map,
                        expandedType,
                        identifier,
                        type,
                        expectValue(),
                        info.access,
                        isStatic = info.isStatic,
                        isFinal = info.isFinal,
                        isConst = info.isConst,
                        isNative = info.isNative,
                        isOverride = info.isOverride,
                        isInline = info.isInline
                    )
                } else if (hasNext && peekType == ShakeTokenType.LPAREN) {
                    if (info.isConst) throw ParserError("Const functions are not supported")
                    val args = expectFunctionArguments()
                    val body = if (!info.isAbstract && !info.isNative) expectParseBodyStatement2() else null
                    return ShakeFunctionDeclarationNode(
                        map,
                        expandedType,
                        identifier,
                        body,
                        args,
                        type,
                        info.access,
                        isStatic = info.isStatic,
                        isFinal = info.isFinal,
                        isAbstract = info.isAbstract,
                        isSynchronized = info.isSynchronized,
                        isNative = info.isNative,
                        isOverride = info.isOverride,
                        isOperator = info.isOperator,
                        isInline = info.isInline
                    )
                } else {
                    ShakeVariableDeclarationNode(
                        map,
                        expandedType,
                        identifier,
                        type,
                        null,
                        info.access,
                        isStatic = info.isStatic,
                        isFinal = info.isFinal,
                        isConst = info.isConst,
                        isNative = info.isNative,
                        isOverride = info.isOverride,
                        isInline = info.isInline
                    )
                }
            }

            else -> throw ParserError("Unexpected token (" + input.peekType() + ')')
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
                isInline = false
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
            isInline = false
        )
    }

    /**
     * Expects a type for a variable, function parameter, type-argument, etc.
     */
    private fun expectType(): ShakeVariableType {
        var t = input.peekType()

        val unsigned = if (t == ShakeTokenType.KEYWORD_UNSIGNED) {
            input.skip()
            t = input.peekType()
            true
        } else {
            false
        }
        var type = if (t == ShakeTokenType.KEYWORD_DYNAMIC) {
            if (unsigned) throw ParserError("Unsigned dynamic is not supported")
            input.skip()
            ShakeVariableType.DYNAMIC
        } else if (t == ShakeTokenType.KEYWORD_BYTE) {
            input.skip()
            if (unsigned) ShakeVariableType.UNSIGNED_BYTE else ShakeVariableType.BYTE
        } else if (t == ShakeTokenType.KEYWORD_SHORT) {
            input.skip()
            if (unsigned) ShakeVariableType.UNSIGNED_SHORT else ShakeVariableType.SHORT
        } else if (t == ShakeTokenType.KEYWORD_INT) {
            input.skip()
            if (unsigned) ShakeVariableType.UNSIGNED_INTEGER else ShakeVariableType.INTEGER
        } else if (t == ShakeTokenType.KEYWORD_LONG) {
            input.skip()
            if (unsigned) ShakeVariableType.UNSIGNED_LONG else ShakeVariableType.LONG
        } else if (t == ShakeTokenType.KEYWORD_FLOAT) {
            if (unsigned) throw ParserError("Unsigned float is not supported")
            input.skip()
            ShakeVariableType.FLOAT
        } else if (t == ShakeTokenType.KEYWORD_DOUBLE) {
            if (unsigned) throw ParserError("Unsigned double is not supported")
            input.skip()
            ShakeVariableType.DOUBLE
        } else if (t == ShakeTokenType.KEYWORD_CHAR) {
            if (unsigned) throw ParserError("Unsigned char is not supported")
            input.skip()
            ShakeVariableType.CHAR
        } else if (t == ShakeTokenType.KEYWORD_BOOLEAN) {
            if (unsigned) throw ParserError("Unsigned boolean is not supported")
            input.skip()
            ShakeVariableType.BOOLEAN
        } else if (t == ShakeTokenType.KEYWORD_VOID) {
            if (unsigned) throw ParserError("Unsigned void is not supported")
            input.skip()
            ShakeVariableType.VOID
        } else if (t == ShakeTokenType.IDENTIFIER) {
            if (unsigned) throw ParserError("Unsigned object is not supported")
            ShakeVariableType.objectType(expectNamespace())
        } else {
            throw ParserError("Expecting a variable type")
        }

        while (input.peekType() == ShakeTokenType.LSQBR) {
            input.skip()
            if (input.peekType() != ShakeTokenType.RSQBR) {
                throw ParserError("Expecting ']'")
            }
            type = ShakeVariableType.arrayType(type)
            input.skip()
        }

        return type
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
                ShakeTokenType.LPAREN -> ret = expectFunctionCall(identifierNode)
                ShakeTokenType.ASSIGN -> ret = expectVariableAssignment(identifierNode)
                ShakeTokenType.IDENTIFIER ->
                    ret =
                        this.expectLocalDeclaration(ShakeVariableType.objectType(identifierNode), false)

                ShakeTokenType.ADD_ASSIGN -> ret = expectVariableAddAssignment(identifierNode)
                ShakeTokenType.SUB_ASSIGN -> ret = expectVariableSubAssignment(identifierNode)
                ShakeTokenType.MUL_ASSIGN -> ret = expectVariableMulAssignment(identifierNode)
                ShakeTokenType.DIV_ASSIGN -> ret = expectVariableDivAssignment(identifierNode)
                ShakeTokenType.MOD_ASSIGN -> ret = expectVariableModAssignment(identifierNode)
                ShakeTokenType.POW_ASSIGN -> ret = expectVariablePowAssignment(identifierNode)
                ShakeTokenType.INCR -> ret = expectVariableIncrease(identifierNode)
                ShakeTokenType.DECR -> ret = expectVariableDecrease(identifierNode)
                ShakeTokenType.DOT -> {
                    if (ret != null && ret !is ShakeValuedNode) throw ParserError("Expecting a valued node")
                    input.skip()
                    input.skipIgnorable()
                    return expectIdentifierStatement(
                        (
                            ret ?: ShakeVariableUsageNode(
                                map,
                                identifierNode
                            )
                            ) as ShakeValuedNode
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
    private fun expectIdentifierValue(parent: ShakeValuedNode? = null): ShakeValuedNode {
        if (input.skipIgnorable().nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = ShakeIdentifierNode(map, parent, expectNotNull(input.actualValue), input.actualStart)
        var ret: ShakeValuedNode? = null

        if (input.hasNext()) {
            when (input.skipIgnorable().peekType()) {
                ShakeTokenType.LPAREN -> ret = expectFunctionCall(identifierNode)
                ShakeTokenType.ASSIGN -> ret = expectVariableAssignment(identifierNode)
                ShakeTokenType.ADD_ASSIGN -> ret = expectVariableAddAssignment(identifierNode)
                ShakeTokenType.SUB_ASSIGN -> ret = expectVariableSubAssignment(identifierNode)
                ShakeTokenType.MUL_ASSIGN -> ret = expectVariableMulAssignment(identifierNode)
                ShakeTokenType.DIV_ASSIGN -> ret = expectVariableDivAssignment(identifierNode)
                ShakeTokenType.MOD_ASSIGN -> ret = expectVariableModAssignment(identifierNode)
                ShakeTokenType.POW_ASSIGN -> ret = expectVariablePowAssignment(identifierNode)
                ShakeTokenType.INCR -> ret = expectVariableIncrease(identifierNode)
                ShakeTokenType.DECR -> ret = expectVariableDecrease(identifierNode)
                else -> {}
            }
            if (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT) {
                input.skip()
                input.skipIgnorable()
                return expectIdentifierValue(ret ?: ShakeVariableUsageNode(map, identifierNode))
            }
            if (ret != null) return ret
        }
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
        val node = expectIdentifierStatement(null) as? ShakeFunctionCallNode
            ?: throw ParserError(
                "Expecting a call after keyword new",
                start,
                input.actualEnd
            )
        return ShakeClassConstructionNode(
            map,
            node.function,
            node.args,
            newKeywordPosition
        )
    }

    // ****************************************************************************
    // Imports

    /**
     * Expects an import statement. This function is only called if the next token is an import keyword.
     */
    private fun expectImport(): ShakeImportNode {
        if (!input.hasNext() || input.peekType() != ShakeTokenType.KEYWORD_IMPORT) throw ParserError("Expecting import keyword")
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
        if (!input.skipIgnorable()
            .hasNext() || input.peekType() != ShakeTokenType.KEYWORD_PACKAGE
        ) {
            throw ParserError("Expecting package keyword")
        }
        val list = mutableListOf<String>()
        do {
            input.skip()
            if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
            list.add(expectNotNull(input.actualValue))
        } while (input.hasNext() && input.skipIgnorable().peekType() == ShakeTokenType.DOT)
        return ShakePackageNode(map, list.toTypedArray())
    }

    // ****************************************************************************
    // Classes

    /**
     * Expects a class declaration. This function is only called if the next token is a class keyword.
     */
    private fun expectClassDeclaration(ctx: DeclarationContextInformation): ShakeClassDeclarationNode {
        if (input.skipIgnorable()
            .nextType() != ShakeTokenType.KEYWORD_CLASS
        ) {
            throw ParserError("Expecting class keyword")
        }

        if (ctx.isSynchronized) throw ParserError("Synchronized classes are not supported")
        if (ctx.isConst) throw ParserError("Const classes are not supported")
        if (ctx.isOverride) throw ParserError("Override classes are not supported")
        if (ctx.isOperator) throw ParserError("Operator classes are not supported")

        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue() ?: throw ParserError("Identifier has no value")
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
            ctx.isNative
        )
    }

    /**
     * Expects an interface declaration. This function is only called if the next token is an interface keyword.
     */
    private fun expectInterfaceDeclaration(
        info: DeclarationContextInformation
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
            info.isNative
        )
    }

    /**
     * Expects an enum declaration. This function is only called if the next token is an enum keyword.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun expectEnumDeclaration(
        info: DeclarationContextInformation
    ): ShakeClassDeclarationNode {
        TODO("Not implemented")
    }

    /**
     * Expects an interface declaration. This function is only called if the next token is a interface keyword.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun expectObjectDeclaration(
        info: DeclarationContextInformation
    ): ShakeClassDeclarationNode {
        TODO("Not implemented")
    }

    // ****************************************************************************
    // Functions

    /**
     * Expects function arguments. (This parses all between the "(" and ")" of a function)
     */
    private fun expectFunctionArguments(): Array<ShakeFunctionArgumentNode> {
        val args = ArrayList<ShakeFunctionArgumentNode>()
        if (!input.skipIgnorable()
            .hasNext() || input.nextType() != ShakeTokenType.LPAREN
        ) {
            throw ParserError("Expecting '('")
        }
        if (input.skipIgnorable().hasNext() && input.peekType() != ShakeTokenType.RPAREN) {
            args.add(expectArgument())
            while (input.hasNext() && input.peekType() == ShakeTokenType.COMMA) {
                input.skip()
                if (input.hasNext() && input.peekType() != ShakeTokenType.RPAREN) args.add(expectArgument()) else break
            }
        }
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) {
            throw ParserError(
                "Expecting ')'"
            )
        }
        return args.toTypedArray()
    }

    /**
     * Expects a function argument declaration.
     */
    private fun expectArgument(): ShakeFunctionArgumentNode {
        val type = expectType()
        val name = input.nextValue() ?: throw ParserError("Expecting identifier")
        val value = if (input.peekType() == ShakeTokenType.ASSIGN) {
            input.skip()
            expectValue()
        } else {
            null
        }
        return ShakeFunctionArgumentNode(map, name, type, value)
    }

    /**
     * Expects a function call. This is called with the function itself already parsed. It gets the function as argument.
     * It is activated when the next token is a '(' operator on a value.
     */
    private fun expectFunctionCall(function: ShakeValuedNode): ShakeFunctionCallNode {
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
        return ShakeFunctionCallNode(map, function, args.toTypedArray())
    }

    /**
     * Expects a return statement. It is activated when the next token is a 'return' keyword.
     */
    private fun expectReturnStatement(): ShakeReturnNode {
        if (!input.skipIgnorable()
            .hasNext() || input.nextType() != ShakeTokenType.KEYWORD_RETURN
        ) {
            throw ParserError("Expecting 'return'")
        }
        return ShakeReturnNode(map, expectNotNull(expectValue()))
    }

    private fun expectConstructorDeclaration(
        info: DeclarationContextInformation
    ): ShakeConstructorDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_CONSTRUCTOR) throw ParserError("Expecting function keyword")
        if (info.scope != DeclarationScope.CLASS && info.scope != DeclarationScope.ENUM) {
            throw ParserError("A constructor must be inside of a class")
        }
        if (info.isFinal) throw ParserError("A constructor must not be final")
        if (info.isStatic) throw ParserError("A constructor must not be static")
        if (info.isAbstract) throw ParserError("A constructor must not be abstract")
        if (info.isSynchronized) throw ParserError("A constructor must not be synchronized")
        if (info.isConst) throw ParserError("A constructor must not be const")

        val name = if (input.skipIgnorable().peekType() == ShakeTokenType.IDENTIFIER) input.nextValue() else null
        val args = expectFunctionArguments()
        val body = expectParseBodyStatement2()
        return ShakeConstructorDeclarationNode(
            map,
            name,
            body,
            args,
            info.access,
            isSynchronized = info.isSynchronized,
            isNative = info.isNative
        )
    }

    // ****************************************************************************
    // Variables
    private fun expectVariableAssignment(variable: ShakeValuedNode): ShakeVariableAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ASSIGN) throw ParserError("Expecting '='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariableAddAssignment(variable: ShakeValuedNode): ShakeVariableAddAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ADD_ASSIGN) throw ParserError("Expecting '+='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableAddAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariableSubAssignment(variable: ShakeValuedNode): ShakeVariableSubAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.SUB_ASSIGN) throw ParserError("Expecting '-='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableSubAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariableMulAssignment(variable: ShakeValuedNode): ShakeVariableMulAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MUL_ASSIGN) throw ParserError("Expecting '*='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableMulAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariableDivAssignment(variable: ShakeValuedNode): ShakeVariableDivAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DIV_ASSIGN) throw ParserError("Expecting '/='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableDivAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariableModAssignment(variable: ShakeValuedNode): ShakeVariableModAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MOD_ASSIGN) throw ParserError("Expecting '%='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableModAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariablePowAssignment(variable: ShakeValuedNode): ShakeVariablePowAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.POW_ASSIGN) throw ParserError("Expecting '**='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariablePowAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun expectVariableIncrease(variable: ShakeValuedNode): ShakeVariableIncreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.INCR) throw ParserError("Expecting '++'")
        return ShakeVariableIncreaseNode(map, variable, input.actualStart)
    }

    private fun expectVariableDecrease(variable: ShakeValuedNode): ShakeVariableDecreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DECR) throw ParserError("Expecting '--'")
        return ShakeVariableDecreaseNode(map, variable, input.actualStart)
    }

    // ****************************************************************************
    // Loops & If
    private fun expectForLoop(): ShakeForNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_FOR) throw ParserError("Expecting for keyword")
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        val declaration = expectStatement() // TODO check if it is a statement
        expectSemicolon()
        val condition = expectValue()
        expectSemicolon()
        val round = expectStatement()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
        val body = expectParseBodyStatement()
        return ShakeForNode(
            map,
            body,
            declaration,
            expectNotNull(condition),
            expectNotNull(round)
        ) // TODO check if it is a statement
    }

    private fun expectDoWhileLoop(): ShakeDoWhileNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_DO) throw ParserError("Expecting do keyword")
        val body = expectParseBodyStatement()
        skipSeparators()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = expectParseConditionStatement()
        return ShakeDoWhileNode(map, body, condition)
    }

    private fun expectWhileLoop(): ShakeWhileNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = expectParseConditionStatement()
        val body = expectParseBodyStatement()
        return ShakeWhileNode(map, body, condition)
    }

    private fun expectIfStatement(): ShakeIfNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_IF) throw ParserError("Expecting if keyword")
        val condition = expectParseConditionStatement()
        val body = expectParseBodyStatement()
        val separator = skipSeparators() > 0
        if (input.hasNext() && input.peekType() == ShakeTokenType.KEYWORD_ELSE) {
            if (!separator) throw ParserError("Awaited separator at this point")
            input.skip()
            val elseBody = expectParseBodyStatement()
            return ShakeIfNode(map, body, elseBody, condition)
        }
        return ShakeIfNode(map, body, condition)
    }

    private fun expectParseConditionStatement(): ShakeValuedNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) {
            throw ParserError(
                "Expecting '('"
            )
        }
        val condition = expectLogicalOr()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) {
            throw ParserError(
                "Expecting ')'"
            )
        }
        return condition
    }

    private fun expectParseBodyStatement(): ShakeBlockNode {
        skipSeparators()
        return if (input.peekType() == ShakeTokenType.LCURL) {
            input.skip()
            val list = mutableListOf<ShakeStatementNode>()
            skipSeparators()
            while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
                list.add(expectStatement())
                skipSeparators()
            }
            if (!input.hasNext() || input.nextType() != ShakeTokenType.RCURL) {
                throw ParserError(
                    "Expecting '}'"
                )
            }
            ShakeBlockNode(map, list)
        } else {
            ShakeBlockNode(
                map,
                arrayOf(expectNotNull(expectStatement()))
            )
        }
    }

    private fun expectParseBodyStatement2(): ShakeBlockNode {
        skipSeparators()
        if (input.peekType() != ShakeTokenType.LCURL) throw ParserError("Expecting '{'")
        input.skip()
        val list = mutableListOf<ShakeStatementNode>()
        skipSeparators()
        while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            list.add(expectStatement())
            skipSeparators()
        }
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting '}'")
        return ShakeBlockNode(map, list)
    }

    // ****************************************************************************
    // Statements
    // (Factor)
    private fun expectFactor(): ShakeValuedNode {
        val token = input.peekType()
        if (token == ShakeTokenType.LPAREN) {
            input.skip()
            val result = expectLogicalOr()
            if (input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
            return result
        }
        if (token == ShakeTokenType.KEYWORD_TRUE) {
            input.skip()
            return ShakeLogicalTrueNode(map)
        }
        if (token == ShakeTokenType.KEYWORD_FALSE) {
            input.skip()
            return ShakeLogicalFalseNode(map)
        }
        if (token == ShakeTokenType.INTEGER) {
            input.skip()
            return ShakeIntegerNode(map, input.actualValue!!.toInt())
        }
        if (token == ShakeTokenType.DOUBLE) {
            input.skip()
            return ShakeDoubleNode(map, input.actualValue!!.toDouble())
        }
        if (token == ShakeTokenType.IDENTIFIER) {
            return expectIdentifierValue(null)
        }
        if (token == ShakeTokenType.KEYWORD_NEW) {
            return expectClassConstruction()
        }
        if (token == ShakeTokenType.ADD) {
            input.skip()
            return ShakeUnaryPlusNode(map, expectFactor(), input.position)
        }
        if (token == ShakeTokenType.SUB) {
            input.skip()
            return ShakeUnaryMinusNode(map, expectFactor(), input.position)
        }
        if (token == ShakeTokenType.LOGICAL_NOT) {
            input.skip()
            return ShakeLogicalNotNode(map, expectFactor(), input.position)
        }
        if (token == ShakeTokenType.STRING) {
            input.skip()
            return ShakeStringNode(map, parseString(input.actualValue!!))
        }
        if (token == ShakeTokenType.CHARACTER) {
            input.skip()
            return ShakeCharacterNode(map, parseString(input.actualValue!!)[0])
        }
        throw ParserError(input.toString())
    }

    // Casting
    private fun expectCast(): ShakeValuedNode {
        var result = expectFactor()
        while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.KEYWORD_AS) {
            input.skip()
            var target: CastTarget?
            when (input.skipIgnorable().peekType()) {
                ShakeTokenType.KEYWORD_BYTE -> {
                    target = CastTarget.BYTE
                    input.skip()
                }

                ShakeTokenType.KEYWORD_SHORT -> {
                    target = CastTarget.SHORT
                    input.skip()
                }

                ShakeTokenType.KEYWORD_INT -> {
                    target = CastTarget.INTEGER
                    input.skip()
                }

                ShakeTokenType.KEYWORD_LONG -> {
                    target = CastTarget.LONG
                    input.skip()
                }

                ShakeTokenType.KEYWORD_FLOAT -> {
                    target = CastTarget.FLOAT
                    input.skip()
                }

                ShakeTokenType.KEYWORD_DOUBLE -> {
                    target = CastTarget.DOUBLE
                    input.skip()
                }

                ShakeTokenType.KEYWORD_BOOLEAN -> {
                    target = CastTarget.BOOLEAN
                    input.skip()
                }

                ShakeTokenType.KEYWORD_CHAR -> {
                    target = CastTarget.CHAR
                    input.skip()
                }

                ShakeTokenType.IDENTIFIER -> target = CastTarget(expectNamespace())
                else -> throw ParserError("Expecting cast-target")
            }
            result = ShakeCastNode(map, result, target)
        }
        return result
    }

    // (Calculations)
    private fun expectExpr(): ShakeValuedNode {
        var result = expectTerm()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType().also { tmpType = it } == ShakeTokenType.ADD || tmpType == ShakeTokenType.SUB)
        ) {
            input.skip()
            val pos = input.actualStart
            result = if (tmpType == ShakeTokenType.ADD) {
                ShakeAddNode(map, result, expectTerm(), pos)
            } else {
                ShakeSubNode(map, result, expectTerm(), pos)
            }
        }
        return result
    }

    private fun expectTerm(): ShakeValuedNode {
        var result = expectPow()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (
                input.peekType()
                    .also {
                        tmpType = it
                    } == ShakeTokenType.MUL || tmpType == ShakeTokenType.DIV || tmpType == ShakeTokenType.MOD
                )
        ) {
            input.skip()
            val pos = input.actualStart
            result = when (tmpType) {
                ShakeTokenType.MUL -> ShakeMulNode(map, result, expectPow(), pos)
                ShakeTokenType.DIV -> ShakeDivNode(map, result, expectPow(), pos)
                else -> ShakeModNode(map, result, expectPow(), pos)
            }
        }
        return result
    }

    private fun expectPow(): ShakeValuedNode {
        var result = expectCast()
        while (input.hasNext() && input.peekType() == ShakeTokenType.POW) {
            input.skip()
            val pos = input.actualStart
            result = ShakePowNode(map, result, expectCast(), pos)
        }
        return result
    }

    // (Logical)
    private fun expectLogicalOr(): ShakeValuedNode {
        var result = expectLogicalXOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_OR) {
            input.skip()
            val pos = input.actualStart
            result = ShakeLogicalOrNode(map, result, expectLogicalXOr(), pos)
        }
        return result
    }

    private fun expectLogicalXOr(): ShakeValuedNode {
        var result = expectLogicalAnd()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_XOR) {
            input.skip()
            val pos = input.actualStart
            result = ShakeLogicalXOrNode(map, result, expectLogicalAnd(), pos)
        }
        return result
    }

    private fun expectLogicalAnd(): ShakeValuedNode {
        var result = expectCompare()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_AND) {
            input.skip()
            val pos = input.actualStart
            result = ShakeLogicalAndNode(map, result, expectCompare(), pos)
        }
        return result
    }

    private fun expectCompare(): ShakeValuedNode {
        var left = expectExpr()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (
                input.peekType().also { tmpType = it } == ShakeTokenType.EQ_EQUALS ||
                    tmpType == ShakeTokenType.BIGGER_EQUALS ||
                    tmpType == ShakeTokenType.SMALLER_EQUALS ||
                    tmpType == ShakeTokenType.BIGGER ||
                    tmpType == ShakeTokenType.SMALLER ||
                    tmpType == ShakeTokenType.NOT_EQUALS
                )
        ) {
            input.skip()
            val pos = input.actualStart
            left = when (tmpType) {
                ShakeTokenType.EQ_EQUALS -> return ShakeEqualNode(map, left, expectLogicalOr(), pos)
                ShakeTokenType.BIGGER_EQUALS -> ShakeGreaterThanOrEqualNode(map, left, expectLogicalOr(), pos)
                ShakeTokenType.SMALLER_EQUALS -> ShakeLessThanOrEqualNode(map, left, expectLogicalOr(), pos)
                ShakeTokenType.BIGGER -> ShakeGreaterThanNode(map, left, expectLogicalOr(), pos)
                ShakeTokenType.SMALLER -> ShakeLessThanNode(map, left, expectLogicalOr(), pos)
                else -> ShakeNotEqualNode(map, left, expectLogicalOr(), pos)
            }
        }
        return left
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
        if (input.peekType() == ShakeTokenType.KEYWORD_EXTENDS) { // TODO Replace with COLON?
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
        val name = mutableListOf<String>()
        do {
            if (!input.skipIgnorable()
                .hasNext() || input.nextType() != ShakeTokenType.IDENTIFIER
            ) {
                throw ParserError("Expecting identifier")
            }
            name.add(input.actualValue ?: throw ParserError("Expecting identifier to have value"))
        } while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT)
        return ShakeNamespaceNode(map, name.toTypedArray())
    }

    // ****************************************************************************
    // Errors

    inner class ParserError(message: String?, name: String?, details: String?, start: Position?, end: Position?) :
        com.shakelang.shake.util.parseutils.CompilerError(
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

enum class DeclarationScope {
    FILE,
    CLASS,
    OBJECT,
    INTERFACE,
    ENUM,
    BLOCK
}

class DeclarationContextInformation(
    val scope: DeclarationScope
) {
    var access: ShakeAccessDescriber = ShakeAccessDescriber.PACKAGE
    var isStatic: Boolean = false
    var isFinal: Boolean = false
    var isConst: Boolean = false
    var isAbstract: Boolean = false
    var isOverride: Boolean = false
    var isOperator: Boolean = false
    var isNative: Boolean = false
    var isSynchronized: Boolean = false
    var isInline: Boolean = false
    var isUnsigned: Boolean = false
}
