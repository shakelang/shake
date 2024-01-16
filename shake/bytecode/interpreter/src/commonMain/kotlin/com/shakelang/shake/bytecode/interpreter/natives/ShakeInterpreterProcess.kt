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
            val value = stack.popByte()
            stdout.write(value.toString().toBytes())
        }

        native("shake/lang/print(D)V") {
            val value = stack.popByte()
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

        native("shake/lang/power(BB)D") {
            val right = stack.popByte()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Bb)D") {
            val right = stack.popUByte()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(BS)D") {
            val right = stack.popShort()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Bs)D") {
            val right = stack.popUShort()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(BI)D") {
            val right = stack.popInt()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Bi)D") {
            val right = stack.popUInt()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(BJ)D") {
            val right = stack.popLong()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Bj)D") {
            val right = stack.popULong()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(BF)D") {
            val right = stack.popFloat()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(BD)D") {
            val right = stack.popDouble()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bB)D") {
            val right = stack.popByte()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bb)D") {
            val right = stack.popUByte()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bS)D") {
            val right = stack.popShort()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bs)D") {
            val right = stack.popUShort()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bI)D") {
            val right = stack.popInt()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bi)D") {
            val right = stack.popUInt()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bJ)D") {
            val right = stack.popLong()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bj)D") {
            val right = stack.popULong()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bF)D") {
            val right = stack.popFloat()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(bD)D") {
            val right = stack.popDouble()
            val left = stack.popByte()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(SB)D") {
            val right = stack.popByte()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Sb)D") {
            val right = stack.popUByte()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(SS)D") {
            val right = stack.popShort()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Ss)D") {
            val right = stack.popUShort()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(SI)D") {
            val right = stack.popInt()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Si)D") {
            val right = stack.popUInt()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(SJ)D") {
            val right = stack.popLong()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Sj)D") {
            val right = stack.popULong()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(SF)D") {
            val right = stack.popFloat()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(SD)D") {
            val right = stack.popDouble()
            val left = stack.popShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sB)D") {
            val right = stack.popByte()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sb)D") {
            val right = stack.popUByte()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sS)D") {
            val right = stack.popShort()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(ss)D") {
            val right = stack.popUShort()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sI)D") {
            val right = stack.popInt()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(si)D") {
            val right = stack.popUInt()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sJ)D") {
            val right = stack.popLong()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sj)D") {
            val right = stack.popULong()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sF)D") {
            val right = stack.popFloat()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(sD)D") {
            val right = stack.popDouble()
            val left = stack.popUShort()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(IB)D") {
            val right = stack.popByte()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Ib)D") {
            val right = stack.popUByte()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(IS)D") {
            val right = stack.popShort()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Is)D") {
            val right = stack.popUShort()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(II)D") {
            val right = stack.popInt()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Ii)D") {
            val right = stack.popUInt()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(IJ)D") {
            val right = stack.popLong()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Ij)D") {
            val right = stack.popULong()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(IF)D") {
            val right = stack.popFloat()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(ID)D") {
            val right = stack.popDouble()
            val left = stack.popInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(iB)D") {
            val right = stack.popByte()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(ib)D") {
            val right = stack.popUByte()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(iS)D") {
            val right = stack.popShort()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(is)D") {
            val right = stack.popUShort()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(iI)D") {
            val right = stack.popInt()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(ii)D") {
            val right = stack.popUInt()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(iJ)D") {
            val right = stack.popLong()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(ij)D") {
            val right = stack.popULong()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(iF)D") {
            val right = stack.popFloat()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(iD)D") {
            val right = stack.popDouble()
            val left = stack.popUInt()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(JB)D") {
            val right = stack.popByte()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Jb)D") {
            val right = stack.popUByte()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(JS)D") {
            val right = stack.popShort()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Js)D") {
            val right = stack.popUShort()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(JI)D") {
            val right = stack.popInt()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Ji)D") {
            val right = stack.popUInt()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(JJ)D") {
            val right = stack.popLong()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Jj)D") {
            val right = stack.popULong()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(JF)D") {
            val right = stack.popFloat()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(JD)D") {
            val right = stack.popDouble()
            val left = stack.popLong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jB)D") {
            val right = stack.popByte()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jb)D") {
            val right = stack.popUByte()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jS)D") {
            val right = stack.popShort()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(js)D") {
            val right = stack.popUShort()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jI)D") {
            val right = stack.popInt()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(ji)D") {
            val right = stack.popUInt()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jJ)D") {
            val right = stack.popLong()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jj)D") {
            val right = stack.popULong()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jF)D") {
            val right = stack.popFloat()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(jD)D") {
            val right = stack.popDouble()
            val left = stack.popULong()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(FB)D") {
            val right = stack.popByte()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Fb)D") {
            val right = stack.popUByte()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(FS)D") {
            val right = stack.popShort()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Fs)D") {
            val right = stack.popUShort()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(FI)D") {
            val right = stack.popInt()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Fi)D") {
            val right = stack.popUInt()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(FJ)D") {
            val right = stack.popLong()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Fj)D") {
            val right = stack.popULong()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(FF)D") {
            val right = stack.popFloat()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(FD)D") {
            val right = stack.popDouble()
            val left = stack.popFloat()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(DB)D") {
            val right = stack.popByte()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Db)D") {
            val right = stack.popUByte()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(DS)D") {
            val right = stack.popShort()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Ds)D") {
            val right = stack.popUShort()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(DI)D") {
            val right = stack.popInt()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Di)D") {
            val right = stack.popUInt()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(DJ)D") {
            val right = stack.popLong()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(Dj)D") {
            val right = stack.popULong()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(DF)D") {
            val right = stack.popFloat()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }

        native("shake/lang/power(DD)D") {
            val right = stack.popDouble()
            val left = stack.popDouble()
            stack.push(left.toDouble().pow(right.toDouble()))
        }
    }
}
