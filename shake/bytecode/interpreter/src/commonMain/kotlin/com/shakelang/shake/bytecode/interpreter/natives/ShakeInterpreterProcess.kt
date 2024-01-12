package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.output.OutputStream
import com.shakelang.util.primitives.bytes.toBytes

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
            val value = stack.pop()
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
            val value = stack.pop()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(D)V") {
            val value = stack.pop()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(Z)V") {
            val value = stack.pop()
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
            val value = stack.pop()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(D)V") {
            val value = stack.pop()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(Z)V") {
            val value = stack.pop()
            stdout.write((if (value != 0.toByte()) "true" else "false").toBytes() + "\n".toBytes())
        }

        native("shake/lang/println(C)V") {
            val value = stack.popShort().toInt().toChar()
            stdout.write(value.toString().toBytes() + "\n".toBytes())
        }
    }
}
