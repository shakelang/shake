package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue
import com.shakelang.util.primitives.bits.*

/**
 * A ShakeField is a field in a ShakeClass or ShakePackage
 * @see ShakeClass
 * @see ShakePackage
 */
interface ShakeField :
    ShakeDeclaration,
    ShakeAssignable {

    /**
     * The project this field is in.
     * This also keeps the project if this field is inside a package or class
     * @see ShakeProject
     */
    val project: ShakeProject

    /**
     * The package this field is in. Also holds the package if this field is in a class
     * @see ShakePackage
     */
    val pkg: ShakePackage?

    /**
     * The class this field is in. This is null if the field is not within a class
     * @see ShakeClass
     */
    val clazz: ShakeClass?

    /**
     * The parent scope for initializing this field (the scope of the class or package)
     */
    val parentScope: ShakeScope

    /**
     * The (simple) name of this field
     */
    override val name: String

    /**
     * Is this field static?
     */
    val isStatic: Boolean

    /**
     * Is this field final?
     */
    val isFinal: Boolean

    /**
     * Is this field abstract?
     */
    val isAbstract: Boolean

    /**
     * Is this field private?
     */
    val isPrivate: Boolean

    /**
     * Is this field protected?
     */
    val isProtected: Boolean

    /**
     * Is this field public?
     */
    val isPublic: Boolean

    /**
     * Is this field native?
     */
    val isNative: Boolean

    /**
     * Field flags
     */
    val flags: Short get() =
        (0).toShort()
            .withBit0(isStatic)
            .withBit1(isFinal)
            .withBit2(isAbstract)
            .withBit3(isPrivate)
            .withBit4(isProtected)
            .withBit5(isPublic)
            .withBit6(isNative)

    /**
     * The initial value of this field
     */
    val initialValue: ShakeValue?

    /**
     * The type this field expands
     */
    val expanding: ShakeType?

    /**
     * The unique name of this field
     */
    override val uniqueName: String
        get() = qualifiedName

    /**
     * The qualified name of this field
     */
    override val qualifiedName: String
        get() = (if (clazz != null) clazz!!.qualifierPrefix else pkg!!.qualifierPrefix) + name

    /**
     * The signature of this field
     */
    val signature: String get() = name

    /**
     * The type resulting when assigning this field
     */
    override fun assignType(other: ShakeType, scope: ShakeScope): ShakeType = type.assignType(other, scope) ?: other

    /**
     * The type resulting when add-assigning this field
     */
    override fun additionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.additionAssignType(other, scope) ?: type

    /**
     * The type resulting when subtract-assigning this field
     */
    override fun subtractionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.subtractionAssignType(other, scope) ?: type

    /**
     * The type resulting when multiply-assigning this field
     */
    override fun multiplicationAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.multiplicationAssignType(other, scope) ?: type

    /**
     * The type resulting when divide-assigning this field
     */
    override fun divisionAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.divisionAssignType(other, scope) ?: type

    /**
     * The type resulting when modulus-assigning this field
     */
    override fun modulusAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.modulusAssignType(other, scope) ?: type

    /**
     * The type resulting when power-assigning this field
     */
    override fun powerAssignType(other: ShakeType, scope: ShakeScope): ShakeType =
        type.powerAssignType(other, scope) ?: type

    /**
     * The type resulting when incrementing this field (increment before)
     */
    override fun incrementBeforeType(scope: ShakeScope): ShakeType = type.incrementBeforeType(scope) ?: type

    /**
     * The type resulting when incrementing this field (increment after)
     */
    override fun incrementAfterType(scope: ShakeScope): ShakeType = type.incrementAfterType(scope) ?: type

    /**
     * The type resulting when decrementing this field (decrement before)
     */
    override fun decrementBeforeType(scope: ShakeScope): ShakeType? = type.decrementBeforeType(scope) ?: type

    /**
     * The type resulting when decrementing this field (decrement after)
     */
    override fun decrementAfterType(scope: ShakeScope): ShakeType? = type.decrementAfterType(scope) ?: type

    /**
     * Get the json representation of this field
     */
    override fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "isStatic" to isStatic,
        "isFinal" to isFinal,
        "isAbstract" to isAbstract,
        "isPrivate" to isPrivate,
        "isProtected" to isProtected,
        "isPublic" to isPublic,
        "type" to type.toJson(),
    )

    /**
     * Processor phase 3 action
     */
    fun phase3()

    /**
     * Processor phase 4 action
     */
    fun phase4()
}
