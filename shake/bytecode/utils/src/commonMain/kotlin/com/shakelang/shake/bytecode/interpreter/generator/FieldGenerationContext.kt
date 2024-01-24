package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.MutableField
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.attributes.AttributeGenerationContext

@Suppress("MemberVisibilityCanBePrivate", "unused")
class FieldGenerationContext(
    val parentPathBase: String,
    val constantPool: MutableConstantPool,
) {

    val path get() = "$parentPathBase$name"

    var name: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("Name already specified")
            field = value
        }

    var type: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("Type already specified")
            field = value
        }

    var flags: Short = 0x00

    var isPublic: Boolean
        get() = Flags.isPublic(flags)
        set(value) {
            flags = Flags.setPublic(flags, value)
        }

    var isPrivate: Boolean
        get() = Flags.isPrivate(flags)
        set(value) {
            flags = Flags.setPrivate(flags, value)
        }

    var isProtected: Boolean
        get() = Flags.isProtected(flags)
        set(value) {
            flags = Flags.setProtected(flags, value)
        }

    var isStatic: Boolean
        get() = Flags.isStatic(flags)
        set(value) {
            flags = Flags.setStatic(flags, value)
        }

    var isFinal: Boolean
        get() = Flags.isFinal(flags)
        set(value) {
            flags = Flags.setFinal(flags, value)
        }

    var isAbstract: Boolean
        get() = Flags.isAbstract(flags)
        set(value) {
            flags = Flags.setAbstract(flags, value)
        }

    val attributes: MutableList<AttributeGenerationContext> = mutableListOf()

    fun attribute(generator: AttributeGenerationContext.() -> Unit) {
        val ctx = AttributeGenerationContext(constantPool)
        ctx.generator()
        attributes.add(ctx)
    }

    fun attribute(name: String, data: ByteArray) {
        val ctx = AttributeGenerationContext(constantPool)
        ctx.name = name
        ctx.data = data
        attributes.add(ctx)
    }

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Attribute(generator: AttributeGenerationContext.() -> Unit) {
        val ctx = AttributeGenerationContext(constantPool)
        ctx.generator()
        attributes.add(ctx)
    }

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Attribute(name: String, data: ByteArray) {
        val ctx = AttributeGenerationContext(constantPool)
        ctx.name = name
        ctx.data = data
        attributes.add(ctx)
    }

    fun toField(
        pool: MutableConstantPool,
    ): Field {
        val nameConstant = pool.resolveUtf8(name)
        val typeConstant = pool.resolveUtf8(type)
        return Field(
            pool,
            nameConstant,
            typeConstant,
            flags,
            attributes.map { it.toAttribute(pool) },
        )
    }

    fun toMutableField(
        pool: MutableConstantPool,
    ): MutableField {
        val nameConstant = pool.resolveUtf8(name)
        val typeConstant = pool.resolveUtf8(type)
        return MutableField(
            pool,
            nameConstant,
            typeConstant,
            flags,
            attributes.map { it.toMutableAttribute(pool) }.toMutableList(),
        )
    }

    companion object {
        val Flags = Field.Companion.Flags
    }
}
