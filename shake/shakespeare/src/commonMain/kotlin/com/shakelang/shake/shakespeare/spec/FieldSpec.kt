@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.AbstractSpec

open class FieldSpec(
    val name: NamespaceSpec,
    val type: TypeSpec,
    val isVal: Boolean = true,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isSynchronized: Boolean = false,
    val isNative: Boolean = false,
) : AbstractSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        if (isStatic) builder.append("static ")
        if (isFinal) builder.append("final ")
        if (isSynchronized) builder.append("synchronized ")
        if (isNative) builder.append("native ")
        builder.append(if (isVal) "val" else "var")
            .append(" ")
            .append(name)
            .append(": ")
            .append(type.generate(ctx))
        return builder.toString()
    }

    open class FieldSpecBuilder
    internal constructor() {
        var name: NamespaceSpec? = null
        var type: TypeSpec? = null
        var isStatic = false
        var isFinal = false
        var accessModifier = AccessModifier.PUBLIC
        var isSynchronized = false
        var isNative = false

        fun name(name: NamespaceSpec): FieldSpecBuilder {
            this.name = name
            return this
        }

        fun type(type: TypeSpec): FieldSpecBuilder {
            this.type = type
            return this
        }

        fun static(isStatic: Boolean = true): FieldSpecBuilder {
            this.isStatic = isStatic
            return this
        }

        fun final(isFinal: Boolean = true): FieldSpecBuilder {
            this.isFinal = isFinal
            return this
        }

        fun accessModifier(accessModifier: AccessModifier): FieldSpecBuilder {
            this.accessModifier = accessModifier
            return this
        }

        fun synchronized(isSynchronized: Boolean = true): FieldSpecBuilder {
            this.isSynchronized = isSynchronized
            return this
        }

        fun native(isNative: Boolean = true): FieldSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun build(): FieldSpec {
            return FieldSpec(name!!, type!!)
        }
    }

    companion object {
        fun builder(): FieldSpecBuilder {
            return FieldSpecBuilder()
        }
    }
}
