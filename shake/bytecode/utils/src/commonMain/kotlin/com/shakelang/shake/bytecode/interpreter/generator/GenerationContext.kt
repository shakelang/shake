package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.*
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool

class GenerationContext {

    val pool = MutableConstantPool()

    var name: String = UNDEFINED
        set(value) {
            if (field != UNDEFINED) throw Error("Name already specified")
            field = value
        }

    val classes: MutableList<ClassGenerationContext> = mutableListOf()
    val methods: MutableList<MethodGenerationContext> = mutableListOf()
    val fields: MutableList<FieldGenerationContext> = mutableListOf()

    fun Class(generator: ClassGenerationContext.() -> Unit) {
        val ctx = ClassGenerationContext()
        ctx.generator()
        classes.add(ctx)
    }

    fun Method(generator: MethodGenerationContext.() -> Unit) {
        val ctx = MethodGenerationContext()
        ctx.generator()
        methods.add(ctx)
    }

    fun Field(generator: FieldGenerationContext.() -> Unit) {
        val ctx = FieldGenerationContext()
        ctx.generator()
        fields.add(ctx)
    }

    fun utf8Ref(value: String): Int {
        return pool.resolveUtf8(value)
    }

    fun byteRef(value: Byte): Int {
        return pool.resolveByte(value)
    }

    fun shortRef(value: Short): Int {
        return pool.resolveShort(value)
    }

    fun intRef(value: Int): Int {
        return pool.resolveInt(value)
    }

    fun longRef(value: Long): Int {
        return pool.resolveLong(value)
    }

    fun floatRef(value: Float): Int {
        return pool.resolveFloat(value)
    }

    fun doubleRef(value: Double): Int {
        return pool.resolveDouble(value)
    }

    fun classRef(value: String): Int {
        return pool.resolveClass(value)
    }



    fun toStorageFormat(): StorageFormat {
        return StorageFormat(
            0x00,
            0x01,
            pool.resolveUtf8(name),
            pool,
            classes.map { it.toClass(pool) },
            methods.map { it.toMethod(pool) },
            fields.map { it.toField(pool) }
        )
    }

    fun toMutableStorageFormat(): MutableStorageFormat {
        return MutableStorageFormat(
            0x00,
            0x01,
            pool.resolveUtf8(name),
            pool,
            classes.map { it.toMutableClass(pool) }.toMutableList(),
            methods.map { it.toMutableMethod(pool) }.toMutableList(),
            fields.map { it.toMutableField(pool) }.toMutableList()
        )
    }

    fun ShakeBytecodeGenerator.call(method: String) {
        this.call(utf8Ref(method))
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
