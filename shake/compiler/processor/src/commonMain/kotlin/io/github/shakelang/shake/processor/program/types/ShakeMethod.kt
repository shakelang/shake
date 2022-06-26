package io.github.shakelang.shake.processor.program.types

import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable
import io.github.shakelang.shake.processor.program.types.code.ShakeScope


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

    override val qualifiedName: String get() = "${(clazz?.qualifiedName ?: pkg?.qualifiedName)?.plus(".")}$name(${parameters.joinToString(", ") { it.type.qualifiedName }})${returnType.qualifiedName}"
    val signature: String get() = "$name(${parameters.joinToString(", ") { it.type.qualifiedName }})${returnType.qualifiedName}"
    val qualifiedSignature: String get() = "${(clazz?.qualifiedName ?: pkg?.qualifiedName)?.plus(".")}$signature"
    override val returnType: ShakeType
    val scope : ShakeScope

    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
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
}