package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.AnonymousAttributeImpl
import com.shakelang.shake.bytecode.interpreter.format.attribute.MutableAnonymousAttributeImpl
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.testlib.shouldContainExactly
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
            0,
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
            listOf(field),
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
            emptyList()
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
            listOf(subClass),
            emptyList(),
            emptyList(),
            emptyList()
        )

        clazz.subClasses shouldContainExactly listOf(subClass)
    }

    "attributes" {
        val pool = MutableConstantPool()
        val attribute = AnonymousAttributeImpl(
            pool,
            0,
            byteArrayOf(0)
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
            emptyList(),
            emptyList(),
            listOf(Field(pool, 0, 0, 0, emptyList())),
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
            listOf(Class(pool, 0, 0, 0, emptyList(), emptyList(), emptyList(), emptyList(), emptyList())),
            emptyList(),
            emptyList(),
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
            listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(0)))
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
            *0.toShort().toBytes()
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
            *0.toShort().toBytes()
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
            *0.toShort().toBytes()
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

    "get isPublic (true)" {
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

    "get isPublic (false)" {
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

    "get isPrivate (true)" {
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

    "get isPrivate (false)" {
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

    "get isProtected (true)" {
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

    "get isProtected (false)" {
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

    "get isStatic (true)" {
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

    "get isStatic (false)" {
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

    "get isFinal (true)" {
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

    "get isFinal (false)" {
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
})

class MutableClassTests : FreeSpec({

    "name constant" {
        val pool = MutableConstantPool()
        val nameConstant = pool.resolveUtf8("test")
        val clazz = MutableClass(
            pool,
            nameConstant,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.nameConstant shouldBe nameConstant
    }

    "set name constant" {
        val pool = MutableConstantPool()
        val nameConstant = pool.resolveUtf8("test")
        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.nameConstant = nameConstant
        clazz.nameConstant shouldBe nameConstant
    }

    "super name constant" {
        val pool = MutableConstantPool()
        val superNameConstant = pool.resolveUtf8("test")
        val clazz = MutableClass(
            pool,
            0,
            superNameConstant,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.superNameConstant shouldBe superNameConstant
    }

    "set super name constant" {
        val pool = MutableConstantPool()
        val superNameConstant = pool.resolveUtf8("test")
        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.superNameConstant = superNameConstant
        clazz.superNameConstant shouldBe superNameConstant
    }

    "flags" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000001.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic shouldBe true
        clazz.isPrivate shouldBe false
        clazz.isProtected shouldBe false
        clazz.isStatic shouldBe false
        clazz.isFinal shouldBe false
    }

    "set flags" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic = true
        clazz.isPrivate = true
        clazz.isProtected = true
        clazz.isStatic = true
        clazz.isFinal = true

        clazz.isPublic shouldBe true
        clazz.isPrivate shouldBe true
        clazz.isProtected shouldBe true
        clazz.isStatic shouldBe true
        clazz.isFinal shouldBe true
    }

    "interfaces" {
        val pool = MutableConstantPool()
        val interfaceConstant = pool.resolveUtf8("test")
        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(interfaceConstant),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.interfacesConstants shouldContainExactly mutableListOf(interfaceConstant)
    }

    "set interfaces" {
        val pool = MutableConstantPool()
        val interfaceConstant = pool.resolveUtf8("test")
        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.interfacesConstants = mutableListOf(interfaceConstant)
        clazz.interfacesConstants shouldContainExactly mutableListOf(interfaceConstant)
    }

    "fields" {
        val pool = MutableConstantPool()
        val field = MutableField(
            pool,
            0,
            0,
            0,
            mutableListOf()
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(field),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.fields shouldContainExactly mutableListOf(field)
    }

    "modify fields" {
        val pool = MutableConstantPool()
        val field = MutableField(
            pool,
            0,
            0,
            0,
            mutableListOf()
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.fields.add(field)
        clazz.fields shouldContainExactly mutableListOf(field)
    }

    "methods" {
        val pool = MutableConstantPool()
        val method = MutableMethod(
            pool,
            0,
            0,
            0,
            mutableListOf()
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(method),
            mutableListOf(),
            mutableListOf()
        )

        clazz.methods shouldContainExactly mutableListOf(method)
    }

    "modify methods" {
        val pool = MutableConstantPool()
        val method = MutableMethod(
            pool,
            0,
            0,
            0,
            mutableListOf()
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.methods.add(method)
        clazz.methods shouldContainExactly mutableListOf(method)
    }

    "sub MutableClasses" {
        val pool = MutableConstantPool()
        val subClass = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(subClass),
            mutableListOf()
        )

        clazz.subClasses shouldContainExactly mutableListOf(subClass)
    }

    "modify sub MutableClasses" {
        val pool = MutableConstantPool()
        val subClass = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.subClasses.add(subClass)
        clazz.subClasses shouldContainExactly mutableListOf(subClass)
    }

    "attributes" {
        val pool = MutableConstantPool()
        val attribute = MutableAnonymousAttributeImpl(
            pool,
            0,
            byteArrayOf(0)
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(attribute)
        )

        clazz.attributes shouldContainExactly mutableListOf(attribute)
    }

    "modify attributes" {
        val pool = MutableConstantPool()
        val attribute = MutableAnonymousAttributeImpl(
            pool,
            0,
            byteArrayOf(0)
        )

        val clazz = MutableClass(
            pool,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.attributes.add(attribute)
        clazz.attributes shouldContainExactly mutableListOf(attribute)
    }

    "equals" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz2 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz3 = MutableClass(
            pool,
            pool.resolveUtf8("test3"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz4 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test3"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz5 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            1,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz6 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(pool.resolveUtf8("test")),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz7 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(MutableField(pool, 0, 0, 0, mutableListOf())),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val clazz8 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(MutableMethod(pool, 0, 0, 0, mutableListOf())),
            mutableListOf(),
            mutableListOf()
        )

        val clazz9 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(
                MutableClass(
                    pool,
                    0,
                    0,
                    0,
                    mutableListOf(),
                    mutableListOf(),
                    mutableListOf(),
                    mutableListOf(),
                    mutableListOf()
                )
            ),
            mutableListOf()
        )

        val clazz10 = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(MutableAnonymousAttributeImpl(pool, 0, byteArrayOf(0)))
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
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000001.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic shouldBe true
    }

    "is public (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic shouldBe false
    }

    "is private (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000010.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPrivate shouldBe true
    }

    "is private (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPrivate shouldBe false
    }

    "is protected (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000100.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isProtected shouldBe true
    }

    "is protected (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isProtected shouldBe false
    }

    "is static (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00001000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isStatic shouldBe true
    }

    "is static (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isStatic shouldBe false
    }

    "is final (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00010000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isFinal shouldBe true
    }

    "is final (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isFinal shouldBe false
    }

    "bump" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            1,
            2,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
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
            *0.toShort().toBytes()
        )
    }

    "bump to byte array" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            1,
            2,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.dump() shouldContainExactly byteArrayOf(
            *0.toBytes(),
            *1.toBytes(),
            *2.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes()
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
            *0.toShort().toBytes()
        ).dataStream()

        val clazz = MutableClass.fromStream(pool, stream)

        clazz shouldBe MutableClass(
            pool,
            0,
            1,
            2,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )
    }

    "get isPublic (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000001.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic shouldBe true
    }

    "get isPublic (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic shouldBe false
    }

    "set isPublic (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic = true
        clazz.isPublic shouldBe true
    }

    "set isPublic (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000001.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPublic = false
        clazz.isPublic shouldBe false
    }

    "get isPrivate (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000010.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPrivate shouldBe true
    }

    "get isPrivate (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPrivate shouldBe false
    }

    "set isPrivate (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPrivate = true
        clazz.isPrivate shouldBe true
    }

    "set isPrivate (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000010.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isPrivate = false
        clazz.isPrivate shouldBe false
    }

    "get isProtected (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000100.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isProtected shouldBe true
    }

    "get isProtected (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isProtected shouldBe false
    }

    "set isProtected (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isProtected = true
        clazz.isProtected shouldBe true
    }

    "set isProtected (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00000100.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isProtected = false
        clazz.isProtected shouldBe false
    }

    "get isStatic (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000001_00001000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isStatic shouldBe true
    }

    "get isStatic (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isStatic shouldBe false
    }

    "set isStatic (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isStatic = true
        clazz.isStatic shouldBe true
    }

    "set isStatic (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000001_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isStatic = false
        clazz.isStatic shouldBe false
    }

    "get isFinal (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b00000000_00010000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isFinal shouldBe true
    }

    "get isFinal (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isFinal shouldBe false
    }

    "set isFinal (true)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isFinal = true
        clazz.isFinal shouldBe true
    }

    "set isFinal (false)" {
        val pool = MutableConstantPool()
        val clazz = MutableClass(
            pool,
            0,
            0,
            0b10000000_00000000.toShort(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        clazz.isFinal = false
        clazz.isFinal shouldBe false
    }
})
