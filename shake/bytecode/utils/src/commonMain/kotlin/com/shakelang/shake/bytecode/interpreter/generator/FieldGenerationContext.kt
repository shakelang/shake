package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.MutableField
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.attributes.AttributeGenerationContext
import kotlin.experimental.and
import kotlin.experimental.or

class FieldGenerationContext {

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
        get() = flags and 0b00000000_00000001.toShort() != 0.toShort()
        set(value) = if (value) flags = flags or 0b00000000_00000001 else flags = flags and 0b1111111_11111110
    var isPrivate: Boolean
        get() = flags and 0b00000000_00000010.toShort() != 0.toShort()
        set(value) = if (value) flags = flags or 0b00000000_00000010 else flags = flags and 0b1111111_11111101

    var isProtected: Boolean
        get() = flags and 0b00000000_00000100.toShort() != 0.toShort()
        set(value) = if (value) flags = flags or 0b00000000_00000100 else flags = flags and 0b1111111_11111011
    var isStatic: Boolean
        get() = flags and 0b00000000_00001000.toShort() != 0.toShort()
        set(value) = if (value) flags = flags or 0b00000000_00001000 else flags = flags and 0b1111111_11110111
    var isFinal: Boolean
        get() = flags and 0b00000000_00010000.toShort() != 0.toShort()
        set(value) = if (value) flags = flags or 0b00000000_00010000 else flags = flags and 0b1111111_11101111
    val attributes: MutableList<AttributeGenerationContext> = mutableListOf()

    fun attribute(generator: AttributeGenerationContext.() -> Unit) {
        val ctx = AttributeGenerationContext()
        ctx.generator()
        attributes.add(ctx)
    }

    fun attribute(name: String, data: ByteArray) {
        val ctx = AttributeGenerationContext()
        ctx.name = name
        ctx.data = data
        attributes.add(ctx)
    }

    fun Attribute(generator: AttributeGenerationContext.() -> Unit) {
        val ctx = AttributeGenerationContext()
        ctx.generator()
        attributes.add(ctx)
    }

    fun Attribute(name: String, data: ByteArray) {
        val ctx = AttributeGenerationContext()
        ctx.name = name
        ctx.data = data
        attributes.add(ctx)
    }

    fun toField(
        pool: MutableConstantPool
    ): Field {
        val nameConstant = pool.resolveUtf8(name)
        val typeConstant = pool.resolveUtf8(type)
        return Field(
            pool,
            nameConstant,
            typeConstant,
            flags,
            attributes.map { it.toAttribute(pool) }
        )
    }

    fun toMutableField(
        pool: MutableConstantPool
    ): MutableField {
        val nameConstant = pool.resolveUtf8(name)
        val typeConstant = pool.resolveUtf8(type)
        return MutableField(
            pool,
            nameConstant,
            typeConstant,
            flags,
            attributes.map { it.toMutableAttribute(pool) }.toMutableList()
        )
    }
}