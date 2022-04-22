package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeScope
import kotlin.math.min

open class ShakeClass {
    val staticScope = StaticScope()
    val instanceScope = InstanceScope()
    val prj: ShakeProject
    val pkg: ShakePackage?
    val parentScope: ShakeScope
    val name: String
    val methods: List<ShakeMethod>
    val fields: List<ShakeClassField>
    val classes: List<ShakeClass>
    val staticMethods: List<ShakeMethod>
    val staticFields: List<ShakeClassField>
    val staticClasses: List<ShakeClass>
    val constructors: List<ShakeConstructor>

    val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    var superClass: ShakeClass? = null
        private set

    private var _interfaces: List<ShakeClass?> = listOf()
        private set

    val interfaces: List<ShakeClass>
        get() = _interfaces.map { it!! }

    constructor(
        prj: ShakeProject,
        pkg: ShakePackage?,
        parentScope: ShakeScope,
        name: String,
        methods: List<ShakeMethod>,
        fields: List<ShakeClassField>,
        classes: List<ShakeClass>,
        staticMethods: List<ShakeMethod>,
        staticFields: List<ShakeClassField>,
        staticClasses: List<ShakeClass>,
        constructors: List<ShakeConstructor> = listOf()
    ) {
        this.prj = prj
        this.pkg = pkg
        this.parentScope = parentScope
        this.name = name
        this.methods = methods
        this.fields = fields
        this.classes = classes
        this.staticMethods = staticMethods
        this.staticFields = staticFields
        this.staticClasses = staticClasses
        this.constructors = constructors
    }
    private constructor(
        baseProject: ShakeProject,
        pkg: ShakePackage?,
        parentScope: ShakeScope,
        clz: ShakeClassDeclarationNode
    ) {
        this.prj = baseProject
        this.pkg = pkg
        this.name = clz.name
        this.parentScope = parentScope

        val mtds = clz.methods.map {
            val method = ShakeMethod(
                this,
                if(it.isStatic) staticScope else instanceScope,
                it.name,
                ShakeCode.fromTree(it.body),
                it.isStatic,
                it.isFinal,
                false,
                false,
                false,
                it.access == ShakeAccessDescriber.PRIVATE,
                it.access == ShakeAccessDescriber.PROTECTED,
                it.access == ShakeAccessDescriber.PUBLIC,
            )
            method.lateinitReturnType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
            method
                .lateinitParameterTypes(it.args.map { p -> p.name })
                .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
            method
        }
        this.methods = mtds.filter { !it.isStatic }
        this.staticMethods = mtds.filter { it.isStatic }
        val flds = clz.fields.map {
            val field = ShakeClassField.from(this, if(it.isStatic) staticScope else instanceScope, it)
            field.lateinitType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
            field
        }
        this.fields = flds.filter { !it.isStatic }
        this.staticFields = flds.filter { it.isStatic }

        // TODO inner classes

        this.constructors = clz.constructors.map {
            val constr = ShakeConstructor(
                this,
                ShakeCode.fromTree(it.body),
                false,
                it.access == ShakeAccessDescriber.PRIVATE,
                it.access == ShakeAccessDescriber.PROTECTED,
                it.access == ShakeAccessDescriber.PUBLIC,
                name = it.name
            )
            constr.lateinitParameterTypes(it.args.map { p -> p.name })
                .forEachIndexed { i, run -> baseProject.getType(it.args[i].type) { type -> run(type) } }
            constr
        }
        /*
        TODO Interface & Super
        val superClass = cl.lateinitSuper()
        val interfaces = cl.lateinitInterfaces(clz..size)
         *
         */

        this.classes = emptyList()
        this.staticClasses = emptyList()
    }

    fun lateinitSuper(): (ShakeClass?) -> ShakeClass? {
        return {
            superClass = it
            it
        }
    }

    fun lateinitInterfaces(number: Int): List<(ShakeClass) -> ShakeClass> {
        val interfaces = MutableList<ShakeClass?>(number) { null }
        this._interfaces = interfaces
        return interfaces.indices.map { i -> {
                interfaces[i] = it
                it
            }
        }
    }

    fun compatibleTo(other: ShakeClass): Boolean {
        if (this == other) return true
        if (this.superClass != null && this.superClass!!.compatibleTo(other)) return true
        return this.interfaces.any { it.compatibleTo(other) }
    }

    fun compatibilityDistance(other: ShakeClass): Int {
        if (this == other) return 0
        val scd = (this.superClass?.compatibilityDistance(other) ?: -1) + 1
        val intDistance = (this.interfaces.minOfOrNull { it.compatibilityDistance(other) } ?: -2) + 1
        if(scd < 0) return intDistance
        if(intDistance < 0) return scd
        return min(scd, intDistance)
    }

    fun asType(): ShakeType {
        return ShakeType.Object(this)
    }

    open fun processCode() {
        this.methods.forEach { it.processCode() }
        this.staticMethods.forEach { it.processCode() }
        this.fields.forEach { it.processCode() }
        this.staticFields.forEach { it.processCode() }
        this.constructors.forEach { it.processCode() }
        this.classes.forEach { it.processCode() }
        this.staticClasses.forEach { it.processCode() }
    }

    open fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to this.name,
            "super" to this.superClass?.name,
            "interfaces" to this.interfaces.map { it.name },
            "methods" to this.methods.map { it.toJson() },
            "staticMethods" to this.staticMethods.map { it.toJson() },
            "fields" to this.fields.map { it.toJson() },
            "staticFields" to this.staticFields.map { it.toJson() },
            "constructors" to this.constructors.map { it.toJson() },
            "classes" to this.classes.map { it.toJson() },
            "staticClasses" to this.staticClasses.map { it.toJson() },
        )
    }

    inner class StaticScope : ShakeScope {

        override val processor: ShakeCodeProcessor
            get() = parent.processor

        override val parent: ShakeScope
            get() = pkg?.scope ?: prj.projectScope

        override fun get(name: String): ShakeAssignable? {
            return staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: ShakeFunction) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return staticClasses.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

    }

    inner class InstanceScope : ShakeScope {

        override val processor: ShakeCodeProcessor
            get() = parent.processor

        override val parent: ShakeScope
            get() = pkg?.scope ?: prj.projectScope

        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name } ?: staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: ShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<ShakeFunction> {
            return methods.filter { it.name == name } + staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: ShakeFunction) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): ShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: ShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

    }

    companion object {
        fun from(baseProject: ShakeProject, pkg: ShakePackage?, parentScope: ShakeScope, clz: ShakeClassDeclarationNode): ShakeClass {
            return ShakeClass(baseProject, pkg, parentScope, clz)
        }
    }
}