package com.shakelang.shake.util.testlib

import io.kotest.core.spec.style.FreeSpec

@OptIn(ExperimentalUnsignedTypes::class)
class ArrayMatchingTests : FreeSpec({

    "ByteArray shouldContainExactly ByteArray" {
        byteArrayOf(1, 2, 3) shouldContainExactly byteArrayOf(1, 2, 3)
    }

    "ByteArray shouldContainExactly Collection" {
        byteArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "ByteArray shouldContainExactly List" {
        byteArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "ByteArray shouldContainExactly Set" {
        byteArrayOf(1, 2, 3) shouldContainExactly setOf(1, 2, 3)
    }

    "ByteArray shouldContainExactly Sequence" {
        byteArrayOf(1, 2, 3) shouldContainExactly sequenceOf(1, 2, 3)
    }

    "ShortArray shouldContainExactly ShortArray" {
        shortArrayOf(1, 2, 3) shouldContainExactly shortArrayOf(1, 2, 3)
    }

    "ShortArray shouldContainExactly Collection" {
        shortArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "ShortArray shouldContainExactly List" {
        shortArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "ShortArray shouldContainExactly Set" {
        shortArrayOf(1, 2, 3) shouldContainExactly setOf(1, 2, 3)
    }

    "ShortArray shouldContainExactly Sequence" {
        shortArrayOf(1, 2, 3) shouldContainExactly sequenceOf(1, 2, 3)
    }

    "IntArray shouldContainExactly IntArray" {
        intArrayOf(1, 2, 3) shouldContainExactly intArrayOf(1, 2, 3)
    }

    "IntArray shouldContainExactly Collection" {
        intArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "IntArray shouldContainExactly List" {
        intArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "IntArray shouldContainExactly Set" {
        intArrayOf(1, 2, 3) shouldContainExactly setOf(1, 2, 3)
    }

    "IntArray shouldContainExactly Sequence" {
        intArrayOf(1, 2, 3) shouldContainExactly sequenceOf(1, 2, 3)
    }

    "LongArray shouldContainExactly LongArray" {
        longArrayOf(1, 2, 3) shouldContainExactly longArrayOf(1, 2, 3)
    }

    "LongArray shouldContainExactly Collection" {
        longArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "LongArray shouldContainExactly List" {
        longArrayOf(1, 2, 3) shouldContainExactly listOf(1, 2, 3)
    }

    "LongArray shouldContainExactly Set" {
        longArrayOf(1, 2, 3) shouldContainExactly setOf(1, 2, 3)
    }

    "LongArray shouldContainExactly Sequence" {
        longArrayOf(1, 2, 3) shouldContainExactly sequenceOf(1, 2, 3)
    }

    "FloatArray shouldContainExactly FloatArray" {
        floatArrayOf(1f, 2f, 3f) shouldContainExactly floatArrayOf(1f, 2f, 3f)
    }

    "FloatArray shouldContainExactly Collection" {
        floatArrayOf(1f, 2f, 3f) shouldContainExactly listOf(1f, 2f, 3f)
    }

    "FloatArray shouldContainExactly List" {
        floatArrayOf(1f, 2f, 3f) shouldContainExactly listOf(1f, 2f, 3f)
    }

    "FloatArray shouldContainExactly Set" {
        floatArrayOf(1f, 2f, 3f) shouldContainExactly setOf(1f, 2f, 3f)
    }

    "FloatArray shouldContainExactly Sequence" {
        floatArrayOf(1f, 2f, 3f) shouldContainExactly sequenceOf(1f, 2f, 3f)
    }

    "DoubleArray shouldContainExactly DoubleArray" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldContainExactly doubleArrayOf(1.0, 2.0, 3.0)
    }

    "DoubleArray shouldContainExactly Collection" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldContainExactly listOf(1.0, 2.0, 3.0)
    }

    "DoubleArray shouldContainExactly List" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldContainExactly listOf(1.0, 2.0, 3.0)
    }

    "DoubleArray shouldContainExactly Set" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldContainExactly setOf(1.0, 2.0, 3.0)
    }

    "DoubleArray shouldContainExactly Sequence" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldContainExactly sequenceOf(1.0, 2.0, 3.0)
    }

    "BooleanArray shouldContainExactly BooleanArray" {
        booleanArrayOf(true, false, true) shouldContainExactly booleanArrayOf(true, false, true)
    }

    "BooleanArray shouldContainExactly Collection" {
        booleanArrayOf(true, false, true) shouldContainExactly listOf(true, false, true)
    }

    "BooleanArray shouldContainExactly List" {
        booleanArrayOf(true, false, true) shouldContainExactly listOf(true, false, true)
    }

    "BooleanArray shouldContainExactly Set" {
        booleanArrayOf(true, false) shouldContainExactly setOf(true, false)
    }

    "BooleanArray shouldContainExactly Sequence" {
        booleanArrayOf(true, false, true) shouldContainExactly sequenceOf(true, false, true)
    }

    "CharArray shouldContainExactly CharArray" {
        charArrayOf('a', 'b', 'c') shouldContainExactly charArrayOf('a', 'b', 'c')
    }

    "CharArray shouldContainExactly Collection" {
        charArrayOf('a', 'b', 'c') shouldContainExactly listOf('a', 'b', 'c')
    }

    "CharArray shouldContainExactly List" {
        charArrayOf('a', 'b', 'c') shouldContainExactly listOf('a', 'b', 'c')
    }

    "CharArray shouldContainExactly Set" {
        charArrayOf('a', 'b', 'c') shouldContainExactly setOf('a', 'b', 'c')
    }

    "CharArray shouldContainExactly Sequence" {
        charArrayOf('a', 'b', 'c') shouldContainExactly sequenceOf('a', 'b', 'c')
    }

    "UByteArray shouldContainExactly UByteArray" {
        ubyteArrayOf(1u, 2u, 3u) shouldContainExactly ubyteArrayOf(1u, 2u, 3u)
    }

    "UByteArray shouldContainExactly Collection" {
        ubyteArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "UByteArray shouldContainExactly List" {
        ubyteArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "UByteArray shouldContainExactly Set" {
        ubyteArrayOf(1u, 2u, 3u) shouldContainExactly setOf(1u, 2u, 3u)
    }

    "UByteArray shouldContainExactly Sequence" {
        ubyteArrayOf(1u, 2u, 3u) shouldContainExactly sequenceOf(1u, 2u, 3u)
    }

    "UShortArray shouldContainExactly UShortArray" {
        ushortArrayOf(1u, 2u, 3u) shouldContainExactly ushortArrayOf(1u, 2u, 3u)
    }

    "UShortArray shouldContainExactly Collection" {
        ushortArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "UShortArray shouldContainExactly List" {
        ushortArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "UShortArray shouldContainExactly Set" {
        ushortArrayOf(1u, 2u, 3u) shouldContainExactly setOf(1u, 2u, 3u)
    }

    "UShortArray shouldContainExactly Sequence" {
        ushortArrayOf(1u, 2u, 3u) shouldContainExactly sequenceOf(1u, 2u, 3u)
    }

    "UIntArray shouldContainExactly UIntArray" {
        uintArrayOf(1u, 2u, 3u) shouldContainExactly uintArrayOf(1u, 2u, 3u)
    }

    "UIntArray shouldContainExactly Collection" {
        uintArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "UIntArray shouldContainExactly List" {
        uintArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "UIntArray shouldContainExactly Set" {
        uintArrayOf(1u, 2u, 3u) shouldContainExactly setOf(1u, 2u, 3u)
    }

    "UIntArray shouldContainExactly Sequence" {
        uintArrayOf(1u, 2u, 3u) shouldContainExactly sequenceOf(1u, 2u, 3u)
    }

    "ULongArray shouldContainExactly ULongArray" {
        ulongArrayOf(1u, 2u, 3u) shouldContainExactly ulongArrayOf(1u, 2u, 3u)
    }

    "ULongArray shouldContainExactly Collection" {
        ulongArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "ULongArray shouldContainExactly List" {
        ulongArrayOf(1u, 2u, 3u) shouldContainExactly listOf(1u, 2u, 3u)
    }

    "ULongArray shouldContainExactly Set" {
        ulongArrayOf(1u, 2u, 3u) shouldContainExactly setOf(1u, 2u, 3u)
    }

    "ULongArray shouldContainExactly Sequence" {
        ulongArrayOf(1u, 2u, 3u) shouldContainExactly sequenceOf(1u, 2u, 3u)
    }

    "ByteArray shouldNotContainExactly ByteArray with different order" {
        byteArrayOf(1, 2, 3) shouldNotContainExactly byteArrayOf(3, 2, 1)
        byteArrayOf(1, 2, 3) shouldNotContainExactly byteArrayOf(1)
    }

    "ByteArray shouldNotContainExactly Collection with different order" {
        byteArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        byteArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "ByteArray shouldNotContainExactly List with different order" {
        byteArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        byteArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "ByteArray shouldNotContainExactly Set with different order" {
        byteArrayOf(1, 2, 3) shouldNotContainExactly setOf(3, 2, 1)
        byteArrayOf(1, 2, 3) shouldNotContainExactly setOf(1)
    }

    "ByteArray shouldNotContainExactly Sequence with different order" {
        byteArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(3, 2, 1)
        byteArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(1)
    }

    "ShortArray shouldNotContainExactly ShortArray with different order" {
        shortArrayOf(1, 2, 3) shouldNotContainExactly shortArrayOf(3, 2, 1)
        shortArrayOf(1, 2, 3) shouldNotContainExactly shortArrayOf(1)
    }

    "ShortArray shouldNotContainExactly Collection with different order" {
        shortArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        shortArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "ShortArray shouldNotContainExactly List with different order" {
        shortArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        shortArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "ShortArray shouldNotContainExactly Set with different order" {
        shortArrayOf(1, 2, 3) shouldNotContainExactly setOf(3, 2, 1)
        shortArrayOf(1, 2, 3) shouldNotContainExactly setOf(1)
    }

    "ShortArray shouldNotContainExactly Sequence with different order" {
        shortArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(3, 2, 1)
        shortArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(1)
    }

    "IntArray shouldNotContainExactly IntArray with different order" {
        intArrayOf(1, 2, 3) shouldNotContainExactly intArrayOf(3, 2, 1)
        intArrayOf(1, 2, 3) shouldNotContainExactly intArrayOf(1)
    }

    "IntArray shouldNotContainExactly Collection with different order" {
        intArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        intArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "IntArray shouldNotContainExactly List with different order" {
        intArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        intArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "IntArray shouldNotContainExactly Set with different order" {
        intArrayOf(1, 2, 3) shouldNotContainExactly setOf(3, 2, 1)
        intArrayOf(1, 2, 3) shouldNotContainExactly setOf(1)
    }

    "IntArray shouldNotContainExactly Sequence with different order" {
        intArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(3, 2, 1)
        intArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(1)
    }

    "LongArray shouldNotContainExactly LongArray with different order" {
        longArrayOf(1, 2, 3) shouldNotContainExactly longArrayOf(3, 2, 1)
        longArrayOf(1, 2, 3) shouldNotContainExactly longArrayOf(1)
    }

    "LongArray shouldNotContainExactly Collection with different order" {
        longArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        longArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "LongArray shouldNotContainExactly List with different order" {
        longArrayOf(1, 2, 3) shouldNotContainExactly listOf(3, 2, 1)
        longArrayOf(1, 2, 3) shouldNotContainExactly listOf(1)
    }

    "LongArray shouldNotContainExactly Set with different order" {
        longArrayOf(1, 2, 3) shouldNotContainExactly setOf(3, 2, 1)
        longArrayOf(1, 2, 3) shouldNotContainExactly setOf(1)
    }

    "LongArray shouldNotContainExactly Sequence with different order" {
        longArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(3, 2, 1)
        longArrayOf(1, 2, 3) shouldNotContainExactly sequenceOf(1)
    }

    "FloatArray shouldNotContainExactly FloatArray with different order" {
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly floatArrayOf(3f, 2f, 1f)
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly floatArrayOf(1f)
    }

    "FloatArray shouldNotContainExactly Collection with different order" {
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly listOf(3f, 2f, 1f)
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly listOf(1f)
    }

    "FloatArray shouldNotContainExactly List with different order" {
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly listOf(3f, 2f, 1f)
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly listOf(1f)
    }

    "FloatArray shouldNotContainExactly Set with different order" {
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly setOf(3f, 2f, 1f)
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly setOf(1f)
    }

    "FloatArray shouldNotContainExactly Sequence with different order" {
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly sequenceOf(3f, 2f, 1f)
        floatArrayOf(1f, 2f, 3f) shouldNotContainExactly sequenceOf(1f)
    }

    "DoubleArray shouldNotContainExactly DoubleArray with different order" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly doubleArrayOf(3.0, 2.0, 1.0)
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly doubleArrayOf(1.0)
    }

    "DoubleArray shouldNotContainExactly Collection with different order" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly listOf(3.0, 2.0, 1.0)
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly listOf(1.0)
    }

    "DoubleArray shouldNotContainExactly List with different order" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly listOf(3.0, 2.0, 1.0)
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly listOf(1.0)
    }

    "DoubleArray shouldNotContainExactly Set with different order" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly setOf(3.0, 2.0, 1.0)
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly setOf(1.0)
    }

    "DoubleArray shouldNotContainExactly Sequence with different order" {
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly sequenceOf(3.0, 2.0, 1.0)
        doubleArrayOf(1.0, 2.0, 3.0) shouldNotContainExactly sequenceOf(1.0)
    }

    "BooleanArray shouldNotContainExactly BooleanArray with different order" {
        booleanArrayOf(true, false, true) shouldNotContainExactly booleanArrayOf(false, true, false)
        booleanArrayOf(true, false, true) shouldNotContainExactly booleanArrayOf(true)
    }

    "BooleanArray shouldNotContainExactly Collection with different order" {
        booleanArrayOf(true, false, true) shouldNotContainExactly listOf(false, true, false)
        booleanArrayOf(true, false, true) shouldNotContainExactly listOf(true)
    }

    "BooleanArray shouldNotContainExactly List with different order" {
        booleanArrayOf(true, false, true) shouldNotContainExactly listOf(false, true, false)
        booleanArrayOf(true, false, true) shouldNotContainExactly listOf(true)
    }

    "BooleanArray shouldNotContainExactly Set with different order" {
        booleanArrayOf(true, false, true) shouldNotContainExactly setOf(false, true, false)
        booleanArrayOf(true, false, true) shouldNotContainExactly setOf(true)
    }

    "BooleanArray shouldNotContainExactly Sequence with different order" {
        booleanArrayOf(true, false, true) shouldNotContainExactly sequenceOf(false, true, false)
        booleanArrayOf(true, false, true) shouldNotContainExactly sequenceOf(true)
    }

    "CharArray shouldNotContainExactly CharArray with different order" {
        charArrayOf('a', 'b', 'c') shouldNotContainExactly charArrayOf('b', 'a', 'c')
        charArrayOf('a', 'b', 'c') shouldNotContainExactly charArrayOf('a')
    }

    "CharArray shouldNotContainExactly Collection with different order" {
        charArrayOf('a', 'b', 'c') shouldNotContainExactly listOf('b', 'a', 'c')
        charArrayOf('a', 'b', 'c') shouldNotContainExactly listOf('a')
    }

    "CharArray shouldNotContainExactly List with different order" {
        charArrayOf('a', 'b', 'c') shouldNotContainExactly listOf('b', 'a', 'c')
        charArrayOf('a', 'b', 'c') shouldNotContainExactly listOf('a')
    }

    "CharArray shouldNotContainExactly Set with different order" {
        charArrayOf('a', 'b', 'c') shouldNotContainExactly setOf('b', 'a', 'c')
        charArrayOf('a', 'b', 'c') shouldNotContainExactly setOf('a')
    }

    "CharArray shouldNotContainExactly Sequence with different order" {
        charArrayOf('a', 'b', 'c') shouldNotContainExactly sequenceOf('b', 'a', 'c')
        charArrayOf('a', 'b', 'c') shouldNotContainExactly sequenceOf('a')
    }

    "UByteArray shouldNotContainExactly UByteArray with different order" {
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly ubyteArrayOf(2u, 1u, 3u)
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly ubyteArrayOf(1u)
    }

    "UByteArray shouldNotContainExactly Collection with different order" {
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "UByteArray shouldNotContainExactly List with different order" {
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "UByteArray shouldNotContainExactly Set with different order" {
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(2u, 1u, 3u)
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(1u)
    }

    "UByteArray shouldNotContainExactly Sequence with different order" {
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(2u, 1u, 3u)
        ubyteArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(1u)
    }

    "UShortArray shouldNotContainExactly UShortArray with different order" {
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly ushortArrayOf(2u, 1u, 3u)
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly ushortArrayOf(1u)
    }

    "UShortArray shouldNotContainExactly Collection with different order" {
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "UShortArray shouldNotContainExactly List with different order" {
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "UShortArray shouldNotContainExactly Set with different order" {
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(2u, 1u, 3u)
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(1u)
    }

    "UShortArray shouldNotContainExactly Sequence with different order" {
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(2u, 1u, 3u)
        ushortArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(1u)
    }

    "UIntArray shouldNotContainExactly UIntArray with different order" {
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly uintArrayOf(2u, 1u, 3u)
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly uintArrayOf(1u)
    }

    "UIntArray shouldNotContainExactly Collection with different order" {
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "UIntArray shouldNotContainExactly List with different order" {
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "UIntArray shouldNotContainExactly Set with different order" {
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(2u, 1u, 3u)
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(1u)
    }

    "UIntArray shouldNotContainExactly Sequence with different order" {
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(2u, 1u, 3u)
        uintArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(1u)
    }

    "ULongArray shouldNotContainExactly ULongArray with different order" {
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly ulongArrayOf(2u, 1u, 3u)
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly ulongArrayOf(1u)
    }

    "ULongArray shouldNotContainExactly Collection with different order" {
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "ULongArray shouldNotContainExactly List with different order" {
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(2u, 1u, 3u)
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly listOf(1u)
    }

    "ULongArray shouldNotContainExactly Set with different order" {
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(2u, 1u, 3u)
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly setOf(1u)
    }

    "ULongArray shouldNotContainExactly Sequence with different order" {
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(2u, 1u, 3u)
        ulongArrayOf(1u, 2u, 3u) shouldNotContainExactly sequenceOf(1u)
    }
})
