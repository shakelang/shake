package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream

actual val defaultStdin: InputStream
    get() = System.`in`
actual val defaultStdout: OutputStream
    get() = System.out
