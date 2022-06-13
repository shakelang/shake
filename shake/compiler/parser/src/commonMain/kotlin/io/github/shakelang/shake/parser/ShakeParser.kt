package io.github.shakelang.shake.parser

import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.Characters.parseString
import io.github.shakelang.parseutils.characters.position.Position
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.lexer.token.ShakeTokenType
import io.github.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.ShakeCastNode.CastTarget
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeCharacterNode
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.factor.ShakeStringNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionArgumentNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.functions.ShakeReturnNode
import io.github.shakelang.shake.parser.node.logical.*
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassType
import io.github.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import io.github.shakelang.shake.parser.node.variables.*

abstract class ShakeParser {

    abstract val input: ShakeTokenInputStream
    abstract val map: PositionMap

    abstract fun parse(): ShakeFileNode
    abstract fun parseAsStatements(): ShakeBlockNode
    abstract fun expectValue(): ShakeNode

    companion object {

        fun from(input: ShakeTokenInputStream) = ShakeParserImpl(input)

    }

}

class ShakeParserImpl (

    override val input: ShakeTokenInputStream

) : ShakeParser() {

    override val map: PositionMap = input.map

    override fun parse(): ShakeFileNode {
        if (!input.hasNext()) return ShakeFileNode(map, arrayOf())
        val result = doParseProgram()
        if (input.hasNext()) throw ParserError("Input did not end")
        return result
    }

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

    fun doParseProgram(): ShakeFileNode {
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

    fun expectShakeFileChild(): ShakeFileChildNode {
        val token = input.peekType()
        if (token == ShakeTokenType.KEYWORD_IMPORT) return  expectImport()
        return expectDeclarationInFile()
    }

    fun expectDeclarationInFile(
        access: ShakeAccessDescriber = ShakeAccessDescriber.PACKAGE,
        isFinal: Boolean = false,
        isAbstract: Boolean = false,
    ): ShakeFileChildNode {
        val input = input
        return when (input.peekType()) {
            ShakeTokenType.KEYWORD_PUBLIC -> {
                input.skip()
                expectDeclarationInFile(ShakeAccessDescriber.PUBLIC, isFinal)
            }
            ShakeTokenType.KEYWORD_PROTECTED -> {
                input.skip()
                expectDeclarationInFile(ShakeAccessDescriber.PROTECTED, isFinal)
            }
            ShakeTokenType.KEYWORD_PRIVATE -> {
                input.skip()
                expectDeclarationInFile(ShakeAccessDescriber.PRIVATE, isFinal)
            }
            ShakeTokenType.KEYWORD_STATIC -> {
                throw ParserError("Static keyword is only for objects in classes")
            }
            ShakeTokenType.KEYWORD_FINAL -> {
                input.skip()
                if(isFinal) throw ParserError("Final keyword is only allowed once")
                expectDeclarationInFile(access, isFinal = true, isAbstract = isAbstract)
            }
            ShakeTokenType.KEYWORD_ABSTRACT -> {
                input.skip()
                if(isAbstract) throw ParserError("Abstract keyword is only allowed once")
                expectDeclarationInFile(access, isFinal = isFinal, isAbstract = true)
            }
            ShakeTokenType.KEYWORD_CLASS,
            ShakeTokenType.KEYWORD_OBJECT,
            ShakeTokenType.KEYWORD_ENUM,
            ShakeTokenType.KEYWORD_INTERFACE
                -> expectClassDeclaration(access, DeclarationScope.FILE, isStatic = false, isFinal = isFinal)
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
            ShakeTokenType.IDENTIFIER -> expectCStyleDeclaration(
                access,
                isStatic = false,
                isFinal = isFinal,
                isAbstract = isAbstract
            )
            else -> throw ParserError("Unexpected token (" + input.peekType() + ')')
        }
    }

    fun expectStatement(): ShakeStatementNode {
        val token = input.peekType()
        return when(token) {
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
                throw ParserError("Unexpected token ($token)")
            }
        }
    }

    override fun expectValue(): ShakeValuedNode = expectLogicalOr()

    // ****************************************************************************
    // Utils
    fun skipSeparators(): Int {
        var number = 0
        while (input.hasNext() && (input.peekType() == ShakeTokenType.SEMICOLON
                    || input.peekType() == ShakeTokenType.LINE_SEPARATOR)
        ) {
            number++
            input.skip()
        }
        return number
    }

    fun expectSemicolon() {
        if (input.skipIgnorable().nextType() != ShakeTokenType.SEMICOLON)
            throw ParserError("Expecting semicolon at this point")
    }

    fun expectDeclaration(
        access: ShakeAccessDescriber,
        declarationScope: DeclarationScope,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean,
    ): ShakeNode {
        val input = input
        return when (input.peekType()) {
            ShakeTokenType.KEYWORD_PUBLIC -> {
                input.skip()
                if(access != ShakeAccessDescriber.PACKAGE) throw ParserError("Access modifier is only allowed once")
                expectDeclaration(ShakeAccessDescriber.PUBLIC, declarationScope, isStatic, isFinal, isAbstract)
            }
            ShakeTokenType.KEYWORD_PROTECTED -> {
                input.skip()
                if(access != ShakeAccessDescriber.PACKAGE) throw ParserError("Access modifier is only allowed once")
                expectDeclaration(ShakeAccessDescriber.PROTECTED, declarationScope, isStatic, isFinal, isAbstract)
            }
            ShakeTokenType.KEYWORD_PRIVATE -> {
                input.skip()
                if(access != ShakeAccessDescriber.PACKAGE) throw ParserError("Access modifier is only allowed once")
                expectDeclaration(ShakeAccessDescriber.PRIVATE, declarationScope, isStatic, isFinal, isAbstract)
            }
            ShakeTokenType.KEYWORD_STATIC -> {
                input.skip()
                if (declarationScope != DeclarationScope.CLASS
                    && declarationScope != DeclarationScope.INTERFACE
                    && declarationScope != DeclarationScope.OBJECT
                    && declarationScope != DeclarationScope.ENUM)
                        throw ParserError("Static keyword is only for objects in classes, interfaces or enums")
                expectDeclaration(access, declarationScope, isStatic = true, isFinal = isFinal, isAbstract = isAbstract)
            }
            ShakeTokenType.KEYWORD_FINAL -> {
                input.skip()
                if(isFinal) throw ParserError("Final keyword is only allowed once")
                expectDeclaration(access, declarationScope, isStatic, true, isAbstract)
            }
            ShakeTokenType.KEYWORD_ABSTRACT -> {
                input.skip()
                if(isAbstract) throw ParserError("Abstract keyword is only allowed once")
                expectDeclaration(access, declarationScope, isStatic, isFinal, true)
            }
            ShakeTokenType.KEYWORD_CLASS -> expectClassDeclaration(access, declarationScope, isStatic, isFinal)
            ShakeTokenType.KEYWORD_CONSTRUCTOR -> expectConstructorDeclaration(access, declarationScope, isStatic, isFinal)
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
            ShakeTokenType.IDENTIFIER -> expectCStyleDeclaration(
                access,
                isStatic,
                isFinal,
                isAbstract
            )
            else -> throw ParserError("Unexpected token (" + input.peekType() + ')')
        }
    }

    fun expectLocalDeclaration(): ShakeVariableDeclarationNode {
        var final = false
        if(input.peekType() == ShakeTokenType.KEYWORD_FINAL) {
            final = true
            input.skip()
        }
        if(input.peekType() == ShakeTokenType.KEYWORD_CONST) final = true
        return expectLocalDeclaration(expectType(), final)
    }

    fun expectLocalDeclaration(type: ShakeVariableType, final: Boolean): ShakeVariableDeclarationNode {
        if(input.nextType() != ShakeTokenType.IDENTIFIER)
            throw ParserError("Expecting identifier")
        val name = input.actualValue!!

        if(!input.hasNext() || input.peekType() != ShakeTokenType.ASSIGN)
            return ShakeVariableDeclarationNode(map, name, type, null, ShakeAccessDescriber.PACKAGE, isStatic = false, isFinal = final)

        input.skip()
        val value = expectValue()

        return ShakeVariableDeclarationNode(map, name, type, value, ShakeAccessDescriber.PACKAGE, isStatic = false, isFinal = final)
    }

    fun expectType(): ShakeVariableType {
        return when(input.nextType()) {
            ShakeTokenType.KEYWORD_DYNAMIC -> ShakeVariableType.DYNAMIC
            ShakeTokenType.KEYWORD_BOOLEAN -> ShakeVariableType.BOOLEAN
            ShakeTokenType.KEYWORD_CHAR -> ShakeVariableType.CHAR
            ShakeTokenType.KEYWORD_BYTE -> ShakeVariableType.BYTE
            ShakeTokenType.KEYWORD_SHORT -> ShakeVariableType.SHORT
            ShakeTokenType.KEYWORD_INT -> ShakeVariableType.INTEGER
            ShakeTokenType.KEYWORD_LONG -> ShakeVariableType.LONG
            ShakeTokenType.KEYWORD_FLOAT -> ShakeVariableType.FLOAT
            ShakeTokenType.KEYWORD_DOUBLE -> ShakeVariableType.DOUBLE
            ShakeTokenType.IDENTIFIER -> {
                TODO("Not implemented")
            }
            else -> throw ParserError("Unexpected token (" + input.peekType() + ')')
        }
    }

    fun expectDeclaration(declarationScope: DeclarationScope): ShakeNode {
        return expectDeclaration(
            ShakeAccessDescriber.PACKAGE,
            declarationScope,
            isStatic = false,
            isFinal = false,
            isAbstract = false
        )
    }

    fun expectIdentifierStatement(parent: ShakeValuedNode? = null): ShakeStatementNode {
        if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = ShakeIdentifierNode(map, parent, expectNotNull(input.actualValue), input.actualStart)
        var ret: ShakeStatementNode? = null

        if (input.hasNext()) {
            when (input.skipIgnorable().peekType()) {
                ShakeTokenType.LPAREN -> ret = expectFunctionCall(identifierNode)
                ShakeTokenType.ASSIGN -> ret = expectVariableAssignment(identifierNode)
                ShakeTokenType.IDENTIFIER -> ret = this.expectLocalDeclaration(ShakeVariableType(identifierNode), false)
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
                if(ret != null && ret !is ShakeValuedNode) throw ParserError("Expecting a valued node")
                input.skip()
                input.skipIgnorable()
                return expectIdentifierStatement((ret ?: ShakeVariableUsageNode(map, identifierNode)) as ShakeValuedNode )
            }
            if (ret != null) return ret
        }
        throw ParserError("Expecting declaration, assignment or function call")
    }

    fun expectIdentifierValue(parent: ShakeValuedNode? = null): ShakeValuedNode {
        if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
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

    fun expectClassConstruction(): ShakeClassConstructionNode {
        input.skip()
        val newKeywordPosition = input.actualStart
        input.skipIgnorable()
        val start = input.actualStart
        val node = expectIdentifierStatement(null) as? ShakeFunctionCallNode
            ?: throw ParserError(
                "Expecting a call after keyword new",
                start, input.actualEnd
            )
        return ShakeClassConstructionNode(
            map, node.function, node.args,
            newKeywordPosition
        )
    }

    // ****************************************************************************
    // Imports
    fun expectImport(): ShakeImportNode {
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

    // ****************************************************************************
    // Classes
    fun expectClassDeclaration(
        access: ShakeAccessDescriber,
        declarationScope: DeclarationScope,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeClassDeclarationNode {

        val type = when (input.skipIgnorable().nextType()) {
            ShakeTokenType.KEYWORD_CLASS -> ShakeClassType.CLASS
            ShakeTokenType.KEYWORD_INTERFACE -> ShakeClassType.INTERFACE
            ShakeTokenType.KEYWORD_ENUM -> ShakeClassType.ENUM
            ShakeTokenType.KEYWORD_OBJECT -> ShakeClassType.OBJECT
            else -> throw ParserError("Expecting class or interface keyword")
        }

        if(type == ShakeClassType.INTERFACE && isFinal) throw ParserError("Interfaces cannot be final")

        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue() ?: throw ParserError("Identifier has no value")
        val fields: MutableList<ShakeVariableDeclarationNode> = ArrayList()
        val methods: MutableList<ShakeFunctionDeclarationNode> = ArrayList()
        val classes: MutableList<ShakeClassDeclarationNode> = ArrayList()
        val constructors: MutableList<ShakeConstructorDeclarationNode> = ArrayList()

        var extends: ShakeNamespaceNode? = null
        var implements: MutableList<ShakeNamespaceNode>? = null

        if(type == ShakeClassType.OBJECT || type == ShakeClassType.CLASS) {
            while (input.skipIgnorable().hasNext() &&
                (input.peekType() == ShakeTokenType.KEYWORD_EXTENDS
                        || input.peekType() == ShakeTokenType.KEYWORD_IMPLEMENTS)) {

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
                        } else a = true
                        implements.add(expectNamespace())

                    } while (input.skipIgnorable().peekType() == ShakeTokenType.COMMA)
                }
            }
        }
        else if(type == ShakeClassType.INTERFACE) {
            if (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.KEYWORD_EXTENDS) {
                if (implements != null) throw ParserError("Interface can only use extends once")
                implements = mutableListOf()

                var a = false

                do {
                    if (a) {
                        input.skip()
                        input.skipIgnorable()
                    } else a = true
                    implements.add(expectNamespace())
                } while (input.skipIgnorable().peekType() == ShakeTokenType.COMMA)
            }
        }

        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            skipSeparators()
            when (val node = expectDeclaration(
                when(type) {
                    ShakeClassType.CLASS -> DeclarationScope.CLASS
                    ShakeClassType.INTERFACE -> DeclarationScope.INTERFACE
                    ShakeClassType.ENUM -> DeclarationScope.ENUM
                    ShakeClassType.OBJECT -> DeclarationScope.OBJECT
                }
            )) {
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
            access,
            type,
            isStatic,
            isFinal
        )
    }

    // ****************************************************************************
    // Functions

    fun expectCStyleFunctionDeclaration(
        type: ShakeVariableType,
        identifier: String,
        access: ShakeAccessDescriber,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean
    ): ShakeFunctionDeclarationNode {
        val args = expectFunctionArguments()
        val body = expectParseBodyStatement()
        return ShakeFunctionDeclarationNode(
            map,
            identifier,
            body,
            args,
            type,
            access,
            isStatic,
            isFinal,
            isAbstract
        )
    }

    fun expectFunctionArguments(): Array<ShakeFunctionArgumentNode> {
        val args = ArrayList<ShakeFunctionArgumentNode>()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        if (input.hasNext() && input.peekType() != ShakeTokenType.RPAREN) {
            args.add(expectArgument())
            while (input.hasNext() && input.peekType() == ShakeTokenType.COMMA) {
                input.skip()
                if (input.hasNext() && input.peekType() != ShakeTokenType.RPAREN) args.add(expectArgument()) else break
            }
        }
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError(
            "Expecting ')'"
        )
        return args.toTypedArray()
    }

    fun expectFunctionCall(function: ShakeValuedNode): ShakeFunctionCallNode {
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

    fun expectArgument(): ShakeFunctionArgumentNode {
        val next = input.nextType()
        input.skipIgnorable()
        var peek: ShakeTokenType
        if (next == ShakeTokenType.IDENTIFIER && (!input.hasNext() || input.peekType()
                .also { peek = it } != ShakeTokenType.IDENTIFIER && (peek != ShakeTokenType.DOT))
        ) return ShakeFunctionArgumentNode(map, input.actualValue!!)
        val type: ShakeVariableType
        when (next) {
            ShakeTokenType.KEYWORD_DYNAMIC -> type = ShakeVariableType.DYNAMIC
            ShakeTokenType.KEYWORD_BOOLEAN -> type = ShakeVariableType.BOOLEAN
            ShakeTokenType.KEYWORD_CHAR -> type = ShakeVariableType.CHAR
            ShakeTokenType.KEYWORD_BYTE -> type = ShakeVariableType.BYTE
            ShakeTokenType.KEYWORD_SHORT -> type = ShakeVariableType.SHORT
            ShakeTokenType.KEYWORD_INT -> type = ShakeVariableType.INTEGER
            ShakeTokenType.KEYWORD_LONG -> type = ShakeVariableType.LONG
            ShakeTokenType.KEYWORD_FLOAT -> type = ShakeVariableType.FLOAT
            ShakeTokenType.KEYWORD_DOUBLE -> type = ShakeVariableType.DOUBLE
            ShakeTokenType.IDENTIFIER -> {
                var node = ShakeIdentifierNode(
                    map, input.actualValue!!,
                    input.position
                )
                while (input.peekType() == ShakeTokenType.DOT) {
                    input.skip()
                    input.skipIgnorable()
                    if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
                    node = ShakeIdentifierNode(map, input.actualValue!!, input.actualStart)
                }
                type = ShakeVariableType(node)
            }
            else -> throw ParserError("Unknown variable-type token: " + next.name)
        }
        return if (input.hasNext() && input.peekType() == ShakeTokenType.IDENTIFIER) {
            val identifier = input.nextValue()
            input.skipIgnorable()
            ShakeFunctionArgumentNode(map, identifier!!, type)
        } else throw ParserError("Expecting identifier")
    }

    fun expectReturnStatement(): ShakeReturnNode {
        input.skip()
        return ShakeReturnNode(map, expectNotNull(expectValue()))
    }

    fun expectConstructorDeclaration(
        access: ShakeAccessDescriber,
        declarationScope: DeclarationScope,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeConstructorDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_CONSTRUCTOR) throw ParserError("Expecting function keyword")
        if (declarationScope != DeclarationScope.CLASS && declarationScope != DeclarationScope.ENUM)    throw ParserError("A constructor must be inside of a class")
        if (isFinal) throw ParserError("A constructor must not be final")
        if (isStatic) throw ParserError("A constructor must not be static")
        val name = if (input.skipIgnorable().peekType() == ShakeTokenType.IDENTIFIER) input.nextValue() else null
        val args = expectFunctionArguments()
        val body = expectParseBodyStatement()
        return ShakeConstructorDeclarationNode(map, name, body, args, access)
    }

    // ****************************************************************************
    // Variables
    fun expectVariableAssignment(variable: ShakeValuedNode): ShakeVariableAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ASSIGN) throw ParserError("Expecting '='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariableAddAssignment(variable: ShakeValuedNode): ShakeVariableAddAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ADD_ASSIGN) throw ParserError("Expecting '+='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableAddAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariableSubAssignment(variable: ShakeValuedNode): ShakeVariableSubAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.SUB_ASSIGN) throw ParserError("Expecting '-='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableSubAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariableMulAssignment(variable: ShakeValuedNode): ShakeVariableMulAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MUL_ASSIGN) throw ParserError("Expecting '*='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableMulAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariableDivAssignment(variable: ShakeValuedNode): ShakeVariableDivAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DIV_ASSIGN) throw ParserError("Expecting '/='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableDivAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariableModAssignment(variable: ShakeValuedNode): ShakeVariableModAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MOD_ASSIGN) throw ParserError("Expecting '%='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariableModAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariablePowAssignment(variable: ShakeValuedNode): ShakeVariablePowAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.POW_ASSIGN) throw ParserError("Expecting '**='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(expectValue())
        return ShakeVariablePowAssignmentNode(map, variable, value, operatorPosition)
    }

    fun expectVariableIncrease(variable: ShakeValuedNode): ShakeVariableIncreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.INCR) throw ParserError("Expecting '++'")
        return ShakeVariableIncreaseNode(map, variable, input.actualStart)
    }

    fun expectVariableDecrease(variable: ShakeValuedNode): ShakeVariableDecreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DECR) throw ParserError("Expecting '--'")
        return ShakeVariableDecreaseNode(map, variable, input.actualStart)
    }

    fun expectCStyleDeclaration(
        access: ShakeAccessDescriber,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean
    ): ShakeFileChildNode {
        val t = input.nextType()
        val declarationNode =
            if (t == ShakeTokenType.KEYWORD_DYNAMIC) ShakeVariableType.DYNAMIC
            else (if (t == ShakeTokenType.KEYWORD_BYTE) ShakeVariableType.BYTE
            else if (t == ShakeTokenType.KEYWORD_SHORT) ShakeVariableType.SHORT
            else if (t == ShakeTokenType.KEYWORD_INT) ShakeVariableType.INTEGER
            else if (t == ShakeTokenType.KEYWORD_LONG) ShakeVariableType.LONG
            else if (t == ShakeTokenType.KEYWORD_FLOAT) ShakeVariableType.FLOAT
            else if (t == ShakeTokenType.KEYWORD_DOUBLE) ShakeVariableType.DOUBLE
            else if (t == ShakeTokenType.KEYWORD_BOOLEAN) ShakeVariableType.BOOLEAN
            else if (t == ShakeTokenType.KEYWORD_CHAR) ShakeVariableType.CHAR
            else if (t == ShakeTokenType.KEYWORD_VOID) ShakeVariableType.VOID
            else if (t == ShakeTokenType.IDENTIFIER) ShakeVariableType.OBJECT else null)!!
        return expectCStyleDeclaration(declarationNode, access, isStatic, isFinal, isAbstract)
    }

    fun expectCStyleDeclaration(
        type: ShakeVariableType,
        access: ShakeAccessDescriber,
        isStatic: Boolean,
        isFinal: Boolean,
        isAbstract: Boolean
    ): ShakeFileChildNode {
        // TODO error on void variable type
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER
        ) throw ParserError("Expecting identifier")
        val identifier = input.nextValue() ?: throw ParserError("Expecting identifier to contain value")
        val hasNext = input.skipIgnorable().hasNext()
        return if (hasNext && input.peekType() == ShakeTokenType.ASSIGN) {
            input.skip()
            ShakeVariableDeclarationNode(map, identifier, type, expectValue(), access, isStatic, isFinal)
        } else if (hasNext && input.peekType() == ShakeTokenType.LPAREN) expectCStyleFunctionDeclaration(
            type,
            identifier,
            access,
            isStatic,
            isFinal,
            isAbstract
        ) else ShakeVariableDeclarationNode(map, input.actualValue!!, type, null, access, isStatic, isFinal)
    }

    // ****************************************************************************
    // Loops & If
    fun expectForLoop(): ShakeForNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_FOR) throw ParserError("Expecting for keyword")
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        val declaration = expectStatement() // TODO check if it is a statement
        expectSemicolon()
        val condition = expectValue()
        expectSemicolon()
        val round = expectStatement()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
        val body = expectParseBodyStatement()
        return ShakeForNode(map, body, declaration, expectNotNull(condition), expectNotNull(round)) // TODO check if it is a statement
    }

    fun expectDoWhileLoop(): ShakeDoWhileNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_DO) throw ParserError("Expecting do keyword")
        val body = expectParseBodyStatement()
        skipSeparators()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = expectParseConditionStatement()
        return ShakeDoWhileNode(map, body, condition)
    }

    fun expectWhileLoop(): ShakeWhileNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = expectParseConditionStatement()
        val body = expectParseBodyStatement()
        return ShakeWhileNode(map, body, condition)
    }

    fun expectIfStatement(): ShakeIfNode {
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

    fun expectParseConditionStatement(): ShakeValuedNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError(
            "Expecting '('"
        )
        val condition = expectLogicalOr()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError(
            "Expecting ')'"
        )
        return condition
    }

    fun expectParseBodyStatement(): ShakeBlockNode {
        skipSeparators()
        return if (input.peekType() == ShakeTokenType.LCURL) {
            input.skip()
            val list = mutableListOf<ShakeStatementNode>()
            skipSeparators()
            while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
                list.add(expectStatement())
                skipSeparators()
            }
            if (!input.hasNext() || input.nextType() != ShakeTokenType.RCURL) throw ParserError(
                "Expecting '}'"
            )
            ShakeBlockNode(map, list)
        } else {
            ShakeBlockNode(
                map,
                arrayOf(expectNotNull(expectStatement()))
            )
        }
    }

    // ****************************************************************************
    // Statements
    // (Factor)
    fun expectFactor(): ShakeValuedNode {
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
            return ShakeAddNode(map, 0, expectFactor(), input.position)
        }
        if (token == ShakeTokenType.SUB) {
            input.skip()
            return ShakeSubNode(map, 0, expectFactor(), input.position)
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
    fun expectCast(): ShakeValuedNode {
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
    fun expectExpr(): ShakeValuedNode {
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

    fun expectTerm(): ShakeValuedNode {
        var result = expectPow()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType()
                .also { tmpType = it } == ShakeTokenType.MUL || tmpType == ShakeTokenType.DIV || tmpType == ShakeTokenType.MOD)
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

    fun expectPow(): ShakeValuedNode {
        var result = expectCast()
        while (input.hasNext() && input.peekType() == ShakeTokenType.POW) {
            input.skip()
            val pos = input.actualStart
            result = ShakePowNode(map, result, expectCast(), pos)
        }
        return result
    }

    // (Logical)
    fun expectLogicalOr(): ShakeValuedNode {
        var result = expectLogicalXOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_OR) {
            input.skip()
            result = ShakeLogicalOrNode(map, result, expectLogicalXOr())
        }
        return result
    }

    fun expectLogicalXOr(): ShakeValuedNode {
        var result = expectLogicalAnd()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_XOR) {
            input.skip()
            result = ShakeLogicalXOrNode(map, result, expectLogicalAnd())
        }
        return result
    }

    fun expectLogicalAnd(): ShakeValuedNode {
        var result = expectCompare()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_AND) {
            input.skip()
            result = ShakeLogicalAndNode(map, result, expectCompare())
        }
        return result
    }

    fun expectCompare(): ShakeValuedNode {
        var left = expectExpr()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext()
            && (input.peekType().also { tmpType = it } == ShakeTokenType.EQ_EQUALS ||
                    tmpType == ShakeTokenType.BIGGER_EQUALS
                    || tmpType == ShakeTokenType.SMALLER_EQUALS
                    || tmpType == ShakeTokenType.BIGGER
                    || tmpType == ShakeTokenType.SMALLER)) {

            input.skip()
            left = when (tmpType) {
                ShakeTokenType.EQ_EQUALS -> return ShakeLogicalEqEqualsNode(map, left, expectLogicalOr())
                ShakeTokenType.BIGGER_EQUALS -> ShakeLogicalBiggerEqualsNode(map, left, expectLogicalOr())
                ShakeTokenType.SMALLER_EQUALS -> ShakeLogicalSmallerEqualsNode(map, left, expectLogicalOr())
                ShakeTokenType.BIGGER -> ShakeLogicalBiggerNode(map, left, expectLogicalOr())
                else -> ShakeLogicalSmallerNode(map, left, expectLogicalOr())
            }

        }
        return left
    }


    fun <T> expectNotNull(v: T?): T {
        if(v == null) throw ParserError("Expecting value")
        return v
    }

    fun expectNamespace(): ShakeNamespaceNode {
        val name = mutableListOf<String>()
        do {
            if (!input.skipIgnorable().hasNext() && input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
            name.add(input.actualValue ?: throw ParserError("Expecting identifier to have value"))
        } while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT)
        return ShakeNamespaceNode(map, name.toTypedArray())
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

enum class DeclarationScope {
    FILE,
    CLASS,
    OBJECT,
    INTERFACE,
    ENUM,
    BLOCK,
}