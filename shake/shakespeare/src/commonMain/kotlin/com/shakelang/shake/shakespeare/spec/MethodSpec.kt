@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.CodeSpec

class ParameterSpec(
    val name: Identifier,
    val type: Type,
) {
    fun generate(ctx: GenerationContext): String {
        return "${type.generate(ctx)} name"
    }

    class ParameterSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var type: Type? = null
        fun name(name: Identifier): ParameterSpecBuilder {
            this.name = name
            return this
        }

        fun type(type: Type): ParameterSpecBuilder {
            this.type = type
            return this
        }

        fun build(): ParameterSpec {
            return ParameterSpec(name!!, type!!)
        }
    }

    companion object {
        fun builder(): ParameterSpecBuilder {
            return ParameterSpecBuilder()
        }
    }
}

class MethodSpec(
    val name: Identifier,
    val returnType: Type,
    val parameters: List<ParameterSpec>,
    val body: CodeSpec,
    val isStatic: Boolean = false,
    val isAbstract: Boolean = false,
    val isFinal: Boolean = false,
    val isOverride: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) {
    fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()

        builder.append(accessModifier.prefix())
        if (isStatic) builder.append("static ")
        if (isAbstract) builder.append("abstract ")
        if (isFinal) builder.append("final ")
        if (isOverride) builder.append("override ")
        if (isSynchronized) builder.append("synchronized ")
        if (isNative) builder.append("native ")

        builder.append(returnType.generate(ctx))
        builder.append(" ")
        builder.append(name)
        builder.append("(")
        builder.append(parameters.joinToString(", ") { it.generate(ctx) })
        builder.append(") ")
        builder.append(body.generate(ctx))
        return builder.toString()
    }

    class MethodSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var returnType: Type? = null
        val parameters: MutableList<ParameterSpec> = ArrayList()
        var body: CodeSpec? = null
        var isStatic = false
        var isAbstract = false
        var isFinal = false
        var isOverride = false
        var accessModifier = AccessModifier.PUBLIC
        var isSynchronized = false
        var isNative = false

        fun name(name: Identifier): MethodSpecBuilder {
            this.name = name
            return this
        }

        fun returnType(returnType: Type): MethodSpecBuilder {
            this.returnType = returnType
            return this
        }

        fun addParameter(parameter: ParameterSpec): MethodSpecBuilder {
            parameters.add(parameter)
            return this
        }

        fun body(body: CodeSpec): MethodSpecBuilder {
            this.body = body
            return this
        }

        fun static(isStatic: Boolean = true): MethodSpecBuilder {
            this.isStatic = isStatic
            return this
        }

        fun abstract(isAbstract: Boolean = true): MethodSpecBuilder {
            this.isAbstract = isAbstract
            return this
        }

        fun final(isFinal: Boolean = true): MethodSpecBuilder {
            this.isFinal = isFinal
            return this
        }

        fun override(isOverride: Boolean = true): MethodSpecBuilder {
            this.isOverride = isOverride
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): MethodSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun synchronized(isSynchronized: Boolean = true): MethodSpecBuilder {
            this.isSynchronized = isSynchronized
            return this
        }

        fun native(isNative: Boolean = true): MethodSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun build(): MethodSpec {
            return MethodSpec(name!!, returnType!!, parameters, body!!)
        }
    }

    companion object {
        fun builder(): MethodSpecBuilder {
            return MethodSpecBuilder()
        }
    }
}
