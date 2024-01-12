package com.shakelang.shake.bytecode.interpreter.natives

import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.output.OutputStream

expect val defaultStdin: InputStream
expect val defaultStdout: OutputStream
