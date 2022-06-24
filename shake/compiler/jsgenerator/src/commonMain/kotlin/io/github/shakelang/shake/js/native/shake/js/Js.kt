package io.github.shakelang.shake.js.native.shake.js

import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.js.native.NativeFunction
import io.github.shakelang.shake.js.output.JsValuedStatement
import io.github.shakelang.shake.processor.program.types.code.ShakeInvocation

class Js : NativeFunction {

    override val signature: String
        get() = "shake.js.js(LString)DY"

    override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
        return object : JsValuedStatement {
            override fun generate(indentAmount: Int, indent: String): String {
                return in
            }

            override val needsParens: Boolean
                get() = true

        }
    }

}