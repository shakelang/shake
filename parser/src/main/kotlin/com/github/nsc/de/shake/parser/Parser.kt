package com.github.nsc.de.shake.parser

import com.github.nsc.de.shake.lexer.token.TokenType.getName
import com.github.nsc.de.shake.util.Characters.parseString
import com.github.nsc.de.shake.lexer.token.TokenInputStream
import com.github.nsc.de.shake.lexer.token.TokenType
import com.github.nsc.de.shake.parser.node.*
import com.github.nsc.de.shake.parser.node.variables.VariableUsageNode
import com.github.nsc.de.shake.parser.node.objects.ClassConstructionNode
import com.github.nsc.de.shake.parser.node.functions.FunctionCallNode
import com.github.nsc.de.shake.parser.node.objects.ClassDeclarationNode
import com.github.nsc.de.shake.parser.node.variables.VariableDeclarationNode
import com.github.nsc.de.shake.parser.node.functions.FunctionDeclarationNode
import com.github.nsc.de.shake.parser.node.objects.ConstructorDeclarationNode
import com.github.nsc.de.shake.parser.node.functions.FunctionArgumentNode
import com.github.nsc.de.shake.parser.node.variables.VariableAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableAddAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableSubAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableMulAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableDivAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableModAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariablePowAssignmentNode
import com.github.nsc.de.shake.parser.node.variables.VariableIncreaseNode
import com.github.nsc.de.shake.parser.node.variables.VariableDecreaseNode
import com.github.nsc.de.shake.parser.node.loops.ForNode
import com.github.nsc.de.shake.parser.node.loops.DoWhileNode
import com.github.nsc.de.shake.parser.node.loops.WhileNode
import com.github.nsc.de.shake.parser.node.logical.LogicalTrueNode
import com.github.nsc.de.shake.parser.node.logical.LogicalFalseNode
import com.github.nsc.de.shake.parser.node.factor.IntegerNode
import com.github.nsc.de.shake.parser.node.factor.StringNode
import com.github.nsc.de.shake.parser.node.factor.CharacterNode
import com.github.nsc.de.shake.parser.node.CastNode.CastTarget
import com.github.nsc.de.shake.parser.node.expression.*
import com.github.nsc.de.shake.parser.node.factor.DoubleNode
import com.github.nsc.de.shake.parser.node.functions.ReturnNode
import com.github.nsc.de.shake.parser.node.logical.LogicalOrNode
import com.github.nsc.de.shake.parser.node.logical.LogicalXOrNode
import com.github.nsc.de.shake.parser.node.logical.LogicalAndNode
import com.github.nsc.de.shake.parser.node.logical.LogicalEqEqualsNode
import com.github.nsc.de.shake.parser.node.logical.LogicalBiggerEqualsNode
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerEqualsNode
import com.github.nsc.de.shake.parser.node.logical.LogicalBiggerNode
import com.github.nsc.de.shake.parser.node.logical.LogicalSmallerNode
import com.github.nsc.de.shake.util.CompilerError
import com.github.nsc.de.shake.util.characterinput.position.Position
import com.github.nsc.de.shake.util.characterinput.position.PositionMap
import java.util.ArrayList

class Parser(val input: TokenInputStream) {
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
            if (position >= input.getPosition()) break
            position = input.getPosition()
            if (input.hasNext()) {
                val result = operation()
                if (result != null) nodes.add(result)
            }

            // if(this.skipSeparators() > 0) separator = true;
            skipSeparators()
        }
        return Tree(map, nodes)
    }

    private fun operation(): Node {
        val token = input.peekType()
        if (token == TokenType.KEYWORD_WHILE) return whileLoop()
        if (token == TokenType.KEYWORD_DO) return doWhileLoop()
        if (token == TokenType.KEYWORD_FOR) return forLoop()
        if (token == TokenType.KEYWORD_IF) return ifStatement()
        if (token == TokenType.KEYWORD_RETURN) return returnStatement()
        return if (token == TokenType.KEYWORD_IMPORT) parseImport() else valuedOperation()
    }

    private fun valuedOperation(): ValuedNode {
        val token = input.peekType()
        if (token == TokenType.KEYWORD_FUNCTION
            || token == TokenType.KEYWORD_VAR
            || token == TokenType.KEYWORD_CONST
            || token == TokenType.KEYWORD_CLASS
            || token == TokenType.KEYWORD_PUBLIC
            || token == TokenType.KEYWORD_PROTECTED
            || token == TokenType.KEYWORD_PRIVATE
            || token == TokenType.KEYWORD_FINAL
            || token == TokenType.KEYWORD_STATIC
            || token == TokenType.KEYWORD_DYNAMIC
            || token == TokenType.KEYWORD_BYTE
            || token == TokenType.KEYWORD_SHORT
            || token == TokenType.KEYWORD_INT
            || token == TokenType.KEYWORD_LONG
            || token == TokenType.KEYWORD_FLOAT
            || token == TokenType.KEYWORD_DOUBLE
            || token == TokenType.KEYWORD_BOOLEAN
            || token == TokenType.KEYWORD_CHAR
            || token == TokenType.KEYWORD_VOID) return parseDeclaration()

        // Expression
        if (token == TokenType.INTEGER
            || token == TokenType.DOUBLE
            || token == TokenType.KEYWORD_TRUE
            || token == TokenType.KEYWORD_FALSE
            || token == TokenType.IDENTIFIER
            || token == TokenType.KEYWORD_NEW
            || token == TokenType.STRING
            || token == TokenType.CHARACTER) return logicalOr()
        else throw ParserError("Expected value")
    }

    // ****************************************************************************
    // Utils
    private fun skipSeparators(): Int {
        var number = 0
        while (input.hasNext() && (input.peekType() == TokenType.SEMICOLON
                    || input.peekType() == TokenType.LINE_SEPARATOR)
        ) {
            number++
            input.skip()
        }
        return number
    }

    private fun awaitSemicolon() {
        if (input.skipIgnorable()
                .nextType() != TokenType.SEMICOLON
        ) throw ParserError("Expecting semicolon at this point", input.getPosition())
    }

    private fun parseDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ValuedNode {
        val input = input
        return when (input.peekType()) {
            TokenType.KEYWORD_PUBLIC -> {
                input.skip()
                parseDeclaration(AccessDescriber.PUBLIC, isInClass, isStatic, isFinal)
            }
            TokenType.KEYWORD_PROTECTED -> {
                input.skip()
                parseDeclaration(AccessDescriber.PROTECTED, isInClass, isStatic, isFinal)
            }
            TokenType.KEYWORD_PRIVATE -> {
                input.skip()
                parseDeclaration(AccessDescriber.PRIVATE, isInClass, isStatic, isFinal)
            }
            TokenType.KEYWORD_STATIC -> {
                if (!isInClass) throw ParserError("Static keyword is only for objects in classes")
                input.skip()
                parseDeclaration(access, isInClass = true, isStatic = true, isFinal = isFinal)
            }
            TokenType.KEYWORD_FINAL -> {
                input.skip()
                parseDeclaration(access, isInClass, isStatic, true)
            }
            TokenType.KEYWORD_FUNCTION -> functionDeclaration(access, isInClass, isStatic, isFinal)
            TokenType.KEYWORD_CLASS -> classDeclaration(access, isInClass, isStatic, isFinal)
            TokenType.KEYWORD_CONSTRUCTOR -> constructorDeclaration(access, isInClass, isStatic, isFinal)
            TokenType.KEYWORD_CONST, TokenType.KEYWORD_VAR -> varDeclaration1(access, isInClass, isStatic, isFinal)
            TokenType.KEYWORD_DYNAMIC, TokenType.KEYWORD_BOOLEAN, TokenType.KEYWORD_CHAR, TokenType.KEYWORD_BYTE, TokenType.KEYWORD_SHORT, TokenType.KEYWORD_INT, TokenType.KEYWORD_LONG, TokenType.KEYWORD_FLOAT, TokenType.KEYWORD_DOUBLE, TokenType.KEYWORD_VOID, TokenType.IDENTIFIER -> cStyleDeclaration(
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
        if (input.nextType() != TokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val identifierNode = IdentifierNode(map, parent, input.actualValue()!!, input.actualStart())
        var ret: ValuedNode? = null

        // Assignments
        if (input.hasNext()) {
            when (input.skipIgnorable().peekType()) {
                TokenType.LPAREN -> ret = functionCall(identifierNode)
                TokenType.ASSIGN -> ret = varAssignment(identifierNode)
                TokenType.IDENTIFIER -> ret = this.cStyleDeclaration(
                    VariableType(identifierNode),
                    AccessDescriber.PRIVATE, isInClass = false, isStatic = false, isFinal = false
                )
                TokenType.ADD_ASSIGN -> ret = varAddAssignment(identifierNode)
                TokenType.SUB_ASSIGN -> ret = varSubAssignment(identifierNode)
                TokenType.MUL_ASSIGN -> ret = varMulAssignment(identifierNode)
                TokenType.DIV_ASSIGN -> ret = varDivAssignment(identifierNode)
                TokenType.MOD_ASSIGN -> ret = varModAssignment(identifierNode)
                TokenType.POW_ASSIGN -> ret = varPowAssignment(identifierNode)
                TokenType.INCR -> ret = varIncrease(identifierNode)
                TokenType.DECR -> ret = varDecrease(identifierNode)
            }
            if (input.skipIgnorable().hasNext() && input.peekType() == TokenType.DOT) {
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
        val newKeywordPosition = input.actualStart()
        input.skipIgnorable()
        val start = input.actualStart()
        val node = parseIdentifier(null) as? FunctionCallNode
            ?: throw ParserError(
                "Expecting a call after keyword new",
                start, input.actualEnd()
            )
        return ClassConstructionNode(
            map, node.function, node.args,
            newKeywordPosition
        )
    }

    // ****************************************************************************
    // Imports
    private fun parseImport(): ImportNode {
        if (!input.hasNext() || input.peekType() != TokenType.KEYWORD_IMPORT) throw ParserError("Expecting import keyword")
        val list = ArrayList<String?>()
        do {
            input.skip()
            if (input.skipIgnorable().nextType() == TokenType.MUL) {
                list.add(ImportNode.EVERYTHING)
                break
            }
            if (input.actualType() != TokenType.IDENTIFIER) throw ParserError("Expecting identifier")
            list.add(input.actualValue())
        } while (input.hasNext() && input.skipIgnorable().peekType() == TokenType.DOT)
        return ImportNode(map, list.toArray(arrayOf()))
    }

    // ****************************************************************************
    // Classes
    private fun classDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ClassDeclarationNode {
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_CLASS) throw ParserError("Expecting class keyword")
        if (!input.hasNext() || input.peekType() != TokenType.IDENTIFIER) throw ParserError("Expecting identifier")
        val name = input.nextValue()
        val fields: MutableList<VariableDeclarationNode> = ArrayList()
        val methods: MutableList<FunctionDeclarationNode> = ArrayList()
        val classes: MutableList<ClassDeclarationNode> = ArrayList()
        val constructors: MutableList<ConstructorDeclarationNode> = ArrayList()

        // TODO: extends, implements
        if (input.nextType() != TokenType.LCURL) throw ParserError("Expecting class-body")
        while (input.hasNext() && input.peekType() != TokenType.RCURL) {
            skipSeparators()
            when (val node = parseDeclaration(true)) {
                is ClassDeclarationNode -> classes.add(node)
                is FunctionDeclarationNode -> methods.add(node)
                is VariableDeclarationNode -> fields.add(node)
                is ConstructorDeclarationNode -> constructors.add(node)
            }
            skipSeparators()
        }
        if (input.nextType() != TokenType.RCURL) throw ParserError("Expecting class-body to end")
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
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_FUNCTION) throw ParserError("Expecting function keyword")
        if (!input.hasNext() || input.peekType() != TokenType.IDENTIFIER) throw ParserError("Expecting identifier")
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
        if (!input.hasNext() || input.nextType() != TokenType.LPAREN) throw ParserError("Expecting '('")
        if (input.hasNext() && input.peekType() != TokenType.RPAREN) {
            args.add(parseArgument())
            while (input.hasNext() && input.peekType() == TokenType.COMMA) {
                input.skip()
                if (input.hasNext() && input.peekType() != TokenType.RPAREN) args.add(parseArgument()) else break
            }
        }
        if (!input.hasNext() || input.nextType() != TokenType.RPAREN) throw ParserError(
            "Expecting ')'",
            input.getPosition()
        )
        return args.toTypedArray()
    }

    private fun functionCall(function: ValuedNode): FunctionCallNode {
        val args: MutableList<ValuedNode> = ArrayList()
        if (!input.hasNext() || input.nextType() != TokenType.LPAREN) throw ParserError("Expecting '('")
        if (input.peekType() != TokenType.RPAREN) {
            args.add(valuedOperation())
            while (input.hasNext() && input.peekType() == TokenType.COMMA) {
                input.skip()
                val operation = valuedOperation()
                if (operation != null) args.add(operation) else break
            }
        }
        if (!input.hasNext() || input.nextType() != TokenType.RPAREN) throw ParserError("Expecting ')'")
        return FunctionCallNode(map, function, args.toTypedArray())
    }

    private fun parseArgument(): FunctionArgumentNode {
        val next = input.nextType()
        input.skipIgnorable()
        var peek: Byte
        if (next == TokenType.IDENTIFIER && (!input.hasNext() || input.peekType()
                .also { peek = it } != TokenType.IDENTIFIER && peek != TokenType.DOT)
        ) return FunctionArgumentNode(map, input.actualValue()!!)
        val type: VariableType
        when (next) {
            TokenType.KEYWORD_DYNAMIC -> type = VariableType.DYNAMIC
            TokenType.KEYWORD_BOOLEAN -> type = VariableType.BOOLEAN
            TokenType.KEYWORD_CHAR -> type = VariableType.CHAR
            TokenType.KEYWORD_BYTE -> type = VariableType.BYTE
            TokenType.KEYWORD_SHORT -> type = VariableType.SHORT
            TokenType.KEYWORD_INT -> type = VariableType.INTEGER
            TokenType.KEYWORD_LONG -> type = VariableType.LONG
            TokenType.KEYWORD_FLOAT -> type = VariableType.FLOAT
            TokenType.KEYWORD_DOUBLE -> type = VariableType.DOUBLE
            TokenType.IDENTIFIER -> {
                var node = IdentifierNode(
                    map, input.actualValue()!!,
                    input.getPosition()
                )
                while (input.peekType() == TokenType.DOT) {
                    input.skip()
                    input.skipIgnorable()
                    if (input.nextType() != TokenType.IDENTIFIER) throw ParserError("Expecting identifier")
                    node = IdentifierNode(map, input.actualValue()!!, input.actualStart())
                }
                type = VariableType(node)
            }
            else -> throw ParserError("Unknown variable-type token: " + getName(next))
        }
        return if (input.hasNext() && input.peekType() == TokenType.IDENTIFIER) {
            val identifier = input.nextValue()
            input.skipIgnorable()
            FunctionArgumentNode(map, identifier!!, type)
        } else throw ParserError("Expecting identifier")
    }

    private fun returnStatement(): ReturnNode {
        input.skip()
        return ReturnNode(map, valuedOperation())
    }

    private fun constructorDeclaration(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): ConstructorDeclarationNode {
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_CONSTRUCTOR) throw ParserError("Expecting function keyword")
        if (!isInClass) throw ParserError("A constructor must be inside of a class")
        if (isFinal) throw ParserError("A constructor must not be final")
        if (isStatic) throw ParserError("A constructor must not be static")
        val name = if (input.skipIgnorable().peekType() == TokenType.IDENTIFIER) input.nextValue() else null
        val args = parseFunctionArguments()
        val body = parseBodyStatement()
        return ConstructorDeclarationNode(map, name, body, args, access)
    }

    // ****************************************************************************
    // Variables
    private fun varAssignment(variable: ValuedNode): VariableAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.ASSIGN) throw ParserError("Expecting '='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariableAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varAddAssignment(variable: ValuedNode): VariableAddAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.ADD_ASSIGN) throw ParserError("Expecting '+='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariableAddAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varSubAssignment(variable: ValuedNode): VariableSubAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.SUB_ASSIGN) throw ParserError("Expecting '-='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariableSubAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varMulAssignment(variable: ValuedNode): VariableMulAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.MUL_ASSIGN) throw ParserError("Expecting '*='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariableMulAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varDivAssignment(variable: ValuedNode): VariableDivAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.DIV_ASSIGN) throw ParserError("Expecting '/='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariableDivAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varModAssignment(variable: ValuedNode): VariableModAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.MOD_ASSIGN) throw ParserError("Expecting '%='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariableModAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varPowAssignment(variable: ValuedNode): VariablePowAssignmentNode {
        if (!input.hasNext() || input.nextType() != TokenType.POW_ASSIGN) throw ParserError("Expecting '**='")
        val operatorPosition = input.actualStart()
        val value = operation()
        return VariablePowAssignmentNode(map, variable, value, operatorPosition)
    }

    private fun varIncrease(variable: ValuedNode): VariableIncreaseNode {
        if (!input.hasNext() || input.nextType() != TokenType.INCR) throw ParserError("Expecting '++'")
        return VariableIncreaseNode(map, variable, input.actualStart())
    }

    private fun varDecrease(variable: ValuedNode): VariableDecreaseNode {
        if (!input.hasNext() || input.nextType() != TokenType.DECR) throw ParserError("Expecting '--'")
        return VariableDecreaseNode(map, variable, input.actualStart())
    }

    private fun varDeclaration1(
        access: AccessDescriber,
        isInClass: Boolean,
        isStatic: Boolean,
        isFinal: Boolean
    ): VariableDeclarationNode {
        var final = isFinal
        if (!input.hasNext()) throw ParserError("Expecting var or const keyword")
        if (input.nextType() == TokenType.KEYWORD_CONST) {
            if (final) throw ParserError("A constant is always final, must not have \"final\" attribute!")
            final = true
        } else if (input.actualType() != TokenType.KEYWORD_VAR) throw ParserError("Expecting var or const keyword")
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != TokenType.IDENTIFIER
        ) throw ParserError("Expecting identifier")
        val identifier = input.nextValue()
        val pos = input.actualStart()
        return if (input.skipIgnorable().hasNext() && input.peekType() == TokenType.ASSIGN) {
            VariableDeclarationNode(
                map, identifier!!, VariableType.DYNAMIC,
                varAssignment(IdentifierNode(map, identifier, pos)),
                access, isInClass, isStatic, final
            )
        } else {
            VariableDeclarationNode(
                map,
                input.actualValue()!!,
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
            if (t == TokenType.KEYWORD_DYNAMIC) VariableType.DYNAMIC else (if (t == TokenType.KEYWORD_BYTE) VariableType.BYTE else if (t == TokenType.KEYWORD_SHORT) VariableType.SHORT else if (t == TokenType.KEYWORD_INT) VariableType.INTEGER else if (t == TokenType.KEYWORD_LONG) VariableType.LONG else if (t == TokenType.KEYWORD_FLOAT) VariableType.FLOAT else if (t == TokenType.KEYWORD_DOUBLE) VariableType.DOUBLE else if (t == TokenType.KEYWORD_BOOLEAN) VariableType.BOOLEAN else if (t == TokenType.KEYWORD_CHAR) VariableType.CHAR else if (t == TokenType.KEYWORD_VOID) VariableType.VOID else if (t == TokenType.IDENTIFIER) VariableType.OBJECT else null)!!
        return cStyleDeclaration(declarationNode, access, isInClass, isStatic, isFinal)
    }

    private fun cStyleDeclaration(
        type: VariableType, access: AccessDescriber, isInClass: Boolean, isStatic: Boolean,
        isFinal: Boolean
    ): ValuedNode {

        // TODO error on void variable type
        if (!input.skipIgnorable()
                .hasNext() || input.peekType() != TokenType.IDENTIFIER
        ) throw ParserError("Expecting identifier")
        val identifier = input.nextValue()
        val position = input.actualStart()
        val hasNext = input.skipIgnorable().hasNext()
        return if (hasNext && input.peekType() == TokenType.ASSIGN) {
            VariableDeclarationNode(
                map, identifier!!, type,
                varAssignment(IdentifierNode(map, identifier, position)), access, isInClass, isStatic, isFinal
            )
        } else if (hasNext && input.peekType() == TokenType.LPAREN) cStyleFunctionDeclaration(
            type,
            identifier,
            access,
            isInClass,
            isStatic,
            isFinal
        ) else VariableDeclarationNode(
            map, input.actualValue()!!, type, null, access, isInClass,
            isStatic, isFinal
        )
    }

    // ****************************************************************************
    // Loops & If
    private fun forLoop(): Node {
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_FOR) throw ParserError("Expecting for keyword")
        if (!input.hasNext() || input.nextType() != TokenType.LPAREN) throw ParserError("Expecting '('")
        val declaration = operation()
        awaitSemicolon()
        val condition = valuedOperation()
        awaitSemicolon()
        val round = operation()
        if (!input.hasNext() || input.nextType() != TokenType.RPAREN) throw ParserError("Expecting ')'")
        val body = parseBodyStatement()
        return ForNode(map, body, declaration, condition, round)
    }

    private fun doWhileLoop(): Node {
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_DO) throw ParserError("Expecting do keyword")
        val body = parseBodyStatement()
        skipSeparators()
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = parseConditionStatement()
        return DoWhileNode(map, body, condition)
    }

    private fun whileLoop(): Node {
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_WHILE) throw ParserError("Expecting while keyword")
        val condition = parseConditionStatement()
        val body = parseBodyStatement()
        return WhileNode(map, body, condition)
    }

    private fun ifStatement(): Node {
        if (!input.hasNext() || input.nextType() != TokenType.KEYWORD_IF) throw ParserError("Expecting if keyword")
        val condition = parseConditionStatement()
        val body = parseBodyStatement()
        val separator = skipSeparators() > 0
        if (input.hasNext() && input.peekType() == TokenType.KEYWORD_ELSE) {
            if (!separator) throw ParserError("Awaited separator at this point")
            input.skip()
            val elseBody = parseBodyStatement()
            return IfNode(map, body, elseBody, condition)
        }
        return IfNode(map, body, condition)
    }

    private fun parseConditionStatement(): ValuedNode {
        if (!input.hasNext() || input.nextType() != TokenType.LPAREN) throw ParserError(
            "Expecting '('",
            input.getPosition()
        )
        val condition = logicalOr()
        if (!input.hasNext() || input.nextType() != TokenType.RPAREN) throw ParserError(
            "Expecting ')'",
            input.getPosition()
        )
        return condition
    }

    private fun parseBodyStatement(): Tree {
        skipSeparators()
        return if (input.peekType() == TokenType.LCURL) {
            input.skip()
            val body = prog()
            if (!input.hasNext() || input.nextType() != TokenType.RCURL) throw ParserError(
                "Expecting '}'",
                input.getPosition()
            )
            body
        } else {
            Tree(
                map,
                arrayOf(operation())
            )
        }
    }

    // ****************************************************************************
    // Statements
    // (Factor)
    private fun factor(): ValuedNode {
        val token = input.peekType()
        if (token == TokenType.LPAREN) {
            input.skip()
            val result = logicalOr()
            if (input.nextType() != TokenType.RPAREN) throw ParserError("Expecting ')'")
            return result
        }
        if (token == TokenType.KEYWORD_TRUE) {
            input.skip()
            return LogicalTrueNode(map)
        }
        if (token == TokenType.KEYWORD_FALSE) {
            input.skip()
            return LogicalFalseNode(map)
        }
        if (token == TokenType.INTEGER) {
            input.skip()
            return IntegerNode(map, input.actualValue()!!.toInt())
        }
        if (token == TokenType.DOUBLE) {
            input.skip()
            return DoubleNode(map, input.actualValue()!!.toDouble())
        }
        if (token == TokenType.IDENTIFIER) {
            return parseIdentifier(null)
        }
        if (token == TokenType.KEYWORD_NEW) {
            return parseClassConstruction()
        }
        if (token == TokenType.ADD) {
            input.skip()
            return AddNode(map, 0, factor(), input.getPosition())
        }
        if (token == TokenType.SUB) {
            input.skip()
            return SubNode(map, 0, factor(), input.getPosition())
        }
        if (token == TokenType.STRING) {
            input.skip()
            return StringNode(map, parseString(input.actualValue()!!))
        }
        if (token == TokenType.CHARACTER) {
            input.skip()
            return CharacterNode(map, parseString(input.actualValue()!!)[0])
        }
        throw ParserError(input.toString())
    }

    // Casting
    private fun cast(): ValuedNode {
        var result = factor()
        while (input.skipIgnorable().hasNext() && input.peekType() == TokenType.KEYWORD_AS) {
            input.skip()
            var target: CastTarget?
            when (input.skipIgnorable().peekType()) {
                TokenType.KEYWORD_BYTE -> {
                    target = CastTarget.BYTE
                    input.skip()
                }
                TokenType.KEYWORD_SHORT -> {
                    target = CastTarget.SHORT
                    input.skip()
                }
                TokenType.KEYWORD_INT -> {
                    target = CastTarget.INTEGER
                    input.skip()
                }
                TokenType.KEYWORD_LONG -> {
                    target = CastTarget.LONG
                    input.skip()
                }
                TokenType.KEYWORD_FLOAT -> {
                    target = CastTarget.FLOAT
                    input.skip()
                }
                TokenType.KEYWORD_DOUBLE -> {
                    target = CastTarget.DOUBLE
                    input.skip()
                }
                TokenType.KEYWORD_BOOLEAN -> {
                    target = CastTarget.BOOLEAN
                    input.skip()
                }
                TokenType.KEYWORD_CHAR -> {
                    target = CastTarget.CHAR
                    input.skip()
                }
                TokenType.IDENTIFIER -> {
                    var node: IdentifierNode? = null
                    do {
                        if (node != null) input.skip()
                        if (!input.skipIgnorable()
                                .hasNext() && input.nextType() != TokenType.IDENTIFIER
                        ) throw ParserError("Expecting identifier")
                        node = IdentifierNode(map, node, input.actualValue()!!, input.actualStart())
                    } while (input.skipIgnorable().hasNext() && input.peekType() == TokenType.DOT)
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
        var tmpType: Byte = 0
        while (input.hasNext() &&
            (input.peekType().also { tmpType = it } == TokenType.ADD || tmpType == TokenType.SUB)
        ) {
            input.skip()
            val pos = input.actualStart()
            result = if (tmpType == TokenType.ADD) {
                AddNode(map, result, term(), pos)
            } else {
                SubNode(map, result, term(), pos)
            }
        }
        return result
    }

    private fun term(): ValuedNode {
        var result = pow()
        var tmpType: Byte = 0
        while (input.hasNext() &&
            (input.peekType()
                .also { tmpType = it } == TokenType.MUL || tmpType == TokenType.DIV || tmpType == TokenType.MOD)
        ) {
            input.skip()
            val pos = input.actualStart()
            result = when (tmpType) {
                TokenType.MUL -> MulNode(map, result, pow(), pos)
                TokenType.DIV -> DivNode(map, result, pow(), pos)
                else -> ModNode(map, result, pow(), pos)
            }
        }
        return result
    }

    private fun pow(): ValuedNode {
        var result = cast()
        while (input.hasNext() && input.peekType() == TokenType.POW) {
            input.skip()
            val pos = input.actualStart()
            result = PowNode(map, result, cast(), pos)
        }
        return result
    }

    // (Logical)
    private fun logicalOr(): ValuedNode {
        var result = logicalXOr()
        while (input.hasNext() && input.peekType() == TokenType.LOGICAL_OR) {
            input.skip()
            result = LogicalOrNode(map, result, logicalXOr())
        }
        return result
    }

    private fun logicalXOr(): ValuedNode {
        var result = logicalAnd()
        while (input.hasNext() && input.peekType() == TokenType.LOGICAL_XOR) {
            input.skip()
            result = LogicalXOrNode(map, result, logicalAnd())
        }
        return result
    }

    private fun logicalAnd(): ValuedNode {
        var result = compare()
        while (input.hasNext() && input.peekType() == TokenType.LOGICAL_AND) {
            input.skip()
            result = LogicalAndNode(map, result, compare())
        }
        return result
    }

    private fun compare(): ValuedNode {
        var left = expr()
        var tmpType: Byte = 0
        while (input.hasNext()
            && (input.peekType().also { tmpType = it } == TokenType.EQ_EQUALS ||
                    tmpType == TokenType.BIGGER_EQUALS
                    || tmpType == TokenType.SMALLER_EQUALS
                    || tmpType == TokenType.BIGGER
                    || tmpType == TokenType.SMALLER)) {

            input.skip()
            left = when (tmpType) {
                TokenType.EQ_EQUALS -> return LogicalEqEqualsNode(map, left, logicalOr())
                TokenType.BIGGER_EQUALS -> LogicalBiggerEqualsNode(map, left, logicalOr())
                TokenType.SMALLER_EQUALS -> LogicalSmallerEqualsNode(map, left, logicalOr())
                TokenType.BIGGER -> LogicalBiggerNode(map, left, logicalOr())
                else -> LogicalSmallerNode(map, left, logicalOr())
            }

        }
        return left
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

        @Suppress("deprecation")
        constructor(error: String, position: Int) : this(
            error,
            input.map.resolve(input.getStart(position)),
            input.map.resolve(input.getEnd(position))
        )

        constructor(error: String) : this(
            error,
            input.map.resolve(input.peekStart()),
            input.map.resolve(input.peekEnd())
        )
    }

}