package io.github.shakelang.shake.js

import io.github.shakelang.shake.generation.ShakeGenerator
import io.github.shakelang.shake.js.output.*
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.logical.*
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.*
import io.github.shakelang.shake.processor.program.ShakePackage

class ShakeJsGenerator : ShakeGenerator<JsOutput>() {
    override fun visitTree(t: ShakeTree): JsTree {
        val r = JsTree(t.children.mapNotNull { visit(it).toStatement() })
        return r
    }

    override fun visitDoubleNode(n: ShakeDoubleNode): JsDouble {
        return JsDouble(n.number)
    }

    override fun visitIntegerNode(n: ShakeIntegerNode): JsOutput {
        return JsInteger(n.number)
    }

    override fun visitAddNode(n: ShakeAddNode): JsAdd {
        return JsAdd(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitSubNode(n: ShakeSubNode): JsSubtract {
        return JsSubtract(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitMulNode(n: ShakeMulNode): JsMultiply {
        return JsMultiply(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitDivNode(n: ShakeDivNode): JsDivide {
        return JsDivide(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitModNode(n: ShakeModNode): JsModulo {
        return JsModulo(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitPowNode(n: ShakePowNode): JsOutput {
        return JsFunctionCall(JsField("pow", parent = JsField("Math")), args = listOf(visit(n.left).toValue(), visit(n.right).toValue()))
    }

    override fun visitVariableDeclarationNode(n: ShakeVariableDeclarationNode): JsDeclaration {
        if(n.isFinal) {
            if(n.assignment == null) throw IllegalStateException("Final variable must have an assignment")
            return JsConstantDeclaration(n.name, visit(n.assignment!!.value).toValue())
        }
        if(n.assignment == null) return JsVariableDeclaration(n.name)
        return JsVariableDeclaration(n.name, visit(n.assignment!!.value).toValue())
    }

    override fun visitVariableAssignmentNode(n: ShakeVariableAssignmentNode): JsAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsAssignment(JsField((n.variable as ShakeIdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableAddAssignmentNode(n: ShakeVariableAddAssignmentNode): JsAddAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsAddAssignment(JsField((n.variable as ShakeIdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableSubAssignmentNode(n: ShakeVariableSubAssignmentNode): JsSubtractAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsSubtractAssignment(JsField((n.variable as ShakeIdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableMulAssignmentNode(n: ShakeVariableMulAssignmentNode): JsMultiplyAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsMultiplyAssignment(JsField((n.variable as ShakeIdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableDivAssignmentNode(n: ShakeVariableDivAssignmentNode): JsDivideAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsDivideAssignment(JsField((n.variable as ShakeIdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableModAssignmentNode(n: ShakeVariableModAssignmentNode): JsModuloAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsModuloAssignment(JsField((n.variable as ShakeIdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariablePowAssignmentNode(n: ShakeVariablePowAssignmentNode): JsAssignment {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsAssignment(JsField((n.variable as ShakeIdentifierNode).name),
            JsFunctionCall(JsField("pow",
                parent = JsField((n.variable as ShakeIdentifierNode).name)),
                args = listOf(visit(n.value).toValue())
        ))
    }

    override fun visitVariableIncreaseNode(n: ShakeVariableIncreaseNode): JsIncrement {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsIncrement(JsField((n.variable as ShakeIdentifierNode).name))
    }

    override fun visitVariableDecreaseNode(n: ShakeVariableDecreaseNode): JsDecrement {
        if(n.variable !is ShakeIdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsDecrement(JsField((n.variable as ShakeIdentifierNode).name))
    }

    override fun visitVariableUsageNode(n: ShakeVariableUsageNode): JsField {
        return JsField(n.variable.name, parent = n.variable.parent?.let { visit(it).toValue() })
    }

    override fun visitEqEqualsNode(n: ShakeLogicalEqEqualsNode): JsEquals {
        return JsEquals(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitBiggerEqualsNode(n: ShakeLogicalBiggerEqualsNode): JsGreaterThanOrEquals {
        return JsGreaterThanOrEquals(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitSmallerEqualsNode(n: ShakeLogicalSmallerEqualsNode): JsLessThanOrEquals {
        return JsLessThanOrEquals(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitBiggerNode(n: ShakeLogicalBiggerNode): JsGreaterThan {
        return JsGreaterThan(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitSmallerNode(n: ShakeLogicalSmallerNode): JsLessThan {
        return JsLessThan(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitLogicalAndNode(n: ShakeLogicalAndNode): JsAnd {
        return JsAnd(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitLogicalOrNode(n: ShakeLogicalOrNode): JsOr {
        return JsOr(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitLogicalXOrNode(n: ShakeLogicalXOrNode): JsXOr {
        return JsXOr(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitWhileNode(n: ShakeWhileNode): JsWhile {
        return JsWhile(visit(n.condition).toValue(), visitTree(n.body))
    }

    override fun visitDoWhileNode(n: ShakeDoWhileNode): JsDoWhile {
        return JsDoWhile(visit(n.condition).toValue(), visitTree(n.body))
    }

    override fun visitForNode(n: ShakeForNode): JsFor {
        return JsFor(
            visit(n.declaration).toStatement()!!,
            visit(n.condition).toValue(),
            visit(n.round).toStatement()!!,
            visitTree(n.body)
        )
    }

    override fun visitIfNode(n: ShakeIfNode): JsIf {
        return JsIf(visit(n.condition).toValue(), visitTree(n.body), n.elseBody?.let { visitTree(it) })
    }

    override fun visitFunctionDeclarationNode(n: ShakeFunctionDeclarationNode): JsFunctionDeclaration {
        return JsFunctionDeclaration(n.name, n.args.map { JsVariableDeclaration(it.name) }, visitTree(n.body))
    }

    override fun visitClassDeclarationNode(n: ShakeClassDeclarationNode): JsClassDeclaration {
        return JsClassDeclaration(
            n.name,
            functions = n.methods
                .filter { !it.isStatic }
                .map { visitFunctionDeclarationNode(it) },
            staticFunctions = n.methods
                .filter { it.isStatic }
                .map { visitFunctionDeclarationNode(it) },
            fields = n.fields.filter { !it.isStatic }
                .map { visitVariableDeclarationNode(it) },
            staticFields = n.fields
                .filter { it.isStatic }
                .map { visitVariableDeclarationNode(it) }
        )
    }

    override fun visitClassConstruction(n: ShakeClassConstructionNode): JsNew {
        return JsNew(visit(n.type).toValue(), n.args.map { visit(it).toValue() })
    }

    override fun visitFunctionCallNode(n: ShakeFunctionCallNode): JsFunctionCall {
        return JsFunctionCall(visit(n.function).toValue(), n.args.map { visit(it).toValue() })
    }

    override fun visitIdentifierNode(n: ShakeIdentifierNode): JsOutput {
        if(n.parent != null) {
            return JsField(n.name, parent = visit(n.parent!!).toValue())
        }
        return JsField(n.name)
    }

    override fun visitLogicalTrueNode(n: ShakeLogicalTrueNode): JsOutput {
        return JsLiteral.TRUE
    }

    override fun visitLogicalFalseNode(n: ShakeLogicalFalseNode): JsOutput {
        return JsLiteral.FALSE
    }

    override fun visitImportNode(n: ShakeImportNode): JsOutput {
        TODO("Not yet implemented")
    }

    override fun visitCastNode(n: ShakeCastNode): JsValue {
        return visit(n.value).toValue()
    }

    fun generateSingleFile(src: ShakePackage): JsTree {
        return JsTree(listOf(
            JsVariableDeclaration("SHAKE_PACKAGE"),
            JsAssignment(JsField("SHAKE_PACKAGE"), generateSingleFilePackage(src))
        ))
    }

    fun generateSingleFilePackage(pkg: ShakePackage): JsObject {
        return JsObject(mapOf(
            JsStringLiteral("packages") to JsObject(
                pkg.subpackages.associate {
                    JsStringLiteral(it.name) to generateSingleFilePackage(it)
                }
            ),
            JsStringLiteral("classes") to JsObject(
                pkg.classes.values.associate {
                    JsStringLiteral(it.name) to JsFunctionCall(
                        JsInlineFunction(
                            emptyList(),
                            JsTree(listOf(
                                visitClassDeclarationNode(it),
                                JsReturn(JsField(it.name))
                            ))
                        ),
                        emptyList()
                    )
                }
            ),
            JsStringLiteral("functions") to JsObject(
                pkg.functions.values.associate {
                    JsStringLiteral(it.name) to visitFunctionDeclarationNode(it).inline()
                }
            ),
            JsStringLiteral("fields") to JsObject(
                pkg.fields.values.associate {
                    JsStringLiteral(it.name) to (visitVariableDeclarationNode(it).value ?: JsLiteral.NULL)
                }
            )
        ))
    }



    override val extension: String
        get() = "js"

    override val name: String
        get() = "bundle"

}