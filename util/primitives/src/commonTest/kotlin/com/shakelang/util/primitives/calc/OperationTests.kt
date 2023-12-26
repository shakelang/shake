package com.shakelang.util.primitives.calc

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class OperationTests : FreeSpec({

    "byte plus" {
        1.toByte().plus(1.toUByte()) shouldBe 2
        1.toByte().plus(1.toUShort()) shouldBe 2
        1.toByte().plus(1.toUInt()) shouldBe 2
        1.toByte().plus(1.toULong()) shouldBe 2
    }

    "short plus" {
        1.toShort().plus(1.toUByte()) shouldBe 2
        1.toShort().plus(1.toUShort()) shouldBe 2
        1.toShort().plus(1.toUInt()) shouldBe 2
        1.toShort().plus(1.toULong()) shouldBe 2
    }

    "int plus" {
        1.plus(1.toUByte()) shouldBe 2
        1.plus(1.toUShort()) shouldBe 2
        1.plus(1.toUInt()) shouldBe 2
        1.plus(1.toULong()) shouldBe 2
    }

    "long plus" {
        1.toLong().plus(1.toUByte()) shouldBe 2
        1.toLong().plus(1.toUShort()) shouldBe 2
        1.toLong().plus(1.toUInt()) shouldBe 2
        1.toLong().plus(1.toULong()) shouldBe 2
    }

    "byte minus" {
        1.toByte().minus(1.toUByte()) shouldBe 0
        1.toByte().minus(1.toUShort()) shouldBe 0
        1.toByte().minus(1.toUInt()) shouldBe 0
        1.toByte().minus(1.toULong()) shouldBe 0
    }

    "short minus" {
        1.toShort().minus(1.toUByte()) shouldBe 0
        1.toShort().minus(1.toUShort()) shouldBe 0
        1.toShort().minus(1.toUInt()) shouldBe 0
        1.toShort().minus(1.toULong()) shouldBe 0
    }

    "int minus" {
        1.minus(1.toUByte()) shouldBe 0
        1.minus(1.toUShort()) shouldBe 0
        1.minus(1.toUInt()) shouldBe 0
        1.minus(1.toULong()) shouldBe 0
    }

    "long minus" {
        1.toLong().minus(1.toUByte()) shouldBe 0
        1.toLong().minus(1.toUShort()) shouldBe 0
        1.toLong().minus(1.toUInt()) shouldBe 0
        1.toLong().minus(1.toULong()) shouldBe 0
    }

    "byte times" {
        2.toByte().times(4.toUByte()) shouldBe 8
        2.toByte().times(4.toUShort()) shouldBe 8
        2.toByte().times(4.toUInt()) shouldBe 8
        2.toByte().times(4.toULong()) shouldBe 8
    }

    "short times" {
        2.toShort().times(4.toUByte()) shouldBe 8
        2.toShort().times(4.toUShort()) shouldBe 8
        2.toShort().times(4.toUInt()) shouldBe 8
        2.toShort().times(4.toULong()) shouldBe 8
    }

    "int times" {
        2.times(4.toUByte()) shouldBe 8
        2.times(4.toUShort()) shouldBe 8
        2.times(4.toUInt()) shouldBe 8
        2.times(4.toULong()) shouldBe 8
    }

    "long times" {
        2.toLong().times(4.toUByte()) shouldBe 8
        2.toLong().times(4.toUShort()) shouldBe 8
        2.toLong().times(4.toUInt()) shouldBe 8
        2.toLong().times(4.toULong()) shouldBe 8
    }

    "byte div" {
        8.toByte().div(4.toUByte()) shouldBe 2
        8.toByte().div(4.toUShort()) shouldBe 2
        8.toByte().div(4.toUInt()) shouldBe 2
        8.toByte().div(4.toULong()) shouldBe 2
    }

    "short div" {
        8.toShort().div(4.toUByte()) shouldBe 2
        8.toShort().div(4.toUShort()) shouldBe 2
        8.toShort().div(4.toUInt()) shouldBe 2
        8.toShort().div(4.toULong()) shouldBe 2
    }

    "int div" {
        8.div(4.toUByte()) shouldBe 2
        8.div(4.toUShort()) shouldBe 2
        8.div(4.toUInt()) shouldBe 2
        8.div(4.toULong()) shouldBe 2
    }

    "long div" {
        8.toLong().div(4.toUByte()) shouldBe 2
        8.toLong().div(4.toUShort()) shouldBe 2
        8.toLong().div(4.toUInt()) shouldBe 2
        8.toLong().div(4.toULong()) shouldBe 2
    }

    "byte rem" {
        8.toByte().rem(4.toUByte()) shouldBe 0
        8.toByte().rem(4.toUShort()) shouldBe 0
        8.toByte().rem(4.toUInt()) shouldBe 0
        8.toByte().rem(4.toULong()) shouldBe 0
    }

    "short rem" {
        8.toShort().rem(4.toUByte()) shouldBe 0
        8.toShort().rem(4.toUShort()) shouldBe 0
        8.toShort().rem(4.toUInt()) shouldBe 0
        8.toShort().rem(4.toULong()) shouldBe 0
    }

    "int rem" {
        8.rem(4.toUByte()) shouldBe 0
        8.rem(4.toUShort()) shouldBe 0
        8.rem(4.toUInt()) shouldBe 0
        8.rem(4.toULong()) shouldBe 0
    }

    "long rem" {
        8.toLong().rem(4.toUByte()) shouldBe 0
        8.toLong().rem(4.toUShort()) shouldBe 0
        8.toLong().rem(4.toUInt()) shouldBe 0
        8.toLong().rem(4.toULong()) shouldBe 0
    }

    "ubyte plus" {
        1.toUByte().plus(1.toByte()) shouldBe 2
        1.toUByte().plus(1.toShort()) shouldBe 2
        1.toUByte().plus(1.toInt()) shouldBe 2
        1.toUByte().plus(1.toLong()) shouldBe 2
    }

    "ushort plus" {
        1.toUShort().plus(1.toByte()) shouldBe 2
        1.toUShort().plus(1.toShort()) shouldBe 2
        1.toUShort().plus(1.toInt()) shouldBe 2
        1.toUShort().plus(1.toLong()) shouldBe 2
    }

    "uint plus" {
        1.toUInt().plus(1.toByte()) shouldBe 2
        1.toUInt().plus(1.toShort()) shouldBe 2
        1.toUInt().plus(1.toInt()) shouldBe 2
        1.toUInt().plus(1.toLong()) shouldBe 2
    }

    "ulong plus" {
        1.toULong().plus(1.toByte()) shouldBe 2
        1.toULong().plus(1.toShort()) shouldBe 2
        1.toULong().plus(1.toInt()) shouldBe 2
        1.toULong().plus(1.toLong()) shouldBe 2
    }

    "ubyte minus" {
        1.toUByte().minus(1.toByte()) shouldBe 0
        1.toUByte().minus(1.toShort()) shouldBe 0
        1.toUByte().minus(1.toInt()) shouldBe 0
        1.toUByte().minus(1.toLong()) shouldBe 0
    }

    "ushort minus" {
        1.toUShort().minus(1.toByte()) shouldBe 0
        1.toUShort().minus(1.toShort()) shouldBe 0
        1.toUShort().minus(1.toInt()) shouldBe 0
        1.toUShort().minus(1.toLong()) shouldBe 0
    }

    "uint minus" {
        1.toUInt().minus(1.toByte()) shouldBe 0
        1.toUInt().minus(1.toShort()) shouldBe 0
        1.toUInt().minus(1.toInt()) shouldBe 0
        1.toUInt().minus(1.toLong()) shouldBe 0
    }

    "ulong minus" {
        1.toULong().minus(1.toByte()) shouldBe 0
        1.toULong().minus(1.toShort()) shouldBe 0
        1.toULong().minus(1.toInt()) shouldBe 0
        1.toULong().minus(1.toLong()) shouldBe 0
    }

    "ubyte times" {
        1.toUByte().times(1.toByte()) shouldBe 1
        1.toUByte().times(1.toShort()) shouldBe 1
        1.toUByte().times(1.toInt()) shouldBe 1
        1.toUByte().times(1.toLong()) shouldBe 1
    }

    "ushort times" {
        1.toUShort().times(1.toByte()) shouldBe 1
        1.toUShort().times(1.toShort()) shouldBe 1
        1.toUShort().times(1.toInt()) shouldBe 1
        1.toUShort().times(1.toLong()) shouldBe 1
    }

    "uint times" {
        1.toUInt().times(1.toByte()) shouldBe 1
        1.toUInt().times(1.toShort()) shouldBe 1
        1.toUInt().times(1.toInt()) shouldBe 1
        1.toUInt().times(1.toLong()) shouldBe 1
    }

    "ulong times" {
        1.toULong().times(1.toByte()) shouldBe 1
        1.toULong().times(1.toShort()) shouldBe 1
        1.toULong().times(1.toInt()) shouldBe 1
        1.toULong().times(1.toLong()) shouldBe 1
    }

    "ubyte div" {
        1.toUByte().div(1.toByte()) shouldBe 1
        1.toUByte().div(1.toShort()) shouldBe 1
        1.toUByte().div(1.toInt()) shouldBe 1
        1.toUByte().div(1.toLong()) shouldBe 1
    }

    "ushort div" {
        1.toUShort().div(1.toByte()) shouldBe 1
        1.toUShort().div(1.toShort()) shouldBe 1
        1.toUShort().div(1.toInt()) shouldBe 1
        1.toUShort().div(1.toLong()) shouldBe 1
    }

    "uint div" {
        1.toUInt().div(1.toByte()) shouldBe 1
        1.toUInt().div(1.toShort()) shouldBe 1
        1.toUInt().div(1.toInt()) shouldBe 1
        1.toUInt().div(1.toLong()) shouldBe 1
    }

    "ulong div" {
        1.toULong().div(1.toByte()) shouldBe 1
        1.toULong().div(1.toShort()) shouldBe 1
        1.toULong().div(1.toInt()) shouldBe 1
        1.toULong().div(1.toLong()) shouldBe 1
    }

    "ubyte rem" {
        1.toUByte().rem(1.toByte()) shouldBe 0
        1.toUByte().rem(1.toShort()) shouldBe 0
        1.toUByte().rem(1.toInt()) shouldBe 0
        1.toUByte().rem(1.toLong()) shouldBe 0
    }

    "ushort rem" {
        1.toUShort().rem(1.toByte()) shouldBe 0
        1.toUShort().rem(1.toShort()) shouldBe 0
        1.toUShort().rem(1.toInt()) shouldBe 0
        1.toUShort().rem(1.toLong()) shouldBe 0
    }

    "uint rem" {
        1.toUInt().rem(1.toByte()) shouldBe 0
        1.toUInt().rem(1.toShort()) shouldBe 0
        1.toUInt().rem(1.toInt()) shouldBe 0
        1.toUInt().rem(1.toLong()) shouldBe 0
    }

    "ulong rem" {
        1.toULong().rem(1.toByte()) shouldBe 0
        1.toULong().rem(1.toShort()) shouldBe 0
        1.toULong().rem(1.toInt()) shouldBe 0
        1.toULong().rem(1.toLong()) shouldBe 0
    }
})
