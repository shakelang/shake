package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import com.shakelang.shake.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ClassTests : FreeSpec({

    "name constant" {
        val pool = MutableConstantPool()
        val nameConstant = pool.resolveUtf8("test")
        val clazz = Class(
            pool,
            nameConstant,
            0,
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.nameConstant shouldBe nameConstant
    }

    "super name constant" {
        val pool = MutableConstantPool()
        val superNameConstant = pool.resolveUtf8("test")
        val clazz = Class(
            pool,
            0,
            superNameConstant,
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.superNameConstant shouldBe superNameConstant
    }

    "flags" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000001.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isPublic shouldBe true
        clazz.isPrivate shouldBe false
        clazz.isProtected shouldBe false
        clazz.isStatic shouldBe false
        clazz.isFinal shouldBe false
    }

    "interfaces" {
        val pool = MutableConstantPool()
        val interfaceConstant = pool.resolveUtf8("test")
        val clazz = Class(
            pool,
            0,
            0,
            0,
            listOf(interfaceConstant),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.interfacesConstants shouldContainExactly listOf(interfaceConstant)
    }

    "fields" {
        val pool = MutableConstantPool()
        val field = Field(
            pool,
            0,
            0,
            emptyList()
        )

        val clazz = Class(
            pool,
            0,
            0,
            0,
            emptyList(),
            listOf(field),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.fields shouldContainExactly listOf(field)
    }

    "methods" {
        val pool = MutableConstantPool()
        val method = Method(
            pool,
            0,
            0,
            0,
            emptyList(),
        )

        val clazz = Class(
            pool,
            0,
            0,
            0,
            emptyList(),
            emptyList(),
            listOf(method),
            emptyList(),
            emptyList()
        )

        clazz.methods shouldContainExactly listOf(method)
    }

    "sub classes" {
        val pool = MutableConstantPool()
        val subClass = Class(
            pool,
            0,
            0,
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz = Class(
            pool,
            0,
            0,
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            listOf(subClass),
            emptyList()
        )

        clazz.subClasses shouldContainExactly listOf(subClass)
    }

    "attributes" {
        val pool = MutableConstantPool()
        val attribute = Attribute(
            pool,
            0,
            byteArrayOf(0),
        )

        val clazz = Class(
            pool,
            0,
            0,
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            listOf(attribute)
        )

        clazz.attributes shouldContainExactly listOf(attribute)
    }

    "equals" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz2 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz3 = Class(
            pool,
            pool.resolveUtf8("test3"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz4 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test3"),
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz5 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            1,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz6 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(pool.resolveUtf8("test")),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz7 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            listOf(Field(pool, 0, 0, emptyList())),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val clazz8 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            emptyList(),
            listOf(Method(pool, 0, 0, 0, emptyList())),
            emptyList(),
            emptyList()
        )

        val clazz9 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            listOf(Class(pool, 0, 0, 0, emptyList(), emptyList(), emptyList(), emptyList(), emptyList())),
            emptyList()
        )

        val clazz10 = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            listOf(Attribute(pool, 0, byteArrayOf(0)))
        )

        clazz shouldBe clazz
        clazz shouldBe clazz2
        clazz shouldNotBe clazz3
        clazz shouldNotBe clazz4
        clazz shouldNotBe clazz5
        clazz shouldNotBe clazz6
        clazz shouldNotBe clazz7
        clazz shouldNotBe clazz8
        clazz shouldNotBe clazz9
        clazz shouldNotBe clazz10
    }

    "is public (true)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000001.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isPublic shouldBe true
    }

    "is public (false)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isPublic shouldBe false
    }

    "is private (true)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000010.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isPrivate shouldBe true
    }

    "is private (false)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isPrivate shouldBe false
    }

    "is protected (true)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000100.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isProtected shouldBe true
    }

    "is protected (false)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isProtected shouldBe false
    }

    "is static (true)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00001000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isStatic shouldBe true
    }

    "is static (false)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isStatic shouldBe false
    }

    "is final (true)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00010000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isFinal shouldBe true
    }

    "is final (false)" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.isFinal shouldBe false
    }

    "bump" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            1,
            2,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        val stream = ByteArrayOutputStream()
        clazz.dump(DataOutputStream(stream))

        stream.toByteArray() shouldContainExactly byteArrayOf(
            *0.toBytes(),
            *1.toBytes(),
            *2.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
        )
    }

    "bump to byte array" {
        val pool = MutableConstantPool()
        val clazz = Class(
            pool,
            0,
            1,
            2,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.dump() shouldContainExactly byteArrayOf(
            *0.toBytes(),
            *1.toBytes(),
            *2.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
        )
    }

    "from byte array" {
        val pool = MutableConstantPool()
        val stream = byteArrayOf(
            *pool.resolveUtf8("test").toBytes(),
            *pool.resolveUtf8("test2").toBytes(),
            *2.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
        ).dataStream()

        val clazz = Class.fromStream(pool, stream)

        clazz shouldBe Class(
            pool,
            0,
            1,
            2,
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )
    }
})