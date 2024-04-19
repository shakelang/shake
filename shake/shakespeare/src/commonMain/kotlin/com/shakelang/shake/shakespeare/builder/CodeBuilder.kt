@file:Suppress("ktlint:standard:function-naming", "FunctionName")

package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.code.CodeSpec

class CodeBuilder internal constructor(init: CodeBuilder.() -> Unit) : CodeSpec.CodeSpecBuilder(), Builder {
    init {
        init()
    }

    fun variableDeclaration(init: VariableDeclarationBuilder.() -> Unit) {
        val builder = VariableDeclarationBuilder(init)
        builder.isVal = false
        statements.add(builder.build())
    }

    fun valDeclaration(init: VariableDeclarationBuilder.() -> Unit) {
        val builder = VariableDeclarationBuilder(init)
        builder.isVal = true
        statements.add(builder.build())
    }

    fun varDeclaration(init: VariableDeclarationBuilder.() -> Unit) = variableDeclaration(init)

    fun If(init: IfBuilder.() -> Unit) {
        val builder = IfBuilder(init)
        statements.add(builder.build())
    }

    fun While(init: WhileBuilder.() -> Unit) {
        val builder = WhileBuilder(init)
        statements.add(builder.build())
    }

    fun DoWhile(init: DoWhileBuilder.() -> Unit) {
        val builder = DoWhileBuilder(init)
        statements.add(builder.build())
    }

    fun For(init: ForBuilder.() -> Unit) {
        val builder = ForBuilder(init)
        statements.add(builder.build())
    }

    fun Return(init: ReturnBuilder.() -> Unit) {
        val builder = ReturnBuilder(init)
        statements.add(builder.build())
    }

    fun Assignment(init: VariableAssignmentBuilder.() -> Unit) {
        val builder = VariableAssignmentBuilder(init)
        statements.add(builder.build())
    }

    fun AdditionAssignment(init: VariableAdditionSpecBuilder.() -> Unit) {
        val builder = VariableAdditionSpecBuilder(init)
        statements.add(builder.build())
    }

    fun SubtractionAssignment(init: VariableSubtractionSpecBuilder.() -> Unit) {
        val builder = VariableSubtractionSpecBuilder(init)
        statements.add(builder.build())
    }

    fun MultiplicationAssignment(init: VariableMultiplicationSpecBuilder.() -> Unit) {
        val builder = VariableMultiplicationSpecBuilder(init)
        statements.add(builder.build())
    }

    fun DivisionAssignment(init: VariableDivisionSpecBuilder.() -> Unit) {
        val builder = VariableDivisionSpecBuilder(init)
        statements.add(builder.build())
    }

    fun ModuloAssignment(init: VariableModuloSpecBuilder.() -> Unit) {
        val builder = VariableModuloSpecBuilder(init)
        statements.add(builder.build())
    }

    fun IncrementBefore(init: VariableIncrementBeforeSpecBuilder.() -> Unit) {
        val builder = VariableIncrementBeforeSpecBuilder(init)
        statements.add(builder.build())
    }

    fun IncrementAfter(init: VariableIncrementAfterSpecBuilder.() -> Unit) {
        val builder = VariableIncrementAfterSpecBuilder(init)
        statements.add(builder.build())
    }

    fun DecrementBefore(init: VariableDecrementBeforeSpecBuilder.() -> Unit) {
        val builder = VariableDecrementBeforeSpecBuilder(init)
        statements.add(builder.build())
    }

    fun DecrementAfter(init: VariableDecrementAfterSpecBuilder.() -> Unit) {
        val builder = VariableDecrementAfterSpecBuilder(init)
        statements.add(builder.build())
    }

    fun IncrementBefore(variable: String) {
        this.variableIncrementBefore {
            name(variable)
        }
    }

    fun IncrementAfter(variable: String) {
        this.variableIncrementAfter {
            name(variable)
        }
    }

    fun DecrementBefore(variable: String) {
        this.variableDecrementBefore {
            name(variable)
        }
    }

    fun DecrementAfter(variable: String) {
        this.variableDecrementAfter {
            name(variable)
        }
    }

    fun FunctionCall(init: FunctionCallBuilder.() -> Unit) {
        val builder = FunctionCallBuilder(init)
        statements.add(builder.build())
    }
}
