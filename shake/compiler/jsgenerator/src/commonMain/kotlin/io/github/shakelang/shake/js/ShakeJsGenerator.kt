package io.github.shakelang.shake.js

import io.github.shakelang.shake.js.native.JsNatives
import io.github.shakelang.shake.js.output.*
import io.github.shakelang.shake.processor.program.creation.*
import io.github.shakelang.shake.processor.program.creation.code.*
import io.github.shakelang.shake.processor.program.creation.code.statements.*
import io.github.shakelang.shake.processor.program.creation.code.values.*

class ShakeJsGenerator {

    fun visitValue(v: CreationShakeValue): JsValue {
        return when(v) {
            is CreationShakeDoubleLiteral -> visitDouble(v)
            is CreationShakeIntegerLiteral -> visitInteger(v)
            is CreationShakeBooleanLiteral -> visitBoolean(v)
            is CreationShakeAddition -> visitAddition(v)
            is CreationShakeSubtraction -> visitSubtraction(v)
            is CreationShakeMultiplication -> visitMultiplication(v)
            is CreationShakeDivision -> visitDivision(v)
            is CreationShakeModulus -> visitModulus(v)
            is CreationShakePower -> visitPower(v)
            is CreationShakeAssignment -> visitAssignment(v)
            is CreationShakeAddAssignment -> visitAdditionAssignment(v)
            is CreationShakeSubAssignment -> visitSubtractionAssignment(v)
            is CreationShakeMulAssignment -> visitMultiplicationAssignment(v)
            is CreationShakeDivAssignment -> visitDivisionAssignment(v)
            is CreationShakeModAssignment -> visitModulusAssignment(v)
            is CreationShakePowerAssignment -> visitPowerAssignment(v)
            is CreationShakeIncrementBefore -> visitIncrementBefore(v)
            is CreationShakeIncrementAfter -> visitIncrementAfter(v)
            is CreationShakeDecrementBefore -> visitDecrementBefore(v)
            is CreationShakeDecrementAfter -> visitDecrementAfter(v)
            is CreationShakeUsage -> visitUsage(v)
            is CreationShakeEquals -> visitEquals(v)
            is CreationShakeNotEquals -> visitNotEquals(v)
            is CreationShakeGreaterThan -> visitGreaterThan(v)
            is CreationShakeGreaterThanOrEqual -> visitGreaterThanOrEqual(v)
            is CreationShakeLessThan -> visitLessThan(v)
            is CreationShakeLessThanOrEqual -> visitLessThanOrEqual(v)
            is CreationShakeAnd -> visitAnd(v)
            is CreationShakeOr -> visitOr(v)
            is CreationShakeNot -> visitNot(v)
            is CreationShakeInvocation -> visitInvocation(v)
            is CreationShakeNew -> visitNew(v)
            is CreationShakeCast -> visitCast(v)
            else -> throw IllegalArgumentException("Unsupported value type: ${v::class.simpleName}")
        }
    }

    fun visitStatement(s: CreationShakeStatement): JsStatement {
        return when(s) {
            is CreationShakePower -> visitPower(s)
            is CreationShakeAssignment -> visitAssignment(s)
            is CreationShakeAddAssignment -> visitAdditionAssignment(s)
            is CreationShakeSubAssignment -> visitSubtractionAssignment(s)
            is CreationShakeMulAssignment -> visitMultiplicationAssignment(s)
            is CreationShakeDivAssignment -> visitDivisionAssignment(s)
            is CreationShakeModAssignment -> visitModulusAssignment(s)
            is CreationShakePowerAssignment -> visitPowerAssignment(s)
            is CreationShakeIncrementBefore -> visitIncrementBefore(s)
            is CreationShakeIncrementAfter -> visitIncrementAfter(s)
            is CreationShakeDecrementBefore -> visitDecrementBefore(s)
            is CreationShakeDecrementAfter -> visitDecrementAfter(s)
            is CreationShakeInvocation -> visitInvocation(s)
            is CreationShakeNew -> visitNew(s)
            is CreationShakeDoWhile -> visitDoWhile(s)
            is CreationShakeWhile -> visitWhile(s)
            is CreationShakeFor -> visitFor(s)
            is CreationShakeIf -> visitIf(s)
            is CreationShakeReturn -> visitReturn(s)
            is CreationShakeVariableDeclaration -> visitVariableDeclaration(s)
            else -> throw IllegalArgumentException("Unsupported value type: ${s::class.simpleName}")
        }

    }

    fun visitCode(t: CreationShakeCode): JsTree {
        return JsTree(t.statements.map { visitStatement(it) })
    }

    fun visitDouble(n: CreationShakeDoubleLiteral): JsDouble {
        return JsDouble(n.value)
    }

    fun visitInteger(n: CreationShakeIntegerLiteral): JsInteger {
        return JsInteger(n.value)
    }

    fun visitBoolean(n: CreationShakeBooleanLiteral): JsLiteral {
        return if(n.value) JsLiteral.TRUE else JsLiteral.FALSE
    }

    fun visitAddition(n: CreationShakeAddition): JsAdd {
        return JsAdd(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitSubtraction(n: CreationShakeSubtraction): JsSubtract {
        return JsSubtract(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitMultiplication(n: CreationShakeMultiplication): JsMultiply {
        return JsMultiply(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitDivision(n: CreationShakeDivision): JsDivide {
        return JsDivide(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitModulus(n: CreationShakeModulus): JsModulo {
        return JsModulo(visitValue(n.left).toValue(), visitValue(n.right).toValue())
    }

    fun visitPower(n: CreationShakePower): JsFunctionCall {
        return JsFunctionCall(JsField("pow", parent = JsField("Math")),
            args = listOf(visitValue(n.left).toValue(), visitValue(n.right).toValue()))
    }

    fun visitVariableDeclaration(n: CreationShakeVariableDeclaration): JsDeclaration {
        if(n.isFinal) {
            if(n.initialValue == null) throw IllegalStateException("Final variable must have an assignment")
            return JsConstantDeclaration(n.name, visitValue(n.initialValue!!).toValue())
        }
        if(n.initialValue == null) return JsVariableDeclaration(n.name)
        return JsVariableDeclaration(n.name, visitValue(n.initialValue!!).toValue())
    }

    fun visitAssignable(a: CreationShakeAssignable): JsAssignable {
        if(a is CreationShakeVariableDeclaration) return JsAssignable(JsField(a.name))
        if(a is CreationShakeField) {
            if(a.clazz == null) return JsAssignable(JsField(a.name))
            if (a.isStatic) return JsAssignable(JsField(a.name, parent = JsField(a.clazz!!.name)))
            return JsAssignable(JsField(a.name, parent = JsField("this")))
        }
        TODO("not implemented")
    }

    fun visitAssignment(n: CreationShakeAssignment): JsAssignment {
        val variable = visitAssignable(n.variable)
        return variable.assign(visitValue(n.value))
    }

    fun visitAdditionAssignment(n: CreationShakeAddAssignment): JsAddAssignment {
        val variable = visitAssignable(n.variable)
        return variable.addAssign(visitValue(n.value))
    }

    fun visitSubtractionAssignment(n: CreationShakeSubAssignment): JsSubtractAssignment {
        val variable = visitAssignable(n.variable)
        return variable.subtractAssign(visitValue(n.value))
    }

    fun visitMultiplicationAssignment(n: CreationShakeMulAssignment): JsMultiplyAssignment {
        val variable = visitAssignable(n.variable)
        return variable.multiplyAssign(visitValue(n.value))
    }

    fun visitDivisionAssignment(n: CreationShakeDivAssignment): JsDivideAssignment {
        val variable = visitAssignable(n.variable)
        return variable.divideAssign(visitValue(n.value))
    }

    fun visitModulusAssignment(n: CreationShakeModAssignment): JsModuloAssignment {
        val variable = visitAssignable(n.variable)
        return variable.moduloAssign(visitValue(n.value))
    }

    fun visitPowerAssignment(n: CreationShakePowerAssignment): JsAssignment {
        val variable = visitAssignable(n.variable)
        return variable.assign(JsFunctionCall(JsField("pow", parent = JsField("Math")),
            args = listOf(visitValue(n.value).toValue(), visitValue(n.value).toValue())))
    }

    fun visitIncrementBefore(n: CreationShakeIncrementBefore): JsBeforeIncrement {
        val variable = visitAssignable(n.variable)
        return variable.incrementBefore()
    }

    fun visitIncrementAfter(n: CreationShakeIncrementAfter): JsAfterIncrement {
        val variable = visitAssignable(n.variable)
        return variable.incrementAfter()
    }

    fun visitDecrementBefore(n: CreationShakeDecrementBefore): JsBeforeDecrement {
        val variable = visitAssignable(n.variable)
        return variable.decrementBefore()
    }

    fun visitDecrementAfter(n: CreationShakeDecrementAfter): JsAfterDecrement {
        val variable = visitAssignable(n.variable)
        return variable.decrementAfter()
    }

    fun visitUsage(n: CreationShakeUsage): JsField {
        if(n is CreationShakeVariableUsage) return JsField(n.name)
        if(n is CreationShakeClassFieldUsage) {
            if(n.receiver != null) return JsField(n.name, parent = visitValue(n.receiver!!))
            if(n.declaration.isStatic) return JsField(n.name, parent = JsField(n.declaration.clazz?.name ?: throw IllegalStateException("Static field must have a class")))
            return JsField(n.name, parent = JsField("this"))
        }
        if(n is CreationShakeFieldUsage) {
            return JsField(n.name)
        }
        throw IllegalStateException("Unknown usage: $n")
    }

    fun visitEquals(n: CreationShakeEquals): JsEquals {
        return JsEquals(visitValue(n.left), visitValue(n.right))
    }

    fun visitNotEquals(n: CreationShakeNotEquals): JsNotEquals {
        return JsNotEquals(visitValue(n.left), visitValue(n.right))
    }

    fun visitLessThan(n: CreationShakeLessThan): JsLessThan {
        return JsLessThan(visitValue(n.left), visitValue(n.right))
    }

    fun visitLessThanOrEqual(n: CreationShakeLessThanOrEqual): JsLessThanOrEqual {
        return JsLessThanOrEqual(visitValue(n.left), visitValue(n.right))
    }

    fun visitGreaterThan(n: CreationShakeGreaterThan): JsGreaterThan {
        return JsGreaterThan(visitValue(n.left), visitValue(n.right))
    }

    fun visitGreaterThanOrEqual(n: CreationShakeGreaterThanOrEqual): JsGreaterThanOrEqual {
        return JsGreaterThanOrEqual(visitValue(n.left), visitValue(n.right))
    }

    fun visitAnd(n: CreationShakeAnd): JsAnd {
        return JsAnd(visitValue(n.left), visitValue(n.right))
    }

    fun visitOr(n: CreationShakeOr): JsOr {
        return JsOr(visitValue(n.left), visitValue(n.right))
    }

    fun visitXor(n: CreationShakeXor): JsXor {
        return JsXor(visitValue(n.left), visitValue(n.right))
    }

    fun visitNot(n: CreationShakeNot): JsNot {
        return JsNot(visitValue(n.value))
    }


    fun visitWhile(n: CreationShakeWhile): JsWhile {
        return JsWhile(visitValue(n.condition), visitCode(n.body))
    }

    fun visitDoWhile(n: CreationShakeDoWhile): JsDoWhile {
        return JsDoWhile(visitValue(n.condition), visitCode(n.body))
    }

    fun visitFor(n: CreationShakeFor): JsFor {
        return JsFor(visitStatement(n.init), visitValue(n.condition), visitStatement(n.update), visitCode(n.body))
    }

    fun visitIf(n: CreationShakeIf): JsIf {
        return JsIf(visitValue(n.condition), visitCode(n.body), n.elseBody?.let { visitCode(it) })
    }

    fun visitParameter(n: CreationShakeParameter): JsParameter {
        // TODO Default values
        return JsParameter(n.name)
    }

    fun visitFunctionDeclaration(n: CreationShakeMethod): JsFunctionDeclaration {
        val name = n.name
        val parameters = n.parameters.map { visitParameter(it) }
        val body = visitCode(n.body ?: throw IllegalStateException("Method must have a body"))
        return JsFunctionDeclaration(name, parameters, body)
    }

    fun visitMethodDeclaration(n: CreationShakeMethod): JsFunctionDeclaration {
        val name = n.name
        val parameters = n.parameters.map { visitParameter(it) }
        val body = visitCode(n.body ?: throw IllegalStateException("Method must have a body"))
        return JsFunctionDeclaration(name, parameters, body)
    }

    fun visitFieldDeclaration(n: CreationShakeField): JsDeclaration {
        if(n.isFinal) {
            if(n.initialValue == null) throw IllegalStateException("Final field must have initial value")
            return JsConstantDeclaration(n.name, visitValue(n.initialValue!!))
        }
        if(n.initialValue != null) return JsVariableDeclaration(n.name, visitValue(n.initialValue!!))
        return JsVariableDeclaration(n.name)
    }


    fun visitClassDeclaration(n: CreationShakeClass): JsClassDeclaration {
        return JsClassDeclaration(
            n.name,
            functions = n.methods.filter { !it.isNative }.map { visitMethodDeclaration(it) },
            staticFunctions = n.staticMethods.filter { !it.isNative }.map { visitMethodDeclaration(it) },
            fields = n.fields.filter { !it.isNative }.map { visitFieldDeclaration(it) },
            staticFields = n.staticFields.filter { !it.isNative }.map { visitFieldDeclaration(it) }
        )
    }

    fun visitNew(n: CreationShakeNew): JsNew {
        // TODO: Implement
        return JsNew(JsField(n.reference.clazz.name), n.arguments.map { visitValue(it) })
    }

    fun visitInvocation(n: CreationShakeInvocation): JsValuedStatement {

        val callable = n.callable

        when(callable) {
            is CreationShakeMethod -> {

                if(callable.isNative) {

                    if(callable.clazz == null || callable.isStatic)
                        JsNatives.getNativeFunction(callable).handle(n, n.arguments.map { visitValue(it) }, null)

                    JsNatives.getNativeFunction(callable).handle(n, n.arguments.map { visitValue(it) }, visitValue(n.parent!!))

                }

                if(callable.clazz == null) return JsFunctionCall(JsField(callable.name), n.arguments.map { visitValue(it) })

                if(callable.isStatic)
                    return JsFunctionCall(JsField(callable.name, JsField(callable.clazz!!.name)), n.arguments.map { visitValue(it) })

                if(n.parent != null)
                    return JsFunctionCall(JsField(callable.name, visitValue(n.parent!!)), n.arguments.map { visitValue(it) })

                return JsFunctionCall(JsField(callable.name, JsField("this")), n.arguments.map { visitValue(it) })

            }
            is CreationShakeLambdaDeclaration -> TODO()
            // is ShakeLambdaDeclaration -> return JsFunctionCall(callable, n.arguments.map { visitValue(it) })
            else -> throw IllegalStateException("Unknown callable type")
        }
    }

    fun visitCast(n: CreationShakeCast): JsValue {
        return visitValue(n.value)
    }

    fun visitReturn(n: CreationShakeReturn): JsReturn {
        return JsReturn(n.value?.let { visitValue(it) })
    }

    fun visitPackage(prj: JsProject, parent: JsPackage?, n: CreationShakePackage): JsPackage {
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

    fun visitProject(n: CreationShakeProject): JsProject {
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
        subpackages: Array<CreationShakePackage>,
        classes: Array<CreationShakeClass>,
        functions: Array<CreationShakeMethod>,
        fields: Array<CreationShakeField>
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
        if(hasContents()) files += mapOf("index.js" to generatePackageFile())
        files += mapOf(
            "structure.js" to "module.exports=global.packageJsLibrary=global.packageJsLibrary||function(){try{return require(name)}catch(a){if(\"MODULE_NOT_FOUND\"===a.code)return!1;throw a}}()||function(){function c(a){return a&&\"object\"==typeof a&&!Array.isArray(a)}function d(a,...f){if(!f.length)return a;let e=f.shift();if(c(a)&&c(e))for(let b in e)c(e[b])?(a[b]||Object.assign(a,{[b]:{}}),d(a[b],e[b])):Object.assign(a,{[b]:e[b]});return d(a,...f)}function e(a){let b={};return Object.keys(a).forEach(h=>{let g=h.split(/[.\\/]/g),i=a[h],c=b;for(let f=0;f<g.length;f++)c[g[f]]||(c[g[f]]={}),c=c[g[f]];\"function\"==typeof i?Object.defineProperty(c,\"\$it\",{get:function(){let a=i();return Object.defineProperty(c,\"\$it\",{value:a}),a},configurable:!0}):d(c,e(i[h]))}),b}function a(a){let b=a?.packages||{},c=e(b),f;return f={packages:c,pImport(d){let c=d.split(/[.\\/]/g),a=f.packages;for(let b=0;b<c.length;b++){if(!a[c[b]])return;a=a[c[b]]}return a.\$it},add(a){d(f.packages,e(a))}}}let b=a({});return Object.assign(b,{createPackageSystem:a,require:a=>()=>require(`\${a}`),object:a=>()=>a})}();\n\n" +
                    JsTree(listOf(
                        JsFunctionCall(JsField("add", JsField("packages")), listOf(JsObject(
                            packages.map {
                                JsStringLiteral(it) to JsFunctionCall(JsField("require"), listOf(JsStringLiteral("$it.js")))
                            }.toTypedArray().toMap()
                        ))),
                        export(listOf("import"))
                    )).generate()

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

    val packages: List<String> get() = subpackages.flatMap { it.packages } + if(hasContents()) listOf(qualifiedName) else listOf()

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
        subpackages: Array<CreationShakePackage>,
        classes: Array<CreationShakeClass>,
        functions: Array<CreationShakeMethod>,
        fields: Array<CreationShakeField>
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
        if(hasContents()) files += mapOf("$qualifiedName.js" to generatePackageFile())
        return files
    }
}