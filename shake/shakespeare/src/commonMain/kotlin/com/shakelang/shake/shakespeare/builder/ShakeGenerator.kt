@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.*
import com.shakelang.shake.shakespeare.spec.code.*

interface Builder : BuilderContext
interface BuilderContext : ValueCreationContext

class VariableDeclarationBuilder
internal constructor(init: VariableDeclarationBuilder.() -> Unit) : VariableDeclarationSpec.VariableDeclarationSpecBuilder(), Builder {
    init {
        init()
    }
}

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

class WhileBuilder
internal constructor(init: WhileBuilder.() -> Unit) : WhileSpec.WhileSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class DoWhileBuilder
internal constructor(init: DoWhileBuilder.() -> Unit) : DoWhileSpec.DoWhileSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class ForBuilder
internal constructor(init: ForBuilder.() -> Unit) : ForSpec.ForSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class IfBuilder
internal constructor(init: IfBuilder.() -> Unit) : IfSpec.IfSpecBuilder(), Builder {
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
internal constructor(init: ReturnBuilder.() -> Unit) : ReturnSpec.ReturnSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableAssignmentBuilder
internal constructor(init: VariableAssignmentBuilder.() -> Unit) : VariableAssignmentSpec.VariableAssignmentSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableAdditionSpecBuilder
internal constructor(init: VariableAdditionSpecBuilder.() -> Unit) : VariableAdditionAssignmentSpec.VariableAdditionAssignmentSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableSubtractionSpecBuilder
internal constructor(init: VariableSubtractionSpecBuilder.() -> Unit) : VariableSubtractionAssignmentSpec.VariableSubtractionAssignmentSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableMultiplicationSpecBuilder
internal constructor(init: VariableMultiplicationSpecBuilder.() -> Unit) : VariableMultiplicationAssignmentSpec.VariableMultiplicationAssignmentSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableDivisionSpecBuilder
internal constructor(init: VariableDivisionSpecBuilder.() -> Unit) : VariableDivisionAssignmentSpec.VariableDivisionAssignmentSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableModuloSpecBuilder
internal constructor(init: VariableModuloSpecBuilder.() -> Unit) : VariableModuloAssignmentSpec.VariableModuloAssignmentSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableIncrementBeforeSpecBuilder
internal constructor(init: VariableIncrementBeforeSpecBuilder.() -> Unit) : VariableIncrementBeforeSpec.VariableIncrementBeforeSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableIncrementAfterSpecBuilder
internal constructor(init: VariableIncrementAfterSpecBuilder.() -> Unit) : VariableIncrementAfterSpec.VariableIncrementAfterSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableDecrementBeforeSpecBuilder
internal constructor(init: VariableDecrementBeforeSpecBuilder.() -> Unit) : VariableDecrementBeforeSpec.VariableDecrementBeforeSpecBuilder(), Builder {
    init {
        init()
    }
}

class VariableDecrementAfterSpecBuilder
internal constructor(init: VariableDecrementAfterSpecBuilder.() -> Unit) : VariableDecrementAfterSpec.VariableDecrementAfterSpecBuilder(), Builder {
    init {
        init()
    }
}

class FunctionCallBuilder
internal constructor(init: FunctionCallBuilder.() -> Unit) : FunctionCallSpec.FunctionCallSpecBuilder(), Builder {
    init {
        init()
    }
}

class FieldDeclarationBuilder
internal constructor(init: FieldDeclarationBuilder.() -> Unit) : FieldSpec.FieldSpecBuilder(), Builder {
    init {
        init()
    }
}

class ParameterDeclarationBuilder
internal constructor(init: ParameterDeclarationBuilder.() -> Unit) : ParameterSpec.ParameterSpecBuilder(), Builder {
    init {
        init()
    }
}

class MethodDeclarationBuilder
internal constructor(init: MethodDeclarationBuilder.() -> Unit) : MethodSpec.MethodSpecBuilder(), Builder {
    init {
        init()
    }

    fun parameter(init: ParameterDeclarationBuilder.() -> Unit) {
        val builder = ParameterDeclarationBuilder(init)
        parameters.add(builder.build())
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }

    fun args(init: MethodDeclarationBuilder.() -> Unit) {
        init()
    }
}

class ClassDeclarationBuilder
internal constructor(init: ClassDeclarationBuilder.() -> Unit) : ClassSpec.ClassSpecBuilder(), Builder {
    init {
        init()
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit) {
        val builder = FieldDeclarationBuilder(init)
        fields.add(builder.build())
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit) {
        val builder = MethodDeclarationBuilder(init)
        methods.add(builder.build())
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit) {
        val builder = ClassDeclarationBuilder(init)
        this.classes.add(builder.build())
    }
}

class ShakeFileBuilder
internal constructor(init: ShakeFileBuilder.() -> Unit) : Builder {

    val contents = mutableListOf<String>()

    init {
        init()
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit) {
        val builder = ClassDeclarationBuilder(init)
        contents.add(builder.build().generate(GenerationContext()))
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit, name: String) {
        val builder = ClassDeclarationBuilder(init)
        builder.name = Identifier(name)
        val classSpec = builder.build()
        contents.add(classSpec.generate(GenerationContext()))
    }

    fun Class(init: ClassDeclarationBuilder.() -> Unit, name: Identifier) {
        val builder = ClassDeclarationBuilder(init)
        builder.name = name
        val classSpec = builder.build()
        contents.add(classSpec.generate(GenerationContext()))
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit) {
        val builder = FieldDeclarationBuilder(init)
        contents.add(builder.build().generate(GenerationContext()))
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit, name: String) {
        val builder = FieldDeclarationBuilder(init)
        builder.name = Identifier(name)
        val fieldSpec = builder.build()
        contents.add(fieldSpec.generate(GenerationContext()))
    }

    fun Field(init: FieldDeclarationBuilder.() -> Unit, name: Identifier) {
        val builder = FieldDeclarationBuilder(init)
        builder.name = name
        val fieldSpec = builder.build()
        contents.add(fieldSpec.generate(GenerationContext()))
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit) {
        val builder = MethodDeclarationBuilder(init)
        contents.add(builder.build().generate(GenerationContext()))
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit, name: String) {
        val builder = MethodDeclarationBuilder(init)
        builder.name = Identifier(name)
        val methodSpec = builder.build()
        contents.add(methodSpec.generate(GenerationContext()))
    }

    fun Method(init: MethodDeclarationBuilder.() -> Unit, name: Identifier) {
        val builder = MethodDeclarationBuilder(init)
        builder.name = name
        val methodSpec = builder.build()
        contents.add(methodSpec.generate(GenerationContext()))
    }

    fun Package(name: String) {
        contents.add("package $name")
    }

    fun Package(name: Identifier) {
        contents.add("package $name")
    }

    fun Import(name: String) {
        contents.add("import $name")
    }

    fun Import(name: Identifier) {
        contents.add("import $name")
    }

    fun newline() {
        contents.add("")
    }

    fun generate(): String {
        return contents.joinToString("\n")
    }
}

fun buildShakeFile(init: ShakeFileBuilder.() -> Unit): String {
    val builder = ShakeFileBuilder(init)
    return builder.generate()
}

interface ValueCreationContext {
    fun string(value: String) = StringLiteralSpec.builder().value(value).build()

    fun byte(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun byte(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun byte(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun short(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun short(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun short(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun int(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun int(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun int(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun long(value: Byte) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Short) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Int) = IntLiteralSpec.builder().value(value).build()
    fun long(value: Long) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Float) = IntLiteralSpec.builder().value(value.toInt()).build()
    fun long(value: Double) = IntLiteralSpec.builder().value(value.toInt()).build()

    fun float(value: Byte) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Short) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Int) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Long) = FloatLiteralSpec.builder().value(value.toFloat()).build()
    fun float(value: Float) = FloatLiteralSpec.builder().value(value).build()
    fun float(value: Double) = FloatLiteralSpec.builder().value(value.toFloat()).build()

    fun double(value: Byte) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Short) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Int) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Long) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Float) = FloatLiteralSpec.builder().value(value.toDouble()).build()
    fun double(value: Double) = FloatLiteralSpec.builder().value(value).build()

    fun boolean(value: Boolean) = BooleanLiteralSpec.builder().value(value).build()

    fun nullValue() = NullLiteralSpec.INSTANCE

    fun variable(name: String) = VariableReferenceSpec.builder().name(name).build()
    fun variableReference(name: String) = VariableReferenceSpec.builder().name(name).build()

    fun variableAssignment(init: VariableAssignmentBuilder.() -> Unit) = VariableAssignmentBuilder(init).build()
    fun variableAdditionAssignment(init: VariableAdditionSpecBuilder.() -> Unit) = VariableAdditionSpecBuilder(init).build()
    fun variableSubtractionAssignment(init: VariableSubtractionSpecBuilder.() -> Unit) = VariableSubtractionSpecBuilder(init).build()
    fun variableMultiplicationAssignment(init: VariableMultiplicationSpecBuilder.() -> Unit) = VariableMultiplicationSpecBuilder(init).build()
    fun variableDivisionAssignment(init: VariableDivisionSpecBuilder.() -> Unit) = VariableDivisionSpecBuilder(init).build()
    fun variableModuloAssignment(init: VariableModuloSpecBuilder.() -> Unit) = VariableModuloSpecBuilder(init).build()
    fun variableIncrementBefore(init: VariableIncrementBeforeSpecBuilder.() -> Unit) = VariableIncrementBeforeSpecBuilder(init).build()
    fun variableIncrementAfter(init: VariableIncrementAfterSpecBuilder.() -> Unit) = VariableIncrementAfterSpecBuilder(init).build()
    fun variableDecrementBefore(init: VariableDecrementBeforeSpecBuilder.() -> Unit) = VariableDecrementBeforeSpecBuilder(init).build()
    fun variableDecrementAfter(init: VariableDecrementAfterSpecBuilder.() -> Unit) = VariableDecrementAfterSpecBuilder(init).build()
    fun variableIncrementBefore(variable: String) = VariableIncrementBeforeSpecBuilder { name(variable) }.build()
    fun variableIncrementAfter(variable: String) = VariableIncrementAfterSpecBuilder { name(variable) }.build()
    fun variableDecrementBefore(variable: String) = VariableDecrementBeforeSpecBuilder { name(variable) }.build()
    fun variableDecrementAfter(variable: String) = VariableDecrementAfterSpecBuilder { name(variable) }.build()
    fun functionCall(init: FunctionCallBuilder.() -> Unit) = FunctionCallBuilder(init).build()
}
