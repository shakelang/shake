package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.code.*

@Suppress("ktlint:standard:property-naming")
class VariableDeclarationBuilder
internal constructor(init: VariableDeclarationBuilder.() -> Unit) : VariableDeclarationSpec.VariableDeclarationSpecBuilder() {
    init {
        init()
    }
}

@Suppress("ktlint:standard:function-naming")
class CodeBuilder internal constructor(init: CodeBuilder.() -> Unit) : CodeSpec.CodeSpecBuilder() {
    init {
        init()
    }

    fun variableDeclaration(init: VariableDeclarationBuilder.() -> Unit) {
        val builder = VariableDeclarationBuilder(init)
        statements.add(builder.build())
    }

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

    fun variableAssignment(init: VariableAssignmentBuilder.() -> Unit) {
        val builder = VariableAssignmentBuilder(init)
        statements.add(builder.build())
    }

    fun variableAdditionAssignment(init: VariableAdditionSpecBuilder.() -> Unit) {
        val builder = VariableAdditionSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableSubtractionAssignment(init: VariableSubtractionSpecBuilder.() -> Unit) {
        val builder = VariableSubtractionSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableMultiplicationAssignment(init: VariableMultiplicationSpecBuilder.() -> Unit) {
        val builder = VariableMultiplicationSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableDivisionAssignment(init: VariableDivisionSpecBuilder.() -> Unit) {
        val builder = VariableDivisionSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableModuloAssignment(init: VariableModuloSpecBuilder.() -> Unit) {
        val builder = VariableModuloSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableIncrementBefore(init: VariableIncrementBeforeSpecBuilder.() -> Unit) {
        val builder = VariableIncrementBeforeSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableIncrementAfter(init: VariableIncrementAfterSpecBuilder.() -> Unit) {
        val builder = VariableIncrementAfterSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableDecrementBefore(init: VariableDecrementBeforeSpecBuilder.() -> Unit) {
        val builder = VariableDecrementBeforeSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableDecrementAfter(init: VariableDecrementAfterSpecBuilder.() -> Unit) {
        val builder = VariableDecrementAfterSpecBuilder(init)
        statements.add(builder.build())
    }

    fun variableIncrementBefore(variable: String) {
        this.variableIncrementBefore {
            name(variable)
        }
    }

    fun variableIncrementAfter(variable: String) {
        this.variableIncrementAfter {
            name(variable)
        }
    }

    fun variableDecrementBefore(variable: String) {
        this.variableDecrementBefore {
            name(variable)
        }
    }

    fun variableDecrementAfter(variable: String) {
        this.variableDecrementAfter {
            name(variable)
        }
    }

    fun functionCall(init: FunctionCallBuilder.() -> Unit) {
        val builder = FunctionCallBuilder(init)
        statements.add(builder.build())
    }
}

class WhileBuilder
internal constructor(init: WhileBuilder.() -> Unit) : WhileSpec.WhileSpecBuilder() {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class DoWhileBuilder
internal constructor(init: DoWhileBuilder.() -> Unit) : DoWhileSpec.DoWhileSpecBuilder() {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class ForBuilder
internal constructor(init: ForBuilder.() -> Unit) : ForSpec.ForSpecBuilder() {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class IfBuilder
internal constructor(init: IfBuilder.() -> Unit) : IfSpec.IfSpecBuilder() {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }

    fun elseBody(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        elseBody = builder.build()
    }

    fun elseIf(init: IfBuilder.() -> Unit) {
        elseBody {
            val builder = IfBuilder(init)
            statements.add(builder.build())
        }
    }
}

class ReturnBuilder
internal constructor(init: ReturnBuilder.() -> Unit) : ReturnSpec.ReturnSpecBuilder() {
    init {
        init()
    }
}

class VariableAssignmentBuilder
internal constructor(init: VariableAssignmentBuilder.() -> Unit) : VariableAssignmentSpec.VariableAssignmentSpecBuilder() {
    init {
        init()
    }
}

class VariableAdditionSpecBuilder
internal constructor(init: VariableAdditionSpecBuilder.() -> Unit) : VariableAdditionAssignmentSpec.VariableAdditionAssignmentSpecBuilder() {
    init {
        init()
    }
}

class VariableSubtractionSpecBuilder
internal constructor(init: VariableSubtractionSpecBuilder.() -> Unit) : VariableSubtractionAssignmentSpec.VariableSubtractionAssignmentSpecBuilder() {
    init {
        init()
    }
}

class VariableMultiplicationSpecBuilder
internal constructor(init: VariableMultiplicationSpecBuilder.() -> Unit) : VariableMultiplicationAssignmentSpec.VariableMultiplicationAssignmentSpecBuilder() {
    init {
        init()
    }
}

class VariableDivisionSpecBuilder
internal constructor(init: VariableDivisionSpecBuilder.() -> Unit) : VariableDivisionAssignmentSpec.VariableDivisionAssignmentSpecBuilder() {
    init {
        init()
    }
}

class VariableModuloSpecBuilder
internal constructor(init: VariableModuloSpecBuilder.() -> Unit) : VariableModuloAssignmentSpec.VariableModuloAssignmentSpecBuilder() {
    init {
        init()
    }
}

class VariableIncrementBeforeSpecBuilder
internal constructor(init: VariableIncrementBeforeSpecBuilder.() -> Unit) : VariableIncrementBeforeSpec.VariableIncrementBeforeSpecBuilder() {
    init {
        init()
    }
}

class VariableIncrementAfterSpecBuilder
internal constructor(init: VariableIncrementAfterSpecBuilder.() -> Unit) : VariableIncrementAfterSpec.VariableIncrementAfterSpecBuilder() {
    init {
        init()
    }
}

class VariableDecrementBeforeSpecBuilder
internal constructor(init: VariableDecrementBeforeSpecBuilder.() -> Unit) : VariableDecrementBeforeSpec.VariableDecrementBeforeSpecBuilder() {
    init {
        init()
    }
}

class VariableDecrementAfterSpecBuilder
internal constructor(init: VariableDecrementAfterSpecBuilder.() -> Unit) : VariableDecrementAfterSpec.VariableDecrementAfterSpecBuilder() {
    init {
        init()
    }
}

class FunctionCallBuilder
internal constructor(init: FunctionCallBuilder.() -> Unit) : FunctionCallSpec.FunctionCallSpecBuilder() {
    init {
        init()
    }
}
