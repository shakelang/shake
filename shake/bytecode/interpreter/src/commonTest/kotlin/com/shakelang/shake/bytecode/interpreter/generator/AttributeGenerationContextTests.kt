package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class AttributeGenerationContextTests : FreeSpec({

    "test attribute generation" {

        val ctx = AttributeGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.data shouldBe byteArrayOf()

        ctx.name = "test"
        ctx.data = byteArrayOf(1, 2, 3, 4, 5)

        ctx.name shouldBe "test"
        ctx.data shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

    "test to attribute" {

        val ctx = AttributeGenerationContext()
        ctx.name = "test"
        ctx.data = byteArrayOf(1, 2, 3, 4, 5)

        val pool = MutableConstantPool()
        val attribute = ctx.toAttribute(pool)
        attribute.name shouldBe "test"
        attribute.value shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

    }

})

class FieldGenerationContextTests : FreeSpec({

    "test field generation" {

        val ctx = FieldGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.flags shouldBe 0
        ctx.isFinal shouldBe false
        ctx.isStatic shouldBe false
        ctx.isPublic shouldBe false
        ctx.isProtected shouldBe false
        ctx.isPrivate shouldBe false

    }

    "test name" {

        val ctx = FieldGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.name = "test"
        ctx.name shouldBe "test"

    }

    "test flags" {

        val ctx = FieldGenerationContext()
        ctx.flags shouldBe 0
        ctx.flags = 1
        ctx.flags shouldBe 1

    }

    "test isPublic" {

        val ctx = FieldGenerationContext()
        ctx.isPublic shouldBe false
        ctx.flags shouldBe 0
        ctx.isPublic = true
        ctx.isPublic shouldBe true
        ctx.flags shouldBe 0b0000000_00000001.toShort()

    }

    "test isPrivate" {

        val ctx = FieldGenerationContext()
        ctx.isPrivate shouldBe false
        ctx.flags shouldBe 0
        ctx.isPrivate = true
        ctx.isPrivate shouldBe true
        ctx.flags shouldBe 0b0000000_00000010.toShort()

    }


    "test isProtected" {

        val ctx = FieldGenerationContext()
        ctx.isProtected shouldBe false
        ctx.flags shouldBe 0
        ctx.isProtected = true
        ctx.isProtected shouldBe true
        ctx.flags shouldBe 0b0000000_00000100.toShort()

    }

    "test isStatic" {

        val ctx = FieldGenerationContext()
        ctx.isStatic shouldBe false
        ctx.flags shouldBe 0
        ctx.isStatic = true
        ctx.isStatic shouldBe true
        ctx.flags shouldBe 0b0000000_00001000.toShort()

    }

    "test isFinal" {

        val ctx = FieldGenerationContext()
        ctx.isFinal shouldBe false
        ctx.flags shouldBe 0
        ctx.isFinal = true
        ctx.isFinal shouldBe true
        ctx.flags shouldBe 0b0000000_00010000.toShort()

    }

    "test to field" {

        val ctx = FieldGenerationContext()
        ctx.name = "test"
        ctx.flags = 0b0000000_00010000.toShort()

        val pool = MutableConstantPool()
        val field = ctx.toField(pool)
        field.name shouldBe "test"
        field.flags shouldBe 0b0000000_00010000.toShort()

    }
})