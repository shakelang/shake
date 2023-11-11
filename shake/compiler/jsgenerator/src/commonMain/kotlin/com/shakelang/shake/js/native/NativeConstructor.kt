package com.shakelang.shake.js.native

import com.shakelang.shake.js.output.JsValue
import com.shakelang.shake.js.output.JsValuedStatement
import io.github.shakelang.shake.processor.program.types.code.ShakeNew

interface NativeConstructor {

    fun handle(it: ShakeNew, args: List<JsValue>): JsValuedStatement
}
