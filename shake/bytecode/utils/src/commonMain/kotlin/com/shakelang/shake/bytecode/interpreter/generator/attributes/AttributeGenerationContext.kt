package com.shakelang.shake.bytecode.interpreter.generator.attributes

import com.shakelang.shake.bytecode.interpreter.format.attribute.AnonymousAttributeImpl
import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAnonymousAttributeImpl
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAttribute
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.GenerationContext

open class AttributeGenerationContext(
    val constantPool: MutableConstantPool
) {

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