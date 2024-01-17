package com.shakelang.shake.bytecode.interpreter.heap

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll

class GlobalMemoryTests : FreeSpec({

    "grow" {
        val memory = GlobalMemory()
        memory.grow(1)
        memory.size shouldBe 1024 * 16
        memory.outerSize shouldBe 1
        memory.contents.size shouldBe 1
        memory.contents[0].size shouldBe 1024 * 16
        memory.grow(1)
        memory.size shouldBe 1024 * 16 * 2
        memory.outerSize shouldBe 2
        memory.contents.size shouldBe 2
        memory.contents[0].size shouldBe 1024 * 16
    }

    "set / get" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 1), Arb.long()) { i, value ->
            memory[i] = value.toByte()
            memory[i] shouldBe value.toByte()
        }
    }

    "set / get (2 args)" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 1), Arb.long()) { i, value ->
            val outer = (i / memory.innerSize).toInt()
            val inner = (i % memory.innerSize).toInt()
            memory[outer, inner] = value.toByte()
            memory[outer, inner] shouldBe value.toByte()
        }
    }

    "setBytes / getBytes" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(
            1000,
            Arb.long(0, 1024 * 16 * 10 - 10 - 1),
            Arb.byteArray(Arb.of(10), Arb.byte()),
        ) { i, arr ->
            memory.setBytes(i, arr)
            memory.getBytes(i, arr.size.toLong()) shouldBe arr
        }
    }

    "setByte / getByte" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 1), Arb.byte()) { i, value ->
            memory.setByte(i, value)
            memory.getByte(i) shouldBe value
        }
    }

    "setUByte / getUByte" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 2 - 1), Arb.uByte()) { i, value ->
            memory.setUByte(i, value)
            memory.getUByte(i) shouldBe value
        }
    }

    "setShort / getShort" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 2 - 1), Arb.short()) { i, value ->
            memory.setShort(i, value)
            memory.getShort(i) shouldBe value
        }
    }

    "setUShort / getUShort" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 2 - 1), Arb.uShort()) { i, value ->
            memory.setUShort(i, value)
            memory.getUShort(i) shouldBe value
        }
    }

    "setInt / getInt" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 4 - 1), Arb.int()) { i, value ->
            memory.setInt(i, value)
            memory.getInt(i) shouldBe value
        }
    }

    "setUInt / getUInt" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 4 - 1), Arb.uInt()) { i, value ->
            memory.setUInt(i, value)
            memory.getUInt(i) shouldBe value
        }
    }

    "setLong / getLong" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 8 - 1), Arb.long()) { i, value ->
            memory.setLong(i, value)
            memory.getLong(i) shouldBe value
        }
    }

    "setULong / getULong" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 8 - 1), Arb.uLong()) { i, value ->
            memory.setULong(i, value)
            memory.getULong(i) shouldBe value
        }
    }

    "setFloat / getFloat" {
        val memory = GlobalMemory()
        memory.grow(10)

        val floats = arrayOf(
            0.0f,
            10.0f,
            42.0f,
            100.0f,
            1000.0f,
            10000.0f,
            100000.0f,
            1000000.0f,
        )

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 4 - 1), Arb.of(*floats)) { i, value ->
            memory.setFloat(i, value)
            memory.getFloat(i) shouldBe Float.fromBits(value.toBits())
        }
    }

    "setDouble / getDouble" {
        val memory = GlobalMemory()
        memory.grow(10)

        val doubles = arrayOf(
            0.0,
            10.0,
            42.0,
            100.0,
            1000.0,
            10000.0,
            100000.0,
            1000000.0,
        )
        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 8 - 1), Arb.of(*doubles)) { i, value ->
            memory.setDouble(i, value)
            memory.getDouble(i) shouldBe Double.fromBits(value.toBits())
        }
    }

    "setBoolean / getBoolean" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 1), Arb.boolean()) { i, value ->
            memory.setBoolean(i, value)
            memory.getBoolean(i) shouldBe value
        }
    }

    "setChar / getChar" {
        val memory = GlobalMemory()
        memory.grow(10)

        checkAll(1000, Arb.long(0, 1024 * 16 * 10 - 2 - 1), Arb.char()) { i, value ->
            memory.setChar(i, value)
            memory.getChar(i) shouldBe value
        }
    }
})
