package io.github.shakelang.shake.processor

import io.github.shakelang.parseutils.File
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.ShakeParser
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.functions.ShakeReturnNode
import io.github.shakelang.shake.parser.node.logical.*
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.variables.*
import io.github.shakelang.shake.processor.program.*
import io.github.shakelang.shake.processor.program.ShakeDeclaration
import io.github.shakelang.shake.processor.program.code.*
import io.github.shakelang.shake.processor.program.code.statements.*
import io.github.shakelang.shake.processor.program.code.values.*

class ShakeProcessorOptions {
    var precalculate: Boolean = true
}

abstract class ShakeProcessor <T> {

    val options: ShakeProcessorOptions = ShakeProcessorOptions()

    abstract val src: T

    open fun parseFile(src: String): ShakeFile {

        val file = File(src).contents
        val chars: CharacterInputStream = SourceCharacterInputStream(src, file)

        val lexer = ShakeLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser.from(tokens)
        return parser.parse()

    }

    abstract fun loadFile(directory: String, src: String)

    fun <O> generate(f: (T) -> O): O {
        return f(src)
    }


}
open class ShakePackageBasedProcessor : ShakeProcessor<ShakeProject>() {

    val codeProcessor = ShakeCodeProcessor()
    open val project = ShakeProject(codeProcessor)
    override val src: ShakeProject
        get() = project

    open fun loadSynthetic(src: String, contents: ShakeFile) {
        val reformatted = src.replace("\\", "/")
        project.putFile(reformatted.split("/").toTypedArray(), contents)
    }

    override fun loadFile(directory: String, src: String) {

        val reformatted = src.replace("\\", "/")
        val contents = parseFile("$directory/$src")
        project.putFile(reformatted.split("/").toTypedArray(), contents)

    }

    fun finish() : ShakeProject {
        project.finish()
        return project
    }
}

open class ShakeCodeProcessor {

    fun visitValue(scope: ShakeScope, value: ShakeValuedNode): ShakeValue {
        return when(value) {
            is ShakeIntegerNode -> visitIntegerNode(scope, value)
            is ShakeDoubleNode -> visitDoubleNode(scope, value)
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
            else -> throw IllegalArgumentException("Unsupported value type: ${value::class}")
        }
    }

    fun visitStatement(scope: ShakeScope, statement: ShakeStatementNode): ShakeStatement {
        return when(statement) {
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

    fun visitTree(scope: ShakeScope, t: ShakeTree): ShakeCode {
        return ShakeCode(t.children.map {
            visitStatement(scope, it)
        })
    }

    fun visitType(scope: ShakeScope, t: ShakeVariableType): ShakeType? {
        return when(t.type) {
            ShakeVariableType.Type.DYNAMIC -> null
            ShakeVariableType.Type.BYTE -> ShakeType.Primitives.BYTE
            ShakeVariableType.Type.SHORT -> ShakeType.Primitives.SHORT
            ShakeVariableType.Type.INTEGER -> ShakeType.Primitives.INT
            ShakeVariableType.Type.LONG -> ShakeType.Primitives.LONG
            ShakeVariableType.Type.FLOAT -> ShakeType.Primitives.FLOAT
            ShakeVariableType.Type.DOUBLE -> ShakeType.Primitives.DOUBLE
            ShakeVariableType.Type.BOOLEAN -> ShakeType.Primitives.BOOLEAN
            else -> null
        }
    }

    fun visitDoubleNode(scope: ShakeScope, n: ShakeDoubleNode): ShakeDoubleLiteral {
        return ShakeDoubleLiteral(n.number)
    }

    fun visitIntegerNode(scope: ShakeScope, n: ShakeIntegerNode): ShakeIntegerLiteral {
        return ShakeIntegerLiteral(n.number)
    }

    fun visitAddNode(scope: ShakeScope, n: ShakeAddNode): ShakeAddition {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val type = left.type.additionType(right.type) ?: throw Exception("Cannot add ${left.type} and ${right.type}")
        return ShakeAddition(left, right, type)
    }

    fun visitSubNode(scope: ShakeScope, n: ShakeSubNode): ShakeSubtraction {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val type = left.type.subtractionType(right.type) ?: throw Exception("Cannot subtract ${left.type} and ${right.type}")
        return ShakeSubtraction(left, right, type)
    }

    fun visitMulNode(scope: ShakeScope, n: ShakeMulNode): ShakeMultiplication {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val type = left.type.multiplicationType(right.type) ?: throw Exception("Cannot multiply ${left.type} and ${right.type}")
        return ShakeMultiplication(left, right, type)
    }

    fun visitDivNode(scope: ShakeScope, n: ShakeDivNode): ShakeDivision {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val type = left.type.divisionType(right.type) ?: throw Exception("Cannot divide ${left.type} and ${right.type}")
        return ShakeDivision(left, right, type)
    }

    fun visitModNode(scope: ShakeScope, n: ShakeModNode): ShakeModulus {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val type = left.type.modulusType(right.type) ?: throw Exception("Cannot modulus ${left.type} and ${right.type}")
        return ShakeModulus(left, right, type)
    }

    fun visitPowNode(scope: ShakeScope, n: ShakePowNode): ShakePower {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        val type = left.type.powerType(right.type) ?: throw Exception("Cannot power ${left.type} and ${right.type}")
        return ShakePower(left, right, type)
    }

    fun visitVariableDeclarationNode(scope: ShakeScope, n: ShakeVariableDeclarationNode): ShakeVariableDeclaration {
        val value = if(n.value != null) visitValue(scope, n.value!!) else null
        val type = visitType(scope, n.type) ?: value?.type ?: throw Exception("Cannot infer type of variable ${n.name}")
        val decl = ShakeVariableDeclaration(scope, n.name, type, value, n.isFinal)
        scope.set(decl)
        return decl
    }

    fun getAssignable(scope: ShakeScope, n: ShakeValuedNode): ShakeAssignable? {
        return if(n !is ShakeIdentifierNode) ShakeAssignable.wrap(visitValue(scope, n))
        else if(n.parent != null) {
            val parent = visitValue(scope, n.parent!!)
            ShakeChild(scope, parent, n.name)
        } else scope.get(n.name)
    }

    fun visitVariableAssignmentNode(scope: ShakeScope, n: ShakeVariableAssignmentNode): ShakeAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createAssignment(value)
    }

    fun visitVariableAddAssignmentNode(scope: ShakeScope, n: ShakeVariableAddAssignmentNode): ShakeAddAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createAddAssignment(value)
    }

    fun visitVariableSubAssignmentNode(scope: ShakeScope, n: ShakeVariableSubAssignmentNode): ShakeSubAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createSubtractAssignment(value)
    }

    fun visitVariableMulAssignmentNode(scope: ShakeScope, n: ShakeVariableMulAssignmentNode): ShakeMulAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createMultiplyAssignment(value)
    }

    fun visitVariableDivAssignmentNode(scope: ShakeScope, n: ShakeVariableDivAssignmentNode): ShakeDivAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createDivideAssignment(value)
    }

    fun visitVariableModAssignmentNode(scope: ShakeScope, n: ShakeVariableModAssignmentNode): ShakeModAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createModulusAssignment(value)
    }

    fun visitVariablePowAssignmentNode(scope: ShakeScope, n: ShakeVariablePowAssignmentNode): ShakePowerAssignment {
        val value = visitValue(scope, n.value)
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        if(variable.type != value.type) throw Exception("Cannot assign ${value.type} to ${variable.type}")
        return variable.createPowerAssignment(value)
    }

    fun visitVariableIncrementNode(scope: ShakeScope, n: ShakeVariableIncreaseNode): ShakeIncrementBefore {
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        return variable.createIncrementBeforeAssignment()
    }

    fun visitVariableDecrementNode(scope: ShakeScope, n: ShakeVariableDecreaseNode): ShakeDecrementBefore {
        val variable = getAssignable(scope, n.variable) ?: throw Exception("Cannot assign to ${n.variable}")
        return variable.createDecrementBeforeAssignment()
    }

    fun visitVariableUsageNode(scope: ShakeScope, n: ShakeVariableUsageNode): ShakeValue {
        val identifier = n.variable
        if(identifier.parent != null) {
            val parent = visitValue(scope, identifier.parent!!)
            val type = parent.type.childType(identifier.name)
                ?: throw Exception("Cannot access ${identifier.name} in ${parent.type}")
            return ShakeChild(scope, parent, identifier.name).access(scope)
        }
        val variable = scope.get(identifier.name) ?: throw Exception("Variable ${identifier.name} not declared")
        return variable.access(scope) // TODO null value
    }

    fun visitEqEqualsNode(scope: ShakeScope, n: ShakeLogicalEqEqualsNode): ShakeEquals {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeEquals(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitBiggerEqualsNode(scope: ShakeScope, n: ShakeLogicalBiggerEqualsNode): ShakeGreaterThanOrEqual {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeGreaterThanOrEqual(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitSmallerEqualsNode(scope: ShakeScope, n: ShakeLogicalSmallerEqualsNode): ShakeLessThanOrEqual {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeLessThanOrEqual(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitBiggerNode(scope: ShakeScope, n: ShakeLogicalBiggerNode): ShakeGreaterThan {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeGreaterThan(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitSmallerNode(scope: ShakeScope, n: ShakeLogicalSmallerNode): ShakeLessThan {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeLessThan(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitLogicalAndNode(scope: ShakeScope, n: ShakeLogicalAndNode): ShakeAnd {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeAnd(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitLogicalOrNode(scope: ShakeScope, n: ShakeLogicalOrNode): ShakeOr {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeOr(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitLogicalXOrNode(scope: ShakeScope, n: ShakeLogicalXOrNode): ShakeXor {
        val left = visitValue(scope, n.left)
        val right = visitValue(scope, n.right)
        return ShakeXor(left, right, left.type.equalsType(right.type)
            ?: throw Exception("Cannot compare ${left.type} to ${right.type}"))
    }

    fun visitBoolean(scope: ShakeScope, n: ShakeValuedNode): ShakeValue {
        val value = visitValue(scope, n)
        return if(value.type == ShakeType.Primitives.BOOLEAN) value
            else throw Exception("Cannot convert ${value.type} to boolean")
    }

    fun visitWhileNode(scope: ShakeScope, n: ShakeWhileNode): ShakeWhile {
        val condition = visitBoolean(scope, n.condition)
        val body = visitTree(scope, n.body)
        return ShakeWhile(condition, body)
    }

    fun visitDoWhileNode(scope: ShakeScope, n: ShakeDoWhileNode): ShakeDoWhile {
        val condition = visitBoolean(scope, n.condition)
        val body = visitTree(scope, n.body)
        return ShakeDoWhile(condition, body)
    }

    fun visitForNode(scope: ShakeScope, n: ShakeForNode): ShakeFor {
        val init = visitStatement(scope, n.declaration)
        val condition = visitBoolean(scope, n.condition)
        val update = visitStatement(scope, n.round)
        val body = visitTree(scope, n.body)
        return ShakeFor(init, condition, update, body)
    }

    fun visitIfNode(scope: ShakeScope, n: ShakeIfNode): ShakeIf {
        val condition = visitBoolean(scope, n.condition)
        val body = visitTree(scope, n.body)
        if(n.elseBody != null) {
            val elseBody = visitTree(scope, n.elseBody!!)
            return ShakeIf(condition, body, elseBody)
        }
        return ShakeIf(condition, body)
    }

    fun visitClassConstruction(scope: ShakeScope, n: ShakeClassConstructionNode): ShakeNew {
        val classNode = n.type
        if(classNode is ShakeIdentifierNode) {
            if(classNode.parent != null) {
                TODO("Construction of inner classes not implemented")
            }
            val className = classNode.name
            val clz = scope.getClass(className)?: throw Exception("Class $className not found")
            val args = n.args.map { visitValue(scope, it) }
            val types = args.map { it.type }
            val constructors = clz.constructors
            if(constructors.isEmpty()) throw Exception("No constructor found for class $className")
            val constructor = ShakeSelect.selectConstructor(constructors, types) ?:
                throw Exception("No constructor found for class $className with arguments $types")
            return ShakeNew(constructor, args)
        }
        TODO("Returned constructor. Will this ever be possible?")
    }

    fun visitFunctionCallNode(scope: ShakeScope, n: ShakeFunctionCallNode): ShakeInvocation {
        val functionNode = n.function
        if(functionNode is ShakeIdentifierNode) {
            if(functionNode.parent != null) {
                val parent = visitValue(scope, functionNode.parent!!)
                val name = functionNode.name
                val args = n.args.map { visitValue(scope, it) }
                val types = args.map { it.type }
                val functions = parent.type.childFunctions(name) ?: throw Exception("No function named $name in ${parent.type}")
                val function = ShakeSelect.selectFunction(functions, types)
                    ?: throw Exception("No function named $name with arguments $types in ${parent.type}")
                return ShakeInvocation(function, args, parent)
            }

            val name = functionNode.name
            val args = n.args.map { visitValue(scope, it) }
            val types = args.map { it.type }
            val functions = scope.getFunctions(name)
            if(functions.isEmpty()) throw Exception("No function named $name")
            val function = ShakeSelect.selectFunction(functions, types)
                ?: throw Exception("No function named $name with arguments $types")
            return ShakeInvocation(function, args, null)
        }
        TODO("Direct returned lambda functions")
    }

    fun visitLogicalTrueNode(scope: ShakeScope, n: ShakeLogicalTrueNode): ShakeValue {
        return ShakeBooleanLiteral.TRUE
    }

    fun visitLogicalFalseNode(scope: ShakeScope, n: ShakeLogicalFalseNode): ShakeValue {
        return ShakeBooleanLiteral.FALSE
    }

    fun visitCastNode(scope: ShakeScope, n: ShakeCastNode): ShakeCast {
        val value = visitValue(scope, n.value)
        val target = when(n.castTarget.type) {
            ShakeCastNode.CastTarget.CastTargetType.CHAR -> ShakeType.Primitives.CHAR
            ShakeCastNode.CastTarget.CastTargetType.BYTE -> ShakeType.Primitives.BYTE
            ShakeCastNode.CastTarget.CastTargetType.SHORT -> ShakeType.Primitives.SHORT
            ShakeCastNode.CastTarget.CastTargetType.INT -> ShakeType.Primitives.INT
            ShakeCastNode.CastTarget.CastTargetType.LONG -> ShakeType.Primitives.LONG
            ShakeCastNode.CastTarget.CastTargetType.FLOAT -> ShakeType.Primitives.FLOAT
            ShakeCastNode.CastTarget.CastTargetType.DOUBLE -> ShakeType.Primitives.DOUBLE
            ShakeCastNode.CastTarget.CastTargetType.BOOLEAN -> ShakeType.Primitives.BOOLEAN
            ShakeCastNode.CastTarget.CastTargetType.OBJECT -> {
                val st = n.castTarget.subtype ?: throw Exception("No subtype for object cast")
                if(st.parent != null) TODO()
                val type = scope.getClass(st.name) ?: throw Exception("No class named ${st.name} for object cast")
                type.asType()
            }
            else -> throw Exception("Unsupported cast target type ${n.castTarget.type}")
        }
        return ShakeCast(value, target)
    }

    fun visitReturnNode(scope: ShakeScope, n: ShakeReturnNode): ShakeReturn {
        val value = visitValue(scope, n.value)
        return ShakeReturn(value)
    }

}