@file:Suppress("unused")
package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.util.primitives.calc.shl
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CastUtilTests : FreeSpec({

    "test byte to byte cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test byte to short cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test byte to int cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test byte to long cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test byte to float cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test byte to double cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test byte to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test byte to ushort cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test byte to uint cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test byte to ulong cast" {
        val stack = ByteStack()
        stack.push(1.toByte())
        CastUtil.performCast(stack, PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test short to byte cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test short to short cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test short to int cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test short to long cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test short to float cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test short to double cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test short to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test short to ushort cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test short to uint cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test short to ulong cast" {
        val stack = ByteStack()
        stack.push(1.toShort())
        CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test int to byte cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test int to short cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test int to int cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test int to long cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test int to float cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test int to double cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test int to ubyte cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test int to ushort cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test int to uint cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test int to ulong cast" {
        val stack = ByteStack()
        stack.push(1)
        CastUtil.performCast(stack, PCast.INT shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test long to byte cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test long to short cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test long to int cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test long to long cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test long to float cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test long to double cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test long to ubyte cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test long to ushort cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test long to uint cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test long to ulong cast" {
        val stack = ByteStack()
        stack.push(1L)
        CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test float to byte cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test float to short cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test float to int cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test float to long cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test float to float cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test float to double cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test float to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test float to ushort cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test float to uint cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test float to ulong cast" {
        val stack = ByteStack()
        stack.push(1.0f)
        CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test double to byte cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test double to short cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test double to int cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test double to long cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test double to float cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test double to double cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test double to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test double to ushort cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test double to uint cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test double to ulong cast" {
        val stack = ByteStack()
        stack.push(1.0)
        CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test ubyte to byte cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test ubyte to short cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test ubyte to int cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test ubyte to long cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test ubyte to float cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test ubyte to double cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test ubyte to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test ubyte to ushort cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test ubyte to uint cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test ubyte to ulong cast" {
        val stack = ByteStack()
        stack.push(1.toUByte())
        CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test ushort to byte cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test ushort to short cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test ushort to int cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test ushort to long cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test ushort to float cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test ushort to double cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test ushort to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test ushort to ushort cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test ushort to uint cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test ushort to ulong cast" {
        val stack = ByteStack()
        stack.push(1.toUShort())
        CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test uint to byte cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test uint to short cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test uint to int cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test uint to long cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test uint to float cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test uint to double cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test uint to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test uint to ushort cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test uint to uint cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test uint to ulong cast" {
        val stack = ByteStack()
        stack.push(1.toUInt())
        CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }

    "test ulong to byte cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.BYTE)
        stack.size shouldBe 1
        stack.pop() shouldBe 1.toByte()
    }

    "test ulong to short cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.SHORT)
        stack.size shouldBe 2
        stack.popShort() shouldBe 1.toShort()
    }

    "test ulong to int cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.INT)
        stack.size shouldBe 4
        stack.popInt() shouldBe 1
    }

    "test ulong to long cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.LONG)
        stack.size shouldBe 8
        stack.popLong() shouldBe 1L
    }

    "test ulong to float cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.FLOAT)
        stack.size shouldBe 4
        stack.popFloat() shouldBe 1.0f
    }

    "test ulong to double cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.DOUBLE)
        stack.size shouldBe 8
        stack.popDouble() shouldBe 1.0
    }

    "test ulong to ubyte cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.UBYTE)
        stack.size shouldBe 1
        stack.popUByte() shouldBe 1.toUByte()
    }

    "test ulong to ushort cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.USHORT)
        stack.size shouldBe 2
        stack.popUShort() shouldBe 1.toUShort()
    }

    "test ulong to uint cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.UINT)
        stack.size shouldBe 4
        stack.popUInt() shouldBe 1.toUInt()
    }

    "test ulong to ulong cast" {
        val stack = ByteStack()
        stack.push(1.toULong())
        CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.ULONG)
        stack.size shouldBe 8
        stack.popULong() shouldBe 1.toULong()
    }
})