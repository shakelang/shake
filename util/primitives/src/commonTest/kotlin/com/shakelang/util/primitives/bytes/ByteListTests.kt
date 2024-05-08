package com.shakelang.util.primitives.bytes

import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
@OptIn(ExperimentalUnsignedTypes::class)
class ByteListTests : FlatTestSpec({

    describe("to") {

        describe("toByte()") {

            it("should convert a byte of size 1 to a byte") {
                val b = byteArrayOf(1.toByte()).toList()
                b.toByte() shouldBe 1.toByte()
            }

            it("should throw an exception if the byte array is > 1") {
                val b = byteArrayOf(1.toByte(), 2.toByte()).toList()
                runCatching { b.toByte() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 1") {
                val b = byteArrayOf().toList()
                runCatching { b.toByte() }.isFailure shouldBe true
            }
        }

        describe("toUByte()") {

            it("should convert a byte of size 1 to a ubyte") {
                val b = byteArrayOf(1.toByte()).toList()
                b.toUByte() shouldBe 1.toUByte()
            }

            it("should throw an exception if the byte array is > 1") {
                val b = byteArrayOf(1.toByte(), 2.toByte()).toList()
                runCatching { b.toUByte() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 1") {
                val b = byteArrayOf().toList()
                runCatching { b.toUByte() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedByte()") {
            it("should convert a byte of size 1 to a ubyte") {
                val b = byteArrayOf(1.toByte()).toList()
                b.toUnsignedByte() shouldBe 1.toUByte()
            }

            it("should throw an exception if the byte array is > 1") {
                val b = byteArrayOf(1.toByte(), 2.toByte()).toList()
                runCatching { b.toUnsignedByte() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 1") {
                val b = byteArrayOf().toList()
                runCatching { b.toUnsignedByte() }.isFailure shouldBe true
            }
        }

        describe("toShortBE()") {

            it("should convert a byte of size 2 to a short") {
                val b = byteArrayOf(0x01.toByte(), 2).toList()
                b.toShortBE() shouldBe 0x0102.toShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toShortBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toShortBE() }.isFailure shouldBe true
            }
        }

        describe("toShortLE()") {

            it("should convert a byte of size 2 to a short") {
                val b = byteArrayOf(0x02.toByte(), 1).toList()
                b.toShortLE() shouldBe 0x0102.toShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toShortLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toShortLE() }.isFailure shouldBe true
            }
        }

        describe("toShort()") {

            it("should convert a byte of size 2 to a short") {
                val b = byteArrayOf(0x01.toByte(), 2).toList()
                b.toShort() shouldBe 0x0102.toShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toShort() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toShort() }.isFailure shouldBe true
            }
        }

        describe("toUShortBE()") {

            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2).toList()
                b.toUShortBE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUShortBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toUShortBE() }.isFailure shouldBe true
            }
        }

        describe("toUShortLE()") {

            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x02.toByte(), 1).toList()
                b.toUShortLE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUShortLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toUShortLE() }.isFailure shouldBe true
            }
        }

        describe("toUShort()") {

            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2).toList()
                b.toUShort() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUShort() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toUShort() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedShortBE()") {
            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2).toList()
                b.toUnsignedShortBE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUnsignedShortBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toUnsignedShortBE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedShortLE()") {
            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x02.toByte(), 1).toList()
                b.toUnsignedShortLE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUnsignedShortLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toUnsignedShortLE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedShort()") {
            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2).toList()
                b.toUnsignedShort() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUnsignedShort() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte()).toList()
                runCatching { b.toUnsignedShort() }.isFailure shouldBe true
            }
        }

        describe("toIntBE()") {

            it("should convert a byte of size 4 to a int") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4).toList()
                b.toIntBE() shouldBe 0x01020304
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toIntBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toIntBE() }.isFailure shouldBe true
            }
        }

        describe("toIntLE()") {

            it("should convert a byte of size 4 to a int") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1).toList()
                b.toIntLE() shouldBe 0x01020304
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toIntLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toIntLE() }.isFailure shouldBe true
            }
        }

        describe("toInt()") {

            it("should convert a byte of size 4 to a int") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4).toList()
                b.toInt() shouldBe 0x01020304
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toInt() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toInt() }.isFailure shouldBe true
            }
        }

        describe("toUIntBE()") {

            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4).toList()
                b.toUIntBE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toUIntBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUIntBE() }.isFailure shouldBe true
            }
        }

        describe("toUIntLE()") {

            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1).toList()
                b.toUIntLE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toUIntLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUIntLE() }.isFailure shouldBe true
            }
        }

        describe("toUInt()") {

            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4).toList()
                b.toUInt() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toUInt() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUInt() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedIntBE()") {
            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4).toList()
                b.toUnsignedIntBE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toUnsignedIntBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUnsignedIntBE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedIntLE()") {
            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1).toList()
                b.toUnsignedIntLE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toUnsignedIntLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUnsignedIntLE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedInt()") {
            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4).toList()
                b.toUnsignedInt() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toUnsignedInt() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toUnsignedInt() }.isFailure shouldBe true
            }
        }

        describe("toLongBE()") {

            it("should convert a byte of size 8 to a long") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.toLongBE() shouldBe 0x0102030405060708
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toLongBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toLongBE() }.isFailure shouldBe true
            }
        }

        describe("toLongLE()") {

            it("should convert a byte of size 8 to a long") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1).toList()
                b.toLongLE() shouldBe 0x0102030405060708
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toLongLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toLongLE() }.isFailure shouldBe true
            }
        }

        describe("toLong()") {

            it("should convert a byte of size 8 to a long") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.toLong() shouldBe 0x0102030405060708
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toLong() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toLong() }.isFailure shouldBe true
            }
        }

        describe("toULongBE()") {

            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.toULongBE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toULongBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toULongBE() }.isFailure shouldBe true
            }
        }

        describe("toULongLE()") {

            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1).toList()
                b.toULongLE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toULongLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toULongLE() }.isFailure shouldBe true
            }
        }

        describe("toULong()") {

            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.toULong() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toULong() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toULong() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedLongBE()") {
            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.toUnsignedLongBE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toUnsignedLongBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toUnsignedLongBE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedLongLE()") {
            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1).toList()
                b.toUnsignedLongLE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toUnsignedLongLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toUnsignedLongLE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedLong()") {
            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.toUnsignedLong() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toUnsignedLong() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toUnsignedLong() }.isFailure shouldBe true
            }
        }

        describe("toFloatBE()") {

            it("should convert a byte of size 4 to a float") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte()).toList()
                assertCompare(1.0f, b.toFloatBE())
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toFloatBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toFloatBE() }.isFailure shouldBe true
            }
        }

        describe("toFloatLE()") {

            it("should convert a byte of size 4 to a float") {
                val b = byteArrayOf(0x00.toByte(), 0x00.toByte(), 0x80.toByte(), 0x3F.toByte()).toList()
                assertCompare(1.0f, b.toFloatLE())
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toFloatLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toFloatLE() }.isFailure shouldBe true
            }
        }

        describe("toFloat()") {

            it("should convert a byte of size 4 to a float") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte()).toList()
                assertCompare(1.0f, b.toFloat())
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5).toList()
                runCatching { b.toFloat() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3).toList()
                runCatching { b.toFloat() }.isFailure shouldBe true
            }
        }

        describe("toDoubleBE()") {
            it("should convert a byte of size 8 to a double") {
                val b = byteArrayOf(0x3F.toByte(), 0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()).toList()
                assertCompare(1.0, b.toDoubleBE())
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toDoubleBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toDoubleBE() }.isFailure shouldBe true
            }
        }

        describe("toDoubleLE()") {
            it("should convert a byte of size 8 to a double") {
                val b = byteArrayOf(0, 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toList()
                assertCompare(1.0, b.toDoubleLE())
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toDoubleLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toDoubleLE() }.isFailure shouldBe true
            }
        }

        describe("toDouble()") {
            it("should convert a byte of size 8 to a double") {
                val b = byteArrayOf(0x3F.toByte(), 0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()).toList()
                assertCompare(1.0, b.toDouble())
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9).toList()
                runCatching { b.toDouble() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7).toList()
                runCatching { b.toDouble() }.isFailure shouldBe true
            }
        }

        describe("toHexString()") {
            it("should convert a byte array to a hex string") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8, 0x2A.toByte()).toList()
                b.toHexString() shouldBe "01020304050607082a"
            }
        }

        describe("toUtf8String()") {
            it("should convert a byte array to a utf8 string") {
                val b = "Hello World\u0009".toCharArray().map { it.code.toByte() }.toByteArray().toList()
                b.toUtf8String() shouldBe "Hello World\u0009"
            }
        }
    }

    describe("get") {
        describe("getByte") {
            it("should get the byte at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getByte(0) shouldBe 1.toByte()
                b.getByte(1) shouldBe 2.toByte()
                b.getByte(2) shouldBe 3.toByte()
                b.getByte(3) shouldBe 4.toByte()
                b.getByte(4) shouldBe 5.toByte()
                b.getByte(5) shouldBe 6.toByte()
                b.getByte(6) shouldBe 7.toByte()
                b.getByte(7) shouldBe 8.toByte()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getByte(8) }.isFailure shouldBe true
                runCatching { b.getByte(-1) }.isFailure shouldBe true
            }
        }

        describe("getUByte") {
            it("should get the ubyte at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUByte(0) shouldBe 1.toUByte()
                b.getUByte(1) shouldBe 2.toUByte()
                b.getUByte(2) shouldBe 3.toUByte()
                b.getUByte(3) shouldBe 4.toUByte()
                b.getUByte(4) shouldBe 5.toUByte()
                b.getUByte(5) shouldBe 6.toUByte()
                b.getUByte(6) shouldBe 7.toUByte()
                b.getUByte(7) shouldBe 8.toUByte()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUByte(8) }.isFailure shouldBe true
                runCatching { b.getUByte(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedByte") {
            it("should get the ubyte at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedByte(0) shouldBe 1.toUByte()
                b.getUnsignedByte(1) shouldBe 2.toUByte()
                b.getUnsignedByte(2) shouldBe 3.toUByte()
                b.getUnsignedByte(3) shouldBe 4.toUByte()
                b.getUnsignedByte(4) shouldBe 5.toUByte()
                b.getUnsignedByte(5) shouldBe 6.toUByte()
                b.getUnsignedByte(6) shouldBe 7.toUByte()
                b.getUnsignedByte(7) shouldBe 8.toUByte()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedByte(8) }.isFailure shouldBe true
                runCatching { b.getUnsignedByte(-1) }.isFailure shouldBe true
            }
        }

        describe("getShortBE") {
            it("should get the short at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getShortBE(0) shouldBe 0x0102.toShort()
                b.getShortBE(1) shouldBe 0x0203.toShort()
                b.getShortBE(4) shouldBe 0x0506.toShort()
                b.getShortBE(5) shouldBe 0x0607.toShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getShortBE(7) }.isFailure shouldBe true
                runCatching { b.getShortBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getShortLE") {
            it("should get the short at the given index") {
                val b = byteArrayOf(0x02.toByte(), 1, 4, 3, 6, 5, 8, 7).toList()
                b.getShortLE(0) shouldBe 0x0102.toShort()
                b.getShortLE(1) shouldBe 0x0401.toShort()
                b.getShortLE(4) shouldBe 0x0506.toShort()
                b.getShortLE(5) shouldBe 0x0805.toShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getShortLE(7) }.isFailure shouldBe true
                runCatching { b.getShortLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getShort") {
            it("should get the short at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getShort(0) shouldBe 0x0102.toShort()
                b.getShort(1) shouldBe 0x0203.toShort()
                b.getShort(4) shouldBe 0x0506.toShort()
                b.getShort(5) shouldBe 0x0607.toShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getShort(7) }.isFailure shouldBe true
                runCatching { b.getShort(-1) }.isFailure shouldBe true
            }
        }

        describe("getUShortBE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUShortBE(0) shouldBe 0x0102.toUShort()
                b.getUShortBE(1) shouldBe 0x0203.toUShort()
                b.getUShortBE(4) shouldBe 0x0506.toUShort()
                b.getUShortBE(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUShortBE(7) }.isFailure shouldBe true
                runCatching { b.getUShortBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUShortLE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x02.toByte(), 1, 4, 3, 6, 5, 8, 7).toList()
                b.getUShortLE(0) shouldBe 0x0102.toUShort()
                b.getUShortLE(1) shouldBe 0x0401.toUShort()
                b.getUShortLE(4) shouldBe 0x0506.toUShort()
                b.getUShortLE(5) shouldBe 0x0805.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUShortLE(7) }.isFailure shouldBe true
                runCatching { b.getUShortLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUShort") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUShort(0) shouldBe 0x0102.toUShort()
                b.getUShort(1) shouldBe 0x0203.toUShort()
                b.getUShort(4) shouldBe 0x0506.toUShort()
                b.getUShort(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUShort(7) }.isFailure shouldBe true
                runCatching { b.getUShort(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedShortBE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedShortBE(0) shouldBe 0x0102.toUShort()
                b.getUnsignedShortBE(1) shouldBe 0x0203.toUShort()
                b.getUnsignedShortBE(4) shouldBe 0x0506.toUShort()
                b.getUnsignedShortBE(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedShortBE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedShortBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedShortLE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x02.toByte(), 1, 4, 3, 6, 5, 8, 7).toList()
                b.getUnsignedShortLE(0) shouldBe 0x0102.toUShort()
                b.getUnsignedShortLE(1) shouldBe 0x0401.toUShort()
                b.getUnsignedShortLE(4) shouldBe 0x0506.toUShort()
                b.getUnsignedShortLE(5) shouldBe 0x0805.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedShortLE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedShortLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedShort") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedShort(0) shouldBe 0x0102.toUShort()
                b.getUnsignedShort(1) shouldBe 0x0203.toUShort()
                b.getUnsignedShort(4) shouldBe 0x0506.toUShort()
                b.getUnsignedShort(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedShort(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedShort(-1) }.isFailure shouldBe true
            }
        }

        describe("getIntBE") {
            it("should get the int at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getIntBE(0) shouldBe 0x01020304
                b.getIntBE(1) shouldBe 0x02030405
                b.getIntBE(4) shouldBe 0x05060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getIntBE(7) }.isFailure shouldBe true
                runCatching { b.getIntBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getIntLE") {
            it("should get the int at the given index") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1, 8, 7, 6, 5).toList()
                b.getIntLE(0) shouldBe 0x01020304
                b.getIntLE(1) shouldBe 0x08010203
                b.getIntLE(4) shouldBe 0x05060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getIntLE(7) }.isFailure shouldBe true
                runCatching { b.getIntLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getInt") {
            it("should get the int at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getInt(0) shouldBe 0x01020304
                b.getInt(1) shouldBe 0x02030405
                b.getInt(4) shouldBe 0x05060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getInt(7) }.isFailure shouldBe true
                runCatching { b.getInt(-1) }.isFailure shouldBe true
            }
        }

        describe("getUIntBE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUIntBE(0) shouldBe 0x01020304u
                b.getUIntBE(1) shouldBe 0x02030405u
                b.getUIntBE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUIntBE(7) }.isFailure shouldBe true
                runCatching { b.getUIntBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUIntLE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1, 8, 7, 6, 5).toList()
                b.getUIntLE(0) shouldBe 0x01020304u
                b.getUIntLE(1) shouldBe 0x08010203u
                b.getUIntLE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUIntLE(7) }.isFailure shouldBe true
                runCatching { b.getUIntLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUInt") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUInt(0) shouldBe 0x01020304u
                b.getUInt(1) shouldBe 0x02030405u
                b.getUInt(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUInt(7) }.isFailure shouldBe true
                runCatching { b.getUInt(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedIntBE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedIntBE(0) shouldBe 0x01020304u
                b.getUnsignedIntBE(1) shouldBe 0x02030405u
                b.getUnsignedIntBE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedIntBE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedIntBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedIntLE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1, 8, 7, 6, 5).toList()
                b.getUnsignedIntLE(0) shouldBe 0x01020304u
                b.getUnsignedIntLE(1) shouldBe 0x08010203u
                b.getUnsignedIntLE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedIntLE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedIntLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedInt") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedInt(0) shouldBe 0x01020304u
                b.getUnsignedInt(1) shouldBe 0x02030405u
                b.getUnsignedInt(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedInt(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedInt(-1) }.isFailure shouldBe true
            }
        }

        describe("getLongBE") {
            it("should get the long at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getLongBE(0) shouldBe 0x0102030405060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getLongBE(7) }.isFailure shouldBe true
                runCatching { b.getLongBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getLongLE") {
            it("should get the long at the given index") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1).toList()
                b.getLongLE(0) shouldBe 0x0102030405060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getLongLE(7) }.isFailure shouldBe true
                runCatching { b.getLongLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getLong") {
            it("should get the long at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getLong(0) shouldBe 0x0102030405060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getLong(7) }.isFailure shouldBe true
                runCatching { b.getLong(-1) }.isFailure shouldBe true
            }
        }

        describe("getULongBE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getULongBE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getULongBE(7) }.isFailure shouldBe true
                runCatching { b.getULongBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getULongLE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1).toList()
                b.getULongLE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getULongLE(7) }.isFailure shouldBe true
                runCatching { b.getULongLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getULong") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getULong(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getULong(7) }.isFailure shouldBe true
                runCatching { b.getULong(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedLongBE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedLongBE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedLongBE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedLongBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedLongLE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1).toList()
                b.getUnsignedLongLE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedLongLE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedLongLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedLong") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getUnsignedLong(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getUnsignedLong(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedLong(-1) }.isFailure shouldBe true
            }
        }

        describe("getFloatBE") {
            it("should get the float at the given index") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte()).toList()
                assertCompare(1.0f, b.getFloatBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4).toList()
                runCatching { b.getFloatBE(4) }.isFailure shouldBe true
                runCatching { b.getFloatBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getFloatLE") {
            it("should get the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0x00.toByte(), 0x80.toByte(), 0x3F.toByte()).toList()
                assertCompare(1.0f, b.getFloatLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4).toList()
                runCatching { b.getFloatLE(4) }.isFailure shouldBe true
                runCatching { b.getFloatLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getFloat") {
            it("should get the float at the given index") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte()).toList()
                assertCompare(1.0f, b.getFloat(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4).toList()
                runCatching { b.getFloat(4) }.isFailure shouldBe true
                runCatching { b.getFloat(-1) }.isFailure shouldBe true
            }
        }

        describe("getDoubleBE") {
            it("should get the double at the given index") {
                val b = byteArrayOf(
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
                assertCompare(1.0, b.getDoubleBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getDoubleBE(8) }.isFailure shouldBe true
                runCatching { b.getDoubleBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getDoubleLE") {
            it("should get the double at the given index") {
                val b = byteArrayOf(
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0xF0.toByte(),
                    0x3F.toByte(),
                ).toList()
                assertCompare(1.0, b.getDoubleLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getDoubleLE(8) }.isFailure shouldBe true
                runCatching { b.getDoubleLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getDouble") {
            it("should get the double at the given index") {
                val b = byteArrayOf(
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                ).toList()
                assertCompare(1.0, b.getDouble(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getDouble(8) }.isFailure shouldBe true
                runCatching { b.getDouble(-1) }.isFailure shouldBe true
            }
        }

        describe("getBytesBE") {
            it("should get the bytes at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getBytesBE(0, 4) shouldBe byteArrayOf(1.toByte(), 2, 3, 4)
                b.getBytesBE(1, 4) shouldBe byteArrayOf(2.toByte(), 3, 4, 5)
                b.getBytesBE(4, 4) shouldBe byteArrayOf(5.toByte(), 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getBytesBE(8, 1) }.isFailure shouldBe true
                runCatching { b.getBytesBE(-1, 1) }.isFailure shouldBe true
            }
        }

        describe("getBytesLE") {
            it("should get the bytes at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getBytesLE(0, 4) shouldBe byteArrayOf(4.toByte(), 3, 2, 1)
                b.getBytesLE(1, 4) shouldBe byteArrayOf(5.toByte(), 4, 3, 2)
                b.getBytesLE(4, 4) shouldBe byteArrayOf(8.toByte(), 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getBytesLE(8, 1) }.isFailure shouldBe true
                runCatching { b.getBytesLE(-1, 1) }.isFailure shouldBe true
            }
        }

        describe("getBytes") {
            it("should get the bytes at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                b.getBytes(0, 4) shouldBe byteArrayOf(1.toByte(), 2, 3, 4)
                b.getBytes(1, 4) shouldBe byteArrayOf(2.toByte(), 3, 4, 5)
                b.getBytes(4, 4) shouldBe byteArrayOf(5.toByte(), 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toList()
                runCatching { b.getBytes(8, 1) }.isFailure shouldBe true
                runCatching { b.getBytes(-1, 1) }.isFailure shouldBe true
            }
        }
    }

    describe("set") {
        describe("setByte") {
            it("should set the byte at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setByte(0, 1.toByte())
                b.setByte(1, 2.toByte())
                b.setByte(2, 3.toByte())
                b.setByte(3, 4.toByte())
                b.setByte(4, 5.toByte())
                b.setByte(5, 6.toByte())
                b.setByte(6, 7.toByte())
                b.setByte(7, 8.toByte())
                b shouldBe byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setByte(8, 1.toByte()) }.isFailure shouldBe true
                runCatching { b.setByte(-1, 1.toByte()) }.isFailure shouldBe true
            }
        }

        describe("setUByte") {
            it("should set the ubyte at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUByte(0, 1.toUByte())
                b.setUByte(1, 2.toUByte())
                b.setUByte(2, 3.toUByte())
                b.setUByte(3, 4.toUByte())
                b.setUByte(4, 5.toUByte())
                b.setUByte(5, 6.toUByte())
                b.setUByte(6, 7.toUByte())
                b.setUByte(7, 8.toUByte())
                b shouldBe byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUByte(8, 1.toUByte()) }.isFailure shouldBe true
                runCatching { b.setUByte(-1, 1.toUByte()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedByte") {
            it("should set the ubyte at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedByte(0, 1.toUByte())
                b.setUnsignedByte(1, 2.toUByte())
                b.setUnsignedByte(2, 3.toUByte())
                b.setUnsignedByte(3, 4.toUByte())
                b.setUnsignedByte(4, 5.toUByte())
                b.setUnsignedByte(5, 6.toUByte())
                b.setUnsignedByte(6, 7.toUByte())
                b.setUnsignedByte(7, 8.toUByte())
                b shouldBe byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedByte(8, 1.toUByte()) }.isFailure shouldBe true
                runCatching { b.setUnsignedByte(-1, 1.toUByte()) }.isFailure shouldBe true
            }
        }

        describe("setShortBE") {
            it("should set the short at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setShortBE(0, 0x0102.toShort())
                b.setShortBE(1, 0x0203.toShort())
                b.setShortBE(4, 0x0506.toShort())
                b.setShortBE(5, 0x0607.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setShortBE(7, 0x0102.toShort()) }.isFailure shouldBe true
                runCatching { b.setShortBE(-1, 0x0102.toShort()) }.isFailure shouldBe true
            }
        }

        describe("setShortLE") {
            it("should set the short at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setShortLE(0, 0x0102.toShort())
                b.setShortLE(1, 0x0203.toShort())
                b.setShortLE(4, 0x0506.toShort())
                b.setShortLE(5, 0x0607.toShort())
                b shouldBe byteArrayOf(0x02.toByte(), 3, 2, 0, 6, 7, 6, 0).toMutableList()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setShortLE(7, 0x0102.toShort()) }.isFailure shouldBe true
                runCatching { b.setShortLE(-1, 0x0102.toShort()) }.isFailure shouldBe true
            }
        }

        describe("setShort") {
            it("should set the short at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setShort(0, 0x0102.toShort())
                b.setShort(1, 0x0203.toShort())
                b.setShort(4, 0x0506.toShort())
                b.setShort(5, 0x0607.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setShort(7, 0x0102.toShort()) }.isFailure shouldBe true
                runCatching { b.setShort(-1, 0x0102.toShort()) }.isFailure shouldBe true
            }
        }

        describe("setUShortBE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUShortBE(0, 0x0102.toUShort())
                b.setUShortBE(1, 0x0203.toUShort())
                b.setUShortBE(4, 0x0506.toUShort())
                b.setUShortBE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUShortBE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUShortBE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUShortLE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUShortLE(0, 0x0102.toUShort())
                b.setUShortLE(1, 0x0203.toUShort())
                b.setUShortLE(4, 0x0506.toUShort())
                b.setUShortLE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x02.toByte(), 3, 2, 0, 6, 7, 6, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUShortLE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUShortLE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUShort") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUShort(0, 0x0102.toUShort())
                b.setUShort(1, 0x0203.toUShort())
                b.setUShort(4, 0x0506.toUShort())
                b.setUShort(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUShort(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUShort(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedShortBE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedShortBE(0, 0x0102.toUShort())
                b.setUnsignedShortBE(1, 0x0203.toUShort())
                b.setUnsignedShortBE(4, 0x0506.toUShort())
                b.setUnsignedShortBE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedShortBE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUnsignedShortBE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedShortLE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedShortLE(0, 0x0102.toUShort())
                b.setUnsignedShortLE(1, 0x0203.toUShort())
                b.setUnsignedShortLE(4, 0x0506.toUShort())
                b.setUnsignedShortLE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x02.toByte(), 3, 2, 0, 6, 7, 6, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedShortLE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUnsignedShortLE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedShort") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedShort(0, 0x0102.toUShort())
                b.setUnsignedShort(1, 0x0203.toUShort())
                b.setUnsignedShort(4, 0x0506.toUShort())
                b.setUnsignedShort(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedShort(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUnsignedShort(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setIntBE") {
            it("should set the int at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setIntBE(0, 0x01020304)
                b.setIntBE(1, 0x02030405)
                b.setIntBE(4, 0x05060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setIntBE(7, 0x01020304) }.isFailure shouldBe true
                runCatching { b.setIntBE(-1, 0x01020304) }.isFailure shouldBe true
            }
        }

        describe("setIntLE") {
            it("should set the int at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setIntLE(0, 0x01020304)
                b.setIntLE(1, 0x02030405)
                b.setIntLE(4, 0x05060708)
                b shouldBe byteArrayOf(0x04.toByte(), 5, 4, 3, 8, 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setIntLE(7, 0x01020304) }.isFailure shouldBe true
                runCatching { b.setIntLE(-1, 0x01020304) }.isFailure shouldBe true
            }
        }

        describe("setInt") {
            it("should set the int at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setInt(0, 0x01020304)
                b.setInt(1, 0x02030405)
                b.setInt(4, 0x05060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setInt(7, 0x01020304) }.isFailure shouldBe true
                runCatching { b.setInt(-1, 0x01020304) }.isFailure shouldBe true
            }
        }

        describe("setUIntBE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUIntBE(0, 0x01020304u)
                b.setUIntBE(1, 0x02030405u)
                b.setUIntBE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUIntBE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUIntBE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUIntLE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUIntLE(0, 0x01020304u)
                b.setUIntLE(1, 0x02030405u)
                b.setUIntLE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x04.toByte(), 5, 4, 3, 8, 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUIntLE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUIntLE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUInt") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUInt(0, 0x01020304u)
                b.setUInt(1, 0x02030405u)
                b.setUInt(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUInt(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUInt(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedIntBE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedIntBE(0, 0x01020304u)
                b.setUnsignedIntBE(1, 0x02030405u)
                b.setUnsignedIntBE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedIntBE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUnsignedIntBE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedIntLE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedIntLE(0, 0x01020304u)
                b.setUnsignedIntLE(1, 0x02030405u)
                b.setUnsignedIntLE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x04.toByte(), 5, 4, 3, 8, 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedIntLE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUnsignedIntLE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedInt") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedInt(0, 0x01020304u)
                b.setUnsignedInt(1, 0x02030405u)
                b.setUnsignedInt(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedInt(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUnsignedInt(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setLongBE") {
            it("should set the long at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setLongBE(0, 0x0102030405060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setLongBE(7, 0x0102030405060708) }.isFailure shouldBe true
                runCatching { b.setLongBE(-1, 0x0102030405060708) }.isFailure shouldBe true
            }
        }

        describe("setLongLE") {
            it("should set the long at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setLongLE(0, 0x0102030405060708)
                b shouldBe byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setLongLE(7, 0x0102030405060708) }.isFailure shouldBe true
                runCatching { b.setLongLE(-1, 0x0102030405060708) }.isFailure shouldBe true
            }
        }

        describe("setLong") {
            it("should set the long at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setLong(0, 0x0102030405060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setLong(7, 0x0102030405060708) }.isFailure shouldBe true
                runCatching { b.setLong(-1, 0x0102030405060708) }.isFailure shouldBe true
            }
        }

        describe("setULongBE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setULongBE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setULongBE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setULongBE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setULongLE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setULongLE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setULongLE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setULongLE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setULong") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setULong(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setULong(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setULong(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedLongBE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedLongBE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedLongBE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setUnsignedLongBE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedLongLE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedLongLE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedLongLE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setUnsignedLongLE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedLong") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                b.setUnsignedLong(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0).toMutableList()
                runCatching { b.setUnsignedLong(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setUnsignedLong(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setFloatBE") {
            it("should set the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0x80.toByte(), 0x3F.toByte()).toMutableList()
                b.setFloatBE(0, 1.0f)
                assertCompare(1.0f, b.getFloatBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setFloatBE(8, 1.0f) }.isFailure shouldBe true
                runCatching { b.setFloatBE(-1, 1.0f) }.isFailure shouldBe true
            }
        }

        describe("setFloatLE") {
            it("should set the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0x3F.toByte(), 0x80.toByte(), 0, 0).toMutableList()
                b.setFloatLE(0, 1.0f)
                assertCompare(1.0f, b.getFloatLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setFloatLE(8, 1.0f) }.isFailure shouldBe true
                runCatching { b.setFloatLE(-1, 1.0f) }.isFailure shouldBe true
            }
        }

        describe("setFloat") {
            it("should set the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0x80.toByte(), 0x3F.toByte()).toMutableList()
                b.setFloat(0, 1.0f)
                assertCompare(1.0f, b.getFloat(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setFloat(8, 1.0f) }.isFailure shouldBe true
                runCatching { b.setFloat(-1, 1.0f) }.isFailure shouldBe true
            }
        }

        describe("setDoubleBE") {
            it("should set the double at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setDoubleBE(0, 1.0)
                assertCompare(1.0, b.getDoubleBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setDoubleBE(8, 1.0) }.isFailure shouldBe true
                runCatching { b.setDoubleBE(-1, 1.0) }.isFailure shouldBe true
            }
        }

        describe("setDoubleLE") {
            it("should set the double at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setDoubleLE(0, 1.0)
                assertCompare(1.0, b.getDoubleLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setDoubleLE(8, 1.0) }.isFailure shouldBe true
                runCatching { b.setDoubleLE(-1, 1.0) }.isFailure shouldBe true
            }
        }

        describe("setDouble") {
            it("should set the double at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setDouble(0, 1.0)
                assertCompare(1.0, b.getDouble(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setDouble(8, 1.0) }.isFailure shouldBe true
                runCatching { b.setDouble(-1, 1.0) }.isFailure shouldBe true
            }
        }

        describe("setBytesBE (ByteArray)") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setBytesBE(0, byteArrayOf(0x01.toByte(), 2, 3, 4))
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setBytesBE(8, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
                runCatching { b.setBytesBE(-1, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
            }
        }

        describe("setBytesLE (ByteArray)") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setBytesLE(0, byteArrayOf(0x01.toByte(), 2, 3, 4))
                b shouldBe byteArrayOf(0x04.toByte(), 3, 2, 1, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setBytesLE(8, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
                runCatching { b.setBytesLE(-1, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
            }
        }

        describe("setBytes (ByteArray)") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setBytes(0, byteArrayOf(0x01.toByte(), 2, 3, 4))
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setBytes(8, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
                runCatching { b.setBytes(-1, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
            }
        }

        describe("setBytesBE (Array)") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setBytesBE(0, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setBytesBE(8, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList()) }.isFailure shouldBe true
                runCatching { b.setBytesBE(-1, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList()) }.isFailure shouldBe true
            }
        }

        describe("setBytesLE (Array)") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setBytesLE(0, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList())
                b shouldBe byteArrayOf(0x04.toByte(), 3, 2, 1, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setBytesLE(8, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList()) }.isFailure shouldBe true
                runCatching { b.setBytesLE(-1, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList()) }.isFailure shouldBe true
            }
        }

        describe("setBytes (ByteArray)") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte()).toMutableList()
                b.setBytes(0, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8).toMutableList()
                runCatching { b.setBytes(8, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList()) }.isFailure shouldBe true
                runCatching { b.setBytes(-1, byteArrayOf(0x01.toByte(), 2, 3, 4).toMutableList()) }.isFailure shouldBe true
            }
        }
    }

    describe("append") {

        describe("append byte") {
            it("appendByte(byte)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendByte(0x03.toByte())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte())
            }

            it("append(byte)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x03.toByte())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte())
            }
        }

        describe("append short") {
            it("appendShortBE(short)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendShortBE(0x0304.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendShortLE(short)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendShortLE(0x0304.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendShort(short)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendShort(0x0304.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("append(short)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x0304.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }
        }

        describe("append int") {
            it("appendIntBE(int)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendIntBE(0x03040506)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }

            it("appendIntLE(int)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendIntLE(0x03040506)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x06.toByte(), 0x05.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendInt(int)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendInt(0x03040506)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }

            it("append(int)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x03040506)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }
        }

        describe("append long") {
            it("appendLongBE(long)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendLongBE(0x0102030405060708)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }

            it("appendLongLE(long)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendLongLE(0x0102030405060708)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x08.toByte(),
                    0x07.toByte(),
                    0x06.toByte(),
                    0x05.toByte(),
                    0x04.toByte(),
                    0x03.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                )
            }

            it("appendLong(long)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendLong(0x0102030405060708)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }

            it("append(long)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x0102030405060708)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }
        }

        describe("append float") {
            it("appendFloatBE(float)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendFloatBE(1.0f)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x3F.toByte(),
                    0x80.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
            }

            it("appendFloatLE(float)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendFloatLE(1.0f)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x80.toByte(),
                    0x3F.toByte(),
                )
            }

            it("appendFloat(float)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendFloat(1.0f)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x3F.toByte(),
                    0x80.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
            }

            it("append(float)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(1.0f)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x3F.toByte(),
                    0x80.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
            }
        }

        describe("append double") {
            it("appendDoubleBE(double)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendDoubleBE(1.0)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
            }

            it("appendDoubleLE(double)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendDoubleLE(1.0)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0xF0.toByte(),
                    0x3F.toByte(),
                )
            }

            it("appendDouble(double)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendDouble(1.0)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
            }

            it("append(double)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(1.0)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                )
            }
        }

        describe("append ubyte") {
            it("appendUByte(ubyte)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUByte(0x03u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte())
            }

            it("appendUnsignedByte(ubyte)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedByte(0x03u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte())
            }

            it("append(ubyte)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x03u.toUByte())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte())
            }
        }

        describe("append ushort") {
            it("appendUShortBE(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUShortBE(0x0304u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendUShortLE(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUShortLE(0x0304u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendUShort(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUShort(0x0304u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendUnsignedShortBE(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedShortBE(0x0304u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendUnsignedShortLE(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedShortLE(0x0304u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendUnsignedShort(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedShort(0x0304u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("append(ushort)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x0304u.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }
        }

        describe("append uint") {
            it("appendUIntBE(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUIntBE(0x03040506u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }

            it("appendUIntLE(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUIntLE(0x03040506u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x06.toByte(), 0x05.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendUInt(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUInt(0x03040506u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }

            it("appendUnsignedIntBE(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedIntBE(0x03040506u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }

            it("appendUnsignedIntLE(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedIntLE(0x03040506u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x06.toByte(), 0x05.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendUnsignedInt(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedInt(0x03040506u)
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }

            it("append(uint)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x03040506u.toUInt())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte())
            }
        }

        describe("append ulong") {

            it("appendULongBE(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendULongBE(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }

            it("appendULongLE(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendULongLE(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x08.toByte(),
                    0x07.toByte(),
                    0x06.toByte(),
                    0x05.toByte(),
                    0x04.toByte(),
                    0x03.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                )
            }

            it("appendULong(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendULong(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }

            it("appendUnsignedLongBE(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedLongBE(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }

            it("appendUnsignedLongLE(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedLongLE(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x08.toByte(),
                    0x07.toByte(),
                    0x06.toByte(),
                    0x05.toByte(),
                    0x04.toByte(),
                    0x03.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                )
            }

            it("appendUnsignedLong(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendUnsignedLong(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }

            it("append(ulong)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(0x0102030405060708u)
                b shouldBe byteArrayOf(
                    0x01.toByte(),
                    0x02.toByte(),
                    0x01.toByte(),
                    0x02.toByte(),
                    0x03.toByte(),
                    0x04.toByte(),
                    0x05.toByte(),
                    0x06.toByte(),
                    0x07.toByte(),
                    0x08.toByte(),
                )
            }
        }

        describe("append bytes") {
            it("appendBytesBE(byteArray)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendBytesBE(byteArrayOf(0x03.toByte(), 0x04.toByte()))
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendBytesLE(byteArray)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendBytesLE(byteArrayOf(0x03.toByte(), 0x04.toByte()))
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendBytes(byteArray)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendBytes(byteArrayOf(0x03.toByte(), 0x04.toByte()))
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("append(byteArray)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(byteArrayOf(0x03.toByte(), 0x04.toByte()))
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendBytesBE(list)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendBytesBE(byteArrayOf(0x03.toByte(), 0x04.toByte()).toMutableList())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("appendBytesLE(list)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendBytesLE(byteArrayOf(0x03.toByte(), 0x04.toByte()).toMutableList())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x04.toByte(), 0x03.toByte())
            }

            it("appendBytes(list)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.appendBytes(byteArrayOf(0x03.toByte(), 0x04.toByte()).toMutableList())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }

            it("append(list)") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                b.append(byteArrayOf(0x03.toByte(), 0x04.toByte()).toMutableList())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte())
            }
        }
    }

    describe("remove") {

        describe("removeLastBytes()") {

            it("should remove the last n bytes") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastBytes(2) shouldBe byteArrayOf(0x03.toByte(), 0x04.toByte())
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte())
            }

            it("should throw an exception if the number of bytes to remove is greater than the size of the list") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte()).toMutableList()
                runCatching { b.removeLastBytes(3) }.isFailure shouldBe true
            }
        }

        describe("remove byte") {
            it("removeLastByte") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte()).toMutableList()
                b.removeLastByte() shouldBe 0x03.toByte()
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte())
                runCatching { (0..2).forEach { _ -> b.removeLastByte() } }.isFailure shouldBe true
            }
        }

        describe("remove short") {
            it("removeLastShortBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastShortBE() shouldBe 0x0304.toShort()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastShortBE() }.isFailure shouldBe true
            }

            it("removeLastShortLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastShortLE() shouldBe 0x0403.toShort()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastShortLE() }.isFailure shouldBe true
            }

            it("removeLastShort()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastShort() shouldBe 0x0304.toShort()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastShort() }.isFailure shouldBe true
            }
        }

        describe("remove int") {
            it("removeLastIntBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastIntBE() shouldBe 0x03040506.toInt()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastIntBE() }.isFailure shouldBe true
            }

            it("removeLastIntLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastIntLE() shouldBe 0x06050403.toInt()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastIntLE() }.isFailure shouldBe true
            }

            it("removeLastInt()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastInt() shouldBe 0x03040506.toInt()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastInt() }.isFailure shouldBe true
            }
        }

        describe("remove long") {
            it("removeLastLongBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastLongBE() shouldBe 0x030405060708090a.toLong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastLongBE() }.isFailure shouldBe true
            }

            it("removeLastLongLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastLongLE() shouldBe 0x0a09080706050403.toLong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastLongLE() }.isFailure shouldBe true
            }

            it("removeLastLong()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastLong() shouldBe 0x030405060708090a.toLong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastLong() }.isFailure shouldBe true
            }
        }

        describe("remove float") {
            it("removeLastFloatBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte()).toMutableList()
                b.removeLastFloatBE() shouldBe 1.0f
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastFloatBE() }.isFailure shouldBe true
            }

            it("removeLastFloatLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x00.toByte(), 0x00.toByte(), 0x80.toByte(), 0x3F.toByte()).toMutableList()
                b.removeLastFloatLE() shouldBe 1.0f
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastFloatLE() }.isFailure shouldBe true
            }

            it("removeLastFloat()") {
                val b = byteArrayOf(0x02.toByte(), 0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte()).toMutableList()
                b.removeLastFloat() shouldBe 1.0f
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastFloat() }.isFailure shouldBe true
            }
        }

        describe("remove double") {
            it("removeLastDoubleBE()") {
                val b = byteArrayOf(
                    0x02.toByte(),
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                ).toMutableList()
                b.removeLastDoubleBE() shouldBe 1.0
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastDoubleBE() }.isFailure shouldBe true
            }

            it("removeLastDoubleLE()") {
                val b = byteArrayOf(
                    0x02.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0xF0.toByte(),
                    0x3F.toByte(),
                ).toMutableList()
                b.removeLastDoubleLE() shouldBe 1.0
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastDoubleLE() }.isFailure shouldBe true
            }

            it("removeLastDouble()") {
                val b = byteArrayOf(
                    0x02.toByte(),
                    0x3F.toByte(),
                    0xF0.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                    0x00.toByte(),
                ).toMutableList()
                b.removeLastDouble() shouldBe 1.0
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastDouble() }.isFailure shouldBe true
            }
        }

        describe("remove ubyte") {
            it("removeLastUByte") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte()).toMutableList()
                b.removeLastUnsignedByte() shouldBe 0x03u
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte())
                runCatching { (0..2).forEach { _ -> b.removeLastUByte() } }.isFailure shouldBe true
            }

            it("removeLastUnsignedByte") {
                val b = byteArrayOf(0x01.toByte(), 0x02.toByte(), 0x03.toByte()).toMutableList()
                b.removeLastUnsignedByte() shouldBe 0x03u
                b shouldBe byteArrayOf(0x01.toByte(), 0x02.toByte())
                runCatching { (0..2).forEach { _ -> b.removeLastUnsignedByte() } }.isFailure shouldBe true
            }
        }

        describe("remove ushort") {
            it("removeLastUShortBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastUShortBE() shouldBe 0x0304u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUShortBE() }.isFailure shouldBe true
            }

            it("removeLastUShortLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastUShortLE() shouldBe 0x0403u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUShortLE() }.isFailure shouldBe true
            }

            it("removeLastUShort()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastUShort() shouldBe 0x0304u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUShort() }.isFailure shouldBe true
            }

            it("removeLastUnsignedShortBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastUnsignedShortBE() shouldBe 0x0304u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedShortBE() }.isFailure shouldBe true
            }

            it("removeLastUnsignedShortLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastUnsignedShortLE() shouldBe 0x0403u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedShortLE() }.isFailure shouldBe true
            }

            it("removeLastUnsignedShort()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte()).toMutableList()
                b.removeLastUnsignedShort() shouldBe 0x0304u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedShort() }.isFailure shouldBe true
            }
        }

        describe("remove uint") {
            it("removeLastUIntBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastUIntBE() shouldBe 0x03040506u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUIntBE() }.isFailure shouldBe true
            }

            it("removeLastUIntLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastUIntLE() shouldBe 0x06050403u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUIntLE() }.isFailure shouldBe true
            }

            it("removeLastUInt()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastUInt() shouldBe 0x03040506u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUInt() }.isFailure shouldBe true
            }

            it("removeLastUnsignedIntBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastUnsignedIntBE() shouldBe 0x03040506u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedIntBE() }.isFailure shouldBe true
            }

            it("removeLastUnsignedIntLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastUnsignedIntLE() shouldBe 0x06050403u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedIntLE() }.isFailure shouldBe true
            }

            it("removeLastUnsignedInt()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte()).toMutableList()
                b.removeLastUnsignedInt() shouldBe 0x03040506u
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedInt() }.isFailure shouldBe true
            }
        }

        describe("remove ulong") {
            it("removeLastULongBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastULongBE() shouldBe 0x030405060708090a.toULong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastULongBE() }.isFailure shouldBe true
            }

            it("removeLastULongLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastULongLE() shouldBe 0x0a09080706050403.toULong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastULongLE() }.isFailure shouldBe true
            }

            it("removeLastULong()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastULong() shouldBe 0x030405060708090a.toULong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastULong() }.isFailure shouldBe true
            }

            it("removeLastUnsignedLongBE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastUnsignedLongBE() shouldBe 0x030405060708090a.toULong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedLongBE() }.isFailure shouldBe true
            }

            it("removeLastUnsignedLongLE()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastUnsignedLongLE() shouldBe 0x0a09080706050403.toULong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedLongLE() }.isFailure shouldBe true
            }

            it("removeLastUnsignedLong()") {
                val b = byteArrayOf(0x02.toByte(), 0x03.toByte(), 0x04.toByte(), 0x05.toByte(), 0x06.toByte(), 0x07.toByte(), 0x08.toByte(), 0x09.toByte(), 0x0a.toByte()).toMutableList()
                b.removeLastUnsignedLong() shouldBe 0x030405060708090a.toULong()
                b shouldBe byteArrayOf(0x02.toByte())
                runCatching { b.removeLastUnsignedLong() }.isFailure shouldBe true
            }
        }
    }
})
