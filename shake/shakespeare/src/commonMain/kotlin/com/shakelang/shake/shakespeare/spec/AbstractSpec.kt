package com.shakelang.shake.shakespeare.spec

interface AbstractSpec {
    fun generate(ctx: GenerationContext): String
}
