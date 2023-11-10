package io.github.shakelang.shake.processor.program.creation.code.values

import io.github.shakelang.shake.processor.program.types.ShakeType
import io.github.shakelang.shake.processor.program.types.code.values.ShakeValue

interface CreationShakeValue : ShakeValue {
    override val type: ShakeType
    override fun toJson(): Map<String, Any?>
}
