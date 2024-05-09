package com.shakelang.util.primitives.bits

import com.shakelang.util.testlib.FlatTestSpec
import com.shakelang.util.testlib.TestEnvironment
import com.shakelang.util.testlib.TestSpecContext
import io.kotest.matchers.shouldBe

class BitTests : FlatTestSpec(
    {

        fun <T> TestSpecContext.bitValueTests(
            bitAmount: Int,
            creator: (bits: List<Boolean>) -> T,
            testLambdas: List<(T) -> Boolean>,
            nthBitLambda: (T, Int) -> Boolean,
            bitsLambda: (T) -> List<Boolean>,
            ignoreInJs: Boolean = false,
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

            runInEnvironments(if (ignoreInJs) listOf(TestEnvironment.JVM) else listOf(TestEnvironment.JVM, TestEnvironment.JS)) {
                it("bits") {
                    // Test with all bits set to 1
                    val bits = MutableList(bitAmount) { true }
                    val value = creator(bits)
                    bitsLambda(value) shouldBe bits

                    // Test with all bits set to 0
                    for (i in 0 until bitAmount) {
                        bits[i] = false
                        val v = creator(bits)
                        bitsLambda(v) shouldBe bits
                        bits[i] = true
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
                    { it.bits },
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
                    { it.bits },
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
                    { it.bits },
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
                    { it.bits },
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
                    { it.bits },
                    true,
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
                    { it.bits },
                    true,
                )
            }

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
                    { it.bits },
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
                    { it.bits },
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
                    { it.bits },
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
                    { it.bits },
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
                    { it.bits },
                )
            }
        }

        fun <T> TestSpecContext.withBitTests(
            bitAmount: Int,
            creator: (bits: List<Boolean>) -> T,
            testLambdas: List<(T, Boolean) -> T>,
            nthBitLambda: (T, Int, Boolean) -> T,
        ) {
            if (testLambdas.size != bitAmount) throw IllegalArgumentException("testLambdas.size != bitAmount")

            for (n in 0 until bitAmount) {
                val lambda = testLambdas[n]

                // test the n-th bit
                describe("withBit$n") {
                    it("set to 0") {
                        // Test with only the n-th bit set to 0
                        val bits = MutableList(bitAmount) { true }
                        val target = MutableList(bitAmount) { true }
                        target[n] = false
                        val value = creator(bits)
                        lambda(value, false) shouldBe creator(target)
                        target[n] = true

                        // Test with one other bit set to 0
                        for (i in 0 until bitAmount) {
                            if (i == n) continue
                            bits[i] = false
                            target[i] = false
                            target[n] = false
                            val v = creator(bits)
                            lambda(v, false) shouldBe creator(target)
                            bits[i] = true
                            target[i] = true
                            target[n] = true
                        }
                    }

                    it("set to 1") {
                        // Test with only the n-th bit set to 1
                        val bits = MutableList(bitAmount) { false }
                        val target = MutableList(bitAmount) { false }
                        target[n] = true
                        val value = creator(bits)
                        lambda(value, true) shouldBe creator(target)
                        target[n] = false

                        // Test with one other bit set to 1
                        for (i in 0 until bitAmount) {
                            if (i == n) continue
                            bits[i] = true
                            target[i] = true
                            target[n] = true
                            val v = creator(bits)
                            lambda(v, true) shouldBe creator(target)
                            bits[i] = false
                            target[i] = false
                            target[n] = false
                        }
                    }
                }
            }

            describe("nth bit") {
                for (n in 0 until bitAmount) {
                    // test the n-th bit
                    describe("withBit$n") {
                        it("set to 0") {
                            // Test with only the n-th bit set to 0
                            val bits = MutableList(bitAmount) { true }
                            val target = MutableList(bitAmount) { true }
                            target[n] = false
                            val value = creator(bits)
                            nthBitLambda(value, n, false) shouldBe creator(target)
                            target[n] = true

                            // Test with one other bit set to 0
                            for (i in 0 until bitAmount) {
                                if (i == n) continue
                                bits[i] = false
                                target[i] = false
                                target[n] = false
                                val v = creator(bits)
                                nthBitLambda(v, n, false) shouldBe creator(target)
                                bits[i] = true
                                target[i] = true
                                target[n] = true
                            }
                        }

                        it("set to 1") {
                            // Test with only the n-th bit set to 1
                            val bits = MutableList(bitAmount) { false }
                            val target = MutableList(bitAmount) { false }
                            target[n] = true
                            val value = creator(bits)
                            nthBitLambda(value, n, true) shouldBe creator(target)
                            target[n] = false

                            // Test with one other bit set to 1
                            for (i in 0 until bitAmount) {
                                if (i == n) continue
                                bits[i] = true
                                target[i] = true
                                target[n] = true
                                val v = creator(bits)
                                nthBitLambda(v, n, true) shouldBe creator(target)
                                bits[i] = false
                                target[i] = false
                                target[n] = false
                            }
                        }
                    }
                }
            }
        }

        describe("With Bit") {
            describe("Byte") {
                withBitTests(
                    8,
                    { bits -> byteFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("Short") {
                withBitTests(
                    16,
                    { bits -> shortFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("Int") {
                withBitTests(
                    32,
                    { bits -> intFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                        { num, b -> num.withBit16(b) },
                        { num, b -> num.withBit17(b) },
                        { num, b -> num.withBit18(b) },
                        { num, b -> num.withBit19(b) },
                        { num, b -> num.withBit20(b) },
                        { num, b -> num.withBit21(b) },
                        { num, b -> num.withBit22(b) },
                        { num, b -> num.withBit23(b) },
                        { num, b -> num.withBit24(b) },
                        { num, b -> num.withBit25(b) },
                        { num, b -> num.withBit26(b) },
                        { num, b -> num.withBit27(b) },
                        { num, b -> num.withBit28(b) },
                        { num, b -> num.withBit29(b) },
                        { num, b -> num.withBit30(b) },
                        { num, b -> num.withBit31(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("Long") {
                withBitTests(
                    64,
                    { bits -> longFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                        { num, b -> num.withBit16(b) },
                        { num, b -> num.withBit17(b) },
                        { num, b -> num.withBit18(b) },
                        { num, b -> num.withBit19(b) },
                        { num, b -> num.withBit20(b) },
                        { num, b -> num.withBit21(b) },
                        { num, b -> num.withBit22(b) },
                        { num, b -> num.withBit23(b) },
                        { num, b -> num.withBit24(b) },
                        { num, b -> num.withBit25(b) },
                        { num, b -> num.withBit26(b) },
                        { num, b -> num.withBit27(b) },
                        { num, b -> num.withBit28(b) },
                        { num, b -> num.withBit29(b) },
                        { num, b -> num.withBit30(b) },
                        { num, b -> num.withBit31(b) },
                        { num, b -> num.withBit32(b) },
                        { num, b -> num.withBit33(b) },
                        { num, b -> num.withBit34(b) },
                        { num, b -> num.withBit35(b) },
                        { num, b -> num.withBit36(b) },
                        { num, b -> num.withBit37(b) },
                        { num, b -> num.withBit38(b) },
                        { num, b -> num.withBit39(b) },
                        { num, b -> num.withBit40(b) },
                        { num, b -> num.withBit41(b) },
                        { num, b -> num.withBit42(b) },
                        { num, b -> num.withBit43(b) },
                        { num, b -> num.withBit44(b) },
                        { num, b -> num.withBit45(b) },
                        { num, b -> num.withBit46(b) },
                        { num, b -> num.withBit47(b) },
                        { num, b -> num.withBit48(b) },
                        { num, b -> num.withBit49(b) },
                        { num, b -> num.withBit50(b) },
                        { num, b -> num.withBit51(b) },
                        { num, b -> num.withBit52(b) },
                        { num, b -> num.withBit53(b) },
                        { num, b -> num.withBit54(b) },
                        { num, b -> num.withBit55(b) },
                        { num, b -> num.withBit56(b) },
                        { num, b -> num.withBit57(b) },
                        { num, b -> num.withBit58(b) },
                        { num, b -> num.withBit59(b) },
                        { num, b -> num.withBit60(b) },
                        { num, b -> num.withBit61(b) },
                        { num, b -> num.withBit62(b) },
                        { num, b -> num.withBit63(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            runInEnvironments(listOf(TestEnvironment.JVM)) {
                describe("Float") {
                    withBitTests(
                        32,
                        { bits -> floatFromBits(bits) },
                        listOf(
                            { num, b -> num.withBit0(b) },
                            { num, b -> num.withBit1(b) },
                            { num, b -> num.withBit2(b) },
                            { num, b -> num.withBit3(b) },
                            { num, b -> num.withBit4(b) },
                            { num, b -> num.withBit5(b) },
                            { num, b -> num.withBit6(b) },
                            { num, b -> num.withBit7(b) },
                            { num, b -> num.withBit8(b) },
                            { num, b -> num.withBit9(b) },
                            { num, b -> num.withBit10(b) },
                            { num, b -> num.withBit11(b) },
                            { num, b -> num.withBit12(b) },
                            { num, b -> num.withBit13(b) },
                            { num, b -> num.withBit14(b) },
                            { num, b -> num.withBit15(b) },
                            { num, b -> num.withBit16(b) },
                            { num, b -> num.withBit17(b) },
                            { num, b -> num.withBit18(b) },
                            { num, b -> num.withBit19(b) },
                            { num, b -> num.withBit20(b) },
                            { num, b -> num.withBit21(b) },
                            { num, b -> num.withBit22(b) },
                            { num, b -> num.withBit23(b) },
                            { num, b -> num.withBit24(b) },
                            { num, b -> num.withBit25(b) },
                            { num, b -> num.withBit26(b) },
                            { num, b -> num.withBit27(b) },
                            { num, b -> num.withBit28(b) },
                            { num, b -> num.withBit29(b) },
                            { num, b -> num.withBit30(b) },
                            { num, b -> num.withBit31(b) },
                        ),
                        { value, n, b -> value.withBit(n, b) },
                    )
                }

                describe("Double") {
                    withBitTests(
                        64,
                        { bits -> doubleFromBits(bits) },
                        listOf(
                            { num, b -> num.withBit0(b) },
                            { num, b -> num.withBit1(b) },
                            { num, b -> num.withBit2(b) },
                            { num, b -> num.withBit3(b) },
                            { num, b -> num.withBit4(b) },
                            { num, b -> num.withBit5(b) },
                            { num, b -> num.withBit6(b) },
                            { num, b -> num.withBit7(b) },
                            { num, b -> num.withBit8(b) },
                            { num, b -> num.withBit9(b) },
                            { num, b -> num.withBit10(b) },
                            { num, b -> num.withBit11(b) },
                            { num, b -> num.withBit12(b) },
                            { num, b -> num.withBit13(b) },
                            { num, b -> num.withBit14(b) },
                            { num, b -> num.withBit15(b) },
                            { num, b -> num.withBit16(b) },
                            { num, b -> num.withBit17(b) },
                            { num, b -> num.withBit18(b) },
                            { num, b -> num.withBit19(b) },
                            { num, b -> num.withBit20(b) },
                            { num, b -> num.withBit21(b) },
                            { num, b -> num.withBit22(b) },
                            { num, b -> num.withBit23(b) },
                            { num, b -> num.withBit24(b) },
                            { num, b -> num.withBit25(b) },
                            { num, b -> num.withBit26(b) },
                            { num, b -> num.withBit27(b) },
                            { num, b -> num.withBit28(b) },
                            { num, b -> num.withBit29(b) },
                            { num, b -> num.withBit30(b) },
                            { num, b -> num.withBit31(b) },
                            { num, b -> num.withBit32(b) },
                            { num, b -> num.withBit33(b) },
                            { num, b -> num.withBit34(b) },
                            { num, b -> num.withBit35(b) },
                            { num, b -> num.withBit36(b) },
                            { num, b -> num.withBit37(b) },
                            { num, b -> num.withBit38(b) },
                            { num, b -> num.withBit39(b) },
                            { num, b -> num.withBit40(b) },
                            { num, b -> num.withBit41(b) },
                            { num, b -> num.withBit42(b) },
                            { num, b -> num.withBit43(b) },
                            { num, b -> num.withBit44(b) },
                            { num, b -> num.withBit45(b) },
                            { num, b -> num.withBit46(b) },
                            { num, b -> num.withBit47(b) },
                            { num, b -> num.withBit48(b) },
                            { num, b -> num.withBit49(b) },
                            { num, b -> num.withBit50(b) },
                            { num, b -> num.withBit51(b) },
                            { num, b -> num.withBit52(b) },
                            { num, b -> num.withBit53(b) },
                            { num, b -> num.withBit54(b) },
                            { num, b -> num.withBit55(b) },
                            { num, b -> num.withBit56(b) },
                            { num, b -> num.withBit57(b) },
                            { num, b -> num.withBit58(b) },
                            { num, b -> num.withBit59(b) },
                            { num, b -> num.withBit60(b) },
                            { num, b -> num.withBit61(b) },
                            { num, b -> num.withBit62(b) },
                            { num, b -> num.withBit63(b) },
                        ),
                        { value, n, b -> value.withBit(n, b) },
                    )
                }
            }

            describe("UByte") {
                withBitTests(
                    8,
                    { bits -> ubyteFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("UShort") {
                withBitTests(
                    16,
                    { bits -> ushortFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("UInt") {
                withBitTests(
                    32,
                    { bits -> uintFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                        { num, b -> num.withBit16(b) },
                        { num, b -> num.withBit17(b) },
                        { num, b -> num.withBit18(b) },
                        { num, b -> num.withBit19(b) },
                        { num, b -> num.withBit20(b) },
                        { num, b -> num.withBit21(b) },
                        { num, b -> num.withBit22(b) },
                        { num, b -> num.withBit23(b) },
                        { num, b -> num.withBit24(b) },
                        { num, b -> num.withBit25(b) },
                        { num, b -> num.withBit26(b) },
                        { num, b -> num.withBit27(b) },
                        { num, b -> num.withBit28(b) },
                        { num, b -> num.withBit29(b) },
                        { num, b -> num.withBit30(b) },
                        { num, b -> num.withBit31(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("ULong") {
                withBitTests(
                    64,
                    { bits -> ulongFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                        { num, b -> num.withBit16(b) },
                        { num, b -> num.withBit17(b) },
                        { num, b -> num.withBit18(b) },
                        { num, b -> num.withBit19(b) },
                        { num, b -> num.withBit20(b) },
                        { num, b -> num.withBit21(b) },
                        { num, b -> num.withBit22(b) },
                        { num, b -> num.withBit23(b) },
                        { num, b -> num.withBit24(b) },
                        { num, b -> num.withBit25(b) },
                        { num, b -> num.withBit26(b) },
                        { num, b -> num.withBit27(b) },
                        { num, b -> num.withBit28(b) },
                        { num, b -> num.withBit29(b) },
                        { num, b -> num.withBit30(b) },
                        { num, b -> num.withBit31(b) },
                        { num, b -> num.withBit32(b) },
                        { num, b -> num.withBit33(b) },
                        { num, b -> num.withBit34(b) },
                        { num, b -> num.withBit35(b) },
                        { num, b -> num.withBit36(b) },
                        { num, b -> num.withBit37(b) },
                        { num, b -> num.withBit38(b) },
                        { num, b -> num.withBit39(b) },
                        { num, b -> num.withBit40(b) },
                        { num, b -> num.withBit41(b) },
                        { num, b -> num.withBit42(b) },
                        { num, b -> num.withBit43(b) },
                        { num, b -> num.withBit44(b) },
                        { num, b -> num.withBit45(b) },
                        { num, b -> num.withBit46(b) },
                        { num, b -> num.withBit47(b) },
                        { num, b -> num.withBit48(b) },
                        { num, b -> num.withBit49(b) },
                        { num, b -> num.withBit50(b) },
                        { num, b -> num.withBit51(b) },
                        { num, b -> num.withBit52(b) },
                        { num, b -> num.withBit53(b) },
                        { num, b -> num.withBit54(b) },
                        { num, b -> num.withBit55(b) },
                        { num, b -> num.withBit56(b) },
                        { num, b -> num.withBit57(b) },
                        { num, b -> num.withBit58(b) },
                        { num, b -> num.withBit59(b) },
                        { num, b -> num.withBit60(b) },
                        { num, b -> num.withBit61(b) },
                        { num, b -> num.withBit62(b) },
                        { num, b -> num.withBit63(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }

            describe("Char") {
                withBitTests(
                    16,
                    { bits -> charFromBits(bits) },
                    listOf(
                        { num, b -> num.withBit0(b) },
                        { num, b -> num.withBit1(b) },
                        { num, b -> num.withBit2(b) },
                        { num, b -> num.withBit3(b) },
                        { num, b -> num.withBit4(b) },
                        { num, b -> num.withBit5(b) },
                        { num, b -> num.withBit6(b) },
                        { num, b -> num.withBit7(b) },
                        { num, b -> num.withBit8(b) },
                        { num, b -> num.withBit9(b) },
                        { num, b -> num.withBit10(b) },
                        { num, b -> num.withBit11(b) },
                        { num, b -> num.withBit12(b) },
                        { num, b -> num.withBit13(b) },
                        { num, b -> num.withBit14(b) },
                        { num, b -> num.withBit15(b) },
                    ),
                    { value, n, b -> value.withBit(n, b) },
                )
            }
        }

        describe("fromBits") {
            describe("byteFromBits()") {
                it("should create a byte from bits") {
                    byteFromBits(listOf(false, true, false, true, false, true, false, true)) shouldBe 0b10101010.toByte()
                }
                it("should throw an exception if the list is too short") {
                    runCatching { byteFromBits(listOf(true, false, true, false, true, false, true)) }.isFailure shouldBe true
                }
                it("should throw an exception if the list is too long") {
                    runCatching { byteFromBits(listOf(true, false, true, false, true, false, true, false, true)) }.isFailure shouldBe true
                }
            }

            describe("shortFromBits()") {
                it("should create a short from bits") {
                    shortFromBits(listOf(true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false)) shouldBe 0b0101010101010101.toShort()
                }
                it("should throw an exception if the list is too short") {
                    runCatching { shortFromBits(listOf(true, false, true, false, true, false, true, false, true, false, true, false, true, false, true)) }.isFailure shouldBe true
                }
                it("should throw an exception if the list is too long") {
                    runCatching { shortFromBits(listOf(true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false, true)) }.isFailure shouldBe true
                }
            }

            describe("intFromBits()") {
                it("should create an int from bits") {
                    intFromBits(
                        listOf(
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                        ),
                    ) shouldBe 0b01010101010101010101010101010101u.toInt()
                }

                it("should throw an exception if the list is too short") {
                    runCatching {
                        intFromBits(
                            listOf(
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true,
                            ),
                        )
                    }.isFailure shouldBe true
                }
                it("should throw an exception if the list is too long") {
                    runCatching {
                        intFromBits(
                            listOf(
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true,
                            ),
                        )
                    }.isFailure shouldBe true
                }
            }

            describe("longFromBits()") {
                it("should create an int from bits") {
                    longFromBits(
                        listOf(
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                            true, false, true, false,
                        ),
                    ) shouldBe 0b0101010101010101010101010101010101010101010101010101010101010101uL.toLong()
                }

                it("should throw an exception if the list is too short") {
                    runCatching {
                        longFromBits(
                            listOf(
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true,
                            ),
                        )
                    }.isFailure shouldBe true
                }
                it("should throw an exception if the list is too long") {
                    runCatching {
                        longFromBits(
                            listOf(
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true, false, true, false,
                                true,
                            ),
                        )
                    }.isFailure shouldBe true
                }
            }
        }
    },
)
