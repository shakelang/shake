@file:Suppress("unused")

package com.shakelang.shake.bytecode.interpreter

import com.shakelang.util.primitives.calc.shl
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CastUtilTests : FreeSpec(
    {

        "byte to byte cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "byte to shorts cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "byte to int cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "byte to long cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "byte to float cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "byte to double cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "byte to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "byte to ushort cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "byte to uint cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "byte to ulong cast" {
            val stack = ByteStack()
            stack.push(1.toByte())
            CastUtil.performCast(stack, PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "shorts to byte cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "shorts to shorts cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "shorts to int cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "shorts to long cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "shorts to float cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "shorts to double cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "shorts to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "shorts to ushort cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "shorts to uint cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "shorts to ulong cast" {
            val stack = ByteStack()
            stack.push(1.toShort())
            CastUtil.performCast(stack, PCast.SHORT shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "int to byte cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "int to shorts cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "int to int cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "int to long cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "int to float cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "int to double cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "int to ubyte cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "int to ushort cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "int to uint cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "int to ulong cast" {
            val stack = ByteStack()
            stack.push(1)
            CastUtil.performCast(stack, PCast.INT shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "long to byte cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "long to shorts cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "long to int cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "long to long cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "long to float cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "long to double cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "long to ubyte cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "long to ushort cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "long to uint cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "long to ulong cast" {
            val stack = ByteStack()
            stack.push(1L)
            CastUtil.performCast(stack, PCast.LONG shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "float to byte cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "float to shorts cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "float to int cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "float to long cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "float to float cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "float to double cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "float to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "float to ushort cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "float to uint cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "float to ulong cast" {
            val stack = ByteStack()
            stack.push(1.0f)
            CastUtil.performCast(stack, PCast.FLOAT shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "double to byte cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "double to shorts cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "double to int cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "double to long cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "double to float cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "double to double cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "double to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "double to ushort cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "double to uint cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "double to ulong cast" {
            val stack = ByteStack()
            stack.push(1.0)
            CastUtil.performCast(stack, PCast.DOUBLE shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "ubyte to byte cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "ubyte to shorts cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "ubyte to int cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "ubyte to long cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "ubyte to float cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "ubyte to double cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "ubyte to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "ubyte to ushort cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "ubyte to uint cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "ubyte to ulong cast" {
            val stack = ByteStack()
            stack.push(1.toUByte())
            CastUtil.performCast(stack, PCast.UBYTE shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "ushort to byte cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "ushort to shorts cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "ushort to int cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "ushort to long cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "ushort to float cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "ushort to double cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "ushort to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "ushort to ushort cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "ushort to uint cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "ushort to ulong cast" {
            val stack = ByteStack()
            stack.push(1.toUShort())
            CastUtil.performCast(stack, PCast.USHORT shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "uint to byte cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "uint to shorts cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "uint to int cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "uint to long cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "uint to float cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "uint to double cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "uint to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "uint to ushort cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "uint to uint cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "uint to ulong cast" {
            val stack = ByteStack()
            stack.push(1.toUInt())
            CastUtil.performCast(stack, PCast.UINT shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }

        "ulong to byte cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.BYTE)
            stack.size shouldBe 1
            stack.pop() shouldBe 1.toByte()
        }

        "ulong to shorts cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.SHORT)
            stack.size shouldBe 2
            stack.popShort() shouldBe 1.toShort()
        }

        "ulong to int cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.INT)
            stack.size shouldBe 4
            stack.popInt() shouldBe 1
        }

        "ulong to long cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.LONG)
            stack.size shouldBe 8
            stack.popLong() shouldBe 1L
        }

        "ulong to float cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.FLOAT)
            stack.size shouldBe 4
            stack.popFloat() shouldBe 1.0f
        }

        "ulong to double cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.DOUBLE)
            stack.size shouldBe 8
            stack.popDouble() shouldBe 1.0
        }

        "ulong to ubyte cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.UBYTE)
            stack.size shouldBe 1
            stack.popUByte() shouldBe 1.toUByte()
        }

        "ulong to ushort cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.USHORT)
            stack.size shouldBe 2
            stack.popUShort() shouldBe 1.toUShort()
        }

        "ulong to uint cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.UINT)
            stack.size shouldBe 4
            stack.popUInt() shouldBe 1.toUInt()
        }

        "ulong to ulong cast" {
            val stack = ByteStack()
            stack.push(1.toULong())
            CastUtil.performCast(stack, PCast.ULONG shl 4 or PCast.ULONG)
            stack.size shouldBe 8
            stack.popULong() shouldBe 1.toULong()
        }
    },
)
