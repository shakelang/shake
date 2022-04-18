package io.github.shakelang.shake.parser

import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.Characters.parseString
import io.github.shakelang.parseutils.characters.position.Position
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import io.github.shakelang.shake.lexer.token.ShakeTokenType
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.variables.ShakeVariableUsageNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionArgumentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableAddAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableSubAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableMulAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDivAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableModAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariablePowAssignmentNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableIncreaseNode
import io.github.shakelang.shake.parser.node.variables.ShakeVariableDecreaseNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalTrueNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalFalseNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.factor.ShakeStringNode
import io.github.shakelang.shake.parser.node.factor.ShakeCharacterNode
import io.github.shakelang.shake.parser.node.ShakeCastNode.CastTarget
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.functions.ShakeReturnNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalOrNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalXOrNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalAndNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalEqEqualsNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalBiggerEqualsNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalSmallerEqualsNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalBiggerNode
import io.github.shakelang.shake.parser.node.logical.ShakeLogicalSmallerNode

class ShakeParser(val input: ShakeTokenInputStream) {
    private val map: PositionMap = input.map
    fun parse(): ShakeTree {
        if (!input.hasNext()) return ShakeTree(map, arrayOf())
        val result = prog()
        if (input.hasNext()) throw ParserError("Input did not end")
        return result
    }

    // ****************************************************************************
    // Basic Program
    private fun prog(): ShakeTree {
        val nodes: MutableList<ShakeNode> = ArrayList()
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
                val result = operation()
                if (result != null) nodes.add(result)
            }

            // if(this.skipSeparators() > 0) separator = true;
            skipSeparators()
        }
        return ShakeTree(map, nodes)
    }

    private fun operation(): ShakeNode? {
        val token = input.peekType()
        if (token == ShakeTokenType.KEYWORD_WHILE) return whileLoop()
        if (token == ShakeTokenType.KEYWORD_DO) return doWhileLoop()
        if (token == ShakeTokenType.KEYWORD_FOR) return forLoop()
        if (token == ShakeTokenType.KEYWORD_IF) return ifStatement()
        if (token == ShakeTokenType.KEYWORD_RETURN) return returnStatement()
        if (token == ShakeTokenType.KEYWORD_IMPORT) return  parseImport()
        return valuedOperation()
    }

    private fun valuedOperation(): ShakeValuedNode? {
        val token = input.peekType()
        if (token == ShakeTokenType.KEYWORD_FUNCTION
            || token == ShakeTokenType.KEYWORD_VAR
            || token == ShakeTokenType.KEYWORD_CONST
            || token == ShakeTokenType.KEYWORD_CLASS
            || token == ShakeTokenType.KEYWORD_PUBLIC
            || token == ShakeTokenType.KEYWORD_PROTECTED
            || token == ShakeTokenType.KEYWORD_PRIVATE
            || token == ShakeTokenType.KEYWORD_FINAL
            || token == ShakeTokenType.KEYWORD_STATIC
            || token == ShakeTokenType.KEYWORD_DYNAMIC
            || token == ShakeTokenType.KEYWORD_BYTE
            || token == ShakeTokenType.KEYWORD_SHORT
            || token == ShakeTokenType.KEYWORD_INT
            || token == ShakeTokenType.KEYWORD_LONG
            || token == ShakeTokenType.KEYWORD_FLOAT
            || token == ShakeTokenType.KEYWORD_DOUBLE
            || token == ShakeTokenType.KEYWORD_BOOLEAN
            || token == ShakeTokenType.KEYWORD_CHAR
            || token == ShakeTokenType.KEYWORD_VOID) return parseDeclaration()

        // Expression
        return if (token == ShakeTokenType.INTEGER
            || token == ShakeTokenType.DOUBLE
            || token == ShakeTokenType.KEYWORD_TRUE
            || token == ShakeTokenType.KEYWORD_FALSE
            || token == ShakeTokenType.IDENTIFIER
            || token == ShakeTokenType.KEYWORD_NEW
            || token == ShakeTokenType.STRING
            || token == ShakeTokenType.CHARACTER) logicalOr()
        else null
    }

    // ****************************************************************************
    // Utils
    private fun skipSeparators(): Int {
        var number = 0
        while (input.hasNext() && (input.peekType() == ShakeTokenType.SEMICOLON
                    || input.peekType() == ShakeTokenType.LINE_SEPARATOR)
        ) {
            number++
            input.skip()
        }
        return number
    }

    private fun expectSemicolon() {
        if (input.skipIgnorable().nextType() != ShakeTokenType.SEMICOLON)
            throw ParserError("Expecting semicolon at this point")
    }

    private fun parseDeclaration(
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeValuedNode {
        val input = input
        return when (input.peekType()) {
            ShakeTokenType.KEYWORD_PUBLIC -> {
                input.skip()
                parseDeclaration(ShakeAccessDescriber.PUBLIC, isInClass, isStatic, isFinal)
            }
            ShakeTokenType.KEYWORD_PROTECTED -> {
                input.skip()
                parseDeclaration(ShakeAccessDescriber.PROTECTED, isInClass, isStatic, isFinal)
            }
            ShakeTokenType.KEYWORD_PRIVATE -> {
                input.skip()
                parseDeclaration(ShakeAccessDescriber.PRIVATE, isInClass, isStatic, isFinal)
            }
            ShakeTokenType.KEYWORD_STATIC -> {
                if (!isInClass) throw ParserError("Static keyword is only for objects in classes")
                input.skip()
                parseDeclaration(access, isInClass = true, isStatic = true, isFinal = isFinal)
            }
            ShakeTokenType.KEYWORD_FINAL -> {
                input.skip()
                parseDeclaration(access, isInClass, isStatic, true)
            }
            ShakeTokenType.KEYWORD_FUNCTION -> functionDeclaration(access, isInClass, isStatic, isFinal)
            ShakeTokenType.KEYWORD_CLASS -> classDeclaration(access, isInClass, isStatic, isFinal)
            ShakeTokenType.KEYWORD_CONSTRUCTOR -> constructorDeclaration(access, isInClass, isStatic, isFinal)
            ShakeTokenType.KEYWORD_CONST, ShakeTokenType.KEYWORD_VAR -> varDeclaration1(access, isInClass, isStatic, isFinal)
            ShakeTokenType.KEYWORD_DYNAMIC, ShakeTokenType.KEYWORD_BOOLEAN, ShakeTokenType.KEYWORD_CHAR, ShakeTokenType.KEYWORD_BYTE, ShakeTokenType.KEYWORD_SHORT, ShakeTokenType.KEYWORD_INT, ShakeTokenType.KEYWORD_LONG, ShakeTokenType.KEYWORD_FLOAT, ShakeTokenType.KEYWORD_DOUBLE, ShakeTokenType.KEYWORD_VOID, ShakeTokenType.IDENTIFIER -> cStyleDeclaration(
                access,
                isInClass,
                isStatic,
                isFinal
            )
            else -> throw ParserError("Unexpected token (" + input.peekType() + ')')
        }
    }

    private fun parseDeclaration(isInClass: Boolean = false): ShakeValuedNode {
        return parseDeclaration(ShakeAccessDescriber.PACKAGE, isInClass, isStatic = false, isFinal = false)
    }

    private fun parseIdentifier(parent: ShakeValuedNode?): ShakeValuedNode {
        if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = ShakeIdentifierNode(map, parent, expectNotNull(input.actualValue), input.actualStart)
        var ret: ShakeValuedNode? = null

        // Assignments
        if (input.hasNext()) {
            when (input.skipIgnorable().peekType()) {
                ShakeTokenType.LPAREN -> ret = functionCall(identifierNode)
                ShakeTokenType.ASSIGN -> ret = varAssignment(identifierNode)
                ShakeTokenType.IDENTIFIER -> ret = this.cStyleDeclaration(
                    ShakeVariableType(identifierNode),
                    ShakeAccessDescriber.PRIVATE, isInClass = false, isStatic = false, isFinal = false
                )
                ShakeTokenType.ADD_ASSIGN -> ret = varAddAssignment(identifierNode)
                ShakeTokenType.SUB_ASSIGN -> ret = varSubAssignment(identifierNode)
                ShakeTokenType.MUL_ASSIGN -> ret = varMulAssignment(identifierNode)
                ShakeTokenType.DIV_ASSIGN -> ret = varDivAssignment(identifierNode)
                ShakeTokenType.MOD_ASSIGN -> ret = varModAssignment(identifierNode)
                ShakeTokenType.POW_ASSIGN -> ret = varPowAssignment(identifierNode)
                ShakeTokenType.INCR -> ret = varIncrease(identifierNode)
                ShakeTokenType.DECR -> ret = varDecrease(identifierNode)
                else -> {}
            }
            if (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT) {
                input.skip()
                input.skipIgnorable()
                return parseIdentifier(ret ?: ShakeVariableUsageNode(map, identifierNode))
            }
            if (ret != null) return ret
        }
        return ShakeVariableUsageNode(map, identifierNode)
    }

    private fun parseClassConstruction(): ShakeClassConstructionNode {
        input.skip()
        val newKeywordPosition = input.actualStart
        input.skipIgnorable()
        val start = input.actualStart
        val node = parseIdentifier(null) as? ShakeFunctionCallNode
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
    private fun parseImport(): ShakeImportNode {
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
    private fun classDeclaration(
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeClassDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_CLASS) throw ParserError("Expecting class keyword")
        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue()
        val fields: MutableList<ShakeVariableDeclarationNode> = ArrayList()
        val methods: MutableList<ShakeFunctionDeclarationNode> = ArrayList()
        val classes: MutableList<ShakeClassDeclarationNode> = ArrayList()
        val constructors: MutableList<ShakeConstructorDeclarationNode> = ArrayList()

        // TODO: extends, implements
        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            skipSeparators()
            when (val node = parseDeclaration(true)) {
                is ShakeClassDeclarationNode -> classes.add(node)
                is ShakeFunctionDeclarationNode -> methods.add(node)
                is ShakeVariableDeclarationNode -> fields.add(node)
                is ShakeConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting class-body to end")
        return ShakeClassDeclarationNode(
            map, name!!, fields, methods, classes, constructors, access, isInClass,
            isStatic, isFinal
        )
    }

    // ****************************************************************************
    // Functions
    private fun functionDeclaration(
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeFunctionDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_FUNCTION) throw ParserError("Expecting function keyword")
        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue()!!
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return ShakeFunctionDeclarationNode(map, name, body, args, access, isInClass, isStatic, isFinal)
    }

    private fun cStyleFunctionDeclaration(
        type: ShakeVariableType, identifier: String?, access: ShakeAccessDescriber,
        isInClass: Boolean, isStatic: Boolean, isFinal: Boolean
    ): ShakeFunctionDeclarationNode {
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return ShakeFunctionDeclarationNode(
            map, identifier!!, body, args, type,
            access, isInClass, isStatic, isFinal
        )
    }

    private fun parseFunctionArguments(): Array<ShakeFunctionArgumentNode> {
        val args = ArrayList<ShakeFunctionArgumentNode>()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        if (input.hasNext() && input.peekType() != ShakeTokenType.RPAREN) {
            args.add(parseArgument())
            while (input.hasNext() && input.peekType() == ShakeTokenType.COMMA) {
                input.skip()
                if (input.hasNext() && input.peekType() != ShakeTokenType.RPAREN) args.add(parseArgument()) else break
            }
        }
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError(
            "Expecting ')'"
        )
        return args.toTypedArray()
    }

    private fun functionCall(function: ShakeValuedNode): ShakeFunctionCallNode {
        val args: MutableList<ShakeValuedNode> = ArrayList()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        if (input.peekType() != ShakeTokenType.RPAREN) {
            args.add(expectNotNull(valuedOperation()))
            while (input.hasNext() && input.peekType() == ShakeTokenType.COMMA) {
                input.skip()
                val operation = valuedOperation()
                if (operation != null) args.add(operation) else break
            }
        }
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
        return ShakeFunctionCallNode(map, function, args.toTypedArray())
    }

    private fun parseArgument(): ShakeFunctionArgumentNode {
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

    private fun returnStatement(): ShakeReturnNode {
        input.skip()
        return ShakeReturnNode(map, expectNotNull(valuedOperation()))
    }

    private fun constructorDeclaration(
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeConstructorDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_CONSTRUCTOR) throw ParserError("Expecting function keyword")
        if (!isInClass) throw ParserError("A constructor must be inside of a class")
        if (isFinal) throw ParserError("A constructor must not be final")
        if (isStatic) throw ParserError("A constructor must not be static")
        val name = if (input.skipIgnorable().peekType() == ShakeTokenType.IDENTIFIER) input.nextValue() else null
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return ShakeConstructorDeclarationNode(map, name, body, args, access)
    }

    // ****************************************************************************
    // Variables
    private fun varAssignment(variable: ShakeValuedNode): ShakeVariableAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ASSIGN) throw ParserError("Expecting '='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariableAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varAddAssignment(variable: ShakeValuedNode): ShakeVariableAddAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ADD_ASSIGN) throw ParserError("Expecting '+='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariableAddAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varSubAssignment(variable: ShakeValuedNode): ShakeVariableSubAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.SUB_ASSIGN) throw ParserError("Expecting '-='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariableSubAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varMulAssignment(variable: ShakeValuedNode): ShakeVariableMulAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MUL_ASSIGN) throw ParserError("Expecting '*='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariableMulAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varDivAssignment(variable: ShakeValuedNode): ShakeVariableDivAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DIV_ASSIGN) throw ParserError("Expecting '/='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariableDivAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varModAssignment(variable: ShakeValuedNode): ShakeVariableModAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MOD_ASSIGN) throw ParserError("Expecting '%='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariableModAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varPowAssignment(variable: ShakeValuedNode): ShakeVariablePowAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.POW_ASSIGN) throw ParserError("Expecting '**='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(valuedOperation())
        return ShakeVariablePowAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varIncrease(variable: ShakeValuedNode): ShakeVariableIncreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.INCR) throw ParserError("Expecting '++'")
        return ShakeVariableIncreaseNode(map, variable, input.actualStart)
    }

    private fun varDecrease(variable: ShakeValuedNode): ShakeVariableDecreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DECR) throw ParserError("Expecting '--'")
        return ShakeVariableDecreaseNode(map, variable, input.actualStart)
    }

    private fun varDeclaration1(
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeVariableDeclarationNode {
        var final = isFinal
        if (!input.hasNext()) throw ParserError("Expecting var or const keyword")
        if (input.nextType() == ShakeTokenType.KEYWORD_CONST) {
            if (final) throw ParserError("A constant is always final, must not have \"final\" attribute!")
            final = true
        } else if (input.actualType != ShakeTokenType.KEYWORD_VAR) throw ParserError("Expecting var or const keyword")
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER
        ) throw ParserError("Expecting identifier")
        val identifier = expectNotNull(input.nextValue())
        val pos = input.actualStart
        return if (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.ASSIGN) {
            ShakeVariableDeclarationNode(
                map, identifier, ShakeVariableType.DYNAMIC,
                varAssignment(ShakeIdentifierNode(map, identifier, pos)),
                access, isInClass, isStatic, final
            )
        } else {
            ShakeVariableDeclarationNode(
                map,
                identifier,
                ShakeVariableType.DYNAMIC,
                null,
                access,
                isInClass,
                isStatic,
                final
            )
        }
    }

    private fun cStyleDeclaration(
        access: ShakeAccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ShakeValuedNode {
        val t = input.nextType()
        val declarationNode =
            if (t == ShakeTokenType.KEYWORD_DYNAMIC) ShakeVariableType.DYNAMIC else (if (t == ShakeTokenType.KEYWORD_BYTE) ShakeVariableType.BYTE else if (t == ShakeTokenType.KEYWORD_SHORT) ShakeVariableType.SHORT else if (t == ShakeTokenType.KEYWORD_INT) ShakeVariableType.INTEGER else if (t == ShakeTokenType.KEYWORD_LONG) ShakeVariableType.LONG else if (t == ShakeTokenType.KEYWORD_FLOAT) ShakeVariableType.FLOAT else if (t == ShakeTokenType.KEYWORD_DOUBLE) ShakeVariableType.DOUBLE else if (t == ShakeTokenType.KEYWORD_BOOLEAN) ShakeVariableType.BOOLEAN else if (t == ShakeTokenType.KEYWORD_CHAR) ShakeVariableType.CHAR else if (t == ShakeTokenType.KEYWORD_VOID) ShakeVariableType.VOID else if (t == ShakeTokenType.IDENTIFIER) ShakeVariableType.OBJECT else null)!!
        return cStyleDeclaration(declarationNode, access, isInClass, isStatic, isFinal)
    }

    private fun cStyleDeclaration(
        type: ShakeVariableType, access: ShakeAccessDescriber, isInClass: Boolean, isStatic: Boolean,
        isFinal: Boolean
    ): ShakeValuedNode {

        // TODO error on void variable type
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER
        ) throw ParserError("Expecting identifier")
        val identifier = input.nextValue()
        val position = input.actualStart
        val hasNext = input.skipIgnorable().hasNext()
        return if (hasNext && input.peekType() == ShakeTokenType.ASSIGN) {
            ShakeVariableDeclarationNode(
                map, identifier!!, type,
                varAssignment(ShakeIdentifierNode(map, identifier, position)), access, isInClass, isStatic, isFinal
            )
        } else if (hasNext && input.peekType() == ShakeTokenType.LPAREN) cStyleFunctionDeclaration(
            type,
            identifier,
            access,
            isInClass,
            isStatic,
            isFinal
        ) else ShakeVariableDeclarationNode(
            map, input.actualValue!!, type, null, access, isInClass,
            isStatic, isFinal
        )
    }

    // ****************************************************************************
    // Loops & If
    private fun forLoop(): ShakeForNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_FOR) throw ParserError("Expecting for keyword")
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        val declaration = operation() as ShakeStatementNode // TODO check if it is a statement
        expectSemicolon()
        val condition = valuedOperation()
        expectSemicolon()
        val round = operation()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
        val body = parseBodyStatement()
        return ShakeForNode(map, body, declaration, expectNotNull(condition), expectNotNull(round) as ShakeStatementNode) // TODO check if it is a statement
    }

    private fun doWhileLoop(): ShakeDoWhileNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_DO) throw ParserError("Expecting do keyword")
        val body = parseBodyStatement()
        skipSeparators()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = parseConditionStatement()
        return ShakeDoWhileNode(map, body, condition)
    }

    private fun whileLoop(): ShakeWhileNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = parseConditionStatement()
        val body = parseBodyStatement()
        return ShakeWhileNode(map, body, condition)
    }

    private fun ifStatement(): ShakeIfNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_IF) throw ParserError("Expecting if keyword")
        val condition = parseConditionStatement()
        val body = parseBodyStatement()
        val separator = skipSeparators() > 0
        if (input.hasNext() && input.peekType() == ShakeTokenType.KEYWORD_ELSE) {
            if (!separator) throw ParserError("Awaited separator at this point")
            input.skip()
            val elseBody = parseBodyStatement()
            return ShakeIfNode(map, body, elseBody, condition)
        }
        return ShakeIfNode(map, body, condition)
    }

    private fun parseConditionStatement(): ShakeValuedNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError(
            "Expecting '('"
        )
        val condition = logicalOr()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError(
            "Expecting ')'"
        )
        return condition
    }

    private fun parseBodyStatement(): ShakeTree {
        skipSeparators()
        return if (input.peekType() == ShakeTokenType.LCURL) {
            input.skip()
            val body = prog()
            if (!input.hasNext() || input.nextType() != ShakeTokenType.RCURL) throw ParserError(
                "Expecting '}'"
            )
            body
        } else {
            ShakeTree(
                map,
                arrayOf(expectNotNull(operation()))
            )
        }
    }

    // ****************************************************************************
    // Statements
    // (Factor)
    private fun factor(): ShakeValuedNode {
        val token = input.peekType()
        if (token == ShakeTokenType.LPAREN) {
            input.skip()
            val result = logicalOr()
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
            return parseIdentifier(null)
        }
        if (token == ShakeTokenType.KEYWORD_NEW) {
            return parseClassConstruction()
        }
        if (token == ShakeTokenType.ADD) {
            input.skip()
            return ShakeAddNode(map, 0, factor(), input.position)
        }
        if (token == ShakeTokenType.SUB) {
            input.skip()
            return ShakeSubNode(map, 0, factor(), input.position)
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
    private fun cast(): ShakeValuedNode {
        var result = factor()
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
                ShakeTokenType.IDENTIFIER -> {
                    var node: ShakeIdentifierNode? = null
                    do {
                        if (node != null) input.skip()
                        if (!input.skipIgnorable()
                                .hasNext() && input.nextType() != ShakeTokenType.IDENTIFIER
                        ) throw ParserError("Expecting identifier")
                        node = ShakeIdentifierNode(map, node, input.actualValue!!, input.actualStart)
                    } while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT)
                    target = CastTarget(node)
                }
                else -> throw ParserError("Expecting cast-target")
            }
            result = ShakeCastNode(map, result, target)
        }
        return result
    }

    // (Calculations)
    private fun expr(): ShakeValuedNode {
        var result = term()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType().also { tmpType = it } == ShakeTokenType.ADD || tmpType == ShakeTokenType.SUB)
        ) {
            input.skip()
            val pos = input.actualStart
            result = if (tmpType == ShakeTokenType.ADD) {
                ShakeAddNode(map, result, term(), pos)
            } else {
                ShakeSubNode(map, result, term(), pos)
            }
        }
        return result
    }

    private fun term(): ShakeValuedNode {
        var result = pow()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType()
                .also { tmpType = it } == ShakeTokenType.MUL || tmpType == ShakeTokenType.DIV || tmpType == ShakeTokenType.MOD)
        ) {
            input.skip()
            val pos = input.actualStart
            result = when (tmpType) {
                ShakeTokenType.MUL -> ShakeMulNode(map, result, pow(), pos)
                ShakeTokenType.DIV -> ShakeDivNode(map, result, pow(), pos)
                else -> ShakeModNode(map, result, pow(), pos)
            }
        }
        return result
    }

    private fun pow(): ShakeValuedNode {
        var result = cast()
        while (input.hasNext() && input.peekType() == ShakeTokenType.POW) {
            input.skip()
            val pos = input.actualStart
            result = ShakePowNode(map, result, cast(), pos)
        }
        return result
    }

    // (Logical)
    private fun logicalOr(): ShakeValuedNode {
        var result = logicalXOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_OR) {
            input.skip()
            result = ShakeLogicalOrNode(map, result, logicalXOr())
        }
        return result
    }

    private fun logicalXOr(): ShakeValuedNode {
        var result = logicalAnd()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_XOR) {
            input.skip()
            result = ShakeLogicalXOrNode(map, result, logicalAnd())
        }
        return result
    }

    private fun logicalAnd(): ShakeValuedNode {
        var result = compare()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_AND) {
            input.skip()
            result = ShakeLogicalAndNode(map, result, compare())
        }
        return result
    }

    private fun compare(): ShakeValuedNode {
        var left = expr()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext()
            && (input.peekType().also { tmpType = it } == ShakeTokenType.EQ_EQUALS ||
                    tmpType == ShakeTokenType.BIGGER_EQUALS
                    || tmpType == ShakeTokenType.SMALLER_EQUALS
                    || tmpType == ShakeTokenType.BIGGER
                    || tmpType == ShakeTokenType.SMALLER)) {

            input.skip()
            left = when (tmpType) {
                ShakeTokenType.EQ_EQUALS -> return ShakeLogicalEqEqualsNode(map, left, logicalOr())
                ShakeTokenType.BIGGER_EQUALS -> ShakeLogicalBiggerEqualsNode(map, left, logicalOr())
                ShakeTokenType.SMALLER_EQUALS -> ShakeLogicalSmallerEqualsNode(map, left, logicalOr())
                ShakeTokenType.BIGGER -> ShakeLogicalBiggerNode(map, left, logicalOr())
                else -> ShakeLogicalSmallerNode(map, left, logicalOr())
            }

        }
        return left
    }


    private fun <T> expectNotNull(v: T?): T {
        if(v == null) throw ParserError("Expecting value")
        return v
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