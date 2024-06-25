package com.shakelang.shake.parser.impl

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.shake.parser.ShakeParser
import com.shakelang.shake.parser.node.ShakeFileChildNode
import com.shakelang.shake.parser.node.ShakeNode
import com.shakelang.shake.parser.node.ShakeStatementNode
import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.misc.*
import com.shakelang.shake.parser.node.mixed.*
import com.shakelang.shake.parser.node.outer.*
import com.shakelang.shake.parser.node.outer.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeClassDeclarationSuperClassEntry
import com.shakelang.shake.parser.node.outer.ShakeConstructorDeclarationNode
import com.shakelang.shake.parser.node.statements.*
import com.shakelang.shake.parser.node.values.ShakeCastNode
import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.expression.*
import com.shakelang.shake.parser.node.values.factor.*

/**
 * The default implementation of the abstract [ShakeParser] class.
 */
class ShakeParserImpl(input: ShakeTokenInputStream) : ShakeParserHelper(input) {

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
        if (input.hasNext()) throw errorFactory.createErrorAtCurrent("Input did not end")
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
        skipSeparators()
        while (input.hasNext()) {
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
        skipSeparators()
        while (input.hasNext()) {
            if (input.hasNext()) {
                val result = expectFileChild()
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
    private fun expectFileChild(): ShakeFileChildNode {
        //
        // Expects [import | package | declaration | statement]
        //

        val token = input.peek().type
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

        return when (input.skipIgnorable().peek().type) {
            ShakeTokenType.KEYWORD_WHILE -> expectStatementWhileLoop()
            ShakeTokenType.KEYWORD_DO -> expectStatementDoWhileLoop()
            ShakeTokenType.KEYWORD_FOR -> expectStatementForLoop()
            ShakeTokenType.KEYWORD_IF -> expectStatementIf()
            ShakeTokenType.KEYWORD_RETURN -> expectStatementReturn()
            ShakeTokenType.KEYWORD_CONST,
            ShakeTokenType.KEYWORD_VAR,
            ShakeTokenType.KEYWORD_VAL,
            ShakeTokenType.KEYWORD_FINAL,
            -> expectLocalDeclaration()

            else -> {
                val value = expectValue()
                if (value is ShakeStatementNode) {
                    value
                } else {
                    throw errorFactory.createErrorAtCurrent("Expecting statement, got ${value.json}")
                }
            }
        }
    }

    // ****************************************************************************
    // Utils

    /**
     * Expects a separator (semicolon or line separator)
     * If there are multiple separators, they are skipped
     * If there are no separators, a ParserError is thrown
     * @return The consumed separator
     * @throws com.shakelang.util.parseutils.parser.AbstractParser.ParserError If there are no separators
     */
    private fun expectSeparator() {
        if (!input.hasNext()) return
//        expectToken(listOf(ShakeTokenType.SEMICOLON, ShakeTokenType.LINE_SEPARATOR), skipIgnorable = false)
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
    private fun expectDeclaration(declarationScope: DeclarationScope): ShakeNode = expectDeclaration(DeclarationContextInformation(declarationScope))

    /**
     * Expect a declaration (function declaration, class declaration, field declaration, etc.).
     * @return The parsed declaration
     */
    private fun expectDeclaration(
        info: DeclarationContextInformation,
    ): ShakeNode = when (input.peek().type) {
        //
        // Access modifiers
        // Just adds the modifier to the info (recursively calls expectDeclaration)
        // Modifiers: public, protected, private, static, final, abstract, synchronized, const, native, override, operator, inline
        //

        ShakeTokenType.KEYWORD_PUBLIC -> {
            if (info.access != null) throw errorFactory.createErrorAtCurrent("Access modifier is only allowed once")
            info.access = ShakeAccessDescriber.of(input.next())
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_PROTECTED -> {
            if (info.access != null) throw errorFactory.createErrorAtCurrent("Access modifier is only allowed once")
            info.access = ShakeAccessDescriber.of(input.next())
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_PRIVATE -> {
            if (info.access != null) throw errorFactory.createErrorAtCurrent("Access modifier is only allowed once")
            info.access = ShakeAccessDescriber.of(input.next())
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_STATIC -> {
            if (info.isStatic) throw errorFactory.createErrorAtCurrent("Static keyword is only allowed once")
            info.staticToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_FINAL -> {
            if (info.isFinal) throw errorFactory.createErrorAtCurrent("Final keyword is only allowed once")
            info.finalToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_ABSTRACT -> {
            if (info.isAbstract) throw errorFactory.createErrorAtCurrent("Abstract keyword is only allowed once")
            info.abstractToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_SYNCHRONIZED -> {
            if (info.isSynchronized) throw errorFactory.createErrorAtCurrent("Synchronized keyword is only allowed once")
            info.synchronizedToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_CONST -> {
            if (info.isConst) throw errorFactory.createErrorAtCurrent("Const keyword is only allowed once")
            info.constToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_NATIVE -> {
            if (info.isNative) throw errorFactory.createErrorAtCurrent("Native keyword is only allowed once")
            info.nativeToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_OVERRIDE -> {
            if (info.isOverride) throw errorFactory.createErrorAtCurrent("Override keyword is only allowed once")
            info.overrideToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_OPERATOR -> {
            if (info.isOperator) throw errorFactory.createErrorAtCurrent("Operator keyword is only allowed once")
            info.operatorToken = input.next()
            expectDeclaration(info)
        }

        ShakeTokenType.KEYWORD_INLINE -> {
            if (info.isInline) throw errorFactory.createErrorAtCurrent("Inline keyword is only allowed once")
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
                throw errorFactory.createErrorAtCurrent("Constructor is only allowed in classes")
            }
            expectConstructorDeclaration(info)
        }

        //
        // function
        //
        ShakeTokenType.KEYWORD_FUN -> expectMethodDeclaration(info)

        //
        // field
        //
        ShakeTokenType.KEYWORD_VAL, ShakeTokenType.KEYWORD_VAR -> expectFieldDeclaration(info)

        else -> {
            throw errorFactory.createErrorAtCurrent("Unexpected token (${input.next().type})")
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
    private fun expectMethodDeclaration(info: DeclarationContextInformation): ShakeMethodDeclarationNode {
        //
        // fun <[generics]> <[namespace]> ([args])? ([colon] <[type]>)? ([colon] <[type]>)? (<[block]>)?
        //

        val funToken = input.next()

        val generics = checkGenericsDeclaration()

        val namespace = expectNamespace()
        val expanding = namespace.parent?.toType()
        val expandingDot = namespace.dotToken

        val name = namespace.nameToken

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

        return ShakeMethodDeclarationNode(
            map,
            expanding,
            name,
            body,
            argList,
            returnType,
            info.access ?: ShakeAccessDescriber.PACKAGE,
            staticToken = info.staticToken,
            finalToken = info.finalToken,
            abstractToken = info.abstractToken,
            overrideToken = info.overrideToken,
            synchronizedToken = info.synchronizedToken,
            nativeToken = info.nativeToken,
            operatorToken = info.operatorToken,
            inlineToken = info.inlineToken,
            funToken = funToken,
            lparenToken = lparen,
            rparenToken = rparen,
            colonToken = colon,
            commaTokens = commas,
            expandingDotToken = expandingDot,
            generics = generics,
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
        while (input.skipIgnorable().hasNext() && input.peek().type == ShakeTokenType.DOT) {
            val dot = input.next()
            namespaceNode = ShakeNamespaceNode(map, expectToken(ShakeTokenType.IDENTIFIER), namespaceNode, dot)
        }
        return namespaceNode
    }

    private fun expectTypeArguments(): ShakeTypeArguments {
        val open = expectToken(ShakeTokenType.LT)
        val args = mutableListOf<ShakeVariableType>()
        val commas = mutableListOf<ShakeToken>()
        do {
            args.add(expectType())
            if (nextToken(ShakeTokenType.COMMA)) {
                commas.add(input.next())
            } else {
                break
            }
        } while (true)
        val close = expectToken(ShakeTokenType.GT)
        return ShakeTypeArguments(open, args.toTypedArray(), commas.toTypedArray(), close)
    }

    private fun checkTypeArguments(): ShakeTypeArguments? = if (nextToken(ShakeTokenType.LT)) {
        expectTypeArguments()
    } else {
        null
    }

    /**
     * Expect a type.
     */
    private fun expectType(): ShakeVariableType {
        val type = expectNamespace().toType()

        val typeargs = checkTypeArguments()

        return if (typeargs != null) {
            ShakeVariableType(type.namespace, typeargs)
        } else {
            type
        }
    }

    // ****************************************************************************
    // Imports

    /**
     * Expects an import statement. This function is only called if the next token is an import keyword.
     */
    private fun expectImport(): ShakeImportNode {
        val importToken = expectToken(ShakeTokenType.KEYWORD_IMPORT)
        val list = mutableListOf<ShakeToken>()
        val dots = mutableListOf<ShakeToken>()

        list.add(expectToken(ShakeTokenType.IDENTIFIER))

        while (nextToken(ShakeTokenType.DOT)) {
            dots.add(input.next())
            list.add(expectToken(listOf(ShakeTokenType.IDENTIFIER, ShakeTokenType.MUL)))
        }
        return ShakeImportNode(map, list.toTypedArray(), importToken, dots.toTypedArray())
    }

    /**
     * Expect a package declaration. This function is only called if the next token is a package keyword.
     */
    private fun expectPackage(): ShakePackageNode {
        val packageToken = expectToken(ShakeTokenType.KEYWORD_PACKAGE)
        return ShakePackageNode(map, expectNamespace(), packageToken)
    }

    // ****************************************************************************
    // Classes

    private fun expectTypeArgumentDefinition(): ShakeGenericDeclaration {
        val name = expectToken(ShakeTokenType.IDENTIFIER)
        val colon = if (nextToken(ShakeTokenType.COLON)) input.next() else null
        val type = if (nextToken(ShakeTokenType.IDENTIFIER)) expectType() else null
        return ShakeGenericDeclaration(name, colon, type)
    }

    private fun expectGenericsDeclaration(): ShakeGenericsDeclaration {
        val open = expectToken(ShakeTokenType.LT)
        val args = mutableListOf<ShakeGenericDeclaration>()
        val commas = mutableListOf<ShakeToken>()
        do {
            args.add(expectTypeArgumentDefinition())
            if (nextToken(ShakeTokenType.COMMA)) {
                commas.add(input.next())
            } else {
                break
            }
        } while (true)
        val close = expectToken(ShakeTokenType.GT)
        return ShakeGenericsDeclaration(open, args.toTypedArray(), commas.toTypedArray(), close)
    }

    private fun checkGenericsDeclaration(): ShakeGenericsDeclaration? = if (nextToken(ShakeTokenType.LT)) {
        expectGenericsDeclaration()
    } else {
        null
    }

    /**
     * Expects a class declaration. This function is only called if the next token is a class keyword.
     */
    private fun expectClassDeclaration(ctx: DeclarationContextInformation): ShakeClassDeclarationNode {
        val classToken = expectToken(ShakeTokenType.KEYWORD_CLASS)

        if (ctx.isSynchronized) throw errorFactory.createErrorAtCurrent("Synchronized classes are not supported")
        if (ctx.isConst) throw errorFactory.createErrorAtCurrent("Const classes are not supported")
        if (ctx.isOverride) throw errorFactory.createErrorAtCurrent("Override classes are not supported")
        if (ctx.isOperator) throw errorFactory.createErrorAtCurrent("Operator classes are not supported")

        val name = expectToken(ShakeTokenType.IDENTIFIER)

        val typeargs = checkGenericsDeclaration()

        val fields = mutableListOf<ShakeFieldDeclarationNode>()
        val methods = mutableListOf<ShakeMethodDeclarationNode>()
        val classes = mutableListOf<ShakeClassDeclarationNode>()
        val constructors = mutableListOf<ShakeConstructorDeclarationNode>()

        var colon: ShakeToken? = null
        val superClasses = mutableListOf<ShakeClassDeclarationSuperClassEntry>()

        val superCommas = mutableListOf<ShakeToken>()

        if (nextToken(ShakeTokenType.COLON)) {
            colon = input.peek()
            do {
                superCommas.add(input.next())
                superClasses.add(
                    ShakeClassDeclarationSuperClassEntry(expectType()),
                )
                // TODO Constructor invocation
            } while (nextToken(ShakeTokenType.COMMA))
        }

        val lcurl = expectToken(ShakeTokenType.LCURL)
        while (input.skipIgnorable().hasNext() && input.peek().type != ShakeTokenType.RCURL) {
            when (val node = expectDeclaration(DeclarationScope.CLASS)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeMethodDeclarationNode -> methods.add(node)
                is ShakeFieldDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        val rcurl = expectToken(ShakeTokenType.RCURL)
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
            typeargs,
        )
    }

    /**
     * Expects an interface declaration. This function is only called if the next token is an interface keyword.
     */
    private fun expectInterfaceDeclaration(
        info: DeclarationContextInformation,
    ): ShakeClassDeclarationNode {
        val interfaceToken = expectToken(ShakeTokenType.KEYWORD_INTERFACE)

        if (info.isSynchronized) throw errorFactory.createErrorAtCurrent("Synchronized interfaces are not supported")
        if (info.isConst) throw errorFactory.createErrorAtCurrent("Const interfaces are not supported")
        if (info.isAbstract) throw errorFactory.createErrorAtCurrent("Abstract interfaces are not supported")
        if (info.isFinal) throw errorFactory.createErrorAtCurrent("Final interfaces are not supported")
        if (info.isOverride) throw errorFactory.createErrorAtCurrent("Override interfaces are not supported")
        if (info.isOperator) throw errorFactory.createErrorAtCurrent("Operator interfaces are not supported")

        val name = expectToken(ShakeTokenType.IDENTIFIER)

        val typeargs = checkGenericsDeclaration()

        val fields = mutableListOf<ShakeFieldDeclarationNode>()
        val methods = mutableListOf<ShakeMethodDeclarationNode>()
        val classes = mutableListOf<ShakeClassDeclarationNode>()

        var implements: MutableList<ShakeClassDeclarationSuperClassEntry>? = null
        val commaTokens = mutableListOf<ShakeToken>()

        if (nextToken(ShakeTokenType.COLON)) {
            input.skip()
            implements = mutableListOf()
            do {
                implements.add(
                    ShakeClassDeclarationSuperClassEntry(
                        expectType(),
                    ),
                )
            } while (nextToken(ShakeTokenType.COMMA))
        }

        if (input.next().type != ShakeTokenType.LCURL) throw errorFactory.createErrorAtCurrent("Expecting class-body")
        while (input.hasNext() && input.peek().type != ShakeTokenType.RCURL) {
            skipSeparators()
            when (val node = expectDeclaration(DeclarationScope.INTERFACE)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeMethodDeclarationNode -> methods.add(node)
                is ShakeFieldDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> throw errorFactory.createErrorAtCurrent("Constructors are not allowed in interfaces")
            }
            skipSeparators()
        }
        if (input.next().type != ShakeTokenType.RCURL) throw errorFactory.createErrorAtCurrent("Expecting class-body to end")
        return ShakeClassDeclarationNode(
            map,
            interfaceToken,
            name,
            implements?.toTypedArray() ?: emptyArray(),
            fields.toTypedArray(),
            methods.toTypedArray(),
            classes.toTypedArray(),
            emptyArray(),
            info.access ?: ShakeAccessDescriber.PACKAGE,
            info.staticToken,
            info.finalToken,
            info.abstractToken,
            info.nativeToken,
            typeargs,
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
        val args: Array<ShakeParameterNode>,
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
        val args = mutableListOf<ShakeParameterNode>()
        val commas = mutableListOf<ShakeToken>()
        if (!input.skipIgnorable()
                .hasNext() ||
            input.peek().type != ShakeTokenType.LPAREN
        ) {
            throw errorFactory.createErrorAtCurrent("Expecting '('")
        }

        val lparen = input.next()

        if (input.skipIgnorable().hasNext() && input.peek().type != ShakeTokenType.RPAREN) {
            args.add(expectArgument())
            while (input.hasNext() && input.peek().type == ShakeTokenType.COMMA) {
                commas.add(input.next())
                if (input.hasNext() && input.peek().type != ShakeTokenType.RPAREN) args.add(expectArgument()) else break
            }
        }

        if (!input.hasNext() || input.peek().type != ShakeTokenType.RPAREN) {
            throw errorFactory.createErrorAtCurrent(
                "Expecting ')'",
            )
        }

        val rparen = input.next()

        return ParametersResult(args.toTypedArray(), lparen, rparen, commas.toTypedArray())
    }

    /**
     * Expects a function argument declaration.
     */
    private fun expectArgument(): ShakeParameterNode {
        val name = expectToken(ShakeTokenType.IDENTIFIER)
        val colon = expectToken(ShakeTokenType.COLON)
        val type = expectType()
        if (nextToken(ShakeTokenType.ASSIGN)) {
            val assign = input.next()
            val value = expectValue()
            return ShakeParameterNode(map, name, colon, type, value, assign)
        }
        return ShakeParameterNode(map, name, colon, type, null, null)
    }

    /**
     * Expects a function call. This is called with the function itself already parsed. It gets the function as argument.
     * It is activated when the next token is a '(' operator on a value.
     */
    private fun expectInvocation(function: ShakeValuedNode): ShakeInvocationNode {
        val args: MutableList<ShakeValuedNode> = ArrayList()
        val typeArgs = checkTypeArguments()
        val lp = expectToken(ShakeTokenType.LPAREN)
        val commas = mutableListOf<ShakeToken>()
        if (!nextToken(ShakeTokenType.RPAREN)) {
            args.add(expectNotNull(expectValue()))
            while (input.hasNext() && input.peek().type == ShakeTokenType.COMMA) {
                commas.add(input.next())
                if (nextToken(ShakeTokenType.RPAREN)) break
                val operation = expectValue()
                args.add(operation)
            }
        }
        val rp = expectToken(ShakeTokenType.RPAREN)
        return ShakeInvocationNode(map, function, args.toTypedArray(), lp, rp, commas.toTypedArray(), typeArgs)
    }

    /**
     * Expects a return statement. It is activated when the next token is a 'return' keyword.
     */
    private fun expectStatementReturn(): ShakeReturnNode {
        val returnToken = expectToken(ShakeTokenType.KEYWORD_RETURN)
        return ShakeReturnNode(map, expectNotNull(expectValue()), returnToken)
    }

    private fun expectConstructorDeclaration(
        info: DeclarationContextInformation,
    ): ShakeConstructorDeclarationNode {
        val constructorToken = expectToken(ShakeTokenType.KEYWORD_CONSTRUCTOR)
        if (info.scope != DeclarationScope.CLASS && info.scope != DeclarationScope.ENUM) {
            throw errorFactory.createErrorAtCurrent("A constructor must be inside of a class")
        }
        if (info.isFinal) throw errorFactory.createErrorAtCurrent("A constructor must not be final")
        if (info.isStatic) throw errorFactory.createErrorAtCurrent("A constructor must not be static")
        if (info.isAbstract) throw errorFactory.createErrorAtCurrent("A constructor must not be abstract")
        if (info.isSynchronized) throw errorFactory.createErrorAtCurrent("A constructor must not be synchronized")
        if (info.isConst) throw errorFactory.createErrorAtCurrent("A constructor must not be const")

        val name = if (input.skipIgnorable().peek().type == ShakeTokenType.IDENTIFIER) input.next() else null
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
    // Loops & If
    private fun expectStatementForLoop(): ShakeForNode {
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

    private fun expectStatementDoWhileLoop(): ShakeDoWhileNode {
        val doToken = expectToken(ShakeTokenType.KEYWORD_DO)
        val body = expectBlock()
        skipSeparators()
        val whileToken = expectToken(ShakeTokenType.KEYWORD_WHILE)
        val (condition, lparen, rparen) = expectParseConditionStatement()
        return ShakeDoWhileNode(map, body, condition, doToken, whileToken, lparen, rparen)
    }

    private fun expectStatementWhileLoop(): ShakeWhileNode {
        val whileToken = expectToken(ShakeTokenType.KEYWORD_WHILE)
        val (condition, lparen, rparen) = expectParseConditionStatement()
        val body = expectBlock()
        return ShakeWhileNode(map, body, condition, whileToken, lparen, rparen)
    }

    private fun expectStatementIf(): ShakeIfNode {
        val ifToken = expectToken(ShakeTokenType.KEYWORD_IF)
        val (condition, lparen, rparen) = expectParseConditionStatement()
        val body = expectBlock()
        skipSeparators()
        if (input.hasNext() && input.peek().type == ShakeTokenType.KEYWORD_ELSE) {
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
        operator fun component1(): ShakeValuedNode = condition

        operator fun component2(): ShakeToken = lparen

        operator fun component3(): ShakeToken = rparen
    }

    private fun expectParseConditionStatement(): ConditionResult {
        val lparen = expectToken(ShakeTokenType.LPAREN)
        val condition = expectValuedLogicalOr()
        val rparen = expectToken(ShakeTokenType.RPAREN)
        return ConditionResult(condition, lparen, rparen)
    }

    private fun expectBlock(): ShakeBlockNode {
        skipSeparators()
        return if (input.peek().type == ShakeTokenType.LCURL) {
            val lcurly = input.next()
            val list = mutableListOf<ShakeStatementNode>()
            skipSeparators()
            while (input.hasNext() && input.peek().type != ShakeTokenType.RCURL) {
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
    // Values

    /**
     * Parses a value (a literal, a variable usage, a method call, calculation, etc.).
     * @return The parsed value
     */
    override fun expectValue() = expectValuedAssignment()

    private fun expectValuedAssignment(): ShakeValuedNode {
        var left = expectValuedLogicalOr()
        if (input.hasNext() &&
            (
                input.peek().type == ShakeTokenType.ASSIGN ||
                    input.peek().type == ShakeTokenType.ADD_ASSIGN ||
                    input.peek().type == ShakeTokenType.SUB_ASSIGN ||
                    input.peek().type == ShakeTokenType.MUL_ASSIGN ||
                    input.peek().type == ShakeTokenType.DIV_ASSIGN ||
                    input.peek().type == ShakeTokenType.MOD_ASSIGN ||
                    input.peek().type == ShakeTokenType.POW_ASSIGN
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

                else -> throw errorFactory.createErrorAtCurrent("Unexpected token")
            }
        }
        return left
    }

    // (Logical)
    private fun expectValuedLogicalOr(): ShakeValuedNode {
        var result = expectValuedLogicalXOr()
        while (input.hasNext() && input.peek().type == ShakeTokenType.LOGIC_OR) {
            val operator = input.next()
            result = ShakeLogicalOrNode(map, result, expectValuedLogicalXOr(), operator)
        }
        return result
    }

    private fun expectValuedLogicalXOr(): ShakeValuedNode {
        var result = expectValuedLogicalAnd()
        while (input.hasNext() && input.peek().type == ShakeTokenType.LOGIC_XOR) {
            val operator = input.next()
            result = ShakeLogicalXOrNode(map, result, expectValuedLogicalAnd(), operator)
        }
        return result
    }

    private fun expectValuedLogicalAnd(): ShakeValuedNode {
        var result = expectValuedBitwiseOr()
        while (input.hasNext() && input.peek().type == ShakeTokenType.LOGIC_AND) {
            val operator = input.next()
            result = ShakeLogicalAndNode(map, result, expectValuedBitwiseOr(), operator)
        }
        return result
    }

    private fun expectValuedBitwiseOr(): ShakeValuedNode {
        var result = expectValuedBitwiseXOr()
        while (input.hasNext() &&
            (
                input.peek().type == ShakeTokenType.BIT_OR ||
                    input.peek().type == ShakeTokenType.BIT_NOR
                )
        ) {
            val operator = input.next()
            result = if (operator.type == ShakeTokenType.BIT_OR) {
                ShakeBitwiseOrNode(map, result, expectValuedBitwiseXOr(), operator)
            } else {
                ShakeBitwiseNOrNode(map, result, expectValuedBitwiseXOr(), operator)
            }
        }
        return result
    }

    private fun expectValuedBitwiseXOr(): ShakeValuedNode {
        var result = expectValuedBitwiseAnd()
        while (input.hasNext() &&
            (
                input.peek().type == ShakeTokenType.BIT_XOR ||
                    input.peek().type == ShakeTokenType.BIT_XNOR
                )
        ) {
            val operator = input.next()
            result = if (operator.type == ShakeTokenType.BIT_XOR) {
                ShakeBitwiseXOrNode(map, result, expectValuedBitwiseAnd(), operator)
            } else {
                ShakeBitwiseXNOrNode(map, result, expectValuedBitwiseAnd(), operator)
            }
        }
        return result
    }

    private fun expectValuedBitwiseAnd(): ShakeValuedNode {
        var result = expectValuedEquality()
        while (input.hasNext() &&
            (
                input.peek().type == ShakeTokenType.BIT_AND ||
                    input.peek().type == ShakeTokenType.BIT_NAND
                )
        ) {
            val operator = input.next()
            result = if (operator.type == ShakeTokenType.BIT_AND) {
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
                input.peek().type.also { tmpType = it } == ShakeTokenType.EQ ||
                    tmpType == ShakeTokenType.NEQ
                )
        ) {
            val operator = input.next()
            left = when (tmpType) {
                ShakeTokenType.EQ -> return ShakeEqualNode(map, left, expectValuedRelational(), operator)
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
                input.peek().type.also { tmpType = it } == ShakeTokenType.GE ||
                    tmpType == ShakeTokenType.LE ||
                    tmpType == ShakeTokenType.GT ||
                    tmpType == ShakeTokenType.LT
                )
        ) {
            val operator = input.next()
            left = when (tmpType) {
                ShakeTokenType.GE -> ShakeGreaterThanOrEqualNode(map, left, expectValuedBitShift(), operator)
                ShakeTokenType.LE -> ShakeLessThanOrEqualNode(map, left, expectValuedBitShift(), operator)
                ShakeTokenType.GT -> ShakeGreaterThanNode(map, left, expectValuedBitShift(), operator)
                else -> ShakeLessThanNode(map, left, expectValuedBitShift(), operator)
            }
        }
        return left
    }

    private fun expectValuedBitShift(): ShakeValuedNode {
        /* https://github.com/shakelang/shake/issues/237 */
        var left = expectValuedExpr()
        while (input.has(2) &&
            (
                (
                    // A << B
                    (input.peek().type == ShakeTokenType.LT) &&
                        (input.peek(2).type == ShakeTokenType.LT)
                    ) ||
                    // A >> B
                    (
                        input.peek().type == ShakeTokenType.GT &&
                            input.peek(2).type == ShakeTokenType.GT
                        )
                )
        ) {
            val operator = input.next()
            val operator2 = input.next()
            left = when (operator.type) {
                ShakeTokenType.LT -> ShakeBitwiseShiftLeftNode(map, left, expectValuedExpr(), operator, operator2)
                else -> {
                    // A >>> B
                    if (input.hasNext() && input.peek().type == ShakeTokenType.GT) {
                        val operator3 = input.next()
                        ShakeBitwiseShiftRightUnsignedNode(map, left, expectValuedExpr(), operator, operator2, operator3)
                    } else {
                        ShakeBitwiseShiftRightNode(map, left, expectValuedExpr(), operator, operator2)
                    }
                }
            }
        }
        return left
    }

    // (Calculations)
    private fun expectValuedExpr(): ShakeValuedNode {
        var result = expectValuedTerm()
        var tmpType: ShakeTokenType? = null
        while (input.hasNext() &&
            (input.peek().type.also { tmpType = it } == ShakeTokenType.ADD || tmpType == ShakeTokenType.SUB)
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
                input.peek().type
                    .also {
                        tmpType = it
                    } == ShakeTokenType.MUL ||
                    tmpType == ShakeTokenType.DIV ||
                    tmpType == ShakeTokenType.MOD
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
        while (input.hasNext() && input.peek().type == ShakeTokenType.POW) {
            val pow = input.next()
            result = ShakePowNode(map, result, expectValuedCast(), pow)
        }
        return result
    }

    private fun expectValuedCast(): ShakeValuedNode {
        var result = expectValuedFunctionReturning()
        while (input.skipIgnorable().hasNext() && input.peek().type == ShakeTokenType.KEYWORD_AS) {
            val asToken = input.next()
            val target = expectType()
            result = ShakeCastNode(map, result, target)
        }
        return result
    }

    private fun lookaheadCheckFunctionCall(): Boolean {
        val next = input.peek()

        var i = 2
        var level = 1

        while (input.has(i)) {
            val token = input.peek(i)
            if (token.type == ShakeTokenType.LT) {
                level++
            } else if (token.type == ShakeTokenType.GT) {
                level--
            }

            // TODO Multi line max be possible?
            // val test = mutableListOf<
            //   ShakeToken
            // >()
            if (token.type == ShakeTokenType.LINE_SEPARATOR || token.type == ShakeTokenType.SEMICOLON) {
                return false
            }

            i++
        }

        return level == 0
    }

    private fun expectValuedFunctionReturning(): ShakeValuedNode {
        var value = expectValuedMandatory()
        while (input.hasNext() &&
            (
                input.peek().type == ShakeTokenType.LPAREN ||
                    input.peek().type == ShakeTokenType.DOT ||
                    (input.peek().type == ShakeTokenType.LT && lookaheadCheckFunctionCall())
                )
        ) {
            when (input.peek().type) {
                ShakeTokenType.LPAREN,
                ShakeTokenType.LT,
                -> value = expectInvocation(value)

                ShakeTokenType.DOT -> {
                    input.skip()
                    value = expectValueIdentifier(value)
                }

                else -> throw errorFactory.createErrorAtCurrent("Unexpected token")
            }
        }
        return value
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

    private fun expectValuedMandatory(): ShakeValuedNode {
        // Incr / Decr tokens before
        val tokens = mutableListOf<ShakeToken>()
        while (nextToken(listOf(ShakeTokenType.INCR, ShakeTokenType.DECR))) {
            tokens.add(input.next())
        }

        var value = expectValuedFactor()

        tokens.reversed().forEach {
            value = when (it.type) {
                ShakeTokenType.INCR -> ShakeVariableIncrementBeforeNode(map, value, it)
                ShakeTokenType.DECR -> ShakeVariableDecrementBeforeNode(map, value, it)
                else -> throw errorFactory.createErrorAtCurrent("Unexpected token")
            }
        }

        // Incr / Decr tokens after
        while (nextToken(listOf(ShakeTokenType.INCR, ShakeTokenType.DECR))) {
            val token = input.next()
            value = when (token.type) {
                ShakeTokenType.INCR -> ShakeVariableIncrementAfterNode(map, value, token)
                ShakeTokenType.DECR -> ShakeVariableDecrementAfterNode(map, value, token)
                else -> throw errorFactory.createErrorAtCurrent("Unexpected token")
            }
        }

        return value
    }

    private fun expectValuedFactor(): ShakeValuedNode {
        val token = input.peek().type
        when (token) {
            ShakeTokenType.LPAREN -> {
                val lp = input.next()
                val contents = expectValuedLogicalOr()
                if (input.peek().type != ShakeTokenType.RPAREN) throw errorFactory.createErrorAtCurrent("Expecting ')'")
                val rp = input.next()
                return ShakePriorityNode(map, contents, lp, rp)
            }

            ShakeTokenType.KEYWORD_TRUE -> {
                return ShakeTrueLiteralNode(map, input.next())
            }

            ShakeTokenType.KEYWORD_FALSE -> {
                return ShakeFalseLiteralNode(map, input.next())
            }

            ShakeTokenType.INTEGER -> {
                return ShakeIntegerLiteralNode(map, input.next())
            }

            ShakeTokenType.FLOAT -> {
                return ShakeFloatLiteralNode(map, input.next())
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

            ShakeTokenType.LOGIC_NOT -> {
                val op = input.next()
                return ShakeLogicalNotNode(map, expectValuedFactor(), op)
            }

            ShakeTokenType.BIT_NOT -> {
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

            else -> throw errorFactory.createErrorAtCurrent("Unexpected token $token")
        }
    }
}
