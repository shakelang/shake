@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.AbstractSpec
import com.shakelang.shake.shakespeare.spec.code.CodeSpec

class ParameterSpec(
    val name: Identifier,
    val type: Type,
) : AbstractSpec {
    override fun generate(ctx: GenerationContext): String {
        return "${name.name}: ${type.generate(ctx)}"
    }

    open class ParameterSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var type: Type? = null
        fun name(name: Identifier): ParameterSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): ParameterSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun type(type: Type): ParameterSpecBuilder {
            this.type = type
            return this
        }

        fun type(type: String): ParameterSpecBuilder {
            this.type = Type.of(type)
            return this
        }

        fun build(): ParameterSpec {
            return ParameterSpec(
                name ?: throw IllegalStateException("Name must be set"),
                type ?: throw IllegalStateException("Type must be set"),
            )
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
    val extending: Type? = null,
    val parameters: List<ParameterSpec>,
    val body: CodeSpec?,
    val isStatic: Boolean = false,
    val isAbstract: Boolean = false,
    val isFinal: Boolean = false,
    val isOverride: Boolean = false,
    val isOperator: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) : AbstractSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()

        builder.append(accessModifier.prefix())
        if (isStatic) builder.append("static ")
        if (isAbstract) builder.append("abstract ")
        if (isFinal) builder.append("final ")
        if (isOverride) builder.append("override ")
        if (isSynchronized) builder.append("synchronized ")
        if (isNative) builder.append("native ")
        if (isOperator) builder.append("operator ")

        builder.append("fun ")

        if (extending != null) {
            builder.append(extending.generate(ctx)).append(".")
        }

        builder.append(name)
        builder.append("(")
        builder.append(parameters.joinToString(", ") { it.generate(ctx) })
        builder.append("): ")
        builder.append(returnType.generate(ctx))
        if (body != null) {
            builder.append(" ")
            builder.append(body.generate(ctx))
        }
        return builder.toString()
    }

    open class MethodSpecBuilder
    internal constructor() {
        var name: Identifier? = null
        var returnType: Type? = null
        var extending: Type? = null
        val parameters: MutableList<ParameterSpec> = ArrayList()
        var body: CodeSpec? = null
        var isStatic = false
        var isAbstract = false
        var isFinal = false
        var isOverride = false
        var accessModifier = AccessModifier.PUBLIC
        var isSynchronized = false
        var isNative = false
        var isOperator = false

        fun name(name: Identifier): MethodSpecBuilder {
            this.name = name
            return this
        }

        fun name(name: String): MethodSpecBuilder {
            this.name = Identifier(name)
            return this
        }

        fun returnType(returnType: Type): MethodSpecBuilder {
            this.returnType = returnType
            return this
        }

        fun returnType(returnType: String): MethodSpecBuilder {
            this.returnType = Type.of(returnType)
            return this
        }

        fun extending(extending: Type): MethodSpecBuilder {
            this.extending = extending
            return this
        }

        fun extending(extending: String): MethodSpecBuilder {
            this.extending = Type.of(extending)
            return this
        }

        fun addParameter(parameter: ParameterSpec): MethodSpecBuilder {
            parameters.add(parameter)
            return this
        }

        fun parameters(parameters: List<ParameterSpec>): MethodSpecBuilder {
            this.parameters.addAll(parameters)
            return this
        }

        fun parameters(vararg parameters: ParameterSpec): MethodSpecBuilder {
            this.parameters.addAll(parameters)
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

        fun operator(isOperator: Boolean = true): MethodSpecBuilder {
            this.isOperator = isOperator
            return this
        }

        fun build(): MethodSpec {
            return MethodSpec(
                name ?: throw IllegalStateException("Name must be set"),
                returnType ?: throw IllegalStateException("Return type must be set"),
                extending ?: throw IllegalStateException("Extending must be set"),
                parameters,
                body,
                isStatic,
                isAbstract,
                isFinal,
                isOverride,
                isOperator,
                accessModifier,
                isSynchronized,
                isNative,
            )
        }
    }

    companion object {
        fun builder(): MethodSpecBuilder {
            return MethodSpecBuilder()
        }
    }
}
