package com.shakelang.shake.js

import com.shakelang.shake.js.native.JsNatives
import com.shakelang.shake.js.output.*
import io.github.shakelang.shake.js.output.*
import io.github.shakelang.shake.processor.program.types.*
import io.github.shakelang.shake.processor.program.types.code.*
import io.github.shakelang.shake.processor.program.types.code.statements.*
import io.github.shakelang.shake.processor.program.types.code.values.*

class ShakeJsGenerator {

    fun visitValue(v: ShakeValue): JsValue {
        return when (v) {
            is ShakeDoubleLiteral -> visitDouble(v)
            is ShakeIntLiteral -> visitInteger(v)
            is ShakeStringLiteral -> visitString(v)
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
            is ShakePowAssignment -> visitPowerAssignment(v)
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
            is ShakeInvocation -> visitInvocationValue(v)
            is ShakeNew -> visitNew(v)
            is ShakeCast -> visitCast(v)
            else -> throw IllegalArgumentException("Unsupported value type: ${v::class.simpleName}")
        }
    }

    fun visitStatement(s: ShakeStatement): JsStatement {
        return when (s) {
            is ShakePower -> visitPower(s)
            is ShakeAssignment -> visitAssignment(s)
            is ShakeAddAssignment -> visitAdditionAssignment(s)
            is ShakeSubAssignment -> visitSubtractionAssignment(s)
            is ShakeMulAssignment -> visitMultiplicationAssignment(s)
            is ShakeDivAssignment -> visitDivisionAssignment(s)
            is ShakeModAssignment -> visitModulusAssignment(s)
            is ShakePowAssignment -> visitPowerAssignment(s)
            is ShakeIncrementBefore -> visitIncrementBefore(s)
            is ShakeIncrementAfter -> visitIncrementAfter(s)
            is ShakeDecrementBefore -> visitDecrementBefore(s)
            is ShakeDecrementAfter -> visitDecrementAfter(s)
            is ShakeInvocation -> visitInvocationStatement(s)
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

    fun visitInteger(n: ShakeIntLiteral): JsInteger {
        return JsInteger(n.value)
    }

    fun visitString(n: ShakeStringLiteral): JsStringLiteral {
        return JsStringLiteral(n.value)
    }

    fun visitBoolean(n: ShakeBooleanLiteral): JsLiteral {
        return if (n.value) JsLiteral.TRUE else JsLiteral.FALSE
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
        return JsFunctionCall(
            JsField("pow", parent = JsField("Math")),
            args = listOf(visitValue(n.left).toValue(), visitValue(n.right).toValue())
        )
    }

    fun visitVariableDeclaration(n: ShakeVariableDeclaration): JsDeclaration {
        if (n.isFinal) {
            if (n.initialValue == null) throw IllegalStateException("Final variable must have an assignment")
            return JsConstantDeclaration(n.name, visitValue(n.initialValue!!).toValue())
        }
        if (n.initialValue == null) return JsVariableDeclaration(n.name)
        return JsVariableDeclaration(n.name, visitValue(n.initialValue!!).toValue())
    }

    fun visitAssignable(a: ShakeAssignable): JsAssignable {
        if (a is ShakeVariableDeclaration) return JsAssignable(JsField(a.name))
        if (a is ShakeField) {
            if (a.clazz == null) return JsAssignable(JsField(a.name))
            if (a.isStatic) return JsAssignable(JsField(a.name, parent = JsField(a.clazz!!.name)))
            return JsAssignable(JsField(a.name, parent = JsField("this")))
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

    fun visitPowerAssignment(n: ShakePowAssignment): JsAssignment {
        val variable = visitAssignable(n.variable)
        return variable.assign(
            JsFunctionCall(
                JsField("pow", parent = JsField("Math")),
                args = listOf(visitValue(n.value).toValue(), visitValue(n.value).toValue())
            )
        )
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
        if (n is ShakeVariableUsage) return JsField(n.name)
        if (n is ShakeClassFieldUsage) {
            if (n.receiver != null) return JsField(n.name, parent = visitValue(n.receiver!!))
            if (n.declaration.isStatic) {
                return JsField(
                    n.name,
                    parent = JsField(
                        n.declaration.clazz?.name ?: throw IllegalStateException("Static field must have a class")
                    )
                )
            }
            return JsField(n.name, parent = JsField("this"))
        }
        if (n is ShakeFieldUsage) {
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

    fun visitFunctionDeclaration(n: ShakeMethod): JsFunctionDeclaration {
        val name = n.name
        val parameters = n.parameters.map { visitParameter(it) }
        val body = visitCode(n.body ?: throw IllegalStateException("Method must have a body"))
        return JsFunctionDeclaration(name, parameters, body)
    }

    fun visitMethodDeclaration(n: ShakeMethod): JsFunctionDeclaration {
        val name = n.name
        val parameters = n.parameters.map { visitParameter(it) }
        val body = visitCode(n.body ?: throw IllegalStateException("Method must have a body"))
        return JsFunctionDeclaration(name, parameters, body)
    }

    fun visitFieldDeclaration(n: ShakeField): JsDeclaration {
        if (n.isFinal) {
            if (n.initialValue == null) throw IllegalStateException("Final field must have initial value")
            return JsConstantDeclaration(n.name, visitValue(n.initialValue!!))
        }
        if (n.initialValue != null) return JsVariableDeclaration(n.name, visitValue(n.initialValue!!))
        return JsVariableDeclaration(n.name)
    }

    fun visitClassDeclaration(n: ShakeClass): JsClassDeclaration {
        return JsClassDeclaration(
            n.name,
            functions = n.methods.filter { !it.isNative }.map { visitMethodDeclaration(it) },
            staticFunctions = n.staticMethods.filter { !it.isNative }.map { visitMethodDeclaration(it) },
            fields = n.fields.filter { !it.isNative }.map { visitFieldDeclaration(it) },
            staticFields = n.staticFields.filter { !it.isNative }.map { visitFieldDeclaration(it) }
        )
    }

    fun visitNew(n: ShakeNew): JsNew {
        // TODO: Implement
        return JsNew(JsField(n.reference.clazz.name), n.arguments.map { visitValue(it) })
    }

    fun visitInvocationValue(n: ShakeInvocation): JsValue {
        val callable = n.callable

        when (callable) {
            is ShakeMethod -> {
                if (callable.isNative) {
                    return JsNatives.getNativeFunction(callable).handleValue(this, n)
                }

                if (callable.clazz == null) {
                    return JsFunctionCall(
                        JsField(callable.name),
                        n.arguments.map { visitValue(it) }
                    )
                }

                if (callable.isStatic) {
                    return JsFunctionCall(
                        JsField(callable.name, JsField(callable.clazz!!.name)),
                        n.arguments.map { visitValue(it) }
                    )
                }

                if (n.parent != null) {
                    return JsFunctionCall(
                        JsField(callable.name, visitValue(n.parent!!)),
                        n.arguments.map { visitValue(it) }
                    )
                }

                return JsFunctionCall(JsField(callable.name, JsField("this")), n.arguments.map { visitValue(it) })
            }

            is ShakeLambdaDeclaration -> TODO()
            // is ShakeLambdaDeclaration -> return JsFunctionCall(callable, n.arguments.map { visitValue(it) })
            else -> throw IllegalStateException("Unknown callable type")
        }
    }

    fun visitInvocationStatement(n: ShakeInvocation): JsStatement {
        val callable = n.callable

        when (callable) {
            is ShakeMethod -> {
                if (callable.isNative) {
                    return JsNatives.getNativeFunction(callable).handleStatement(this, n)
                }

                if (callable.clazz == null) {
                    return JsFunctionCall(
                        JsField(callable.name),
                        n.arguments.map { visitValue(it) }
                    )
                }

                if (callable.isStatic) {
                    return JsFunctionCall(
                        JsField(callable.name, JsField(callable.clazz!!.name)),
                        n.arguments.map { visitValue(it) }
                    )
                }

                if (n.parent != null) {
                    return JsFunctionCall(
                        JsField(callable.name, visitValue(n.parent!!)),
                        n.arguments.map { visitValue(it) }
                    )
                }

                return JsFunctionCall(JsField(callable.name, JsField("this")), n.arguments.map { visitValue(it) })
            }

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
        return JsPackage(
            prj,
            parent,
            n.name,
            n.subpackages.toTypedArray(),
            n.classes.filter { !it.isNative }.toTypedArray(),
            n.functions.filter { !it.isNative }.toTypedArray(),
            n.fields.filter { !it.isNative }.toTypedArray()
        )
    }

    fun visitProject(n: ShakeProject): JsProject {
        return JsProject(
            this,
            n.subpackages.toTypedArray(),
            n.classes.filter { !it.isNative }.toTypedArray(),
            n.functions.filter { !it.isNative }.toTypedArray(),
            n.fields.filter { !it.isNative }.toTypedArray()
        )
    }
}

fun export(values: Map<JsValue, JsValue>): JsAssignment {
    return JsAssignment(JsField("exports", JsField("module")), JsObject(values))
}

fun export(values: List<String>): JsAssignment {
    return export(values.associate { JsField(it) to JsField(it) })
}

class JsProject {

    val gen: ShakeJsGenerator
    val subpackages: List<JsPackage>
    val classes: List<JsClassDeclaration>
    val functions: List<JsFunctionDeclaration>
    val fields: List<JsDeclaration>

    val packages: List<String> get() = subpackages.flatMap { it.packages }

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
        functions: Array<ShakeMethod>,
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
            "classes" to classes.map { it.generate() },
            "functions" to functions.map { it.generate() },
            "fields" to fields.map { it.generate() }
        )
    }

    fun hasContents(): Boolean {
        return classes.isNotEmpty() || functions.isNotEmpty() || fields.isNotEmpty()
    }

    fun generatePackageFile(): String {
        return (
            classes.map { it.generate() } +
                functions.map { it.generate() } +
                fields.map { it.generate() } +
                export(classes.map { it.name } + functions.map { it.name } + fields.map { it.name }).generate()
            ).joinToString("\n")
    }

    fun generatePackageFiles(): Map<String, String> {
        val files = subpackages.flatMap { it.generatePackageFiles().toList() }.toMap().toMutableMap()
        if (hasContents()) files += mapOf("index.js" to generatePackageFile())
        files += mapOf(
            "structure.js" to "module.exports=global.packageJsLibrary=global.packageJsLibrary||function(){try{return require(name)}catch(a){if(\"MODULE_NOT_FOUND\"===a.code)return!1;throw a}}()||function(){function c(a){return a&&\"object\"==typeof a&&!Array.isArray(a)}function d(a,...f){if(!f.length)return a;let e=f.shift();if(c(a)&&c(e))for(let b in e)c(e[b])?(a[b]||Object.assign(a,{[b]:{}}),d(a[b],e[b])):Object.assign(a,{[b]:e[b]});return d(a,...f)}function e(a){let b={};return Object.keys(a).forEach(h=>{let g=h.split(/[.\\/]/g),i=a[h],c=b;for(let f=0;f<g.length;f++)c[g[f]]||(c[g[f]]={}),c=c[g[f]];\"function\"==typeof i?Object.defineProperty(c,\"\$it\",{get:function(){let a=i();return Object.defineProperty(c,\"\$it\",{value:a}),a},configurable:!0}):d(c,e(i[h]))}),b}function a(a){let b=a?.packages||{},c=e(b),f;return f={packages:c,pImport(d){let c=d.split(/[.\\/]/g),a=f.packages;for(let b=0;b<c.length;b++){if(!a[c[b]])return;a=a[c[b]]}return a.\$it},add(a){d(f.packages,e(a))}}}let b=a({});return Object.assign(b,{createPackageSystem:a,require:a=>()=>require(`\${a}`),object:a=>()=>a})}();\n\n" +
                JsTree(
                    listOf(
                        JsFunctionCall(
                            JsField("add", JsField("packages")),
                            listOf(
                                JsObject(
                                    packages.map {
                                        JsStringLiteral(it) to JsFunctionCall(
                                            JsField("require"),
                                            listOf(JsStringLiteral("$it.js"))
                                        )
                                    }.toTypedArray().toMap()
                                )
                            )
                        ),
                        export(listOf("import"))
                    )
                ).generate()

        )
        return files
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
    val qualifiedName: String get() = (parent?.qualifiedName?.plus(".") ?: "") + name

    val packages: List<String> get() = subpackages.flatMap { it.packages } + if (hasContents()) listOf(qualifiedName) else listOf()

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
        functions: Array<ShakeMethod>,
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
            "classes" to classes.map { it.generate() },
            "functions" to functions.map { it.generate() },
            "fields" to fields.map { it.generate() }
        )
    }

    fun hasContents(): Boolean {
        return classes.isNotEmpty() || functions.isNotEmpty() || fields.isNotEmpty()
    }

    fun generatePackageFile(): String {
        return (
            classes.map { it.generate() } +
                functions.map { it.generate() } +
                fields.map { it.generate() } +
                export(classes.map { it.name } + functions.map { it.name } + fields.map { it.name }).generate()
            ).joinToString("\n")
    }

    fun generatePackageFiles(): Map<String, String> {
        val files = subpackages.flatMap { it.generatePackageFiles().toList() }.toMap().toMutableMap()
        if (hasContents()) files += mapOf("$qualifiedName.js" to generatePackageFile())
        return files
    }
}
