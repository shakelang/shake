package com.shakelang.shake.shakespeare.spec

import com.shakelang.shake.shakespeare.AbstractSpec

open class NamespaceSpec(vararg name: String) : AbstractSpec {

    val name = name.flatMap { it.split(".") }.toTypedArray()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NamespaceSpec) return false
        if (!name.contentEquals(other.name)) return false
        return true
    }

    override fun hashCode(): Int {
        return name.contentHashCode()
    }

    override fun generate(ctx: GenerationContext): String {
        return name.joinToString(".")
    }

    override fun toString(): String {
        return name.joinToString(".")
    }
}
