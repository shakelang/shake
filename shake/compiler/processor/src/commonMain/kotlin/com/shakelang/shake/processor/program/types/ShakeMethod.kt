package com.shakelang.shake.processor.program.types

import com.shakelang.shake.processor.program.types.code.ShakeInvokable
import com.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeMethod : ShakeInvokable {
    val prj: ShakeProject
    val pkg: ShakePackage?
    val clazz: ShakeClass?
    val parentScope: ShakeScope
    val name: String
    val isStatic: Boolean
    val isFinal: Boolean
    val isAbstract: Boolean
    val isSynchronized: Boolean
    val isStrict: Boolean
    val isPrivate: Boolean
    val isProtected: Boolean
    val isPublic: Boolean
    val isNative: Boolean
    val isOperator: Boolean
    val expanding: ShakeType?

    override val qualifiedName: String
        get() = "${(clazz?.qualifierPrefix ?: pkg?.qualifierPrefix)}$name"

    val parameterTypes: List<ShakeType> get() = listOfNotNull(expanding, *parameters.map { it.type }.toTypedArray())
    val signature: String
        get() = "$name(${parameterTypes.joinToString(",") { it.qualifiedName }})${returnType.qualifiedName}"
    val qualifiedSignature: String
        get() = "$qualifiedName(${parameterTypes.joinToString(",") { it.qualifiedName }})${returnType.qualifiedName}"

    override val returnType: ShakeType
    val scope: ShakeScope

    override fun toJson(): Map<String, Any?> {
        return mapOf(
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
            "body" to body?.toJson()
        )
    }



    fun phase3()
    fun phase4()
}
