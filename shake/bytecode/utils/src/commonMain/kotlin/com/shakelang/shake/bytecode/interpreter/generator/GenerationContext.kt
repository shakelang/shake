package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.*
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool

class GenerationContext {

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

    fun toStorageFormat(): StorageFormat {
        val pool = MutableConstantPool()
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
        val pool = MutableConstantPool()
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

    companion object {
        const val UNDEFINED = ":undefined:"
    }
}


fun generatePackage(generator: GenerationContext.() -> Unit): StorageFormat {
    val ctx = GenerationContext()
    ctx.generator()
    return ctx.toStorageFormat()
}
