package io.github.shakelang.shake.js

import io.github.shakelang.shake.generation.ShakeGenerator
import io.github.shakelang.shake.js.output.*
import io.github.shakelang.shake.processor.program.*
import io.github.shakelang.shake.processor.program.code.*
import io.github.shakelang.shake.processor.program.code.statements.*
import io.github.shakelang.shake.processor.program.code.values.*

class ShakeJsGenerator {

    fun visitValue(v: ShakeValue): JsValue {
        return when(v) {
            is ShakeDoubleLiteral -> visitDouble(v)
            is ShakeIntegerLiteral -> visitInteger(v)
            is ShakeBooleanLiteral -> visitBoolean(v)
            is ShakeAddition -> visitAddition(v)
            is ShakeSubtraction -> visitSubtraction(v)
            is ShakeMultiplication -> visitMultiplication(v)
            is ShakeDivision -> visitDivision(v)
            is ShakeModulus -> visitModulus(v)
            is ShakePower -> visitPower(v)
            is ShakeAssignment -> visitAssignment(v)
            is ShakeAddAssignment -> visitAdditionAssignment(v)
            is ShakeSubAssignment -> visitSubtractionAssignment(v)
            is ShakeMulAssignment -> visitMultiplicationAssignment(v)
            is ShakeDivAssignment -> visitDivisionAssignment(v)
            is ShakeModAssignment -> visitModulusAssignment(v)
            is ShakePowerAssignment -> visitPowerAssignment(v)
            is ShakeIncrementBefore -> visitIncrementBefore(v)
            is ShakeIncrementAfter -> visitIncrementAfter(v)
            is ShakeDecrementBefore -> visitDecrementBefore(v)
            is ShakeDecrementAfter -> visitDecrementAfter(v)
            is ShakeUsage -> visitUsage(v)
            is ShakeEquals -> visitEquals(v)
            is ShakeNotEquals -> visitNotEquals(v)
            is ShakeGreaterThan -> visitGreaterThan(v)
            is ShakeGreaterThanOrEqual -> visitGreaterThanOrEqual(v)
            is ShakeLessThan -> visitLessThan(v)
            is ShakeLessThanOrEqual -> visitLessThanOrEqual(v)
            is ShakeAnd -> visitAnd(v)
            is ShakeOr -> visitOr(v)
            is ShakeNot -> visitNot(v)
            is ShakeInvocation -> visitInvocation(v)
            is ShakeNew -> visitNew(v)
            is ShakeCast -> visitCast(v)
            else -> throw IllegalArgumentException("Unsupported value type: ${v::class.simpleName}")
        }
    }

    fun visitStatement(s: ShakeStatement): JsStatement {
        return when(s) {
            is ShakePower -> visitPower(s)
            is ShakeAssignment -> visitAssignment(s)
            is ShakeAddAssignment -> visitAdditionAssignment(s)
            is ShakeSubAssignment -> visitSubtractionAssignment(s)
            is ShakeMulAssignment -> visitMultiplicationAssignment(s)
            is ShakeDivAssignment -> visitDivisionAssignment(s)
            is ShakeModAssignment -> visitModulusAssignment(s)
            is ShakePowerAssignment -> visitPowerAssignment(s)
            is ShakeIncrementBefore -> visitIncrementBefore(s)
            is ShakeIncrementAfter -> visitIncrementAfter(s)
            is ShakeDecrementBefore -> visitDecrementBefore(s)
            is ShakeDecrementAfter -> visitDecrementAfter(s)
            is ShakeInvocation -> visitInvocation(s)
            is ShakeNew -> visitNew(s)
            is ShakeDoWhile -> visitDoWhile(s)
            is ShakeWhile -> visitWhile(s)
            is ShakeFor -> visitFor(s)
            is ShakeIf -> visitIf(s)
            is ShakeReturn -> visitReturn(s)
            is ShakeVariableDeclaration -> visitVariableDeclaration(s)
            else -> throw IllegalArgumentException("Unsupported value type: ${s::class.simpleName}")
        }

    }

    fun visitCode(t: ShakeCode): JsTree {
        return JsTree(t.statements.map { visitStatement(it) })
    }

    fun visitDouble(n: ShakeDoubleLiteral): JsDouble {
        return JsDouble(n.value)
    }

    fun visitInteger(n: ShakeIntegerLiteral): JsInteger {
        return JsInteger(n.value)
    }

    fun visitBoolean(n: ShakeBooleanLiteral): JsLiteral {
        return if(n.value) JsLiteral.TRUE else JsLiteral.FALSE
    }

    fun visitAddition(n: ShakeAddition): JsAdd {
        return JsAdd(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitSubtraction(n: ShakeSubtraction): JsSubtract {
        return JsSubtract(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitMultiplication(n: ShakeMultiplication): JsMultiply {
        return JsMultiply(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitDivision(n: ShakeDivision): JsDivide {
        return JsDivide(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitModulus(n: ShakeModulus): JsModulo {
        return JsModulo(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitPower(n: ShakePower): JsFunctionCall {
        return JsFunctionCall(JsField("pow", parent = JsField("Math")),
            args = listOf(visitValue(n.left).toValue(), visitValue(n.right).toValue()))
    }

    fun visitVariableDeclaration(n: ShakeVariableDeclaration): JsDeclaration {
        if(n.isFinal) {
            if(n.initialValue == null) throw IllegalStateException("Final variable must have an assignment")
            return JsConstantDeclaration(n.name, visitValue(n.initialValue!!).toValue())
        }
        if(n.initialValue == null) return JsVariableDeclaration(n.name)
        return JsVariableDeclaration(n.name, visitValue(n.initialValue!!).toValue())
    }

    fun visitAssignable(a: ShakeAssignable): JsAssignable {
        if(a is ShakeVariableDeclaration) return JsAssignable(JsField(a.name))
        if(a is ShakeClassField) {
            if(a.isStatic) return JsAssignable(JsField(a.name, parent = JsField(a.clazz.name)))
            return JsAssignable(JsField(a.name, parent = JsField("this")))
        }
        if(a is ShakeField) {
            return JsAssignable(JsField(a.name))
        }
        TODO("not implemented")
    }

    fun visitAssignment(n: ShakeAssignment): JsAssignment {
        val variable = visitAssignable(n.variable)
        return variable.assign(visitValue(n.value))
    }

    fun visitAdditionAssignment(n: ShakeAddAssignment): JsAddAssignment {
        val variable = visitAssignable(n.variable)
        return variable.addAssign(visitValue(n.value))
    }

    fun visitSubtractionAssignment(n: ShakeSubAssignment): JsSubtractAssignment {
        val variable = visitAssignable(n.variable)
        return variable.subtractAssign(visitValue(n.value))
    }

    fun visitMultiplicationAssignment(n: ShakeMulAssignment): JsMultiplyAssignment {
        val variable = visitAssignable(n.variable)
        return variable.multiplyAssign(visitValue(n.value))
    }

    fun visitDivisionAssignment(n: ShakeDivAssignment): JsDivideAssignment {
        val variable = visitAssignable(n.variable)
        return variable.divideAssign(visitValue(n.value))
    }

    fun visitModulusAssignment(n: ShakeModAssignment): JsModuloAssignment {
        val variable = visitAssignable(n.variable)
        return variable.moduloAssign(visitValue(n.value))
    }

    fun visitPowerAssignment(n: ShakePowerAssignment): JsAssignment {
        val variable = visitAssignable(n.variable)
        return variable.assign(JsFunctionCall(JsField("pow", parent = JsField("Math")),
            args = listOf(visitValue(n.value).toValue(), visitValue(n.value).toValue())))
    }

    fun visitIncrementBefore(n: ShakeIncrementBefore): JsBeforeIncrement {
        val variable = visitAssignable(n.variable)
        return variable.incrementBefore()
    }

    fun visitIncrementAfter(n: ShakeIncrementAfter): JsAfterIncrement {
        val variable = visitAssignable(n.variable)
        return variable.incrementAfter()
    }

    fun visitDecrementBefore(n: ShakeDecrementBefore): JsBeforeDecrement {
        val variable = visitAssignable(n.variable)
        return variable.decrementBefore()
    }

    fun visitDecrementAfter(n: ShakeDecrementAfter): JsAfterDecrement {
        val variable = visitAssignable(n.variable)
        return variable.decrementAfter()
    }

    fun visitUsage(n: ShakeUsage): JsField {
        if(n is ShakeVariableUsage) return JsField(n.name)
        if(n is ShakeClassFieldUsage) {
            if(n.receiver != null) return JsField(n.name, parent = visitValue(n.receiver!!))
            if(n.declaration.isStatic) return JsField(n.name, parent = JsField(n.declaration.clazz.name))
            return JsField(n.name, parent = JsField("this"))
        }
        if(n is ShakeFieldUsage) {
            return JsField(n.name)
        }
        throw IllegalStateException("Unknown usage: $n")
    }

    fun visitEquals(n: ShakeEquals): JsEquals {
        return JsEquals(visitValue(n.left), visitValue(n.right))
    }

    fun visitNotEquals(n: ShakeNotEquals): JsNotEquals {
        return JsNotEquals(visitValue(n.left), visitValue(n.right))
    }

    fun visitLessThan(n: ShakeLessThan): JsLessThan {
        return JsLessThan(visitValue(n.left), visitValue(n.right))
    }

    fun visitLessThanOrEqual(n: ShakeLessThanOrEqual): JsLessThanOrEqual {
        return JsLessThanOrEqual(visitValue(n.left), visitValue(n.right))
    }

    fun visitGreaterThan(n: ShakeGreaterThan): JsGreaterThan {
        return JsGreaterThan(visitValue(n.left), visitValue(n.right))
    }

    fun visitGreaterThanOrEqual(n: ShakeGreaterThanOrEqual): JsGreaterThanOrEqual {
        return JsGreaterThanOrEqual(visitValue(n.left), visitValue(n.right))
    }

    fun visitAnd(n: ShakeAnd): JsAnd {
        return JsAnd(visitValue(n.left), visitValue(n.right))
    }

    fun visitOr(n: ShakeOr): JsOr {
        return JsOr(visitValue(n.left), visitValue(n.right))
    }

    fun visitXor(n: ShakeXor): JsXor {
        return JsXor(visitValue(n.left), visitValue(n.right))
    }

    fun visitNot(n: ShakeNot): JsNot {
        return JsNot(visitValue(n.value))
    }


    fun visitWhile(n: ShakeWhile): JsWhile {
        return JsWhile(visitValue(n.condition), visitCode(n.body))
    }

    fun visitDoWhile(n: ShakeDoWhile): JsDoWhile {
        return JsDoWhile(visitValue(n.condition), visitCode(n.body))
    }

    fun visitFor(n: ShakeFor): JsFor {
        return JsFor(visitStatement(n.init), visitValue(n.condition), visitStatement(n.update), visitCode(n.body))
    }

    fun visitIf(n: ShakeIf): JsIf {
        return JsIf(visitValue(n.condition), visitCode(n.body), n.elseBody?.let { visitCode(it) })
    }

    fun visitParameter(n: ShakeParameter): JsParameter {
        // TODO Default values
        return JsParameter(n.name)
    }

    fun visitFunctionDeclaration(n: ShakeFunction): JsFunctionDeclaration {
        val name = n.name
        val parameters = n.parameters.map { visitParameter(it) }
        val body = visitCode(n.body)
        return JsFunctionDeclaration(name, parameters, body)
    }

    fun visitMethodDeclaration(n: ShakeMethod): JsFunctionDeclaration {
        val name = n.name
        val parameters = n.parameters.map { visitParameter(it) }
        val body = visitCode(n.body)
        return JsFunctionDeclaration(name, parameters, body)
    }

    fun visitFieldDeclaration(n: ShakeField): JsDeclaration {
        if(n.isFinal) {
            if(n.initialValue == null) throw IllegalStateException("Final field must have initial value")
            return JsConstantDeclaration(n.name, visitValue(n.initialValue!!))
        }
        if(n.initialValue != null) return JsVariableDeclaration(n.name, visitValue(n.initialValue!!))
        return JsVariableDeclaration(n.name)
    }


    fun visitClassDeclaration(n: ShakeClass): JsClassDeclaration {
        return JsClassDeclaration(
            n.name,
            functions = n.methods.map { visitMethodDeclaration(it) },
            staticFunctions = n.staticMethods.map { visitMethodDeclaration(it) },
            fields = n.fields.map { visitFieldDeclaration(it) },
            staticFields = n.staticFields.map { visitFieldDeclaration(it) }
        )
    }

    fun visitNew(n: ShakeNew): JsNew {
        // TODO: Implement
        return JsNew(JsField(n.reference.clazz.name), n.arguments.map { visitValue(it) })
    }

    fun visitInvocation(n: ShakeInvocation): JsFunctionCall {

        val callable = n.callable

        when(callable) {
            is ShakeMethod -> {

                if(callable.isStatic)
                    return JsFunctionCall(JsField(callable.name, JsField(callable.clazz.name)), n.arguments.map { visitValue(it) })

                if(n.parent != null)
                    return JsFunctionCall(JsField(callable.name, visitValue(n.parent!!)), n.arguments.map { visitValue(it) })

                return JsFunctionCall(JsField(callable.name, JsField("this")), n.arguments.map { visitValue(it) })

            }
            is ShakeFunction -> return JsFunctionCall(JsField(callable.name), n.arguments.map { visitValue(it) })
            is ShakeLambdaDeclaration -> TODO()
            // is ShakeLambdaDeclaration -> return JsFunctionCall(callable, n.arguments.map { visitValue(it) })
            else -> throw IllegalStateException("Unknown callable type")
        }
    }

    fun visitCast(n: ShakeCast): JsValue {
        return visitValue(n.value)
    }

    fun visitReturn(n: ShakeReturn): JsReturn {
        return JsReturn(n.value?.let { visitValue(it) })
    }

    fun visitPackage(prj: JsProject, parent: JsPackage?, n: ShakePackage): JsPackage {
        return JsPackage(prj, parent, n.name, n.subpackages.toTypedArray(), n.classes.toTypedArray(), n.functions.toTypedArray(), n.fields.toTypedArray())
    }

    fun visitProject(n: ShakeProject): JsProject {
        return JsProject(this, n.subpackages.toTypedArray(), n.classes.toTypedArray(), n.functions.toTypedArray(), n.fields.toTypedArray())
    }

}

class JsProject {

    val gen: ShakeJsGenerator
    val subpackages: List<JsPackage>
    val classes: List<JsClassDeclaration>
    val functions: List<JsFunctionDeclaration>
    val fields: List<JsDeclaration>

    constructor(
        generator: ShakeJsGenerator,
        subpackages: List<JsPackage>,
        classes: List<JsClassDeclaration>,
        functions: List<JsFunctionDeclaration>,
        fields: List<JsDeclaration>
    ) {
        this.gen = generator
        this.subpackages = subpackages
        this.classes = classes
        this.functions = functions
        this.fields = fields
    }

    constructor(
        generator: ShakeJsGenerator,
        subpackages: Array<ShakePackage>,
        classes: Array<ShakeClass>,
        functions: Array<ShakeFunction>,
        fields: Array<ShakeField>
    ) {
        this.gen = generator
        this.subpackages = subpackages.map { generator.visitPackage(this, null, it) }
        this.classes = classes.map { generator.visitClassDeclaration(it) }
        this.functions = functions.map { generator.visitFunctionDeclaration(it) }
        this.fields = fields.map { generator.visitFieldDeclaration(it) }
    }

    fun toMap(): Map<String, Any> {
        return mapOf(
            "packages" to subpackages.map { it.toMap() },
            "classes" to classes.map { it.generate(2) },
            "functions" to functions.map { it.generate(2) },
            "fields" to fields.map { it.generate(2) }
        )
    }
}

class JsPackage {

    val prj: JsProject
    val parent: JsPackage?
    val name: String
    val subpackages: List<JsPackage>
    val classes: List<JsClassDeclaration>
    val functions: List<JsFunctionDeclaration>
    val fields: List<JsDeclaration>

    constructor(
        prj: JsProject,
        parent: JsPackage?,
        name: String,
        subpackages: List<JsPackage>,
        classes: List<JsClassDeclaration>,
        functions: List<JsFunctionDeclaration>,
        fields: List<JsDeclaration>
    ) {
        this.prj = prj
        this.parent = parent
        this.name = name
        this.subpackages = subpackages
        this.classes = classes
        this.functions = functions
        this.fields = fields
    }

    constructor(
        prj: JsProject,
        parent: JsPackage?,
        name: String,
        subpackages: Array<ShakePackage>,
        classes: Array<ShakeClass>,
        functions: Array<ShakeFunction>,
        fields: Array<ShakeField>
    ) {
        this.prj = prj
        this.parent = parent
        this.name = name
        this.subpackages = subpackages.map { prj.gen.visitPackage(prj, this, it) }
        this.classes = classes.map { prj.gen.visitClassDeclaration(it) }
        this.functions = functions.map { prj.gen.visitFunctionDeclaration(it) }
        this.fields = fields.map { prj.gen.visitFieldDeclaration(it) }
    }

    fun toMap(): Map<String, Any> {
        return mapOf(
            "name" to name,
            "subpackages" to subpackages.map { it.toMap() },
            "classes" to classes.map { it.generate(2) },
            "functions" to functions.map { it.generate(2) },
            "fields" to fields.map { it.generate(2) }
        )
    }


}