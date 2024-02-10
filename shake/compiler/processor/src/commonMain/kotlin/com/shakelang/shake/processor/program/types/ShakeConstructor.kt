package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.ShakeScope

/**
 * Represents a constructor in the Shake language.
 * Constructors are responsible for initializing new instances of a Shake class.
 *
 * @since 0.1.0
 */
interface ShakeConstructor {
    /**
     * The class which this constructor belongs to.
     */
    val clazz: ShakeClass

    /**
     * The body of the constructor. It may contain the initialization code.
     */
    val body: ShakeCode?

    /**
     * Indicates whether the constructor enforces strict type checking.
     */
    val isStrict: Boolean

    /**
     * Indicates if the constructor is private.
     */
    val isPrivate: Boolean

    /**
     * Indicates if the constructor is protected.
     */
    val isProtected: Boolean

    /**
     * Indicates if the constructor is public.
     */
    val isPublic: Boolean

    /**
     * Indicates if the constructor is native, i.e., implemented in a language other than Shake.
     */
    val isNative: Boolean

    /**
     * The name of the constructor. It may be null for anonymous constructors.
     */
    val name: String?

    /**
     * The parameters accepted by the constructor.
     */
    val parameters: List<ShakeParameter>

    /**
     * The scope in which the constructor is defined.
     */
    val scope: ShakeScope

    /**
     * Fully qualified name of the constructor, combining the class's qualifier prefix and the constructor's name.
     */
    val qualifiedName: String
        get() = "${clazz.qualifierPrefix}+${name ?: "default"}"

    /**
     * List of types of the parameters that this constructor accepts.
     */
    val parameterTypes: List<ShakeType>
        get() = parameters.map { it.type }

    /**
     * Signature of the constructor, including its name and parameter types.
     */
    val signature: String
        get() = "+${name ?: "default"}(${parameterTypes.joinToString(",") { it.qualifiedName }})N"

    /**
     * Fully qualified signature of the constructor, including the qualified name and parameter types.
     */
    val qualifiedSignature: String
        get() = "$qualifiedName(${parameterTypes.joinToString(",") { it.qualifiedName }})N"

    /**
     * Converts the constructor to a JSON representation.
     *
     * @return A map containing the JSON representation of the constructor.
     */
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "class" to clazz.toJson(),
            "body" to body?.toJson(),
            "isStrict" to isStrict,
            "isPrivate" to isPrivate,
            "isProtected" to isProtected,
            "isPublic" to isPublic,
            "name" to name,
            "parameters" to parameters.map { it.toJson() },
        )
    }

    /**
     * Performs the third phase of the constructor processing.
     */
    fun phase3()

    /**
     * Performs the fourth phase of the constructor processing.
     */
    fun phase4()
}
