package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import com.shakelang.util.primitives.bytes.toBytes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MutableConstantPoolTests : FreeSpec({

    "is utf8" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.isUtf8(0) shouldBe true
        pool.isUtf8(1) shouldBe false
    }

    "is byte" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.isByte(0) shouldBe false
        pool.isByte(1) shouldBe true
    }

    "is short" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ShortConstant(0)
            )
        )
        pool.isShort(0) shouldBe false
        pool.isShort(1) shouldBe true
    }

    "is int" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.IntConstant(0)
            )
        )
        pool.isInt(0) shouldBe false
        pool.isInt(1) shouldBe true
    }

    "is long" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.LongConstant(0)
            )
        )
        pool.isLong(0) shouldBe false
        pool.isLong(1) shouldBe true
    }

    "is float" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.FloatConstant(0f)
            )
        )
        pool.isFloat(0) shouldBe false
        pool.isFloat(1) shouldBe true
    }

    "is double" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.DoubleConstant(0.0)
            )
        )
        pool.isDouble(0) shouldBe false
        pool.isDouble(1) shouldBe true
    }

    "is class" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0)
            )
        )

        pool.isClass(0) shouldBe false
        pool.isClass(1) shouldBe true
    }

    "get utf8" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.getUtf8(0) shouldBe ConstantPoolEntry.Utf8Constant("test")

        shouldThrow<ConstantPoolTypeException> {
            pool.getUtf8(1)
        }
    }

    "get byte" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.getByte(1) shouldBe ConstantPoolEntry.ByteConstant(0)

        shouldThrow<ConstantPoolTypeException> {
            pool.getByte(0)
        }
    }

    "get short" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ShortConstant(0)
            )
        )
        pool.getShort(1) shouldBe ConstantPoolEntry.ShortConstant(0)

        shouldThrow<ConstantPoolTypeException> {
            pool.getShort(0)
        }
    }

    "get int" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.IntConstant(0)
            )
        )
        pool.getInt(1) shouldBe ConstantPoolEntry.IntConstant(0)

        shouldThrow<ConstantPoolTypeException> {
            pool.getInt(0)
        }
    }

    "get long" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.LongConstant(0)
            )
        )
        pool.getLong(1) shouldBe ConstantPoolEntry.LongConstant(0)

        shouldThrow<ConstantPoolTypeException> {
            pool.getLong(0)
        }
    }

    "get float" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.FloatConstant(0f)
            )
        )
        pool.getFloat(1) shouldBe ConstantPoolEntry.FloatConstant(0f)

        shouldThrow<ConstantPoolTypeException> {
            pool.getFloat(0)
        }
    }

    "get double" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.DoubleConstant(0.0)
            )
        )
        pool.getDouble(1) shouldBe ConstantPoolEntry.DoubleConstant(0.0)

        shouldThrow<ConstantPoolTypeException> {
            pool.getDouble(0)
        }
    }

    "get class" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0)
            )
        )
        pool.getClass(1) shouldBe ConstantPoolEntry.ClassConstant(0)

        shouldThrow<ConstantPoolTypeException> {
            pool.getClass(0)
        }
    }

    "find utf8" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.findUtf8("test") shouldBe 0
        pool.findUtf8("test2") shouldBe null
    }

    "find byte" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0),
                ConstantPoolEntry.ByteConstant(1)
            )
        )
        pool.findByte(0) shouldBe 1
        pool.findByte(1) shouldBe 2
        pool.findByte(2) shouldBe null
    }

    "find short" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ShortConstant(0),
                ConstantPoolEntry.ShortConstant(1)
            )
        )
        pool.findShort(0) shouldBe 1
        pool.findShort(1) shouldBe 2
        pool.findShort(2) shouldBe null
    }

    "find int" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.IntConstant(0),
                ConstantPoolEntry.IntConstant(1)
            )
        )
        pool.findInt(0) shouldBe 1
        pool.findInt(1) shouldBe 2
        pool.findInt(2) shouldBe null
    }

    "find long" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.LongConstant(0),
                ConstantPoolEntry.LongConstant(1)
            )
        )
        pool.findLong(0) shouldBe 1
        pool.findLong(1) shouldBe 2
        pool.findLong(2) shouldBe null
    }

    "find float" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.FloatConstant(0f),
                ConstantPoolEntry.FloatConstant(1f)
            )
        )
        pool.findFloat(0f) shouldBe 1
        pool.findFloat(1f) shouldBe 2
        pool.findFloat(2f) shouldBe null
    }

    "find double" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.DoubleConstant(0.0),
                ConstantPoolEntry.DoubleConstant(1.0)
            )
        )
        pool.findDouble(0.0) shouldBe 1
        pool.findDouble(1.0) shouldBe 2
        pool.findDouble(2.0) shouldBe null
    }

    "find class" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )

        pool.findClass(0) shouldBe 1
        pool.findClass(1) shouldBe 2
        pool.findClass(2) shouldBe null
    }

    "test bump" {
        val stream = ByteArrayOutputStream()
        val dataStream = DataOutputStream(stream)
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )

        pool.dump(dataStream)

        stream.toByteArray() contentEquals byteArrayOf(
            *3.toBytes(), // size
            1.toByte(), // constant type (utf8)
            *4.toShort().toBytes(), // length of utf8
            *"test".toBytes(), // value of utf8
            8.toByte(), // constant type (class)
            *0.toBytes(), // identifier of class
            8.toByte(), // constant type (class)
            *1.toBytes() // identifier of class
        ) shouldBe true
    }

    "test bump to array" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )

        val arr = pool.dump()

        arr contentEquals byteArrayOf(
            *3.toBytes(), // size
            1.toByte(), // constant type (utf8)
            *4.toShort().toBytes(), // length of utf8
            *"test".toBytes(), // value of utf8
            8.toByte(), // constant type (class)
            *0.toBytes(), // identifier of class
            8.toByte(), // constant type (class)
            *1.toBytes() // identifier of class
        ) shouldBe true
    }

    "equals" {
        val pool = MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )

        pool shouldBe pool
        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )
        pool shouldNotBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(1),
                ConstantPoolEntry.ClassConstant(0)
            )
        )
        pool shouldNotBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(2)
            )
        )
    }

    "from stream" {
        val stream = byteArrayOf(
            *3.toBytes(), // size
            1.toByte(), // constant type (utf8)
            *4.toShort().toBytes(), // length of utf8
            *"test".toBytes(), // value of utf8
            8.toByte(), // constant type (class)
            *0.toBytes(), // identifier of class
            8.toByte(), // constant type (class)
            *1.toBytes() // identifier of class
        ).dataStream()

        val pool = MutableConstantPool.fromStream(stream)

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )
    }

    "create utf8" {
        val pool = MutableConstantPool()
        pool.createUtf8("test") shouldBe 0
        pool.createUtf8("test") shouldBe 1
        pool.createUtf8("test2") shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.Utf8Constant("test2")
            )
        )
    }

    "create byte" {
        val pool = MutableConstantPool()
        pool.createByte(0) shouldBe 0
        pool.createByte(0) shouldBe 1
        pool.createByte(1) shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.ByteConstant(0),
                ConstantPoolEntry.ByteConstant(0),
                ConstantPoolEntry.ByteConstant(1)
            )
        )
    }

    "create short" {
        val pool = MutableConstantPool()
        pool.createShort(0) shouldBe 0
        pool.createShort(0) shouldBe 1
        pool.createShort(1) shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.ShortConstant(0),
                ConstantPoolEntry.ShortConstant(0),
                ConstantPoolEntry.ShortConstant(1)
            )
        )
    }

    "create int" {
        val pool = MutableConstantPool()
        pool.createInt(0) shouldBe 0
        pool.createInt(0) shouldBe 1
        pool.createInt(1) shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.IntConstant(0),
                ConstantPoolEntry.IntConstant(0),
                ConstantPoolEntry.IntConstant(1)
            )
        )
    }

    "create long" {
        val pool = MutableConstantPool()
        pool.createLong(0) shouldBe 0
        pool.createLong(0) shouldBe 1
        pool.createLong(1) shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.LongConstant(0),
                ConstantPoolEntry.LongConstant(0),
                ConstantPoolEntry.LongConstant(1)
            )
        )
    }

    "create float" {
        val pool = MutableConstantPool()
        pool.createFloat(0f) shouldBe 0
        pool.createFloat(0f) shouldBe 1
        pool.createFloat(1f) shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.FloatConstant(0f),
                ConstantPoolEntry.FloatConstant(0f),
                ConstantPoolEntry.FloatConstant(1f)
            )
        )
    }

    "create double" {
        val pool = MutableConstantPool()
        pool.createDouble(0.0) shouldBe 0
        pool.createDouble(0.0) shouldBe 1
        pool.createDouble(1.0) shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.DoubleConstant(0.0),
                ConstantPoolEntry.DoubleConstant(0.0),
                ConstantPoolEntry.DoubleConstant(1.0)
            )
        )
    }

    "create class" {
        val pool = MutableConstantPool()
        pool.createClass(0) shouldBe 0
        pool.createClass("test") shouldBe 2
        pool.createClass("test") shouldBe 3

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(1),
                ConstantPoolEntry.ClassConstant(1)
            )
        )
    }

    "resolve utf8" {
        val pool = MutableConstantPool()

        pool.resolveUtf8("test") shouldBe 0
        pool.resolveUtf8("test") shouldBe 0
        pool.resolveUtf8("test2") shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.Utf8Constant("test2")
            )
        )
    }

    "resolve byte" {
        val pool = MutableConstantPool()

        pool.resolveByte(0) shouldBe 0
        pool.resolveByte(0) shouldBe 0
        pool.resolveByte(1) shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.ByteConstant(0),
                ConstantPoolEntry.ByteConstant(1)
            )
        )
    }

    "resolve short" {
        val pool = MutableConstantPool()

        pool.resolveShort(0) shouldBe 0
        pool.resolveShort(0) shouldBe 0
        pool.resolveShort(1) shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.ShortConstant(0),
                ConstantPoolEntry.ShortConstant(1)
            )
        )
    }

    "resolve int" {
        val pool = MutableConstantPool()

        pool.resolveInt(0) shouldBe 0
        pool.resolveInt(0) shouldBe 0
        pool.resolveInt(1) shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.IntConstant(0),
                ConstantPoolEntry.IntConstant(1)
            )
        )
    }

    "resolve long" {
        val pool = MutableConstantPool()

        pool.resolveLong(0) shouldBe 0
        pool.resolveLong(0) shouldBe 0
        pool.resolveLong(1) shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.LongConstant(0),
                ConstantPoolEntry.LongConstant(1)
            )
        )
    }

    "resolve float" {
        val pool = MutableConstantPool()

        pool.resolveFloat(0f) shouldBe 0
        pool.resolveFloat(0f) shouldBe 0
        pool.resolveFloat(1f) shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.FloatConstant(0f),
                ConstantPoolEntry.FloatConstant(1f)
            )
        )
    }

    "resolve double" {
        val pool = MutableConstantPool()

        pool.resolveDouble(0.0) shouldBe 0
        pool.resolveDouble(0.0) shouldBe 0
        pool.resolveDouble(1.0) shouldBe 1

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.DoubleConstant(0.0),
                ConstantPoolEntry.DoubleConstant(1.0)
            )
        )
    }

    "resolve class" {
        val pool = MutableConstantPool()

        pool.resolveClass(0) shouldBe 0
        pool.resolveClass(0) shouldBe 0
        pool.resolveClass("test") shouldBe 2
        pool.resolveClass("test") shouldBe 2

        pool shouldBe MutableConstantPool(
            mutableListOf(
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(1)
            )
        )
    }
})
