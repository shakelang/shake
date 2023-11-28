@file:Suppress("unused")

package com.shakelang.shake.util.primitives.calc

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class SHLTest : FreeSpec({

    "byte shl byte" {
        (0b01.toByte() shl 1.toByte()) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2.toByte()) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3.toByte()) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4.toByte()) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5.toByte()) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6.toByte()) shouldBe 0b1000000.toByte()
    }

    "byte shl short" {
        (0b01.toByte() shl 1.toShort()) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2.toShort()) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3.toShort()) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4.toShort()) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5.toShort()) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6.toShort()) shouldBe 0b1000000.toByte()
    }

    "byte shl int" {
        (0b01.toByte() shl 1) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6) shouldBe 0b1000000.toByte()
    }

    "byte shl long" {
        (0b01.toByte() shl 1L) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2L) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3L) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4L) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5L) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6L) shouldBe 0b1000000.toByte()
    }

    "byte shl unsigned byte" {
        (0b01.toByte() shl 1u.toUByte()) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2u.toUByte()) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3u.toUByte()) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4u.toUByte()) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5u.toUByte()) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6u.toUByte()) shouldBe 0b1000000.toByte()
    }

    "byte shl unsigned short" {
        (0b01.toByte() shl 1u.toUShort()) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2u.toUShort()) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3u.toUShort()) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4u.toUShort()) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5u.toUShort()) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6u.toUShort()) shouldBe 0b1000000.toByte()
    }

    "byte shl unsigned int" {
        (0b01.toByte() shl 1u) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2u) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3u) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4u) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5u) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6u) shouldBe 0b1000000.toByte()
    }

    "byte shl unsigned long" {
        (0b01.toByte() shl 1uL) shouldBe 0b10.toByte()
        (0b01.toByte() shl 2uL) shouldBe 0b100.toByte()
        (0b01.toByte() shl 3uL) shouldBe 0b1000.toByte()
        (0b01.toByte() shl 4uL) shouldBe 0b10000.toByte()
        (0b01.toByte() shl 5uL) shouldBe 0b100000.toByte()
        (0b01.toByte() shl 6uL) shouldBe 0b1000000.toByte()
    }

    "short shl byte" {
        (0b01.toShort() shl 1.toByte()) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2.toByte()) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3.toByte()) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4.toByte()) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5.toByte()) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6.toByte()) shouldBe 0b1000000.toShort()
    }

    "short shl short" {
        (0b01.toShort() shl 1.toShort()) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2.toShort()) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3.toShort()) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4.toShort()) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5.toShort()) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6.toShort()) shouldBe 0b1000000.toShort()
    }

    "short shl int" {
        (0b01.toShort() shl 1) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6) shouldBe 0b1000000.toShort()
    }

    "short shl long" {
        (0b01.toShort() shl 1L) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2L) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3L) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4L) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5L) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6L) shouldBe 0b1000000.toShort()
    }

    "short shl unsigned byte" {
        (0b01.toShort() shl 1u.toUByte()) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2u.toUByte()) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3u.toUByte()) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4u.toUByte()) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5u.toUByte()) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6u.toUByte()) shouldBe 0b1000000.toShort()
    }

    "short shl unsigned short" {
        (0b01.toShort() shl 1u.toUShort()) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2u.toUShort()) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3u.toUShort()) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4u.toUShort()) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5u.toUShort()) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6u.toUShort()) shouldBe 0b1000000.toShort()
    }

    "short shl unsigned int" {
        (0b01.toShort() shl 1u) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2u) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3u) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4u) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5u) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6u) shouldBe 0b1000000.toShort()
    }

    "short shl unsigned long" {
        (0b01.toShort() shl 1uL) shouldBe 0b10.toShort()
        (0b01.toShort() shl 2uL) shouldBe 0b100.toShort()
        (0b01.toShort() shl 3uL) shouldBe 0b1000.toShort()
        (0b01.toShort() shl 4uL) shouldBe 0b10000.toShort()
        (0b01.toShort() shl 5uL) shouldBe 0b100000.toShort()
        (0b01.toShort() shl 6uL) shouldBe 0b1000000.toShort()
    }

    "int shl byte" {
        (0b01 shl 1.toByte()) shouldBe 0b10
        (0b01 shl 2.toByte()) shouldBe 0b100
        (0b01 shl 3.toByte()) shouldBe 0b1000
        (0b01 shl 4.toByte()) shouldBe 0b10000
        (0b01 shl 5.toByte()) shouldBe 0b100000
        (0b01 shl 6.toByte()) shouldBe 0b1000000
    }

    "int shl short" {
        (0b01 shl 1.toShort()) shouldBe 0b10
        (0b01 shl 2.toShort()) shouldBe 0b100
        (0b01 shl 3.toShort()) shouldBe 0b1000
        (0b01 shl 4.toShort()) shouldBe 0b10000
        (0b01 shl 5.toShort()) shouldBe 0b100000
        (0b01 shl 6.toShort()) shouldBe 0b1000000
    }

    "int shl int" {
        (0b01 shl 1) shouldBe 0b10
        (0b01 shl 2) shouldBe 0b100
        (0b01 shl 3) shouldBe 0b1000
        (0b01 shl 4) shouldBe 0b10000
        (0b01 shl 5) shouldBe 0b100000
        (0b01 shl 6) shouldBe 0b1000000
    }

    "int shl long" {
        (0b01 shl 1L) shouldBe 0b10
        (0b01 shl 2L) shouldBe 0b100
        (0b01 shl 3L) shouldBe 0b1000
        (0b01 shl 4L) shouldBe 0b10000
        (0b01 shl 5L) shouldBe 0b100000
        (0b01 shl 6L) shouldBe 0b1000000
    }

    "int shl unsigned byte" {
        (0b01 shl 1u.toUByte()) shouldBe 0b10
        (0b01 shl 2u.toUByte()) shouldBe 0b100
        (0b01 shl 3u.toUByte()) shouldBe 0b1000
        (0b01 shl 4u.toUByte()) shouldBe 0b10000
        (0b01 shl 5u.toUByte()) shouldBe 0b100000
        (0b01 shl 6u.toUByte()) shouldBe 0b1000000
    }

    "int shl unsigned short" {
        (0b01 shl 1u.toUShort()) shouldBe 0b10
        (0b01 shl 2u.toUShort()) shouldBe 0b100
        (0b01 shl 3u.toUShort()) shouldBe 0b1000
        (0b01 shl 4u.toUShort()) shouldBe 0b10000
        (0b01 shl 5u.toUShort()) shouldBe 0b100000
        (0b01 shl 6u.toUShort()) shouldBe 0b1000000
    }

    "int shl unsigned int" {
        (0b01 shl 1u) shouldBe 0b10
        (0b01 shl 2u) shouldBe 0b100
        (0b01 shl 3u) shouldBe 0b1000
        (0b01 shl 4u) shouldBe 0b10000
        (0b01 shl 5u) shouldBe 0b100000
        (0b01 shl 6u) shouldBe 0b1000000
    }

    "int shl unsigned long" {
        (0b01 shl 1uL) shouldBe 0b10
        (0b01 shl 2uL) shouldBe 0b100
        (0b01 shl 3uL) shouldBe 0b1000
        (0b01 shl 4uL) shouldBe 0b10000
        (0b01 shl 5uL) shouldBe 0b100000
        (0b01 shl 6uL) shouldBe 0b1000000
    }

    "long shl byte" {
        (0b01L shl 1.toByte()) shouldBe 0b10L
        (0b01L shl 2.toByte()) shouldBe 0b100L
        (0b01L shl 3.toByte()) shouldBe 0b1000L
        (0b01L shl 4.toByte()) shouldBe 0b10000L
        (0b01L shl 5.toByte()) shouldBe 0b100000L
        (0b01L shl 6.toByte()) shouldBe 0b1000000L
    }

    "long shl short" {
        (0b01L shl 1.toShort()) shouldBe 0b10L
        (0b01L shl 2.toShort()) shouldBe 0b100L
        (0b01L shl 3.toShort()) shouldBe 0b1000L
        (0b01L shl 4.toShort()) shouldBe 0b10000L
        (0b01L shl 5.toShort()) shouldBe 0b100000L
        (0b01L shl 6.toShort()) shouldBe 0b1000000L
    }

    "long shl int" {
        (0b01L shl 1) shouldBe 0b10L
        (0b01L shl 2) shouldBe 0b100L
        (0b01L shl 3) shouldBe 0b1000L
        (0b01L shl 4) shouldBe 0b10000L
        (0b01L shl 5) shouldBe 0b100000L
        (0b01L shl 6) shouldBe 0b1000000L
    }

    "long shl long" {
        (0b01L shl 1L) shouldBe 0b10L
        (0b01L shl 2L) shouldBe 0b100L
        (0b01L shl 3L) shouldBe 0b1000L
        (0b01L shl 4L) shouldBe 0b10000L
        (0b01L shl 5L) shouldBe 0b100000L
        (0b01L shl 6L) shouldBe 0b1000000L
    }

    "long shl unsigned byte" {
        (0b01L shl 1u.toUByte()) shouldBe 0b10L
        (0b01L shl 2u.toUByte()) shouldBe 0b100L
        (0b01L shl 3u.toUByte()) shouldBe 0b1000L
        (0b01L shl 4u.toUByte()) shouldBe 0b10000L
        (0b01L shl 5u.toUByte()) shouldBe 0b100000L
        (0b01L shl 6u.toUByte()) shouldBe 0b1000000L
    }

    "long shl unsigned short" {
        (0b01L shl 1u.toUShort()) shouldBe 0b10L
        (0b01L shl 2u.toUShort()) shouldBe 0b100L
        (0b01L shl 3u.toUShort()) shouldBe 0b1000L
        (0b01L shl 4u.toUShort()) shouldBe 0b10000L
        (0b01L shl 5u.toUShort()) shouldBe 0b100000L
        (0b01L shl 6u.toUShort()) shouldBe 0b1000000L
    }

    "long shl unsigned int" {
        (0b01L shl 1u) shouldBe 0b10L
        (0b01L shl 2u) shouldBe 0b100L
        (0b01L shl 3u) shouldBe 0b1000L
        (0b01L shl 4u) shouldBe 0b10000L
        (0b01L shl 5u) shouldBe 0b100000L
        (0b01L shl 6u) shouldBe 0b1000000L
    }

    "long shl unsigned long" {
        (0b01L shl 1uL) shouldBe 0b10L
        (0b01L shl 2uL) shouldBe 0b100L
        (0b01L shl 3uL) shouldBe 0b1000L
        (0b01L shl 4uL) shouldBe 0b10000L
        (0b01L shl 5uL) shouldBe 0b100000L
        (0b01L shl 6uL) shouldBe 0b1000000L
    }

    "unsigned byte shl byte" {
        (0b01u.toUByte() shl 1.toByte()) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2.toByte()) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3.toByte()) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4.toByte()) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5.toByte()) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6.toByte()) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl short" {
        (0b01u.toUByte() shl 1.toShort()) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2.toShort()) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3.toShort()) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4.toShort()) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5.toShort()) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6.toShort()) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl int" {
        (0b01u.toUByte() shl 1) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl long" {
        (0b01u.toUByte() shl 1L) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2L) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3L) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4L) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5L) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6L) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl unsigned byte" {
        (0b01u.toUByte() shl 1u.toUByte()) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2u.toUByte()) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3u.toUByte()) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4u.toUByte()) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5u.toUByte()) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6u.toUByte()) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl unsigned short" {
        (0b01u.toUByte() shl 1u.toUShort()) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2u.toUShort()) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3u.toUShort()) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4u.toUShort()) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5u.toUShort()) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6u.toUShort()) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl unsigned int" {
        (0b01u.toUByte() shl 1u) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2u) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3u) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4u) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5u) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6u) shouldBe 0b1000000u.toUByte()
    }

    "unsigned byte shl unsigned long" {
        (0b01u.toUByte() shl 1uL) shouldBe 0b10u.toUByte()
        (0b01u.toUByte() shl 2uL) shouldBe 0b100u.toUByte()
        (0b01u.toUByte() shl 3uL) shouldBe 0b1000u.toUByte()
        (0b01u.toUByte() shl 4uL) shouldBe 0b10000u.toUByte()
        (0b01u.toUByte() shl 5uL) shouldBe 0b100000u.toUByte()
        (0b01u.toUByte() shl 6uL) shouldBe 0b1000000u.toUByte()
    }

    "unsigned short shl byte" {
        (0b01u.toUShort() shl 1.toByte()) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2.toByte()) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3.toByte()) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4.toByte()) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5.toByte()) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6.toByte()) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl short" {
        (0b01u.toUShort() shl 1.toShort()) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2.toShort()) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3.toShort()) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4.toShort()) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5.toShort()) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6.toShort()) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl int" {
        (0b01u.toUShort() shl 1) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl long" {
        (0b01u.toUShort() shl 1L) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2L) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3L) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4L) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5L) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6L) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl unsigned byte" {
        (0b01u.toUShort() shl 1u.toUByte()) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2u.toUByte()) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3u.toUByte()) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4u.toUByte()) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5u.toUByte()) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6u.toUByte()) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl unsigned short" {
        (0b01u.toUShort() shl 1u.toUShort()) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2u.toUShort()) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3u.toUShort()) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4u.toUShort()) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5u.toUShort()) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6u.toUShort()) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl unsigned int" {
        (0b01u.toUShort() shl 1u) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2u) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3u) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4u) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5u) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6u) shouldBe 0b1000000u.toUShort()
    }

    "unsigned short shl unsigned long" {
        (0b01u.toUShort() shl 1uL) shouldBe 0b10u.toUShort()
        (0b01u.toUShort() shl 2uL) shouldBe 0b100u.toUShort()
        (0b01u.toUShort() shl 3uL) shouldBe 0b1000u.toUShort()
        (0b01u.toUShort() shl 4uL) shouldBe 0b10000u.toUShort()
        (0b01u.toUShort() shl 5uL) shouldBe 0b100000u.toUShort()
        (0b01u.toUShort() shl 6uL) shouldBe 0b1000000u.toUShort()
    }

    "unsigned int shl byte" {
        (0b01u shl 1.toByte()) shouldBe 0b10u
        (0b01u shl 2.toByte()) shouldBe 0b100u
        (0b01u shl 3.toByte()) shouldBe 0b1000u
        (0b01u shl 4.toByte()) shouldBe 0b10000u
        (0b01u shl 5.toByte()) shouldBe 0b100000u
        (0b01u shl 6.toByte()) shouldBe 0b1000000u
    }

    "unsigned int shl short" {
        (0b01u shl 1.toShort()) shouldBe 0b10u
        (0b01u shl 2.toShort()) shouldBe 0b100u
        (0b01u shl 3.toShort()) shouldBe 0b1000u
        (0b01u shl 4.toShort()) shouldBe 0b10000u
        (0b01u shl 5.toShort()) shouldBe 0b100000u
        (0b01u shl 6.toShort()) shouldBe 0b1000000u
    }

    "unsigned int shl int" {
        (0b01u shl 1) shouldBe 0b10u
        (0b01u shl 2) shouldBe 0b100u
        (0b01u shl 3) shouldBe 0b1000u
        (0b01u shl 4) shouldBe 0b10000u
        (0b01u shl 5) shouldBe 0b100000u
        (0b01u shl 6) shouldBe 0b1000000u
    }

    "unsigned int shl long" {
        (0b01u shl 1L) shouldBe 0b10u
        (0b01u shl 2L) shouldBe 0b100u
        (0b01u shl 3L) shouldBe 0b1000u
        (0b01u shl 4L) shouldBe 0b10000u
        (0b01u shl 5L) shouldBe 0b100000u
        (0b01u shl 6L) shouldBe 0b1000000u
    }

    "unsigned int shl unsigned byte" {
        (0b01u shl 1u.toUByte()) shouldBe 0b10u
        (0b01u shl 2u.toUByte()) shouldBe 0b100u
        (0b01u shl 3u.toUByte()) shouldBe 0b1000u
        (0b01u shl 4u.toUByte()) shouldBe 0b10000u
        (0b01u shl 5u.toUByte()) shouldBe 0b100000u
        (0b01u shl 6u.toUByte()) shouldBe 0b1000000u
    }

    "unsigned int shl unsigned short" {
        (0b01u shl 1u.toUShort()) shouldBe 0b10u
        (0b01u shl 2u.toUShort()) shouldBe 0b100u
        (0b01u shl 3u.toUShort()) shouldBe 0b1000u
        (0b01u shl 4u.toUShort()) shouldBe 0b10000u
        (0b01u shl 5u.toUShort()) shouldBe 0b100000u
        (0b01u shl 6u.toUShort()) shouldBe 0b1000000u
    }

    "unsigned int shl unsigned int" {
        (0b01u shl 1u) shouldBe 0b10u
        (0b01u shl 2u) shouldBe 0b100u
        (0b01u shl 3u) shouldBe 0b1000u
        (0b01u shl 4u) shouldBe 0b10000u
        (0b01u shl 5u) shouldBe 0b100000u
        (0b01u shl 6u) shouldBe 0b1000000u
    }

    "unsigned int shl unsigned long" {
        (0b01u shl 1uL) shouldBe 0b10u
        (0b01u shl 2uL) shouldBe 0b100u
        (0b01u shl 3uL) shouldBe 0b1000u
        (0b01u shl 4uL) shouldBe 0b10000u
        (0b01u shl 5uL) shouldBe 0b100000u
        (0b01u shl 6uL) shouldBe 0b1000000u
    }

    "unsigned long shl byte" {
        (0b01uL shl 1.toByte()) shouldBe 0b10uL
        (0b01uL shl 2.toByte()) shouldBe 0b100uL
        (0b01uL shl 3.toByte()) shouldBe 0b1000uL
        (0b01uL shl 4.toByte()) shouldBe 0b10000uL
        (0b01uL shl 5.toByte()) shouldBe 0b100000uL
        (0b01uL shl 6.toByte()) shouldBe 0b1000000uL
    }

    "unsigned long shl short" {
        (0b01uL shl 1.toShort()) shouldBe 0b10uL
        (0b01uL shl 2.toShort()) shouldBe 0b100uL
        (0b01uL shl 3.toShort()) shouldBe 0b1000uL
        (0b01uL shl 4.toShort()) shouldBe 0b10000uL
        (0b01uL shl 5.toShort()) shouldBe 0b100000uL
        (0b01uL shl 6.toShort()) shouldBe 0b1000000uL
    }

    "unsigned long shl int" {
        (0b01uL shl 1) shouldBe 0b10uL
        (0b01uL shl 2) shouldBe 0b100uL
        (0b01uL shl 3) shouldBe 0b1000uL
        (0b01uL shl 4) shouldBe 0b10000uL
        (0b01uL shl 5) shouldBe 0b100000uL
        (0b01uL shl 6) shouldBe 0b1000000uL
    }

    "unsigned long shl long" {
        (0b01uL shl 1L) shouldBe 0b10uL
        (0b01uL shl 2L) shouldBe 0b100uL
        (0b01uL shl 3L) shouldBe 0b1000uL
        (0b01uL shl 4L) shouldBe 0b10000uL
        (0b01uL shl 5L) shouldBe 0b100000uL
        (0b01uL shl 6L) shouldBe 0b1000000uL
    }

    "unsigned long shl unsigned byte" {
        (0b01uL shl 1u.toUByte()) shouldBe 0b10uL
        (0b01uL shl 2u.toUByte()) shouldBe 0b100uL
        (0b01uL shl 3u.toUByte()) shouldBe 0b1000uL
        (0b01uL shl 4u.toUByte()) shouldBe 0b10000uL
        (0b01uL shl 5u.toUByte()) shouldBe 0b100000uL
        (0b01uL shl 6u.toUByte()) shouldBe 0b1000000uL
    }

    "unsigned long shl unsigned short" {
        (0b01uL shl 1u.toUShort()) shouldBe 0b10uL
        (0b01uL shl 2u.toUShort()) shouldBe 0b100uL
        (0b01uL shl 3u.toUShort()) shouldBe 0b1000uL
        (0b01uL shl 4u.toUShort()) shouldBe 0b10000uL
        (0b01uL shl 5u.toUShort()) shouldBe 0b100000uL
        (0b01uL shl 6u.toUShort()) shouldBe 0b1000000uL
    }

    "unsigned long shl unsigned int" {
        (0b01uL shl 1u) shouldBe 0b10uL
        (0b01uL shl 2u) shouldBe 0b100uL
        (0b01uL shl 3u) shouldBe 0b1000uL
        (0b01uL shl 4u) shouldBe 0b10000uL
        (0b01uL shl 5u) shouldBe 0b100000uL
        (0b01uL shl 6u) shouldBe 0b1000000uL
    }

    "unsigned long shl unsigned long" {
        (0b01uL shl 1uL) shouldBe 0b10uL
        (0b01uL shl 2uL) shouldBe 0b100uL
        (0b01uL shl 3uL) shouldBe 0b1000uL
        (0b01uL shl 4uL) shouldBe 0b10000uL
        (0b01uL shl 5uL) shouldBe 0b100000uL
        (0b01uL shl 6uL) shouldBe 0b1000000uL
    }
})

class SHRTest : FreeSpec({
    "byte shr byte" {
        (0b10.toByte() shr 1.toByte()) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2.toByte()) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3.toByte()) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4.toByte()) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5.toByte()) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6.toByte()) shouldBe 0b01.toByte()
    }

    "byte shr short" {
        (0b10.toByte() shr 1.toShort()) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2.toShort()) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3.toShort()) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4.toShort()) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5.toShort()) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6.toShort()) shouldBe 0b01.toByte()
    }

    "byte shr int" {
        (0b10.toByte() shr 1) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6) shouldBe 0b01.toByte()
    }

    "byte shr long" {
        (0b10.toByte() shr 1L) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2L) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3L) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4L) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5L) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6L) shouldBe 0b01.toByte()
    }

    "byte shr unsigned byte" {
        (0b10.toByte() shr 1u.toUByte()) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2u.toUByte()) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3u.toUByte()) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4u.toUByte()) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5u.toUByte()) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6u.toUByte()) shouldBe 0b01.toByte()
    }

    "byte shr unsigned short" {
        (0b10.toByte() shr 1u.toUShort()) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2u.toUShort()) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3u.toUShort()) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4u.toUShort()) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5u.toUShort()) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6u.toUShort()) shouldBe 0b01.toByte()
    }

    "byte shr unsigned int" {
        (0b10.toByte() shr 1u) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2u) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3u) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4u) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5u) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6u) shouldBe 0b01.toByte()
    }

    "byte shr unsigned long" {
        (0b10.toByte() shr 1uL) shouldBe 0b01.toByte()
        (0b100.toByte() shr 2uL) shouldBe 0b01.toByte()
        (0b1000.toByte() shr 3uL) shouldBe 0b01.toByte()
        (0b10000.toByte() shr 4uL) shouldBe 0b01.toByte()
        (0b100000.toByte() shr 5uL) shouldBe 0b01.toByte()
        (0b1000000.toByte() shr 6uL) shouldBe 0b01.toByte()
    }

    "short shr byte" {
        (0b10.toShort() shr 1.toByte()) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2.toByte()) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3.toByte()) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4.toByte()) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5.toByte()) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6.toByte()) shouldBe 0b01.toShort()
    }

    "short shr short" {
        (0b10.toShort() shr 1.toShort()) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2.toShort()) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3.toShort()) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4.toShort()) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5.toShort()) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6.toShort()) shouldBe 0b01.toShort()
    }

    "short shr int" {
        (0b10.toShort() shr 1) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6) shouldBe 0b01.toShort()
    }

    "short shr long" {
        (0b10.toShort() shr 1L) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2L) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3L) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4L) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5L) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6L) shouldBe 0b01.toShort()
    }

    "short shr unsigned byte" {
        (0b10.toShort() shr 1u.toUByte()) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2u.toUByte()) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3u.toUByte()) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4u.toUByte()) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5u.toUByte()) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6u.toUByte()) shouldBe 0b01.toShort()
    }

    "short shr unsigned short" {
        (0b10.toShort() shr 1u.toUShort()) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2u.toUShort()) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3u.toUShort()) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4u.toUShort()) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5u.toUShort()) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6u.toUShort()) shouldBe 0b01.toShort()
    }

    "short shr unsigned int" {
        (0b10.toShort() shr 1u) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2u) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3u) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4u) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5u) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6u) shouldBe 0b01.toShort()
    }

    "short shr unsigned long" {
        (0b10.toShort() shr 1uL) shouldBe 0b01.toShort()
        (0b100.toShort() shr 2uL) shouldBe 0b01.toShort()
        (0b1000.toShort() shr 3uL) shouldBe 0b01.toShort()
        (0b10000.toShort() shr 4uL) shouldBe 0b01.toShort()
        (0b100000.toShort() shr 5uL) shouldBe 0b01.toShort()
        (0b1000000.toShort() shr 6uL) shouldBe 0b01.toShort()
    }

    "int shr byte" {
        (0b10 shr 1.toByte()) shouldBe 0b01
        (0b100 shr 2.toByte()) shouldBe 0b01
        (0b1000 shr 3.toByte()) shouldBe 0b01
        (0b10000 shr 4.toByte()) shouldBe 0b01
        (0b100000 shr 5.toByte()) shouldBe 0b01
        (0b1000000 shr 6.toByte()) shouldBe 0b01
    }

    "int shr short" {
        (0b10 shr 1.toShort()) shouldBe 0b01
        (0b100 shr 2.toShort()) shouldBe 0b01
        (0b1000 shr 3.toShort()) shouldBe 0b01
        (0b10000 shr 4.toShort()) shouldBe 0b01
        (0b100000 shr 5.toShort()) shouldBe 0b01
        (0b1000000 shr 6.toShort()) shouldBe 0b01
    }

    "int shr int" {
        (0b10 shr 1) shouldBe 0b01
        (0b100 shr 2) shouldBe 0b01
        (0b1000 shr 3) shouldBe 0b01
        (0b10000 shr 4) shouldBe 0b01
        (0b100000 shr 5) shouldBe 0b01
        (0b1000000 shr 6) shouldBe 0b01
    }

    "int shr long" {
        (0b10 shr 1L) shouldBe 0b01
        (0b100 shr 2L) shouldBe 0b01
        (0b1000 shr 3L) shouldBe 0b01
        (0b10000 shr 4L) shouldBe 0b01
        (0b100000 shr 5L) shouldBe 0b01
        (0b1000000 shr 6L) shouldBe 0b01
    }

    "int shr unsigned byte" {
        (0b10 shr 1u.toUByte()) shouldBe 0b01
        (0b100 shr 2u.toUByte()) shouldBe 0b01
        (0b1000 shr 3u.toUByte()) shouldBe 0b01
        (0b10000 shr 4u.toUByte()) shouldBe 0b01
        (0b100000 shr 5u.toUByte()) shouldBe 0b01
        (0b1000000 shr 6u.toUByte()) shouldBe 0b01
    }

    "int shr unsigned short" {
        (0b10 shr 1u.toUShort()) shouldBe 0b01
        (0b100 shr 2u.toUShort()) shouldBe 0b01
        (0b1000 shr 3u.toUShort()) shouldBe 0b01
        (0b10000 shr 4u.toUShort()) shouldBe 0b01
        (0b100000 shr 5u.toUShort()) shouldBe 0b01
        (0b1000000 shr 6u.toUShort()) shouldBe 0b01
    }

    "int shr unsigned int" {
        (0b10 shr 1u) shouldBe 0b01
        (0b100 shr 2u) shouldBe 0b01
        (0b1000 shr 3u) shouldBe 0b01
        (0b10000 shr 4u) shouldBe 0b01
        (0b100000 shr 5u) shouldBe 0b01
        (0b1000000 shr 6u) shouldBe 0b01
    }

    "int shr unsigned long" {
        (0b10 shr 1uL) shouldBe 0b01
        (0b100 shr 2uL) shouldBe 0b01
        (0b1000 shr 3uL) shouldBe 0b01
        (0b10000 shr 4uL) shouldBe 0b01
        (0b100000 shr 5uL) shouldBe 0b01
        (0b1000000 shr 6uL) shouldBe 0b01
    }

    "long shr byte" {
        (0b10L shr 1.toByte()) shouldBe 0b01L
        (0b100L shr 2.toByte()) shouldBe 0b01L
        (0b1000L shr 3.toByte()) shouldBe 0b01L
        (0b10000L shr 4.toByte()) shouldBe 0b01L
        (0b100000L shr 5.toByte()) shouldBe 0b01L
        (0b1000000L shr 6.toByte()) shouldBe 0b01L
    }

    "long shr short" {
        (0b10L shr 1.toShort()) shouldBe 0b01L
        (0b100L shr 2.toShort()) shouldBe 0b01L
        (0b1000L shr 3.toShort()) shouldBe 0b01L
        (0b10000L shr 4.toShort()) shouldBe 0b01L
        (0b100000L shr 5.toShort()) shouldBe 0b01L
        (0b1000000L shr 6.toShort()) shouldBe 0b01L
    }

    "long shr int" {
        (0b10L shr 1) shouldBe 0b01L
        (0b100L shr 2) shouldBe 0b01L
        (0b1000L shr 3) shouldBe 0b01L
        (0b10000L shr 4) shouldBe 0b01L
        (0b100000L shr 5) shouldBe 0b01L
        (0b1000000L shr 6) shouldBe 0b01L
    }

    "long shr long" {
        (0b10L shr 1L) shouldBe 0b01L
        (0b100L shr 2L) shouldBe 0b01L
        (0b1000L shr 3L) shouldBe 0b01L
        (0b10000L shr 4L) shouldBe 0b01L
        (0b100000L shr 5L) shouldBe 0b01L
        (0b1000000L shr 6L) shouldBe 0b01L
    }

    "long shr unsigned byte" {
        (0b10L shr 1u.toUByte()) shouldBe 0b01L
        (0b100L shr 2u.toUByte()) shouldBe 0b01L
        (0b1000L shr 3u.toUByte()) shouldBe 0b01L
        (0b10000L shr 4u.toUByte()) shouldBe 0b01L
        (0b100000L shr 5u.toUByte()) shouldBe 0b01L
        (0b1000000L shr 6u.toUByte()) shouldBe 0b01L
    }

    "long shr unsigned short" {
        (0b10L shr 1u.toUShort()) shouldBe 0b01L
        (0b100L shr 2u.toUShort()) shouldBe 0b01L
        (0b1000L shr 3u.toUShort()) shouldBe 0b01L
        (0b10000L shr 4u.toUShort()) shouldBe 0b01L
        (0b100000L shr 5u.toUShort()) shouldBe 0b01L
        (0b1000000L shr 6u.toUShort()) shouldBe 0b01L
    }

    "long shr unsigned int" {
        (0b10L shr 1u) shouldBe 0b01L
        (0b100L shr 2u) shouldBe 0b01L
        (0b1000L shr 3u) shouldBe 0b01L
        (0b10000L shr 4u) shouldBe 0b01L
        (0b100000L shr 5u) shouldBe 0b01L
        (0b1000000L shr 6u) shouldBe 0b01L
    }

    "long shr unsigned long" {
        (0b10L shr 1uL) shouldBe 0b01L
        (0b100L shr 2uL) shouldBe 0b01L
        (0b1000L shr 3uL) shouldBe 0b01L
        (0b10000L shr 4uL) shouldBe 0b01L
        (0b100000L shr 5uL) shouldBe 0b01L
        (0b1000000L shr 6uL) shouldBe 0b01L
    }

    "unsigned byte shr byte" {
        (0b10u.toUByte() shr 1.toByte()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2.toByte()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3.toByte()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4.toByte()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5.toByte()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6.toByte()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr short" {
        (0b10u.toUByte() shr 1.toShort()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2.toShort()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3.toShort()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4.toShort()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5.toShort()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6.toShort()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr int" {
        (0b10u.toUByte() shr 1) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr long" {
        (0b10u.toUByte() shr 1L) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2L) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3L) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4L) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5L) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6L) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr unsigned byte" {
        (0b10u.toUByte() shr 1u.toUByte()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2u.toUByte()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3u.toUByte()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4u.toUByte()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5u.toUByte()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6u.toUByte()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr unsigned short" {
        (0b10u.toUByte() shr 1u.toUShort()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2u.toUShort()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3u.toUShort()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4u.toUShort()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5u.toUShort()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6u.toUShort()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr unsigned int" {
        (0b10u.toUByte() shr 1u) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2u) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3u) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4u) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5u) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6u) shouldBe 0b01u.toUByte()
    }

    "unsigned byte shr unsigned long" {
        (0b10u.toUByte() shr 1uL) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() shr 2uL) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() shr 3uL) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() shr 4uL) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() shr 5uL) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() shr 6uL) shouldBe 0b01u.toUByte()
    }

    "unsigned short shr byte" {
        (0b10u.toUShort() shr 1.toByte()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2.toByte()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3.toByte()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4.toByte()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5.toByte()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6.toByte()) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr short" {
        (0b10u.toUShort() shr 1.toShort()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2.toShort()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3.toShort()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4.toShort()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5.toShort()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6.toShort()) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr int" {
        (0b10u.toUShort() shr 1) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr long" {
        (0b10u.toUShort() shr 1L) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2L) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3L) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4L) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5L) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6L) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr unsigned byte" {
        (0b10u.toUShort() shr 1u.toUByte()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2u.toUByte()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3u.toUByte()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4u.toUByte()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5u.toUByte()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6u.toUByte()) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr unsigned short" {
        (0b10u.toUShort() shr 1u.toUShort()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2u.toUShort()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3u.toUShort()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4u.toUShort()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5u.toUShort()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6u.toUShort()) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr unsigned int" {
        (0b10u.toUShort() shr 1u) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2u) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3u) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4u) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5u) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6u) shouldBe 0b01u.toUShort()
    }

    "unsigned short shr unsigned long" {
        (0b10u.toUShort() shr 1uL) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() shr 2uL) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() shr 3uL) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() shr 4uL) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() shr 5uL) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() shr 6uL) shouldBe 0b01u.toUShort()
    }

    "unsigned int shr byte" {
        (0b10u shr 1.toByte()) shouldBe 0b01u
        (0b100u shr 2.toByte()) shouldBe 0b01u
        (0b1000u shr 3.toByte()) shouldBe 0b01u
        (0b10000u shr 4.toByte()) shouldBe 0b01u
        (0b100000u shr 5.toByte()) shouldBe 0b01u
        (0b1000000u shr 6.toByte()) shouldBe 0b01u
    }

    "unsigned int shr short" {
        (0b10u shr 1.toShort()) shouldBe 0b01u
        (0b100u shr 2.toShort()) shouldBe 0b01u
        (0b1000u shr 3.toShort()) shouldBe 0b01u
        (0b10000u shr 4.toShort()) shouldBe 0b01u
        (0b100000u shr 5.toShort()) shouldBe 0b01u
        (0b1000000u shr 6.toShort()) shouldBe 0b01u
    }

    "unsigned int shr int" {
        (0b10u shr 1) shouldBe 0b01u
        (0b100u shr 2) shouldBe 0b01u
        (0b1000u shr 3) shouldBe 0b01u
        (0b10000u shr 4) shouldBe 0b01u
        (0b100000u shr 5) shouldBe 0b01u
        (0b1000000u shr 6) shouldBe 0b01u
    }

    "unsigned int shr long" {
        (0b10u shr 1L) shouldBe 0b01u
        (0b100u shr 2L) shouldBe 0b01u
        (0b1000u shr 3L) shouldBe 0b01u
        (0b10000u shr 4L) shouldBe 0b01u
        (0b100000u shr 5L) shouldBe 0b01u
        (0b1000000u shr 6L) shouldBe 0b01u
    }

    "unsigned int shr unsigned byte" {
        (0b10u shr 1u.toUByte()) shouldBe 0b01u
        (0b100u shr 2u.toUByte()) shouldBe 0b01u
        (0b1000u shr 3u.toUByte()) shouldBe 0b01u
        (0b10000u shr 4u.toUByte()) shouldBe 0b01u
        (0b100000u shr 5u.toUByte()) shouldBe 0b01u
        (0b1000000u shr 6u.toUByte()) shouldBe 0b01u
    }

    "unsigned int shr unsigned short" {
        (0b10u shr 1u.toUShort()) shouldBe 0b01u
        (0b100u shr 2u.toUShort()) shouldBe 0b01u
        (0b1000u shr 3u.toUShort()) shouldBe 0b01u
        (0b10000u shr 4u.toUShort()) shouldBe 0b01u
        (0b100000u shr 5u.toUShort()) shouldBe 0b01u
        (0b1000000u shr 6u.toUShort()) shouldBe 0b01u
    }

    "unsigned int shr unsigned int" {
        (0b10u shr 1u) shouldBe 0b01u
        (0b100u shr 2u) shouldBe 0b01u
        (0b1000u shr 3u) shouldBe 0b01u
        (0b10000u shr 4u) shouldBe 0b01u
        (0b100000u shr 5u) shouldBe 0b01u
        (0b1000000u shr 6u) shouldBe 0b01u
    }

    "unsigned int shr unsigned long" {
        (0b10u shr 1uL) shouldBe 0b01u
        (0b100u shr 2uL) shouldBe 0b01u
        (0b1000u shr 3uL) shouldBe 0b01u
        (0b10000u shr 4uL) shouldBe 0b01u
        (0b100000u shr 5uL) shouldBe 0b01u
        (0b1000000u shr 6uL) shouldBe 0b01u
    }

    "unsigned long shr byte" {
        (0b10uL shr 1.toByte()) shouldBe 0b01uL
        (0b100uL shr 2.toByte()) shouldBe 0b01uL
        (0b1000uL shr 3.toByte()) shouldBe 0b01uL
        (0b10000uL shr 4.toByte()) shouldBe 0b01uL
        (0b100000uL shr 5.toByte()) shouldBe 0b01uL
        (0b1000000uL shr 6.toByte()) shouldBe 0b01uL
    }

    "unsigned long shr short" {
        (0b10uL shr 1.toShort()) shouldBe 0b01uL
        (0b100uL shr 2.toShort()) shouldBe 0b01uL
        (0b1000uL shr 3.toShort()) shouldBe 0b01uL
        (0b10000uL shr 4.toShort()) shouldBe 0b01uL
        (0b100000uL shr 5.toShort()) shouldBe 0b01uL
        (0b1000000uL shr 6.toShort()) shouldBe 0b01uL
    }

    "unsigned long shr int" {
        (0b10uL shr 1) shouldBe 0b01uL
        (0b100uL shr 2) shouldBe 0b01uL
        (0b1000uL shr 3) shouldBe 0b01uL
        (0b10000uL shr 4) shouldBe 0b01uL
        (0b100000uL shr 5) shouldBe 0b01uL
        (0b1000000uL shr 6) shouldBe 0b01uL
    }

    "unsigned long shr long" {
        (0b10uL shr 1L) shouldBe 0b01uL
        (0b100uL shr 2L) shouldBe 0b01uL
        (0b1000uL shr 3L) shouldBe 0b01uL
        (0b10000uL shr 4L) shouldBe 0b01uL
        (0b100000uL shr 5L) shouldBe 0b01uL
        (0b1000000uL shr 6L) shouldBe 0b01uL
    }

    "unsigned long shr unsigned byte" {
        (0b10uL shr 1u.toUByte()) shouldBe 0b01uL
        (0b100uL shr 2u.toUByte()) shouldBe 0b01uL
        (0b1000uL shr 3u.toUByte()) shouldBe 0b01uL
        (0b10000uL shr 4u.toUByte()) shouldBe 0b01uL
        (0b100000uL shr 5u.toUByte()) shouldBe 0b01uL
        (0b1000000uL shr 6u.toUByte()) shouldBe 0b01uL
    }

    "unsigned long shr unsigned short" {
        (0b10uL shr 1u.toUShort()) shouldBe 0b01uL
        (0b100uL shr 2u.toUShort()) shouldBe 0b01uL
        (0b1000uL shr 3u.toUShort()) shouldBe 0b01uL
        (0b10000uL shr 4u.toUShort()) shouldBe 0b01uL
        (0b100000uL shr 5u.toUShort()) shouldBe 0b01uL
        (0b1000000uL shr 6u.toUShort()) shouldBe 0b01uL
    }

    "unsigned long shr unsigned int" {
        (0b10uL shr 1u) shouldBe 0b01uL
        (0b100uL shr 2u) shouldBe 0b01uL
        (0b1000uL shr 3u) shouldBe 0b01uL
        (0b10000uL shr 4u) shouldBe 0b01uL
        (0b100000uL shr 5u) shouldBe 0b01uL
        (0b1000000uL shr 6u) shouldBe 0b01uL
    }

    "unsigned long shr unsigned long" {
        (0b10uL shr 1uL) shouldBe 0b01uL
        (0b100uL shr 2uL) shouldBe 0b01uL
        (0b1000uL shr 3uL) shouldBe 0b01uL
        (0b10000uL shr 4uL) shouldBe 0b01uL
        (0b100000uL shr 5uL) shouldBe 0b01uL
        (0b1000000uL shr 6uL) shouldBe 0b01uL
    }
})

class USHRTest : FreeSpec({
    "byte ushr byte" {
        (0b10.toByte() ushr 1.toByte()) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2.toByte()) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3.toByte()) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4.toByte()) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5.toByte()) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6.toByte()) shouldBe 0b01.toByte()
    }

    "byte ushr short" {
        (0b10.toByte() ushr 1.toShort()) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2.toShort()) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3.toShort()) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4.toShort()) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5.toShort()) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6.toShort()) shouldBe 0b01.toByte()
    }

    "byte ushr int" {
        (0b10.toByte() ushr 1) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6) shouldBe 0b01.toByte()
    }

    "byte ushr long" {
        (0b10.toByte() ushr 1L) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2L) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3L) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4L) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5L) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6L) shouldBe 0b01.toByte()
    }

    "byte ushr unsigned byte" {
        (0b10.toByte() ushr 1u.toUByte()) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2u.toUByte()) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3u.toUByte()) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4u.toUByte()) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5u.toUByte()) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6u.toUByte()) shouldBe 0b01.toByte()
    }

    "byte ushr unsigned short" {
        (0b10.toByte() ushr 1u.toUShort()) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2u.toUShort()) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3u.toUShort()) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4u.toUShort()) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5u.toUShort()) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6u.toUShort()) shouldBe 0b01.toByte()
    }

    "byte ushr unsigned int" {
        (0b10.toByte() ushr 1u) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2u) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3u) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4u) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5u) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6u) shouldBe 0b01.toByte()
    }

    "byte ushr unsigned long" {
        (0b10.toByte() ushr 1uL) shouldBe 0b01.toByte()
        (0b100.toByte() ushr 2uL) shouldBe 0b01.toByte()
        (0b1000.toByte() ushr 3uL) shouldBe 0b01.toByte()
        (0b10000.toByte() ushr 4uL) shouldBe 0b01.toByte()
        (0b100000.toByte() ushr 5uL) shouldBe 0b01.toByte()
        (0b1000000.toByte() ushr 6uL) shouldBe 0b01.toByte()
    }

    "short ushr byte" {
        (0b10.toShort() ushr 1.toByte()) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2.toByte()) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3.toByte()) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4.toByte()) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5.toByte()) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6.toByte()) shouldBe 0b01.toShort()
    }

    "short ushr short" {
        (0b10.toShort() ushr 1.toShort()) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2.toShort()) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3.toShort()) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4.toShort()) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5.toShort()) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6.toShort()) shouldBe 0b01.toShort()
    }

    "short ushr int" {
        (0b10.toShort() ushr 1) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6) shouldBe 0b01.toShort()
    }

    "short ushr long" {
        (0b10.toShort() ushr 1L) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2L) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3L) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4L) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5L) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6L) shouldBe 0b01.toShort()
    }

    "short ushr unsigned byte" {
        (0b10.toShort() ushr 1u.toUByte()) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2u.toUByte()) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3u.toUByte()) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4u.toUByte()) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5u.toUByte()) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6u.toUByte()) shouldBe 0b01.toShort()
    }

    "short ushr unsigned short" {
        (0b10.toShort() ushr 1u.toUShort()) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2u.toUShort()) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3u.toUShort()) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4u.toUShort()) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5u.toUShort()) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6u.toUShort()) shouldBe 0b01.toShort()
    }

    "short ushr unsigned int" {
        (0b10.toShort() ushr 1u) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2u) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3u) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4u) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5u) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6u) shouldBe 0b01.toShort()
    }

    "short ushr unsigned long" {
        (0b10.toShort() ushr 1uL) shouldBe 0b01.toShort()
        (0b100.toShort() ushr 2uL) shouldBe 0b01.toShort()
        (0b1000.toShort() ushr 3uL) shouldBe 0b01.toShort()
        (0b10000.toShort() ushr 4uL) shouldBe 0b01.toShort()
        (0b100000.toShort() ushr 5uL) shouldBe 0b01.toShort()
        (0b1000000.toShort() ushr 6uL) shouldBe 0b01.toShort()
    }

    "int ushr byte" {
        (0b10 ushr 1.toByte()) shouldBe 0b01
        (0b100 ushr 2.toByte()) shouldBe 0b01
        (0b1000 ushr 3.toByte()) shouldBe 0b01
        (0b10000 ushr 4.toByte()) shouldBe 0b01
        (0b100000 ushr 5.toByte()) shouldBe 0b01
        (0b1000000 ushr 6.toByte()) shouldBe 0b01
    }

    "int ushr short" {
        (0b10 ushr 1.toShort()) shouldBe 0b01
        (0b100 ushr 2.toShort()) shouldBe 0b01
        (0b1000 ushr 3.toShort()) shouldBe 0b01
        (0b10000 ushr 4.toShort()) shouldBe 0b01
        (0b100000 ushr 5.toShort()) shouldBe 0b01
        (0b1000000 ushr 6.toShort()) shouldBe 0b01
    }

    "int ushr int" {
        (0b10 ushr 1) shouldBe 0b01
        (0b100 ushr 2) shouldBe 0b01
        (0b1000 ushr 3) shouldBe 0b01
        (0b10000 ushr 4) shouldBe 0b01
        (0b100000 ushr 5) shouldBe 0b01
        (0b1000000 ushr 6) shouldBe 0b01
    }

    "int ushr long" {
        (0b10 ushr 1L) shouldBe 0b01
        (0b100 ushr 2L) shouldBe 0b01
        (0b1000 ushr 3L) shouldBe 0b01
        (0b10000 ushr 4L) shouldBe 0b01
        (0b100000 ushr 5L) shouldBe 0b01
        (0b1000000 ushr 6L) shouldBe 0b01
    }

    "int ushr unsigned byte" {
        (0b10 ushr 1u.toUByte()) shouldBe 0b01
        (0b100 ushr 2u.toUByte()) shouldBe 0b01
        (0b1000 ushr 3u.toUByte()) shouldBe 0b01
        (0b10000 ushr 4u.toUByte()) shouldBe 0b01
        (0b100000 ushr 5u.toUByte()) shouldBe 0b01
        (0b1000000 ushr 6u.toUByte()) shouldBe 0b01
    }

    "int ushr unsigned short" {
        (0b10 ushr 1u.toUShort()) shouldBe 0b01
        (0b100 ushr 2u.toUShort()) shouldBe 0b01
        (0b1000 ushr 3u.toUShort()) shouldBe 0b01
        (0b10000 ushr 4u.toUShort()) shouldBe 0b01
        (0b100000 ushr 5u.toUShort()) shouldBe 0b01
        (0b1000000 ushr 6u.toUShort()) shouldBe 0b01
    }

    "int ushr unsigned int" {
        (0b10 ushr 1u) shouldBe 0b01
        (0b100 ushr 2u) shouldBe 0b01
        (0b1000 ushr 3u) shouldBe 0b01
        (0b10000 ushr 4u) shouldBe 0b01
        (0b100000 ushr 5u) shouldBe 0b01
        (0b1000000 ushr 6u) shouldBe 0b01
    }

    "int ushr unsigned long" {
        (0b10 ushr 1uL) shouldBe 0b01
        (0b100 ushr 2uL) shouldBe 0b01
        (0b1000 ushr 3uL) shouldBe 0b01
        (0b10000 ushr 4uL) shouldBe 0b01
        (0b100000 ushr 5uL) shouldBe 0b01
        (0b1000000 ushr 6uL) shouldBe 0b01
    }

    "long ushr byte" {
        (0b10L ushr 1.toByte()) shouldBe 0b01L
        (0b100L ushr 2.toByte()) shouldBe 0b01L
        (0b1000L ushr 3.toByte()) shouldBe 0b01L
        (0b10000L ushr 4.toByte()) shouldBe 0b01L
        (0b100000L ushr 5.toByte()) shouldBe 0b01L
        (0b1000000L ushr 6.toByte()) shouldBe 0b01L
    }

    "long ushr short" {
        (0b10L ushr 1.toShort()) shouldBe 0b01L
        (0b100L ushr 2.toShort()) shouldBe 0b01L
        (0b1000L ushr 3.toShort()) shouldBe 0b01L
        (0b10000L ushr 4.toShort()) shouldBe 0b01L
        (0b100000L ushr 5.toShort()) shouldBe 0b01L
        (0b1000000L ushr 6.toShort()) shouldBe 0b01L
    }

    "long ushr int" {
        (0b10L ushr 1) shouldBe 0b01L
        (0b100L ushr 2) shouldBe 0b01L
        (0b1000L ushr 3) shouldBe 0b01L
        (0b10000L ushr 4) shouldBe 0b01L
        (0b100000L ushr 5) shouldBe 0b01L
        (0b1000000L ushr 6) shouldBe 0b01L
    }

    "long ushr long" {
        (0b10L ushr 1L) shouldBe 0b01L
        (0b100L ushr 2L) shouldBe 0b01L
        (0b1000L ushr 3L) shouldBe 0b01L
        (0b10000L ushr 4L) shouldBe 0b01L
        (0b100000L ushr 5L) shouldBe 0b01L
        (0b1000000L ushr 6L) shouldBe 0b01L
    }

    "long ushr unsigned byte" {
        (0b10L ushr 1u.toUByte()) shouldBe 0b01L
        (0b100L ushr 2u.toUByte()) shouldBe 0b01L
        (0b1000L ushr 3u.toUByte()) shouldBe 0b01L
        (0b10000L ushr 4u.toUByte()) shouldBe 0b01L
        (0b100000L ushr 5u.toUByte()) shouldBe 0b01L
        (0b1000000L ushr 6u.toUByte()) shouldBe 0b01L
    }

    "long ushr unsigned short" {
        (0b10L ushr 1u.toUShort()) shouldBe 0b01L
        (0b100L ushr 2u.toUShort()) shouldBe 0b01L
        (0b1000L ushr 3u.toUShort()) shouldBe 0b01L
        (0b10000L ushr 4u.toUShort()) shouldBe 0b01L
        (0b100000L ushr 5u.toUShort()) shouldBe 0b01L
        (0b1000000L ushr 6u.toUShort()) shouldBe 0b01L
    }

    "long ushr unsigned int" {
        (0b10L ushr 1u) shouldBe 0b01L
        (0b100L ushr 2u) shouldBe 0b01L
        (0b1000L ushr 3u) shouldBe 0b01L
        (0b10000L ushr 4u) shouldBe 0b01L
        (0b100000L ushr 5u) shouldBe 0b01L
        (0b1000000L ushr 6u) shouldBe 0b01L
    }

    "long ushr unsigned long" {
        (0b10L ushr 1uL) shouldBe 0b01L
        (0b100L ushr 2uL) shouldBe 0b01L
        (0b1000L ushr 3uL) shouldBe 0b01L
        (0b10000L ushr 4uL) shouldBe 0b01L
        (0b100000L ushr 5uL) shouldBe 0b01L
        (0b1000000L ushr 6uL) shouldBe 0b01L
    }

    "unsigned byte ushr byte" {
        (0b10u.toUByte() ushr 1.toByte()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2.toByte()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3.toByte()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4.toByte()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5.toByte()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6.toByte()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr short" {
        (0b10u.toUByte() ushr 1.toShort()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2.toShort()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3.toShort()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4.toShort()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5.toShort()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6.toShort()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr int" {
        (0b10u.toUByte() ushr 1) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr long" {
        (0b10u.toUByte() ushr 1L) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2L) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3L) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4L) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5L) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6L) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr unsigned byte" {
        (0b10u.toUByte() ushr 1u.toUByte()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2u.toUByte()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3u.toUByte()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4u.toUByte()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5u.toUByte()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6u.toUByte()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr unsigned short" {
        (0b10u.toUByte() ushr 1u.toUShort()) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2u.toUShort()) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3u.toUShort()) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4u.toUShort()) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5u.toUShort()) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6u.toUShort()) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr unsigned int" {
        (0b10u.toUByte() ushr 1u) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2u) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3u) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4u) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5u) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6u) shouldBe 0b01u.toUByte()
    }

    "unsigned byte ushr unsigned long" {
        (0b10u.toUByte() ushr 1uL) shouldBe 0b01u.toUByte()
        (0b100u.toUByte() ushr 2uL) shouldBe 0b01u.toUByte()
        (0b1000u.toUByte() ushr 3uL) shouldBe 0b01u.toUByte()
        (0b10000u.toUByte() ushr 4uL) shouldBe 0b01u.toUByte()
        (0b100000u.toUByte() ushr 5uL) shouldBe 0b01u.toUByte()
        (0b1000000u.toUByte() ushr 6uL) shouldBe 0b01u.toUByte()
    }

    "unsigned short ushr byte" {
        (0b10u.toUShort() ushr 1.toByte()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2.toByte()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3.toByte()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4.toByte()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5.toByte()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6.toByte()) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr short" {
        (0b10u.toUShort() ushr 1.toShort()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2.toShort()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3.toShort()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4.toShort()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5.toShort()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6.toShort()) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr int" {
        (0b10u.toUShort() ushr 1) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr long" {
        (0b10u.toUShort() ushr 1L) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2L) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3L) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4L) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5L) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6L) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr unsigned byte" {
        (0b10u.toUShort() ushr 1u.toUByte()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2u.toUByte()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3u.toUByte()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4u.toUByte()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5u.toUByte()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6u.toUByte()) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr unsigned short" {
        (0b10u.toUShort() ushr 1u.toUShort()) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2u.toUShort()) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3u.toUShort()) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4u.toUShort()) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5u.toUShort()) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6u.toUShort()) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr unsigned int" {
        (0b10u.toUShort() ushr 1u) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2u) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3u) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4u) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5u) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6u) shouldBe 0b01u.toUShort()
    }

    "unsigned short ushr unsigned long" {
        (0b10u.toUShort() ushr 1uL) shouldBe 0b01u.toUShort()
        (0b100u.toUShort() ushr 2uL) shouldBe 0b01u.toUShort()
        (0b1000u.toUShort() ushr 3uL) shouldBe 0b01u.toUShort()
        (0b10000u.toUShort() ushr 4uL) shouldBe 0b01u.toUShort()
        (0b100000u.toUShort() ushr 5uL) shouldBe 0b01u.toUShort()
        (0b1000000u.toUShort() ushr 6uL) shouldBe 0b01u.toUShort()
    }

    "unsigned int ushr byte" {
        (0b10u ushr 1.toByte()) shouldBe 0b01u
        (0b100u ushr 2.toByte()) shouldBe 0b01u
        (0b1000u ushr 3.toByte()) shouldBe 0b01u
        (0b10000u ushr 4.toByte()) shouldBe 0b01u
        (0b100000u ushr 5.toByte()) shouldBe 0b01u
        (0b1000000u ushr 6.toByte()) shouldBe 0b01u
    }

    "unsigned int ushr short" {
        (0b10u ushr 1.toShort()) shouldBe 0b01u
        (0b100u ushr 2.toShort()) shouldBe 0b01u
        (0b1000u ushr 3.toShort()) shouldBe 0b01u
        (0b10000u ushr 4.toShort()) shouldBe 0b01u
        (0b100000u ushr 5.toShort()) shouldBe 0b01u
        (0b1000000u ushr 6.toShort()) shouldBe 0b01u
    }

    "unsigned int ushr int" {
        (0b10u ushr 1) shouldBe 0b01u
        (0b100u ushr 2) shouldBe 0b01u
        (0b1000u ushr 3) shouldBe 0b01u
        (0b10000u ushr 4) shouldBe 0b01u
        (0b100000u ushr 5) shouldBe 0b01u
        (0b1000000u ushr 6) shouldBe 0b01u
    }

    "unsigned int ushr long" {
        (0b10u ushr 1L) shouldBe 0b01u
        (0b100u ushr 2L) shouldBe 0b01u
        (0b1000u ushr 3L) shouldBe 0b01u
        (0b10000u ushr 4L) shouldBe 0b01u
        (0b100000u ushr 5L) shouldBe 0b01u
        (0b1000000u ushr 6L) shouldBe 0b01u
    }

    "unsigned int ushr unsigned byte" {
        (0b10u ushr 1u.toUByte()) shouldBe 0b01u
        (0b100u ushr 2u.toUByte()) shouldBe 0b01u
        (0b1000u ushr 3u.toUByte()) shouldBe 0b01u
        (0b10000u ushr 4u.toUByte()) shouldBe 0b01u
        (0b100000u ushr 5u.toUByte()) shouldBe 0b01u
        (0b1000000u ushr 6u.toUByte()) shouldBe 0b01u
    }

    "unsigned int ushr unsigned short" {
        (0b10u ushr 1u.toUShort()) shouldBe 0b01u
        (0b100u ushr 2u.toUShort()) shouldBe 0b01u
        (0b1000u ushr 3u.toUShort()) shouldBe 0b01u
        (0b10000u ushr 4u.toUShort()) shouldBe 0b01u
        (0b100000u ushr 5u.toUShort()) shouldBe 0b01u
        (0b1000000u ushr 6u.toUShort()) shouldBe 0b01u
    }

    "unsigned int ushr unsigned int" {
        (0b10u ushr 1u) shouldBe 0b01u
        (0b100u ushr 2u) shouldBe 0b01u
        (0b1000u ushr 3u) shouldBe 0b01u
        (0b10000u ushr 4u) shouldBe 0b01u
        (0b100000u ushr 5u) shouldBe 0b01u
        (0b1000000u ushr 6u) shouldBe 0b01u
    }

    "unsigned int ushr unsigned long" {
        (0b10u ushr 1uL) shouldBe 0b01u
        (0b100u ushr 2uL) shouldBe 0b01u
        (0b1000u ushr 3uL) shouldBe 0b01u
        (0b10000u ushr 4uL) shouldBe 0b01u
        (0b100000u ushr 5uL) shouldBe 0b01u
        (0b1000000u ushr 6uL) shouldBe 0b01u
    }

    "unsigned long ushr byte" {
        (0b10uL ushr 1.toByte()) shouldBe 0b01uL
        (0b100uL ushr 2.toByte()) shouldBe 0b01uL
        (0b1000uL ushr 3.toByte()) shouldBe 0b01uL
        (0b10000uL ushr 4.toByte()) shouldBe 0b01uL
        (0b100000uL ushr 5.toByte()) shouldBe 0b01uL
        (0b1000000uL ushr 6.toByte()) shouldBe 0b01uL
    }

    "unsigned long ushr short" {
        (0b10uL ushr 1.toShort()) shouldBe 0b01uL
        (0b100uL ushr 2.toShort()) shouldBe 0b01uL
        (0b1000uL ushr 3.toShort()) shouldBe 0b01uL
        (0b10000uL ushr 4.toShort()) shouldBe 0b01uL
        (0b100000uL ushr 5.toShort()) shouldBe 0b01uL
        (0b1000000uL ushr 6.toShort()) shouldBe 0b01uL
    }

    "unsigned long ushr int" {
        (0b10uL ushr 1) shouldBe 0b01uL
        (0b100uL ushr 2) shouldBe 0b01uL
        (0b1000uL ushr 3) shouldBe 0b01uL
        (0b10000uL ushr 4) shouldBe 0b01uL
        (0b100000uL ushr 5) shouldBe 0b01uL
        (0b1000000uL ushr 6) shouldBe 0b01uL
    }

    "unsigned long ushr long" {
        (0b10uL ushr 1L) shouldBe 0b01uL
        (0b100uL ushr 2L) shouldBe 0b01uL
        (0b1000uL ushr 3L) shouldBe 0b01uL
        (0b10000uL ushr 4L) shouldBe 0b01uL
        (0b100000uL ushr 5L) shouldBe 0b01uL
        (0b1000000uL ushr 6L) shouldBe 0b01uL
    }

    "unsigned long ushr unsigned byte" {
        (0b10uL ushr 1u.toUByte()) shouldBe 0b01uL
        (0b100uL ushr 2u.toUByte()) shouldBe 0b01uL
        (0b1000uL ushr 3u.toUByte()) shouldBe 0b01uL
        (0b10000uL ushr 4u.toUByte()) shouldBe 0b01uL
        (0b100000uL ushr 5u.toUByte()) shouldBe 0b01uL
        (0b1000000uL ushr 6u.toUByte()) shouldBe 0b01uL
    }

    "unsigned long ushr unsigned short" {
        (0b10uL ushr 1u.toUShort()) shouldBe 0b01uL
        (0b100uL ushr 2u.toUShort()) shouldBe 0b01uL
        (0b1000uL ushr 3u.toUShort()) shouldBe 0b01uL
        (0b10000uL ushr 4u.toUShort()) shouldBe 0b01uL
        (0b100000uL ushr 5u.toUShort()) shouldBe 0b01uL
        (0b1000000uL ushr 6u.toUShort()) shouldBe 0b01uL
    }

    "unsigned long ushr unsigned int" {
        (0b10uL ushr 1u) shouldBe 0b01uL
        (0b100uL ushr 2u) shouldBe 0b01uL
        (0b1000uL ushr 3u) shouldBe 0b01uL
        (0b10000uL ushr 4u) shouldBe 0b01uL
        (0b100000uL ushr 5u) shouldBe 0b01uL
        (0b1000000uL ushr 6u) shouldBe 0b01uL
    }

    "unsigned long ushr unsigned long" {
        (0b10uL ushr 1uL) shouldBe 0b01uL
        (0b100uL ushr 2uL) shouldBe 0b01uL
        (0b1000uL ushr 3uL) shouldBe 0b01uL
        (0b10000uL ushr 4uL) shouldBe 0b01uL
        (0b100000uL ushr 5uL) shouldBe 0b01uL
        (0b1000000uL ushr 6uL) shouldBe 0b01uL
    }
})
