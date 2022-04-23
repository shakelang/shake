package io.github.shakelang.shake.processor.program.types

interface ShakeClassField : ShakeField {
    val clazz: ShakeClass
    override val qualifiedName: String
}