package com.shakelang.shake.bytecode.interpreter.generator

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
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

class FieldGenerationContextTests : FreeSpec({

    "field generation" {

        val ctx = FieldGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.flags shouldBe 0
        ctx.isFinal shouldBe false
        ctx.isStatic shouldBe false
        ctx.isPublic shouldBe false
        ctx.isProtected shouldBe false
        ctx.isPrivate shouldBe false

    }

    "name" {

        val ctx = FieldGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.name = "test"
        ctx.name shouldBe "test"

    }

    "flags" {

        val ctx = FieldGenerationContext()
        ctx.flags shouldBe 0
        ctx.flags = 1
        ctx.flags shouldBe 1

    }

    "isPublic" {

        val ctx = FieldGenerationContext()
        ctx.isPublic shouldBe false
        ctx.flags shouldBe 0
        ctx.isPublic = true
        ctx.isPublic shouldBe true
        ctx.flags shouldBe 0b0000000_00000001.toShort()

    }

    "isPrivate" {

        val ctx = FieldGenerationContext()
        ctx.isPrivate shouldBe false
        ctx.flags shouldBe 0
        ctx.isPrivate = true
        ctx.isPrivate shouldBe true
        ctx.flags shouldBe 0b0000000_00000010.toShort()

    }


    "isProtected" {

        val ctx = FieldGenerationContext()
        ctx.isProtected shouldBe false
        ctx.flags shouldBe 0
        ctx.isProtected = true
        ctx.isProtected shouldBe true
        ctx.flags shouldBe 0b0000000_00000100.toShort()

    }

    "isStatic" {

        val ctx = FieldGenerationContext()
        ctx.isStatic shouldBe false
        ctx.flags shouldBe 0
        ctx.isStatic = true
        ctx.isStatic shouldBe true
        ctx.flags shouldBe 0b0000000_00001000.toShort()

    }

    "isFinal" {

        val ctx = FieldGenerationContext()
        ctx.isFinal shouldBe false
        ctx.flags shouldBe 0
        ctx.isFinal = true
        ctx.isFinal shouldBe true
        ctx.flags shouldBe 0b0000000_00010000.toShort()

    }

    "attribute generation" {

        val ctx = FieldGenerationContext()
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

        val ctx = FieldGenerationContext()
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

        val ctx = FieldGenerationContext()
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

class MethodGenerationContextTests : FreeSpec({

    "method generation" {

        val ctx = MethodGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.flags shouldBe 0
        ctx.isFinal shouldBe false
        ctx.isStatic shouldBe false
        ctx.isPublic shouldBe false
        ctx.isProtected shouldBe false
        ctx.isPrivate shouldBe false

    }

    "name" {

        val ctx = MethodGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.name = "test"
        ctx.name shouldBe "test"

    }

    "flags" {

        val ctx = MethodGenerationContext()
        ctx.flags shouldBe 0
        ctx.flags = 1
        ctx.flags shouldBe 1

    }

    "isPublic" {

        val ctx = MethodGenerationContext()
        ctx.isPublic shouldBe false
        ctx.flags shouldBe 0
        ctx.isPublic = true
        ctx.isPublic shouldBe true
        ctx.flags shouldBe 0b0000000_00000001.toShort()

    }

    "isPrivate" {

        val ctx = MethodGenerationContext()
        ctx.isPrivate shouldBe false
        ctx.flags shouldBe 0
        ctx.isPrivate = true
        ctx.isPrivate shouldBe true
        ctx.flags shouldBe 0b0000000_00000010.toShort()

    }

    "isProtected" {

        val ctx = MethodGenerationContext()
        ctx.isProtected shouldBe false
        ctx.flags shouldBe 0
        ctx.isProtected = true
        ctx.isProtected shouldBe true
        ctx.flags shouldBe 0b0000000_00000100.toShort()

    }

    "isStatic" {

        val ctx = MethodGenerationContext()
        ctx.isStatic shouldBe false
        ctx.flags shouldBe 0
        ctx.isStatic = true
        ctx.isStatic shouldBe true
        ctx.flags shouldBe 0b0000000_00001000.toShort()

    }

    "isFinal" {

        val ctx = MethodGenerationContext()
        ctx.isFinal shouldBe false
        ctx.flags shouldBe 0
        ctx.isFinal = true
        ctx.isFinal shouldBe true
        ctx.flags shouldBe 0b0000000_00010000.toShort()

    }

    "attribute generation" {

        val ctx = MethodGenerationContext()
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

    "to method" {

        val ctx = MethodGenerationContext()
        ctx.name = "test"
        ctx.flags = 0b0000000_00010000.toShort()
        ctx.attribute {
            name = "test"
            data = byteArrayOf(1, 2, 3, 4, 5)
        }

        val pool = MutableConstantPool()
        val method = ctx.toMethod(pool)
        method.name shouldBe "test"
        method.flags shouldBe 0b0000000_00010000.toShort()
        method.attributes.size shouldBe 1

    }

    "to mutable method" {

        val ctx = MethodGenerationContext()
        ctx.name = "test"
        ctx.flags = 0b0000000_00010000.toShort()
        ctx.attribute {
            name = "test"
            data = byteArrayOf(1, 2, 3, 4, 5)
        }

        val pool = MutableConstantPool()
        val method = ctx.toMutableMethod(pool)
        method.name shouldBe "test"
        method.flags shouldBe 0b0000000_00010000.toShort()
        method.attributes.size shouldBe 1

    }

})

class ClassGenerationContextTests : FreeSpec({

    "class generation" {

        val ctx = ClassGenerationContext()
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

        val ctx = ClassGenerationContext()
        ctx.name shouldBe GenerationContext.UNDEFINED
        ctx.name = "test"
        ctx.name shouldBe "test"

    }

    "superName" {

        val ctx = ClassGenerationContext()
        ctx.superName shouldBe GenerationContext.UNDEFINED
        ctx.superName = "test"
        ctx.superName shouldBe "test"

    }

    "flags" {

        val ctx = ClassGenerationContext()
        ctx.flags shouldBe 0
        ctx.flags = 1
        ctx.flags shouldBe 1

    }

    "isPublic" {

        val ctx = ClassGenerationContext()
        ctx.isPublic shouldBe false
        ctx.flags shouldBe 0
        ctx.isPublic = true
        ctx.isPublic shouldBe true
        ctx.flags shouldBe 0b0000000_00000001.toShort()

    }

    "isPrivate" {

        val ctx = ClassGenerationContext()
        ctx.isPrivate shouldBe false
        ctx.flags shouldBe 0
        ctx.isPrivate = true
        ctx.isPrivate shouldBe true
        ctx.flags shouldBe 0b0000000_00000010.toShort()

    }

    "isProtected" {

        val ctx = ClassGenerationContext()
        ctx.isProtected shouldBe false
        ctx.flags shouldBe 0
        ctx.isProtected = true
        ctx.isProtected shouldBe true
        ctx.flags shouldBe 0b0000000_00000100.toShort()

    }

    "isStatic" {

        val ctx = ClassGenerationContext()
        ctx.isStatic shouldBe false
        ctx.flags shouldBe 0
        ctx.isStatic = true
        ctx.isStatic shouldBe true
        ctx.flags shouldBe 0b0000000_00001000.toShort()

    }

    "isFinal" {

        val ctx = ClassGenerationContext()
        ctx.isFinal shouldBe false
        ctx.flags shouldBe 0
        ctx.isFinal = true
        ctx.isFinal shouldBe true
        ctx.flags shouldBe 0b0000000_00010000.toShort()

    }

    "attribute generation" {

        val ctx = ClassGenerationContext()
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

        val ctx = ClassGenerationContext()
        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.fields.size shouldBe 1

    }

    "method generation" {

        val ctx = ClassGenerationContext()
        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.methods.size shouldBe 1

    }

    "subclass generation" {

        val ctx = ClassGenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.subClasses.size shouldBe 1

    }

    "extends" {

        val ctx = ClassGenerationContext()
        ctx.superName shouldBe GenerationContext.UNDEFINED
        ctx.extends("test")
        ctx.superName shouldBe "test"

    }

    "implements" {

        val ctx = ClassGenerationContext()
        ctx.interfaces.size shouldBe 0
        ctx.implements("test")
        ctx.interfaces.size shouldBe 1
        ctx.interfaces[0] shouldBe "test"

    }

    "to class" {

        val ctx = ClassGenerationContext()
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

        val ctx = ClassGenerationContext()
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

})

class GenerationContextTests : FreeSpec({

    "generation context" {

        val ctx = GenerationContext()
        ctx.classes.size shouldBe 0
        ctx.methods.size shouldBe 0
        ctx.fields.size shouldBe 0

    }

    "field generation" {

        val ctx = GenerationContext()
        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.fields.size shouldBe 1

    }

    "method generation" {

        val ctx = GenerationContext()
        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.methods.size shouldBe 1

    }

    "class generation" {

        val ctx = GenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.classes.size shouldBe 1

    }

    "toStorageFormat" {

        val ctx = GenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        val format = ctx.toStorageFormat()
        format.classes.size shouldBe 1
        format.methods.size shouldBe 1
        format.fields.size shouldBe 1

    }

    "toMutableStorageFormat" {

        val ctx = GenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        val format = ctx.toMutableStorageFormat()
        format.classes.size shouldBe 1
        format.methods.size shouldBe 1
        format.fields.size shouldBe 1

    }

    "generate shortcut" {
        val format = generate {
            Class {
                name = "test"
                superName = "super"
                flags = 0b0000000_00010000.toShort()
            }

            Method {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }

            Field {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }
        }

        format.classes.size shouldBe 1
        format.methods.size shouldBe 1
        format.fields.size shouldBe 1
    }

})