package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeScope
import kotlin.math.min

open class CreationShakeClass {
    val staticScope = StaticScope()
    val instanceScope = InstanceScope()
    val prj: CreationShakeProject
    val pkg: CreationShakePackage?
    val parentScope: CreationShakeScope
    val name: String
    val methods: List<CreationShakeMethod>
    val fields: List<CreationShakeClassField>
    val classes: List<CreationShakeClass>
    val staticMethods: List<CreationShakeMethod>
    val staticFields: List<CreationShakeClassField>
    val staticClasses: List<CreationShakeClass>
    val constructors: List<CreationShakeConstructor>

    val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    var superClass: CreationShakeClass? = null
        private set

    private var _interfaces: List<CreationShakeClass?> = listOf()
        private set

    val interfaces: List<CreationShakeClass>
        get() = _interfaces.map { it!! }

    constructor(
        prj: CreationShakeProject,
        pkg: CreationShakePackage?,
        parentScope: CreationShakeScope,
        name: String,
        methods: List<CreationShakeMethod>,
        fields: List<CreationShakeClassField>,
        classes: List<CreationShakeClass>,
        staticMethods: List<CreationShakeMethod>,
        staticFields: List<CreationShakeClassField>,
        staticClasses: List<CreationShakeClass>,
        constructors: List<CreationShakeConstructor> = listOf()
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
        baseProject: CreationShakeProject,
        pkg: CreationShakePackage?,
        parentScope: CreationShakeScope,
        clz: ShakeClassDeclarationNode
    ) {
        this.prj = baseProject
        this.pkg = pkg
        this.name = clz.name
        this.parentScope = parentScope

        val mtds = clz.methods.map {
            val method = CreationShakeMethod(
                this,
                if(it.isStatic) staticScope else instanceScope,
                it.name,
                CreationShakeCode.fromTree(it.body),
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
            val field = CreationShakeClassField.from(this, if (it.isStatic) staticScope else instanceScope, it)
            field.lateinitType().let { run -> baseProject.getType(it.type) { type -> run(type) } }
            field
        }
        this.fields = flds.filter { !it.isStatic }
        this.staticFields = flds.filter { it.isStatic }

        // TODO inner classes

        this.constructors = clz.constructors.map {
            val constr = CreationShakeConstructor(
                this,
                CreationShakeCode.fromTree(it.body),
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

    fun lateinitSuper(): (CreationShakeClass?) -> CreationShakeClass? {
        return {
            superClass = it
            it
        }
    }

    fun lateinitInterfaces(number: Int): List<(CreationShakeClass) -> CreationShakeClass> {
        val interfaces = MutableList<CreationShakeClass?>(number) { null }
        this._interfaces = interfaces
        return interfaces.indices.map { i -> {
                interfaces[i] = it
                it
            }
        }
    }

    fun compatibleTo(other: CreationShakeClass): Boolean {
        if (this == other) return true
        if (this.superClass != null && this.superClass!!.compatibleTo(other)) return true
        return this.interfaces.any { it.compatibleTo(other) }
    }

    fun compatibilityDistance(other: CreationShakeClass): Int {
        if (this == other) return 0
        val scd = (this.superClass?.compatibilityDistance(other) ?: -1) + 1
        val intDistance = (this.interfaces.minOfOrNull { it.compatibilityDistance(other) } ?: -2) + 1
        if(scd < 0) return intDistance
        if(intDistance < 0) return scd
        return min(scd, intDistance)
    }

    fun asType(): CreationShakeType {
        return CreationShakeType.Object(this)
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

    inner class StaticScope : CreationShakeScope {

        override val processor: ShakeCodeProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope

        override fun get(name: String): CreationShakeAssignable? {
            return staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeFunction> {
            return staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeFunction) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return staticClasses.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

    }

    inner class InstanceScope : CreationShakeScope {

        override val processor: ShakeCodeProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name } ?: staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeFunction> {
            return methods.filter { it.name == name } + staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeFunction) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

    }

    companion object {
        fun from(baseProject: CreationShakeProject, pkg: CreationShakePackage?, parentScope: CreationShakeScope, clz: ShakeClassDeclarationNode): CreationShakeClass {
            return CreationShakeClass(baseProject, pkg, parentScope, clz)
        }
    }
}