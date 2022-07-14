package io.github.shakelang.shake.js.native

import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.js.output.JsValue
import io.github.shakelang.shake.processor.program.types.code.values.ShakeFieldUsage

interface NativeField {

    fun handle(generator: ShakeJsGenerator, fieldUsage: ShakeFieldUsage) : JsValue

    val signature: String
}