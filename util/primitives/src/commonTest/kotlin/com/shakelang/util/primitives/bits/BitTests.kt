package com.shakelang.util.primitives.bits

import com.shakelang.util.testlib.FlatTestSpec
import com.shakelang.util.testlib.TestSpecContext
import io.kotest.matchers.shouldBe

class BitTests : FlatTestSpec({

    fun <T> TestSpecContext.bitValueTests(
        bitAmount: Int,
        creator: (bits: List<Boolean>) -> T,
        testLambdas: List<(T) -> Boolean>,
        nthBitLambda: (T, Int) -> Boolean,
    ) {
        if (testLambdas.size != bitAmount) throw IllegalArgumentException("testLambdas.size != bitAmount")

        for (n in 0 until bitAmount) {
            val lambda = testLambdas[n]

            // test the n-th bit
            describe("bit$n") {
                it("should be 1") {
                    // Test with only the n-th bit set to 1
                    val bits = MutableList(bitAmount) { false }
                    bits[n] = true
                    val value = creator(bits)
                    lambda(value) shouldBe true

                    // Test with one other bit set to 1
                    for (i in 0 until bitAmount) {
                        if (i == n) continue
                        bits[i] = true
                        bits[n] = true
                        val v = creator(bits)
                        lambda(v) shouldBe true
                        bits[i] = false
                    }
                }
            }
        }

        describe("nth bit") {
            for (n in 0 until bitAmount) {
                // test the n-th bit
                describe("bit$n") {
                    it("should be 1") {
                        // Test with only the n-th bit set to 1
                        val bits = MutableList(bitAmount) { false }
                        bits[n] = true
                        val value = creator(bits)
                        nthBitLambda(value, n) shouldBe true

                        // Test with one other bit set to 1
                        for (i in 0 until bitAmount) {
                            if (i == n) continue
                            bits[i] = true
                            bits[n] = true
                            val v = creator(bits)
                            nthBitLambda(v, n) shouldBe true
                            bits[i] = false
                        }
                    }
                }
            }
        }
    }

    describe("Bit Values") {
        describe("Byte") {
            bitValueTests(
                8,
                { bits -> byteFromBits(bits) },
                listOf(
                    { it.bit0 },
                    { it.bit1 },
                    { it.bit2 },
                    { it.bit3 },
                    { it.bit4 },
                    { it.bit5 },
                    { it.bit6 },
                    { it.bit7 },
                ),
                { value, n -> value.bit(n) },
            )
        }

        describe("Short") {
            bitValueTests(
                16,
                { bits -> shortFromBits(bits) },
                listOf(
                    { it.bit0 },
                    { it.bit1 },
                    { it.bit2 },
                    { it.bit3 },
                    { it.bit4 },
                    { it.bit5 },
                    { it.bit6 },
                    { it.bit7 },
                    { it.bit8 },
                    { it.bit9 },
                    { it.bit10 },
                    { it.bit11 },
                    { it.bit12 },
                    { it.bit13 },
                    { it.bit14 },
                    { it.bit15 },
                ),
                { value, n -> value.bit(n) },
            )
        }

        describe("Int") {
            bitValueTests(
                32,
                { bits -> intFromBits(bits) },
                listOf(
                    { it.bit0 },
                    { it.bit1 },
                    { it.bit2 },
                    { it.bit3 },
                    { it.bit4 },
                    { it.bit5 },
                    { it.bit6 },
                    { it.bit7 },
                    { it.bit8 },
                    { it.bit9 },
                    { it.bit10 },
                    { it.bit11 },
                    { it.bit12 },
                    { it.bit13 },
                    { it.bit14 },
                    { it.bit15 },
                    { it.bit16 },
                    { it.bit17 },
                    { it.bit18 },
                    { it.bit19 },
                    { it.bit20 },
                    { it.bit21 },
                    { it.bit22 },
                    { it.bit23 },
                    { it.bit24 },
                    { it.bit25 },
                    { it.bit26 },
                    { it.bit27 },
                    { it.bit28 },
                    { it.bit29 },
                    { it.bit30 },
                    { it.bit31 },
                ),
                { value, n -> value.bit(n) },
            )
        }

        describe("Long") {
            bitValueTests(
                64,
                { bits -> longFromBits(bits) },
                listOf(
                    { it.bit0 },
                    { it.bit1 },
                    { it.bit2 },
                    { it.bit3 },
                    { it.bit4 },
                    { it.bit5 },
                    { it.bit6 },
                    { it.bit7 },
                    { it.bit8 },
                    { it.bit9 },
                    { it.bit10 },
                    { it.bit11 },
                    { it.bit12 },
                    { it.bit13 },
                    { it.bit14 },
                    { it.bit15 },
                    { it.bit16 },
                    { it.bit17 },
                    { it.bit18 },
                    { it.bit19 },
                    { it.bit20 },
                    { it.bit21 },
                    { it.bit22 },
                    { it.bit23 },
                    { it.bit24 },
                    { it.bit25 },
                    { it.bit26 },
                    { it.bit27 },
                    { it.bit28 },
                    { it.bit29 },
                    { it.bit30 },
                    { it.bit31 },
                    { it.bit32 },
                    { it.bit33 },
                    { it.bit34 },
                    { it.bit35 },
                    { it.bit36 },
                    { it.bit37 },
                    { it.bit38 },
                    { it.bit39 },
                    { it.bit40 },
                    { it.bit41 },
                    { it.bit42 },
                    { it.bit43 },
                    { it.bit44 },
                    { it.bit45 },
                    { it.bit46 },
                    { it.bit47 },
                    { it.bit48 },
                    { it.bit49 },
                    { it.bit50 },
                    { it.bit51 },
                    { it.bit52 },
                    { it.bit53 },
                    { it.bit54 },
                    { it.bit55 },
                    { it.bit56 },
                    { it.bit57 },
                    { it.bit58 },
                    { it.bit59 },
                    { it.bit60 },
                    { it.bit61 },
                    { it.bit62 },
                    { it.bit63 },
                ),
                { value, n -> value.bit(n) },
            )
        }

        describe("Float") {
            bitValueTests(
                32,
                { bits -> floatFromBits(bits) },
                listOf(
                    { it.bit0 },
                    { it.bit1 },
                    { it.bit2 },
                    { it.bit3 },
                    { it.bit4 },
                    { it.bit5 },
                    { it.bit6 },
                    { it.bit7 },
                    { it.bit8 },
                    { it.bit9 },
                    { it.bit10 },
                    { it.bit11 },
                    { it.bit12 },
                    { it.bit13 },
                    { it.bit14 },
                    { it.bit15 },
                    { it.bit16 },
                    { it.bit17 },
                    { it.bit18 },
                    { it.bit19 },
                    { it.bit20 },
                    { it.bit21 },
                    { it.bit22 },
                    { it.bit23 },
                    { it.bit24 },
                    { it.bit25 },
                    { it.bit26 },
                    { it.bit27 },
                    { it.bit28 },
                    { it.bit29 },
                    { it.bit30 },
                    { it.bit31 },
                ),
                { value, n -> value.bit(n) },
            )
        }

        describe("Double") {
            bitValueTests(
                64,
                { bits -> doubleFromBits(bits) },
                listOf(
                    { it.bit0 },
                    { it.bit1 },
                    { it.bit2 },
                    { it.bit3 },
                    { it.bit4 },
                    { it.bit5 },
                    { it.bit6 },
                    { it.bit7 },
                    { it.bit8 },
                    { it.bit9 },
                    { it.bit10 },
                    { it.bit11 },
                    { it.bit12 },
                    { it.bit13 },
                    { it.bit14 },
                    { it.bit15 },
                    { it.bit16 },
                    { it.bit17 },
                    { it.bit18 },
                    { it.bit19 },
                    { it.bit20 },
                    { it.bit21 },
                    { it.bit22 },
                    { it.bit23 },
                    { it.bit24 },
                    { it.bit25 },
                    { it.bit26 },
                    { it.bit27 },
                    { it.bit28 },
                    { it.bit29 },
                    { it.bit30 },
                    { it.bit31 },
                    { it.bit32 },
                    { it.bit33 },
                    { it.bit34 },
                    { it.bit35 },
                    { it.bit36 },
                    { it.bit37 },
                    { it.bit38 },
                    { it.bit39 },
                    { it.bit40 },
                    { it.bit41 },
                    { it.bit42 },
                    { it.bit43 },
                    { it.bit44 },
                    { it.bit45 },
                    { it.bit46 },
                    { it.bit47 },
                    { it.bit48 },
                    { it.bit49 },
                    { it.bit50 },
                    { it.bit51 },
                    { it.bit52 },
                    { it.bit53 },
                    { it.bit54 },
                    { it.bit55 },
                    { it.bit56 },
                    { it.bit57 },
                    { it.bit58 },
                    { it.bit59 },
                    { it.bit60 },
                    { it.bit61 },
                    { it.bit62 },
                    { it.bit63 },
                ),
                { value, n -> value.bit(n) },
            )

            describe("UByte") {
                bitValueTests(
                    8,
                    { bits -> ubyteFromBits(bits) },
                    listOf(
                        { it.bit0 },
                        { it.bit1 },
                        { it.bit2 },
                        { it.bit3 },
                        { it.bit4 },
                        { it.bit5 },
                        { it.bit6 },
                        { it.bit7 },
                    ),
                    { value, n -> value.bit(n) },
                )
            }

            describe("UShort") {
                bitValueTests(
                    16,
                    { bits -> ushortFromBits(bits) },
                    listOf(
                        { it.bit0 },
                        { it.bit1 },
                        { it.bit2 },
                        { it.bit3 },
                        { it.bit4 },
                        { it.bit5 },
                        { it.bit6 },
                        { it.bit7 },
                        { it.bit8 },
                        { it.bit9 },
                        { it.bit10 },
                        { it.bit11 },
                        { it.bit12 },
                        { it.bit13 },
                        { it.bit14 },
                        { it.bit15 },
                    ),
                    { value, n -> value.bit(n) },
                )
            }

            describe("UInt") {
                bitValueTests(
                    32,
                    { bits -> uintFromBits(bits) },
                    listOf(
                        { it.bit0 },
                        { it.bit1 },
                        { it.bit2 },
                        { it.bit3 },
                        { it.bit4 },
                        { it.bit5 },
                        { it.bit6 },
                        { it.bit7 },
                        { it.bit8 },
                        { it.bit9 },
                        { it.bit10 },
                        { it.bit11 },
                        { it.bit12 },
                        { it.bit13 },
                        { it.bit14 },
                        { it.bit15 },
                        { it.bit16 },
                        { it.bit17 },
                        { it.bit18 },
                        { it.bit19 },
                        { it.bit20 },
                        { it.bit21 },
                        { it.bit22 },
                        { it.bit23 },
                        { it.bit24 },
                        { it.bit25 },
                        { it.bit26 },
                        { it.bit27 },
                        { it.bit28 },
                        { it.bit29 },
                        { it.bit30 },
                        { it.bit31 },
                    ),
                    { value, n -> value.bit(n) },
                )
            }

            describe("ULong") {
                bitValueTests(
                    64,
                    { bits -> ulongFromBits(bits) },
                    listOf(
                        { it.bit0 },
                        { it.bit1 },
                        { it.bit2 },
                        { it.bit3 },
                        { it.bit4 },
                        { it.bit5 },
                        { it.bit6 },
                        { it.bit7 },
                        { it.bit8 },
                        { it.bit9 },
                        { it.bit10 },
                        { it.bit11 },
                        { it.bit12 },
                        { it.bit13 },
                        { it.bit14 },
                        { it.bit15 },
                        { it.bit16 },
                        { it.bit17 },
                        { it.bit18 },
                        { it.bit19 },
                        { it.bit20 },
                        { it.bit21 },
                        { it.bit22 },
                        { it.bit23 },
                        { it.bit24 },
                        { it.bit25 },
                        { it.bit26 },
                        { it.bit27 },
                        { it.bit28 },
                        { it.bit29 },
                        { it.bit30 },
                        { it.bit31 },
                        { it.bit32 },
                        { it.bit33 },
                        { it.bit34 },
                        { it.bit35 },
                        { it.bit36 },
                        { it.bit37 },
                        { it.bit38 },
                        { it.bit39 },
                        { it.bit40 },
                        { it.bit41 },
                        { it.bit42 },
                        { it.bit43 },
                        { it.bit44 },
                        { it.bit45 },
                        { it.bit46 },
                        { it.bit47 },
                        { it.bit48 },
                        { it.bit49 },
                        { it.bit50 },
                        { it.bit51 },
                        { it.bit52 },
                        { it.bit53 },
                        { it.bit54 },
                        { it.bit55 },
                        { it.bit56 },
                        { it.bit57 },
                        { it.bit58 },
                        { it.bit59 },
                        { it.bit60 },
                        { it.bit61 },
                        { it.bit62 },
                        { it.bit63 },
                    ),
                    { value, n -> value.bit(n) },
                )
            }

            describe("Char") {
                bitValueTests(
                    16,
                    { bits -> charFromBits(bits) },
                    listOf(
                        { it.bit0 },
                        { it.bit1 },
                        { it.bit2 },
                        { it.bit3 },
                        { it.bit4 },
                        { it.bit5 },
                        { it.bit6 },
                        { it.bit7 },
                        { it.bit8 },
                        { it.bit9 },
                        { it.bit10 },
                        { it.bit11 },
                        { it.bit12 },
                        { it.bit13 },
                        { it.bit14 },
                        { it.bit15 },
                    ),
                    { value, n -> value.bit(n) },
                )
            }
        }
    }
})
