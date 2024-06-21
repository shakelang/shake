package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.util.primitives.bits.*
import kotlin.math.min

/**
 * A ShakeClass is a class in the Shake programming language
 *
 * ```shake
 * class MyClass {
 *    // ...
 * }
 * ```
 */
interface ShakeClass {

    /**
     * The project this class is in. This also keeps the project if this class is inside of
     * a package or class
     * @see ShakeProject
     */
    val staticScope: ShakeScope

    /**
     * The project this class is in. This also keeps the project if this class is inside of
     * a package or class
     * @see ShakeProject
     */
    val instanceScope: ShakeScope

    /**
     * The project this class is in. This also keeps the project if this class is inside of
     * a package or class
     * @see ShakeProject
     */
    val prj: ShakeProject

    /**
     * The package this class is in. Also holds the package if this class is in a class
     * @see ShakePackage
     */
    val pkg: ShakePackage?

    /**
     * The class this class is in. This is null if the class is not within a class
     * @see ShakeClass
     */
    val clazz: ShakeClass?

    /**
     * The parent scope of this class. The class's scope will extend this scope.
     * @see ShakeScope
     */
    val parentScope: ShakeScope

    /**
     * The (simple) name of this class
     */
    val name: String

    /**
     * The methods of this class
     */
    val methods: List<ShakeMethod>

    /**
     * The fields of this class
     */
    val fields: List<ShakeField>

    /**
     * The classes of this class
     */
    val classes: List<ShakeClass>

    /**
     * Is this class an interface?
     */
    val isInterface: Boolean

    /**
     * Is this class an enum?
     */
    val isEnum: Boolean

    /**
     * Is this class an annotation?
     */
    val isAnnotation: Boolean

    /**
     * Is this class an object?
     */
    val isObject: Boolean

    /**
     * Is this class abstract?
     */
    val isAbstract: Boolean

    /**
     * Is this class final?
     */
    val isFinal: Boolean

    /**
     * Is this class static?
     */
    val isStatic: Boolean

    /**
     * Is this class public?
     */
    val isPublic: Boolean

    /**
     * Is this class private?
     */
    val isPrivate: Boolean

    /**
     * Is this class protected?
     */
    val isProtected: Boolean

    /**
     * Is this class native?
     */
    val isNative: Boolean

    val flags: ShakeClassFlags get() =
        ShakeClassFlags(
            isInterface,
            isEnum,
            isAnnotation,
            isObject,
            isAbstract,
            isFinal,
            isStatic,
            isPublic,
            isPrivate,
            isProtected,
            isNative,
        )

    /**
     * The instance methods of this class
     * @see ShakeMethod
     */
    val instanceMethods: List<ShakeMethod> get() = methods.filter { !it.isStatic }

    /**
     * The instance fields of this class
     * @see ShakeField
     */
    val instanceFields: List<ShakeField> get() = fields.filter { !it.isStatic }

    /**
     * The instance classes of this class
     * @see ShakeClass
     */
    val instanceClasses: List<ShakeClass> get() = classes.filter { !it.isStatic }

    /**
     * The static methods of this class
     * @see ShakeMethod
     */
    val staticMethods: List<ShakeMethod> get() = methods.filter { it.isStatic }

    /**
     * The static fields of this class
     * @see ShakeField
     */
    val staticFields: List<ShakeField> get() = fields.filter { it.isStatic }

    /**
     * The static classes of this class
     * @see ShakeClass
     */
    val staticClasses: List<ShakeClass> get() = classes.filter { it.isStatic }

    /**
     * The constructors of this class
     * @see ShakeConstructor
     */
    val constructors: List<ShakeConstructor>

    /**
     * The prefix for child entities (classes, fields, methods, ...) of this class
     */
    val qualifierPrefix: String
        get() = (if (clazz != null) clazz!!.qualifierPrefix else pkg!!.qualifierPrefix) + name + ":"

    /**
     * The qualified name of this class (including the package and parent classes)
     */
    val qualifiedName: String
        get() = (if (clazz != null) clazz!!.qualifierPrefix else pkg!!.qualifierPrefix) + name

    /**
     * The signature of this class (including the package and parent classes)
     */
    val signature: String get() = qualifiedName

    /**
     * The superclass of this class
     */
    val superClass: ShakeType.Object

    /**
     * The interfaces of this class
     */
    val interfaces: List<ShakeType.Object>

    /**
     * All instance-methods of this class (including methods defined in superclasses and interfaces)
     * Overridden methods are not included
     */
    val allMethods: List<ShakeMethod>
        get() {
            val methods = this.methods.filter { !it.isStatic }
            val superMethods = superClass.clazz.allMethods.filter {
                methods.none { m -> m.name == it.name && m.signature == it.signature }
            }
            val interfaceMethods = interfaces.flatMap { it.clazz.allMethods }.filter {
                methods.none { m -> m.name == it.name && m.signature == it.signature } &&
                    // Super methods have priority over interface methods
                    superMethods.none { m -> m.name == it.name && m.signature == it.signature }
            }
            // TODO: Check for duplicate methods (caused by multiple interfaces)
            // This will only be a problem if there are multiple default implementations
            return methods + superMethods + interfaceMethods
        }

    /**
     * All instance-fields of this class (including fields defined in superclasses and interfaces)
     * Overridden fields are not included
     */
    val allFields: List<ShakeField>
        get() {
            val fields = this.fields.filter { !it.isStatic }
            val superFields = if (isObjectClass) {
                emptyList()
            } else {
                superClass.clazz.allFields.filter {
                    fields.none { f -> f.name == it.name }
                }
            }
            val interfaceFields = interfaces.flatMap { it.clazz.allFields }.filter {
                fields.none { f -> f.name == it.name } &&
                    // Super fields have priority over interface fields
                    superFields.none { f -> f.name == it.name }
            }

            // TODO: Check for duplicate fields (caused by multiple interfaces)
            // This will only be a problem if there are multiple default implementations
            return fields + superFields + interfaceFields
        }

    /**
     * Is this class the default "shake/lang/Object" class?
     * This is needed to terminate the recursion in [compatibleTo], [compatibilityDistance],
     * [allMethods] and [allFields]
     */
    private val isObjectClass get() = qualifiedName == "shake/lang/Object"

    /**
     * Is this class compatible to the other class?
     */
    fun compatibleTo(other: ShakeClass): Boolean {
        if (this == other) return true
        if (isObjectClass) return false
        if (this.superClass.clazz.compatibleTo(other)) return true
        return this.interfaces.any { it.clazz.compatibleTo(other) }
    }

    /**
     * The compatibility distance between this class and the other class
     * @see compatibleTo
     */
    fun compatibilityDistance(other: ShakeClass): Int {
        if (this == other) return 0
        if (isObjectClass) return -1
        val scd = this.superClass.clazz.compatibilityDistance(other) + 1
        val intDistance = (this.interfaces.minOfOrNull { it.clazz.compatibilityDistance(other) } ?: -2) + 1
        if (scd < 0) return intDistance
        if (intDistance < 0) return scd
        return min(scd, intDistance)
    }

    /**
     * Get the json representation of this class
     */
    fun toJson(): Map<String, Any?> = mapOf(
        "name" to this.name,
        "super" to this.superClass.qualifiedName,
        "interfaces" to this.interfaces.map { it.name },
        "methods" to this.methods.map { it.toJson() },
        "staticMethods" to this.staticMethods.map { it.toJson() },
        "fields" to this.fields.map { it.toJson() },
        "staticFields" to this.staticFields.map { it.toJson() },
        "constructors" to this.constructors.map { it.toJson() },
        "classes" to this.classes.map { it.toJson() },
        "staticClasses" to this.staticClasses.map { it.toJson() },
    )

    /**
     * Get a child class
     */
    fun getClass(descriptor: List<String>): ShakeClass? {
        if (descriptor.isEmpty()) return this
        val subClass = this.classes.find { it.name == descriptor.first() } ?: return null
        return subClass.getClass(descriptor.drop(1))
    }

    /**
     * Get a child class
     */
    fun getClass(descriptor: Array<String>) = getClass(descriptor.toList())

    /**
     * Get a child class
     */
    fun getClass(descriptor: String) = classes.find { it.name == descriptor }

    /**
     * Get a child method
     */
    fun getMethods(descriptor: List<String>): List<ShakeMethod> {
        if (descriptor.isEmpty()) return emptyList()
        val methodName = descriptor.last()
        val classDescriptor = descriptor.dropLast(1)
        val clz = getClass(classDescriptor) ?: return emptyList()
        return clz.methods.filter { it.name == methodName }
    }

    /**
     * Get a child method
     */
    fun getMethods(descriptor: Array<String>) = getMethods(descriptor.toList())

    /**
     * Get a child method
     */
    fun getMethods(descriptor: String) = getMethods(descriptor.split("."))

    /**
     * Get a child field
     */
    fun getField(descriptor: List<String>): ShakeField? {
        if (descriptor.isEmpty()) return null
        val fieldName = descriptor.last()
        val classDescriptor = descriptor.dropLast(1)
        val clz = getClass(classDescriptor) ?: return null
        return clz.fields.find { it.name == fieldName }
    }

    /**
     * Get a child field
     */
    fun getField(descriptor: Array<String>) = getField(descriptor.toList())

    /**
     * Get a child field
     */
    fun getField(descriptor: String) = fields.find { it.name == descriptor }

    /**
     * Processor phase 1 action
     */
    fun phase1()

    /**
     * Processor phase 2 action
     */
    fun phase2()

    /**
     * Processor phase 3 action
     */
    fun phase3()

    /**
     * Processor phase 4 action
     */
    fun phase4()

    open class ShakeClassFlags(
        flags: Short,
    ) {

        constructor(flags: Int) : this(flags.toShort())
        constructor(
            isInterface: Boolean = false,
            isEnum: Boolean = false,
            isAnnotation: Boolean = false,
            isObject: Boolean = false,
            isAbstract: Boolean = false,
            isFinal: Boolean = false,
            isStatic: Boolean = false,
            isPublic: Boolean = false,
            isPrivate: Boolean = false,
            isProtected: Boolean = false,
            isNative: Boolean = false,
        ) : this(
            (0).toShort()
                .withBit0(isInterface)
                .withBit1(isEnum)
                .withBit2(isAnnotation)
                .withBit3(isObject)
                .withBit4(isAbstract)
                .withBit5(isFinal)
                .withBit6(isStatic)
                .withBit7(isPublic)
                .withBit8(isPrivate)
                .withBit9(isProtected)
                .withBit10(isNative),
        )

        var flags: Short = flags
            protected set

        open var isInterface: Boolean get() = flags.bit0
            protected set(value) {
                flags = flags.withBit0(value)
            }

        open var isEnum: Boolean get() = flags.bit1
            protected set(value) {
                flags = flags.withBit1(value)
            }

        open var isAnnotation: Boolean get() = flags.bit2
            protected set(value) {
                flags = flags.withBit2(value)
            }

        open var isObject: Boolean get() = flags.bit3
            protected set(value) {
                flags = flags.withBit3(value)
            }

        open var isAbstract: Boolean get() = flags.bit4
            protected set(value) {
                flags = flags.withBit4(value)
            }

        open var isFinal: Boolean get() = flags.bit5
            protected set(value) {
                flags = flags.withBit5(value)
            }

        open var isStatic: Boolean get() = flags.bit6
            protected set(value) {
                flags = flags.withBit6(value)
            }

        open var isPublic: Boolean get() = flags.bit7
            protected set(value) {
                flags = flags.withBit7(value)
            }

        open var isPrivate: Boolean get() = flags.bit8
            protected set(value) {
                flags = flags.withBit8(value)
            }

        open var isProtected: Boolean get() = flags.bit9
            protected set(value) {
                flags = flags.withBit9(value)
            }

        open var isNative: Boolean get() = flags.bit10
            protected set(value) {
                flags = flags.withBit10(value)
            }

        fun toFlags() = ShakeClassFlags(flags)
        fun toMutableFlags() = ShakeMutableClassFlags(flags)

        fun toShort() = flags
    }

    class ShakeMutableClassFlags(
        flags: Short,
    ) : ShakeClassFlags(flags) {
        constructor(flags: Int) : this(flags.toShort())
        constructor(
            isInterface: Boolean = false,
            isEnum: Boolean = false,
            isAnnotation: Boolean = false,
            isObject: Boolean = false,
            isAbstract: Boolean = false,
            isFinal: Boolean = false,
            isStatic: Boolean = false,
            isPublic: Boolean = false,
            isPrivate: Boolean = false,
            isProtected: Boolean = false,
            isNative: Boolean = false,
        ) : this(
            (0).toShort()
                .withBit0(isInterface)
                .withBit1(isEnum)
                .withBit2(isAnnotation)
                .withBit3(isObject)
                .withBit4(isAbstract)
                .withBit5(isFinal)
                .withBit6(isStatic)
                .withBit7(isPublic)
                .withBit8(isPrivate)
                .withBit9(isProtected)
                .withBit10(isNative),
        )

        override var isInterface: Boolean
            get() = super.isInterface
            set(value) {
                super.isInterface = value
            }

        override var isEnum: Boolean
            get() = super.isEnum
            set(value) {
                super.isEnum = value
            }

        override var isAnnotation: Boolean
            get() = super.isAnnotation
            set(value) {
                super.isAnnotation = value
            }

        override var isObject: Boolean
            get() = super.isObject
            set(value) {
                super.isObject = value
            }

        override var isAbstract: Boolean
            get() = super.isAbstract
            set(value) {
                super.isAbstract = value
            }

        override var isFinal: Boolean
            get() = super.isFinal
            set(value) {
                super.isFinal = value
            }

        override var isStatic: Boolean
            get() = super.isStatic
            set(value) {
                super.isStatic = value
            }

        override var isPublic: Boolean
            get() = super.isPublic
            set(value) {
                super.isPublic = value
            }

        override var isPrivate: Boolean
            get() = super.isPrivate
            set(value) {
                super.isPrivate = value
            }

        override var isProtected: Boolean
            get() = super.isProtected
            set(value) {
                super.isProtected = value
            }

        override var isNative: Boolean
            get() = super.isNative
            set(value) {
                super.isNative = value
            }
    }
}
