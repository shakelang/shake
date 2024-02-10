package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.types.ShakeAssignable
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
    override val clazz: ShakeClass?,
    val clz: ShakeClassDeclarationNode,
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
    override val isAnnotation: Boolean
    override val isEnum: Boolean
    override val isInterface: Boolean
    override val isObject: Boolean

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
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-1)
     */
    override fun phase1() {
        debug("phases", "Phase 1 of class $qualifiedName")
        clz.classes.forEach {
            if (it.isStatic) {
                val clz = from(prj, pkg, this.staticScope, it)
                this.classes.add(clz)
            } else {
                val clz = from(prj, pkg, this.instanceScope, it)
                this.classes.add(clz)
            }
        }

        classes.forEach {
            it.phase1()
        }
    }

    /**
     * Phase 2: Link Superclasses and Interfaces
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-2)
     */
    override fun phase2() {
        debug("phases", "Phase 2 of class $qualifiedName")
        val extends = clz.extends?.toString() ?: "shake.lang.Object"
        this.superClass = parentScope.getClass(extends)
            ?: throw IllegalStateException("Superclass $extends not found in classpath")

        this.clz.implements.forEach {
            this._interfaces.add(parentScope.getClass(it.toString()))
        }
    }

    /**
     * Phase 3: Process all methods and fields (without code)
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-3)
     */
    override fun phase3() {
        debug("phases", "Phase 3 of class $qualifiedName")
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

        methods.forEach { it.phase3() }
        fields.forEach { it.phase3() }
        constructors.forEach { it.phase3() }
        classes.forEach { it.phase3() }
    }

    /**
     * Phase 4: Process all code
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-4)
     */
    override fun phase4() {
        debug("phases", "Phase 4 of class $qualifiedName")
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
        this.isAnnotation = false // TODO implement
        this.isEnum = false // TODO implement
        this.isInterface = false // TODO implement
        this.isObject = false // TODO implement
    }

    fun asType(): CreationShakeType {
        return CreationShakeType.Object(this)
    }

    inner class StaticScope : CreationShakeScope() {

        override val uniqueName: String
            get() = "$qualifiedName(static)"

        override val processor: ShakeASTProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope
        override val project get() = prj

        override fun getField(name: String): CreationShakeAssignable? {
            val field = staticFields.find { it.name == name }
            if (field != null) {
                debug("scope", "Searching for field $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for field $name in $uniqueName had no result")
            }
            return field ?: parent.getField(name)
        }

        override fun getFields(name: String): List<CreationShakeAssignable> {
            val fields = staticFields.filter { it.name == name }
            if (fields.isNotEmpty()) {
                debug("scope", "Searching for fields $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for fields $name in $uniqueName had no result")
            }
            return fields + parent.getFields(name)
        }

        override fun setField(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            val methods = staticMethods.filter { it.name == name }
            if (methods.isNotEmpty()) {
                debug("scope", "Searching for method $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for method $name in $uniqueName had no result")
            }
            return methods + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            val clazz = staticClasses.find { it.name == name }
            if (clazz != null) {
                debug("scope", "Searching for class $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for class $name in $uniqueName had no result")
            }
            return clazz ?: parent.getClass(name)
        }

        override fun getClasses(name: String): List<CreationShakeClass> {
            val classes = staticClasses.filter { it.name == name }
            if (classes.isNotEmpty()) {
                debug("scope", "Searching for classes $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for classes $name in $uniqueName had no result")
            }
            return classes + parent.getClasses(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getThis(): ShakeAssignable? {
            return parent.getThis()
        }

        override fun getThis(name: String): ShakeAssignable? {
            return parent.getThis(name)
        }

        override fun getSuper(): ShakeAssignable? {
            return parent.getSuper()
        }

        override fun getSuper(name: String): ShakeAssignable? {
            return parent.getSuper(name)
        }
    }

    inner class InstanceScope : CreationShakeScope() {

        override val uniqueName: String
            get() = "$qualifiedName(instance)"

        override val processor: ShakeASTProcessor get() = parent.processor
        override val parent: CreationShakeScope get() = parentScope
        override val project get() = parent.project

        override fun getField(name: String): CreationShakeAssignable? {
            val field = fields.find { it.name == name }
            if (field != null) {
                debug("scope", "Searching for field $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for field $name in $uniqueName had no result")
            }
            return field ?: parent.getField(name)
        }

        override fun getFields(name: String): List<CreationShakeAssignable> {
            val fields = fields.filter { it.name == name }
            if (fields.isNotEmpty()) {
                debug("scope", "Searching for fields $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for fields $name in $uniqueName had no result")
            }
            return fields + parent.getFields(name)
        }

        override fun setField(value: CreationShakeDeclaration) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            val methods = methods.filter { it.name == name }
            if (methods.isNotEmpty()) {
                debug("scope", "Searching for method $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for method $name in $uniqueName had no result")
            }
            return methods + parent.getFunctions(name)
        }

        override fun setFunctions(function: CreationShakeMethod) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getClass(name: String): CreationShakeClass? {
            val clazz = classes.find { it.name == name }
            if (clazz != null) {
                debug("scope", "Searching for class $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for class $name in $uniqueName had no result")
            }
            return clazz ?: parent.getClass(name)
        }

        override fun getClasses(name: String): List<CreationShakeClass> {
            val classes = classes.filter { it.name == name }
            if (classes.isNotEmpty()) {
                debug("scope", "Searching for classes $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for classes $name in $uniqueName had no result")
            }
            return classes + parent.getClasses(name)
        }

        override fun setClass(klass: CreationShakeClass) {
            throw IllegalStateException("Cannot set in this scope")
        }

        override fun getThis(): ShakeAssignable? {
            TODO()
        }

        override fun getThis(name: String): ShakeAssignable? {
            TODO()
        }

        override fun getSuper(): ShakeAssignable? {
            TODO()
        }

        override fun getSuper(name: String): ShakeAssignable? {
            TODO()
        }
    }

    companion object {

        val debug = ShakeProcessor.debug.child("creation", "class")

        fun from(
            baseProject: CreationShakeProject,
            pkg: CreationShakePackage,
            parentScope: CreationShakeScope,
            clz: ShakeClassDeclarationNode,
        ): CreationShakeClass {
            return CreationShakeClass(baseProject, pkg, parentScope, null, clz)
        }

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            clz: ShakeClassDeclarationNode,
        ): CreationShakeClass {
            return CreationShakeClass(clazz.prj, clazz.pkg, parentScope, clazz, clz)
        }
    }
}
