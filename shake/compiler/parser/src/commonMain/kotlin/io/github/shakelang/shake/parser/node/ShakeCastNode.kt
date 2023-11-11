package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ShakeCastNode(map: PositionMap, val value: ShakeValuedNode, val castTarget: CastTarget) :
    ShakeValuedNodeImpl(map) {

    class CastTarget @JvmOverloads constructor(val type: CastTargetType, val subtype: ShakeNamespaceNode? = null) {

        constructor(type: ShakeNamespaceNode?) : this(CastTargetType.OBJECT, type)

        enum class CastTargetType {
            BYTE, SHORT, INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR, STRING, OBJECT
        }

        override fun toString(): String {
            return subtype?.toString() ?: type.toString()
        }

        companion object {
            @JvmField
            val BYTE = CastTarget(CastTargetType.BYTE)

            @JvmField
            val SHORT = CastTarget(CastTargetType.SHORT)

            @JvmField
            val INTEGER = CastTarget(CastTargetType.INT)

            @JvmField
            val LONG = CastTarget(CastTargetType.LONG)

            @JvmField
            val FLOAT = CastTarget(CastTargetType.FLOAT)

            @JvmField
            val DOUBLE = CastTarget(CastTargetType.DOUBLE)

            @JvmField
            val BOOLEAN = CastTarget(CastTargetType.BOOLEAN)

            @JvmField
            val CHAR = CastTarget(CastTargetType.CHAR)

            @JvmField
            val STRING = CastTarget(CastTargetType.STRING)
        }
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to "CastNode",
            "value" to value.json,
            "cast_target" to castTarget.toString()
        )
}
