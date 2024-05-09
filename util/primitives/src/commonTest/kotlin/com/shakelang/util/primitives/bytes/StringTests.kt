package com.shakelang.util.primitives.bytes

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class StringTests : FreeSpec(
    {

        "string to bytes" {
            val str = "Hello, world!"
            val bytes = str.toBytes()
            bytes.size shouldBe str.length
            bytes[0] shouldBe 'H'.code.toByte()
            bytes[1] shouldBe 'e'.code.toByte()
            bytes[2] shouldBe 'l'.code.toByte()
            bytes[3] shouldBe 'l'.code.toByte()
            bytes[4] shouldBe 'o'.code.toByte()
            bytes[5] shouldBe ','.code.toByte()
            bytes[6] shouldBe ' '.code.toByte()
            bytes[7] shouldBe 'w'.code.toByte()
            bytes[8] shouldBe 'o'.code.toByte()
            bytes[9] shouldBe 'r'.code.toByte()
            bytes[10] shouldBe 'l'.code.toByte()
            bytes[11] shouldBe 'd'.code.toByte()
            bytes[12] shouldBe '!'.code.toByte()
        }

        "decode hex" {
            val str = "48656c6c6f2c20776f726c6421"
            val bytes = str.decodeHexString()
            bytes.size shouldBe 13
            bytes[0] shouldBe 'H'.code.toByte()
            bytes[1] shouldBe 'e'.code.toByte()
            bytes[2] shouldBe 'l'.code.toByte()
            bytes[3] shouldBe 'l'.code.toByte()
            bytes[4] shouldBe 'o'.code.toByte()
            bytes[5] shouldBe ','.code.toByte()
            bytes[6] shouldBe ' '.code.toByte()
            bytes[7] shouldBe 'w'.code.toByte()
            bytes[8] shouldBe 'o'.code.toByte()
            bytes[9] shouldBe 'r'.code.toByte()
            bytes[10] shouldBe 'l'.code.toByte()
            bytes[11] shouldBe 'd'.code.toByte()
            bytes[12] shouldBe '!'.code.toByte()
        }
    },
)
