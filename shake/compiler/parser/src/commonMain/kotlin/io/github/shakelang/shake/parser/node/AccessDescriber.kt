package io.github.shakelang.shake.parser.node

enum class AccessDescriber {
    PUBLIC, PROTECTED, PACKAGE, PRIVATE;
    override fun toString(): String = name
}