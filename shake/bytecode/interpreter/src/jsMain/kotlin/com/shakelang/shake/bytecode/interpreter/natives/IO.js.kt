package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.output.OutputStream

actual val defaultStdin: InputStream
    get() = object : InputStream() {
        override fun read(): Int {
            TODO("Not yet implemented")
        }
    }

actual val defaultStdout: OutputStream
    get() = object : OutputStream() {
        override fun write(b: Int) {
            print(b.toChar())
        }

        override fun write(b: ByteArray) {
            print(b.joinToString { it.toUByte().toInt().toChar().toString() })
        }

        override fun write(b: ByteArray, off: Int, len: Int) {
            this.write(b.sliceArray(off until off + len))
        }
    }
