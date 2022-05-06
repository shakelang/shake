package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.*
import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable
import io.github.shakelang.shake.processor.util.Pointer
import kotlin.math.min

open class ImmutableShakeClass : ShakeClass {
    final override val staticScope = StaticScope()
    final override val instanceScope = InstanceScope()
    final override val prj: ImmutableShakeProject
    final override val pkg: ImmutableShakePackage?
    final override val parentScope: ImmutableShakeScope
    final override val name: String
    final override val methods: List<ImmutableShakeMethod>
    final override val fields: List<ShakeClassField>
    final override val classes: List<ImmutableShakeClass>
    final override val staticMethods: List<ImmutableShakeMethod>
    final override val staticFields: List<ShakeClassField>
    final override val staticClasses: List<ImmutableShakeClass>
    final override val constructors: List<ShakeConstructor>

    val superClassPointer: Pointer<ShakeClass?>
    val interfacePointers: List<Pointer<ShakeClass>>

    override val qualifiedName: String
        get() = (pkg?.qualifiedName?.plus(".") ?: "") + name

    override val superClass: ShakeClass? get() = superClassPointer.value
    private var _interfaces: List<ShakeClass?> = listOf()
        private set

    override val interfaces: List<ShakeClass>
        get() = _interfaces.map { it!! }

    constructor(
        prj: ImmutableShakeProject,
        pkg: ImmutableShakePackage?,
        parentScope: ImmutableShakeScope,
        name: String,
        methods: List<ImmutableShakeMethod>,
        fields: List<ShakeClassField>,
        classes: List<ImmutableShakeClass>,
        staticMethods: List<ImmutableShakeMethod>,
        staticFields: List<ShakeClassField>,
        staticClasses: List<ImmutableShakeClass>,
        constructors: List<ShakeConstructor> = listOf(),
        superClass: ShakeClass?,
        interfaces: List<ShakeClass>
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
        this.superClassPointer = Pointer.of(superClass)
        this.interfacePointers = interfaces.map { Pointer.of(it) }
    }

    override fun compatibleTo(other: ShakeClass): Boolean {
        if (this == other) return true
        if (this.superClass != null && this.superClass!!.compatibleTo(other)) return true
        return this.interfaces.any { it.compatibleTo(other) }
    }

    override fun compatibilityDistance(other: ShakeClass): Int {
        if (this == other) return 0
        val scd = (this.superClass?.compatibilityDistance(other) ?: -1) + 1
        val intDistance = (this.interfaces.minOfOrNull { it.compatibilityDistance(other) } ?: -2) + 1
        if(scd < 0) return intDistance
        if(intDistance < 0) return scd
        return min(scd, intDistance)
    }

    override fun toJson(): Map<String, Any?> {
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

    inner class StaticScope : ImmutableShakeScope {

        override val parent: ImmutableShakeScope get() = parentScope

        override fun get(name: String): ShakeAssignable? {
            return staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun getFunctions(name: String): List<ImmutableShakeFunction> {
            return staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun getClass(name: String): ImmutableShakeClass? {
            return staticClasses.find { it.name == name } ?: parent.getClass(name)
        }

        override fun getInvokable(name: String): List<ShakeInvokable> {
            return getFunctions(name) + parent.getInvokable(name)
        }

        override fun use(name: String) {
            TODO("Not yet implemented")
        }

    }

    inner class InstanceScope : ImmutableShakeScope {

        override val parent: ImmutableShakeScope get() = parentScope

        override fun get(name: String): ShakeAssignable? {
            return fields.find { it.name == name } ?: staticFields.find { it.name == name } ?: parent.get(name)
        }

        override fun getFunctions(name: String): List<ImmutableShakeFunction> {
            return methods.filter { it.name == name } + staticMethods.filter { it.name == name } + parent.getFunctions(name)
        }

        override fun getClass(name: String): ImmutableShakeClass? {
            return classes.find { it.name == name } ?: parent.getClass(name)
        }

        override fun getInvokable(name: String): List<ShakeInvokable> {
            return getFunctions(name) + parent.getInvokable(name)
        }

        override fun use(name: String) {
            TODO("Not yet implemented")
        }

    }
}