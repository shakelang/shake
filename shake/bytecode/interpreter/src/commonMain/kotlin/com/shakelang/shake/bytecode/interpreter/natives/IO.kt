package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream

expect val defaultStdin: InputStream
expect val defaultStdout: OutputStream
