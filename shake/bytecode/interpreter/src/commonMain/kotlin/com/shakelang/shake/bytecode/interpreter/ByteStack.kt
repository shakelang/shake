package com.shakelang.shake.bytecode.interpreter

import com.shakelang.util.primitives.bytes.*
import kotlin.jvm.JvmName

class StackOverflowException : Exception("Stack overflow")
class StackUnderflowException : Exception("Stack underflow")

class ByteStackElement(
    val value: Byte,
    val below: ByteStackElement?,
)

class ByteStack(
    val maxSize: Int = 1000,
) {

    var size = 0
        private set

    private var top: ByteStackElement? = null

    fun push(value: Byte) {
        if (size >= maxSize) throw StackOverflowException()
        top = ByteStackElement(value, top)
        size++
//        println("push ${value.toBytes().toHexString()} (${toByteArray().toHexString()})")
    }

    fun pop(): Byte {
        if (size <= 0) throw StackUnderflowException()
        val value = top!!.value
        top = top!!.below
        size--
//        println("pop ${value.toBytes().toHexString()} (${toByteArray().toHexString()})")
        return value
    }

    fun popByte(): Byte {
        return pop()
    }

    fun peek(): Byte = top!!.value

    fun push(values: ByteArray) {
        for (value in values.reversed()) push(value)
    }

    fun pop(size: Int): ByteArray {
        val bytes = ByteArray(size)
        for (i in 0 until size) bytes[i] = pop()
        return bytes
    }

    fun push(value: Short) = push(value.toBytes())
    fun push(value: Int) = push(value.toBytes())
    fun push(value: Long) = push(value.toBytes())
    fun push(value: Float) = push(value.toBytes())
    fun push(value: Double) = push(value.toBytes())

    fun popShort(): Short = pop(2).toShort()
    fun popInt(): Int = pop(4).toInt()
    fun popLong(): Long = pop(8).toLong()
    fun popFloat(): Float = pop(4).toFloat()
    fun popDouble(): Double = pop(8).toDouble()
    fun popUByte(): UByte = pop().toUByte()
    fun popUShort(): UShort = popShort().toUShort()
    fun popUInt(): UInt = popInt().toUInt()
    fun popULong(): ULong = popLong().toULong()

    @JvmName("pushUByte")
    fun push(uByte: UByte) = push(uByte.toByte())

    @JvmName("pushUShort")
    fun push(uShort: UShort) = push(uShort.toShort())

    @JvmName("pushUInt")
    fun push(uInt: UInt) = push(uInt.toInt())

    @JvmName("pushULong")
    fun push(uLong: ULong) = push(uLong.toLong())

    fun toByteArray(): ByteArray {
        val bytes = ByteArray(size)
        var element = top
        for (i in 0 until size) {
            bytes[i] = element!!.value
            element = element.below
        }
        return bytes.reversedArray()
    }
}
