package io.github.shakelang.shake.processor.program.immutable

import io.github.shakelang.shake.processor.program.types.code.ShakeInvokable
import io.github.shakelang.shake.processor.program.types.code.ShakeScope

interface ImmutableShakeScope : ShakeScope {

    override fun getInvokable(name: String): List<ShakeInvokable> {
        return getFunctions(name)
    }

    override fun use(name: String) {

    }
}