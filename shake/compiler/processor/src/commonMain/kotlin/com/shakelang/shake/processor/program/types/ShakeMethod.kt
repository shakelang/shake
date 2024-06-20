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
    val flags: Short get() =
        (0).toShort()
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
            .withBit10(isExtension)

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
}
