package io.github.shakelang.shake.parser

import io.github.shakelang.parseutils.CompilerError
import io.github.shakelang.parseutils.characters.Characters.parseString
import io.github.shakelang.parseutils.characters.position.Position
import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import io.github.shakelang.shake.lexer.token.ShakeTokenType
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.variables.VariableUsageNode
import io.github.shakelang.shake.parser.node.objects.ClassConstructionNode
import io.github.shakelang.shake.parser.node.functions.FunctionCallNode
import io.github.shakelang.shake.parser.node.objects.ClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.VariableDeclarationNode
import io.github.shakelang.shake.parser.node.functions.FunctionDeclarationNode
import io.github.shakelang.shake.parser.node.objects.ConstructorDeclarationNode
import io.github.shakelang.shake.parser.node.functions.FunctionArgumentNode
import io.github.shakelang.shake.parser.node.variables.VariableAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariableAddAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariableSubAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariableMulAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariableDivAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariableModAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariablePowAssignmentNode
import io.github.shakelang.shake.parser.node.variables.VariableIncreaseNode
import io.github.shakelang.shake.parser.node.variables.VariableDecreaseNode
import io.github.shakelang.shake.parser.node.loops.ForNode
import io.github.shakelang.shake.parser.node.loops.DoWhileNode
import io.github.shakelang.shake.parser.node.loops.WhileNode
import io.github.shakelang.shake.parser.node.logical.LogicalTrueNode
import io.github.shakelang.shake.parser.node.logical.LogicalFalseNode
import io.github.shakelang.shake.parser.node.factor.IntegerNode
import io.github.shakelang.shake.parser.node.factor.StringNode
import io.github.shakelang.shake.parser.node.factor.CharacterNode
import io.github.shakelang.shake.parser.node.CastNode.CastTarget
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.DoubleNode
import io.github.shakelang.shake.parser.node.functions.ReturnNode
import io.github.shakelang.shake.parser.node.logical.LogicalOrNode
import io.github.shakelang.shake.parser.node.logical.LogicalXOrNode
import io.github.shakelang.shake.parser.node.logical.LogicalAndNode
import io.github.shakelang.shake.parser.node.logical.LogicalEqEqualsNode
import io.github.shakelang.shake.parser.node.logical.LogicalBiggerEqualsNode
import io.github.shakelang.shake.parser.node.logical.LogicalSmallerEqualsNode
import io.github.shakelang.shake.parser.node.logical.LogicalBiggerNode
import io.github.shakelang.shake.parser.node.logical.LogicalSmallerNode

class Parser(val input: ShakeTokenInputStream) {
    private val map: PositionMap = input.map
    fun parse(): Tree {
        if (!input.hasNext()) return Tree(map, arrayOf())
        val result = prog()
        if (input.hasNext()) throw ParserError("Input did not end")
        return result
    }

    // ****************************************************************************
    // Basic Program
    private fun prog(): Tree {
        val nodes: MutableList<Node> = ArrayList()
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
        return Tree(map, nodes)
    }

    private fun operation(): Node? {
        val token = input.peekType()
        if (token == ShakeTokenType.KEYWORD_WHILE) return whileLoop()
        if (token == ShakeTokenType.KEYWORD_DO) return doWhileLoop()
        if (token == ShakeTokenType.KEYWORD_FOR) return forLoop()
        if (token == ShakeTokenType.KEYWORD_IF) return ifStatement()
        if (token == ShakeTokenType.KEYWORD_RETURN) return returnStatement()
        return if (token == ShakeTokenType.KEYWORD_IMPORT) parseImport() else valuedOperation()
    }

    private fun valuedOperation(): ValuedNode? {
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
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ValuedNode {
        val input = input
        return when (input.peekType()) {
            ShakeTokenType.KEYWORD_PUBLIC -> {
                input.skip()
                parseDeclaration(AccessDescriber.PUBLIC, isInClass, isStatic, isFinal)
            }
            ShakeTokenType.KEYWORD_PROTECTED -> {
                input.skip()
                parseDeclaration(AccessDescriber.PROTECTED, isInClass, isStatic, isFinal)
            }
            ShakeTokenType.KEYWORD_PRIVATE -> {
                input.skip()
                parseDeclaration(AccessDescriber.PRIVATE, isInClass, isStatic, isFinal)
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

    private fun parseDeclaration(isInClass: Boolean = false): ValuedNode {
        return parseDeclaration(AccessDescriber.PACKAGE, isInClass, isStatic = false, isFinal = false)
    }

    private fun parseIdentifier(parent: ValuedNode?): ValuedNode {
        if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = IdentifierNode(map, parent, expectNotNull(input.actualValue), input.actualStart)
        var ret: ValuedNode? = null

        // Assignments
        if (input.hasNext()) {
            when (input.skipIgnorable().peekType()) {
                ShakeTokenType.LPAREN -> ret = functionCall(identifierNode)
                ShakeTokenType.ASSIGN -> ret = varAssignment(identifierNode)
                ShakeTokenType.IDENTIFIER -> ret = this.cStyleDeclaration(
                    VariableType(identifierNode),
                    AccessDescriber.PRIVATE, isInClass = false, isStatic = false, isFinal = false
                )
                ShakeTokenType.ADD_ASSIGN -> ret = varAddAssignment(identifierNode)
                ShakeTokenType.SUB_ASSIGN -> ret = varSubAssignment(identifierNode)
                ShakeTokenType.MUL_ASSIGN -> ret = varMulAssignment(identifierNode)
                ShakeTokenType.DIV_ASSIGN -> ret = varDivAssignment(identifierNode)
                ShakeTokenType.MOD_ASSIGN -> ret = varModAssignment(identifierNode)
                ShakeTokenType.POW_ASSIGN -> ret = varPowAssignment(identifierNode)
                ShakeTokenType.INCR -> ret = varIncrease(identifierNode)
                ShakeTokenType.DECR -> ret = varDecrease(identifierNode)
            }
            if (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT) {
                input.skip()
                input.skipIgnorable()
                return parseIdentifier(ret ?: VariableUsageNode(map, identifierNode))
            }
            if (ret != null) return ret
        }
        return VariableUsageNode(map, identifierNode)
    }

    private fun parseClassConstruction(): ClassConstructionNode {
        input.skip()
        val newKeywordPosition = input.actualStart
        input.skipIgnorable()
        val start = input.actualStart
        val node = parseIdentifier(null) as? FunctionCallNode
            ?: throw ParserError(
                "Expecting a call after keyword new",
                start, input.actualEnd
            )
        return ClassConstructionNode(
            map, node.function, node.args,
            newKeywordPosition
        )
    }

    // ****************************************************************************
    // Imports
    private fun parseImport(): ImportNode {
        if (!input.hasNext() || input.peekType() != ShakeTokenType.KEYWORD_IMPORT) throw ParserError("Expecting import keyword")
        val list = mutableListOf<String>()
        do {
            input.skip()
            if (input.skipIgnorable().nextType() == ShakeTokenType.MUL) {
                list.add(ImportNode.EVERYTHING)
                break
            }
            if (input.actualType != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
            list.add(expectNotNull(input.actualValue))
        } while (input.hasNext() && input.skipIgnorable().peekType() == ShakeTokenType.DOT)
        return ImportNode(map, list.toTypedArray())
    }

    // ****************************************************************************
    // Classes
    private fun classDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ClassDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_CLASS) throw ParserError("Expecting class keyword")
        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue()
        val fields: MutableList<VariableDeclarationNode> = ArrayList()
        val methods: MutableList<FunctionDeclarationNode> = ArrayList()
        val classes: MutableList<ClassDeclarationNode> = ArrayList()
        val constructors: MutableList<ConstructorDeclarationNode> = ArrayList()

        // TODO: extends, implements
        if (input.nextType() != ShakeTokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.hasNext() && input.peekType() != ShakeTokenType.RCURL) {
            skipSeparators()
            when (val node = parseDeclaration(true)) {
                is ClassDeclarationNode -> classes.add(node)
                is FunctionDeclarationNode -> methods.add(node)
                is VariableDeclarationNode -> fields.add(node)
                is ConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != ShakeTokenType.RCURL) throw ParserError("Expecting class-body to end")
        return ClassDeclarationNode(
            map, name!!, fields, methods, classes, constructors, access, isInClass,
            isStatic, isFinal
        )
    }

    // ****************************************************************************
    // Functions
    private fun functionDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): FunctionDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_FUNCTION) throw ParserError("Expecting function keyword")
        if (!input.hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue()!!
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return FunctionDeclarationNode(map, name, body, args, access, isInClass, isStatic, isFinal)
    }

    private fun cStyleFunctionDeclaration(
        type: VariableType, identifier: String?, access: AccessDescriber,
        isInClass: Boolean, isStatic: Boolean, isFinal: Boolean
    ): FunctionDeclarationNode {
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return FunctionDeclarationNode(
            map, identifier!!, body, args, type,
            access, isInClass, isStatic, isFinal
        )
    }

    private fun parseFunctionArguments(): Array<FunctionArgumentNode> {
        val args = ArrayList<FunctionArgumentNode>()
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

    private fun functionCall(function: ValuedNode): FunctionCallNode {
        val args: MutableList<ValuedNode> = ArrayList()
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
        return FunctionCallNode(map, function, args.toTypedArray())
    }

    private fun parseArgument(): FunctionArgumentNode {
        val next = input.nextType()
        input.skipIgnorable()
        var peek: ShakeTokenType
        if (next == ShakeTokenType.IDENTIFIER && (!input.hasNext() || input.peekType()
                .also { peek = it } != ShakeTokenType.IDENTIFIER && (peek != ShakeTokenType.DOT))
        ) return FunctionArgumentNode(map, input.actualValue!!)
        val type: VariableType
        when (next) {
            ShakeTokenType.KEYWORD_DYNAMIC -> type = VariableType.DYNAMIC
            ShakeTokenType.KEYWORD_BOOLEAN -> type = VariableType.BOOLEAN
            ShakeTokenType.KEYWORD_CHAR -> type = VariableType.CHAR
            ShakeTokenType.KEYWORD_BYTE -> type = VariableType.BYTE
            ShakeTokenType.KEYWORD_SHORT -> type = VariableType.SHORT
            ShakeTokenType.KEYWORD_INT -> type = VariableType.INTEGER
            ShakeTokenType.KEYWORD_LONG -> type = VariableType.LONG
            ShakeTokenType.KEYWORD_FLOAT -> type = VariableType.FLOAT
            ShakeTokenType.KEYWORD_DOUBLE -> type = VariableType.DOUBLE
            ShakeTokenType.IDENTIFIER -> {
                var node = IdentifierNode(
                    map, input.actualValue!!,
                    input.position
                )
                while (input.peekType() == ShakeTokenType.DOT) {
                    input.skip()
                    input.skipIgnorable()
                    if (input.nextType() != ShakeTokenType.IDENTIFIER) throw ParserError("Expecting identifier")
                    node = IdentifierNode(map, input.actualValue!!, input.actualStart)
                }
                type = VariableType(node)
            }
            else -> throw ParserError("Unknown variable-type token: " + next.name)
        }
        return if (input.hasNext() && input.peekType() == ShakeTokenType.IDENTIFIER) {
            val identifier = input.nextValue()
            input.skipIgnorable()
            FunctionArgumentNode(map, identifier!!, type)
        } else throw ParserError("Expecting identifier")
    }

    private fun returnStatement(): ReturnNode {
        input.skip()
        return ReturnNode(map, expectNotNull(valuedOperation()))
    }

    private fun constructorDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ConstructorDeclarationNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_CONSTRUCTOR) throw ParserError("Expecting function keyword")
        if (!isInClass) throw ParserError("A constructor must be inside of a class")
        if (isFinal) throw ParserError("A constructor must not be final")
        if (isStatic) throw ParserError("A constructor must not be static")
        val name = if (input.skipIgnorable().peekType() == ShakeTokenType.IDENTIFIER) input.nextValue() else null
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return ConstructorDeclarationNode(map, name, body, args, access)
    }

    // ****************************************************************************
    // Variables
    private fun varAssignment(variable: ValuedNode): VariableAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ASSIGN) throw ParserError("Expecting '='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariableAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varAddAssignment(variable: ValuedNode): VariableAddAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.ADD_ASSIGN) throw ParserError("Expecting '+='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariableAddAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varSubAssignment(variable: ValuedNode): VariableSubAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.SUB_ASSIGN) throw ParserError("Expecting '-='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariableSubAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varMulAssignment(variable: ValuedNode): VariableMulAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MUL_ASSIGN) throw ParserError("Expecting '*='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariableMulAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varDivAssignment(variable: ValuedNode): VariableDivAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DIV_ASSIGN) throw ParserError("Expecting '/='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariableDivAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varModAssignment(variable: ValuedNode): VariableModAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.MOD_ASSIGN) throw ParserError("Expecting '%='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariableModAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varPowAssignment(variable: ValuedNode): VariablePowAssignmentNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.POW_ASSIGN) throw ParserError("Expecting '**='")
        val operatorPosition = input.actualStart
        val value = expectNotNull(operation())
        return VariablePowAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varIncrease(variable: ValuedNode): VariableIncreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.INCR) throw ParserError("Expecting '++'")
        return VariableIncreaseNode(map, variable, input.actualStart)
    }

    private fun varDecrease(variable: ValuedNode): VariableDecreaseNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.DECR) throw ParserError("Expecting '--'")
        return VariableDecreaseNode(map, variable, input.actualStart)
    }

    private fun varDeclaration1(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): VariableDeclarationNode {
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
            VariableDeclarationNode(
                map, identifier, VariableType.DYNAMIC,
                varAssignment(IdentifierNode(map, identifier, pos)),
                access, isInClass, isStatic, final
            )
        } else {
            VariableDeclarationNode(
                map,
                identifier,
                VariableType.DYNAMIC,
                null,
                access,
                isInClass,
                isStatic,
                final
            )
        }
    }

    private fun cStyleDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ValuedNode {
        val t = input.nextType()
        val declarationNode =
            if (t == ShakeTokenType.KEYWORD_DYNAMIC) VariableType.DYNAMIC else (if (t == ShakeTokenType.KEYWORD_BYTE) VariableType.BYTE else if (t == ShakeTokenType.KEYWORD_SHORT) VariableType.SHORT else if (t == ShakeTokenType.KEYWORD_INT) VariableType.INTEGER else if (t == ShakeTokenType.KEYWORD_LONG) VariableType.LONG else if (t == ShakeTokenType.KEYWORD_FLOAT) VariableType.FLOAT else if (t == ShakeTokenType.KEYWORD_DOUBLE) VariableType.DOUBLE else if (t == ShakeTokenType.KEYWORD_BOOLEAN) VariableType.BOOLEAN else if (t == ShakeTokenType.KEYWORD_CHAR) VariableType.CHAR else if (t == ShakeTokenType.KEYWORD_VOID) VariableType.VOID else if (t == ShakeTokenType.IDENTIFIER) VariableType.OBJECT else null)!!
        return cStyleDeclaration(declarationNode, access, isInClass, isStatic, isFinal)
    }

    private fun cStyleDeclaration(
        type: VariableType, access: AccessDescriber, isInClass: Boolean, isStatic: Boolean,
        isFinal: Boolean
    ): ValuedNode {

        // TODO error on void variable type
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != ShakeTokenType.IDENTIFIER
        ) throw ParserError("Expecting identifier")
        val identifier = input.nextValue()
        val position = input.actualStart
        val hasNext = input.skipIgnorable().hasNext()
        return if (hasNext && input.peekType() == ShakeTokenType.ASSIGN) {
            VariableDeclarationNode(
                map, identifier!!, type,
                varAssignment(IdentifierNode(map, identifier, position)), access, isInClass, isStatic, isFinal
            )
        } else if (hasNext && input.peekType() == ShakeTokenType.LPAREN) cStyleFunctionDeclaration(
            type,
            identifier,
            access,
            isInClass,
            isStatic,
            isFinal
        ) else VariableDeclarationNode(
            map, input.actualValue!!, type, null, access, isInClass,
            isStatic, isFinal
        )
    }

    // ****************************************************************************
    // Loops & If
    private fun forLoop(): Node {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_FOR) throw ParserError("Expecting for keyword")
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError("Expecting '('")
        val declaration = operation()
        expectSemicolon()
        val condition = valuedOperation()
        expectSemicolon()
        val round = operation()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
        val body = parseBodyStatement()
        return ForNode(map, body, declaration!!, expectNotNull(condition), expectNotNull(round))
    }

    private fun doWhileLoop(): Node {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_DO) throw ParserError("Expecting do keyword")
        val body = parseBodyStatement()
        skipSeparators()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = parseConditionStatement()
        return DoWhileNode(map, body, condition)
    }

    private fun whileLoop(): Node {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = parseConditionStatement()
        val body = parseBodyStatement()
        return WhileNode(map, body, condition)
    }

    private fun ifStatement(): Node {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.KEYWORD_IF) throw ParserError("Expecting if keyword")
        val condition = parseConditionStatement()
        val body = parseBodyStatement()
        val separator = skipSeparators() > 0
        if (input.hasNext() && input.peekType() == ShakeTokenType.KEYWORD_ELSE) {
            if (!separator) throw ParserError("Awaited separator at this point")
            input.skip()
            val elseBody = parseBodyStatement()
            return IfNode(map, body, elseBody, condition)
        }
        return IfNode(map, body, condition)
    }

    private fun parseConditionStatement(): ValuedNode {
        if (!input.hasNext() || input.nextType() != ShakeTokenType.LPAREN) throw ParserError(
            "Expecting '('"
        )
        val condition = logicalOr()
        if (!input.hasNext() || input.nextType() != ShakeTokenType.RPAREN) throw ParserError(
            "Expecting ')'"
        )
        return condition
    }

    private fun parseBodyStatement(): Tree {
        skipSeparators()
        return if (input.peekType() == ShakeTokenType.LCURL) {
            input.skip()
            val body = prog()
            if (!input.hasNext() || input.nextType() != ShakeTokenType.RCURL) throw ParserError(
                "Expecting '}'"
            )
            body
        } else {
            Tree(
                map,
                arrayOf(expectNotNull(operation()))
            )
        }
    }

    // ****************************************************************************
    // Statements
    // (Factor)
    private fun factor(): ValuedNode {
        val token = input.peekType()
        if (token == ShakeTokenType.LPAREN) {
            input.skip()
            val result = logicalOr()
            if (input.nextType() != ShakeTokenType.RPAREN) throw ParserError("Expecting ')'")
            return result
        }
        if (token == ShakeTokenType.KEYWORD_TRUE) {
            input.skip()
            return LogicalTrueNode(map)
        }
        if (token == ShakeTokenType.KEYWORD_FALSE) {
            input.skip()
            return LogicalFalseNode(map)
        }
        if (token == ShakeTokenType.INTEGER) {
            input.skip()
            return IntegerNode(map, input.actualValue!!.toInt())
        }
        if (token == ShakeTokenType.DOUBLE) {
            input.skip()
            return DoubleNode(map, input.actualValue!!.toDouble())
        }
        if (token == ShakeTokenType.IDENTIFIER) {
            return parseIdentifier(null)
        }
        if (token == ShakeTokenType.KEYWORD_NEW) {
            return parseClassConstruction()
        }
        if (token == ShakeTokenType.ADD) {
            input.skip()
            return AddNode(map, 0, factor(), input.position)
        }
        if (token == ShakeTokenType.SUB) {
            input.skip()
            return SubNode(map, 0, factor(), input.position)
        }
        if (token == ShakeTokenType.STRING) {
            input.skip()
            return StringNode(map, parseString(input.actualValue!!))
        }
        if (token == ShakeTokenType.CHARACTER) {
            input.skip()
            return CharacterNode(map, parseString(input.actualValue!!)[0])
        }
        throw ParserError(input.toString())
    }

    // Casting
    private fun cast(): ValuedNode {
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
                    var node: IdentifierNode? = null
                    do {
                        if (node != null) input.skip()
                        if (!input.skipIgnorable()
                                .hasNext() && input.nextType() != ShakeTokenType.IDENTIFIER
                        ) throw ParserError("Expecting identifier")
                        node = IdentifierNode(map, node, input.actualValue!!, input.actualStart)
                    } while (input.skipIgnorable().hasNext() && input.peekType() == ShakeTokenType.DOT)
                    target = CastTarget(node)
                }
                else -> throw ParserError("Expecting cast-target")
            }
            result = CastNode(map, result, target)
        }
        return result
    }

    // (Calculations)
    private fun expr(): ValuedNode {
        var result = term()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType().also { tmpType = it } == ShakeTokenType.ADD || tmpType == ShakeTokenType.SUB)
        ) {
            input.skip()
            val pos = input.actualStart
            result = if (tmpType == ShakeTokenType.ADD) {
                AddNode(map, result, term(), pos)
            } else {
                SubNode(map, result, term(), pos)
            }
        }
        return result
    }

    private fun term(): ValuedNode {
        var result = pow()
        var tmpType: ShakeTokenType = ShakeTokenType.NONE
        while (input.hasNext() &&
            (input.peekType()
                .also { tmpType = it } == ShakeTokenType.MUL || tmpType == ShakeTokenType.DIV || tmpType == ShakeTokenType.MOD)
        ) {
            input.skip()
            val pos = input.actualStart
            result = when (tmpType) {
                ShakeTokenType.MUL -> MulNode(map, result, pow(), pos)
                ShakeTokenType.DIV -> DivNode(map, result, pow(), pos)
                else -> ModNode(map, result, pow(), pos)
            }
        }
        return result
    }

    private fun pow(): ValuedNode {
        var result = cast()
        while (input.hasNext() && input.peekType() == ShakeTokenType.POW) {
            input.skip()
            val pos = input.actualStart
            result = PowNode(map, result, cast(), pos)
        }
        return result
    }

    // (Logical)
    private fun logicalOr(): ValuedNode {
        var result = logicalXOr()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_OR) {
            input.skip()
            result = LogicalOrNode(map, result, logicalXOr())
        }
        return result
    }

    private fun logicalXOr(): ValuedNode {
        var result = logicalAnd()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_XOR) {
            input.skip()
            result = LogicalXOrNode(map, result, logicalAnd())
        }
        return result
    }

    private fun logicalAnd(): ValuedNode {
        var result = compare()
        while (input.hasNext() && input.peekType() == ShakeTokenType.LOGICAL_AND) {
            input.skip()
            result = LogicalAndNode(map, result, compare())
        }
        return result
    }

    private fun compare(): ValuedNode {
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
                ShakeTokenType.EQ_EQUALS -> return LogicalEqEqualsNode(map, left, logicalOr())
                ShakeTokenType.BIGGER_EQUALS -> LogicalBiggerEqualsNode(map, left, logicalOr())
                ShakeTokenType.SMALLER_EQUALS -> LogicalSmallerEqualsNode(map, left, logicalOr())
                ShakeTokenType.BIGGER -> LogicalBiggerNode(map, left, logicalOr())
                else -> LogicalSmallerNode(map, left, logicalOr())
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