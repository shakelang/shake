package com.shakelang.shake.js.native

import com.shakelang.shake.js.ShakeJsGenerator
import com.shakelang.shake.js.output.JsValue
import io.github.shakelang.shake.processor.program.types.code.values.ShakeFieldUsage

interface NativeField {

    fun handle(generator: ShakeJsGenerator, fieldUsage: ShakeFieldUsage): JsValue

    val signature: String
}
