package com.shakelang.shake.util.primitives.bytes

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class PrimitiveTests : FreeSpec({

    "byte to bytes" {
        val b = 0xFFu.toByte()
        val bytes = b.toBytes()
        bytes.size shouldBe 1
        bytes[0] shouldBe b
    }

    "short to bytes" {
        val s = 0xFFFEu.toShort()
        val bytes = s.toBytes()
        bytes.size shouldBe 2
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xFEu.toByte()
    }

    "int to bytes" {
        val i = 0xFFFEFDFCu
        val bytes = i.toBytes()
        bytes.size shouldBe 4
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xFEu.toByte()
        bytes[2] shouldBe 0xFDu.toByte()
        bytes[3] shouldBe 0xFCu.toByte()
    }

    "long to bytes" {
        val l = 0x0FFEFDFCFBF9F8F7L
        val bytes = l.toBytes()
        bytes.size shouldBe 8
        bytes[0] shouldBe 0x0Fu.toByte()
        bytes[1] shouldBe 0xFEu.toByte()
        bytes[2] shouldBe 0xFDu.toByte()
        bytes[3] shouldBe 0xFCu.toByte()
        bytes[4] shouldBe 0xFBu.toByte()
        bytes[5] shouldBe 0xF9u.toByte()
        bytes[6] shouldBe 0xF8u.toByte()
        bytes[7] shouldBe 0xF7u.toByte()
    }

    "float to bytes" {
        val f = Float.fromBits(0x3f333333)
        val bytes = f.toBytes()
        bytes.size shouldBe 4
        bytes[0] shouldBe 0x3fu.toByte()
        bytes[1] shouldBe 0x33u.toByte()
        bytes[2] shouldBe 0x33u.toByte()
        bytes[3] shouldBe 0x33u.toByte()
    }

    "double to bytes" {
        val d = Double.fromBits(0x3ff3333333333333)
        val bytes = d.toBytes()
        bytes.size shouldBe 8
        bytes[0] shouldBe 0x3fu.toByte()
        bytes[1] shouldBe 0xf3u.toByte()
        bytes[2] shouldBe 0x33u.toByte()
        bytes[3] shouldBe 0x33u.toByte()
        bytes[4] shouldBe 0x33u.toByte()
        bytes[5] shouldBe 0x33u.toByte()
        bytes[6] shouldBe 0x33u.toByte()
        bytes[7] shouldBe 0x33u.toByte()
    }

    "ubyte to bytes" {
        val b: UByte = 0xFFu
        val bytes = b.toBytes()
        bytes.size shouldBe 1
        bytes[0] shouldBe 0xFFu.toByte()
    }

    "ushort to bytes" {
        val s: UShort = 0xFFAAu
        val bytes = s.toBytes()
        bytes.size shouldBe 2
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xAAu.toByte()
    }

    "uint to bytes" {
        val i = 0xFFAABBCCu
        val bytes = i.toBytes()
        bytes.size shouldBe 4
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xAAu.toByte()
        bytes[2] shouldBe 0xBBu.toByte()
        bytes[3] shouldBe 0xCCu.toByte()
    }

    "ulong to bytes" {
        val l = 0xFFAABBCCDDEE0011uL
        val bytes = l.toBytes()
        bytes.size shouldBe 8
        bytes[0] shouldBe 0xFFu.toByte()
        bytes[1] shouldBe 0xAAu.toByte()
        bytes[2] shouldBe 0xBBu.toByte()
        bytes[3] shouldBe 0xCCu.toByte()
        bytes[4] shouldBe 0xDDu.toByte()
        bytes[5] shouldBe 0xEEu.toByte()
        bytes[6] shouldBe 0x00u.toByte()
        bytes[7] shouldBe 0x11u.toByte()
    }

    "byteOf" {
        val b = byteOf(1)
        b shouldBe 1
    }

    "shortOf" {
        val s = shortOf(1, 2)
        s shouldBe 0x0102
    }

    "intOf" {
        val i = intOf(1, 2, 3, 4)
        i shouldBe 0x01020304
    }

    "longOf" {
        val l = longOf(1, 2, 3, 4, 5, 6, 7, 8)
        l shouldBe 0x0102030405060708
    }

    "floatOf" {
        val f = floatOf(1, 2, 3, 4)
        f.toBits() shouldBe 0x01020304
    }

    "doubleOf" {
        val d = doubleOf(1, 2, 3, 4, 5, 6, 7, 8)
        d.toBits() shouldBe 0x0102030405060708
    }

    "ubyteOf" {
        val b = ubyteOf(1)
        b shouldBe 1u
    }

    "ushortOf" {
        val s = ushortOf(1, 2)
        s shouldBe 0x0102u
    }

    "uintOf" {
        val i = uintOf(1, 2, 3, 4)
        i shouldBe 0x01020304u
    }

    "ulongOf" {
        val l = ulongOf(1, 2, 3, 4, 5, 6, 7, 8)
        l shouldBe 0x0102030405060708uL
    }

    "unsignedByteOf" {
        val b = unsignedByteOf(1)
        b shouldBe 1u
    }

    "unsignedShortOf" {
        val s = unsignedShortOf(1, 2)
        s shouldBe 0x0102u
    }

    "unsignedIntOf" {
        val i = unsignedIntOf(1, 2, 3, 4)
        i shouldBe 0x01020304u
    }

    "unsignedLongOf" {
        val l = unsignedLongOf(1, 2, 3, 4, 5, 6, 7, 8)
        l shouldBe 0x0102030405060708uL
    }
})
