package io.github.shakelang.shake.processor.program.creation

import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.processor.ShakeCodeProcessor
import io.github.shakelang.shake.processor.program.creation.code.CreationShakeCode
import io.github.shakelang.shake.processor.program.types.ShakeClass

class CreationShakeClass: ShakeClass {
    override val staticScope: StaticScope
    override val instanceScope: InstanceScope
    override val prj: CreationShakeProject
    override val pkg: CreationShakePackage?
    override val parentScope: CreationShakeScope
    override val name: String
    override val methods: List<CreationShakeMethod>
    override val fields: List<CreationShakeField>
    override val classes: List<CreationShakeClass>
    override val constructors: List<CreationShakeConstructor>

    override val isAbstract: Boolean
    override val isFinal: Boolean
    override val isStatic: Boolean
    override val isPublic: Boolean
    override val isPrivate: Boolean
    override val isProtected: Boolean

    override val instanceClasses: List<ShakeClass> get() = classes.filter { !it.isStatic }
    override val instanceMethods: List<CreationShakeMethod> get() = methods.filter { !it.isStatic }
    override val instanceFields: List<CreationShakeField> get() = fields.filter { !it.isStatic }

    override val staticMethods: List<CreationShakeMethod> get() = methods.filter { it.isStatic }
    override val staticFields: List<CreationShakeField> get() = fields.filter { it.isStatic }
    override val staticClasses: List<CreationShakeClass> get() = classes.filter { it.isStatic }


    override var superClass: CreationShakeClass? = null
        private set

    private var _interfaces: List<CreationShakeClass?> = listOf()

    override val interfaces: List<CreationShakeClass>
        get() = _interfaces.map { it!! }

    constructor(
        prj: CreationShakeProject,
        pkg: CreationShakePackage?,
        parentScope: CreationShakeScope,
        name: String,
        methods: List<CreationShakeMethod>,
        fields: List<CreationShakeField>,
        classes: List<CreationShakeClass>,
        constructors: List<CreationShakeConstructor> = listOf(),
        isAbstract: Boolean = false,
        isFinal: Boolean = false,
        isStatic: Boolean = false,
        isPublic: Boolean = false,
        isPrivate: Boolean = false,
        isProtected: Boolean = false
    ) {
        this.prj = prj
        this.pkg = pkg
        this.parentScope = parentScope
        this.staticScope = StaticScope()
        this.instanceScope = InstanceScope()
        this.name = name
        this.methods = methods
        this.fields = fields
        this.classes = classes
        this.constructors = constructors
        this.isAbstract = isAbstract
        this.isFinal = isFinal
        this.isStatic = isStatic
        this.isPublic = isPublic
        this.isPrivate = isPrivate
        this.isProtected = isProtected
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
        this.staticScope = StaticScope()
        this.instanceScope = InstanceScope()

        this.isAbstract = false // TODO implement abstract
        this.isFinal = clz.isFinal
        this.isStatic = clz.isStatic
        this.isPublic = clz.access == ShakeAccessDescriber.PUBLIC
        this.isPrivate = clz.access == ShakeAccessDescriber.PRIVATE
        this.isProtected = clz.access == ShakeAccessDescriber.PROTECTED

        this.methods = clz.methods.map {
            val method = CreationShakeMethod(
                this.prj,
                this.pkg,
                this,
                if(it.isStatic) staticScope else instanceScope,
                it.name,
                it.body?.let { it1 -> CreationShakeCode.fromTree(it1) },
                it.isStatic,
                it.isFinal,
                false,
                false,
                false,
                it.access == ShakeAccessDescriber.PRIVATE,
                it.access == ShakeAccessDescriber.PROTECTED,
                it.access == ShakeAccessDescriber.PUBLIC,
            )
            method.lateinitReturnType().let { run -> instanceScope.getType(it.type) { type -> run(type) } }
            method
                .lateinitParameterTypes(it.args.map { p -> p.name })
                .forEachIndexed { i, run -> instanceScope.getType(it.args[i].type) { type -> run(type) } }
            method
        }

        this.fields = clz.fields.map {
            val field = CreationShakeField.from(this, if (it.isStatic) staticScope else instanceScope, it)
            field.lateinitType().let { run -> instanceScope.getType(it.type) { type -> run(type) } }
            field
        }

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
                .forEachIndexed { i, run -> instanceScope.getType(it.args[i].type) { type -> run(type) } }
            constr
        }
        /*
        TODO Interface & Super
        val superClass = cl.lateinitSuper()
        val interfaces = cl.lateinitInterfaces(clz..size)
         *
         */

        this.classes = emptyList()
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

    fun asType(): CreationShakeType {
        return CreationShakeType.Object(this)
    }

    fun processCode() {
        this.methods.forEach { it.processCode() }
        this.staticMethods.forEach { it.processCode() }
        this.fields.forEach { it.processCode() }
        this.staticFields.forEach { it.processCode() }
        this.constructors.forEach { it.processCode() }
        this.classes.forEach { it.processCode() }
        this.staticClasses.forEach { it.processCode() }
    }

    inner class StaticScope : CreationShakeScope() {

        override val processor: ShakeCodeProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope
        override val project get() = prj

        override fun get(name: String): CreationShakeAssignable? {
            return staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            return staticClasses.find { it.name == name } ?: parent.getClass(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

    }

    inner class InstanceScope : CreationShakeScope() {

        override val processor: ShakeCodeProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope
        override val project get() = parent.project

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name } ?: staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return methods.filter { it.name == name } + staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
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