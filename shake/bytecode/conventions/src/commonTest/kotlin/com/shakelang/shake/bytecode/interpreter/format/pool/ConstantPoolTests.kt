package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ConstantPoolTests : FreeSpec({

    "is utf8" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.isUtf8(0) shouldBe true
        pool.isUtf8(1) shouldBe false
    }

    "is byte" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.isByte(0) shouldBe false
        pool.isByte(1) shouldBe true
    }

    "is short" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ShortConstant(0)
            )
        )
        pool.isShort(0) shouldBe false
        pool.isShort(1) shouldBe true
    }

    "is int" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.IntConstant(0)
            )
        )
        pool.isInt(0) shouldBe false
        pool.isInt(1) shouldBe true
    }

    "is long" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.LongConstant(0)
            )
        )
        pool.isLong(0) shouldBe false
        pool.isLong(1) shouldBe true
    }

    "is float" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.FloatConstant(0f)
            )
        )
        pool.isFloat(0) shouldBe false
        pool.isFloat(1) shouldBe true
    }

    "is double" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.DoubleConstant(0.0)
            )
        )
        pool.isDouble(0) shouldBe false
        pool.isDouble(1) shouldBe true
    }

    "is class" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0)
            )
        )

        pool.isClass(0) shouldBe false
        pool.isClass(1) shouldBe true
    }

    "get utf8" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.getUtf8(0) shouldBe ConstantPoolEntry.Utf8Constant("test")

        shouldThrow<ClassCastException> {
            pool.getUtf8(1)
        }
    }

    "get byte" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.getByte(1) shouldBe ConstantPoolEntry.ByteConstant(0)

        shouldThrow<ClassCastException> {
            pool.getByte(0)
        }
    }

    "get short" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ShortConstant(0)
            )
        )
        pool.getShort(1) shouldBe ConstantPoolEntry.ShortConstant(0)

        shouldThrow<ClassCastException> {
            pool.getShort(0)
        }
    }

    "get int" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.IntConstant(0)
            )
        )
        pool.getInt(1) shouldBe ConstantPoolEntry.IntConstant(0)

        shouldThrow<ClassCastException> {
            pool.getInt(0)
        }
    }

    "get long" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.LongConstant(0)
            )
        )
        pool.getLong(1) shouldBe ConstantPoolEntry.LongConstant(0)

        shouldThrow<ClassCastException> {
            pool.getLong(0)
        }
    }

    "get float" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.FloatConstant(0f)
            )
        )
        pool.getFloat(1) shouldBe ConstantPoolEntry.FloatConstant(0f)

        shouldThrow<ClassCastException> {
            pool.getFloat(0)
        }
    }

    "get double" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.DoubleConstant(0.0)
            )
        )
        pool.getDouble(1) shouldBe ConstantPoolEntry.DoubleConstant(0.0)

        shouldThrow<ClassCastException> {
            pool.getDouble(0)
        }
    }

    "get class" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0)
            )
        )
        pool.getClass(1) shouldBe ConstantPoolEntry.ClassConstant(0)

        shouldThrow<ClassCastException> {
            pool.getClass(0)
        }
    }

    "find utf8" {
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ByteConstant(0)
            )
        )
        pool.findUtf8("test") shouldBe 0
        pool.findUtf8("test2") shouldBe null
    }

    "find byte" {
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
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
        val pool = ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )

        pool shouldBe pool
        pool shouldBe ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )
        pool shouldNotBe ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(1),
                ConstantPoolEntry.ClassConstant(0)
            )
        )
        pool shouldNotBe ConstantPool(
            listOf(
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

        val pool = ConstantPool.fromStream(stream)

        pool shouldBe ConstantPool(
            listOf(
                ConstantPoolEntry.Utf8Constant("test"),
                ConstantPoolEntry.ClassConstant(0),
                ConstantPoolEntry.ClassConstant(1)
            )
        )
    }
})
