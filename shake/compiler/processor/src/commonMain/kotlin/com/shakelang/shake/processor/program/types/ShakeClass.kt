package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
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
    val superClass: ShakeClass

    /**
     * The interfaces of this class
     */
    val interfaces: List<ShakeClass>

    /**
     * All instance-methods of this class (including methods defined in superclasses and interfaces)
     * Overridden methods are not included
     */
    val allMethods: List<ShakeMethod>
        get() {
            val methods = this.methods.filter { !it.isStatic }
            val superMethods = superClass.allMethods.filter {
                methods.none { m -> m.name == it.name && m.signature == it.signature }
            }
            val interfaceMethods = interfaces.flatMap { it.allMethods }.filter {
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
                superClass.allFields.filter {
                    fields.none { f -> f.name == it.name }
                }
            }
            val interfaceFields = interfaces.flatMap { it.allFields }.filter {
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
        if (this.superClass.compatibleTo(other)) return true
        return this.interfaces.any { it.compatibleTo(other) }
    }

    /**
     * The compatibility distance between this class and the other class
     * @see compatibleTo
     */
    fun compatibilityDistance(other: ShakeClass): Int {
        if (this == other) return 0
        if (isObjectClass) return -1
        val scd = this.superClass.compatibilityDistance(other) + 1
        val intDistance = (this.interfaces.minOfOrNull { it.compatibilityDistance(other) } ?: -2) + 1
        if (scd < 0) return intDistance
        if (intDistance < 0) return scd
        return min(scd, intDistance)
    }

    /**
     * Get the json representation of this class
     */
    fun toJson(): Map<String, Any?> {
        return mapOf(
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
    }

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
}
