package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.MutableClass
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.attributes.AttributeGenerationContext
import kotlin.experimental.and
import kotlin.experimental.or

class ClassGenerationContext(
    val constantPool: MutableConstantPool
) {

    var name: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("Name already specified")
            field = value
        }

    var superName: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("SuperName already specified")
            field = value
        }

    var interfaces: MutableList<String> = mutableListOf()
        set(value) {
            if (field.isNotEmpty()) throw Error("Interfaces already specified")
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

    val fields: MutableList<FieldGenerationContext> = mutableListOf()

    val methods: MutableList<MethodGenerationContext> = mutableListOf()

    val subClasses: MutableList<ClassGenerationContext> = mutableListOf()

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

    fun Attribute(generator: AttributeGenerationContext.() -> Unit) {
        val ctx = AttributeGenerationContext(constantPool)
        ctx.generator()
        attributes.add(ctx)
    }

    fun Attribute(name: String, data: ByteArray) {
        val ctx = AttributeGenerationContext(constantPool)
        ctx.name = name
        ctx.data = data
        attributes.add(ctx)
    }

    fun extends(it: String) {
        superName = it
    }

    fun implements(it: String) {
        interfaces.add(it)
    }

    fun Field(generator: FieldGenerationContext.() -> Unit) {
        val ctx = FieldGenerationContext(constantPool)
        ctx.generator()
        fields.add(ctx)
    }

    fun Method(generator: MethodGenerationContext.() -> Unit) {
        val ctx = MethodGenerationContext(constantPool)
        ctx.generator()
        methods.add(ctx)
    }

    fun Class(generator: ClassGenerationContext.() -> Unit) {
        val ctx = ClassGenerationContext(constantPool)
        ctx.generator()
        subClasses.add(ctx)
    }

    fun toClass(
        pool: MutableConstantPool
    ): Class {
        val nameConstant = pool.resolveUtf8(name)
        val superConstant = pool.resolveUtf8(superName)
        return Class(
            pool,
            nameConstant,
            superConstant,
            flags,
            listOf(),
            subClasses.map { it.toClass(pool) },
            methods.map { it.toMethod(pool) },
            fields.map { it.toField(pool) },
            attributes.map { it.toAttribute(pool) }
        )
    }

    fun toMutableClass(
        pool: MutableConstantPool
    ): MutableClass {
        val nameConstant = pool.resolveUtf8(name)
        val superConstant = pool.resolveUtf8(superName)
        return MutableClass(
            pool,
            nameConstant,
            superConstant,
            flags,
            mutableListOf(),
            fields.map { it.toMutableField(pool) }.toMutableList(),
            methods.map { it.toMutableMethod(pool) }.toMutableList(),
            subClasses.map { it.toMutableClass(pool) }.toMutableList(),
            attributes.map { it.toMutableAttribute(pool) }.toMutableList()
        )
    }
}