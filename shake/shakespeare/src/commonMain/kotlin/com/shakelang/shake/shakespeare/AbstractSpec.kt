package com.shakelang.shake.shakespeare

import com.shakelang.shake.shakespeare.spec.GenerationContext

interface AbstractSpec {
    fun generate(ctx: GenerationContext): String
}
