package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.*
import com.shakelang.shake.bytecode.interpreter.format.attribute.*
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.dataStream
import kotlin.experimental.and
import kotlin.experimental.or

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

class MethodGenerationContext {

    var name: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("Name already specified")
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

    fun toMethod(
        pool: MutableConstantPool
    ): Method {
        val nameConstant = pool.resolveUtf8(name)
        return Method(
            pool,
            nameConstant,
            nameConstant,
            flags,
            attributes.map { it.toAttribute(pool) }
        )
    }

    fun toMutableMethod(
        pool: MutableConstantPool
    ): MutableMethod {
        val nameConstant = pool.resolveUtf8(name)
        return MutableMethod(
            pool,
            nameConstant,
            nameConstant,
            flags,
            attributes.map { it.toMutableAttribute(pool) }.toMutableList()
        )
    }
}

class ClassGenerationContext {

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

    fun extends(it: String) {
        superName = it
    }

    fun implements(it: String) {
        interfaces.add(it)
    }

    fun Field(generator: FieldGenerationContext.() -> Unit) {
        val ctx = FieldGenerationContext()
        ctx.generator()
        fields.add(ctx)
    }

    fun Method(generator: MethodGenerationContext.() -> Unit) {
        val ctx = MethodGenerationContext()
        ctx.generator()
        methods.add(ctx)
    }

    fun Class(generator: ClassGenerationContext.() -> Unit) {
        val ctx = ClassGenerationContext()
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

open class AttributeGenerationContext {

    var name: String = GenerationContext.UNDEFINED
        set(value) {
            if (field != GenerationContext.UNDEFINED) throw Error("Name already specified")
            field = value
        }

    open var data: ByteArray = byteArrayOf()

    open fun toAttribute(
        pool: MutableConstantPool
    ): Attribute {
        val nameConstant = pool.resolveUtf8(name)
        return AnonymousAttributeImpl(
            pool,
            nameConstant,
            data
        )
    }

    open fun toMutableAttribute(
        pool: MutableConstantPool
    ): MutableAttribute {
        val nameConstant = pool.resolveUtf8(name)
        return MutableAnonymousAttributeImpl(
            pool,
            nameConstant,
            data
        )
    }
}




class CodeAttributeGenerationContext : AttributeGenerationContext() {

    val generator = ShakeBytecodeGenerator()
    var maxStack = 0
    var maxLocals = 0
    var code: ByteArray = byteArrayOf()
    val exceptionTable = mutableListOf<CodeAttribute.ExceptionTableEntry>()
    val attributes = mutableListOf<AttributeGenerationContext>()

    /**
     * @deprecated Modify the data of the attribute directly
     */
    override var data: ByteArray
        get() = toAttribute(MutableConstantPool()).value
        set(value) {
            TODO()
        }

    fun bytecode(init: ShakeBytecodeGenerator.() -> Unit) {
        val generator = ShakeBytecodeGenerator()
        generator.init()
        this.code = generator.toByteArray()
    }

    fun exceptionTableEntry(init: ExceptionTableEntryGenerationContext.() -> Unit) {
        val context = ExceptionTableEntryGenerationContext()
        context.init()
        exceptionTable.add(context.toEntry())
    }

    fun exceptionTableEntry(startPc: Int, endPc: Int, handlerPc: Int, catchType: Int) {
        exceptionTable.add(CodeAttribute.ExceptionTableEntry(startPc, endPc, handlerPc, catchType))
    }

    fun attribute(init: AttributeGenerationContext.() -> Unit) {
        val context = AttributeGenerationContext()
        context.init()
        attributes.add(context)
    }

    override fun toAttribute(pool: MutableConstantPool): CodeAttribute {
        return CodeAttribute(
            pool,
            pool.resolveUtf8("Code"),
            maxStack,
            maxLocals,
            code,
            exceptionTable.toTypedArray(),
            arrayOf()
        )
    }

    override fun toMutableAttribute(pool: MutableConstantPool): MutableCodeAttribute {
        return MutableCodeAttribute(
            pool,
            pool.resolveUtf8("Code"),
            maxStack,
            maxLocals,
            code,
            exceptionTable.toTypedArray(),
            arrayOf()
        )
    }
    class ExceptionTableEntryGenerationContext {

        var startPc: Int = 0
        var endPc: Int = 0
        var handlerPc: Int = 0
        var catchType: Int = 0

        fun toEntry() = CodeAttribute.ExceptionTableEntry(startPc, endPc, handlerPc, catchType)

    }

}

fun generate(generator: GenerationContext.() -> Unit): StorageFormat {
    val ctx = GenerationContext()
    ctx.generator()
    return ctx.toStorageFormat()
}
