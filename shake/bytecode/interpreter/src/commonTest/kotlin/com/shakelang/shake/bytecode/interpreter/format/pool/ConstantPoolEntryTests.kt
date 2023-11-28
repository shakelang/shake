package com.shakelang.shake.bytecode.interpreter.format.pool

import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ConstantPoolEntryTests : FreeSpec({

    "utf8 constant to bytes" {
        val constant = ConstantPoolEntry.Utf8Constant("test")
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(1, 0, 4, *"test".toBytes())
    }

    "utf8 constant from bytes" {
        val constant = ConstantPoolEntry.Utf8Constant.fromStream(byteArrayOf(0, 4, *"test".toBytes()).dataStream())
        constant.value shouldBe "test"
    }

    "byte constant to bytes" {
        val constant = ConstantPoolEntry.ByteConstant(1)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(2, 1)
    }

    "byte constant from bytes" {
        val constant = ConstantPoolEntry.ByteConstant.fromStream(byteArrayOf(1).dataStream())
        constant.value shouldBe 1
    }

    "short constant to bytes" {
        val constant = ConstantPoolEntry.ShortConstant(1)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(3, 0, 1)
    }

    "short constant from bytes" {
        val constant = ConstantPoolEntry.ShortConstant.fromStream(byteArrayOf(0, 1).dataStream())
        constant.value shouldBe 1
    }

    "int constant to bytes" {
        val constant = ConstantPoolEntry.IntConstant(1)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(4, 0, 0, 0, 1)
    }

    "int constant from bytes" {
        val constant = ConstantPoolEntry.IntConstant.fromStream(byteArrayOf(0, 0, 0, 1).dataStream())
        constant.value shouldBe 1
    }

    "long constant to bytes" {
        val constant = ConstantPoolEntry.LongConstant(1)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(5, 0, 0, 0, 0, 0, 0, 0, 1)
    }

    "long constant from bytes" {
        val constant = ConstantPoolEntry.LongConstant.fromStream(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 1).dataStream())
        constant.value shouldBe 1
    }

    "float constant to bytes" {
        val constant = ConstantPoolEntry.FloatConstant(1f)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(6, *(1f.toBits().toBytes()))
    }

    "float constant from bytes" {
        val constant = ConstantPoolEntry.FloatConstant.fromStream(byteArrayOf(*(1f.toBytes())).dataStream())
        constant.value shouldBe 1f
    }

    "double constant to bytes" {
        val constant = ConstantPoolEntry.DoubleConstant(1.0)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(7, *(1.0.toBits().toBytes()))
    }

    "double constant from bytes" {
        val constant = ConstantPoolEntry.DoubleConstant.fromStream(byteArrayOf(*(1.0.toBytes())).dataStream())
        constant.value shouldBe 1.0
    }

    "class constant to bytes" {
        val constant = ConstantPoolEntry.ClassConstant(1)
        val bytes = constant.dump()
        bytes shouldBe byteArrayOf(8, 0, 0, 0, 1)
    }

    "class constant from bytes" {
        val constant = ConstantPoolEntry.ClassConstant.fromStream(byteArrayOf(0, 0, 0, 1).dataStream())
        constant.identifier shouldBe 1
    }
})