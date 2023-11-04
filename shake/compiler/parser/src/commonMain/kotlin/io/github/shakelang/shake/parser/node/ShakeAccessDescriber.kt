package io.github.shakelang.shake.parser.node

enum class ShakeAccessDescriber {
    PUBLIC, PROTECTED, PACKAGE, PRIVATE;

    override fun toString(): String = name
}