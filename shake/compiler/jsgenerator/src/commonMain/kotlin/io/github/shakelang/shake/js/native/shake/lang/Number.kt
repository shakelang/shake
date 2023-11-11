package io.github.shakelang.shake.js.native.shake.lang

import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.js.native.NativeFunction
import io.github.shakelang.shake.js.output.*
import io.github.shakelang.shake.processor.program.types.code.ShakeInvocation
import kotlin.String

object Number {
    class Plus(
        override val signature: String
    ) : NativeFunction {
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            val left = generator.visitValue(invokation.parent!!)
            val right = generator.visitValue(invokation.arguments[0])
            return JsAdd(left, right)
        }
    }

    class Minus(
        override val signature: String
    ) : NativeFunction {
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            val left = generator.visitValue(invokation.parent!!)
            val right = generator.visitValue(invokation.arguments[0])
            return JsSubtract(left, right)
        }
    }

    class Times(
        override val signature: String
    ) : NativeFunction {
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            val left = generator.visitValue(invokation.parent!!)
            val right = generator.visitValue(invokation.arguments[0])
            return JsMultiply(left, right)
        }
    }

    class Div(
        override val signature: String
    ) : NativeFunction {
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            val left = generator.visitValue(invokation.parent!!)
            val right = generator.visitValue(invokation.arguments[0])
            return JsDivide(left, right)
        }
    }

    class Mod(
        override val signature: String
    ) : NativeFunction {
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            val left = generator.visitValue(invokation.parent!!)
            val right = generator.visitValue(invokation.arguments[0])
            return JsModulo(left, right)
        }
    }

    class Pow(
        override val signature: String
    ) : NativeFunction {
        override fun handleValue(generator: ShakeJsGenerator, invokation: ShakeInvocation): JsValue {
            val left = generator.visitValue(invokation.parent!!)
            val right = generator.visitValue(invokation.arguments[0])
            return JsFunctionCall(
                JsField("pow", parent = JsField("Math")),
                args = listOf(left, right)
            )
        }
    }
}
