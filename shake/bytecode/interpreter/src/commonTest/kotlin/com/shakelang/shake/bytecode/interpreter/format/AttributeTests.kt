package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class AttributeTests : FreeSpec({

    "test name constant" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, 0, byteArrayOf())
        attribute.nameConstant shouldBe 0
    }

    "test name getter" {
        val pool = MutableConstantPool()
        val constant = pool.resolveUtf8("test")
        val attribute = Attribute(pool, constant, byteArrayOf())
        attribute.name shouldBe "test"
    }

    "test value" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, 0, byteArrayOf(1, 2, 3))
        attribute.value contentEquals byteArrayOf(1, 2, 3) shouldBe true
    }

    "test bump" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, 0, byteArrayOf(1, 2, 3))

        val stream = ByteArrayOutputStream()
        attribute.bump(DataOutputStream(stream))
        val arr = stream.toByteArray()

        arr contentEquals byteArrayOf(0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3) shouldBe true
    }

    "test bump to array" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, 0, byteArrayOf(1, 2, 3))

        val arr = attribute.bump()

        arr contentEquals byteArrayOf(0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3) shouldBe true
    }
    
    "test equals" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute2 = Attribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute3 = Attribute(pool, 0, byteArrayOf(1, 2, 4))
        val attribute4 = Attribute(pool, 1, byteArrayOf(1, 2, 3))
        val attribute5 = Attribute(pool, 1, byteArrayOf(1, 2, 4))

        attribute shouldBe attribute
        attribute shouldBe attribute2
        attribute shouldNotBe attribute3
        attribute shouldNotBe attribute4
        attribute shouldNotBe attribute5
    }

    "test hash code" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute2 = Attribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute3 = Attribute(pool, 0, byteArrayOf(1, 2, 4))
        val attribute4 = Attribute(pool, 1, byteArrayOf(1, 2, 3))
        val attribute5 = Attribute(pool, 1, byteArrayOf(1, 2, 4))

        attribute.hashCode() shouldBe attribute.hashCode()
        attribute.hashCode() shouldBe attribute2.hashCode()
        attribute.hashCode() shouldNotBe attribute3.hashCode()
        attribute.hashCode() shouldNotBe attribute4.hashCode()
        attribute.hashCode() shouldNotBe attribute5.hashCode()
    }

    "test from stream" {
        val pool = MutableConstantPool()
        pool.resolveUtf8("test")
        val stream = byteArrayOf(0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3).dataStream()
        val attribute = Attribute.fromStream(pool, stream)
        attribute.name shouldBe "test"
        attribute.value contentEquals byteArrayOf(1, 2, 3) shouldBe true
    }
})

class MutableAttributeTests : FreeSpec({

    "test name constant" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf())
        attribute.nameConstant shouldBe 0
    }

    "test name getter" {
        val pool = MutableConstantPool()
        val constant = pool.resolveUtf8("test")
        val attribute = MutableAttribute(pool, constant, byteArrayOf())
        attribute.name shouldBe "test"
    }

    "test set name constant" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf())
        attribute.nameConstant = 1
        attribute.nameConstant shouldBe 1
    }

    "test set name" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf())
        attribute.name = "test"
        attribute.name shouldBe "test"
    }

    "test value" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))
        attribute.value contentEquals byteArrayOf(1, 2, 3) shouldBe true
    }

    "test set value" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))
        attribute.value = byteArrayOf(1, 2, 4)
        attribute.value contentEquals byteArrayOf(1, 2, 4) shouldBe true
    }

    "test bump" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))

        val stream = ByteArrayOutputStream()
        attribute.bump(DataOutputStream(stream))
        val arr = stream.toByteArray()

        arr contentEquals byteArrayOf(0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3) shouldBe true
    }

    "test bump to array" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))

        val arr = attribute.bump()

        arr contentEquals byteArrayOf(0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 3) shouldBe true
    }

    "test equals" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute2 = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute3 = MutableAttribute(pool, 0, byteArrayOf(1, 2, 4))
        val attribute4 = MutableAttribute(pool, 1, byteArrayOf(1, 2, 3))
        val attribute5 = MutableAttribute(pool, 1, byteArrayOf(1, 2, 4))

        attribute shouldBe attribute
        attribute shouldBe attribute2
        attribute shouldNotBe attribute3
        attribute shouldNotBe attribute4
        attribute shouldNotBe attribute5
    }

    "test hash code" {
        val pool = MutableConstantPool()
        val attribute = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute2 = MutableAttribute(pool, 0, byteArrayOf(1, 2, 3))
        val attribute3 = MutableAttribute(pool, 0, byteArrayOf(1, 2, 4))
        val attribute4 = MutableAttribute(pool, 1, byteArrayOf(1, 2, 3))
        val attribute5 = MutableAttribute(pool, 1, byteArrayOf(1, 2, 4))

        attribute.hashCode() shouldBe attribute.hashCode()
        attribute.hashCode() shouldBe attribute2.hashCode()
        attribute.hashCode() shouldNotBe attribute3.hashCode()
        attribute.hashCode() shouldNotBe attribute4.hashCode()
        attribute.hashCode() shouldNotBe attribute5.hashCode()
    }

    "test from stream" {
        val pool = MutableConstantPool()
        val stream = byteArrayOf(*pool.resolveUtf8("test").toBytes(), 0, 0, 0, 3, 1, 2, 3).dataStream()
        val attribute = MutableAttribute.fromStream(pool, stream)
        attribute.name shouldBe "test"
        attribute.value contentEquals byteArrayOf(1, 2, 3) shouldBe true
    }

    "test from attribute" {
        val pool = MutableConstantPool()
        val attribute = Attribute(pool, pool.resolveUtf8("test"), byteArrayOf(1, 2, 3))
        val attribute2 = MutableAttribute.fromAttribute(attribute)
        attribute2.name shouldBe attribute.name
        attribute2.value contentEquals attribute.value shouldBe true
    }
})