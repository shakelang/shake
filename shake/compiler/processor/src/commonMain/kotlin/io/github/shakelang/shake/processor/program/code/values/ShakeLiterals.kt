package io.github.shakelang.shake.processor.program.code.values

import io.github.shakelang.shake.processor.program.ShakeType
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeDoubleLiteral(val value: Double) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.DOUBLE

    override fun toString(): String = value.toString()

}

class ShakeIntegerLiteral(val value: Int) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.INT

    override fun toString(): String = value.toString()

}

class ShakeBooleanLiteral(val value: Boolean) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.BOOLEAN

    override fun toString(): String = value.toString()

    companion object {
        val TRUE = ShakeBooleanLiteral(true)
        val FALSE = ShakeBooleanLiteral(false)
    }

}

class ShakeCharacterLiteral(val value: Char) : ShakeValue {

    override val type: ShakeType
        get() = ShakeType.Primitives.CHAR

    override fun toString(): String = value.toString()

}