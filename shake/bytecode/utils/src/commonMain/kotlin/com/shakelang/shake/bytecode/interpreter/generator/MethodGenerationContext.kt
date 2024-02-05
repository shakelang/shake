package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.MutableMethod
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.attributes.AttributeGenerationContext
import com.shakelang.shake.bytecode.interpreter.generator.attributes.CodeAttributeGenerationContext

@Suppress("MemberVisibilityCanBePrivate", "unused")
class MethodGenerationContext(
    val parentPathBase: String,
    val constantPool: MutableConstantPool,
) {
    val path get() = "$parentPathBase$name"

    var name: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("Name already specified")
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

    var isNative: Boolean
        get() = Flags.isNative(flags)
        set(value) {
            flags = Flags.setNative(flags, value)
        }

    var isAbstract: Boolean
        get() = Flags.isAbstract(flags)
        set(value) {
            flags = Flags.setAbstract(flags, value)
        }

    var isSynchronized: Boolean
        get() = Flags.isSynchronized(flags)
        set(value) {
            flags = Flags.setSynchronized(flags, value)
        }

    var isStrict: Boolean
        get() = Flags.isStrict(flags)
        set(value) {
            flags = Flags.setStrict(flags, value)
        }

    var isConstructor: Boolean
        get() = Flags.isConstructor(flags)
        set(value) {
            flags = Flags.setConstructor(flags, value)
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

    fun code(
        generator: CodeAttributeGenerationContext.() -> Unit,
    ) {
        val ctx = CodeAttributeGenerationContext(constantPool)
        ctx.generator()
        attributes.add(ctx)
    }

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Code(
        generator: CodeAttributeGenerationContext.() -> Unit,
    ) {
        val ctx = CodeAttributeGenerationContext(constantPool)
        ctx.generator()
        attributes.add(ctx)
    }

    fun toMethod(
        pool: MutableConstantPool,
    ): Method {
        val nameConstant = pool.resolveUtf8(name)
        return Method(
            pool,
            nameConstant,
            flags,
            attributes.map { it.toAttribute(pool) },
        )
    }

    fun toMutableMethod(
        pool: MutableConstantPool,
    ): MutableMethod {
        val nameConstant = pool.resolveUtf8(name)
        return MutableMethod(
            pool,
            nameConstant,
            flags,
            attributes.map { it.toMutableAttribute(pool) }.toMutableList(),
        )
    }

    companion object {
        val Flags = Method.Companion.Flags
    }
}
