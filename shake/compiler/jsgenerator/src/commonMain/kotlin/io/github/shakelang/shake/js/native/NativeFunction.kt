package io.github.shakelang.shake.js.native

import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.js.output.JsValuedStatement
import io.github.shakelang.shake.processor.program.types.code.ShakeInvocation

interface NativeFunction {

    val signature: String
    fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation) : JsValuedStatement

}