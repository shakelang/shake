package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class FieldGenerationContextTests : FreeSpec({

    "field generation" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.flags shouldBe 0
        ctx.isFinal shouldBe false
        ctx.isStatic shouldBe false
        ctx.isPublic shouldBe false
        ctx.isProtected shouldBe false
        ctx.isPrivate shouldBe false
    }

    "name" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.name = "test"
        ctx.name shouldBe "test"
    }

    "flags" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.flags shouldBe 0
        ctx.flags = 1
        ctx.flags shouldBe 1
    }

    "isPublic" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.isPublic shouldBe false
        ctx.flags shouldBe 0
        ctx.isPublic = true
        ctx.isPublic shouldBe true
        ctx.flags shouldBe 0b0000000_00000001.toShort()
    }

    "isPrivate" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.isPrivate shouldBe false
        ctx.flags shouldBe 0
        ctx.isPrivate = true
        ctx.isPrivate shouldBe true
        ctx.flags shouldBe 0b0000000_00000010.toShort()
    }

    "isProtected" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.isProtected shouldBe false
        ctx.flags shouldBe 0
        ctx.isProtected = true
        ctx.isProtected shouldBe true
        ctx.flags shouldBe 0b0000000_00000100.toShort()
    }

    "isStatic" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.isStatic shouldBe false
        ctx.flags shouldBe 0
        ctx.isStatic = true
        ctx.isStatic shouldBe true
        ctx.flags shouldBe 0b0000000_00001000.toShort()
    }

    "isFinal" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.isFinal shouldBe false
        ctx.flags shouldBe 0
        ctx.isFinal = true
        ctx.isFinal shouldBe true
        ctx.flags shouldBe 0b0000000_00010000.toShort()
    }

    "attribute generation" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.name shouldBe GenerationContext.UNDEFINED

        ctx.attribute {
            name = "test"
            data = byteArrayOf(1, 2, 3, 4, 5)
        }

        ctx.attributes.size shouldBe 1
        ctx.attributes[0].name shouldBe "test"
        ctx.attributes[0].data shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

        ctx.attributes.clear()

        ctx.attribute("test2", byteArrayOf(1, 2, 3, 4, 5))

        ctx.attributes.size shouldBe 1
        ctx.attributes[0].name shouldBe "test2"
        ctx.attributes[0].data shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

        ctx.attributes.clear()

        ctx.Attribute {
            name = "test2"
            data = byteArrayOf(1, 2, 3, 4, 5)
        }

        ctx.attributes.size shouldBe 1
        ctx.attributes[0].name shouldBe "test2"
        ctx.attributes[0].data shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)

        ctx.attributes.clear()

        ctx.Attribute("test3", byteArrayOf(1, 2, 3, 4, 5))

        ctx.attributes.size shouldBe 1
        ctx.attributes[0].name shouldBe "test3"
        ctx.attributes[0].data shouldContainExactly byteArrayOf(1, 2, 3, 4, 5)
    }

    "to field" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.name = "test"
        ctx.flags = 0b0000000_00010000.toShort()
        ctx.attribute {
            name = "test"
            data = byteArrayOf(1, 2, 3, 4, 5)
        }

        val pool = MutableConstantPool()
        val field = ctx.toField(pool)
        field.name shouldBe "test"
        field.flags shouldBe 0b0000000_00010000.toShort()
        field.attributes.size shouldBe 1
    }

    "to mutable field" {

        val ctx = FieldGenerationContext(MutableConstantPool())
        ctx.name = "test"
        ctx.flags = 0b0000000_00010000.toShort()
        ctx.attribute {
            name = "test"
            data = byteArrayOf(1, 2, 3, 4, 5)
        }

        val pool = MutableConstantPool()
        val field = ctx.toMutableField(pool)
        field.name shouldBe "test"
        field.flags shouldBe 0b0000000_00010000.toShort()
        field.attributes.size shouldBe 1
    }
})
