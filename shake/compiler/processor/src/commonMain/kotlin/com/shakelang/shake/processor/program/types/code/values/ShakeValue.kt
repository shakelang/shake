package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeProject
import com.shakelang.shake.processor.program.types.ShakeType

interface ShakeValue {
    val project: ShakeProject
    val type: ShakeType
    fun toJson(): Map<String, Any?>
}
