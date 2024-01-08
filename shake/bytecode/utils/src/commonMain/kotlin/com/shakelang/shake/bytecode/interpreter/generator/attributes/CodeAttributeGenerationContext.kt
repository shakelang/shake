package com.shakelang.shake.bytecode.interpreter.generator.attributes

import com.shakelang.shake.bytecode.interpreter.format.attribute.CodeAttribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableCodeAttribute
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.PooledShakeBytecodeInstructionGenerator

class CodeAttributeGenerationContext(constantPool: MutableConstantPool) : AttributeGenerationContext(constantPool) {

    var maxStack = 0
    var maxLocals = 0
    var code: ByteArray = byteArrayOf()
    val exceptionTable = mutableListOf<CodeAttribute.ExceptionTableEntry>()
    val attributes = mutableListOf<AttributeGenerationContext>()

    /**
     * @deprecated Modify the data of the attribute directly
     */
    override var data: ByteArray
        /**
         * @deprecated Modify the data of the attribute directly
         */
        get() = toAttribute(MutableConstantPool()).value

        /**
         * @deprecated Modify the data of the attribute directly
         */
        set(value) {
            TODO()
        }

    fun bytecode(init: PooledShakeBytecodeInstructionGenerator.() -> Unit) {
        this.code = com.shakelang.shake.bytecode.interpreter.generator.bytecode(this.constantPool, init)
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
        val context = AttributeGenerationContext(constantPool)
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
            attributes.map { it.toAttribute(pool) }.toTypedArray(),
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
            attributes.map { it.toMutableAttribute(pool) }.toTypedArray(),
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
