@file:Suppress("unused")

package io.github.shakelang.shake.util.parseutils

private var helperByte: Byte = 0
fun generateByte(reset: Boolean = false): Byte = if (reset) {
    helperByte = 0
    0
} else {
    helperByte++
}

private var helperShort: Short = 0
fun generateShort(reset: Boolean = false): Short = if (reset) {
    helperShort = 0
    0
} else {
    helperShort++
}

private var helperInt: Int = 0
fun generateInt(reset: Boolean = false): Int = if (reset) {
    helperInt = 0
    0
} else {
    helperInt++
}

private var helperLong: Long = 0
fun generateLong(reset: Boolean = false): Long = if (reset) {
    helperLong = 0
    0
} else {
    helperLong++
}

private var helperUByte: UByte = 0u
fun generateUByte(reset: Boolean = false): UByte = if (reset) {
    helperUByte = 0u
    0u
} else {
    helperUByte++
}

fun bGenerateUByte(reset: Boolean = false): Byte = generateUByte(reset).toByte()

private var helperUShort: UShort = 0u
fun generateUShort(reset: Boolean = false): UShort = if (reset) {
    helperUShort = 0u
    0u
} else {
    helperUShort++
}

private var helperUInt: UInt = 0u
fun generateUInt(reset: Boolean = false): UInt = if (reset) {
    helperUInt = 0u
    0u
} else {
    helperUInt++
}

private var helperULong: ULong = 0u
fun generateULong(reset: Boolean = false): ULong = if (reset) {
    helperULong = 0u
    0u
} else {
    helperULong++
}