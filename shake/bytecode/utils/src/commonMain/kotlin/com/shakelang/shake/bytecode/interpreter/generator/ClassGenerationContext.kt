package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.MutableClass
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.attributes.AttributeGenerationContext

@Suppress("MemberVisibilityCanBePrivate", "unused")
class ClassGenerationContext(
    val parentPathBase: String,
    val constantPool: MutableConstantPool,
) {

    val pathBase get() = "$parentPathBase$name:"
    val path get() = "$parentPathBase$name"

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

    var isInterface: Boolean
        get() = Flags.isInterface(flags)
        set(value) {
            flags = Flags.setInterface(flags, value)
        }

    var isAbstract: Boolean
        get() = Flags.isAbstract(flags)
        set(value) {
            flags = Flags.setAbstract(flags, value)
        }

    var isSynthetic: Boolean
        get() = Flags.isSynthetic(flags)
        set(value) {
            flags = Flags.setSynthetic(flags, value)
        }

    var isAnnotation: Boolean
        get() = Flags.isAnnotation(flags)
        set(value) {
            flags = Flags.setAnnotation(flags, value)
        }

    var isEnum: Boolean
        get() = Flags.isEnum(flags)
        set(value) {
            flags = Flags.setEnum(flags, value)
        }

    var isObject: Boolean
        get() = Flags.isObject(flags)
        set(value) {
            flags = Flags.setObject(flags, value)
        }

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

    fun extends(it: String) {
        superName = it
    }

    fun implements(it: String) {
        interfaces.add(it)
    }

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Field(generator: FieldGenerationContext.() -> Unit) {
        val ctx = FieldGenerationContext(
            pathBase,
            constantPool,
        )
        ctx.generator()
        fields.add(ctx)
    }

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Method(generator: MethodGenerationContext.() -> Unit) {
        val ctx = MethodGenerationContext(
            pathBase,
            constantPool,
        )
        ctx.generator()
        methods.add(ctx)
    }

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Class(generator: ClassGenerationContext.() -> Unit) {
        val ctx = ClassGenerationContext(
            pathBase,
            constantPool,
        )
        ctx.generator()
        subClasses.add(ctx)
    }

    fun toClass(
        pool: MutableConstantPool,
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
            attributes.map { it.toAttribute(pool) },
        )
    }

    fun toMutableClass(
        pool: MutableConstantPool,
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
            attributes.map { it.toMutableAttribute(pool) }.toMutableList(),
        )
    }

    companion object {
        val Flags = Class.Companion.Flags
    }
}
