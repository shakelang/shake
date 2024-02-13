@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.spec

enum class AccessModifier(val value: String) {
    PUBLIC("public"),
    PROTECTED("protected"),
    PRIVATE("private"),
    PACKAGE_PRIVATE(""),

    ;

    override fun toString(): String {
        return value
    }

    fun prefix(): String {
        return if (this == PACKAGE_PRIVATE) "" else "$value "
    }
}
