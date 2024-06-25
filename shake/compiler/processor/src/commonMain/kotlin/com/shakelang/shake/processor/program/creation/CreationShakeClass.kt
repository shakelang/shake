package com.shakelang.shake.processor.program.creation

import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.outer.ShakeClassDeclarationNode
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.ShakeProcessor
import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeClass

@Suppress("ktlint:standard:backing-property-naming")
class CreationShakeClass
// TODO implement abstract

// TODO inner classes

/*
TODO Interface & Super
val superClass = cl.lateinitSuper()
val interfaces = cl.lateinitInterfaces(clz..size)
 *
 */
internal constructor(
    baseProject: CreationShakeProject,
    override val pkg: CreationShakePackage,
    override val parentScope: CreationShakeScope,
    override val clazz: ShakeClass?,
    override val name: String,
    private val declarationNode: ShakeClassDeclarationNode?,
    override val isAbstract: Boolean,
    override val isFinal: Boolean,
    override val isStatic: Boolean,
    override val isPublic: Boolean,
    override val isPrivate: Boolean,
    override val isProtected: Boolean,
    override val isNative: Boolean,
    override val isAnnotation: Boolean,
    override val isEnum: Boolean,
    override val isInterface: Boolean,
    override val isObject: Boolean,
    classes: List<CreationShakeClass>,
    methods: List<CreationShakeMethod>,
    fields: List<CreationShakeField>,
    constructors: List<CreationShakeConstructor>,
    private val superClassNames: List<TypeStorage>,
    generics: List<CreationShakeType.Generic>,
) : ShakeClass {

    // The phase of the class
    private var phase: Byte = 0

    constructor(
        baseProject: CreationShakeProject,
        pkg: CreationShakePackage,
        parentScope: CreationShakeScope,
        parentClass: ShakeClass?,
        clz: ShakeClassDeclarationNode,
    ) : this(
        baseProject,
        pkg,
        parentScope,
        parentClass,
        clz.name,
        clz,
        clz.isAbstract,
        clz.isFinal,
        clz.isStatic,
        clz.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PUBLIC,
        clz.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PRIVATE,
        clz.access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PROTECTED,
        clz.isNative,
        false, // TODO implement
        clz.isEnum,
        clz.isInterface,
        clz.isObject,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        clz.superClasses.map {
            TypeStorage.from(it.type)
        },
        emptyList(),
    )

    constructor(
        project: CreationShakeProject,
        name: String,
        pkg: CreationShakePackage,
        clazz: ShakeClass?,
        scope: CreationShakeScope,
        flags: ShakeClass.ShakeClassFlags,
    ) : this(
        project,
        pkg,
        scope,
        clazz,
        name,
        null,
        flags.isAbstract,
        flags.isFinal,
        flags.isStatic,
        flags.isPublic,
        flags.isPrivate,
        flags.isProtected,
        flags.isNative,
        flags.isAnnotation,
        flags.isEnum,
        flags.isInterface,
        flags.isObject,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
    )

    constructor(
        project: CreationShakeProject,
        name: String,
        pkg: CreationShakePackage,
        clazz: ShakeClass?,
        scope: CreationShakeScope,
        flags: Short,
    ) : this(
        project,
        name,
        pkg,
        clazz,
        scope,
        ShakeClass.ShakeClassFlags(flags),
    )

    override val staticScope: StaticScope
    override val instanceScope: InstanceScope
    override val prj: CreationShakeProject = baseProject

    private val _methods: MutableList<CreationShakeMethod> = methods.toMutableList()
    private val _constructors: MutableList<CreationShakeConstructor> = constructors.toMutableList()
    private val _fields: MutableList<CreationShakeField> = fields.toMutableList()
    private val _classes: MutableList<CreationShakeClass> = classes.toMutableList()
    private val _generics: MutableList<CreationShakeType.Generic> = generics.toMutableList()

    override val methods: List<CreationShakeMethod> get() = _methods.toList()
    override val fields: List<CreationShakeField> get() = _fields.toList()
    override val classes: List<CreationShakeClass> get() = _classes.toList()
    override val constructors: List<CreationShakeConstructor> get() = _constructors.toList()
    override val generics: List<CreationShakeType.Generic> get() = _generics.toList()

    override val instanceClasses: List<ShakeClass> get() = _classes.filter { !it.isStatic }
    override val instanceMethods: List<CreationShakeMethod> get() = _methods.filter { !it.isStatic }
    override val instanceFields: List<CreationShakeField> get() = _fields.filter { !it.isStatic }

    override val staticMethods: List<CreationShakeMethod> get() = _methods.filter { it.isStatic }
    override val staticFields: List<CreationShakeField> get() = _fields.filter { it.isStatic }
    override val staticClasses: List<CreationShakeClass> get() = _classes.filter { it.isStatic }

    override lateinit var superClass: CreationShakeType.Object
        private set

    private var _interfaces: MutableList<CreationShakeType.Object?> = mutableListOf()

    override val interfaces: List<CreationShakeType.Object>
        get() = _interfaces.map { it!! }

    /**
     * Phase 1: Register all classes
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-1)
     */
    override fun phase1() {
        debug("phases", "Phase 1 of class $qualifiedName")

        if (this.phase > 0) {
            debug("phases", "Skipping phase 1 of class $qualifiedName")
            return
        }
        this.phase = 1

        // Register all subclasses (will only be executed, if clz is not null)
        // In MapRebuilder this should just be ignored
        declarationNode?.classes?.forEach {
            if (it.isStatic) {
                val clz = from(prj, pkg, this.staticScope, it)
                this._classes.add(clz)
            } else {
                val clz = from(prj, pkg, this.instanceScope, it)
                this._classes.add(clz)
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

        if (this.phase > 1) {
            debug("phases", "Skipping phase 2 of class $qualifiedName")
            return
        }
        this.phase = 2

        superClassNames.forEach {
            val superClass = it.resolve(parentScope) as? CreationShakeType.Object
                ?: throw IllegalStateException("Superclass $it is not a class")

            if (superClass.clazz.isInterface) {
                this._interfaces.add(superClass)
                debug("inheritance", "Added interface $it to class $qualifiedName")
                return@forEach
            }

            if (superClass.clazz.isEnum) throw IllegalStateException("Superclass $it is an enum and cannot be extended")
            if (superClass.clazz.isObject) throw IllegalStateException("Superclass $it is an object and cannot be extended")
            if (superClass.clazz.isFinal) throw IllegalStateException("Superclass $it is final and cannot be extended")

            debug("inheritance", "Set superclass of class $qualifiedName to $it")

            if (this::superClass.isInitialized) throw IllegalStateException("Superclass already set, can only extend one class")
            this.superClass = superClass
        }

        if (!this::superClass.isInitialized) {
            this.superClass = parentScope.getClass("shake.lang.Object")?.asType() ?: throw IllegalStateException("shake.lang.Object not found in classpath")
        }

        // Resolve generics
        if (declarationNode != null) {
            _generics.addAll(
                declarationNode.generics?.generics?.map {
                    CreationShakeType.Generic(
                        it.name,
                        it.type?.let { it1 -> parentScope.getType(it1) },
                        qualifiedName,
                    )
                } ?: emptyList(),
            )
        }
    }

    /**
     * Phase 3: Process all methods and fields (without code)
     * [See in the Specification](https://spec.shakelang.com/compiler/processor/#phase-3)
     */
    override fun phase3() {
        debug("phases", "Phase 3 of class $qualifiedName")

        if (this.phase > 2) {
            debug("phases", "Skipping phase 3 of class $qualifiedName")
            return
        }
        this.phase = 3

        // This should only be executed if clz is not null
        // In MapRebuilder this should just be ignored
        declarationNode?.methods?.forEach {
            val scope = if (it.isStatic) staticScope else instanceScope
            val method = CreationShakeMethod.from(this, scope, it)
            this._methods.add(method)
        }
        declarationNode?.fields?.forEach {
            val scope = if (it.isStatic) staticScope else instanceScope
            val field = CreationShakeField.from(this, scope, it)
            this._fields.add(field)
        }
        declarationNode?.constructors?.forEach {
            val constructor = CreationShakeConstructor.from(this, instanceScope, it)
            this._constructors.add(constructor)
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

        if (this.phase > 3) {
            debug("phases", "Skipping phase 4 of class $qualifiedName")
            return
        }
        this.phase = 4

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
    }

    fun asType(): CreationShakeType.Object = CreationShakeType.Object(this)

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

        override fun setField(value: CreationShakeDeclaration): Unit = throw IllegalStateException("Cannot set in this scope")

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            val methods = staticMethods.filter { it.name == name }
            if (methods.isNotEmpty()) {
                debug("scope", "Searching for method $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for method $name in $uniqueName had no result")
            }
            return methods + parent.getFunctions(name)
        }

        override fun getConstructors(name: String): List<CreationShakeConstructor> {
            val constructors = constructors.filter { it.name == name }
            if (constructors.isNotEmpty()) {
                debug("scope", "Searching for constructor $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for constructor $name in $uniqueName had no result")
            }
            return constructors + parent.getConstructors(name)
        }

        override fun getDirectClass(name: String): CreationShakeClass? = staticClasses.find { it.name == name }

        override fun getDirectClasses(name: String): List<CreationShakeClass> = staticClasses.filter { it.name == name }

        override fun getThis(): ShakeAssignable? = parent.getThis()

        override fun getThis(name: String): ShakeAssignable? = parent.getThis(name)

        override fun getSuper(): ShakeAssignable? = parent.getSuper()

        override fun getSuper(name: String): ShakeAssignable? = parent.getSuper(name)
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

        override fun setField(value: CreationShakeDeclaration): Unit = throw IllegalStateException("Cannot set in this scope")

        override fun getFunctions(name: String): List<CreationShakeMethod> {
            val methods = methods.filter { it.name == name }
            if (methods.isNotEmpty()) {
                debug("scope", "Searching for method $name in $uniqueName successful")
            } else {
                debug("scope", "Searching for method $name in $uniqueName had no result")
            }
            return methods + parent.getFunctions(name)
        }

        override fun internalGetType(type: String, generics: List<CreationShakeType>?): CreationShakeType {
            val genericType = this@CreationShakeClass.generics.find { it.name == type }
            if (genericType != null) {
                debug("scope", "Searching for generic type $type in $uniqueName successful")
                return genericType
            }
            return super.internalGetType(type, generics)
        }

        override fun getDirectClass(name: String): CreationShakeClass? = classes.find { it.name == name }

        override fun getDirectClasses(name: String): List<CreationShakeClass> = classes.filter { it.name == name }

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
        ): CreationShakeClass = CreationShakeClass(baseProject, pkg, parentScope, null, clz)

        fun from(
            clazz: CreationShakeClass,
            parentScope: CreationShakeScope,
            clz: ShakeClassDeclarationNode,
        ): CreationShakeClass = CreationShakeClass(clazz.prj, clazz.pkg, parentScope, clazz, clz)

        fun disablePhases(e: CreationShakeClass) {
            e.phase = 4
        }

        fun initSuper(e: CreationShakeClass, superClass: CreationShakeType.Object) {
            e.superClass = superClass
        }

        fun initInterfaces(e: CreationShakeClass, interfaces: List<CreationShakeType.Object>) {
            e._interfaces = interfaces.toMutableList()
        }

        fun exposeMutableFields(e: CreationShakeClass): MutableList<CreationShakeField> = e._fields
        fun exposeMutableMethods(e: CreationShakeClass): MutableList<CreationShakeMethod> = e._methods
        fun exposeMutableConstructors(e: CreationShakeClass): MutableList<CreationShakeConstructor> = e._constructors
        fun exposeMutableClasses(e: CreationShakeClass): MutableList<CreationShakeClass> = e._classes
        fun exposeMutableGenerics(e: CreationShakeClass): MutableList<CreationShakeType.Generic> = e._generics
    }
}
