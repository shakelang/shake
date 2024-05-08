package com.shakelang.util.primitives.bytes

import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class PrimitiveTests : FlatTestSpec({

    describe("to bytes") {

        describe("byte") {

            it("byte to bytes") {
                val b = 0xFFu.toByte()
                val bytes = b.toBytes()
                bytes.size shouldBe 1
                bytes[0] shouldBe b
            }
        }

        describe("short") {

            it("toBytesBE()") {
                val s = 0xFFFEu.toShort()
                val bytes = s.toBytesBE()
                bytes.size shouldBe 2
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xFEu.toByte()
            }

            it("toBytesLE()") {
                val s = 0xFFFEu.toShort()
                val bytes = s.toBytesLE()
                bytes.size shouldBe 2
                bytes[0] shouldBe 0xFEu.toByte()
                bytes[1] shouldBe 0xFFu.toByte()
            }

            it("toBytes()") {
                val s = 0xFFFEu.toShort()
                val bytes = s.toBytes()
                bytes.size shouldBe 2
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xFEu.toByte()
            }
        }

        describe("int") {

            it("toBytesBE()") {
                val i = 0xFFFEFDFCu.toInt()
                val bytes = i.toBytesBE()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xFEu.toByte()
                bytes[2] shouldBe 0xFDu.toByte()
                bytes[3] shouldBe 0xFCu.toByte()
            }

            it("toBytesLE()") {
                val i = 0xFFFEFDFCu.toInt()
                val bytes = i.toBytesLE()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0xFCu.toByte()
                bytes[1] shouldBe 0xFDu.toByte()
                bytes[2] shouldBe 0xFEu.toByte()
                bytes[3] shouldBe 0xFFu.toByte()
            }

            it("toBytes()") {
                val i = 0xFFFEFDFCu.toInt()
                val bytes = i.toBytes()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xFEu.toByte()
                bytes[2] shouldBe 0xFDu.toByte()
                bytes[3] shouldBe 0xFCu.toByte()
            }
        }

        describe("long") {

            it("toBytesBE()") {
                val l = 0xFFFEFDFCFBFAF9F8uL.toLong()
                val bytes = l.toBytesBE()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xFEu.toByte()
                bytes[2] shouldBe 0xFDu.toByte()
                bytes[3] shouldBe 0xFCu.toByte()
                bytes[4] shouldBe 0xFBu.toByte()
                bytes[5] shouldBe 0xFAu.toByte()
                bytes[6] shouldBe 0xF9u.toByte()
                bytes[7] shouldBe 0xF8u.toByte()
            }

            it("toBytesLE()") {
                val l = 0xFFFEFDFCFBFAF9F8uL.toLong()
                val bytes = l.toBytesLE()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0xF8u.toByte()
                bytes[1] shouldBe 0xF9u.toByte()
                bytes[2] shouldBe 0xFAu.toByte()
                bytes[3] shouldBe 0xFBu.toByte()
                bytes[4] shouldBe 0xFCu.toByte()
                bytes[5] shouldBe 0xFDu.toByte()
                bytes[6] shouldBe 0xFEu.toByte()
                bytes[7] shouldBe 0xFFu.toByte()
            }

            it("toBytes()") {
                val l = 0xFFFEFDFCFBFAF9F8uL.toLong()
                val bytes = l.toBytes()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xFEu.toByte()
                bytes[2] shouldBe 0xFDu.toByte()
                bytes[3] shouldBe 0xFCu.toByte()
                bytes[4] shouldBe 0xFBu.toByte()
                bytes[5] shouldBe 0xFAu.toByte()
                bytes[6] shouldBe 0xF9u.toByte()
                bytes[7] shouldBe 0xF8u.toByte()
            }
        }

        describe("float") {

            it("toBytesBE()") {
                val f = Float.fromBits(0x3f800000)
                val bytes = f.toBytesBE()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0x3fu.toByte()
                bytes[1] shouldBe 0x80u.toByte()
                bytes[2] shouldBe 0x00u.toByte()
                bytes[3] shouldBe 0x00u.toByte()
            }

            it("toBytesLE()") {
                val f = Float.fromBits(0x3f800000)
                val bytes = f.toBytesLE()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0x00u.toByte()
                bytes[1] shouldBe 0x00u.toByte()
                bytes[2] shouldBe 0x80u.toByte()
                bytes[3] shouldBe 0x3fu.toByte()
            }

            it("toBytes()") {
                val f = Float.fromBits(0x3f800000)
                val bytes = f.toBytes()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0x3fu.toByte()
                bytes[1] shouldBe 0x80u.toByte()
                bytes[2] shouldBe 0x00u.toByte()
                bytes[3] shouldBe 0x00u.toByte()
            }
        }

        describe("double") {

            it("toBytesBE()") {
                val d = Double.fromBits(0x3ff0000000000000)
                val bytes = d.toBytesBE()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0x3fu.toByte()
                bytes[1] shouldBe 0xf0u.toByte()
                bytes[2] shouldBe 0x00u.toByte()
                bytes[3] shouldBe 0x00u.toByte()
                bytes[4] shouldBe 0x00u.toByte()
                bytes[5] shouldBe 0x00u.toByte()
                bytes[6] shouldBe 0x00u.toByte()
                bytes[7] shouldBe 0x00u.toByte()
            }

            it("toBytesLE()") {
                val d = Double.fromBits(0x3ff0000000000000)
                val bytes = d.toBytesLE()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0x00u.toByte()
                bytes[1] shouldBe 0x00u.toByte()
                bytes[2] shouldBe 0x00u.toByte()
                bytes[3] shouldBe 0x00u.toByte()
                bytes[4] shouldBe 0x00u.toByte()
                bytes[5] shouldBe 0x00u.toByte()
                bytes[6] shouldBe 0xf0u.toByte()
                bytes[7] shouldBe 0x3fu.toByte()
            }

            it("toBytes()") {
                val d = Double.fromBits(0x3ff0000000000000)
                val bytes = d.toBytes()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0x3fu.toByte()
                bytes[1] shouldBe 0xf0u.toByte()
                bytes[2] shouldBe 0x00u.toByte()
                bytes[3] shouldBe 0x00u.toByte()
                bytes[4] shouldBe 0x00u.toByte()
                bytes[5] shouldBe 0x00u.toByte()
                bytes[6] shouldBe 0x00u.toByte()
                bytes[7] shouldBe 0x00u.toByte()
            }
        }

        describe("ubyte") {

            it("ubyte to bytes") {
                val b: UByte = 0xFFu
                val bytes = b.toBytes()
                bytes.size shouldBe 1
                bytes[0] shouldBe b.toByte()
            }
        }

        describe("ushort") {

            it("toBytesBE()") {
                val s: UShort = 0xFFAAu
                val bytes = s.toBytesBE()
                bytes.size shouldBe 2
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xAAu.toByte()
            }

            it("toBytesLE()") {
                val s: UShort = 0xFFAAu
                val bytes = s.toBytesLE()
                bytes.size shouldBe 2
                bytes[0] shouldBe 0xAAu.toByte()
                bytes[1] shouldBe 0xFFu.toByte()
            }

            it("toBytes()") {
                val s: UShort = 0xFFAAu
                val bytes = s.toBytes()
                bytes.size shouldBe 2
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xAAu.toByte()
            }
        }

        describe("uint") {

            it("toBytesBE()") {
                val i: UInt = 0xFFAABBCCu
                val bytes = i.toBytesBE()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xAAu.toByte()
                bytes[2] shouldBe 0xBBu.toByte()
                bytes[3] shouldBe 0xCCu.toByte()
            }

            it("toBytesLE()") {
                val i: UInt = 0xFFAABBCCu
                val bytes = i.toBytesLE()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0xCCu.toByte()
                bytes[1] shouldBe 0xBBu.toByte()
                bytes[2] shouldBe 0xAAu.toByte()
                bytes[3] shouldBe 0xFFu.toByte()
            }

            it("toBytes()") {
                val i: UInt = 0xFFAABBCCu
                val bytes = i.toBytes()
                bytes.size shouldBe 4
                bytes[0] shouldBe 0xFFu.toByte()
                bytes[1] shouldBe 0xAAu.toByte()
                bytes[2] shouldBe 0xBBu.toByte()
                bytes[3] shouldBe 0xCCu.toByte()
            }
        }

        describe("ulong") {

            it("toBytesBE()") {
                val l: ULong = 0xFFAABBCCDDEE0011uL
                val bytes = l.toBytesBE()
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

            it("toBytesLE()") {
                val l: ULong = 0xFFAABBCCDDEE0011uL
                val bytes = l.toBytesLE()
                bytes.size shouldBe 8
                bytes[0] shouldBe 0x11u.toByte()
                bytes[1] shouldBe 0x00u.toByte()
                bytes[2] shouldBe 0xEEu.toByte()
                bytes[3] shouldBe 0xDDu.toByte()
                bytes[4] shouldBe 0xCCu.toByte()
                bytes[5] shouldBe 0xBBu.toByte()
                bytes[6] shouldBe 0xAAu.toByte()
                bytes[7] shouldBe 0xFFu.toByte()
            }

            it("toBytes()") {
                val l: ULong = 0xFFAABBCCDDEE0011uL
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
        }
    }

    it("byteOf") {
        val b = byteOf(1)
        b shouldBe 1
    }

    it("shortOf") {
        val s = shortOf(1, 2)
        s shouldBe 0x0102
    }

    it("intOf") {
        val i = intOf(1, 2, 3, 4)
        i shouldBe 0x01020304
    }

    it("longOf") {
        val l = longOf(1, 2, 3, 4, 5, 6, 7, 8)
        l shouldBe 0x0102030405060708
    }

    it("floatOf") {
        val f = floatOf(1, 2, 3, 4)
        f.toBits() shouldBe 0x01020304
    }

    it("doubleOf") {
        val d = doubleOf(1, 2, 3, 4, 5, 6, 7, 8)
        d.toBits() shouldBe 0x0102030405060708
    }

    it("ubyteOf") {
        val b = ubyteOf(1)
        b shouldBe 1u
    }

    it("ushortOf") {
        val s = ushortOf(1, 2)
        s shouldBe 0x0102u
    }

    it("uintOf") {
        val i = uintOf(1, 2, 3, 4)
        i shouldBe 0x01020304u
    }

    it("ulongOf") {
        val l = ulongOf(1, 2, 3, 4, 5, 6, 7, 8)
        l shouldBe 0x0102030405060708uL
    }

    it("unsignedByteOf") {
        val b = unsignedByteOf(1)
        b shouldBe 1u
    }

    it("unsignedShortOf") {
        val s = unsignedShortOf(1, 2)
        s shouldBe 0x0102u
    }

    it("unsignedIntOf") {
        val i = unsignedIntOf(1, 2, 3, 4)
        i shouldBe 0x01020304u
    }

    it("unsignedLongOf") {
        val l = unsignedLongOf(1, 2, 3, 4, 5, 6, 7, 8)
        l shouldBe 0x0102030405060708uL
    }
})
