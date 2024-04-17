@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.spec.code.ValueSpec

open class FieldSpec(
    val name: String,
    open val type: TypeSpec?,
    val isVal: Boolean = true,
    val isStatic: Boolean = false,
    val isFinal: Boolean = false,
    val accessModifier: AccessModifier = AccessModifier.PUBLIC,
    val isNative: Boolean = false,
    val isConst: Boolean = false,
    val isOverride: Boolean = false,
    val isInline: Boolean = false,
    open val value: ValueSpec? = null,
) : AbstractSpec {
    override fun generate(ctx: GenerationContext): String {
        val builder = StringBuilder()
        builder.append(accessModifier.prefix())
        if (isStatic) builder.append("static ")
        if (isFinal) builder.append("final ")
        if (isNative) builder.append("native ")
        if (isOverride) builder.append("override ")
        if (isInline) builder.append("inline ")
        if (isConst) builder.append("const ")
        builder.append(if (isVal) "val" else "var")
            .append(" ")
            .append(name)
        if (type != null) builder.append(": ").append(type!!.generate(ctx))
        if (value != null) builder.append(" = ").append(value!!.generate(ctx))
        return builder.toString()
    }

    open class FieldSpecBuilder
    internal constructor() {
        var name: String? = null
        var type: TypeSpec? = null
        var isVal = false
        var isStatic = false
        var isFinal = false
        var accessModifier = AccessModifier.PUBLIC
        var isNative = false
        var isConst = false
        var isOverride = false
        var isInline = false
        var value: ValueSpec? = null

        fun isVal(isVal: Boolean = true): FieldSpecBuilder {
            this.isVal = isVal
            return this
        }

        fun name(name: String): FieldSpecBuilder {
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

        fun native(isNative: Boolean = true): FieldSpecBuilder {
            this.isNative = isNative
            return this
        }

        fun const(isConst: Boolean = true): FieldSpecBuilder {
            this.isConst = isConst
            return this
        }

        fun override(isOverride: Boolean = true): FieldSpecBuilder {
            this.isOverride = isOverride
            return this
        }

        fun inline(isInline: Boolean = true): FieldSpecBuilder {
            this.isInline = isInline
            return this
        }

        fun value(value: ValueSpec): FieldSpecBuilder {
            this.value = value
            return this
        }

        fun build(): FieldSpec {
            return FieldSpec(name!!, type!!, isVal, isStatic, isFinal, accessModifier, isNative, isConst, isOverride, isInline, value)
        }
    }

    companion object {
        fun builder(): FieldSpecBuilder {
            return FieldSpecBuilder()
        }
    }
}
