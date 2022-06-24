package io.github.shakelang.shake.js.native

import io.github.shakelang.shake.js.output.JsValue
import io.github.shakelang.shake.js.output.JsValuedStatement
import io.github.shakelang.shake.processor.program.types.code.ShakeInvocation

interface NativeFunction {

    fun handle(invokation: ShakeInvocation, args: List<JsValue>, receiver: JsValue?) : JsValuedStatement

    val signature: String
}