@file:OptIn(ExperimentalUnsignedTypes::class)

package com.shakelang.util.primitives.bytes

import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import kotlin.math.abs
import kotlin.math.max

@Suppress("unused")
@OptIn(ExperimentalUnsignedTypes::class)
class ByteArrayTests : FlatTestSpec({

    describe("to") {

        describe("toByte()") {

            it("should convert a byte of size 1 to a byte") {
                val b = byteArrayOf(1.toByte())
                b.toByte() shouldBe 1.toByte()
            }

            it("should throw an exception if the byte array is > 1") {
                val b = byteArrayOf(1.toByte(), 2.toByte())
                runCatching { b.toByte() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 1") {
                val b = byteArrayOf()
                runCatching { b.toByte() }.isFailure shouldBe true
            }
        }

        describe("toUByte()") {

            it("should convert a byte of size 1 to a ubyte") {
                val b = byteArrayOf(1.toByte())
                b.toUByte() shouldBe 1.toUByte()
            }

            it("should throw an exception if the byte array is > 1") {
                val b = byteArrayOf(1.toByte(), 2.toByte())
                runCatching { b.toUByte() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 1") {
                val b = byteArrayOf()
                runCatching { b.toUByte() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedByte()") {
            it("should convert a byte of size 1 to a ubyte") {
                val b = byteArrayOf(1.toByte())
                b.toUnsignedByte() shouldBe 1.toUByte()
            }

            it("should throw an exception if the byte array is > 1") {
                val b = byteArrayOf(1.toByte(), 2.toByte())
                runCatching { b.toUnsignedByte() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 1") {
                val b = byteArrayOf()
                runCatching { b.toUnsignedByte() }.isFailure shouldBe true
            }
        }

        describe("toShortBE()") {

            it("should convert a byte of size 2 to a short") {
                val b = byteArrayOf(0x01.toByte(), 2)
                b.toShortBE() shouldBe 0x0102.toShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toShortBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toShortBE() }.isFailure shouldBe true
            }
        }

        describe("toShortLE()") {

            it("should convert a byte of size 2 to a short") {
                val b = byteArrayOf(0x02.toByte(), 1)
                b.toShortLE() shouldBe 0x0102.toShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toShortLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toShortLE() }.isFailure shouldBe true
            }
        }

        describe("toShort()") {

            it("should convert a byte of size 2 to a short") {
                val b = byteArrayOf(0x01.toByte(), 2)
                b.toShort() shouldBe 0x0102.toShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toShort() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toShort() }.isFailure shouldBe true
            }
        }

        describe("toUShortBE()") {

            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2)
                b.toUShortBE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUShortBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toUShortBE() }.isFailure shouldBe true
            }
        }

        describe("toUShortLE()") {

            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x02.toByte(), 1)
                b.toUShortLE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUShortLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toUShortLE() }.isFailure shouldBe true
            }
        }

        describe("toUShort()") {

            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2)
                b.toUShort() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUShort() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toUShort() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedShortBE()") {
            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2)
                b.toUnsignedShortBE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUnsignedShortBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toUnsignedShortBE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedShortLE()") {
            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x02.toByte(), 1)
                b.toUnsignedShortLE() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUnsignedShortLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toUnsignedShortLE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedShort()") {
            it("should convert a byte of size 2 to a ushort") {
                val b = byteArrayOf(0x01.toByte(), 2)
                b.toUnsignedShort() shouldBe 0x0102.toUShort()
            }

            it("should throw an exception if the byte array is > 2") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUnsignedShort() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 2") {
                val b = byteArrayOf(1.toByte())
                runCatching { b.toUnsignedShort() }.isFailure shouldBe true
            }
        }

        describe("toIntBE()") {

            it("should convert a byte of size 4 to a int") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4)
                b.toIntBE() shouldBe 0x01020304
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toIntBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toIntBE() }.isFailure shouldBe true
            }
        }

        describe("toIntLE()") {

            it("should convert a byte of size 4 to a int") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1)
                b.toIntLE() shouldBe 0x01020304
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toIntLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toIntLE() }.isFailure shouldBe true
            }
        }

        describe("toInt()") {

            it("should convert a byte of size 4 to a int") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4)
                b.toInt() shouldBe 0x01020304
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toInt() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toInt() }.isFailure shouldBe true
            }
        }

        describe("toUIntBE()") {

            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4)
                b.toUIntBE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toUIntBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUIntBE() }.isFailure shouldBe true
            }
        }

        describe("toUIntLE()") {

            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1)
                b.toUIntLE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toUIntLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUIntLE() }.isFailure shouldBe true
            }
        }

        describe("toUInt()") {

            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4)
                b.toUInt() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toUInt() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUInt() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedIntBE()") {
            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4)
                b.toUnsignedIntBE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toUnsignedIntBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUnsignedIntBE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedIntLE()") {
            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1)
                b.toUnsignedIntLE() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toUnsignedIntLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUnsignedIntLE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedInt()") {
            it("should convert a byte of size 4 to a uint") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4)
                b.toUnsignedInt() shouldBe 0x01020304u
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toUnsignedInt() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toUnsignedInt() }.isFailure shouldBe true
            }
        }

        describe("toLongBE()") {

            it("should convert a byte of size 8 to a long") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.toLongBE() shouldBe 0x0102030405060708
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toLongBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toLongBE() }.isFailure shouldBe true
            }
        }

        describe("toLongLE()") {

            it("should convert a byte of size 8 to a long") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
                b.toLongLE() shouldBe 0x0102030405060708
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toLongLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toLongLE() }.isFailure shouldBe true
            }
        }

        describe("toLong()") {

            it("should convert a byte of size 8 to a long") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.toLong() shouldBe 0x0102030405060708
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toLong() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toLong() }.isFailure shouldBe true
            }
        }

        describe("toULongBE()") {

            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.toULongBE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toULongBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toULongBE() }.isFailure shouldBe true
            }
        }

        describe("toULongLE()") {

            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
                b.toULongLE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toULongLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toULongLE() }.isFailure shouldBe true
            }
        }

        describe("toULong()") {

            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.toULong() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toULong() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toULong() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedLongBE()") {
            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.toUnsignedLongBE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toUnsignedLongBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toUnsignedLongBE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedLongLE()") {
            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
                b.toUnsignedLongLE() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toUnsignedLongLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toUnsignedLongLE() }.isFailure shouldBe true
            }
        }

        describe("toUnsignedLong()") {
            it("should convert a byte of size 8 to a ulong") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.toUnsignedLong() shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toUnsignedLong() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toUnsignedLong() }.isFailure shouldBe true
            }
        }

        describe("toFloatBE()") {

            it("should convert a byte of size 4 to a float") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte())
                assertCompare(1.0f, b.toFloatBE())
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toFloatBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toFloatBE() }.isFailure shouldBe true
            }
        }

        describe("toFloatLE()") {

            it("should convert a byte of size 4 to a float") {
                val b = byteArrayOf(0x00.toByte(), 0x00.toByte(), 0x80.toByte(), 0x3F.toByte())
                assertCompare(1.0f, b.toFloatLE())
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toFloatLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toFloatLE() }.isFailure shouldBe true
            }
        }

        describe("toFloat()") {

            it("should convert a byte of size 4 to a float") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte())
                assertCompare(1.0f, b.toFloat())
            }

            it("should throw an exception if the byte array is > 4") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5)
                runCatching { b.toFloat() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 4") {
                val b = byteArrayOf(1.toByte(), 2, 3)
                runCatching { b.toFloat() }.isFailure shouldBe true
            }
        }

        describe("toDoubleBE()") {
            it("should convert a byte of size 8 to a double") {
                val b = byteArrayOf(0x3F.toByte(), 0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte())
                assertCompare(1.0, b.toDoubleBE())
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toDoubleBE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toDoubleBE() }.isFailure shouldBe true
            }
        }

        describe("toDoubleLE()") {
            it("should convert a byte of size 8 to a double") {
                val b = byteArrayOf(0, 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                assertCompare(1.0, b.toDoubleLE())
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toDoubleLE() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toDoubleLE() }.isFailure shouldBe true
            }
        }

        describe("toDouble()") {
            it("should convert a byte of size 8 to a double") {
                val b = byteArrayOf(0x3F.toByte(), 0xF0.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte())
                assertCompare(1.0, b.toDouble())
            }

            it("should throw an exception if the byte array is > 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, 9)
                runCatching { b.toDouble() }.isFailure shouldBe true
            }

            it("should throw an exception if the byte array is < 8") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7)
                runCatching { b.toDouble() }.isFailure shouldBe true
            }
        }

        describe("toHexString()") {
            it("should convert a byte array to a hex string") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8, 0x2A.toByte())
                b.toHexString() shouldBe "01020304050607082a"
            }
        }

        describe("toUtf8String()") {
            it("should convert a byte array to a utf8 string") {
                val b = "Hello World\u0009".toCharArray().map { it.code.toByte() }.toByteArray()
                b.toUtf8String() shouldBe "Hello World\u0009"
            }
        }
    }

    describe("get") {
        describe("getByte") {
            it("should get the byte at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
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
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getByte(8) }.isFailure shouldBe true
                runCatching { b.getByte(-1) }.isFailure shouldBe true
            }
        }

        describe("getUByte") {
            it("should get the ubyte at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
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
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUByte(8) }.isFailure shouldBe true
                runCatching { b.getUByte(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedByte") {
            it("should get the ubyte at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
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
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedByte(8) }.isFailure shouldBe true
                runCatching { b.getUnsignedByte(-1) }.isFailure shouldBe true
            }
        }

        describe("getShortBE") {
            it("should get the short at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getShortBE(0) shouldBe 0x0102.toShort()
                b.getShortBE(1) shouldBe 0x0203.toShort()
                b.getShortBE(4) shouldBe 0x0506.toShort()
                b.getShortBE(5) shouldBe 0x0607.toShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getShortBE(7) }.isFailure shouldBe true
                runCatching { b.getShortBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getShortLE") {
            it("should get the short at the given index") {
                val b = byteArrayOf(0x02.toByte(), 1, 4, 3, 6, 5, 8, 7)
                b.getShortLE(0) shouldBe 0x0102.toShort()
                b.getShortLE(1) shouldBe 0x0401.toShort()
                b.getShortLE(4) shouldBe 0x0506.toShort()
                b.getShortLE(5) shouldBe 0x0805.toShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getShortLE(7) }.isFailure shouldBe true
                runCatching { b.getShortLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getShort") {
            it("should get the short at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getShort(0) shouldBe 0x0102.toShort()
                b.getShort(1) shouldBe 0x0203.toShort()
                b.getShort(4) shouldBe 0x0506.toShort()
                b.getShort(5) shouldBe 0x0607.toShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getShort(7) }.isFailure shouldBe true
                runCatching { b.getShort(-1) }.isFailure shouldBe true
            }
        }

        describe("getUShortBE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUShortBE(0) shouldBe 0x0102.toUShort()
                b.getUShortBE(1) shouldBe 0x0203.toUShort()
                b.getUShortBE(4) shouldBe 0x0506.toUShort()
                b.getUShortBE(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUShortBE(7) }.isFailure shouldBe true
                runCatching { b.getUShortBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUShortLE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x02.toByte(), 1, 4, 3, 6, 5, 8, 7)
                b.getUShortLE(0) shouldBe 0x0102.toUShort()
                b.getUShortLE(1) shouldBe 0x0401.toUShort()
                b.getUShortLE(4) shouldBe 0x0506.toUShort()
                b.getUShortLE(5) shouldBe 0x0805.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUShortLE(7) }.isFailure shouldBe true
                runCatching { b.getUShortLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUShort") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUShort(0) shouldBe 0x0102.toUShort()
                b.getUShort(1) shouldBe 0x0203.toUShort()
                b.getUShort(4) shouldBe 0x0506.toUShort()
                b.getUShort(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUShort(7) }.isFailure shouldBe true
                runCatching { b.getUShort(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedShortBE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUnsignedShortBE(0) shouldBe 0x0102.toUShort()
                b.getUnsignedShortBE(1) shouldBe 0x0203.toUShort()
                b.getUnsignedShortBE(4) shouldBe 0x0506.toUShort()
                b.getUnsignedShortBE(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedShortBE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedShortBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedShortLE") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x02.toByte(), 1, 4, 3, 6, 5, 8, 7)
                b.getUnsignedShortLE(0) shouldBe 0x0102.toUShort()
                b.getUnsignedShortLE(1) shouldBe 0x0401.toUShort()
                b.getUnsignedShortLE(4) shouldBe 0x0506.toUShort()
                b.getUnsignedShortLE(5) shouldBe 0x0805.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedShortLE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedShortLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedShort") {
            it("should get the ushort at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUnsignedShort(0) shouldBe 0x0102.toUShort()
                b.getUnsignedShort(1) shouldBe 0x0203.toUShort()
                b.getUnsignedShort(4) shouldBe 0x0506.toUShort()
                b.getUnsignedShort(5) shouldBe 0x0607.toUShort()
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedShort(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedShort(-1) }.isFailure shouldBe true
            }
        }

        describe("getIntBE") {
            it("should get the int at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getIntBE(0) shouldBe 0x01020304
                b.getIntBE(1) shouldBe 0x02030405
                b.getIntBE(4) shouldBe 0x05060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getIntBE(7) }.isFailure shouldBe true
                runCatching { b.getIntBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getIntLE") {
            it("should get the int at the given index") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1, 8, 7, 6, 5)
                b.getIntLE(0) shouldBe 0x01020304
                b.getIntLE(1) shouldBe 0x08010203
                b.getIntLE(4) shouldBe 0x05060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getIntLE(7) }.isFailure shouldBe true
                runCatching { b.getIntLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getInt") {
            it("should get the int at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getInt(0) shouldBe 0x01020304
                b.getInt(1) shouldBe 0x02030405
                b.getInt(4) shouldBe 0x05060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getInt(7) }.isFailure shouldBe true
                runCatching { b.getInt(-1) }.isFailure shouldBe true
            }
        }

        describe("getUIntBE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUIntBE(0) shouldBe 0x01020304u
                b.getUIntBE(1) shouldBe 0x02030405u
                b.getUIntBE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUIntBE(7) }.isFailure shouldBe true
                runCatching { b.getUIntBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUIntLE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1, 8, 7, 6, 5)
                b.getUIntLE(0) shouldBe 0x01020304u
                b.getUIntLE(1) shouldBe 0x08010203u
                b.getUIntLE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUIntLE(7) }.isFailure shouldBe true
                runCatching { b.getUIntLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUInt") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUInt(0) shouldBe 0x01020304u
                b.getUInt(1) shouldBe 0x02030405u
                b.getUInt(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUInt(7) }.isFailure shouldBe true
                runCatching { b.getUInt(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedIntBE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUnsignedIntBE(0) shouldBe 0x01020304u
                b.getUnsignedIntBE(1) shouldBe 0x02030405u
                b.getUnsignedIntBE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedIntBE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedIntBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedIntLE") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x04.toByte(), 3, 2, 1, 8, 7, 6, 5)
                b.getUnsignedIntLE(0) shouldBe 0x01020304u
                b.getUnsignedIntLE(1) shouldBe 0x08010203u
                b.getUnsignedIntLE(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedIntLE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedIntLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedInt") {
            it("should get the uint at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUnsignedInt(0) shouldBe 0x01020304u
                b.getUnsignedInt(1) shouldBe 0x02030405u
                b.getUnsignedInt(4) shouldBe 0x05060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedInt(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedInt(-1) }.isFailure shouldBe true
            }
        }

        describe("getLongBE") {
            it("should get the long at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getLongBE(0) shouldBe 0x0102030405060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getLongBE(7) }.isFailure shouldBe true
                runCatching { b.getLongBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getLongLE") {
            it("should get the long at the given index") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
                b.getLongLE(0) shouldBe 0x0102030405060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getLongLE(7) }.isFailure shouldBe true
                runCatching { b.getLongLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getLong") {
            it("should get the long at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getLong(0) shouldBe 0x0102030405060708
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getLong(7) }.isFailure shouldBe true
                runCatching { b.getLong(-1) }.isFailure shouldBe true
            }
        }

        describe("getULongBE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getULongBE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getULongBE(7) }.isFailure shouldBe true
                runCatching { b.getULongBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getULongLE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
                b.getULongLE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getULongLE(7) }.isFailure shouldBe true
                runCatching { b.getULongLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getULong") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getULong(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getULong(7) }.isFailure shouldBe true
                runCatching { b.getULong(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedLongBE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUnsignedLongBE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedLongBE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedLongBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedLongLE") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
                b.getUnsignedLongLE(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedLongLE(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedLongLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getUnsignedLong") {
            it("should get the ulong at the given index") {
                val b = byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getUnsignedLong(0) shouldBe 0x0102030405060708u
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getUnsignedLong(7) }.isFailure shouldBe true
                runCatching { b.getUnsignedLong(-1) }.isFailure shouldBe true
            }
        }

        describe("getFloatBE") {
            it("should get the float at the given index") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte())
                assertCompare(1.0f, b.getFloatBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4)
                runCatching { b.getFloatBE(4) }.isFailure shouldBe true
                runCatching { b.getFloatBE(-1) }.isFailure shouldBe true
            }
        }

        describe("getFloatLE") {
            it("should get the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0x00.toByte(), 0x80.toByte(), 0x3F.toByte())
                assertCompare(1.0f, b.getFloatLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4)
                runCatching { b.getFloatLE(4) }.isFailure shouldBe true
                runCatching { b.getFloatLE(-1) }.isFailure shouldBe true
            }
        }

        describe("getFloat") {
            it("should get the float at the given index") {
                val b = byteArrayOf(0x3F.toByte(), 0x80.toByte(), 0x00.toByte(), 0x00.toByte())
                assertCompare(1.0f, b.getFloat(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4)
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
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
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
                )
                assertCompare(1.0, b.getDoubleLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
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
                )
                assertCompare(1.0, b.getDouble(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getDouble(8) }.isFailure shouldBe true
                runCatching { b.getDouble(-1) }.isFailure shouldBe true
            }
        }

        describe("getBytesBE") {
            it("should get the bytes at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getBytesBE(0, 4) shouldBe byteArrayOf(1.toByte(), 2, 3, 4)
                b.getBytesBE(1, 4) shouldBe byteArrayOf(2.toByte(), 3, 4, 5)
                b.getBytesBE(4, 4) shouldBe byteArrayOf(5.toByte(), 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getBytesBE(8, 1) }.isFailure shouldBe true
                runCatching { b.getBytesBE(-1, 1) }.isFailure shouldBe true
            }
        }

        describe("getBytesLE") {
            it("should get the bytes at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getBytesLE(0, 4) shouldBe byteArrayOf(4.toByte(), 3, 2, 1)
                b.getBytesLE(1, 4) shouldBe byteArrayOf(5.toByte(), 4, 3, 2)
                b.getBytesLE(4, 4) shouldBe byteArrayOf(8.toByte(), 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getBytesLE(8, 1) }.isFailure shouldBe true
                runCatching { b.getBytesLE(-1, 1) }.isFailure shouldBe true
            }
        }

        describe("getBytes") {
            it("should get the bytes at the given index") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                b.getBytes(0, 4) shouldBe byteArrayOf(1.toByte(), 2, 3, 4)
                b.getBytes(1, 4) shouldBe byteArrayOf(2.toByte(), 3, 4, 5)
                b.getBytes(4, 4) shouldBe byteArrayOf(5.toByte(), 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.getBytes(8, 1) }.isFailure shouldBe true
                runCatching { b.getBytes(-1, 1) }.isFailure shouldBe true
            }
        }
    }

    describe("set") {
        describe("setByte") {
            it("should set the byte at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
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
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setByte(8, 1.toByte()) }.isFailure shouldBe true
                runCatching { b.setByte(-1, 1.toByte()) }.isFailure shouldBe true
            }
        }

        describe("setUByte") {
            it("should set the ubyte at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
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
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUByte(8, 1.toUByte()) }.isFailure shouldBe true
                runCatching { b.setUByte(-1, 1.toUByte()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedByte") {
            it("should set the ubyte at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
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
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedByte(8, 1.toUByte()) }.isFailure shouldBe true
                runCatching { b.setUnsignedByte(-1, 1.toUByte()) }.isFailure shouldBe true
            }
        }

        describe("setShortBE") {
            it("should set the short at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setShortBE(0, 0x0102.toShort())
                b.setShortBE(1, 0x0203.toShort())
                b.setShortBE(4, 0x0506.toShort())
                b.setShortBE(5, 0x0607.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setShortBE(7, 0x0102.toShort()) }.isFailure shouldBe true
                runCatching { b.setShortBE(-1, 0x0102.toShort()) }.isFailure shouldBe true
            }
        }

        describe("setShortLE") {
            it("should set the short at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setShortLE(0, 0x0102.toShort())
                b.setShortLE(1, 0x0203.toShort())
                b.setShortLE(4, 0x0506.toShort())
                b.setShortLE(5, 0x0607.toShort())
                b shouldBe byteArrayOf(0x02.toByte(), 3, 2, 0, 6, 7, 6, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setShortLE(7, 0x0102.toShort()) }.isFailure shouldBe true
                runCatching { b.setShortLE(-1, 0x0102.toShort()) }.isFailure shouldBe true
            }
        }

        describe("setShort") {
            it("should set the short at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setShort(0, 0x0102.toShort())
                b.setShort(1, 0x0203.toShort())
                b.setShort(4, 0x0506.toShort())
                b.setShort(5, 0x0607.toShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setShort(7, 0x0102.toShort()) }.isFailure shouldBe true
                runCatching { b.setShort(-1, 0x0102.toShort()) }.isFailure shouldBe true
            }
        }

        describe("setUShortBE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUShortBE(0, 0x0102.toUShort())
                b.setUShortBE(1, 0x0203.toUShort())
                b.setUShortBE(4, 0x0506.toUShort())
                b.setUShortBE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUShortBE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUShortBE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUShortLE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUShortLE(0, 0x0102.toUShort())
                b.setUShortLE(1, 0x0203.toUShort())
                b.setUShortLE(4, 0x0506.toUShort())
                b.setUShortLE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x02.toByte(), 3, 2, 0, 6, 7, 6, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUShortLE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUShortLE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUShort") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUShort(0, 0x0102.toUShort())
                b.setUShort(1, 0x0203.toUShort())
                b.setUShort(4, 0x0506.toUShort())
                b.setUShort(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUShort(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUShort(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedShortBE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedShortBE(0, 0x0102.toUShort())
                b.setUnsignedShortBE(1, 0x0203.toUShort())
                b.setUnsignedShortBE(4, 0x0506.toUShort())
                b.setUnsignedShortBE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedShortBE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUnsignedShortBE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedShortLE") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedShortLE(0, 0x0102.toUShort())
                b.setUnsignedShortLE(1, 0x0203.toUShort())
                b.setUnsignedShortLE(4, 0x0506.toUShort())
                b.setUnsignedShortLE(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x02.toByte(), 3, 2, 0, 6, 7, 6, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedShortLE(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUnsignedShortLE(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedShort") {
            it("should set the ushort at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedShort(0, 0x0102.toUShort())
                b.setUnsignedShort(1, 0x0203.toUShort())
                b.setUnsignedShort(4, 0x0506.toUShort())
                b.setUnsignedShort(5, 0x0607.toUShort())
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 0, 5, 6, 7, 0)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedShort(7, 0x0102.toUShort()) }.isFailure shouldBe true
                runCatching { b.setUnsignedShort(-1, 0x0102.toUShort()) }.isFailure shouldBe true
            }
        }

        describe("setIntBE") {
            it("should set the int at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setIntBE(0, 0x01020304)
                b.setIntBE(1, 0x02030405)
                b.setIntBE(4, 0x05060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setIntBE(7, 0x01020304) }.isFailure shouldBe true
                runCatching { b.setIntBE(-1, 0x01020304) }.isFailure shouldBe true
            }
        }

        describe("setIntLE") {
            it("should set the int at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setIntLE(0, 0x01020304)
                b.setIntLE(1, 0x02030405)
                b.setIntLE(4, 0x05060708)
                b shouldBe byteArrayOf(0x04.toByte(), 5, 4, 3, 8, 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setIntLE(7, 0x01020304) }.isFailure shouldBe true
                runCatching { b.setIntLE(-1, 0x01020304) }.isFailure shouldBe true
            }
        }

        describe("setInt") {
            it("should set the int at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setInt(0, 0x01020304)
                b.setInt(1, 0x02030405)
                b.setInt(4, 0x05060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setInt(7, 0x01020304) }.isFailure shouldBe true
                runCatching { b.setInt(-1, 0x01020304) }.isFailure shouldBe true
            }
        }

        describe("setUIntBE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUIntBE(0, 0x01020304u)
                b.setUIntBE(1, 0x02030405u)
                b.setUIntBE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUIntBE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUIntBE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUIntLE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUIntLE(0, 0x01020304u)
                b.setUIntLE(1, 0x02030405u)
                b.setUIntLE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x04.toByte(), 5, 4, 3, 8, 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUIntLE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUIntLE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUInt") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUInt(0, 0x01020304u)
                b.setUInt(1, 0x02030405u)
                b.setUInt(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUInt(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUInt(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedIntBE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedIntBE(0, 0x01020304u)
                b.setUnsignedIntBE(1, 0x02030405u)
                b.setUnsignedIntBE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedIntBE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUnsignedIntBE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedIntLE") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedIntLE(0, 0x01020304u)
                b.setUnsignedIntLE(1, 0x02030405u)
                b.setUnsignedIntLE(4, 0x05060708u)
                b shouldBe byteArrayOf(0x04.toByte(), 5, 4, 3, 8, 7, 6, 5)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedIntLE(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUnsignedIntLE(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedInt") {
            it("should set the uint at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedInt(0, 0x01020304u)
                b.setUnsignedInt(1, 0x02030405u)
                b.setUnsignedInt(4, 0x05060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedInt(7, 0x01020304u) }.isFailure shouldBe true
                runCatching { b.setUnsignedInt(-1, 0x01020304u) }.isFailure shouldBe true
            }
        }

        describe("setLongBE") {
            it("should set the long at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setLongBE(0, 0x0102030405060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setLongBE(7, 0x0102030405060708) }.isFailure shouldBe true
                runCatching { b.setLongBE(-1, 0x0102030405060708) }.isFailure shouldBe true
            }
        }

        describe("setLongLE") {
            it("should set the long at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setLongLE(0, 0x0102030405060708)
                b shouldBe byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setLongLE(7, 0x0102030405060708) }.isFailure shouldBe true
                runCatching { b.setLongLE(-1, 0x0102030405060708) }.isFailure shouldBe true
            }
        }

        describe("setLong") {
            it("should set the long at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setLong(0, 0x0102030405060708)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setLong(7, 0x0102030405060708) }.isFailure shouldBe true
                runCatching { b.setLong(-1, 0x0102030405060708) }.isFailure shouldBe true
            }
        }

        describe("setULongBE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setULongBE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setULongBE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setULongBE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setULongLE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setULongLE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setULongLE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setULongLE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setULong") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setULong(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setULong(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setULong(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedLongBE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedLongBE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedLongBE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setUnsignedLongBE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedLongLE") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedLongLE(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x08.toByte(), 7, 6, 5, 4, 3, 2, 1)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedLongLE(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setUnsignedLongLE(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setUnsignedLong") {
            it("should set the ulong at the given index") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                b.setUnsignedLong(0, 0x0102030405060708u)
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 5, 6, 7, 8)
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(0.toByte(), 0, 0, 0, 0, 0, 0, 0)
                runCatching { b.setUnsignedLong(7, 0x0102030405060708u) }.isFailure shouldBe true
                runCatching { b.setUnsignedLong(-1, 0x0102030405060708u) }.isFailure shouldBe true
            }
        }

        describe("setFloatBE") {
            it("should set the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0x80.toByte(), 0x3F.toByte())
                b.setFloatBE(0, 1.0f)
                assertCompare(1.0f, b.getFloatBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setFloatBE(8, 1.0f) }.isFailure shouldBe true
                runCatching { b.setFloatBE(-1, 1.0f) }.isFailure shouldBe true
            }
        }

        describe("setFloatLE") {
            it("should set the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0x3F.toByte(), 0x80.toByte(), 0, 0)
                b.setFloatLE(0, 1.0f)
                assertCompare(1.0f, b.getFloatLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setFloatLE(8, 1.0f) }.isFailure shouldBe true
                runCatching { b.setFloatLE(-1, 1.0f) }.isFailure shouldBe true
            }
        }

        describe("setFloat") {
            it("should set the float at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0x80.toByte(), 0x3F.toByte())
                b.setFloat(0, 1.0f)
                assertCompare(1.0f, b.getFloat(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setFloat(8, 1.0f) }.isFailure shouldBe true
                runCatching { b.setFloat(-1, 1.0f) }.isFailure shouldBe true
            }
        }

        describe("setDoubleBE") {
            it("should set the double at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                b.setDoubleBE(0, 1.0)
                assertCompare(1.0, b.getDoubleBE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setDoubleBE(8, 1.0) }.isFailure shouldBe true
                runCatching { b.setDoubleBE(-1, 1.0) }.isFailure shouldBe true
            }
        }

        describe("setDoubleLE") {
            it("should set the double at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                b.setDoubleLE(0, 1.0)
                assertCompare(1.0, b.getDoubleLE(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setDoubleLE(8, 1.0) }.isFailure shouldBe true
                runCatching { b.setDoubleLE(-1, 1.0) }.isFailure shouldBe true
            }
        }

        describe("setDouble") {
            it("should set the double at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                b.setDouble(0, 1.0)
                assertCompare(1.0, b.getDouble(0))
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setDouble(8, 1.0) }.isFailure shouldBe true
                runCatching { b.setDouble(-1, 1.0) }.isFailure shouldBe true
            }
        }

        describe("setBytesBE") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                b.setBytesBE(0, byteArrayOf(0x01.toByte(), 2, 3, 4))
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setBytesBE(8, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
                runCatching { b.setBytesBE(-1, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
            }
        }

        describe("setBytesLE") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                b.setBytesLE(0, byteArrayOf(0x01.toByte(), 2, 3, 4))
                b shouldBe byteArrayOf(0x04.toByte(), 3, 2, 1, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setBytesLE(8, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
                runCatching { b.setBytesLE(-1, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
            }
        }

        describe("setBytes") {
            it("should set the bytes at the given index") {
                val b = byteArrayOf(0x00.toByte(), 0, 0, 0, 0, 0, 0xF0.toByte(), 0x3F.toByte())
                b.setBytes(0, byteArrayOf(0x01.toByte(), 2, 3, 4))
                b shouldBe byteArrayOf(0x01.toByte(), 2, 3, 4, 0, 0, 0xF0.toByte(), 0x3F.toByte())
            }

            it("should throw an exception if the index is out of bounds") {
                val b = byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8)
                runCatching { b.setBytes(8, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
                runCatching { b.setBytes(-1, byteArrayOf(0x01.toByte(), 2, 3, 4)) }.isFailure shouldBe true
            }
        }
    }

    describe("byteArrayOf (ubyte)") {
        it("should create a byte array") {
            val b = byteArrayOf(1u, 2u, 3u, 4u, 5u, 6u, 7u, 8u, 255u)
            b shouldBe byteArrayOf(1.toByte(), 2, 3, 4, 5, 6, 7, 8, -1)
        }
    }
})

fun assertCompare(expected: Float, actual: Float) {
    val delta = max(abs(expected / 1000), 0.001f)
    compare(expected, actual, delta) shouldBe true
}

fun assertCompare(expected: Double, actual: Double) {
    val delta = max(abs(expected / 1000000), 0.000001)
    compare(expected, actual, delta) shouldBe true
}

fun compare(f0: Float, f1: Float, delta: Float): Boolean = (f0.isNaN() && f1.isNaN()) ||
    (f0.isInfinite() && f1.isInfinite() && ((f0 > 0 && f1 > 0) || (f0 < 0 && f1 < 0))) ||
    abs(f0 - f1) <= delta

fun compare(d0: Double, d1: Double, delta: Double): Boolean = (d0.isNaN() && d1.isNaN()) ||
    (d0.isInfinite() && d1.isInfinite() && ((d0 > 0 && d1 > 0) || (d0 < 0 && d1 < 0))) ||
    abs(d0 - d1) <= delta
