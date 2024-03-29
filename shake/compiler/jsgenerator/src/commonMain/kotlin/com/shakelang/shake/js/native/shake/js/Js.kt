package com.shakelang.shake.js.native.shake.js

import com.shakelang.shake.js.ShakeJsGenerator
import com.shakelang.shake.js.native.NativeFunction
import com.shakelang.shake.js.output.JsValuedStatement
import com.shakelang.shake.processor.program.types.code.ShakeInvocation
import com.shakelang.shake.processor.program.types.code.values.ShakeStringLiteral

class Js : NativeFunction {

    override val signature: String
        get() = "shake.js\$js(Lshake.lang.String)?"

    override fun handle(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValuedStatement {
        return object : JsValuedStatement {
            override fun generate(indentAmount: Int, indent: String): String {
                if (invokation.arguments.size != 1) {
                    throw IllegalArgumentException("shake.js.js takes exactly one argument")
                }
                val argument = invokation.arguments[0]
                if (argument !is ShakeStringLiteral) {
                    throw IllegalArgumentException("shake.js.js takes exactly one string argument")
                }
                return argument.value
            }

            override val needsParens: Boolean
                get() = true
        }
    }
}
