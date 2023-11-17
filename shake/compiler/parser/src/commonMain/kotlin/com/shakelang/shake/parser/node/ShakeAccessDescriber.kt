package com.shakelang.shake.parser.node

enum class ShakeAccessDescriber(
    val prefix: String?
) {
    PUBLIC("public"),
    PROTECTED("protected"),
    PACKAGE(null),
    PRIVATE("private");

    override fun toString(): String = name.lowercase()
}
