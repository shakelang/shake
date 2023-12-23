package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.program.types.ShakeClass

class CreationShakeClass
// TODO implement abstract

// TODO inner classes

/*
TODO Interface & Super
val superClass = cl.lateinitSuper()
val interfaces = cl.lateinitInterfaces(clz..size)
 *
 */
private constructor(
    baseProject: CreationShakeProject,
    override val pkg: CreationShakePackage,
    override val parentScope: CreationShakeScope,
    val clz: ShakeClassDeclarationNode
) : ShakeClass {
    override val staticScope: StaticScope
    override val instanceScope: InstanceScope
    override val prj: CreationShakeProject = baseProject
    override val name: String = clz.name
    override val methods: MutableList<CreationShakeMethod> = mutableListOf()
    override val fields: MutableList<CreationShakeField> = mutableListOf()
    override val classes: MutableList<CreationShakeClass> = mutableListOf()
    override val constructors: MutableList<CreationShakeConstructor> = mutableListOf()

    override val isAbstract: Boolean
    override val isFinal: Boolean
    override val isStatic: Boolean
    override val isPublic: Boolean
    override val isPrivate: Boolean
    override val isProtected: Boolean
    override val isNative: Boolean

    override val instanceClasses: List<ShakeClass> get() = classes.filter { !it.isStatic }
    override val instanceMethods: List<CreationShakeMethod> get() = methods.filter { !it.isStatic }
    override val instanceFields: List<CreationShakeField> get() = fields.filter { !it.isStatic }

    override val staticMethods: List<CreationShakeMethod> get() = methods.filter { it.isStatic }
    override val staticFields: List<CreationShakeField> get() = fields.filter { it.isStatic }
    override val staticClasses: List<CreationShakeClass> get() = classes.filter { it.isStatic }

    override lateinit var superClass: CreationShakeClass
        private set

    private var _interfaces: MutableList<CreationShakeClass?> = mutableListOf()

    override val interfaces: List<CreationShakeClass>
        get() = _interfaces.map { it!! }

    /**
     * Phase 1: Register all classes
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-1)
     */
    override fun phase1() {
        clz.classes.forEach {
                if(it.isStatic) {
                    val clz = from(prj, pkg, this.staticScope, it)
                    this.classes.add(clz)
                }
                else {
                    val clz = from(prj, pkg, this.instanceScope, it)
                    this.classes.add(clz)
                }
        }
    }

    /**
     * Phase 2: Link Superclasses and Interfaces
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-2)
     */
    override fun phase2() {
        this.superClass = parentScope.getClass(clz.extends?.toString() ?: "shake.lang.Object")
            ?: throw IllegalStateException("Superclass ${clz.extends} not found in classpath")

        this.clz.implements.forEach {
            this._interfaces.add(parentScope.getClass(it.toString()))
        }
    }

    /**
     * Phase 3: Process all methods and fields (without code)
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-3)
     */
    override fun phase3() {
        clz.methods.forEach {
            val scope = if (it.isStatic) staticScope else instanceScope
            val method = CreationShakeMethod.from(this, scope, it)
            this.methods.add(method)
        }
        clz.fields.forEach {
            val scope = if (it.isStatic) staticScope else instanceScope
            val field = CreationShakeField.from(this, scope, it)
            this.fields.add(field)
        }
        clz.constructors.forEach {
            val constructor = CreationShakeConstructor.from(this, instanceScope, it)
            this.constructors.add(constructor)
        }
    }

    /**
     * Phase 4: Process all code
     * [See in the Specification](https://specification.shakelang.com/compiler/processor/#phase-4)
     */
    override fun phase4() {
        this.methods.forEach { it.phase4() }
        this.staticMethods.forEach { it.phase4() }
        this.fields.forEach { it.phase4() }
        this.staticFields.forEach { it.phase4() }
        this.constructors.forEach { it.phase4() }
        this.classes.forEach { it.phase4() }
        this.staticClasses.forEach { it.phase4() }
    }

    init {
        this.staticScope = StaticScope()
        this.instanceScope = InstanceScope()
        this.isAbstract = false
        this.isFinal = clz.isFinal
        this.isStatic = clz.isStatic
        this.isPublic = clz.access == ShakeAccessDescriber.PUBLIC
        this.isPrivate = clz.access == ShakeAccessDescriber.PRIVATE
        this.isProtected = clz.access == ShakeAccessDescriber.PROTECTED
        this.isNative = clz.isNative
    }

    fun asType(): CreationShakeType {
        return CreationShakeType.Object(this)
    }

    inner class StaticScope : CreationShakeScope() {

        override val processor: ShakeASTProcessor get() = parent.processor
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

        override val processor: ShakeASTProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope
        override val project get() = parent.project

        override fun get(name: String): CreationShakeAssignable? {
            return fields.find { it.name == name } ?: staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun set(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            return methods.filter { it.name == name } + staticMethods.filter { it.name == name } + parent.getFunctions(
                name
            )
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
        fun from(
            baseProject: CreationShakeProject,
            pkg: CreationShakePackage,
            parentScope: CreationShakeScope,
            clz: ShakeClassDeclarationNode
        ): CreationShakeClass {
            return CreationShakeClass(baseProject, pkg, parentScope, clz)
        }
    }
}
