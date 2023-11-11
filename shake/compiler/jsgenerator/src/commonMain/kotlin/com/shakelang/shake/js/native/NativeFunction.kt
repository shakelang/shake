package com.shakelang.shake.js.native

import com.shakelang.shake.js.ShakeJsGenerator
import com.shakelang.shake.js.output.JsStatement
import com.shakelang.shake.js.output.JsValue
import com.shakelang.shake.js.output.JsValuedStatement
import com.shakelang.shake.processor.program.types.code.ShakeInvocation

interface NativeFunction {

    val signature: String
    fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
        error("Not available")
    }

    fun handleStatement(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsStatement =
        handle(generator, invokation)

    fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue = handle(generator, invokation)
}
