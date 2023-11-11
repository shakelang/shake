package com.shakelang.shake.processor

import com.shakelang.shake.lexer.ShakeLexer
import com.shakelang.shake.parser.node.*
import com.shakelang.shake.parser.node.expression.*
import com.shakelang.shake.parser.node.factor.*
import com.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import com.shakelang.shake.parser.node.functions.ShakeReturnNode
import com.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import com.shakelang.shake.parser.node.loops.ShakeForNode
import com.shakelang.shake.parser.node.loops.ShakeWhileNode
import com.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import com.shakelang.shake.parser.node.variables.*
import com.shakelang.shake.processor.program.creation.CreationShakeAssignable
import com.shakelang.shake.processor.program.creation.CreationShakeProject
import com.shakelang.shake.processor.program.creation.CreationShakeScope
import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.creation.code.*
import com.shakelang.shake.processor.program.creation.code.statements.*
import com.shakelang.shake.processor.program.creation.code.values.*
import com.shakelang.shake.util.parseutils.File
import com.shakelang.shake.util.parseutils.characters.streaming.CharacterInputStream
import com.shakelang.shake.util.parseutils.characters.streaming.SourceCharacterInputStream

class ShakeProcessorOptions {
    var precalculate: Boolean = true
}

abstract class ShakeProcessor<T> {

    val options: ShakeProcessorOptions = ShakeProcessorOptions()

    abstract val src: T

    open fun parseFile(src: String): ShakeFileNode {
        val file = File(src).contents
        val chars: CharacterInputStream = SourceCharacterInputStream(src, file)

        val lexer = ShakeLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = com.shakelang.shake.parser.ShakeParser.from(tokens)
        return parser.parse()
    }

    abstract fun loadFile(directory: String, src: String)

    fun <O> generate(f: (T) -> O): O {
        return f(src)
    }
}

open class ShakePackageBasedProcessor : ShakeProcessor<CreationShakeProject>() {

    val codeProcessor = ShakeCodeProcessor()
    open val project = CreationShakeProject(codeProcessor)
    override val src: CreationShakeProject
        get() = project

    open fun loadSynthetic(src: String, contents: ShakeFileNode) {
        val reformatted = src.replace("\\", "/")
        project.putFile(reformatted.split("/").toTypedArray(), contents)
    }

    override fun loadFile(directory: String, src: String) {
        val reformatted = src.replace("\\", "/")
        val contents = parseFile("$directory/$src")
        project.putFile(reformatted.split("/").toTypedArray(), contents)
    }

    fun finish(): CreationShakeProject {
        project.finish()
        return project
    }
}

open class ShakeCodeProcessor {

    fun visitValue(scope: CreationShakeScope, value: ShakeValuedNode): CreationShakeValue {
        return when (value) {
            is ShakeIntegerNode -> visitIntegerNode(scope, value)
            is ShakeDoubleNode -> visitDoubleNode(scope, value)
            is ShakeStringNode -> visitStringNode(scope, value)
            is ShakeLogicalTrueNode -> visitLogicalTrueNode(scope, value)
            is ShakeLogicalFalseNode -> visitLogicalFalseNode(scope, value)
            is ShakeLogicalAndNode -> visitLogicalAndNode(scope, value)
            is ShakeLogicalOrNode -> visitLogicalOrNode(scope, value)
            is ShakeLogicalXOrNode -> visitLogicalXOrNode(scope, value)
            is ShakeLogicalEqEqualsNode -> visitEqEqualsNode(scope, value)
            is ShakeLogicalBiggerEqualsNode -> visitBiggerEqualsNode(scope, value)
            is ShakeLogicalSmallerEqualsNode -> visitSmallerEqualsNode(scope, value)
            is ShakeLogicalBiggerNode -> visitBiggerNode(scope, value)
            is ShakeLogicalSmallerNode -> visitSmallerNode(scope, value)
            is ShakeAddNode -> visitAddNode(scope, value)
            is ShakeSubNode -> visitSubNode(scope, value)
            is ShakeMulNode -> visitMulNode(scope, value)
            is ShakeDivNode -> visitDivNode(scope, value)
            is ShakeModNode -> visitModNode(scope, value)
            is ShakePowNode -> visitPowNode(scope, value)
            is ShakeVariableAssignmentNode -> visitVariableAssignmentNode(scope, value)
            is ShakeVariableAddAssignmentNode -> visitVariableAddAssignmentNode(scope, value)
            is ShakeVariableSubAssignmentNode -> visitVariableSubAssignmentNode(scope, value)
            is ShakeVariableMulAssignmentNode -> visitVariableMulAssignmentNode(scope, value)
            is ShakeVariableDivAssignmentNode -> visitVariableDivAssignmentNode(scope, value)
            is ShakeVariableModAssignmentNode -> visitVariableModAssignmentNode(scope, value)
            is ShakeVariablePowAssignmentNode -> visitVariablePowAssignmentNode(scope, value)
            is ShakeVariableIncreaseNode -> visitVariableIncrementNode(scope, value)
            is ShakeVariableDecreaseNode -> visitVariableDecrementNode(scope, value)
            is ShakeVariableUsageNode -> visitVariableUsageNode(scope, value)
            is ShakeCastNode -> visitCastNode(scope, value)
            is ShakeFunctionCallNode -> visitFunctionCallNode(scope, value)
            is ShakeClassConstructionNode -> visitClassConstruction(scope, value)
            else -> throw IllegalArgumentException("Unsupported value type: ${value::class.simpleName}")
        }
    }

    fun visitStatement(scope: CreationShakeScope, statement: ShakeStatementNode): CreationShakeStatement {
        return when (statement) {
            is ShakeIfNode -> visitIfNode(scope, statement)
            is ShakeWhileNode -> visitWhileNode(scope, statement)
            is ShakeDoWhileNode -> visitDoWhileNode(scope, statement)
            is ShakeForNode -> visitForNode(scope, statement)
            is ShakeReturnNode -> visitReturnNode(scope, statement)
            is ShakeVariableDeclarationNode -> visitVariableDeclarationNode(scope, statement)
            is ShakeVariableAssignmentNode -> visitVariableAssignmentNode(scope, statement)
            is ShakeVariableAddAssignmentNode -> visitVariableAddAssignmentNode(scope, statement)
            is ShakeVariableSubAssignmentNode -> visitVariableSubAssignmentNode(scope, statement)
            is ShakeVariableMulAssignmentNode -> visitVariableMulAssignmentNode(scope, statement)
            is ShakeVariableDivAssignmentNode -> visitVariableDivAssignmentNode(scope, statement)
            is ShakeVariableModAssignmentNode -> visitVariableModAssignmentNode(scope, statement)
            is ShakeVariablePowAssignmentNode -> visitVariablePowAssignmentNode(scope, statement)
            is ShakeVariableIncreaseNode -> visitVariableIncrementNode(scope, statement)
            is ShakeVariableDecreaseNode -> visitVariableDecrementNode(scope, statement)
            is ShakeFunctionCallNode -> visitFunctionCallNode(scope, statement)
            is ShakeClassConstructionNode -> visitClassConstruction(scope, statement)
            else -> throw IllegalArgumentException("Unsupported statement type: ${statement::class}")
        }
    }

    fun visitTree(scope: CreationShakeScope, t: ShakeBlockNode): CreationShakeCode {
        return CreationShakeCode(
            t.children.map {
                visitStatement(scope, it)
            }
        )
    }

    fun visitType(scope: CreationShakeScope, t: ShakeVariableType): CreationShakeType? {
        return when (t.type) {
            ShakeVariableType.Type.DYNAMIC -> null
            ShakeVariableType.Type.BYTE -> CreationShakeType.Primitives.BYTE
            ShakeVariableType.Type.SHORT -> CreationShakeType.Primitives.SHORT
            ShakeVariableType.Type.INTEGER -> CreationShakeType.Primitives.INT
            ShakeVariableType.Type.LONG -> CreationShakeType.Primitives.LONG
            ShakeVariableType.Type.FLOAT -> CreationShakeType.Primitives.FLOAT
            ShakeVariableType.Type.DOUBLE -> CreationShakeType.Primitives.DOUBLE
            ShakeVariableType.Type.BOOLEAN -> CreationShakeType.Primitives.BOOLEAN
            else -> null
        }
    }

    fun visitDoubleNode(scope: CreationShakeScope, n: ShakeDoubleNode): CreationShakeDoubleLiteral {
        return CreationShakeDoubleLiteral(scope.project, n.number)
    }

    fun visitIntegerNode(scope: CreationShakeScope, n: ShakeIntegerNode): CreationShakeIntegerLiteral {
        return CreationShakeIntegerLiteral(scope.project, n.number)
    }

    fun visitStringNode(scope: CreationShakeScope, n: ShakeStringNode): CreationShakeStringLiteral {
        return CreationShakeStringLiteral(scope.project, n.value)
    }

    fun visitAddNode(scope: CreationShakeScope, n: ShakeAddNode): CreationShakeValue {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val aw = left.type.additionOperator(right.type, scope)
        return if (aw.overload != null) {
            CreationShakeInvocation(scope.project, aw.overload, listOf(right), left)
        } else {
            CreationShakeAddition(
                scope.project,
                left,
                right,
                aw.returnType ?: throw IllegalStateException("No return type for addition")
            )
        }
    }

    fun visitSubNode(scope: CreationShakeScope, n: ShakeSubNode): CreationShakeValue {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val aw = left.type.subtractionOperator(right.type, scope)
        return if (aw.overload != null) {
            CreationShakeInvocation(scope.project, aw.overload, listOf(right), left)
        } else {
            CreationShakeSubtraction(
                scope.project,
                left,
                right,
                aw.returnType ?: throw IllegalStateException("No return type for subtraction")
            )
        }
    }

    fun visitMulNode(scope: CreationShakeScope, n: ShakeMulNode): CreationShakeValue {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val aw = left.type.multiplicationOperator(right.type, scope)
        return if (aw.overload != null) {
            CreationShakeInvocation(scope.project, aw.overload, listOf(right), left)
        } else {
            CreationShakeMultiplication(
                scope.project,
                left,
                right,
                aw.returnType ?: throw IllegalStateException("No return type for multiplication")
            )
        }
    }

    fun visitDivNode(scope: CreationShakeScope, n: ShakeDivNode): CreationShakeValue {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val aw = left.type.divisionOperator(right.type, scope)
        return if (aw.overload != null) {
            CreationShakeInvocation(scope.project, aw.overload, listOf(right), left)
        } else {
            CreationShakeDivision(
                scope.project,
                left,
                right,
                aw.returnType ?: throw IllegalStateException("No return type for division")
            )
        }
    }

    fun visitModNode(scope: CreationShakeScope, n: ShakeModNode): CreationShakeValue {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val aw = left.type.modulusOperator(right.type, scope)
        return if (aw.overload != null) {
            CreationShakeInvocation(scope.project, aw.overload, listOf(right), left)
        } else {
            CreationShakeModulus(
                scope.project,
                left,
                right,
                aw.returnType ?: throw IllegalStateException("No return type for modulus")
            )
        }
    }

    fun visitPowNode(scope: CreationShakeScope, n: ShakePowNode): CreationShakeValue {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val aw = left.type.powerOperator(right.type, scope)
        return if (aw.overload != null) {
            CreationShakeInvocation(scope.project, aw.overload, listOf(right), left)
        } else {
            CreationShakePower(
                scope.project,
                left,
                right,
                aw.returnType ?: throw IllegalStateException("No return type for power")
            )
        }
    }

    fun visitVariableDeclarationNode(
        scope: CreationShakeScope,
        n: ShakeVariableDeclarationNode
    ): CreationShakeVariableDeclaration {
        val value = if (n.value != null) visitValue(scope, n.value!!) else null
        val type = visitType(scope, n.type) ?: value?.type ?: throw Exception("Cannot infer type of variable ${n.name}")
        val decl = CreationShakeVariableDeclaration(scope, n.name, type, value, n.isFinal)
        scope.set(decl)
        return decl
    }

    fun getAssignable(scope: CreationShakeScope, n: ShakeValuedNode): CreationShakeAssignable? {
        return if (n !is ShakeIdentifierNode) {
            CreationShakeAssignable.wrap(scope.project, visitValue(scope, n))
        } else if (n.parent != null) {
            val parent = visitValue(scope, n.parent!!)
            CreationShakeChild(scope.project, scope, parent, n.name)
        } else {
            scope.get(n.name)
        }
    }

    fun visitVariableAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariableAssignmentNode
    ): CreationShakeAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createAssignment(value, scope)
    }

    fun visitVariableAddAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariableAddAssignmentNode
    ): CreationShakeAddAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createAddAssignment(value, scope)
    }

    fun visitVariableSubAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariableSubAssignmentNode
    ): CreationShakeSubAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createSubtractAssignment(value, scope)
    }

    fun visitVariableMulAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariableMulAssignmentNode
    ): CreationShakeMulAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createMultiplyAssignment(value, scope)
    }

    fun visitVariableDivAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariableDivAssignmentNode
    ): CreationShakeDivAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createDivideAssignment(value, scope)
    }

    fun visitVariableModAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariableModAssignmentNode
    ): CreationShakeModAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createModulusAssignment(value, scope)
    }

    fun visitVariablePowAssignmentNode(
        scope: CreationShakeScope,
        n: ShakeVariablePowAssignmentNode
    ): CreationShakePowerAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if (variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createPowerAssignment(value, scope)
    }

    fun visitVariableIncrementNode(
        scope: CreationShakeScope,
        n: ShakeVariableIncreaseNode
    ): CreationShakeIncrementBefore {
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        return variable.createIncrementBeforeAssignment(scope)
    }

    fun visitVariableDecrementNode(
        scope: CreationShakeScope,
        n: ShakeVariableDecreaseNode
    ): CreationShakeDecrementBefore {
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        return variable.createDecrementBeforeAssignment(scope)
    }

    fun visitVariableUsageNode(scope: CreationShakeScope, n: ShakeVariableUsageNode): CreationShakeValue {
        val identifier = n.variable
        if (identifier.parent != null) {
            val parent = visitValue(scope, identifier.parent!!)
            val type = parent.type.childType(identifier.name, scope)
                ?: throw Exception("Cannot access ${identifier.name} in ${parent.type}")
            return CreationShakeChild(scope.project, scope, parent, identifier.name).access(scope)
        }
        val variable = scope.get(identifier.name) ?: throw Exception("Variable ${identifier.name} not declared")
        return variable.access(scope) // TODO null value
    }

    fun visitEqEqualsNode(scope: CreationShakeScope, n: ShakeLogicalEqEqualsNode): CreationShakeEquals {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeEquals(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitBiggerEqualsNode(
        scope: CreationShakeScope,
        n: ShakeLogicalBiggerEqualsNode
    ): CreationShakeGreaterThanOrEqual {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeGreaterThanOrEqual(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitSmallerEqualsNode(
        scope: CreationShakeScope,
        n: ShakeLogicalSmallerEqualsNode
    ): CreationShakeLessThanOrEqual {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeLessThanOrEqual(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitBiggerNode(scope: CreationShakeScope, n: ShakeLogicalBiggerNode): CreationShakeGreaterThan {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeGreaterThan(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitSmallerNode(scope: CreationShakeScope, n: ShakeLogicalSmallerNode): CreationShakeLessThan {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeLessThan(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitLogicalAndNode(scope: CreationShakeScope, n: ShakeLogicalAndNode): CreationShakeAnd {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeAnd(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitLogicalOrNode(scope: CreationShakeScope, n: ShakeLogicalOrNode): CreationShakeOr {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeOr(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitLogicalXOrNode(scope: CreationShakeScope, n: ShakeLogicalXOrNode): CreationShakeXor {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return CreationShakeXor(
            scope.project,
            left,
            right,
            left.type.equalsType(right.type, scope)
                ?: throw Exception("Cannot compare ${left.type} to ${right.type}")
        )
    }

    fun visitBoolean(scope: CreationShakeScope, n: ShakeValuedNode): CreationShakeValue {
        val value = visitValue(scope, n)
        return if (value.type == CreationShakeType.Primitives.BOOLEAN) {
            value
        } else {
            throw Exception("Cannot convert ${value.type} to boolean")
        }
    }

    fun visitWhileNode(scope: CreationShakeScope, n: ShakeWhileNode): CreationShakeWhile {
        val condition = visitBoolean(scope, n.condition)
        val body = visitTree(scope, n.body)
        return CreationShakeWhile(condition, body)
    }

    fun visitDoWhileNode(scope: CreationShakeScope, n: ShakeDoWhileNode): CreationShakeDoWhile {
        val condition = visitBoolean(scope, n.condition)
        val body = visitTree(scope, n.body)
        return CreationShakeDoWhile(condition, body)
    }

    fun visitForNode(scope: CreationShakeScope, n: ShakeForNode): CreationShakeFor {
        val init = visitStatement(scope, n.declaration)
        val condition = visitBoolean(scope, n.condition)
        val update = visitStatement(scope, n.round)
        val body = visitTree(scope, n.body)
        return CreationShakeFor(init, condition, update, body)
    }

    fun visitIfNode(scope: CreationShakeScope, n: ShakeIfNode): CreationShakeIf {
        val condition = visitBoolean(scope, n.condition)
        val body = visitTree(scope, n.body)
        if (n.elseBody != null) {
            val elseBody = visitTree(scope, n.elseBody!!)
            return CreationShakeIf(condition, body, elseBody)
        }
        return CreationShakeIf(condition, body)
    }

    fun visitClassConstruction(scope: CreationShakeScope, n: ShakeClassConstructionNode): CreationShakeNew {
        val classNode = n.type
        if (classNode is ShakeIdentifierNode) {
            if (classNode.parent != null) {
                TODO("Construction of inner classes not implemented")
            }
            val className = classNode.name
            val clz = scope.getClass(className) ?: throw Exception("Class $className not found")
            val args = n.args.map { visitValue(scope, it) }
            val types = args.map { it.type }
            val constructors = clz.constructors
            if (constructors.isEmpty()) throw Exception("No constructor found for class $className")
            val constructor = ShakeSelect.selectConstructor(constructors, types)
                ?: throw Exception("No constructor found for class $className with arguments $types")
            return CreationShakeNew(scope.project, constructor, args)
        }
        TODO("Returned constructor. Will this ever be possible?")
    }

    fun visitFunctionCallNode(scope: CreationShakeScope, n: ShakeFunctionCallNode): CreationShakeInvocation {
        val functionNode = n.function
        if (functionNode is ShakeIdentifierNode) {
            if (functionNode.parent != null) {
                val parent = visitValue(scope, functionNode.parent!!)
                val name = functionNode.name
                val args = n.args.map { visitValue(scope, it) }
                val types = args.map { it.type }
                val functions = parent.type.childFunctions(name, scope)
                    ?: throw Exception("No function named $name in ${parent.type}")
                val function = ShakeSelect.selectFunction(functions, types)
                    ?: throw Exception("No function named $name with arguments $types in ${parent.type}")
                return CreationShakeInvocation(scope.project, function, args, parent)
            }

            val name = functionNode.name
            val args = n.args.map { visitValue(scope, it) }
            val types = args.map { it.type }
            val functions = scope.getFunctions(name)
            if (functions.isEmpty()) throw Exception("No function named $name")
            val function = ShakeSelect.selectFunction(functions, types)
                ?: throw Exception("No function named $name with arguments $types")
            return CreationShakeInvocation(scope.project, function, args, null)
        }
        TODO("Direct returned lambda functions")
    }

    fun visitLogicalTrueNode(scope: CreationShakeScope, n: ShakeLogicalTrueNode): CreationShakeValue {
        return CreationShakeBooleanLiteral(scope.project, true)
    }

    fun visitLogicalFalseNode(scope: CreationShakeScope, n: ShakeLogicalFalseNode): CreationShakeValue {
        return CreationShakeBooleanLiteral(scope.project, false)
    }

    fun visitCastNode(scope: CreationShakeScope, n: ShakeCastNode): CreationShakeCast {
        val value = visitValue(scope, n.value)
        val target = when (n.castTarget.type) {
            ShakeCastNode.CastTarget.CastTargetType.CHAR -> CreationShakeType.Primitives.CHAR
            ShakeCastNode.CastTarget.CastTargetType.BYTE -> CreationShakeType.Primitives.BYTE
            ShakeCastNode.CastTarget.CastTargetType.SHORT -> CreationShakeType.Primitives.SHORT
            ShakeCastNode.CastTarget.CastTargetType.INT -> CreationShakeType.Primitives.INT
            ShakeCastNode.CastTarget.CastTargetType.LONG -> CreationShakeType.Primitives.LONG
            ShakeCastNode.CastTarget.CastTargetType.FLOAT -> CreationShakeType.Primitives.FLOAT
            ShakeCastNode.CastTarget.CastTargetType.DOUBLE -> CreationShakeType.Primitives.DOUBLE
            ShakeCastNode.CastTarget.CastTargetType.BOOLEAN -> CreationShakeType.Primitives.BOOLEAN
            ShakeCastNode.CastTarget.CastTargetType.OBJECT -> {
                val st = n.castTarget.subtype ?: throw Exception("No subtype for object cast")
                if (n.castTarget.subtype!!.parts.size != 1) TODO()
                val type = scope.getClass(n.castTarget.subtype!!.parts[0])
                    ?: throw Exception("No class named ${n.castTarget.subtype!!.parts[0]} for object cast")
                type.asType()
            }

            else -> throw Exception("Unsupported cast target type ${n.castTarget.type}")
        }
        return CreationShakeCast(scope.project, value, target)
    }

    fun visitReturnNode(scope: CreationShakeScope, n: ShakeReturnNode): CreationShakeReturn {
        val value = visitValue(scope, n.value)
        return CreationShakeReturn(value)
    }
}
