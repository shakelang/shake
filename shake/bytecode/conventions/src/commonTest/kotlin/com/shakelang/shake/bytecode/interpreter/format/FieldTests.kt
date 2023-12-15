package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.Attribute
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class FieldTests : FreeSpec({

    "name constant" {
        val pool = MutableConstantPool()
        val field = Field(pool, 0, 0, 0, listOf())
        field.nameConstant shouldBe 0
    }

    "name getter" {
        val pool = MutableConstantPool()
        val constant = pool.resolveUtf8("test")
        val field = Field(pool, constant, 0, 0, listOf())
        field.name shouldBe "test"
    }

    "flags" {
        val pool = MutableConstantPool()
        var field = Field(pool, 0, 0, 0b00000000_00000001.toShort(), listOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00000010.toShort(), listOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe true
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00000100.toShort(), listOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe true
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00001000.toShort(), listOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe true
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00010000.toShort(), listOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe true

        field = Field(pool, 0, 0, 0b00000000_00000000.toShort(), listOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00000011.toShort(), listOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00000111.toShort(), listOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00001111.toShort(), listOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe true
        field.isFinal shouldBe false

        field = Field(pool, 0, 0, 0b00000000_00011111.toShort(), listOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe true
        field.isFinal shouldBe true

        field = Field(pool, 0, 0, 0b00000000_00011110.toShort(), listOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe true
        field.isFinal shouldBe true
    }

    "dump" {
        val pool = MutableConstantPool()
        val field = Field(pool, 0, 0, 0b00000000_00000001.toShort(), listOf())
        val stream = ByteArrayOutputStream()
        field.dump(DataOutputStream(stream))
        val arr = stream.toByteArray()
        arr contentEquals byteArrayOf(
            *0.toBytes(),
            *0.toBytes(),
            *1.toShort().toBytes(),
            *0.toShort().toBytes()
        ) shouldBe true
    }

    "dump to array" {
        val pool = MutableConstantPool()
        val field = Field(pool, 0, 0, 0b00000000_00000001.toShort(), listOf())
        val arr = field.dump()
        arr contentEquals byteArrayOf(
            *0.toBytes(),
            *0.toBytes(),
            *1.toShort().toBytes(),
            *0.toShort().toBytes()
        ) shouldBe true
    }

    "from stream" {
        val pool = MutableConstantPool()
        val stream = byteArrayOf(
            *pool.resolveUtf8("test").toBytes(),
            *0.toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes()
        ).dataStream()

        val field = Field.fromStream(pool, stream)
        field.name shouldBe "test"
        field.flags shouldBe 1.toShort()
    }
})

class MutableFieldTests : FreeSpec({

    "name constant" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.nameConstant shouldBe 0
    }

    "name getter" {
        val pool = MutableConstantPool()
        val constant = pool.resolveUtf8("test")
        val field = MutableField(pool, constant, 0, 0, mutableListOf())
        field.name shouldBe "test"
    }

    "flags" {
        val pool = MutableConstantPool()
        var field = MutableField(pool, 0, 0, 0b00000000_00000001.toShort(), mutableListOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00000010.toShort(), mutableListOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe true
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00000100.toShort(), mutableListOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe true
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00001000.toShort(), mutableListOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe true
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00010000.toShort(), mutableListOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe true

        field = MutableField(pool, 0, 0, 0b00000000_00000000.toShort(), mutableListOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe false
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00000011.toShort(), mutableListOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe false
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00000111.toShort(), mutableListOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe false
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00001111.toShort(), mutableListOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe true
        field.isFinal shouldBe false

        field = MutableField(pool, 0, 0, 0b00000000_00011111.toShort(), mutableListOf())
        field.isPublic shouldBe true
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe true
        field.isFinal shouldBe true

        field = MutableField(pool, 0, 0, 0b00000000_00011110.toShort(), mutableListOf())
        field.isPublic shouldBe false
        field.isPrivate shouldBe true
        field.isProtected shouldBe true
        field.isStatic shouldBe true
        field.isFinal shouldBe true
    }

    "dump" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0b00000000_00000001.toShort(), mutableListOf())
        val stream = ByteArrayOutputStream()
        field.dump(DataOutputStream(stream))
        val arr = stream.toByteArray()
        arr contentEquals byteArrayOf(
            *0.toBytes(),
            *0.toBytes(),
            *1.toShort().toBytes(),
            *0.toShort().toBytes()
        ) shouldBe true
    }

    "dump to array" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0b00000000_00000001.toShort(), mutableListOf())
        val arr = field.dump()
        arr contentEquals byteArrayOf(
            *0.toBytes(),
            *0.toBytes(),
            *1.toShort().toBytes(),
            *0.toShort().toBytes()
        ) shouldBe true
    }

    "from stream" {
        val pool = MutableConstantPool()
        val stream = byteArrayOf(
            *pool.resolveUtf8("test").toBytes(),
            *0.toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes()
        ).dataStream()

        val field = MutableField.fromStream(pool, stream)
        field.name shouldBe "test"
        field.flags shouldBe 1.toShort()
    }

    "equals" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        val field2 = MutableField(pool, 0, 0, 0, mutableListOf())
        val field3 = MutableField(pool, 1, 0, 0, mutableListOf())
        val field4 = MutableField(pool, 0, 0, 1, mutableListOf())
        val field5 = MutableField(pool, 1, 0, 1, mutableListOf())

        field shouldBe field
        field shouldBe field2
        field shouldNotBe field3
        field shouldNotBe field4
        field shouldNotBe field5
    }

    "hash code" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        val field2 = MutableField(pool, 0, 0, 0, mutableListOf())
        val field3 = MutableField(pool, 1, 0, 0, mutableListOf())
        val field4 = MutableField(pool, 0, 0, 1, mutableListOf())
        val field5 = MutableField(pool, 1, 0, 1, mutableListOf())

        field.hashCode() shouldBe field.hashCode()
        field.hashCode() shouldBe field2.hashCode()
        field.hashCode() shouldNotBe field3.hashCode()
        field.hashCode() shouldNotBe field4.hashCode()
        field.hashCode() shouldNotBe field5.hashCode()
    }

    "from field" {
        val pool = MutableConstantPool()
        val field = Field(pool, pool.resolveUtf8("aaa"), 0, 0, listOf())
        val mutableField = MutableField.fromField(pool, field)
        mutableField.name shouldBe field.name
        mutableField.flags shouldBe field.flags
        mutableField.attributes shouldContainExactly field.attributes
    }

    "set name" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.name = "test"
        field.name shouldBe "test"
    }

    "attributes" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.attributes.size shouldBe 0
        field.attributes.add(Attribute(pool, 0, byteArrayOf()))
        field.attributes.size shouldBe 1
        field.attributes.add(Attribute(pool, 0, byteArrayOf()))
        field.attributes.size shouldBe 2
        field.attributes.add(Attribute(pool, 0, byteArrayOf()))
        field.attributes.size shouldBe 3
    }

    "set name constant" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, pool.resolveUtf8("aaa"), 0, 0, mutableListOf())
        field.nameConstant shouldBe 0
        field.name shouldBe "aaa"
        field.name = "test"
        field.nameConstant shouldBe 1
    }

    "set public" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.isPublic = true
        field.isPublic shouldBe true
    }

    "set private" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.isPrivate = true
        field.isPrivate shouldBe true
    }

    "set protected" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.isProtected = true
        field.isProtected shouldBe true
    }

    "set static" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.isStatic = true
        field.isStatic shouldBe true
    }

    "set final" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.isFinal = true
        field.isFinal shouldBe true
    }

    "set flags" {
        val pool = MutableConstantPool()
        val field = MutableField(pool, 0, 0, 0, mutableListOf())
        field.flags shouldBe 0
        field.isPublic = true
        field.flags shouldBe 1
        field.isPrivate = true
        field.flags shouldBe 3
        field.isProtected = true
        field.flags shouldBe 7
        field.isStatic = true
        field.flags shouldBe 15
        field.isFinal = true
        field.flags shouldBe 31
    }
})
