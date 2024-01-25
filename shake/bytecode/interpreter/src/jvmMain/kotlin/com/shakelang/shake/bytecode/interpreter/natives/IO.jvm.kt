package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.output.OutputStream

actual val defaultStdin: InputStream
    get() = System.`in`
actual val defaultStdout: OutputStream
    get() = System.out
