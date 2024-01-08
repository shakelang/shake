package com.shakelang.shake.bytecode.interpreter

import com.shakelang.util.primitives.calc.shr

object CastUtil {

    fun performCast(stack: ByteStack, cast: UByte) {
        val from = (cast and 0xF0u) shr 4
        val target = cast and 0x0Fu

        when (from) {
            PCast.BYTE -> performByteCast(stack, target)
            PCast.SHORT -> performShortCast(stack, target)
            PCast.INT -> performIntCast(stack, target)
            PCast.LONG -> performLongCast(stack, target)
            PCast.FLOAT -> performFloatCast(stack, target)
            PCast.DOUBLE -> performDoubleCast(stack, target)
            PCast.UBYTE -> performUByteCast(stack, target)
            PCast.USHORT -> performUShortCast(stack, target)
            PCast.UINT -> performUIntCast(stack, target)
            PCast.ULONG -> performULongCast(stack, target)
        }
    }

    private fun performByteCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> {
                /* do nothing */
            }

            PCast.SHORT -> stack.push(stack.pop().toShort())
            PCast.INT -> stack.push(stack.pop().toInt())
            PCast.LONG -> stack.push(stack.pop().toLong())
            PCast.FLOAT -> stack.push(stack.pop().toFloat())
            PCast.DOUBLE -> stack.push(stack.pop().toDouble())
            PCast.UBYTE -> stack.push(stack.pop().toUByte())
            PCast.USHORT -> stack.push(stack.pop().toUShort())
            PCast.UINT -> stack.push(stack.pop().toUInt())
            PCast.ULONG -> stack.push(stack.pop().toULong())
        }
    }

    private fun performShortCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popShort().toByte())
            PCast.SHORT -> {
                /* do nothing */
            }

            PCast.INT -> stack.push(stack.popShort().toInt())
            PCast.LONG -> stack.push(stack.popShort().toLong())
            PCast.FLOAT -> stack.push(stack.popShort().toFloat())
            PCast.DOUBLE -> stack.push(stack.popShort().toDouble())
            PCast.UBYTE -> stack.push(stack.popShort().toUByte())
            PCast.USHORT -> stack.push(stack.popShort().toUShort())
            PCast.UINT -> stack.push(stack.popShort().toUInt())
            PCast.ULONG -> stack.push(stack.popShort().toULong())
        }
    }

    private fun performIntCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popInt().toByte())
            PCast.SHORT -> stack.push(stack.popInt().toShort())
            PCast.INT -> {
                /* do nothing */
            }

            PCast.LONG -> stack.push(stack.popInt().toLong())
            PCast.FLOAT -> stack.push(stack.popInt().toFloat())
            PCast.DOUBLE -> stack.push(stack.popInt().toDouble())
            PCast.UBYTE -> stack.push(stack.popInt().toUByte())
            PCast.USHORT -> stack.push(stack.popInt().toUShort())
            PCast.UINT -> stack.push(stack.popInt().toUInt())
            PCast.ULONG -> stack.push(stack.popInt().toULong())
        }
    }

    private fun performLongCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popLong().toByte())
            PCast.SHORT -> stack.push(stack.popLong().toShort())
            PCast.INT -> stack.push(stack.popLong().toInt())
            PCast.LONG -> {
                /* do nothing */
            }

            PCast.FLOAT -> stack.push(stack.popLong().toFloat())
            PCast.DOUBLE -> stack.push(stack.popLong().toDouble())
            PCast.UBYTE -> stack.push(stack.popLong().toUByte())
            PCast.USHORT -> stack.push(stack.popLong().toUShort())
            PCast.UINT -> stack.push(stack.popLong().toUInt())
            PCast.ULONG -> stack.push(stack.popLong().toULong())
        }
    }

    private fun performFloatCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popFloat().toInt().toByte())
            PCast.SHORT -> stack.push(stack.popFloat().toInt().toShort())
            PCast.INT -> stack.push(stack.popFloat().toInt())
            PCast.LONG -> stack.push(stack.popFloat().toLong())
            PCast.FLOAT -> {
                /* do nothing */
            }

            PCast.DOUBLE -> stack.push(stack.popFloat().toDouble())
            PCast.UBYTE -> stack.push(stack.popFloat().toInt().toUByte())
            PCast.USHORT -> stack.push(stack.popFloat().toInt().toUShort())
            PCast.UINT -> stack.push(stack.popFloat().toUInt())
            PCast.ULONG -> stack.push(stack.popFloat().toULong())
        }
    }

    private fun performDoubleCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popDouble().toInt().toByte())
            PCast.SHORT -> stack.push(stack.popDouble().toInt().toShort())
            PCast.INT -> stack.push(stack.popDouble().toInt())
            PCast.LONG -> stack.push(stack.popDouble().toLong())
            PCast.FLOAT -> stack.push(stack.popDouble().toFloat())
            PCast.DOUBLE -> {
                /* do nothing */
            }

            PCast.UBYTE -> stack.push(stack.popDouble().toInt().toUByte())
            PCast.USHORT -> stack.push(stack.popDouble().toInt().toUShort())
            PCast.UINT -> stack.push(stack.popDouble().toUInt())
            PCast.ULONG -> stack.push(stack.popDouble().toULong())
        }
    }

    private fun performUByteCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popUByte().toByte())
            PCast.SHORT -> stack.push(stack.popUByte().toShort())
            PCast.INT -> stack.push(stack.popUByte().toInt())
            PCast.LONG -> stack.push(stack.popUByte().toLong())
            PCast.FLOAT -> stack.push(stack.popUByte().toFloat())
            PCast.DOUBLE -> stack.push(stack.popUByte().toDouble())
            PCast.UBYTE -> {
                /* do nothing */
            }

            PCast.USHORT -> stack.push(stack.popUByte().toUShort())
            PCast.UINT -> stack.push(stack.popUByte().toUInt())
            PCast.ULONG -> stack.push(stack.popUByte().toULong())
        }
    }

    private fun performUShortCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popUShort().toByte())
            PCast.SHORT -> stack.push(stack.popUShort().toShort())
            PCast.INT -> stack.push(stack.popUShort().toInt())
            PCast.LONG -> stack.push(stack.popUShort().toLong())
            PCast.FLOAT -> stack.push(stack.popUShort().toFloat())
            PCast.DOUBLE -> stack.push(stack.popUShort().toDouble())
            PCast.UBYTE -> stack.push(stack.popUShort().toUByte())
            PCast.USHORT -> {
                /* do nothing */
            }

            PCast.UINT -> stack.push(stack.popUShort().toUInt())
            PCast.ULONG -> stack.push(stack.popUShort().toULong())
        }
    }

    private fun performUIntCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popUInt().toByte())
            PCast.SHORT -> stack.push(stack.popUInt().toShort())
            PCast.INT -> stack.push(stack.popUInt().toInt())
            PCast.LONG -> stack.push(stack.popUInt().toLong())
            PCast.FLOAT -> stack.push(stack.popUInt().toFloat())
            PCast.DOUBLE -> stack.push(stack.popUInt().toDouble())
            PCast.UBYTE -> stack.push(stack.popUInt().toUByte())
            PCast.USHORT -> stack.push(stack.popUInt().toUShort())
            PCast.UINT -> {
                /* do nothing */
            }

            PCast.ULONG -> stack.push(stack.popUInt().toULong())
        }
    }

    private fun performULongCast(stack: ByteStack, target: UByte) {
        when (target) {
            PCast.BYTE -> stack.push(stack.popULong().toByte())
            PCast.SHORT -> stack.push(stack.popULong().toShort())
            PCast.INT -> stack.push(stack.popULong().toInt())
            PCast.LONG -> stack.push(stack.popULong().toLong())
            PCast.FLOAT -> stack.push(stack.popULong().toFloat())
            PCast.DOUBLE -> stack.push(stack.popULong().toDouble())
            PCast.UBYTE -> stack.push(stack.popULong().toUByte())
            PCast.USHORT -> stack.push(stack.popULong().toUShort())
            PCast.UINT -> stack.push(stack.popULong().toUInt())
            PCast.ULONG -> {
                /* do nothing */
            }
        }
    }
}
