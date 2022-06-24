package io.github.shakelang.shake.js.native

import io.github.shakelang.shake.js.output.JsValue
import io.github.shakelang.shake.processor.program.types.code.values.ShakeFieldUsage

interface NativeField {

    fun handle(fieldUsage: ShakeFieldUsage, receiver: JsValue?) : JsValue

    val signature: String
}