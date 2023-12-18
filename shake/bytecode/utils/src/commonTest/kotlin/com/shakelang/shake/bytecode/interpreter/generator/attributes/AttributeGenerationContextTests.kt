package com.shakelang.shake.bytecode.interpreter.generator.attributes

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.bytecode.interpreter.generator.GenerationContext
import com.shakelang.shake.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class AttributeGenerationContextTests : FreeSpec({

    "attribute generation" {

        val ctx = AttributeGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.data shouldBe byteArrayOf()

        ctx.name = "test"
        ctx.data = byteArrayOf(1, 2, 3, 4, 5)

        ctx.name shouldBe "test"
        ctx.data shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)
    }

    "to attribute" {

        val ctx = AttributeGenerationContext()
        ctx.name = "test"
        ctx.data = byteArrayOf(1, 2, 3, 4, 5)

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)
        attribute.name shouldBe "test"
        attribute.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)
    }

    "to mutable attribute" {

        val ctx = AttributeGenerationContext()
        ctx.name = "test"
        ctx.data = byteArrayOf(1, 2, 3, 4, 5)

        val pool = MutableConstantPool()
        val attribute = ctx.toMutableAttribute(pool)
        attribute.name shouldBe "test"
        attribute.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)
    }
})