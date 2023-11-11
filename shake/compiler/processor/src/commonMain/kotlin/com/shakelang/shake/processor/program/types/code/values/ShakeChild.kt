package com.shakelang.shake.processor.program.types.code.values

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.code.ShakeScope

interface ShakeChild : ShakeAssignable {

    val scope: ShakeScope
    val parent: ShakeValue
    val name: String

    val access: ShakeValue
}

interface ShakeChildUsage : ShakeUsage {
    val used: ShakeChild
}
