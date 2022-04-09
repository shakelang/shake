package io.github.shakelang.shake.js

import io.github.shakelang.shake.generation.ShakeGenerator
import io.github.shakelang.shake.js.output.*
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.DoubleNode
import io.github.shakelang.shake.parser.node.factor.IntegerNode
import io.github.shakelang.shake.parser.node.functions.FunctionCallNode
import io.github.shakelang.shake.parser.node.functions.FunctionDeclarationNode
import io.github.shakelang.shake.parser.node.logical.*
import io.github.shakelang.shake.parser.node.loops.DoWhileNode
import io.github.shakelang.shake.parser.node.loops.ForNode
import io.github.shakelang.shake.parser.node.loops.WhileNode
import io.github.shakelang.shake.parser.node.objects.ClassConstructionNode
import io.github.shakelang.shake.parser.node.objects.ClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.*
import io.github.shakelang.shake.processor.ShakePackage

class ShakeJsGenerator : ShakeGenerator<JsOutput>() {
    override fun visitTree(t: Tree): JsTree {
        val r = JsTree(t.children.mapNotNull { visit(it).toStatement() })
        return r
    }

    override fun visitDoubleNode(n: DoubleNode): JsDouble {
        return JsDouble(n.number)
    }

    override fun visitIntegerNode(n: IntegerNode): JsOutput {
        return JsInteger(n.number)
    }

    override fun visitAddNode(n: AddNode): JsAdd {
        return JsAdd(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitSubNode(n: SubNode): JsSubtract {
        return JsSubtract(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitMulNode(n: MulNode): JsMultiply {
        return JsMultiply(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitDivNode(n: DivNode): JsDivide {
        return JsDivide(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitModNode(n: ModNode): JsModulo {
        return JsModulo(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitPowNode(n: PowNode): JsOutput {
        return JsFunctionCall(JsField("pow", parent = JsField("Math")), args = listOf(visit(n.left).toValue(), visit(n.right).toValue()))
    }

    override fun visitVariableDeclarationNode(n: VariableDeclarationNode): JsDeclaration {
        if(n.isFinal) {
            if(n.assignment == null) throw IllegalStateException("Final variable must have an assignment")
            return JsConstantDeclaration(n.name, visit(n.assignment!!.value).toValue())
        }
        if(n.assignment == null) return JsVariableDeclaration(n.name)
        return JsVariableDeclaration(n.name, visit(n.assignment!!.value).toValue())
    }

    override fun visitVariableAssignmentNode(n: VariableAssignmentNode): JsAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsAssignment(JsField((n.variable as IdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableAddAssignmentNode(n: VariableAddAssignmentNode): JsAddAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsAddAssignment(JsField((n.variable as IdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableSubAssignmentNode(n: VariableSubAssignmentNode): JsSubtractAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsSubtractAssignment(JsField((n.variable as IdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableMulAssignmentNode(n: VariableMulAssignmentNode): JsMultiplyAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsMultiplyAssignment(JsField((n.variable as IdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableDivAssignmentNode(n: VariableDivAssignmentNode): JsDivideAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsDivideAssignment(JsField((n.variable as IdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariableModAssignmentNode(n: VariableModAssignmentNode): JsModuloAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsModuloAssignment(JsField((n.variable as IdentifierNode).name), visit(n.value).toValue())
    }

    override fun visitVariablePowAssignmentNode(n: VariablePowAssignmentNode): JsAssignment {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsAssignment(JsField((n.variable as IdentifierNode).name),
            JsFunctionCall(JsField("pow",
                parent = JsField((n.variable as IdentifierNode).name)),
                args = listOf(visit(n.value).toValue())
        ))
    }

    override fun visitVariableIncreaseNode(n: VariableIncreaseNode): JsIncrement {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsIncrement(JsField((n.variable as IdentifierNode).name))
    }

    override fun visitVariableDecreaseNode(n: VariableDecreaseNode): JsDecrement {
        if(n.variable !is IdentifierNode) throw IllegalStateException("Variable assignment must be to an identifier")
        return JsDecrement(JsField((n.variable as IdentifierNode).name))
    }

    override fun visitVariableUsageNode(n: VariableUsageNode): JsField {
        return JsField(n.variable.name, parent = n.variable.parent?.let { visit(it).toValue() })
    }

    override fun visitEqEqualsNode(n: LogicalEqEqualsNode): JsEquals {
        return JsEquals(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitBiggerEqualsNode(n: LogicalBiggerEqualsNode): JsGreaterThanOrEquals {
        return JsGreaterThanOrEquals(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitSmallerEqualsNode(n: LogicalSmallerEqualsNode): JsLessThanOrEquals {
        return JsLessThanOrEquals(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitBiggerNode(n: LogicalBiggerNode): JsGreaterThan {
        return JsGreaterThan(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitSmallerNode(n: LogicalSmallerNode): JsLessThan {
        return JsLessThan(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitLogicalAndNode(n: LogicalAndNode): JsAnd {
        return JsAnd(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitLogicalOrNode(n: LogicalOrNode): JsOr {
        return JsOr(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitLogicalXOrNode(n: LogicalXOrNode): JsXOr {
        return JsXOr(visit(n.left).toValue(), visit(n.right).toValue())
    }

    override fun visitWhileNode(n: WhileNode): JsWhile {
        return JsWhile(visit(n.condition).toValue(), visitTree(n.body))
    }

    override fun visitDoWhileNode(n: DoWhileNode): JsDoWhile {
        return JsDoWhile(visit(n.condition).toValue(), visitTree(n.body))
    }

    override fun visitForNode(n: ForNode): JsFor {
        return JsFor(
            visit(n.declaration).toStatement()!!,
            visit(n.condition).toValue(),
            visit(n.round).toStatement()!!,
            visitTree(n.body)
        )
    }

    override fun visitIfNode(n: IfNode): JsIf {
        return JsIf(visit(n.condition).toValue(), visitTree(n.body), n.elseBody?.let { visitTree(it) })
    }

    override fun visitFunctionDeclarationNode(n: FunctionDeclarationNode): JsFunctionDeclaration {
        return JsFunctionDeclaration(n.name, n.args.map { JsVariableDeclaration(it.name) }, visitTree(n.body))
    }

    override fun visitClassDeclarationNode(n: ClassDeclarationNode): JsClassDeclaration {
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

    override fun visitClassConstruction(n: ClassConstructionNode): JsNew {
        return JsNew(visit(n.type).toValue(), n.args.map { visit(it).toValue() })
    }

    override fun visitFunctionCallNode(n: FunctionCallNode): JsFunctionCall {
        return JsFunctionCall(visit(n.function).toValue(), n.args.map { visit(it).toValue() })
    }

    override fun visitIdentifierNode(n: IdentifierNode): JsOutput {
        if(n.parent != null) {
            return JsField(n.name, parent = visit(n.parent!!).toValue())
        }
        return JsField(n.name)
    }

    override fun visitLogicalTrueNode(n: LogicalTrueNode): JsOutput {
        return JsLiteral.TRUE
    }

    override fun visitLogicalFalseNode(n: LogicalFalseNode): JsOutput {
        return JsLiteral.FALSE
    }

    override fun visitImportNode(n: ImportNode): JsOutput {
        TODO("Not yet implemented")
    }

    override fun visitCastNode(n: CastNode): JsValue {
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