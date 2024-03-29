package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.MutableStorageFormat
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool

@Suppress("MemberVisibilityCanBePrivate", "unused")
class GenerationContext {

    val constantPool = MutableConstantPool()
    val pathBase get() = "$name/"
    val path get() = name

    var name: String = UNDEFINED
        set(value) {
            if (field != UNDEFINED) throw Error("Name already specified")
            field = value
        }

    val classes: MutableList<ClassGenerationContext> = mutableListOf()
    val methods: MutableList<MethodGenerationContext> = mutableListOf()
    val fields: MutableList<FieldGenerationContext> = mutableListOf()

    @Suppress("ktlint:standard:function-naming", "FunctionName")
    fun Class(generator: ClassGenerationContext.() -> Unit) {
        val ctx = ClassGenerationContext(
            pathBase,
            constantPool,
        )
        ctx.generator()
        classes.add(ctx)
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
    fun Field(generator: FieldGenerationContext.() -> Unit) {
        val ctx = FieldGenerationContext(
            pathBase,
            constantPool,
        )
        ctx.generator()
        fields.add(ctx)
    }

    fun utf8Ref(value: String): Int {
        return constantPool.resolveUtf8(value)
    }

    fun byteRef(value: Byte): Int {
        return constantPool.resolveByte(value)
    }

    fun shortRef(value: Short): Int {
        return constantPool.resolveShort(value)
    }

    fun intRef(value: Int): Int {
        return constantPool.resolveInt(value)
    }

    fun longRef(value: Long): Int {
        return constantPool.resolveLong(value)
    }

    fun floatRef(value: Float): Int {
        return constantPool.resolveFloat(value)
    }

    fun doubleRef(value: Double): Int {
        return constantPool.resolveDouble(value)
    }

    fun classRef(value: String): Int {
        return constantPool.resolveClass(value)
    }

    fun toStorageFormat(): StorageFormat {
        return StorageFormat(
            0x00,
            0x01,
            constantPool.resolveUtf8(name),
            constantPool,
            classes.map { it.toClass(constantPool) },
            methods.map { it.toMethod(constantPool) },
            fields.map { it.toField(constantPool) },
        )
    }

    fun toMutableStorageFormat(): MutableStorageFormat {
        return MutableStorageFormat(
            0x00,
            0x01,
            constantPool.resolveUtf8(name),
            constantPool,
            classes.map { it.toMutableClass(constantPool) }.toMutableList(),
            methods.map { it.toMutableMethod(constantPool) }.toMutableList(),
            fields.map { it.toMutableField(constantPool) }.toMutableList(),
        )
    }

    companion object {
        const val UNDEFINED = ":undefined:"
    }
}

fun generatePackage(generator: GenerationContext.() -> Unit): StorageFormat {
    val ctx = GenerationContext()
    ctx.generator()
    return ctx.toStorageFormat()
}
