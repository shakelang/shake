package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.output.OutputStream
import com.shakelang.util.primitives.bytes.toBytes
import kotlin.math.pow

class ShakeInterpreterProcess {

    var stdin = defaultStdin
        private set

    var stdout = defaultStdout
        private set

    fun setIn(inputStream: InputStream) {
        stdin = inputStream
    }

    fun setOut(outputStream: OutputStream) {
        stdout = outputStream
    }

    private val _natives: MutableMap<String, ShakeBytecodeNative> = mutableMapOf()
    val natives: Map<String, ShakeBytecodeNative> get() = _natives

    fun native(signature: String, native: ShakeBytecodeNative.() -> Unit) {
        _natives[signature] = createNative(signature, native)
    }

    init {
        native("shake/lang/print(B)V") {
            val value = stack.popByte()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(b)V") {
            val value = stack.popUByte()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(S)V") {
            val value = stack.popShort()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(s)V") {
            val value = stack.popUShort()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(I)V") {
            val value = stack.popInt()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(i)V") {
            val value = stack.popUInt()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(J)V") {
            val value = stack.popLong()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(j)V") {
            val value = stack.popULong()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(F)V") {
            val value = stack.popFloat()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(D)V") {
            val value = stack.popDouble()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(Z)V") {
            val value = stack.popByte()
            stdout.write((if (value != 0.toByte()) "true" else "false").toBytes())
        }

        native("shake/lang/print(C)V") {
            val value = stack.popShort().toInt().toChar()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/println()V") {
            stdout.write("\n".toBytes())
        }

        native("shake/lang/println(B)V") {
            val value = stack.popUByte()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(b)V") {
            val value = stack.popUByte()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(S)V") {
            val value = stack.popShort()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(s)V") {
            val value = stack.popUShort()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(I)V") {
            val value = stack.popInt()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(i)V") {
            val value = stack.popUInt()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(J)V") {
            val value = stack.popLong()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(j)V") {
            val value = stack.popULong()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(F)V") {
            val value = stack.popByte()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(D)V") {
            val value = stack.popByte()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(Z)V") {
            val value = stack.popByte()
            stdout.write((if (value != 0.toByte()) "true" else "false").toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(C)V") {
            val value = stack.popShort().toInt().toChar()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        // power

        native("shake/lang/pow(B,B)D") {
            print("pow(B,B)")
            val right = stack.popByte()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,b)D") {
            val right = stack.popUByte()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,S)D") {
            val right = stack.popShort()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,s)D") {
            val right = stack.popUShort()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,I)D") {
            val right = stack.popInt()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,i)D") {
            val right = stack.popUInt()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,J)D") {
            val right = stack.popLong()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,j)D") {
            val right = stack.popULong()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,F)D") {
            val right = stack.popFloat()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(B,D)D") {
            val right = stack.popDouble()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,B)D") {
            val right = stack.popByte()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,b)D") {
            val right = stack.popUByte()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,S)D") {
            val right = stack.popShort()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,s)D") {
            val right = stack.popUShort()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,I)D") {
            val right = stack.popInt()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,i)D") {
            val right = stack.popUInt()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,J)D") {
            val right = stack.popLong()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,j)D") {
            val right = stack.popULong()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,F)D") {
            val right = stack.popFloat()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(b,D)D") {
            val right = stack.popDouble()
            val left = stack.popByte()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,B)D") {
            val right = stack.popByte()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,b)D") {
            val right = stack.popUByte()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,S)D") {
            val right = stack.popShort()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,s)D") {
            val right = stack.popUShort()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,I)D") {
            val right = stack.popInt()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,i)D") {
            val right = stack.popUInt()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,J)D") {
            val right = stack.popLong()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,j)D") {
            val right = stack.popULong()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,F)D") {
            val right = stack.popFloat()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(S,D)D") {
            val right = stack.popDouble()
            val left = stack.popShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,B)D") {
            val right = stack.popByte()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,b)D") {
            val right = stack.popUByte()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,S)D") {
            val right = stack.popShort()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,s)D") {
            val right = stack.popUShort()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,I)D") {
            val right = stack.popInt()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,i)D") {
            val right = stack.popUInt()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,J)D") {
            val right = stack.popLong()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,j)D") {
            val right = stack.popULong()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,F)D") {
            val right = stack.popFloat()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(s,D)D") {
            val right = stack.popDouble()
            val left = stack.popUShort()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,B)D") {
            val right = stack.popByte()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,b)D") {
            val right = stack.popUByte()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,S)D") {
            val right = stack.popShort()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,s)D") {
            val right = stack.popUShort()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,I)D") {
            val right = stack.popInt()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,i)D") {
            val right = stack.popUInt()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,J)D") {
            val right = stack.popLong()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,j)D") {
            val right = stack.popULong()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,F)D") {
            val right = stack.popFloat()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(I,D)D") {
            val right = stack.popDouble()
            val left = stack.popInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,B)D") {
            val right = stack.popByte()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,b)D") {
            val right = stack.popUByte()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,S)D") {
            val right = stack.popShort()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,s)D") {
            val right = stack.popUShort()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,I)D") {
            val right = stack.popInt()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,i)D") {
            val right = stack.popUInt()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,J)D") {
            val right = stack.popLong()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,j)D") {
            val right = stack.popULong()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,F)D") {
            val right = stack.popFloat()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(i,D)D") {
            val right = stack.popDouble()
            val left = stack.popUInt()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,B)D") {
            val right = stack.popByte()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,b)D") {
            val right = stack.popUByte()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,S)D") {
            val right = stack.popShort()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,s)D") {
            val right = stack.popUShort()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,I)D") {
            val right = stack.popInt()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,i)D") {
            val right = stack.popUInt()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,J)D") {
            val right = stack.popLong()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,j)D") {
            val right = stack.popULong()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,F)D") {
            val right = stack.popFloat()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(J,D)D") {
            val right = stack.popDouble()
            val left = stack.popLong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,B)D") {
            val right = stack.popByte()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,b)D") {
            val right = stack.popUByte()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,S)D") {
            val right = stack.popShort()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,s)D") {
            val right = stack.popUShort()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,I)D") {
            val right = stack.popInt()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,i)D") {
            val right = stack.popUInt()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,J)D") {
            val right = stack.popLong()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,j)D") {
            val right = stack.popULong()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,F)D") {
            val right = stack.popFloat()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(j,D)D") {
            val right = stack.popDouble()
            val left = stack.popULong()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,B)D") {
            val right = stack.popByte()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,b)D") {
            val right = stack.popUByte()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,S)D") {
            val right = stack.popShort()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,s)D") {
            val right = stack.popUShort()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,I)D") {
            val right = stack.popInt()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,i)D") {
            val right = stack.popUInt()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,J)D") {
            val right = stack.popLong()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,j)D") {
            val right = stack.popULong()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,F)D") {
            val right = stack.popFloat()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(F,D)D") {
            val right = stack.popDouble()
            val left = stack.popFloat()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,B)D") {
            val right = stack.popByte()
            val left = stack.popDouble()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,b)D") {
            val right = stack.popUByte()
            val left = stack.popDouble()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,S)D") {
            val right = stack.popShort()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,s)D") {
            val right = stack.popUShort()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,I)D") {
            val right = stack.popInt()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,i)D") {
            val right = stack.popUInt()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,J)D") {
            val right = stack.popLong()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,j)D") {
            val right = stack.popULong()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,F)D") {
            val right = stack.popFloat()
            val left = stack.popDouble()
            returnData(left.pow(right.toDouble()).toBytes())
        }

        native("shake/lang/pow(D,D)D") {
            val right = stack.popDouble()
            val left = stack.popDouble()
            returnData(left.toDouble().pow(right.toDouble()).toBytes())
        }
    }
}
