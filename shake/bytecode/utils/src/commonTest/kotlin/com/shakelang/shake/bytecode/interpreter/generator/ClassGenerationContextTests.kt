package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ClassGenerationContextTests : FreeSpec(
    {

        "class generation" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.name shouldBe GenerationContext.UNDEFINED
            ctx.superName shouldBe GenerationContext.UNDEFINED
            ctx.flags shouldBe 0
            ctx.isFinal shouldBe false
            ctx.isStatic shouldBe false
            ctx.isPublic shouldBe false
            ctx.isProtected shouldBe false
            ctx.isPrivate shouldBe false
        }

        "name" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.name shouldBe GenerationContext.UNDEFINED
            ctx.name = "test"
            ctx.name shouldBe "test"
        }

        "superName" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.superName shouldBe GenerationContext.UNDEFINED
            ctx.superName = "test"
            ctx.superName shouldBe "test"
        }

        "flags" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.flags shouldBe 0
            ctx.flags = 1
            ctx.flags shouldBe 1
        }

        "isPublic" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.isPublic shouldBe false
            ctx.flags shouldBe 0
            ctx.isPublic = true
            ctx.isPublic shouldBe true
            ctx.flags shouldBe 0b0000000_00000001.toShort()
        }

        "isPrivate" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.isPrivate shouldBe false
            ctx.flags shouldBe 0
            ctx.isPrivate = true
            ctx.isPrivate shouldBe true
            ctx.flags shouldBe 0b0000000_00000010.toShort()
        }

        "isProtected" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.isProtected shouldBe false
            ctx.flags shouldBe 0
            ctx.isProtected = true
            ctx.isProtected shouldBe true
            ctx.flags shouldBe 0b0000000_00000100.toShort()
        }

        "isStatic" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.isStatic shouldBe false
            ctx.flags shouldBe 0
            ctx.isStatic = true
            ctx.isStatic shouldBe true
            ctx.flags shouldBe 0b0000000_00001000.toShort()
        }

        "isFinal" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.isFinal shouldBe false
            ctx.flags shouldBe 0
            ctx.isFinal = true
            ctx.isFinal shouldBe true
            ctx.flags shouldBe 0b0000000_00010000.toShort()
        }

        "attribute generation" {

            val ctx = ClassGenerationContext(MutableConstantPool())
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

        "field generation" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.Field {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }

            ctx.fields.size shouldBe 1
        }

        "method generation" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.Method {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }

            ctx.methods.size shouldBe 1
        }

        "subclass generation" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.Class {
                name = "test"
                superName = "super"
                flags = 0b0000000_00010000.toShort()
            }

            ctx.subClasses.size shouldBe 1
        }

        "extends" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.superName shouldBe GenerationContext.UNDEFINED
            ctx.extends("test")
            ctx.superName shouldBe "test"
        }

        "implements" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.interfaces.size shouldBe 0
            ctx.implements("test")
            ctx.interfaces.size shouldBe 1
            ctx.interfaces[0] shouldBe "test"
        }

        "to class" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.name = "test"
            ctx.superName = "super"
            ctx.flags = 0b0000000_00010000.toShort()
            ctx.attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }
            ctx.Field {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }
            ctx.Method {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }
            ctx.Class {
                name = "test"
                superName = "super"
                flags = 0b0000000_00010000.toShort()
            }

            val pool = MutableConstantPool()
            val clazz = ctx.toClass(pool)
            clazz.name shouldBe "test"
            clazz.superName shouldBe "super"
            clazz.flags shouldBe 0b0000000_00010000.toShort()
            clazz.attributes.size shouldBe 1
            clazz.fields.size shouldBe 1
            clazz.methods.size shouldBe 1
            clazz.subClasses.size shouldBe 1
        }

        "to mutable class" {

            val ctx = ClassGenerationContext(MutableConstantPool())
            ctx.name = "test"
            ctx.superName = "super"
            ctx.flags = 0b0000000_00010000.toShort()
            ctx.attribute {
                name = "test"
                data = byteArrayOf(1, 2, 3, 4, 5)
            }
            ctx.Field {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }
            ctx.Method {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }
            ctx.Class {
                name = "test"
                superName = "super"
                flags = 0b0000000_00010000.toShort()
            }

            val pool = MutableConstantPool()
            val clazz = ctx.toMutableClass(pool)
            clazz.name shouldBe "test"
            clazz.superName shouldBe "super"
            clazz.flags shouldBe 0b0000000_00010000.toShort()
            clazz.attributes.size shouldBe 1
            clazz.fields.size shouldBe 1
            clazz.methods.size shouldBe 1
            clazz.subClasses.size shouldBe 1
        }
    },
)
