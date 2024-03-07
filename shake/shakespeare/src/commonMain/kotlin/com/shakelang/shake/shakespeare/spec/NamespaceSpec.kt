package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.AbstractSpec

open class NamespaceSpec(vararg val name: String) : AbstractSpec {
    override fun generate(ctx: GenerationContext): String {
        return name.joinToString(".")
    }

    override fun toString(): String {
        return name.joinToString(".")
    }
}
