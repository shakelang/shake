package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeInvokable
import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.util.primitives.bits.*

/**
 * A ShakeMethod is a method in a ShakeClass or ShakePackage
 * @see ShakeClass
 * @see ShakePackage
 */
interface ShakeMethod : ShakeInvokable {

    /**
     * The project this method is in. This also keeps the project if this method is inside of
     * a package or class
     * @see ShakeProject
     */
    val prj: ShakeProject

    /**
     * The package this method is in. Also holds the package if this method is in a class
     * @see ShakePackage
     */
    val pkg: ShakePackage?

    /**
     * The class this method is in. This is null if the method is not within a class
     * @see ShakeClass
     */
    val clazz: ShakeClass?

    /**
     * The parent scope of this method. The method's scope will extend this scope.
     * @see ShakeScope
     */
    val parentScope: ShakeScope

    /**
     * The (simple) name of this method
     */
    val name: String

    /**
     * Is this method static?
     */
    val isStatic: Boolean

    /**
     * Is this method final?
     */
    val isFinal: Boolean

    /**
     * Is this method abstract?
     */
    val isAbstract: Boolean

    /**
     * Is this method synchronized?
     */
    val isSynchronized: Boolean

    /**
     * Is this method strict?
     */
    val isStrict: Boolean

    /**
     * Is this method private?
     */
    val isPrivate: Boolean

    /**
     * Is this method protected?
     */
    val isProtected: Boolean

    /**
     * Is this method public?
     */
    val isPublic: Boolean

    /**
     * Is this method native?
     */
    val isNative: Boolean

    /**
     * Is this method an operator?
     */
    val isOperator: Boolean

    /**
     * Is this method an extension method?
     */
    val isExtension: Boolean
        get() = expanding != null

    /**
     * The method's flags
     */
    val flags: ShakeMethodFlags get() = ShakeMethodFlags(
        isStatic,
        isFinal,
        isAbstract,
        isSynchronized,
        isStrict,
        isPrivate,
        isProtected,
        isPublic,
        isNative,
        isOperator,
        isExtension,
    )

    /**
     * The type this method expands (int.toString() -> String)
     */
    val expanding: ShakeType?

    /**
     * The qualified name of this method, including the package and class.
     * **This is not unique, as it does not contain the signature!**
     * @see qualifiedSignature
     */
    override val qualifiedName: String
        get() = "${(clazz?.qualifierPrefix ?: pkg?.qualifierPrefix)}$name"

    /**
     * The signature of this method, including the parameter types and the return type.
     * Does not include the package or class.
     * @see qualifiedSignature
     * @see name
     */
    val signature: String
        get() = "$name(${parameterTypes.joinToString(",") { it.qualifiedName }})${returnType.qualifiedName}"

    /**
     * The qualified signature of this method, including the package, class, parameter types and the return type.
     * This must be unique and can be threaded as a unique identifier.
     * @see signature
     * @see qualifiedName
     */
    val qualifiedSignature: String
        get() = "$qualifiedName(${parameterTypes.joinToString(",") { it.qualifiedName }})${returnType.qualifiedName}"

    /**
     * The parameter types of this method
     */
    val parameterTypes: List<ShakeType> get() = listOfNotNull(expanding, *parameters.map { it.type }.toTypedArray())

    /**
     * The return type of this method
     */
    override val returnType: ShakeType

    /**
     * The body of this method
     */
    val scope: ShakeScope

    /**
     * The json representation of this method
     */
    override fun toJson(): Map<String, Any?> = mapOf(
        "name" to name,
        "qualifiedName" to qualifiedName,
        "signature" to signature,
        "qualifiedSignature" to qualifiedSignature,
        "isNative" to isNative,
        "isStatic" to isStatic,
        "isFinal" to isFinal,
        "isAbstract" to isAbstract,
        "isSynchronized" to isSynchronized,
        "isStrict" to isStrict,
        "isPrivate" to isPrivate,
        "isProtected" to isProtected,
        "isPublic" to isPublic,
        "returnType" to returnType.toJson(),
        "parameters" to parameters.map { it.toJson() },
        "body" to body?.toJson(),
    )

    /**
     * Processor phase 3 action
     */
    fun phase3()

    /**
     * Processor phase 4 action
     */
    fun phase4()

    class Mutable

    open class ShakeMethodFlags(
        flags: Short,
    ) {

        var flags: Short = flags
            protected set

        constructor(flags: Int) : this(flags.toShort())

        constructor(
            isStatic: Boolean = false,
            isFinal: Boolean = false,
            isAbstract: Boolean = false,
            isSynchronized: Boolean = false,
            isStrict: Boolean = false,
            isPrivate: Boolean = false,
            isProtected: Boolean = false,
            isPublic: Boolean = false,
            isNative: Boolean = false,
            isOperator: Boolean = false,
            isExtension: Boolean = false,
        ) : this(
            0
                .withBit0(isStatic)
                .withBit1(isFinal)
                .withBit2(isAbstract)
                .withBit3(isSynchronized)
                .withBit4(isStrict)
                .withBit5(isPrivate)
                .withBit6(isProtected)
                .withBit7(isPublic)
                .withBit8(isNative)
                .withBit9(isOperator)
                .withBit10(isExtension),
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

        open var isSynchronized: Boolean get() = flags.bit3
            protected set(value) {
                flags = flags.withBit3(value)
            }

        open var isStrict: Boolean get() = flags.bit4
            protected set(value) {
                flags = flags.withBit4(value)
            }

        open var isPrivate: Boolean get() = flags.bit5
            protected set(value) {
                flags = flags.withBit5(value)
            }

        open var isProtected: Boolean get() = flags.bit6
            protected set(value) {
                flags = flags.withBit6(value)
            }

        open var isPublic: Boolean get() = flags.bit7
            protected set(value) {
                flags = flags.withBit7(value)
            }

        open var isNative: Boolean get() = flags.bit8
            protected set(value) {
                flags = flags.withBit8(value)
            }

        open var isOperator: Boolean get() = flags.bit9
            protected set(value) {
                flags = flags.withBit9(value)
            }

        open var isExtension: Boolean get() = flags.bit10
            protected set(value) {
                flags = flags.withBit10(value)
            }

        fun toFlags(): ShakeMethodFlags = ShakeMethodFlags(flags)
        fun toMutableFlags(): MutableShakeMethodFlags = MutableShakeMethodFlags(flags)

        fun toShort(): Short = flags
    }

    class MutableShakeMethodFlags(flags: Short) : ShakeMethodFlags(flags) {

        constructor(flags: Int) : this(flags.toShort())
        constructor(
            isStatic: Boolean = false,
            isFinal: Boolean = false,
            isAbstract: Boolean = false,
            isSynchronized: Boolean = false,
            isStrict: Boolean = false,
            isPrivate: Boolean = false,
            isProtected: Boolean = false,
            isPublic: Boolean = false,
            isNative: Boolean = false,
            isOperator: Boolean = false,
            isExtension: Boolean = false,
        ) : this(
            0
                .withBit0(isStatic)
                .withBit1(isFinal)
                .withBit2(isAbstract)
                .withBit3(isSynchronized)
                .withBit4(isStrict)
                .withBit5(isPrivate)
                .withBit6(isProtected)
                .withBit7(isPublic)
                .withBit8(isNative)
                .withBit9(isOperator)
                .withBit10(isExtension),
        )

        override var isStatic: Boolean get() = super.isStatic
            set(value) {
                super.isStatic = value
            }

        override var isFinal: Boolean get() = super.isFinal
            set(value) {
                super.isFinal = value
            }

        override var isAbstract: Boolean get() = super.isAbstract
            set(value) {
                super.isAbstract = value
            }

        override var isSynchronized: Boolean get() = super.isSynchronized
            set(value) {
                super.isSynchronized = value
            }

        override var isStrict: Boolean get() = super.isStrict
            set(value) {
                super.isStrict = value
            }

        override var isPrivate: Boolean get() = super.isPrivate
            set(value) {
                super.isPrivate = value
            }

        override var isProtected: Boolean get() = super.isProtected
            set(value) {
                super.isProtected = value
            }

        override var isPublic: Boolean get() = super.isPublic
            set(value) {
                super.isPublic = value
            }

        override var isNative: Boolean get() = super.isNative
            set(value) {
                super.isNative = value
            }

        override var isOperator: Boolean get() = super.isOperator
            set(value) {
                super.isOperator = value
            }

        override var isExtension: Boolean get() = super.isExtension
            set(value) {
                super.isExtension = value
            }
    }
}
