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
     * Is this an extension field?
     */
    val isExtension: Boolean
        get() = expanding != null

    /**
     * Field flags
     */
    val flags: ShakeFieldFlags get() =
        ShakeFieldFlags(
            isStatic,
            isFinal,
            isAbstract,
            isPrivate,
            isProtected,
            isPublic,
            isNative,
            isExtension,
        )

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

    open class ShakeFieldFlags(
        flags: Short,
    ) {
        var flags: Short = flags
            protected set

        constructor(flags: Int) : this(flags.toShort())
        constructor(
            isStatic: Boolean = false,
            isFinal: Boolean = false,
            isAbstract: Boolean = false,
            isPrivate: Boolean = false,
            isProtected: Boolean = false,
            isPublic: Boolean = false,
            isNative: Boolean = false,
            isExtension: Boolean = false,
        ) : this(
            (0).toShort()
                .withBit0(isStatic)
                .withBit1(isFinal)
                .withBit2(isAbstract)
                .withBit3(isPrivate)
                .withBit4(isProtected)
                .withBit5(isPublic)
                .withBit6(isNative)
                .withBit7(isExtension),
        )

        open var isStatic: Boolean get() = flags.bit0
            protected set(value) {
                flags = flags.withBit0(value)
            }

        open var isFinal: Boolean get() = flags.bit1
            protected set(value) {
                flags = flags.withBit1(value)
            }

        open var isAbstract: Boolean get() = flags.bit2
            protected set(value) {
                flags = flags.withBit2(value)
            }

        open var isPrivate: Boolean get() = flags.bit3
            protected set(value) {
                flags = flags.withBit3(value)
            }

        open var isProtected: Boolean get() = flags.bit4
            protected set(value) {
                flags = flags.withBit4(value)
            }

        open var isPublic: Boolean get() = flags.bit5
            protected set(value) {
                flags = flags.withBit5(value)
            }

        open var isNative: Boolean get() = flags.bit6
            protected set(value) {
                flags = flags.withBit6(value)
            }

        open var isExtension: Boolean get() = flags.bit7
            protected set(value) {
                flags = flags.withBit7(value)
            }

        fun toFlags() = ShakeFieldFlags(flags)
        fun toMutableFlags() = ShakeMutableFieldFlags(flags)

        fun toShort() = flags
    }

    class ShakeMutableFieldFlags(
        flags: Short,
    ) : ShakeFieldFlags(flags) {

        constructor(flags: Int) : this(flags.toShort())
        constructor(
            isStatic: Boolean = false,
            isFinal: Boolean = false,
            isAbstract: Boolean = false,
            isPrivate: Boolean = false,
            isProtected: Boolean = false,
            isPublic: Boolean = false,
            isNative: Boolean = false,
            isExtension: Boolean = false,
        ) : this(
            (0).toShort()
                .withBit0(isStatic)
                .withBit1(isFinal)
                .withBit2(isAbstract)
                .withBit3(isPrivate)
                .withBit4(isProtected)
                .withBit5(isPublic)
                .withBit6(isNative)
                .withBit7(isExtension),
        )

        override var isStatic: Boolean
            get() = super.isStatic
            set(value) {
                super.isStatic = value
            }

        override var isFinal: Boolean
            get() = super.isFinal
            set(value) {
                super.isFinal = value
            }

        override var isAbstract: Boolean
            get() = super.isAbstract
            set(value) {
                super.isAbstract = value
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

        override var isPublic: Boolean
            get() = super.isPublic
            set(value) {
                super.isPublic = value
            }

        override var isNative: Boolean
            get() = super.isNative
            set(value) {
                super.isNative = value
            }

        override var isExtension: Boolean
            get() = super.isExtension
            set(value) {
                super.isExtension = value
            }
    }
}
